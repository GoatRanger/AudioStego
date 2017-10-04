package dji.pilot2.utils;

import android.content.Context;
import com.tencent.android.tpush.XGPushManager;
import dji.pilot.usercenter.b.f;

public class s {
    public static void a(Context context) {
        if (f.getInstance().c()) {
            XGPushManager.registerPush(context.getApplicationContext(), f.getInstance().j(), new 1());
        } else {
            XGPushManager.registerPush(context.getApplicationContext(), new 2());
        }
    }

    public static void b(Context context) {
        if (f.getInstance().c()) {
            XGPushManager.registerPush(context.getApplicationContext(), f.getInstance().j());
        }
    }

    public static void c(Context context) {
        XGPushManager.registerPush(context.getApplicationContext(), "*");
    }

    public static void a(Context context, String str) {
        XGPushManager.setTag(context.getApplicationContext(), str);
    }

    public static void b(Context context, String str) {
        XGPushManager.deleteTag(context.getApplicationContext(), str);
    }
}
