package dji.pilot.fpv.control;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;

public class a {
    private static final int a = 3;
    private ViewGroup b = null;
    private Context c = null;
    private DJIImageView d = null;
    private int e = 0;
    private Animation f = null;

    public a(Context context, ViewGroup viewGroup) {
        this.c = context;
        this.b = viewGroup;
        this.f = AnimationUtils.loadAnimation(context, R.anim.g);
    }

    public void a() {
    }

    public void b() {
    }

    public void a(boolean z, int i, int i2) {
        if (!z || i != 2 || i2 > 0) {
        }
    }

    private void c() {
        if (this.d != null) {
            this.d.clearAnimation();
            this.b.removeView(this.d);
            this.d = null;
            this.e = 0;
        }
    }

    private void d() {
        if (this.d != null) {
            this.e++;
            if (this.e % 3 != 0) {
                return;
            }
            if (this.d.getVisibility() == 0) {
                this.d.setVisibility(8);
            } else {
                this.d.setVisibility(0);
            }
        }
    }
}
