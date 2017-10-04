package dji.common.flightcontroller;

public enum DJIRTKPositioningSolution {
    None(0),
    SinglePoint(16),
    Float(34),
    FixedPoint(50);
    
    private int data;

    private DJIRTKPositioningSolution(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIRTKPositioningSolution find(int i) {
        DJIRTKPositioningSolution dJIRTKPositioningSolution = None;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRTKPositioningSolution;
    }
}
