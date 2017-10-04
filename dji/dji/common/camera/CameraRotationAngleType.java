package dji.common.camera;

public enum CameraRotationAngleType {
    Rotate0(0),
    Rotate90(1),
    Rotate180(2),
    Rotate270(3),
    Unknown(255);
    
    private int data;

    private CameraRotationAngleType(int i) {
        this.data = i;
    }

    public int value() {
        return this.data;
    }

    public boolean _equals(int i) {
        return this.data == i;
    }

    public static CameraRotationAngleType find(int i) {
        CameraRotationAngleType cameraRotationAngleType = Unknown;
        for (int i2 = 0; i2 < values().length; i2++) {
            if (values()[i2]._equals(i)) {
                return values()[i2];
            }
        }
        return cameraRotationAngleType;
    }
}
