package dji.common.remotecontroller;

public enum DJIRCRequestGimbalControlResult {
    Agree(0),
    Deny(1),
    Timeout(2),
    Authorized(3),
    Unknown(4);
    
    private int value;

    private DJIRCRequestGimbalControlResult(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIRCRequestGimbalControlResult find(int i) {
        DJIRCRequestGimbalControlResult dJIRCRequestGimbalControlResult = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRCRequestGimbalControlResult;
    }
}
