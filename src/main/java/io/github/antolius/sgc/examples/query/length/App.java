package io.github.antolius.sgc.examples.query.length;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${backend.url}") String backendUrl
    ) {
        return builder.routes()
                .route(r -> r.path("/foo/bars").uri(backendUrl))
                .build();
    }

    @Bean
    public NettyReactiveWebServerFactory nettyFactory(ServerProperties serverProperties) {
        var maxInBytes = (int) serverProperties.getMaxHttpHeaderSize().toBytes();
        var factory = new NettyReactiveWebServerFactory();
        factory.addServerCustomizers(
                server -> server.httpRequestDecoder(
                        reqDecoder -> reqDecoder
                                .maxInitialLineLength(maxInBytes)
                                .maxHeaderSize(maxInBytes)
                )
        );
        return factory;
    }

}
