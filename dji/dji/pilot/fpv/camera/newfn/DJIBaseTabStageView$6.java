package dji.pilot.fpv.camera.newfn;

import android.view.View;
import android.view.View.OnClickListener;

class DJIBaseTabStageView$6 implements OnClickListener {
    final /* synthetic */ DJIBaseTabStageView a;

    DJIBaseTabStageView$6(DJIBaseTabStageView dJIBaseTabStageView) {
        this.a = dJIBaseTabStageView;
    }

    public void onClick(View view) {
        int i = 0;
        int id = view.getId();
        int length = this.a.w.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.a.g[i2].getVisibility() != 0) {
                i++;
            } else if (id == this.a.w[i2]) {
                this.a.b(i2, i);
                return;
            }
        }
    }
}
