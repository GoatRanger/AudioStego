package dji.common.flightcontroller;

public enum DJIVisionSectorWarning {
    Level1(0),
    Level2(1),
    Level3(3),
    Level4(4),
    Invalid(15),
    Unknown(255);
    
    private int value;

    private DJIVisionSectorWarning(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIVisionSectorWarning find(int i) {
        DJIVisionSectorWarning dJIVisionSectorWarning = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIVisionSectorWarning;
    }
}
