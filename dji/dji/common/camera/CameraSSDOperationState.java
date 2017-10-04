package dji.common.camera;

public enum CameraSSDOperationState {
    Idle(1),
    Saving(2),
    Formatting(4),
    Initializing(5),
    Error(7),
    Full(8),
    Unknown(255);
    
    private int value;

    private CameraSSDOperationState(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static CameraSSDOperationState find(int i) {
        CameraSSDOperationState cameraSSDOperationState;
        CameraSSDOperationState cameraSSDOperationState2 = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                cameraSSDOperationState = values()[i2];
                break;
            }
        }
        cameraSSDOperationState = cameraSSDOperationState2;
        if (i == 0) {
            return Unknown;
        }
        if (i == 6) {
            return Error;
        }
        return cameraSSDOperationState;
    }
}
