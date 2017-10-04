package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewManager;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.R;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.d;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.leftmenu.DJILeftMenu.b;
import dji.publics.DJIUI.DJIImageView;
import java.util.HashMap;
import java.util.Map;

public class DJIGimbalMenu extends DJILeftSecondMenu implements OnClickListener, s {
    private static final MODE[] m = new MODE[]{MODE.YawFollow, MODE.FPV, MODE.YawNoFollow, null};
    protected DJIImageView a = null;
    protected MODE b = MODE.YawNoFollow;
    private DJIImageView n = null;
    private DJIImageView o = null;
    private DJIImageView p = null;
    private DJIImageView q = null;
    private b r = null;

    public DJIGimbalMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnMenuListener(b bVar) {
        this.r = bVar;
    }

    public void handlePushGimbal() {
        if (getVisibility() == 0) {
            i_();
        }
    }

    public void setViewEnable(boolean z) {
        if (z) {
            this.a.setEnabled(true);
            return;
        }
        hideMenu(false);
        this.a.setEnabled(false);
    }

    public void show() {
        super.show();
        i_();
    }

    protected void i_() {
        MODE mode = DataGimbalGetPushParams.getInstance().getMode();
        if (this.b != mode) {
            this.b = mode;
            this.n.setSelected(false);
            this.o.setSelected(false);
            this.p.setSelected(false);
            if (mode == MODE.FPV) {
                this.a.setImageResource(R.drawable.leftmenu_gimbal_fpv);
                this.o.setSelected(true);
            } else if (mode == MODE.YawFollow) {
                this.a.setImageResource(R.drawable.leftmenu_gimbal_follow);
                this.p.setSelected(true);
            } else if (mode == MODE.YawNoFollow) {
                this.a.setImageResource(R.drawable.leftmenu_gimbal_notfollow);
                this.n.setSelected(true);
            }
        }
    }

    protected void b() {
        super.onFinishInflate();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIImageView) findViewById(R.id.a4e);
            this.n = (DJIImageView) findViewById(R.id.a4h);
            this.o = (DJIImageView) findViewById(R.id.a4g);
            this.p = (DJIImageView) findViewById(R.id.a4f);
            this.q = (DJIImageView) findViewById(R.id.a4i);
            int i = 5;
            if (DataGimbalGetPushType.getInstance().isGetted() && DataGimbalGetPushType.getInstance().getType() == DJIGimbalType.Z15) {
                ((ViewManager) this.q.getParent()).removeView(this.q);
                i = 4;
            } else if (dji.pilot.fpv.d.b.k(null)) {
                ((ViewManager) this.o.getParent()).removeView(this.o);
                i = 4;
            }
            this.l = ((((i - 1) * dji.pilot.fpv.model.b.a(this.h, R.dimen.pp)) + (dji.pilot.fpv.model.b.a(this.h, R.dimen.pr) * i)) + dji.pilot.fpv.model.b.a(this.h, R.dimen.pn)) + 1;
            this.a.setOnClickListener(this);
            this.n.setOnClickListener(this);
            this.o.setOnClickListener(this);
            this.p.setOnClickListener(this);
            this.q.setOnClickListener(this);
        }
    }

    protected void a(int i) {
        if (i == 3) {
            DataSpecialControl.getInstance().resetGimbal().start(20);
        } else {
            DataSpecialControl.getInstance().setGimbalMode(m[i]).start(20);
        }
    }

    protected void b(int i) {
        if (this.r != null) {
            this.r.a(1, i);
        }
    }

    public void onClick(View view) {
        if (!this.k) {
            int id = view.getId();
            Map hashMap = new HashMap();
            if (R.id.a4e == id) {
                autoHandle();
                b(0);
            } else if (R.id.a4h == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_Free");
                hashMap.put(d.dH, s.df);
                e.a(s.db, hashMap);
                a(2);
                b(1);
            } else if (R.id.a4g == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_FPV");
                hashMap.put(d.dH, s.dc);
                e.a(s.db, hashMap);
                a(1);
                b(2);
            } else if (R.id.a4f == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_Follow");
                hashMap.put(d.dH, s.de);
                e.a(s.db, hashMap);
                a(0);
                b(3);
            } else if (R.id.a4i == id) {
                e.a("FPV_LeftBarView_GimbalExpandedView_Button_CenterCamera");
                hashMap.put(d.dH, s.dd);
                e.a(s.db, hashMap);
                a(3);
                b(4);
            }
        }
    }

    public void showSecondStyle() {
        this.o.go();
    }

    public void showFirstStyle() {
        this.o.show();
    }
}
