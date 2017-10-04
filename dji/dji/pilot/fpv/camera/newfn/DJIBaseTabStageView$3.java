package dji.pilot.fpv.camera.newfn;

import android.view.View;
import android.view.View.OnClickListener;

class DJIBaseTabStageView$3 implements OnClickListener {
    final /* synthetic */ DJIBaseTabStageView a;

    DJIBaseTabStageView$3(DJIBaseTabStageView dJIBaseTabStageView) {
        this.a = dJIBaseTabStageView;
    }

    public void onClick(View view) {
        if (this.a.r != -1 && this.a.n[this.a.r].a.canGoBack()) {
            this.a.n[this.a.r].a.destoryStageView(true);
        }
    }
}
