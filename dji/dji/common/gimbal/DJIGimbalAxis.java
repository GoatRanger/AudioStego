package dji.common.gimbal;

public enum DJIGimbalAxis {
    Pitch(0),
    Yaw(1),
    Roll(2);
    
    private int value;

    private DJIGimbalAxis(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIGimbalAxis find(int i) {
        DJIGimbalAxis dJIGimbalAxis = Pitch;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIGimbalAxis;
    }
}
