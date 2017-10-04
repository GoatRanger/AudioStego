package com.here.android.mpa.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ConnectionInfoImpl;
import com.nokia.maps.MapServiceClient;
import com.nokia.maps.MapSettings;
import com.nokia.maps.az;
import com.nokia.maps.ba;
import com.nokia.maps.bj;
import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class MapService extends Service {
    private static Context i = null;
    private static final String[] j = new String[]{"gnustl_shared", "crypto_here", "ssl_here", "NuanceVocalizer", "os_adaptation.context", "os_adaptation.network", "MAPSJNI"};
    private static boolean k = true;
    MemoryFile a;
    Semaphore b;
    long c;
    FileDescriptor d;
    protected int e;
    Semaphore f;
    private boolean g;
    private a h;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private short q;
    private boolean r;
    private final RemoteCallbackList<az> s;
    private final com.nokia.maps.ay.a t;

    private static class a extends BroadcastReceiver {
        private ConnectivityManager a = ((ConnectivityManager) MapService.i.getSystemService("connectivity"));
        private HandlerThread b = new HandlerThread("connection_handler");

        public a() throws Exception {
            this.b.start();
            Handler handler = new Handler(this.b.getLooper());
            MapService.i.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"), "android.permission.CHANGE_NETWORK_STATE", handler);
            b();
        }

        public void onReceive(Context context, Intent intent) {
            try {
                b();
            } catch (Exception e) {
                bj.c("MapService", "Exception: %s", e.getLocalizedMessage());
            }
        }

        public void a() {
            MapService.i.unregisterReceiver(this);
            this.b.quit();
        }

        private void b() throws Exception {
            try {
                NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    bj.e("MapService", "Current State is: " + activeNetworkInfo.getDetailedState().toString(), new Object[0]);
                }
                if (activeNetworkInfo == null || activeNetworkInfo.getDetailedState() != DetailedState.CONNECTED) {
                    MapServiceClient.setMapServiceOnline(false);
                    bj.e("MapService", "Current State is offline ", new Object[0]);
                    return;
                }
                MapServiceClient.setMapServiceOnline(false);
                MapServiceClient.setMapServiceOnline(true);
                bj.e("MapService", "Current State is online ", new Object[0]);
                if (VERSION.SDK_INT >= 11) {
                    List select = ProxySelector.getDefault().select(new URI("http://www.here.com"));
                    if (select.size() > 0) {
                        Proxy proxy = (Proxy) select.get(0);
                        if (proxy.type() == Type.HTTP) {
                            MapServiceClient.setMapServiceProxy(proxy.address().toString());
                            return;
                        } else {
                            MapServiceClient.setMapServiceProxy("");
                            return;
                        }
                    }
                    MapServiceClient.setMapServiceProxy("");
                }
            } catch (Throwable e) {
                bj.c("MapService", "Exception occurred when calling ConnectivityManager.getActiveNetworkInfo().  (%s)", e.getLocalizedMessage());
                throw new Exception(e);
            }
        }
    }

    public MapService() {
        this.a = null;
        this.b = null;
        this.c = -1;
        this.d = null;
        this.g = false;
        this.h = null;
        this.e = 0;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.f = null;
        this.r = false;
        this.s = new RemoteCallbackList();
        this.t = new com.nokia.maps.ay.a(this) {
            final /* synthetic */ MapService a;
            private a b = null;
            private Method c = null;
            private byte[] d = null;
            private boolean e = false;
            private b f = null;

            class a implements DeathRecipient {
                final /* synthetic */ AnonymousClass1 a;
                private IBinder b;

                public a(AnonymousClass1 anonymousClass1, IBinder iBinder) throws RemoteException {
                    this.a = anonymousClass1;
                    this.b = iBinder;
                    this.b.linkToDeath(this, 0);
                }

                public void a() {
                    this.b.unlinkToDeath(this, 0);
                }

                public void binderDied() {
                    if (this.a.a.c != -1) {
                        this.a.a.b.release();
                        this.a.a.c = -1;
                    }
                }
            }

            class b implements DeathRecipient {
                final /* synthetic */ AnonymousClass1 a;
                private IBinder b;

                public b(AnonymousClass1 anonymousClass1, IBinder iBinder) throws RemoteException {
                    this.a = anonymousClass1;
                    this.b = iBinder;
                    this.b.linkToDeath(this, 0);
                }

                public void a() {
                    this.b.unlinkToDeath(this, 0);
                }

                public void binderDied() {
                    try {
                        this.a.a();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

            {
                this.a = r3;
            }

            public boolean a(long j) throws RemoteException {
                Boolean valueOf = Boolean.valueOf(true);
                if (j == this.a.c) {
                    try {
                        this.a.c = -1;
                        this.b.a();
                        this.b = null;
                        this.a.b.release();
                    } catch (Exception e) {
                        Exception exception = e;
                        valueOf = Boolean.valueOf(false);
                        exception.printStackTrace();
                    }
                    return valueOf.booleanValue();
                }
                throw new RemoteException();
            }

            public boolean a(long j, ba baVar) throws RemoteException {
                Boolean valueOf = Boolean.valueOf(true);
                try {
                    this.a.b.acquire();
                    this.a.c = j;
                    this.b = new a(this, baVar.asBinder());
                } catch (Exception e) {
                    Exception exception = e;
                    valueOf = Boolean.valueOf(false);
                    exception.printStackTrace();
                }
                return valueOf.booleanValue();
            }

            @TargetApi(13)
            public ParcelFileDescriptor a(int i) throws RemoteException {
                try {
                    if (this.a.a == null) {
                        this.a.a = new MemoryFile("MapsChunk", i);
                        this.a.a.writeBytes(new byte[]{(byte) 0}, 0, i - 1, 1);
                    }
                    if (this.c == null) {
                        this.c = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
                        for (Method method : MemoryFile.class.getMethods()) {
                            System.out.println(method.toString());
                        }
                    }
                    if (this.a.d == null) {
                        this.a.d = (FileDescriptor) this.c.invoke(this.a.a, new Object[0]);
                    }
                    return ParcelFileDescriptor.dup(this.a.d);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public byte[] b(int i) throws RemoteException {
                if (this.d == null) {
                    this.d = new byte[i];
                    for (int i2 = 0; i2 < i; i2++) {
                        this.d[i2] = (byte) 0;
                    }
                }
                return this.d;
            }

            public byte[] b(long j, ba baVar) throws RemoteException {
                if (a(j, baVar)) {
                    return this.d;
                }
                return null;
            }

            public boolean a(long j, byte[] bArr) throws RemoteException {
                this.d = bArr;
                return a(j);
            }

            public void a(az azVar) throws RemoteException {
                if (azVar != null) {
                    this.a.s.register(azVar);
                }
            }

            public void b(az azVar) throws RemoteException {
                if (azVar != null) {
                    this.a.s.unregister(azVar);
                }
            }

            public List<String> a(ba baVar) throws RemoteException {
                List<String> arrayList = new ArrayList();
                synchronized (this.a) {
                    int beginBroadcast = this.a.s.beginBroadcast();
                    for (int i = 0; i < beginBroadcast; i++) {
                        try {
                            String a = ((az) this.a.s.getBroadcastItem(i)).a();
                            if (a != null) {
                                arrayList.add(a);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    this.a.s.finishBroadcast();
                    this.e = true;
                    this.f = new b(this, baVar.asBinder());
                }
                return arrayList;
            }

            public void a() throws RemoteException {
                synchronized (this.a) {
                    if (this.e) {
                        this.f.a();
                        this.f = null;
                        int beginBroadcast = this.a.s.beginBroadcast();
                        for (int i = 0; i < beginBroadcast; i++) {
                            try {
                                ((az) this.a.s.getBroadcastItem(i)).b();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        this.a.s.finishBroadcast();
                        this.e = false;
                    } else {
                        throw new RemoteException();
                    }
                }
            }

            public boolean b() {
                boolean z;
                synchronized (this.a) {
                    z = this.e;
                }
                return z;
            }

            public int c() throws RemoteException {
                try {
                    int i;
                    this.a.f.acquire();
                    synchronized (this.a) {
                        if (this.a.r) {
                            i = this.a.e;
                        } else {
                            MapServiceClient.a(this.a, this.a.o, this.a.l, this.a.m, this.a.n, this.a.p, this.a.e, this.a.q, MapService.k, MapSettings.j());
                            String a = ConnectionInfoImpl.a(true);
                            if (a != null) {
                                MapServiceClient.setUniqueDeviceId(a);
                            }
                            this.a.r = true;
                            i = this.a.e;
                        }
                        MapService.i = this.a.getApplicationContext();
                    }
                    this.a.f.release();
                    return i;
                } catch (InterruptedException e) {
                    return 0;
                }
            }

            public boolean d() throws RemoteException {
                synchronized (this.a) {
                    if (this.a.g) {
                        MapServiceClient.stopServer();
                        this.a.g = false;
                        this.a.r = false;
                    }
                }
                this.a.stopSelf();
                return true;
            }

            public boolean a(boolean z) throws RemoteException {
                if (!z) {
                    this.a.h.a();
                    this.a.h = null;
                    MapServiceClient.setMapServiceOnline(false);
                } else if (this.a.h == null) {
                    try {
                        this.a.h = new a();
                    } catch (Exception e) {
                        bj.c("MapService", "Exception occured - %s.", e.getLocalizedMessage());
                        return false;
                    }
                }
                return true;
            }
        };
        this.b = new Semaphore(1);
        this.f = new Semaphore(0);
    }

    public void onCreate() {
        super.onCreate();
    }

    public boolean onUnbind() {
        return false;
    }

    public void onDestroy() {
        if (this.a != null) {
            this.a.close();
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int i3 = 3;
        synchronized (this) {
            if (intent.getBooleanExtra("nukeservice", false)) {
                boolean z = this.g;
                this.g = false;
                this.r = false;
                if (z) {
                    MapServiceClient.stopServer();
                }
                stopSelf();
                System.exit(0);
                i3 = 2;
            } else {
                if (this.g && this.o != null) {
                    String stringExtra = intent.getStringExtra("diskcachepath");
                    if (!(new File(this.o).exists() || this.o.compareTo(stringExtra) == 0)) {
                        this.f.drainPermits();
                        MapServiceClient.stopServer();
                        this.g = false;
                        this.r = false;
                    }
                }
                if (!this.g) {
                    this.l = intent.getStringExtra("mapdataserverurl");
                    this.m = intent.getStringExtra("mapsatelliteserverurl");
                    this.n = intent.getStringExtra("terrainserverurl");
                    this.o = intent.getStringExtra("diskcachepath");
                    this.p = intent.getStringExtra("sliserverurl");
                    this.q = intent.getShortExtra("mapvariant", (short) 0);
                    k = intent.getBooleanExtra("USESSL", true);
                    i3 = intent.getIntExtra("shutdownmode", 3);
                    if (this.l == null || this.l.length() == 0) {
                        this.l = "hybrid.api.here.com";
                    }
                    if (this.m == null || this.m.length() == 0) {
                        this.m = "http://1.sps.data.here.com";
                    }
                    if (this.n == null || this.n.length() == 0) {
                        this.n = "http://hterrain.mfs.data.here.com";
                    }
                    if (this.p == null || this.p.length() == 0) {
                        this.p = "sli.data.here.com";
                    }
                    if (this.o == null || this.o.length() == 0) {
                        this.o = MapSettings.a();
                    }
                    for (String str : j) {
                        try {
                            System.loadLibrary(str);
                        } catch (UnsatisfiedLinkError e) {
                            if (!(str.compareTo(j[3]) == 0 || str.compareTo(j[4]) == 0 || str.compareTo(j[5]) != 0)) {
                            }
                        } catch (Error e2) {
                            if (str.compareTo(j[3]) == 0) {
                                bj.c("MapService", "Library loaded with error:" + e2.getLocalizedMessage(), new Object[0]);
                            } else if (str.compareTo(j[4]) == 0 || str.compareTo(j[5]) == 0) {
                                bj.b("MapService", "Library loaded failed; the library may be static", new Object[0]);
                            }
                        } catch (Exception e3) {
                        }
                    }
                    ApplicationContext.b(getApplicationContext());
                    if (!new File(this.o).mkdirs()) {
                        bj.f("MapService", "Failed to mkdirs() for " + this.o, new Object[0]);
                    }
                    if (this.e == 0) {
                        this.e = new Random().nextInt(1000) + 25000;
                    }
                    this.g = true;
                    this.f.release();
                }
            }
        }
        return i3;
    }

    public IBinder onBind(Intent intent) {
        return this.t;
    }

    public void onTaskRemoved(Intent intent) {
        MapServiceClient.stopServer();
        synchronized (this) {
            this.g = false;
        }
    }
}
