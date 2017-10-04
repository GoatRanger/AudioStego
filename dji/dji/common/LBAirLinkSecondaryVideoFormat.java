package dji.common;

public enum LBAirLinkSecondaryVideoFormat {
    Resolution720P60FPS(0),
    Resolution720P50FPS(1),
    Resolution1080I60FPS(2),
    Resolution1080I50FPS(3),
    Resolution1080P60FPS(4),
    Resolution1080P50FPS(5),
    Resolution1080P30FPS(6),
    Resolution1080P24FPS(7),
    Resolution1080P25FPS(8),
    Resolution720P30FPS(9),
    Resolution720P25FPS(10),
    Resolution720P24FPS(11),
    Unknown(255);
    
    private int value;

    private LBAirLinkSecondaryVideoFormat(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static LBAirLinkSecondaryVideoFormat find(int i) {
        LBAirLinkSecondaryVideoFormat lBAirLinkSecondaryVideoFormat = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return lBAirLinkSecondaryVideoFormat;
    }
}
