package com.tencent.android.tpush.service;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.f.a.a.g;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.encrypt.a;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.AppInfo;
import com.tencent.android.tpush.service.channel.protocol.DeviceInfo;
import com.tencent.android.tpush.service.channel.protocol.MutableInfo;
import com.tencent.android.tpush.service.channel.protocol.NetworkInfo;
import com.tencent.android.tpush.service.channel.protocol.TpnsConfigReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsGetApListReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClickReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsRegisterReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsTokenTagReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;
import com.tencent.android.tpush.service.channel.protocol.UnregInfo;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import com.tencent.android.tpush.service.d.c;
import com.tencent.android.tpush.service.d.e;
import com.tencent.android.tpush.stat.a.h;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;

public class o {
    private static o a = new o();
    private static JSONArray b = new JSONArray();
    private static final String c = a.a("com.tencent.tpush.last_wifi_ts");
    private p d = new p(this);

    public static o a() {
        return a;
    }

    public void a(g gVar, com.tencent.android.tpush.service.channel.a aVar) {
        if (gVar != null) {
            if (gVar instanceof TpnsPushClientReq) {
                TpnsPushClientReq tpnsPushClientReq = (TpnsPushClientReq) gVar;
                com.tencent.android.tpush.service.b.a.a().a(tpnsPushClientReq.msgList, tpnsPushClientReq.timeUs, aVar);
                return;
            }
            com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", "onReceivedServicePush unhandle message type" + gVar.getClass().getName());
        }
    }

    public com.tencent.android.tpush.service.channel.o b() {
        if (e.d(l.e())) {
            g tpnsReconnectReq = new TpnsReconnectReq();
            tpnsReconnectReq.deviceId = c.a();
            tpnsReconnectReq.networkType = (short) e.e(l.e());
            tpnsReconnectReq.token = CacheManager.getToken(l.e());
            tpnsReconnectReq.unregInfoList = CacheManager.getUninstallAndUnregisterInfo(l.e());
            tpnsReconnectReq.recvMsgList = com.tencent.android.tpush.service.b.a.a().b(l.e(), com.tencent.android.tpush.service.b.a.a().b(l.e()));
            tpnsReconnectReq.msgClickList = com.tencent.android.tpush.service.b.a.a().a(l.e());
            tpnsReconnectReq.sdkVersion = String.valueOf(Constants.PUSH_SDK_VERSION);
            Context e = l.e();
            MutableInfo mutableInfo = new MutableInfo();
            if (e != null && h.j(e) && h.k(e)) {
                mutableInfo.bssid = h.h(e);
                mutableInfo.ssid = h.i(e);
            }
            mutableInfo.mac = h.f(e);
            try {
                mutableInfo.wflist = b(e);
            } catch (Exception e2) {
                com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> getWifiList(" + e + ")" + e2);
            }
            tpnsReconnectReq.mutableInfo = mutableInfo;
            return new com.tencent.android.tpush.service.channel.o(tpnsReconnectReq, this.d);
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", ">> no app registered!");
        return null;
    }

    private String b(Context context) {
        Object obj = null;
        String str = "";
        if (context != null) {
            JSONArray a = com.tencent.android.tpush.common.e.a(context, 10);
            if (a != null && a.length() > 0) {
                int i;
                long c = e.c(context, c, 0);
                if (b == null || b.length() <= 0) {
                    i = 0;
                } else if (b.toString().equalsIgnoreCase(a.toString())) {
                    return "";
                } else {
                    i = Math.abs(b.length() - a.length());
                }
                long currentTimeMillis = System.currentTimeMillis();
                if ((i >= 3 ? 1 : null) != null || Math.abs(currentTimeMillis - c) > OdnpConfigStatic.OEM_MAX_MEDIUM_POWER_INTERVAL) {
                    obj = 1;
                }
                if (obj != null) {
                    e.b(context, c, currentTimeMillis);
                    String jSONArray = a.toString();
                    b = a;
                    return jSONArray;
                }
            }
        }
        return str;
    }

    public static DeviceInfo a(Context context) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.apiLevel = "" + e.c();
        deviceInfo.imei = e.c(context);
        deviceInfo.model = e.d();
        deviceInfo.manu = Build.MANUFACTURER;
        deviceInfo.model = Build.MODEL;
        deviceInfo.network = h.d(context);
        deviceInfo.os = "android";
        DisplayMetrics c = h.c(context);
        deviceInfo.resolution = c.widthPixels + "*" + c.heightPixels;
        deviceInfo.apiLevel = "" + VERSION.SDK_INT;
        deviceInfo.sdCard = h.b(context);
        deviceInfo.sdDouble = h.a(context);
        deviceInfo.sdkVersion = String.valueOf(Constants.PUSH_SDK_VERSION);
        deviceInfo.sdkVersionName = VERSION.RELEASE;
        deviceInfo.isRooted = (long) e.k(context);
        deviceInfo.language = Locale.getDefault().getLanguage();
        deviceInfo.timezone = TimeZone.getDefault().getID();
        deviceInfo.launcherName = e.j(context);
        return deviceInfo;
    }

