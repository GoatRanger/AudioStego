package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataFlycStartFollowMeWithInfo;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.view.DJIGSFollowMeView;
import dji.pilot.publics.widget.CustomerSpinner;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIFollowMeStageView extends DJIRelativeLayout implements c$i, a {
    private DJITextView n = null;
    private DJITextView o = null;
    private DJIGSFollowMeView p = null;
    private double q = 0.0d;
    private CustomerSpinner r = null;
    private OnClickListener s = new OnClickListener(this) {
        final /* synthetic */ DJIFollowMeStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            int i = 1;
            switch (view.getId()) {
                case R.id.ajp:
                    this.a.u = true;
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    dji.pilot.groundStation.a.a.getInstance(null).a(this.a);
                    return;
                case R.id.ajq:
                    b bVar = new b();
                    bVar.a = d.b;
                    bVar.f = c.a;
                    if (this.a.q > 200.0d) {
                        bVar.b = R.string.gs_follow_me_radius_too_far;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                    float height = ((float) DataOsdGetPushCommon.getInstance().getHeight()) / 10.0f;
                    if (height < 10.0f || height > 120.0f) {
                        bVar.b = R.string.gs_follow_me_aircraft_not_in_height_zone;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                    final dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
                    final dji.gs.e.b k = k.k();
                    this.a.findViewById(R.id.ajq).setEnabled(false);
                    if (this.a.r.getSelectedItemPosition() != 0) {
                        i = 0;
                    }
                    new DataFlycSetParams().a("g_config.followme_cfg.enable_change_homepoint_0", Integer.valueOf(i)).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass1 c;

                        public void onSuccess(Object obj) {
                            instance.a(new dji.midware.e.d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    int result = DataFlycStartFollowMeWithInfo.getInstance().getResult();
                                    if (result == 0) {
                                        instance.a(dji.pilot.groundStation.a.a.d.FOLLOW_ME);
                                        this.a.c.a.t.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                e.c(c$i.e);
                                                this.a.a.c.a.u = true;
                                                this.a.a.c.a.findViewById(R.id.ajq).setEnabled(true);
                                                ((DJIStageView) this.a.a.c.a.getParent()).createStageView(R.layout.gs_follow_me_status_view, 14, true);
                                            }
                                        });
                                        return;
                                    }
                                    this.a.c.a.a();
                                    instance.a((int) R.string.gs_follow_me_send_command_failed, dji.pilot.groundStation.a.a(this.a.c.a.getContext(), result), false);
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    this.a.c.a.a();
                                    instance.a((int) R.string.gs_follow_me_send_command_failed, aVar, false);
                                }
                            }, k);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.c.a.a();
                            instance.a((int) R.string.gs_follow_me_send_command_failed, aVar, false);
                        }
                    });
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler t = new Handler();
    private boolean u = false;
    private final Runnable v = new Runnable(this) {
        final /* synthetic */ DJIFollowMeStageView a;

        {
            this.a = r1;
        }

        public void run() {
            float f = 0.0f;
            if (!this.a.u) {
                float atan2;
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                dji.gs.e.b k = k.k();
                if (k != null) {
                    double latitude = instance.getLatitude();
                    double longitude = instance.getLongitude();
                    if (!(latitude == 0.0d && longitude == 0.0d)) {
                        dji.gs.e.b bVar = new dji.gs.e.b(latitude, longitude);
                        atan2 = (float) ((Math.atan2((bVar.c - k.c) * 1000.0d, (-(bVar.b - k.b)) * FlyForbidProtocol.UNLOCK_RADIUS) * 180.0d) / 3.141592653589793d);
                        if (atan2 < 0.0f) {
                            f = atan2 + 360.0f;
                        } else {
                            f = atan2;
                        }
                        latitude = dji.gs.utils.a.a(bVar.b, bVar.c, k.b, k.c);
                        this.a.q = Math.sqrt((latitude * latitude) + ((double) ((instance.getHeight() * instance.getHeight()) / 100)));
                    }
                } else {
                    this.a.o.setText(this.a.getContext().getString(R.string.gs_follow_me_phone_gps_weak));
                    this.a.o.setTextColor(SupportMenu.CATEGORY_MASK);
                }
                atan2 = ((float) instance.getYaw()) / 10.0f;
                float w = (float) dji.pilot.groundStation.a.a.getInstance(null).w();
                this.a.p.updateRotate(f + w, atan2 - w, (atan2 + (((float) DataGimbalGetPushParams.getInstance().getYawAngle()) / 10.0f)) - w);
                atan2 = ((float) DataOsdGetPushCommon.getInstance().getHeight()) / 10.0f;
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    this.a.n.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(atan2))}));
                } else {
                    this.a.n.setText(String.format("%.1fM", new Object[]{Float.valueOf(atan2)}));
                }
                if (atan2 < 10.0f || atan2 > 120.0f) {
                    this.a.n.setTextColor(SupportMenu.CATEGORY_MASK);
                } else {
                    this.a.n.setTextColor(this.a.getContext().getResources().getColor(R.color.er));
                }
                this.a.t.postDelayed(this.a.v, 500);
            }
        }
    };

    public DJIFollowMeStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.u = true;
    }

    public void dispatchOnResume() {
        this.u = false;
        this.t.post(this.v);
        findViewById(R.id.ajq).setEnabled(true);
    }

    public void dispatchOnPause() {
        this.u = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.ajp).setOnClickListener(this.s);
            findViewById(R.id.ajq).setOnClickListener(this.s);
            this.p = (DJIGSFollowMeView) findViewById(R.id.ajt);
            this.n = (DJITextView) findViewById(R.id.ajv);
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.n.setText("0FT");
            } else {
                this.n.setText("0M");
            }
            this.o = (DJITextView) findViewById(R.id.aju);
            this.o.setText(getContext().getString(R.string.gs_follow_me_phone_gps_weak));
            this.r = (CustomerSpinner) findViewById(R.id.ajs);
            this.r.setData(new String[]{getContext().getString(R.string.gs_follow_me_update_home_point_mode_my_location), getContext().getString(R.string.gs_follow_me_update_home_point_mode_original)});
        }
    }

    private void a() {
        this.t.post(new Runnable(this) {
            final /* synthetic */ DJIFollowMeStageView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.findViewById(R.id.ajq).setEnabled(true);
            }
        });
    }
}
