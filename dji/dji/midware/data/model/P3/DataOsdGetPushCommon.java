package dji.midware.data.model.P3;

import android.support.v4.media.TransportMediator;
import dji.logic.d.b;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.a.c;
import dji.pilot.usercenter.protocol.d;
import dji.pilot.usercenter.protocol.e;

public class DataOsdGetPushCommon extends c {
    private static DataOsdGetPushCommon instance = null;

    public enum BatteryType {
        Unknown(0),
        NonSmart(1),
        Smart(2);
        
        private int _value;

        private BatteryType(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static BatteryType find(int i) {
            for (BatteryType batteryType : values()) {
                if (batteryType._equals(i)) {
                    return batteryType;
                }
            }
            return Unknown;
        }
    }

    public enum DroneType {
        Unknown(0),
        Inspire(1),
        P3S(2),
        P3X(3),
        P3C(4),
        OpenFrame(5),
        ACEONE(6),
        WKM(7),
        NAZA(8),
        A2(9),
        A3(10),
        P4(11),
        PM820(14),
        P34K(15),
        wm220(16),
        Orange2(17),
        Pomato(18),
        N3(20),
        PM820PRO(23),
        NoFlyc(255),
        None(100);
        
        private int _value;

        private DroneType(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static DroneType find(int i) {
            for (DroneType droneType : values()) {
                if (droneType._equals(i)) {
                    return droneType;
                }
            }
            return None;
        }
    }

    public enum FLIGHT_ACTION {
        NONE(0),
        WARNING_POWER_GOHOME(1),
        WARNING_POWER_LANDING(2),
        SMART_POWER_GOHOME(3),
        SMART_POWER_LANDING(4),
        LOW_VOLTAGE_LANDING(5),
        LOW_VOLTAGE_GOHOME(6),
        SERIOUS_LOW_VOLTAGE_LANDING(7),
        RC_ONEKEY_GOHOME(8),
        RC_ASSISTANT_TAKEOFF(9),
        RC_AUTO_TAKEOFF(10),
        RC_AUTO_LANDING(11),
        APP_AUTO_GOHOME(12),
        APP_AUTO_LANDING(13),
        APP_AUTO_TAKEOFF(14),
        OUTOF_CONTROL_GOHOME(15),
        API_AUTO_TAKEOFF(16),
        API_AUTO_LANDING(17),
        API_AUTO_GOHOME(18),
        AVOID_GROUND_LANDING(19),
        AIRPORT_AVOID_LANDING(20),
        TOO_CLOSE_GOHOME_LANDING(21),
        TOO_FAR_GOHOME_LANDING(22),
        APP_WP_MISSION(23),
        WP_AUTO_TAKEOFF(24),
        GOHOME_AVOID(25),
        GOHOME_FINISH(26),
        VERT_LOW_LIMIT_LANDING(27),
        BATTERY_FORCE_LANDING(28),
        MC_PROTECT_GOHOME(29);
        
        private int _value;

        private FLIGHT_ACTION(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        public boolean belongsTo(int i) {
            return this._value == i;
        }

        public static FLIGHT_ACTION find(int i) {
            FLIGHT_ACTION flight_action = NONE;
            for (FLIGHT_ACTION flight_action2 : values()) {
                if (flight_action2.belongsTo(i)) {
                    return flight_action2;
                }
            }
            return flight_action;
        }
    }

    public enum FLYC_STATE {
        Manula(0),
        Atti(1),
        Atti_CL(2),
        Atti_Hover(3),
        Hover(4),
        GPS_Blake(5),
        GPS_Atti(6),
        GPS_CL(7),
        GPS_HomeLock(8),
        GPS_HotPoint(9),
        AssitedTakeoff(10),
        AutoTakeoff(11),
        AutoLanding(12),
        AttiLangding(13),
        NaviGo(14),
        GoHome(15),
        ClickGo(16),
        Joystick(17),
        Atti_Limited(23),
        GPS_Atti_Limited(24),
        NaviMissionFollow(25),
        NaviSubMode_Tracking(26),
        NaviSubMode_Pointing(27),
        PANO(28),
        Farming(29),
        FPV(30),
        SPORT(31),
        NOVICE(32),
        FORCE_LANDING(33),
        TERRAIN_TRACKING(35),
        NAVI_ADV_GOHOME(36),
        NAVI_ADV_LANDING(37),
        TRIPOD_GPS(38),
        TRACK_HEADLOCK(39),
        ENGINE_START(41),
        GENTLE_GPS(43),
        OTHER(100);
        
