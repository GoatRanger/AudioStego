package dji.common.gimbal;

public class DJIGimbalAngleRotation {
    public float angle;
    public DJIGimbalRotateDirection direction;
    public boolean enable;

    public DJIGimbalAngleRotation(boolean z, float f, DJIGimbalRotateDirection dJIGimbalRotateDirection) {
        this.enable = z;
        this.angle = f;
        this.direction = dJIGimbalRotateDirection;
    }
}
