package dji.pilot.groundStation.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIGSWayPointStartConfirmView extends DJILinearLayout implements a {
    private OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ DJIGSWayPointStartConfirmView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aoj:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    return;
                case R.id.aok:
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_wait_upload_mission_view, 11, false);
                    return;
                default:
                    return;
            }
        }
    };

    public DJIGSWayPointStartConfirmView(Context context, AttributeSet attributeSet) {
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
            findViewById(R.id.aoj).setOnClickListener(this.a);
            findViewById(R.id.aok).setOnClickListener(this.a);
        }
    }
}
