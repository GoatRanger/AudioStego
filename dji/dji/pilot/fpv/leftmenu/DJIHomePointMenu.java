package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycSetHomePoint;
import dji.midware.data.model.P3.DataFlycSetHomePoint.HOMETYPE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.pilot.R;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.leftmenu.DJILeftMenu.b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.publics.e.e;
import dji.publics.DJIUI.DJIImageView;
import dji.thirdparty.a.c;

public class DJIHomePointMenu extends DJILeftSecondMenu implements OnClickListener, s {
    private static final int a = 20;
    private static final HOMETYPE[] b = new HOMETYPE[]{HOMETYPE.a, HOMETYPE.b, HOMETYPE.d};
    private DJIImageView m = null;
    private DJIImageView n = null;
    private DJIImageView o = null;
    private DJIImageView p = null;
    private b q = null;
    private b r = null;
    private HOMETYPE s = null;

    public DJIHomePointMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnMenuListener(b bVar) {
        this.q = bVar;
    }

    public void setViewEnable(boolean z) {
        if (z) {
            this.m.setEnabled(true);
            return;
        }
        hideMenu(false);
        this.m.setEnabled(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.m = (DJIImageView) findViewById(R.id.a4l);
            this.n = (DJIImageView) findViewById(R.id.a4m);
            this.o = (DJIImageView) findViewById(R.id.a4n);
            this.p = (DJIImageView) findViewById(R.id.a4o);
            this.l = ((dji.pilot.fpv.model.b.a(this.h, R.dimen.pr) * 3) + (dji.pilot.fpv.model.b.a(this.h, R.dimen.pp) * 2)) + dji.pilot.fpv.model.b.a(this.h, R.dimen.pn);
            this.m.setOnClickListener(this);
            this.n.setOnClickListener(this);
            this.o.setOnClickListener(this);
            this.p.setOnClickListener(this);
        }
    }

    protected void a() {
        int childCount = getChildCount();
        for (int i = 1; i < childCount - 1; i++) {
            getChildAt(i).setVisibility(0);
        }
    }

