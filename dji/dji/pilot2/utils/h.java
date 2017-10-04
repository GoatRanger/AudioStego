package dji.pilot2.utils;

import android.graphics.PointF;

public class h {
    float[] a;

    public float[] a() {
        return this.a;
    }

    public void a(PointF[] pointFArr, PointF[] pointFArr2) {
        int min = Math.min(pointFArr.length, pointFArr2.length);
        if (this.a == null || this.a.length < min * min) {
            this.a = new float[((min * min) * 2)];
        }
        int i = 0;
        int i2 = 0;
        while (i < min) {
            int i3 = i2;
            for (i2 = 0; i2 < min; i2++) {
                PointF pointF = new PointF(pointFArr[i].x + ((pointFArr2[i].x - pointFArr[i].x) * (((float) i2) / ((float) min))), pointFArr[i].y + ((pointFArr2[i].y - pointFArr[i].y) * (((float) i2) / ((float) min))));
                int i4 = i3 + 1;
                this.a[i3] = pointF.x;
                i3 = i4 + 1;
                this.a[i4] = pointF.y;
            }
            i++;
            i2 = i3;
        }
    }
}
