package dji.common.battery;

public class DJIBatteryCell {
    private int voltage;

    public DJIBatteryCell(int i) {
        this.voltage = i;
    }

    public int getVoltage() {
        return this.voltage;
    }

    void setVoltage(int i) {
        this.voltage = i;
    }
}
