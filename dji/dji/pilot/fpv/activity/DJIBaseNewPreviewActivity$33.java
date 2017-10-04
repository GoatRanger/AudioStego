package dji.pilot.fpv.activity;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.pilot.newfpv.f.d;
import dji.thirdparty.a.c;

class DJIBaseNewPreviewActivity$33 implements OnTouchListener {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$33(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            c.a().e(d.TOUCH_BLANK_PLACE);
            this.a.A.a();
        } else if (motionEvent.getAction() == 2 && motionEvent.getPointerCount() == 2) {
            DJIBaseNewPreviewActivity.c(this.a).removeMessages(36864);
            this.a.a(motionEvent);
        } else if (motionEvent.getAction() == 1) {
            this.a.A.a();
            DJIBaseNewPreviewActivity.s(this.a);
        }
        return DJIBaseNewPreviewActivity.t(this.a).onTouchEvent(motionEvent);
    }
}
