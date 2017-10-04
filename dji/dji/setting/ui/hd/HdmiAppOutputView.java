package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.e.d;
import dji.midware.f.a;
import dji.midware.f.b;
import dji.pilot.fpv.d.e;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class HdmiAppOutputView extends ItemViewSwitch {
    public HdmiAppOutputView(Context context, AttributeSet attributeSet) {
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
            setVisibility(8);
        } else {
            int i = (!DataDm368_gGetPushCheckStatus.getInstance().isGetted() || DataDm368_gGetPushCheckStatus.getInstance().getHDMIExist()) ? 0 : 1;
            if (dji.pilot.publics.e.a.g(i.getInstance().c()) || i != 0 || a.b()) {
                setVisibility(0);
                this.eS_.setChecked(DataDm368GetGParams.getInstance().getIsDouble());
                return;
            }
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

    public void onEventMainThread(DataDm368_gGetPushCheckStatus dataDm368_gGetPushCheckStatus) {
        DJILogHelper.getInstance().LOGD("368", "" + dataDm368_gGetPushCheckStatus.getHDMIExist(), false, true);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != DataDm368GetGParams.getInstance().getIsDouble()) {
            int i;
            if (z) {
                e.a("FPV_ImageTransmissionSettings_Switcher_Output_ON");
            } else {
                e.a("FPV_ImageTransmissionSettings_Switcher_DualOutput_OFF");
            }
            DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
            CmdId cmdId = CmdId.h;
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            dataDm368SetGParams.a(cmdId, i).start(new d(this) {
                final /* synthetic */ HdmiAppOutputView a;

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
