package dji.common.remotecontroller;

public class DJIRCGimbalControlSpeed {
    public short pitchSpeed;
    public short rollSpeed;
    public short yawSpeed;

    public DJIRCGimbalControlSpeed(short s, short s2, short s3) {
        this.pitchSpeed = s;
        this.rollSpeed = s2;
        this.yawSpeed = s3;
    }
}
