package dji.pilot.fpv.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.publics.widget.FpvPopWarnView;
import dji.publics.widget.FpvPopWarnView.a;

class DJIPreviewActivityLitchi$37 implements OnTouchListener {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$37(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.a.i.a();
        } else if (motionEvent.getAction() == 2 && motionEvent.getPointerCount() == 2) {
            DJIPreviewActivityLitchi.c(this.a).removeMessages(36864);
            if (b.r()) {
                this.a.i.a(motionEvent);
            } else if (b.s()) {
                FpvPopWarnView.getInstance(this.a).pop((int) R.drawable.gs_warning_icon, (int) R.string.unsupport_dzoom_4k, a.LENGTH_SHORT);
            }
        } else if (motionEvent.getAction() == 1) {
            this.a.i.a();
            DJIPreviewActivityLitchi.A(this.a);
        }
        return DJIPreviewActivityLitchi.B(this.a).onTouchEvent(motionEvent);
    }
}
