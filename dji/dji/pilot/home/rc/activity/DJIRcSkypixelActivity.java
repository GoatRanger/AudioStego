package dji.pilot.home.rc.activity;

import android.os.Bundle;
import com.dji.frame.c.c;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;

public class DJIRcSkypixelActivity extends DJIActivityNoFullScreen {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.rc_main_skypiel);
    }

    private void a() {
    }

    protected void onDestroy() {
        super.onDestroy();
        c.a(this).e();
    }
}
