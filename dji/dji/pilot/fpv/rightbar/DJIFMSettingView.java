package dji.pilot.fpv.rightbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.c.d;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.dji_groundstation.controller.f;
import dji.pilot.fpv.camera.a.a;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$f;
import dji.pilot.visual.util.c;
import dji.setting.ui.rc.RcMasterSlaveView;

public class DJIFMSettingView extends DJIStateImageView {
    private boolean a = false;
    private RcModeChannel b = RcModeChannel.CHANNEL_UNKNOWN;
    private int c = 0;
    private int d = 0;
    private boolean e = false;
    private boolean f = true;

    public DJIFMSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIFMSettingView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (c.c()) {
                    dji.thirdparty.a.c.a().e(g$d.MODE_SELECT_VIEW);
                } else {
                    f.getInstance(this.a.getContext()).b(this.a.getContext());
                }
            }
        });
    }

    public void onEventMainThread(e eVar) {
        if (eVar != null) {
            if (eVar.s == 8) {
                if (eVar.t == null) {
                    a(this.a, this.b);
                    return;
                }
                if (eVar.t instanceof Integer) {
                    Integer num = (Integer) eVar.t;
                    if (num.intValue() > 0) {
                        setImageResource(num.intValue());
                    }
                }
                a(true);
            } else if (eVar.s == 9) {
                a(false);
            }
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
        int groundOrSky = dataOsdGetPushCommon.groundOrSky();
        if (modeChannel != this.b || this.d != groundOrSky) {
            this.b = modeChannel;
            this.d = groundOrSky;
            a(this.a, modeChannel);
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        boolean a = b.a(dataOsdGetPushHome.isBeginnerMode(), dataOsdGetPushHome.isMultipleModeOpen());
        if (this.a != a) {
            this.a = a;
            a(a, this.b);
        }
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        if (cVar != null) {
            a(this.a, this.b);
        }
    }

    public void onEventMainThread(c$b dji_pilot_fpv_flightmode_c_b) {
        a(this.a, this.b);
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            this.b = RcModeChannel.CHANNEL_UNKNOWN;
            this.a = false;
            this.c = 0;
            this.d = 2;
            a(false);
            a(this.a, this.b);
        }
    }

    public void onEventMainThread(g$d dji_pilot_visual_a_g_d) {
        if (dji_pilot_visual_a_g_d == g$d.CTRLMODE_CHANGED) {
            a(this.a, this.b);
        }
    }

    public void onEventMainThread(g$f dji_pilot_visual_a_g_f) {
        a(this.a, this.b);
    }

    public void showCheck() {
        if (this.e) {
            a(true);
        }
    }

    public void setCanShow(boolean z) {
        this.f = z;
        if (z) {
            a(true);
        } else {
            a(false);
        }
    }

    public void show() {
        if (this.f) {
            super.show();
        }
    }

    private void a(boolean z) {
        this.e = z;
        if (z) {
            show();
        } else {
            go();
        }
    }

    private void a(boolean z, RcModeChannel rcModeChannel) {
        if (d.b != MODE.a) {
            a(false);
        } else if (i.getInstance().c() != ProductType.A2) {
            if (b.j()) {
                c$b a = dji.pilot.fpv.flightmode.c.getInstance().a();
                boolean c = c.c();
                a.a(null, "flightMode-" + a + com.alipay.sdk.j.i.b + c);
                if (a == c$b.SMART) {
                    return;
                }
                if (c) {
                    setImageResource(c.d());
                    a(true);
                    return;
                }
                a(false);
            } else if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 7) {
            } else {
                if (z && rcModeChannel == RcModeChannel.CHANNEL_F && this.d == 2) {
                    a(true);
                } else {
                    a(false);
                }
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        e l = f.getInstance(getContext()).l();
        if (l != null) {
            onEventMainThread(l);
        }
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushCommon.getInstance());
            onEventMainThread(DataOsdGetPushHome.getInstance());
        }
    }

    protected void onDetachedFromWindow() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
