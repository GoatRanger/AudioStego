package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import com.google.a.b.a.k;
import dji.apppublic.reflect.AppPublicReflect;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.usb.P3.a;
import dji.midware.usb.P3.a.b;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;
import java.util.Arrays;

public class LB2AppOutputView extends ItemViewSpinner {
    protected static int a = 0;
    protected static int b = 1;
    private static int i = 0;
    private static int l = 1;
    protected a c;
    private String[] g = new String[]{k.b, "EXT"};
    private String[] h = new String[]{"HDMI", "AV"};

    public LB2AppOutputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a = Arrays.asList(this.g).indexOf(k.b);
            b = Arrays.asList(this.g).indexOf("EXT");
            i = Arrays.asList(this.h).indexOf("HDMI");
            l = Arrays.asList(this.h).indexOf("AV");
            this.c = a.getInstance();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        dji.publics.a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2AppOutputView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }, 700);
    }

    public void onEventMainThread(a.a aVar) {
        a();
    }

    public void onEventMainThread(a.c cVar) {
        a();
    }

    public void onEventMainThread(b bVar) {
        a();
    }

    protected void a() {
        if (b()) {
            setVisibility(0);
            if (this.c.d() == b.b) {
                c();
                return;
            } else {
                d();
                return;
            }
        }
        setVisibility(8);
    }

    protected boolean b() {
        return a.a() && !e();
    }

    private void c() {
        this.f.setData(this.g, 0, (DJISpinnerButton.b) this);
        if (this.c.b() == a.c.a) {
            this.f.setIndex(a);
        } else {
            this.f.setIndex(b);
        }
    }

    private void d() {
        this.f.setData(this.h, 0, (DJISpinnerButton.b) this);
        if (this.c.c() == a.a.a) {
            this.f.setIndex(i);
        } else {
            this.f.setIndex(l);
        }
    }

    public void onItemClick(int i) {
        if (this.c.d() == b.b) {
            c(i);
        } else {
            d(i);
        }
    }

    private void c(int i) {
        int e = a.getInstance().e();
        DJILogHelper.getInstance().LOGD("", "click single 4 curBandWidth=" + e, false, true);
        if (e == 0) {
            this.f.setIndex(b);
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_bandwidth_only);
        } else if (e == 10) {
            this.f.setIndex(a);
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_bandwidth_only);
        } else {
            a(i);
        }
    }

    protected void a(int i) {
        if (i == a) {
            this.c.a(a.c.a);
            DJILogHelper.getInstance().LOGD("", "set lb app", false, true);
        } else if (i == b) {
            this.c.a(a.c.b);
            DJILogHelper.getInstance().LOGD("", "set ext app", false, true);
        }
    }

    private void d(int i) {
        int f = a.getInstance().f();
        DJILogHelper.getInstance().LOGD("", "click dual curBandWidth=" + f, false, true);
        if (f == 0 && a.a()) {
            this.f.setIndex(l);
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_bandwidth_only);
        } else if (f == 10 && a.a()) {
            this.f.setIndex(i);
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_hd_bandwidth_only);
        } else if (i == i) {
            this.c.a(a.a.a);
            DJILogHelper.getInstance().LOGD("", "set hdmi app", false, true);
        } else if (i == l) {
            this.c.a(a.a.b);
            DJILogHelper.getInstance().LOGD("", "set av app", false, true);
        }
    }

    private boolean e() {
        boolean z = true;
        String dM368Version = AppPublicReflect.getDM368Version();
        if (dM368Version == null) {
            return false;
        }
        String[] split = dM368Version.split("\\.");
        DJILogHelper.getInstance().LOGD("", "!!!!!!!!!!!!HDVIEW DM368G Version:" + dM368Version, true, true);
        if (split.length == 4) {
            int parseInt = (((((Integer.parseInt(split[0]) * 100) * 100) * 100) + ((Integer.parseInt(split[1]) * 100) * 100)) + (Integer.parseInt(split[2]) * 100)) + Integer.parseInt(split[3]);
            DJILogHelper.getInstance().LOGD("", "Dm368G version:" + parseInt, true, true);
            if (parseInt > 1000014) {
                z = false;
            }
            return z;
        }
        DJILogHelper.getInstance().LOGD("", "Dm368G version error:length " + split.length, true, true);
        return false;
    }
}
