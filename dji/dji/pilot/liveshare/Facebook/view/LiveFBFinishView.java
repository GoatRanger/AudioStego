package dji.pilot.liveshare.Facebook.view;

import android.app.Activity;
import dji.pilot.R;
import dji.pilot.liveshare.base.view.LiveBaseFinishView;

public class LiveFBFinishView extends LiveBaseFinishView {
    public LiveFBFinishView(Activity activity) {
        super(activity);
    }

    protected void initTypeWidget() {
        this.mFinishBtn.setBackground(getResources().getDrawable(R.drawable.liveshare_facebook_start_btn));
    }
}
