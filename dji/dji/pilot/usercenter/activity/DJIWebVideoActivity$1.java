package dji.pilot.usercenter.activity;

import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;

class DJIWebVideoActivity$1 implements OnClickListener {
    final /* synthetic */ DJIWebVideoActivity a;

    DJIWebVideoActivity$1(DJIWebVideoActivity dJIWebVideoActivity) {
        this.a = dJIWebVideoActivity;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.dak) {
            this.a.finish();
        }
    }
}
