package dji.pilot2.nativeexplore.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import dji.pilot.R;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class DJI360WebViewActivity extends DJISupportShareWebviewActivity {
    private View W;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (DJIOriLayout.getDeviceType() != DJIDeviceType.Pad) {
            setRequestedOrientation(4);
        } else {
            setRequestedOrientation(6);
        }
        this.W = findViewById(R.id.i4);
        if (DJIOriLayout.getDeviceType().equals(DJIDeviceType.Phone) && getResources().getConfiguration().orientation != 1) {
            a();
            this.W.setFitsSystemWindows(false);
            this.W.setPadding(0, 0, 0, 0);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (DJIOriLayout.getDeviceType().equals(DJIDeviceType.Phone)) {
            a(configuration.orientation);
            if (configuration.orientation == 1) {
                this.W.setFitsSystemWindows(true);
                return;
            }
            this.W.setFitsSystemWindows(false);
            this.W.setPadding(0, 0, 0, 0);
        }
    }

    private void a(int i) {
        if (i == 1) {
            f();
        } else {
            a();
        }
    }

    private void a() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    private void f() {
        getWindow().getDecorView().setSystemUiVisibility(1792);
    }
}
