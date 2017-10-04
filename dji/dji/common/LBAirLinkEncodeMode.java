package dji.common;

public enum LBAirLinkEncodeMode {
    Single(0),
    Dual(1),
    Unknown(255);
    
    private int value;

    private LBAirLinkEncodeMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkEncodeMode find(int i) {
        LBAirLinkEncodeMode lBAirLinkEncodeMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkEncodeMode;
    }
}
