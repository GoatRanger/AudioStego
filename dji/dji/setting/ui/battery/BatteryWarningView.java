package dji.setting.ui.battery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.common.battery.DJIBatteryOverview;
import dji.common.battery.DJIBatteryStatus;
import dji.common.error.DJIError;
import dji.common.product.Model;
import dji.log.DJILog;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;
import dji.setting.ui.widget.DJISeekbarRightValue;
import dji.setting.ui.widget.DividerLinearLayout;

public class BatteryWarningView extends DividerLinearLayout implements d {
    private static String a = "BatteryWarningView";
    private DJISeekbarRightValue b;
    private DJISeekbarRightValue c;
    private long d = 0;
    private OnSeekBarChangeListener e = new OnSeekBarChangeListener(this) {
        final /* synthetic */ BatteryWarningView a;

        {
            this.a = r1;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int a = a.a(a.e(e.z));
            int a2 = a.a(a.e(e.A));
            int progress = this.a.b.getProgress() + 15;
            DJILog.d(BatteryWarningView.a, "listener go home percent : " + a + ", curPercent: " + progress);
            if (a != progress) {
                this.a.d = System.currentTimeMillis();
                if (a.a(a.e(e.A)) > progress) {
                    a.c(e.A, Integer.valueOf(progress - 5), null);
                    this.a.c.initParams((progress - 10) - 5, 10, this.a.f);
                    this.a.c.setProgress((progress - 10) - 5);
                } else {
                    this.a.c.initParams(progress - 10, 10, this.a.f);
                    DJILog.d(BatteryWarningView.a, "seekbar value : " + (a2 - 10) + ", max cur: " + (progress - 10));
                    this.a.c.setProgress(a2 - 10);
                }
                a.c(e.z, Integer.valueOf(progress), new h(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a.d = 0;
                        DJILog.d(BatteryWarningView.a, "go home set success");
                    }

                    public void a(DJIError dJIError) {
                        DJILog.d(BatteryWarningView.a, "go home error : " + dJIError);
                        this.a.a.d = 0;
                        this.a.a.d();
                    }
                });
            }
        }
    };
    private OnSeekBarChangeListener f = new OnSeekBarChangeListener(this) {
        final /* synthetic */ BatteryWarningView a;

        {
            this.a = r1;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            int a = a.a(a.e(e.A));
            int progress = this.a.c.getProgress() + 10;
            DJILog.d(BatteryWarningView.a, "listener landing percent : " + a + ", curPercent: " + progress);
            if (a != progress) {
                a.c(e.A, Integer.valueOf(progress), new h(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                    }

                    public void a(DJIError dJIError) {
                        DJILog.d(BatteryWarningView.a, "landing error : " + dJIError);
                        this.a.a.d();
                    }
                });
            }
        }
    };

    public BatteryWarningView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    private void b() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_battery_warning);
        if (!isInEditMode()) {
            this.b = (DJISeekbarRightValue) findViewById(R.id.setting_ui_batter_low_seekbar);
            this.c = (DJISeekbarRightValue) findViewById(R.id.setting_ui_batter_seriour_seekbar);
        }
    }

    private int c() {
        DJIBatteryOverview[] dJIBatteryOverviewArr = (DJIBatteryOverview[]) a.a(dji.sdksharedlib.b.a.s, true);
        int length = dJIBatteryOverviewArr.length;
        if (dJIBatteryOverviewArr != null) {
            for (DJIBatteryOverview isConnected : dJIBatteryOverviewArr) {
                if (!isConnected.isConnected()) {
                    length--;
                }
            }
        }
        return length;
    }

    private void d() {
        int a = a.a(a.e(e.z));
        int a2 = a.a(a.e(e.A));
        DJIBatteryStatus dJIBatteryStatus = (DJIBatteryStatus) a.d(dji.sdksharedlib.b.a.m);
        DJILog.d(a, "goHome : " + a);
        DJILog.d(a, "landing : " + a2);
        Model model = (Model) a.a(dji.sdksharedlib.b.h.c);
        if (!(model == Model.Phantom_3_Advanced || model == Model.Phantom_3_4K || model == Model.Phantom_3_Professional || model == Model.Phantom_3_Standard)) {
            dJIBatteryStatus = DJIBatteryStatus.NORMAL;
        }
        if (dJIBatteryStatus == null || dJIBatteryStatus == DJIBatteryStatus.EXCEPTION) {
            this.b.setProgress(0);
            this.b.getSeekbaBar().setEnabled(false);
            this.c.setProgress(0);
            this.c.getSeekbaBar().setEnabled(false);
            return;
        }
        this.b.initParams(35, 15, this.e);
        this.b.setProgress(a - 15);
        this.b.getSeekbaBar().setEnabled(true);
        this.c.initParams(a - 10, 10, this.f);
        this.c.setProgress(a2 - 10);
        this.c.getSeekbaBar().setEnabled(true);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a.a(this, dji.sdksharedlib.b.h.c);
            a.g(this, new String[]{e.A, e.z});
            a.d(this, new String[]{dji.sdksharedlib.b.a.m});
            a.e(this, new String[]{dji.sdksharedlib.b.a.s});
            d();
        }
    }

    protected void onDetachedFromWindow() {
        a.a(this);
        super.onDetachedFromWindow();
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        DJILog.d(a, "key change : " + cVar);
        if (System.currentTimeMillis() - this.d > 500) {
            d();
        }
    }
}
