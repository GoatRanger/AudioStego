package dji.midware.data.model.P3;

public enum DataCameraGetPushShotParams$PanoMode {
    Auto360(1),
    Ball(2),
    Self(3),
    Manual(4),
    Auto180(5),
    VERTICAL(6),
    SECTORIAL(7),
    OTHER(0);
    
    private int data;

    private DataCameraGetPushShotParams$PanoMode(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static DataCameraGetPushShotParams$PanoMode find(int i) {
        DataCameraGetPushShotParams$PanoMode dataCameraGetPushShotParams$PanoMode = OTHER;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return dataCameraGetPushShotParams$PanoMode;
    }
}
