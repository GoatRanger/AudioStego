package dji.common;

public enum LBAirLinkPIPPosition {
    TopLeft(0),
    TopRight(1),
    BottomLeft(2),
    BottomRight(3),
    Unknown(255);
    
    private int value;

    private LBAirLinkPIPPosition(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkPIPPosition find(int i) {
        LBAirLinkPIPPosition lBAirLinkPIPPosition = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkPIPPosition;
    }
}
