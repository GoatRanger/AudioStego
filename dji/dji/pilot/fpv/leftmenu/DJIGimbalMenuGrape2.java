package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.b;
import dji.publics.DJIUI.DJIImageView;

public class DJIGimbalMenuGrape2 extends DJIGimbalMenu implements OnClickListener {
    private static final MODE[] m = new MODE[]{MODE.YawNoFollow, MODE.YawFollow, MODE.FPV, MODE.FPV};
    private DJIImageView n = null;
    private DJIImageView o = null;
    private DJIImageView p = null;
    private DJIImageView q = null;
    private int r = -1;

    public DJIGimbalMenuGrape2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void i_() {
        MODE mode = DataGimbalGetPushParams.getInstance().getMode();
        int subMode = DataGimbalGetPushParams.getInstance().getSubMode();
        DJILogHelper.getInstance().LOGD("View", "gimbal mode=" + mode + " mIsAhead=" + subMode);
        if (this.b != mode || this.r != subMode) {
            this.r = subMode;
            this.b = mode;
            this.n.setSelected(false);
            this.o.setSelected(false);
            this.q.setSelected(false);
            this.p.setSelected(false);
            if (mode == MODE.FPV) {
                if (this.r == 0) {
                    this.a.setImageResource(R.drawable.leftmenu_gimbal_fpv);
                    this.o.setSelected(true);
                    return;
                }
                this.a.setImageResource(R.drawable.leftmenu_gimbal_below);
                this.q.setSelected(true);
            } else if (mode == MODE.YawFollow) {
                this.r = -1;
                this.a.setImageResource(R.drawable.leftmenu_gimbal_follow);
                this.p.setSelected(true);
            } else if (mode == MODE.YawNoFollow) {
                this.r = -1;
                this.a.setImageResource(R.drawable.leftmenu_gimbal_notfollow);
                this.n.setSelected(true);
            }
        }
    }

    protected void onFinishInflate() {
        super.b();
        if (!isInEditMode()) {
            this.a = (DJIImageView) findViewById(R.id.a4e);
            this.p = (DJIImageView) findViewById(R.id.a4f);
            this.n = (DJIImageView) findViewById(R.id.a4h);
            this.o = (DJIImageView) findViewById(R.id.a4j);
            this.q = (DJIImageView) findViewById(R.id.a4k);
            this.l = (((b.a(this.h, R.dimen.pr) * 5) + (b.a(this.h, R.dimen.pp) * 4)) + b.a(this.h, R.dimen.pn)) + 1;
            this.a.setOnClickListener(this);
            this.n.setOnClickListener(this);
            this.o.setOnClickListener(this);
            this.p.setOnClickListener(this);
            this.q.setOnClickListener(this);
        }
    }

    protected void a(int i) {
        switch (i) {
            case 0:
                DataSpecialControl.getInstance().setGimbalMode(m[i]).start(20);
                return;
            case 1:
                DataSpecialControl.getInstance().setGimbalMode(m[i]).start(20);
                return;
            case 2:
                DataSpecialControl.getInstance().setGimbalMode(m[i], false).start(20);
                return;
            case 3:
                DataSpecialControl.getInstance().setGimbalMode(m[i], true).start(20);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        if (!this.k) {
            int id = view.getId();
            if (R.id.a4e == id) {
                autoHandle();
                b(0);
            } else if (R.id.a4h == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_Free");
                a(0);
                b(1);
            } else if (R.id.a4f == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_Follow");
                a(1);
                b(3);
            } else if (R.id.a4j == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_FPV_ahead");
                a(2);
                b(5);
            } else if (R.id.a4k == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_FPV_below");
                a(3);
                b(6);
            }
        }
    }

    public void showSecondStyle() {
    }

    public void showFirstStyle() {
    }
}
