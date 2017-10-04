package dji.common.flightcontroller;

public enum DJILandingGearStatus {
    Unknown((byte) 0),
    Deployed((byte) 1),
    Retracted((byte) 3),
    Deploying((byte) 2),
    Retracting((byte) 4),
    Stopped((byte) 5);
    
    private byte _value;

    private DJILandingGearStatus(byte b) {
        this._value = (byte) 0;
        this._value = b;
    }

    public byte value() {
        return this._value;
    }

    public boolean _equals(byte b) {
        return this._value == b;
    }

    public static DJILandingGearStatus find(byte b) {
        DJILandingGearStatus dJILandingGearStatus = Unknown;
        for (int i = 0; i < values().length; i++) {
            if (values()[i]._equals(b)) {
                return values()[i];
            }
        }
        return dJILandingGearStatus;
    }
}
