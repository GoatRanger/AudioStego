package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;

public class NfzRedZoneDetailView extends RelativeLayout implements a {
    public NfzRedZoneDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        findViewById(R.id.bfc).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NfzRedZoneDetailView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                View view2 = (View) this.a.getParent();
                if (view2 instanceof DJIStageView) {
                    ((DJIStageView) view2).destoryStageView(true);
                }
            }
        });
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
}
