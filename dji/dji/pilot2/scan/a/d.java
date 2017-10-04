package dji.pilot2.scan.a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.a.n;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class d {
    private static final String a = d.class.getSimpleName();
    private static final int b = 240;
    private static final int c = 240;
    private static final int d = 1200;
    private static final int e = 675;
    private final Context f;
    private final b g;
    private Camera h;
    private a i;
    private Rect j;
    private Rect k;
    private boolean l;
    private boolean m;
    private int n = -1;
    private int o;
    private int p;
    private final f q;

    public d(Context context) {
        this.f = context;
        this.g = new b(context);
        this.q = new f(this.g);
    }

    public synchronized void a(SurfaceHolder surfaceHolder) throws IOException {
        Camera camera = this.h;
        if (camera == null) {
            if (this.n >= 0) {
                camera = e.a(this.n);
            } else {
                camera = e.a();
            }
            if (camera == null) {
                throw new IOException();
            }
            this.h = camera;
        }
        Camera camera2 = camera;
        camera2.setPreviewDisplay(surfaceHolder);
        if (!this.l) {
            this.l = true;
            this.g.a(camera2);
            if (this.o > 0 && this.p > 0) {
                a(this.o, this.p);
                this.o = 0;
                this.p = 0;
            }
        }
        Parameters parameters = camera2.getParameters();
        if (parameters == null) {
            r0 = null;
        } else {
            r0 = parameters.flatten();
        }
        try {
            this.g.a(camera2, false);
        } catch (RuntimeException e) {
            Log.w(a, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(a, "Resetting to saved camera params: " + r0);
            if (r0 != null) {
                String str;
                Parameters parameters2 = camera2.getParameters();
                parameters2.unflatten(str);
                try {
                    camera2.setParameters(parameters2);
                    this.g.a(camera2, true);
                } catch (RuntimeException e2) {
                    Log.w(a, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
    }

    public synchronized boolean a() {
        return this.h != null;
    }

    public synchronized void b() {
        if (this.h != null) {
            this.h.release();
            this.h = null;
            this.j = null;
            this.k = null;
        }
    }

    public synchronized void c() {
        Camera camera = this.h;
        if (!(camera == null || this.m)) {
            camera.startPreview();
            this.m = true;
            this.i = new a(this.f, this.h);
        }
    }

    public synchronized void d() {
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
        if (this.h != null && this.m) {
            this.h.stopPreview();
            this.q.a(null, 0);
            this.m = false;
        }
    }

    public synchronized void a(float f, float f2, int i, int i2) {
        Rect a = a(f, f2, 1.0f, i, i2);
        List arrayList = new ArrayList();
        arrayList.add(new Area(a, 1));
        this.h.getParameters().setFocusAreas(arrayList);
    }

    private static Rect a(float f, float f2, float f3, int i, int i2) {
        int intValue = Float.valueOf(300.0f * f3).intValue();
        int i3 = (int) ((f2 / ((float) i2)) - 1000.0f);
        int a = a(((int) ((f / ((float) i)) - 1000.0f)) - (intValue / 2), (int) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, 1000);
        i3 = a(i3 - (intValue / 2), (int) NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, 1000);
        RectF rectF = new RectF((float) a, (float) i3, (float) (a + intValue), (float) (intValue + i3));
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    private static int a(int i, int i2, int i3) {
        if (i > i3) {
            return i3;
        }
        if (i < i2) {
            return i2;
        }
        return i;
    }

    public synchronized void a(Handler handler, int i) {
        Camera camera = this.h;
        if (camera != null && this.m) {
            this.q.a(handler, i);
            camera.setOneShotPreviewCallback(this.q);
        }
    }

    public synchronized Rect e() {
        Rect rect = null;
        synchronized (this) {
            if (this.j == null) {
                if (this.h != null) {
                    Point b = this.g.b();
                    if (b != null) {
                        int b2 = (b(b.x, 240, d) * 4) / 5;
                        int i = (b.x - b2) / 2;
                        int b3 = (b.y / 3) - (((b(b.y, 240, e) * 4) / 5) / 2);
                        this.j = new Rect(i, b3, i + b2, b2 + b3);
                        Log.d(a, "Calculated framing rect: " + this.j);
                    }
                }
            }
            rect = this.j;
        }
        return rect;
    }

    private static int b(int i, int i2, int i3) {
        int i4 = (i * 5) / 8;
        if (i4 < i2) {
            return i2;
        }
        if (i4 > i3) {
            return i3;
        }
        return i4;
    }

    public synchronized Rect f() {
        Rect rect = null;
        synchronized (this) {
            if (this.k == null) {
                Rect e = e();
                if (e != null) {
                    Rect rect2 = new Rect(e);
                    Point a = this.g.a();
                    Point b = this.g.b();
                    if (!(a == null || b == null)) {
                        rect2.left = (rect2.left * a.y) / b.x;
                        rect2.right = (rect2.right * a.y) / b.x;
                        rect2.top = (rect2.top * a.x) / b.y;
                        rect2.bottom = (rect2.bottom * a.x) / b.y;
                        this.k = rect2;
                    }
                }
            }
            rect = this.k;
        }
        return rect;
    }

    public synchronized void a(int i) {
        this.n = i;
    }

    public synchronized void a(int i, int i2) {
        if (this.l) {
            Point b = this.g.b();
            if (i > b.x) {
                i = b.x;
            }
            if (i2 > b.y) {
                i2 = b.y;
            }
            int i3 = (b.x - i) / 2;
            int i4 = (b.y - i2) / 2;
            this.j = new Rect(i3, i4, i3 + i, i4 + i2);
            Log.d(a, "Calculated manual framing rect: " + this.j);
            this.k = null;
        } else {
            this.o = i;
            this.p = i2;
        }
    }

    public n a(byte[] bArr, int i, int i2) {
        Rect f = f();
        if (f == null) {
            return null;
        }
        return new n(bArr, i, i2, f.left, f.top, f.width(), f.height(), false);
    }
}
