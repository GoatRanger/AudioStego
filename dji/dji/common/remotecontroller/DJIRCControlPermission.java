package dji.common.remotecontroller;

public class DJIRCControlPermission {
    public boolean hasCaptureControlPermission;
    public boolean hasGimbalPitchControlPermission;
    public boolean hasGimbalRollControlPermission;
    public boolean hasGimbalYawControlPermission;
    public boolean hasPlaybackControlPermission;
    public boolean hasRecordControlPermission;

    public DJIRCControlPermission(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.hasGimbalYawControlPermission = z;
        this.hasGimbalRollControlPermission = z2;
        this.hasGimbalPitchControlPermission = z3;
        this.hasPlaybackControlPermission = z4;
        this.hasRecordControlPermission = z5;
        this.hasCaptureControlPermission = z6;
    }
}
