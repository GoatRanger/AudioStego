package dji.midware.data.config.P3;

import com.here.posclient.analytics.TrackerEvent;
import dji.pilot.usercenter.f.d;
import dji.thirdparty.g.b.a.a;
import it.sauronsoftware.ftp4j.FTPCodes;

public enum s {
    ERR_TooCloseToHomePoint(160),
    ERR_IOC_UNKNOW_TYPE(161),
    ERR_HP_INVALID_FLOAT_VALUE(162),
    ERR_HP_INVALID_LAT_LONTI(163),
    ERR_HP_UNKNOW_DIRECTION(166),
    ERR_HP_IS_PAUSED(169),
    ERR_HP_NOT_PAUSED(170),
    ERR_RCModeError(208),
    ERR_MCModeError(209),
    ERR_IOCWorking(210),
    ERR_MissionNotInit(211),
    ERR_MissionNotExist(212),
    ERR_MissionConflict(213),
    ERR_MissionEstimateTimeTooLong(FTPCodes.HELP_MESSAGE),
    ERR_HighPriorityMissionExecuting(FTPCodes.NAME_SYSTEM_TIME),
    ERR_GpsSignalWeak(216),
    ERR_LowBattery(217),
    ERR_AircraftNotInTheAir(218),
    ERR_MissionParamInvalid(219),
    ERR_MissionConditionNotSatisfied(FTPCodes.SERVICE_READY_FOR_NEW_USER),
    ERR_MissionAcrossNoFlyZone(221),
    ERR_HomePointNotRecorded(TrackerEvent.RadioMapManualCommonIndoor),
    ERR_AircraftInNoFlyZone(TrackerEvent.RadioMapManualPrivateIndoor),
    ERR_AltitudeTooHigh(192),
    ERR_AltitudeTooLow(193),
    ERR_MissionRadiusInvalid(194),
    ERR_MissionSpeedTooLarge(195),
    ERR_MissionEntryPointInvalid(196),
    ERR_MissionHeadingModeInvalid(197),
    ERR_MissionResumeFailed(198),
    ERR_MissionRadiusOverLimited(199),
    ERR_INVALID_PRODUCT(200),
    ERR_DISTANCE_TOO_LONG(d.y),
    ERR_FOR_IN_NOVICE_MODE(FTPCodes.SUPERFLOUS_COMMAND),
    ERR_BAD_RTK_SIGNAL(205),
    ERR_FM_DIST_TOO_LARGE(176),
    ERR_FM_UL_DISCONNECT(177),
    ERR_FM_ERROR_GIMBAL_PITCH(178),
    ERR_WP_INFO_INVALID(a.fw_),
    ERR_WP_DATA_INVALID(FTPCodes.DATA_CONNECTION_OPEN),
    ERR_WP_TRACE_TOO_LONG(FTPCodes.DATA_CONNECTION_CLOSING),
    ERR_WP_TOTAL_TOO_LONG(FTPCodes.ENTER_PASSIVE_MODE),
    ERR_WP_DATA_INDEX_OVER_RANGE(228),
    ERR_WP_DIST_TOO_CLOSE(229),
    ERR_WP_DIST_TOO_FAR(FTPCodes.USER_LOGGED_IN),
    ERR_WP_DATA_DAMPING_CHECK_FAILED(231),
    ERR_WP_ACTION_DATA_INVALID(232),
    ERR_WP_HAVING_REMAINING_WP(233),
    ERR_WP_INFO_NOT_UPLOADED(234),
    ERR_WP_DATA_NOT_UPLOADED(235),
    ERR_WP_REQUEST_IS_RUNNING(236),
    ERR_WP_NOT_RUNNING_WP_FUNC(237),
    ERR_WP_IDLE_VAL_INVALID(238),
    ERR_FOR_TAKING_OFF(240),
    ERR_FOR_LANDING(241),
    ERR_FOR_GOING_HOME(242),
    ERR_FOR_ENGINE_STARTING(243),
    ERR_FOR_WRONG_CMD(244),
    ERR_FOR_NO_RELATIVE_POSITION(247),
    ERR_FOR_NO_HORIZONTAL_VELOCITY(248),
    ERR_UNDEFINED(255);
    
    private static s[] ak;
    private int aj;

    static {
        ak = values();
    }

    private s(int i) {
        this.aj = i;
    }

    public int a() {
        return this.aj;
    }

    public boolean a(int i) {
        return this.aj == i;
    }

    public static s find(int i) {
        s sVar = ERR_UNDEFINED;
        for (int i2 = 0; i2 < ak.length; i2++) {
            if (ak[i2].a(i)) {
                return ak[i2];
            }
        }
        return sVar;
    }
}
