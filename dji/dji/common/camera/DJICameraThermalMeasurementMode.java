package dji.common.camera;

public enum DJICameraThermalMeasurementMode {
    Disabled(0),
    SpotMetering(1),
    AreaMetering(2),
    Unknown(255);
    
    private int value;

    private DJICameraThermalMeasurementMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJICameraThermalMeasurementMode find(int i) {
        DJICameraThermalMeasurementMode dJICameraThermalMeasurementMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJICameraThermalMeasurementMode;
    }
}
