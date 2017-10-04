package dji.common.battery;

public class DJIBatteryOverview {
    private int energyRemainingPercent;
    private int index;
    private boolean isConnected;

    public int getIndex() {
        return this.index;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public int getEnergyRemainingPercent() {
        return this.energyRemainingPercent;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setIsConnected(boolean z) {
        this.isConnected = z;
    }

    public void setEnergyRemainingPercent(int i) {
        this.energyRemainingPercent = i;
    }
}
