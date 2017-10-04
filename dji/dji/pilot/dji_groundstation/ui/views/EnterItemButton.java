package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import dji.common.flightcontroller.DJIFlightControllerFlightMode;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.a;
import dji.pilot.dji_groundstation.a.b;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.controller.d;
import dji.pilot.dji_groundstation.controller.e;
import dji.pilot.dji_groundstation.controller.f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class EnterItemButton extends DJILinearLayout {
    private static final String a = "EnterItemButton";
    private DJIImageView b = null;
    private DJITextView c = null;
    private d$a d = null;
    private c e = null;
    private String f = "";

    public EnterItemButton(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.view_enteritem, this);
        setOrientation(1);
        this.b = (DJIImageView) findViewById(R.id.enteritem_image);
        this.c = (DJITextView) findViewById(R.id.enteritem_title);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EnterItemButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.a()) {
                    e.a(this.a.f, this.a.getContext());
                }
            }
        });
    }

    private boolean a() {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        ProductType c = i.getInstance().c();
        CharSequence charSequence = "";
        if (instance.getFlycState() == FLYC_STATE.GoHome || instance.getFlycState() == FLYC_STATE.AutoLanding) {
            charSequence = getContext().getString(R.string.gsnew_gs_function_not_available);
        } else if (instance.getVoltageWarning() == 2) {
            charSequence = getContext().getString(R.string.gsnew_battery_low_warning);
        } else if (instance.getFlycState() == FLYC_STATE.Atti) {
            charSequence = getContext().getString(R.string.gsnew_fpv_flight_mode_atti_tip);
        }
        if (charSequence.isEmpty()) {
            if (instance.getFlycState() == FLYC_STATE.NOVICE && this.d.a() == d$a.Smart.a()) {
                charSequence = getContext().getString(R.string.gsnew_fpv_flight_mode_novice_tip);
            } else if (c != ProductType.A2 && (!instance.isMotorUp() || instance.groundOrSky() != 2)) {
                charSequence = getContext().getString(R.string.gsnew_gs_not_take_off_warning_title);
            } else if (c == ProductType.A2 && !instance.isMotorUp()) {
                charSequence = getContext().getString(R.string.gsnew_gs_not_take_off_warning_title);
            }
            if (!charSequence.isEmpty()) {
                d.getInstance().a(8, charSequence);
                Toast.makeText(getContext(), charSequence, 0).show();
                return false;
            } else if ((i.getInstance().c() == ProductType.A2 && !instance.isMotorUp()) || (i.getInstance().c() != ProductType.A2 && instance.groundOrSky() != 2)) {
                Object aVar = new a();
                aVar.a = R.string.gsnew_gs_not_take_off_warning_title;
                aVar.b = R.string.gsnew_gs_not_take_off_warning_desc;
                aVar.j = R.string.gsnew_gs_main_exit_help_ok;
                aVar.i = "";
                aVar.d = 250;
                aVar.e = 270;
                aVar.k = false;
                aVar.f = R.drawable.gs_warning_icon;
                f.getInstance(getContext()).a(b.EVENT_CONFIRMDIALOG_SHOW, aVar);
                return false;
            } else if (this.e == null || this.e.ordinal() != c.m.ordinal()) {
                return true;
            } else {
                e.c(this.f);
                e.a(getContext());
                dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
                eVar.s = 3;
                dji.thirdparty.a.c.a().e(eVar);
                return false;
            }
        }
        d.getInstance().a(8, charSequence);
        Toast.makeText(getContext(), charSequence, 0).show();
        return false;
    }

    public void setImage(int i) {
        if (this.b != null) {
            this.b.setImageResource(i);
        }
    }

    public void setTitle(int i) {
        if (this.c != null) {
            this.c.setText(getContext().getResources().getString(i));
        }
    }

    private d$a a(DJIFlightControllerFlightMode dJIFlightControllerFlightMode) {
        if (dJIFlightControllerFlightMode == null) {
            return d$a.None;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.Tracking.ordinal()) {
            return d$a.Track;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.Pointing.ordinal()) {
            return d$a.Point;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSFollowMe.ordinal() || dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSHomeLock.ordinal() || dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSWaypoint.ordinal() || dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSCourseLock.ordinal() || dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSHotPoint.ordinal()) {
            return d$a.Smart;
        }
        return d$a.Normal;
    }

    private c b(DJIFlightControllerFlightMode dJIFlightControllerFlightMode) {
        if (dJIFlightControllerFlightMode == null) {
            return c.a;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSFollowMe.ordinal()) {
            return c.m;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSHotPoint.ordinal()) {
            return c.b;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSCourseLock.ordinal()) {
            return c.o;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSHomeLock.ordinal()) {
            return c.q;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.GPSWaypoint.ordinal()) {
            return c.e;
        }
        if (dJIFlightControllerFlightMode.ordinal() == DJIFlightControllerFlightMode.TerrainTracking.ordinal()) {
            return c.t;
        }
        return c.a;
    }

    public void setImageSelected(boolean z) {
        if (this.b != null) {
            this.b.setSelected(z);
        }
    }

    public void setTitleSelected(boolean z) {
        if (this.c != null) {
            this.c.setSelected(z);
        }
    }

    public void setJumpToProcotal(String str) {
        this.f = str;
        String a = e.a(str);
        String b = e.b(str);
        if (a.equals(e.a)) {
            if (b.equals(e.d)) {
                this.d = d$a.Track;
            } else if (b.equals("normal")) {
                this.d = d$a.Normal;
            } else if (b.equals(e.c)) {
                this.d = d$a.Point;
            } else if (b.equals(e.h)) {
                this.d = d$a.Smart;
            } else if (b.equals(e.e)) {
                this.d = d$a.Gesture;
            }
            this.e = c.a;
        } else if (a.equals(e.m)) {
            this.d = d$a.Smart;
            this.e = c.a;
            if (b.equals(e.U)) {
                this.e = c.o;
            } else if (b.equals(e.Q)) {
                this.e = c.m;
            } else if (b.equals(e.X)) {
                this.e = c.q;
            } else if (b.equals(e.t)) {
                this.e = c.b;
            } else if (b.equals(e.B)) {
                this.e = c.e;
            } else if (b.equals(e.aa)) {
                this.e = c.t;
            } else if (b.equals(e.ae)) {
                this.e = c.v;
            }
        }
        d$a a2 = d.getInstance().a();
        c b2 = d.getInstance().b();
        if (this.e != null && this.e.a(b2) && this.e.a() != c.a.a() && a2.a() != c.a.a()) {
            setImageSelected(true);
            setTitleSelected(true);
        } else if (this.d == null || this.d.a() != a2.a() || a2.a() == d$a.Smart.a() || this.d.a() == d$a.None.a() || a2.a() == d$a.None.a()) {
            setImageSelected(false);
            setTitleSelected(false);
        } else {
            setImageSelected(true);
            setTitleSelected(true);
        }
    }
}
