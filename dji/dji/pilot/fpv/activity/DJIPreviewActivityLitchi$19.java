package dji.pilot.fpv.activity;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import dji.pilot.fpv.model.m;

class DJIPreviewActivityLitchi$19 implements Runnable {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$19(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void run() {
        if (!DJIPreviewActivityLitchi.H(this.a).isShown()) {
            try {
                Drawable bitmapDrawable = new BitmapDrawable(this.a.getResources(), this.a.D());
                bitmapDrawable.setColorFilter(m.b());
                DJIPreviewActivityLitchi.H(this.a).setBackground(bitmapDrawable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DJIPreviewActivityLitchi.H(this.a).animate().setDuration(300).alpha(1.0f).withStartAction(new Runnable(this) {
                final /* synthetic */ DJIPreviewActivityLitchi$19 a;

                {
                    this.a = r1;
                }

                public void run() {
                    DJIPreviewActivityLitchi.H(this.a.a).show();
                }
            }).start();
        }
    }
}
