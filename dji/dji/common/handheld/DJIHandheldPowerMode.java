package dji.common.handheld;

public enum DJIHandheldPowerMode {
    Awake(0),
    Sleeping(1),
    PowerOff(2),
    Unknown(255);
    
    private int value;

    private DJIHandheldPowerMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIHandheldPowerMode find(int i) {
        DJIHandheldPowerMode dJIHandheldPowerMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIHandheldPowerMode;
    }
}
