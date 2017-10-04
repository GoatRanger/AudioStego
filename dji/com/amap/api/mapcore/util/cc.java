package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMapException;
import java.io.File;
import java.io.IOException;

public class cc implements com.amap.api.mapcore.util.fr.a {
    cd a = null;
    long b = 0;
    long c = 0;
    long d;
    boolean e = true;
    bx f;
    long g = 0;
    a h;
    private Context i;
    private ch j;
    private String k;
    private fr l;
    private by m;

    public interface a {
        void d();
    }

    public cc(cd cdVar, String str, Context context, ch chVar) throws IOException {
        this.f = bx.a(context.getApplicationContext());
        this.a = cdVar;
        this.i = context;
        this.k = str;
        this.j = chVar;
        g();
    }

    private void f() throws IOException {
        fw ciVar = new ci(this.k);
        ciVar.a(1800000);
        ciVar.b(1800000);
        this.l = new fr(ciVar, this.b, this.c);
        this.m = new by(this.a.b() + File.separator + this.a.c(), this.b);
    }

    private void g() {
        if (this.f.f(this.a.e())) {
            this.e = false;
            l();
            return;
        }
        this.b = 0;
        this.c = 0;
    }

    public void a() {
        try {
            if (dj.c(this.i)) {
                i();
                if (dm.a == 1) {
                    if (!h()) {
                        this.e = true;
                    }
                    if (this.e) {
                        this.d = b();
                        if (this.d == -1) {
                            cf.a("File Length is not known!");
                        } else if (this.d == -2) {
                            cf.a("File is not access!");
                        } else {
                            this.c = this.d;
                        }
                        this.b = 0;
                    }
                    if (this.j != null) {
                        this.j.m();
                    }
                    f();
                    this.l.a(this);
                } else if (this.j != null) {
                    this.j.a(com.amap.api.mapcore.util.ch.a.amap_exception);
                }
            } else if (this.j != null) {
                this.j.a(com.amap.api.mapcore.util.ch.a.network_exception);
            }
        } catch (Throwable e) {
            ee.a(e, "SiteFileFetch", "download");
            if (this.j != null) {
                this.j.a(com.amap.api.mapcore.util.ch.a.amap_exception);
            }
        } catch (IOException e2) {
            if (this.j != null) {
                this.j.a(com.amap.api.mapcore.util.ch.a.file_io_exception);
            }
        }
    }

    private boolean h() {
        if (new File(this.a.b() + File.separator + this.a.c()).length() < 10) {
            return false;
        }
        return true;
    }

    private void i() throws AMapException {
        if (dm.a != 1) {
            int i = 0;
            while (i < 3) {
                try {
                    if (!dm.a(this.i, dj.e())) {
                        i++;
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    ee.a(th, "SiteFileFetch", "authOffLineDownLoad");
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long b() throws java.io.IOException {
        /*
        r5 = this;
        r2 = -1;
        r0 = new java.net.URL;
        r1 = r5.a;
        r1 = r1.a();
        r0.<init>(r1);
        r0 = r0.openConnection();
        r0 = (java.net.HttpURLConnection) r0;
        r1 = "User-Agent";
        r3 = com.amap.api.mapcore.util.r.d;
        r0.setRequestProperty(r1, r3);
        r1 = r0.getResponseCode();
        r3 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r1 < r3) goto L_0x0027;
    L_0x0021:
        r5.a(r1);
        r0 = -2;
    L_0x0026:
        return r0;
    L_0x0027:
        r1 = 1;
    L_0x0028:
        r3 = r0.getHeaderFieldKey(r1);
        if (r3 == 0) goto L_0x0043;
    L_0x002e:
        r4 = "Content-Length";
        r4 = r3.equalsIgnoreCase(r4);
        if (r4 == 0) goto L_0x0040;
    L_0x0036:
        r0 = r0.getHeaderField(r3);
        r0 = java.lang.Integer.parseInt(r0);
    L_0x003e:
        r0 = (long) r0;
        goto L_0x0026;
    L_0x0040:
        r1 = r1 + 1;
        goto L_0x0028;
    L_0x0043:
        r0 = r2;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.cc.b():long");
    }

    private void j() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.a != null && currentTimeMillis - this.g > 500) {
            k();
            this.g = currentTimeMillis;
            a(this.b);
        }
    }

    private void k() {
        this.f.a(this.a.e(), this.a.d(), this.d, this.b, this.c);
    }

    private void a(long j) {
        if (this.d > 0 && this.j != null) {
            this.j.a(this.d, j);
            this.g = System.currentTimeMillis();
        }
    }

    private boolean l() {
        if (!this.f.f(this.a.e())) {
            return false;
        }
        this.d = (long) this.f.d(this.a.e());
        long[] a = this.f.a(this.a.e(), 0);
        this.b = a[0];
        this.c = a[1];
        return true;
    }

    private void a(int i) {
        System.err.println("Error Code : " + i);
    }

    public void c() {
        if (this.l != null) {
            this.l.a();
        }
    }

    public void d() {
        if (this.j != null) {
            this.j.o();
        }
        k();
    }

    public void e() {
        j();
        if (this.j != null) {
            this.j.n();
        }
        if (this.m != null) {
            this.m.a();
        }
        if (this.h != null) {
            this.h.d();
        }
    }

    public void a(Throwable th) {
        if (this.j != null) {
            this.j.a(com.amap.api.mapcore.util.ch.a.network_exception);
        }
        if (!(th instanceof IOException) && this.m != null) {
            this.m.a();
        }
    }

    public void a(byte[] bArr, long j) {
        try {
            this.m.a(bArr);
            this.b = j;
            j();
        } catch (Throwable e) {
            e.printStackTrace();
            ee.a(e, "fileAccessI", "fileAccessI.write(byte[] data)");
            if (this.j != null) {
                this.j.a(com.amap.api.mapcore.util.ch.a.file_io_exception);
            }
            if (this.l != null) {
                this.l.a();
            }
        }
    }

    public void a(a aVar) {
        this.h = aVar;
    }
}
