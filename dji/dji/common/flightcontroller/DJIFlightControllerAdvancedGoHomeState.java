package dji.common.flightcontroller;

public enum DJIFlightControllerAdvancedGoHomeState {
    None(0),
    TurningYaw(1),
    ExecutingGoHome(2),
    HoveringAtSafePoint(3),
    Planning(4),
    Unknown(255);
    
    private int data;

    private DJIFlightControllerAdvancedGoHomeState(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightControllerAdvancedGoHomeState find(int i) {
        DJIFlightControllerAdvancedGoHomeState dJIFlightControllerAdvancedGoHomeState = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightControllerAdvancedGoHomeState;
    }
}
