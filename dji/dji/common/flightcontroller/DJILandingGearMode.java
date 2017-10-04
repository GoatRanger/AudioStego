package dji.common.flightcontroller;

public enum DJILandingGearMode {
    Transport(0),
    Auto(1),
    Normal(2),
    OTHER(3);
    
    private int data;

    private DJILandingGearMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJILandingGearMode find(int i) {
        DJILandingGearMode dJILandingGearMode = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJILandingGearMode;
    }
}
