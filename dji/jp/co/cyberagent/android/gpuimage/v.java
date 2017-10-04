package jp.co.cyberagent.android.gpuimage;

public class v extends u {
    public static final String a = "precision lowp float;\nvarying highp vec2 textureCoordinate;\nuniform sampler2D inputImageTexture;\nuniform sampler2D inputImageTexture2; //earlyBirdCurves\nuniform sampler2D inputImageTexture3; //earlyBirdOverlay\nuniform sampler2D inputImageTexture4; //vig\nuniform sampler2D inputImageTexture5; //earlyBirdBlowout\nuniform sampler2D inputImageTexture6; //earlyBirdMap\nconst mat3 saturate = mat3(\n1.210300,\n-0.089700,\n-0.091000,\n-0.176100,\n1.123900,\n-0.177400,\n-0.034200,\n-0.034200,\n1.265800);\nconst vec3 rgbPrime = vec3(0.25098, 0.14640522, 0.0); \nconst vec3 desaturate = vec3(.3, .59, .11);\nvoid main()\n{\nvec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\nvec2 lookup;\nlookup.y = 0.5;\nlookup.x = texel.r;\ntexel.r = texture2D(inputImageTexture2, lookup).r;\nlookup.x = texel.g;\ntexel.g = texture2D(inputImageTexture2, lookup).g;\nlookup.x = texel.b;\ntexel.b = texture2D(inputImageTexture2, lookup).b;\nfloat desaturatedColor;\nvec3 result;\ndesaturatedColor = dot(desaturate, texel);\nlookup.x = desaturatedColor;\nresult.r = texture2D(inputImageTexture3, lookup).r;\nlookup.x = desaturatedColor;\nresult.g = texture2D(inputImageTexture3, lookup).g;\nlookup.x = desaturatedColor;\nresult.b = texture2D(inputImageTexture3, lookup).b;\ntexel = saturate * mix(texel, result, .5);\nvec2 tc = (2.0 * textureCoordinate) - 1.0;\nfloat d = dot(tc, tc);\nvec3 sampled;\nlookup.y = .5;\n/*\nlookup.x = texel.r;\nsampled.r = texture2D(inputImageTexture4, lookup).r;\nlookup.x = texel.g;\nsampled.g = texture2D(inputImageTexture4, lookup).g;\nlookup.x = texel.b;\nsampled.b = texture2D(inputImageTexture4, lookup).b;\nfloat value = smoothstep(0.0, 1.25, pow(d, 1.35)/1.65);\ntexel = mix(texel, sampled, value);\n*/\nlookup = vec2(d, texel.r);\ntexel.r = texture2D(inputImageTexture4, lookup).r;\nlookup.y = texel.g;\ntexel.g = texture2D(inputImageTexture4, lookup).g;\nlookup.y = texel.b;\ntexel.b\t= texture2D(inputImageTexture4, lookup).b;\nfloat value = smoothstep(0.0, 1.25, pow(d, 1.35)/1.65);\nlookup.x = texel.r;\nsampled.r = texture2D(inputImageTexture5, lookup).r;\nlookup.x = texel.g;\nsampled.g = texture2D(inputImageTexture5, lookup).g;\nlookup.x = texel.b;\nsampled.b = texture2D(inputImageTexture5, lookup).b;\ntexel = mix(sampled, texel, value);\nlookup.x = texel.r;\ntexel.r = texture2D(inputImageTexture6, lookup).r;\nlookup.x = texel.g;\ntexel.g = texture2D(inputImageTexture6, lookup).g;\nlookup.x = texel.b;\ntexel.b = texture2D(inputImageTexture6, lookup).b;\ngl_FragColor = vec4(texel, 1.0);\n}\n";

    public v() {
        super(a);
    }
}
