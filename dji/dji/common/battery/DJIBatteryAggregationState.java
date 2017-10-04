package dji.common.battery;

public class DJIBatteryAggregationState {
    private boolean batteryDisconnected;
    private DJIBatteryOverview[] batteryOverviews;
    private int currentCurrent;
    private int currentEnergy;
    private int currentVoltage;
    private int energyRemainingPercent;
    private boolean firmwareDifferenceDetected;
    private int fullChargeEnergy;
    private boolean hasDamagedCell;
    private int highestBatteryTemperature;
    private boolean lowCellVoltageDetected;
    private int numberOfConnectedBatteries;
    private boolean voltageDifferenceDetected;

    public int getNumberOfConnectedBatteries() {
        return this.numberOfConnectedBatteries;
    }

    public DJIBatteryOverview[] getBatteryOverviews() {
        return this.batteryOverviews;
    }

    public int getCurrentVoltage() {
        return this.currentVoltage;
    }

    public int getCurrentCurrent() {
        return this.currentCurrent;
    }

    public int getFullChargeEnergy() {
        return this.fullChargeEnergy;
    }

    public int getCurrentEnergy() {
        return this.currentEnergy;
    }

    public int getEnergyRemainingPercent() {
        return this.energyRemainingPercent;
    }

    public int getHighestBatteryTemperature() {
        return this.highestBatteryTemperature;
    }

    public boolean getBatteryDisconnected() {
        return this.batteryDisconnected;
    }

    public boolean getVoltageDifferenceDetected() {
        return this.voltageDifferenceDetected;
    }

    public boolean getLowCellVoltageDetected() {
        return this.lowCellVoltageDetected;
    }

    public boolean hasDamagedCell() {
        return this.hasDamagedCell;
    }

    public boolean firmwareDifferenceDetected() {
        return this.firmwareDifferenceDetected;
    }

    public void setNumberOfConnectedBatteries(int i) {
        this.numberOfConnectedBatteries = i;
    }

    public void setBatteryOverviews(DJIBatteryOverview[] dJIBatteryOverviewArr) {
        this.batteryOverviews = dJIBatteryOverviewArr;
    }

    public void setCurrentVoltage(int i) {
        this.currentVoltage = i;
    }

    public void setCurrentCurrent(int i) {
        this.currentCurrent = i;
    }

    public void setFullChargeEnergy(int i) {
        this.fullChargeEnergy = i;
    }

    public void setCurrentEnergy(int i) {
        this.currentEnergy = i;
    }

    public void setEnergyRemainingPercent(int i) {
        this.energyRemainingPercent = i;
    }

    public void setHighestBatteryTemperature(int i) {
        this.highestBatteryTemperature = i;
    }

    public void setBatteryDisconnected(boolean z) {
        this.batteryDisconnected = z;
    }

    public void setVoltageDifferenceDetected(boolean z) {
        this.voltageDifferenceDetected = z;
    }

    public void setLowCellVoltageDetected(boolean z) {
        this.lowCellVoltageDetected = z;
    }

    public void setHasDamagedCell(boolean z) {
        this.hasDamagedCell = z;
    }

    public void setFirmwareDifferenceDetected(boolean z) {
        this.firmwareDifferenceDetected = z;
    }
}
