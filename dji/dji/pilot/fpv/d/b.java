package dji.pilot.fpv.d;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.common.flightcontroller.DJIFlightControllerFlightMode;
import dji.logic.f.d;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRawParams.DiskStatus;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LED_REASON;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus.CHANNEL_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorFailReason;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushHome.MotorEscmState;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal.SIGNAL_STATUS;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.e.e;
import dji.pilot.publics.objects.g;
import dji.setting.ui.general.ShowMfSwitchView;
import dji.thirdparty.a.c;
import java.text.DecimalFormat;

@SuppressLint({"NewApi"})
public class b {
    public static final int a = 60;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static int e = 3;
    private static boolean f = false;
    private static boolean g = false;
    private static final int h = 1;
    private static volatile int i = 0;
    private static volatile boolean j = true;
    private static final DecimalFormat k = new DecimalFormat("#,###");
    private static boolean l = true;
    private static int m = 0;
    private static ProductType n = null;
    private static String o = "DJI Device";

    public enum a {
        COMPASS_DISTURB,
        COMPASS_NON_DISTURB,
        DEVICE_LOCK
    }

    public static boolean a() {
        boolean z = true;
        if (i >= 1) {
            z = false;
        }
        if (z) {
            i++;
        }
        return z;
    }

    public static void a(boolean z) {
        if (f != z) {
            f = z;
            if (z) {
                c.a().e(a.COMPASS_DISTURB);
            } else {
                c.a().e(a.COMPASS_NON_DISTURB);
            }
        }
    }

    public static boolean b() {
        return f;
    }

    public static void b(boolean z) {
        if (g != z) {
            g = z;
        }
    }

    public static boolean c() {
        return g;
    }

    public static boolean a(int i) {
        return (e & i) != 0;
    }

    public static void a(int i, boolean z) {
        if (z) {
            e |= i;
        } else {
            e &= i ^ -1;
        }
    }

    public static void d() {
        i = 0;
        j = true;
        e = 3;
        g = false;
        h(0);
        if (f) {
            f = false;
            c.a().e(a.COMPASS_NON_DISTURB);
        }
    }

    @Deprecated
    public static int[] a(FLYC_STATE flyc_state, boolean z, ProductType productType, DroneType droneType, RcModeChannel rcModeChannel, boolean z2, boolean z3) {
        return f.a(DJIFlightControllerFlightMode.find(flyc_state.value()), z, productType, droneType, rcModeChannel, z2, z3);
    }

    public static int[] a(FLYC_STATE flyc_state, boolean z, boolean z2) {
        return f.a(DJIFlightControllerFlightMode.find(flyc_state.value()), z, z2);
    }

    public static int a(FLYC_STATE flyc_state, boolean z) {
        int i = R.string.fpv_default_str;
        if (FLYC_STATE.Manula == flyc_state) {
            i = R.string.ctrl_mode_manual;
        } else if (FLYC_STATE.Atti == flyc_state) {
            i = R.string.ctrl_mode_atti;
        } else if (FLYC_STATE.Atti_CL == flyc_state) {
            i = R.string.ctrl_mode_atti;
        } else if (FLYC_STATE.Atti_Hover == flyc_state) {
            i = R.string.ctrl_mode_atti;
        } else if (FLYC_STATE.Atti_Limited == flyc_state) {
            i = R.string.ctrl_mode_atti;
        } else if (FLYC_STATE.AttiLangding == flyc_state) {
            i = R.string.ctrl_mode_atti;
        } else if (FLYC_STATE.AutoLanding == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.AssitedTakeoff == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.AutoTakeoff == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.GoHome == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.GPS_Atti == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.GPS_Atti_Limited == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.GPS_Blake == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.GPS_CL == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.GPS_HomeLock == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.GPS_HotPoint == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.Hover == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.Joystick == flyc_state) {
            i = R.string.ctrl_mode_atti;
        } else if (FLYC_STATE.NaviGo == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.ClickGo == flyc_state) {
            i = R.string.ctrl_mode_pgps;
        } else if (FLYC_STATE.NaviSubMode_Tracking == flyc_state) {
            i = R.string.ctrl_mode_ftracking;
        } else if (FLYC_STATE.NaviSubMode_Pointing == flyc_state) {
            i = R.string.ctrl_mode_fpointing;
        } else if (FLYC_STATE.SPORT == flyc_state) {
            i = R.string.ctrl_mode_sport;
        } else if (FLYC_STATE.FORCE_LANDING == flyc_state) {
            i = R.string.ctrl_mode_landing;
        } else if (FLYC_STATE.ENGINE_START == flyc_state) {
            i = R.string.ctrl_mode_takeoff;
        }
        if (i == R.string.ctrl_mode_pgps) {
            if (z) {
                return R.string.ctrl_mode_popti;
            }
            return i;
        } else if (i != R.string.ctrl_mode_atti) {
            return i;
        } else {
            RcModeChannel modeChannel = DataOsdGetPushCommon.getInstance().getModeChannel();
            DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
            if (!a(instance.isBeginnerMode(), instance.isMultipleModeOpen()) || modeChannel == RcModeChannel.CHANNEL_F || modeChannel == RcModeChannel.CHANNEL_P) {
                return R.string.ctrl_mode_patti;
            }
            return i;
        }
    }

