package dji.common.remotecontroller;

public enum DJIRCToAircraftPairingState {
    None(0),
    Pairing(1),
    Completed(2),
    Unknown(3);
    
    private int value;

    private DJIRCToAircraftPairingState(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIRCToAircraftPairingState find(int i) {
        DJIRCToAircraftPairingState dJIRCToAircraftPairingState = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRCToAircraftPairingState;
    }
}
