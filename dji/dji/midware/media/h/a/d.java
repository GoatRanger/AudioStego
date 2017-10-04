package dji.midware.media.h.a;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.midware.media.e;
import dji.thirdparty.afinal.c.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class d {
    private static String a = "GLRenderBase";
    private static boolean b = false;
    private static final int c = 4;
    private static final int d = 20;
    private static final int e = 0;
    private static final int f = 3;
    protected float[] g = new float[]{-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, -1.0f, 0.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f};
    protected int h;
    protected int i;
    protected int j;
    private FloatBuffer k;
    private float[] l = new float[16];
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;

    protected abstract void a();

    protected abstract String b();

    protected abstract String c();

    protected abstract void d();

    public void e() {
        this.k = ByteBuffer.allocateDirect(this.g.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.k.put(this.g).position(0);
        g();
        d();
    }

    public void a(int i, int i2, float[] fArr, boolean z, float f, int i3, int i4, int i5, int i6, int i7, int i8) {
        b(i, i2, fArr, z, f, i3, i4, i5, i6, i7, i8);
    }

    public void b(int i, int i2, float[] fArr, boolean z, float f, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = (i3 * i3) / (i7 - i5);
        int i10 = (i4 * i4) / (i8 - i6);
        a(i, i2, fArr, z, f, ((-i5) * i9) / i3, ((-i6) * i10) / i4, i9, i10);
    }

    public void c(int i, int i2, float[] fArr, boolean z, float f, int i3, int i4, int i5, int i6, int i7, int i8) {
        a(i, i2, fArr, z, f, i5, i6, i7 - i5, i8 - i6);
    }

    public void a(int i, int i2, float[] fArr, boolean z, float f, int i3, int i4) {
        a(i, i2, fArr, z, f, 0, 0, i3, i4);
    }

    public void a(int i, int i2, float[] fArr, boolean z, float f, int i3, int i4, int i5, int i6) {
        if (fArr == null || fArr.length != 16) {
            fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
            e.c(b, a, "using default mSTMatrix");
        }
        GLES20.glViewport(i3, i4, i5, i6);
        this.i = i5;
        this.j = i6;
        e.c(b, a, String.format("glViewport x=%d, y=%d, width=%d, height=%d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)}));
        dji.midware.media.h.d.a("GLRenderBase draw: set viewPort");
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        dji.midware.media.h.d.a("GLRenderBase draw: after clear");
        GLES20.glUseProgram(this.h);
        dji.midware.media.h.d.a("GLRenderBase draw: after glUseProgram");
        this.k.position(0);
        GLES20.glVertexAttribPointer(this.m, 3, 5126, false, 20, this.k);
        dji.midware.media.h.d.a("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.m);
        dji.midware.media.h.d.a("glEnableVertexAttribArray maPositionHandle");
        this.k.position(3);
        GLES20.glVertexAttribPointer(this.n, 2, 5126, false, 20, this.k);
        dji.midware.media.h.d.a("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.n);
        dji.midware.media.h.d.a("glEnableVertexAttribArray maTextureHandle");
        Matrix.setRotateM(this.l, 0, f, 0.0f, 0.0f, 1.0f);
        GLES20.glUniformMatrix4fv(this.o, 1, false, this.l, 0);
        if (z) {
            e.d(b, a, "before invert: " + c.b(fArr));
            float f2 = fArr[5];
            float f3 = fArr[13];
            fArr[5] = -f2;
            fArr[13] = f2 + f3;
            e.d(b, a, "after invert: " + c.b(fArr));
        }
        GLES20.glUniformMatrix4fv(this.p, 1, false, fArr, 0);
        a(33984, i2, i);
        GLES20.glUniform1i(this.q, 0);
        a();
        GLES20.glDrawArrays(5, 0, 4);
        dji.midware.media.h.d.a("glDrawArrays");
        a(33984, i2, 0);
    }

    protected void a(int i, int i2, int i3) {
        GLES20.glActiveTexture(i);
        GLES20.glBindTexture(i2, i3);
    }

    private int b(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        dji.midware.media.h.d.a("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        Log.e(a, "Could not compile shader " + i + ":");
        Log.e(a, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    private void a(int i) {
        GLES20.glDeleteShader(i);
    }

    private void g() {
        int b = b(35633, b());
        if (b == 0) {
            Log.e(a, "can't create vertex shader");
        }
        int b2 = b(35632, c());
        if (b2 == 0) {
            Log.e(a, "can't create fragment shader for display");
        }
        this.h = GLES20.glCreateProgram();
        if (this.h == 0) {
            e.b(a, "Could not create a shader program");
        } else {
            e.c(b, a, "create an OpenGL shader program with ID=" + this.h);
        }
        GLES20.glAttachShader(this.h, b);
        dji.midware.media.h.d.a("glAttachShader");
        GLES20.glAttachShader(this.h, b2);
        dji.midware.media.h.d.a("glAttachShader");
        GLES20.glLinkProgram(this.h);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(this.h, 35714, iArr, 0);
        if (iArr[0] != 1) {
            Log.e(a, "Could not link program: ");
            Log.e(a, GLES20.glGetProgramInfoLog(this.h));
            GLES20.glDeleteProgram(this.h);
            this.h = 0;
        }
        if (this.h == 0) {
            throw new RuntimeException("failed creating program");
        }
        if (b != 0) {
            a(b);
        }
        if (b2 != 0) {
            a(b2);
        }
        this.m = GLES20.glGetAttribLocation(this.h, "aPosition");
        a(this.m, "aPosition");
        this.n = GLES20.glGetAttribLocation(this.h, "aTextureCoord");
        a(this.n, "aTextureCoord");
        this.o = GLES20.glGetUniformLocation(this.h, "uMVPMatrix");
        a(this.o, "uMVPMatrix");
        this.p = GLES20.glGetUniformLocation(this.h, "uSTMatrix");
        a(this.p, "uSTMatrix");
        this.q = GLES20.glGetUniformLocation(this.h, "sTexture");
        a(this.q, "sTexture");
    }

    protected void a(int i, String str) {
        if (i < 0) {
            throw new RuntimeException("Unable to locate '" + str + "' in program");
        }
    }

    public void f() {
        if (this.h != 0) {
            e.c(b, a, "delete an OpenGL shader program with ID=" + this.h);
            GLES20.glDeleteProgram(this.h);
        }
    }

    public void b(ByteBuffer byteBuffer, int i, int i2) {
        GLES20.glPixelStorei(3333, 4);
        byteBuffer.clear();
        GLES20.glReadPixels(0, 0, i, i2, dji.midware.media.h.d.c, 5121, byteBuffer);
    }
}
