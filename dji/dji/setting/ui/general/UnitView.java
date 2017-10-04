package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import dji.apppublic.reflect.AppPublicReflect;
import dji.log.WM220LogUtil;
import dji.midware.data.model.P3.DataRcSetRcUnitNLang;
import dji.midware.e.d;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class UnitView extends DividerLinearLayout implements OnCheckedChangeListener {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    protected int d;
    protected TextView e;
    protected RadioGroup f;
    protected RadioButton g;
    protected RadioButton h;
    protected RadioButton i;

    public UnitView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_group_general_unit);
        this.e = (TextView) findViewById(R.id.setting_ui_item_title);
        this.f = (RadioGroup) findViewById(R.id.setting_ui_item_radiogroup);
        this.g = (RadioButton) findViewById(R.id.setting_ui_group_unit_imperial);
        this.h = (RadioButton) findViewById(R.id.setting_ui_group_unit_metric);
        this.i = (RadioButton) findViewById(R.id.setting_ui_group_unit_mertic_km);
        if (!isInEditMode()) {
            this.f.setOnCheckedChangeListener(this);
        }
    }

    private void a() {
        int v = DJIGenSettingDataManager.getInstance().v();
        if (v == 0) {
            this.g.setChecked(true);
        } else if (v == 1) {
            this.h.setChecked(true);
        } else {
            this.i.setChecked(true);
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int i2;
        int i3 = 2;
        int v = DJIGenSettingDataManager.getInstance().v();
        if (i == this.g.getId()) {
            i2 = 0;
        } else if (i == this.h.getId()) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        if (i2 != v) {
            if (i2 == 0) {
                e.a("FPV_GeneralSettings_Units_UnitsOfMeasurement_Button_Imperial");
            } else if (i2 == 1) {
                e.a("FPV_GeneralSettings_Units_UnitsOfMeasurement_Button_Metric");
            } else {
                e.a("FPV_GeneralSettings_Units_UnitsOfMeasurement_Button_Metric_Km");
            }
            DJIGenSettingDataManager.getInstance().b(i2);
            AppPublicReflect.handleParamUnitChanged(i2);
            if (i2 != 0) {
                i3 = 0;
            }
            DataRcSetRcUnitNLang.getInstance().a(i3).start(new d(this) {
                final /* synthetic */ UnitView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    WM220LogUtil.LOGD("**into DataRcSetRcUnitNLang onSuccess");
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    WM220LogUtil.LOGD("**into DataRcSetRcUnitNLang onFailure");
                }
            });
        }
    }
}
