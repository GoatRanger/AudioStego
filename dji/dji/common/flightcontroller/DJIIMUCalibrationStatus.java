package dji.common.flightcontroller;

public enum DJIIMUCalibrationStatus {
    Initialization(0),
    InProgress(1),
    Succeed(2),
    Failed(3),
    None(4),
    Unknown(255);
    
    private int data;

    private DJIIMUCalibrationStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIIMUCalibrationStatus find(int i) {
        DJIIMUCalibrationStatus dJIIMUCalibrationStatus = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIIMUCalibrationStatus;
    }
}
