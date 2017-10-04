package dji.pilot.playback.litchi;

import android.graphics.SurfaceTexture;
import android.view.TextureView.SurfaceTextureListener;
import dji.log.DJILogHelper;
import dji.midware.media.h.e;

class DJIPlayBackVideoPreviewActivity$3 implements SurfaceTextureListener {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$3(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        DJILogHelper.getInstance().LOGD("", "mVideoDecoder=" + DJIPlayBackVideoPreviewActivity.q(this.a), false, true);
        if (DJIPlayBackVideoPreviewActivity.r(this.a) != null) {
            DJIPlayBackVideoPreviewActivity.r(this.a).a(null);
            DJIPlayBackVideoPreviewActivity.r(this.a).b();
            DJIPlayBackVideoPreviewActivity.a(this.a, null);
        }
        if (DJIPlayBackVideoPreviewActivity.p(this.a) != null) {
            DJIPlayBackVideoPreviewActivity.p(this.a).c();
            DJIPlayBackVideoPreviewActivity.a(this.a, null);
        }
        return false;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "onSurfaceTextureAvailable mVideoDecoder", false, true);
        DJIPlayBackVideoPreviewActivity.a(this.a, e.a(getClass()));
        DJIPlayBackVideoPreviewActivity.p(this.a).a(surfaceTexture, i, i2);
        if (DJIPlayBackVideoPreviewActivity.q(this.a) == null) {
            DJIPlayBackVideoPreviewActivity.c(this.a, DJIPlayBackVideoPreviewActivity.p(this.a));
        } else {
            DJIPlayBackVideoPreviewActivity.r(this.a).a(DJIPlayBackVideoPreviewActivity.p(this.a));
        }
    }
}
