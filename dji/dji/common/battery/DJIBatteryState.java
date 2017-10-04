package dji.common.battery;

public class DJIBatteryState {
    private int batteryEnergyRemainingPercent;
    private float batteryTemperature;
    private int currentCurrent;
    private int currentEnergy;
    private int currentVoltage;
    private int fullChargeEnergy;
    private boolean isBeingCharged;
    private int lifetimeRemainingPercent;
    private int numberOfDischarge;

    public DJIBatteryState(int i) {
        this.currentVoltage = i;
    }

    public DJIBatteryState(int i, int i2, int i3, int i4, int i5, int i6, float f, int i7) {
        this.fullChargeEnergy = i;
        this.currentEnergy = i2;
        this.currentVoltage = i3;
        this.currentCurrent = i4;
        this.lifetimeRemainingPercent = i5;
        this.batteryEnergyRemainingPercent = i6;
        this.batteryTemperature = f;
        this.numberOfDischarge = i7;
    }

    public int getFullChargeEnergy() {
        return this.fullChargeEnergy;
    }

    public int getCurrentEnergy() {
        return this.currentEnergy;
    }

    public int getCurrentVoltage() {
        return this.currentVoltage;
    }

    public int getCurrentCurrent() {
        return this.currentCurrent;
    }

    public int getLifetimeRemainingPercent() {
        return this.lifetimeRemainingPercent;
    }

    public int getBatteryEnergyRemainingPercent() {
        return this.batteryEnergyRemainingPercent;
    }

    public float getBatteryTemperature() {
        return this.batteryTemperature;
    }

    public int getNumberOfDischarge() {
        return this.numberOfDischarge;
    }

    public void setFullChargeEnergy(int i) {
        this.fullChargeEnergy = i;
    }

    public void setCurrentEnergy(int i) {
        this.currentEnergy = i;
    }

    public void setCurrentVoltage(int i) {
        this.currentVoltage = i;
    }

    public void setCurrentCurrent(int i) {
        this.currentCurrent = i;
    }

    public void setLifetimeRemainingPercent(int i) {
        this.lifetimeRemainingPercent = i;
    }

    public void setBatteryEnergyRemainingPercent(int i) {
        this.batteryEnergyRemainingPercent = i;
    }

    public void setBatteryTemperature(int i) {
        this.batteryTemperature = (float) i;
    }

    public void setNumberOfDischarge(int i) {
        this.numberOfDischarge = i;
    }

    public boolean isBeingCharged() {
        return this.isBeingCharged;
    }

    public void setBeingCharged(boolean z) {
        this.isBeingCharged = z;
    }
}
