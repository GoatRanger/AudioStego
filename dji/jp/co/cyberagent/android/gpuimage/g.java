package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class g extends e {
    public static final String a = "  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform highp float red;\n  uniform highp float green;\n  uniform highp float blue;\n  \n  void main()\n  {\n      highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n      \n      gl_FragColor = vec4(textureColor.r * red, textureColor.g * green, textureColor.b * blue, 1.0);\n  }\n";
    private int j;
    private float k;
    private int l;
    private float m;
    private int n;
    private float o;
    private boolean p;

    public g() {
        this(1.0f, 1.0f, 1.0f);
    }

    public g(float f, float f2, float f3) {
        super(e.b, a);
        this.p = false;
        this.k = f;
        this.m = f2;
        this.o = f3;
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), "red");
        this.l = GLES20.glGetUniformLocation(l(), "green");
        this.n = GLES20.glGetUniformLocation(l(), "blue");
        this.p = true;
        a(this.k);
        b(this.m);
        c(this.o);
    }

    public void a(float f) {
        this.k = f;
        if (this.p) {
            a(this.j, this.k);
        }
    }

    public void b(float f) {
        this.m = f;
        if (this.p) {
            a(this.l, this.m);
        }
    }

    public void c(float f) {
        this.o = f;
        if (this.p) {
            a(this.n, this.o);
        }
    }
}
