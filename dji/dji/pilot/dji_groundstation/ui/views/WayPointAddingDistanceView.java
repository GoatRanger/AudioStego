package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class WayPointAddingDistanceView extends TextView {
    private static final String a = "WayPointAddingDistanceView";
    private Handler b = new Handler(Looper.getMainLooper());
    private Runnable c = new Runnable(this) {
        final /* synthetic */ WayPointAddingDistanceView a;

        {
            this.a = r1;
        }

        public void run() {
            double w = e.getInstance().w();
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(f.a((float) w))}));
            } else {
                this.a.setText(String.format("%.1fM", new Object[]{Double.valueOf(w)}));
            }
            this.a.b.postDelayed(this, 1000);
        }
    };

    public WayPointAddingDistanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.post(this.c);
    }
}
