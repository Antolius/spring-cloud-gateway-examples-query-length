package io.github.antolius.sgc.examples.query.length;

import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public Options wireMockOptions(@Value("${wiremock.server.port}") int port) {
        return WireMockConfiguration.options()
                .port(port)
                .jettyHeaderBufferSize(16_384);
    }

}
