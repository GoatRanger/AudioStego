package dji.common.airlink;

public enum LBAirLinkPIPDisplay {
    LB(0),
    Ext(1),
    PIPLB(2),
    PIPExt(3),
    Unknown(4);
    
    private int value;

    private LBAirLinkPIPDisplay(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkPIPDisplay find(int i) {
        LBAirLinkPIPDisplay lBAirLinkPIPDisplay = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkPIPDisplay;
    }
}
