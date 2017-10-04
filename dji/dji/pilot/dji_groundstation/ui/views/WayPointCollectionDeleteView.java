package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.pilot.visual.a.d;

public class WayPointCollectionDeleteView extends ImageView {
    private static final String a = "WayPointCollectionDeleteView";
    private Handler b = new Handler(Looper.getMainLooper());
    private Runnable c = new Runnable(this) {
        final /* synthetic */ WayPointCollectionDeleteView a;

        {
            this.a = r1;
        }

        public void run() {
            if (e.getInstance().j() != null) {
                this.a.setVisibility(0);
                return;
            }
            this.a.setVisibility(8);
            this.a.b.postDelayed(this, 500);
        }
    };

    public WayPointCollectionDeleteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setAlpha(d.c);
        this.b.post(this.c);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                setAlpha(1.0f);
                break;
            case 1:
                setAlpha(d.c);
                break;
            default:
                setAlpha(d.c);
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
