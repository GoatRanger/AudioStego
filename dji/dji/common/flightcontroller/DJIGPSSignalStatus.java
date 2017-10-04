package dji.common.flightcontroller;

public enum DJIGPSSignalStatus {
    Level0(0),
    Level1(1),
    Level2(2),
    Level3(3),
    Level4(4),
    Level5(5),
    None(255);
    
    private int data;

    private DJIGPSSignalStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DJIGPSSignalStatus find(int i) {
        DJIGPSSignalStatus dJIGPSSignalStatus = None;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIGPSSignalStatus;
    }
}
