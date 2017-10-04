package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataDm368SetParams;
import dji.midware.data.model.P3.DataDm368SetParams.DM368CmdId;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.e.d;
import dji.midware.usb.P3.a;
import dji.midware.usb.P3.a.b;
import dji.midware.usb.P3.a.c;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DividerLinearLayout;

public class LB2BandWidthView extends DividerLinearLayout {
    public static final int a = 1;
    private static final int f = 0;
    private TextView b;
    private SeekBar c;
    private String d;
    private String e;
    private a g;

    public LB2BandWidthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_hd_lb2_bandwidth);
        if (!isInEditMode()) {
            this.g = a.getInstance();
            this.d = getResources().getString(R.string.setting_ui_hd_bandwidth_lb);
            this.e = getResources().getString(R.string.setting_ui_hd_bandwidth_ext);
            this.c = (SeekBar) findViewById(R.id.setting_ui_hd_seekbar);
            this.b = (TextView) findViewById(R.id.setting_ui_hd_txt);
            this.c.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ LB2BandWidthView a;

                {
                    this.a = r1;
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (this.a.g.d() == b.b) {
                        this.a.setSingleBandWidth(seekBar.getProgress());
                    } else {
                        this.a.setDualBandWidth(seekBar.getProgress());
                    }
                    e.a("FPV_ImageTransmissionSettings_Slider_BandWidthProportion");
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    this.a.b.setText((this.a.d + (i * 10) + "%") + this.a.e + ((10 - i) * 10) + "%");
                }
            });
            b();
        }
    }

    private void setSingleBandWidth(final int i) {
        if (i <= 10 && i >= 0) {
            int i2;
            if (i == 0) {
                i2 = 0;
            } else if (i == 10) {
                i2 = 1;
            } else {
                i2 = 2;
            }
            DataOsdSetConfig.getInstance().g(i2).start(new d(this) {
                final /* synthetic */ LB2BandWidthView b;

                public void onSuccess(Object obj) {
                    this.b.setOsdBandWidth(i);
                    DJILogHelper.getInstance().LOGD("", "set video source success ", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.b.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.b();
                        }
                    });
                    DJILogHelper.getInstance().LOGD("", "set video source failure", false, true);
                }
            });
        }
    }

    private void setOsdBandWidth(final int i) {
        DataOsdSetConfig.getInstance().f(i).start(new d(this) {
            final /* synthetic */ LB2BandWidthView b;

            public void onSuccess(Object obj) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (i == 0) {
                            a.getInstance().a(c.b);
                            dji.setting.ui.widget.a.a(this.a.b.getContext(), R.string.setting_ui_hd_bandwidth_ext_only);
                        } else if (i == 10) {
                            a.getInstance().a(c.a);
                            dji.setting.ui.widget.a.a(this.a.b.getContext(), R.string.setting_ui_hd_bandwidth_lb_only);
                        }
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.b();
                    }
                });
                DJILogHelper.getInstance().LOGD("", "bandwidth set fail: " + aVar.name(), false, true);
            }
        });
    }

    private void setDualBandWidth(final int i) {
        d anonymousClass4 = new d(this) {
            final /* synthetic */ LB2BandWidthView b;

            public void onSuccess(Object obj) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (i == 0) {
                            a.getInstance().a(a.a.b);
                            dji.setting.ui.widget.a.a(this.a.b.getContext(), R.string.setting_ui_hd_bandwidth_av_only);
                        } else if (i == 10) {
                            a.getInstance().a(a.a.a);
                            dji.setting.ui.widget.a.a(this.a.b.getContext(), R.string.setting_ui_hd_bandwidth_hdmi_only);
                        }
                    }
                });
                this.b.g.a();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.b();
                    }
                });
                DJILogHelper.getInstance().LOGD("", "bandwidth set fail: " + aVar.name(), false, true);
            }
        };
        if (ProductType.Orange2 == i.getInstance().c()) {
            new DataOsdSetConfig().f(i).start(anonymousClass4);
        } else {
            new DataDm368SetParams().a(DM368CmdId.e, i).start(anonymousClass4);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        dji.thirdparty.a.c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        dji.publics.a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2BandWidthView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b();
            }
        }, 700);
    }

    private void b() {
        if (a.c() && DataRcGetMaster.getInstance().getMode() == MODE.a) {
            setVisibility(0);
            int e;
            if (a.getInstance().d() == b.b) {
                this.d = getResources().getString(R.string.setting_ui_hd_bandwidth_lb);
                this.e = getResources().getString(R.string.setting_ui_hd_bandwidth_ext);
                e = a.getInstance().e();
                if (e <= 10 && e >= 0) {
                    this.c.setProgress(e);
                    this.b.setText((this.d + (e * 10) + "% ") + this.e + ((10 - e) * 10) + "%");
                    return;
                }
                return;
            }
            this.d = getResources().getString(R.string.setting_ui_hd_bandwidth_hdmi);
            this.e = getResources().getString(R.string.setting_ui_hd_bandwidth_av);
            e = a.getInstance().f();
            if (e <= 10 && e >= 0) {
                this.c.setProgress(e);
                this.b.setText((this.d + (e * 10) + "% ") + this.e + ((10 - e) * 10) + "%");
                return;
            }
            return;
        }
        setVisibility(8);
    }

    public void onEventMainThread(DataRcGetPushParams dataRcGetPushParams) {
        a.a("onEventMainThread(DataRcGetPushParams");
        b();
    }

    public void onEventMainThread(DataOsdGetPushConfig dataOsdGetPushConfig) {
        a.a("onEventMainThread(DataOsdGetPushConfig ");
        b();
    }

    public void onEventMainThread(b bVar) {
        b();
    }
}
