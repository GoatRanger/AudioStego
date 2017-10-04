package dji.sdksharedlib.hardware.abstractions;

public enum DJISDKCacheUpdateType {
    DYNAMIC(0),
    USER_DRIVEN(1),
    Unknown(255);
    
    private int value;

    private DJISDKCacheUpdateType(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJISDKCacheUpdateType find(int i) {
        DJISDKCacheUpdateType dJISDKCacheUpdateType = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJISDKCacheUpdateType;
    }
}
