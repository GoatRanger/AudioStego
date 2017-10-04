package dji.common.remotecontroller;

public enum DJIRCControlChannelName {
    Throttle(0),
    Pitch(1),
    Roll(2),
    Yaw(3);
    
    private int value;

    private DJIRCControlChannelName(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIRCControlChannelName find(int i) {
        DJIRCControlChannelName dJIRCControlChannelName = Throttle;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRCControlChannelName;
    }
}
