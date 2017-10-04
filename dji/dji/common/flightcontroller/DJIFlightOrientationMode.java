package dji.common.flightcontroller;

public enum DJIFlightOrientationMode {
    DefaultAircraftHeading(255),
    CourseLock(1),
    HomeLock(2);
    
    private int data;

    private DJIFlightOrientationMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIFlightOrientationMode find(int i) {
        DJIFlightOrientationMode dJIFlightOrientationMode = DefaultAircraftHeading;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIFlightOrientationMode;
    }
}
