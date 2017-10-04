package dji.pilot.playback.litchi;

import android.graphics.SurfaceTexture;
import android.view.TextureView.SurfaceTextureListener;
import dji.log.DJILogHelper;
import dji.midware.media.h.e;

class DJIPlayBackVideoPreviewActivity$2 implements SurfaceTextureListener {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$2(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        if (DJIPlayBackVideoPreviewActivity.p(this.a) != null) {
            DJIPlayBackVideoPreviewActivity.p(this.a).a(i, i2);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        DJILogHelper.getInstance().LOGD("", "mVideoDecoder=" + DJIPlayBackVideoPreviewActivity.q(this.a), false, true);
        if (DJIPlayBackVideoPreviewActivity.q(this.a) != null) {
            DJIPlayBackVideoPreviewActivity.q(this.a).setSurface(null);
            DJIPlayBackVideoPreviewActivity.q(this.a).release();
            DJIPlayBackVideoPreviewActivity.a(this.a, null);
        }
        if (DJIPlayBackVideoPreviewActivity.p(this.a) != null) {
            DJIPlayBackVideoPreviewActivity.p(this.a).c();
            DJIPlayBackVideoPreviewActivity.a(this.a, null);
        }
        return false;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        DJIPlayBackVideoPreviewActivity.a(this.a, e.a(getClass()));
        DJIPlayBackVideoPreviewActivity.p(this.a).a(surfaceTexture, i, i2);
        if (DJIPlayBackVideoPreviewActivity.q(this.a) == null) {
            DJIPlayBackVideoPreviewActivity.b(this.a, DJIPlayBackVideoPreviewActivity.p(this.a));
        } else {
            DJIPlayBackVideoPreviewActivity.q(this.a).setSurface(DJIPlayBackVideoPreviewActivity.p(this.a));
        }
    }
}
