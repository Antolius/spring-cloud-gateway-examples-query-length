package io.github.antolius.sgc.examples.query.length;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class NettyRequestDecoratorCustomizer
        implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    private final int maxInBytes;

    public NettyRequestDecoratorCustomizer(ServerProperties serverProperties) {
        maxInBytes = (int) serverProperties.getMaxHttpHeaderSize().toBytes();
    }

    @Override
    public void customize(NettyReactiveWebServerFactory factory) {
        factory.addServerCustomizers(
                server -> server.httpRequestDecoder(
                        reqDecorator -> reqDecorator
                                .maxInitialLineLength(maxInBytes)
                                .maxHeaderSize(maxInBytes)
                )
        );
    }
}
