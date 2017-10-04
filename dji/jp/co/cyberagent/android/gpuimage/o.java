package jp.co.cyberagent.android.gpuimage;

public class o extends u {
    public static final String a = "precision lowp float;\nvarying highp vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2;\nvoid main()\n{\nvec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\ntexel = vec3(dot(vec3(0.3, 0.6, 0.1), texel));\ntexel = vec3(texture2D(inputImageTexture2, vec2(texel.r, .16666)).r);\ngl_FragColor = vec4(texel, 1.0);\n}\n";

    public o() {
        super(a);
    }
}
