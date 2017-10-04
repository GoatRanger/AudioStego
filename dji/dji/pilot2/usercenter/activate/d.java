package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import dji.pilot2.utils.r;
import java.util.HashMap;

public class d {
    private static d a;
    private r b;
    private Context c;
    private Object d = new Object();

    private d() {
    }

    public static d getInstance() {
        if (a == null) {
            a = new d();
        }
        return a;
    }

    public void a(Context context) {
        synchronized (this.d) {
            if (this.c == null) {
                this.c = context;
                this.b = new r(context);
            }
        }
    }

    public boolean a() {
        return this.c != null;
    }

    public int b() {
        return this.b.q();
    }

    public void c() {
        this.b.i();
    }

    public void d() {
        this.b.a();
    }

    public void e() {
        this.b.c();
    }

    public HashMap<String, WifiConfiguration> f() {
        return this.b.j();
    }

    public void a(WifiConfiguration wifiConfiguration) {
        this.b.a(wifiConfiguration);
    }

    public void a(int i) {
        this.b.b(i);
    }

    public String g() {
        return this.b.n();
    }

    public boolean h() {
        return this.b.b();
    }
}
