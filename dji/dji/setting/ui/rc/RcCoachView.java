package dji.setting.ui.rc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataRcCoachMode;
import dji.midware.data.model.P3.DataRcCoachMode.CoachMode;
import dji.midware.data.model.P3.DataRcCoachMode.OptMode;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.c.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.rc.RcMasterSlaveView.c;
import dji.setting.ui.widget.DividerLinearLayout;

public class RcCoachView extends DividerLinearLayout implements OnCheckedChangeListener {
    private Switch a = null;
    private CoachMode b = CoachMode.b;
    private DataCommonGetVersion c = null;
    private final Runnable d = new Runnable(this) {
        final /* synthetic */ RcCoachView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b();
        }
    };

    public RcCoachView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_rc_coach_view);
        if (!isInEditMode()) {
            this.c = new DataCommonGetVersion();
            this.c.setDeviceType(DeviceType.OSD);
            this.a = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.a.setOnCheckedChangeListener(this);
        }
    }

    public void onEventMainThread(c cVar) {
        if (cVar != null) {
            a(d.b);
        }
    }

    public void onEventMainThread(i.a aVar) {
        a(d.b);
    }

    public void onEventMainThread(DataCommonGetVersion dataCommonGetVersion) {
        if (dataCommonGetVersion.getDeviceType() == DeviceType.OSD) {
            a(d.b);
        }
    }

    private void a(MODE mode) {
        if (!dji.pilot.publics.e.a.a(i.getInstance().a(), dji.pilot.publics.e.a.e(this.c.isGetted() ? this.c.getFirmVer(".") : ""))) {
            setVisibility(8);
        } else if (mode == null || mode == MODE.a) {
            setVisibility(0);
            b();
        } else {
            setVisibility(8);
        }
    }

    private void a() {
        this.c.start(new dji.midware.e.d(this) {
            final /* synthetic */ RcCoachView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a(d.b);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void b() {
        DataRcCoachMode.getInstance().a(OptMode.a).start(new dji.midware.e.d(this) {
            final /* synthetic */ RcCoachView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b = DataRcCoachMode.getInstance().a();
                        this.a.a.c();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.postDelayed(this.a.d, 100);
            }
        });
    }

    private void c() {
        if (CoachMode.c == this.b) {
            this.a.setChecked(true);
        } else {
            this.a.setChecked(false);
        }
    }

    private void setCoachMode(final CoachMode coachMode) {
        new DataRcCoachMode().a(OptMode.b).a(coachMode).start(new dji.midware.e.d(this) {
            final /* synthetic */ RcCoachView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.b = coachMode;
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.c();
                    }
                });
            }
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        final CoachMode coachMode = z ? CoachMode.c : CoachMode.b;
        if (coachMode != this.b) {
            if (z) {
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_rc_coach_tip_desc, new OnClickListener(this) {
                    final /* synthetic */ RcCoachView b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.setCoachMode(coachMode);
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ RcCoachView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.c();
                    }
                });
            } else {
                setCoachMode(coachMode);
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!dji.thirdparty.a.c.a().c(this)) {
                dji.thirdparty.a.c.a().a(this);
            }
            a();
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.d);
        if (!isInEditMode() && dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
