package dji.pilot.playback.litchi;

import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.d.e;

class DJIPlayBackVideoPreviewActivity$7 implements OnClickListener {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$7(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bie) {
            e.a("PlayBack_AlbumPreview_TopBarView_Button_Back");
            if (DJIPlayBackVideoPreviewActivity.c() == 0) {
                this.a.finishThis();
            } else if (DJIPlayBackVideoPreviewActivity.x(this.a).a) {
                DJIPlayBackVideoPreviewActivity.y(this.a);
            } else {
                this.a.finishThis();
            }
        } else if (id == R.id.big) {
            e.a("PlayBack_AlbumPreview_TopBarView_Button_Share");
            DJIPlayBackVideoPreviewActivity.z(this.a);
        } else if (id == R.id.bii) {
            e.a("PlayBack_AlbumPreview_TopBarView_Button_Delete");
            DJIPlayBackVideoPreviewActivity.A(this.a);
        } else if (id == R.id.bih) {
            e.a("PlayBack_AlbumPreview_TopBarView_Button_Download");
            if (DJIPlayBackVideoPreviewActivity.c() == 1) {
                DJIPlayBackVideoPreviewActivity.B(this.a);
            }
        } else if (id == R.id.bhv) {
            if (DJIPlayBackVideoPreviewActivity.x(this.a).a) {
                DJIPlayBackVideoPreviewActivity.y(this.a);
                return;
            }
            DJIPlayBackVideoPreviewActivity.a(this.a).go();
            DJIPlayBackVideoPreviewActivity.e(this.a).setEnabled(true);
            DJIPlayBackVideoPreviewActivity.b(this.a).show();
            c.a();
            if (c.b(DJIPlayBackVideoPreviewActivity.c(this.a).b())) {
                DJIPlayBackVideoPreviewActivity.d(this.a).show();
                DJIPlayBackVideoPreviewActivity.a(this.a, true);
            }
        } else if (id == R.id.bim) {
            if (DJIPlayBackVideoPreviewActivity.c() == 1) {
                DJIPlayBackVideoPreviewActivity.C(this.a);
            } else if (DJIPlayBackVideoPreviewActivity.c() == 2) {
                DJIPlayBackVideoPreviewActivity.D(this.a);
            } else {
                DJIPlayBackVideoPreviewActivity.E(this.a);
            }
        } else if (id == R.id.bin) {
            if (DJIPlayBackVideoPreviewActivity.c() == 1) {
                DJIPlayBackVideoPreviewActivity.F(this.a);
            } else if (DJIPlayBackVideoPreviewActivity.c() == 2) {
                DJIPlayBackVideoPreviewActivity.G(this.a);
            } else {
                DJIPlayBackVideoPreviewActivity.H(this.a);
            }
        } else if (id == R.id.bhs) {
            e.a("PlayBack_AlbumPreview_Button_PlayVideo");
            if (DJIPlayBackVideoPreviewActivity.c() == 1) {
                DJIPlayBackVideoPreviewActivity.C(this.a);
            } else if (DJIPlayBackVideoPreviewActivity.c() == 2) {
                DJIPlayBackVideoPreviewActivity.D(this.a);
            } else {
                DJIPlayBackVideoPreviewActivity.E(this.a);
            }
            DJIPlayBackVideoPreviewActivity.e(this.a).go();
        }
    }
}
