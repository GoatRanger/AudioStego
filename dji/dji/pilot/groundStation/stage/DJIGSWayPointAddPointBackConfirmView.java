package dji.pilot.groundStation.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIGSWayPointAddPointBackConfirmView extends DJILinearLayout implements a {
    private OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ DJIGSWayPointAddPointBackConfirmView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.anq:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    return;
                case R.id.anr:
                    dji.pilot.groundStation.a.a.getInstance(null).i().l().g();
                    dji.pilot.groundStation.a.a.getInstance(null).M();
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_view, 5, false);
                    return;
                default:
                    return;
            }
        }
    };

    public DJIGSWayPointAddPointBackConfirmView(Context context, AttributeSet attributeSet) {
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
            findViewById(R.id.anq).setOnClickListener(this.a);
            findViewById(R.id.anr).setOnClickListener(this.a);
        }
    }
}
