package dji.common.flightcontroller;

public class DJIVirtualStickFlightControlData {
    private float mPitch;
    private float mRoll;
    private float mVerticalThrottle;
    private float mYaw;

    public float getPitch() {
        return this.mPitch;
    }

    public void setPitch(float f) {
        this.mPitch = f;
    }

    public float getRoll() {
        return this.mRoll;
    }

    public void setRoll(float f) {
        this.mRoll = f;
    }

    public float getYaw() {
        return this.mYaw;
    }

    public void setYaw(float f) {
        this.mYaw = f;
    }

    public float getVerticalThrottle() {
        return this.mVerticalThrottle;
    }

    void setVerticalThrottle(float f) {
        this.mVerticalThrottle = f;
    }

    public DJIVirtualStickFlightControlData(float f, float f2, float f3, float f4) {
        this.mPitch = f;
        this.mRoll = f2;
        this.mYaw = f3;
        this.mVerticalThrottle = f4;
    }
}
