package jp.co.cyberagent.android.gpuimage;

public class w extends u {
    public static final String a = " precision lowp float;\nvarying highp vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2;\nvoid main()\n{\nvec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\ntexel = vec3(\ntexture2D(inputImageTexture2, vec2(texel.r, .16666)).r,\ntexture2D(inputImageTexture2, vec2(texel.g, .5)).g,\ntexture2D(inputImageTexture2, vec2(texel.b, .83333)).b);\ngl_FragColor = vec4(texel, 1.0);\n}\n";

    public w() {
        super(a);
    }
}
