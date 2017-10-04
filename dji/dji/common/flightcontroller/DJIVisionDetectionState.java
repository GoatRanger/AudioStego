package dji.common.flightcontroller;

import java.util.List;

public class DJIVisionDetectionState {
    List<DJIVisionDetectionSector> detectionSectors;
    private boolean isBraking = false;
    private boolean isSensorWorking = false;
    private DJIVisionSystemWarning warningLevel;

    public DJIVisionDetectionState(boolean z, boolean z2, boolean z3, boolean z4, DJIVisionSystemWarning dJIVisionSystemWarning, List<DJIVisionDetectionSector> list) {
        this.isSensorWorking = z3;
        this.isBraking = z4;
        this.warningLevel = dJIVisionSystemWarning;
        this.detectionSectors = list;
    }

    public boolean isSensorWorking() {
        return this.isSensorWorking;
    }

    public boolean isBraking() {
        return this.isBraking;
    }

    public DJIVisionSystemWarning getWarningLevel() {
        return this.warningLevel;
    }

    public List<DJIVisionDetectionSector> getDetectionSectors() {
        return this.detectionSectors;
    }

    public void setSensorWorking(boolean z) {
        this.isSensorWorking = z;
    }

    public void setBraking(boolean z) {
        this.isBraking = z;
    }

    public void setWarningLevel(DJIVisionSystemWarning dJIVisionSystemWarning) {
        this.warningLevel = dJIVisionSystemWarning;
    }

    public void setDetectionSectors(List<DJIVisionDetectionSector> list) {
        this.detectionSectors = list;
    }
}
