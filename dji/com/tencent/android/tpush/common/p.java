package com.tencent.android.tpush.common;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushActivity;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushReceiver;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.rpc.XGRemoteService;
import com.tencent.android.tpush.service.XGPushService;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.d.e;
import com.tencent.android.tpush.service.l;
import com.tencent.android.tpush.service.u;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class p {
    private static AtomicBoolean a = new AtomicBoolean(false);
    private static boolean b = false;

    public static int a(Context context) {
        if (a.get()) {
            return 0;
        }
        try {
            if (!h(context)) {
                a.i("Util", "XG is disable");
                return Constants.CODE_SERVICE_DISABLED;
            } else if (!TpnsSecurity.checkTpnsSecurityLibSo(context)) {
                a.i("Util", "can not load library from so file");
                return Constants.CODE_SO_ERROR;
            } else if (!l.a(context)) {
                return Constants.CODE_PERMISSIONS_ERROR;
            } else {
                if (b(context)) {
                    if (!i(context)) {
                        a.h("Util", "You should set com.tencent.android.tpush.XGPushActivity's property android:exported = true in the AndroidManifest.xml");
                    }
                    e.m(context);
                    a.set(true);
                    return 0;
                }
                a.i("Util", "The service rpc.XGRemoteService is unfined, Please add it in AndroidManifest.xml");
                return Constants.CODE_AIDL_ERROR;
            }
        } catch (Throwable th) {
            a.c("Util", "Util -> initGlobal", th);
            return -1;
        }
    }

    public static boolean b(Context context) {
        try {
            List a = e.a(context, context.getPackageName() + Constants.RPC_SUFFIX);
            if (a != null && a.size() > 0) {
                return true;
            }
        } catch (Throwable th) {
            a.c("Util", "Util -> isAIDLConfiged", th);
        }
        return false;
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String c(Context context) {
        if (context != null) {
            return Rijndael.decrypt(e.a(context, Constants.SETTINGS_SERVICE_PACKAGE_NAME, false));
        }
        return "";
    }

    public static int d(Context context) {
        if (context != null) {
            try {
                List<RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(Integer.MAX_VALUE);
                if (runningServices != null && runningServices.size() > 0) {
                    String name = XGPushService.class.getName();
                    for (RunningServiceInfo runningServiceInfo : runningServices) {
                        String className = runningServiceInfo.service.getClassName();
                        if (!name.equals(className)) {
                            if ("com.tencent.android.tpush.service.XGPushService".equals(className)) {
                            }
                        }
                        return runningServiceInfo.pid != 0 ? 1 : 2;
                    }
                }
            } catch (Throwable th) {
                a.c(Constants.ServiceLogTag, "getServiceStatus", th);
            }
        }
        return 0;
    }

    public static void e(Context context) {
        if (context != null) {
            l.c(context.getApplicationContext());
            List a = e.a(context);
            if (a == null || o.a(context).a() || a.size() < 1 || (a.size() < 2 && ((ResolveInfo) a.get(0)).activityInfo.packageName.equals(context.getPackageName()))) {
                l.a(context);
                a.a(Constants.ServiceLogTag, "Action -> start Local Service()");
            } else {
                context.sendBroadcast(new Intent(Constants.ACTION_SDK_INSTALL));
            }
            g.a().a(new q(context), 1500);
        }
    }

    public static boolean a(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
            return true;
        } catch (Throwable e) {
            a.c("Util", "safeUnregisterReceiver error", e);
            return false;
        }
    }

    public static String f(Context context) {
        Throwable th;
        String str = "";
        String str2;
        try {
            str2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str2 != null) {
                return str2;
            }
            try {
                return "";
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str2 = str;
            th = th4;
            a.c("Util", "get app version error", th);
            return str2;
        }
    }

    private static void a(Context context, String str) {
        if (context != null && str != null && str.trim().length() != 0) {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ComponentName componentName = new ComponentName(context.getPackageName(), str);
                if (packageManager.getComponentEnabledSetting(componentName) != 1) {
                    packageManager.setComponentEnabledSetting(componentName, 1, 1);
                }
            }
        }
    }

    public static void g(Context context) {
        if (context != null && !b) {
            try {
                a(context, XGPushService.class.getName());
                a(context, XGPushActivity.class.getName());
                a(context, XGRemoteService.class.getName());
                for (ActivityInfo activityInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers) {
                    String str = activityInfo.name;
                    Class loadClass = context.getClassLoader().loadClass(str);
                    if (XGPushBaseReceiver.class.isAssignableFrom(loadClass) || loadClass.getName().equals(XGPushReceiver.class.getName())) {
                        a(context, str);
                    }
                }
            } catch (Throwable e) {
                a.c("Util", "enableComponents", e);
            }
            b = true;
        }
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(j));
        } catch (Throwable e) {
            a.c("Util", "getDateString", e);
            return "20141111";
        }
    }

    public static boolean h(Context context) {
        if (context == null) {
            return true;
        }
        if (XGPushManager.enableService == -1) {
            XGPushManager.enableService = m.a(context, XGPushManager.getServiceTag(context), 2);
        }
        if (XGPushManager.enableService == 2 && TpnsSecurity.checkTpnsSecurityLibSo(context)) {
            String a = e.a(context, com.tencent.android.tpush.service.a.a.b("stopXG"), true);
            if (!a(a)) {
                String[] split = Rijndael.decrypt(a).split(",");
                Map hashMap = new HashMap();
                for (String valueOf : split) {
                    try {
                        hashMap.put(Long.valueOf(valueOf), Long.valueOf(0));
                    } catch (NumberFormatException e) {
                    }
                }
                if (hashMap.size() > 0) {
                    if (XGPushConfig.getAccessId(context) > 0 && hashMap.containsKey(Long.valueOf(XGPushConfig.getAccessId(context)))) {
                        XGPushManager.enableService(context, false);
                        return false;
                    } else if (XGPush4Msdk.getQQAccessId(context) > 0 && hashMap.containsKey(Long.valueOf(XGPush4Msdk.getQQAccessId(context)))) {
                        XGPushManager.enableService(context, false);
                        return false;
                    }
                }
            }
        }
        if (XGPushManager.enableService != 0) {
            return true;
        }
        return false;
    }

    public static boolean i(Context context) {
        boolean z = false;
        try {
            return context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), XGPushActivity.class.getName()), 0).exported;
        } catch (Throwable e) {
            a.c(Constants.LogTag, "Util -> checkActivityIsExported", e);
            return z;
        }
    }

    public static boolean j(Context context) {
        try {
            return ((PowerManager) context.getSystemService("power")).isScreenOn();
        } catch (Throwable e) {
            a.c(Constants.LogTag, "Util -> isScreenOn", e);
            return false;
        }
    }

    public static int k(Context context) {
        int i = -1;
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("status", -1);
            Object obj = (intExtra == 2 || intExtra == 5) ? 1 : null;
            if (obj != null) {
                i = registerReceiver.getIntExtra("plugged", -1);
            }
        } catch (Throwable e) {
            a.c(Constants.LogTag, "Util -> getChangedStatus", e);
        }
        return i;
    }

    public static void l(Context context) {
        if (context == null) {
            a.h("Util", "Util -> getWakeCpu error null context");
            return;
        }
        try {
            u.a().a(((PowerManager) context.getSystemService("power")).newWakeLock(536870913, "TPUSH"));
            if (!u.a().b().isHeld()) {
                u.a().b().acquire();
            }
            a.c("Util", "get Wake Cpu ");
        } catch (Throwable th) {
            a.c("Util", "get Wake cpu", th);
        }
    }

    public static void a() {
        try {
            WakeLock b = u.a().b();
            if (b != null) {
                if (b.isHeld()) {
                    b.release();
                }
                a.c("Util", "stop WakeLock CPU");
            }
        } catch (Throwable th) {
            a.c("Util", "stopWakeLock", th);
        }
    }
}
