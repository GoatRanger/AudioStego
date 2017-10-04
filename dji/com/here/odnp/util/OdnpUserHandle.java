package com.here.odnp.util;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.UserHandle;
import java.lang.reflect.Field;

public class OdnpUserHandle {
    public static final UserHandle CURRENT = getUserHandleField("CURRENT");
    public static final UserHandle OWNER = getUserHandleField("OWNER");
    private static final String TAG = "odnp.util.OdnpUserHandle";
    public static final int USER_CURRENT = getIntField("USER_CURRENT", -2);
    public static final int USER_OWNER = getIntField("USER_CURRENT", 0);

    private OdnpUserHandle() {
    }

    @TargetApi(17)
    public static UserHandle createUserHandle(int i) {
        try {
            return (UserHandle) UserHandle.class.getDeclaredConstructor(new Class[]{Integer.TYPE}).newInstance(new Object[]{Integer.valueOf(i)});
        } catch (Exception e) {
            return null;
        }
    }

    @TargetApi(17)
    public static int getCallingUserId() {
        try {
            return ((Integer) UserHandle.class.getMethod("getCallingUserId", new Class[0]).invoke(null, new Object[0])).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    @TargetApi(17)
    private static int getIntField(String str, int i) {
        if (VERSION.SDK_INT >= 17) {
            try {
                Field declaredField = UserHandle.class.getDeclaredField(str);
                declaredField.setAccessible(true);
                i = ((Integer) declaredField.get(null)).intValue();
            } catch (Exception e) {
            }
        }
        return i;
    }

    @TargetApi(17)
    private static UserHandle getUserHandleField(String str) {
        if (VERSION.SDK_INT >= 17) {
            try {
                Field declaredField = UserHandle.class.getDeclaredField(str);
                declaredField.setAccessible(true);
                return (UserHandle) declaredField.get(null);
            } catch (Exception e) {
            }
        }
        return null;
    }
}
