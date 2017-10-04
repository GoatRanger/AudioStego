package dji.pilot.fpv.activity;

import android.view.MotionEvent;
import dji.pilot.visual.view.VisualScreenTouchView.a;

class DJIBaseNewPreviewActivity$34 implements a {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$34(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void a(MotionEvent motionEvent) {
        this.a.i.handleMotion4LongPress(motionEvent);
    }
}
