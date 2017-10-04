package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.set.R;
import dji.pilot.set.g;
import dji.pilot.set.view.base.SetLocalSwitchView;

public class AutoClearVideoCacheSetterView extends SetLocalSwitchView {
    public AutoClearVideoCacheSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected String getKey() {
        return g.r;
    }

    protected int getTitleId() {
        return R.string.fpv_gensetting_videocache_autoclean;
    }
}
