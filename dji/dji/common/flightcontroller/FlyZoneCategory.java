package dji.common.flightcontroller;

public enum FlyZoneCategory {
    Warning(0),
    Authorization(1),
    Restricted(2),
    EnhancedWarning(3),
    Unknown(255);
    
    private int data;

    private FlyZoneCategory(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static FlyZoneCategory find(int i) {
        FlyZoneCategory flyZoneCategory = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return flyZoneCategory;
    }
}