        private int data;

        private FLYC_STATE(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FLYC_STATE find(int i) {
            FLYC_STATE flyc_state = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return flyc_state;
        }
    }

    public enum GOHOME_STATUS {
        STANDBY(0),
        PREASCENDING(1),
        ALIGN(2),
        ASCENDING(3),
        CRUISE(4),
        BRAKING(5),
        BYPASSING(6),
        OTHER(7);
        
        private int data;

        private GOHOME_STATUS(int i) {
            this.data = 0;
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static GOHOME_STATUS find(int i) {
            GOHOME_STATUS gohome_status = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return gohome_status;
        }
    }

    public enum IMU_INITFAIL_REASON {
        MonitorError(0),
        ColletingData(1),
        GyroDead(2),
        AcceDead(3),
        CompassDead(4),
        BarometerDead(5),
        BarometerNegative(6),
        CompassModTooLarge(7),
        GyroBiasTooLarge(8),
        AcceBiasTooLarge(9),
        CompassNoiseTooLarge(10),
        BarometerNoiseTooLarge(11),
        WaitingMcStationary(12),
        AcceMoveTooLarge(13),
        McHeaderMoved(14),
        McVirbrated(15),
        None(100);
        
        private int _value;

        private IMU_INITFAIL_REASON(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static IMU_INITFAIL_REASON find(int i) {
            for (IMU_INITFAIL_REASON imu_initfail_reason : values()) {
                if (imu_initfail_reason._equals(i)) {
                    return imu_initfail_reason;
                }
            }
            return None;
        }
    }

    public enum MotorFailReason {
        TAKEOFF_EXCEPTION(94),
        ESC_STALL_NEAR_GROUND(95),
        ESC_UNBALANCE_ON_GRD(96),
        ESC_PART_EMPTY_ON_GRD(97),
        ENGINE_START_FAILED(98),
        AUTO_TAKEOFF_LANCH_FAILED(99),
        ROLL_OVER_ON_GRD(100),
        OTHER(128);
        
        private int _value;

        private MotorFailReason(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static MotorFailReason find(int i) {
            for (MotorFailReason motorFailReason : values()) {
                if (motorFailReason._equals(i)) {
                    return motorFailReason;
                }
            }
            return OTHER;
        }
    }

