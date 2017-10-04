package dji.pilot.fpv.camera.newfn;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;

class DJICameraFnOtherView$8 implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ DJICameraFnOtherView b;

    DJICameraFnOtherView$8(DJICameraFnOtherView dJICameraFnOtherView, int i) {
        this.b = dJICameraFnOtherView;
        this.a = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (DataCameraGetPushStateInfo.getInstance().getSDCardState() == SDCardState.None && this.a == 1) {
            DJICameraFnOtherView.r(this.b);
        } else {
            DJICameraFnOtherView.s(this.b);
        }
    }
}