    public static boolean a(FLYC_STATE flyc_state) {
        if (flyc_state == FLYC_STATE.Atti || flyc_state == FLYC_STATE.Atti_CL || flyc_state == FLYC_STATE.Atti_Hover || flyc_state == FLYC_STATE.Atti_Limited || flyc_state == FLYC_STATE.AttiLangding) {
            return true;
        }
        return false;
    }

    public static boolean b(FLYC_STATE flyc_state) {
        if (flyc_state == FLYC_STATE.AutoTakeoff || flyc_state == FLYC_STATE.AttiLangding || flyc_state == FLYC_STATE.AutoLanding || flyc_state == FLYC_STATE.AssitedTakeoff || flyc_state == FLYC_STATE.GoHome) {
            return true;
        }
        return false;
    }

    public static boolean a(MotorEscmState motorEscmState) {
        return motorEscmState == MotorEscmState.NON_SMART || motorEscmState == MotorEscmState.MOTOR_IDLE || motorEscmState == MotorEscmState.MOTOR_UP || motorEscmState == MotorEscmState.MOTOR_OFF || motorEscmState == MotorEscmState.NON_CONNECT;
    }

    public static int a(TRIPOD_STATUS tripod_status) {
        if (TRIPOD_STATUS.UNKNOWN == tripod_status) {
            return R.string.fpv_errorpop_tripod_unknown;
        }
        if (TRIPOD_STATUS.FOLD_COMPELTE == tripod_status) {
            return R.string.fpv_errorpop_tripod_fold_complete;
        }
        if (TRIPOD_STATUS.FOLOING == tripod_status) {
            return R.string.fpv_errorpop_tripod_foloing;
        }
        if (TRIPOD_STATUS.STRETCH_COMPLETE == tripod_status) {
            return R.string.fpv_errorpop_tripod_stretch_complete;
        }
        if (TRIPOD_STATUS.STRETCHING == tripod_status) {
            return R.string.fpv_errorpop_tripod_stretching;
        }
        if (TRIPOD_STATUS.STOP_DEFORMATION == tripod_status) {
            return R.string.fpv_errorpop_tripod_stop_deformation;
        }
        return R.string.fpv_errorpop_tripod_unknown;
    }

