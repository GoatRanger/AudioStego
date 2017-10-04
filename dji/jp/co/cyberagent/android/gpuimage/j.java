package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

public class j extends e {
    public static final String a = "attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nuniform mat4 transformMatrix;\nuniform mat4 orthographicMatrix;\nvarying vec2 textureCoordinate;\n  \nvoid main()\n{\n\tgl_Position = transformMatrix * vec4(position.xyz, 1.0) * orthographicMatrix;\n\t\n\ttextureCoordinate = inputTextureCoordinate.xy;\n}\n";
    int j;
    int k;
    private boolean l = false;
    private float m = 0.0f;
    private float n = 1.0f;
    private float o = 0.0f;
    private float p = 0.0f;

    public j() {
        super(a, e.c);
    }

    public void a() {
        super.a();
        this.j = GLES20.glGetUniformLocation(l(), "transformMatrix");
        this.k = GLES20.glGetUniformLocation(l(), "orthographicMatrix");
        this.l = true;
        a(this.m, this.n, this.o, this.p);
    }

    public float c() {
        return this.n;
    }

    public float p() {
        return this.p;
    }

    public float q() {
        return this.o;
    }

    public float r() {
        return this.m;
    }

    public void a(float f, float f2, float f3, float f4) {
        this.m = f;
        this.n = f2;
        this.o = f3;
        this.p = f4;
        a(new float[]{((float) Math.cos((double) f)) * f2, (-((float) Math.sin((double) f))) * f2, 0.0f, 0.0f, ((float) Math.sin((double) f)) * f2, ((float) Math.cos((double) f)) * f2, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, f3, f4, 0.0f, 1.0f});
    }

    public void a(int i, int i2) {
        float[] fArr = new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        float f = ((float) i2) / ((float) i);
        a(fArr, -1.0f, 1.0f, -f, f, -1.0f, 1.0f);
        b(fArr);
    }

    private void a(float[] fArr, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 - f3;
        float f8 = f6 - f5;
        float f9 = (-(f2 + f)) / (f2 - f);
        float f10 = (-(f4 + f3)) / (f4 - f3);
        float f11 = (-(f6 + f5)) / (f6 - f5);
        fArr[0] = 2.0f / (f2 - f);
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = f9;
        fArr[4] = 0.0f;
        fArr[5] = 2.0f / f7;
        fArr[6] = 0.0f;
        fArr[7] = f10;
        fArr[8] = 0.0f;
        fArr[9] = 0.0f;
        fArr[10] = 2.0f / f8;
        fArr[11] = f11;
        fArr[12] = 0.0f;
        fArr[13] = 0.0f;
        fArr[14] = 0.0f;
        fArr[15] = 1.0f;
    }

    private void a(float[] fArr) {
        if (this.l) {
            f(this.j, fArr);
        }
    }

    private void b(float[] fArr) {
        if (this.l) {
            f(this.k, fArr);
        }
    }
}
