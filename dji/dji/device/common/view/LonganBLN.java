package dji.device.common.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.fpv.R;
import lecho.lib.hellocharts.model.l;

public class LonganBLN extends View {
    private ObjectAnimator a = null;

    public LonganBLN(Context context) {
        super(context);
        a();
    }

    public LonganBLN(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public LonganBLN(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setBackground(getResources().getDrawable(R.drawable.lp_bln));
    }

    private void a() {
        this.a = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f, l.n, 0.4f, 0.2f, 0.0f, 0.0f, 1.0f});
        this.a.setDuration(1000);
        this.a.setRepeatCount(-1);
        this.a.setTarget(this);
    }

    public void startAnim() {
        if (!isShown()) {
            setVisibility(0);
        }
        if (!this.a.isRunning()) {
            this.a.start();
        }
    }

    public void stopAnim() {
        if (this.a.isRunning()) {
            this.a.cancel();
        }
        if (isShown()) {
            setVisibility(4);
        }
    }
}
