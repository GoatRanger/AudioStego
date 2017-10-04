package com.nokia.maps;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.android.mpa.common.MapEngine.OnMapDownloadListener;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.here.android.mpa.service.MapService;
import com.nokia.maps.annotation.HybridNative;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.nlp.NlpResourceManager;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Online
public class MapsEngine extends MapServiceClient {
    private static final Object D = new Object();
    private static volatile MapsEngine E = null;
    private static Context F = null;
    private static boolean G = false;
    private static final String H = MapsEngine.class.getName();
    private static String I = null;
    private static AtomicBoolean J = new AtomicBoolean(false);
    private static String K = null;
    private static CopyOnWriteArrayList<Object> L = new CopyOnWriteArrayList();
    private static boolean N = true;
    private static OnEngineInitListener O = null;
    private static AtomicInteger P = new AtomicInteger(0);
    private static AtomicBoolean Q = new AtomicBoolean(true);
    private static boolean Y = false;
    private static boolean Z = false;
    static List<g> h = new ArrayList();
    static String i = null;
    static String j = null;
    static String k = null;
    static String l = null;
    static boolean m = true;
    static int n = -1;
    private static List<String> o = new ArrayList();
    private static de t = null;
    private static boolean u = true;
    private static e v = e.ENotInitialized;
    private static final d w = d.DYNAMIC;
    private static c x = c.UNKNOWN;
    private static String y = "";
    private CopyOnWriteArrayList<OnMapDownloadListener> A = null;
    private boolean B = false;
    private boolean C = false;
    private List<WeakReference<h>> M = new ArrayList();
    private CopyOnWriteArrayList<b> R = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<f> S = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<k> T = new CopyOnWriteArrayList();
    private l U = null;
    private az V = new com.nokia.maps.az.a(this) {
        final /* synthetic */ MapsEngine a;

        {
            this.a = r1;
        }

        public String a() throws RemoteException {
            if (this.a.U != null) {
                this.a.U.a();
            }
            if (this.a.a() || MapsEngine.P.get() <= 0) {
                return null;
            }
            ApplicationInfo applicationInfo = MapsEngine.F.getApplicationInfo();
            if (applicationInfo.name != null) {
                return applicationInfo.name;
            }
            if (applicationInfo.packageName != null) {
                return applicationInfo.packageName;
            }
            if (applicationInfo.processName != null) {
                return applicationInfo.processName;
            }
            return "Unknown process";
        }

        public void b() throws RemoteException {
            if (this.a.U != null) {
                this.a.U.b();
            }
        }
    };
    private boolean W = false;
    private com.nokia.maps.cc.a X = new com.nokia.maps.cc.a(this) {
        final /* synthetic */ MapsEngine a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.A != null && !this.a.C) {
                this.a.C = true;
                if (MapSettings.k() == MapSettings$b.EWorkerThread) {
                    this.a.Z();
                } else {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.Z();
                        }
                    });
                }
            } else if (this.a.A != null && this.a.C) {
                if (MapSettings.k() == MapSettings$b.EWorkerThread) {
                    this.a.aa();
                } else {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.aa();
                        }
                    });
                }
            }
        }

        public void b() {
            this.a.C = false;
            if (this.a.A == null) {
                return;
            }
            if (MapSettings.k() == MapSettings$b.EWorkerThread) {
                this.a.ab();
            } else {
                ez.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.ab();
                    }
                });
            }
        }
    };
    @OnlineNative
    private int nativeptr;
    private String[] p = new String[]{"mwconfig_client", "resource.db"};
    private String[] q = new String[]{"places"};
    private a r;
    private Locale s = null;
    private cc z;

    @Internal
    public interface f {
        void a(boolean z);
    }

    public interface h {
        void a(Context context, Intent intent);
    }

    private static class a extends BroadcastReceiver {
        private ConnectivityManager a;
        private HandlerThread b;
        private Boolean c = null;
        private int d;

        public a(Context context) throws Exception {
            this.a = (ConnectivityManager) context.getSystemService("connectivity");
            this.b = new HandlerThread("connection_handler");
            this.b.start();
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"), "android.permission.ACCESS_NETWORK_STATE", new Handler(this.b.getLooper()));
            b();
        }

        public void onReceive(Context context, Intent intent) {
            try {
                b();
            } catch (Exception e) {
                bj.c(MapsEngine.H, "Exception: %s", new Object[]{e.getLocalizedMessage()});
            }
        }

        public void a() {
            MapsEngine.F.unregisterReceiver(this);
            this.b.quit();
        }

        private void b() throws Exception {
            try {
                NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    bj.e(MapsEngine.H, "Current State is: " + activeNetworkInfo.getDetailedState().toString(), new Object[0]);
                }
                if (activeNetworkInfo != null && activeNetworkInfo.getDetailedState() == DetailedState.CONNECTED) {
                    int type = activeNetworkInfo.getType();
                    switch (type) {
                        case 0:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                            ConnectionInfoImpl.setTransportInfo(((TelephonyManager) MapsEngine.F.getSystemService("phone")).getNetworkOperator());
                            break;
                        case 1:
                            ConnectionInfoImpl.setTransportInfo("WiFi");
                            break;
                        case 6:
                            ConnectionInfoImpl.setTransportInfo("WiMAX");
                            break;
                        default:
                            ConnectionInfoImpl.setTransportInfo("Unknown");
                            break;
                    }
                    if (!(this.c != null && this.c.booleanValue() && this.d == type)) {
                        MapsEngine.c().setOnlineNative(false, false);
                        MapsEngine.c().setOnlineNative(true, type == 1);
                        bj.e(MapsEngine.H, "Current State is online ", new Object[0]);
                    }
                    this.c = Boolean.valueOf(true);
                    this.d = type;
                } else if (this.c == null || this.c.booleanValue()) {
                    this.c = Boolean.valueOf(false);
                    MapsEngine.c().setOnlineNative(false, false);
                    bj.e(MapsEngine.H, "Current State is offline ", new Object[0]);
                }
                Iterator it = MapsEngine.c().R.iterator();
                while (it.hasNext()) {
                    ((b) it.next()).a(this.c.booleanValue());
                }
            } catch (Throwable e) {
                bj.c(MapsEngine.H, "Exception occurred when calling ConnectivityManager.getActiveNetworkInfo().  (%s)", new Object[]{e.getLocalizedMessage()});
                throw new Exception(e);
            }
        }
    }

    public interface b {
        void a(boolean z);
    }

    enum c {
        UNKNOWN((short) -1),
        INTERNATIONAL((short) 0),
        CHINA((short) 1),
        PAKISTAN((short) 2),
        KOREA((short) 3),
        INDIA((short) 4);
        
        short g;

        private c(short s) {
            this.g = s;
        }
    }

    private enum d {
        DYNAMIC,
        INTERNATIONAL,
        CHINA
    }

    public enum e {
        ENotInitialized,
        EInitializing,
        EInitalized,
        EDiskCacheLocked,
        EError,
        EFileRW
    }

    private static class g {
        private final String a;
        private final boolean b;

        public g(String str, boolean z) {
            dy.a((Object) str, "Cannot have a library with null name");
            this.a = str;
            this.b = z;
        }

        public String a() {
            return this.a;
        }

        public boolean b() {
            return this.b;
        }
    }

    private static class i extends BroadcastReceiver {
        private Handler a = null;

        private static final class a implements Runnable {
            private final Context a;
            private final Intent b;
            private MapsEngine c;

            public a(Context context, Intent intent) {
                this.a = context;
                this.b = intent;
                try {
                    this.c = MapsEngine.c();
                } catch (Exception e) {
                    bj.c(MapsEngine.H, "MapEngine was not initialized", new Object[0]);
                }
            }

            public void run() {
                int i = 0;
                Locale d = this.c.Y();
                while (Locale.getDefault() == d && i < 100) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                    }
                    i++;
                }
                if (Locale.getDefault() != d) {
                    List<WeakReference> arrayList = new ArrayList();
                    try {
                        this.c.I();
                        for (WeakReference weakReference : this.c.M) {
                            h hVar = (h) weakReference.get();
                            if (hVar != null) {
                                hVar.a(this.a, this.b);
                            } else {
                                arrayList.add(weakReference);
                            }
                        }
                        for (WeakReference weakReference2 : arrayList) {
                            this.c.M.remove(weakReference2);
                        }
                        arrayList.clear();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }

        public i(Context context) {
            HandlerThread handlerThread = new HandlerThread("localechange_handler");
            handlerThread.start();
            context.registerReceiver(this, new IntentFilter("android.intent.action.LOCALE_CHANGED"), "android.permission.CHANGE_CONFIGURATION", new Handler(handlerThread.getLooper()));
        }

        public void onReceive(Context context, Intent intent) {
            if (this.a == null) {
                this.a = new Handler();
            }
            this.a.post(new a(context, intent));
        }
    }

    private class j extends AsyncTask<Void, Void, Error> {
        final /* synthetic */ MapsEngine a;
        private MapsEngine b;
        private OnEngineInitListener c;
        private boolean d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Error) obj);
        }

        public j(MapsEngine mapsEngine, MapsEngine mapsEngine2, OnEngineInitListener onEngineInitListener, boolean z) {
            this.a = mapsEngine;
            this.b = mapsEngine2;
            this.c = onEngineInitListener;
            this.d = z;
        }

        protected Error a(Void... voidArr) {
            Error a;
            synchronized (MapsEngine.D) {
                a = a();
            }
            return a;
        }

        private Error a() {
            bj.a(MapsEngine.H, "IN", new Object[0]);
            MapsEngine.g(MapsEngine.F);
            ApplicationContext.b(MapsEngine.F);
            this.a.createMapsEngineNative(this.a.K());
            bj.a(MapsEngine.H, "Successfully called Native Method init engine", new Object[0]);
            if (!this.a.f(MapsEngine.F)) {
                MapsEngine.v = e.EFileRW;
                return aq.a(Error.FILE_RW_ERROR, "Could not create cache directory.");
            } else if (!SSLCertManager.a(MapsEngine.F)) {
                MapsEngine.v = e.EFileRW;
                return aq.a(Error.FILE_RW_ERROR, "Could not deploy certificates.");
            } else if (!NlpResourceManager.a(MapsEngine.F)) {
                MapsEngine.v = e.EFileRW;
                return aq.a(Error.FILE_RW_ERROR, "Could not initialize NLP database.");
            } else if (PositioningResourceManager.a(MapsEngine.F)) {
                TelephonyManager telephonyManager = (TelephonyManager) MapsEngine.F.getSystemService("phone");
                File dir = MapsEngine.F.getDir("here_maps", 0);
                if (dir == null) {
                    MapsEngine.v = e.EFileRW;
                    return aq.a(Error.FILE_RW_ERROR, "Could not retrieve android cache directory.");
                } else if (dir.exists() || dir.mkdirs()) {
                    String file = dir.toString();
                    String e = MapSettings.e();
                    if (e == null) {
                        MapsEngine.v = e.EFileRW;
                        return aq.a(Error.FILE_RW_ERROR, "Could not retrieve voice cache directory.");
                    }
                    dir = new File(e);
                    if (dir.exists() || dir.mkdirs()) {
                        Error error;
                        int a = this.a.initEngine(MapsEngine.l, MapsEngine.i, MapsEngine.j, MapsEngine.k, MapSettings.a(), "diskcache-v4", file, e, telephonyManager, MapsEngine.y, MapsEngine.x.g, MapsEngine.K, this.a.g, !MapServiceClient.b, MapSettings.j());
                        MapsEngine.K = null;
                        switch (a) {
                            case 0:
                                error = Error.NONE;
                                break;
                            case 1:
                                error = aq.a(Error.USAGE_EXPIRED, "Evaluation version is over.");
                                break;
                            case 2:
                                error = aq.a(Error.MODEL_NOT_SUPPORTED, "Device " + Build.MANUFACTURER + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Build.MODEL + " is not supported.");
                                break;
                            case 3:
                                error = aq.a(Error.DEVICE_NOT_SUPPORTED, "Device with this IMEI number is not supported.");
                                break;
                            case 4:
                                error = aq.a(Error.UNKNOWN, "Native engine initialization failed for unknown reason.");
                                break;
                            case 5:
                                error = aq.a(Error.MISSING_APP_CREDENTIAL, "Missing app id or app token in the AndroidManifest.xml file. Please refer to the user guide for details about proper project setup.");
                                break;
                            case 7:
                                error = Error.BUSY;
                                break;
                            case 8:
                                error = Error.FILE_RW_ERROR;
                                break;
                            default:
                                error = aq.a(Error.UNKNOWN, "Native engine initialization failed for unknown reason (unsupported return code).");
                                break;
                        }
                        if (error == Error.NONE) {
                            this.a.I();
                            dz.a(MapsEngine.F);
                            this.a.d(MapsEngine.N);
                            this.a.e(MapsEngine.F);
                            this.a.z = new cc(this.b);
                            this.a.z.start();
                            MapsEngine.E = this.b;
                            MapsEngine.v = e.EInitalized;
                            MapsEngine.t = new de(MapsEngine.F);
                            try {
                                Class.forName("com.google.gson.GsonBuilder");
                                PlacesCategoryGraph.a(true);
                            } catch (ClassNotFoundException e2) {
                                bj.e(MapsEngine.H, "GSON library is not loaded", new Object[0]);
                            } catch (Exception e3) {
                                bj.c(MapsEngine.H, "PlacesCategoryGraph refreshCache failed.  Error: %s", new Object[]{e3.getLocalizedMessage()});
                            }
                            BaseNativeObject.I = true;
                            String a2 = ConnectionInfoImpl.a(false);
                            if (a2 != null) {
                                ConnectionInfoImpl.setDeviceId(a2);
                            }
                            l.a(MapsEngine.F, MapsEngine.Z);
                        } else {
                            MapsEngine.v = e.EError;
                        }
                        bj.a(MapsEngine.H, "OUT - return %s", new Object[]{error.toString()});
                        return error;
                    }
                    bj.c(MapsEngine.H, "Failed to mkdirs() for the  folder", new Object[0]);
                    MapsEngine.v = e.EFileRW;
                    return aq.a(Error.FILE_RW_ERROR, "Could not create voice cache directory.");
                } else {
                    bj.c(MapsEngine.H, "Failed to mkdirs() for the here_maps folder", new Object[0]);
                    MapsEngine.v = e.EFileRW;
                    return aq.a(Error.FILE_RW_ERROR, "Could not create android cache directory.");
                }
            } else {
                MapsEngine.v = e.EFileRW;
                return aq.a(Error.FILE_RW_ERROR, "Could not initialize Positioning resources.");
            }
        }

        protected void a(Error error) {
            bj.a(MapsEngine.H, "onPostExecute", new Object[0]);
            if (error != Error.NONE) {
                MapsEngine.E = null;
                MapsEngine.O = null;
                MapsEngine.v = e.EError;
            } else if (this.d) {
                this.a.a(this.a.V);
            }
            MapsEngine.b(MapsEngine.F, error, this.c);
        }
    }

    public interface k {

        public static abstract class a implements k {
            public void a(MapPackageSelection mapPackageSelection, String str, boolean z, boolean z2) {
            }

            public void a(String str, boolean z) {
            }

            public void a(String[] strArr, boolean z) {
            }

            public void a(int i) {
            }

            public void a(String str, int i) {
            }

            public void b(MapPackageSelection mapPackageSelection) {
            }

            public void a(long j, long j2) {
            }
        }

        void a(int i);

        void a(long j, long j2);

        void a(MapPackageSelection mapPackageSelection, String str, boolean z, boolean z2);

        void a(String str, int i);

        void a(String str, boolean z);

        void a(String[] strArr, boolean z);

        void b(MapPackageSelection mapPackageSelection);
    }

    public interface l {
        String a();

        void b();
    }

    private enum m {
        MAP_DATA(0),
        MAP_TILE(1),
        HISTORICAL_TRAFFIC_TILE(2),
        PLACES(3),
        GEOLOCATION(4),
        REVERSE_GEOLOCATION(5),
        CUSTOM_LOCATION(6),
        CUSTOM_LOCATION_QA(7),
        SATELLITE(8),
        TERRAIN(9),
        STREET_LEVEL_IMAGERY(10),
        STREET_LEVEL_IMAGERY_COVERAGE_TILES(11),
        STREET_LEVEL_IMAGERY_REPORTING(12),
        STREET_LEVEL_IMAGERY_REPORTING_QA(13),
        VENUE3D_AUTH(17),
        VENUE3D_AUTH_QA(18),
        PUBLIC_TRANSPORT_TIMETABLE_ROUTING(20),
        VOICE_CATALOG(22),
        VOICE_CATALOG_QA(23),
        URBAN_MOBILITY(24),
        URBAN_MOBILITY_DEVEL(25),
        URBAN_MOBILITY_FUNC(26),
        URBAN_MOBILITY_QA(27),
        URBAN_MOBILITY_DEMO(28),
        URBAN_MOBILITY_DI(29),
        URBAN_MOBILITY_CI(30),
        URBAN_MOBILITY_STAGING(31),
        TRAFFIC_DATA(32),
        ROUTING_DATA(33);
        
        private int D;

        private m(int i) {
            this.D = i;
        }

        public int a() {
            return this.D;
        }
    }

    public static native void addIMEICryptoString(String str);

    private native boolean containsChinaMcc(int[] iArr);

    private native boolean containsIndiaMcc(int[] iArr);

    private native synchronized boolean continueMapInstallationNative();

    private native void createMapsEngineNative(boolean z);

    private native void destroyMapsEngineNative();

    private native synchronized void getCompatibleMapVersionsNative();

    public static native long getDiskCacheSize();

    private native String getMapDataCountryCode(String str);

    private native synchronized void getMapVersionNative();

    private native String getMccCountryCode(int i);

    public static native long getPermissionStringTimeExpiry();

    private native String getServerUrl(int i, boolean z);

    private native int initEngine(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, TelephonyManager telephonyManager, String str9, short s, String str10, int i, boolean z, String str11);

    private static native boolean isCacheTrimmingOnNative();

    public static native boolean isEval();

    public static final native boolean isInternalBuild();

    private native boolean isSouthKoreaEndpointsEnabled();

    private static native void setHttpProxy(String str);

    private native void setLocaleLanguageNative(String str);

    private native void setOnlineNative(boolean z, boolean z2);

    private native synchronized void updateMapToVersionNative(String str);

    public native synchronized boolean beginODMLInstallation();

    public native synchronized boolean cancelCompatibleMapVersionQuery();

    public native synchronized boolean cancelMapInstallation();

    public native synchronized void endODMLInstallation();

    public native boolean isOnline();

    public native synchronized boolean pollMapData();

    native synchronized void reloadMapModelEngineNative();

    static {
        h.add(new g("crypto_here", true));
        h.add(new g("ssl_here", true));
        h.add(new g("NuanceVocalizer", false));
        h.add(new g("os_adaptation.context", false));
        h.add(new g("os_adaptation.network", false));
        h.add(new g("MAPSJNI", true));
    }

    public static e b() {
        return v;
    }

    private static boolean d(Context context) {
        boolean z;
        boolean a = bf.a(context);
        if (a) {
            bj.a(H, "Loading %d libraries ...", new Object[]{Integer.valueOf(h.size())});
            z = a;
            for (g gVar : h) {
                boolean booleanValue = bf.a(gVar.a()).booleanValue();
                if (booleanValue) {
                    a = booleanValue;
                } else if (gVar.b()) {
                    o.add(gVar.a());
                    a = booleanValue;
                } else {
                    a = true;
                }
                if (z && r0) {
                    a = true;
                } else {
                    a = false;
                }
                z = a;
            }
        } else {
            o.add(bf.a);
            z = a;
        }
        BaseNativeObject.H = z;
        return z;
    }

    public static MapsEngine a(Context context, OnEngineInitListener onEngineInitListener) throws Exception {
        MapsEngine b;
        synchronized (D) {
            if (E == null || v != e.EInitalized) {
                b = b(context, onEngineInitListener);
            } else {
                b(context, Error.NONE, onEngineInitListener);
                b = E;
            }
        }
        return b;
    }

    public static MapsEngine b(Context context, OnEngineInitListener onEngineInitListener) throws Exception {
        int i;
        int i2 = 0;
        String str = H;
        String str2 = "IN - s_initState=%s listener=0x%08x";
        Object[] objArr = new Object[2];
        objArr[0] = v.toString();
        if (onEngineInitListener == null) {
            i = 0;
        } else {
            i = onEngineInitListener.hashCode();
        }
        objArr[1] = Integer.valueOf(i);
        bj.a(str, str2, objArr);
        dy.a((Object) context, "Cannot initialize with a null Context");
        String str3 = new ContextWrapper(context).getFilesDir() + File.separator + ".." + File.separator + "lib/libMAPSJNI.so";
        if (new File(str3).exists()) {
            str3 = "com.here.network.NetworkProtocol";
            try {
                Class.forName("com.here.network.NetworkProtocol");
                synchronized (D) {
                    if (E == null) {
                        b(context);
                    }
                    if (!BaseNativeObject.H) {
                        bj.c(H, "MapsEngine#serviceInit some native libs are missing", new Object[0]);
                        v = e.EError;
                        b(context, aq.a(Error.MISSING_LIBRARIES, "Native libraries missing: " + em.a(o, ", ") + ". " + "Please refer to the user guide for details about proper project setup."), onEngineInitListener);
                        E = null;
                        O = null;
                        return null;
                    } else if (ApplicationContext.b().g() != Error.NONE) {
                        bj.c(H, "MapsEngine#serviceInit Init ERROR", new Object[0]);
                        b(context, ApplicationContext.b().g(), onEngineInitListener);
                        r0 = E;
                        return r0;
                    } else if (E != null && v == e.EInitializing) {
                        str3 = H;
                        String str4 = "s_initState=%s adding listener(0x%08x) to listener list.";
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = v.toString();
                        if (onEngineInitListener != null) {
                            i2 = onEngineInitListener.hashCode();
                        }
                        objArr2[1] = Integer.valueOf(i2);
                        bj.e(str3, str4, objArr2);
                        if (onEngineInitListener != null) {
                            L.add(onEngineInitListener);
                        }
                        r0 = E;
                        return r0;
                    } else if (v == e.ENotInitialized || v == e.EError || v == e.EDiskCacheLocked || v == e.EFileRW) {
                        v = e.EInitializing;
                        E = b(context);
                        List arrayList = new ArrayList();
                        if (E.c(arrayList)) {
                            str3 = MapSettings.h();
                            File file = new File(str3);
                            File file2 = new File(str3 + File.separator + "foo.bar");
                            if (!file2.delete()) {
                                bj.d(H, "ERROR: Failed to delete test file", new Object[0]);
                            }
                            if (!file.mkdirs()) {
                                bj.d(H, "ERROR: Failed to mkdirs() for the diskcache path", new Object[0]);
                            }
                            try {
                                if (!file2.createNewFile()) {
                                    bj.c(H, "ERROR: Failed to create test file, checking for existence", new Object[0]);
                                }
                                if (file2.delete()) {
                                    i = 1;
                                } else {
                                    bj.c(H, "ERROR: Problem removing test file from disk cache", new Object[0]);
                                    i = 1;
                                }
                            } catch (IOException e) {
                                bj.c(H, "ERROR: IOException trying to create test file", new Object[0]);
                                if (file2.delete()) {
                                    i = 0;
                                } else {
                                    bj.c(H, "ERROR: Problem removing test file from disk cache", new Object[0]);
                                    i = 0;
                                }
                            } catch (Throwable th) {
                                if (!file2.delete()) {
                                    bj.c(H, "ERROR: Problem removing test file from disk cache", new Object[0]);
                                }
                            }
                            if (!file.exists() || r0 == 0) {
                                E.a(F);
                                b(context, aq.a(Error.FILE_RW_ERROR, "SDK cache is missing."), onEngineInitListener);
                                E = null;
                                O = null;
                                return null;
                            }
                            MapsEngine mapsEngine;
                            int a;
                            boolean z;
                            if (l == null) {
                                l = E.getServerUrl(m.MAP_DATA.a(), !MapServiceClient.b);
                            }
                            if (i == null) {
                                mapsEngine = E;
                                a = m.SATELLITE.a();
                                if (MapServiceClient.b) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                i = mapsEngine.getServerUrl(a, z);
                            }
                            if (j == null) {
                                mapsEngine = E;
                                a = m.TERRAIN.a();
                                if (MapServiceClient.b) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                j = mapsEngine.getServerUrl(a, z);
                            }
                            if (k == null) {
                                mapsEngine = E;
                                a = m.STREET_LEVEL_IMAGERY.a();
                                if (MapServiceClient.b) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                k = mapsEngine.getServerUrl(a, z);
                            }
                            E.d(MapSettings.d());
                            if (f().booleanValue()) {
                                E.b(onEngineInitListener);
                            } else {
                                try {
                                    short s;
                                    O = onEngineInitListener;
                                    r0 = E;
                                    Context context2 = F;
                                    str = MapSettings.d();
                                    str2 = l;
                                    String str5 = i;
                                    String str6 = j;
                                    String m = m();
                                    if (x == c.UNKNOWN) {
                                        s = (short) 0;
                                    } else {
                                        s = x.g;
                                    }
                                    r0.a(context2, str, str2, str5, str6, m, s);
                                } catch (Throwable e2) {
                                    bj.b(H, "Map Service was NOT FOUND. Return error.", new Object[0]);
                                    b(context, aq.a(Error.MISSING_SERVICE, MapService.class.getSimpleName() + " is missing from the AndroidManifest.xml file. " + " Please refer to the user guide for details about " + "proper project setup.", e2), onEngineInitListener);
                                    E = null;
                                    O = null;
                                } catch (Throwable e22) {
                                    bj.c(H, "singleton.connectService() failed.  Error: %s", new Object[]{e22.getLocalizedMessage()});
                                    b(context, aq.a(Error.UNKNOWN, "Unknown error occurred.", e22), onEngineInitListener);
                                    E = null;
                                    O = null;
                                }
                            }
                        } else {
                            b(context, aq.a(Error.MISSING_PERMISSION, "Missing permissions: " + em.a(arrayList, ", ") + ". " + "Please refer to the user guide for details about proper project setup."), onEngineInitListener);
                            O = null;
                            r0 = E;
                            return r0;
                        }
                    } else {
                        throw new Exception("Cannot initialize the engine twice");
                    }
                }
            } catch (Throwable e222) {
                if (onEngineInitListener != null) {
                    bj.c(H, "MapsEngine#serviceInit NOT FOUND:", new Object[]{"com.here.network.NetworkProtocol"});
                    b(context, aq.a(Error.MISSING_LIBRARIES, "com.here.network.NetworkProtocol class not found.", e222), onEngineInitListener);
                }
                return E;
            }
        }
        if (onEngineInitListener != null) {
            bj.c(H, "MapsEngine#serviceInit NOT FOUND:", new Object[]{str3});
            b(context, aq.a(Error.MISSING_LIBRARIES, "Library " + str3 + " not found."), onEngineInitListener);
        }
        return E;
        str3 = H;
        str4 = "OUT - s_initState=%s listener=0x%08x";
        objArr2 = new Object[2];
        objArr2[0] = v.toString();
        if (onEngineInitListener != null) {
            i2 = onEngineInitListener.hashCode();
        }
        objArr2[1] = Integer.valueOf(i2);
        bj.a(str3, str4, objArr2);
        return E;
    }

    private void b(OnEngineInitListener onEngineInitListener) {
        new j(this, this, onEngineInitListener, false).execute(new Void[0]);
    }

    protected void a(ComponentName componentName, IBinder iBinder) {
        super.a(componentName, iBinder);
        new j(this, this, O, true).execute(new Void[0]);
    }

    protected void a(ComponentName componentName) {
        super.a(componentName);
    }

    private MapsEngine(Context context) {
        if (v != e.ENotInitialized) {
            throw new RuntimeException("Cannot initialize the engine twice");
        }
        F = context.getApplicationContext();
        if (ApplicationContext.a(F) == null) {
            throw new RuntimeException("Map Engine Creation Failed");
        }
        ac();
    }

    private void e(Context context) {
        i iVar = new i(context);
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            destroyMapsEngineNative();
        }
        super.finalize();
    }

    @OnlineNative
    private void onNativeCrash() {
        new RuntimeException("A crash in native code occurred").printStackTrace();
        Log.e("NATIVE CRASH", "Call stacks ---->");
        for (Entry entry : Thread.getAllStackTraces().entrySet()) {
            System.err.println("\n\nThread:" + ((Thread) entry.getKey()).getName());
            for (Object obj : (StackTraceElement[]) entry.getValue()) {
                System.err.println("\tat " + obj);
            }
        }
        Log.e("NATIVE CRASH", "Call stacks <----");
    }

    static MapsEngine b(Context context) {
        bj.a(H, "IN", new Object[0]);
        synchronized (D) {
            bj.a(H, "->> synchronized() ...", new Object[0]);
            if (E == null && d(context)) {
                E = new MapsEngine(context);
            }
            bj.a(H, "<<- synchronized() ...", new Object[0]);
        }
        String str = H;
        String str2 = "OUT - s_instance %s";
        Object[] objArr = new Object[1];
        objArr[0] = E == null ? "NULL" : "OK";
        bj.a(str, str2, objArr);
        return E;
    }

    static MapsEngine c() throws Exception {
        bj.a(H, "IN", new Object[0]);
        synchronized (D) {
            bj.a(H, "->> synchronized() ...", new Object[0]);
            if (E == null) {
                throw new Exception("MapsEngine singleton cannot be used unless it has been initalized");
            }
            bj.a(H, "<<- synchronized() ...", new Object[0]);
        }
        String str = H;
        String str2 = "OUT - s_instance %s";
        Object[] objArr = new Object[1];
        objArr[0] = E == null ? "NULL" : "OK";
        bj.a(str, str2, objArr);
        return E;
    }

    public static de d() {
        return t;
    }

    private boolean f(Context context) {
        boolean z;
        File file = new File(MapSettings.c());
        if (file.exists()) {
            z = true;
        } else {
            z = file.mkdirs();
        }
        for (String file2 : this.p) {
            if (!new File(file, file2).exists()) {
                z = false;
                break;
            }
        }
        File file3 = new File(MapSettings.d());
        int i = 1;
        for (String file4 : this.q) {
            File file5 = new File(file3, file4);
            if (!file5.exists()) {
                i = false;
            } else if (z) {
                continue;
            } else {
                try {
                    MapsEngineResourceManager.a(file5);
                } catch (IOException e) {
                    bj.f(H, "ERROR: deleting dir: %s: %s", new Object[]{file5.getName(), e.getLocalizedMessage()});
                    return false;
                }
            }
        }
        if (!z || r3 == 0) {
            if (!file.canWrite()) {
                bj.c(H, "Cannot write to directory: %s", new Object[]{file.getAbsolutePath()});
                return false;
            } else if (MapsEngineResourceManager.a(context, file.getAbsolutePath())) {
                SupplementaryResourceManager.a(F, file.getAbsolutePath());
            } else {
                bj.c(H, "Failed to deploy resources into location: %s", new Object[]{file.getAbsolutePath()});
                return false;
            }
        }
        return true;
    }

    @Internal
    public static Context e() {
        return F;
    }

    static Boolean f() {
        return Boolean.valueOf(isCacheTrimmingOnNative());
    }

    private void a(List<Integer> list) {
        b((List) list);
        if (list.isEmpty()) {
            Integer c = c(((TelephonyManager) F.getSystemService("phone")).getSimOperator());
            if (c != null) {
                list.add(c);
            }
        }
        if (n != -1) {
            list.add(0, Integer.valueOf(n));
        }
    }

    private Integer c(String str) {
        if (str == null || str.length() < 3) {
            return null;
        }
        return Integer.valueOf(Integer.parseInt(str.substring(0, 3)));
    }

    private List<Integer> b(List<Integer> list) {
        try {
            Class cls = Class.forName("android.telephony.MSimTelephonyManager");
            Object systemService = F.getSystemService((String) Context.class.getDeclaredField("MSIM_TELEPHONY_SERVICE").get(F));
            Method declaredMethod = cls.getDeclaredMethod("getSimOperator", new Class[]{Integer.TYPE});
            for (int i = 0; i < 2; i++) {
                Integer c = c((String) declaredMethod.invoke(systemService, new Object[]{Integer.valueOf(i)}));
                if (c != null && Collections.binarySearch(list, c) < 0) {
                    list.add(c);
                    Collections.sort(list);
                }
            }
        } catch (ClassNotFoundException e) {
            bj.e(H, "Couldn't find the MSimTelephonyManager class on this device", new Object[0]);
        } catch (NoSuchFieldException e2) {
            bj.c(H, "Couldn't find the MSIM_TELEPHONY_SERVICE field from Context: %s", new Object[]{e2.getLocalizedMessage()});
        } catch (IllegalArgumentException e3) {
            bj.c(H, "Illegal argument for getSystemService: %s", new Object[]{e3.getLocalizedMessage()});
        } catch (IllegalAccessException e4) {
            bj.c(H, "Illegal access for getSystemService: %s", new Object[]{e4.getLocalizedMessage()});
        } catch (NoSuchMethodException e5) {
            bj.c(H, "Couldn't find the getSimOperator method: %s", new Object[]{e5.getLocalizedMessage()});
        } catch (InvocationTargetException e6) {
            bj.c(H, "Failed to invoke the getSimOperator method: %s", new Object[]{e6.getLocalizedMessage()});
        } catch (Throwable th) {
            bj.c(H, "Failed to invoke the getSimOperator method for unknown reason: %s", new Object[]{th.getLocalizedMessage()});
        }
        return list;
    }

    private void d(String str) {
        if (w == d.CHINA) {
            V();
        } else if (w == d.INTERNATIONAL) {
            e("");
        } else if (isSouthKoreaEndpointsEnabled()) {
            X();
        } else {
            String mapDataCountryCode = getMapDataCountryCode(str);
            if (mapDataCountryCode == null || mapDataCountryCode.isEmpty()) {
                List<Integer> arrayList = new ArrayList();
                a((List) arrayList);
                if (arrayList.isEmpty()) {
                    try {
                        mapDataCountryCode = Locale.getDefault().getISO3Country();
                    } catch (Exception e) {
                    }
                } else {
                    int[] iArr = new int[arrayList.size()];
                    int i = 0;
                    for (Integer intValue : arrayList) {
                        int i2 = i + 1;
                        iArr[i] = intValue.intValue();
                        i = i2;
                    }
                    mapDataCountryCode = (m && containsChinaMcc(iArr)) ? "CHN" : containsIndiaMcc(iArr) ? "IND" : getMccCountryCode(((Integer) arrayList.get(0)).intValue());
                }
            }
            if (mapDataCountryCode.equalsIgnoreCase("CHN")) {
                V();
            } else if (mapDataCountryCode.equalsIgnoreCase("IND")) {
                W();
            } else {
                e(mapDataCountryCode);
            }
        }
    }

    private void e(String str) {
        x = c.INTERNATIONAL;
        y = str;
    }

    private void V() {
        e("CHN");
    }

    private void W() {
        x = c.INDIA;
        y = "IND";
    }

    private void X() {
        x = c.KOREA;
        y = "KOR";
    }

    public static String g() {
        return y;
    }

    public static String h() {
        return E.getServerUrl(m.HISTORICAL_TRAFFIC_TILE.a(), !MapServiceClient.b);
    }

    public static String i() {
        if (!J.get()) {
            I = E.getServerUrl(m.PLACES.a(), !MapServiceClient.b);
        }
        return I;
    }

    public static String j() {
        return E.getServerUrl(m.GEOLOCATION.a(), !MapServiceClient.b);
    }

    public static String k() {
        return E.getServerUrl(m.REVERSE_GEOLOCATION.a(), !MapServiceClient.b);
    }

    public static String l() {
        return E.getServerUrl(m.CUSTOM_LOCATION.a(), !MapServiceClient.b);
    }

    public static String m() {
        return k;
    }

    public static String n() {
        return E.getServerUrl(m.STREET_LEVEL_IMAGERY_COVERAGE_TILES.a(), !MapServiceClient.b);
    }

    public static String o() {
        return E.getServerUrl(m.STREET_LEVEL_IMAGERY_REPORTING.a(), !MapServiceClient.b);
    }

    public static String p() {
        return E.getServerUrl(m.STREET_LEVEL_IMAGERY_REPORTING_QA.a(), !MapServiceClient.b);
    }

    public static String q() {
        return E.getServerUrl(m.VENUE3D_AUTH.a(), !MapServiceClient.b);
    }

    public static String r() {
        return E.getServerUrl(m.VENUE3D_AUTH_QA.a(), !MapServiceClient.b);
    }

    public static String s() {
        return E.getServerUrl(m.PUBLIC_TRANSPORT_TIMETABLE_ROUTING.a(), !MapServiceClient.b);
    }

    public void t() {
        if (P.get() != 0) {
            if (P.decrementAndGet() == 0) {
                y();
            }
            bj.e(H, "resume counter value = " + P.get(), new Object[0]);
        }
    }

    public void u() {
        if (P.incrementAndGet() == 1) {
            z();
            if (!f().booleanValue()) {
                if (this.r != null) {
                    a(true);
                } else {
                    a(false);
                }
            }
        }
        bj.e(H, "resume counter value = " + P.get(), new Object[0]);
    }

    public void v() {
        t();
    }

    public void w() {
        u();
    }

    public int x() {
        return P.get();
    }

    public static void y() {
        cc.a();
    }

    public static void z() {
        cc.b();
    }

    public void a(h hVar) {
        this.M.add(new WeakReference(hVar));
    }

    public void a(b bVar) {
        this.R.addIfAbsent(bVar);
    }

    @Internal
    public void a(f fVar) {
        this.S.addIfAbsent(fVar);
    }

    @Internal
    public void b(f fVar) {
        this.S.remove(fVar);
    }

    public cc A() {
        return this.z;
    }

    public static void b(boolean z) {
        bj.a(H, "online: %s", new Object[]{Boolean.valueOf(z)});
        N = z;
        try {
            MapsEngine c = c();
            if (c != null && v == e.EInitalized) {
                c.d(z);
            }
        } catch (Exception e) {
        }
    }

    boolean B() {
        return !N;
    }

    private boolean d(boolean z) {
        try {
            Iterator it = this.S.iterator();
            while (it.hasNext()) {
                ((f) it.next()).a(z);
            }
        } catch (Exception e) {
            bj.c(H, "onForcedOnlineChanged exception", new Object[0]);
        }
        if (!z) {
            if (this.r != null) {
                this.r.a();
                this.r = null;
            }
            setOnlineNative(false, false);
            if (!(f().booleanValue() || a(false))) {
                bj.c(H, "Setting service offline Failed!", new Object[0]);
            }
        } else if (this.r == null) {
            try {
                this.r = new a(F);
                if (!(f().booleanValue() || a(true))) {
                    bj.c(H, "Setting service online Failed!", new Object[0]);
                }
            } catch (Exception e2) {
                bj.c(H, "Exception occured - %s.", new Object[]{e2.getLocalizedMessage()});
                return false;
            }
        }
        return true;
    }

    public static boolean C() {
        boolean z = false;
        try {
            MapsEngine c = c();
            if (c != null && v == e.EInitalized) {
                z = c.isOnline();
            }
        } catch (Exception e) {
        }
        return z;
    }

    synchronized void D() {
        reloadMapModelEngineNative();
    }

    public synchronized void E() {
        getMapVersionNative();
    }

    @OnlineNative
    private void onMapVersion(final String str, final boolean z) {
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            Iterator it = this.T.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a(str, z);
            }
            return;
        }
        ez.a(new Runnable(this) {
            final /* synthetic */ MapsEngine c;

            public void run() {
                Iterator it = this.c.T.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).a(str, z);
                }
            }
        });
    }

    public synchronized void F() {
        getCompatibleMapVersionsNative();
    }

    @HybridNative
    private void onCompatibleMapVersions(final String[] strArr, final boolean z) {
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            Iterator it = this.T.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a(strArr, z);
            }
            return;
        }
        ez.a(new Runnable(this) {
            final /* synthetic */ MapsEngine c;

            public void run() {
                Iterator it = this.c.T.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).a(strArr, z);
                }
            }
        });
    }

    public synchronized void a(String str) {
        updateMapToVersionNative(str);
    }

    @HybridNative
    private void onUpdateToVersionProgress(final int i) {
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            Iterator it = this.T.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a(i);
            }
            return;
        }
        ez.a(new Runnable(this) {
            final /* synthetic */ MapsEngine b;

            public void run() {
                Iterator it = this.b.T.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).a(i);
                }
            }
        });
    }

    @HybridNative
    private void onUpdateToVersionCompleted(final String str, final int i) {
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            Iterator it = this.T.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a(str, i);
            }
            return;
        }
        ez.a(new Runnable(this) {
            final /* synthetic */ MapsEngine c;

            public void run() {
                Iterator it = this.c.T.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).a(str, i);
                }
            }
        });
    }

    public boolean G() {
        bj.e(H, "IN", new Object[0]);
        boolean continueMapInstallationNative = continueMapInstallationNative();
        String str = H;
        String str2 = "OUT - returns %s";
        Object[] objArr = new Object[1];
        objArr[0] = continueMapInstallationNative ? "true" : dji.pilot.phonecamera.h.e;
        bj.e(str, str2, objArr);
        return continueMapInstallationNative;
    }

    @HybridNative
    private void onODMLSelection(MapPackageSelection mapPackageSelection, String str, boolean z, boolean z2) {
        bj.e(H, "onODMLSelection:" + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + z + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + z2, new Object[0]);
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            Iterator it = this.T.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a(mapPackageSelection, str, z, z2);
            }
            return;
        }
        final MapPackageSelection mapPackageSelection2 = mapPackageSelection;
        final String str2 = str;
        final boolean z3 = z;
        final boolean z4 = z2;
        ez.a(new Runnable(this) {
            final /* synthetic */ MapsEngine e;

            public void run() {
                Iterator it = this.e.T.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).a(mapPackageSelection2, str2, z3, z4);
                }
            }
        });
    }

    @HybridNative
    private void onInstallSelection(final MapPackageSelection mapPackageSelection) {
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            Iterator it = this.T.iterator();
            while (it.hasNext()) {
                ((k) it.next()).b(mapPackageSelection);
            }
            return;
        }
        ez.a(new Runnable(this) {
            final /* synthetic */ MapsEngine b;

            public void run() {
                Iterator it = this.b.T.iterator();
                while (it.hasNext()) {
                    ((k) it.next()).b(mapPackageSelection);
                }
            }
        });
    }

    @HybridNative
    private void onInstallationSize(long j, long j2) {
        bj.e(H, "diskSize=%d networkSize=%d", new Object[]{Long.valueOf(j), Long.valueOf(j2)});
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            Iterator it = this.T.iterator();
            while (it.hasNext()) {
                ((k) it.next()).a(j, j2);
            }
        } else {
            final long j3 = j;
            final long j4 = j2;
            ez.a(new Runnable(this) {
                final /* synthetic */ MapsEngine c;

                public void run() {
                    Iterator it = this.c.T.iterator();
                    while (it.hasNext()) {
                        ((k) it.next()).a(j3, j4);
                    }
                }
            });
        }
        G();
    }

    private static void g(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            G = i != 0;
        } catch (NameNotFoundException e) {
            G = false;
        }
        String str = "MapsEngine";
        String str2 = "Debug messages are %s";
        Object[] objArr = new Object[1];
        objArr[0] = G ? "ENABLED" : "DISABLED";
        bj.d(str, str2, objArr);
    }

    static boolean H() {
        return G;
    }

    public void a(k kVar) {
        this.T.addIfAbsent(kVar);
    }

    public void b(k kVar) {
        this.T.remove(kVar);
    }

    public void I() {
        a(Locale.getDefault());
    }

    public void a(Locale locale) {
        String language;
        this.s = locale;
        if (locale.getCountry().isEmpty()) {
            language = locale.getLanguage();
        } else {
            language = locale.getLanguage() + "-" + locale.getCountry();
        }
        setLocaleLanguageNative(language);
    }

    private Locale Y() {
        return this.s;
    }

    private static void b(Context context, final Error error, final OnEngineInitListener onEngineInitListener) {
        bj.a(H, "error: %s", new Object[]{error});
        if (onEngineInitListener != null || !L.isEmpty()) {
            new Handler(context.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (onEngineInitListener != null) {
                        onEngineInitListener.onEngineInitializationCompleted(error);
                    }
                    Iterator it = MapsEngine.L.iterator();
                    while (it.hasNext()) {
                        ((OnEngineInitListener) it.next()).onEngineInitializationCompleted(error);
                    }
                    MapsEngine.L.clear();
                }
            });
        }
    }

    private boolean c(List<String> list) {
        List<String> arrayList = new ArrayList();
        arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        arrayList.add("android.permission.ACCESS_NETWORK_STATE");
        arrayList.add("android.permission.ACCESS_WIFI_STATE");
        arrayList.add("android.permission.INTERNET");
        for (String str : arrayList) {
            if (F.checkCallingOrSelfPermission(str) != 0) {
                list.add(str);
            }
        }
        return list.size() == 0;
    }

    public void a(OnMapDownloadListener onMapDownloadListener) {
        if (this.z != null) {
            if (this.A == null) {
                this.A = new CopyOnWriteArrayList();
            }
            if (onMapDownloadListener != null) {
                this.A.addIfAbsent(onMapDownloadListener);
                if (!this.B) {
                    this.C = false;
                    this.z.a(this.X);
                    this.B = true;
                }
            }
        }
    }

    public void b(OnMapDownloadListener onMapDownloadListener) {
        if (this.z != null && this.A != null && onMapDownloadListener != null) {
            this.A.remove(onMapDownloadListener);
            if (this.A.size() == 0) {
                this.z.b(this.X);
                this.B = false;
                this.C = false;
            }
        }
    }

    private void Z() {
        Iterator it = this.A.iterator();
        while (it.hasNext()) {
            ((OnMapDownloadListener) it.next()).onMapDataDownloadStart();
        }
    }

    private void aa() {
        Iterator it = this.A.iterator();
        while (it.hasNext()) {
            ((OnMapDownloadListener) it.next()).onMapDataDownloadInProgress();
        }
    }

    private void ab() {
        Iterator it = this.A.iterator();
        while (it.hasNext()) {
            ((OnMapDownloadListener) it.next()).onMapDataDownloadEnd();
        }
    }

    public static boolean J() {
        return u;
    }

    public static void c(boolean z) {
        u = z;
    }

    public boolean K() {
        return Y;
    }

    private void ac() {
        String property = System.getProperty("java.vm.version");
        int indexOf = property.indexOf(46);
        if (indexOf >= 1) {
            try {
                int parseInt = Integer.parseInt(property.substring(0, indexOf));
                if (parseInt >= 0 && parseInt < 2) {
                    Y = true;
                }
            } catch (Exception e) {
            }
        }
    }
}