    public enum MotorStartFailedCause {
        None(0),
        CompassError(1),
        AssistantProtected(2),
        DeviceLocked(3),
        DistanceLimit(4),
        IMUNeedCalibration(5),
        IMUSNError(6),
        IMUWarning(7),
        CompassCalibrating(8),
        AttiError(9),
        NoviceProtected(10),
        BatteryCellError(11),
        BatteryCommuniteError(12),
        SeriouLowVoltage(13),
        SeriouLowPower(14),
        LowVoltage(15),
        TempureVolLow(16),
        SmartLowToLand(17),
        BatteryNotReady(18),
        SimulatorMode(19),
        PackMode(20),
        AttitudeAbNormal(21),
        UnActive(22),
        FlyForbiddenError(23),
        BiasError(24),
        EscError(25),
        ImuInitError(26),
        SystemUpgrade(27),
        SimulatorStarted(28),
        ImuingError(29),
        AttiAngleOver(30),
        GyroscopeError(31),
        AcceletorError(32),
        CompassFailed(33),
        BarometerError(34),
        BarometerNegative(35),
        CompassBig(36),
        GyroscopeBiasBig(37),
        AcceletorBiasBig(38),
        CompassNoiseBig(39),
        BarometerNoiseBig(40),
        InvalidSn(41),
        FLASH_OPERATING(44),
        GPS_DISCONNECT(45),
        SDCardException(47),
        IMUNoconnection(61),
        RCCalibration(62),
        RCCalibrationException(63),
        RCCalibrationUnfinished(64),
        RCCalibrationException2(65),
        RCCalibrationException3(66),
        AircraftTypeMismatch(67),
        FoundUnfinishedModule(68),
        CYRO_ABNORMAL(70),
        BARO_ABNORMAL(71),
        COMPASS_ABNORMAL(72),
        GPS_ABNORMAL(73),
        NS_ABNORMAL(74),
        TOPOLOGY_ABNORMAL(75),
        RC_NEED_CALI(76),
        INVALID_FLOAT(77),
        M600_BAT_TOO_LITTLE(78),
        M600_BAT_AUTH_ERR(79),
        M600_BAT_COMM_ERR(80),
        M600_BAT_DIF_VOLT_LARGE_1(81),
        M600_BAT_DIF_VOLT_LARGE_2(82),
        INVALID_VERSION(83),
        GimbalGyroError(84),
        GimbalPitchNoData(85),
        GimbalRollNoData(86),
        GimbalYawNoData(87),
        GimbalFirmIsUpdata(88),
        GimbalDisorder(89),
        GIMBAL_GYRO_ABNORMAL(84),
        GIMBAL_ESC_PITCH_NON_DATA(85),
        GIMBAL_ESC_ROLL_NON_DATA(86),
        GIMBAL_ESC_YAW_NON_DATA(87),
        GIMBAL_FIRM_IS_UPDATING(88),
        GIMBAL_DISORDER(89),
        GIMBAL_PITCH_SHOCK(90),
        GIMBAL_ROLL_SHOCK(91),
        GIMBAL_YAW_SHOCK(92),
        GimbalPitchShock(90),
        GimbalRollShock(91),
        GimbalYawShock(92),
        IMUcCalibrationFinished(93),
        BatVersionError(101),
        RTK_BAD_SIGNAL(102),
        RTK_DEVIATION_ERROR(103),
        ESC_CALIBRATING(d.k),
        GPS_SIGN_INVALID(113),
        GIMBAL_IS_CALIBRATING(114),
        LOCK_BY_APP(115),
        START_FLY_HEIGHT_ERROR(116),
        ESC_VERSION_NOT_MATCH(117),
        IMU_ORI_NOT_MATCH(118),
        STOP_BY_APP(119),
        COMPASS_IMU_ORI_NOT_MATCH(120),
        OTHER(Integer.MAX_VALUE);
        
        private int data;
        private int relvalue;

        private MotorStartFailedCause(int i) {
            this.data = 0;
            this.relvalue = 0;
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public int relvalue() {
            return this.relvalue;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static MotorStartFailedCause find(int i) {
            MotorStartFailedCause motorStartFailedCause;
            MotorStartFailedCause motorStartFailedCause2 = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    motorStartFailedCause = values()[i2];
                    break;
                }
            }
            motorStartFailedCause = motorStartFailedCause2;
            motorStartFailedCause.relvalue = i;
            return motorStartFailedCause;
        }
    }

    public enum NON_GPS_CAUSE {
        ALREADY(0),
        FORBIN(1),
        GPSNUM_NONENOUGH(2),
        GPS_HDOP_LARGE(3),
        GPS_POSITION_NONMATCH(4),
        SPEED_ERROR_LARGE(5),
        YAW_ERROR_LARGE(6),
        COMPASS_ERROR_LARGE(7),
        UNKNOWN(8);
        
        private int data;

        private NON_GPS_CAUSE(int i) {
            this.data = 0;
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static NON_GPS_CAUSE find(int i) {
            NON_GPS_CAUSE non_gps_cause = UNKNOWN;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return non_gps_cause;
        }
    }

    public enum RcModeChannel {
        CHANNEL_MANUAL(0),
        CHANNEL_A(1),
        CHANNEL_P(2),
        CHANNEL_NAV(3),
        CHANNEL_FPV(4),
        CHANNEL_FARM(5),
        CHANNEL_S(6),
        CHANNEL_F(7),
        CHANNEL_UNKNOWN(255);
        
