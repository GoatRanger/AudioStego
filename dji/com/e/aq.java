package com.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.support.v4.media.TransportMediator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.services.location.network.NetworkLocationApi.Options;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

public class aq {
    public static long a = 0;
    private static int ag = -1;
    private boolean A = false;
    private long B = 0;
    private long C = 0;
    private int D = 0;
    private String E = "00:00:00:00:00:00";
    private String F = null;
    private bn G = null;
    private Timer H = null;
    private TimerTask I = null;
    private int J = 0;
    private by K = null;
    private co L = null;
    private int[] M = new int[]{0, 0, 0};
    private String N = null;
    private String O = null;
    private long P = 0;
    private long Q = 0;
    private String R = null;
    private ay S = null;
    private bk T = null;
    private StringBuilder U = new StringBuilder();
    private AmapLoc V = null;
    private String W = null;
    private Timer X = null;
    private TimerTask Y = null;
    private String Z = null;
    private int aa = 0;
    private int ab = 0;
    private boolean ac = true;
    private boolean ad = true;
    private boolean ae = true;
    private boolean af = false;
    private String ah = null;
    private String ai = null;
    private boolean aj = false;
    public boolean b = false;
    ar c;
    int d = -1;
    boolean e = false;
    Object f = new Object();
    public boolean g = false;
    int h = 12;
    TreeMap<Integer, ScanResult> i = null;
    boolean j = true;
    a k = new a(this);
    StringBuilder l = null;
    private Context m = null;
    private ConnectivityManager n = null;
    private ba o = null;
    private az p = null;
    private ArrayList<ScanResult> q = new ArrayList();
    private ArrayList<ScanResult> r = new ArrayList();
    private HashMap<String, ArrayList<ScanResult>> s = new HashMap();
    private b t = null;
    private WifiInfo u = null;
    private JSONObject v = null;
    private AmapLoc w = null;
    private long x = 0;
    private long y = 0;
    private boolean z = false;

    class a implements com.e.ar.a {
        final /* synthetic */ aq a;

        a(aq aqVar) {
            this.a = aqVar;
        }

        public void a(int i) {
            this.a.d = i;
        }
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ aq a;

