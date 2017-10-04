package dji.common.flightcontroller;

public class DJIRTKReceiverInfo {
    private boolean isChannelAvailable;
    private int satelliteCount;

    void setChannelAvailable(boolean z) {
        this.isChannelAvailable = z;
    }

    void setSatelliteCount(int i) {
        this.satelliteCount = i;
    }

    public boolean isConstellationSupported() {
        return this.isChannelAvailable;
    }

    public int getSatelliteCount() {
        return this.satelliteCount;
    }

    public DJIRTKReceiverInfo(boolean z, int i) {
        this.isChannelAvailable = z;
        this.satelliteCount = i;
    }
}
