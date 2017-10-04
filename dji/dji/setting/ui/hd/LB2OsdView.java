package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DJINumberProgress;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class LB2OsdView extends DividerLinearLayout implements OnCheckedChangeListener, OnSeekBarChangeListener {
    private DJINumberProgress a;
    private DJINumberProgress b;
    private DJINumberProgress c;
    private DJINumberProgress d;
    private Switch e;
    private View f;

    public LB2OsdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_hd_lb2_osd);
        if (!isInEditMode()) {
            this.f = findViewById(R.id.setting_ui_hd_osd_ly);
            this.a = (DJINumberProgress) findViewById(R.id.setting_ui_hd_osd_left);
            this.b = (DJINumberProgress) findViewById(R.id.setting_ui_hd_osd_top);
            this.c = (DJINumberProgress) findViewById(R.id.setting_ui_hd_osd_right);
            this.d = (DJINumberProgress) findViewById(R.id.setting_ui_hd_osd_bottom);
            this.e = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.a.initParams(49, "1", "49", 1, (OnSeekBarChangeListener) this);
            this.b.initParams(49, "1", "49", 1, (OnSeekBarChangeListener) this);
            this.c.initParams(49, "1", "49", 1, (OnSeekBarChangeListener) this);
            this.d.initParams(49, "1", "49", 1, (OnSeekBarChangeListener) this);
            this.e.setOnCheckedChangeListener(this);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        b();
        a.f();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        dji.publics.a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2OsdView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        }, 700);
    }

    public void onEventMainThread(a aVar) {
        b();
    }

    private void b() {
        if (a.a()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        boolean isShowOsd = DataDm368GetGParams.getInstance().getIsShowOsd();
        if (isShowOsd) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        this.e.setChecked(isShowOsd);
        a(this.a, DataDm368GetGParams.getInstance().getOsdMarginLeft());
        a(this.b, DataDm368GetGParams.getInstance().getOsdMarginTop());
        a(this.c, DataDm368GetGParams.getInstance().getOsdMarginRight());
        a(this.d, DataDm368GetGParams.getInstance().getOsdMarginBottom());
    }

    private void a(DJINumberProgress dJINumberProgress, int i) {
        if (i >= 1 && i <= 50) {
            dJINumberProgress.setProgress(i - 1);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = 0;
        if (DataDm368GetGParams.getInstance().getIsShowOsd() != z) {
            if (z) {
                e.a("FPV_ImageTransmissionSettings_Switcher_DisplayOSDOnHDMIOutput_ON");
            } else {
                e.a("FPV_ImageTransmissionSettings_Switcher_DisplayOSDOnHDMIOutput_OFF");
            }
            if (z) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
            CmdId cmdId = CmdId.a;
            if (z) {
                i = 1;
            }
            dataDm368SetGParams.a(cmdId, i).start(new d(this) {
                final /* synthetic */ LB2OsdView a;

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

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        CmdId cmdId = null;
        int progress = seekBar.getProgress() + 1;
        if (progress >= 1 && progress <= 50) {
            if (seekBar == this.a.getSeekbaBar()) {
                cmdId = CmdId.c;
            } else if (seekBar == this.b.getSeekbaBar()) {
                cmdId = CmdId.e;
            } else if (seekBar == this.c.getSeekbaBar()) {
                cmdId = CmdId.d;
            } else if (seekBar == this.d.getSeekbaBar()) {
                cmdId = CmdId.f;
            }
            new DataDm368SetGParams().a(cmdId, progress).start(new d(this) {
                final /* synthetic */ LB2OsdView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.b();
                }
            });
        }
    }
}
