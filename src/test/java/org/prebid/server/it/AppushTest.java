package org.prebid.server.it;

import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.prebid.server.model.Endpoint;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static java.util.Collections.singletonList;

public class AppushTest extends IntegrationTest {

    @Test
    public void openrtb2AuctionShouldRespondWithBidsFromAppush() throws IOException, JSONException {
        // given
        WIRE_MOCK_RULE.stubFor(post(urlPathEqualTo("/appush-exchange"))
                .withRequestBody(equalToJson(jsonFrom("openrtb2/appush/test-appush-bid-request.json")))
                .willReturn(aResponse().withBody(jsonFrom("openrtb2/appush/test-appush-bid-response.json"))));

        // when
        final Response response = responseFor("openrtb2/appush/test-auction-appush-request.json",
                Endpoint.openrtb2_auction);

        // then
        assertJsonEquals("openrtb2/appush/test-auction-appush-response.json", response, singletonList("appush"));
    }
}
