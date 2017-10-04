package dji.midware.media.h.a;

import android.opengl.GLES20;
import android.util.Log;
import dji.midware.media.e;
import dji.midware.media.h.d;
import java.nio.ByteBuffer;

public class c extends d {
    private static String d = "GLRGB2YUVRender";
    protected boolean a = false;
    final boolean b;
    final boolean c;
    private final String e = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    private final String f = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.299*rgb.r+0.587*rgb.g+0.114*rgb.b;\nyuv.g=-0.148*rgb.r-0.289*rgb.g+0.437*rgb.b+0.5;\nyuv.b=0.615*rgb.r-0.515*rgb.g-0.100*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
    private final String k = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.2568*rgb.r+0.5041*rgb.g+0.0979*rgb.b+0.0625;\nyuv.g=-0.1482*rgb.r-0.291*rgb.g+0.4392*rgb.b+0.5;\nyuv.b=0.4392*rgb.r-0.3678*rgb.g-0.0714*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
    private final String l = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.299*rgb.r+0.587*rgb.g+0.114*rgb.b;\nyuv.g=-0.148*rgb.r-0.289*rgb.g+0.437*rgb.b+0.5;\nyuv.b=0.615*rgb.r-0.515*rgb.g-0.100*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
    private final String m = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.2568*rgb.r+0.5041*rgb.g+0.0979*rgb.b+0.0625;\nyuv.g=-0.1482*rgb.r-0.291*rgb.g+0.4392*rgb.b+0.5;\nyuv.b=0.4392*rgb.r-0.3678*rgb.g-0.0714*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
    private byte[] n;
    private byte[] o;
    private int p = 0;
    private int q = 0;

    public c(boolean z, boolean z2) {
        this.b = z;
        this.c = z2;
    }

    public void a(ByteBuffer byteBuffer, int i, int i2) {
        GLES20.glPixelStorei(3333, 4);
        byteBuffer.clear();
        GLES20.glReadPixels(0, 0, i, i2, d.c, 5121, byteBuffer);
        try {
            if (this.a) {
                this.a = false;
                if (!(this.n != null && this.p == i && this.q == i2)) {
                    this.n = new byte[((i * i2) * 4)];
                    this.o = new byte[((int) (((double) (i * i2)) * 1.5d))];
                }
                byteBuffer.position(0);
                byteBuffer.limit((i * i2) * 4);
                byteBuffer.get(this.n, 0, this.n.length);
                dji.midware.media.d.a(this.n, this.o, i, i2);
                dji.midware.media.d.a(ByteBuffer.wrap(this.o), 0, this.o.length, System.currentTimeMillis() + ".yuv");
                e.a("yuv saved");
            }
        } catch (Exception e) {
            e.a(e);
        }
        if (dji.midware.media.d.a(false)) {
            Log.i(d, "after readYUVData() : time=" + System.currentTimeMillis());
        }
    }

    protected String b() {
        return "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    }

    protected String c() {
        if (this.b) {
            if (this.c) {
                return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.299*rgb.r+0.587*rgb.g+0.114*rgb.b;\nyuv.g=-0.148*rgb.r-0.289*rgb.g+0.437*rgb.b+0.5;\nyuv.b=0.615*rgb.r-0.515*rgb.g-0.100*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
            }
            return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.2568*rgb.r+0.5041*rgb.g+0.0979*rgb.b+0.0625;\nyuv.g=-0.1482*rgb.r-0.291*rgb.g+0.4392*rgb.b+0.5;\nyuv.b=0.4392*rgb.r-0.3678*rgb.g-0.0714*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
        } else if (this.c) {
            return "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.299*rgb.r+0.587*rgb.g+0.114*rgb.b;\nyuv.g=-0.148*rgb.r-0.289*rgb.g+0.437*rgb.b+0.5;\nyuv.b=0.615*rgb.r-0.515*rgb.g-0.100*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
        } else {
            return "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\nvec3 yuv;\nvec3 rgb;\nrgb.r=texture2D(sTexture, vTextureCoord).r;\nrgb.g=texture2D(sTexture, vTextureCoord).g;\nrgb.b=texture2D(sTexture, vTextureCoord).b;\nyuv.r=0.2568*rgb.r+0.5041*rgb.g+0.0979*rgb.b+0.0625;\nyuv.g=-0.1482*rgb.r-0.291*rgb.g+0.4392*rgb.b+0.5;\nyuv.b=0.4392*rgb.r-0.3678*rgb.g-0.0714*rgb.b+0.5;\n;gl_FragColor =vec4(yuv,1.0);\n}\n";
        }
    }

    protected void d() {
    }

    protected void a() {
    }
}
