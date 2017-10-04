package dji.setting.ui.reflect;

import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.setting.ui.rc.C1C2View;

public class SettingUIReflect {
    public static int getRcCustomStringId(DJICustomType dJICustomType) {
        return C1C2View.getStringByType(dJICustomType);
    }

    public static String getAppVersion() {
        Object obj = get("getAppVersion");
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }

    public static boolean isSDKToolsExist() {
        try {
            Object obj = Class.forName("com.dji.sdktools.Utils").getField("isSDKToolsExist").get(null);
            if (obj != null) {
                return ((Boolean) obj).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