    public static int[] a(FLIGHT_ACTION flight_action) {
        int[] iArr = new int[]{0, 0};
        if (FLIGHT_ACTION.WARNING_POWER_GOHOME == flight_action) {
            iArr[0] = R.string.fpv_errorpop_flightaction_warning_power_gohome;
        } else if (FLIGHT_ACTION.WARNING_POWER_LANDING == flight_action) {
            iArr[0] = R.string.fpv_errorpop_flightaction_warning_power_landing;
        } else if (FLIGHT_ACTION.SMART_POWER_GOHOME == flight_action) {
            iArr[0] = R.string.fpv_errorpop_flightaction_smart_power_gohome;
        } else if (FLIGHT_ACTION.SMART_POWER_LANDING == flight_action) {
            iArr[0] = R.string.fpv_errorpop_flightaction_smart_power_landing;
        } else if (FLIGHT_ACTION.LOW_VOLTAGE_LANDING == flight_action) {
            iArr[0] = R.string.fpv_errorpop_flightaction_low_voltage_landing;
        } else if (FLIGHT_ACTION.LOW_VOLTAGE_GOHOME == flight_action) {
            iArr[0] = R.string.fpv_errorpop_flightaction_low_voltage_gohome;
            iArr[1] = 1;
        } else if (FLIGHT_ACTION.SERIOUS_LOW_VOLTAGE_LANDING == flight_action) {
            iArr[0] = R.string.fpv_errorpop_flightaction_serious_low_voltage_landing;
            iArr[1] = 1;
        } else if (!(FLIGHT_ACTION.RC_ONEKEY_GOHOME == flight_action || FLIGHT_ACTION.RC_ASSISTANT_TAKEOFF == flight_action || FLIGHT_ACTION.RC_AUTO_TAKEOFF == flight_action || FLIGHT_ACTION.RC_AUTO_LANDING == flight_action)) {
            if (FLIGHT_ACTION.APP_AUTO_GOHOME == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_app_auto_gohome;
            } else if (FLIGHT_ACTION.APP_AUTO_LANDING == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_app_auto_landing;
            } else if (FLIGHT_ACTION.APP_AUTO_TAKEOFF == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_app_auto_takeoff;
            } else if (FLIGHT_ACTION.OUTOF_CONTROL_GOHOME == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_outof_control_gohome;
                iArr[1] = 1;
            } else if (FLIGHT_ACTION.API_AUTO_TAKEOFF == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_api_auto_takeoff;
            } else if (FLIGHT_ACTION.API_AUTO_LANDING == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_api_auto_landing;
            } else if (FLIGHT_ACTION.API_AUTO_GOHOME == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_api_auto_gohome;
            } else if (FLIGHT_ACTION.AVOID_GROUND_LANDING == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_avoid_ground_auto_landing;
            } else if (FLIGHT_ACTION.AIRPORT_AVOID_LANDING == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_airport_avoid_auto_landing;
            } else if (FLIGHT_ACTION.TOO_CLOSE_GOHOME_LANDING == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_too_close_auto_landing;
            } else if (FLIGHT_ACTION.TOO_FAR_GOHOME_LANDING == flight_action) {
                iArr[0] = R.string.fpv_errorpop_flightaction_too_far_auto_landing;
            }
        }
        return iArr;
    }

    public static int a(SDCardState sDCardState) {
        if (sDCardState == SDCardState.Normal) {
            return R.string.sdcardstatus_normal;
        }
        if (sDCardState == SDCardState.None) {
            return R.string.sdcardstatus_removal;
        }
        if (sDCardState == SDCardState.Invalid) {
            return R.string.sdcardstatus_invalid;
        }
        if (sDCardState == SDCardState.WriteProtection) {
            return R.string.sdcardstatus_write_protect;
        }
        if (sDCardState == SDCardState.Unformat) {
            return R.string.sdcardstatus_not_formated;
        }
        if (sDCardState == SDCardState.Formating) {
            return R.string.sdcardstatus_formating;
        }
        if (sDCardState == SDCardState.Illegal) {
            return R.string.sdcardstatus_invalid_filesystem;
        }
        if (sDCardState == SDCardState.Busy) {
            return R.string.sdcardstatus_busy;
        }
        if (sDCardState == SDCardState.Full) {
            return R.string.sdcardstatus_full;
        }
        if (sDCardState == SDCardState.Slow) {
            return R.string.sdcardstatus_slow;
        }
        if (sDCardState == SDCardState.Unknow) {
            return R.string.sdcardstatus_unknown_error;
        }
        if (sDCardState == SDCardState.IndexMax) {
            return R.string.sdcardstatus_full;
        }
        if (sDCardState == SDCardState.Initialzing) {
            return R.string.sdcardstatus_initial;
        }
        if (sDCardState == SDCardState.ToFormat) {
            return R.string.sdcardstatus_toformat;
        }
        if (sDCardState == SDCardState.USBConnected) {
            return R.string.sdcardstatus_usb_connected;
        }
        if (sDCardState == SDCardState.TryToRecoverFile) {
            return R.string.sdcardstatus_recover_file;
        }
        return sDCardState == SDCardState.BecomeSlow ? R.string.sdcardstatus_slow : R.string.sdcardstatus_unknown_error;
    }

    public static int a(dji.midware.data.config.P3.a aVar) {
        if (dji.midware.data.config.P3.a.TIMEOUT == aVar || dji.midware.data.config.P3.a.TIMEOUT_REMOTE == aVar) {
            return R.string.code_timeout;
        }
        if (dji.midware.data.config.P3.a.SUCCEED == aVar) {
            return R.string.code_successed;
        }
        if (dji.midware.data.config.P3.a.INVALID_CMD == aVar) {
            return R.string.code_invalid_cmd;
        }
        if (dji.midware.data.config.P3.a.NOT_SUPPORT_CURRENT_STATE == aVar) {
            return R.string.code_notsupport_now;
        }
        if (dji.midware.data.config.P3.a.SDCARD_NOT_INSERTED == aVar) {
            return R.string.code_sd_removal;
        }
        if (dji.midware.data.config.P3.a.SDCARD_FULL == aVar) {
            return R.string.code_sd_full;
        }
        if (dji.midware.data.config.P3.a.SDCARD_ERR == aVar) {
            return R.string.code_sd_error;
        }
        if (dji.midware.data.config.P3.a.CAMERA_CRITICAL_ERR == aVar) {
            return R.string.code_camera_critical_error;
        }
        if (dji.midware.data.config.P3.a.NOCONNECT == aVar) {
            return R.string.code_disconnect;
        }
        return R.string.code_unknown;
    }