    public void a(long j, String str, String str2, String str3, String str4, int i, String str5, String str6, String str7, p pVar) {
        g tpnsRegisterReq = new TpnsRegisterReq();
        tpnsRegisterReq.accessId = j;
        tpnsRegisterReq.accessKey = str;
        tpnsRegisterReq.deviceId = str2;
        tpnsRegisterReq.appCert = str5;
        tpnsRegisterReq.account = str3;
        tpnsRegisterReq.ticket = str4;
        tpnsRegisterReq.ticketType = (short) i;
        tpnsRegisterReq.deviceInfo = a(l.e());
        tpnsRegisterReq.token = CacheManager.getToken(l.e());
        tpnsRegisterReq.version = (short) 1;
        tpnsRegisterReq.appVersion = str6;
        tpnsRegisterReq.reserved = str7;
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.b("PushServiceNetworkHandler", "Register(" + j + "," + str2 + "," + str3 + "," + str4 + "," + i + ")");
        }
        b.a().a(tpnsRegisterReq, pVar);
    }

    public void a(String str, String str2, long j, String str3, String str4, p pVar) {
        g tpnsUnregisterReq = new TpnsUnregisterReq();
        String str5 = "";
        try {
            str5 = TpnsSecurity.getEncryptAPKSignature(l.e().createPackageContext(str4, 0));
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("PushServiceNetworkHandler", ">> create context [for: " + str4 + "] fail.", e);
        }
        tpnsUnregisterReq.unregInfo = new UnregInfo(new AppInfo(j, str3, str5, (byte) 0), (byte) 0, 0);
        b.a().a(tpnsUnregisterReq, pVar);
    }

    public void a(long j) {
        b.a().a(new TpnsConfigReq(j), new q(this));
    }

    private void a(int i, String str, com.tencent.android.tpush.service.channel.a aVar) {
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", "@@ loadConfiguraionFailHandler(" + i + "," + str + ")");
    }

    public void a(String str) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.c("PushServiceNetworkHandler", "Action uninstallReport : pkgName = " + str);
        }
        com.tencent.android.tpush.data.a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
        if (registerInfoByPkgName != null) {
            g tpnsUnregisterReq = new TpnsUnregisterReq();
            tpnsUnregisterReq.unregInfo = new UnregInfo(new AppInfo(registerInfoByPkgName.a, registerInfoByPkgName.b, "", (byte) 0), (byte) 1, System.currentTimeMillis());
            CacheManager.UninstallInfoByPkgName(str);
            b.a().a(tpnsUnregisterReq, new r(this, str));
            return;
        }
        com.tencent.android.tpush.a.a.c("PushServiceNetworkHandler", "The RegisterEntity entity is null, PkgName = " + str);
    }

    private void a(int i, String str, String str2, TpnsUnregisterReq tpnsUnregisterReq, com.tencent.android.tpush.service.channel.a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.a(Constants.ServiceLogTag, "@@ uninstallReportFailedHandler(" + i + "," + str + "," + str2 + "," + tpnsUnregisterReq + ")");
        }
    }

    public void a(ArrayList arrayList, p pVar) {
        if (arrayList != null && arrayList.size() > 0) {
            b.a().a(new TpnsPushVerifyReq(arrayList), pVar);
        }
    }

    public void a(long j, String str, int i, String str2, p pVar) {
        g tpnsTokenTagReq = new TpnsTokenTagReq();
        tpnsTokenTagReq.accessId = j;
        tpnsTokenTagReq.flag = i;
        tpnsTokenTagReq.tag = str2;
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.c("PushServiceNetworkHandler", "Action -> sendTag to server (" + j + "," + str + ")");
        }
        b.a().a(tpnsTokenTagReq, pVar);
    }

    public void b(ArrayList arrayList, p pVar) {
        if (arrayList != null && arrayList.size() != 0) {
            g tpnsPushClickReq = new TpnsPushClickReq();
            tpnsPushClickReq.msgClickList = arrayList;
            b.a().a(tpnsPushClickReq, pVar);
        }
    }

    public void a(boolean z, long j) {
        long lastLoadIpTime = CacheManager.getLastLoadIpTime(l.e());
        if (z) {
            if (System.currentTimeMillis() - lastLoadIpTime > ((long) com.tencent.android.tpush.service.a.a.n) && com.tencent.android.tpush.service.a.a.a(l.e()) != j) {
                a().a(j);
            }
        } else if (com.tencent.android.tpush.service.a.a.a(l.e()) != j) {
            a().a(j);
        }
        if (System.currentTimeMillis() - lastLoadIpTime >= ((long) com.tencent.android.tpush.service.a.a.n)) {
            g tpnsGetApListReq = new TpnsGetApListReq();
            NetworkInfo networkInfo = new NetworkInfo();
            networkInfo.network = e.e(l.e());
            networkInfo.op = e.f(l.e());
            tpnsGetApListReq.netInfo = networkInfo;
            b.a().a(tpnsGetApListReq, new s(this));
        }
    }

    public static byte a(boolean z) {
        if (z) {
            return (byte) 1;
        }
        return (byte) 0;
    }
}
