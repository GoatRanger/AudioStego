package dji.pilot.fpv.activity;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import dji.pilot.fpv.model.m;

class DJIPreviewActivity$19 implements Runnable {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$19(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void run() {
        if (!DJIPreviewActivity.P(this.a).isShown()) {
            try {
                Drawable bitmapDrawable = new BitmapDrawable(this.a.getResources(), this.a.D());
                bitmapDrawable.setColorFilter(m.b());
                DJIPreviewActivity.P(this.a).setBackground(bitmapDrawable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DJIPreviewActivity.P(this.a).animate().setDuration(500).alpha(1.0f).withStartAction(new Runnable(this) {
                final /* synthetic */ DJIPreviewActivity$19 a;

                {
                    this.a = r1;
                }

                public void run() {
                    DJIPreviewActivity.P(this.a.a).show();
                }
            }).start();
        }
    }
}