        private b(aq aqVar) {
            this.a = aqVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                try {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        ba a = this.a.o;
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            if (a != null) {
                                Collection collection = null;
                                collection = a.a();
                                if (collection != null) {
                                    synchronized (this.a.f) {
                                        this.a.r.clear();
                                        this.a.r.addAll(collection);
                                    }
                                }
                                if (this.a.K != null) {
                                    this.a.K.a();
                                }
                                this.a.C = br.b();
                            }
                        } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                            if (this.a.o != null) {
                                int i = 4;
                                try {
                                    i = a.c();
                                } catch (Throwable th) {
                                    bc.a(th, "APS", "onReceive part");
                                }
                                if (this.a.r == null) {
                                    this.a.r = new ArrayList();
                                }
                                switch (i) {
                                    case 0:
                                        this.a.q();
                                        return;
                                    case 1:
                                        this.a.q();
                                        return;
                                    case 4:
                                        this.a.q();
                                        return;
                                    default:
                                        return;
                                }
                            }
                        } else if (action.equals("android.intent.action.SCREEN_ON")) {
                            this.a.j = true;
                        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                            this.a.j = false;
                            if (this.a.K != null) {
                                this.a.x();
                            }
                        } else if (!action.equals("android.intent.action.AIRPLANE_MODE") && !action.equals("android.location.GPS_FIX_CHANGE") && action.equals("android.net.conn.CONNECTIVITY_CHANGE") && this.a.C()) {
                            this.a.a(true, 2);
                        }
                    }
                } catch (Throwable th2) {
                    bc.a(th2, "APS", "onReceive");
                }
            }
        }
    }

    private synchronized void A() {
        if (this.I != null) {
            this.I.cancel();
            this.I = null;
        }
        if (this.H != null) {
            this.H.cancel();
            this.H.purge();
            this.H = null;
        }
    }

    private void B() {
        if (t()) {
            try {
                this.K.a(768);
            } catch (Throwable th) {
                bc.a(th, "APS", "setCollSize");
            }
        }
    }

    private boolean C() {
        return (this.o == null || this.n == null) ? false : this.o.a(this.n);
    }

    private void D() {
        if (br.a(this.v, ParamKey.POIID)) {
            try {
                String string = this.v.getString(ParamKey.POIID);
                if (TextUtils.isEmpty(string)) {
                    this.F = null;
                    return;
                } else if (string.length() > 32) {
                    this.F = null;
                    return;
                } else {
                    this.F = string;
                    return;
                }
            } catch (Throwable th) {
                bc.a(th, "APS", "setPoiid");
                return;
            }
        }
        this.F = null;
    }

    private String E() {
        String str = null;
        try {
            str = by.a("version");
        } catch (Throwable th) {
            bc.a(th, "APS", "getCollVer");
        }
        return str;
    }

    private void F() {
        if (this.o != null && this.m != null && this.b) {
            this.o.a(this.b);
        }
    }

    private boolean G() {
        if (this.m == null) {
            this.U.append("context is null");
            return false;
        } else if (TextUtils.isEmpty(bc.d)) {
            this.U.append("src is null");
            return false;
        } else if (!TextUtils.isEmpty(bc.e)) {
            return true;
        } else {
            this.U.append("license is null");
            return false;
        }
    }

    private void H() {
        if (this.m != null && this.M[0] != 0) {
            SharedPreferences sharedPreferences = this.m.getSharedPreferences("pref", 0);
            if (sharedPreferences != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int append : this.M) {
                    stringBuilder.append(append).append(",");
                }
                try {
                    stringBuilder.deleteCharAt(this.M.length - 1);
                    sharedPreferences.edit().putString("coluphist", cy.a(stringBuilder.toString().getBytes("UTF-8")));
                } catch (Throwable th) {
                    bc.a(th, "APS", "setColUpHist");
                }
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
    }

    private synchronized void I() {
        if (this.ah != null) {
            this.ah = null;
            this.ai = null;
            this.l = null;
        }
        try {
            if (!this.z) {
                this.p.a(this.z, true);
            }
            e();
            if (this.q != null && this.q.isEmpty()) {
                this.C = br.b();
                Collection a = this.o.a();
                if (a != null) {
                    this.q.addAll(a);
                    synchronized (this.f) {
                        if (this.r != null && this.r.isEmpty()) {
                            this.r.addAll(a);
                        }
                    }
                }
            }
            f();
        } catch (Throwable th) {
            bc.a(th, "APS", "initFirstLocateParam");
        }
        this.ah = a(false);
        if (!TextUtils.isEmpty(this.ah)) {
            this.ai = this.ah + com.alipay.sdk.h.a.b + this.ad + com.alipay.sdk.h.a.b + this.ac;
            this.l = b(true);
        }
        this.aj = true;
    }

    private void J() {
        this.V = null;
        this.W = null;
    }

    private void K() {
        if (!bo.f()) {
            L();
        } else if (ax.a[1] > 2000) {
            L();
        } else if (this.X == null || this.Y == null) {
            this.Y = new TimerTask(this) {
                final /* synthetic */ aq a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (ax.a[1] > 2000) {
                        this.a.L();
                        return;
                    }
                    boolean equals;
                    ArrayList b;
                    int size;
                    int i;
                    ArrayList b2;
                    Thread.currentThread().setPriority(1);
                    if (br.a(this.a.v, "fetchoffdatamobile")) {
                        try {
                            equals = "1".equals(this.a.v.getString("fetchoffdatamobile"));
                        } catch (Throwable th) {
                            bc.a(th, "APS", "timerTaskO part");
                        }
                        b = bh.a().b();
                        if (b != null) {
                            size = b.size();
                            if (size > 0) {
                                if (this.a.Z == null) {
                                    this.a.Z = this.a.a(true);
                                }
                                i = 0;
                                while (i < size && i < 20) {
                                    ax.a(this.a.m, this.a.Z, ((bg) b.get(i)).a(), 1, 0, equals, true);
                                    i++;
                                }
                            }
                        }
                        this.a.N();
                        b2 = bf.a().b(this.a.m, 1);
                        if (b2 != null && b2.size() > 0) {
                            Iterator it = b2.iterator();
                            while (it.hasNext()) {
                                ax.a(this.a.Z, (String) it.next(), 1, 0);
                            }
                            return;
                        }
                    }
                    equals = false;
                    b = bh.a().b();
                    if (b != null) {
                        size = b.size();
                        if (size > 0) {
                            if (this.a.Z == null) {
                                this.a.Z = this.a.a(true);
                            }
                            i = 0;
                            while (i < size) {
                                if (equals) {
                                }
                                ax.a(this.a.m, this.a.Z, ((bg) b.get(i)).a(), 1, 0, equals, true);
                                i++;
                            }
                        }
                    }
                    this.a.N();
                    try {
                        b2 = bf.a().b(this.a.m, 1);
                        if (b2 != null) {
                        }
                    } catch (Throwable th2) {
                        bc.a(th2, "APS", "timerTaskO");
                    }
                }
            };
            this.X = new Timer("T-O", false);
            this.X.schedule(this.Y, 0, 60000);
        }
    }

    private void L() {
        if (this.Y != null) {
            this.Y.cancel();
            this.Y = null;
        }
        if (this.X != null) {
            this.X.cancel();
            this.X.purge();
            this.X = null;
        }
    }

    private void M() {
        this.aa = 0;
        this.ab = 0;
    }

    private void N() {
        if (this.m != null && ax.a[0] != 0) {
            SharedPreferences sharedPreferences = this.m.getSharedPreferences("pref", 0);
            if (sharedPreferences != null) {
                StringBuilder stringBuilder = new StringBuilder();
                String str = "activityoffdl";
                for (int append : ax.a) {
                    stringBuilder.append(append).append(",");
                }
                try {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    sharedPreferences.edit().putString(str, br.c(stringBuilder.toString())).commit();
                } catch (Throwable th) {
                    bc.a(th, "APS", "setOffDlHist");
                }
                stringBuilder.delete(0, stringBuilder.length());
            }
        }
    }

    private double[] O() {
        double[] dArr = new double[2];
        if (br.a(this.w)) {
            dArr[0] = this.w.getLat();
            dArr[1] = this.w.getLon();
        } else {
            dArr[0] = 0.0d;
            dArr[1] = 0.0d;
        }
        return dArr;
    }

    private void P() {
        try {
            this.R = null;
            this.w = null;
            this.Q = 0;
            a = 0;
            au.a().b();
        } catch (Throwable th) {
            bc.a(th, "APS", "cleanCache");
        }
    }

    private AmapLoc a(String str, String str2) {
        int i = 0;
        if (!bo.f()) {
            return null;
        }
        if (str != null && str.equals(this.W) && this.V != null) {
            return this.V;
        }
        K();
        ArrayList b = bh.a().b();
        try {
            int i2;
            AmapLoc a;
            if (ax.b()) {
                ArrayList a2 = ax.a(str, false);
                if (a2 != null) {
                    int size = a2.size();
                    for (i2 = 0; i2 < size; i2++) {
                        String str3 = (String) a2.get(i2);
                        a = a(str, str2, null, str3.substring(str3.lastIndexOf(File.separator) + 1, str3.length()), 0);
                        if (br.a(a)) {
                            this.W = str;
                            this.V = a;
                            return a;
                        }
                    }
                }
            }
            i2 = b.size();
            if (i2 != 0) {
                while (i < i2) {
                    a = a(str, str2, null, ((bg) b.get(i)).a(), 0);
                    if (br.a(a)) {
                        this.W = str;
                        this.V = a;
                        return a;
                    }
                    i++;
                }
            }
        } catch (Throwable th) {
            bc.a(th, "APS", "getPureOfflineLocation");
        }
        return null;
    }

    private AmapLoc a(String str, String str2, double[] dArr, String str3, int i) {
        if (!br.k()) {
            return null;
        }
        if (TextUtils.isEmpty(str3)) {
            if (dArr == null) {
                dArr = O();
            }
            if (dArr[0] == 0.0d || dArr[1] == 0.0d) {
                return null;
            }
        }
        double[] dArr2 = dArr;
        br.b();
        return ax.a(dArr2, str3, str, str2, i, this.m, new int[]{this.ab, this.aa});
    }

    private AmapLoc a(boolean z, boolean z2, boolean z3) throws Exception {
        if (!z) {
            a = br.b();
            if (this.m == null) {
                this.U.append("context is null");
                AmapLoc amapLoc = new AmapLoc();
                amapLoc.setErrorCode(1);
                amapLoc.setLocationDetail(this.U.toString());
                return amapLoc;
            }
        }
        try {
            m();
            AmapLoc amapLoc2 = new AmapLoc();
            bm bmVar = new bm();
            try {
                AmapLoc amapLoc3;
                byte[] a = this.T.a(this.m, this.v, this.G, bc.a(), z3);
                if (z) {
                    amapLoc3 = amapLoc2;
                } else if (a == null || a.length == 0) {
                    amapLoc = new AmapLoc();
                    amapLoc.setErrorCode(4);
                    this.U.append("please check the network");
                    amapLoc.setLocationDetail(this.U.toString());
                    return amapLoc;
                } else {
                    String str = new String(a, "UTF-8");
                    if (str.contains("\"status\":\"0\"")) {
                        return bmVar.b(str);
                    }
                    String a2 = bb.a(a);
                    if (a2 == null) {
                        amapLoc = new AmapLoc();
                        amapLoc.setErrorCode(5);
                        this.U.append("decrypt response data error");
                        amapLoc.setLocationDetail(this.U.toString());
                        return amapLoc;
                    }
                    amapLoc = bmVar.a(a2);
                    if (br.a(amapLoc)) {
                        if (amapLoc.getExtra() != null) {
                        }
                        if (amapLoc.getErrorCode() == 0 && amapLoc.getLocationType() == 0) {
                            if ("-5".equals(amapLoc.getRetype()) || "1".equals(amapLoc.getRetype()) || "2".equals(amapLoc.getRetype()) || "14".equals(amapLoc.getRetype()) || "24".equals(amapLoc.getRetype()) || WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(amapLoc.getRetype())) {
                                amapLoc.setLocationType(5);
                            } else {
                                amapLoc.setLocationType(6);
                            }
                            amapLoc.setLocationDetail(amapLoc.getRetype());
                        }
                        amapLoc.setOffset(this.ad);
                        amapLoc.setReversegeo(this.ac);
                        amapLoc3 = amapLoc;
                    } else if (amapLoc != null) {
                        this.N = amapLoc.getRdesc();
                        amapLoc.setErrorCode(6);
                        this.U.append("location faile retype:" + amapLoc.getRetype() + " rdesc:" + (this.N != null ? this.N : "null"));
                        amapLoc.setLocationDetail(this.U.toString());
                        return amapLoc;
                    } else {
                        amapLoc = new AmapLoc();
                        amapLoc.setErrorCode(6);
                        this.U.append("location is null");
                        amapLoc.setLocationDetail(this.U.toString());
                        return amapLoc;
                    }
                }
                return amapLoc3;
            } catch (Throwable th) {
                amapLoc = new AmapLoc();
                amapLoc.setErrorCode(4);
                this.U.append("please check the network");
                amapLoc.setLocationDetail(this.U.toString());
                return amapLoc;
            }
        } catch (Throwable th2) {
            this.U.append("get parames error:" + th2.getMessage());
            amapLoc = new AmapLoc();
            amapLoc.setErrorCode(3);
            amapLoc.setLocationDetail(this.U.toString());
            return amapLoc;
        }
    }

    private String a(int i, int i2, int i3) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("e", i);
        jSONObject.put("d", i2);
        jSONObject.put("u", i3);
        return jSONObject.toString();
    }

    private void a(SharedPreferences sharedPreferences) {
        if (this.m != null && sharedPreferences != null) {
            String str;
            String str2 = "smac";
            if (sharedPreferences.contains(str2)) {
                try {
                    str = new String(cy.b(sharedPreferences.getString(str2, null)), "UTF-8");
                } catch (Throwable th) {
                    bc.a(th, "APS", "getSmac");
                    sharedPreferences.edit().remove(str2).commit();
                    str = null;
                }
            } else {
                str = null;
            }
            if (!TextUtils.isEmpty(str) && !str.equals("00:00:00:00:00:00")) {
                this.E = str;
            }
        }
    }

    private void a(boolean z, int i) {
        if (z) {
            c(i);
        } else {
            A();
        }
    }

    private boolean a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (Throwable e) {
            bc.a(e, "APS", "wifiSigFine");
        }
        return i2 >= 1;
    }

    private boolean a(long j) {
        if (br.b() - j >= 800) {
            return false;
        }
        long j2 = 0;
        if (br.a(this.w)) {
            j2 = br.a() - this.w.getTime();
        }
        return j2 <= 10000;
    }

    private boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getBSSID()) || TextUtils.isEmpty(wifiInfo.getSSID()) || wifiInfo.getBSSID().equals("00:00:00:00:00:00") || wifiInfo.getBSSID().contains(" :")) ? false : true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(int r7) {
        /*
        r6 = this;
        r1 = 674234367; // 0x282fffff float:9.769962E-15 double:3.33116038E-315;
        r0 = 70254591; // 0x42fffff float:2.0688699E-36 double:3.471038E-316;
        r2 = r6.t();
        if (r2 != 0) goto L_0x000d;
    L_0x000c:
        return;
    L_0x000d:
        r6.B();	 Catch:{ Throwable -> 0x00a2 }
        switch(r7) {
            case 0: goto L_0x0013;
            case 1: goto L_0x00ac;
            case 2: goto L_0x00af;
            default: goto L_0x0013;
        };	 Catch:{ Throwable -> 0x00a2 }
    L_0x0013:
        r1 = r6.K;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 0;
        r3 = 1;
        r4 = 1;
        r3 = r6.a(r3, r0, r4);	 Catch:{ Throwable -> 0x00a2 }
        r1.a(r2, r3);	 Catch:{ Throwable -> 0x00a2 }
        r1 = r6.K;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1.f();	 Catch:{ Throwable -> 0x00a2 }
        r6.L = r1;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r6.L;	 Catch:{ Throwable -> 0x00a2 }
        if (r1 == 0) goto L_0x008c;
    L_0x002b:
        r1 = r6.L;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1.a();	 Catch:{ Throwable -> 0x00a2 }
        r2 = r6.T;	 Catch:{ Throwable -> 0x00a2 }
        r3 = r6.m;	 Catch:{ Throwable -> 0x00a2 }
        r4 = "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&";
        r5 = 0;
        r2 = r2.a(r1, r3, r4, r5);	 Catch:{ Throwable -> 0x00a2 }
        r3 = r6.t();	 Catch:{ Throwable -> 0x00a2 }
        if (r3 == 0) goto L_0x008c;
    L_0x0042:
        r3 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x00a2 }
        if (r3 != 0) goto L_0x00e8;
    L_0x0048:
        r3 = "true";
        r2 = r2.equals(r3);	 Catch:{ Throwable -> 0x00a2 }
        if (r2 == 0) goto L_0x00e8;
    L_0x0050:
        r2 = r6.K;	 Catch:{ Throwable -> 0x00a2 }
        r3 = r6.L;	 Catch:{ Throwable -> 0x00a2 }
        r4 = 1;
        r5 = 1;
        r0 = r6.a(r4, r0, r5);	 Catch:{ Throwable -> 0x00a2 }
        r2.a(r3, r0);	 Catch:{ Throwable -> 0x00a2 }
        r2 = 0;
        r0 = "yyyyMMdd";
        r0 = com.e.br.a(r2, r0);	 Catch:{ Throwable -> 0x00a2 }
        r2 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r3 = 0;
        r2 = r2[r3];	 Catch:{ Throwable -> 0x00a2 }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Throwable -> 0x00a2 }
        r2 = r0.equals(r2);	 Catch:{ Throwable -> 0x00a2 }
        if (r2 == 0) goto L_0x00bd;
    L_0x0074:
        r0 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 1;
        r3 = r0[r2];	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1.length;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1 + r3;
        r0[r2] = r1;	 Catch:{ Throwable -> 0x00a2 }
    L_0x007d:
        r0 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r1 = 2;
        r2 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r3 = 2;
        r2 = r2[r3];	 Catch:{ Throwable -> 0x00a2 }
        r2 = r2 + 1;
        r0[r1] = r2;	 Catch:{ Throwable -> 0x00a2 }
        r6.H();	 Catch:{ Throwable -> 0x00a2 }
    L_0x008c:
        r6.w();	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.t();	 Catch:{ Throwable -> 0x00a2 }
        if (r0 == 0) goto L_0x00fc;
    L_0x0095:
        r0 = r6.K;	 Catch:{ Throwable -> 0x00a2 }
        r0 = r0.h();	 Catch:{ Throwable -> 0x00a2 }
        if (r0 != 0) goto L_0x00fc;
    L_0x009d:
        r6.A();	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x000c;
    L_0x00a2:
        r0 = move-exception;
        r1 = "APS";
        r2 = "up";
        com.e.bc.a(r0, r1, r2);
        goto L_0x000c;
    L_0x00ac:
        r0 = r1;
        goto L_0x0013;
    L_0x00af:
        r0 = r6.C();	 Catch:{ Throwable -> 0x00a2 }
        if (r0 != 0) goto L_0x00b8;
    L_0x00b5:
        r0 = r1;
        goto L_0x0013;
    L_0x00b8:
        r0 = 2083520511; // 0x7c2fffff float:3.6553767E36 double:1.029395907E-314;
        goto L_0x0013;
    L_0x00bd:
        r2 = r6.M;	 Catch:{ Throwable -> 0x00cd }
        r3 = 0;
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ Throwable -> 0x00cd }
        r2[r3] = r0;	 Catch:{ Throwable -> 0x00cd }
    L_0x00c6:
        r0 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 1;
        r1 = r1.length;	 Catch:{ Throwable -> 0x00a2 }
        r0[r2] = r1;	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x007d;
    L_0x00cd:
        r0 = move-exception;
        r2 = "APS";
        r3 = "up part";
        com.e.bc.a(r0, r2, r3);	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 0;
        r3 = 0;
        r0[r2] = r3;	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 1;
        r3 = 0;
        r0[r2] = r3;	 Catch:{ Throwable -> 0x00a2 }
        r0 = r6.M;	 Catch:{ Throwable -> 0x00a2 }
        r2 = 2;
        r3 = 0;
        r0[r2] = r3;	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x00c6;
    L_0x00e8:
        r1 = r6.J;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r1 + 1;
        r6.J = r1;	 Catch:{ Throwable -> 0x00a2 }
        r1 = r6.K;	 Catch:{ Throwable -> 0x00a2 }
        r2 = r6.L;	 Catch:{ Throwable -> 0x00a2 }
        r3 = 1;
        r4 = 0;
        r0 = r6.a(r3, r0, r4);	 Catch:{ Throwable -> 0x00a2 }
        r1.a(r2, r0);	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x008c;
    L_0x00fc:
        r0 = r6.J;	 Catch:{ Throwable -> 0x00a2 }
        r1 = 3;
        if (r0 < r1) goto L_0x000c;
    L_0x0101:
        r6.A();	 Catch:{ Throwable -> 0x00a2 }
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.aq.b(int):void");
    }

    private void b(SharedPreferences sharedPreferences) {
        int i = 0;
        if (this.m != null) {
            SharedPreferences sharedPreferences2 = this.m.getSharedPreferences("pref", 0);
            if (sharedPreferences2 != null && sharedPreferences2.contains("coluphist")) {
                try {
                    String[] split = cy.a(sharedPreferences2.getString("coluphist", null).getBytes("UTF-8")).split(",");
                    while (i < 3) {
                        this.M[i] = Integer.parseInt(split[i]);
                        i++;
                    }
                } catch (Throwable th) {
                    bc.a(th, "APS", "getColUpHist");
                    sharedPreferences2.edit().remove("coluphist").commit();
                }
            }
        }
    }

    private void c(final int i) {
        if (this.I == null) {
            this.I = new TimerTask(this) {
                final /* synthetic */ aq b;

                public void run() {
                    try {
                        Thread.currentThread().setPriority(1);
                        if (br.b() - this.b.y >= 10000) {
                            if (this.b.C()) {
                                this.b.b(i);
                            } else {
                                this.b.A();
                            }
                        }
                    } catch (Throwable th) {
                        bc.a(th, "APS", "timerTaskU run");
                    }
                }
            };
        }
        if (this.H == null) {
            this.H = new Timer("T-U", false);
            this.H.schedule(this.I, 2000, 2000);
        }
    }

    private void c(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            String str = "activityoffdl";
            if (sharedPreferences.contains(str)) {
                try {
                    String[] split = br.d(sharedPreferences.getString(str, null)).split(",");
                    for (int i = 0; i < 2; i++) {
                        ax.a[i] = Integer.parseInt(split[i]);
                    }
                } catch (Throwable th) {
                    bc.a(th, "APS", "getOffDlHist");
                    sharedPreferences.edit().remove(str).commit();
                }
            }
        }
    }

    private void j() {
        boolean z;
        boolean z2;
        boolean z3;
        try {
            z = br.a(this.v, "reversegeo") ? this.v.getBoolean("reversegeo") : true;
            try {
                z2 = br.a(this.v, "isOffset") ? this.v.getBoolean("isOffset") : true;
                try {
                    z3 = br.a(this.v, "isLocationCacheEnable") ? this.v.getBoolean("isLocationCacheEnable") : true;
                    try {
                        if (!(z2 == this.ad && z == this.ac && z3 == this.ae)) {
                            P();
                        }
                    } catch (JSONException e) {
                    }
                } catch (JSONException e2) {
                    z3 = true;
                }
            } catch (JSONException e3) {
                z3 = true;
                z2 = true;
            }
        } catch (JSONException e4) {
            z3 = true;
            z = true;
            z2 = true;
        }
        this.ad = z2;
        this.ac = z;
        this.ae = z3;
        this.z = br.a(this.m);
        this.af = true;
    }

    private void k() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            intentFilter.addAction("android.location.GPS_FIX_CHANGE");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.m.registerReceiver(this.t, intentFilter);
            r();
        } catch (Throwable th) {
            bc.a(th, "APS", "initBroadcastListener");
        }
    }

    private void l() {
        this.p.a();
        this.p.h();
    }

    private void m() throws Throwable {
        String str;
        boolean z;
        if (this.p.a(this.z)) {
            this.p.h();
        }
        String str2 = "0";
        String str3 = "0";
        String str4 = "0";
        String str5 = "0";
        String str6 = "0";
        String str7 = bc.h;
        bc.c = "";
        int a = br.a(-32768, 32767);
        String str8 = "";
        String str9 = "";
        String str10 = "";
        String str11 = bc.d;
        String str12 = bc.e;
        if (this.ad) {
            str = str11;
            str11 = str12;
        } else {
            str = "UC_nlp_20131029";
            str11 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        az azVar = this.p;
        int d = azVar.d();
        TelephonyManager f = azVar.f();
        ArrayList b = azVar.b();
        String str13 = d == 2 ? "1" : str2;
        if (f != null) {
            if (TextUtils.isEmpty(bc.a)) {
                try {
                    bc.a = cx.q(this.m);
                } catch (Throwable th) {
                    bc.a(th, "APS", "getApsReq part4");
                }
            }
            if (TextUtils.isEmpty(bc.a)) {
                bc.a = "888888888888888";
            }
            if (TextUtils.isEmpty(bc.b)) {
                try {
                    bc.b = f.getSubscriberId();
                } catch (Throwable th2) {
                    bc.a(th2, "APS", "getApsReq part2");
                }
            }
            if (TextUtils.isEmpty(bc.b)) {
                bc.b = "888888888888888";
            }
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = this.n.getActiveNetworkInfo();
        } catch (Throwable th3) {
            bc.a(th3, "APS", "getApsReq part");
        }
        if (br.a(networkInfo) != -1) {
            str9 = br.b(f);
            if (s()) {
                if (a(this.u)) {
                    str12 = "2";
                    if (!s()) {
                        q();
                        if (this.q != null) {
                            this.q.clear();
                            str8 = str9;
                            str9 = str12;
                        }
                    }
                    str8 = str9;
                    str9 = str12;
                }
            }
            str12 = "1";
            if (s()) {
                q();
                if (this.q != null) {
                    this.q.clear();
                    str8 = str9;
                    str9 = str12;
                }
            }
            str8 = str9;
            str9 = str12;
        } else {
            this.u = null;
        }
        D();
        if (!b.isEmpty()) {
            StringBuilder stringBuilder4 = new StringBuilder();
            ay ayVar;
            switch (d) {
                case 1:
                    M();
                    ayVar = (ay) b.get(0);
                    stringBuilder4.delete(0, stringBuilder4.length());
                    stringBuilder4.append("<mcc>").append(ayVar.a).append("</mcc>");
                    stringBuilder4.append("<mnc>").append(ayVar.b).append("</mnc>");
                    stringBuilder4.append("<lac>").append(ayVar.c).append("</lac>");
                    stringBuilder4.append("<cellid>").append(ayVar.d);
                    stringBuilder4.append("</cellid>");
                    stringBuilder4.append("<signal>").append(ayVar.j);
                    stringBuilder4.append("</signal>");
                    str2 = stringBuilder4.toString();
                    for (int i = 1; i < b.size(); i++) {
                        ayVar = (ay) b.get(i);
                        stringBuilder.append(ayVar.c).append(",");
                        stringBuilder.append(ayVar.d).append(",");
                        stringBuilder.append(ayVar.j);
                        if (i < b.size() - 1) {
                            stringBuilder.append("*");
                        }
                    }
                    str12 = str2;
                    break;
                case 2:
                    ayVar = (ay) b.get(0);
                    stringBuilder4.delete(0, stringBuilder4.length());
                    stringBuilder4.append("<mcc>").append(ayVar.a).append("</mcc>");
                    stringBuilder4.append("<sid>").append(ayVar.g).append("</sid>");
                    stringBuilder4.append("<nid>").append(ayVar.h).append("</nid>");
                    stringBuilder4.append("<bid>").append(ayVar.i).append("</bid>");
                    if (ayVar.f <= 0 || ayVar.e <= 0) {
                        M();
                    } else {
                        this.aa = ayVar.f;
                        this.ab = ayVar.e;
                        stringBuilder4.append("<lon>").append(ayVar.f).append("</lon>");
                        stringBuilder4.append("<lat>").append(ayVar.e).append("</lat>");
                    }
                    stringBuilder4.append("<signal>").append(ayVar.j).append("</signal>");
                    str12 = stringBuilder4.toString();
                    break;
                default:
                    M();
                    str12 = str10;
                    break;
            }
            stringBuilder4.delete(0, stringBuilder4.length());
            str10 = str12;
        }
        if (s()) {
            int length;
            if (a(this.u)) {
                stringBuilder3.append(this.u.getBSSID()).append(",");
                int rssi = this.u.getRssi();
                if (rssi < -128) {
                    rssi = 0;
                } else if (rssi > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                    rssi = 0;
                }
                stringBuilder3.append(rssi).append(",");
                str12 = this.u.getSSID();
                try {
                    length = this.u.getSSID().getBytes("UTF-8").length;
                } catch (Throwable th32) {
                    bc.a(th32, "APS", "getApsReq");
                    length = 32;
                }
                if (length >= 32) {
                    str12 = "unkwn";
                }
                stringBuilder3.append(str12.replace("*", "."));
            }
            int min = Math.min(this.q.size(), 15);
            for (length = 0; length < min; length++) {
                ScanResult scanResult = (ScanResult) this.q.get(length);
                stringBuilder2.append(scanResult.BSSID).append(",");
                stringBuilder2.append(scanResult.level).append(",");
                stringBuilder2.append(scanResult.SSID).append("*");
            }
        } else {
            q();
        }
        if (stringBuilder2.length() == 0) {
            stringBuilder2.append(stringBuilder3);
        } else {
            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
        }
        if (br.a(this.v, "reversegeo")) {
            try {
                z = this.v.getBoolean("reversegeo");
            } catch (Throwable th22) {
                bc.a(th22, "APS", "getApsReq part");
            }
            if (z) {
                this.G.b = (short) 2;
            } else {
                this.G.b = (short) 0;
            }
            if (br.a(this.v, "multi")) {
                try {
                    if (this.v.getString("multi").equals("1")) {
                        this.G.b = (short) 1;
                    }
                } catch (Throwable th222) {
                    bc.a(th222, "APS", "getApsReq");
                }
            }
            this.G.c = str;
            this.G.d = str11;
            this.G.f = br.f();
            this.G.g = "android" + br.g();
            this.G.h = br.b(this.m);
            this.G.i = str13;
            this.G.j = str3;
            this.G.k = this.A ? "1" : "0";
            this.G.l = str4;
            this.G.m = str5;
            this.G.n = str6;
            this.G.o = str7;
            this.G.p = bc.a;
            this.G.q = bc.b;
            this.G.s = String.valueOf(a);
            this.G.t = this.E;
            this.G.v = d();
            this.G.w = E();
            this.G.F = this.F;
            this.G.u = bc.c;
            this.G.x = str8;
            this.G.y = str9;
            this.G.z = String.valueOf(d);
            this.G.A = str10;
            this.G.B = stringBuilder.toString();
            this.G.D = stringBuilder2.toString();
            this.G.E = String.valueOf(br.b() - this.C);
            this.G.C = stringBuilder3.toString();
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder2.delete(0, stringBuilder2.length());
            stringBuilder3.delete(0, stringBuilder3.length());
        }
        z = true;
        if (z) {
            this.G.b = (short) 0;
        } else {
            this.G.b = (short) 2;
        }
        if (br.a(this.v, "multi")) {
            if (this.v.getString("multi").equals("1")) {
                this.G.b = (short) 1;
            }
        }
        this.G.c = str;
        this.G.d = str11;
        this.G.f = br.f();
        this.G.g = "android" + br.g();
        this.G.h = br.b(this.m);
        this.G.i = str13;
        this.G.j = str3;
        if (this.A) {
        }
        this.G.k = this.A ? "1" : "0";
        this.G.l = str4;
        this.G.m = str5;
        this.G.n = str6;
        this.G.o = str7;
        this.G.p = bc.a;
        this.G.q = bc.b;
        this.G.s = String.valueOf(a);
        this.G.t = this.E;
        this.G.v = d();
        this.G.w = E();
        this.G.F = this.F;
        this.G.u = bc.c;
        this.G.x = str8;
        this.G.y = str9;
        this.G.z = String.valueOf(d);
        this.G.A = str10;
        this.G.B = stringBuilder.toString();
        this.G.D = stringBuilder2.toString();
        this.G.E = String.valueOf(br.b() - this.C);
        this.G.C = stringBuilder3.toString();
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder2.delete(0, stringBuilder2.length());
        stringBuilder3.delete(0, stringBuilder3.length());
    }

    private void n() {
        long b = br.b();
        if (o()) {
            if (b - this.x >= 10000) {
                synchronized (this.f) {
                    this.r.clear();
                }
            }
            r();
            if (b - this.x >= 10000) {
                for (int i = 20; i > 0 && this.r.isEmpty(); i--) {
                    try {
                        Thread.sleep(OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME / ((long) 20));
                    } catch (Throwable th) {
                        bc.a(th, "APS", "mayWaitForWifi");
                    }
                }
            }
            synchronized (this.f) {
            }
        }
        if (this.r.isEmpty() && this.o != null) {
            this.C = br.b();
            br.a();
            Collection a = this.o.a();
            if (a != null) {
                synchronized (this.f) {
                    this.r.addAll(a);
                }
            }
        }
    }

    private boolean o() {
        boolean z = false;
        if (!TextUtils.isEmpty(this.F)) {
            return true;
        }
        if (s()) {
            if (this.B == 0) {
                z = true;
            } else if (br.b() - this.B >= OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME && br.b() - this.C >= 1500) {
                z = true;
            }
        }
        return z;
    }

    private boolean p() {
        return a == 0 || br.b() - a > Options.MIN_DESIRED_INTERVAL;
    }

    private void q() {
        this.C = 0;
        this.u = null;
        synchronized (this.f) {
            this.r.clear();
            this.s.clear();
        }
    }

    private void r() {
        boolean z = false;
        if (s()) {
            boolean equals;
            if (br.c() < 18 && br.c() > 3 && br.a(this.v, "wifiactivescan")) {
                try {
                    equals = "1".equals(this.v.getString("wifiactivescan"));
                } catch (Throwable th) {
                    bc.a(th, "APS", "updateWifi part1");
                }
                if (equals) {
                    try {
                        z = this.o.e();
                        if (z) {
                            this.B = br.b();
                        }
                    } catch (Throwable th2) {
                        bc.a(th2, "APS", "updateWifi part");
                    }
                }
                if (!z) {
                    try {
                        if (this.o.d()) {
                            this.B = br.b();
                        }
                    } catch (Throwable th22) {
                        bc.a(th22, "APS", "updateWifi");
                        return;
                    }
                }
            }
            equals = false;
            if (equals) {
                z = this.o.e();
                if (z) {
                    this.B = br.b();
                }
            }
            if (!z) {
                if (this.o.d()) {
                    this.B = br.b();
                }
            }
        }
    }

    private boolean s() {
        return this.o != null ? this.o.f() : false;
    }

    private boolean t() {
        return this.K != null;
    }

    private boolean u() {
        boolean z = false;
        try {
            if (t()) {
                z = this.K.e();
            }
        } catch (Throwable th) {
            bc.a(th, "APS", "collStarted");
        }
        return z;
    }

    private void v() {
        if (t()) {
            Object obj = 1;
            if (br.a(this.v, "coll")) {
                try {
                    if (this.v.getString("coll").equals("0")) {
                        obj = null;
                    }
                } catch (Throwable th) {
                    bc.a(th, "APS", "start3rdCM");
                }
            }
            if (obj == null) {
                x();
            } else if (!u()) {
                try {
                    this.K.b(bc.l * 1000);
                    B();
                    w();
                    this.K.b();
                } catch (Throwable th2) {
                    bc.a(th2, "APS", "start3rdCM");
                }
            }
        }
    }

    private void w() {
        if (!t()) {
            return;
        }
        if (!t() || this.K.h() <= 0) {
            try {
                if (!t() || !this.K.g()) {
                }
            } catch (Throwable th) {
                bc.a(th, "APS", "collFileSwitch");
            }
        }
    }

    private void x() {
        if (u()) {
            bc.l = 20;
            try {
                this.K.d();
            } catch (Throwable th) {
                bc.a(th, "APS", "stop3rdCM");
            }
        }
    }

    private void y() {
        if (this.m != null && !TextUtils.isEmpty(this.E)) {
            SharedPreferences sharedPreferences = this.m.getSharedPreferences("pref", 0);
            Object obj = null;
            try {
                obj = cy.a(this.E.getBytes("UTF-8"));
            } catch (Throwable th) {
                bc.a(th, "APS", "setSmac");
            }
            if (!TextUtils.isEmpty(obj)) {
                sharedPreferences.edit().putString("smac", obj).commit();
            }
        }
    }

    private void z() {
        bc.d = "";
        bc.e = "";
        bc.g = "";
    }

    public synchronized AmapLoc a() throws Throwable {
        AmapLoc amapLoc;
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            if (this.U.length() > 0) {
                this.U.delete(0, this.U.length());
            }
            if (G()) {
                j();
                this.D++;
                w();
                F();
                if (this.m != null) {
                    SharedPreferences sharedPreferences = this.m.getSharedPreferences("pref", 0);
                    b(sharedPreferences);
                    c(sharedPreferences);
                    a(sharedPreferences);
                }
                K();
                if (a(a) && br.a(this.w)) {
                    if (this.ae && bo.p()) {
                        this.w.setLocationType(2);
                    }
                    amapLoc = this.w;
                } else {
                    this.p.a(this.z, false);
                    try {
                        n();
                        this.x = br.b();
                        e();
                        f();
                    } catch (Throwable th) {
                        bc.a(th, "APS", "getLocation");
                    }
                    this.ah = a(false);
                    if (TextUtils.isEmpty(this.ah)) {
                        if (!this.e) {
                            g();
                        }
                        for (int i = 4; i > 0 && this.d != 0; i--) {
                            SystemClock.sleep(500);
                        }
                        if (this.d == 0) {
                            this.w = this.c.d();
                            if (this.w != null) {
                                amapLoc = this.w;
                            }
                        }
                        amapLoc = new AmapLoc();
                        amapLoc.setErrorCode(this.h);
                        amapLoc.setLocationDetail(this.U.toString());
                    } else {
                        boolean z3;
                        this.l = b(false);
                        ay c = !this.z ? this.p.c() : null;
                        boolean z4 = !(c == null && this.S == null) && (this.S == null || !this.S.a(c));
                        boolean p = p();
                        if (this.w != null) {
                            int size = this.q.size();
                            if (this.w.getAccuracy() <= 299.0f || size <= 5) {
                                z = false;
                            }
                            z3 = z;
                        } else {
                            z3 = false;
                        }
                        if (!(this.w == null || this.R == null || z3 || z4 || !this.ae || !bo.p() || br.a() - this.w.getTime() > bo.q())) {
                            z2 = be.a().a(this.R, this.l);
                            if (z2 || (this.Q != 0 && br.b() - this.Q < OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME)) {
                                if (this.p.a(this.z)) {
                                    this.p.h();
                                }
                                if (br.a(this.w)) {
                                    this.w.setType("mem");
                                    this.w.setLocationType(2);
                                    amapLoc = this.w;
                                }
                            }
                        }
                        if (z2) {
                            this.Q = 0;
                        } else {
                            this.Q = br.b();
                        }
                        if (this.O == null || this.ah.equals(this.O)) {
                            if (this.O == null) {
                                this.P = br.a();
                                this.O = this.ah;
                            } else {
                                this.P = br.a();
                            }
                        } else if (br.a() - this.P < OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                            this.ah = this.O;
                        } else {
                            this.P = br.a();
                            this.O = this.ah;
                        }
                        this.ai = this.ah + com.alipay.sdk.h.a.b + this.ad + com.alipay.sdk.h.a.b + this.ac;
                        amapLoc = (!this.ae || z3 || p) ? null : be.a().a(this.ai, this.l, this.ae);
                        if ((!p && !br.a(amapLoc)) || z3) {
                            this.w = a(false, false, true);
                            if (br.a(this.w)) {
                                this.w.setType("new");
                                this.R = this.l.toString();
                                this.S = c;
                                J();
                            }
                        } else if (p) {
                            this.w = a(false, false, true);
                            if (br.a(this.w)) {
                                this.R = this.l.toString();
                                this.S = c;
                                J();
                            }
                        } else {
                            this.Q = 0;
                            amapLoc.setLocationType(4);
                            this.w = amapLoc;
                            J();
                        }
                        be.a().a(this.ai, this.l, this.w, this.m, true);
                        bh.a().a(this.m, this.ah, this.w);
                        if (!br.a(this.w)) {
                            amapLoc = a(this.ah, this.l.toString());
                            if (br.a(amapLoc)) {
                                this.R = this.l.toString();
                                AmapLoc amapLoc2 = this.w;
                                this.w = amapLoc;
                                this.w.setLocationType(8);
                                this.w.setLocationDetail("离线定位，在线定位失败原因:" + amapLoc2.getLocationDetail());
                            }
                        }
                        this.l.delete(0, this.l.length());
                        this.l = null;
                        amapLoc = this.w;
                    }
                }
            } else {
                amapLoc = new AmapLoc();
                amapLoc.setErrorCode(1);
                amapLoc.setLocationDetail(this.U.toString());
            }
        }
        return amapLoc;
    }

    public AmapLoc a(AmapLoc amapLoc, String... strArr) {
        au.a().a(this.ae);
        return (strArr == null || strArr.length == 0) ? au.a().a(amapLoc) : strArr[0].equals("shake") ? au.a().a(amapLoc) : strArr[0].equals("fusion") ? au.a().b(amapLoc) : amapLoc;
    }

    public synchronized String a(boolean z) {
        String str;
        Object obj = null;
        synchronized (this) {
            if (this.z) {
                this.p.g();
            } else {
                this.p.j();
            }
            str = "";
            String str2 = "";
            String str3 = "network";
            if (s()) {
                this.u = this.o.b();
            } else {
                q();
                if (this.q != null) {
                    this.q.clear();
                }
            }
            str2 = "";
            int d = this.p.d();
            ArrayList b = this.p.b();
            if ((b == null || b.isEmpty()) && (this.q == null || this.q.isEmpty())) {
                this.U.append("⊗ lstCgi & ⊗ wifis");
                this.h = 12;
            } else {
                ay ayVar;
                StringBuilder stringBuilder;
                switch (d) {
                    case 1:
                        if (!b.isEmpty()) {
                            ayVar = (ay) b.get(0);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(ayVar.a).append("#");
                            stringBuilder.append(ayVar.b).append("#");
                            stringBuilder.append(ayVar.c).append("#");
                            stringBuilder.append(ayVar.d).append("#");
                            stringBuilder.append(str3).append("#");
                            str = (!this.q.isEmpty() || a(this.u)) ? "cgiwifi" : "cgi";
                            stringBuilder.append(str);
                            str = stringBuilder.toString();
                            break;
                        }
                        break;
                    case 2:
                        if (!b.isEmpty()) {
                            ayVar = (ay) b.get(0);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(ayVar.a).append("#");
                            stringBuilder.append(ayVar.b).append("#");
                            stringBuilder.append(ayVar.g).append("#");
                            stringBuilder.append(ayVar.h).append("#");
                            stringBuilder.append(ayVar.i).append("#");
                            stringBuilder.append(str3).append("#");
                            str = (!this.q.isEmpty() || a(this.u)) ? "cgiwifi" : "cgi";
                            stringBuilder.append(str);
                            str = stringBuilder.toString();
                            break;
                        }
                        break;
                    case 9:
                        Object obj2 = (!this.q.isEmpty() || a(this.u)) ? 1 : null;
                        if (!z) {
                            if (!a(this.u) || !this.q.isEmpty()) {
                                if (this.q.size() == 1) {
                                    this.h = 2;
                                    if (a(this.u)) {
                                        if (this.u.getBSSID().equals(((ScanResult) this.q.get(0)).BSSID)) {
                                            this.U.append("same access wifi & around wifi 1");
                                        }
                                    } else {
                                        this.U.append("⊗ access wifi & around wifi 1");
                                    }
                                    str = String.format(Locale.US, "#%s#", new Object[]{str3});
                                    if (obj != null) {
                                        if (str3.equals("network")) {
                                            str = "";
                                            this.h = 2;
                                            this.U.append("is network & no wifi");
                                            break;
                                        }
                                    }
                                    str = str + "wifi";
                                    break;
                                }
                            }
                            this.h = 2;
                            this.U.append("⊗ around wifi(s) & has access wifi");
                            str = String.format(Locale.US, "#%s#", new Object[]{str3});
                            if (obj != null) {
                                str = str + "wifi";
                            } else if (str3.equals("network")) {
                                str = "";
                                this.h = 2;
                                this.U.append("is network & no wifi");
                            }
                        }
                        obj = obj2;
                        str = String.format(Locale.US, "#%s#", new Object[]{str3});
                        if (obj != null) {
                            str = str + "wifi";
                        } else if (str3.equals("network")) {
                            str = "";
                            this.h = 2;
                            this.U.append("is network & no wifi");
                        }
                        break;
                    default:
                        this.h = 11;
                        this.U.append("get cgi failure");
                        break;
                }
                if (!TextUtils.isEmpty(str)) {
                    if (!str.startsWith("#")) {
                        str = "#" + str;
                    }
                    str = br.j() + str;
                }
            }
        }
        return str;
    }

    public synchronized void a(AmapLoc amapLoc) {
        if (br.a(amapLoc)) {
            be.a().a(this.ai, this.l, amapLoc, this.m, true);
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("##")) {
            z();
            return;
        }
        String[] split = str.split("##");
        if (split.length != 4) {
            z();
            return;
        }
        bc.d = split[0];
        bc.e = split[1];
        bc.g = split[2];
        bc.h = split[3];
    }

    public void a(JSONObject jSONObject) {
        this.v = jSONObject;
        if (br.a(jSONObject, "collwifiscan")) {
            try {
                Object string = jSONObject.getString("collwifiscan");
                if (TextUtils.isEmpty(string)) {
                    bc.l = 20;
                } else {
                    bc.l = Integer.parseInt(string) / 1000;
                }
                if (u()) {
                    this.K.b(bc.l * 1000);
                }
            } catch (Throwable th) {
                bc.a(th, "APS", "setExtra");
            }
        }
        if (this.p != null) {
            this.p.a(jSONObject);
        }
        if (this.o != null) {
            this.o.a(jSONObject);
        }
    }

    public synchronized boolean a(Context context) {
        boolean z;
        if (this.m != null) {
            z = true;
        } else if (context == null) {
            z = false;
        } else {
            this.m = context.getApplicationContext();
            bo.b(this.m);
            br.b(this.m);
            if (this.o == null) {
                this.o = new ba(this.m, (WifiManager) br.a(this.m, "wifi"), this.v);
            }
            if (this.p == null) {
                this.p = new az(this.m, this.v);
            }
            be.a().a(this.m);
            z = true;
        }
        return z;
    }

    public synchronized boolean a(Context context, boolean z) {
        boolean z2;
        if (a(context)) {
            this.T = bk.a(this.m, z);
            if (this.n == null) {
                this.n = (ConnectivityManager) br.a(this.m, "connectivity");
            }
            this.G = new bn();
            z2 = true;
        } else {
            z2 = false;
        }
        return z2;
    }

    public synchronized StringBuilder b(boolean z) {
        StringBuilder stringBuilder;
        Object obj = null;
        synchronized (this) {
            az azVar = this.p;
            if (this.z) {
                azVar.g();
            }
            stringBuilder = new StringBuilder(700);
            int d = azVar.d();
            ArrayList b = azVar.b();
            switch (d) {
                case 1:
                    for (d = 1; d < b.size(); d++) {
                        stringBuilder.append("#").append(((ay) b.get(d)).b);
                        stringBuilder.append("|").append(((ay) b.get(d)).c);
                        stringBuilder.append("|").append(((ay) b.get(d)).d);
                    }
                    break;
            }
            if (((!z && TextUtils.isEmpty(this.E)) || this.E.equals("00:00:00:00:00:00")) && this.u != null) {
                this.E = this.u.getMacAddress();
                y();
                if (TextUtils.isEmpty(this.E)) {
                    this.E = "00:00:00:00:00:00";
                }
            }
            if (s()) {
                Object bssid;
                String str = "";
                if (a(this.u)) {
                    bssid = this.u.getBSSID();
                } else {
                    String str2 = str;
                }
                int size = this.q.size();
                for (d = 0; d < size; d++) {
                    str = "nb";
                    if (bssid.equals(((ScanResult) this.q.get(d)).BSSID)) {
                        str = "access";
                        obj = 1;
                    }
                    stringBuilder.append(String.format(Locale.US, "#%s,%s", new Object[]{((ScanResult) this.q.get(d)).BSSID, str}));
                }
                if (obj == null && !TextUtils.isEmpty(bssid)) {
                    stringBuilder.append("#").append(bssid);
                    stringBuilder.append(",access");
                }
            } else {
                q();
                if (this.q != null) {
                    this.q.clear();
                }
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(0);
            }
        }
        return stringBuilder;
    }

    public void b() {
        try {
            j();
            I();
            a(a(true, true, true));
        } catch (Throwable th) {
            bc.a(th, "APS", "doFusionLocation");
        }
    }

    public synchronized void b(Context context) {
        if (a(context, true)) {
            bc.m = true;
            if (this.t == null) {
                this.t = new b();
            }
            try {
                this.K = by.a(this.m, this.o, this.p);
                this.y = br.b();
            } catch (Throwable th) {
                bc.a(th, "APS", "setExtra");
            }
            k();
            l();
            this.p.e();
            bh.a().a(this.m);
            this.g = true;
        }
    }

    public synchronized AmapLoc c(boolean z) throws Exception {
        AmapLoc amapLoc;
        if (this.U.length() > 0) {
            this.U.delete(0, this.U.length());
        }
        if (!this.af) {
            j();
        }
        if (!this.aj) {
            I();
        }
        try {
            ay c = !this.z ? this.p.c() : null;
        } catch (Throwable th) {
            bc.a(th, "APS", "doFirstNetLocate");
            c = null;
        }
        if (TextUtils.isEmpty(this.ah)) {
            amapLoc = null == null ? new AmapLoc() : null;
            amapLoc.setErrorCode(this.h);
            amapLoc.setLocationDetail(this.U.toString());
        } else {
            amapLoc = a(false, true, z);
            if (br.a(amapLoc)) {
                amapLoc.setType("new");
                this.R = this.l.toString();
                this.S = c;
                this.w = amapLoc;
                J();
            } else {
                AmapLoc a = a(this.ah, this.l.toString());
                if (br.a(a)) {
                    this.R = this.l.toString();
                    a.setType(d.A);
                    a.setLocationType(8);
                    a.setLocationDetail("离线定位结果，在线定位失败原因:" + amapLoc.getLocationDetail());
                    this.w = a;
                    amapLoc = a;
                }
            }
        }
        return amapLoc;
    }

    public synchronized void c() {
        this.af = false;
        this.aj = false;
        this.g = false;
        bc.m = false;
        x();
        this.K = null;
        this.L = null;
        this.R = null;
        J();
        if (this.c != null) {
            this.c.a();
            this.c = null;
            this.e = false;
            this.d = -1;
        }
        A();
        try {
            bf.a().a(this.m, 1);
        } catch (Throwable th) {
            bc.a(th, "APS", "destroy part");
        }
        au.a().b();
        br.i();
        try {
            if (!(this.m == null || this.t == null)) {
                this.m.unregisterReceiver(this.t);
            }
            this.t = null;
        } catch (Throwable th2) {
            this.t = null;
        }
        if (this.p != null) {
            this.p.i();
        }
        bh.a().c();
        ax.a();
        L();
        this.P = 0;
        q();
        if (this.q != null) {
            this.q.clear();
        }
        this.w = null;
        this.m = null;
        if (this.i != null) {
            this.i.clear();
            this.i = null;
        }
        ag = -1;
    }

    public synchronized void c(Context context) {
        try {
            if (ag == -1) {
                ag = 1;
                bo.a(context);
            }
        } catch (Throwable th) {
            bc.a(th, "APS", "initAuth");
        }
    }

    public String d() {
        return "2.5.0";
    }

    public synchronized void e() {
        List list = this.q;
        Collection collection = this.r;
        list.clear();
        synchronized (this.f) {
            if (collection != null) {
                if (collection.size() > 0) {
                    list.addAll(collection);
                }
            }
        }
    }

    public synchronized void f() {
        if (!(this.q == null || this.q.isEmpty())) {
            boolean z;
            int size;
            int i;
            ScanResult scanResult;
            int length;
            if (br.b() - this.C > 3600000) {
                q();
                if (this.q != null) {
                    this.q.clear();
                }
            }
            boolean h = br.h();
            if (br.a(this.v, "nbssid")) {
                try {
                    if (this.v.getString("nbssid").equals("1")) {
                        h = true;
                    } else if (this.v.getString("nbssid").equals("0")) {
                        h = false;
                    }
                    z = h;
                } catch (Throwable th) {
                    bc.a(th, "APS", "setWifiOrder part");
                }
                if (this.i == null) {
                    this.i = new TreeMap(Collections.reverseOrder());
                }
                this.i.clear();
                size = this.q.size();
                for (i = 0; i < size; i++) {
                    scanResult = (ScanResult) this.q.get(i);
                    if (br.a(scanResult) && (size <= 20 || a(scanResult.level))) {
                        if (!TextUtils.isEmpty(scanResult.SSID)) {
                            scanResult.SSID = "unkwn";
                        } else if (z) {
                            scanResult.SSID = String.valueOf(i);
                        } else {
                            scanResult.SSID = scanResult.SSID.replace("*", ".");
                            try {
                                length = scanResult.SSID.getBytes("UTF-8").length;
                            } catch (Throwable th2) {
                                bc.a(th2, "APS", "setWifiOrder");
                                length = 32;
                            }
                            if (length >= 32) {
                                scanResult.SSID = String.valueOf(i);
                            }
                        }
                        this.i.put(Integer.valueOf((scanResult.level * 30) + i), scanResult);
                    }
                }
                this.q.clear();
                for (ScanResult scanResult2 : this.i.values()) {
                    this.q.add(scanResult2);
                }
                this.i.clear();
            }
            z = h;
            if (this.i == null) {
                this.i = new TreeMap(Collections.reverseOrder());
            }
            this.i.clear();
            size = this.q.size();
            for (i = 0; i < size; i++) {
                scanResult2 = (ScanResult) this.q.get(i);
                if (!TextUtils.isEmpty(scanResult2.SSID)) {
                    scanResult2.SSID = "unkwn";
                } else if (z) {
                    scanResult2.SSID = String.valueOf(i);
                } else {
                    scanResult2.SSID = scanResult2.SSID.replace("*", ".");
                    length = scanResult2.SSID.getBytes("UTF-8").length;
                    if (length >= 32) {
                        scanResult2.SSID = String.valueOf(i);
                    }
                }
                this.i.put(Integer.valueOf((scanResult2.level * 30) + i), scanResult2);
            }
            this.q.clear();
            while (r1.hasNext()) {
                this.q.add(scanResult2);
            }
            this.i.clear();
        }
    }

    public synchronized void g() {
        if (this.D >= 1 && !this.e) {
            if (this.c == null) {
                this.c = new ar(this.m.getApplicationContext());
                this.c.a(this.k);
            }
            try {
                if (this.c != null) {
                    this.c.b();
                }
                this.e = true;
            } catch (Throwable th) {
                bc.a(th, "APS", "bindService");
                this.e = true;
            }
        }
    }

    public synchronized AmapLoc h() {
        AmapLoc amapLoc;
        if (this.U.length() > 0) {
            this.U.delete(0, this.U.length());
        }
        if (!this.af) {
            j();
        }
        I();
        amapLoc = null;
        if (TextUtils.isEmpty(this.ah)) {
            if (null == null) {
                amapLoc = new AmapLoc();
            }
            amapLoc.setErrorCode(this.h);
            amapLoc.setLocationDetail(this.U.toString());
        } else {
            amapLoc = be.a().a(this.ai, this.l, this.ae);
            if (br.a(amapLoc)) {
                this.Q = 0;
                amapLoc.setLocationType(4);
                this.w = amapLoc;
                J();
            }
        }
        return amapLoc;
    }

    public void i() {
        if (this.j && !u()) {
            v();
        }
    }
}
