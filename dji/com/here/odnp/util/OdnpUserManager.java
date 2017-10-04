package com.here.odnp.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import com.tencent.android.tpush.common.Constants;

public class OdnpUserManager {
    private static final String TAG = "odnp.util.OdnpUserManager";

    private OdnpUserManager() {
    }

    @TargetApi(17)
    public static int getCurrentUser(Context context) {
        try {
            return ((Integer) ActivityManager.class.getMethod("getCurrentUser", new Class[0]).invoke((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME), new Object[0])).intValue();
        } catch (Exception e) {
            return 0;
        }
    }
}
