package dji.device.active;

import android.os.Bundle;
import dji.pilot.fpv.R;
import dji.publics.DJIObject.DJIBaseActivityForVirtualKey;

public class OsmoActiveActivity extends DJIBaseActivityForVirtualKey {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.osmo_active_layout);
    }
}
