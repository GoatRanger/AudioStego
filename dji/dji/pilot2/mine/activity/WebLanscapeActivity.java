package dji.pilot2.mine.activity;

import android.os.Bundle;
import dji.pilot.publics.objects.DJIApplication;
import dji.publics.DJIUI.DJIOriLayout;

public class WebLanscapeActivity extends WebActivity {
    protected void onCreate(Bundle bundle) {
        this.v = true;
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        DJIOriLayout.setOrientation(this, 6);
    }
}
