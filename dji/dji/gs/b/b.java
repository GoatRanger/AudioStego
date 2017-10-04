package dji.gs.b;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import dji.thirdparty.a.c;

public class b {
    public static float a;
    private Context b;
    private boolean c = false;
    private boolean d = false;
    private float e;
    private a f;
    private Display g;
    private int h;
    private float i;

    public interface a {
        void a(float f);
    }

    public b(Context context) {
        this.b = context;
    }

    public void a(a aVar) {
        this.f = aVar;
        c.a().a(this);
    }

    public void a() {
        Log.e("", "angleTmp start");
    }

    public void b() {
        Log.e("", "angleTmp stop");
    }

    public void c() {
        c.a().d(this);
    }

    public void a(boolean z) {
        this.c = z;
        if (!z) {
            a = this.e;
            if (this.f != null) {
                this.f.a(a);
            }
        }
    }

    private int d() {
        if (this.g == null) {
            this.g = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay();
        }
        return this.g.getRotation();
    }

    public void onEventMainThread(float[] fArr) {
        boolean z = false;
        if (!this.c) {
            this.e = (float) ((int) Math.toDegrees((double) fArr[0]));
            if (this.e - a > 2.0f || a - this.e > 2.0f) {
                z = true;
            }
            this.d = z;
            if (this.d) {
                a = this.e;
                this.h = d();
                if (this.h == 3) {
                    this.e += 180.0f;
                }
                this.i = this.e;
                if (this.f != null) {
                    this.f.a(this.i);
                }
            }
        }
    }
}
