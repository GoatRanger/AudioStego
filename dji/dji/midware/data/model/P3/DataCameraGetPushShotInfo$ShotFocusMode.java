package dji.midware.data.model.P3;

public enum DataCameraGetPushShotInfo$ShotFocusMode {
    Manual(0),
    Auto(1),
    OTHER(6);
    
    private int data;

    private DataCameraGetPushShotInfo$ShotFocusMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataCameraGetPushShotInfo$ShotFocusMode find(int i) {
        DataCameraGetPushShotInfo$ShotFocusMode dataCameraGetPushShotInfo$ShotFocusMode = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataCameraGetPushShotInfo$ShotFocusMode;
    }
}
