package dji.pilot.countrycode.a;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.here.odnp.config.OdnpConfigStatic;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataWiFiSetWiFiCountryCode;
import dji.pilot.fpv.control.t;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import java.util.Timer;

public class c implements d {
    private static c l = null;
    a a;
    private b b = b.getInstance();
    private DJISDKCache c = DJISDKCache.getInstance();
    private dji.sdksharedlib.b.c d = null;
    private dji.sdksharedlib.b.c e = null;
    private dji.sdksharedlib.b.c f = null;
    private String g = "";
    private String h = "";
    private dji.pilot.countrycode.model.a i = new dji.pilot.countrycode.model.a();
    private dji.pilot.countrycode.model.a j = new dji.pilot.countrycode.model.a();
    private Context k;
    private Timer m = null;
    private String n = "";

    public interface a {
        void a(String str);

        void b(String str);
    }

    public static c getInstance() {
        if (l == null) {
            synchronized (c.class) {
                if (l == null) {
                    l = new c();
                }
            }
        }
        return l;
    }

    private void c() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    private c() {
        c();
        dji.sdksharedlib.b.c.a aVar = new dji.sdksharedlib.b.c.a();
        this.f = aVar.b(e.a).a(Integer.valueOf(0)).d("InternalSerialNumber").a();
        this.c.startListeningForUpdates(this.f, this, false);
        this.d = aVar.b(e.a).a(Integer.valueOf(0)).d(e.S).a();
        this.e = aVar.b(e.a).a(Integer.valueOf(0)).d(e.R).a();
        this.c.startListeningForUpdates(this.d, this, false);
        this.c.startListeningForUpdates(this.e, this, false);
        e();
        this.h = dji.pilot.server.a.a();
        dji.sdksharedlib.d.a availableValue = this.c.getAvailableValue(this.f);
        if (!(availableValue == null || availableValue.e() == null)) {
            this.g = (String) availableValue.e();
        }
        availableValue = this.c.getAvailableValue(this.e);
        if (!(availableValue == null || availableValue.e() == null)) {
            this.i.b = ((Double) availableValue.e()).doubleValue();
        }
        availableValue = this.c.getAvailableValue(this.d);
        if (!(availableValue == null || availableValue.e() == null)) {
            this.i.a = ((Double) availableValue.e()).doubleValue();
        }
        Location e = dji.a.a.getInstance().e();
        if (e != null && !dji.pilot.countrycode.model.a.a(e.getLongitude()) && !dji.pilot.countrycode.model.a.a(e.getLatitude())) {
            this.j.a = e.getLongitude();
            this.j.b = e.getLatitude();
        }
    }

    public void onEventBackgroundThread(dji.pilot.server.b.a aVar) {
        if (aVar == dji.pilot.server.b.a.a) {
            a.a(this.k, "【国家码获取（get）】触发点：:获取token时候触发");
            f();
        }
    }

    public void onEventBackgroundThread(dji.pilot.publics.objects.DJINetWorkReceiver.a aVar) {
        if (aVar == dji.pilot.publics.objects.DJINetWorkReceiver.a.CONNECT_OK) {
            a.a(this.k, "【国家码获取（get）】触发点：:网络变化且成功联网，DJINetWorkStatusEvent = " + aVar.toString());
            f();
        }
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.equals(this.f)) {
            a.a("获取飞机FlycSn onValueChange");
            if (aVar2 != null && aVar2.e() != null) {
                String str = (String) aVar2.e();
                a.a("获取飞机FlycSn" + str);
                if (!TextUtils.isEmpty(str)) {
                    this.g = str;
                    a.a(this.k, "【国家码获取（get）】触发点：:重连飞机时触发（FlycSn onValueChange方式）");
                    f();
                }
            }
        } else if (cVar.equals(this.e)) {
            if (aVar2 != null && aVar2.e() != null && this.i.c()) {
                r0 = ((Double) aVar2.e()).doubleValue();
                if (!dji.pilot.countrycode.model.a.a(r0)) {
                    this.i.b = r0;
                    this.c.stopListeningOnKey(this.e, this);
                    synchronized (this.i) {
                        if (!this.i.a()) {
                            a.a(this.k, "【国家码获取（get）】触发点：:开机首次获取飞机GPs触发");
                            f();
                        }
                    }
                } else if (!a.d) {
                    a.d = true;
                    a.a("获取飞机GPs的Lat=" + r0 + "系统判别为空，继续监听直到不空");
                }
            }
        } else if (cVar.equals(this.d) && aVar2 != null && aVar2.e() != null && this.i.b()) {
            r0 = ((Double) aVar2.e()).doubleValue();
            if (!dji.pilot.countrycode.model.a.a(r0)) {
                this.i.a = r0;
                this.c.stopListeningOnKey(this.d, this);
                synchronized (this.i) {
                    if (!this.i.a()) {
                        a.a(this.k, "【国家码获取（get）】触发点：:开机首次获取飞机GPs触发");
                        f();
                    }
                }
            } else if (!a.e) {
                a.e = true;
                a.a("获取飞机GPs的Lng=" + r0 + "系统判别为空，继续监听直到不空");
            }
        }
    }

    private void d() {
        new Thread(new 1(this)).start();
    }

    private void e() {
        if (this.m == null) {
            this.m = new Timer();
            this.m.schedule(new 2(this), 1000, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
        }
    }

    public void a(Context context, a aVar) {
        this.a = aVar;
        if (context != null) {
            this.k = context.getApplicationContext();
        }
        a.a(this.k, "【国家码获取（get）】触发点：开机首次获取，打开app时触发（这个事件经常和手机第一次获取GPS一起发生）");
        f();
    }

    private void f() {
        this.b.a(dji.pilot.server.a.a(), this.g, this.i, this.j, new 3(this));
    }

    public String a() {
        return this.n;
    }

    private void g() {
        String a = b.getInstance().a();
        if (!TextUtils.isEmpty(a)) {
            this.n = a;
            boolean a2 = t.a(this.k);
            a.a(this.k, " DJIProductManager=" + i.getInstance().c() + "【国家码设置（set）】开始,参数" + "手机是否支持5G=" + a2 + ",国家码=" + a);
            DataWiFiSetWiFiCountryCode.getInstance().a(a).b(a).a(a2).start(new 4(this));
        } else if (this.a != null) {
            this.a.b("set CountryCode fail,countryCode.len==0");
        }
    }

    @Deprecated
    private void h() {
        dji.sdksharedlib.b.c.a aVar = new dji.sdksharedlib.b.c.a();
        aVar.b(e.a).a(Integer.valueOf(0));
        aVar.d("InternalSerialNumber");
        DJISDKCache.getInstance().getValue(aVar.a(), new 5(this));
    }

    public void b() {
        if (this.m != null) {
            this.m.cancel();
            this.m = null;
        }
        if (this.c != null) {
            this.c.stopListeningOnKey(this.f, this);
            this.c.stopListeningOnKey(this.d, this);
            this.c.stopListeningOnKey(this.e, this);
        }
    }
}
