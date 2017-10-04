package dji.common;

public enum VideoDataChannel {
    FPVCamera(0),
    HDGimbal(1),
    HDMI(2),
    AV(3),
    Unknown(255);
    
    private int value;

    private VideoDataChannel(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static VideoDataChannel find(int i) {
        VideoDataChannel videoDataChannel = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return videoDataChannel;
    }
}
