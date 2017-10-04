package dji.phone.a;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class e extends Animation {
    private static final String a = "DJIRotate3dAnimation";
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final float f;
    private final float g;
    private final boolean h;
    private Camera i;

    public e(float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        this.b = f;
        this.c = f2;
        this.d = f3;
        this.e = f4;
        this.f = f5;
        this.g = f6;
        this.h = z;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.i = new Camera();
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.b;
        f2 += (this.c - f2) * f;
        float f3 = this.d;
        float f4 = this.e;
        Camera camera = this.i;
        Matrix matrix = transformation.getMatrix();
        Log.i("interpolatedTime", f + "");
        camera.save();
        if (this.h) {
            camera.translate(0.0f, 0.0f, this.f * f);
        } else {
            camera.translate(0.0f, 0.0f, this.f * (1.0f - f));
        }
        camera.rotateY(f2);
        camera.getMatrix(matrix);
        camera.restore();
        float[] fArr = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        matrix.getValues(fArr);
        fArr[6] = fArr[6] / this.g;
        matrix.setValues(fArr);
        matrix.preTranslate(-f3, -f4);
        matrix.postTranslate(f3, f4);
    }
}
