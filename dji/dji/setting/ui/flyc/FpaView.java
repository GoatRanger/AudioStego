package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class FpaView extends DividerLinearLayout implements OnCheckedChangeListener {
    private static final String a = "g_config.control.multi_control_mode_enable_0";
    private Switch b;
    private TextView c;
    private ImageView d;

    public FpaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_mode);
        this.b = (Switch) findViewById(R.id.setting_ui_item_switch);
        this.c = (TextView) findViewById(R.id.setting_ui_flyc_mode_desc);
        this.d = (ImageView) findViewById(R.id.setting_ui_flyc_mode_img);
        if (!isInEditMode()) {
            this.b.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (DataOsdGetPushHome.getInstance().isMultipleModeOpen() != z) {
            new DataFlycSetParams().a(a, Integer.valueOf(z ? 1 : 0)).start(new d(this) {
                final /* synthetic */ FpaView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.b();
                        }
                    });
                }
            });
        }
    }

    private void b() {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 3 || dji.pilot.publics.e.a.a(i.getInstance().c())) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        if (this.b.isChecked() != instance.isMultipleModeOpen()) {
            this.b.setChecked(instance.isMultipleModeOpen());
        }
        ProductType c = i.getInstance().c();
        if (c == ProductType.litchiC) {
            this.d.setImageResource(R.drawable.setting_ui_flyc_mode_s1paf);
            this.c.setText(R.string.setting_ui_flyc_mode_nosport);
        } else if ((!dji.pilot.publics.e.a.k(c) || !dji.pilot.publics.e.a.w(c)) && !dji.pilot.publics.e.a.h()) {
            this.d.setImageResource(R.drawable.setting_ui_flyc_mode_paf);
            if (dji.pilot.publics.e.a.w(c)) {
                this.c.setText(R.string.setting_ui_flyc_mode_sport_novision);
            } else {
                this.c.setText(R.string.setting_ui_flyc_mode_nosport);
            }
        } else if (dji.pilot.publics.e.a.c(c)) {
            this.d.setImageResource(R.drawable.setting_ui_flyc_kumquat_rc_mode);
            this.c.setText(R.string.setting_ui_flyc_mode_sport_kumquat);
        } else {
            this.d.setImageResource(R.drawable.setting_ui_flyc_mode_asp);
            this.c.setText(R.string.setting_ui_flyc_mode_sport_p4);
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        b();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }
}
