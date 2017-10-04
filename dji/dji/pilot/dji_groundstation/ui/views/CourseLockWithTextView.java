package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.controller.DataMgr.b;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class CourseLockWithTextView extends DJIRelativeLayout {
    private static final String a = "CourseLockWithTextView";
    private CourseLockView b = null;
    private DJITextView c = null;
    private Handler d = new Handler(Looper.getMainLooper());
    private Runnable e = new Runnable(this) {
        final /* synthetic */ CourseLockWithTextView a;

        {
            this.a = r1;
        }

        public void run() {
            if (b.getInstance().j() && b.getInstance().i()) {
                this.a.b.forceLock();
                b.getInstance().a(false);
            } else if (b.getInstance().i()) {
                this.a.b.lock(true);
            } else {
                this.a.b.updateRotate();
                this.a.b.postInvalidate();
            }
            this.a.c.setText(String.format("%d°", new Object[]{Integer.valueOf((int) this.a.b.getDegree())}));
            this.a.d.postDelayed(this, 100);
        }
    };

    public CourseLockWithTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.view_courselock_with_text, this);
        this.d.post(this.e);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (CourseLockView) findViewById(R.id.courselock_view);
        this.c = (DJITextView) findViewById(R.id.courselock_degree);
    }
}
