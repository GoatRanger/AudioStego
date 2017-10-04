package dji.pilot.liveshare.Weibo.view;

import android.app.Activity;
import dji.pilot.R;
import dji.pilot.liveshare.Weibo.a.b;
import dji.pilot.liveshare.base.view.LiveBaseManageView;

public class LiveWBManageView extends LiveBaseManageView {
    public LiveWBManageView(Activity activity) {
        super(activity);
    }

    protected void initWidgetType() {
        this.mOverButton.setBackground(getResources().getDrawable(R.drawable.liveshare_weibo_start_btn));
    }

    protected void stopStream() {
        b.getInstance(this.mActivity).updateStreamStatus();
        dji.pilot.liveshare.b.getInstance().stopStream();
    }
}
