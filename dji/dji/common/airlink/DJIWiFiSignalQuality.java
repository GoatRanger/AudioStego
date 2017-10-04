package dji.common.airlink;

public enum DJIWiFiSignalQuality {
    Good(0),
    Medium(1),
    bad(2),
    Unknown(100);
    
    private int value;

    private DJIWiFiSignalQuality(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIWiFiSignalQuality find(int i) {
        DJIWiFiSignalQuality dJIWiFiSignalQuality = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIWiFiSignalQuality;
    }
}
