package dji.setting.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.thirdparty.a.c;
import java.util.HashMap;

public class SettingUIRootView extends LinearLayout implements OnClickListener {
    private static final String a = "SettingUIRootView";
    private HashMap<Integer, Integer> b = new HashMap();
    private HashMap<Integer, SettingUIStageView> c = new HashMap();
    private HashMap<Integer, View> d = new HashMap();
    private int e = 0;
    private FrameLayout f;
    private View g;
    private View h;
    private TextView i;
    private OnClickListener j = new OnClickListener(this) {
        final /* synthetic */ SettingUIRootView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            this.a.b();
        }
    };
    private OnClickListener k = new OnClickListener(this) {
        final /* synthetic */ SettingUIRootView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            c.a().e(a.CloseBtnClick);
        }
    };

    public enum a {
        CloseBtnClick,
        BackBtnClick
    }

    public SettingUIRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        Log.d(a, "onAttachedToWindow");
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
        Log.d(a, "onDetachedFromWindow");
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_root);
        if (!isInEditMode()) {
            this.f = (FrameLayout) findViewById(R.id.root_content_layout);
            this.i = (TextView) findViewById(R.id.root_title);
            this.g = findViewById(R.id.root_back_btn);
            this.g.setOnClickListener(this.j);
            this.h = findViewById(R.id.root_close_btn);
            this.h.setOnClickListener(this.k);
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_rc), Integer.valueOf(R.layout.setting_ui_group_rc));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_gimbal), Integer.valueOf(R.layout.setting_ui_group_gimbal));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_gimbal_ronin), Integer.valueOf(R.layout.setting_ui_group_gimbal_ronin));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_flyc), Integer.valueOf(R.layout.setting_ui_group_flyc));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_hd), Integer.valueOf(R.layout.setting_ui_group_hd));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_joystick), Integer.valueOf(R.layout.setting_ui_group_joystick));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_battery), Integer.valueOf(R.layout.setting_ui_group_battery));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_battery_m600), Integer.valueOf(R.layout.setting_ui_group_battery_m600));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_battery_inspire2), Integer.valueOf(R.layout.setting_ui_group_battery_insprie2));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_battery_non_smart), Integer.valueOf(R.layout.setting_ui_group_battery_non_smart));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_general), Integer.valueOf(R.layout.setting_ui_group_general));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_wifi), Integer.valueOf(R.layout.setting_ui_group_wifi));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_vision), Integer.valueOf(R.layout.setting_ui_group_vision));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_guidance), Integer.valueOf(R.layout.setting_ui_group_guidance));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_innertools_editor), Integer.valueOf(R.layout.setting_ui_group_innertools_editor));
            this.b.put(Integer.valueOf(R.id.setting_ui_root_btn_innertools_viewer), Integer.valueOf(R.layout.setting_ui_group_innertools_viewer));
            for (Integer num : this.b.keySet()) {
                View findViewById = findViewById(num.intValue());
                findViewById.setOnClickListener(this);
                this.d.put(num, findViewById);
            }
            a(R.id.setting_ui_root_btn_flyc);
        }
    }

    public void onClick(View view) {
        a(view.getId());
    }

    private void b() {
        if (this.c.get(Integer.valueOf(this.e)) != null) {
            if (((SettingUIStageView) this.c.get(Integer.valueOf(this.e))).popView() == 1) {
                this.g.setVisibility(8);
            }
            this.i.setText(((SettingUIStageView) this.c.get(Integer.valueOf(this.e))).getTitleId());
        }
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.BackBtnClick) {
            b();
        }
    }

    public void onEventMainThread(c cVar) {
        this.g.setVisibility(0);
        this.i.setText(cVar.a());
    }

    private void a(int i) {
        if (i != this.e) {
            View view = (SettingUIStageView) this.c.get(Integer.valueOf(i));
            if (view == null) {
                view = (SettingUIStageView) LayoutInflater.from(getContext()).inflate(((Integer) this.b.get(Integer.valueOf(i))).intValue(), null);
                this.c.put(Integer.valueOf(i), view);
            }
            View view2 = view;
            SettingUIStageView settingUIStageView = (SettingUIStageView) this.c.get(Integer.valueOf(this.e));
            if (settingUIStageView != null) {
                this.f.removeView(settingUIStageView);
            }
            if (this.e > 0) {
                ((View) this.d.get(Integer.valueOf(this.e))).setSelected(false);
            }
            this.f.addView(view2);
            this.i.setText(view2.getTitleId());
            this.e = i;
            ((View) this.d.get(Integer.valueOf(this.e))).setSelected(true);
            if (view2.getChildCount() > 1) {
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(8);
            }
        }
    }

    public int getCurTabId() {
        return this.e;
    }

    public void gotoTab(int i) {
        a(i);
    }

    public void gotoGoHomeItem() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        int i = R.id.setting_ui_root_btn_flyc;
        a(i);
        View view = (SettingUIStageView) this.c.get(Integer.valueOf(i));
        view.pushView(new c(R.layout.setting_ui_group_flyc_adv, R.string.setting_ui_flyc_adv_setting, view), false);
        view.pushView(new c(R.layout.setting_ui_group_general_about, R.string.setting_ui_flyc_fs_gohome, view), false);
        this.i.setText(R.string.setting_ui_flyc_fs_gohome);
        this.g.setVisibility(0);
    }

    public void gotoFlycSensor() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        int i = R.id.setting_ui_root_btn_flyc;
        a(i);
        View view = (SettingUIStageView) this.c.get(Integer.valueOf(i));
        view.pushView(new c(R.layout.setting_ui_group_flyc_adv, R.string.setting_ui_flyc_adv_setting, view), false);
        if (dji.pilot.publics.e.a.f()) {
            view.pushView(new c(R.layout.setting_ui_group_flyc_a3_sensor, R.string.setting_ui_flyc_sensor, view), false);
        } else {
            view.pushView(new c(R.layout.setting_ui_group_flyc_sensor, R.string.setting_ui_flyc_sensor, view), false);
        }
        this.i.setText(R.string.setting_ui_flyc_sensor);
        this.g.setVisibility(0);
    }

    public void gotoFlycCompass() {
        if (dji.pilot.publics.e.a.f()) {
            if (getVisibility() != 0) {
                setVisibility(0);
            }
            int i = R.id.setting_ui_root_btn_flyc;
            a(i);
            View view = (SettingUIStageView) this.c.get(Integer.valueOf(i));
            view.pushView(new c(R.layout.setting_ui_group_flyc_adv, R.string.setting_ui_flyc_adv_setting, view), false);
            view.pushView(new c(R.layout.setting_ui_group_flyc_a3_sensor, R.string.setting_ui_flyc_sensor, view), false);
            this.i.setText(R.string.setting_ui_flyc_sensor);
            this.g.setVisibility(0);
        }
    }

    public void gotoRcMode() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        int i = R.id.setting_ui_root_btn_rc;
        a(i);
        View view = (SettingUIStageView) this.c.get(Integer.valueOf(i));
        if (dji.pilot.publics.e.a.j(null)) {
            view.pushView(new c(R.layout.setting_ui_group_rc_common, R.string.setting_ui_rc, view), false);
            this.i.setText(R.string.setting_ui_rc);
            this.g.setVisibility(0);
            return;
        }
        this.i.setText(R.string.setting_ui_rc);
        this.g.setVisibility(8);
    }
}
