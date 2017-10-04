package dji.common.camera;

public enum CameraSSDCapacity {
    Capacity_256G(0),
    Capacity_512G(1),
    Capacity_1T(2),
    Unknown(255);
    
    private int value;

    private CameraSSDCapacity(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public boolean _equals(int i) {
        return this.value == i;
    }

    public static CameraSSDCapacity find(int i) {
        CameraSSDCapacity cameraSSDCapacity = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return cameraSSDCapacity;
    }
}
