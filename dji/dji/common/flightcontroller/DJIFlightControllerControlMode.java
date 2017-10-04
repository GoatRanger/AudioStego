package dji.common.flightcontroller;

public enum DJIFlightControllerControlMode {
    Manual(0),
    Smart(2),
    Unknown(255);
    
    private int data;

    private DJIFlightControllerControlMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightControllerControlMode find(int i) {
        DJIFlightControllerControlMode dJIFlightControllerControlMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightControllerControlMode;
    }
}
