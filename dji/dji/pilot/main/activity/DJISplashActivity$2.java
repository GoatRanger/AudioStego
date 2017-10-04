package dji.pilot.main.activity;

import android.view.View;
import android.view.View.OnClickListener;

class DJISplashActivity$2 implements OnClickListener {
    final /* synthetic */ DJISplashActivity a;

    DJISplashActivity$2(DJISplashActivity dJISplashActivity) {
        this.a = dJISplashActivity;
    }

    public void onClick(View view) {
        DJISplashActivity.a(this.a).setEnabled(false);
        DJISplashActivity.a(this.a, false);
    }
}
