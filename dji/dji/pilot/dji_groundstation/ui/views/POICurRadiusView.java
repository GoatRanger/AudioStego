package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.d;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class POICurRadiusView extends TextView {
    private static final String a = "POICurRadiusView";
    private double b = 0.0d;
    private boolean c = true;
    private Handler d = new Handler(Looper.getMainLooper());
    private Runnable e = new Runnable(this) {
        final /* synthetic */ POICurRadiusView a;

        {
            this.a = r1;
        }

        public void run() {
            if (d.getInstance().b().a(c.b)) {
                this.a.b = f.a(dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().i(), dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().j());
                dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().c(this.a.b);
                if (this.a.b < 5.0d || this.a.b > 500.0d) {
                    this.a.setTextColor(SupportMenu.CATEGORY_MASK);
                } else {
                    this.a.setTextColor(this.a.getContext().getResources().getColor(R.color.setting_ui_btn_hover));
                }
                if (this.a.b < 0.0d) {
                    this.a.setText(R.string.gsnew_fpv_default_str);
                } else if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    this.a.setText(String.format("%.1fFT", new Object[]{Float.valueOf(f.a((float) this.a.b))}));
                } else {
                    this.a.setText(String.format("%.1fM", new Object[]{Double.valueOf(this.a.b)}));
                }
            }
            if (this.a.c) {
                this.a.d.postDelayed(this.a.e, 100);
            }
        }
    };

    public POICurRadiusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.d.post(this.e);
    }

    public void setIsAutoUpdate(boolean z) {
        this.c = z;
    }

    public double getCurRadius() {
        return this.b;
    }
}