    public static int a(LED_REASON led_reason) {
        if (LED_REASON.SET_HOME == led_reason) {
            return R.string.fpv_led_set_home;
        }
        if (LED_REASON.SET_HOT_POINT == led_reason) {
            return R.string.fpv_led_set_hot_point;
        }
        if (LED_REASON.SET_COURSE_LOCK == led_reason) {
            return R.string.fpv_led_set_course_lock;
        }
        if (LED_REASON.TEST_LED == led_reason) {
            return R.string.fpv_led_test;
        }
        if (LED_REASON.USB_IS_VALID == led_reason) {
            return R.string.fpv_led_usb_is_valid;
        }
        if (LED_REASON.PACKING_FAIL == led_reason) {
            return R.string.fpv_led_packing_fail;
        }
        if (LED_REASON.PACKING_NORMAL == led_reason) {
            return R.string.fpv_led_packing_normal;
        }
        if (LED_REASON.NO_ATTI == led_reason) {
            return R.string.fpv_led_no_atti;
        }
        if (LED_REASON.COMPASS_CALI_STEP0 == led_reason) {
            return R.string.fpv_led_compass_cali_step0;
        }
        if (LED_REASON.COMPASS_CALI_STEP1 == led_reason) {
            return R.string.fpv_led_compass_cali_step1;
        }
        if (LED_REASON.COMPASS_CALI_ERROR == led_reason) {
            return R.string.fpv_led_compass_cali_error;
        }
        if (LED_REASON.SENSOR_TEMP_NOT_READY == led_reason) {
            return R.string.fpv_led_sensor_temp_not_ready;
        }
        if (LED_REASON.IMU_OR_GYRO_LOST == led_reason) {
            return R.string.fpv_led_imu_gyro_lost;
        }
        if (LED_REASON.IMU_BAD_ATTI == led_reason) {
            return R.string.fpv_led_imu_bad_atti;
        }
        if (LED_REASON.SYSTEM_ERROR == led_reason) {
            return R.string.fpv_led_system_error;
        }
        if (LED_REASON.IMU_ERROR == led_reason) {
            return R.string.fpv_led_imu_error;
        }
        if (LED_REASON.IMU_NEED_CALI == led_reason) {
            return R.string.fpv_led_imu_need_cali;
        }
        if (LED_REASON.COMPASS_OUT_RANGE == led_reason) {
            return R.string.fpv_led_compass_need_cali;
        }
        if (LED_REASON.RC_COMPLETELY_LOST == led_reason) {
            return R.string.fpv_led_failsafe;
        }
        if (LED_REASON.BATTERY_WARNING == led_reason) {
            if (i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3) {
                return R.string.fpv_led_battery_voltage_warning;
            }
            return R.string.fpv_led_battery_warning;
        } else if (LED_REASON.BATTERY_ERROR == led_reason) {
            if (i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3) {
                return R.string.fpv_led_battery_voltage_error;
            }
            return R.string.fpv_led_battery_error;
        } else if (LED_REASON.IMU_WARNING == led_reason) {
            return R.string.fpv_led_imu_warning;
        } else {
            if (LED_REASON.SET_FLY_LIMIT == led_reason) {
                return R.string.fpv_led_set_fly_limit;
            }
            if (LED_REASON.FDI_VIBRATE == led_reason) {
                return R.string.fpv_led_fdi_vibrate;
            }
            if (LED_REASON.CODE_ERROR == led_reason) {
                return R.string.fpv_led_coder_error;
            }
            if (LED_REASON.SYSTEM_RECONSTRUCTION == led_reason) {
                return R.string.fpv_led_system_reconstruction;
            }
            if (LED_REASON.RECORDER_ERROR == led_reason) {
                return R.string.fpv_led_recorder_error;
            }
            return R.string.fpv_led_normal;
        }
    }

