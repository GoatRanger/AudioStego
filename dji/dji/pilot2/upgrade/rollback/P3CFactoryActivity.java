package dji.pilot2.upgrade.rollback;

import android.app.Activity;
import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;

public class P3CFactoryActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.v2_p3c_factory_layout);
    }
}
