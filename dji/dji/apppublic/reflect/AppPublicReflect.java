package dji.apppublic.reflect;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.Switch;
import dji.pilot.fpv.control.DJIRedundancySysController.c;

public class AppPublicReflect {
    public static void notifyConfigChange(String str) {
        invoke("notifyConfigChange", str);
    }

    public static String getDJIMemberManager_getEmail() {
        Object obj = get("getDJIMemberManager_getEmail");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public static String getDJIMemberManager_getUID() {
        Object obj = get("getDJIMemberManager_getUID");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public static boolean getBuildConfig_DEBUG() {
        Object obj = get("getBuildConfig_DEBUG");
        if (obj != null) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public static void dji_gs_Config_setUnitFT(boolean z) {
        invoke("dji_gs_Config_setUnitFT", Boolean.valueOf(z));
    }

    public static void dji_gs_utils_GpsUtils_OPEN(boolean z) {
        invoke("dji_gs_utils_GpsUtils_OPEN", Boolean.valueOf(z));
    }

    public static void handleParamUnitChanged(int i) {
        invoke("handleParamUnitChanged", Integer.valueOf(i));
    }

    public static void postVideoAllDeleteEvent() {
        invoke("postVideoAllDeleteEvent");
    }

    public static double[] getLocation() {
        Object obj = get("getLocation");
        if (obj != null) {
            return (double[]) obj;
        }
        return null;
    }

    public static String getAircraftVersion() {
        Object obj = get("getAircraftVersion");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public static String getAircraftLB2Version() {
        Object obj = get("getAircraftLB2Version");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public static String getCameraVersion() {
        Object obj = get("getCameraVersion");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public static String getRcVersion() {
        Object obj = get("getRcVersion");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public static Boolean isFactoryMode() {
        Object obj = get("isFactoryMode");
        if (obj != null) {
            return (Boolean) obj;
        }
        return Boolean.valueOf(false);
    }

    public static Boolean isInnerToolsApk() {
        Object obj = get("isInnerToolsApk");
        if (obj != null) {
            return (Boolean) obj;
        }
        return Boolean.valueOf(false);
    }

    public static long getStreamTime() {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            return ((Long) cls.getMethod("getStreamTime", new Class[0]).invoke(cls, new Object[0])).longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isRunning() {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            return ((Boolean) cls.getMethod("isRunning", new Class[0]).invoke(cls, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isLaunch() {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            return ((Boolean) cls.getMethod("isLaunch", new Class[0]).invoke(cls, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void GS_HOME_CIRCLE_EVENT_UPDATE() {
        invoke("GS_HOME_CIRCLE_EVENT_UPDATE");
    }

    public static void showConnectWarning() {
        invoke("showConnectWarning");
    }

    public static void DismissConnectWaning() {
        invoke("DismissConnectWaning");
    }

    public static void postErrorModel(int i, int i2, int i3) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.SettingUIReflect");
            cls.getMethod("postErrorModel", new Class[]{Integer.class, Integer.class, Integer.class}).invoke(cls, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isOpenAllChanel() {
        Object obj = get("isOpenAllChanel");
        if (obj == null) {
            return true;
        }
        return ((Boolean) obj).booleanValue();
    }

    public static String getDM368Version() {
        Object obj = get("getDM368Version");
        if (obj == null) {
            return null;
        }
        return (String) obj;
    }

    public static void enterLiveShare(Context context, int i) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            cls.getMethod("enterLiveActivity", new Class[]{Context.class, Integer.class}).invoke(cls, new Object[]{context, Integer.valueOf(i)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleLiveShare(Context context) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            cls.getMethod("handleLiveShare", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopLiveShare(Activity activity) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            cls.getMethod("stopLiveShare", new Class[]{Activity.class}).invoke(cls, new Object[]{activity});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enterYoutubeLive(Context context) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            cls.getMethod("enterYoutubeLive", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void youtubeInit(Switch switchR) {
        invoke("youtubeInit", switchR);
    }

    public static void youtubeUnInit() {
        invoke("youtubeUnInit");
    }

    public static void facebookInit(Button button) {
        invoke("facebookInit", button);
    }

    public static void facebookUnInit() {
        invoke("facebookUnInit");
    }

    public static void enterFacebookLive() {
        invoke("enterFacebookLive");
    }

    public static void sensorPopWindow(c cVar) {
        invoke("sensorPopWindow", cVar);
    }

    public static void sensorPopWindow(Integer num) {
        invoke("sensorPopWindow", num);
    }

    private static void invoke(String str) {
        get(str);
    }

    private static void invoke(String str, Object obj) {
        get(str, obj);
    }

    private static Object get(String str, Object obj) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            return cls.getMethod(str, new Class[]{obj.getClass()}).invoke(cls, new Object[]{obj});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object get(String str) {
        try {
            Class cls = Class.forName("dji.pilot.reflect.AppPublicReflect");
            return cls.getMethod(str, new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
