package dji.midware.data.model.P3;

public enum DataCameraGetPushStateInfo$EncryptStatus {
    NON_ENCRYPT(0),
    CHECK_FAILED(1),
    CHECK_SUCCESS(2),
    OTHER(7);
    
    private int data;

    private DataCameraGetPushStateInfo$EncryptStatus(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataCameraGetPushStateInfo$EncryptStatus find(int i) {
        DataCameraGetPushStateInfo$EncryptStatus dataCameraGetPushStateInfo$EncryptStatus = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataCameraGetPushStateInfo$EncryptStatus;
    }
}
