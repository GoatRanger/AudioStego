package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem$WayPoint;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class PointToPointDistanceView extends TextView {
    private static final String a = "PointToPointDistanceView";
    private Handler b = new Handler(Looper.getMainLooper());
    private Runnable c = new Runnable(this) {
        final /* synthetic */ PointToPointDistanceView a;

        {
            this.a = r1;
        }

        public void run() {
            float ceil = (float) Math.ceil(this.a.getWayPointDistanceFromStart());
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(f.a(ceil))}));
            } else {
                this.a.setText(String.format("%.1fM", new Object[]{Float.valueOf(ceil)}));
            }
            this.a.b.postDelayed(this, 300);
        }
    };

    public PointToPointDistanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b.post(this.c);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b.removeCallbacks(this.c);
    }

    public double getWayPointDistanceFromStart() {
        DJIWPCollectionItem j = e.getInstance().j();
        if (j == null || j.getPoints().size() <= 0) {
            return 0.0d;
        }
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) j.getPoints().get(0);
        double a = f.a(dJIWPCollectionItem$WayPoint.lat, dJIWPCollectionItem$WayPoint.lng);
        double a2 = f.a() - dJIWPCollectionItem$WayPoint.getHeight();
        return Math.sqrt((a2 * a2) + (a * a));
    }
}
