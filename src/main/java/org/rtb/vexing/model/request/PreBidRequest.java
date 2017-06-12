package org.rtb.vexing.model.request;

import java.util.List;

import com.iab.openrtb.request.App;
import com.iab.openrtb.request.Device;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class PreBidRequest {

    public String account_id;

    // FIXME Ensure there is at least one.
    public List<AdUnit> ad_units;

    // --- NOT REQUIRED ---

    /* Unique transaction ID. */
    String tid;

    /* How long to wait for adapters to return bids. */
    // FIXME Ensure value greater than 0 but no more than 2000. Use 0 as "not set" default?
    int timeout_millis;

    /*
     * Flag to indicate if the impression requires secure HTTPS URL creative
     * assets and markup, where 0 = non-secure, 1 = secure. If omitted, the
     * secure state will be interpreted from the request to the prebid server.
     */
    // FIXME Go code check "X-Forwarded-Proto" header for "https" or if TLS used on request.
    Integer secure;  // ... really just a boolean 0|1.

    /* Cache markup for two-phase response (get response then separate call to get markup). */
    Integer cache_markup;

    /*
     * This object should be included if the ad supported content is a
     * non-browser application (typically in mobile) as opposed to a website. At
     * a minimum, it is useful to provide an App ID or bundle, but this is not
     * strictly required.
     */
    // FIXME Go code has a lot of fallback behavior when App not defined, but then just errors checks if there.
    App app;

    /*
     * 3.2.18 Object: Device. This object provides information pertaining to
     * the device through which the user is interacting. Device information
     * includes its hardware, platform, location, and carrier data. The device
     * can refer to a mobile handset, a desktop computer, set top box, or other
     * digital device.
     */
    // FIXME Go code relies on empty Device to exist.
    Device device;

    // FIXME Go code creates a Bidders array based upon request and AdUnits.
}
