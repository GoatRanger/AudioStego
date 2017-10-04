package dji.common.flightcontroller;

public enum DJIVisionSystemWarning {
    Safe(0),
    Dangerous(3),
    Invalid(15),
    Unknown(255);
    
    private int value;

    private DJIVisionSystemWarning(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIVisionSystemWarning find(int i) {
        DJIVisionSystemWarning dJIVisionSystemWarning = Unknown;
        switch (i) {
            case 0:
            case 1:
            case 2:
                return Safe;
            case 3:
                return Dangerous;
            case 15:
                return Invalid;
            default:
                return Unknown;
        }
    }
}
