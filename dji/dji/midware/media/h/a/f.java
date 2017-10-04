package dji.midware.media.h.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.media.h.d;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class f extends d {
    private static final String a = "OverExposureWarner";
    private static String b = "attribute vec4 aPosition; \nattribute vec4 aTextureCoord; \nuniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\n//x:width in {0, 1}, y:height in {0, 1} z:offset in {0, 1}, w:blend factor \nuniform vec4 overexp_texture_param; \nvarying vec2 v_texcoord; \nvarying vec4 v_overexp_texcoord; \n \t void main() \n\t { \n\t     gl_Position = uMVPMatrix * aPosition; \n\t     v_texcoord = (uSTMatrix * aTextureCoord).xy; \n\t     v_overexp_texcoord = vec4(v_texcoord.x * overexp_texture_param.x + overexp_texture_param.z,\t                               v_texcoord.y * overexp_texture_param.y,\t                               ceil(overexp_texture_param.w), overexp_texture_param.w*64.0); \n\t } \n";
    private static String c = "//#declare \nvarying highp vec2 v_texcoord; \nvarying highp vec4 v_overexp_texcoord; \nuniform sampler2D sTexture; \nuniform sampler2D s_texture_overexp; \n// use alpha channel to store lumaince \n// const highp vec4 luminanceVec = vec4(0.2126, 0.7152, 0.0722, 1.0); \nvoid main() \n{\t  \n  \t//get rgb color \n\t\thighp vec4 rgb_color = texture2D(sTexture, v_texcoord); \n//get over exposed texture color \n     highp vec4 over_exposed_tex_color = vec4(texture2D(s_texture_overexp, v_overexp_texcoord.xy).a);  \n     highp float lumaince = 0.2568*rgb_color.r+0.5041*rgb_color.g+0.0979*rgb_color.b+0.0625;  \n     highp float blend_factor = clamp(lumaince*64.0 - v_overexp_texcoord.w, 0.0 ,1.0)*v_overexp_texcoord.z;  \n     highp vec4 ret_color = mix(rgb_color, over_exposed_tex_color, blend_factor); \n    gl_FragColor = vec4(ret_color.xyz, 1.0);  \n }  \n";
    private int d = -1;
    private int e;
    private int f;
    private int k;
    private int l;
    private long m;
    private boolean n;
    private int o = 0;

    public static class a {
        public boolean a = false;
        public int b = 0;

        public a(boolean z, int i) {
            this.a = z;
            this.b = i;
        }
    }

    public f(boolean z, int i) {
        this.n = z;
        this.o = i;
    }

    protected String b() {
        return b;
    }

    protected String c() {
        if (this.n) {
            return c.replace("//#declare", "#extension GL_OES_EGL_image_external : require").replace("uniform sampler2D sTexture;", "uniform samplerExternalOES sTexture;");
        }
        return c;
    }

    protected void d() {
        ServiceManager.getInstance();
        Bitmap decodeResource = BitmapFactory.decodeResource(ServiceManager.getContext().getResources(), this.o);
        this.k = decodeResource.getWidth();
        this.l = decodeResource.getHeight();
        Buffer allocate = ByteBuffer.allocate((this.k * this.l) * 4);
        decodeResource.copyPixelsToBuffer(allocate);
        allocate.clear();
        this.d = d.a(3553, true);
        d.a(this.d, 3553, allocate, this.k, this.l);
        this.e = GLES20.glGetUniformLocation(this.h, "s_texture_overexp");
        a(this.e, "s_texture_overexp");
        this.f = GLES20.glGetUniformLocation(this.h, "overexp_texture_param");
        a(this.f, "overexp_texture_param");
        this.m = System.currentTimeMillis();
    }

    protected void a() {
        a(33985, 3553, this.d);
        GLES20.glUniform1i(this.e, 1);
        GLES20.glUniform4f(this.f, ((float) this.i) / ((float) this.k), ((float) this.j) / ((float) this.l), ((float) (System.currentTimeMillis() - this.m)) / 2000.0f, 0.85f);
    }

    public void f() {
        super.f();
        if (this.d != -1) {
            d.b(this.d);
        }
    }
}
