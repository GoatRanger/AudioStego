package dji.pilot.fpv.activity;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import dji.pilot.fpv.model.m;

class DJIPreviewActivityGrape$11 implements Runnable {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$11(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void run() {
        if (!DJIPreviewActivityGrape.u(this.a).isShown()) {
            try {
                Drawable bitmapDrawable = new BitmapDrawable(this.a.getResources(), this.a.D());
                bitmapDrawable.setColorFilter(m.b());
                DJIPreviewActivityGrape.u(this.a).setBackground(bitmapDrawable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DJIPreviewActivityGrape.u(this.a).animate().setDuration(500).alpha(1.0f).withStartAction(new Runnable(this) {
                final /* synthetic */ DJIPreviewActivityGrape$11 a;

                {
                    this.a = r1;
                }

                public void run() {
                    DJIPreviewActivityGrape.u(this.a.a).show();
                }
            }).start();
        }
    }
}
