package dji.pilot.fpv.activity;

class DJIPreviewActivity$37 implements Runnable {
    DJIPreviewActivity$37() {
    }

    public void run() {
        if (DJIPreviewActivity.j() != null) {
            DJIPreviewActivity.j().dismiss();
        }
    }
}
