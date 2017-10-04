package dji.pilot.fpv.activity;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import dji.pilot.fpv.model.m;

class DJIBaseNewPreviewActivity$15 implements Runnable {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$15(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void run() {
        if (!this.a.d.isShown()) {
            try {
                Drawable bitmapDrawable = new BitmapDrawable(this.a.getResources(), this.a.D());
                bitmapDrawable.setColorFilter(m.b());
                this.a.d.setBackground(bitmapDrawable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.a.d.animate().setDuration(300).alpha(1.0f).withStartAction(new Runnable(this) {
                final /* synthetic */ DJIBaseNewPreviewActivity$15 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.d.show();
                }
            }).start();
        }
    }
}
