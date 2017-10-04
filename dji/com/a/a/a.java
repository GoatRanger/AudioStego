package com.a.a;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.a.a.a.a.a.f;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class a {
    static final Handler a = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            int i = message.what;
            throw new AssertionError("Unknown handler message received: " + message.what);
        }
    };
    static volatile a b = null;
    private static final k d = new k();
    volatile boolean c;
    private final Application e;
    private final ExecutorService f;
    private final g g;
    private final n h;
    private final i i;
    private final a j;
    private final b k;
    private final c l;

    public static class a {
        private final Application a;
        private String b;
        private String c;
        private int d = 20;
        private long e = 30000;
        private i f;
        private String g;
        private c h;
        private ExecutorService i;
        private e j;

        public a(Context context, String str) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            } else if (com.a.a.a.b.a(context, "android.permission.INTERNET")) {
                this.a = (Application) context.getApplicationContext();
                if (this.a == null) {
                    throw new IllegalArgumentException("Application context must not be null.");
                } else if (com.a.a.a.b.a((CharSequence) str)) {
                    throw new IllegalArgumentException("writeKey must not be null or empty.");
                } else {
                    this.b = str;
                }
            } else {
                throw new IllegalArgumentException("INTERNET permission is required.");
            }
        }

        public a a(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("flushQueueSize must be greater than or equal to zero.");
            } else if (i > 250) {
                throw new IllegalArgumentException("flushQueueSize must be less than or equal to 250.");
            } else {
                this.d = i;
                return this;
            }
        }

        public a a(long j, TimeUnit timeUnit) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("timeUnit must not be null.");
            } else if (j <= 0) {
                throw new IllegalArgumentException("flushInterval must be greater than zero.");
            } else {
                this.e = timeUnit.toMillis(j);
                return this;
            }
        }

        public a a(i iVar) {
            if (iVar == null) {
                throw new IllegalArgumentException("defaultOptions must not be null.");
            }
            this.f = new i();
            for (Entry entry : iVar.a().entrySet()) {
                if (entry.getValue() instanceof Boolean) {
                    this.f.a((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                } else {
                    this.f.a((String) entry.getKey(), true);
                }
            }
            return this;
        }

        public a a(String str) {
            this.c = str;
            return this;
        }

        public a a(c cVar) {
            if (cVar == null) {
                throw new IllegalArgumentException("LogLevel must not be null.");
            }
            this.h = cVar;
            return this;
        }

        public a a(e eVar) {
            if (eVar == null) {
                throw new IllegalArgumentException("ConnectionFactory must not be null.");
            }
            this.j = eVar;
            return this;
        }

        public a a() {
            if (this.f == null) {
                this.f = new i();
            }
            if (this.h == null) {
                this.h = c.NONE;
            }
            if (com.a.a.a.b.a(this.g)) {
                this.g = this.b;
            }
            if (this.i == null) {
                this.i = new com.a.a.a.b.a();
            }
            if (this.j == null) {
                this.j = new e();
            }
            final n nVar = new n();
            final c cVar = c.a;
            final d dVar = new d(this.a, this.b, this.j);
            a anonymousClass1 = new a(this) {
                final /* synthetic */ a d;

                public g a(a aVar) {
                    return g.a(aVar, cVar, dVar, this.d.i, nVar, this.d.g, this.d.e, this.d.d);
                }
            };
            a aVar = new a(this.a, cVar, this.g);
            if (!aVar.b() || aVar.a() == null) {
                if (this.h.a()) {
                    com.a.a.a.b.a("analytics-android", "Traits cache not set or is null. Creating a new one . . . .");
                }
                aVar.a(o.a());
            }
            String a = a(this.a);
            if (a != null) {
                if (this.h.a()) {
                    com.a.a.a.b.a("analytics-android", "Old V1 Session ID discovered . . . Overriding Traits cache");
                }
                o oVar = (o) aVar.a();
                oVar.b(a);
                oVar.a(a);
                aVar.c();
                aVar.a(oVar);
            } else if (this.h.a()) {
                com.a.a.a.b.a("analytics-android", "Did not find Old V1 Session ID discovered . . . leaving anonymousId and userId as they are");
            }
            b a2 = b.a(this.a, (o) aVar.a());
            a2.a(this.c);
            a2.a(this.a);
            return new a(this.a, this.i, anonymousClass1, nVar, aVar, a2, this.f, this.h);
        }

        private String a(Context context, String str) {
            if (this.h.a()) {
                com.a.a.a.b.a("analytics-android", "Old Shared Preferences File  = " + str);
            }
            return context.getSharedPreferences(str, 4).getString("session.id", null);
        }

        private String a(Context context) {
            String a = a(context, "io.segment.android." + context.getPackageName());
            if (a != null) {
                return a;
            }
            if (this.h.a()) {
                com.a.a.a.b.a("analytics-android", "Old Session Id could not be found at location : " + r1);
            }
            return a(context, "b.a.a." + context.getPackageName());
        }
    }

    public interface b {
        void a(Object obj);
    }

    public enum c {
        NONE,
        BASIC,
        INFO,
        d;

        public boolean a() {
            return this != NONE;
        }
    }

    a(Application application, ExecutorService executorService, a aVar, n nVar, a aVar2, b bVar, i iVar, c cVar) {
        this.e = application;
        this.f = executorService;
        this.h = nVar;
        this.j = aVar2;
        this.k = bVar;
        this.i = iVar;
        this.l = cVar;
        this.g = aVar.a(this);
    }

    public void a(String str, k kVar, i iVar) {
        if (com.a.a.a.b.a((CharSequence) str)) {
            throw new IllegalArgumentException("event must not be null or empty.");
        }
        if (kVar == null) {
            kVar = d;
        }
        if (iVar == null) {
            iVar = this.i;
        }
        a(new f(this.k, iVar, str, kVar));
    }

    void a(com.a.a.a.a.a.b bVar) {
        if (this.c) {
            throw new IllegalStateException("Cannot enqueue messages after client is shutdown.");
        }
        if (this.l.a()) {
            com.a.a.a.b.a("Created payload %s.", bVar);
        }
        this.g.a(bVar);
    }

    public void a() {
        if (this.c) {
            throw new IllegalStateException("Cannot enqueue messages after client is shutdown.");
        }
        this.g.c();
    }

    public b b() {
        return this.k;
    }

    public Application c() {
        return this.e;
    }

    public c d() {
        return this.l;
    }
}
