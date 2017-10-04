package com.here.services.test.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.here.odnp.posclient.IPosClientManager;
import com.here.posclient.ClientConfiguration;
import com.here.services.internal.IBoundService;
import com.here.services.test.internal.ILocationTestClient.Stub;

public class LocationTestClientService extends Stub implements IBoundService {
    private static final String TAG = "services.test.internal.PositioningTestClientService";
    private final IPosClientTestManager mPosClientTestManager;

    public LocationTestClientService(IPosClientManager iPosClientManager, Intent intent) {
        this.mPosClientTestManager = PosClientTestManager.create(iPosClientManager);
    }

    public void dumpData() {
        this.mPosClientTestManager.dumpData();
    }

    public void dumpHeapData() {
        this.mPosClientTestManager.dumpHeapData();
    }

    public void dumpCachedData() {
        this.mPosClientTestManager.dumpCachedData();
    }

    public void clearData(int i) {
        this.mPosClientTestManager.clearData(i);
    }

    public void setUsername(String str) {
        this.mPosClientTestManager.setUsername(str);
    }

    public void toggleFeature(String str, boolean z) throws RemoteException {
        this.mPosClientTestManager.toggleFeature(str, z);
    }

    public int availableFeatures() throws RemoteException {
        return this.mPosClientTestManager.availableFeatures();
    }

    public boolean getCollectionStatus() {
        return this.mPosClientTestManager.getCollectionStatus();
    }

    public void dumpFingerprints() {
        this.mPosClientTestManager.dumpFingerprints();
    }

    public void sendFingerprints() {
        this.mPosClientTestManager.sendFingerprints();
    }

    public boolean getActiveCollection() {
        return this.mPosClientTestManager.getActiveCollection();
    }

    public boolean setActiveCollection(boolean z) {
        return this.mPosClientTestManager.setActiveCollection(z);
    }

    public boolean getAutoUpload() {
        return this.mPosClientTestManager.getAutoUpload();
    }

    public boolean setAutoUpload(boolean z) {
        return this.mPosClientTestManager.setAutoUpload(z);
    }

    public long getGnssFingerprintCount() {
        return this.mPosClientTestManager.getGnssFingerprintCount();
    }

    public long getNonGnssFingerprintCount() {
        return this.mPosClientTestManager.getNonGnssFingerprintCount();
    }

    public void unBind() {
        this.mPosClientTestManager.close();
    }

    public ClientConfiguration getClientConfiguration() throws RemoteException {
        return this.mPosClientTestManager.getClientConfiguration();
    }
}
