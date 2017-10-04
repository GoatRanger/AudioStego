package dji.midware.data.model.P3;

public enum DataCameraGetPushStateInfo$RecordType {
    NO(0),
    START(1),
    STARTING(2),
    STOP(3),
    OTHER(7);
    
    private int data;

    private DataCameraGetPushStateInfo$RecordType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataCameraGetPushStateInfo$RecordType find(int i) {
        DataCameraGetPushStateInfo$RecordType dataCameraGetPushStateInfo$RecordType = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataCameraGetPushStateInfo$RecordType;
    }
}
