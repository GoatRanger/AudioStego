package dji.pilot.fpv.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class DJIPreviewActivity$44 implements OnTouchListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$44(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.i.a();
        } else if (motionEvent.getAction() == 2) {
            this.a.a(motionEvent);
        } else if (motionEvent.getAction() == 1) {
            this.a.i.a();
            DJIPreviewActivity.I(this.a);
        }
        return DJIPreviewActivity.J(this.a).onTouchEvent(motionEvent);
    }
}
