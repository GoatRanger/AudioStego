package dji.pilot.fpv.activity;

class DJIPreviewActivity$18 implements Runnable {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$18(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void run() {
        if (!DJIPreviewActivity.N(this.a)) {
            DJIPreviewActivity.O(this.a).setBackgroundColor(this.a.getResources().getColor(17170444));
            DJIPreviewActivity.g(this.a, true);
        }
        if (DJIPreviewActivity.P(this.a).isShown()) {
            DJIPreviewActivity.P(this.a).animate().setDuration(500).alpha(0.0f).withEndAction(new Runnable(this) {
                final /* synthetic */ DJIPreviewActivity$18 a;

                {
                    this.a = r1;
                }

                public void run() {
                    DJIPreviewActivity.P(this.a.a).hide();
                }
            }).start();
        }
        DJIPreviewActivity.i(this.a);
        DJIPreviewActivity.Q(this.a);
        DJIPreviewActivity.R(this.a);
    }
}
