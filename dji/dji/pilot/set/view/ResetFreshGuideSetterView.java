package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import dji.pilot.set.R;
import dji.pilot.set.a;
import dji.pilot.set.g;
import dji.pilot.set.view.base.SetButtonView;

public class ResetFreshGuideSetterView extends SetButtonView {
    public ResetFreshGuideSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        a.a(getContext(), g.v, false);
        Toast makeText = Toast.makeText(getContext(), R.string.reset_fresh_guide_succuss, 1);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    protected int getTitleId() {
        return R.string.reset_fresh_guide;
    }

    protected int getButtonStringId() {
        return R.string.reset_fresh_guide_start;
    }
}
