package dji.phone.tracking.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.f.a;
import dji.phone.g.b;
import dji.phone.tracking.e;
import dji.phone.widget.DJILPUISwitcher;
import dji.pilot.fpv.R;
import it.sauronsoftware.ftp4j.FTPCodes;

public class DJILPTrackTargetView extends RelativeLayout {
    private static final String a = "DJILPTrackTargetView";
    private static final int f = 10;
    private TrackingBGView b;
    private TextView c;
    private TextView d;
    private int e;
    private int g = Color.argb(255, 75, FTPCodes.HELP_MESSAGE, 99);
    private int h = Color.argb(255, 234, 47, 70);
    private int i;
    private Rect j = new Rect();
    private Runnable k = new Runnable(this) {
        final /* synthetic */ DJILPTrackTargetView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.setVisibility(8);
            this.a.j = new Rect(0, 0, 0, 0);
            this.a.changePos();
        }
    };

    public DJILPTrackTargetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.a(this);
        this.e = getResources().getDimensionPixelSize(R.dimen.dp_120_in_sw320dp);
        this.b = (TrackingBGView) findViewById(R.id.lp_tk_target_iv);
        this.c = (TextView) findViewById(R.id.lp_tk_target_warning_tv);
        this.c.setShadowLayer(6.0f, 0.0f, 0.0f, getResources().getColor(R.color.black_60P_longan));
        this.d = (TextView) findViewById(R.id.lp_tk_target_fps_tv);
        this.d.setVisibility(8);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a.b(this);
    }

    public void changePos() {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        LayoutParams layoutParams2 = (LayoutParams) this.b.getLayoutParams();
        layoutParams2.width = this.j.width();
        layoutParams2.height = this.j.height();
        if (this.j.width() > this.e) {
            layoutParams.width = this.j.width();
        } else {
            layoutParams.width = this.e;
        }
        layoutParams.height = this.j.height();
        layoutParams.leftMargin = this.j.left - ((layoutParams.width / 2) - (this.j.width() / 2));
        layoutParams.topMargin = this.j.top;
        this.b.setLayoutParams(layoutParams2);
        setLayoutParams(layoutParams);
    }

    public void onEventMainThread(e eVar) {
        if (DJILPUISwitcher.a == b.TRACKING) {
            if (this.i != eVar.b) {
                this.i = eVar.b;
                if (this.i == 1) {
                    this.b.setColor(this.g);
                } else {
                    this.b.setColor(this.h);
                    this.c.setText(R.string.lp_tk_wraning_detecting);
                }
            }
            if (this.i == 1) {
                if (eVar.c == 0) {
                    this.c.setText(null);
                } else if (eVar.c == 1) {
                    this.c.setText(R.string.lp_tk_warning_too_large);
                } else if (eVar.c == 2) {
                    this.c.setText(R.string.lp_tk_wraning_too_small);
                }
            }
            if (this.j == null || Math.abs(this.j.left - eVar.a.left) > 10 || Math.abs(this.j.top - eVar.a.top) > 10) {
                this.j = eVar.a;
                changePos();
            }
        }
    }

    public void onEventMainThread(dji.phone.tracking.a.b.b bVar) {
        switch (bVar) {
            case WAIT_INIT:
            case NONE:
                if (isShown()) {
                    postDelayed(this.k, 300);
                    return;
                }
                return;
            case TRACKING:
                removeCallbacks(this.k);
                if (!isShown()) {
                    setVisibility(0);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
