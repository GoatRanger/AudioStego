package dji.common.gimbal;

public enum DJIGimbalRotateAngleMode {
    RelativeAngle(0),
    AbsoluteAngle(1);
    
    private int value;

    private DJIGimbalRotateAngleMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIGimbalRotateAngleMode find(int i) {
        DJIGimbalRotateAngleMode dJIGimbalRotateAngleMode = RelativeAngle;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIGimbalRotateAngleMode;
    }
}
