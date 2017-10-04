package dji.pilot.publics.e;

import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;

/* synthetic */ class d$1 {
    static final /* synthetic */ int[] a = new int[MotorStartFailedCause.values().length];

    static {
        try {
            a[MotorStartFailedCause.CompassError.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[MotorStartFailedCause.AssistantProtected.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[MotorStartFailedCause.DeviceLocked.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[MotorStartFailedCause.DistanceLimit.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            a[MotorStartFailedCause.IMUNeedCalibration.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            a[MotorStartFailedCause.IMUSNError.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            a[MotorStartFailedCause.IMUWarning.ordinal()] = 7;
        } catch (NoSuchFieldError e7) {
        }
        try {
            a[MotorStartFailedCause.CompassCalibrating.ordinal()] = 8;
        } catch (NoSuchFieldError e8) {
        }
        try {
            a[MotorStartFailedCause.AttiError.ordinal()] = 9;
        } catch (NoSuchFieldError e9) {
        }
        try {
            a[MotorStartFailedCause.NoviceProtected.ordinal()] = 10;
        } catch (NoSuchFieldError e10) {
        }
        try {
            a[MotorStartFailedCause.BatteryCellError.ordinal()] = 11;
        } catch (NoSuchFieldError e11) {
        }
        try {
            a[MotorStartFailedCause.BatteryCommuniteError.ordinal()] = 12;
        } catch (NoSuchFieldError e12) {
        }
        try {
            a[MotorStartFailedCause.SeriouLowVoltage.ordinal()] = 13;
        } catch (NoSuchFieldError e13) {
        }
        try {
            a[MotorStartFailedCause.SeriouLowPower.ordinal()] = 14;
        } catch (NoSuchFieldError e14) {
        }
        try {
            a[MotorStartFailedCause.LowVoltage.ordinal()] = 15;
        } catch (NoSuchFieldError e15) {
        }
        try {
            a[MotorStartFailedCause.TempureVolLow.ordinal()] = 16;
        } catch (NoSuchFieldError e16) {
        }
        try {
            a[MotorStartFailedCause.SmartLowToLand.ordinal()] = 17;
        } catch (NoSuchFieldError e17) {
        }
        try {
            a[MotorStartFailedCause.BatteryNotReady.ordinal()] = 18;
        } catch (NoSuchFieldError e18) {
        }
        try {
            a[MotorStartFailedCause.SimulatorMode.ordinal()] = 19;
        } catch (NoSuchFieldError e19) {
        }
        try {
            a[MotorStartFailedCause.PackMode.ordinal()] = 20;
        } catch (NoSuchFieldError e20) {
        }
        try {
            a[MotorStartFailedCause.AttitudeAbNormal.ordinal()] = 21;
        } catch (NoSuchFieldError e21) {
        }
        try {
            a[MotorStartFailedCause.UnActive.ordinal()] = 22;
        } catch (NoSuchFieldError e22) {
        }
        try {
            a[MotorStartFailedCause.FlyForbiddenError.ordinal()] = 23;
        } catch (NoSuchFieldError e23) {
        }
        try {
            a[MotorStartFailedCause.BiasError.ordinal()] = 24;
        } catch (NoSuchFieldError e24) {
        }
        try {
            a[MotorStartFailedCause.EscError.ordinal()] = 25;
        } catch (NoSuchFieldError e25) {
        }
        try {
            a[MotorStartFailedCause.ImuInitError.ordinal()] = 26;
        } catch (NoSuchFieldError e26) {
        }
        try {
            a[MotorStartFailedCause.SystemUpgrade.ordinal()] = 27;
        } catch (NoSuchFieldError e27) {
        }
        try {
            a[MotorStartFailedCause.SimulatorStarted.ordinal()] = 28;
        } catch (NoSuchFieldError e28) {
        }
        try {
            a[MotorStartFailedCause.ImuingError.ordinal()] = 29;
        } catch (NoSuchFieldError e29) {
        }
        try {
            a[MotorStartFailedCause.AttiAngleOver.ordinal()] = 30;
        } catch (NoSuchFieldError e30) {
        }
        try {
            a[MotorStartFailedCause.GyroscopeError.ordinal()] = 31;
        } catch (NoSuchFieldError e31) {
        }
        try {
            a[MotorStartFailedCause.AcceletorError.ordinal()] = 32;
        } catch (NoSuchFieldError e32) {
        }
        try {
            a[MotorStartFailedCause.CompassFailed.ordinal()] = 33;
        } catch (NoSuchFieldError e33) {
        }
        try {
            a[MotorStartFailedCause.BarometerError.ordinal()] = 34;
        } catch (NoSuchFieldError e34) {
        }
        try {
            a[MotorStartFailedCause.BarometerNegative.ordinal()] = 35;
        } catch (NoSuchFieldError e35) {
        }
        try {
            a[MotorStartFailedCause.CompassBig.ordinal()] = 36;
        } catch (NoSuchFieldError e36) {
        }
        try {
            a[MotorStartFailedCause.GyroscopeBiasBig.ordinal()] = 37;
        } catch (NoSuchFieldError e37) {
        }
        try {
            a[MotorStartFailedCause.AcceletorBiasBig.ordinal()] = 38;
        } catch (NoSuchFieldError e38) {
        }
        try {
            a[MotorStartFailedCause.CompassNoiseBig.ordinal()] = 39;
        } catch (NoSuchFieldError e39) {
        }
        try {
            a[MotorStartFailedCause.BarometerNoiseBig.ordinal()] = 40;
        } catch (NoSuchFieldError e40) {
        }
        try {
            a[MotorStartFailedCause.InvalidSn.ordinal()] = 41;
        } catch (NoSuchFieldError e41) {
        }
        try {
            a[MotorStartFailedCause.AircraftTypeMismatch.ordinal()] = 42;
        } catch (NoSuchFieldError e42) {
        }
        try {
            a[MotorStartFailedCause.M600_BAT_AUTH_ERR.ordinal()] = 43;
        } catch (NoSuchFieldError e43) {
        }
        try {
            a[MotorStartFailedCause.M600_BAT_COMM_ERR.ordinal()] = 44;
        } catch (NoSuchFieldError e44) {
        }
        try {
            a[MotorStartFailedCause.M600_BAT_TOO_LITTLE.ordinal()] = 45;
        } catch (NoSuchFieldError e45) {
        }
        try {
            a[MotorStartFailedCause.M600_BAT_DIF_VOLT_LARGE_1.ordinal()] = 46;
        } catch (NoSuchFieldError e46) {
        }
        try {
            a[MotorStartFailedCause.M600_BAT_DIF_VOLT_LARGE_2.ordinal()] = 47;
        } catch (NoSuchFieldError e47) {
        }
        try {
            a[MotorStartFailedCause.TOPOLOGY_ABNORMAL.ordinal()] = 48;
        } catch (NoSuchFieldError e48) {
        }
        try {
            a[MotorStartFailedCause.FoundUnfinishedModule.ordinal()] = 49;
        } catch (NoSuchFieldError e49) {
        }
        try {
            a[MotorStartFailedCause.IMUNoconnection.ordinal()] = 50;
        } catch (NoSuchFieldError e50) {
        }
        try {
            a[MotorStartFailedCause.IMUcCalibrationFinished.ordinal()] = 51;
        } catch (NoSuchFieldError e51) {
        }
        try {
            a[MotorStartFailedCause.NS_ABNORMAL.ordinal()] = 52;
        } catch (NoSuchFieldError e52) {
        }
        try {
            a[MotorStartFailedCause.RCCalibration.ordinal()] = 53;
        } catch (NoSuchFieldError e53) {
        }
        try {
            a[MotorStartFailedCause.RCCalibrationException.ordinal()] = 54;
        } catch (NoSuchFieldError e54) {
        }
        try {
            a[MotorStartFailedCause.RCCalibrationException2.ordinal()] = 55;
        } catch (NoSuchFieldError e55) {
        }
        try {
            a[MotorStartFailedCause.RCCalibrationException3.ordinal()] = 56;
        } catch (NoSuchFieldError e56) {
        }
        try {
            a[MotorStartFailedCause.RC_NEED_CALI.ordinal()] = 57;
        } catch (NoSuchFieldError e57) {
        }
        try {
            a[MotorStartFailedCause.RCCalibrationUnfinished.ordinal()] = 58;
        } catch (NoSuchFieldError e58) {
        }
        try {
            a[MotorStartFailedCause.SDCardException.ordinal()] = 59;
        } catch (NoSuchFieldError e59) {
        }
        try {
            a[MotorStartFailedCause.INVALID_FLOAT.ordinal()] = 60;
        } catch (NoSuchFieldError e60) {
        }
        try {
            a[MotorStartFailedCause.INVALID_VERSION.ordinal()] = 61;
        } catch (NoSuchFieldError e61) {
        }
        try {
            a[MotorStartFailedCause.BARO_ABNORMAL.ordinal()] = 62;
        } catch (NoSuchFieldError e62) {
        }
        try {
            a[MotorStartFailedCause.COMPASS_ABNORMAL.ordinal()] = 63;
        } catch (NoSuchFieldError e63) {
        }
        try {
            a[MotorStartFailedCause.FLASH_OPERATING.ordinal()] = 64;
        } catch (NoSuchFieldError e64) {
        }
        try {
            a[MotorStartFailedCause.GPS_ABNORMAL.ordinal()] = 65;
        } catch (NoSuchFieldError e65) {
        }
        try {
            a[MotorStartFailedCause.GPS_DISCONNECT.ordinal()] = 66;
        } catch (NoSuchFieldError e66) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_GYRO_ABNORMAL.ordinal()] = 67;
        } catch (NoSuchFieldError e67) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_ESC_PITCH_NON_DATA.ordinal()] = 68;
        } catch (NoSuchFieldError e68) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_ESC_ROLL_NON_DATA.ordinal()] = 69;
        } catch (NoSuchFieldError e69) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_ESC_YAW_NON_DATA.ordinal()] = 70;
        } catch (NoSuchFieldError e70) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_FIRM_IS_UPDATING.ordinal()] = 71;
        } catch (NoSuchFieldError e71) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_DISORDER.ordinal()] = 72;
        } catch (NoSuchFieldError e72) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_PITCH_SHOCK.ordinal()] = 73;
        } catch (NoSuchFieldError e73) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_ROLL_SHOCK.ordinal()] = 74;
        } catch (NoSuchFieldError e74) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_YAW_SHOCK.ordinal()] = 75;
        } catch (NoSuchFieldError e75) {
        }
        try {
            a[MotorStartFailedCause.RTK_BAD_SIGNAL.ordinal()] = 76;
        } catch (NoSuchFieldError e76) {
        }
        try {
            a[MotorStartFailedCause.RTK_DEVIATION_ERROR.ordinal()] = 77;
        } catch (NoSuchFieldError e77) {
        }
        try {
            a[MotorStartFailedCause.GIMBAL_IS_CALIBRATING.ordinal()] = 78;
        } catch (NoSuchFieldError e78) {
        }
        try {
            a[MotorStartFailedCause.ESC_CALIBRATING.ordinal()] = 79;
        } catch (NoSuchFieldError e79) {
        }
        try {
            a[MotorStartFailedCause.GPS_SIGN_INVALID.ordinal()] = 80;
        } catch (NoSuchFieldError e80) {
        }
        try {
            a[MotorStartFailedCause.LOCK_BY_APP.ordinal()] = 81;
        } catch (NoSuchFieldError e81) {
        }
        try {
            a[MotorStartFailedCause.START_FLY_HEIGHT_ERROR.ordinal()] = 82;
        } catch (NoSuchFieldError e82) {
        }
        try {
            a[MotorStartFailedCause.ESC_VERSION_NOT_MATCH.ordinal()] = 83;
        } catch (NoSuchFieldError e83) {
        }
        try {
            a[MotorStartFailedCause.IMU_ORI_NOT_MATCH.ordinal()] = 84;
        } catch (NoSuchFieldError e84) {
        }
        try {
            a[MotorStartFailedCause.STOP_BY_APP.ordinal()] = 85;
        } catch (NoSuchFieldError e85) {
        }
    }
}
