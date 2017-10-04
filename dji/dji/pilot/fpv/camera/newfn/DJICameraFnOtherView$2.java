package dji.pilot.fpv.camera.newfn;

import android.util.Log;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJICameraFnOtherView$2 implements d {
    final /* synthetic */ DJICameraFnOtherView a;

    DJICameraFnOtherView$2(DJICameraFnOtherView dJICameraFnOtherView) {
        this.a = dJICameraFnOtherView;
    }

    public void onSuccess(Object obj) {
        Log.i(getClass().getSimpleName(), "set gimbal lock onSuccess");
    }

    public void onFailure(a aVar) {
        Log.i(getClass().getSimpleName(), "set gimbal lock onFailure");
    }
}
