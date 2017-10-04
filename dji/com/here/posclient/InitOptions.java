package com.here.posclient;

public class InitOptions {
    public static final String KEY_OPTION_APP_ID = "com.here.posclient.InitOptions.appId";
    public static final String KEY_OPTION_CUSTOMER_ID = "com.here.posclient.InitOptions.customerId";
    public static final String KEY_OPTION_FEATURES = "com.here.posclient.InitOptions.features";
    public static final String KEY_OPTION_OFFLINE_MODE = "com.here.posclient.InitOptions.offlineMode";
    public static final String KEY_OPTION_RADIO_MAP_STORAGE = "com.here.posclient.InitOptions.radioMapStorage";
    private String mAppId;
    private String mCustomerId;
    private String mDataDir;
    private long mFeatures = PositioningFeature.All.value;
    private long mFlags = 0;
    private IMeasurementProvider mMeasurementProvider;
    private INetworkManager mNetworkManager;
    private IPosClientObserver mPosClientObserver;
    private IPowerManager mPowerManager;
    private String mRadioMapDir;
    private String mWorkingDir;

    private interface Flags {
        public static final int Dont_Start_Engines = 2;
        public static final int Dont_Use_Network = 4;
        public static final int None = 0;
        public static final int Remove_Persistent_data = 1;
    }

    public InitOptions setRemovePersistentData() {
        this.mFlags |= 1;
        return this;
    }

    public InitOptions setDontStartEngines() {
        this.mFlags |= 2;
        return this;
    }

    public InitOptions setDontUserNetwork() {
        this.mFlags |= 4;
        return this;
    }

    public long getFlags() {
        return this.mFlags;
    }

    public InitOptions setFeatures(long j) {
        this.mFeatures = j;
        return this;
    }

    public long getFeatures() {
        return this.mFeatures;
    }

    public InitOptions setPosClientObserver(IPosClientObserver iPosClientObserver) {
        this.mPosClientObserver = iPosClientObserver;
        return this;
    }

    public IPosClientObserver getPosClientObserver() {
        return this.mPosClientObserver;
    }

    public InitOptions setMeasurementProvider(IMeasurementProvider iMeasurementProvider) {
        this.mMeasurementProvider = iMeasurementProvider;
        return this;
    }

    public IMeasurementProvider getMeasurementProvider() {
        return this.mMeasurementProvider;
    }

    public InitOptions setNetworkManager(INetworkManager iNetworkManager) {
        this.mNetworkManager = iNetworkManager;
        return this;
    }

    public INetworkManager getNetworkManager() {
        return this.mNetworkManager;
    }

    public InitOptions setPowerManager(IPowerManager iPowerManager) {
        this.mPowerManager = iPowerManager;
        return this;
    }

    public IPowerManager getPowerManager() {
        return this.mPowerManager;
    }

    public InitOptions setWorkingDir(String str) {
        this.mWorkingDir = str;
        return this;
    }

    public String getWorkingDir() {
        return this.mWorkingDir;
    }

    public InitOptions setDataDir(String str) {
        this.mDataDir = str;
        return this;
    }

    public String getDataDir() {
        return this.mDataDir;
    }

    public InitOptions setRadioMapDir(String str) {
        this.mRadioMapDir = str;
        return this;
    }

    public String getRadioMapDir() {
        return this.mRadioMapDir;
    }

    public InitOptions setAppId(String str) {
        this.mAppId = str;
        return this;
    }

    public InitOptions setCustomerId(String str) {
        this.mCustomerId = str;
        return this;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getCustomerId() {
        return this.mCustomerId;
    }

    public boolean isValid() {
        return (this.mPosClientObserver == null || this.mMeasurementProvider == null || this.mNetworkManager == null || this.mPowerManager == null || this.mWorkingDir == null || this.mAppId == null) ? false : true;
    }
}
