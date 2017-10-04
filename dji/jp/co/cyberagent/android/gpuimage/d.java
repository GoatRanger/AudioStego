package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;
import dji.pilot2.multimoment.activity.DJIMultiMomentFineActivity;

public class d extends e {
    public static final String a = "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float contrast;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n }";
    private int j;
    private float k;

    public d() {
        this(1.2f);
    }

    public d(float f) {
        super(e.b, a);
        this.k = f;
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), DJIMultiMomentFineActivity.R);
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
