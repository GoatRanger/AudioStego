package dji.pilot2.main.activity;

import android.os.Handler.Callback;
import android.os.Message;
import dji.pilot.R;
import dji.pilot.publics.control.a$c;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.thirdparty.a.c;

class DJIMainFragmentActivity$2 implements Callback {
    final /* synthetic */ DJIMainFragmentActivity a;

    DJIMainFragmentActivity$2(DJIMainFragmentActivity dJIMainFragmentActivity) {
        this.a = dJIMainFragmentActivity;
    }

    public boolean handleMessage(Message message) {
        DJILibraryFragment dJILibraryFragment = (DJILibraryFragment) this.a.getFragmentManager().findFragmentById(R.id.c9c);
        if (dJILibraryFragment != null) {
            switch (message.what) {
                case 0:
                    dJILibraryFragment.a.q.beginPullFile(true);
                    break;
                case 1:
                    if (DJIMainFragmentActivity.g() == 2) {
                        dJILibraryFragment.a.showTitleBar();
                        this.a.f();
                    }
                    dJILibraryFragment.a.q.clearAlbumDirectoryInfo();
                    break;
                case 2:
                    if (!DJIMainFragmentActivity.l(this.a).booleanValue()) {
                        DJIMainFragmentActivity.a(this.a, Boolean.valueOf(true));
                        DJIMainFragmentActivity.n(this.a).startAnimation(DJIMainFragmentActivity.m(this.a));
                        DJIMainFragmentActivity.n(this.a).setVisibility(0);
                        break;
                    }
                    break;
                case 3:
                    if (DJIMainFragmentActivity.l(this.a).booleanValue()) {
                        DJIMainFragmentActivity.a(this.a, Boolean.valueOf(false));
                        DJIMainFragmentActivity.n(this.a).clearAnimation();
                        DJIMainFragmentActivity.n(this.a).setVisibility(8);
                        break;
                    }
                    break;
                case 4:
                    dJILibraryFragment.a.exitSelectMode();
                    this.a.f();
                    break;
                case 5:
                    dJILibraryFragment.a.showTitleBar();
                    this.a.f();
                    break;
                case 6:
                    c.a().e(a$c.SHOW);
                    break;
                default:
                    break;
            }
        }
        return false;
    }
}
