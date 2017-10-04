package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;
import dji.pilot2.multimoment.activity.DJIMultiMomentFineActivity;

public class i extends e {
    public static final String a = " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float saturation;\n \n // Values from \"Graphics Shaders: Theory and Practice\" by Bailey and Cunningham\n const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n \n void main()\n {\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp float luminance = dot(textureColor.rgb, luminanceWeighting);\n    lowp vec3 greyScaleColor = vec3(luminance);\n    \n    gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);\n     \n }";
    private int j;
    private float k;

    public i() {
        this(1.0f);
    }

    public i(float f) {
        super(e.b, a);
        this.k = f;
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), DJIMultiMomentFineActivity.Q);
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
