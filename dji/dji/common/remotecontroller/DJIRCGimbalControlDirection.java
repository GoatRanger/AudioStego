package dji.common.remotecontroller;

public enum DJIRCGimbalControlDirection {
    Pitch(0),
    Roll(1),
    Yaw(2);
    
    private int value;

    private DJIRCGimbalControlDirection(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIRCGimbalControlDirection find(int i) {
        DJIRCGimbalControlDirection dJIRCGimbalControlDirection = Pitch;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRCGimbalControlDirection;
    }
}
