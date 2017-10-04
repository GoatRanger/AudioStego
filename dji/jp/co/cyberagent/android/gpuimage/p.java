package jp.co.cyberagent.android.gpuimage;

public class p extends u {
    public static final String a = "precision lowp float;\nvarying highp vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2; //map\nuniform sampler2D inputImageTexture3; //vigMap\nvoid main()\n{\nvec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\ntexel = vec3(\ntexture2D(inputImageTexture2, vec2(texel.r, .16666)).r,\ntexture2D(inputImageTexture2, vec2(texel.g, .5)).g,\ntexture2D(inputImageTexture2, vec2(texel.b, .83333)).b);\nvec2 tc = (2.0 * textureCoordinate) - 1.0;\nfloat d = dot(tc, tc);\nvec2 lookup = vec2(d, texel.r);\ntexel.r = texture2D(inputImageTexture3, lookup).r;\nlookup.y = texel.g;\ntexel.g = texture2D(inputImageTexture3, lookup).g;\nlookup.y = texel.b;\ntexel.b\t= texture2D(inputImageTexture3, lookup).b;\ngl_FragColor = vec4(texel, 1.0);\n}\n";

    public p() {
        super(a);
    }
}
