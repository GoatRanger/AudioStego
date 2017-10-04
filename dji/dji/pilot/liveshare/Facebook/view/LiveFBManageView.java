package dji.pilot.liveshare.Facebook.view;

import android.app.Activity;
import dji.pilot.R;
import dji.pilot.liveshare.b;
import dji.pilot.liveshare.base.view.LiveBaseManageView;

public class LiveFBManageView extends LiveBaseManageView {
    public LiveFBManageView(Activity activity) {
        super(activity);
    }

    protected void initWidgetType() {
        this.mOverButton.setBackground(getResources().getDrawable(R.drawable.liveshare_facebook_start_btn));
    }

    protected void stopStream() {
        b.getInstance().stopStream();
    }
}
