package dji.common.flightcontroller;

public class DJISimulatorStateData {
    private boolean areMotorsOn;
    private boolean isFlying;
    private double latitude;
    private double longitude;
    private float pitch;
    private float positionX;
    private float positionY;
    private float positionZ;
    private float roll;
    private float yaw;

    public boolean areMotorsOn() {
        return this.areMotorsOn;
    }

    public void setMotorsOn(boolean z) {
        this.areMotorsOn = z;
    }

    public boolean isFlying() {
        return this.isFlying;
    }

    public void setFlying(boolean z) {
        this.isFlying = z;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public float getRoll() {
        return this.roll;
    }

    public void setRoll(float f) {
        this.roll = f;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float f) {
        this.yaw = f;
    }

    public float getPositionX() {
        return this.positionX;
    }

    public void setPositionX(float f) {
        this.positionX = f;
    }

    public float getPositionY() {
        return this.positionY;
    }

    public void setPositionY(float f) {
        this.positionY = f;
    }

    public float getPositionZ() {
        return this.positionZ;
    }

    public void setPositionZ(float f) {
        this.positionZ = f;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }
}
