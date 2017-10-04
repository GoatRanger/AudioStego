package dji.common.remotecontroller;

public enum DJIRCJoinMasterResult {
    Successful(0),
    PasswordError(1),
    Rejected(2),
    Maximum(3),
    ResponseTimeout(4),
    Unknown(5);
    
    private int value;

    private DJIRCJoinMasterResult(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIRCJoinMasterResult find(int i) {
        DJIRCJoinMasterResult dJIRCJoinMasterResult = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRCJoinMasterResult;
    }
}
