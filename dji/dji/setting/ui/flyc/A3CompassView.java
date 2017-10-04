package dji.setting.ui.flyc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushParamsByHash;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.SettingUIRootView;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class A3CompassView extends DividerLinearLayout {
    private static final String a = "A3CompassView";
    private int[] b = new int[]{R.string.setting_ui_redundancy_sensor_compass_stat_0, R.string.setting_ui_redundancy_sensor_compass_stat_1, R.string.setting_ui_redundancy_sensor_compass_stat_2, R.string.setting_ui_redundancy_sensor_compass_stat_3, R.string.setting_ui_redundancy_sensor_compass_stat_4, R.string.setting_ui_redundancy_sensor_compass_stat_empty, R.string.setting_ui_redundancy_sensor_compass_stat_empty, R.string.setting_ui_redundancy_sensor_compass_stat_empty, R.string.setting_ui_redundancy_sensor_compass_stat_8, R.string.setting_ui_redundancy_sensor_compass_stat_9};
    private int[] c = new int[]{R.string.setting_ui_redundancy_sensor_compass_stat_action_0, R.string.setting_ui_redundancy_sensor_compass_stat_action_1, R.string.setting_ui_redundancy_sensor_compass_stat_empty, R.string.setting_ui_redundancy_sensor_compass_stat_action_3, R.string.setting_ui_redundancy_sensor_compass_stat_action_4, R.string.setting_ui_redundancy_sensor_compass_stat_empty, R.string.setting_ui_redundancy_sensor_compass_stat_empty, R.string.setting_ui_redundancy_sensor_compass_stat_action_7, R.string.setting_ui_redundancy_sensor_compass_stat_action_8, R.string.setting_ui_redundancy_sensor_compass_stat_action_9};
    private View d;
    private TextView e;
    private TextView f;
    private TextView g;
    private Button h;
    private StatusView[] i;
    private String[] l = new String[]{"g_config.fdi_sensor[0].mag_over_0", "g_config.fdi_sensor[1].mag_over_0", "g_config.fdi_sensor[2].mag_over_0"};
    private String[] m = new String[]{"g_config.fdi_sensor[0].mag_stat_0", "g_config.fdi_sensor[1].mag_stat_0", "g_config.fdi_sensor[2].mag_stat_0"};
    private String n = "g_status.ns_busy_dev_0";

    public A3CompassView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_a3compass);
        if (!isInEditMode()) {
            this.d = findViewById(R.id.setting_ui_flyc_imu_ly3);
            this.e = (TextView) findViewById(R.id.setting_ui_flyc_imu_1);
            this.f = (TextView) findViewById(R.id.setting_ui_flyc_imu_2);
            this.g = (TextView) findViewById(R.id.setting_ui_flyc_imu_3);
            this.i = new StatusView[3];
            this.i[0] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_compass_1);
            this.i[1] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_compass_2);
            this.i[2] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_compass_3);
            this.e.setSelected(true);
            this.h = (Button) findViewById(R.id.setting_ui_flyc_cal_btn);
            this.h.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ A3CompassView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.Calibration).start(null);
                    c.a().e(SettingUIRootView.a.CloseBtnClick);
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        c.a().a(getAllKeys(), 1);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        c.a().b(getAllKeys(), 1);
    }

    public void onEventMainThread(DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash) {
        b();
    }

    @SuppressLint({"NewApi"})
    private void b() {
        DJILogHelper.getInstance().LOGD(a, "updateView", false, true);
        if (c()) {
            setVisibility(0);
            Object obj = null;
            int t = dji.pilot.publics.e.a.t(i.getInstance().c());
            if (2 == t) {
                this.d.setVisibility(8);
            } else {
                this.d.setVisibility(0);
            }
            int i = 0;
            while (i < t) {
                float f;
                Object obj2;
                StatusView statusView = this.i[i];
                float abs = Math.abs(d.read(this.l[i]).value.floatValue());
                DJILogHelper.getInstance().LOGD(a, "compass: " + abs + ", mCompassValueKeys:" + this.l, false, true);
                if (abs > 999.0f) {
                    abs = 999.0f;
                }
                int intValue = d.read(this.m[i]).value.intValue();
                DJILogHelper.getInstance().LOGD(a, "compassStat: " + intValue + ", --" + String.format("%.0f", new Object[]{Float.valueOf(abs)}), false, true);
                if (intValue == 5) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_green));
                    f = abs;
                } else if (intValue == 6) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_yellow));
                    f = abs;
                } else if (intValue == 7) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_red));
                    obj = 1;
                    f = abs;
                } else {
                    f = -1.0f;
                }
                if (intValue >= 0 && intValue < this.c.length) {
                    statusView.b.setText(this.c[intValue]);
                }
                statusView.c.setVisibility(0);
                statusView.a.setVisibility(0);
                LayoutParams layoutParams = (LayoutParams) statusView.b.getLayoutParams();
                layoutParams.removeRule(15);
                layoutParams.addRule(12);
                statusView.b.setLayoutParams(layoutParams);
                if (f != -1.0f) {
                    statusView.a.setText(String.format("%.0f", new Object[]{Float.valueOf(f)}));
                    statusView.c.setProgress((int) ((f / 999.0f) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
                    if (statusView.c.getProgress() < 5) {
                        statusView.c.setProgress(5);
                        obj2 = obj;
                    }
                    obj2 = obj;
                } else {
                    statusView.c.setProgress(0);
                    if (intValue < 0 || intValue >= this.c.length) {
                        statusView.b.setVisibility(8);
                        obj2 = obj;
                    } else {
                        statusView.b.setText(this.c[intValue]);
                        statusView.b.setVisibility(0);
                        if (intValue == 1) {
                            statusView.c.setVisibility(8);
                            statusView.a.setVisibility(8);
                            layoutParams.removeRule(12);
                            layoutParams.addRule(15);
                        }
                        if (intValue == 3 || intValue == 8) {
                            obj2 = 1;
                        }
                        obj2 = obj;
                    }
                }
                i++;
                obj = obj2;
            }
            this.e.setSelected(false);
            this.f.setSelected(false);
            this.g.setSelected(false);
            int intValue2 = (d.read(this.n).value.intValue() >> 6) & 3;
            if (intValue2 == 1) {
                this.e.setSelected(true);
            } else if (intValue2 == 2) {
                this.f.setSelected(true);
            } else if (intValue2 == 3) {
                this.g.setSelected(true);
            }
            if (obj != null) {
                this.h.setAlpha(1.0f);
                return;
            } else {
                this.h.setAlpha(dji.pilot.visual.a.d.c);
                return;
            }
        }
        setVisibility(8);
    }

    private boolean c() {
        return dji.pilot.publics.e.a.f();
    }

    private String[] getAllKeys() {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        for (Object add : this.l) {
            arrayList.add(add);
        }
        String[] strArr = this.m;
        int length = strArr.length;
        while (i < length) {
            arrayList.add(strArr[i]);
            i++;
        }
        arrayList.add(this.n);
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        return strArr2;
    }
}
