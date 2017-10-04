package dji.setting.ui.vision;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class VisionDownVPSView extends DividerLinearLayout implements OnCheckedChangeListener {
    protected Switch a = null;
    private ParamInfo b = null;

    public VisionDownVPSView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_vision_downsensor_enable);
        if (!isInEditMode()) {
            this.b = d.read("g_config.mvo_cfg.mvo_func_en_0");
            this.a = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.a.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        boolean z2 = true;
        if (this.b.value.intValue() != 1) {
            z2 = false;
        }
        if (z2 != z) {
            if (z) {
                setChecked(z);
            } else {
                dji.setting.ui.widget.a.c(getContext(), R.string.setting_ui_flyc_vision_desc, new OnClickListener(this) {
                    final /* synthetic */ VisionDownVPSView b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.setChecked(z);
                        dialogInterface.dismiss();
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ VisionDownVPSView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.b();
                        dialogInterface.dismiss();
                    }
                });
            }
        }
    }

    private void setChecked(boolean z) {
        int i;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        String str = "g_config.mvo_cfg.mvo_func_en_0";
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        dataFlycSetParams.a(str, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
            final /* synthetic */ VisionDownVPSView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                b.getInstance().a("g_config.mvo_cfg.mvo_func_en_0");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

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

    private void b() {
        boolean z = true;
        Switch switchR = this.a;
        if (this.b.value.intValue() != 1) {
            z = false;
        }
        switchR.setChecked(z);
    }

    public void onEventMainThread(a.a aVar) {
        if ("g_config.mvo_cfg.mvo_func_en_0".equals(aVar.a)) {
            b();
        }
    }

    public void onEventMainThread(o oVar) {
        if (o.b == oVar) {
            onEventMainThread(i.getInstance().c());
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (dji.pilot.publics.e.a.k(productType)) {
            setVisibility(0);
            b.getInstance().a("g_config.mvo_cfg.mvo_func_en_0");
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
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
