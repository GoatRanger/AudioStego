package jp.co.cyberagent.android.gpuimage;

import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLES20;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class e {
    public static final String b = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}";
    public static final String c = "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}";
    private final LinkedList<Runnable> a;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    protected int i;
    private final String j;
    private final String k;
    private boolean l;

    public e() {
        this(b, c);
    }

    public e(String str, String str2) {
        this.a = new LinkedList();
        this.j = str;
        this.k = str2;
    }

    public final void d() {
        a();
        this.l = true;
        b();
    }

    public void a() {
        this.d = y.a(this.j, this.k);
        this.e = GLES20.glGetAttribLocation(this.d, "position");
        this.f = GLES20.glGetUniformLocation(this.d, "inputImageTexture");
        this.g = GLES20.glGetAttribLocation(this.d, "inputTextureCoordinate");
        this.l = true;
    }

    public void b() {
    }

    public final void e() {
        this.l = false;
        GLES20.glDeleteProgram(this.d);
        f();
    }

    public void f() {
    }

    public void a(int i, int i2) {
        this.h = i;
        this.i = i2;
    }

    public void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.d);
        h();
        if (this.l) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.e, 2, 5126, false, 0, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.e);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.g, 2, 5126, false, 0, floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.g);
            if (i != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i);
                GLES20.glUniform1i(this.f, 0);
            }
            g();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.e);
            GLES20.glDisableVertexAttribArray(this.g);
            GLES20.glBindTexture(3553, 0);
        }
    }

    protected void g() {
    }

    protected void h() {
        while (!this.a.isEmpty()) {
            try {
                ((Runnable) this.a.removeFirst()).run();
            } catch (NoSuchElementException e) {
            }
        }
    }

    public boolean i() {
        return this.l;
    }

    public int j() {
        return this.h;
    }

    public int k() {
        return this.i;
    }

    public int l() {
        return this.d;
    }

    public int m() {
        return this.e;
    }

    public int n() {
        return this.g;
    }

    public int o() {
        return this.f;
    }

    protected void b(final int i, final int i2) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniform1i(i, i2);
            }
        });
    }

    protected void a(final int i, final float f) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniform1f(i, f);
            }
        });
    }

    protected void a(final int i, final float[] fArr) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniform2fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void b(final int i, final float[] fArr) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniform3fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void c(final int i, final float[] fArr) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniform4fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void d(final int i, final float[] fArr) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniform1fv(i, fArr.length, FloatBuffer.wrap(fArr));
            }
        });
    }

    protected void a(final int i, final PointF pointF) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniform2fv(i, 1, new float[]{pointF.x, pointF.y}, 0);
            }
        });
    }

    protected void e(final int i, final float[] fArr) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniformMatrix3fv(i, 1, false, fArr, 0);
            }
        });
    }

    protected void f(final int i, final float[] fArr) {
        a(new Runnable(this) {
            final /* synthetic */ e c;

            public void run() {
                GLES20.glUniformMatrix4fv(i, 1, false, fArr, 0);
            }
        });
    }

    protected void a(Runnable runnable) {
        synchronized (this.a) {
            this.a.addLast(runnable);
        }
    }

    public static String a(String str, Context context) {
        try {
            InputStream open = context.getAssets().open(str);
            String a = a(open);
            open.close();
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(InputStream inputStream) {
        Scanner useDelimiter = new Scanner(inputStream).useDelimiter("\\A");
        return useDelimiter.hasNext() ? useDelimiter.next() : "";
    }
}
