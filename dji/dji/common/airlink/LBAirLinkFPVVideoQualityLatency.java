package dji.common.airlink;

public enum LBAirLinkFPVVideoQualityLatency {
    HighQuality(0),
    LowLatency(1),
    Unknown(255);
    
    private int value;

    private LBAirLinkFPVVideoQualityLatency(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkFPVVideoQualityLatency find(int i) {
        LBAirLinkFPVVideoQualityLatency lBAirLinkFPVVideoQualityLatency = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkFPVVideoQualityLatency;
    }
}
