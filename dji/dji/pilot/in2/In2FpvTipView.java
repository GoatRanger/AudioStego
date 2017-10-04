package dji.pilot.in2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import dji.pilot.R;

public class In2FpvTipView extends FrameLayout {
    public final String a = "In2FpvTipView";

    public In2FpvTipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        addView(LayoutInflater.from(getContext()).inflate(R.layout.fpv_topbar_flyc_tip_in2, null));
    }
}
