package dji.common.flightcontroller;

public enum DJIIMUSensorState {
    Unknown(255),
    Disconnect(1),
    Calibrating(2),
    Failed(3),
    DataException(4),
    WarmingUp(5),
    Motion(6),
    BiasNormal(7),
    BiasMedium(8),
    BiasLarge(9);
    
    private int data;

    private DJIIMUSensorState(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIIMUSensorState find(int i) {
        DJIIMUSensorState dJIIMUSensorState = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIIMUSensorState;
    }
}
