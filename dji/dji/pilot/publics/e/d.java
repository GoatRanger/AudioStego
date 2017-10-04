package dji.pilot.publics.e;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.widget.Toast;
import com.google.android.gms.location.places.Place;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions$Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.tencent.bugly.crashreport.CrashReport;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.pilot.R;
import dji.pilot.c.a;
import dji.pilot.fpv.d.b;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class d {
    public static final String a = "http://chat10.live800.com/live800/chatClient/chatbox.jsp?jid=2964301564&companyID=409811&prechatinfoexist=1&subject=%E6%89%8B%E6%9C%BAAPP";
    public static final String b = "Inspire1@dji.com";
    public static final String c = "http://chat32.live800.com/live800/chatClient/chatbox.jsp?companyID=493623&configID=72904&jid=5418788219";
    public static final String d = "support@dji.com";
    private static final String e = "[A-Z0-9a-z._-]+@[A-Za-z0-9.-]+(?:[-.][a-zA-Z0-9]+)*\\.[A-Za-z]{2,4}";
    private static final int f = 31457280;

    public static String a(ProductType productType) {
        String str = "support@dji.com";
        if (ProductType.Orange == productType || ProductType.BigBanana == productType || ProductType.OrangeRAW == productType || ProductType.Olives == productType || ProductType.OrangeCV600 == productType) {
            return "Inspire1@dji.com";
        }
        return str;
    }

    public static String b(ProductType productType) {
        String str = "http://chat10.live800.com/live800/chatClient/chatbox.jsp?jid=2964301564&companyID=409811&prechatinfoexist=1&subject=%E6%89%8B%E6%9C%BAAPP";
        if (b.k(productType)) {
            return "http://chat32.live800.com/live800/chatClient/chatbox.jsp?companyID=493623&configID=72904&jid=5418788219";
        }
        return str;
    }

    public static void a(Context context, String str, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.setFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (z) {
            Toast.makeText(context.getApplicationContext(), R.string.app_non_web, 0).show();
        }
    }

    public static void a(Context context, String str, String str2, String str3, boolean z) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:" + str));
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", str3);
        intent.setFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (z) {
            Toast.makeText(context.getApplicationContext(), R.string.fpv_about_none_app, 0).show();
        }
    }

    public static void b(Context context, String str, boolean z) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + str));
        intent.setFlags(268435456);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (z) {
            Toast.makeText(context.getApplicationContext(), R.string.fpv_about_none_app, 0).show();
        }
    }

    public static void a(Context context, boolean z) {
        Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (z) {
            Toast.makeText(context.getApplicationContext(), R.string.fpv_about_none_app, 0).show();
        }
    }

    public static void b(Context context, boolean z) {
        Intent intent = new Intent("android.settings.SETTINGS");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (z) {
            Toast.makeText(context.getApplicationContext(), R.string.fpv_about_none_app, 0).show();
        }
    }

    public static void c(Context context, boolean z) {
        Intent intent = new Intent("android.settings.WIFI_SETTINGS");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (z) {
            Toast.makeText(context.getApplicationContext(), R.string.fpv_about_none_app, 0).show();
        }
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean a(String... strArr) {
        for (String a : strArr) {
            if (a(a)) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(String str, String str2) {
        boolean z = str == str2;
        if (z || str == null) {
            return z;
        }
        return str.equals(str2);
    }

    public static boolean b(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        return Pattern.compile(e).matcher(str.trim()).matches();
    }

    public static int a() {
        return TimeZone.getDefault().getOffset(new Date().getTime()) / 360000;
    }

    public static long a(byte[] bArr, int i, int i2) {
        long j = 0;
        if (bArr != null && bArr.length >= i + i2) {
            int i3 = (i + i2) - 1;
            while (i3 >= i) {
                long j2 = ((long) (bArr[i3] & 255)) + (j * 256);
                i3--;
                j = j2;
            }
        }
        return j;
    }

    public static long c(String str) {
        long j = 0;
        for (String parseInt : str.split("\\.")) {
            j = (j * 256) + ((long) Integer.parseInt(parseInt, 10));
        }
        return j;
    }

    public static String a(Context context) {
        String str = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String b = dji.pilot.c.b.b(context);
            if (b == null) {
                b = context.getResources().getString(R.string.buildname);
            }
            str = packageInfo.versionName + "." + b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void b(Context context) {
        DisplayImageOptions build = new DisplayImageOptions$Builder().imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(200)).cacheInMemory(true).cacheOnDisc(true).considerExifParams(true).bitmapConfig(Config.RGB_565).build();
        int maxMemory = (int) (((float) Runtime.getRuntime().maxMemory()) * 0.16f);
        if (maxMemory > f) {
            maxMemory = f;
        }
        ImageLoader.getInstance().init(new Builder(context).threadPoolSize(3).denyCacheImageMultipleSizesInMemory().discCacheSize(f).memoryCacheSize(maxMemory).discCacheFileNameGenerator(new Md5FileNameGenerator()).defaultDisplayImageOptions(build).build());
    }

    public static void c(Context context) {
        CrashReport.initCrashReport(context, "900019054", false);
        CrashReport.putUserData(context, "buildnum", dji.pilot.c.b.b(context));
    }

    public static String a(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        if (obj != null) {
            try {
                a(obj, stringBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    private static String a(Object obj, StringBuffer stringBuffer) throws Exception {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        String str = "";
        stringBuffer.append("------  begin ------\n");
        for (Field field : declaredFields) {
            stringBuffer.append(field.getName());
            stringBuffer.append(" : ");
            Object a = a(obj, field.getName(), field.getType(), null);
            if (a != null) {
                if (a.getClass().isArray()) {
                    for (int i = 0; i < Array.getLength(a); i++) {
                        Object obj2 = Array.get(a, i);
                        if (obj2.getClass().isPrimitive()) {
                            stringBuffer.append(obj2.toString());
                        } else if (obj2 instanceof String) {
                            stringBuffer.append(obj2.toString());
                        } else if (obj2 instanceof Date) {
                            stringBuffer.append(obj2.toString());
                        } else if (obj2 instanceof Number) {
                            stringBuffer.append(obj2.toString());
                        } else {
                            a(obj2, stringBuffer);
                        }
                    }
                }
                if (a.getClass().getName().indexOf("com.cignacmb.core.model.") > -1) {
                    a(a, stringBuffer);
                }
            }
            stringBuffer.append(a);
            stringBuffer.append("\n");
        }
        stringBuffer.append("------  end ------\n");
        return stringBuffer.toString();
    }

    private static Object a(Object obj, String str, Class cls, Object[] objArr) throws Exception {
        Class cls2 = obj.getClass();
        String str2 = str.substring(0, 1).toUpperCase() + str.substring(1);
        Method method = null;
        try {
            if (cls == Boolean.TYPE) {
                method = cls2.getMethod("is" + str2, new Class[0]);
            } else {
                method = cls2.getMethod("get" + str2, new Class[0]);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            return " can't find 'get" + str2 + "' method";
        }
        return method.invoke(obj, new Object[0]);
    }

    public static int[] a(MotorStartFailedCause motorStartFailedCause) {
        int[] iArr = new int[]{0, 0};
        switch (1.a[motorStartFailedCause.ordinal()]) {
            case 1:
                iArr[0] = R.string.takeoff_failed_tips_compass_error;
                break;
            case 2:
                iArr[0] = R.string.takeoff_failed_tips_assistant_protected;
                break;
            case 3:
                iArr[0] = R.string.takeoff_failed_tips_device_locked;
                break;
            case 4:
                iArr[0] = R.string.takeoff_failed_tips_distance_limit;
                break;
            case 5:
                iArr[0] = R.string.takeoff_failed_tips_imu_need_calibration;
                break;
            case 6:
                iArr[0] = R.string.takeoff_failed_tips_imu_sn_error;
                break;
            case 7:
                iArr[0] = R.string.takeoff_failed_tips_imu_warning;
                break;
            case 8:
                iArr[0] = R.string.takeoff_failed_tips_compass_calibrating;
                break;
            case 9:
                iArr[0] = R.string.takeoff_failed_tips_atti_error;
                break;
            case 10:
                iArr[0] = R.string.takeoff_failed_tips_novice_protected;
                break;
            case 11:
                iArr[0] = R.string.takeoff_failed_tips_battery_cell_error;
                break;
            case 12:
                iArr[0] = R.string.takeoff_failed_tips_battery_communite_error;
                break;
            case 13:
                iArr[0] = R.string.takeoff_failed_tips_seriou_low_voltage;
                break;
            case 14:
                iArr[0] = R.string.takeoff_failed_tips_seriou_low_power;
                break;
            case 15:
                iArr[0] = R.string.takeoff_failed_tips_low_voltage;
                break;
            case 16:
                iArr[0] = R.string.takeoff_failed_tips_tempure_vol_low;
                break;
            case 17:
                iArr[0] = R.string.takeoff_failed_tips_smart_low_to_land;
                break;
            case 18:
                iArr[0] = R.string.takeoff_failed_tips_battery_not_ready;
                break;
            case 19:
                iArr[0] = R.string.takeoff_failed_tips_simulator_mode;
                break;
            case 20:
                iArr[0] = R.string.takeoff_failed_tips_pack_mode;
                break;
            case 21:
                iArr[0] = R.string.takeoff_failed_tips_attitude_abnormal;
                break;
            case 22:
                iArr[0] = R.string.takeoff_failed_tips_unactive;
                break;
            case 24:
                iArr[0] = R.string.takeoff_failed_tips_bias_limit;
                break;
            case 25:
                iArr[0] = R.string.takeoff_failed_tips_esc_error;
                break;
            case 26:
                iArr[0] = R.string.takeoff_failed_tips_imu_initing;
                break;
            case 27:
                iArr[0] = R.string.takeoff_failed_tips_system_upgrade;
                break;
            case 28:
                iArr[0] = R.string.takeoff_failed_tips_simulator_started;
                break;
            case 29:
                iArr[0] = R.string.takeoff_failed_tips_imuing;
                break;
            case 30:
                iArr[0] = R.string.takeoff_failed_tips_attiangle;
                break;
            case 31:
                iArr[0] = R.string.takeoff_failed_tips_gyroscope_error;
                break;
            case 32:
                iArr[0] = R.string.takeoff_failed_tips_acceletor_error;
                break;
            case 33:
                iArr[0] = R.string.takeoff_failed_tips_compass_failed;
                break;
            case 34:
                iArr[0] = R.string.takeoff_failed_tips_barometer_error;
                break;
            case 35:
                iArr[0] = R.string.takeoff_failed_tips_barometer_negative;
                break;
            case 36:
                iArr[0] = R.string.takeoff_failed_tips_compass_big;
                break;
            case 37:
                iArr[0] = R.string.takeoff_failed_tips_gyroscope_bias_big;
                break;
            case 38:
                iArr[0] = R.string.takeoff_failed_tips_acceletor_bias_big;
                break;
            case 39:
                iArr[0] = R.string.takeoff_failed_tips_compass_noise_big;
                break;
            case 40:
                iArr[0] = R.string.takeoff_failed_tips_barometer_noise_big;
                break;
            case 41:
                iArr[0] = R.string.takeoff_failed_tips_invalid_sn;
                break;
            case 42:
                iArr[0] = R.string.error_aircraft_type_mismatch_title;
                iArr[1] = R.string.error_aircraft_type_mismatch_desc;
                break;
            case 43:
                iArr[0] = R.string.error_battery_authentication_exception_title;
                iArr[1] = R.string.error_battery_authentication_exception_desc;
                break;
            case 44:
                iArr[0] = R.string.error_battery_communication_exception_title;
                iArr[1] = R.string.error_battery_communication_exception_desc;
                break;
            case 45:
                iArr[0] = R.string.error_battery_num_not_enough_title;
                iArr[1] = R.string.error_battery_num_not_enough_desc;
                break;
            case 46:
                iArr[0] = R.string.error_battery_voltage_difference_large_title;
                iArr[1] = R.string.error_battery_voltage_difference_large_desc;
                break;
            case 47:
                iArr[0] = R.string.error_battery_voltage_difference_very_large_title;
                iArr[1] = R.string.error_battery_voltage_difference_very_large_desc;
                break;
            case 48:
                iArr[0] = R.string.error_device_topology_exception_title;
                iArr[1] = R.string.error_device_topology_exception_desc;
                break;
            case 49:
                iArr[0] = R.string.error_found_unfinished_module_title;
                iArr[1] = R.string.error_found_unfinished_module_desc;
                break;
            case 50:
                iArr[0] = R.string.error_imu_noconnection_title;
                iArr[1] = R.string.error_imu_noconnection_desc;
                break;
            case 51:
                iArr[0] = R.string.error_imu_calibration_finished_title;
                iArr[1] = R.string.error_imu_calibration_finished_desc;
                break;
            case 52:
                iArr[0] = R.string.error_ns_exception_title;
                iArr[1] = R.string.error_ns_exception_desc;
                break;
            case 53:
                iArr[0] = R.string.error_rc_calibration_title;
                iArr[1] = R.string.error_rc_calibration_desc;
                break;
            case 54:
                iArr[0] = R.string.error_rc_calibration_exception_title;
                iArr[1] = R.string.error_rc_calibration_exception_desc;
                break;
            case 55:
                iArr[0] = R.string.error_rc_calibration_exception2_title;
                iArr[1] = R.string.error_rc_calibration_exception2_desc;
                break;
            case 56:
                iArr[0] = R.string.error_rc_calibration_exception3_title;
                iArr[1] = R.string.error_rc_calibration_exception3_desc;
                break;
            case 57:
                iArr[0] = R.string.error_rc_calibration_exception4_title;
                iArr[1] = R.string.error_rc_calibration_exception4_desc;
                break;
            case 58:
                iArr[0] = R.string.error_rc_calibration_unfinished_title;
                iArr[1] = R.string.error_rc_calibration_unfinished_desc;
                break;
            case 59:
                iArr[0] = R.string.error_sdcard_exception_title;
                iArr[1] = R.string.error_sdcard_exception_desc;
                break;
            case 60:
                iArr[0] = R.string.error_software_data_invalid_title;
                iArr[1] = R.string.error_software_data_invalid_desc;
                break;
            case 61:
                iArr[0] = R.string.error_version_mismatch_title;
                iArr[1] = R.string.error_version_mismatch_desc;
                break;
            case 62:
                iArr[0] = R.string.error_baro_abnormal;
                break;
            case 63:
                iArr[0] = R.string.error_compass_abnormal;
                break;
            case 64:
                iArr[0] = R.string.error_flash_operating;
                break;
            case 65:
                iArr[0] = R.string.error_gps_abnormal;
                break;
            case Place.TYPE_MUSEUM /*66*/:
                iArr[0] = R.string.error_gps_disconnect;
                break;
            case 67:
                iArr[0] = R.string.error_gimbal_abnormal;
                iArr[1] = R.string.error_gimbal_abnormal_desc;
                break;
            case Place.TYPE_PAINTER /*68*/:
                iArr[0] = R.string.error_gimbal_esc_pitch_error;
                iArr[1] = R.string.error_gimbal_esc_pitch_error_desc;
                break;
            case 69:
                iArr[0] = R.string.error_gimbal_esc_roll_error;
                iArr[1] = R.string.error_gimbal_esc_roll_error_desc;
                break;
            case Place.TYPE_PARKING /*70*/:
                iArr[0] = R.string.error_gimbal_esc_yaw_error;
                iArr[1] = R.string.error_gimbal_esc_yaw_error_desc;
                break;
            case 71:
                iArr[0] = R.string.error_gimbal_is_updating;
                iArr[1] = R.string.error_gimbal_is_updating_desc;
                break;
            case 72:
                iArr[0] = R.string.error_gimbal_disorder;
                iArr[1] = R.string.error_gimbal_disorder_desc;
                break;
            case 73:
                iArr[0] = R.string.error_gimbal_pitch_shock;
                iArr[1] = R.string.error_gimbal_pitch_shock_desc;
                break;
            case Place.TYPE_PLACE_OF_WORSHIP /*74*/:
                iArr[0] = R.string.error_gimbal_roll_shock;
                iArr[1] = R.string.error_gimbal_roll_shock_desc;
                break;
            case 75:
                iArr[0] = R.string.error_gimbal_yaw_shock;
                iArr[1] = R.string.error_gimbal_yaw_shock_desc;
                break;
            case 76:
                iArr[0] = R.string.error_rtk_bad_signal;
                break;
            case 77:
                iArr[0] = R.string.error_rtk_deviation;
                break;
            case Place.TYPE_REAL_ESTATE_AGENCY /*78*/:
                iArr[0] = R.string.error_gimbal_is_calibrating;
                break;
            case 79:
                iArr[0] = R.string.error_esc_calibrating_desc;
                iArr[1] = R.string.error_esc_calibrating_soul;
                break;
            case 80:
                iArr[0] = R.string.error_gps_sign_invalid_desc;
                break;
            case Place.TYPE_RV_PARK /*81*/:
                iArr[0] = R.string.error_lock_by_app_desc;
                break;
            case Place.TYPE_SCHOOL /*82*/:
                iArr[0] = R.string.error_start_fly_height_error_desc;
                iArr[1] = R.string.error_start_fly_height_error_soul;
                break;
            case Place.TYPE_SHOE_STORE /*83*/:
                iArr[0] = R.string.error_esc_version_not_match_desc;
                iArr[1] = R.string.error_esc_version_not_match_soul;
                break;
            case Place.TYPE_SHOPPING_MALL /*84*/:
                iArr[0] = R.string.error_imu_ori_not_match_desc;
                iArr[1] = R.string.error_imu_ori_not_match_soul;
                break;
            case Place.TYPE_SPA /*85*/:
                iArr[0] = R.string.error_stop_by_app_desc;
                break;
        }
        return iArr;
    }

    public static void a(String str, int i) {
        if (a.G) {
            if (str == null) {
                str = "printStack";
            }
            StackTraceElement[] stackTrace = new Exception().getStackTrace();
            int length = stackTrace.length;
            DJILogHelper.getInstance().LOGE(str, "************Start printStack time=" + new Date(System.currentTimeMillis()), str);
            if (i <= 0 || i >= length - 1) {
                i = length;
            }
            for (length = 1; length < i; length++) {
                DJILogHelper.getInstance().LOGE(str, stackTrace[length].toString(), str);
            }
            DJILogHelper.getInstance().LOGE(str, "************End printStack*****************", str);
        }
    }

    public static void d(String str) {
        a(str, 3);
    }
}
