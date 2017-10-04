package dji.pilot.fpv.camera.newfn;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class DJICameraFnOtherView$5 implements OnCheckedChangeListener {
    final /* synthetic */ DJICameraFnOtherView a;

    DJICameraFnOtherView$5(DJICameraFnOtherView dJICameraFnOtherView) {
        this.a = dJICameraFnOtherView;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        DJICameraFnOtherView.a(this.a, compoundButton, z);
    }
}
