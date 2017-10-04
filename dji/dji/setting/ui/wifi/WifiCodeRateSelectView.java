package dji.setting.ui.wifi;

import android.content.Context;
import android.util.AttributeSet;
import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataWifiGetWifiCurCodeRate;
import dji.midware.data.model.P3.DataWifiSetWifiCodeRate;
import dji.midware.e.d;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;

public class WifiCodeRateSelectView extends ItemViewSpinner {
    private int[] a = new int[]{1, 2, 4};
    private String[] b = new String[]{"1Mbps", "2Mbps", "4Mbps"};

    public WifiCodeRateSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        if (a.a()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        this.f.setData(this.b, 0, (b) this);
        setEnabled(false);
        DataWifiGetWifiCurCodeRate.getInstance().start(new d(this) {
            final /* synthetic */ WifiCodeRateSelectView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.onWifiCodeRateGetted();
                    }
                });
            }

            public void onFailure(a aVar) {
                WM220LogUtil.LOGI("**onWifiCodeRateGetted fail" + aVar);
            }
        });
    }

    public void onItemClick(int i) {
        DataWifiSetWifiCodeRate.getInstance().a(this.a[i]).start(new d(this) {
            final /* synthetic */ WifiCodeRateSelectView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                WM220LogUtil.LOGI("**set code rate onSuccess");
            }

            public void onFailure(a aVar) {
                WM220LogUtil.LOGI("**set code rate onFailure");
            }
        });
    }

    public void onWifiCodeRateGetted() {
        int curCodeRate = DataWifiGetWifiCurCodeRate.getInstance().getCurCodeRate();
        WM220LogUtil.LOGI("**DataWifiGetWifiCurCodeRate curRate = " + curCodeRate);
        setEnabled(true);
        for (int i = 0; i != this.a.length; i++) {
            if (curCodeRate == this.a[i]) {
                this.f.setIndex(i);
                return;
            }
        }
        this.f.setIndex(0);
    }
}
