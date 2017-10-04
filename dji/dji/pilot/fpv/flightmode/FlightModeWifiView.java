package dji.pilot.fpv.flightmode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.newfpv.d;
import dji.pilot.newfpv.f.i;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.view.b;
import dji.pilot.visual.a.g;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.publics.DJIUI.DJIRelativeLayout;
import java.util.ArrayList;
import java.util.List;

public class FlightModeWifiView extends DJIRelativeLayout implements a, h<i>, g {
    private LinearLayout a;
    private LinearLayout b;
    private LinearLayout c = null;
    private LinearLayout d;
    private ImageView e;
    private List<LinearLayout> f = new ArrayList();
    private OnClickListener g;
    private c h;
    private dji.pilot.newfpv.g i = null;
    private int j = 0;
    private d k = null;

    public FlightModeWifiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bindInfo(b.a.ViewId_WifiFlightMode, i.SHOW, i.HIDE);
    }

    private void a() {
        setVisibility(8);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.h = c.getInstance();
            this.e = (ImageView) findViewById(R.id.adn);
            this.e.setLayoutParams(new LayoutParams((dji.pilot.fpv.model.b.a(getContext(), R.dimen.pr) + (dji.pilot.fpv.model.b.a(getContext(), R.dimen.n3) * 2)) + (dji.pilot.fpv.model.b.a(getContext(), R.dimen.pg) * 2), -1));
            this.e.setBackgroundColor(getResources().getColor(R.color.a_));
            this.a = (LinearLayout) findViewById(R.id.ado);
            this.b = (LinearLayout) findViewById(R.id.adp);
            this.c = (LinearLayout) findViewById(R.id.adq);
            this.d = (LinearLayout) findViewById(R.id.adr);
            this.a.setTag(c$b.POINT);
            this.b.setTag(c$b.TRACK);
            this.c.setTag(c$b.TRACK_SELFIE);
            this.d.setTag(c$b.NORMAL);
            this.f.add(this.a);
            this.f.add(this.b);
            this.f.add(this.c);
            this.f.add(this.d);
            this.g = new OnClickListener(this) {
                final /* synthetic */ FlightModeWifiView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (view.getTag().equals(this.a.h.a())) {
                        this.a.a();
                        return;
                    }
                    switch (view.getId()) {
                        case R.id.ado:
                            this.a.a(c$b.POINT, g$e.POINT_MODE, g$f.WORKING);
                            return;
                        case R.id.adp:
                            this.a.a(c$b.TRACK, g$e.TRACK_MODE, g$f.WORKING);
                            return;
                        case R.id.adq:
                            this.a.a(c$b.TRACK_SELFIE, g$e.TRACK_MODE, g$f.WORKING);
                            return;
                        case R.id.adr:
                            this.a.a(c$b.NORMAL, g$e.NONE, g$f.NONE);
                            return;
                        default:
                            return;
                    }
                }
            };
            this.a.setOnClickListener(this.g);
            this.b.setOnClickListener(this.g);
            this.c.setOnClickListener(this.g);
            this.d.setOnClickListener(this.g);
        }
    }

    private void b() {
        int size = this.f.size();
        for (int i = 0; i != size; i++) {
            ((LinearLayout) this.f.get(i)).setSelected(false);
        }
    }

    private void a(final c$b dji_pilot_fpv_flightmode_c_b, final g$e dji_pilot_visual_a_g_e, final g$f dji_pilot_visual_a_g_f) {
        a();
        if (c$b.TRACK_SELFIE == dji_pilot_fpv_flightmode_c_b || c$b.TRACK == dji_pilot_fpv_flightmode_c_b || c$b.POINT == dji_pilot_fpv_flightmode_c_b) {
            CameraOrientation cameraOrientation = (CameraOrientation) dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.bW);
            if (cameraOrientation != null && cameraOrientation.equals(CameraOrientation.Portrait)) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.a = DJIErrorPopView.d.b;
                bVar.f = c.a;
                bVar.b = R.string.gs_hint_no_mission_in_rotation_mode;
                dji.thirdparty.a.c.a().e(bVar);
                return;
            }
        }
        if (!this.h.b() || b.a(getContext(), false)) {
            if (c$b.TRACK == dji_pilot_fpv_flightmode_c_b) {
                if (!b.b(getContext())) {
                    return;
                }
            } else if (c$b.POINT == dji_pilot_fpv_flightmode_c_b) {
                if (!b.a()) {
                    return;
                }
            } else if (c$b.TRACK_SELFIE == dji_pilot_fpv_flightmode_c_b && !b.a(getContext())) {
                return;
            }
            if (c$b.TRACK_SELFIE == dji_pilot_fpv_flightmode_c_b) {
                b.a(getContext(), new Runnable(this) {
                    final /* synthetic */ FlightModeWifiView d;

                    public void run() {
                        this.d.a(dji_pilot_fpv_flightmode_c_b, dji_pilot_visual_a_g_f, dji_pilot_visual_a_g_e);
                    }
                });
            } else {
                a(dji_pilot_fpv_flightmode_c_b, dji_pilot_visual_a_g_f, dji_pilot_visual_a_g_e);
            }
        }
    }

    private void a(c$b dji_pilot_fpv_flightmode_c_b, g$f dji_pilot_visual_a_g_f, g$e dji_pilot_visual_a_g_e) {
        this.h.a(dji_pilot_fpv_flightmode_c_b);
        if (dji_pilot_visual_a_g_f == g$f.WORKING) {
            dji.pilot.visual.a.c.getInstance().a(g$f.WORKING);
            dji.pilot.visual.a.c.getInstance().a(dji_pilot_visual_a_g_e);
            dji.thirdparty.a.c.a().e(g$d.ENTER_VISUAL);
            return;
        }
        dji.pilot.visual.a.c.getInstance().g();
        dji.thirdparty.a.c.a().e(g$d.EXIT_VISUAL);
    }

    private void c() {
        b();
        c$b a = this.h.a();
        int size = this.f.size();
        for (int i = 0; i != size; i++) {
            if (((LinearLayout) this.f.get(i)).getTag().equals(a)) {
                ((LinearLayout) this.f.get(i)).setSelected(true);
            }
        }
    }

    public void onEventMainThread(c$b dji_pilot_fpv_flightmode_c_b) {
        c();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            c();
        }
    }

    protected void onDetachedFromWindow() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    public void bind(dji.pilot.newfpv.g gVar, int i) {
        this.i = gVar;
        this.j = i;
    }

    public void bindInfo(b.a aVar, i iVar, i iVar2) {
        this.k = new d(this, aVar, iVar, iVar2);
    }

    public b.a getViewId() {
        return this.k.b;
    }

    public d getViewInfo() {
        return this.k;
    }

    public boolean needShow() {
        if (dji.logic.c.b.getInstance().a(null)) {
            return true;
        }
        return false;
    }

    public View getSelf() {
        return this;
    }

    public void onEventMainThread(i iVar) {
        if (iVar == i.SHOW) {
            if (isShown()) {
                setVisibility(8);
            } else if (needShow() && this.i.a(this.k, 0)) {
                setVisibility(0);
            }
        } else if (iVar == i.HIDE) {
            setVisibility(8);
        }
    }
}
