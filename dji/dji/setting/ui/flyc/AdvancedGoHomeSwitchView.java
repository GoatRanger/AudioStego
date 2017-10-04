package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.common.error.DJIError;
import dji.log.DJILogHelper;
import dji.midware.data.params.P3.ParamInfo;
import dji.sdksharedlib.e.a.a;
import dji.sdksharedlib.e.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class AdvancedGoHomeSwitchView extends ItemViewSwitch {
    private static final String a = "AdvancedGoHomeSwitchView";
    private static final String e = "FlightControllerConfigAdvancedGoHomeSwitch";
    private String f = "";

    public AdvancedGoHomeSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = 1;
        ParamInfo a = a.getInstance().a("FlightControllerConfigAdvancedGoHomeSwitch");
        DJILogHelper.getInstance().LOGD(a, "" + a.value.toString(), true, true);
        if ((a.value.intValue() == 1) != z) {
            a instance = a.getInstance();
            String str = "FlightControllerConfigAdvancedGoHomeSwitch";
            if (!z) {
                i = 0;
            }
            instance.a(str, Integer.valueOf(i), new e(this) {
                final /* synthetic */ AdvancedGoHomeSwitchView a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                }

                public void a(DJIError dJIError) {
                    DJILogHelper.getInstance().LOGD(AdvancedGoHomeSwitchView.a, "fails code = " + dJIError.getDescription());
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a();
                        }
                    });
                }
            });
        }
    }

    private void a() {
        boolean z = true;
        if (!a.getInstance().b("FlightControllerConfigAdvancedGoHomeSwitch") || dji.pilot.publics.e.a.g()) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        ParamInfo a = a.getInstance().a("FlightControllerConfigAdvancedGoHomeSwitch");
        DJILogHelper.getInstance().LOGD(a, "" + a.value.toString(), true, true);
        if (a.value.intValue() != 1) {
            z = false;
        }
        this.eS_.setChecked(z);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f = ((b) dji.sdksharedlib.e.a.a.a.a().get("FlightControllerConfigAdvancedGoHomeSwitch")).h;
        if (!isInEditMode()) {
            a();
            a.b().a(((b) dji.sdksharedlib.e.a.a.a.a().get("FlightControllerConfigAdvancedGoHomeSwitch")).h);
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals(this.f)) {
            a();
        }
    }
}
