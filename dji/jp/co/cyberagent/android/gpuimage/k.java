package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import jp.co.cyberagent.android.gpuimage.a.a;

public class k extends e {
    private static final String l = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n}";
    public int a;
    public int j;
    public int k;
    private ByteBuffer m;
    private Bitmap n;

    public k(String str) {
        this(l, str);
    }

    public k(String str, String str2) {
        super(str, str2);
        this.k = -1;
        a(z.NORMAL, false, false);
    }

    public void a() {
        super.a();
        this.a = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate2");
        this.j = GLES20.glGetUniformLocation(l(), "inputImageTexture2");
        GLES20.glEnableVertexAttribArray(this.a);
        if (this.n != null && !this.n.isRecycled()) {
            a(this.n);
        }
    }

    public void a(final Bitmap bitmap) {
        if (bitmap == null || !bitmap.isRecycled()) {
            this.n = bitmap;
            if (this.n != null) {
                a(new Runnable(this) {
                    final /* synthetic */ k b;

                    public void run() {
                        if (this.b.k == -1 && bitmap != null && !bitmap.isRecycled()) {
                            GLES20.glActiveTexture(33987);
                            this.b.k = y.a(bitmap, -1, false);
                        }
                    }
                });
            }
        }
    }

    public Bitmap c() {
        return this.n;
    }

    public void p() {
        if (this.n != null && !this.n.isRecycled()) {
            this.n.recycle();
            this.n = null;
        }
    }

    public void f() {
        super.f();
        GLES20.glDeleteTextures(1, new int[]{this.k}, 0);
        this.k = -1;
        p();
    }

    protected void g() {
        GLES20.glEnableVertexAttribArray(this.a);
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(3553, this.k);
        GLES20.glUniform1i(this.j, 3);
        this.m.position(0);
        GLES20.glVertexAttribPointer(this.a, 2, 5126, false, 0, this.m);
    }

    public void a(z zVar, boolean z, boolean z2) {
        float[] a = a.a(zVar, z, z2);
        ByteBuffer order = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = order.asFloatBuffer();
        asFloatBuffer.put(a);
        asFloatBuffer.flip();
        this.m = order;
    }
}
