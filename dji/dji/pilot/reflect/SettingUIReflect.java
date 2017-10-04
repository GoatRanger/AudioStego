package dji.pilot.reflect;

import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;

public class SettingUIReflect {
    public static int getRcCustomStringId(DJICustomType dJICustomType) {
        Object obj = get("getRcCustomStringId", dJICustomType);
        if (obj != null) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    private static void invoke(String str) {
        get(str);
    }

    private static void invoke(String str, Object obj) {
        get(str, obj);
    }

    private static Object get(String str, Object obj) {
        try {
            Class cls = Class.forName("dji.setting.ui.reflect.SettingUIReflect");
            return cls.getMethod(str, new Class[]{obj.getClass()}).invoke(cls, new Object[]{obj});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Object get(String str) {
        try {
            Class cls = Class.forName("dji.setting.ui.reflect.SettingUIReflect");
            return cls.getMethod(str, new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
