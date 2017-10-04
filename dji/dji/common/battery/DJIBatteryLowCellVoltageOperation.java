package dji.common.battery;

public enum DJIBatteryLowCellVoltageOperation {
    LEDWarning(0),
    GoHome(1),
    Landing(2),
    Unknown(255);
    
    private int data;

    private DJIBatteryLowCellVoltageOperation(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIBatteryLowCellVoltageOperation find(int i) {
        DJIBatteryLowCellVoltageOperation dJIBatteryLowCellVoltageOperation = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIBatteryLowCellVoltageOperation;
    }
}
