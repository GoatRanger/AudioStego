package com.here.odnp.posclient.test;

import com.here.odnp.ble.IBleManager;
import com.here.odnp.cell.ICellManager;
import com.here.odnp.gnss.IGnssManager;
import com.here.odnp.posclient.CloseableSession;
import com.here.odnp.posclient.PosClientManager;
import com.here.odnp.wifi.IWifiManager;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.PositioningFeature;

public class PosClientTesterSession extends CloseableSession implements IPosClientTesterSession {
    private static final String TAG = "odnp.posclient.test.PosClientTesterSession";
    private IBleManager mSavedBleManager;
    private ICellManager mSavedCellManager;
    private IGnssManager mSavedGnssManager;
    private IWifiManager mSavedWifiManager;

    public PosClientTesterSession(PosClientManager posClientManager) {
        super(posClientManager);
        this.mPosClientManager.addTesterSession(this);
    }

    public boolean setCellManager(ICellManager iCellManager) {
        return this.mPosClientManager.setCellManager(iCellManager);
    }

    public boolean setWifiManager(IWifiManager iWifiManager) {
        return this.mPosClientManager.setWifiManager(iWifiManager);
    }

    public boolean setGnssManager(IGnssManager iGnssManager) {
        return this.mPosClientManager.setGnssManager(iGnssManager);
    }

    public void storeMeasurementManagers() {
        if (this.mSavedCellManager == null) {
            this.mSavedCellManager = this.mPosClientManager.getCellManager();
        }
        if (this.mSavedWifiManager == null) {
            this.mSavedWifiManager = this.mPosClientManager.getWifiManager();
        }
        if (this.mSavedGnssManager == null) {
            this.mSavedGnssManager = this.mPosClientManager.getGnssManager();
        }
        if (this.mSavedBleManager == null) {
            this.mSavedBleManager = this.mPosClientManager.getBleManager();
        }
    }

    public boolean setBleManager(IBleManager iBleManager) {
        return this.mPosClientManager.setBleManager(iBleManager);
    }

    public boolean restoreMeasurementManagers() {
        try {
            boolean z;
            if (this.mSavedCellManager == null || this.mSavedCellManager.equals(this.mPosClientManager.getCellManager())) {
                z = false;
            } else {
                this.mPosClientManager.setCellManager(this.mSavedCellManager);
                z = true;
            }
            if (!(this.mSavedWifiManager == null || this.mSavedWifiManager.equals(this.mPosClientManager.getWifiManager()))) {
                this.mPosClientManager.setWifiManager(this.mSavedWifiManager);
                z = true;
            }
            if (!(this.mSavedGnssManager == null || this.mSavedGnssManager.equals(this.mPosClientManager.getGnssManager()))) {
                this.mPosClientManager.setGnssManager(this.mSavedGnssManager);
                z = true;
            }
            if (this.mSavedBleManager == null || this.mSavedBleManager.equals(this.mPosClientManager.getBleManager())) {
                return z;
            }
            this.mPosClientManager.setBleManager(this.mSavedBleManager);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clearData(int i) {
        this.mPosClientManager.clearData(i);
    }

    public void setUsername(String str) {
        this.mPosClientManager.setUsername(str);
    }

    public void toggleFeature(PositioningFeature positioningFeature, boolean z) {
        this.mPosClientManager.toggleFeature(positioningFeature, z);
    }

    public int availableFeatures() {
        return this.mPosClientManager.availableFeatures();
    }

    public void handleGlobalLocationSettingChanged(boolean z) {
        this.mPosClientManager.handleGlobalLocationSettingChanged(z);
    }

    public void resetPositioningFilter() {
        this.mPosClientManager.resetPositioningFilter();
    }

    public boolean getCollectionStatus() {
        return this.mPosClientManager.getCollectionStatus();
    }

    public void dumpFingerprints() {
        this.mPosClientManager.dumpFingerprints();
    }

    public void sendFingerprints() {
        this.mPosClientManager.sendFingerprints();
    }

    public boolean getActiveCollection() {
        return this.mPosClientManager.getActiveCollection();
    }

    public boolean setActiveCollection(boolean z) {
        return this.mPosClientManager.setActiveCollection(z);
    }

    public boolean getAutoUpload() {
        return this.mPosClientManager.getAutoUpload();
    }

    public boolean setAutoUpload(boolean z) {
        return this.mPosClientManager.setAutoUpload(z);
    }

    public long getGnssFingerprintCount() {
        return this.mPosClientManager.getGnssFingerprintCount();
    }

    public long getNonGnssFingerprintCount() {
        return this.mPosClientManager.getNonGnssFingerprintCount();
    }

    public void dumpCachedData() {
        this.mPosClientManager.dumpCachedData();
    }

    public void setRadioMapDownloadPath(String str) {
        this.mPosClientManager.setRadioMapPath(str);
    }

    public ClientConfiguration getClientConfiguration() {
        return this.mPosClientManager.getClientConfiguration();
    }

    protected void onOpen() {
    }

    protected void onClose() {
        if (restoreMeasurementManagers()) {
            resetPositioningFilter();
        }
        this.mPosClientManager.removeTesterSession(this);
    }
}
