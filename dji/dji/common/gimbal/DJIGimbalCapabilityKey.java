package dji.common.gimbal;

import dji.common.util.DJIParamCapability;
import dji.common.util.DJIParamMinMaxCapability;

public enum DJIGimbalCapabilityKey {
    AdjustPitch(DJIParamMinMaxCapability.class),
    AdjustYaw(DJIParamMinMaxCapability.class),
    AdjustRoll(DJIParamMinMaxCapability.class),
    PitchRangeExtension(DJIParamMinMaxCapability.class),
    ControllerSpeedPitch(DJIParamMinMaxCapability.class),
    ControllerSpeedYaw(DJIParamMinMaxCapability.class),
    ControllerSmoothingPitch(DJIParamMinMaxCapability.class),
    ControllerSmoothingYaw(DJIParamMinMaxCapability.class),
    ControllerDeadbandPitch(DJIParamMinMaxCapability.class),
    ControllerDeadbandYaw(DJIParamMinMaxCapability.class),
    SmoothTrackEnabledPitch(DJIParamCapability.class),
    SmoothTrackEnabledYaw(DJIParamCapability.class),
    SmoothTrackAccelerationPitch(DJIParamMinMaxCapability.class),
    SmoothTrackAccelerationYaw(DJIParamMinMaxCapability.class),
    SmoothTrackSpeedPitch(DJIParamMinMaxCapability.class),
    SmoothTrackSpeedYaw(DJIParamMinMaxCapability.class),
    SmoothTrackDeadbandPitch(DJIParamMinMaxCapability.class),
    SmoothTrackDeadbandYaw(DJIParamMinMaxCapability.class),
    EndpointPitchUp(DJIParamMinMaxCapability.class),
    EndpointPitchDown(DJIParamMinMaxCapability.class),
    EndpointYawLeft(DJIParamMinMaxCapability.class),
    EndpointYawRight(DJIParamMinMaxCapability.class),
    MotorControlStiffnessPitch(DJIParamMinMaxCapability.class),
    MotorControlStiffnessYaw(DJIParamMinMaxCapability.class),
    MotorControlStiffnessRoll(DJIParamMinMaxCapability.class),
    MotorControlStrengthPitch(DJIParamMinMaxCapability.class),
    MotorControlStrengthYaw(DJIParamMinMaxCapability.class),
    MotorControlStrengthRoll(DJIParamMinMaxCapability.class),
    MotorControlGyroFilteringPitch(DJIParamMinMaxCapability.class),
    MotorControlGyroFilteringYaw(DJIParamMinMaxCapability.class),
    MotorControlGyroFilteringRoll(DJIParamMinMaxCapability.class),
    MotorControlPrecontrolPitch(DJIParamMinMaxCapability.class),
    MotorControlPrecontrolYaw(DJIParamMinMaxCapability.class),
    MotorControlPrecontrolRoll(DJIParamMinMaxCapability.class),
    AdvancedSettingsProfile(DJIParamCapability.class);
    
    private Class<? extends DJIParamCapability> capabilityCls;

    public String value() {
        return name();
    }

    public Class<? extends DJIParamCapability> capabilityClass() {
        return this.capabilityCls;
    }

    private DJIGimbalCapabilityKey(Class<? extends DJIParamCapability> cls) {
        this.capabilityCls = cls;
    }
}
