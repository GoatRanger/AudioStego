package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.set.R;
import dji.pilot.set.g;
import dji.pilot.set.view.base.SetLocalSwitchView;

public class VideoCacheSetterView extends SetLocalSwitchView {
    public VideoCacheSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected String getKey() {
        return g.q;
    }

    protected int getTitleId() {
        return R.string.fpv_gensetting_videocache_title;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
    }

    protected boolean getDefaultValue() {
        return true;
    }
}
