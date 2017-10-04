package dji.pilot.usercenter.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class c {
    private static final int a = 4096;
    private static final int b = 4097;
    private static final int c = 4098;
    private static final int d = 4099;
    private boolean e;
    private Context f;
    private dji.thirdparty.afinal.c g;
    private b h;
    private final ArrayList<c> i;

    public interface d {
        void a(String str, Object obj, int i);

        void a(String str, Object obj, int i, int i2);

        void a(String str, Object obj, boolean z);

        void a(String str, String str2, Object obj);
    }

    private static final class a extends dji.thirdparty.afinal.f.a<File> {
        public String a = null;
        public Handler b = null;

        public a(String str, Handler handler) {
            this.a = str;
            this.b = handler;
        }

        public void a(boolean z) {
            this.b.obtainMessage(4096, z ? 0 : 1, 0, this.a).sendToTarget();
        }

        public void a(long j, long j2) {
            if (j2 > j) {
                j2 = j;
            }
            this.b.obtainMessage(4097, j == 0 ? 0 : (int) ((100 * j2) / j), (int) j, this.a).sendToTarget();
        }

        public void a(Throwable th, int i, String str) {
            this.b.obtainMessage(4099, i, 0, this.a).sendToTarget();
        }

        public void a(File file) {
            this.b.obtainMessage(4098, 0, 0, this.a).sendToTarget();
        }
    }

    private static final class b extends Handler {
        private final WeakReference<c> a;

        public b(c cVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(cVar);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            c cVar = (c) this.a.get();
            if (cVar != null) {
                switch (message.what) {
                    case 4096:
                        String str = (String) message.obj;
                        if (message.arg1 != 1) {
                            z = false;
                        }
                        cVar.a(str, z);
                        return;
                    case 4097:
                        cVar.a((String) message.obj, message.arg1, message.arg2);
                        return;
                    case 4098:
                        cVar.d((String) message.obj);
                        return;
                    case 4099:
                        cVar.a((String) message.obj, message.arg1);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class c {
        public String a;
        public dji.thirdparty.afinal.f.c<File> b;
        public a c;
        public Object d;
        public String e;
        public WeakReference<d> f;

        private c() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
        }

        public boolean equals(Object obj) {
            boolean equals = super.equals(obj);
            if (equals || !(obj instanceof c)) {
                return equals;
            }
            c cVar = (c) obj;
            if (cVar.a == null || !cVar.a.equals(this.a)) {
                return equals;
            }
            return true;
        }

        public int hashCode() {
            if (this.a != null) {
                return this.a.hashCode() + 527;
            }
            return 17;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(48);
            stringBuilder.append("url[").append(this.a).append(dji.pilot.usercenter.protocol.d.H);
            return stringBuilder.toString();
        }
    }

    private static final class e {
        public static c a = new c();

        private e() {
        }
    }

    public static c getInstance() {
        return e.a;
    }

    public synchronized boolean a(Context context) {
        if (!this.e) {
            this.f = context.getApplicationContext();
            this.g = com.dji.frame.c.c.b(this.f);
            this.e = true;
        }
        return this.e;
    }

    public synchronized boolean a() {
        if (this.e) {
        }
        return !this.e;
    }

    public void a(String str, String str2, boolean z, boolean z2, Object obj, d dVar) {
        try {
            c cVar = new c();
            cVar.d = obj;
            cVar.c = new a(str, this.h);
            cVar.a = str;
            cVar.b = this.g.a(str, str2, z, z2, cVar.c);
            cVar.e = str2;
            if (dVar != null) {
                cVar.f = new WeakReference(dVar);
            }
            a(cVar);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (str != null) {
            c b = b(str);
            if (b != null) {
                b.b.h();
                b(b);
            }
        }
    }

    private c b(String str) {
        c cVar;
        synchronized (this.i) {
            for (int i = 0; i < this.i.size(); i++) {
                cVar = (c) this.i.get(i);
                if (str.equals(cVar.a)) {
                    break;
                }
            }
            cVar = null;
        }
        return cVar;
    }

    private c a(c cVar) {
        synchronized (this.i) {
            this.i.add(cVar);
        }
        return cVar;
    }

    private c c(String str) {
        c cVar;
        synchronized (this.i) {
            for (int i = 0; i < this.i.size(); i++) {
                cVar = (c) this.i.get(i);
                if (str.equals(cVar.a)) {
                    this.i.remove(i);
                    break;
                }
            }
            cVar = null;
        }
        return cVar;
    }

    private c b(c cVar) {
        synchronized (this.i) {
            this.i.remove(cVar);
        }
        return cVar;
    }

    private void d(String str) {
        if (str != null) {
            c b = b(str);
            if (b != null) {
                b(b);
                if (b.f != null) {
                    d dVar = (d) b.f.get();
                    if (dVar != null) {
                        dVar.a(str, b.e, b.d);
                    }
                }
            }
        }
    }

    private void a(String str, int i) {
        if (str != null) {
            c b = b(str);
            if (b != null) {
                b(b);
                if (b.f != null) {
                    d dVar = (d) b.f.get();
                    if (dVar != null) {
                        dVar.a(str, b.d, i);
                    }
                }
            }
        }
    }

    private void a(String str, boolean z) {
        if (str != null) {
            c b = b(str);
            if (b != null && b.f != null) {
                d dVar = (d) b.f.get();
                if (dVar != null) {
                    dVar.a(str, b.d, z);
                }
            }
        }
    }

    private void a(String str, int i, int i2) {
        if (str != null) {
            c b = b(str);
            if (b != null && b.f != null) {
                d dVar = (d) b.f.get();
                if (dVar != null) {
                    dVar.a(str, b.d, i, i2);
                }
            }
        }
    }

    private c() {
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = new ArrayList();
        this.h = new b(this);
    }
}
