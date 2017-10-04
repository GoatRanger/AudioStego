package com.here.posclient;

import com.here.posclient.INetworkManager.Connection;

public class PosClientLib {

    public interface ClearItem {
        public static final int ACTIVE_STORAGE = 128;
        public static final int ALL = 1073741823;
        public static final int CALIBRATION = 256;
        public static final int CELL_CACHE = 4;
        public static final int CROWDSOURCING_FILES = 64;
        public static final int CURRENT_POSITION = 8;
        public static final int DONT_RESTART_ENGINES = 1073741824;
        public static final int LEARNING_CACHE = 16;
        public static final int ONLINE_LATEST_KNOWN_POSITION = 1;
        public static final int RADIO_MAP_FILES = 32;
        public static final int WLAN_CACHE = 2;
    }

    public enum ConnectionChangeAction {
        CONNECTION_CONNECTED,
        CONNECTION_DISCONNECTED,
        CONNECTION_CHANGED
    }

    public static native boolean clearData(int i);

    private static native boolean getClientConfiguration(ClientConfiguration clientConfiguration);

    public static native PositionEstimate getLastPosition();

    public static native void handleBleScanResult(long j, BleMeasurement[] bleMeasurementArr, boolean z);

    public static native void handleCellularScanResult(CellMeasurement cellMeasurement, boolean z);

    public static native void handleCellularStatusChanged(CellularStatus cellularStatus);

    public static native void handleConnectionChange(ConnectionChangeAction connectionChangeAction, Connection connection);

    public static native void handleGnssLocationUpdate(PositionEstimate positionEstimate, boolean z);

    public static native void handleGnssStatusChanged(GnssStatus gnssStatus);

    public static native void handleRequestError(int i, int i2);

    public static native void handleWifiScanResult(long j, WifiMeasurement[] wifiMeasurementArr, boolean z, boolean z2);

    public static native void handleWifiStatusChanged(int i);

    public static native boolean init(InitOptions initOptions);

    public static native boolean requestPosition(UpdateOptions updateOptions);

    public static native void setBatteryLevel(int i);

    public static native boolean setUpdateOptions(UpdateOptions updateOptions);

    public static native int startPositionUpdates();

    public static native void stopPositionUpdates();

    public static native void uninit();

    static {
        System.loadLibrary("gnustl_shared");
        System.loadLibrary("crypto_here");
        System.loadLibrary("ssl_here");
        System.loadLibrary("posclient");
    }

    public static ClientConfiguration getClientConfiguration() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        return getClientConfiguration(clientConfiguration) ? clientConfiguration : null;
    }
}
