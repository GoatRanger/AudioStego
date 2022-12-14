package com.tencent.android.tpush;

import android.content.Context;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.b.b;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.service.d.e;
import dji.pilot2.multimoment.view.HorizonalSegmentView;

public class XGPush4Msdk {
    private static long a = 0;
    private static long b = 0;
    private static String c = "";

    private static String b(Context context) {
        return context.getPackageName() + ":" + "XG_DEBUG_SERVER_INFO";
    }

    public static void setDebugServerInfo(Context context, String str, int i) {
        if (e.a(str)) {
            g.a().a(new c(context));
        } else {
            m.b(context, b(context), str + "," + i);
        }
    }

    public static String getDebugServerInfo(Context context) {
        return m.a(context, b(context), null);
    }

    private static boolean a(long j, long j2, long j3) {
        return j >= j2 && j < j3;
    }

    public static void setQQAppId(Context context, long j) {
        long j2;
        if (a(j, 0, 200000)) {
            j2 = 90000000;
        } else if (a(j, 99000000, 100000000)) {
            j2 = 0;
        } else if (a(j, 100200000, 100600000)) {
            j2 = -10000000;
        } else if (a(j, 101000000, 101400000)) {
            j2 = -10400000;
        } else if (a(j, 900000000, 900100000)) {
            j2 = -809000000;
        } else if (a(j, 1000000000, 1000100000)) {
            j2 = -908900000;
        } else if (a(j, 1101000000, 1104500000)) {
            j2 = -1009800000;
        } else if (a(j, 1150000000, 1150100000)) {
            j2 = -1055300000;
        } else if (a(j, 100600000, 101000000)) {
            j2 = -5800000;
        } else {
            a.h(Constants.MSDK_TAG, "手Q的appid：" + j + " 不在固定的范围，请联系msdk和信鸽的同事解决之。");
            j2 = 0;
        }
        j2 = (j2 + 2100000000) + j;
        a = j;
        b = j2;
        m.b(context, "TPUSH_QQ_ACCESS_ID", b);
        m.b(context, "TPUSH_QQ_APP_ID", j);
    }

    public static long getQQAccessId(Context context) {
        return m.a(context, "TPUSH_QQ_ACCESS_ID", b);
    }

    public static void setQQAppKey(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            a.c(Constants.MSDK_TAG, "convert qq AppKey:" + str + " to accessKey:" + str);
        }
        c = str;
        m.b(context, "TPUSH_QQ_ACCESS_KEY", c);
    }

    public static String getQQAppKey(Context context) {
        return m.a(context, "TPUSH_QQ_ACCESS_KEY", c);
    }

    public static void setTag(Context context, String str) {
        a.c(Constants.MSDK_TAG, "setTag: tagName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        XGPushManager.a(context, str, 1, getQQAccessId(context));
    }

    public static void deleteTag(Context context, String str) {
        if (XGPushConfig.enableDebug) {
            a.c(Constants.MSDK_TAG, "deleteTag: tagName=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        XGPushManager.a(context, str, 2, getQQAccessId(context));
    }

    public static void registerPush(Context context, String str, XGIOperateCallback xGIOperateCallback) {
        XGIOperateCallback dVar;
        if (XGPushConfig.enableDebug) {
            a.d(Constants.MSDK_TAG, "registerPush: account=" + str + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (xGIOperateCallback == null) {
            dVar = new d();
        } else {
            dVar = xGIOperateCallback;
        }
        if (e.a(str)) {
            XGPushManager.a(context, null, null, -1, null, dVar, getQQAccessId(context), getQQAppKey(context));
            return;
        }
        XGPushManager.a(context, str, "0", 0, null, dVar, getQQAccessId(context), getQQAppKey(context));
    }

    public static void unregisterPush(Context context, XGIOperateCallback xGIOperateCallback) {
        if (XGPushConfig.enableDebug) {
            a.d(Constants.MSDK_TAG, "unregisterPush,qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        if (xGIOperateCallback == null) {
            xGIOperateCallback = new e();
        }
        XGPushManager.a(context, xGIOperateCallback, getQQAccessId(context), getQQAppKey(context));
    }

    public static long addLocalNotification(Context context, XGLocalMessage xGLocalMessage) {
        if (XGPushConfig.enableDebug) {
            a.d(Constants.MSDK_TAG, "addLocalNotification:msg=" + xGLocalMessage.toString() + ",qqAppid=" + a + ",xg_accessid=" + getQQAccessId(context));
        }
        return XGPushManager.a(context, xGLocalMessage, getQQAccessId(context));
    }

    public static void setPushNotificationBuilder(Context context, int i, XGPushNotificationBuilder xGPushNotificationBuilder) {
        if (context == null) {
            throw new IllegalArgumentException("context is null.");
        } else if (i < 5000 || i > HorizonalSegmentView.N) {
            throw new IllegalArgumentException("notificationBulderId超过范围[5000, 6000].");
        } else if (xGPushNotificationBuilder != null) {
            b.a(context, i, xGPushNotificationBuilder);
        }
    }

    public static void setDefaultNotificationBuilder(Context context, XGPushNotificationBuilder xGPushNotificationBuilder) {
        XGPushManager.setDefaultNotificationBuilder(context, xGPushNotificationBuilder);
    }
}
