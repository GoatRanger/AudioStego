package dji.setting.ui.vision;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.common.error.DJIError;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.a;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.setting.ui.vision.a.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class PreciseLandingSwitchView extends DividerLinearLayout implements OnCheckedChangeListener, a {
    private static final String E = "FlightControllerConfigPreciseLandingSwitch";
    private static final String a = "PreciseLandingSwitchView";
    private String F = "";
    private Switch G = null;

    public PreciseLandingSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_vision_preciselanding);
        if (!isInEditMode()) {
            this.G = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.G.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = 1;
        ParamInfo a = dji.sdksharedlib.e.a.a.getInstance().a("FlightControllerConfigPreciseLandingSwitch");
        DJILogHelper.getInstance().LOGD(a, "" + a.value.toString(), true, true);
        if ((a.value.intValue() == 1) != z) {
            dji.sdksharedlib.e.a.a instance = dji.sdksharedlib.e.a.a.getInstance();
            String str = "FlightControllerConfigPreciseLandingSwitch";
            if (!z) {
                i = 0;
            }
            instance.a(str, Integer.valueOf(i), new e(this) {
                final /* synthetic */ PreciseLandingSwitchView a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                }

                public void a(DJIError dJIError) {
                    DJILogHelper.getInstance().LOGD(PreciseLandingSwitchView.a, "fails code = " + dJIError.getDescription());
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

    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        if (visibility != i) {
            c.a().e(new a.c(b.VisionViewVisibilityEvent, 0));
        }
    }

    private void b() {
        boolean z = true;
        ProductType c = i.getInstance().c();
        ParamInfo read = d.read("g_config.mvo_cfg.mvo_func_en_0");
        if (!dji.sdksharedlib.e.a.a.getInstance().b("FlightControllerConfigPreciseLandingSwitch") || (!(ProductType.Pomato == c || dji.pilot.publics.e.a.c(c)) || read.value.intValue() == 0)) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        ParamInfo a = dji.sdksharedlib.e.a.a.getInstance().a("FlightControllerConfigPreciseLandingSwitch");
        DJILogHelper.getInstance().LOGD(a, "" + a.value.toString(), true, true);
        if (a.value.intValue() != 1) {
            z = false;
        }
        this.G.setChecked(z);
    }

    public void onEventMainThread(a.a aVar) {
        if ("g_config.mvo_cfg.mvo_func_en_0".equals(aVar.a)) {
            b();
        }
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.F = ((dji.sdksharedlib.e.a.b) dji.sdksharedlib.e.a.a.a.a().get("FlightControllerConfigPreciseLandingSwitch")).h;
        if (!isInEditMode()) {
            b();
            dji.setting.ui.flyc.a.b().a(((dji.sdksharedlib.e.a.b) dji.sdksharedlib.e.a.a.a.a().get("FlightControllerConfigPreciseLandingSwitch")).h);
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(dji.setting.ui.flyc.a.a aVar) {
        if (aVar.a.equals(this.F)) {
            b();
        }
    }
}
