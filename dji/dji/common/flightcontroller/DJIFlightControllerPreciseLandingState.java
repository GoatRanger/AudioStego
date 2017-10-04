package dji.common.flightcontroller;

public enum DJIFlightControllerPreciseLandingState {
    None(0),
    TurningYaw(1),
    Landing(2),
    Unknown(255);
    
    private int data;

    private DJIFlightControllerPreciseLandingState(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightControllerPreciseLandingState find(int i) {
        DJIFlightControllerPreciseLandingState dJIFlightControllerPreciseLandingState = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightControllerPreciseLandingState;
    }
}
