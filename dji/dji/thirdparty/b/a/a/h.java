package dji.thirdparty.b.a.a;

import com.alipay.sdk.b.c;
import com.google.api.client.http.HttpMethods;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.c.e;
import dji.thirdparty.c.f;
import dji.thirdparty.c.p;
import dji.thirdparty.c.w;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

final class h {
    private static final int a = 15;
    private static final int b = 31;
    private static final int c = 63;
    private static final int d = 127;
    private static final f[] e = new f[]{new f(f.e, ""), new f(f.b, HttpMethods.GET), new f(f.b, HttpMethods.POST), new f(f.c, d.t), new f(f.c, "/index.html"), new f(f.d, "http"), new f(f.d, com.alipay.sdk.b.b.a), new f(f.a, "200"), new f(f.a, "204"), new f(f.a, "206"), new f(f.a, "304"), new f(f.a, "400"), new f(f.a, "404"), new f(f.a, "500"), new f("accept-charset", ""), new f("accept-encoding", "gzip, deflate"), new f("accept-language", ""), new f("accept-ranges", ""), new f("accept", ""), new f("access-control-allow-origin", ""), new f("age", ""), new f("allow", ""), new f("authorization", ""), new f("cache-control", ""), new f("content-disposition", ""), new f("content-encoding", ""), new f("content-language", ""), new f("content-length", ""), new f("content-location", ""), new f("content-range", ""), new f(com.alipay.sdk.f.d.d, ""), new f("cookie", ""), new f("date", ""), new f("etag", ""), new f("expect", ""), new f(MobileRegisterActivity.RESPONSE_EXPIRES, ""), new f("from", ""), new f(c.f, ""), new f("if-match", ""), new f("if-modified-since", ""), new f("if-none-match", ""), new f("if-range", ""), new f("if-unmodified-since", ""), new f("last-modified", ""), new f("link", ""), new f(n.C, ""), new f("max-forwards", ""), new f("proxy-authenticate", ""), new f("proxy-authorization", ""), new f("range", ""), new f("referer", ""), new f("refresh", ""), new f("retry-after", ""), new f("server", ""), new f("set-cookie", ""), new f("strict-transport-security", ""), new f("transfer-encoding", ""), new f("user-agent", ""), new f("vary", ""), new f("via", ""), new f("www-authenticate", "")};
    private static final Map<f, Integer> f = c();

    static final class a {
        f[] a = new f[8];
        int b = (this.a.length - 1);
        int c = 0;
        int d = 0;
        private final List<f> e = new ArrayList();
        private final e f;
        private int g;
        private int h;

        a(int i, w wVar) {
            this.g = i;
            this.h = i;
            this.f = p.a(wVar);
        }

        int a() {
            return this.h;
        }

        void a(int i) {
            this.g = i;
            this.h = i;
            e();
        }

        private void e() {
            if (this.h >= this.d) {
                return;
            }
            if (this.h == 0) {
                f();
            } else {
                b(this.d - this.h);
            }
        }

        private void f() {
            this.e.clear();
            Arrays.fill(this.a, null);
            this.b = this.a.length - 1;
            this.c = 0;
            this.d = 0;
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                for (int length = this.a.length - 1; length >= this.b && i > 0; length--) {
                    i -= this.a[length].j;
                    this.d -= this.a[length].j;
                    this.c--;
                    i2++;
                }
                System.arraycopy(this.a, this.b + 1, this.a, (this.b + 1) + i2, this.c);
                this.b += i2;
            }
            return i2;
        }

        void b() throws IOException {
            while (!this.f.g()) {
                int j = this.f.j() & 255;
                if (j == 128) {
                    throw new IOException("index == 0");
                } else if ((j & 128) == 128) {
                    c(a(j, 127) - 1);
                } else if (j == 64) {
                    h();
                } else if ((j & 64) == 64) {
                    f(a(j, 63) - 1);
                } else if ((j & 32) == 32) {
                    this.h = a(j, 31);
                    if (this.h < 0 || this.h > this.g) {
                        throw new IOException("Invalid dynamic table size update " + this.h);
                    }
                    e();
                } else if (j == 16 || j == 0) {
                    g();
                } else {
                    e(a(j, 15) - 1);
                }
            }
        }

