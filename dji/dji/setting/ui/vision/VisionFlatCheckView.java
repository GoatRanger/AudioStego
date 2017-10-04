package dji.setting.ui.vision;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.common.error.DJIError;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.a;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.e.a.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class VisionFlatCheckView extends DividerLinearLayout implements OnCheckedChangeListener, a {
    private Switch E = null;
    private ParamInfo F = null;
    private String G = null;
    private int H = 0;
    private final String a = e.cR;

    public VisionFlatCheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_vision_flatcheck);
        if (!isInEditMode()) {
            this.F = dji.sdksharedlib.e.a.a.getInstance().a(e.cR);
            this.G = ((b) dji.sdksharedlib.e.a.a.a.a().get(e.cR)).h;
            this.E = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.E.setOnCheckedChangeListener(this);
        }
    }

    private void setChecked(boolean z) {
        int i = 0;
        this.E.setEnabled(false);
        dji.sdksharedlib.e.a.a instance = dji.sdksharedlib.e.a.a.getInstance();
        String str = e.cR;
        if (z) {
            i = 1;
        }
        instance.a(str, Integer.valueOf(i), new dji.sdksharedlib.hardware.abstractions.b.e(this) {
            final /* synthetic */ VisionFlatCheckView a;

            {
                this.a = r1;
            }

            public void a(Object obj) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.E.setEnabled(true);
                    }
                });
            }

            public void a(DJIError dJIError) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a(false);
                        this.a.a.E.setEnabled(true);
                    }
                });
            }
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if ((this.F.value.intValue() != 0) != z) {
            setChecked(z);
        }
    }

    private void a(boolean z) {
        this.E.setChecked(this.F.value.intValue() != 0);
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (flycVersion != this.H) {
            this.H = flycVersion;
            b();
        }
    }

    public void onEventMainThread(a.a aVar) {
        if (this.G.equals(aVar.a)) {
            a(false);
        }
        if ("g_config.mvo_cfg.mvo_func_en_0".equals(aVar.a)) {
            b();
        }
    }

    private void b() {
        ProductType c = i.getInstance().c();
        if ((ProductType.Pomato == c || dji.pilot.publics.e.a.c(c)) && this.H >= 16) {
            if (d.read("g_config.mvo_cfg.mvo_func_en_0").value.intValue() != 0) {
                setVisibility(0);
            } else {
                setVisibility(8);
            }
            a(false);
            b.getInstance().a(this.G);
            return;
        }
        setVisibility(8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            b();
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
