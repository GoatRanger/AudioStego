package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import dji.pilot.R;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.concurrent.Semaphore;

public class DJIGSWayPointWaitUploadMissionView extends DJIRelativeLayout implements c$i, a {
    private ProgressBar n = null;
    private DJITextView o = null;
    private OnClickListener p = null;
    private final Handler q = new Handler();

    public DJIGSWayPointWaitUploadMissionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
        dji.pilot.groundStation.a.a.getInstance(null).a(this);
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        this.n.setProgress(0);
        this.o.setText(String.format(getContext().getString(R.string.gs_wait_dialog_desc), new Object[]{Integer.valueOf(0)}));
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            ((DJITextView) findViewById(R.id.aov)).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIGSWayPointWaitUploadMissionView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.p != null) {
                        this.a.p.onClick(view);
                    }
                }
            });
            this.n = (ProgressBar) findViewById(R.id.aoy);
            this.n.setProgress(0);
            this.o = (DJITextView) findViewById(R.id.aoz);
            this.o.setText(String.format(getContext().getString(R.string.gs_wait_dialog_desc), new Object[]{Integer.valueOf(0)}));
        }
    }

    public void setOnCancelListener(OnClickListener onClickListener) {
        this.p = onClickListener;
    }

    public void dismiss(final boolean z, final boolean z2) {
        final Semaphore semaphore = new Semaphore(0);
        this.q.post(new Runnable(this) {
            final /* synthetic */ DJIGSWayPointWaitUploadMissionView d;

            public void run() {
                if (z2) {
                    dji.pilot.groundStation.a.a.getInstance(null).y();
                } else if (z) {
                    e.c(c$i.i);
                    ((DJIStageView) this.d.getParent()).createStageView(R.layout.gs_way_point_status_view, 21, false);
                } else {
                    ((DJIStageView) this.d.getParent()).stop();
                }
                semaphore.release();
            }
        });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
        }
    }

    public void setProgress(final int i) {
        this.q.post(new Runnable(this) {
            final /* synthetic */ DJIGSWayPointWaitUploadMissionView b;

            public void run() {
                this.b.n.setProgress(i);
                this.b.o.setText(String.format(this.b.getContext().getString(R.string.gs_wait_dialog_desc), new Object[]{Integer.valueOf(i)}));
            }
        });
    }
}
