package dji.common.flightcontroller;

public enum DJIVirtualStickVerticalControlMode {
    Velocity(0),
    Position(1);
    
    private int data;

    private DJIVirtualStickVerticalControlMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIVirtualStickVerticalControlMode find(int i) {
        DJIVirtualStickVerticalControlMode dJIVirtualStickVerticalControlMode = Velocity;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIVirtualStickVerticalControlMode;
    }
}
