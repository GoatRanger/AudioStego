package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJILinearLayout;

public class NfzCancelView extends DJILinearLayout implements a {
    private DJIStateTextView a;

    public NfzCancelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (DJIStateTextView) findViewById(R.id.t8);
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NfzCancelView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                View view2 = (View) this.a.getParent();
                if (view2 instanceof DJIStageView) {
                    ((DJIStageView) view2).stop();
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
