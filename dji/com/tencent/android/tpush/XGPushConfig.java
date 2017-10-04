package com.tencent.android.tpush;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import com.d.a;
import com.d.c;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.e;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.util.ArrayList;
import java.util.List;

@c(a = 1, b = 3, c = "20150316", e = {a.RECEIVERCHECK, a.INTENTCHECK}, f = "确认已进行安全校验")
public class XGPushConfig {
    public static final String TPUSH_ACCESS_ID = "XG_V2_ACCESS_ID";
    public static final String TPUSH_ACCESS_KEY = "XG_V2_ACCESS_KEY";
    private static final String a = XGPushConfig.class.getSimpleName();
    private static String b = "";
    private static String c = "";
    private static long d = -1;
    private static String e = "";
    public static boolean enableDebug = false;

    public static synchronized long getAccessId(Context context) {
        long j;
        synchronized (XGPushConfig.class) {
            if (context == null) {
                j = d;
            } else if (d != -1) {
                j = d;
            } else if (TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                if (defaultSharedPreferences != null) {
                    String string = defaultSharedPreferences.getString(TPUSH_ACCESS_ID, null);
                    if (string != null) {
                        try {
                            d = Long.valueOf(Rijndael.decrypt(string)).longValue();
                        } catch (Throwable e) {
                            d = -1;
                            com.tencent.android.tpush.a.a.b(a, "get accessId error", e);
                        }
                    }
                }
                if (d == -1) {
                    Object a = e.a(context, TPUSH_ACCESS_ID, null);
                    if (a != null) {
                        try {
                            d = Long.valueOf(a.toString()).longValue();
                        } catch (Throwable e2) {
                            com.tencent.android.tpush.a.a.b(Constants.LogTag, "get accessId from getMetaData failed: ", e2);
                            d = -1;
                        }
                    }
                }
                if (d == -1) {
                    com.tencent.android.tpush.a.a.h(Constants.LogTag, "accessId没有初始化");
                }
                j = d;
            } else {
                j = d;
            }
        }
        return j;
    }

    public static void setAccessId(Context context, long j) {
        if (context == null) {
            com.tencent.android.tpush.a.a.h(a, "null  context");
            return;
        }
        d = j;
        g.a().a(new p(context, j));
    }

    public static synchronized String getAccessKey(Context context) {
        String str = null;
        synchronized (XGPushConfig.class) {
            if (!com.tencent.android.tpush.service.d.e.a(e)) {
                str = e;
            } else if (TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                if (defaultSharedPreferences != null) {
                    str = defaultSharedPreferences.getString(TPUSH_ACCESS_KEY, null);
                    if (com.tencent.android.tpush.service.d.e.a(str)) {
                        e = Rijndael.decrypt(str);
                    }
                }
                if (com.tencent.android.tpush.service.d.e.a(e)) {
                    Object a = e.a(context, TPUSH_ACCESS_KEY, null);
                    if (a != null) {
                        e = a.toString();
                    }
                }
                if (com.tencent.android.tpush.service.d.e.a(e)) {
                    com.tencent.android.tpush.a.a.h(a, "accessKey is null");
                }
                str = e;
            }
        }
        return str;
    }

    public static void setAccessKey(Context context, String str) {
        if (context == null || str == null) {
            com.tencent.android.tpush.a.a.h(Constants.LogTag, "null context or null accessKey");
            return;
        }
        e = str;
        g.a().a(new q(context, str));
    }

    public static String getToken(Context context) {
        if (context != null) {
            return CacheManager.getToken(context);
        }
        com.tencent.android.tpush.a.a.h(Constants.LogTag, "null context");
        return null;
    }

    public static void enableDebug(Context context, boolean z) {
        if (context != null) {
            enableDebug = z;
            g.a().a(new r(context, z));
        }
    }

    public static boolean isEnableDebug(Context context) {
        return com.tencent.android.tpush.service.d.e.b(context, new StringBuilder().append("com.tencent.android.tpush.debug,").append(context.getPackageName()).toString(), 0) != 0;
    }

    public static List getAccessidList(Context context) {
        ArrayList arrayList = new ArrayList(2);
        if (context != null) {
            long accessId = getAccessId(context);
            if (accessId > 0) {
                arrayList.add(Long.valueOf(accessId));
            }
            accessId = XGPush4Msdk.getQQAccessId(context);
            if (accessId > 0) {
                arrayList.add(Long.valueOf(accessId));
            }
            Object a = e.a(context, TPUSH_ACCESS_ID, null);
            if (a != null) {
                try {
                    accessId = Long.valueOf(a.toString()).longValue();
                    if (!arrayList.contains(Long.valueOf(accessId))) {
                        arrayList.add(Long.valueOf(accessId));
                    }
                } catch (Throwable e) {
                    com.tencent.android.tpush.a.a.b(a, "get accessId from getMetaData failed: ", e);
                }
            }
        }
        return arrayList;
    }

    public static void setInstallChannel(Context context, String str) {
        if (context != null && str != null && str.trim().length() != 0) {
            b = str;
        }
    }

    public static String getInstallChannel(Context context) {
        return b;
    }

    public static void setGameServer(Context context, String str) {
        if (context != null && str != null && str.trim().length() != 0) {
            c = str;
        }
    }

    public static String getGameServer(Context context) {
        return c;
    }

    public static void setHeartbeatIntervalMs(Context context, int i) {
        if (context != null && i >= 5000 && i < 1800000) {
            try {
                m.b(context, "com.tencent.android.xg.wx.HeartbeatIntervalMs", i);
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(a, "setHeartbeatIntervalMs", e);
            }
        }
    }

    public static void setReportDebugMode(Context context, boolean z) {
        if (context != null) {
            try {
                System.putInt(context.getContentResolver(), context.getPackageName() + ".report.mode", z ? 1 : 0);
            } catch (Throwable th) {
                com.tencent.android.tpush.a.a.c(a, "setReportDebugMode", th);
            }
        }
    }

    public static boolean getReportDebugMode(Context context) {
        try {
            if (System.getInt(context.getContentResolver(), context.getPackageName() + ".report.mode") != 0) {
                return true;
            }
            return false;
        } catch (SettingNotFoundException e) {
            return false;
        }
    }
}
