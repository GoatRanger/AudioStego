package dji.common.flightcontroller;

public enum DJIFlightFailsafeOperation {
    Hover(0),
    Landing(1),
    GoHome(2),
    Unknown(255);
    
    private int data;

    private DJIFlightFailsafeOperation(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightFailsafeOperation find(int i) {
        DJIFlightFailsafeOperation dJIFlightFailsafeOperation = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightFailsafeOperation;
    }
}