        private int _value;

        private RcModeChannel(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        public boolean belongsTo(int i) {
            return this._value == i;
        }

        public static RcModeChannel realFind(int i) {
            if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 16) {
                for (RcModeChannel rcModeChannel : values()) {
                    if (rcModeChannel.belongsTo(i)) {
                        return rcModeChannel;
                    }
                }
                return CHANNEL_P;
            } else if (i == RcModeChannelAfter16.ATTI.value()) {
                return CHANNEL_A;
            } else {
                if (i == RcModeChannelAfter16.GPS_ATTI.value()) {
                    return CHANNEL_P;
                }
                if (i == RcModeChannelAfter16.GPS_SPORT.value()) {
                    return CHANNEL_S;
                }
                return CHANNEL_P;
            }
        }

        public static RcModeChannel find(int i, int i2, DroneType droneType) {
            return find(i, i2, droneType, true);
        }

        public static RcModeChannel find(int i, int i2, DroneType droneType, boolean z) {
            RcModeChannel rcModeChannel = CHANNEL_P;
            if (i2 >= 14 && z) {
                return b.getInstance().a(i);
            }
            if (DroneType.P4 == droneType || DroneType.wm220 == droneType) {
                if (i == 0) {
                    return CHANNEL_A;
                }
                if (i == 1) {
                    return CHANNEL_S;
                }
                return CHANNEL_P;
            } else if (i == 0) {
                return CHANNEL_F;
            } else {
                if (i == 1) {
                    return CHANNEL_A;
                }
                return CHANNEL_P;
            }
        }
    }

    enum RcModeChannelAfter16 {
        MANUAL(0),
        ATTI(1),
        ATTI_GENTLE(2),
        ATTI_MANUAL(3),
        ATTI_SPORT(4),
        GPS_ATTI(5),
        GPS_GENTLE(6),
        GPS_NORMAL(7),
        GPS_SPORT(8),
        NAVIGATION(9),
        FPV(10),
        FARM(11),
        OTHER(255);
        
        private int _value;

        private RcModeChannelAfter16(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static RcModeChannelAfter16 find(int i) {
            for (RcModeChannelAfter16 rcModeChannelAfter16 : values()) {
                if (rcModeChannelAfter16._equals(i)) {
                    return rcModeChannelAfter16;
                }
            }
            return OTHER;
        }
    }

    public enum SDKCtrlDevice {
        RC(0),
        APP(1),
        ONBOARD_DEVICE(2),
        CAMERA(3),
        OTHER(128);
        
        private int _value;

        private SDKCtrlDevice(int i) {
            this._value = 0;
            this._value = i;
        }

        public int value() {
            return this._value;
        }

        private boolean _equals(int i) {
            return this._value == i;
        }

        public static SDKCtrlDevice find(int i) {
            for (SDKCtrlDevice sDKCtrlDevice : values()) {
                if (sDKCtrlDevice._equals(i)) {
                    return sDKCtrlDevice;
                }
            }
            return OTHER;
        }
    }

    public enum TRIPOD_STATUS {
        UNKNOWN((byte) 0),
        FOLD_COMPELTE((byte) 1),
        FOLOING((byte) 2),
        STRETCH_COMPLETE((byte) 3),
        STRETCHING((byte) 4),
        STOP_DEFORMATION((byte) 5);
        
        private byte _value;

        private TRIPOD_STATUS(byte b) {
            this._value = (byte) 0;
            this._value = b;
        }

        public byte value() {
            return this._value;
        }

        private boolean belongsTo(byte b) {
            return this._value == b;
        }

        public static TRIPOD_STATUS ofValue(byte b) {
            for (TRIPOD_STATUS tripod_status : values()) {
                if (tripod_status.belongsTo(b)) {
                    return tripod_status;
                }
            }
            return UNKNOWN;
        }
    }