    private void setAircraftHome(int i) {
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        byte b = (dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude)) ? (byte) 1 : (byte) 0;
        if (b == (byte) 0 || !dji.pilot.fpv.d.b.e()) {
            a((int) R.string.fpv_errorpop_homepoint_aircraft_failed, d.b);
        } else {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.a).a(Math.toRadians(latitude), Math.toRadians(longitude)).a((byte) 1).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIHomePointMenu a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(a aVar) {
                    this.a.a((int) R.string.fpv_errorpop_homepoint_aircraft_failed, d.b);
                }
            });
        }
    }

    private float a(double d, double d2) {
        if ((!dji.pilot.fpv.d.b.a(null) || DataRcGetPushGpsInfo.getInstance().getGpsStatus()) && dji.pilot.fpv.d.b.e()) {
            double latitude = DataOsdGetPushHome.getInstance().getLatitude();
            double longitude = DataOsdGetPushHome.getInstance().getLongitude();
            if (dji.pilot.fpv.d.b.a(d) && dji.pilot.fpv.d.b.b(d2) && dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude)) {
                float b = dji.pilot.fpv.d.b.b(d, d2, latitude, longitude);
                if (b > 3000.0f) {
                    return -1.0f;
                }
                return b;
            }
        }
        return -1.0f;
    }

    private void setRcHome(int i) {
        double d;
        float a;
        double d2 = 0.0d;
        dji.gs.e.b k = k.k();
        if (k != null) {
            d = k.b;
            d2 = k.c;
            a = a(d, d2);
        } else {
            a = -1.0f;
            d = 0.0d;
        }
        if (a != -1.0f) {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.b).a(Math.toRadians(d), Math.toRadians(d2)).a((byte) 1).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIHomePointMenu a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(a aVar) {
                    if (dji.pilot.fpv.d.b.i()) {
                        this.a.a((int) R.string.fpv_errorpop_homepoint_phone_failed, d.b);
                    } else {
                        this.a.a((int) R.string.fpv_errorpop_homepoint_rc_failed, d.b);
                    }
                }
            });
        } else if (dji.pilot.fpv.d.b.i()) {
            a((int) R.string.fpv_errorpop_homepoint_phone_invalid, d.b);
        } else {
            a((int) R.string.fpv_errorpop_homepoint_rc_invalid, d.b);
        }
    }

    private void a(HOMETYPE hometype, int i) {
        if (hometype == HOMETYPE.a) {
            setAircraftHome(i);
        } else if (hometype == HOMETYPE.b) {
            setRcHome(i);
        }
    }

    private void a(int i, d dVar) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.b = R.string.fpv_errorpop_homepoint_set_title;
        bVar.d = i;
        bVar.a = dVar;
        c.a().e(bVar);
    }

    private void a(int i, float f) {
        this.s = b[i];
        a(f);
    }

    private void a(float f) {
        if (this.r == null) {
            this.r = new b(this.h);
            this.r.a(1);
            this.r.a(new b.a(this) {
                final /* synthetic */ DJIHomePointMenu a;

                {
                    this.a = r1;
                }

                public void a(DialogInterface dialogInterface, int i) {
                    this.a.b(i);
                }

                public void b(DialogInterface dialogInterface, int i) {
                    this.a.a(i);
                }

                public void a(DialogInterface dialogInterface, boolean z, int i) {
                }
            });
            this.r.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ DJIHomePointMenu a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
        }
        if (this.r != null && !this.r.isShowing()) {
            float f2;
            if (this.s == HOMETYPE.a) {
                this.r.b((int) R.drawable.leftmenu_dlg_homepoint_aircraft);
                this.r.a(8, 20).e(8);
                this.r.a(8, this.h.getString(R.string.fpv_leftmenu_homepoint_norecord));
                this.r.a(this.h.getString(R.string.fpv_errorpop_homepoint_now_desc));
                float f3 = e.f((float) DataOsdGetPushHome.getInstance().getGoHomeHeight());
                this.r.b(this.h.getString(R.string.fpv_leftmenu_homepoint_now_desc, new Object[]{Float.valueOf(f2), Float.valueOf(f3)}));
            } else if (this.s == HOMETYPE.c || this.s == HOMETYPE.b) {
                this.r.b((int) R.drawable.leftmenu_dlg_homepoint_rc);
                this.r.a(8, 20).e(8);
                this.r.a(8, this.h.getString(R.string.fpv_leftmenu_homepoint_norecord));
                f2 = e.f(f);
                if (dji.pilot.fpv.d.b.i()) {
                    this.r.a(this.h.getString(R.string.fpv_errorpop_homepoint_mobile_litchi));
                    this.r.b(this.h.getString(R.string.fpv_leftmenu_homepoint_other_desc, new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
                } else {
                    this.r.a(this.h.getString(R.string.fpv_errorpop_homepoint_mobile));
                    this.r.b(this.h.getString(R.string.fpv_leftmenu_homepoint_other_desc, new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
                }
            } else if (this.s == HOMETYPE.d) {
                this.r.b((int) R.drawable.leftmenu_dlg_homepoint_rc);
                this.r.a(0, 20).e(8);
                this.r.a(8, this.h.getString(R.string.fpv_leftmenu_homepoint_norecord));
                this.r.a(this.h.getString(R.string.fpv_leftmenu_homefollow));
                this.r.b(this.h.getString(R.string.fpv_leftmenu_homefollow_desc));
            }
            this.r.show();
        }
    }

    private void b() {
        if (this.r != null && this.r.isShowing()) {
            this.r.dismiss();
        }
    }

    private void a(int i) {
        b();
        if (this.s == HOMETYPE.d) {
            a(HOMETYPE.d, this.r.d());
        } else {
            a(this.s, 0);
        }
        if (this.s == HOMETYPE.a) {
            dji.pilot.fpv.d.e.c(s.cZ);
        } else if (this.s == HOMETYPE.c) {
            dji.pilot.fpv.d.e.c(s.da);
        }
    }

    private void b(int i) {
        b();
    }

    private void d() {
        b bVar = new b(this.h);
        bVar.a(2);
        bVar.a(new b.a(this) {
            final /* synthetic */ DJIHomePointMenu a;

            {
                this.a = r1;
            }

            public void a(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

            public void b(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

            public void a(DialogInterface dialogInterface, boolean z, int i) {
            }
        });
        bVar.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ DJIHomePointMenu a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        bVar.a(8, 0);
        bVar.e(8);
        bVar.c(this.h.getString(R.string.app_enter));
        bVar.d(8);
        if (dji.pilot.fpv.d.b.i()) {
            bVar.b(this.h.getString(R.string.fpv_errorpop_homepoint_phone_invalid));
            bVar.a(this.h.getString(R.string.fpv_errorpop_homepoint_mobile_litchi));
        } else {
            bVar.b(this.h.getString(R.string.fpv_errorpop_homepoint_rc_invalid));
            bVar.a(this.h.getString(R.string.fpv_errorpop_homepoint_mobile));
        }
        bVar.show();
    }

    private void c(int i) {
        if (this.q != null) {
            this.q.a(3, i);
        }
    }

    public void onClick(View view) {
        if (!this.k) {
            int id = view.getId();
            if (R.id.a4l == id) {
                autoHandle();
                c(0);
            } else if (R.id.a4m == id) {
                dji.pilot.fpv.d.e.a("FPV_LeftBarView_HomePointExpandedView_Button_Aircraft");
                a(0, 0.0f);
                c(1);
            } else if (R.id.a4n == id) {
                dji.pilot.fpv.d.e.a("FPV_LeftBarView_HomePointExpandedView_Button_RCControl");
                c(2);
                dji.gs.e.b k = k.k();
                if (k != null) {
                    float a = a(k.b, k.c);
                    if (a != -1.0f) {
                        a(1, a);
                        return;
                    }
                }
                d();
            } else if (R.id.a4o == id) {
                a(2, 0.0f);
                c(3);
            }
        }
    }
}
