package dji.setting.ui.wifi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import dji.common.airlink.WiFiFrequencyBand;
import dji.log.WM220LogUtil;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.pilot.fpv.control.t;
import dji.pilot.fpv.control.t.b;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.rc.FreqSnrSdrView;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.util.regex.Pattern;

public class WifiSettingView extends DividerLinearLayout {
    private EditText a = null;
    private EditText b = null;
    private TextView c = null;
    private WifiChannelSelectView d = null;
    private FreqSnrSdrView e = null;
    private View f;
    private WifiChannelIndexView g;
    private WifiFreqModeView h;
    private OnClickListener i = null;
    private t l = null;
    private b m = null;
    private Dialog n = null;

    public WifiSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_wifi);
        if (!isInEditMode()) {
            b();
            this.a = (EditText) findViewById(R.id.fpv_wifi_setting_ssid_et);
            this.b = (EditText) findViewById(R.id.fpv_wifi_setting_pwd_et);
            this.c = (TextView) findViewById(R.id.fpv_wifi_setting_apply_tv);
            this.d = (WifiChannelSelectView) findViewById(R.id.fpv_wifi_channel_select_view);
            this.e = (FreqSnrSdrView) findViewById(R.id.fpv_hd_channel_sdr_freq);
            this.f = findViewById(R.id.wifi_snr_view);
            if (dji.logic.c.b.getInstance().a(null)) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
            this.d.setOnChannelSelectListener(new WifiChannelSelectView.b(this) {
                final /* synthetic */ WifiSettingView a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.e.setWorkFreqIndex(this.a.d.getCurSpinnerPos());
                    this.a.e.postInvalidate();
                }
            });
            this.g = (WifiChannelIndexView) findViewById(R.id.setting_ui_wifi_channel_index_view);
            this.h = (WifiFreqModeView) findViewById(R.id.setting_ui_wifi_freq_mode_view);
            this.h.setOnWifiChannelModeChangedListener(new WifiFreqModeView.a(this) {
                final /* synthetic */ WifiSettingView a;

                {
                    this.a = r1;
                }

                public void a(WiFiFrequencyBand wiFiFrequencyBand) {
                    this.a.g.onShowedChannelModeChanged(wiFiFrequencyBand);
                }
            });
            this.c.setOnClickListener(this.i);
        }
    }

    private void b() {
        this.l = t.getInstance();
        this.l.a();
        this.i = new OnClickListener(this) {
            final /* synthetic */ WifiSettingView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.l.b(this.a.a.getText().toString(), this.a.b.getText().toString())) {
                    this.a.e();
                }
            }
        };
        this.m = new b(this) {
            final /* synthetic */ WifiSettingView a;

            {
                this.a = r1;
            }

            public void c(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
                if (!z) {
                }
            }

            public void a(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
                String c = this.a.l.c();
                this.a.a.setText(c);
                if (!a.a(c)) {
                    this.a.a.setSelection(c.length());
                }
            }

            public void d(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
                if (!z) {
                }
            }

            public void b(boolean z, String str, dji.midware.data.config.P3.a aVar, int i) {
                String d = this.a.l.d();
                this.a.b.setText(d);
                if (!a.a(d)) {
                    this.a.b.setSelection(d.length());
                }
            }

            public void a(boolean z, dji.midware.data.config.P3.a aVar, int i) {
                this.a.a(z, aVar, i);
            }

            public void a(boolean z, dji.midware.data.config.P3.a aVar) {
                if (z) {
                    WM220LogUtil.LOGI("**onWifiChannelGetted success cur: " + this.a.d.getCurSpinnerPos());
                    this.a.d.onWifiChannelGetted();
                    this.a.g.onWifiChannelGetted(this.a.h.getCurSelectShowedMode());
                    this.a.e.setXAxisTextValues(this.a.d.getSpinnerStrings());
                    this.a.e.setWorkFreqIndex(this.a.d.getCurSpinnerPos());
                    this.a.e.postInvalidate();
                    return;
                }
                WM220LogUtil.LOGI("**onWifiChannelGetted" + aVar);
            }

            public void b(boolean z, dji.midware.data.config.P3.a aVar) {
            }
        };
    }

    private void c() {
        this.n = dji.setting.ui.widget.a.b(getContext(), R.string.setting_ui_wifi_setting_wait);
    }

    private void d() {
        if (this.n != null) {
            this.n.dismiss();
        }
    }

    private void e() {
        dji.setting.ui.widget.a.a(getContext(), getResources().getString(R.string.setting_ui_wifi_apply_tip, new Object[]{this.b.getText().toString()}), new DialogInterface.OnClickListener(this) {
            final /* synthetic */ WifiSettingView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.f();
            }
        });
    }

    private void f() {
        String obj = this.a.getText().toString();
        String obj2 = this.b.getText().toString();
        if (a.a(obj)) {
            Toast.makeText(getContext(), R.string.setting_ui_wifi_ssid_empty_tip, 0).show();
        } else if (a.a(obj2)) {
            Toast.makeText(getContext(), R.string.setting_ui_wifi_pwd_empty_tip, 0).show();
        } else if (!checkWifiSsidValid(obj)) {
            Toast.makeText(getContext(), R.string.setting_ui_wifi_ssid_invalid, 0).show();
        } else if (checkWifiPwdValid(obj2)) {
            c();
            this.l.c(obj, obj2);
        } else {
            Toast.makeText(getContext(), R.string.setting_ui_wifi_pwd_invalid, 0).show();
        }
    }

    private void a(boolean z, dji.midware.data.config.P3.a aVar, int i) {
        d();
        if (!z) {
            Toast.makeText(getContext(), R.string.setting_ui_wifi_apply_fail, 0).show();
        }
        this.a.setText(this.l.c());
        this.b.setText(this.l.d());
    }

    public void onEventMainThread(p pVar) {
        if (pVar != p.b) {
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            this.e.clean();
            this.l.g();
            this.l.f();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.l.a(this.m);
            String c = this.l.c();
            this.a.setText(c);
            if (!a.a(c)) {
                this.a.setSelection(c.length());
            }
            c = this.l.d();
            this.b.setText(c);
            if (!a.a(c)) {
                this.b.setSelection(c.length());
            }
            this.l.f();
            this.l.g();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        this.l.h();
        this.l.a(null);
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public static boolean checkWifiSsidValid(String str) {
        String str2 = "[A-Z0-9a-z-_ ]{1,32}";
        return Pattern.compile("[A-Z0-9a-z-_ ]{1,32}").matcher(str).matches();
    }

    public static boolean checkWifiPwdValid(String str) {
        String str2 = "[A-Z0-9a-z]{8,63}";
        return Pattern.compile("[A-Z0-9a-z]{8,63}").matcher(str).matches();
    }
}
