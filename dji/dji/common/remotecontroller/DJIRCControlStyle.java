package dji.common.remotecontroller;

public enum DJIRCControlStyle {
    Japanese(1),
    American(2),
    Chinese(3),
    Custom(4),
    SlaveDefault(0),
    SlaveCustom(1),
    Unknown(255);
    
    private int value;

    private DJIRCControlStyle(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static DJIRCControlStyle find(int i) {
        DJIRCControlStyle dJIRCControlStyle = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dJIRCControlStyle;
    }
}
