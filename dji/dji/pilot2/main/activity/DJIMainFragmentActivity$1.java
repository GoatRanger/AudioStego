package dji.pilot2.main.activity;

import dji.pilot2.flymonitor.a.a;
import dji.pilot2.publics.object.DJINotificationDialog;

class DJIMainFragmentActivity$1 implements Runnable {
    final /* synthetic */ DJIMainFragmentActivity a;

    DJIMainFragmentActivity$1(DJIMainFragmentActivity dJIMainFragmentActivity) {
        this.a = dJIMainFragmentActivity;
    }

    public void run() {
        if (DJIMainFragmentActivity.a(this.a) && !DJINotificationDialog.b) {
            a aVar = new a(this.a);
            aVar.a(DJIMainFragmentActivity.b(this.a));
            aVar.show();
            DJIMainFragmentActivity.a(this.a, false);
        }
    }
}
