package dji.pilot.store;

import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIFragmentActivity;
import dji.pilot.publics.objects.d;

public class DJIStoreActivity extends DJIFragmentActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.store_view);
    }

    protected d a() {
        return (d) this.g.findFragmentByTag("store");
    }
}
