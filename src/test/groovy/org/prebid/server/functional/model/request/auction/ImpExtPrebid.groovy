package org.prebid.server.functional.model.request.auction

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import groovy.transform.ToString

@ToString(includeNames = true, ignoreNulls = true)
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy)
class ImpExtPrebid {

    Bidder bidder
    StoredAuctionResponse storedAuctionResponse
    List<StoredBidResponse> storedBidResponse
    PrebidStoredRequest storedRequest
    ImpExtPrebidFloors floors

    static ImpExtPrebid getDefaultImpExtPrebid() {
        new ImpExtPrebid(bidder: Bidder.defaultBidder)
    }
}
