package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class NfzSuccessView extends DJIRelativeLayout implements a {
    public NfzSuccessView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            findViewById(R.id.bfr).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NfzSuccessView a;

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
            DJIStateTextView dJIStateTextView = (DJIStateTextView) findViewById(R.id.bfq);
            dJIStateTextView.getPaint().setFlags(8);
            dJIStateTextView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ NfzSuccessView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    View view2 = (View) this.a.getParent();
                    if (view2 instanceof DJIStageView) {
                        ((DJIStageView) view2).createStageView(R.layout.nfz_report_error_view, 2, true);
                    }
                }
            });
        }
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
