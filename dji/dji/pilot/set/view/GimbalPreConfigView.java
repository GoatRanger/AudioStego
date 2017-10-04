package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataGimbalGetPushUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.thirdparty.a.c;

public class GimbalPreConfigView extends LinearLayout implements OnCheckedChangeListener {
    private RadioGroup a;
    private Switch b;
    private Switch c;
    private Switch d;
    private d e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private LinearLayout i;
    private LinearLayout j;
    private LinearLayout k;

    public GimbalPreConfigView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public GimbalPreConfigView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a();
        b();
    }

    protected void onAttachedToWindow() {
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
    }

    public void onEventMainThread(DataGimbalGetPushUserParams dataGimbalGetPushUserParams) {
        a(dataGimbalGetPushUserParams);
    }

    private void a() {
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.set_item_gimbal_pre_config, null, false));
        this.e = new d(this) {
            final /* synthetic */ GimbalPreConfigView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(a aVar) {
            }
        };
        this.a = (RadioGroup) findViewById(R.id.set_gimbal_preconfig_group);
        this.b = (Switch) findViewById(R.id.advance_settings_switch);
        this.c = (Switch) findViewById(R.id.show_item_pan_switch);
        this.d = (Switch) findViewById(R.id.show_item_tilt_switch);
        this.f = (LinearLayout) findViewById(R.id.advance_settings_linearlayout);
        this.g = (LinearLayout) findViewById(R.id.set_gimbal_pan_bar);
        this.h = (LinearLayout) findViewById(R.id.set_gimbal_tilt_bar);
        this.i = (LinearLayout) findViewById(R.id.set_gimbal_bar);
        this.j = (LinearLayout) findViewById(R.id.set_gimbal_control_stick);
        this.k = (LinearLayout) findViewById(R.id.set_gimbal_preconfig_sub_layout);
        this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
            final /* synthetic */ GimbalPreConfigView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.a(this.a.f, z);
            }
        });
        this.c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
            final /* synthetic */ GimbalPreConfigView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.a(this.a.g, z);
                this.a.a("yaw_smooth_track", z);
                GimbalPreConfigView gimbalPreConfigView = this.a;
                LinearLayout c = this.a.i;
                boolean z2 = z || this.a.d.isChecked();
                gimbalPreConfigView.a(c, z2);
            }
        });
        this.d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) {
            final /* synthetic */ GimbalPreConfigView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.a(this.a.h, z);
                this.a.a("pitch_smooth_track", z);
                GimbalPreConfigView gimbalPreConfigView = this.a;
                LinearLayout c = this.a.i;
                boolean z2 = z || this.a.c.isChecked();
                gimbalPreConfigView.a(c, z2);
            }
        });
        this.a.setOnCheckedChangeListener(this);
        a(this.f, false);
    }

    private void b() {
        a(DataGimbalGetPushUserParams.getInstance());
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.v("Hello", "OnChange!");
        if (i == R.id.set_gimbal_preconfig_customer1) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(0));
            setLinearLayoutAlpha(false);
            this.b.setChecked(true);
        } else if (i == R.id.set_gimbal_preconfig_customer2) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(1));
            setLinearLayoutAlpha(false);
            this.b.setChecked(true);
        } else if (i == R.id.set_gimbal_preconfig_slow) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(5));
            setLinearLayoutAlpha(true);
            this.b.setChecked(false);
        } else if (i == R.id.set_gimbal_preconfig_middle) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(4));
            setLinearLayoutAlpha(true);
            this.b.setChecked(false);
        } else if (i == R.id.set_gimbal_preconfig_quick) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(3));
            setLinearLayoutAlpha(true);
            this.b.setChecked(false);
        }
        DataGimbalSetUserParams.getInstance().start(this.e);
    }

    private void a(LinearLayout linearLayout, boolean z) {
        if (z) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
    }

    private void a(DataGimbalGetPushUserParams dataGimbalGetPushUserParams) {
        LinearLayout linearLayout = this.i;
        boolean z = (dataGimbalGetPushUserParams.getPitchSmoothTrack() == 0 && dataGimbalGetPushUserParams.getYawSmoothTrack() == 0) ? false : true;
        a(linearLayout, z);
        if (dataGimbalGetPushUserParams.getYawSmoothTrack() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.c.setChecked(z);
        a(this.g, z);
        if (dataGimbalGetPushUserParams.getPitchSmoothTrack() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.d.setChecked(z);
        a(this.h, z);
        switch (dataGimbalGetPushUserParams.getPresetID()) {
            case 0:
                this.a.check(R.id.set_gimbal_preconfig_customer1);
                setLinearLayoutAlpha(false);
                return;
            case 1:
                this.a.check(R.id.set_gimbal_preconfig_customer2);
                setLinearLayoutAlpha(false);
                return;
            case 3:
                this.a.check(R.id.set_gimbal_preconfig_quick);
                setLinearLayoutAlpha(true);
                return;
            case 4:
                this.a.check(R.id.set_gimbal_preconfig_middle);
                setLinearLayoutAlpha(true);
                return;
            case 5:
                this.a.check(R.id.set_gimbal_preconfig_slow);
                setLinearLayoutAlpha(true);
                return;
            default:
                return;
        }
    }

    private void setLinearLayoutAlpha(boolean z) {
        if (z) {
            this.i.setAlpha(0.3f);
            this.j.setAlpha(0.3f);
            this.k.setAlpha(0.3f);
            this.c.setClickable(false);
            this.d.setClickable(false);
            this.c.setEnabled(false);
            this.d.setEnabled(false);
            return;
        }
        this.i.setAlpha(1.0f);
        this.j.setAlpha(1.0f);
        this.k.setAlpha(1.0f);
        this.c.setClickable(true);
        this.d.setClickable(true);
        this.c.setEnabled(true);
        this.d.setEnabled(true);
    }

    private void a(String str, boolean z) {
        if (z) {
            DataGimbalSetUserParams.getInstance().a(str, Integer.valueOf(1));
        } else {
            DataGimbalSetUserParams.getInstance().a(str, Integer.valueOf(0));
        }
        DataGimbalSetUserParams.getInstance().start(this.e);
    }
}
