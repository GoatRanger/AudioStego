package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetPushChart;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetSwitchView;

public class HistogramSetterView extends SetSwitchView {
    public HistogramSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        this.d = DataCameraGetPushStateInfo.getInstance().isHistogramEnable();
        setValueView(this.d);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }

    protected void setValue(boolean z) {
        DataCameraSetPushChart.getInstance().a(z).start(this.e);
    }

    protected int getTitleId() {
        return R.string.set_histogram;
    }
}
