package dji.internal.version;

import android.content.Context;
import dji.log.DJILog;
import java.util.LinkedList;
import java.util.List;

public class c {
    private static final String d = "DJIVersionController";
    private static c e = null;
    private d a;
    private e b;
    private b c;
    private List<b> f = new LinkedList();
    private String g;

    public enum a {
        a
    }

    public interface b {
        void a(String str, String str2);
    }

    public boolean a(b bVar) {
        return this.f.add(bVar);
    }

    public boolean b(b bVar) {
        return this.f.remove(bVar);
    }

    public void a() {
        this.f.clear();
    }

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (e == null) {
                e = new c();
            }
            cVar = e;
        }
        return cVar;
    }

    private c() {
    }

    public void a(Context context) {
        try {
            dji.thirdparty.a.c.a().a(this);
        } catch (Exception e) {
            DJILog.e(d, "Failed to register event, Exception is: " + e.getMessage());
        }
        this.a = new d();
        this.a.a(context);
        this.b = new e();
        this.b.a(context);
        this.c = new b();
        this.c.a(context);
    }

    public void b() {
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        if (this.c != null) {
            this.c.a();
            this.c = null;
        }
        dji.thirdparty.a.c.a().d(this);
    }

    private void a(String str, String str2) {
        for (b a : this.f) {
            a.a(str, str2);
        }
    }

    private void a(String str) {
        if (str != null || this.g != null) {
            if (this.g == null || !this.g.equals(str)) {
                a(this.g, str);
                this.g = str;
            }
        }
    }

    public e c() {
        return this.b;
    }

    public String d() {
        return this.g;
    }

    public void onEventBackgroundThread(d dVar) {
        a(this.a.b());
    }

    public void onEventBackgroundThread(e eVar) {
    }

    public void onEventBackgroundThread(b bVar) {
    }
}
