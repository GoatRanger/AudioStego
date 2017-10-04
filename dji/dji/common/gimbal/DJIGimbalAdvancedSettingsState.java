package dji.common.gimbal;

public class DJIGimbalAdvancedSettingsState {
    int mJoystickPitchSmoothing;
    int mJoystickPitchSpeed;
    int mJoystickYawSmoothing;
    int mJoystickYawSpeed;
    int mSmoothTrackPitchAcceleration;
    int mSmoothTrackPitchDeadband;
    boolean mSmoothTrackPitchEnable;
    int mSmoothTrackPitchSpeed;
    int mSmoothTrackYawAcceleration;
    int mSmoothTrackYawDeadband;
    boolean mSmoothTrackYawEnable;
    int mSmoothTrackYawSpeed;
    DJIGimbalAdvancedSettingsProfile profile;

    public DJIGimbalAdvancedSettingsState(DJIGimbalAdvancedSettingsProfile dJIGimbalAdvancedSettingsProfile, boolean z, boolean z2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.profile = dJIGimbalAdvancedSettingsProfile;
        this.mSmoothTrackYawEnable = z;
        this.mSmoothTrackPitchEnable = z2;
        this.mSmoothTrackYawSpeed = i;
        this.mSmoothTrackPitchSpeed = i2;
        this.mSmoothTrackYawDeadband = i3;
        this.mSmoothTrackPitchDeadband = i4;
        this.mSmoothTrackYawAcceleration = i5;
        this.mSmoothTrackPitchAcceleration = i6;
        this.mJoystickYawSmoothing = i7;
        this.mJoystickPitchSmoothing = i8;
        this.mJoystickYawSpeed = i9;
        this.mJoystickPitchSpeed = i10;
    }

    public DJIGimbalAdvancedSettingsProfile getAdvancedSettingsProfile() {
        return this.profile;
    }

    public boolean isSmoothTrackEnabledYaw() {
        return this.mSmoothTrackYawEnable;
    }

    public boolean isSmoothTrackEnabledPitch() {
        return this.mSmoothTrackPitchEnable;
    }

    public int getSmoothTrackSpeedYaw() {
        return this.mSmoothTrackYawSpeed;
    }

    public int getSmoothTrackSpeedPitch() {
        return this.mSmoothTrackPitchSpeed;
    }

    public int getSmoothTrackDeadbandYaw() {
        return this.mSmoothTrackYawDeadband;
    }

    public int getSmoothTrackDeadbandPitch() {
        return this.mSmoothTrackPitchDeadband;
    }

    public int getSmoothTrackAccelerationYaw() {
        return this.mSmoothTrackYawAcceleration;
    }

    public int getSmoothTrackAccelerationPitch() {
        return this.mSmoothTrackPitchAcceleration;
    }

    public int getControllerSmoothingYaw() {
        return this.mJoystickYawSmoothing;
    }

    public int getControllerSmoothingPitch() {
        return this.mJoystickPitchSmoothing;
    }

    public int getControllerSpeedYaw() {
        return this.mJoystickYawSpeed;
    }

    public int getControllerSpeedPitch() {
        return this.mJoystickPitchSpeed;
    }
}
