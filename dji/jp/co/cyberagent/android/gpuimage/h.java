package jp.co.cyberagent.android.gpuimage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import jp.co.cyberagent.android.gpuimage.a.a;

@TargetApi(11)
public class h implements Renderer {
    public static final int a = -1;
    static final float[] b = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    public final Object c = new Object();
    private e d;
    private int e = -1;
    private SurfaceTexture f = null;
    private final FloatBuffer g;
    private final FloatBuffer h;
    private int i;
    private int j;
    private int k;
    private int l;
    private final Queue<Runnable> m;
    private final Queue<Runnable> n;
    private z o;
    private boolean p;
    private boolean q;

    public h(e eVar) {
        this.d = eVar;
        this.m = new LinkedList();
        this.n = new LinkedList();
        this.g = ByteBuffer.allocateDirect(b.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.g.put(b).position(0);
        this.h = ByteBuffer.allocateDirect(a.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        b(z.NORMAL, false, false);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glDisable(2929);
        this.d.d();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.i = i;
        this.j = i2;
        GLES20.glViewport(0, 0, i, i2);
        GLES20.glUseProgram(this.d.l());
        this.d.a(i, i2);
        g();
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    @SuppressLint({"WrongCall"})
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        a(this.m);
        this.d.a(this.e, this.g, this.h);
        a(this.n);
        if (this.f != null) {
            this.f.updateTexImage();
        }
    }

    private void a(Queue<Runnable> queue) {
        synchronized (queue) {
            while (!queue.isEmpty()) {
                ((Runnable) queue.poll()).run();
            }
        }
    }

    public void a(final e eVar) {
        a(new Runnable(this) {
            final /* synthetic */ h b;

            public void run() {
                e a = this.b.d;
                this.b.d = eVar;
                if (a != null) {
                    a.e();
                }
                this.b.d.d();
                GLES20.glUseProgram(this.b.d.l());
                this.b.d.a(this.b.i, this.b.j);
            }
        });
    }

    public void a() {
        a(new Runnable(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void run() {
                GLES20.glDeleteTextures(1, new int[]{this.a.e}, 0);
                this.a.e = -1;
            }
        });
    }

    public void a(Bitmap bitmap) {
        a(bitmap, true);
    }

    public void a(final Bitmap bitmap, final boolean z) {
        if (bitmap != null) {
            a(new Runnable(this) {
                final /* synthetic */ h c;

                public void run() {
                    Bitmap createBitmap;
                    Log.d("rxq", "photo w:" + bitmap.getWidth() + "    photo h:" + bitmap.getHeight());
                    if (bitmap.getWidth() % 2 == 1) {
                        createBitmap = Bitmap.createBitmap(bitmap.getWidth() + 1, bitmap.getHeight(), Config.ARGB_8888);
                        Canvas canvas = new Canvas(createBitmap);
                        canvas.drawARGB(0, 0, 0, 0);
                        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
                    } else {
                        createBitmap = null;
                    }
                    this.c.e = y.a(createBitmap != null ? createBitmap : bitmap, this.c.e, z);
                    if (createBitmap != null) {
                        createBitmap.recycle();
                    }
                    this.c.k = bitmap.getWidth();
                    this.c.l = bitmap.getHeight();
                    this.c.g();
                }
            });
        }
    }

    protected int b() {
        return this.i;
    }

    protected int c() {
        return this.j;
    }

    private void g() {
        float f = 1.0f;
        float[] fArr = b;
        float[] a = a.a(this.o, this.p, this.q);
        float f2 = ((float) this.k) / ((float) this.l);
        if (f2 < 1.0f) {
            f = (((float) this.l) * 1.3333f) / ((float) this.k);
            f2 = 1.3333f;
        }
        float[] fArr2 = new float[]{b[0] / f, b[1] / f2, b[2] / f, b[3] / f2, b[4] / f, b[5] / f2, b[6] / f, b[7] / f2};
        this.g.clear();
        this.g.put(fArr2).position(0);
        this.h.clear();
        this.h.put(a).position(0);
    }

    public void a(z zVar, boolean z, boolean z2) {
        b(zVar, z2, z);
    }

    public void a(z zVar) {
        this.o = zVar;
        g();
    }

    public void b(z zVar, boolean z, boolean z2) {
        this.p = z;
        this.q = z2;
        a(zVar);
    }

    public z d() {
        return this.o;
    }

    public boolean e() {
        return this.p;
    }

    public boolean f() {
        return this.q;
    }

    protected void a(Runnable runnable) {
        synchronized (this.m) {
            this.m.add(runnable);
        }
    }

    protected void b(Runnable runnable) {
        synchronized (this.n) {
            this.n.add(runnable);
        }
    }
}
