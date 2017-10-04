package dji.midware.data.model.P3;

public enum DataSpecialControl$PlayCtrType {
    START(0),
    STOP(1),
    PAUSE(2),
    FastForward(3),
    FastRewind(4),
    TouchProgress(5),
    OTHER(100);
    
    private int data;

    private DataSpecialControl$PlayCtrType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataSpecialControl$PlayCtrType find(int i) {
        DataSpecialControl$PlayCtrType dataSpecialControl$PlayCtrType = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataSpecialControl$PlayCtrType;
    }
}
