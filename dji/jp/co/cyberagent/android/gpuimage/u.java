package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import jp.co.cyberagent.android.gpuimage.a.a;

public class u extends e {
    private Bitmap A;
    private ByteBuffer B;
    private Bitmap C;
    private ByteBuffer D;
    private Bitmap E;
    private ByteBuffer F;
    private Bitmap G;
    private ByteBuffer a;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    private Bitmap y;
    private ByteBuffer z;

    public u(String str) {
        this(e.b, str);
    }

    public u(String str, String str2) {
        super(str, str2);
        this.l = -1;
        this.o = -1;
        this.r = -1;
        this.u = -1;
        this.x = -1;
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate2");
        this.k = GLES20.glGetUniformLocation(l(), "inputImageTexture2");
        if (this.y != null) {
            a(this.y, 2);
        }
        if (this.k != -1) {
            this.a = a(z.NORMAL, false, false);
        }
        this.m = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate3");
        this.n = GLES20.glGetUniformLocation(l(), "inputImageTexture3");
        if (this.A != null) {
            a(this.A, 3);
        }
        if (this.n != -1) {
            this.z = a(z.NORMAL, false, false);
        }
        this.p = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate4");
        this.q = GLES20.glGetUniformLocation(l(), "inputImageTexture4");
        if (this.C != null) {
            a(this.C, 4);
        }
        if (this.q != -1) {
            this.B = a(z.NORMAL, false, false);
        }
        this.s = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate5");
        this.t = GLES20.glGetUniformLocation(l(), "inputImageTexture5");
        if (this.E != null) {
            a(this.E, 5);
        }
        if (this.t != -1) {
            this.D = a(z.NORMAL, false, false);
        }
        this.v = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate6");
        this.w = GLES20.glGetUniformLocation(l(), "inputImageTexture6");
        if (this.G != null) {
            a(this.G, 6);
        }
        if (this.w != -1) {
            this.F = a(z.NORMAL, false, false);
        }
    }

    public void f() {
        super.f();
        GLES20.glDeleteTextures(5, new int[]{this.l, this.o, this.r, this.u, this.x}, 0);
        this.l = -1;
        this.o = -1;
        this.r = -1;
        this.u = -1;
        this.x = -1;
    }

    public void c() {
        if (!(this.y == null || this.y.isRecycled())) {
            this.y.recycle();
            this.y = null;
        }
        if (!(this.A == null || this.A.isRecycled())) {
            this.A.recycle();
            this.A = null;
        }
        if (!(this.C == null || this.C.isRecycled())) {
            this.C.recycle();
            this.C = null;
        }
        if (!(this.E == null || this.E.isRecycled())) {
            this.E.recycle();
            this.E = null;
        }
        if (this.G != null && !this.G.isRecycled()) {
            this.G.recycle();
            this.G = null;
        }
    }

    protected void g() {
        if (this.k != -1) {
            GLES20.glEnableVertexAttribArray(this.j);
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.l);
            GLES20.glUniform1i(this.k, 3);
            this.a.position(0);
            GLES20.glVertexAttribPointer(this.j, 2, 5126, false, 0, this.a);
        }
        if (this.n != -1) {
            GLES20.glEnableVertexAttribArray(this.m);
            GLES20.glActiveTexture(33988);
            GLES20.glBindTexture(3553, this.o);
            GLES20.glUniform1i(this.n, 4);
            this.z.position(0);
            GLES20.glVertexAttribPointer(this.m, 2, 5126, false, 0, this.z);
        }
        if (this.q != -1) {
            GLES20.glEnableVertexAttribArray(this.p);
            GLES20.glActiveTexture(33989);
            GLES20.glBindTexture(3553, this.r);
            GLES20.glUniform1i(this.q, 5);
            this.B.position(0);
            GLES20.glVertexAttribPointer(this.p, 2, 5126, false, 0, this.B);
        }
        if (this.t != -1) {
            GLES20.glEnableVertexAttribArray(this.s);
            GLES20.glActiveTexture(33990);
            GLES20.glBindTexture(3553, this.u);
            GLES20.glUniform1i(this.t, 6);
            this.D.position(0);
            GLES20.glVertexAttribPointer(this.s, 2, 5126, false, 0, this.D);
        }
        if (this.w != -1) {
            GLES20.glEnableVertexAttribArray(this.v);
            GLES20.glActiveTexture(33991);
            GLES20.glBindTexture(3553, this.x);
            GLES20.glUniform1i(this.w, 7);
            this.F.position(0);
            GLES20.glVertexAttribPointer(this.v, 2, 5126, false, 0, this.F);
        }
    }

    public void a(final Bitmap bitmap, int i) {
        if (bitmap != null && !bitmap.isRecycled()) {
            switch (i) {
                case 2:
                    this.y = bitmap;
                    a(new Runnable(this) {
                        final /* synthetic */ u b;

                        public void run() {
                            GLES20.glActiveTexture(33987);
                            this.b.l = y.a(bitmap, -1, false);
                        }
                    });
                    return;
                case 3:
                    this.A = bitmap;
                    a(new Runnable(this) {
                        final /* synthetic */ u b;

                        public void run() {
                            GLES20.glActiveTexture(33988);
                            this.b.o = y.a(bitmap, -1, false);
                        }
                    });
                    return;
                case 4:
                    this.C = bitmap;
                    a(new Runnable(this) {
                        final /* synthetic */ u b;

                        public void run() {
                            GLES20.glActiveTexture(33989);
                            this.b.r = y.a(bitmap, -1, false);
                        }
                    });
                    return;
                case 5:
                    this.E = bitmap;
                    a(new Runnable(this) {
                        final /* synthetic */ u b;

                        public void run() {
                            GLES20.glActiveTexture(33990);
                            this.b.u = y.a(bitmap, -1, false);
                        }
                    });
                    return;
                case 6:
                    this.G = bitmap;
                    a(new Runnable(this) {
                        final /* synthetic */ u b;

                        public void run() {
                            GLES20.glActiveTexture(33991);
                            this.b.x = y.a(bitmap, -1, false);
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    }

    public ByteBuffer a(z zVar, boolean z, boolean z2) {
        float[] a = a.a(zVar, z, z2);
        ByteBuffer order = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = order.asFloatBuffer();
        asFloatBuffer.put(a);
        asFloatBuffer.flip();
        return order;
    }
}
