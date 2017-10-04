package dji.midware.data.model.P3;

public enum DataCameraGetPushShotInfo$ShotFDType {
    FixedShotFD(0),
    ZoomShotFD(1),
    OTHER(6);
    
    private int data;

    private DataCameraGetPushShotInfo$ShotFDType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataCameraGetPushShotInfo$ShotFDType find(int i) {
        DataCameraGetPushShotInfo$ShotFDType dataCameraGetPushShotInfo$ShotFDType = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataCameraGetPushShotInfo$ShotFDType;
    }
}
