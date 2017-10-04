package dji.pilot.fpv.d;

import dji.common.flightcontroller.DJIFlightControllerFlightMode;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.NON_GPS_CAUSE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.pilot.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.d.a;

public class f {
    public static int[] a(DJIFlightControllerFlightMode dJIFlightControllerFlightMode, boolean z, boolean z2) {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        return a(dJIFlightControllerFlightMode, z, i.getInstance().c(), instance.getDroneType(), instance.getModeChannel(), a(), z2);
    }

    public static boolean a() {
        a availableValue = DJISDKCache.getInstance().getAvailableValue(a(e.a, e.aG));
        if (availableValue != null) {
            return ((Boolean) availableValue.e()).booleanValue();
        }
        return false;
    }

    public static void a(c[] cVarArr, d dVar, boolean z) {
        for (c startListeningForUpdates : cVarArr) {
            DJISDKCache.getInstance().startListeningForUpdates(startListeningForUpdates, dVar, z);
        }
    }

    public static c a(String str, String str2) {
        return new c.a().b(str).d(str2).a();
    }

    public static int[] a(DJIFlightControllerFlightMode dJIFlightControllerFlightMode, boolean z, ProductType productType, DroneType droneType, RcModeChannel rcModeChannel, boolean z2, boolean z3) {
        int[] iArr = new int[]{R.string.fpv_default_str, R.string.fpv_off};
        if (z3 || (DataOsdGetPushCommon.getInstance().isGetted() && DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.NoFlyc)) {
            boolean q = b.q(productType);
            if (DJIFlightControllerFlightMode.Manual == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_manual;
            } else if (DJIFlightControllerFlightMode.Atti == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_atti;
            } else if (DJIFlightControllerFlightMode.AttiCourseLock == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_atti;
                iArr[1] = R.string.direct_lock_cl;
            } else if (DJIFlightControllerFlightMode.AttiHover == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_atti;
            } else if (DJIFlightControllerFlightMode.AttiLimited == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_atti;
            } else if (DJIFlightControllerFlightMode.AttiLanding == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_landing;
            } else if (DJIFlightControllerFlightMode.AutoLanding == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_landing;
            } else if (DJIFlightControllerFlightMode.AssistedTakeOff == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_takeoff;
            } else if (DJIFlightControllerFlightMode.AutoTakeOff == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_takeoff;
            } else if (DJIFlightControllerFlightMode.GoHome == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_gohome;
            } else if (DJIFlightControllerFlightMode.Joystick == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_joystick;
            } else if (DJIFlightControllerFlightMode.GPSAtti == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_pgps;
            } else if (DJIFlightControllerFlightMode.GPSAttiLimited == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_pgps;
            } else if (DJIFlightControllerFlightMode.GPSBlake == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_pgps;
            } else if (DJIFlightControllerFlightMode.Hover == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_pgps;
            } else if (DJIFlightControllerFlightMode.GPSCourseLock == dJIFlightControllerFlightMode) {
                if (q) {
                    iArr[0] = R.string.ctrl_mode_cl;
                } else if (DroneType.A2 == droneType) {
                    iArr[0] = R.string.ctrl_mode_pcl;
                } else {
                    iArr[0] = R.string.ctrl_mode_fcl;
                }
                iArr[1] = R.string.direct_lock_cl;
            } else if (DJIFlightControllerFlightMode.GPSHomeLock == dJIFlightControllerFlightMode) {
                if (q) {
                    iArr[0] = R.string.ctrl_mode_hl;
                } else if (DroneType.A2 == droneType) {
                    iArr[0] = R.string.ctrl_mode_phl;
                } else {
                    iArr[0] = R.string.ctrl_mode_fhl;
                }
                iArr[1] = R.string.direct_lock_hl;
            } else if (DJIFlightControllerFlightMode.GPSHotPoint == dJIFlightControllerFlightMode) {
                if (q) {
                    iArr[0] = R.string.ctrl_mode_poi;
                } else {
                    iArr[0] = R.string.ctrl_mode_fpoi;
                }
                iArr[1] = R.string.direct_lock_pl;
            } else if (DJIFlightControllerFlightMode.GPSWaypoint == dJIFlightControllerFlightMode) {
                if (q) {
                    iArr[0] = R.string.ctrl_mode_way_point;
                } else {
                    iArr[0] = R.string.ctrl_mode_fway_point;
                }
            } else if (DJIFlightControllerFlightMode.ClickGo == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_pgps;
            } else if (DJIFlightControllerFlightMode.GPSFollowMe == dJIFlightControllerFlightMode) {
                if (q) {
                    iArr[0] = R.string.ctrl_mode_follow_me;
                } else {
                    iArr[0] = R.string.ctrl_mode_ffollow_me;
                }
            } else if (DJIFlightControllerFlightMode.Tracking == dJIFlightControllerFlightMode || DJIFlightControllerFlightMode.TrackHeadLock == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_ftracking;
            } else if (DJIFlightControllerFlightMode.Pointing == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_fpointing;
            } else if (DJIFlightControllerFlightMode.PANO == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_pano;
            } else if (DJIFlightControllerFlightMode.Farming == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_farm;
            } else if (DJIFlightControllerFlightMode.FPV == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_pgps;
            } else if (DJIFlightControllerFlightMode.GPSSport == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_sport;
            } else if (DJIFlightControllerFlightMode.GPSNovice == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_novice;
            } else if (DJIFlightControllerFlightMode.ConfirmLanding == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_landing;
            } else if (DJIFlightControllerFlightMode.TerrainTracking == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_terrain_tracking;
            } else if (DJIFlightControllerFlightMode.NaviAdvGoHome == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_navi_adv_gohome;
            } else if (DJIFlightControllerFlightMode.NaviAdvLanding == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_navi_adv_landing;
            } else if (DJIFlightControllerFlightMode.TripodGPS == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_tripod_gps;
            } else if (DJIFlightControllerFlightMode.GPSGentle == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_gentle_gps;
            } else if (DJIFlightControllerFlightMode.ENGINE_START == dJIFlightControllerFlightMode) {
                iArr[0] = R.string.ctrl_mode_takeoff;
            }
            if (R.string.ctrl_mode_pgps == iArr[0]) {
                if (z) {
                    iArr[0] = R.string.ctrl_mode_opti;
                } else if (q) {
                    iArr[0] = R.string.ctrl_mode_gps;
                } else if (z2 && RcModeChannel.CHANNEL_F == rcModeChannel) {
                    iArr[0] = R.string.ctrl_mode_fexit;
                }
            } else if (!(R.string.ctrl_mode_atti != iArr[0] || q || (z2 && rcModeChannel == RcModeChannel.CHANNEL_A))) {
                iArr[0] = R.string.ctrl_mode_patti;
            }
        }
        return iArr;
    }

    public static boolean a(NON_GPS_CAUSE non_gps_cause) {
        return non_gps_cause == NON_GPS_CAUSE.COMPASS_ERROR_LARGE || non_gps_cause == NON_GPS_CAUSE.SPEED_ERROR_LARGE || non_gps_cause == NON_GPS_CAUSE.YAW_ERROR_LARGE;
    }

    public static boolean a(FLYC_STATE flyc_state) {
        return FLYC_STATE.AutoLanding == flyc_state || FLYC_STATE.AttiLangding == flyc_state;
    }
}
