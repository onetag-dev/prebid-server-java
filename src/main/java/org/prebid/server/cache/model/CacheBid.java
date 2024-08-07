package org.prebid.server.cache.model;

import com.iab.openrtb.response.Bid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.prebid.server.auction.model.BidInfo;
import org.prebid.server.cache.CoreCacheService;

/**
 * Holds the information about cache TTL for particular {@link Bid} to be send to {@link CoreCacheService}.
 */
@AllArgsConstructor(staticName = "of")
@Value
public class CacheBid {

    BidInfo bidInfo;

    Integer ttl;
}
