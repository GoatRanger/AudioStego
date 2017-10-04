package dji.pilot.fpv.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIPreviewActivityTomato extends DJIBaseNewPreviewActivity implements VideoSurfaceView$a {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onEventMainThread(ProductType productType) {
        switch (1.a[productType.ordinal()]) {
            case 1:
                return;
            default:
                finishThis();
                return;
        }
    }

    public void a() {
        super.a();
        View inflate = View.inflate(this, R.layout.fpv_preview_surface, null);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenWidth, screenHeight);
        layoutParams.addRule(13, -1);
        inflate.setLayoutParams(layoutParams);
        ((DJIRelativeLayout) findViewById(R.id.a1v)).addView(inflate, 0);
    }

    protected void d() {
        if (this.r.c()) {
            boolean z;
            if (this.M) {
                z = false;
            } else {
                z = true;
            }
            this.M = z;
            if (this.M) {
                a(false);
            } else {
                b(false);
            }
        }
    }
}