    public static int a(MotorFailReason motorFailReason) {
        if (motorFailReason == MotorFailReason.TAKEOFF_EXCEPTION) {
            return R.string.motor_stop_reason_takeoff_exception;
        }
        if (motorFailReason == MotorFailReason.ESC_STALL_NEAR_GROUND) {
            return R.string.motor_stop_reason_esc_stall_near_gound;
        }
        if (motorFailReason == MotorFailReason.ESC_UNBALANCE_ON_GRD) {
            return R.string.motor_stop_reason_esc_unbalance_on_grd;
        }
        if (motorFailReason == MotorFailReason.ESC_PART_EMPTY_ON_GRD) {
            return R.string.motor_stop_reason_esc_part_empty_on_grd;
        }
        if (motorFailReason == MotorFailReason.ENGINE_START_FAILED) {
            return R.string.motor_stop_reason_engine_start_failed;
        }
        if (motorFailReason == MotorFailReason.AUTO_TAKEOFF_LANCH_FAILED) {
            return R.string.motor_stop_reason_auto_takeoff_lanch_faile;
        }
        if (motorFailReason == MotorFailReason.ROLL_OVER_ON_GRD) {
            return R.string.motor_stop_reason_roll_over_on_grd;
        }
        return 0;
    }

    public static boolean a(double d) {
        return dji.pilot.publics.e.a.a(d);
    }

    public static boolean b(double d) {
        return dji.pilot.publics.e.a.b(d);
    }

    public static boolean b(int i) {
        return dji.pilot.publics.e.a.a(i);
    }

    public static boolean e() {
        return dji.pilot.publics.e.a.c();
    }

    public static boolean a(DroneType droneType, int i, int i2, int i3) {
        if (dji.pilot.publics.e.a.a(droneType) || i < 6) {
            return b(i2);
        }
        return i3 >= 3;
    }

    public static int c(int i) {
        if (i == 0 || i >= 50) {
            return 0;
        }
        if (i <= 7) {
            return 1;
        }
        if (i > 10) {
            return 5;
        }
        return i - 6;
    }

    public static float a(double d, double d2, double d3, double d4) {
        return dji.pilot.publics.e.a.b(d, d2, d3, d4);
    }

    public static float b(double d, double d2, double d3, double d4) {
        return dji.pilot.publics.e.a.c(d, d2, d3, d4);
    }

    public static float[] c(double d, double d2, double d3, double d4) {
        return dji.pilot.publics.e.a.a(d, d2, d3, d4);
    }

    public static boolean b(SDCardState sDCardState) {
        if (SDCardState.Normal == sDCardState || SDCardState.Slow == sDCardState) {
            return true;
        }
        return false;
    }

    public static int[] d(int i) {
        int[] iArr = new int[2];
        int i2 = i % 60;
        int i3 = i / 60;
        iArr[0] = i3 % 60;
        if (i2 > 0) {
            iArr[0] = iArr[0] + 1;
        }
        iArr[1] = i3 / 60;
        if (iArr[0] == 60) {
            iArr[1] = iArr[1] + 1;
            iArr[0] = 0;
        }
        return iArr;
    }

    public static int[] e(int i) {
        return new int[]{i % 60, i / 60};
    }

    public static int[] f(int i) {
        r0 = new int[3];
        int i2 = i / 60;
        r0[1] = i2 % 60;
        r0[2] = i2 / 60;
        return r0;
    }

    public static String a(Context context, float f, boolean z) {
        return a(context, "%.1f", f, z);
    }

    public static String a(Context context, String str, float f, boolean z) {
        int w = z ? DJIGenSettingDataManager.getInstance().w() : 1;
        Object[] objArr;
        if (w == 0) {
            objArr = new Object[1];
            objArr[0] = String.format(str, new Object[]{Float.valueOf(e.d(f))});
            return context.getString(R.string.battery_temperature_h_unit, objArr);
        } else if (2 == w) {
            objArr = new Object[1];
            objArr[0] = String.format(str, new Object[]{Float.valueOf(e.c(f))});
            return context.getString(R.string.battery_temperature_k_unit, objArr);
        } else {
            Object[] objArr2 = new Object[1];
            objArr2[0] = String.format(str, new Object[]{Float.valueOf(f)});
            return context.getString(R.string.battery_temperature_unit, objArr2);
        }
    }

    public static float a(float f) {
        int w = DJIGenSettingDataManager.getInstance().w();
        if (w == 0) {
            return e.d(f);
        }
        if (2 == w) {
            return e.c(f);
        }
        return f;
    }

