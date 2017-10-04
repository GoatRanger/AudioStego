package dji.pilot2.main.activity;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.d.c;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.pilot2.utils.l;
import dji.pilot2.widget.a;

class DJIMainFragmentActivity$9 implements OnClickListener {
    final /* synthetic */ DJIMainFragmentActivity a;

    DJIMainFragmentActivity$9(DJIMainFragmentActivity dJIMainFragmentActivity) {
        this.a = dJIMainFragmentActivity;
    }

    public void onClick(View view) {
        int i;
        DJIMainFragmentActivity.a(this.a, DJIMainFragmentActivity.d(this.a).beginTransaction());
        DJIMainFragmentActivity.a(this.a, DJIMainFragmentActivity.f(this.a).hide(DJIMainFragmentActivity.e(this.a)[0]).hide(DJIMainFragmentActivity.e(this.a)[1]).hide(DJIMainFragmentActivity.e(this.a)[2]).hide(DJIMainFragmentActivity.e(this.a)[3]));
        if (DJIMainFragmentActivity.g(this.a) != null) {
            for (int i2 = 0; i2 < DJIMainFragmentActivity.g(this.a).size(); i2++) {
                if (DJIMainFragmentActivity.g(this.a).get(i2) == view) {
                    i = i2;
                    break;
                }
            }
        }
        i = -1;
        if (i != -1 && i != DJIMainFragmentActivity.h(this.a)) {
            if (i != 2 && ((float) SystemClock.elapsedRealtime()) - DJIMainFragmentActivity.i(this.a) > 5000.0f) {
                DJIMainFragmentActivity.a(this.a, (float) SystemClock.elapsedRealtime());
            }
            ((View) DJIMainFragmentActivity.g(this.a).get(DJIMainFragmentActivity.h(this.a))).setSelected(false);
            ((View) DJIMainFragmentActivity.g(this.a).get(i)).setSelected(true);
            DJIMainFragmentActivity.a(this.a, i);
            if (DJIMainFragmentActivity.h(this.a) == 0) {
                DJIMainFragmentActivity.j(this.a).setBackgroundColor(this.a.getResources().getColor(R.color.nb));
            } else {
                DJIMainFragmentActivity.j(this.a).setBackgroundColor(this.a.getResources().getColor(R.color.nb));
            }
            DJIMainFragmentActivity.f(this.a).show(DJIMainFragmentActivity.e(this.a)[DJIMainFragmentActivity.h(this.a)]).commitAllowingStateLoss();
            if (DJIMainFragmentActivity.h(this.a) == 0 && f.getInstance().c()) {
                l.getInstance().a(DJIMainFragmentActivity.c(this.a));
            }
            if (DJIMainFragmentActivity.h(this.a) == 1 && a.c()) {
                a.a(false);
                DJILibraryFragment dJILibraryFragment = (DJILibraryFragment) DJIMainFragmentActivity.e(this.a)[1];
                if (dJILibraryFragment == null || dJILibraryFragment.a == null || dJILibraryFragment.a.r == null) {
                    Log.i(a.a, "null");
                } else {
                    dJILibraryFragment.a.r.showTipLibrary();
                }
            }
            if (DJIMainFragmentActivity.h(this.a) == 0) {
                e.b(c.e.eI_);
            } else if (DJIMainFragmentActivity.h(this.a) == 1) {
                e.b(c.e.eJ_);
            } else if (DJIMainFragmentActivity.h(this.a) == 2) {
                e.b(c.e.eK_);
            } else if (DJIMainFragmentActivity.h(this.a) == 3) {
                e.b(c.e.eA_);
            }
        }
    }
}
