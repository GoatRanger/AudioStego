package dji.midware.data.model.P3;

public enum DataSpecialControl$MulDelValue {
    ALL_CANCEL((byte) -4),
    ALL_SELECT((byte) -3),
    PAGE_CANCEL((byte) -2),
    PAGE_SELECT((byte) -1),
    INVALID((byte) 0);
    
    private byte data;

    private DataSpecialControl$MulDelValue(byte b) {
        this.data = b;
    }

    public byte value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataSpecialControl$MulDelValue find(byte b) {
        DataSpecialControl$MulDelValue dataSpecialControl$MulDelValue = INVALID;
        for (int i = 0; i < values().length; i++) {
            if (values()[i]._equals(b)) {
                return values()[i];
            }
        }
        return dataSpecialControl$MulDelValue;
    }
}