        public List<f> c() {
            List arrayList = new ArrayList(this.e);
            this.e.clear();
            return arrayList;
        }

        private void c(int i) throws IOException {
            if (h(i)) {
                this.e.add(h.e[i]);
                return;
            }
            int d = d(i - h.e.length);
            if (d < 0 || d > this.a.length - 1) {
                throw new IOException("Header index too large " + (i + 1));
            }
            this.e.add(this.a[d]);
        }

        private int d(int i) {
            return (this.b + 1) + i;
        }

        private void e(int i) throws IOException {
            this.e.add(new f(g(i), d()));
        }

        private void g() throws IOException {
            this.e.add(new f(h.b(d()), d()));
        }

        private void f(int i) throws IOException {
            a(-1, new f(g(i), d()));
        }

        private void h() throws IOException {
            a(-1, new f(h.b(d()), d()));
        }

        private f g(int i) {
            if (h(i)) {
                return h.e[i].h;
            }
            return this.a[d(i - h.e.length)].h;
        }

        private boolean h(int i) {
            return i >= 0 && i <= h.e.length - 1;
        }

        private void a(int i, f fVar) {
            this.e.add(fVar);
            int i2 = fVar.j;
            if (i != -1) {
                i2 -= this.a[d(i)].j;
            }
            if (i2 > this.h) {
                f();
                return;
            }
            int b = b((this.d + i2) - this.h);
            if (i == -1) {
                if (this.c + 1 > this.a.length) {
                    Object obj = new f[(this.a.length * 2)];
                    System.arraycopy(this.a, 0, obj, this.a.length, this.a.length);
                    this.b = this.a.length - 1;
                    this.a = obj;
                }
                b = this.b;
                this.b = b - 1;
                this.a[b] = fVar;
                this.c++;
            } else {
                this.a[(b + d(i)) + i] = fVar;
            }
            this.d = i2 + this.d;
        }

        private int i() throws IOException {
            return this.f.j() & 255;
        }

        int a(int i, int i2) throws IOException {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            i3 = 0;
            while (true) {
                int i4 = i();
                if ((i4 & 128) == 0) {
                    return (i4 << i3) + i2;
                }
                i2 += (i4 & 127) << i3;
                i3 += 7;
            }
        }

        f d() throws IOException {
            int i = i();
            Object obj = (i & 128) == 128 ? 1 : null;
            i = a(i, 127);
            if (obj != null) {
                return f.a(j.a().b(this.f.g((long) i)));
            }
            return this.f.d((long) i);
        }
    }

    static final class b {
        private final dji.thirdparty.c.c a;

        b(dji.thirdparty.c.c cVar) {
            this.a = cVar;
        }

        void a(List<f> list) throws IOException {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                f h = ((f) list.get(i)).h.h();
                Integer num = (Integer) h.f.get(h);
                if (num != null) {
                    a(num.intValue() + 1, 15, 0);
                    a(((f) list.get(i)).i);
                } else {
                    this.a.b(0);
                    a(h);
                    a(((f) list.get(i)).i);
                }
            }
        }

        void a(int i, int i2, int i3) throws IOException {
            if (i < i2) {
                this.a.b(i3 | i);
                return;
            }
            this.a.b(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.a.b((i4 & 127) | 128);
                i4 >>>= 7;
            }
            this.a.b(i4);
        }

        void a(f fVar) throws IOException {
            a(fVar.j(), 127, 0);
            this.a.a(fVar);
        }
    }

    private h() {
    }

    private static Map<f, Integer> c() {
        Map linkedHashMap = new LinkedHashMap(e.length);
        for (int i = 0; i < e.length; i++) {
            if (!linkedHashMap.containsKey(e[i].h)) {
                linkedHashMap.put(e[i].h, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static f b(f fVar) throws IOException {
        int i = 0;
        int j = fVar.j();
        while (i < j) {
            byte b = fVar.b(i);
            if (b < (byte) 65 || b > (byte) 90) {
                i++;
            } else {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + fVar.a());
            }
        }
        return fVar;
    }
}
