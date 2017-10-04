package dji.pilot.playback.litchi;

import android.view.View;
import android.view.View.OnClickListener;
import dji.logic.album.a.b.d;
import dji.logic.album.a.b.f;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.mode.PhotoPreviewInfo;

class DJIPlayBackPhotoPreviewActivity$7 implements OnClickListener {
    final /* synthetic */ DJIPlayBackPhotoPreviewActivity a;

    DJIPlayBackPhotoPreviewActivity$7(DJIPlayBackPhotoPreviewActivity dJIPlayBackPhotoPreviewActivity) {
        this.a = dJIPlayBackPhotoPreviewActivity;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bhm) {
            e.a("PlayBack_AlbumPreview_TopBarView_Button_Back");
            if (h.getInstance().a) {
                DJIPlayBackPhotoPreviewActivity.f(this.a);
            } else {
                this.a.finishThis();
            }
        } else if (id == R.id.bhs) {
            DJIPlayBackPhotoPreviewActivity.a(this.a, (PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(DJIPlayBackPhotoPreviewActivity.g(this.a)));
        } else if (id == R.id.bho) {
            e.a("PlayBack_AlbumPreview_TopBarView_Button_Share");
            if (DJIPlayBackPhotoPreviewActivity.i(this.a) == 1) {
                DJIPlayBackPhotoPreviewActivity.j(this.a);
            }
        } else if (id == R.id.bhp) {
            e.a("PlayBack_AlbumPreview_TopBarView_Button_PhotoInfo");
            if (DJIPlayBackPhotoPreviewActivity.i(this.a) == 1) {
                DJIPlayBackPhotoPreviewActivity.k(this.a);
            }
        } else if (id == R.id.bht) {
            e.a("PlayBack_AlbumPreview_BottomBarView_Button_Delete");
            DJIPlayBackPhotoPreviewActivity.l(this.a);
        } else if (id == R.id.bhu) {
            e.a("PlayBack_AlbumPreview_BottomBarView_Button_Download");
            if (((((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(DJIPlayBackPhotoPreviewActivity.g(this.a))).v == f.c.a() || ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(DJIPlayBackPhotoPreviewActivity.g(this.a))).v == f.d.a()) && d.find(((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(DJIPlayBackPhotoPreviewActivity.g(this.a))).r).c()) || ((PhotoPreviewInfo) DJIPlayBackPhotoPreviewActivity.h(this.a).get(DJIPlayBackPhotoPreviewActivity.g(this.a))).v == f.b.a()) {
                DJIPlayBackPhotoPreviewActivity.m(this.a);
                DJIPlayBackPhotoPreviewActivity.a(this.a, false);
                return;
            }
            DJIPlayBackPhotoPreviewActivity.n(this.a);
        } else if (id == R.id.bhv) {
            if (h.getInstance().a) {
                e.a("PlayBack_AlbumPreview_BottomBarView_Button_CancelDownload");
                DJIPlayBackPhotoPreviewActivity.f(this.a);
                return;
            }
            e.a("PlayBack_AlbumPreview_BottomBarView_Button_Cancel");
            DJIPlayBackPhotoPreviewActivity.a(this.a);
            DJIPlayBackPhotoPreviewActivity.b(this.a).setPagingEnabled(true);
        } else if (4096 <= id && id < DJIPlayBackPhotoPreviewActivity.o(this.a).length + 4096) {
            DJIPlayBackPhotoPreviewActivity.p(this.a);
        }
    }
}
