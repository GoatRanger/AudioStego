package dji.common.airlink;

public enum LBAirLinkChannelSelectionMode {
    Auto(0),
    Manual(1),
    Unknown(255);
    
    private int value;

    private LBAirLinkChannelSelectionMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkChannelSelectionMode find(int i) {
        LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkChannelSelectionMode;
    }
}
