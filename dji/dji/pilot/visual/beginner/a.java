package dji.pilot.visual.beginner;

import android.content.Context;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.g;
import dji.pilot.visual.a.g$e;
import dji.thirdparty.a.c;

public class a implements b {
    private final Context D;
    private boolean E;
    private boolean F;
    private volatile boolean G;
    private volatile int H;

    private static final class a {
        private static final a a = new a();

        private a() {
        }
    }

    public static a getInstance() {
        return a.a;
    }

    public void a() {
        if (this.G && this.H == 3) {
            c.a().e(dji.pilot.visual.beginner.b.a.POINT_TAP);
        }
    }

    public void b() {
        if (this.G && this.H == 3) {
            c.a().e(dji.pilot.visual.beginner.b.a.POINT_TAP_HIDEAUTO);
        }
    }

    public void c() {
        if (this.G && this.H == 3) {
            c.a().e(dji.pilot.visual.beginner.b.a.POINT_PRE_CLICK);
        }
    }

    public void d() {
        if (!this.G) {
            return;
        }
        if (this.H == 6 || this.H == 12 || this.H == 11) {
            c.a().e(dji.pilot.visual.beginner.b.a.VISUAL_STOP);
        }
    }

    public void e() {
        if (this.G && this.H == 5) {
            c.a().e(dji.pilot.visual.beginner.b.a.POINT_SPEED);
        }
    }

    public void f() {
        if (this.G && this.H == 8) {
            c.a().e(dji.pilot.visual.beginner.b.a.TRACK_SELECT);
        }
    }

    public void g() {
        if (this.G && this.H == 11) {
            c.a().e(dji.pilot.visual.beginner.b.a.TRACK_PERSON);
        }
    }

    public void a(boolean z) {
        this.G = z;
    }

    public void a(int i) {
        this.H = i;
    }

    public int[] h() {
        int[] iArr = C;
        if (!this.G || this.H == Integer.MIN_VALUE) {
            return iArr;
        }
        return y[this.H];
    }

    public boolean i() {
        g$e f = dji.pilot.visual.a.c.getInstance().f();
        if (f == g$e.POINT_MODE) {
            return m();
        }
        if (f == g$e.TRACK_MODE) {
            return l();
        }
        return false;
    }

    private boolean l() {
        return this.E;
    }

    private boolean m() {
        return this.F;
    }

    public void j() {
        g$e f = dji.pilot.visual.a.c.getInstance().f();
        if (f == g$e.POINT_MODE) {
            n();
        } else if (f == g$e.TRACK_MODE) {
            o();
        }
    }

    private void n() {
        this.F = false;
        g.a(this.D, b.c, this.F);
    }

    private void o() {
        this.E = false;
        g.a(this.D, b.d, this.E);
    }

    public void k() {
        this.E = true;
        this.F = true;
        g.a(this.D, b.d, this.E);
        g.a(this.D, b.c, this.F);
    }

    private a() {
        this.E = true;
        this.F = true;
        this.G = false;
        this.H = Integer.MIN_VALUE;
        this.D = DJIApplication.a();
        this.E = g.b(this.D, b.d, this.E);
        this.F = g.b(this.D, b.c, this.F);
    }
}
