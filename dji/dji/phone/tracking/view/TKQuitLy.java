package dji.phone.tracking.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.f.a;
import dji.phone.e.a.e;
import dji.phone.tracking.a.b.b;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class TKQuitLy extends RelativeLayout {
    private static final String c = "TKQuitLy";
    LayoutParams a;
    b b;
    private View d;
    private View e;
    private Runnable f = new Runnable(this) {
        final /* synthetic */ TKQuitLy a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.setVisibility(4);
        }
    };

    public TKQuitLy(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TKQuitLy a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                c.a().e(new dji.phone.e.b(e.BTN_TK_QUIT, dji.phone.e.a.c.c));
                this.a.setVisibility(4);
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.a(this);
        this.d = findViewById(R.id.lp_tk_quit_left_spacer);
        this.e = findViewById(R.id.lp_tk_quit_right_spacer);
        this.a = (LayoutParams) getLayoutParams();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a.b(this);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public void onEventMainThread(b bVar) {
        if (this.b != bVar) {
            this.b = bVar;
            switch (bVar) {
                case TRACKING:
                    removeCallbacks(this.f);
                    if (!isShown()) {
                        setVisibility(0);
                        return;
                    }
                    return;
                case WAIT_INIT:
                case NONE:
                    if (isShown()) {
                        postDelayed(this.f, 300);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        if (bVar.a == e.VIEW_UI_SWITCHER && bVar.c.b()) {
            setVisibility(4);
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        float a = dji.phone.k.c.a(bVar.b());
        if (a == 90.0f || a == 270.0f) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.a);
            layoutParams.leftMargin = ((int) (((float) layoutParams.leftMargin) - ((((float) (getMeasuredWidth() + getMeasuredHeight())) + 1.0f) / 2.0f))) + 1;
            layoutParams.bottomMargin = (int) (((float) layoutParams.bottomMargin) + ((((float) (getMeasuredWidth() + getMeasuredHeight())) + 1.0f) / 2.0f));
            this.e.setVisibility(0);
            this.d.setVisibility(4);
            setLayoutParams(layoutParams);
        } else {
            setLayoutParams(this.a);
            this.e.setVisibility(4);
            this.d.setVisibility(0);
        }
        dji.phone.h.a.a(this, getRotation(), a);
    }
}
