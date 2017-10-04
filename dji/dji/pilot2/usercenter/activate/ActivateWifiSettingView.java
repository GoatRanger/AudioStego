package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.airlink.WiFiFrequencyBand;
import dji.common.error.DJIError;
import dji.midware.data.model.P3.DataWifiGetWifiFreqMode;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.t;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.a.e;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.h;

public class ActivateWifiSettingView extends ActivateBaseView {
    private static final int m = 1;
    private static final int n = 2;
    private static final int o = 3;
    private RadioGroup c;
    private RadioButton d;
    private TextView e;
    private RadioButton f;
    private View g;
    private View h;
    private final int i = 0;
    private final int j = 1;
    private int k = 0;
    private b l = b.Unknown;

    private class a extends Handler {
        final /* synthetic */ ActivateWifiSettingView a;

        private a(ActivateWifiSettingView activateWifiSettingView) {
            this.a = activateWifiSettingView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    this.a.a.a();
                    return;
                case 2:
                    this.a.a.b((String) message.obj);
                    return;
                case 3:
                    if (-1 == message.arg1) {
                        this.a.a(b.Country_2DOT4G);
                        return;
                    } else if (t.a(this.a.getContext())) {
                        this.a.a(b.Country_5G_Mobile_5G);
                        return;
                    } else {
                        this.a.a(b.Country_5G_Mobile_2DOT4G);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    private enum b {
        Unknown,
        Mobile_2DOT4G,
        Country_2DOT4G,
        Country_5G_Mobile_5G,
        Country_5G_Mobile_2DOT4G
    }

    public ActivateWifiSettingView(Context context) {
        super(context);
    }

    public ActivateWifiSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateWifiSettingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onShow() {
        a(this.l);
        return true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = new a();
        b();
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            a(this.l);
        }
    }

    private void a(b bVar) {
        dji.pilot.countrycode.a.a.a("updateRadioGroup->SupportType==" + bVar);
        if (bVar.equals(b.Unknown)) {
            this.f.setVisibility(8);
            this.h.setVisibility(8);
            this.d.setVisibility(8);
            this.g.setVisibility(8);
            this.e.setText(R.string.v2_active_wifi_setting_detect_progress);
            if (t.a(getContext())) {
                getWifiBand();
            } else {
                a(b.Mobile_2DOT4G);
            }
        } else if (bVar.equals(b.Mobile_2DOT4G)) {
            this.f.setVisibility(0);
            this.h.setVisibility(0);
            this.f.setChecked(true);
            this.d.setVisibility(8);
            this.g.setVisibility(8);
            this.e.setText(R.string.v2_active_wifi_setting_detect_mobile_2dot4g);
        } else if (bVar.equals(b.Country_2DOT4G)) {
            this.f.setVisibility(0);
            this.h.setVisibility(0);
            this.f.setChecked(true);
            this.d.setVisibility(8);
            this.g.setVisibility(8);
            this.e.setText(R.string.v2_active_wifi_setting_detect_country_2dot4g);
        } else if (bVar.equals(b.Country_5G_Mobile_2DOT4G)) {
            this.f.setVisibility(0);
            this.h.setVisibility(0);
            this.f.setChecked(true);
            this.d.setVisibility(8);
            this.g.setVisibility(8);
            this.e.setText(R.string.v2_active_wifi_setting_detect_mobile_2dot4g);
        } else if (bVar.equals(b.Country_5G_Mobile_5G)) {
            this.f.setVisibility(0);
            this.h.setVisibility(0);
            this.d.setVisibility(0);
            this.g.setVisibility(0);
            this.d.setChecked(true);
            this.e.setText(R.string.v2_active_wifi_setting_detect_result_5g);
        }
        this.l = bVar;
    }

    private void b() {
        this.c = (RadioGroup) findViewById(R.id.c52);
        this.d = (RadioButton) findViewById(R.id.c55);
        this.f = (RadioButton) findViewById(R.id.c53);
        this.h = findViewById(R.id.c54);
        this.g = findViewById(R.id.c56);
        this.e = (TextView) findViewById(R.id.c57);
        this.c.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ActivateWifiSettingView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.c53) {
                    this.a.k = 0;
                } else {
                    this.a.k = 1;
                }
            }
        });
    }

    private void getWifiBand() {
        DataWifiGetWifiFreqMode.getInstance().start(new d(this) {
            final /* synthetic */ ActivateWifiSettingView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                int freqMode = DataWifiGetWifiFreqMode.getInstance().getFreqMode();
                Message obtainMessage = this.a.b.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.arg1 = freqMode;
                this.a.b.sendMessage(obtainMessage);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void setData() {
        Object obj;
        DJISDKCache instance = DJISDKCache.getInstance();
        c i = dji.sdksharedlib.a.b.i(e.m);
        if (this.k == 0) {
            obj = WiFiFrequencyBand.FrequencyBand2Dot4G;
        } else {
            obj = WiFiFrequencyBand.FrequencyBandDual;
        }
        instance.setValue(i, obj, new h(this) {
            final /* synthetic */ ActivateWifiSettingView a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b.sendEmptyMessage(1);
            }

            public void a(DJIError dJIError) {
                Message obtainMessage = this.a.b.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = dJIError.getDescription() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
                this.a.b.sendMessage(obtainMessage);
            }
        });
    }
}
