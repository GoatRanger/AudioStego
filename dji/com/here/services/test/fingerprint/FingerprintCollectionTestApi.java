package com.here.services.test.fingerprint;

import com.here.services.HereLocationApiClient;

public interface FingerprintCollectionTestApi {
    void dumpFingerprints(HereLocationApiClient hereLocationApiClient);

    boolean getActiveCollection(HereLocationApiClient hereLocationApiClient);

    boolean getAutoUpload(HereLocationApiClient hereLocationApiClient);

    boolean getCollectionStatus(HereLocationApiClient hereLocationApiClient);

    long getGnssFingerprintCount(HereLocationApiClient hereLocationApiClient);

    long getNonGnssFingerprintCount(HereLocationApiClient hereLocationApiClient);

    void sendFingerprints(HereLocationApiClient hereLocationApiClient);

    boolean setActiveCollection(HereLocationApiClient hereLocationApiClient, boolean z);

    boolean setAutoUpload(HereLocationApiClient hereLocationApiClient, boolean z);

    void setUsername(HereLocationApiClient hereLocationApiClient, String str);
}
