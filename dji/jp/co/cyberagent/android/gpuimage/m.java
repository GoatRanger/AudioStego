package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import jp.co.cyberagent.android.gpuimage.a.a;

public class m extends e {
    public static final String a = "varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate7;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture7;\n \n void main()\n {\n     lowp vec4 c2 = texture2D(inputImageTexture, textureCoordinate);\n     lowp vec4 c1 = texture2D(inputImageTexture7, textureCoordinate7);\n     \n     lowp vec4 outputColor;\n     outputColor.r = c1.r + c2.r * c2.a * (1.0 - c1.a);\n     outputColor.g = c1.g + c2.g * c2.a * (1.0 - c1.a);\n     outputColor.b = c1.b + c2.b * c2.a * (1.0 - c1.a);\n     outputColor.a = c1.a + c2.a * (1.0 - c1.a);\n     \n     gl_FragColor = outputColor;\n }";
    private static final String m = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate7;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate7;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate7 = inputTextureCoordinate7.xy;\n}";
    public int j;
    public int k;
    public int l = -1;
    private ByteBuffer n;
    private Bitmap o = null;

    public m() {
        super(m, a);
        a(z.NORMAL, false, true);
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetAttribLocation(l(), "inputTextureCoordinate7");
        this.k = GLES20.glGetUniformLocation(l(), "inputImageTexture7");
        GLES20.glEnableVertexAttribArray(this.j);
        if (this.o != null) {
            a(this.o);
        }
    }

    public void a(final Bitmap bitmap) {
        this.o = bitmap;
        a(new Runnable(this) {
            final /* synthetic */ m b;

            public void run() {
                GLES20.glActiveTexture(33992);
                this.b.l = y.a(bitmap, -1, false);
            }
        });
    }

    public void f() {
        super.f();
        GLES20.glDeleteTextures(1, new int[]{this.l}, 0);
        this.l = -1;
    }

    protected void g() {
        GLES20.glEnableVertexAttribArray(this.j);
        GLES20.glActiveTexture(33992);
        GLES20.glBindTexture(3553, this.l);
        GLES20.glUniform1i(this.k, 8);
        this.n.position(0);
        GLES20.glVertexAttribPointer(this.j, 2, 5126, false, 0, this.n);
    }

    public void a(z zVar, boolean z, boolean z2) {
        float[] a = a.a(zVar, z, z2);
        ByteBuffer order = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = order.asFloatBuffer();
        asFloatBuffer.put(a);
        asFloatBuffer.flip();
        this.n = order;
    }
}
