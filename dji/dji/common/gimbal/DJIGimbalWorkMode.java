package dji.common.gimbal;

public enum DJIGimbalWorkMode {
    FreeMode(0),
    FpvMode(1),
    YawFollowMode(2),
    Unknown(255);
    
    private int value;

    private DJIGimbalWorkMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIGimbalWorkMode find(int i) {
        DJIGimbalWorkMode dJIGimbalWorkMode = FreeMode;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIGimbalWorkMode;
    }
}
