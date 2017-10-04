package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class CurHeightView extends TextView {
    private static final String a = "CurHeightView";
    private double b = 0.0d;
    private boolean c = true;
    private Handler d = new Handler(Looper.getMainLooper());
    private Runnable e = new Runnable(this) {
        final /* synthetic */ CurHeightView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b = 0.1d * ((double) DataOsdGetPushCommon.getInstance().getHeight());
            if (this.a.b > 5.0d) {
                this.a.setTextColor(this.a.getContext().getResources().getColor(R.color.setting_ui_btn_hover));
            } else {
                this.a.setTextColor(SupportMenu.CATEGORY_MASK);
            }
            if (this.a.b <= 0.0d) {
                this.a.setText(R.string.gsnew_fpv_default_str);
            } else if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(f.a((float) this.a.b))}));
            } else {
                this.a.setText(String.format("%.1fM", new Object[]{Double.valueOf(this.a.b)}));
            }
            if (this.a.c) {
                this.a.d.postDelayed(this.a.e, 100);
            }
        }
    };

    public CurHeightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CurHeightView(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.d.post(this.e);
    }

    public double getFlightHeight() {
        return this.b;
    }

    public void setIsAutoUpdate(boolean z) {
        this.c = z;
    }
}
