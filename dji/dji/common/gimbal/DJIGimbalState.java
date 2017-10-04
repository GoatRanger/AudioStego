package dji.common.gimbal;

public class DJIGimbalState {
    private DJIGimbalLoadingBalanceStatus balanceState;
    private boolean isAttitudeReset = false;
    private boolean isCalibrating = false;
    private boolean isCallibrationSuccess = false;
    private boolean isMobileDeviceMounted;
    private boolean isMotorOverloaded;
    private boolean isPitchAtStop = false;
    private boolean isRollAtStop = false;
    private boolean isTestingBalance = false;
    private boolean isYawAtStop = false;
    private DJIGimbalAttitude mAttitudeInDegress = null;
    private float mRollFineTuneInDegrees = -1.0f;
    private DJIGimbalBalanceTestResult pitchTestResult;
    private DJIGimbalBalanceTestResult rollTestResult;
    private DJIGimbalWorkMode workMode = null;

    public DJIGimbalState(DJIGimbalAttitude dJIGimbalAttitude, int i, DJIGimbalWorkMode dJIGimbalWorkMode, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, DJIGimbalBalanceTestResult dJIGimbalBalanceTestResult, DJIGimbalBalanceTestResult dJIGimbalBalanceTestResult2) {
        this.mAttitudeInDegress = dJIGimbalAttitude;
        this.mRollFineTuneInDegrees = (float) i;
        this.workMode = dJIGimbalWorkMode;
        this.isAttitudeReset = z;
        this.isCalibrating = z2;
        this.isCallibrationSuccess = z3;
        this.isPitchAtStop = z4;
        this.isRollAtStop = z5;
        this.isYawAtStop = z6;
        this.pitchTestResult = dJIGimbalBalanceTestResult;
        this.rollTestResult = dJIGimbalBalanceTestResult2;
    }

    public DJIGimbalAttitude getAttitudeInDegrees() {
        return this.mAttitudeInDegress;
    }

    public float getRollFineTuneInDegrees() {
        return this.mRollFineTuneInDegrees;
    }

    public DJIGimbalWorkMode getWorkMode() {
        return this.workMode;
    }

    public boolean isAttitudeReset() {
        return this.isAttitudeReset;
    }

    public boolean isCalibrating() {
        return this.isCalibrating;
    }

    public boolean isCalibrationSuccess() {
        return this.isCallibrationSuccess;
    }

    public boolean isPitchAtStop() {
        return this.isPitchAtStop;
    }

    public boolean isRollAtStop() {
        return this.isRollAtStop;
    }

    public boolean isYawAtStop() {
        return this.isYawAtStop;
    }

    public boolean isTestingBalance() {
        return this.isTestingBalance;
    }

    public DJIGimbalBalanceTestResult getPitchTestResult() {
        return this.pitchTestResult;
    }

    public DJIGimbalBalanceTestResult getRollTestResult() {
        return this.rollTestResult;
    }

    public void setAttitude(DJIGimbalAttitude dJIGimbalAttitude) {
        this.mAttitudeInDegress = dJIGimbalAttitude;
    }

    public void setRollFineTune(int i) {
        this.mRollFineTuneInDegrees = ((float) i) / 10.0f;
    }

    public void setWorkMode(DJIGimbalWorkMode dJIGimbalWorkMode) {
        this.workMode = dJIGimbalWorkMode;
    }

    public void setAttitudeReset(boolean z) {
        this.isAttitudeReset = z;
    }

    public void setCalibrating(boolean z) {
        this.isCalibrating = z;
    }

    public void setPitchReachMax(boolean z) {
        this.isPitchAtStop = z;
    }

    public void setRollReachMax(boolean z) {
        this.isYawAtStop = z;
    }

    public void setYawReachMax(boolean z) {
        this.isYawAtStop = z;
    }

    public void setIsCalibrationSuccess(boolean z) {
        this.isCallibrationSuccess = z;
    }

    public void setIsTestingBalance(boolean z) {
        this.isTestingBalance = z;
    }

    public void setPitchTestResult(DJIGimbalBalanceTestResult dJIGimbalBalanceTestResult) {
        this.pitchTestResult = dJIGimbalBalanceTestResult;
    }

    public void setRollTestResult(DJIGimbalBalanceTestResult dJIGimbalBalanceTestResult) {
        this.rollTestResult = dJIGimbalBalanceTestResult;
    }

    public boolean isMobileDeviceMounted() {
        return this.isMobileDeviceMounted;
    }

    public void setMobileDeviceMounted(boolean z) {
        this.isMobileDeviceMounted = z;
    }

    public boolean isMotorOverloaded() {
        return this.isMotorOverloaded;
    }

    public void setMotorOverloaded(boolean z) {
        this.isMotorOverloaded = z;
    }

    public DJIGimbalLoadingBalanceStatus getBalanceState() {
        return this.balanceState;
    }

    public void setBalanceState(DJIGimbalLoadingBalanceStatus dJIGimbalLoadingBalanceStatus) {
        this.balanceState = dJIGimbalLoadingBalanceStatus;
    }
}
