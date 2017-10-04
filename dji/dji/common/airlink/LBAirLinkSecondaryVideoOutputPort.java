package dji.common.airlink;

public enum LBAirLinkSecondaryVideoOutputPort {
    HDMI(0),
    SDI(1),
    Unknown(255);
    
    private int value;

    private LBAirLinkSecondaryVideoOutputPort(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkSecondaryVideoOutputPort find(int i) {
        LBAirLinkSecondaryVideoOutputPort lBAirLinkSecondaryVideoOutputPort = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkSecondaryVideoOutputPort;
    }
}
