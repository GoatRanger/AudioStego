package dji.common.flightcontroller;

public enum FlyZoneShape {
    Cylinder(0),
    Cone(1),
    Unknown(255);
    
    private int data;

    private FlyZoneShape(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static FlyZoneShape find(int i) {
        FlyZoneShape flyZoneShape = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return flyZoneShape;
    }
}