    public static float b(float f) {
        int w = DJIGenSettingDataManager.getInstance().w();
        if (w == 0) {
            return e.e(f);
        }
        if (2 == w) {
            return e.b(f);
        }
        return f;
    }

    public static String a(Context context) {
        int w = DJIGenSettingDataManager.getInstance().w();
        if (w == 0) {
            return context.getString(R.string.battery_temperature_h_unit, new Object[]{""});
        } else if (2 == w) {
            return context.getString(R.string.battery_temperature_k_unit, new Object[]{""});
        } else {
            return context.getString(R.string.battery_temperature_unit, new Object[]{""});
        }
    }

    public static String b(Context context) {
        int w = DJIGenSettingDataManager.getInstance().w();
        if (w == 0) {
            return context.getString(R.string.tau_temperature_fahrenheit);
        }
        if (2 == w) {
            return context.getString(R.string.tau_temperature_kelvin);
        }
        return context.getString(R.string.tau_temperature_celsius);
    }

    public static boolean c(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        if (i.getInstance().c().isFromWifi() && ServiceManager.getInstance().isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean a(ContentResolver contentResolver) {
        if (VERSION.SDK_INT > 16) {
            if (Global.getInt(contentResolver, "adb_enabled", 0) > 0) {
                return true;
            }
            return false;
        } else if (Secure.getInt(contentResolver, "adb_enabled", 0) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public static int g(int i) {
        int i2 = 0;
        if (i != 0) {
            i2 = 101 - ((int) Math.sqrt(Math.pow(10.0d, (double) (((float) (Math.abs(i) - 53)) / 10.0f))));
            if (i2 > 100) {
                return 100;
            }
            if (i2 < 5) {
                return 5;
            }
        }
        return i2;
    }

    public static boolean a(CHANNEL_STATUS channel_status) {
        if (CHANNEL_STATUS.Excellent == channel_status || CHANNEL_STATUS.Good == channel_status || CHANNEL_STATUS.Medium == channel_status) {
            return false;
        }
        return true;
    }

    public static boolean a(SIGNAL_STATUS signal_status) {
        if (SIGNAL_STATUS.Good == signal_status || SIGNAL_STATUS.Medium == signal_status) {
            return false;
        }
        return true;
    }

    public static String a(long j) {
        return k.format(j);
    }

    public static boolean a(ProductType productType) {
        return dji.pilot.publics.e.a.g(productType);
    }

    public static boolean b(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Orange || productType == ProductType.N1 || productType == ProductType.Grape2 || productType == ProductType.A2 || productType == ProductType.BigBanana || productType == ProductType.OrangeRAW || dji.pilot.publics.e.a.d(productType) || productType == ProductType.OrangeCV600;
    }

    public static boolean c(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Tomato || productType == ProductType.Pomato || productType == ProductType.KumquatS || productType == ProductType.KumquatX;
    }

    public static boolean d(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return ProductType.Pomato == productType || dji.pilot.publics.e.a.c(null);
    }

    public static boolean e(ProductType productType) {
        if (productType == null) {
            i.getInstance().c();
        }
        if (c(null)) {
            return DJIGenSettingDataManager.getInstance().B();
        }
        return false;
    }

    public static boolean f(ProductType productType) {
        return dji.pilot.publics.e.a.d(productType);
    }

    public static boolean g(ProductType productType) {
        return dji.pilot.publics.e.a.h(productType);
    }

    public static boolean h(ProductType productType) {
        return d.a(productType);
    }

    public static boolean i(ProductType productType) {
        if (ServiceManager.getInstance().isRemoteOK()) {
            if (productType == ProductType.Grape2 || productType == ProductType.A2) {
                return true;
            }
            return false;
        } else if (i.getInstance().a() == ProductType.Grape2 || productType == ProductType.A2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean f() {
        return dji.pilot.publics.e.a.d();
    }

    public static boolean g() {
        ProductType c = i.getInstance().c();
        return d.a(c) || c == ProductType.P34K || c == ProductType.litchiC || c == ProductType.Tomato || c == ProductType.Pomato;
    }

    public static boolean h() {
        ProductType c = i.getInstance().c();
        return c == ProductType.Tomato || c == ProductType.Pomato || dji.pilot.publics.e.a.c(c);
    }

    public static DJICustomType[] a(Boolean bool) {
        return dji.pilot.publics.e.a.a(bool);
    }

    public static boolean j(ProductType productType) {
        return dji.pilot.publics.e.a.r(productType);
    }

    public static boolean i() {
        return k(i.getInstance().c());
    }

    public static boolean k(ProductType productType) {
        return dji.pilot.publics.e.a.b(productType);
    }

    public static boolean l(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType.isFromWifi() || dji.logic.c.b.getInstance().a(productType);
    }

    public static boolean a(CameraType cameraType) {
        return CameraType.DJICameraTypeFC350 == cameraType || cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeCV600 || CameraType.DJICameraTypeFC6310 == cameraType || cameraType == CameraType.DJICameraTypeGD600;
    }

    public static boolean b(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw;
    }

    public static boolean c(CameraType cameraType) {
        dji.logic.c.b.getInstance().a(null);
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeFC220 || cameraType == CameraType.DJICameraTypeCV600 || cameraType == CameraType.DJICameraTypeFC220S || CameraType.DJICameraTypeFC6310 == cameraType || cameraType == CameraType.DJICameraTypeGD600;
    }

    public static boolean d(CameraType cameraType) {
        return (!e(cameraType) || cameraType == CameraType.DJICameraTypeGD600 || cameraType == CameraType.DJICameraTypeFC220S || cameraType == CameraType.DJICameraTypeFC220) ? false : true;
    }

    public static boolean e(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeGD600 || f(cameraType);
    }

    public static boolean f(CameraType cameraType) {
        if (cameraType == null && DataCameraGetPushStateInfo.getInstance().isGetted()) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == null) {
            return false;
        }
        if ((cameraType == CameraType.DJICameraTypeFC220 || cameraType == CameraType.DJICameraTypeFC220S) && g.b(dji.pilot2.b.a.a(), ShowMfSwitchView.a, false)) {
            return true;
        }
        return false;
    }

    public static boolean g(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeFC6310;
    }

    public static boolean a(CameraType cameraType, ZoomFocusType zoomFocusType) {
        return (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw) && zoomFocusType == ZoomFocusType.AutoZoomFocus;
    }

    public static boolean a(ZoomFocusType zoomFocusType, CameraType cameraType) {
        return zoomFocusType == ZoomFocusType.AutoZoomFocus && (cameraType == CameraType.DJICameraTypeCV600 || cameraType == CameraType.DJICameraTypeGD600);
    }

    public static boolean h(CameraType cameraType) {
        return cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeFC6310 || cameraType == CameraType.DJICameraTypeGD600;
    }

    public static boolean m(ProductType productType) {
        return dji.pilot.publics.e.a.u(productType);
    }

    public static boolean n(ProductType productType) {
        return dji.pilot.publics.e.a.k(productType);
    }

    public static boolean o(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Orange || productType == ProductType.N1 || productType == ProductType.BigBanana || productType == ProductType.OrangeRAW || productType == ProductType.Olives || dji.pilot.publics.e.a.d(productType) || productType == ProductType.OrangeCV600;
    }

    public static boolean i(CameraType cameraType) {
        return CameraType.DJICameraTypeFC550 == cameraType || CameraType.DJICameraTypeFC550Raw == cameraType;
    }

    public static int a(int i, int i2) {
        if (i > 0 && i <= 100) {
            i2 = ((int) ((((float) (i - 1)) * ((float) i2)) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) + 1;
        } else if (i <= 100) {
            i2 = 0;
        }
        if (dji.pilot.publics.e.a.f(null) && (i == 5 || i == 6)) {
            return 0;
        }
        return i2;
    }

    public static boolean a(boolean z, boolean z2) {
        return !z && z2;
    }

    public static boolean j() {
        return dji.pilot.publics.e.a.a(DataOsdGetPushCommon.getInstance());
    }

    public static boolean a(DataOsdGetPushCommon dataOsdGetPushCommon) {
        return dji.pilot.publics.e.a.a(dataOsdGetPushCommon);
    }

    public static boolean p(ProductType productType) {
        return dji.pilot.publics.e.a.c(productType) || productType == ProductType.Pomato;
    }

    public static boolean q(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.Tomato || productType == ProductType.Pomato || dji.pilot.publics.e.a.c(productType) || dji.pilot.publics.e.a.h();
    }

    public static boolean a(ProductType productType, boolean z, RcModeChannel rcModeChannel) {
        boolean z2 = false;
        if (q(productType)) {
            if (!z || rcModeChannel == RcModeChannel.CHANNEL_P) {
                return false;
            }
            return true;
        } else if (productType.equals(ProductType.A2) && DataA2PushCommom.getInstance().f() == 1) {
            return false;
        } else {
            if (!j()) {
                return true;
            }
            if (!(z && rcModeChannel == RcModeChannel.CHANNEL_F)) {
                z2 = true;
            }
            return z2;
        }
    }

    private static Shape u() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    public static Drawable a(Drawable drawable, boolean z) {
        int i = 0;
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                boolean z2;
                int id = layerDrawable.getId(i2);
                Drawable drawable2 = layerDrawable.getDrawable(i2);
                if (id == 16908301 || id == 16908303) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                drawableArr[i2] = a(drawable2, z2);
            }
            Drawable layerDrawable2 = new LayerDrawable(drawableArr);
            while (i < numberOfLayers) {
                layerDrawable2.setId(i, layerDrawable.getId(i));
                i++;
            }
            return layerDrawable2;
        } else if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        } else {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable shapeDrawable = new ShapeDrawable(u());
            shapeDrawable.getPaint().setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP));
            if (z) {
                return new ClipDrawable(shapeDrawable, 80, 2);
            }
            return shapeDrawable;
        }
    }

    public static int k() {
        return m;
    }

    public static void h(int i) {
        m = i;
    }

    public static boolean r(ProductType productType) {
        return dji.pilot.publics.e.a.d(productType);
    }

    public static boolean l() {
        return DataGimbalGetPushType.getInstance().getType() == DJIGimbalType.Ronin && r(null);
    }

    public static boolean m() {
        return dji.pilot.publics.e.a.f();
    }

    public static boolean n() {
        ProductType c = i.getInstance().c();
        return c == ProductType.A3 || c == ProductType.N3;
    }

    public static boolean o() {
        return dji.pilot.publics.e.a.g();
    }

    public static boolean p() {
        ProductType c = i.getInstance().c();
        return dji.pilot.publics.e.a.d(c) || dji.pilot.publics.e.a.c(c);
    }

    public static boolean j(CameraType cameraType) {
        return dji.pilot.publics.e.a.a(cameraType);
    }

    public static boolean k(CameraType cameraType) {
        if (cameraType == null) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        return cameraType == CameraType.DJICameraTypeGD600;
    }

    public static boolean q() {
        ProductType c = i.getInstance().c();
        return c == ProductType.Tomato || c == ProductType.Pomato || dji.pilot.publics.e.a.c(c);
    }

    public static boolean s(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return productType == ProductType.litchiC || productType == ProductType.P34K || productType == ProductType.Tomato || productType == ProductType.Pomato || dji.pilot.publics.e.a.c(productType);
    }

    public static boolean r() {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        if (cameraType == CameraType.DJICameraTypeFC220S) {
            return true;
        }
        int verstion = DataCameraGetPushStateInfo.getInstance().getVerstion();
        if ((((cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeFC220) && verstion >= 4) || cameraType == CameraType.DJICameraTypeCV600) && !dji.pilot.fpv.camera.a.a.a()) {
            return true;
        }
        return false;
    }

    public static boolean s() {
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        return cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeCV600 || cameraType == CameraType.DJICameraTypeFC220 || cameraType == CameraType.DJICameraTypeFC220S;
    }

    public static boolean t() {
        DataCameraGetPushRawParams instance = DataCameraGetPushRawParams.getInstance();
        if (instance.isGetted() && (instance.getDiskStatus() == DiskStatus.INITIALIZING || instance.getDiskStatus() == DiskStatus.FAST_FORMATING || instance.getDiskStatus() == DiskStatus.LOW_FORMATING)) {
            return true;
        }
        return false;
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + dji.pilot.visual.a.d.c);
    }

    public static int b(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + dji.pilot.visual.a.d.c);
    }

    public static boolean t(ProductType productType) {
        return ProductType.Pomato == productType || ProductType.Orange2 == productType;
    }

    public static boolean a(DJICustomType dJICustomType) {
        return (DJICustomType.CameraSetting == dJICustomType || DJICustomType.MapSwitch == dJICustomType || DJICustomType.ClearRote == dJICustomType || DJICustomType.Bettery == dJICustomType || DJICustomType.Navigation == dJICustomType || DJICustomType.LiveViewExpand == dJICustomType) ? false : true;
    }

    public static boolean i(int i) {
        if (dji.pilot.publics.e.a.c(null)) {
            if (i < 15) {
                return true;
            }
            return false;
        } else if (i >= 30) {
            return false;
        } else {
            return true;
        }
    }

    public static String u(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        if (productType != n) {
            o = dji.pilot.publics.c.d.getInstance().a(productType).activeName;
        }
        return o;
    }
}
