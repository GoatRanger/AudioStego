package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import lecho.lib.hellocharts.model.h;

public class l extends e {
    public static final String a = " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n \n uniform lowp vec2 vignetteCenter;\n uniform lowp vec3 vignetteColor;\n uniform highp float vignetteStart;\n uniform highp float vignetteEnd;\n \n void main()\n {\n     /*\n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(0.5,0.5));\n     rgb *= (1.0 - smoothstep(vignetteStart, vignetteEnd, d));\n     gl_FragColor = vec4(vec3(rgb),1.0);\n      */\n     \n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(vignetteCenter.x, vignetteCenter.y));\n     lowp float percent = smoothstep(vignetteStart, vignetteEnd, d);\n     gl_FragColor = vec4(mix(rgb.x, vignetteColor.x, percent), mix(rgb.y, vignetteColor.y, percent), mix(rgb.z, vignetteColor.z, percent), 1.0);\n }";
    private int j;
    private PointF k;
    private int l;
    private float[] m;
    private int n;
    private float o;
    private int p;
    private float q;

    public l() {
        this(new PointF(), new float[]{0.0f, 0.0f, 0.0f}, 0.3f, h.l);
    }

    public l(PointF pointF, float[] fArr, float f, float f2) {
        super(e.b, a);
        this.k = pointF;
        this.m = fArr;
        this.o = f;
        this.q = f2;
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), "vignetteCenter");
        this.l = GLES20.glGetUniformLocation(l(), "vignetteColor");
        this.n = GLES20.glGetUniformLocation(l(), "vignetteStart");
        this.p = GLES20.glGetUniformLocation(l(), "vignetteEnd");
        a(this.k);
        a(this.m);
        a(this.o);
        b(this.q);
    }

    public void a(PointF pointF) {
        this.k = pointF;
        a(this.j, this.k);
    }

    public void a(float[] fArr) {
        this.m = fArr;
        b(this.l, this.m);
    }

    public void a(float f) {
        this.o = f;
        a(this.n, this.o);
    }

    public void b(float f) {
        this.q = f;
        a(this.p, this.q);
    }
}
