package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.e.d;
import dji.midware.f.a;
import dji.midware.f.b;
import dji.pilot.fpv.d.e;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class LB2HdmiAppOutputView extends ItemViewSwitch {
    public LB2HdmiAppOutputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            b();
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        a.f();
    }

    private void b() {
        if (a.getInstance().d() == b.f) {
            setVisibility(8);
        } else if (a.a()) {
            setVisibility(0);
            this.eS_.setChecked(DataDm368GetGParams.getInstance().getOutputEnable());
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    public void onEventMainThread(b bVar) {
        b();
        if (bVar != b.f) {
            a();
        }
    }

    public void onEventMainThread(a aVar) {
        b();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != DataDm368GetGParams.getInstance().getOutputEnable()) {
            int i;
            if (z) {
                e.a("FPV_ImageTransmissionSettings_Switcher_Output_ON");
            } else {
                e.a("FPV_ImageTransmissionSettings_Switcher_DualOutput_OFF");
            }
            DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
            CmdId cmdId = CmdId.n;
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            dataDm368SetGParams.a(cmdId, i).start(new d(this) {
                final /* synthetic */ LB2HdmiAppOutputView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.f();
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
}
