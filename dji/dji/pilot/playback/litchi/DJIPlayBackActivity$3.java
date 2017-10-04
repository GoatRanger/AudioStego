package dji.pilot.playback.litchi;

import android.view.View;
import android.view.View.OnClickListener;
import com.dji.frame.c.b;
import dji.pilot.R;
import dji.pilot.fpv.activity.DJIPBAlbumActivity;
import dji.pilot.fpv.d.e;

class DJIPlayBackActivity$3 implements OnClickListener {
    final /* synthetic */ DJIPlayBackActivity a;

    DJIPlayBackActivity$3(DJIPlayBackActivity dJIPlayBackActivity) {
        this.a = dJIPlayBackActivity;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.bi0 == id) {
            e.a("PlayBack_TopBarView_Button_BackHome");
            this.a.b().j();
            this.a.finishThis();
        } else if (R.id.bi7 == id) {
            if (this.a.a.getCurrentTab() == 1) {
                this.a.b().h();
            } else if (this.a.a.getCurrentTab() == 0) {
                this.a.b().h();
            }
        } else if (R.id.bi8 == id && this.a.a.getCurrentTab() != 1 && this.a.a.getCurrentTab() == 0) {
            e.a("PlayBack_TopBarView_Button_LocalAlbum");
            b.a(this.a, DJIPBAlbumActivity.class);
        }
    }
}
