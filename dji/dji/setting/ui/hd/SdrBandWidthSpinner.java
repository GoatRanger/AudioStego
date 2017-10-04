package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBSDRBandwidth;
import dji.midware.data.config.P3.ProductType;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.ItemViewRadio;

public class SdrBandWidthSpinner extends ItemViewRadio implements d {
    private a a;
    private final String[] b = new String[]{"20MHz", "10MHz"};
    private final LBSDRBandwidth[] c = new LBSDRBandwidth[]{LBSDRBandwidth.Bandwidth20MHz, LBSDRBandwidth.Bandwidth10MHz};
    private int i = 0;
    private c l;
    private c m;

    public interface a {
        void a();

        void a(int i);
    }

    public SdrBandWidthSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.g.setText(this.b[1]);
            this.h.setText(this.b[0]);
            this.l = b.h(dji.sdksharedlib.b.a.c.H);
            this.m = b.h("ChannelSelectionMode");
            a();
        }
    }

    private void a() {
        if (a.d()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        c();
        b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            DJISDKCache.getInstance().startListeningForUpdates(this.l, this, true);
            DJISDKCache.getInstance().startListeningForUpdates(this.m, this, true);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.g.getId()) {
            DJISDKCache.getInstance().setValue(this.l, this.c[1], null);
        } else {
            DJISDKCache.getInstance().setValue(this.l, this.c[0], null);
        }
    }

    public void onBandWidthGetted(int i) {
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.f().equals(dji.sdksharedlib.b.a.c.H)) {
            c();
        } else if (cVar.equals(this.m)) {
            b();
        }
    }

    private void b() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.m);
        if (availableValue != null) {
            if (availableValue.e() == LBAirLinkChannelSelectionMode.Auto) {
                setVisibility(8);
            } else if (a.d()) {
                setVisibility(0);
            }
        }
    }

    private void c() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.l);
        if (availableValue != null) {
            if (availableValue.e() == LBSDRBandwidth.Bandwidth20MHz) {
                this.h.setChecked(true);
            } else {
                this.g.setChecked(true);
            }
        }
    }

    public void setOnBandChangedListener(a aVar) {
        this.a = aVar;
    }
}
