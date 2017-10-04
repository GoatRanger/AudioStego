package dji.pilot.playback.litchi;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class DJIPlayBackPhotoPreviewActivity$2 implements OnClickListener {
    final /* synthetic */ DJIPlayBackPhotoPreviewActivity a;

    DJIPlayBackPhotoPreviewActivity$2(DJIPlayBackPhotoPreviewActivity dJIPlayBackPhotoPreviewActivity) {
        this.a = dJIPlayBackPhotoPreviewActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DJIPlayBackPhotoPreviewActivity.u(this.a).dismiss();
    }
}
