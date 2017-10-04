package dji.pilot.fpv.activity;

class DJIPreviewActivityGrape$26 implements Runnable {
    DJIPreviewActivityGrape$26() {
    }

    public void run() {
        if (DJIPreviewActivityGrape.g() != null) {
            DJIPreviewActivityGrape.g().dismiss();
        }
    }
}
