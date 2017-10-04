package dji.midware.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.dji.b.b;
import com.tencent.android.tpush.common.Constants;
import dji.thirdparty.a.c;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class a {
    public static String a = null;
    private static final String b = "com.dji.aoaservice";
    private static final String c = "com.dji.aoabridge";
    private static final String d = "com.dji.aoaservice.client.connected";
    @b(a = "aoabridge.server_ip")
    private static String g = "192.168.1.101";
    @b(a = "aoabridge.enable")
    private static boolean h = false;
    @b(a = "aoabridge.is_server")
    private static boolean i = false;
    private int e = 7003;
    private int f = 7006;
    private f j;
    private e k;
    private b l;
    private c m;
    private Context n;
    private long o;
    private String p;
    private BroadcastReceiver q = new 1(this);

    public boolean a() {
        return i && h;
    }

    public boolean b() {
        return !i && c();
    }

    public boolean c() {
        return h;
    }

    public static a d() {
        return b.a;
    }

    public void a(Context context) {
        this.n = context;
        com.dji.b.a.a().a(a.class);
        if (n()) {
            h = true;
            i = true;
        }
        if (o()) {
            h = true;
            i = false;
            g = g.b();
        }
        if (m()) {
            h = true;
            i = true;
        }
        if (a != null) {
            h = true;
            i = false;
            g = a;
        }
        if (h) {
            if (i) {
                this.j = new f(this.e);
                this.j.d();
                this.k = new e(this.f);
                this.k.a();
            } else {
                this.l = new b(g, this.e);
                this.l.a();
            }
        }
        c.a().a((Object) this);
        if (n()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(d);
            context.registerReceiver(this.q, intentFilter);
        }
    }

    public void e() {
        if (this.j != null) {
            this.j.e();
        }
        if (this.l != null) {
            this.l.b();
        }
        c.a().d((Object) this);
        if (n()) {
            this.n.unregisterReceiver(this.q);
        }
    }

    private boolean m() {
        return g.a().getPackageName().equals(c);
    }

    private boolean n() {
        return g.a().getPackageName().equals(b);
    }

    private boolean o() {
        if (n()) {
            return false;
        }
        return g.a(b);
    }

    public InputStream f() {
        return this.l.c();
    }

    public OutputStream g() {
        return this.l.d();
    }

    public void h() {
        this.j.e();
        this.j.d();
        this.k.b();
        this.k.a();
    }

    public void a(byte[] bArr, int i) {
        if (this.j != null) {
            this.j.a(bArr, i);
        }
    }

    public int i() {
        return this.j.a();
    }

    public List<String> j() {
        return this.j.b();
    }

    public void onEventBackgroundThread(dji.midware.a.b.a aVar) {
        if (aVar == dji.midware.a.b.a.a) {
            this.m = new c(g, this.f);
            this.m.a();
        } else if (this.m != null) {
            this.m.b();
            this.m = null;
        }
    }

    public boolean k() {
        if (this.m != null) {
            return this.m.c();
        }
        return false;
    }

    public void a(String str) {
        this.p = str;
        this.o = System.currentTimeMillis();
        this.j.a(str);
        this.k.a(str);
    }

    public void a(boolean z, int i) {
        Intent intent = new Intent(d);
        intent.putExtra("isEvent", z);
        intent.putExtra("port", i);
        intent.putExtra(Constants.FLAG_PACKAGE_NAME, this.n.getApplicationInfo().packageName);
        this.n.sendBroadcast(intent);
    }

    public String l() {
        return this.j.c();
    }
}
