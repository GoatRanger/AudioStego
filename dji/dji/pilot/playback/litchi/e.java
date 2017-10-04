package dji.pilot.playback.litchi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import dji.pilot.playback.litchi.DJIPlayBackLocalView.a;
import dji.pilot.publics.objects.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;

public abstract class e extends d {
    public static final int e = -1;
    public static final int f = 0;
    public static final int g = 1;
    public static int h = -1;
    protected DJIRelativeLayout a;
    protected DJIImageView b = null;
    protected DJIImageView c = null;
    protected DJIImageView d = null;
    protected boolean i = false;
    private a l;

    public abstract void a(int i);

    public abstract boolean c();

    public abstract boolean d();

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract void j();

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.k == null) {
            this.k = a(layoutInflater, viewGroup, bundle);
        }
        l();
        return this.k;
    }

    public DJIPlayBackActivity a() {
        return (DJIPlayBackActivity) this.j;
    }

    public void b() {
        this.l = DJIPlayBackLocalView.e;
        if (this.l != null) {
            this.l.sendEmptyMessage(2);
        }
    }
}
