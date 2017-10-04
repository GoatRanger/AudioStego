package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
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
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class Fps720View extends ItemViewRadio {
    public Fps720View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.g.setText(R.string.setting_ui_hd_fps_60);
            this.h.setText(R.string.setting_ui_hd_fps_50);
        }
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
            boolean z = DataDm368_gGetPushCheckStatus.getInstance().isGetted() && !DataDm368_gGetPushCheckStatus.getInstance().getHDMIExist();
            if (dji.pilot.publics.e.a.g(i.getInstance().c()) || z) {
                setVisibility(0);
                if (DataDm368GetGParams.getInstance().get720PFps() != 1) {
                    this.g.setChecked(true);
                    return;
                } else {
                    this.h.setChecked(true);
                    return;
                }
            }
            setVisibility(8);
        }
    }

    public void onEventMainThread(a aVar) {
        b();
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

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2 = 1;
        int i3 = DataDm368GetGParams.getInstance().get720PFps();
        if (i3 != 0 || i != this.g.getId()) {
            if (i3 != 1 || i != this.h.getId()) {
                if (i == R.id.setting_ui_group_unit_imperial) {
                    i2 = 0;
                    e.a("FPV_ImageTransmissionSettings_FPS_Button_60");
                } else {
                    e.a("FPV_ImageTransmissionSettings_FPS_Button_50");
                }
                new DataDm368SetGParams().a(CmdId.b, i2).start(new d(this) {
                    final /* synthetic */ Fps720View a;

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
}
