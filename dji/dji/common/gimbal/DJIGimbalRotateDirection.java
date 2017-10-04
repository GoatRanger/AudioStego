package dji.common.gimbal;

public enum DJIGimbalRotateDirection {
    Clockwise(0),
    CounterClockwise(1);
    
    private int value;

    private DJIGimbalRotateDirection(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIGimbalRotateDirection find(int i) {
        DJIGimbalRotateDirection dJIGimbalRotateDirection = Clockwise;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIGimbalRotateDirection;
    }
}
