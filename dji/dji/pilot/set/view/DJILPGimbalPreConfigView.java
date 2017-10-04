package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataGimbalGetPushUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.thirdparty.a.c;

public class DJILPGimbalPreConfigView extends LinearLayout implements OnCheckedChangeListener {
    private RadioGroup a;
    private d b;

    public DJILPGimbalPreConfigView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public DJILPGimbalPreConfigView(Context context, AttributeSet attributeSet, int i) {
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
        addView(((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.lp_set_item_gimbal_pre_config, null, false));
        this.b = new d(this) {
            final /* synthetic */ DJILPGimbalPreConfigView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(a aVar) {
            }
        };
        this.a = (RadioGroup) findViewById(R.id.set_gimbal_preconfig_group);
        this.a.setOnCheckedChangeListener(this);
    }

    private void b() {
        a(DataGimbalGetPushUserParams.getInstance());
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        Log.v("Hello", "OnChange!");
        if (i == R.id.set_gimbal_preconfig_slow) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(5));
        } else if (i == R.id.set_gimbal_preconfig_middle) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(4));
        } else if (i == R.id.set_gimbal_preconfig_quick) {
            DataGimbalSetUserParams.getInstance().a("table_choice", Integer.valueOf(3));
        }
        DataGimbalSetUserParams.getInstance().start(this.b);
    }

    private void a(LinearLayout linearLayout, boolean z) {
        if (z) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
    }

    private void a(DataGimbalGetPushUserParams dataGimbalGetPushUserParams) {
        switch (dataGimbalGetPushUserParams.getPresetID()) {
            case 3:
                this.a.check(R.id.set_gimbal_preconfig_quick);
                return;
            case 4:
                this.a.check(R.id.set_gimbal_preconfig_middle);
                return;
            case 5:
                this.a.check(R.id.set_gimbal_preconfig_slow);
                return;
            default:
                return;
        }
    }

    private void a(String str, boolean z) {
        if (z) {
            DataGimbalSetUserParams.getInstance().a(str, Integer.valueOf(1));
        } else {
            DataGimbalSetUserParams.getInstance().a(str, Integer.valueOf(0));
        }
        DataGimbalSetUserParams.getInstance().start(this.b);
    }
}
