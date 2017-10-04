package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.pilot.dji_groundstation.controller.DataMgr.e;

public class WayPointCountView extends TextView {
    private static final String a = "WayPointCountView";
    private Handler b = new Handler(Looper.getMainLooper());
    private Runnable c = new Runnable(this) {
        final /* synthetic */ WayPointCountView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.setText(Integer.toString(e.getInstance().v()));
            this.a.b.postDelayed(this, 500);
        }
    };

    public WayPointCountView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.post(this.c);
    }
}
