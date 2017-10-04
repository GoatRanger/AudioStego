package dji.pilot.fpv.activity;

import android.os.Bundle;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIFragmentActivity;
import dji.pilot.publics.objects.d;
import dji.pilot.usercenter.b.a;

public class DJIPBAlbumActivity extends DJIFragmentActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DJIApplication dJIApplication = (DJIApplication) getApplication();
        if (dJIApplication != null) {
            dJIApplication.b();
        }
        setContentView(R.layout.fpv_pb_album_view);
    }

    protected d a() {
        return (d) this.g.findFragmentByTag("album");
    }

    protected void onDestroy() {
        super.onDestroy();
        a.getInstance().d(this);
    }
}
