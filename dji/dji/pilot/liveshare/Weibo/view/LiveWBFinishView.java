package dji.pilot.liveshare.Weibo.view;

import android.app.Activity;
import dji.pilot.R;
import dji.pilot.liveshare.base.view.LiveBaseFinishView;

public class LiveWBFinishView extends LiveBaseFinishView {
    public LiveWBFinishView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        this.mFinishBtn.setBackground(getResources().getDrawable(R.drawable.liveshare_weibo_start_btn));
    }
}
