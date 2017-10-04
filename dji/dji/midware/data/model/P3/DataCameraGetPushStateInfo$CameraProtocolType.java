package dji.midware.data.model.P3;

public enum DataCameraGetPushStateInfo$CameraProtocolType {
    Default(0),
    APP2_LIB(1),
    P3SSupport2_7k(2),
    FC350Support1080_120(3),
    FC350SupportDigiZoomAndOSMONO368(4),
    FC330XTureColor(7);
    
    private int data;

    private DataCameraGetPushStateInfo$CameraProtocolType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataCameraGetPushStateInfo$CameraProtocolType find(int i) {
        DataCameraGetPushStateInfo$CameraProtocolType dataCameraGetPushStateInfo$CameraProtocolType = Default;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataCameraGetPushStateInfo$CameraProtocolType;
    }
}
