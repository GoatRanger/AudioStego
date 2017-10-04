package com.e;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.GpsStatus.NmeaListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.HandlerThread;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.media.TransportMediator;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.here.posclient.UpdateOptions;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.pilot.usercenter.mode.n;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

public class bw {
    private static int H = 10000;
    static String a = "";
    static String b = "log.txt";
    static String c = "";
    protected static boolean d = true;
    protected static boolean e = false;
    protected static final String[] j = new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"};
    private static LocationManager m = null;
    private static bw z = null;
    private a A = null;
    private b B = null;
    private CellLocation C = null;
    private long D = 0;
    private List<ScanResult> E = new ArrayList();
    private Timer F = null;
    private HandlerThread G = null;
    Object f = new Object();
    boolean g = false;
    ba h;
    az i;
    private Context k = null;
    private TelephonyManager l = null;
    private SensorManager n = null;
    private String o = "";
    private boolean p = false;
    private int q = 0;
    private long r = -1;
    private String s = "";
    private String t = "";
    private int u = 0;
    private int v = 0;
    private int w = 0;
    private String x = "";
    private long y = 0;

    private class a extends PhoneStateListener {
        final /* synthetic */ bw a;

        private a(bw bwVar) {
            this.a = bwVar;
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            try {
                this.a.y = System.currentTimeMillis();
                this.a.C = cellLocation;
                super.onCellLocationChanged(cellLocation);
            } catch (Throwable th) {
            }
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            try {
                if (serviceState.getState() == 0) {
                    String[] a = br.a(this.a.l);
                    this.a.u = Integer.parseInt(a[0]);
                    this.a.v = Integer.parseInt(a[1]);
                }
                super.onServiceStateChanged(serviceState);
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "onServiceStateChanged");
            }
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            try {
                if (this.a.p) {
                    this.a.q = signalStrength.getCdmaDbm();
                } else {
                    this.a.q = signalStrength.getGsmSignalStrength();
                    if (this.a.q == 99) {
                        this.a.q = -1;
                    } else {
                        this.a.q = (this.a.q * 2) - 113;
                    }
                }
                super.onSignalStrengthsChanged(signalStrength);
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "onSignalStrengthsChanged");
            }
        }
    }

    private class b implements NmeaListener {
        final /* synthetic */ bw a;

        private b(bw bwVar) {
            this.a = bwVar;
        }

        public void onNmeaReceived(long j, String str) {
            try {
                this.a.r = j;
                this.a.s = str;
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "onNmeaReceived");
            }
        }
    }

    private bw(Context context, ba baVar, az azVar) {
        if (context != null) {
            try {
                this.h = baVar;
                this.i = azVar;
                this.k = context;
                this.l = (TelephonyManager) context.getSystemService("phone");
                if (m == null) {
                    m = (LocationManager) context.getSystemService(n.C);
                }
                this.n = (SensorManager) context.getSystemService("sensor");
                if (this.l != null && baVar != null) {
                    this.o = this.l.getSubscriberId();
                    WifiInfo b = baVar.b();
                    if (b != null) {
                        this.t = b.getMacAddress();
                        if (this.t != null && this.t.length() > 0) {
                            this.t = this.t.replace(":", "");
                        }
                    }
                    String[] a = br.a(this.l);
                    this.u = Integer.parseInt(a[0]);
                    this.v = Integer.parseInt(a[1]);
                    this.w = this.l.getNetworkType();
                    this.x = context.getPackageName();
                    this.p = this.l.getPhoneType() == 2;
                }
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "ClientInfoUtil");
            }
        }
    }

    private static int a(Object obj) {
        try {
            Method declaredMethod = Sensor.class.getDeclaredMethod("getMinDelay", (Class[]) null);
            if (declaredMethod != null) {
                return ((Integer) declaredMethod.invoke(obj, (Object[]) null)).intValue();
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getMinDelay");
        }
        return 0;
    }

    protected static bw a(Context context, ba baVar, az azVar) {
        if (z == null) {
            try {
                if (c(context) && b(context)) {
                    z = new bw(context, baVar, azVar);
                }
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "getInstance");
            }
        }
        return z;
    }

    private void a(NmeaListener nmeaListener) {
        if (m != null && nmeaListener != null) {
            m.addNmeaListener(nmeaListener);
        }
    }

    private void a(List<ScanResult> list) {
        if (list != null) {
            try {
                if (list.size() >= 1) {
                    Object hashMap = new HashMap();
                    for (int i = 0; i < list.size(); i++) {
                        ScanResult scanResult = (ScanResult) list.get(i);
                        if (scanResult.SSID == null) {
                            scanResult.SSID = "null";
                        }
                        hashMap.put(Integer.valueOf(scanResult.level), scanResult);
                    }
                    TreeMap treeMap = new TreeMap(Collections.reverseOrder());
                    treeMap.putAll(hashMap);
                    list.clear();
                    for (Object obj : treeMap.keySet()) {
                        list.add(treeMap.get(obj));
                    }
                    hashMap.clear();
                    treeMap.clear();
                }
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "setWifiOrder");
            }
        }
    }

    protected static boolean a(Context context) {
        Throwable th;
        if (context == null) {
            return true;
        }
        boolean z;
        try {
            if (Secure.getString(context.getContentResolver(), "mock_location").equals("0")) {
                z = false;
            } else {
                PackageManager packageManager = context.getPackageManager();
                List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(128);
                String str = "android.permission.ACCESS_MOCK_LOCATION";
                String packageName = context.getPackageName();
                z = false;
                for (ApplicationInfo applicationInfo : installedApplications) {
                    try {
                        if (z) {
                            break;
                        }
                        boolean z2;
                        try {
                            String[] strArr = packageManager.getPackageInfo(applicationInfo.packageName, 4096).requestedPermissions;
                            if (strArr != null) {
                                int length = strArr.length;
                                int i = 0;
                                while (i < length) {
                                    if (!strArr[i].equals(str)) {
                                        i++;
                                    } else if (!applicationInfo.packageName.equals(packageName)) {
                                        z2 = true;
                                        z = z2;
                                    }
                                }
                            }
                            z2 = z;
                        } catch (Exception e) {
                            z2 = z;
                        }
                        z = z2;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
            bc.a(th, "ClientInfoUtil", "isMockGps");
            return z;
        }
        return z;
    }

    protected static boolean a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        int i = 0;
        while (i < strArr.length) {
            try {
                if (strArr[i].equals(str)) {
                    return true;
                }
                i++;
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "hasString");
                return false;
            }
        }
        return false;
    }

    private void b(NmeaListener nmeaListener) {
        if (m != null && nmeaListener != null) {
            m.removeNmeaListener(nmeaListener);
        }
    }

    private static boolean b(Context context) {
        try {
            if (m == null) {
                m = (LocationManager) context.getSystemService(n.C);
            }
            if (m != null) {
                for (String str : m.getAllProviders()) {
                    if (!str.equals("passive")) {
                        if (str.equals("gps")) {
                        }
                    }
                    return true;
                }
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "isProviderEnabled");
        }
        return false;
    }

    private static boolean c(Context context) {
        try {
            try {
                String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                for (String a : j) {
                    if (!a(strArr, a)) {
                        return false;
                    }
                }
            } catch (NameNotFoundException e) {
                return false;
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getPermission");
        }
        return true;
    }

    private boolean x() {
        try {
            return System.currentTimeMillis() - this.D < 3500;
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "isFresh_wifi");
            return true;
        }
    }

    private void y() {
        if (this.h != null) {
            try {
                if (d) {
                    this.h.d();
                }
            } catch (Throwable th) {
                bc.a(th, "ClientInfoUtil", "scanWifi");
            }
        }
    }

    protected List<Object> a(float f) {
        List<Object> arrayList = new ArrayList();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (Math.abs(f) <= 1.0f) {
                f = 1.0f;
            }
            CellLocation cellLocation = (CellLocation) h().get(1);
            if (cellLocation != null && (cellLocation instanceof GsmCellLocation)) {
                arrayList.add(Integer.valueOf(((GsmCellLocation) cellLocation).getLac()));
                arrayList.add(Integer.valueOf(((GsmCellLocation) cellLocation).getCid()));
                if (((double) (currentTimeMillis - ((Long) h().get(0)).longValue())) <= FlyForbidProtocol.DATABASE_UPDATE_DIST / ((double) f)) {
                    arrayList.add(Integer.valueOf(1));
                } else {
                    arrayList.add(Integer.valueOf(0));
                }
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getGsmData");
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected java.util.List<java.lang.Object> a(boolean r7) {
        /*
        r6 = this;
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = r6.c();	 Catch:{ Throwable -> 0x003f }
        if (r0 == 0) goto L_0x0047;
    L_0x000b:
        r0 = new java.util.ArrayList;	 Catch:{ Throwable -> 0x003f }
        r0.<init>();	 Catch:{ Throwable -> 0x003f }
        monitor-enter(r6);	 Catch:{ Throwable -> 0x003f }
        if (r7 != 0) goto L_0x0019;
    L_0x0013:
        r1 = r6.x();	 Catch:{ all -> 0x003c }
        if (r1 == 0) goto L_0x003a;
    L_0x0019:
        r4 = r6.D;	 Catch:{ all -> 0x003c }
        r1 = java.lang.Long.valueOf(r4);	 Catch:{ all -> 0x003c }
        r0.add(r1);	 Catch:{ all -> 0x003c }
        r1 = 0;
    L_0x0023:
        r3 = r6.E;	 Catch:{ all -> 0x003c }
        r3 = r3.size();	 Catch:{ all -> 0x003c }
        if (r1 >= r3) goto L_0x0037;
    L_0x002b:
        r3 = r6.E;	 Catch:{ all -> 0x003c }
        r3 = r3.get(r1);	 Catch:{ all -> 0x003c }
        r2.add(r3);	 Catch:{ all -> 0x003c }
        r1 = r1 + 1;
        goto L_0x0023;
    L_0x0037:
        r0.add(r2);	 Catch:{ all -> 0x003c }
    L_0x003a:
        monitor-exit(r6);	 Catch:{ all -> 0x003c }
    L_0x003b:
        return r0;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x003c }
        throw r0;	 Catch:{ Throwable -> 0x003f }
    L_0x003f:
        r0 = move-exception;
        r1 = "ClientInfoUtil";
        r2 = "getWifiScanResults";
        com.e.bc.a(r0, r1, r2);
    L_0x0047:
        r0 = new java.util.ArrayList;
        r0.<init>();
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bw.a(boolean):java.util.List<java.lang.Object>");
    }

    protected void a() {
        try {
            String str = "";
            b();
            this.g = true;
            if (this.G != null) {
                this.G.quit();
            }
            this.G = new HandlerThread(this, str) {
                final /* synthetic */ bw a;

                public void onLooperPrepared() {
                    try {
                        synchronized (this.a.f) {
                            this.a.F = new Timer();
                            this.a.A = new a();
                            if (this.a.i != null) {
                                this.a.i.a(this.a.A);
                            }
                            this.a.B = new b();
                            try {
                                this.a.a(this.a.B);
                            } catch (Exception e) {
                            }
                        }
                    } catch (Throwable th) {
                        bc.a(th, "ClientInfoUtil", "startInnerListen-onLooperPrepared");
                    }
                }

                public void run() {
                    try {
                        super.run();
                        if (this.a.i != null) {
                            this.a.i.a(null);
                        }
                        if (this.a.B != null) {
                            this.a.b(this.a.B);
                            this.a.B = null;
                        }
                    } catch (Throwable th) {
                        if (this.a.i != null) {
                            this.a.i.a(null);
                        }
                        if (this.a.B != null) {
                            this.a.b(this.a.B);
                            this.a.B = null;
                        }
                        quit();
                    }
                    quit();
                }
            };
            this.G.start();
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "startInnerListen");
        }
    }

    protected void a(int i) {
        try {
            if (i != H) {
                synchronized (this) {
                    this.E.clear();
                }
                if (this.F != null) {
                    this.F.cancel();
                    this.F = null;
                }
                if (i >= 5000) {
                    H = i;
                    this.F = new Timer();
                    y();
                }
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "setWifiScanFreq");
        }
    }

    protected String b(int i) {
        Throwable th;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        List list;
        try {
            if (this.n == null) {
                return "null";
            }
            List sensorList = this.n.getSensorList(-1);
            if (sensorList != null) {
                try {
                    if (!(sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getName() == null || ((Sensor) sensorList.get(i)).getName().length() <= 0)) {
                        list = sensorList;
                        return ((Sensor) list.get(i)).getName();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    list = sensorList;
                    th2 = th;
                    bc.a(th2, "ClientInfoUtil", "getSensorName");
                    return ((Sensor) list.get(i)).getName();
                }
            }
            return "null";
        } catch (Throwable th32) {
            th = th32;
            list = arrayList;
            th2 = th;
            bc.a(th2, "ClientInfoUtil", "getSensorName");
            return ((Sensor) list.get(i)).getName();
        }
    }

    protected List<Integer> b(float f) {
        List<Integer> arrayList = new ArrayList();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (Math.abs(f) <= 1.0f) {
                f = 1.0f;
            }
            CellLocation cellLocation = (CellLocation) h().get(1);
            if (cellLocation != null && (cellLocation instanceof CdmaCellLocation)) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                arrayList.add(Integer.valueOf(cdmaCellLocation.getSystemId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getNetworkId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationId()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationLongitude()));
                arrayList.add(Integer.valueOf(cdmaCellLocation.getBaseStationLatitude()));
                if (((double) (currentTimeMillis - ((Long) h().get(0)).longValue())) <= FlyForbidProtocol.DATABASE_UPDATE_DIST / ((double) f)) {
                    arrayList.add(Integer.valueOf(1));
                } else {
                    arrayList.add(Integer.valueOf(0));
                }
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getCdmaCell");
        }
        return arrayList;
    }

    protected void b() {
        try {
            synchronized (this.f) {
                this.g = false;
                if (this.i != null) {
                    this.i.a(null);
                }
                if (this.B != null) {
                    b(this.B);
                    this.B = null;
                }
                if (this.F != null) {
                    this.F.cancel();
                    this.F = null;
                }
                if (this.G != null) {
                    this.G.quit();
                    this.G = null;
                }
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "stopInnerListen");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected double c(int r7) {
        /*
        r6 = this;
        r2 = 0;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = r6.n;	 Catch:{ Throwable -> 0x001e }
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        r0 = r2;
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = r6.n;	 Catch:{ Throwable -> 0x001e }
        r4 = -1;
        r0 = r0.getSensorList(r4);	 Catch:{ Throwable -> 0x001e }
        if (r0 == 0) goto L_0x001c;
    L_0x0016:
        r1 = r0.get(r7);	 Catch:{ Throwable -> 0x0035 }
        if (r1 != 0) goto L_0x0029;
    L_0x001c:
        r0 = r2;
        goto L_0x000c;
    L_0x001e:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0022:
        r2 = "ClientInfoUtil";
        r3 = "getSensorMaxRange";
        com.e.bc.a(r1, r2, r3);
    L_0x0029:
        r0 = r0.get(r7);
        r0 = (android.hardware.Sensor) r0;
        r0 = r0.getMaximumRange();
        r0 = (double) r0;
        goto L_0x000c;
    L_0x0035:
        r1 = move-exception;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bw.c(int):double");
    }

    protected boolean c() {
        try {
            return this.h != null && this.h.f();
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "isWifiEnabled");
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int d(int r5) {
        /*
        r4 = this;
        r1 = 0;
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = r4.n;	 Catch:{ Throwable -> 0x001d }
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        r0 = r1;
    L_0x000b:
        return r0;
    L_0x000c:
        r0 = r4.n;	 Catch:{ Throwable -> 0x001d }
        r3 = -1;
        r0 = r0.getSensorList(r3);	 Catch:{ Throwable -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0015:
        r2 = r0.get(r5);	 Catch:{ Throwable -> 0x0030 }
        if (r2 != 0) goto L_0x0027;
    L_0x001b:
        r0 = r1;
        goto L_0x000b;
    L_0x001d:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0020:
        r2 = "ClientInfoUtil";
        r3 = "getSensorMinDelay";
        com.e.bc.a(r1, r2, r3);
    L_0x0027:
        r0 = r0.get(r5);
        r0 = a(r0);
        goto L_0x000b;
    L_0x0030:
        r1 = move-exception;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bw.d(int):int");
    }

    protected boolean d() {
        try {
            if (m != null && m.isProviderEnabled("gps")) {
                return true;
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "isGPSEnabled");
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int e(int r5) {
        /*
        r4 = this;
        r1 = 0;
        r2 = new java.util.ArrayList;
        r2.<init>();
        r0 = r4.n;	 Catch:{ Throwable -> 0x001d }
        if (r0 != 0) goto L_0x000c;
    L_0x000a:
        r0 = r1;
    L_0x000b:
        return r0;
    L_0x000c:
        r0 = r4.n;	 Catch:{ Throwable -> 0x001d }
        r3 = -1;
        r0 = r0.getSensorList(r3);	 Catch:{ Throwable -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0015:
        r2 = r0.get(r5);	 Catch:{ Throwable -> 0x0037 }
        if (r2 != 0) goto L_0x0027;
    L_0x001b:
        r0 = r1;
        goto L_0x000b;
    L_0x001d:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0020:
        r2 = "ClientInfoUtil";
        r3 = "getSensorPower";
        com.e.bc.a(r1, r2, r3);
    L_0x0027:
        r0 = r0.get(r5);
        r0 = (android.hardware.Sensor) r0;
        r0 = r0.getPower();
        r0 = (double) r0;
        r2 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r0 = r0 * r2;
        r0 = (int) r0;
        goto L_0x000b;
    L_0x0037:
        r1 = move-exception;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bw.e(int):int");
    }

    protected String e() {
        try {
            if (this.k != null) {
                return cx.q(this.k);
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "isGPSEnabled");
        }
        return "";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected double f(int r7) {
        /*
        r6 = this;
        r2 = 0;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r0 = r6.n;	 Catch:{ Throwable -> 0x001e }
        if (r0 != 0) goto L_0x000d;
    L_0x000b:
        r0 = r2;
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = r6.n;	 Catch:{ Throwable -> 0x001e }
        r4 = -1;
        r0 = r0.getSensorList(r4);	 Catch:{ Throwable -> 0x001e }
        if (r0 == 0) goto L_0x001c;
    L_0x0016:
        r1 = r0.get(r7);	 Catch:{ Throwable -> 0x0035 }
        if (r1 != 0) goto L_0x0029;
    L_0x001c:
        r0 = r2;
        goto L_0x000c;
    L_0x001e:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0022:
        r2 = "ClientInfoUtil";
        r3 = "getSensorResolution";
        com.e.bc.a(r1, r2, r3);
    L_0x0029:
        r0 = r0.get(r7);
        r0 = (android.hardware.Sensor) r0;
        r0 = r0.getResolution();
        r0 = (double) r0;
        goto L_0x000c;
    L_0x0035:
        r1 = move-exception;
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bw.f(int):double");
    }

    protected String f() {
        try {
            if (this.k != null) {
                cx.r(this.k);
            }
            if (this.o != null) {
                return this.o;
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getImsi");
        }
        return "";
    }

    protected byte g(int i) {
        List list;
        Throwable th;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        try {
            if (this.n == null) {
                return Byte.MAX_VALUE;
            }
            List sensorList = this.n.getSensorList(-1);
            if (sensorList != null) {
                try {
                    if (sensorList.get(i) != null && ((Sensor) sensorList.get(i)).getType() <= TransportMediator.KEYCODE_MEDIA_PAUSE) {
                        list = sensorList;
                        return (byte) ((Sensor) list.get(i)).getType();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    list = sensorList;
                    th2 = th;
                    bc.a(th2, "ClientInfoUtil", "getSensorType");
                    return (byte) ((Sensor) list.get(i)).getType();
                }
            }
            return Byte.MAX_VALUE;
        } catch (Throwable th32) {
            th = th32;
            list = arrayList;
            th2 = th;
            bc.a(th2, "ClientInfoUtil", "getSensorType");
            return (byte) ((Sensor) list.get(i)).getType();
        }
    }

    protected boolean g() {
        return this.p;
    }

    protected String h(int i) {
        Throwable th;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        List list;
        try {
            if (this.n == null) {
                return "null";
            }
            List sensorList = this.n.getSensorList(-1);
            if (sensorList != null) {
                try {
                    if (!(sensorList.get(i) == null || ((Sensor) sensorList.get(i)).getVendor() == null || ((Sensor) sensorList.get(i)).getVendor().length() <= 0)) {
                        list = sensorList;
                        return ((Sensor) list.get(i)).getVendor();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    list = sensorList;
                    th2 = th;
                    bc.a(th2, "ClientInfoUtil", "getSensorVendor");
                    return ((Sensor) list.get(i)).getVendor();
                }
            }
            return "null";
        } catch (Throwable th32) {
            th = th32;
            list = arrayList;
            th2 = th;
            bc.a(th2, "ClientInfoUtil", "getSensorVendor");
            return ((Sensor) list.get(i)).getVendor();
        }
    }

    protected List<Object> h() {
        if (System.getInt(this.k.getContentResolver(), "airplane_mode_on", 0) == 1) {
            return new ArrayList();
        }
        try {
            Object obj;
            List<Object> arrayList = new ArrayList();
            if (this.C == null) {
                try {
                    this.C = this.l.getCellLocation();
                } catch (Exception e) {
                }
            }
            if (this.i.a(this.C)) {
                this.y = System.currentTimeMillis();
                obj = this.C;
            } else {
                obj = this.i.a(br.a(this.k), false);
                if (this.i.a((CellLocation) obj)) {
                    this.C = obj;
                    this.y = System.currentTimeMillis();
                } else {
                    obj = this.C;
                }
            }
            arrayList.add(Long.valueOf(this.y));
            arrayList.add(obj);
            return arrayList;
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getMainCellLocation");
            return new ArrayList();
        }
    }

    protected byte i() {
        return (byte) this.q;
    }

    protected byte i(int i) {
        List list;
        Throwable th;
        Throwable th2;
        ArrayList arrayList = new ArrayList();
        try {
            if (this.n == null) {
                return Byte.MAX_VALUE;
            }
            List sensorList = this.n.getSensorList(-1);
            if (sensorList != null) {
                try {
                    if (sensorList.get(i) != null && ((Sensor) sensorList.get(i)).getType() <= TransportMediator.KEYCODE_MEDIA_PAUSE) {
                        list = sensorList;
                        return (byte) ((Sensor) list.get(i)).getVersion();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    list = sensorList;
                    th2 = th;
                    bc.a(th2, "ClientInfoUtil", "getSensorVersion");
                    return (byte) ((Sensor) list.get(i)).getVersion();
                }
            }
            return Byte.MAX_VALUE;
        } catch (Throwable th32) {
            th = th32;
            list = arrayList;
            th2 = th;
            bc.a(th2, "ClientInfoUtil", "getSensorVersion");
            return (byte) ((Sensor) list.get(i)).getVersion();
        }
    }

    protected List<ay> j() {
        try {
            if (this.i != null) {
                this.C = this.i.a(br.a(this.k), false);
                if (this.i.a(this.C)) {
                    this.y = System.currentTimeMillis();
                }
                return this.i.b();
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getNeighboringInfo");
        }
        return null;
    }

    protected List<Object> k() {
        List<Object> arrayList = new ArrayList();
        try {
            long j;
            Object obj;
            String str = "";
            if (d()) {
                long j2 = this.r;
                j = j2;
                obj = this.s;
            } else {
                String str2 = str;
                j = -1;
                String str3 = str2;
            }
            if (j <= 0) {
                j = System.currentTimeMillis() / 1000;
            }
            if (j > UpdateOptions.SOURCE_ANY) {
                j /= 1000;
            }
            arrayList.add(Long.valueOf(j));
            arrayList.add(obj);
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getNmea");
        }
        return arrayList;
    }

    protected long l() {
        Throwable th;
        long j = 0;
        long j2 = this.r;
        if (j2 > 0) {
            try {
                j = j2;
                for (int length = String.valueOf(j2).length(); length != 13; length = String.valueOf(j2).length()) {
                    if (length > 13) {
                        try {
                            j2 = j / 10;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else {
                        j2 = j * 10;
                    }
                    j = j2;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                j = j2;
                th = th4;
            }
        }
        return j;
        bc.a(th, "ClientInfoUtil", "getNmeaTime");
        return j;
    }

    protected String m() {
        try {
            if (this.t == null) {
                WifiInfo wifiInfo = null;
                if (this.h != null) {
                    wifiInfo = this.h.b();
                }
                if (wifiInfo != null) {
                    this.t = wifiInfo.getMacAddress();
                    if (this.t != null && this.t.length() > 0) {
                        this.t = this.t.replace(":", "");
                    }
                }
            }
            if (this.t != null) {
                return this.t;
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getDeviceMac");
        }
        return "";
    }

    protected int n() {
        return this.u;
    }

    protected int o() {
        return this.v;
    }

    protected int p() {
        return this.w;
    }

    protected String q() {
        try {
            if (this.x == null && this.k != null) {
                this.x = this.k.getPackageName();
            }
            if (this.x != null) {
                return this.x;
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getSourceName");
        }
        return "";
    }

    protected List<Object> r() {
        List<Object> arrayList = new ArrayList();
        try {
            if (c()) {
                List a = a(true);
                List list = (List) a.get(1);
                long longValue = ((Long) a.get(0)).longValue();
                a(list);
                arrayList.add(Long.valueOf(longValue));
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        ScanResult scanResult = (ScanResult) list.get(i);
                        if (arrayList.size() - 1 >= 40) {
                            break;
                        }
                        if (scanResult != null) {
                            List arrayList2 = new ArrayList();
                            arrayList2.add(scanResult.BSSID.replace(":", ""));
                            arrayList2.add(Integer.valueOf(scanResult.level));
                            arrayList2.add(scanResult.SSID);
                            arrayList.add(arrayList2);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getWifiData");
        }
        return arrayList;
    }

    protected void s() {
        try {
            synchronized (this) {
                this.E.clear();
            }
            if (this.F != null) {
                this.F.cancel();
                this.F = null;
            }
            this.F = new Timer();
            y();
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "openWifiScan");
        }
    }

    protected void t() {
        try {
            synchronized (this) {
                this.E.clear();
            }
            if (this.F != null) {
                this.F.cancel();
                this.F = null;
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "closeWifiScan");
        }
    }

    protected byte u() {
        List arrayList = new ArrayList();
        try {
            if (this.n == null) {
                return (byte) 0;
            }
            arrayList = this.n.getSensorList(-1);
            if (arrayList == null) {
                return (byte) 0;
            }
            return (byte) arrayList.size();
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "getSensorNum");
        }
    }

    public void v() {
        try {
            synchronized (this) {
                this.E.clear();
                List a = this.h.a();
                this.D = System.currentTimeMillis();
                if (a != null && a.size() > 0) {
                    for (int i = 0; i < a.size(); i++) {
                        this.E.add((ScanResult) a.get(i));
                    }
                }
            }
            TimerTask anonymousClass2 = new TimerTask(this) {
                final /* synthetic */ bw a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        if (bw.d) {
                            this.a.y();
                        }
                    } catch (Throwable th) {
                        bc.a(th, "ClientInfoUtil", "onReceive run");
                    }
                }
            };
            synchronized (this) {
                if (this.F != null) {
                    this.F.cancel();
                    this.F = null;
                }
                this.F = new Timer();
                this.F.schedule(anonymousClass2, (long) H);
            }
        } catch (Throwable th) {
            bc.a(th, "ClientInfoUtil", "onReceive");
        }
    }

    protected Context w() {
        return this.k;
    }
}
