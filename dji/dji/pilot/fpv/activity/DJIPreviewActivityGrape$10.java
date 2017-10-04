package dji.pilot.fpv.activity;

class DJIPreviewActivityGrape$10 implements Runnable {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$10(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void run() {
        if (!DJIPreviewActivityGrape.s(this.a)) {
            DJIPreviewActivityGrape.t(this.a).setBackgroundColor(this.a.getResources().getColor(17170444));
            DJIPreviewActivityGrape.d(this.a, true);
        }
        if (DJIPreviewActivityGrape.u(this.a).isShown()) {
            DJIPreviewActivityGrape.u(this.a).animate().setDuration(500).alpha(0.0f).withEndAction(new Runnable(this) {
                final /* synthetic */ DJIPreviewActivityGrape$10 a;

                {
                    this.a = r1;
                }

                public void run() {
                    DJIPreviewActivityGrape.u(this.a.a).hide();
                }
            }).start();
        }
    }
}
