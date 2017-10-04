package dji.pilot.fpv.camera.newfn;

class DJIBaseTabStageView$2 implements Runnable {
    final /* synthetic */ DJIBaseTabStageView a;

    DJIBaseTabStageView$2(DJIBaseTabStageView dJIBaseTabStageView) {
        this.a = dJIBaseTabStageView;
    }

    public void run() {
        this.a.b.startAnimation(this.a.m);
    }
}