    public static synchronized DataOsdGetPushCommon getInstance() {
        DataOsdGetPushCommon dataOsdGetPushCommon;
        synchronized (DataOsdGetPushCommon.class) {
            if (instance == null) {
                instance = new DataOsdGetPushCommon();
                instance.isNeedPushLosed = true;
                instance.isRemoteModel = true;
            }
            dataOsdGetPushCommon = instance;
        }
        return dataOsdGetPushCommon;
    }

    protected void setPushRecData(byte[] bArr) {
        super.setPushRecData(bArr);
    }

    public void clear() {
        super.clear();
    }

    public DataOsdGetPushCommon(boolean z) {
        super(z);
    }

    public double getLongitude() {
        return (((Double) get(0, 8, Double.class)).doubleValue() * 180.0d) / 3.141592653589793d;
    }

    public double getLatitude() {
        return (((Double) get(8, 8, Double.class)).doubleValue() * 180.0d) / 3.141592653589793d;
    }

    public int getHeight() {
        return ((Short) get(16, 2, Short.class)).shortValue();
    }

    public int getXSpeed() {
        return ((Short) get(18, 2, Short.class)).shortValue();
    }

    public int getYSpeed() {
        return ((Short) get(20, 2, Short.class)).shortValue();
    }

    public int getZSpeed() {
        return ((Short) get(22, 2, Short.class)).shortValue();
    }

    public int getPitch() {
        return ((Short) get(24, 2, Short.class)).shortValue();
    }

    public int getRoll() {
        return ((Short) get(26, 2, Short.class)).shortValue();
    }

    public int getYaw() {
        return ((Short) get(28, 2, Short.class)).shortValue();
    }

    public boolean getRcState() {
        return (((Short) get(30, 1, Short.class)).shortValue() & 128) == 0;
    }

    public FLYC_STATE getFlycState() {
        if (this._recData == null) {
            return FLYC_STATE.OTHER;
        }
        return FLYC_STATE.find(((Short) get(30, 1, Short.class)).shortValue() & -129);
    }

    public FLYC_COMMAND getAppCommand() {
        return FLYC_COMMAND.find(((Short) get(31, 1, Short.class)).shortValue());
    }

    public boolean canIOCWork() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 1) == 1;
    }

    public int groundOrSky() {
        return (((Integer) get(32, 4, Integer.class)).intValue() >> 1) & 3;
    }

    public boolean isMotorUp() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >> 3) & 1) == 1;
    }

    public boolean isSwaveWork() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 16) != 0;
    }

    public GOHOME_STATUS getGohomeStatus() {
        if (this._recData == null) {
            return GOHOME_STATUS.OTHER;
        }
        return GOHOME_STATUS.find((((Integer) get(32, 4, Integer.class)).intValue() >> 5) & 7);
    }

    public boolean isImuPreheatd() {
        if (this._recData == null) {
            return true;
        }
        return (((Integer) get(32, 4, Integer.class)).intValue() & 4096) != 0;
    }

    public boolean isVisionUsed() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 256) != 0;
    }

