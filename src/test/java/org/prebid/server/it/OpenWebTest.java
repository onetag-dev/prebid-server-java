package org.prebid.server.it;

import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.prebid.server.model.Endpoint;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.Collections.singletonList;

public class OpenWebTest extends IntegrationTest {

    @Test
    public void openrtb2AuctionShouldRespondWithBidsFromOpenWeb() throws IOException, JSONException {
        // given
        WIRE_MOCK_RULE.stubFor(post(urlPathEqualTo("/openweb-exchange"))
                .withQueryParam("publisher_id", equalTo("1"))
                .withRequestBody(equalToJson(jsonFrom("openrtb2/openweb/test-openweb-bid-request.json")))
                .willReturn(aResponse().withBody(jsonFrom("openrtb2/openweb/test-openweb-bid-response.json"))));

        // when
        final Response response = responseFor("openrtb2/openweb/test-auction-openweb-request.json",
                Endpoint.openrtb2_auction);

        // then
        assertJsonEquals("openrtb2/openweb/test-auction-openweb-response.json", response, singletonList("openweb"));
    }
}
