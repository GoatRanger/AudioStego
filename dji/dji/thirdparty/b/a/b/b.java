package dji.thirdparty.b.a.b;

import com.google.api.client.http.HttpStatusCodes;
import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.d;
import dji.thirdparty.b.t;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class b {
    public final ab a;
    public final ad b;

    public static class a {
        final long a;
        final ab b;
        final ad c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l = -1;

        public a(long j, ab abVar, ad adVar) {
            this.a = j;
            this.b = abVar;
            this.c = adVar;
            if (adVar != null) {
                t g = adVar.g();
                int a = g.a();
                for (int i = 0; i < a; i++) {
                    String a2 = g.a(i);
                    String b = g.b(i);
                    if ("Date".equalsIgnoreCase(a2)) {
                        this.d = f.a(b);
                        this.e = b;
                    } else if ("Expires".equalsIgnoreCase(a2)) {
                        this.h = f.a(b);
                    } else if ("Last-Modified".equalsIgnoreCase(a2)) {
                        this.f = f.a(b);
                        this.g = b;
                    } else if ("ETag".equalsIgnoreCase(a2)) {
                        this.k = b;
                    } else if ("Age".equalsIgnoreCase(a2)) {
                        this.l = c.b(b, -1);
                    } else if (j.b.equalsIgnoreCase(a2)) {
                        this.i = Long.parseLong(b);
                    } else if (j.c.equalsIgnoreCase(a2)) {
                        this.j = Long.parseLong(b);
                    }
                }
            }
        }

        public b a() {
            b b = b();
            if (b.a == null || !this.b.g().j()) {
                return b;
            }
            return new b(null, null);
        }

        private b b() {
            long j = 0;
            if (this.c == null) {
                return new b(this.b, null);
            }
            if (this.b.h() && this.c.f() == null) {
                return new b(this.b, null);
            }
            if (!b.a(this.c, this.b)) {
                return new b(this.b, null);
            }
            d g = this.b.g();
            if (g.a() || a(this.b)) {
                return new b(this.b, null);
            }
            long toMillis;
            long d = d();
            long c = c();
            if (g.c() != -1) {
                c = Math.min(c, TimeUnit.SECONDS.toMillis((long) g.c()));
            }
            if (g.i() != -1) {
                toMillis = TimeUnit.SECONDS.toMillis((long) g.i());
            } else {
                toMillis = 0;
            }
            d o = this.c.o();
            if (!(o.g() || g.h() == -1)) {
                j = TimeUnit.SECONDS.toMillis((long) g.h());
            }
            if (o.a() || d + toMillis >= r4 + c) {
                dji.thirdparty.b.ab.a f = this.b.f();
                if (this.k != null) {
                    f.a("If-None-Match", this.k);
                } else if (this.f != null) {
                    f.a("If-Modified-Since", this.g);
                } else if (this.d != null) {
                    f.a("If-Modified-Since", this.e);
                }
                ab d2 = f.d();
                if (a(d2)) {
                    return new b(d2, this.c);
                }
                return new b(d2, null);
            }
            dji.thirdparty.b.ad.a i = this.c.i();
            if (toMillis + d >= c) {
                i.b("Warning", "110 HttpURLConnection \"Response is stale\"");
            }
            if (d > 86400000 && e()) {
                i.b("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
            }
            return new b(null, i.a());
        }

        private long c() {
            d o = this.c.o();
            if (o.c() != -1) {
                return TimeUnit.SECONDS.toMillis((long) o.c());
            }
            long time;
            if (this.h != null) {
                time = this.h.getTime() - (this.d != null ? this.d.getTime() : this.j);
                if (time <= 0) {
                    time = 0;
                }
                return time;
            } else if (this.f == null || this.c.a().a().p() != null) {
                return 0;
            } else {
                time = (this.d != null ? this.d.getTime() : this.i) - this.f.getTime();
                if (time > 0) {
                    return time / 10;
                }
                return 0;
            }
        }

        private long d() {
            long j = 0;
            if (this.d != null) {
                j = Math.max(0, this.j - this.d.getTime());
            }
            if (this.l != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.l));
            }
            return (j + (this.j - this.i)) + (this.a - this.j);
        }

        private boolean e() {
            return this.c.o().c() == -1 && this.h == null;
        }

        private static boolean a(ab abVar) {
            return (abVar.a("If-Modified-Since") == null && abVar.a("If-None-Match") == null) ? false : true;
        }
    }

    private b(ab abVar, ad adVar) {
        this.a = abVar;
        this.b = adVar;
    }

    public static boolean a(ad adVar, ab abVar) {
        switch (adVar.c()) {
            case 200:
            case 203:
            case HttpStatusCodes.STATUS_CODE_NO_CONTENT /*204*/:
            case 300:
            case HttpStatusCodes.STATUS_CODE_MOVED_PERMANENTLY /*301*/:
            case q.b /*308*/:
            case 404:
            case dji.pilot.flyunlimit.a.A /*405*/:
            case dji.pilot.flyunlimit.a.w /*410*/:
            case 414:
            case FTPCodes.SYNTAX_ERROR_IN_PARAMETERS /*501*/:
                break;
            case HttpStatusCodes.STATUS_CODE_FOUND /*302*/:
            case 307:
                if (adVar.b("Expires") == null && adVar.o().c() == -1 && !adVar.o().f() && !adVar.o().e()) {
                    return false;
                }
            default:
                return false;
        }
        return (adVar.o().b() || abVar.g().b()) ? false : true;
    }
}