    public int getVoltageWarning() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 1536) >>> 9;
    }

    public RcModeChannel getModeChannel() {
        return RcModeChannel.find((((Integer) get(32, 4, Integer.class)).intValue() & e.ax) >>> 13, getFlycVersion(), getDroneType());
    }

    public RcModeChannel getModeChannelByFR() {
        return RcModeChannel.find((((Integer) get(32, 4, Integer.class)).intValue() & e.ax) >>> 13, getFlycVersion(), getDroneType(), false);
    }

    public boolean isGpsUsed() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 32768) != 0;
    }

    public boolean getCompassError() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 65536) != 0;
    }

    public boolean getWaveError() {
        return (((Integer) get(32, 4, Integer.class)).intValue() & 131072) != 0;
    }

    public int getGpsLevel() {
        return (((Integer) get(32, 4, Integer.class)).intValue() >>> 18) & 15;
    }

    public BatteryType getBatteryType() {
        DroneType droneType = getDroneType();
        if (droneType == DroneType.Unknown || droneType == DroneType.None) {
            return BatteryType.Smart;
        }
        return BatteryType.find((((Integer) get(32, 4, Integer.class)).intValue() >>> 22) & 3);
    }

    public boolean isAcceletorOverRange() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 24) & 1) != 0;
    }

    public boolean isVibrating() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 25) & 1) != 0;
    }

    public boolean isBarometerDeadInAir() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 26) & 1) != 0;
    }

    public boolean isMotorBlock() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 27) & 1) != 0;
    }

    public boolean isNotEnoughForce() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 28) & 1) != 0;
    }

    public boolean isGoHomeHeightModified() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 30) & 1) == 1;
    }

    public boolean isOutOfLimit() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 31) & 1) == 1;
    }

    public boolean isPropellerCatapult() {
        return ((((Integer) get(32, 4, Integer.class)).intValue() >>> 29) & 1) != 0;
    }

    public int getGpsNum() {
        return ((Short) get(36, 1, Short.class)).shortValue();
    }

    public FLIGHT_ACTION getFlightAction() {
        return FLIGHT_ACTION.find(((Short) get(37, 1, Short.class)).shortValue());
    }

    public MotorStartFailedCause getMotorFailedCause() {
        if ((((Short) get(38, 1, Short.class)).shortValue() >> 7) == 0) {
            return MotorStartFailedCause.None;
        }
        if (this._recData == null || this._recData.length <= 51) {
            return MotorStartFailedCause.find(((Short) get(38, 1, Short.class)).shortValue() & TransportMediator.KEYCODE_MEDIA_PAUSE);
        }
        return getMotorStartCauseNoStartAction();
    }

    public NON_GPS_CAUSE getNonGpsCause() {
        return NON_GPS_CAUSE.find(((Integer) get(39, 1, Integer.class)).intValue() & 15);
    }

    public boolean getWaypointLimitMode() {
        return (((Integer) get(39, 1, Integer.class)).intValue() & 16) == 16;
    }

    public int getBattery() {
        return ((Integer) get(40, 1, Integer.class)).intValue();
    }

    public int getSwaveHeight() {
        return ((Short) get(41, 1, Short.class)).shortValue();
    }

    public int getFlyTime() {
        return ((Integer) get(42, 2, Integer.class)).intValue();
    }

    public int getMotorRevolution() {
        return ((Short) get(44, 1, Short.class)).shortValue();
    }

    public int getFlycVersion() {
        return ((Integer) get(47, 1, Integer.class)).intValue();
    }

    public DroneType getDroneType() {
        return DroneType.find(((Integer) get(48, 1, Integer.class)).intValue());
    }

    public MotorStartFailedCause getMotorStartCauseNoStartAction() {
        return MotorStartFailedCause.find(((Short) get(51, 1, Short.class)).shortValue() & 255);
    }

    public IMU_INITFAIL_REASON getIMUinitFailReason() {
        return IMU_INITFAIL_REASON.find(((Integer) get(49, 1, Integer.class)).intValue());
    }

    public boolean isImuInitError() {
        IMU_INITFAIL_REASON iMUinitFailReason = getIMUinitFailReason();
        if (iMUinitFailReason == IMU_INITFAIL_REASON.None || iMUinitFailReason == IMU_INITFAIL_REASON.ColletingData || iMUinitFailReason == IMU_INITFAIL_REASON.MonitorError) {
            return false;
        }
        return true;
    }

    public boolean isAllowImuInitfailReason() {
        if (DataFlycGetPushCheckStatus.getInstance().getGyroscopeStatus() || DataFlycGetPushCheckStatus.getInstance().getAccDataStatus() || DataFlycGetPushCheckStatus.getInstance().getPressInitStatus() || DataFlycGetPushCheckStatus.getInstance().getIMUInitStatus()) {
            return false;
        }
        return true;
    }

    public MotorFailReason getMotorFailReason() {
        return MotorFailReason.find(((Integer) get(50, 1, Integer.class)).intValue());
    }

    public SDKCtrlDevice getSDKCtrlDevice() {
        return SDKCtrlDevice.find(((Integer) get(52, 1, Integer.class)).intValue());
    }

    protected void doPack() {
    }
}
