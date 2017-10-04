package dji.common.flightcontroller;

public class DJIAttitude {
    public double pitch;
    public double roll;
    public double yaw;

    public DJIAttitude(double d, double d2, double d3) {
        this.pitch = d;
        this.roll = d2;
        this.yaw = d3;
    }
}
