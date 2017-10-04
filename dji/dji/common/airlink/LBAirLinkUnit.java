package dji.common.airlink;

public enum LBAirLinkUnit {
    Imperial(0),
    Metric(1),
    Unknown(255);
    
    private int value;

    private LBAirLinkUnit(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkUnit find(int i) {
        LBAirLinkUnit lBAirLinkUnit = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkUnit;
    }
}
