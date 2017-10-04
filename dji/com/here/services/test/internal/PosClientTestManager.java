package com.here.services.test.internal;

import android.content.Context;
import android.os.Bundle;
import com.here.odnp.debug.DebugFile;
import com.here.odnp.debug.DebugInfo;
import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.posclient.test.IPosClientTesterSession;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.PositioningFeature;

public class PosClientTestManager implements IPosClientTestManager {
    private static final String TAG = "services.test.internal.PosClientTestManager";
    private final Context mContext = this.mPosClientManager.getContext();
    private final IPosClientManager mPosClientManager;
    private final IPosClientTesterSession mPosClientTesterSession = this.mPosClientManager.createTesterSession();

    private PosClientTestManager(IPosClientManager iPosClientManager) {
        this.mPosClientManager = iPosClientManager;
    }

    public static IPosClientTestManager create(IPosClientManager iPosClientManager) {
        return new PosClientTestManager(iPosClientManager);
    }

    public void dumpData() {
        if (this.mPosClientTesterSession.open()) {
            DebugFile.dumpData(this.mContext);
        }
    }

    public void dumpHeapData() {
        if (this.mPosClientTesterSession.open()) {
            DebugInfo.dumpHprofData();
        }
    }

    public void clearData(int i) {
        if (this.mPosClientTesterSession.open()) {
            this.mPosClientTesterSession.clearData(i);
        }
    }

    public void dumpCachedData() {
        if (this.mPosClientTesterSession.open()) {
            this.mPosClientTesterSession.dumpCachedData();
        }
    }

    public void setUsername(String str) {
        if (this.mPosClientTesterSession.open()) {
            this.mPosClientTesterSession.setUsername(str);
        }
    }

    public void toggleFeature(String str, boolean z) {
        if (this.mPosClientTesterSession.open()) {
            try {
                this.mPosClientTesterSession.toggleFeature(PositioningFeature.valueOf(str), z);
            } catch (Exception e) {
            }
        }
    }

    public int availableFeatures() {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.availableFeatures();
        }
        return 0;
    }

    public boolean getCollectionStatus() {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.getCollectionStatus();
        }
        return false;
    }

    public void dumpFingerprints() {
        if (this.mPosClientTesterSession.open()) {
            this.mPosClientTesterSession.dumpFingerprints();
        }
    }

    public void sendFingerprints() {
        if (this.mPosClientTesterSession.open()) {
            this.mPosClientTesterSession.sendFingerprints();
        }
    }

    public boolean getActiveCollection() {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.getActiveCollection();
        }
        return false;
    }

    public boolean setActiveCollection(boolean z) {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.setActiveCollection(z);
        }
        return false;
    }

    public boolean getAutoUpload() {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.getAutoUpload();
        }
        return false;
    }

    public boolean setAutoUpload(boolean z) {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.setAutoUpload(z);
        }
        return false;
    }

    public long getGnssFingerprintCount() {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.getGnssFingerprintCount();
        }
        return 0;
    }

    public long getNonGnssFingerprintCount() {
        if (this.mPosClientTesterSession.open()) {
            return this.mPosClientTesterSession.getNonGnssFingerprintCount();
        }
        return 0;
    }

    public void setRadioMapDownloadPath(String str) {
        this.mPosClientTesterSession.setRadioMapDownloadPath(str);
    }

    public void close() {
        if (this.mPosClientTesterSession != null) {
            this.mPosClientTesterSession.close();
        }
    }

    public ClientConfiguration getClientConfiguration() {
        if (this.mPosClientTesterSession == null) {
            return null;
        }
        return this.mPosClientTesterSession.getClientConfiguration();
    }

    public boolean startNetworkMeasurementPlayback(Bundle bundle) {
        return false;
    }

    public void stopNetworkMeasurementPlayback() {
    }
}
