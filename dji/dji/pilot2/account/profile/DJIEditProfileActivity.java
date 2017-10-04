package dji.pilot2.account.profile;

import android.os.Bundle;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;

public class DJIEditProfileActivity extends DJIActivityNoFullScreen {
    public static final String a = "fromSignUp";
    private DJIEditProfileFragment b;
    private dji.pilot2.account.profile.a.a c;

    public enum a {
        TRUE,
        FALSE
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_profile);
        a();
        this.c.a(0);
    }

    private void a() {
        this.b = (DJIEditProfileFragment) getFragmentManager().findFragmentById(R.id.cf7);
        this.c = new b(this.b, this);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.c.a();
    }
}
