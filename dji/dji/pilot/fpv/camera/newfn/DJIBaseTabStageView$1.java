package dji.pilot.fpv.camera.newfn;

class DJIBaseTabStageView$1 implements Runnable {
    final /* synthetic */ DJIBaseTabStageView a;

    DJIBaseTabStageView$1(DJIBaseTabStageView dJIBaseTabStageView) {
        this.a = dJIBaseTabStageView;
    }

    public void run() {
        this.a.b.show();
        this.a.b.startAnimation(this.a.l);
    }
}
