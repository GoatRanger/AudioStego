package dji.common.airlink;

public enum LBAirLinkDataRate {
    Bandwidth4Mbps(1),
    Bandwidth6Mbps(2),
    Bandwidth8Mbps(3),
    Bandwidth10Mbps(4),
    Unknown(255);
    
    private int value;

    private LBAirLinkDataRate(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkDataRate find(int i) {
        LBAirLinkDataRate lBAirLinkDataRate = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkDataRate;
    }
}
