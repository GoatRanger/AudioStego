package dji.common.airlink;

public enum ChannelSelectionMode {
    Auto(0),
    Manual(1),
    Unknown(255);
    
    private int value;

    private ChannelSelectionMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static ChannelSelectionMode find(int i) {
        ChannelSelectionMode channelSelectionMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return channelSelectionMode;
    }
}
