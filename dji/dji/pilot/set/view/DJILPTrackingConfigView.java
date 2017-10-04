package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import dji.pilot.set.R;
import dji.pilot.set.a;
import dji.pilot.set.g;

public class DJILPTrackingConfigView extends LinearLayout implements OnCheckedChangeListener {
    private RadioGroup a;

    public DJILPTrackingConfigView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public DJILPTrackingConfigView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a();
        b();
    }

    protected void onAttachedToWindow() {
    }

    protected void onDetachedFromWindow() {
    }

    private void a() {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.lp_set_tracking_level, null, false));
        this.a = (RadioGroup) findViewById(R.id.lp_tracking_set_rg);
        this.a.setOnCheckedChangeListener(this);
    }

    private void b() {
        getSwitchBarStatus();
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.v("Hello", "OnChange!");
        if (i == R.id.lp_tracking_set_rg_fast) {
            a.a(getContext(), g.n, 3);
        } else if (i == R.id.lp_tracking_set_rg_middle) {
            a.a(getContext(), g.n, 2);
        } else if (i == R.id.lp_tracking_set_rg_slow) {
            a.a(getContext(), g.n, 1);
        }
    }

    private void a(LinearLayout linearLayout, boolean z) {
        if (z) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
    }

    private void getSwitchBarStatus() {
        switch (a.b(getContext(), g.n, 2)) {
            case 1:
                this.a.check(R.id.lp_tracking_set_rg_slow);
                return;
            case 2:
                this.a.check(R.id.lp_tracking_set_rg_middle);
                return;
            case 3:
                this.a.check(R.id.lp_tracking_set_rg_fast);
                return;
            default:
                return;
        }
    }
}
