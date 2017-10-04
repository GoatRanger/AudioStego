package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class c extends e {
    public static final String a = "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float brightness;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4((textureColor.rgb + vec3(brightness)), textureColor.w);\n }";
    private int j;
    private float k;

    public c() {
        this(0.0f);
    }

    public c(float f) {
        super(e.b, a);
        this.k = f;
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), "brightness");
    }

    public void b() {
        super.b();
        a(this.k);
    }

    public void a(float f) {
        this.k = f;
        a(this.j, this.k);
    }

    public float c() {
        return this.k;
    }
}
