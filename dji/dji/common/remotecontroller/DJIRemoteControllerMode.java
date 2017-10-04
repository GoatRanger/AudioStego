package dji.common.remotecontroller;

public enum DJIRemoteControllerMode {
    Master(0),
    Slave(1),
    Normal(2),
    Unknown(3);
    
    private int value;

    private DJIRemoteControllerMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIRemoteControllerMode find(int i) {
        DJIRemoteControllerMode dJIRemoteControllerMode = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRemoteControllerMode;
    }
}
