package dji.pilot.fpv.control;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycSetHomePoint;
import dji.midware.data.model.P3.DataFlycSetHomePoint.HOMETYPE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.R;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.leftmenu.b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.thirdparty.a.c;

public class p implements s {
    private static final int a = 20;
    private HOMETYPE b = null;
    private Context c = null;
    private b d = null;
    private a e = null;

    public interface a {
        void a();

        void b();
    }

    public p(Context context) {
        this.c = context;
    }

    private void a(int i) {
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        byte b = (dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude)) ? (byte) 1 : (byte) 0;
        if (b == (byte) 0 || !dji.pilot.fpv.d.b.e()) {
            a((int) R.string.fpv_errorpop_homepoint_aircraft_failed, d.b);
        } else {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.a).a(Math.toRadians(latitude), Math.toRadians(longitude)).a((byte) 1).start(new dji.midware.e.d(this) {
                final /* synthetic */ p a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.a((int) R.string.fpv_errorpop_homepoint_aircraft_failed, d.b);
                }
            });
        }
    }

    private float a(double d, double d2, double d3) {
        DJILogHelper.getInstance().LOGD("Home", "GPS Accuracy[" + d3 + dji.pilot.usercenter.protocol.d.H, false, true);
        if (0.0d < d3 && d3 <= 16.0d && dji.pilot.fpv.d.b.e()) {
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

    public void a(a aVar) {
        this.e = aVar;
        b(0);
    }

    private void b(int i) {
        double d;
        float a;
        double d2 = 0.0d;
        dji.gs.e.b k = k.k();
        if (k != null) {
            d = k.b;
            d2 = k.c;
            a = a(d, d2, (double) k.e);
        } else {
            a = -1.0f;
            d = 0.0d;
        }
        if (a != -1.0f) {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.b).a(Math.toRadians(d), Math.toRadians(d2)).a((byte) 1).start(new dji.midware.e.d(this) {
                final /* synthetic */ p a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    if (this.a.e != null) {
                        this.a.e.a();
                    }
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
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
            a(i);
        } else if (hometype == HOMETYPE.b) {
            b(i);
        }
    }

    private void a(int i, d dVar) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.b = R.string.fpv_errorpop_homepoint_set_title;
        bVar.d = i;
        bVar.a = dVar;
        c.a().e(bVar);
    }

    public void a(HOMETYPE hometype) {
        if (hometype == HOMETYPE.a) {
            e.a("FPV_LeftBarView_HomePointExpandedView_Button_Aircraft");
            this.b = hometype;
            a(0.0f);
        } else if (hometype == HOMETYPE.b) {
            e.a("FPV_LeftBarView_HomePointExpandedView_Button_RCControl");
            dji.gs.e.b k = k.k();
            if (k != null) {
                float a = a(k.b, k.c, (double) k.e);
                if (a != -1.0f) {
                    this.b = hometype;
                    a(a);
                    return;
                }
            }
            b();
        }
    }

    private void a(float f) {
        if (this.d == null) {
            this.d = new b(this.c);
            this.d.a(1);
            this.d.a(new dji.pilot.fpv.leftmenu.b.a(this) {
                final /* synthetic */ p a;

                {
                    this.a = r1;
                }

                public void a(DialogInterface dialogInterface, int i) {
                    this.a.d(i);
                }

                public void b(DialogInterface dialogInterface, int i) {
                    this.a.c(i);
                }

                public void a(DialogInterface dialogInterface, boolean z, int i) {
                }
            });
            this.d.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ p a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
        }
        if (this.d != null && !this.d.isShowing()) {
            float f2;
            if (this.b == HOMETYPE.a) {
                this.d.b((int) R.drawable.leftmenu_dlg_homepoint_aircraft);
                this.d.a(8, 20).e(8);
                this.d.a(8, this.c.getString(R.string.fpv_leftmenu_homepoint_norecord));
                this.d.a(this.c.getString(R.string.fpv_errorpop_homepoint_now_desc));
                float f3 = dji.pilot.publics.e.e.f((float) DataOsdGetPushHome.getInstance().getGoHomeHeight());
                this.d.b(this.c.getString(R.string.fpv_leftmenu_homepoint_now_desc, new Object[]{Float.valueOf(f2), Float.valueOf(f3)}));
            } else if (this.b == HOMETYPE.c || this.b == HOMETYPE.b) {
                this.d.b((int) R.drawable.leftmenu_dlg_homepoint_rc);
                this.d.a(8, 20).e(8);
                this.d.a(8, this.c.getString(R.string.fpv_leftmenu_homepoint_norecord));
                f2 = dji.pilot.publics.e.e.f(f);
                if (dji.pilot.fpv.d.b.i()) {
                    this.d.a(this.c.getString(R.string.fpv_errorpop_homepoint_mobile_litchi));
                    this.d.b(this.c.getString(R.string.fpv_leftmenu_homepoint_other_desc, new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
                } else {
                    this.d.a(this.c.getString(R.string.fpv_errorpop_homepoint_mobile));
                    this.d.b(this.c.getString(R.string.fpv_leftmenu_homepoint_other_desc, new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
                }
            } else if (this.b == HOMETYPE.d) {
                this.d.b((int) R.drawable.leftmenu_dlg_homepoint_rc);
                this.d.a(0, 20).e(8);
                this.d.a(8, this.c.getString(R.string.fpv_leftmenu_homepoint_norecord));
                this.d.a(this.c.getString(R.string.fpv_leftmenu_homefollow));
                this.d.b(this.c.getString(R.string.fpv_leftmenu_homefollow_desc));
            }
            this.d.show();
        }
    }

    private void a() {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
    }

    private void c(int i) {
        a();
        if (this.b == HOMETYPE.d) {
            a(HOMETYPE.d, this.d.d());
        } else {
            a(this.b, 0);
        }
        if (this.b == HOMETYPE.a) {
            e.c(s.cZ);
        } else if (this.b == HOMETYPE.c) {
            e.c(s.da);
        }
    }

    private void d(int i) {
        a();
    }

    private void b() {
        b bVar = new b(this.c);
        bVar.a(2);
        bVar.a(new dji.pilot.fpv.leftmenu.b.a(this) {
            final /* synthetic */ p a;

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
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        bVar.a(8, 0);
        bVar.e(8);
        bVar.c(this.c.getString(R.string.app_enter));
        bVar.d(8);
        if (dji.pilot.fpv.d.b.i()) {
            bVar.b(this.c.getString(R.string.fpv_errorpop_homepoint_phone_invalid));
            bVar.a(this.c.getString(R.string.fpv_errorpop_homepoint_mobile_litchi));
        } else {
            bVar.b(this.c.getString(R.string.fpv_errorpop_homepoint_rc_invalid));
            bVar.a(this.c.getString(R.string.fpv_errorpop_homepoint_mobile));
        }
        bVar.show();
    }
}
