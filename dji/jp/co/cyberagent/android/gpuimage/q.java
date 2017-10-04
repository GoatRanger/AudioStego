package jp.co.cyberagent.android.gpuimage;

public class q extends u {
    public static final String a = "precision lowp float;\nvarying highp vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2;  //edgeBurn\nuniform sampler2D inputImageTexture3;  //hefeMap\nuniform sampler2D inputImageTexture4;  //hefeGradientMap\nuniform sampler2D inputImageTexture5;  //hefeSoftLight\nuniform sampler2D inputImageTexture6;  //hefeMetal\nvoid main()\n{\nvec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\nvec3 edge = texture2D(inputImageTexture2, textureCoordinate).rgb;\ntexel = texel * edge;\ntexel = vec3(\ntexture2D(inputImageTexture3, vec2(texel.r, .16666)).r,\ntexture2D(inputImageTexture3, vec2(texel.g, .5)).g,\ntexture2D(inputImageTexture3, vec2(texel.b, .83333)).b);\nvec3 luma = vec3(.30, .59, .11);\nvec3 gradSample = texture2D(inputImageTexture4, vec2(dot(luma, texel), .5)).rgb;\nvec3 final = vec3(\ntexture2D(inputImageTexture5, vec2(gradSample.r, texel.r)).r,\ntexture2D(inputImageTexture5, vec2(gradSample.g, texel.g)).g,\ntexture2D(inputImageTexture5, vec2(gradSample.b, texel.b)).b\n);\nvec3 metal = texture2D(inputImageTexture6, textureCoordinate).rgb;\nvec3 metaled = vec3(\ntexture2D(inputImageTexture5, vec2(metal.r, texel.r)).r,\ntexture2D(inputImageTexture5, vec2(metal.g, texel.g)).g,\ntexture2D(inputImageTexture5, vec2(metal.b, texel.b)).b\n);\ngl_FragColor = vec4(metaled, 1.0);\n}\n";

    public q() {
        super(a);
    }
}
