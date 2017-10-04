package dji.common.gimbal;

public class DJIGimbalAttitude {
    public float pitch;
    public float roll;
    public float yaw;

    public DJIGimbalAttitude(float f, float f2, float f3) {
        this.pitch = f;
        this.roll = f2;
        this.yaw = f3;
    }
}
