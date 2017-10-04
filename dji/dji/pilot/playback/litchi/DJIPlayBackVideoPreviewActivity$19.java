package dji.pilot.playback.litchi;

import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.media.i.d;
import dji.midware.media.i.d.c;

class DJIPlayBackVideoPreviewActivity$19 implements c {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$19(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void a(d dVar, DJIAlbumPullErrorType dJIAlbumPullErrorType) {
        DJILogHelper.getInstance().LOGD("mediaPlayer", "error=" + dJIAlbumPullErrorType, true, true);
    }
}
