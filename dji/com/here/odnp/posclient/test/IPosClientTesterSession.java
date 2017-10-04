package com.here.odnp.posclient.test;

import com.here.odnp.ble.IBleManager;
import com.here.odnp.cell.ICellManager;
import com.here.odnp.gnss.IGnssManager;
import com.here.odnp.posclient.ICloseableSession;
import com.here.odnp.wifi.IWifiManager;
import com.here.posclient.ClientConfiguration;
import com.here.posclient.PositioningFeature;

public interface IPosClientTesterSession extends ICloseableSession {
    int availableFeatures();

    void clearData(int i);

    void dumpCachedData();

    void dumpFingerprints();

    boolean getActiveCollection();

    boolean getAutoUpload();

    ClientConfiguration getClientConfiguration();

    boolean getCollectionStatus();

    long getGnssFingerprintCount();

    long getNonGnssFingerprintCount();

    void handleGlobalLocationSettingChanged(boolean z);

    void resetPositioningFilter();

    boolean restoreMeasurementManagers();

    void sendFingerprints();

    boolean setActiveCollection(boolean z);

    boolean setAutoUpload(boolean z);

    boolean setBleManager(IBleManager iBleManager);

    boolean setCellManager(ICellManager iCellManager);

    boolean setGnssManager(IGnssManager iGnssManager);

    void setRadioMapDownloadPath(String str);

    void setUsername(String str);

    boolean setWifiManager(IWifiManager iWifiManager);

    void storeMeasurementManagers();

    void toggleFeature(PositioningFeature positioningFeature, boolean z);
}
