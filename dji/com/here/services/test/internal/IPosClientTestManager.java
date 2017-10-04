package com.here.services.test.internal;

import android.os.Bundle;
import com.here.posclient.ClientConfiguration;

public interface IPosClientTestManager {
    int availableFeatures();

    void clearData(int i);

    void close();

    void dumpCachedData();

    void dumpData();

    void dumpFingerprints();

    void dumpHeapData();

    boolean getActiveCollection();

    boolean getAutoUpload();

    ClientConfiguration getClientConfiguration();

    boolean getCollectionStatus();

    long getGnssFingerprintCount();

    long getNonGnssFingerprintCount();

    void sendFingerprints();

    boolean setActiveCollection(boolean z);

    boolean setAutoUpload(boolean z);

    void setRadioMapDownloadPath(String str);

    void setUsername(String str);

    boolean startNetworkMeasurementPlayback(Bundle bundle);

    void stopNetworkMeasurementPlayback();

    void toggleFeature(String str, boolean z);
}
