package com.here.posclient;

import dji.pilot.usercenter.protocol.d;

public class CellMeasurement {
    public int gciL1Value;
    public int gciL2Value;
    public int gciL3Value;
    public int gciL4Value;
    public boolean hasGciL3Value;
    public boolean hasGciL4Value;
    public boolean hasLciL1Value;
    public boolean hasLciL2Value;
    public boolean hasLciL3Value;
    public int lciL1Value;
    public int lciL2Value;
    public int lciL3Value;
    public long measurementId;
    public boolean simulated;
    public long timeStamp;
    public RANType type = RANType.UNKNOWN;

    public enum RANType {
        UNKNOWN,
        GERAN,
        UTRAFDD,
        UTRATDD,
        EUTRA,
        CDMA
    }

    public boolean isEqual(CellMeasurement cellMeasurement) {
        if (cellMeasurement == null || this.type != cellMeasurement.type || this.gciL1Value != cellMeasurement.gciL1Value || this.gciL2Value != cellMeasurement.gciL2Value || this.hasGciL3Value != cellMeasurement.hasGciL3Value) {
            return false;
        }
        if ((this.hasGciL3Value && this.gciL3Value != cellMeasurement.gciL3Value) || this.hasGciL4Value != cellMeasurement.hasGciL4Value) {
            return false;
        }
        if ((this.hasGciL4Value && this.gciL4Value != cellMeasurement.gciL4Value) || this.hasLciL1Value != cellMeasurement.hasLciL1Value) {
            return false;
        }
        if ((this.hasLciL1Value && this.lciL1Value != cellMeasurement.lciL1Value) || this.hasLciL2Value != cellMeasurement.hasLciL2Value) {
            return false;
        }
        if ((this.hasLciL2Value && this.lciL2Value != cellMeasurement.lciL2Value) || this.hasLciL3Value != cellMeasurement.hasLciL3Value) {
            return false;
        }
        if ((!this.hasLciL3Value || this.lciL3Value == cellMeasurement.lciL3Value) && this.simulated == cellMeasurement.simulated) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[TYPE:").append(this.type.name());
        stringBuilder.append(" MCC:").append(this.gciL1Value);
        stringBuilder.append(" MNC:").append(this.gciL2Value);
        if (this.hasGciL3Value) {
            if (this.type == RANType.EUTRA) {
                stringBuilder.append(" TAC:");
            } else {
                stringBuilder.append(" LAC:");
            }
            stringBuilder.append(this.gciL3Value);
        }
        if (this.hasGciL4Value) {
            stringBuilder.append(" CID:").append(this.gciL4Value);
        }
        if (this.simulated) {
            stringBuilder.append(" SIMULATED");
        }
        stringBuilder.append(d.H);
        return stringBuilder.toString();
    }
}
