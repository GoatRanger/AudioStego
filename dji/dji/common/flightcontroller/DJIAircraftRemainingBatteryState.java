package dji.common.flightcontroller;

public enum DJIAircraftRemainingBatteryState {
    Normal(0),
    Low(1),
    VeryLow(2),
    Reserved(255);
    
    private int data;

    private DJIAircraftRemainingBatteryState(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIAircraftRemainingBatteryState find(int i) {
        DJIAircraftRemainingBatteryState dJIAircraftRemainingBatteryState = Reserved;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIAircraftRemainingBatteryState;
    }
}
