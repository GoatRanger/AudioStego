package dji.common.flightcontroller;

public enum DJIVirtualStickRollPitchControlMode {
    Angle(0),
    Velocity(1);
    
    private int data;

    private DJIVirtualStickRollPitchControlMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIVirtualStickRollPitchControlMode find(int i) {
        DJIVirtualStickRollPitchControlMode dJIVirtualStickRollPitchControlMode = Angle;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIVirtualStickRollPitchControlMode;
    }
}
