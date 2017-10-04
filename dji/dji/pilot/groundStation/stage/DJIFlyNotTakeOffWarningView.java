package dji.pilot.groundStation.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIFlyNotTakeOffWarningView extends DJILinearLayout implements a {
    private OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ DJIFlyNotTakeOffWarningView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ajf:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    return;
                default:
                    return;
            }
        }
    };

    public DJIFlyNotTakeOffWarningView(Context context, AttributeSet attributeSet) {
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
            findViewById(R.id.ajf).setOnClickListener(this.a);
        }
    }
}
