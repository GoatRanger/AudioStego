package dji.common.flightcontroller;

public class DJIIMUState {
    private DJIIMUSensorState acceleratorState;
    private int calibrationProgress;
    private DJIIMUCalibrationStatus calibrationStatus;
    private DJIIMUSensorState gyroscopeState;
    private int imuID;
    private boolean isConnected = true;

    public void setIsConnected(boolean z) {
        this.isConnected = z;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public void setImuID(int i) {
        this.imuID = i;
    }

    public int getImuId() {
        return this.imuID;
    }

    public void setGyroscopeState(DJIIMUSensorState dJIIMUSensorState) {
        this.gyroscopeState = dJIIMUSensorState;
    }

    public DJIIMUSensorState getGyroscopeState() {
        return this.gyroscopeState;
    }

    public void setAcceleratorState(DJIIMUSensorState dJIIMUSensorState) {
        this.acceleratorState = dJIIMUSensorState;
    }

    public DJIIMUSensorState getAcceleratorState() {
        return this.acceleratorState;
    }

    public void setCalibrationProgress(int i) {
        this.calibrationProgress = i;
    }

    public int getCalibrationProgress() {
        return this.calibrationProgress;
    }

    public void setCalibrationStatus(DJIIMUCalibrationStatus dJIIMUCalibrationStatus) {
        this.calibrationStatus = dJIIMUCalibrationStatus;
    }

    public DJIIMUCalibrationStatus getCalibrationStatus() {
        return this.calibrationStatus;
    }
}
