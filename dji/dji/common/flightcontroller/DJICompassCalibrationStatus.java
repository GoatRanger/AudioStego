package dji.common.flightcontroller;

public enum DJICompassCalibrationStatus {
    Normal(4),
    Horizontal(0),
    Vertical(1),
    Succeeded(2),
    Failed(3);
    
    private int data;

    private DJICompassCalibrationStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJICompassCalibrationStatus find(int i) {
        DJICompassCalibrationStatus dJICompassCalibrationStatus = Failed;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJICompassCalibrationStatus;
    }
}
