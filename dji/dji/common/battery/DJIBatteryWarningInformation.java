package dji.common.battery;

public class DJIBatteryWarningInformation {
    private boolean currentOverload;
    private boolean customDischargeEnabled;
    private short damagedBatteryCellIndex;
    private boolean lowTemperature;
    private boolean overHeating;
    private boolean shortCircuit;
    private short underVoltageBatteryCellIndex;

    public DJIBatteryWarningInformation(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, short s, short s2) {
        this.currentOverload = z;
        this.overHeating = z2;
        this.lowTemperature = z3;
        this.shortCircuit = z4;
        this.customDischargeEnabled = z5;
        this.underVoltageBatteryCellIndex = s;
        this.damagedBatteryCellIndex = s2;
    }

    public DJIBatteryWarningInformation(long j) {
        boolean z = true;
        boolean z2 = (j & 1) == 1 || (j & 2) == 2;
        this.currentOverload = z2;
        if ((j & 4) == 4 || (8 & j) == 8) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.overHeating = z2;
        if ((16 & j) == 16 || (32 & j) == 32) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.lowTemperature = z2;
        if ((64 & j) == 64) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.shortCircuit = z2;
        if ((2097152 & j) != 2097152) {
            z = false;
        }
        this.customDischargeEnabled = z;
        this.underVoltageBatteryCellIndex = (short) ((int) (3968 & j));
        this.damagedBatteryCellIndex = (short) ((int) (126976 & j));
    }

    public DJIBatteryWarningInformation(int i) {
        boolean z = true;
        boolean z2 = (i & 1) == 1 || (i & 2) == 2;
        this.currentOverload = z2;
        if ((i & 4) == 4 || (i & 8) == 8) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.overHeating = z2;
        if ((i & 16) == 16 || (i & 32) == 32) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.lowTemperature = z2;
        if ((i & 64) == 64) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.shortCircuit = z2;
        if ((i & 131072) != 131072) {
            z = false;
        }
        this.customDischargeEnabled = z;
        this.underVoltageBatteryCellIndex = (short) ((i >> 7) & 8);
        this.damagedBatteryCellIndex = (short) ((i >> 10) & 8);
    }

    public boolean isCurrentOverload() {
        return this.currentOverload;
    }

    public boolean isOverHeating() {
        return this.overHeating;
    }

    public boolean isLowTemperature() {
        return this.lowTemperature;
    }

    public boolean isShortCircuit() {
        return this.shortCircuit;
    }

    public boolean isCustomDischargeEnabled() {
        return this.customDischargeEnabled;
    }

    public short getUnderVoltageBatteryCellIndex() {
        return this.underVoltageBatteryCellIndex;
    }

    public short getDamagedBatteryCellIndex() {
        return this.damagedBatteryCellIndex;
    }

    public void setCurrentOverload(boolean z) {
        this.currentOverload = z;
    }

    void setOverHeating(boolean z) {
        this.overHeating = z;
    }

    void setLowTemperature(boolean z) {
        this.lowTemperature = z;
    }

    void setShortCircuit(boolean z) {
        this.shortCircuit = z;
    }

    void setCustomDischargeEnabled(boolean z) {
        this.customDischargeEnabled = z;
    }

    void setUnderVoltageBatteryCellIndex(short s) {
        this.underVoltageBatteryCellIndex = s;
    }

    void setDamagedBatteryCellIndex(short s) {
        this.damagedBatteryCellIndex = s;
    }

    public boolean hasError() {
        return this.currentOverload || this.overHeating || this.lowTemperature || this.shortCircuit || this.underVoltageBatteryCellIndex != (short) 0 || this.damagedBatteryCellIndex != (short) 0 || this.customDischargeEnabled;
    }
}
