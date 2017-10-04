package dji.common.flightcontroller;

public enum DJIVirtualStickFlightCoordinateSystem {
    Ground(0),
    Body(1);
    
    private int data;

    private DJIVirtualStickFlightCoordinateSystem(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIVirtualStickFlightCoordinateSystem find(int i) {
        DJIVirtualStickFlightCoordinateSystem dJIVirtualStickFlightCoordinateSystem = Ground;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIVirtualStickFlightCoordinateSystem;
    }
}
