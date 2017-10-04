package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.publics.a;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class LB2OutputPortView extends ItemViewRadio {
    public LB2OutputPortView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g.setText(R.string.setting_ui_hd_output_hdmi_desc);
        this.h.setText(R.string.setting_ui_hd_output_sdi_desc);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            a.f();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2OutputPortView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }, 700);
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    private void a() {
        if (a.a()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        if (!DataDm368GetGParams.getInstance().getOutputEnable()) {
            setVisibility(8);
        } else if (DataDm368GetGParams.getInstance().getOutputDevice() == 0) {
            this.g.setChecked(true);
        } else {
            this.h.setChecked(true);
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2;
        int outputDevice = DataDm368GetGParams.getInstance().getOutputDevice();
        if (i == this.g.getId()) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        if (i2 != outputDevice) {
            new DataDm368SetGParams().a(CmdId.i, i2).start(new d(this) {
                final /* synthetic */ LB2OutputPortView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.g();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

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
}
