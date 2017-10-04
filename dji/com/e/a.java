package com.e;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import com.amap.api.fence.Fence;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.APSService;
import com.amap.api.location.LocationManagerBase;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.android.tpush.common.Constants;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONObject;

public class a implements LocationManagerBase {
    public static boolean r = false;
    boolean A = false;
    private int B = 0;
    private boolean C = false;
    private Context D;
    private boolean E = false;
    private boolean F = true;
    private long G = 0;
    private long H = 0;
    private long I = 0;
    private ServiceConnection J = new ServiceConnection(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.a.i = new Messenger(iBinder);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.a.i = null;
        }
    };
    private LinkedList<a> K = new LinkedList();
    private LinkedList<a> L = new LinkedList();
    private int M = 0;
    private AMapLocation N = null;
    private JSONObject O = new JSONObject();
    AMapLocationClientOption a;
    public c b;
    cp c = null;
    ArrayList<AMapLocationListener> d = new ArrayList();
    cd e;
    boolean f = false;
    public boolean g = true;
    cq h;
    Messenger i = null;
    Messenger j = null;
    Intent k = null;
    int l = 0;
    boolean m = false;
    long n = 0;
    AMapLocation o = null;
    long p = 0;
    long q = 0;
    ScheduledExecutorService s = Executors.newScheduledThreadPool(3);
    d t = null;
    b u = null;
    ScheduledFuture<Messenger> v = null;
    ScheduledFuture<?> w = null;
    Future<?> x = null;
    Callable<Messenger> y = new Callable<Messenger>(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public Messenger a() throws Exception {
            while (this.a.i == null) {
                Thread.sleep(50);
            }
            return this.a.i;
        }

        public /* synthetic */ Object call() throws Exception {
            return a();
        }
    };
    boolean z = false;

    class a {
        double a;
        double b;
        long c;
        float d;
        float e;
        int f;
        String g;
        final /* synthetic */ a h;

        a(a aVar, AMapLocation aMapLocation, int i) {
            this.h = aVar;
            this.a = aMapLocation.getLatitude();
            this.b = aMapLocation.getLongitude();
            this.c = aMapLocation.getTime();
            this.d = aMapLocation.getAccuracy();
            this.e = aMapLocation.getSpeed();
            this.f = i;
            this.g = aMapLocation.getProvider();
        }

        public boolean equals(Object obj) {
            try {
                a aVar = (a) obj;
                return this.a == aVar.a && this.b == aVar.b;
            } catch (Throwable th) {
                return false;
            }
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.a);
            stringBuffer.append(",");
            stringBuffer.append(this.b);
            stringBuffer.append(",");
            stringBuffer.append(this.d);
            stringBuffer.append(",");
            stringBuffer.append(this.c);
            stringBuffer.append(",");
            stringBuffer.append(this.e);
            stringBuffer.append(",");
            stringBuffer.append(this.f);
            stringBuffer.append(",");
            stringBuffer.append(this.g);
            return stringBuffer.toString();
        }
    }

    class b implements Runnable {
        final /* synthetic */ a a;

        b(a aVar) {
            this.a = aVar;
        }

        public void run() {
            try {
                this.a.c.a(this.a.a);
            } catch (Throwable th) {
                bc.a(th, "AMapLocationManager$GPSTask", "run");
            }
        }
    }

    public static class c extends Handler {
        a a = null;

        public c(a aVar) {
            this.a = aVar;
        }

        public c(a aVar, Looper looper) {
            super(looper);
            this.a = aVar;
        }

        public void handleMessage(Message message) {
            Throwable th;
            AMapLocation aMapLocation;
            AMapLocation aMapLocation2 = null;
            super.handleMessage(message);
            if (this.a != null) {
                Message obtain;
                switch (message.what) {
                    case 1:
                        try {
                            Bundle data = message.getData();
                            this.a.B = 0;
                            if (data != null) {
                                data.setClassLoader(AmapLoc.class.getClassLoader());
                                aMapLocation2 = bc.a((AmapLoc) data.getParcelable(n.C));
                                if (aMapLocation2 == null) {
                                    aMapLocation2 = new AMapLocation("");
                                    aMapLocation2.setErrorCode(8);
                                }
                                aMapLocation2.setProvider("lbs");
                            }
                            this.a.c(aMapLocation2);
                            return;
                        } catch (Throwable th2) {
                            bc.a(th2, "AMapLocationManager$MHandler", "handleMessage LBS_LOCATIONSUCCESS");
                            return;
                        }
                    case 2:
                        try {
                            aMapLocation = (AMapLocation) message.obj;
                            try {
                                if (aMapLocation.getErrorCode() == 0) {
                                    this.a.B = this.a.B + 1;
                                    this.a.n = br.b();
                                    this.a.m = true;
                                }
                            } catch (Throwable th3) {
                                Throwable th4 = th3;
                                aMapLocation2 = aMapLocation;
                                th2 = th4;
                                bc.a(th2, "AMapLocationManager$MHandler", "handleMessage GPS_LOCATIONSUCCESS");
                                aMapLocation = aMapLocation2;
                                if (this.a.i != null) {
                                    if (!a.r) {
                                        this.a.a(0);
                                        a.r = true;
                                    }
                                    this.a.a(7);
                                }
                                this.a.c(aMapLocation);
                                return;
                            }
                        } catch (Throwable th5) {
                            th2 = th5;
                            bc.a(th2, "AMapLocationManager$MHandler", "handleMessage GPS_LOCATIONSUCCESS");
                            aMapLocation = aMapLocation2;
                            if (this.a.i != null) {
                                if (a.r) {
                                    this.a.a(0);
                                    a.r = true;
                                }
                                this.a.a(7);
                            }
                            this.a.c(aMapLocation);
                            return;
                        }
                        try {
                            if (this.a.i != null) {
                                if (a.r) {
                                    this.a.a(0);
                                    a.r = true;
                                }
                                this.a.a(7);
                            }
                            this.a.c(aMapLocation);
                            return;
                        } catch (Throwable th22) {
                            bc.a(th22, "AMapLocationManager$MHandler", "MHandler:handleMessage GPS_LOCATIONSUCCESS:NGPS");
                            return;
                        }
                    case 5:
                        this.a.n = br.b();
                        this.a.m = true;
                        return;
                    case 100:
                        try {
                            this.a.k();
                            return;
                        } catch (Throwable th222) {
                            bc.a(th222, "AMapLocationManager$MHandler", "handleMessage FASTSKY");
                            return;
                        }
                    case 103:
                        Intent intent = (Intent) message.obj;
                        if (this.a != null) {
                            this.a.a(intent);
                            return;
                        }
                        return;
                    case 1002:
                        try {
                            this.a.a((AMapLocationListener) message.obj);
                            return;
                        } catch (Throwable th2222) {
                            bc.a(th2222, "AMapLocationManage$MHandlerr", "handleMessage SET_LISTENER");
                            return;
                        }
                    case 1003:
                        try {
                            this.a.n();
                            return;
                        } catch (Throwable th22222) {
                            bc.a(th22222, "AMapLocationManager$MHandler", "handleMessage START_LOCATION");
                            return;
                        }
                    case 1004:
                        try {
                            this.a.o();
                            return;
                        } catch (Throwable th222222) {
                            bc.a(th222222, "AMapLocationManager$MHandler", "handleMessage STOP_LOCATION");
                            return;
                        }
                    case 1005:
                        try {
                            this.a.b((AMapLocationListener) message.obj);
                            return;
                        } catch (Throwable th2222222) {
                            bc.a(th2222222, "AMapLocationManager$MHandler", "handleMessage REMOVE_LISTENER");
                            return;
                        }
                    case 1006:
                        try {
                            this.a.a((Fence) message.obj);
                            return;
                        } catch (Throwable th22222222) {
                            bc.a(th22222222, "AMapLocationManager$MHandler", "handleMessage ADD_GEOFENCE");
                            return;
                        }
                    case 1007:
                        try {
                            this.a.a((PendingIntent) message.obj);
                            return;
                        } catch (Throwable th222222222) {
                            bc.a(th222222222, "AMapLocationManager$MHandler", "handleMessage REMOVE_GEOFENCE");
                            return;
                        }
                    case 1008:
                        try {
                            obtain = Message.obtain();
                            obtain.what = 2;
                            if (this.a.i != null) {
                                this.a.l = 0;
                                this.a.i.send(obtain);
                                return;
                            }
                            a aVar = this.a;
                            aVar.l++;
                            if (this.a.l < 10) {
                                this.a.b.sendEmptyMessageDelayed(1008, 50);
                                return;
                            }
                            return;
                        } catch (Throwable th2222222222) {
                            bc.a(th2222222222, "AMapLocationManager$MHandler", "handleMessage START_SOCKET");
                            return;
                        }
                    case 1009:
                        try {
                            obtain = Message.obtain();
                            obtain.what = 3;
                            if (this.a.i != null) {
                                this.a.i.send(obtain);
                                return;
                            }
                            return;
                        } catch (Throwable th22222222222) {
                            bc.a(th22222222222, "AMapLocationManager$MHandler", "handleMessage STOP_SOCKET");
                            return;
                        }
                    case 1010:
                        try {
                            this.a.b((Fence) message.obj);
                            return;
                        } catch (Throwable th222222222222) {
                            bc.a(th222222222222, "AMapLocationManager$MHandler", "handleMessage REMOVE_GEOFENCE_ONE");
                            return;
                        }
                    case 1011:
                        try {
                            this.a.p();
                            this.a = null;
                            return;
                        } catch (Throwable th2222222222222) {
                            bc.a(th2222222222222, "AMapLocationManager$MHandler", "handleMessage DESTROY");
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    class d implements Runnable {
        final /* synthetic */ a a;

        d(a aVar) {
            this.a = aVar;
        }

        public void run() {
            try {
                if (this.a.j() || !this.a.m) {
                    if (this.a.g) {
                        this.a.g = false;
                        this.a.m();
                    }
                    if (this.a.a()) {
                        this.a.E = true;
                        this.a.a(1);
                        return;
                    }
                    return;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            } catch (Throwable th) {
                bc.a(th, "AMapLocationManager$NetWorkTask", "run");
            }
        }
    }

    public a(Context context, Intent intent) {
        this.D = context;
        this.k = intent;
        c();
        this.t = new d(this);
        this.u = new b(this);
    }

    private AMapLocation a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        this.N = aMapLocation2;
        long b = br.b();
        if (aMapLocation == null || aMapLocation.getLocationType() != 1 || this.B <= 3) {
            this.q = 0;
            this.M = 0;
            return aMapLocation2;
        }
        if (aMapLocation2.getLocationType() == 1) {
            if (b - this.p < OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) {
                if (br.a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()}) > ((((aMapLocation.getSpeed() + aMapLocation2.getSpeed()) * ((float) (aMapLocation2.getTime() - aMapLocation.getTime()))) / 2000.0f) + (2.0f * (aMapLocation.getAccuracy() + aMapLocation2.getAccuracy()))) + 3000.0f) {
                    if (this.q == 0) {
                        this.q = br.b();
                    }
                    if (b - this.q < 30000) {
                        this.C = true;
                        this.M = 1;
                        return aMapLocation;
                    }
                }
            }
            this.B = 0;
        } else if (this.L != null && this.L.isEmpty()) {
            this.K.clear();
        }
        this.q = 0;
        this.M = 0;
        return aMapLocation2;
    }

    private a a(AMapLocation aMapLocation, int i) {
        return new a(this, aMapLocation, i);
    }

    private void a(int i) {
        try {
            Message obtain = Message.obtain();
            obtain.what = i;
            Bundle bundle = new Bundle();
            bundle.putBoolean("wifiactivescan", this.a.isWifiActiveScan());
            bundle.putBoolean("isNeedAddress", this.a.isNeedAddress());
            bundle.putBoolean("isKillProcess", this.a.isKillProcess());
            bundle.putBoolean("isOffset", this.a.isOffset());
            bundle.putLong("httptimeout", this.a.getHttpTimeOut());
            bundle.putBoolean("isLocationCacheEnable", this.a.isLocationCacheEnable());
            obtain.setData(bundle);
            obtain.replyTo = this.j;
            if (this.i != null) {
                this.i.send(obtain);
            }
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "sendLocMessage");
        }
    }

    private void a(long j) {
        try {
            if (this.w == null || this.w.isCancelled()) {
                this.w = this.s.scheduleAtFixedRate(this.t, j, this.a.getInterval(), TimeUnit.MILLISECONDS);
            }
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "startNetLocationTask");
        }
    }

    private void a(PendingIntent pendingIntent) {
        if (pendingIntent != null && this.e != null) {
            this.e.a(pendingIntent);
        }
    }

    private void a(Intent intent) {
        if (intent == null) {
            try {
                intent = new Intent(this.D, APSService.class);
            } catch (Throwable th) {
                bc.a(th, "AMapLocationManager", "startServiceImpl");
                return;
            }
        }
        intent.putExtra("apiKey", AMapLocationClientOption.APIKEY);
        String e = cu.e(this.D);
        intent.putExtra(Constants.FLAG_PACKAGE_NAME, this.D.getPackageName());
        intent.putExtra("sha1AndPackage", e);
        this.D.bindService(intent, this.J, 1);
    }

    private void a(Fence fence) {
        if (fence != null && this.e != null) {
            this.e.a(fence, fence.a);
        }
    }

    private void a(AMapLocation aMapLocation) {
        if (aMapLocation.getErrorCode() == 0) {
            try {
                long time = aMapLocation.getTime();
                AMapLocation a = a(this.o, aMapLocation);
                a.setTime(time);
                d(a);
            } catch (Throwable th) {
                bc.a(th, "AMapLocationManager", "handleMessage");
            }
        }
    }

    private void a(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (this.d == null) {
            this.d = new ArrayList();
        }
        if (!this.d.contains(aMapLocationListener)) {
            this.d.add(aMapLocationListener);
        }
    }

    private void a(aq aqVar) {
        try {
            aqVar.a("api_serverSDK_130905##S128DF1572465B890OE3F7A13167KLEI##" + cu.c(this.D) + "##" + cu.f(this.D));
            this.O.put("netloc", "0");
            this.O.put("gpsstatus", "0");
            this.O.put("nbssid", "0");
            this.O.put("wait1stwifi", "0");
            this.O.put("autoup", "0");
            this.O.put("upcolmobile", 1);
            this.O.put("enablegetreq", 1);
            this.O.put("reversegeo", this.a.isNeedAddress());
            this.O.put("isOffset", this.a.isOffset());
            this.O.put("wifiactivescan", this.a.isWifiActiveScan() ? "1" : "0");
            this.O.put("httptimeout", this.a.getHttpTimeOut());
            this.O.put("isLocationCacheEnable", this.a.isLocationCacheEnable());
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "initAPSBase 2");
            return;
        }
        this.A = true;
    }

    private void a(aq aqVar, boolean z, boolean z2) {
        try {
            if (!this.A) {
                a(aqVar);
            }
            if (!z) {
                this.O.put(dji.pilot.usercenter.protocol.d.M, cu.f(this.D));
                this.O.put("User-Agent", "AMAP_Location_SDK_Android 2.5.0");
            }
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "initAPS part3");
            return;
        }
        aqVar.a(this.O);
        if (z) {
            aqVar.a(this.D);
        } else {
            aqVar.a(this.D, z2);
        }
    }

    private boolean a() {
        boolean z;
        try {
            if (!(this.v == null || this.v.isDone() || this.v.isCancelled())) {
                this.i = (Messenger) this.v.get(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL, TimeUnit.MILLISECONDS);
            }
            if (this.v != null) {
                this.v.cancel(true);
                this.v = null;
            }
        } catch (InterruptedException e) {
            if (this.v != null) {
                this.v.cancel(true);
                this.v = null;
            }
        } catch (ExecutionException e2) {
            if (this.v != null) {
                this.v.cancel(true);
                this.v = null;
            }
        } catch (TimeoutException e3) {
            if (this.v != null) {
                this.v.cancel(true);
                this.v = null;
            }
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "checkAPSManager");
            return false;
        }
        if (this.i == null) {
            Message obtain = Message.obtain();
            Bundle bundle = new Bundle();
            Parcelable amapLoc = new AmapLoc();
            amapLoc.setErrorCode(10);
            amapLoc.setLocationDetail("请检查配置文件是否配置服务");
            bundle.putParcelable(n.C, amapLoc);
            obtain.setData(bundle);
            obtain.what = 1;
            if (this.b != null) {
                this.b.sendMessage(obtain);
            }
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    private void b() {
        if (this.v == null || this.v.isCancelled()) {
            this.v = this.s.schedule(this.y, 0, TimeUnit.MILLISECONDS);
        }
    }

    private void b(Fence fence) {
        if (fence != null && this.e != null) {
            this.e.a(fence.a, fence.b);
        }
    }

    private void b(AMapLocation aMapLocation) {
        try {
            if ("gps".equals(aMapLocation.getProvider()) || j()) {
                Iterator it = this.d.iterator();
                while (it.hasNext()) {
                    try {
                        ((AMapLocationListener) it.next()).onLocationChanged(aMapLocation);
                    } catch (Throwable th) {
                    }
                }
            }
        } catch (Throwable th2) {
        }
    }

    private void b(AMapLocationListener aMapLocationListener) {
        if (!this.d.isEmpty() && this.d.contains(aMapLocationListener)) {
            this.d.remove(aMapLocationListener);
        }
        if (this.d.isEmpty()) {
            stopLocation();
        }
    }

    private void c() {
        if (Looper.myLooper() == null) {
            this.b = new c(this, this.D.getMainLooper());
        } else {
            this.b = new c(this);
        }
        d();
        b();
        this.h = cq.a(this.D);
        this.j = new Messenger(this.b);
        this.c = new cp(this.D, this.b);
        try {
            this.e = new cd(this.D);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "init");
        }
    }

    private void c(AMapLocation aMapLocation) {
        try {
            if (this.e != null) {
                this.e.a(aMapLocation);
            }
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "handlerLocation part3");
            return;
        }
        if (aMapLocation != null) {
            try {
                if (!this.F) {
                    if (aMapLocation.getErrorCode() == 0) {
                        a(aMapLocation);
                    }
                    this.p = br.b();
                    this.o = aMapLocation;
                    b(aMapLocation);
                    this.h.a(aMapLocation);
                }
            } catch (Throwable th2) {
                bc.a(th2, "AMapLocationManager", "handlerLocation part2");
                return;
            }
        }
        if (this.a.isOnceLocation()) {
            stopLocation();
        }
    }

    private void d() {
        Message obtain = Message.obtain();
        obtain.what = 103;
        obtain.obj = this.k;
        this.b.sendMessage(obtain);
    }

    private void d(AMapLocation aMapLocation) {
        a a = a(aMapLocation, this.M);
        a a2 = a(this.N, this.M);
        if (!this.C) {
            if (this.K.size() >= 5) {
                this.K.removeFirst();
            }
            this.K.add(a);
        } else if (this.M == 0) {
            this.L.add(a);
        } else {
            this.L.add(a2);
        }
        if (this.K.size() + this.L.size() >= 10) {
            this.K.addAll(this.L);
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = this.K.iterator();
            while (it.hasNext()) {
                stringBuffer.append(((a) it.next()).toString());
                stringBuffer.append("#");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            bc.b(stringBuffer.toString());
            this.K.clear();
            this.L.clear();
            this.C = false;
        }
    }

    private void e() {
        try {
            h();
            a(0);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "batterySavingLocaiton");
        }
    }

    private void f() {
        long j = 0;
        try {
            g();
            if (this.a.isGpsFirst()) {
                j = 30000;
            }
            a(j);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "hightAccuracyLocation");
        }
    }

    private void g() {
        try {
            if (!this.a.getLocationMode().equals(AMapLocationMode.Hight_Accuracy)) {
                i();
            }
            if (this.x == null || this.x.isCancelled()) {
                this.x = this.s.submit(this.u);
            }
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "deviceSensorsLocation");
        }
    }

    private void h() {
        try {
            if (this.x != null) {
                this.x.cancel(false);
                this.x = null;
            }
            this.c.a();
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "stopGPSLocationTask");
        }
    }

    private void i() {
        try {
            if (this.w != null) {
                this.w.cancel(false);
                this.w = null;
            }
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "stopNetLocationTask");
        }
    }

    private boolean j() {
        return br.b() - this.n > 10000;
    }

    private void k() {
        Object obj = 1;
        Object obj2 = null;
        try {
            if (this.D.checkCallingOrSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0) {
                obj2 = 1;
            } else if (this.D instanceof Activity) {
                int i = 1;
                obj = null;
            } else {
                obj = null;
            }
            if (obj2 != null) {
                Builder builder = new Builder(this.D);
                builder.setMessage(bo.g());
                if (!("".equals(bo.h()) || bo.h() == null)) {
                    builder.setPositiveButton(bo.h(), new OnClickListener(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.l();
                            dialogInterface.cancel();
                        }
                    });
                }
                builder.setNegativeButton(bo.i(), new OnClickListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog create = builder.create();
                if (obj != null) {
                    create.getWindow().setType(2003);
                }
                create.setCanceledOnTouchOutside(false);
                create.show();
                return;
            }
            l();
        } catch (Throwable th) {
            l();
            bc.a(th, "AMapLocationManager", "showDialog");
        }
    }

    private void l() {
        Intent intent;
        try {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.autonavi.minimap", bo.l()));
            intent.setFlags(268435456);
            intent.setData(Uri.parse(bo.j()));
            this.D.startActivity(intent);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "callAMap part2");
        }
    }

    private void m() {
        boolean z = false;
        AmapLoc amapLoc = null;
        aq aqVar = new aq();
        if (this.a.isLocationCacheEnable()) {
            a(aqVar, true, false);
            try {
                amapLoc = aqVar.h();
            } catch (Throwable th) {
                bc.a(th, "AMapLocationManager", "doAPSLocation:doFirstCacheLocate");
            }
        }
        if (amapLoc == null || !br.a(amapLoc)) {
            a(aqVar, false, false);
            try {
                amapLoc = aqVar.c(false);
                z = true;
            } catch (Throwable th2) {
                bc.a(th2, "AMapLocationManager", "doAPSLocation:doFirstNetLocate");
            }
        }
        Message obtain = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putParcelable(n.C, amapLoc);
        obtain.setData(bundle);
        obtain.what = 1;
        if (this.b != null) {
            this.b.sendMessage(obtain);
        }
        if (z) {
            try {
                a(aqVar, false, true);
                amapLoc = aqVar.c(true);
            } catch (Throwable th3) {
                bc.a(th3, "AMapLocationManager", "doAPSLocation:doFirstNetLocate 2");
            }
            if (amapLoc.getErrorCode() == 0) {
                try {
                    aqVar.a(amapLoc);
                } catch (Throwable th4) {
                    bc.a(th4, "AMapLocationManager", "doAPSLocation:doFirstAddCache");
                }
            }
        }
        aqVar.c();
    }

    private void n() {
        if (this.a == null) {
            this.a = new AMapLocationClientOption();
        }
        this.F = false;
        switch (this.a.getLocationMode()) {
            case Battery_Saving:
                e();
                return;
            case Device_Sensors:
                g();
                return;
            case Hight_Accuracy:
                f();
                return;
            default:
                return;
        }
    }

    private void o() {
        try {
            h();
            i();
            this.K.clear();
            this.L.clear();
            this.m = false;
            this.F = true;
            this.n = 0;
            this.l = 0;
            this.o = null;
            this.p = 0;
            this.C = false;
            this.M = 0;
            this.B = 0;
            this.N = null;
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "stopLocation");
        }
    }

    private void p() {
        this.g = true;
        this.E = false;
        stopLocation();
        if (this.e != null) {
            this.e.a();
        }
        if (this.J != null) {
            this.D.unbindService(this.J);
        }
        if (this.d != null) {
            this.d.clear();
            this.d = null;
        }
        if (this.v != null) {
            this.v.cancel(true);
            this.v = null;
        }
        this.s.shutdownNow();
        this.J = null;
        if (this.b != null) {
            this.b.removeCallbacksAndMessages(null);
        }
    }

    public void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            Message obtain = Message.obtain();
            Fence fence = new Fence();
            fence.b = str;
            fence.d = d;
            fence.c = d2;
            fence.e = f;
            fence.a = pendingIntent;
            fence.f = j;
            obtain.obj = fence;
            obtain.what = 1006;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "addGeoFenceAlert");
        }
    }

    public AMapLocation getLastKnownLocation() {
        AMapLocation aMapLocation = null;
        try {
            aMapLocation = this.h.a();
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "getLastKnownLocation");
        }
        return aMapLocation;
    }

    public boolean isStarted() {
        return this.E;
    }

    public void onDestroy() {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1011;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "onDestroy");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            Message obtain = Message.obtain();
            obtain.obj = pendingIntent;
            obtain.what = 1007;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "removeGeoFenceAlert 2");
        }
    }

    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        try {
            Message obtain = Message.obtain();
            Fence fence = new Fence();
            fence.b = str;
            fence.a = pendingIntent;
            obtain.obj = fence;
            obtain.what = 1010;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "removeGeoFenceAlert 1");
        }
    }

    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            try {
                throw new IllegalArgumentException("listener参数不能为null");
            } catch (Throwable th) {
                bc.a(th, "AMapLocationManager", "setLocationListener");
                return;
            }
        }
        Message obtain = Message.obtain();
        obtain.what = 1002;
        obtain.obj = aMapLocationListener;
        this.b.sendMessage(obtain);
    }

    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (!(this.F || this.a == null || this.a.getInterval() == aMapLocationClientOption.getInterval())) {
                this.a = aMapLocationClientOption.clone();
                i();
                a(0);
            }
            this.a = aMapLocationClientOption.clone();
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "setLocationOption");
        }
    }

    public void startAssistantLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1008;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "startAssistantLocation");
        }
    }

    public void startLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1003;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "startLocation");
        }
    }

    public void stopAssistantLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1009;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "stopAssistantLocation");
        }
    }

    public void stopLocation() {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1004;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "stopLocation");
        }
    }

    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            Message obtain = Message.obtain();
            obtain.what = 1005;
            obtain.obj = aMapLocationListener;
            this.b.sendMessage(obtain);
        } catch (Throwable th) {
            bc.a(th, "AMapLocationManager", "unRegisterLocationListener");
        }
    }
}
