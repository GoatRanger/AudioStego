package dji.setting.ui.flyc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.apppublic.reflect.AppPublicReflect;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycSetHomePoint;
import dji.midware.data.model.P3.DataFlycSetHomePoint.HOMETYPE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.pilot.usercenter.protocol.d;
import dji.setting.ui.widget.a;

public class b {
    private static HOMETYPE a = null;

    public static void a(HOMETYPE hometype, Context context) {
        if (hometype == HOMETYPE.a) {
            e.a("FPV_LeftBarView_HomePointExpandedView_Button_Aircraft");
            a = hometype;
            a(0.0f, context);
        } else if (hometype == HOMETYPE.b) {
            e.a("FPV_LeftBarView_HomePointExpandedView_Button_RCControl");
            double[] location = AppPublicReflect.getLocation();
            if (location != null) {
                float a = a(location[0], location[1], location[2]);
                if (a != -1.0f) {
                    a = hometype;
                    a(a, context);
                    return;
                }
            }
            c(context);
        }
    }

    private static void a(float f, final Context context) {
        AlertDialog a = a.a(context);
        float f2;
        if (a == HOMETYPE.a) {
            float f3 = dji.pilot.publics.e.e.f((float) DataOsdGetPushHome.getInstance().getGoHomeHeight());
            a.setTitle(R.string.setting_ui_flyc_homepoint_now);
            a.setMessage(context.getString(R.string.setting_ui_flyc_homepoint_now_desc, new Object[]{Float.valueOf(f2), Float.valueOf(f3)}));
        } else if (a == HOMETYPE.c || a == HOMETYPE.b) {
            f2 = dji.pilot.publics.e.e.f(f);
            a.setTitle(R.string.setting_ui_flyc_homepoint_mobile);
            a.setMessage(context.getString(R.string.setting_ui_flyc_homepoint_other_desc, new Object[]{Float.valueOf(f), Float.valueOf(f2)}));
        }
        a.setButton(-2, context.getResources().getString(R.string.app_cancel), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        a.setButton(-1, context.getResources().getString(R.string.app_ok), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (b.a == HOMETYPE.a) {
                    b.d(context);
                } else {
                    b.e(context);
                }
                if (b.a == HOMETYPE.a) {
                    e.c(s.cZ);
                } else if (b.a == HOMETYPE.c) {
                    e.c(s.da);
                }
                dialogInterface.dismiss();
            }
        });
        a.show();
    }

    private static void c(Context context) {
        if (dji.pilot.publics.e.a.a()) {
            a.a(context, R.string.setting_ui_flyc_homepoint_phone_invalid);
        } else {
            a.a(context, R.string.setting_ui_flyc_homepoint_rc_invalid);
        }
    }

    private static float a(double d, double d2, double d3) {
        DJILogHelper.getInstance().LOGD("Home", "GPS Accuracy[" + d3 + d.H, false, true);
        if (dji.gs.e.b.a((float) d3, dji.gs.e.b.a) && dji.pilot.publics.e.a.c()) {
            double latitude = DataOsdGetPushHome.getInstance().getLatitude();
            double longitude = DataOsdGetPushHome.getInstance().getLongitude();
            if (dji.pilot.publics.e.a.a(d) && dji.pilot.publics.e.a.b(d2) && dji.pilot.publics.e.a.a(latitude) && dji.pilot.publics.e.a.b(longitude)) {
                float c = dji.pilot.publics.e.a.c(d, d2, latitude, longitude);
                if (c > 3000.0f) {
                    return -1.0f;
                }
                return c;
            }
        }
        return -1.0f;
    }

    private static void d(final Context context) {
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        byte b = (dji.pilot.publics.e.a.a(latitude) && dji.pilot.publics.e.a.b(longitude)) ? (byte) 1 : (byte) 0;
        if (b == (byte) 0 || !dji.pilot.publics.e.a.c()) {
            a.a(context, R.string.setting_ui_flyc_homepoint_aircraft_failed);
        } else {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.a).a(Math.toRadians(latitude), Math.toRadians(longitude)).a((byte) 1).start(new dji.midware.e.d() {
                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    dji.publics.a.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            a.a(context, R.string.setting_ui_flyc_homepoint_aircraft_failed);
                        }
                    });
                }
            });
        }
    }

    private static void e(final Context context) {
        float a;
        double d;
        double d2;
        double[] location = AppPublicReflect.getLocation();
        if (location != null) {
            a = a(location[0], location[1], location[2]);
            d = location[0];
            d2 = location[1];
        } else {
            d2 = 0.0d;
            d = 0.0d;
            a = -1.0f;
        }
        if (a != -1.0f) {
            DataFlycSetHomePoint.getInstance().a(HOMETYPE.b).a(Math.toRadians(d), Math.toRadians(d2)).a((byte) 1).start(new dji.midware.e.d() {
                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    dji.publics.a.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            a.a(context, R.string.setting_ui_flyc_homepoint_failed);
                        }
                    });
                }
            });
            return;
        }
        c(context);
    }
}
