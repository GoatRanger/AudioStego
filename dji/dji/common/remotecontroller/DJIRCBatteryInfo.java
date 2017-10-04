package dji.common.remotecontroller;

public class DJIRCBatteryInfo {
    public int remainingEnergyInMAh;
    public int remainingEnergyInPercent;

    public DJIRCBatteryInfo(int i, int i2) {
        this.remainingEnergyInMAh = i;
        this.remainingEnergyInPercent = i2;
    }
}
