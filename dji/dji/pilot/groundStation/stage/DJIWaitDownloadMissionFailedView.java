package dji.pilot.groundStation.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIWaitDownloadMissionFailedView extends DJILinearLayout implements a {
    private OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ DJIWaitDownloadMissionFailedView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ank:
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                    return;
                case R.id.anl:
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_wait_download_mission_view, 29, false);
                    return;
                default:
                    return;
            }
        }
    };

    public DJIWaitDownloadMissionFailedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            ((DJITextView) findViewById(R.id.ank)).setOnClickListener(this.a);
            ((DJITextView) findViewById(R.id.anl)).setOnClickListener(this.a);
        }
    }
}
