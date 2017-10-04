package dji.common.gimbal;

public class DJIGimbalSpeedRotation {
    public float angleVelocity;
    public DJIGimbalRotateDirection direction;

    public DJIGimbalSpeedRotation(float f, DJIGimbalRotateDirection dJIGimbalRotateDirection) {
        this.angleVelocity = f;
        this.direction = dJIGimbalRotateDirection;
    }
}
