package dji.pilot.publics.objects;

import dji.pilot.fpv.leftmenu.b;

class DJIBaseActivity$2 implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ DJIBaseActivity b;

    DJIBaseActivity$2(DJIBaseActivity dJIBaseActivity, b bVar) {
        this.b = dJIBaseActivity;
        this.a = bVar;
    }

    public void run() {
        if (!this.b.isFinishing()) {
            this.a.show();
        }
    }
}
