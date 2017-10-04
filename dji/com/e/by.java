package com.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.HandlerThread;
import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.text.SimpleDateFormat;
import java.util.List;

public class by {
    protected static boolean a = false;
    protected static boolean b = true;
    private static int d = 10;
    private static int e = 2;
    private static int f = 10;
    private static int g = 10;
    private static int h = 50;
    private static int i = 200;
    private static long j = 0;
    private static Object k = new Object();
    private static by l;
    private a A = null;
    private b B = new b(this) {
        final /* synthetic */ by a;

        {
            this.a = r1;
        }

        public void a() {
            try {
                this.a.o();
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "onChange");
            }
        }
    };
    private LocationListener C = new LocationListener(this) {
        final /* synthetic */ by a;

        {
            this.a = r1;
        }

        private boolean a(Location location) {
            if (location == null) {
                return false;
            }
            try {
                return "gps".equalsIgnoreCase(location.getProvider()) && location.getLatitude() > -90.0d && location.getLatitude() < 90.0d && location.getLongitude() > -180.0d && location.getLongitude() < 180.0d;
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "isValid");
                return false;
            }
        }

        public void onLocationChanged(Location location) {
            try {
                long time = location.getTime();
                long currentTimeMillis = System.currentTimeMillis();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                simpleDateFormat.format(Long.valueOf(time));
                simpleDateFormat.format(Long.valueOf(currentTimeMillis));
                if (time > 0) {
                    currentTimeMillis = time;
                }
                if (location != null && a(location)) {
                    if (location.getSpeed() > ((float) by.f)) {
                        ca.a(by.i);
                        ca.b(by.i * 10);
                    } else if (location.getSpeed() > ((float) by.e)) {
                        ca.a(by.h);
                        ca.b(by.h * 10);
                    } else {
                        ca.a(by.g);
                        ca.b(by.g * 10);
                    }
                    this.a.x.a();
                    a(location);
                    if (this.a.x.a() && a(location)) {
                        location.setTime(System.currentTimeMillis());
                        this.a.a(location, 0, currentTimeMillis);
                    }
                }
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "onLocationChanged");
            }
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    };
    private BroadcastReceiver D = new BroadcastReceiver(this) {
        final /* synthetic */ by a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                try {
                    String action = intent.getAction();
                    if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                        bw.e = false;
                    }
                    if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                        bw.e = true;
                    }
                    if (action.equals("android.intent.action.MEDIA_EJECT")) {
                        bw.e = true;
                    }
                } catch (Throwable th) {
                    bc.a(th, "CollectorManager", "mMockReceier");
                }
            }
        }
    };
    private BroadcastReceiver E = new BroadcastReceiver(this) {
        final /* synthetic */ by a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                try {
                    if (intent.getAction().equals("android.location.GPS_FIX_CHANGE")) {
                        by.b = false;
                    }
                } catch (Throwable th) {
                    bc.a(th, "CollectorManager", "mReceiver");
                }
            }
        }
    };
    private GpsStatus F = null;
    private int G = 0;
    Object c = new Object();
    private boolean m = false;
    private int n = -1;
    private int o = 0;
    private int p = 0;
    private Context q;
    private LocationManager r;
    private bw s;
    private ca t;
    private ce u;
    private bx v;
    private cc w;
    private bz x;
    private cj y;
    private HandlerThread z = null;

    protected interface b {
        void a();
    }

    protected class a implements Listener, NmeaListener {
        long a = 0;
        final /* synthetic */ by b;

        protected a(by byVar) {
            this.b = byVar;
        }

        public void onGpsStatusChanged(int i) {
            try {
                if (this.b.r != null) {
                    switch (i) {
                        case 4:
                            if (by.a || System.currentTimeMillis() - this.a >= 10000) {
                                if (this.b.F == null) {
                                    this.b.F = this.b.r.getGpsStatus(null);
                                } else {
                                    this.b.r.getGpsStatus(this.b.F);
                                }
                                int i2 = 0;
                                int i3 = 0;
                                for (GpsSatellite usedInFix : this.b.F.getSatellites()) {
                                    i2++;
                                    i3 = usedInFix.usedInFix() ? i3 + 1 : i3;
                                }
                                if (this.b.n == -1 || ((i3 >= 4 && this.b.n < 4) || (i3 < 4 && this.b.n >= 4))) {
                                    this.b.n = i3;
                                    if (i3 < 4) {
                                        if (this.b.s != null) {
                                            this.b.s.t();
                                        }
                                    } else if (this.b.s != null) {
                                        this.b.s.s();
                                    }
                                }
                                if (!by.a) {
                                    if ((i3 > 3 || i2 > 15) && this.b.r.getLastKnownLocation("gps") != null) {
                                        this.a = System.currentTimeMillis();
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                    bc.a(th, "CollectorManager", "onGpsStatusChanged");
                }
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "onGpsStatusChanged");
            }
        }

        public void onNmeaReceived(long j, String str) {
        }
    }

    private by(Context context, ba baVar, az azVar) {
        try {
            this.q = context;
            this.s = bw.a(context, baVar, azVar);
            this.y = new cj();
            this.t = new ca(this.s);
            this.v = new bx(context);
            this.u = new ce(this.v);
            this.w = new cc(this.v);
            this.r = (LocationManager) this.q.getSystemService(n.C);
            this.x = bz.a(this.q);
            this.x.a(this.B);
            o();
            this.m = q();
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "CollectorManager");
        }
    }

    public static by a(Context context, ba baVar, az azVar) {
        if (l == null) {
            try {
                synchronized (k) {
                    if (l == null) {
                        l = new by(context, baVar, azVar);
                    }
                }
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "getInstance");
            }
        }
        return l;
    }

    public static String a(String str) {
        return "version".equals(str) ? "V1.0.0r" : "date".equals(str) ? "COL.15.0929r" : null;
    }

    private void a(Location location, int i, long j) {
        List k;
        Long valueOf;
        int i2 = 1;
        try {
            j = System.currentTimeMillis();
            boolean a = this.t.a(location);
            if (a) {
                this.t.b.b = new Location(location);
            }
            boolean b = this.t.b(location);
            if (b) {
                this.t.a.b = new Location(location);
            }
            if (i == 1) {
                b = true;
                a = true;
            } else if (i == 2) {
                b = true;
                a = false;
            }
            if (!a) {
                i2 = b ? 2 : 0;
            } else if (b) {
                i2 = 3;
            }
            ci a2 = this.y.a(location, this.s, i2, (byte) this.G, j, false);
        } catch (Exception e) {
            a2 = null;
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "collect");
            return;
        }
        if (a2 != null) {
            if (this.s != null) {
                k = this.s.k();
                valueOf = Long.valueOf(0);
                if (k != null && k.size() > 0) {
                    valueOf = (Long) k.get(0);
                }
                this.u.a(valueOf.longValue(), a2.a());
            }
        }
        if (this.q != null && this.y != null) {
            SharedPreferences sharedPreferences = this.q.getSharedPreferences("app_pref", 0);
            if (!sharedPreferences.getString("get_sensor", "").equals("true")) {
                try {
                    a2 = this.y.a(null, this.s, i2, (byte) this.G, j, true);
                } catch (Throwable th2) {
                    bc.a(th2, "CollectorManager", "collect inner");
                    a2 = null;
                }
                if (a2 == null) {
                    return;
                }
                if (this.s != null) {
                    k = this.s.k();
                    valueOf = Long.valueOf(0);
                    if (k != null && k.size() > 0) {
                        valueOf = (Long) k.get(0);
                    }
                    this.u.a(valueOf.longValue(), a2.a());
                    sharedPreferences.edit().putString("get_sensor", "true").commit();
                }
            }
        }
    }

    private void o() {
        try {
            this.o = this.x.b() * 1000;
            this.p = this.x.c();
            this.t.a(this.o, this.p);
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "updateStrategy");
        }
    }

    private void p() {
        String str = "";
        try {
            this.r.removeGpsStatusListener(this.A);
            this.A = null;
            this.r.removeUpdates(this.C);
            if (this.z != null) {
                this.z.quit();
                this.z = null;
            }
            this.z = new HandlerThread(this, str) {
                final /* synthetic */ by a;

                public void onLooperPrepared() {
                    try {
                        synchronized (this.a.c) {
                            if (by.a) {
                                this.a.A = new a(this.a);
                                this.a.r.addGpsStatusListener(this.a.A);
                                this.a.r.requestLocationUpdates("passive", 1000, (float) by.d, this.a.C);
                            }
                        }
                    } catch (Throwable th) {
                        bc.a(th, "CollectorManager", "requestLocationUpdates-onLooperPrepared");
                    }
                }

                public void run() {
                    try {
                        super.run();
                        if (!(this.a.A == null || this.a.r == null)) {
                            this.a.r.removeGpsStatusListener(this.a.A);
                            this.a.r.removeUpdates(this.a.C);
                            this.a.A = null;
                        }
                    } catch (Throwable th) {
                        if (!(this.a.A == null || this.a.r == null)) {
                            this.a.r.removeGpsStatusListener(this.a.A);
                            this.a.r.removeUpdates(this.a.C);
                            this.a.A = null;
                        }
                        quit();
                    }
                    quit();
                }
            };
            this.z.start();
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "requestLocationUpdates");
        }
    }

    private boolean q() {
        try {
            List allProviders = this.r.getAllProviders();
            return allProviders != null && allProviders.contains("gps") && allProviders.contains("passive");
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "canCollect");
            return false;
        }
    }

    private void r() {
        try {
            IntentFilter intentFilter = new IntentFilter("android.location.GPS_ENABLED_CHANGE");
            intentFilter.addAction("android.location.GPS_FIX_CHANGE");
            b = true;
            this.q.registerReceiver(this.E, intentFilter);
            intentFilter = new IntentFilter();
            intentFilter.setPriority(1000);
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter.addDataScheme(d.A);
            this.q.registerReceiver(this.D, intentFilter);
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "registerReceiver");
        }
    }

    private void s() {
        if (this.E != null) {
            try {
                this.q.unregisterReceiver(this.E);
                this.E = null;
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "registerReceiver");
            }
        }
        if (this.D != null) {
            try {
                this.q.unregisterReceiver(this.D);
                this.D = null;
            } catch (Throwable th2) {
                bc.a(th2, "CollectorManager", "registerReceiver");
            }
        }
    }

    public void a() {
        if (this.s != null) {
            this.s.v();
        }
    }

    public void a(int i) {
        if (i == 256 || i == 8736 || i == 768) {
            this.v.a(i);
            return;
        }
        try {
            throw new RuntimeException("invalid Size! must be COLLECTOR_SMALL_SIZE or COLLECTOR_BIG_SIZE or COLLECTOR_MEDIUM_SIZE");
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "setCollectorSize");
        }
    }

    public void a(co coVar, String str) {
        if (!bw.e) {
            try {
                boolean a = this.x.a(str);
                if (coVar != null) {
                    byte[] a2 = coVar.a();
                    if (a && a2 != null) {
                        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.q.getSystemService("connectivity")).getActiveNetworkInfo();
                        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                            if (activeNetworkInfo.getType() == 1) {
                                this.x.a(a2.length + this.x.e());
                            } else {
                                this.x.b(a2.length + this.x.f());
                            }
                        }
                    }
                    coVar.a(a);
                    this.w.a(coVar);
                }
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "callBackWrapData");
            }
        }
    }

    public void b() {
        try {
            bw.d = true;
            if (this.m && this.s != null && !a) {
                a = true;
                r();
                p();
                this.s.a();
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", MessageKey.MSG_ACCEPT_TIME_START);
        }
    }

    public void b(int i) {
        if (this.s != null) {
            try {
                this.s.a(i);
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "setWifiScanFreq");
            }
        }
    }

    public void c() {
        try {
            bw.d = false;
            bw.e = false;
            if (this.m && this.s != null && a) {
                s();
                if (this.s != null) {
                    this.s.t();
                }
                synchronized (this.c) {
                    a = false;
                    this.r.removeGpsStatusListener(this.A);
                    this.r.removeNmeaListener(this.A);
                    this.A = null;
                    this.r.removeUpdates(this.C);
                    if (this.z != null) {
                        this.z.quit();
                        this.z = null;
                    }
                }
                this.s.b();
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "stop");
        }
    }

    public void d() {
        try {
            if (this.m) {
                c();
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "destroy");
        }
    }

    public boolean e() {
        return a;
    }

    public co f() {
        co coVar = null;
        if (this.w != null) {
            try {
                g();
                if (this.x.a() && !bw.e) {
                    coVar = this.w.a(this.x.d());
                }
            } catch (Throwable th) {
                bc.a(th, "CollectorManager", "getWrapData");
            }
        }
        return coVar;
    }

    public boolean g() {
        try {
            if (this.s != null) {
                List k = this.s.k();
                if (k != null && k.size() > 0) {
                    return this.v.b(((Long) k.get(0)).longValue());
                }
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "setUploadEnabled");
        }
        return false;
    }

    public int h() {
        try {
            if (this.w != null) {
                return this.w.a();
            }
        } catch (Throwable th) {
            bc.a(th, "CollectorManager", "getLeftUploadNum");
        }
        return 0;
    }
}
