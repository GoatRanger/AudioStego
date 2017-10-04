package dji.pilot2.utils;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.services.location.network.NetworkLocationApi.Options;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.thirdparty.a.c;

public class e {
    public static final boolean a = false;
    public static final String b = "DJINetworkManager";
    static final int g = 20000;
    private static final int j = 1;
    private static final int k = 2;
    private static final int l = 3;
    private static final int m = 4;
    Context c;
    r d;
    WifiConfiguration e;
    a f;
    Handler h = new Handler(new Callback(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (this.a.f != null) {
                        this.a.f.b();
                        if (this.a.h.hasMessages(3)) {
                            this.a.h.removeMessages(3);
                            break;
                        }
                    }
                    break;
                case 2:
                    if (this.a.d.d() != 3) {
                        this.a.f.b();
                        if (this.a.h.hasMessages(3)) {
                            this.a.h.removeMessages(3);
                            break;
                        }
                    }
                    this.a.g();
                    break;
                    break;
                case 3:
                    if (!ServiceManager.getInstance().isConnected()) {
                        this.a.f.c();
                        break;
                    }
                    break;
                case 4:
                    this.a.f.a();
                    break;
            }
            return false;
        }
    });
    boolean i = false;

    public interface a {
        void a();

        void b();

        void c();
    }

    public e(Context context) {
        this.c = context;
        this.d = new r(this.c);
        c.a().a(this);
    }

    public void a() {
        this.d = null;
        c.a().d(this);
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public dji.pilot.publics.widget.a a(String str, OnClickListener onClickListener, String str2, OnClickListener onClickListener2) {
        return dji.pilot.publics.widget.a.a(this.c, str, str2, this.c.getResources().getString(R.string.v2_network_wifi), onClickListener, this.c.getResources().getString(R.string.v2_network_mobile), onClickListener2);
    }

    public void b() {
        Intent intent = new Intent("android.settings.WIFI_SETTINGS");
        intent.addFlags(268435456);
        this.c.startActivity(intent);
        f();
    }

    public void c() {
        e();
        if (!DJINetWorkReceiver.c(this.c)) {
            Intent intent = new Intent("android.settings.DATA_ROAMING_SETTINGS");
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        }
    }

    public void b(a aVar) {
        this.f = aVar;
        if (DJIGlobalService.b().equals("") || DJIGlobalService.c().equals("") || this.d == null) {
            this.f.b();
            return;
        }
        this.h.sendEmptyMessageDelayed(3, Options.MIN_DESIRED_INTERVAL);
        if (this.d.d() == 0 || this.d.d() == 1) {
            this.d.a();
            this.i = true;
        } else if (this.d.d() == 3) {
            if (g()) {
                this.h.sendEmptyMessageDelayed(4, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
            } else {
                this.h.sendEmptyMessage(1);
            }
        } else if (this.d.d() == 2 || this.d.d() == 4) {
            this.h.sendEmptyMessageDelayed(2, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
        }
    }

    private boolean g() {
        this.e = this.d.a(DJIGlobalService.b(), DJIGlobalService.c(), 3);
        return h();
    }

    public String d() {
        return this.d.m();
    }

    public void e() {
        this.d.c();
    }

    public void f() {
        this.d.a();
    }

    private boolean h() {
        return this.d.b(this.e);
    }

    public void onEventMainThread(dji.pilot.publics.objects.DJINetWorkReceiver.a aVar) {
        DJILogHelper.getInstance().LOGD(b, "net event" + aVar, false, false);
        if (aVar == dji.pilot.publics.objects.DJINetWorkReceiver.a.b && this.i) {
            this.h.sendEmptyMessageDelayed(2, 500);
            this.i = false;
        }
    }
}
