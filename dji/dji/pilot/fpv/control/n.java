package dji.pilot.fpv.control;

import android.content.Context;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.activity.b;
import java.util.Arrays;

public class n {
    private static final int d = 3;
    private static final int e = 4;
    private Context a = null;
    private b b = null;
    private final DataOsdGetPushCommon c = DataOsdGetPushCommon.getInstance();
    private final int[] f = new int[12];
    private final int[] g = new int[4];
    private final int[] h = new int[4];
    private int i = 0;
    private o j = null;

    public static boolean a() {
        return dji.pilot.fpv.d.b.j(i.getInstance().c());
    }

    public n(Context context) {
        this.a = context;
        this.j = new o(context);
        b();
    }

    private int a(int i) {
        if (i == 3) {
            return R.string.guidance_direction_left;
        }
        if (i == 2) {
            return R.string.guidance_direction_behind;
        }
        if (i == 1) {
            return R.string.guidance_direction_right;
        }
        return R.string.guidance_direction_front;
    }

    public void b() {
        this.i = 0;
        Arrays.fill(this.f, 0);
        Arrays.fill(this.g, 0);
        Arrays.fill(this.h, 0);
    }

    private boolean a(int[] iArr) {
        int i;
        int i2 = 3;
        int i3 = 0;
        for (i = 0; i < 4; i++) {
            if (iArr[i] != 0) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            return false;
        }
        boolean z;
        int i4 = (this.i % 3) * 4;
        for (i = 0; i < 4; i++) {
            this.f[i4 + i] = iArr[i];
        }
        this.i++;
        Arrays.fill(this.h, 0);
        if (this.i <= 3) {
            i2 = this.i;
        }
        for (i4 = 0; i4 < i2; i4++) {
            for (i = 0; i < 4; i++) {
                int[] iArr2 = this.h;
                iArr2[i] = iArr2[i] + this.f[(i4 * 4) + i];
            }
        }
        for (i = 0; i < 4; i++) {
            this.h[i] = this.h[i] / i2;
        }
        if (Arrays.equals(this.g, this.h)) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            return z;
        }
        while (i3 < 4) {
            this.g[i3] = this.h[i3];
            i3++;
        }
        return z;
    }

    public void a(ProductType productType) {
        this.j.a(productType);
    }

    public void onEventMainThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        if (!this.c.isMotorUp() || !dataFlycGetPushAvoid.isVisualSensorEnable() || !o.a() || !o.c()) {
            d();
        } else if (a(dataFlycGetPushAvoid.getDistance())) {
            int i = 0;
            int i2 = -1;
            while (i < 4) {
                if (this.g[i] > 0 && this.g[i] <= 72 && (i2 == -1 || this.g[i] < this.g[i2])) {
                    i2 = i;
                }
                i++;
            }
            if (i2 == -1) {
                d();
            } else {
                a(this.g, i2);
            }
        }
    }

    public void c() {
        if (this.b == null || !this.b.isShowing()) {
            a(new int[]{10, 30, 72, 32767}, 1);
        } else {
            d();
        }
    }

    private void a(int[] iArr, int i) {
        if (this.b == null) {
            this.b = new b(this.a);
        }
        this.b.a(iArr);
        this.b.a(iArr[i]);
        this.b.a(this.a.getString(a(i)));
        if (!this.b.isShowing()) {
            this.b.show();
        }
    }

    private void d() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
        }
    }
}
