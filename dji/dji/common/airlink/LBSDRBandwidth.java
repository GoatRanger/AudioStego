package dji.common.airlink;

public enum LBSDRBandwidth {
    Bandwidth20MHz(0),
    Bandwidth10MHz(1),
    Bandwidth5MHz(2),
    Unknown(255);
    
    private int value;

    private LBSDRBandwidth(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBSDRBandwidth find(int i) {
        LBSDRBandwidth lBSDRBandwidth = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBSDRBandwidth;
    }
}
