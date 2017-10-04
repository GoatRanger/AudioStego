package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ViewFlipper;
import dji.pilot.dji_groundstation.R;

public class AddViewWithAnimLayout extends ViewFlipper {
    private int a;
    private final int b;
    private View c;
    private View d;
    private LayoutParams e;

    public AddViewWithAnimLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.b = 2;
        this.c = null;
        this.d = null;
        this.e = null;
        this.e = new LayoutParams(-1, -1);
        setInAnimation(getContext(), R.anim.slide_right_in);
        setOutAnimation(getContext(), R.anim.slide_left_out);
    }

    public int getViewCount() {
        return this.a;
    }

    public void addView(View view) {
        super.addView(view, this.e);
        this.a++;
        if (this.c == null && this.d == null) {
            this.c = view;
        }
        if (this.c != null && this.d == null) {
            this.d = view;
        }
        if (this.c == null && this.d != null) {
            this.c = this.d;
            this.d = view;
        }
        if (!(this.c == null || this.d == null)) {
            this.c = this.d;
            this.d = view;
        }
        showNext();
    }

    public void stopFlipping() {
        super.stopFlipping();
        a();
    }

    public void startFlipping() {
        super.startFlipping();
    }

    private void a() {
        removeView(this.c);
        this.c = this.d;
        this.d = null;
    }
}
