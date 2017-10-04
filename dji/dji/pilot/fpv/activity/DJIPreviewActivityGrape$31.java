package dji.pilot.fpv.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class DJIPreviewActivityGrape$31 implements OnTouchListener {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$31(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            DJIPreviewActivityGrape.n(this.a);
        }
        return DJIPreviewActivityGrape.o(this.a).onTouchEvent(motionEvent);
    }
}
