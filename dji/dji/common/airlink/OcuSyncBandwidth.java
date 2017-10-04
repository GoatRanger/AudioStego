package dji.common.airlink;

public enum OcuSyncBandwidth {
    Bandwidth20MHz(0),
    Bandwidth10MHz(1),
    Unknown(255);
    
    private int value;

    private OcuSyncBandwidth(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static OcuSyncBandwidth find(int i) {
        OcuSyncBandwidth ocuSyncBandwidth = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return ocuSyncBandwidth;
    }
}
