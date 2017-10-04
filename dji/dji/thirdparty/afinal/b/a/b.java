package dji.thirdparty.afinal.b.a;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.tencent.android.tpush.common.Constants;
import dji.thirdparty.afinal.g.c;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class b {
    private static final int a = 8388608;
    private static final int b = 52428800;
    private static final int c = 10000;
    private static final boolean d = true;
    private static final boolean e = true;
    private g f;
    private h g;
    private a h;

    public static class a {
        public int a = 8388608;
        public int b = b.b;
        public int c = 10000;
        public File d;
        public boolean e = true;
        public boolean f = true;
        public boolean g = true;

        public a(File file) {
            this.d = file;
        }

        public a(String str) {
            this.d = new File(str);
        }

        public void a(Context context, float f) {
            if (f < 0.05f || f > 0.8f) {
                throw new IllegalArgumentException("setMemCacheSizePercent - percent must be between 0.05 and 0.8 (inclusive)");
            }
            this.a = Math.round(((((float) a(context)) * f) * 1024.0f) * 1024.0f);
        }

        public void a(int i) {
            this.a = i;
        }

        public void b(int i) {
            this.b = i;
        }

        private static int a(Context context) {
            return ((ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getMemoryClass();
        }

        public void c(int i) {
            this.c = i;
        }

        public void a(boolean z) {
            this.g = z;
        }
    }

    public b(a aVar) {
        a(aVar);
    }

    private void a(a aVar) {
        this.h = aVar;
        if (this.h.e) {
            if (this.h.g) {
                this.g = new j(this.h.a);
            } else {
                this.g = new a(this.h.a);
            }
        }
        if (aVar.f) {
            try {
                this.f = new g(this.h.d.getAbsolutePath(), this.h.c, this.h.b, false);
            } catch (IOException e) {
            }
        }
    }

    public void a(String str, Bitmap bitmap) {
        if (str != null && bitmap != null) {
            this.g.a(str, bitmap);
        }
    }

    public void a(String str, byte[] bArr) {
        if (this.f != null && str != null && bArr != null) {
            byte[] b = c.b(str);
            long a = c.a(b);
            ByteBuffer allocate = ByteBuffer.allocate(b.length + bArr.length);
            allocate.put(b);
            allocate.put(bArr);
            synchronized (this.f) {
                try {
                    this.f.a(a, allocate.array());
                } catch (IOException e) {
                    Log.i("", e.getMessage());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r6, dji.thirdparty.afinal.b.a.f.a r7) {
        /*
        r5 = this;
        r0 = 0;
        r1 = r5.f;
        if (r1 != 0) goto L_0x0006;
    L_0x0005:
        return r0;
    L_0x0006:
        r1 = dji.thirdparty.afinal.g.c.b(r6);
        r2 = dji.thirdparty.afinal.g.c.a(r1);
        r4 = new dji.thirdparty.afinal.b.a.g$a;	 Catch:{ IOException -> 0x0029 }
        r4.<init>();	 Catch:{ IOException -> 0x0029 }
        r4.a = r2;	 Catch:{ IOException -> 0x0029 }
        r2 = r7.a;	 Catch:{ IOException -> 0x0029 }
        r4.b = r2;	 Catch:{ IOException -> 0x0029 }
        r2 = r5.f;	 Catch:{ IOException -> 0x0029 }
        monitor-enter(r2);	 Catch:{ IOException -> 0x0029 }
        r3 = r5.f;	 Catch:{ all -> 0x0026 }
        r3 = r3.a(r4);	 Catch:{ all -> 0x0026 }
        if (r3 != 0) goto L_0x002b;
    L_0x0024:
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        goto L_0x0005;
    L_0x0026:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        throw r1;	 Catch:{ IOException -> 0x0029 }
    L_0x0029:
        r1 = move-exception;
        goto L_0x0005;
    L_0x002b:
        monitor-exit(r2);	 Catch:{ all -> 0x0026 }
        r2 = r4.b;	 Catch:{ IOException -> 0x0029 }
        r2 = dji.thirdparty.afinal.g.c.a(r1, r2);	 Catch:{ IOException -> 0x0029 }
        if (r2 == 0) goto L_0x0005;
    L_0x0034:
        r2 = r4.b;	 Catch:{ IOException -> 0x0029 }
        r7.a = r2;	 Catch:{ IOException -> 0x0029 }
        r1 = r1.length;	 Catch:{ IOException -> 0x0029 }
        r7.b = r1;	 Catch:{ IOException -> 0x0029 }
        r1 = r4.c;	 Catch:{ IOException -> 0x0029 }
        r2 = r7.b;	 Catch:{ IOException -> 0x0029 }
        r1 = r1 - r2;
        r7.c = r1;	 Catch:{ IOException -> 0x0029 }
        r0 = 1;
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.afinal.b.a.b.a(java.lang.String, dji.thirdparty.afinal.b.a.f$a):boolean");
    }

    public Bitmap a(String str) {
        if (this.g != null) {
            return this.g.a(str);
        }
        return null;
    }

    public void a() {
        c();
        b();
    }

    public void b() {
        if (this.f != null) {
            this.f.a();
        }
    }

    public void c() {
        if (this.g != null) {
            this.g.a();
        }
    }

    public void b(String str) {
        d(str);
        c(str);
    }

    public void c(String str) {
        a(str, new byte[0]);
    }

    public void d(String str) {
        if (this.g != null) {
            this.g.b(str);
        }
    }

    public void d() {
        if (this.f != null) {
            this.f.close();
        }
    }
}
