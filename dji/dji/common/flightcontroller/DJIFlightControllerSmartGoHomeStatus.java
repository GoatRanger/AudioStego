package dji.common.flightcontroller;

public class DJIFlightControllerSmartGoHomeStatus {
    private boolean aircraftShouldGoHome;
    private int batteryPercentageNeededToGoHome;
    private float maxRadiusAircraftCanFlyAndGoHome;
    private int remainingFlightTime;
    private int timeNeededToGoHome;
    private int timeNeededToLandFromCurrentHeight;

    public int getRemainingFlightTime() {
        return this.remainingFlightTime;
    }

    public void setRemainingFlightTime(int i) {
        this.remainingFlightTime = i;
    }

    public int getTimeNeededToGoHome() {
        return this.timeNeededToGoHome;
    }

    public void setTimeNeededToGoHome(int i) {
        this.timeNeededToGoHome = i;
    }

    public int getTimeNeededToLandFromCurrentHeight() {
        return this.timeNeededToLandFromCurrentHeight;
    }

    public void setTimeNeededToLandFromCurrentHeight(int i) {
        this.timeNeededToLandFromCurrentHeight = i;
    }

    public int getBatteryPercentageNeededToGoHome() {
        return this.batteryPercentageNeededToGoHome;
    }

    public void setBatteryPercentageNeededToGoHome(int i) {
        this.batteryPercentageNeededToGoHome = i;
    }

    public float getMaxRadiusAircraftCanFlyAndGoHome() {
        return this.maxRadiusAircraftCanFlyAndGoHome;
    }

    public void setMaxRadiusAircraftCanFlyAndGoHome(float f) {
        this.maxRadiusAircraftCanFlyAndGoHome = f;
    }

    public boolean isAircraftShouldGoHome() {
        return this.aircraftShouldGoHome;
    }

    public void setAircraftShouldGoHome(boolean z) {
        this.aircraftShouldGoHome = z;
    }
}
