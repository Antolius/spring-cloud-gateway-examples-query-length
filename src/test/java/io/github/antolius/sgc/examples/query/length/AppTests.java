package io.github.antolius.sgc.examples.query.length;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
        properties = {"backend.url=http://localhost:${wiremock.server.port}"})
public class AppTests {

    @Autowired
    private WebTestClient client;

    @Test
    void shouldProxyRequests() {
        // given
        stubFor(get(urlEqualTo("/foo/bars"))
                .willReturn(aResponse().withStatus(200))
        );

        // when
        var actual = client.get().uri("/foo/bars").exchange();

        // then
        actual.expectStatus().isOk();
    }

    @Test
    void shouldSupportLongQueryParameters() {
        // given
        var givenQueryParamLength = 10_000;
        var givenQueryParam = new String(new char[givenQueryParamLength]).replaceAll("\0", "a");
        stubFor(get(urlEqualTo("/foo/bars?q=" + givenQueryParam))
                .willReturn(aResponse().withStatus(200))
        );

        // when
        var actual = client.get().uri("/foo/bars?q=" + givenQueryParam).exchange();

        // then
        actual.expectStatus().isOk();
    }

}
