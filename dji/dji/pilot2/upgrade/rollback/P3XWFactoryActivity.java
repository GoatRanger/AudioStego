package dji.pilot2.upgrade.rollback;

import android.app.Activity;
import android.os.Bundle;
import dji.pilot.R;

public class P3XWFactoryActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        P3cFactoryView.a = 2;
        setContentView(R.layout.v2_p3c_factory_layout);
    }
}
