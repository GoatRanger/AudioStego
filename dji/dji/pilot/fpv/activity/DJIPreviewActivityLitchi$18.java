package dji.pilot.fpv.activity;

class DJIPreviewActivityLitchi$18 implements Runnable {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$18(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void run() {
        if (!DJIPreviewActivityLitchi.F(this.a)) {
            DJIPreviewActivityLitchi.G(this.a).setBackgroundColor(this.a.getResources().getColor(17170444));
            DJIPreviewActivityLitchi.e(this.a, true);
        }
        if (DJIPreviewActivityLitchi.H(this.a).isShown()) {
            DJIPreviewActivityLitchi.H(this.a).animate().setDuration(500).alpha(0.0f).withEndAction(new Runnable(this) {
                final /* synthetic */ DJIPreviewActivityLitchi$18 a;

                {
                    this.a = r1;
                }

                public void run() {
                    DJIPreviewActivityLitchi.H(this.a.a).hide();
                }
            }).start();
        }
        DJIPreviewActivityLitchi.i(this.a);
        DJIPreviewActivityLitchi.I(this.a);
    }
}
