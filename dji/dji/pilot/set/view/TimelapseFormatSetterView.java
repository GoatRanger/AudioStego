package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import dji.logic.f.b;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetVideoRecordMode;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.a;
import dji.pilot.set.g;
import dji.pilot.set.view.base.SetTextView;

public class TimelapseFormatSetterView extends SetTextView {
    public TimelapseFormatSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (b.m(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    protected int getValuesId() {
        return R.array.camera_timelapseformat_value_array;
    }

    protected int getStringArrayId() {
        return R.array.camera_timelapseformat_str_array;
    }

    protected int getTitleId() {
        return R.string.fpv_camera_timelapseformat;
    }

    protected void a() {
        this.c = DataCameraGetPushShotParams.getInstance().getTimelapseSaveType();
        setValueView(this.c);
    }

    public void setValue(final int i) {
        DataCameraSetVideoRecordMode dataCameraSetVideoRecordMode = new DataCameraSetVideoRecordMode();
        dataCameraSetVideoRecordMode.a(1, 50, 300).b(i);
        dataCameraSetVideoRecordMode.start(new d(this) {
            final /* synthetic */ TimelapseFormatSetterView b;

            public void onSuccess(Object obj) {
                Log.d("kevin 11.5", "succeed:");
                a.a(this.b.getContext(), g.i, i);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Log.d("kevin 11.5", "onFailure:" + aVar);
            }
        });
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }
}
