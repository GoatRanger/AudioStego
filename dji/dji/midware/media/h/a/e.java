package dji.midware.media.h.a;

import android.opengl.GLES20;
import lecho.lib.hellocharts.d.c;

public class e extends d {
    protected static final String b = "uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n";
    protected static final String c = "#extension GL_OES_EGL_image_external : require\nprecision highp float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform float k1;\nuniform float k2;\nuniform float k3;\nuniform float p1;\nuniform float p2;\nvoid main() {\n \tfloat xd=vTextureCoord.x*2.0-1.0; \n    float yd=vTextureCoord.y*2.0-1.0; \n    float rd2=xd*xd+yd*yd;    \n    float xc=xd*(1.0-k1*rd2+k2*rd2*rd2+k3*rd2*rd2*rd2)-2.0*p1*xd*yd+p2*(rd2+2.0*xd*xd); \n   float yc=yd*(1.0-k1*rd2+k2*rd2*rd2+k3*rd2*rd2*rd2)+p1*(rd2+2.0*yd*yd)+2.0*p2*xd*yd; \n   if (xc>1.0||yc>1.0||xc<-1.0||yc<-1.0) { \n   \tgl_FragColor = vec4(0.0, 0.0, 0.0, 1.0); \n   } else { \n\t\tgl_FragColor = texture2D(sTexture, vec2(xc*0.5+0.5, yc*0.5+0.5));\n   } \n }\n";
    protected static final String d = "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    protected final boolean a;
    private float e;
    private float f;
    private float k;
    private float l;
    private float m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;

    public e(boolean z) {
        this(z, 1.0f);
    }

    public e(boolean z, float f) {
        this.e = -0.02226907f;
        this.f = 0.01961271f;
        this.k = 0.00522009f;
        this.l = -0.00380254f;
        this.m = 0.0f;
        this.a = z;
        if (f <= 0.0f || f > 1.0f) {
            f = 1.0f;
        }
        this.g = new float[]{-1.0f, -f, 0.0f, 0.0f, 0.0f, 0.0f, -f, 0.0f, 1.0f, 0.0f, -1.0f, f, 0.0f, 0.0f, 1.0f, 0.0f, f, 0.0f, 1.0f, 1.0f, 0.0f, -f, 0.0f, 0.0f, 0.0f, 1.0f, -f, 0.0f, 1.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 1.0f, 1.0f, f, 0.0f, 1.0f, 1.0f};
        for (int i = 0; i < 4; i++) {
            float[] fArr = this.g;
            int i2 = (i * 10) + 3;
            fArr[i2] = fArr[i2] + c.a;
            fArr = this.g;
            i2 = (i * 10) + 8;
            fArr[i2] = fArr[i2] - c.a;
        }
    }

    protected String b() {
        return b;
    }

    protected String c() {
        if (this.a) {
            return c;
        }
        return d;
    }

    protected void d() {
        this.n = GLES20.glGetUniformLocation(this.h, "k1");
        a(this.n, "u_k1");
        this.o = GLES20.glGetUniformLocation(this.h, "k2");
        a(this.o, "u_k2");
        this.p = GLES20.glGetUniformLocation(this.h, "k3");
        a(this.p, "u_k3");
        this.q = GLES20.glGetUniformLocation(this.h, "p1");
        a(this.q, "u_p1");
        this.r = GLES20.glGetUniformLocation(this.h, "p2");
        a(this.r, "u_p2");
    }

    protected void a() {
        GLES20.glUniform1f(this.n, this.e);
        GLES20.glUniform1f(this.o, this.f);
        GLES20.glUniform1f(this.p, this.k);
        GLES20.glUniform1f(this.q, this.l);
        GLES20.glUniform1f(this.r, this.m);
        GLES20.glDrawArrays(5, 4, 4);
    }

    public void a(float f, float f2, float f3, float f4, float f5) {
        this.e = f;
        this.f = f2;
        this.k = f3;
        this.l = f4;
        this.m = f5;
    }
}
