package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import java.lang.ref.WeakReference;

public abstract class dd {
    private da a;
    private com.amap.api.mapcore.util.da.a b;
    protected boolean c = false;
    protected Resources d;
    private boolean e = false;
    private final Object f = new Object();

    public class a extends cv<Boolean, Void, Bitmap> {
        final /* synthetic */ dd a;
        private final WeakReference<com.amap.api.mapcore.util.av.a> e;

        public a(dd ddVar, com.amap.api.mapcore.util.av.a aVar) {
            this.a = ddVar;
            this.e = new WeakReference(aVar);
        }

        protected Bitmap a(Boolean... boolArr) {
            try {
                boolean booleanValue = boolArr[0].booleanValue();
                Object obj = (com.amap.api.mapcore.util.av.a) this.e.get();
                if (obj == null) {
                    return null;
                }
                Bitmap bitmap;
                Bitmap bitmap2;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(obj.a);
                stringBuilder.append("-");
                stringBuilder.append(obj.b);
                stringBuilder.append("-");
                stringBuilder.append(obj.c);
                String stringBuilder2 = stringBuilder.toString();
                synchronized (this.a.f) {
                    while (this.a.c && !d()) {
                        this.a.f.wait();
                    }
                }
                if (this.a.a == null || d() || e() == null || this.a.e) {
                    bitmap = null;
                } else {
                    bitmap = this.a.a.b(stringBuilder2);
                }
                if (!booleanValue || bitmap != null || d() || e() == null || this.a.e) {
                    bitmap2 = bitmap;
                } else {
                    bitmap2 = this.a.a(obj);
                }
                if (bitmap2 == null || this.a.a == null) {
                    return bitmap2;
                }
                this.a.a.a(stringBuilder2, bitmap2);
                return bitmap2;
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        protected void a(Bitmap bitmap) {
            try {
                if (d() || this.a.e) {
                    bitmap = null;
                }
                com.amap.api.mapcore.util.av.a e = e();
                if (bitmap != null && !bitmap.isRecycled() && e != null) {
                    e.a(bitmap);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        protected void b(Bitmap bitmap) {
            super.b((Object) bitmap);
            synchronized (this.a.f) {
                try {
                    this.a.f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }

        private com.amap.api.mapcore.util.av.a e() {
            com.amap.api.mapcore.util.av.a aVar = (com.amap.api.mapcore.util.av.a) this.e.get();
            return this == dd.c(aVar) ? aVar : null;
        }
    }

    protected class b extends cv<Object, Void, Void> {
        final /* synthetic */ dd a;

        protected b(dd ddVar) {
            this.a = ddVar;
        }

        protected /* synthetic */ Object a(Object[] objArr) {
            return d(objArr);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected java.lang.Void d(java.lang.Object... r2) {
            /*
            r1 = this;
            r0 = 0;
            r0 = r2[r0];	 Catch:{ Throwable -> 0x0014 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Throwable -> 0x0014 }
            r0 = r0.intValue();	 Catch:{ Throwable -> 0x0014 }
            switch(r0) {
                case 0: goto L_0x000e;
                case 1: goto L_0x0019;
                case 2: goto L_0x001f;
                case 3: goto L_0x0025;
                default: goto L_0x000c;
            };	 Catch:{ Throwable -> 0x0014 }
        L_0x000c:
            r0 = 0;
            return r0;
        L_0x000e:
            r0 = r1.a;	 Catch:{ Throwable -> 0x0014 }
            r0.c();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0014:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x000c;
        L_0x0019:
            r0 = r1.a;	 Catch:{ Throwable -> 0x0014 }
            r0.b();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x001f:
            r0 = r1.a;	 Catch:{ Throwable -> 0x0014 }
            r0.d();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0025:
            r0 = r1.a;	 Catch:{ Throwable -> 0x0014 }
            r0.e();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.dd.b.d(java.lang.Object[]):java.lang.Void");
        }
    }

    protected abstract Bitmap a(Object obj);

    protected dd(Context context) {
        this.d = context.getResources();
    }

    public void a(boolean z, com.amap.api.mapcore.util.av.a aVar) {
        if (aVar != null) {
            Bitmap bitmap = null;
            try {
                if (this.a != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(aVar.a);
                    stringBuilder.append("-");
                    stringBuilder.append(aVar.b);
                    stringBuilder.append("-");
                    stringBuilder.append(aVar.c);
                    bitmap = this.a.a(stringBuilder.toString());
                }
                if (bitmap != null) {
                    aVar.a(bitmap);
                    return;
                }
                a aVar2 = new a(this, aVar);
                aVar.j = aVar2;
                aVar2.a(cv.d, (Object[]) new Boolean[]{Boolean.valueOf(z)});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(com.amap.api.mapcore.util.da.a aVar) {
        this.b = aVar;
        this.a = da.a(this.b);
        new b(this).c(Integer.valueOf(1));
    }

    public void a(boolean z) {
        this.e = z;
        b(false);
    }

    protected da a() {
        return this.a;
    }

    public static void a(com.amap.api.mapcore.util.av.a aVar) {
        a c = c(aVar);
        if (c != null) {
            c.a(true);
        }
    }

    private static a c(com.amap.api.mapcore.util.av.a aVar) {
        if (aVar != null) {
            return aVar.j;
        }
        return null;
    }

    public void b(boolean z) {
        synchronized (this.f) {
            this.c = z;
            if (!this.c) {
                try {
                    this.f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected void b() {
        if (this.a != null) {
            this.a.a();
        }
    }

    protected void c() {
        if (this.a != null) {
            this.a.b();
        }
    }

    protected void d() {
        if (this.a != null) {
            this.a.c();
        }
    }

    protected void e() {
        if (this.a != null) {
            this.a.d();
            this.a = null;
        }
    }

    public void f() {
        new b(this).c(Integer.valueOf(0));
    }

    public void g() {
        new b(this).c(Integer.valueOf(2));
    }

    public void h() {
        new b(this).c(Integer.valueOf(3));
    }
}
