package dji.pilot.fpv.view;

import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;

class DJIErrorPopView$1 implements OnClickListener {
    final /* synthetic */ DJIErrorPopView a;

    DJIErrorPopView$1(DJIErrorPopView dJIErrorPopView) {
        this.a = dJIErrorPopView;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.zz) {
            DJIErrorPopView.a(this.a, view.getTag());
        }
    }
}
