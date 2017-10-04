package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetSdrConfig;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.pilot.c.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class BandHdView extends ItemViewRadio {
    public BandHdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g.setText(R.string.setting_ui_hd_sdr_full_band);
        this.h.setText("2.4GHz");
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        a();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            if (a.d()) {
                setVisibility(8);
            } else {
                setVisibility(8);
            }
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onBandTypeGetted() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ BandHdView a;

            {
                this.a = r1;
            }

            public void run() {
                if (DataOsdGetSdrConfig.getInstance().getBandwidthType() == 1) {
                    d.k = 1;
                    this.a.h.setChecked(true);
                    return;
                }
                d.k = 0;
                this.a.h.setChecked(true);
            }
        });
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2;
        if (i == this.h.getId()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        DataOsdSetConfig.getInstance().c(0).a(0, i2).start(new dji.midware.e.d(this) {
            final /* synthetic */ BandHdView b;

            public void onSuccess(Object obj) {
                d.k = i2;
                DJILogHelper.getInstance().LOGD("", "Sdr set bandwidth susccess , freqType =" + i2, false, true);
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        Toast.makeText(this.a.b.getContext(), "Sdr set bandwidth susccess, freqType =" + i2, 1000).show();
                    }
                });
            }

            public void onFailure(final a aVar) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        Toast.makeText(this.b.b.getContext(), "Sdr set bandwidth fail , freqType =" + aVar.toString(), 1000).show();
                    }
                });
                DJILogHelper.getInstance().LOGD("", "Sdr set bandwidth fail , freqType =" + i2, false, true);
            }
        });
    }
}
