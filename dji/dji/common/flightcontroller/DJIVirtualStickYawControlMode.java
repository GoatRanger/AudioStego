package dji.common.flightcontroller;

public enum DJIVirtualStickYawControlMode {
    Angle(0),
    AngularVelocity(1);
    
    private int data;

    private DJIVirtualStickYawControlMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIVirtualStickYawControlMode find(int i) {
        DJIVirtualStickYawControlMode dJIVirtualStickYawControlMode = Angle;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIVirtualStickYawControlMode;
    }
}
