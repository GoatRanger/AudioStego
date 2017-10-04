package com.nokia.maps;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build.VERSION;
import android.os.IBinder;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.ba.a;

@Online
public abstract class MapServiceClient extends a {
    static String a = "com.here.android.mpa.service.MapService";
    static boolean b = true;
    static int c = -1;
    long d = 0;
    boolean e = false;
    boolean f = false;
    protected int g = 0;
    private ay h = null;
    private ServiceConnection i = new 1(this);
    private boolean j = false;

    public static native boolean setMapServiceOnline(boolean z);

    public static native void setMapServiceProxy(String str);

    public static native void setUniqueDeviceId(String str);

    private static native void startServer(String str, String str2, String str3, String str4, String str5, int i, short s, boolean z, String[] strArr, String str6);

    public static native void stopServer();

    public MapServiceClient() {
        boolean z = false;
        if (VERSION.SDK_INT >= 13) {
            z = true;
        }
        this.f = z;
    }

    @SuppressLint({"NewApi"})
    protected void a(Context context, String str, String str2, String str3, String str4, String str5, short s) throws Exception {
        Intent intent = new Intent(a);
        intent.putExtra("mapdataserverurl", str2);
        intent.putExtra("mapsatelliteserverurl", str3);
        intent.putExtra("terrainserverurl", str4);
        intent.putExtra("diskcachepath", str);
        intent.putExtra("sliserverurl", str5);
        intent.putExtra("mapvariant", s);
        intent.putExtra("USESSL", b);
        if (c != -1) {
            intent.putExtra("shutdownmode", c);
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 20);
        if (resolveService == null) {
            throw new a();
        }
        ServiceInfo serviceInfo = resolveService.serviceInfo;
        intent.setComponent(new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name));
        context.startService(intent);
        if (!context.bindService(intent, this.i, 1)) {
            throw new Exception("Unable to start map service");
        } else if (VERSION.SDK_INT < 14 || (serviceInfo.flags & 1) == 0) {
            this.d = System.currentTimeMillis();
        } else {
            throw new RuntimeException("Service must be set to stopWithTask=false");
        }
    }

    protected void a(Context context) {
        Intent intent = new Intent(a);
        intent.putExtra("nukeservice", true);
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 20);
        if (resolveService != null) {
            ServiceInfo serviceInfo = resolveService.serviceInfo;
            intent.setComponent(new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name));
            context.startService(intent);
        }
    }

    protected void a(ComponentName componentName, IBinder iBinder) {
        this.h = ay.a.a(iBinder);
        if (this.h != null) {
            try {
                this.g = this.h.c();
            } catch (Exception e) {
            }
        }
        bj.a(MapServiceClient.class.getSimpleName(), "ComponentName=%s - %dms after start", componentName, Long.valueOf(System.currentTimeMillis() - this.d));
    }

    protected void a(ComponentName componentName) {
        this.h = null;
    }

    public boolean a(az azVar) {
        if (this.h == null) {
            return true;
        }
        try {
            this.h.a(azVar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean a() {
        return this.j;
    }

    protected boolean a(boolean z) {
        boolean z2 = false;
        if (this.h != null) {
            try {
                z2 = this.h.a(z);
            } catch (Exception e) {
            }
        }
        return z2;
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, int i, short s, boolean z, String str6) {
        startServer(str, str2, str3, str4, str5, i, s, z, dz.b(context), str6);
    }
}
