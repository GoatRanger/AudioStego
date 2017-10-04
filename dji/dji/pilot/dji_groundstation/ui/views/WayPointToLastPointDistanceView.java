package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.TextView;
import com.here.android.mpa.mapping.Map;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem$WayPoint;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class WayPointToLastPointDistanceView extends TextView {
    private static final String a = "WayPointToLastPointDistanceView";
    private Handler b = new Handler(Looper.getMainLooper());
    private Runnable c = new Runnable(this) {
        final /* synthetic */ WayPointToLastPointDistanceView a;

        {
            this.a = r1;
        }

        public void run() {
            if (((float) Math.ceil(this.a.getWayPointDistanceToEnd())) < 0.0f) {
                this.a.setText(R.string.gsnew_fpv_default_str);
            } else if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(f.a(r0))}));
            } else {
                this.a.setText(String.format("%.1fM", new Object[]{Float.valueOf(r0)}));
            }
            this.a.b.postDelayed(this, 1000);
        }
    };

    public WayPointToLastPointDistanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.post(this.c);
    }

    public double getWayPointDistanceToEnd() {
        int targetWayPoint;
        DataFlycGetPushWayPointMissionInfo instance = DataFlycGetPushWayPointMissionInfo.getInstance();
        if (instance.getMissionType() == 1) {
            targetWayPoint = instance.getTargetWayPoint();
        } else {
            targetWayPoint = -1;
        }
        if (targetWayPoint == -1) {
            return Map.MOVE_PRESERVE_ZOOM_LEVEL;
        }
        DJIWPCollectionItem j = e.getInstance().j();
        if (j == null) {
            return Map.MOVE_PRESERVE_ZOOM_LEVEL;
        }
        if (j.getPoints().size() <= 0) {
            return Map.MOVE_PRESERVE_ZOOM_LEVEL;
        }
        if (targetWayPoint >= j.getPoints().size()) {
            return Map.MOVE_PRESERVE_ZOOM_LEVEL;
        }
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) j.getPoints().get(targetWayPoint);
        double a = f.a(dJIWPCollectionItem$WayPoint.lat, dJIWPCollectionItem$WayPoint.lng);
        if (a < 0.0d) {
            return Map.MOVE_PRESERVE_ZOOM_LEVEL;
        }
        double a2 = f.a() - dJIWPCollectionItem$WayPoint.getHeight();
        return f.a(j.getPoints().subList(targetWayPoint, j.getPoints().size())) + Math.sqrt((a2 * a2) + (a * a));
    }
}
