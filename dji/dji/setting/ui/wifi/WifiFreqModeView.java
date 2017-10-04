package dji.setting.ui.wifi;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import dji.common.airlink.WiFiFrequencyBand;
import dji.common.error.DJIError;
import dji.logic.c.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.fpv.control.t;
import dji.pilot.publics.objects.g;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.a.e;
import dji.sdksharedlib.c.h;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class WifiFreqModeView extends DividerLinearLayout implements OnClickListener {
    private a a;
    private RadioButton b;
    private RadioButton c;
    private RadioButton d;
    private RadioButton e;
    private RadioButton f;
    private RadioGroup g;
    private TextView h;
    private WiFiFrequencyBand i = WiFiFrequencyBand.FrequencyBand2Dot4G;

    public interface a {
        void a(WiFiFrequencyBand wiFiFrequencyBand);
    }

    public void setOnWifiChannelModeChangedListener(a aVar) {
        this.a = aVar;
    }

    public WifiFreqModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_wifi_support_channel_mode);
        if (!isInEditMode()) {
            this.b = (RadioButton) findViewById(R.id.setting_ui_group_24g);
            this.c = (RadioButton) findViewById(R.id.setting_ui_group_5g);
            this.d = (RadioButton) findViewById(R.id.setting_ui_group_dual);
            this.g = (RadioGroup) findViewById(R.id.setting_ui_wifi_cur_mode_radiogroup);
            this.e = (RadioButton) findViewById(R.id.setting_ui_group_cur_24g);
            this.f = (RadioButton) findViewById(R.id.setting_ui_group_cur_5g);
            this.h = (TextView) findViewById(R.id.setting_ui_wifi_cur_mode);
            this.b.setOnClickListener(this);
            this.c.setOnClickListener(this);
            this.d.setOnClickListener(this);
            this.e.setOnClickListener(this);
            this.f.setOnClickListener(this);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            b();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    private void b() {
        if (b.getInstance().a(i.getInstance().c())) {
            setVisibility(0);
            DJISDKCache.getInstance().getValue(dji.sdksharedlib.a.b.i(e.m), new dji.sdksharedlib.c.c(this) {
                final /* synthetic */ WifiFreqModeView a;

                {
                    this.a = r1;
                }

                public void a(dji.sdksharedlib.d.a aVar) {
                    if (aVar != null && aVar.e() != null) {
                        final WiFiFrequencyBand wiFiFrequencyBand = (WiFiFrequencyBand) aVar.e();
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.a.a(wiFiFrequencyBand);
                            }
                        });
                    }
                }

                public void a(DJIError dJIError) {
                }
            });
            return;
        }
        setVisibility(8);
    }

    private void a(WiFiFrequencyBand wiFiFrequencyBand) {
        this.i = wiFiFrequencyBand;
        if (wiFiFrequencyBand == WiFiFrequencyBand.FrequencyBand2Dot4G) {
            this.b.setChecked(true);
            this.h.setVisibility(0);
            this.h.setText("2.4G");
            this.g.setVisibility(8);
        } else if (wiFiFrequencyBand == WiFiFrequencyBand.FrequencyBand5G) {
            this.c.setChecked(true);
            this.h.setVisibility(0);
            this.h.setText("5G");
            this.g.setVisibility(8);
        } else if (wiFiFrequencyBand == WiFiFrequencyBand.FrequencyBandDual) {
            this.d.setChecked(true);
            this.h.setVisibility(8);
            this.g.setVisibility(0);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.setting_ui_group_24g) {
            b(WiFiFrequencyBand.FrequencyBand2Dot4G);
        } else if (id == R.id.setting_ui_group_5g) {
            b(WiFiFrequencyBand.FrequencyBand5G);
        } else if (id == R.id.setting_ui_group_dual) {
            b(WiFiFrequencyBand.FrequencyBandDual);
        } else if (id == R.id.setting_ui_group_cur_24g) {
            if (this.a != null) {
                this.a.a(WiFiFrequencyBand.FrequencyBand2Dot4G);
            }
        } else if (id == R.id.setting_ui_group_cur_5g && this.a != null) {
            this.a.a(WiFiFrequencyBand.FrequencyBand5G);
        }
    }

    private void b(final WiFiFrequencyBand wiFiFrequencyBand) {
        boolean b = g.b(getContext(), t.a, false);
        if ((wiFiFrequencyBand == WiFiFrequencyBand.FrequencyBand5G || wiFiFrequencyBand == WiFiFrequencyBand.FrequencyBandDual) && !b) {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_wifi_not_support_5g_tip, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ WifiFreqModeView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a(this.a.i);
                    dialogInterface.dismiss();
                }
            });
        } else {
            dji.setting.ui.widget.a.b(getContext(), R.string.setting_ui_wifi_setting_changed_restart_tip, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ WifiFreqModeView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.b.setFreqMode(wiFiFrequencyBand);
                }
            }, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ WifiFreqModeView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a(this.a.i);
                    dialogInterface.dismiss();
                }
            });
        }
    }

    private void setFreqMode(final WiFiFrequencyBand wiFiFrequencyBand) {
        DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.i(e.m), wiFiFrequencyBand, new h(this) {
            final /* synthetic */ WifiFreqModeView b;

            public void a() {
                this.b.a(wiFiFrequencyBand);
            }

            public void a(DJIError dJIError) {
                this.b.a(this.b.i);
            }
        });
    }

    public WiFiFrequencyBand getCurSelectShowedMode() {
        if (this.f.isChecked()) {
            return WiFiFrequencyBand.FrequencyBand5G;
        }
        return WiFiFrequencyBand.FrequencyBand2Dot4G;
    }
}
