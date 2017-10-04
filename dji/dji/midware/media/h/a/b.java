package dji.midware.media.h.a;

import android.opengl.GLES20;
import dji.midware.media.e;

public class b extends d {
    public static String b = "GLIdentityRender";
    public static boolean c = false;
    protected static final String d = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    protected static final String e = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float yuv_scale_uniform; \nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord)*yuv_scale_uniform;\n}\n";
    protected static final String f = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform float yuv_scale_uniform; \nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord)*yuv_scale_uniform;\n}\n";
    protected final boolean a;
    private float k = 1.0f;
    private int l;

    public b(boolean z) {
        this.a = z;
    }

    protected String b() {
        return d;
    }

    protected String c() {
        if (this.a) {
            return e;
        }
        return f;
    }

    protected void d() {
        this.l = GLES20.glGetUniformLocation(this.h, "yuv_scale_uniform");
        a(this.l, "yuv_scale_uniform_loc");
    }

    protected void a() {
        GLES20.glUniform1f(this.l, this.k);
        e.c(c, b, "set yuv_scale to shader uniform: loc=" + this.l + " scale=" + this.k);
    }

    public void a(float f) {
        this.k = f;
    }
}
