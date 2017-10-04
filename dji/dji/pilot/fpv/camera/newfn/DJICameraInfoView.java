package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import dji.midware.data.model.P3.DataCameraGetShotInfo;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a$a;
import dji.pilot.fpv.camera.more.b;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.e.d;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJICameraInfoView extends ScrollView implements a {
    private DJITextView a = null;

    public DJICameraInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.a = (DJITextView) findViewById(R.id.ll);
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    private void a() {
        DataCameraGetShotInfo instance = DataCameraGetShotInfo.getInstance();
        CharSequence name = instance.getName();
        if (d.a(name)) {
            this.a.setText(b.a(instance.getMemberId(), instance.getModelId(), instance.getHardVersion()));
        } else {
            this.a.setText(name);
        }
    }

    public void onEventMainThread(a$a dji_pilot_fpv_camera_more_a_a) {
        if (a$a.CAMERAINFO_GETTED == dji_pilot_fpv_camera_more_a_a) {
            a();
        }
    }

    public void dispatchOnResume() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        if (DataCameraGetShotInfo.getInstance().isGetted()) {
            a();
        } else {
            dji.pilot.fpv.camera.more.a.getInstance().a(0);
        }
    }

    public void dispatchOnPause() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public View getView() {
        return this;
    }
}
