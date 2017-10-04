package dji.midware.data.model.P3;

public enum DataSpecialControl$FlyGoHomeStaus {
    INIT((byte) 1),
    START((byte) 2),
    EXIT((byte) 3);
    
    private byte mData;

    private DataSpecialControl$FlyGoHomeStaus(byte b) {
        this.mData = (byte) 1;
        this.mData = b;
    }

    public byte value() {
        return this.mData;
    }

    public boolean _equals(int i) {
        return this.mData == i;
    }

    public static DataSpecialControl$FlyGoHomeStaus find(int i) {
        DataSpecialControl$FlyGoHomeStaus dataSpecialControl$FlyGoHomeStaus = INIT;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataSpecialControl$FlyGoHomeStaus;
    }
}
