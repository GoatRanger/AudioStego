package dji.setting.ui.wifi;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;
import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataWifiGetChannelList;
import dji.midware.data.model.P3.DataWifiSetModeChannel;
import dji.midware.e.d;
import dji.pilot.fpv.control.t;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewSpinner;

public class WifiChannelSelectView extends ItemViewSpinner {
    private b a;
    private String[] b;
    private a[] c;
    private int g;
    private int h = 0;

    private static final class a {
        public String a;
        public int b;

        private a() {
        }
    }

    public interface b {
        void a(int i);
    }

    public WifiChannelSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    private void a() {
        if (a.a()) {
            setVisibility(8);
        } else {
            setVisibility(8);
        }
        setEnabled(false);
    }

    public void onItemClick(final int i) {
        int i2 = 1;
        if (DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
            this.f.setIndex(this.h);
            Toast.makeText(getContext(), R.string.setting_ui_wifi_flying_channel_select_tip, 1).show();
            return;
        }
        final int i3 = this.c[i].b;
        if (this.c[i].a.equals("2.4G")) {
            i2 = 0;
        }
        DataWifiSetModeChannel.getInstance().a(i2).b(i3).start(new d(this) {
            final /* synthetic */ WifiChannelSelectView c;

            public void onSuccess(Object obj) {
                this.c.g = i3;
                this.c.h = i;
                if (this.c.a != null) {
                    this.c.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.a.a(this.a.c.h);
                        }
                    });
                }
                WM220LogUtil.LOGI("DataWifiSetModeChannel set channel success");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.c.f.setIndex(this.a.c.h);
                    }
                });
                WM220LogUtil.LOGI("DataWifiSetModeChannel set channel fail!");
            }
        });
    }

    public void onWifiChannelGetted() {
        int[] iArr = DataWifiGetChannelList.getInstance().get24GChannelList();
        int[] iArr2 = DataWifiGetChannelList.getInstance().get5GChannelList();
        if (iArr.length == 0 && iArr2.length == 0) {
            WM220LogUtil.LOGI("DataWifiGetChannelList get channel list length = 0");
            return;
        }
        int length;
        boolean a = t.a(getContext());
        this.g = DataWifiGetChannelList.getInstance().getCurChannel();
        if (a) {
            length = iArr.length + iArr2.length;
        } else {
            length = iArr.length;
        }
        this.c = new a[length];
        this.b = new String[length];
        this.h = 0;
        String str = "2.4G";
        if (iArr.length > 0 && iArr[0] > 6) {
            str = "5G";
        }
        int i = 0;
        int i2 = 0;
        while (i != iArr.length) {
            this.c[i2] = new a();
            this.c[i2].a = str;
            this.c[i2].b = iArr[i];
            if (this.g == iArr[i]) {
                this.h = i2;
            }
            this.b[i2] = this.c[i2].b + "(" + this.c[i2].a + ")";
            i++;
            i2++;
        }
        if (a) {
            length = 0;
            while (length != iArr2.length) {
                this.c[i2] = new a();
                this.c[i2].a = "5G";
                this.c[i2].b = iArr2[length];
                if (this.g == iArr2[length]) {
                    this.h = i2;
                }
                this.b[i2] = this.c[i2].b + "(" + this.c[i2].a + ")";
                length++;
                i2++;
            }
        }
        setEnabled(true);
        this.f.setData(this.b, 0, (dji.setting.ui.widget.DJISpinnerButton.b) this);
        this.f.setIndex(this.h);
    }

    public int getCurSpinnerPos() {
        return this.h;
    }

    public String[] getSpinnerStrings() {
        return this.b;
    }

    public void setOnChannelSelectListener(b bVar) {
        this.a = bVar;
    }
}
