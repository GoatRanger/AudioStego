package dji.pilot.groundStation.stage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIMainExitHelpView extends DJILinearLayout implements a {
    private OnClickListener a = new OnClickListener(this) {
        final /* synthetic */ DJIMainExitHelpView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.akf:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    return;
                default:
                    return;
            }
        }
    };

    public DJIMainExitHelpView(Context context, AttributeSet attributeSet) {
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
            findViewById(R.id.akf).setOnClickListener(this.a);
            if (i.getInstance().c() == ProductType.litchiC) {
                ((DJIImageView) findViewById(R.id.ake)).setImageResource(R.drawable.gs_main_exit_icon_p3c);
            } else {
                ((DJIImageView) findViewById(R.id.ake)).setImageResource(R.drawable.gs_main_exit_icon_p3x);
            }
        }
    }
}
