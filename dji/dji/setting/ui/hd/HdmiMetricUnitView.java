package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.e.d;
import dji.midware.f.a;
import dji.midware.f.b;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class HdmiMetricUnitView extends ItemViewSwitch {
    public HdmiMetricUnitView(Context context, AttributeSet attributeSet) {
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
        } else if (a.c()) {
            setVisibility(8);
        } else {
            int i = (!DataDm368_gGetPushCheckStatus.getInstance().isGetted() || DataDm368_gGetPushCheckStatus.getInstance().getHDMIExist()) ? 0 : 1;
            if (dji.pilot.publics.e.a.g(i.getInstance().c()) || i != 0) {
                setVisibility(0);
                DataDm368GetGParams.getInstance().start(new d(this) {
                    final /* synthetic */ HdmiMetricUnitView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.eS_.setChecked(DataDm368GetGParams.getInstance().getUnit());
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
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

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != DataDm368GetGParams.getInstance().getUnit()) {
            new DataDm368SetGParams().a(CmdId.g, z ? 1 : 0).start(new d(this) {
                final /* synthetic */ HdmiMetricUnitView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.f();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

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
