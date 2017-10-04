package dji.setting.ui.flyc;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.apppublic.reflect.AppPublicReflect;
import dji.common.product.Model;
import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParamInfoByHash;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.h;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class LimitDistanceView extends DividerLinearLayout implements OnCheckedChangeListener {
    private static final String l = "g_config.flying_limit.max_radius_0";
    private static final String m = "g_config.advanced_function.radius_limit_enabled_0";
    private static final String n = "g_config.novice_cfg.novice_func_enabled_0";
    protected EditText a;
    protected TextView b;
    protected Switch c;
    protected LinearLayout d;
    protected TextView e;
    DataFlycGetParamInfoByHash f = new DataFlycGetParamInfoByHash();
    OnEditorActionListener g = new OnEditorActionListener(this) {
        final /* synthetic */ LimitDistanceView a;

        {
            this.a = r1;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (6 == i) {
                this.a.b();
            }
            return false;
        }
    };
    TextWatcher h = new TextWatcher(this) {
        final /* synthetic */ LimitDistanceView a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.a.a(editable);
        }
    };
    private final String i = "LimitDistanceView";
    private int o;

    public LimitDistanceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_flyc_distance);
        this.a = (EditText) findViewById(R.id.setting_ui_item_edittext);
        this.b = (TextView) findViewById(R.id.setting_ui_item_desc);
        this.c = (Switch) findViewById(R.id.setting_ui_item_switch);
        this.d = (LinearLayout) findViewById(R.id.setting_ui_flyc_distance_ly);
        this.e = (TextView) findViewById(R.id.setting_ui_flyc_distance_txt);
        this.c.setOnCheckedChangeListener(this);
        this.a.setOnEditorActionListener(this.g);
        this.a.addTextChangedListener(this.h);
        this.a.setInputType(2);
        this.a.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
    }

    private void getParam() {
        this.f.setIndex(l).start(new d(this) {
            final /* synthetic */ LimitDistanceView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGE("LimitDistanceView", "get limit distance success");
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGE("LimitDistanceView", "get limit distance error=" + aVar.name());
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                });
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a.b().a(l, m, "g_config.novice_cfg.novice_func_enabled_0");
            getParam();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a() {
        boolean z;
        int i;
        ParamInfo read = dji.midware.data.manager.P3.d.read(m);
        Model model = (Model) dji.sdksharedlib.a.a.a(h.c);
        int i2 = dji.midware.data.manager.P3.d.read("g_config.novice_cfg.novice_func_enabled_0").value.intValue() == 1 ? 1 : 0;
        if (read.value.intValue() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (model != null && model.equals(Model.MavicPro) && c()) {
            i = 1;
        } else {
            i = 0;
        }
        if (i2 != 0) {
            this.e.setVisibility(0);
            this.e.setText("30m");
            this.c.setVisibility(8);
            this.d.setVisibility(8);
        } else if (i != 0) {
            this.e.setVisibility(0);
            i = dji.midware.data.manager.P3.d.read(l).value.intValue();
            if (i > 80) {
                i = 80;
            }
            this.e.setText("" + i);
            this.c.setVisibility(8);
            this.d.setVisibility(8);
        } else {
            this.e.setVisibility(8);
            this.c.setVisibility(0);
            this.c.setChecked(z);
            if (z) {
                this.d.setVisibility(0);
                this.a.setText("" + dji.midware.data.manager.P3.d.read(l).value.intValue());
                try {
                    if (this.f.getParamInfo() != null) {
                        this.b.setText(String.format("(%d~%dM)", new Object[]{Integer.valueOf(this.f.getParamInfo().range.a.intValue()), Integer.valueOf(this.f.getParamInfo().range.b.intValue())}));
                        return;
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            }
            this.d.setVisibility(8);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i;
        if (z) {
            e.a("FPV_MCSettings_Switcher_MaximumDistance_ON");
        } else {
            e.a("FPV_MCSettings_Switcher_MaximumDistance_OFF");
        }
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        String str = m;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        dataFlycSetParams.a(str, Integer.valueOf(i)).start(new d(this) {
            final /* synthetic */ LimitDistanceView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                });
                AppPublicReflect.GS_HOME_CIRCLE_EVENT_UPDATE();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                });
            }
        });
    }

    private void a(Editable editable) {
        String obj = this.a.getEditableText().toString();
        if (obj != null && !obj.isEmpty()) {
            if (this.f.getParamInfo().isCorrect(Integer.valueOf(a.a(obj, Integer.MIN_VALUE)))) {
                this.a.setTextColor(getResources().getColor(R.color.setting_ui_edittext_selected));
            } else {
                this.a.setTextColor(getResources().getColor(R.color.setting_ui_edittext_invalid));
            }
        }
    }

    private void b() {
        String obj = this.a.getEditableText().toString();
        if (obj == null || obj.isEmpty()) {
            a();
            return;
        }
        e.c(s.ds);
        e.a("FPV_MCSettings_TextField_SetMaximumFlightDistance");
        final int a = a.a(obj, Integer.MIN_VALUE);
        if (a != this.o) {
            ParamInfo paramInfo = this.f.getParamInfo();
            ParamInfo read = dji.midware.data.manager.P3.d.read(l);
            if (paramInfo.isCorrect(Integer.valueOf(a))) {
                new DataFlycSetParams().a(read.name, Integer.valueOf(a)).start(new d(this) {
                    final /* synthetic */ LimitDistanceView b;

                    public void onSuccess(Object obj) {
                        this.b.o = a;
                        AppPublicReflect.GS_HOME_CIRCLE_EVENT_UPDATE();
                        this.b.b(R.string.setting_ui_setting_success);
                        this.b.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.a();
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.b(R.string.setting_ui_setting_fail_disconnect);
                        this.b.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.a();
                            }
                        });
                    }
                });
                return;
            }
            a();
            b(R.string.setting_ui_setting_fail_out_of_range);
        }
    }

    public void onEventMainThread(ProductType productType) {
        getParam();
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals(l) || aVar.a.equals(m) || aVar.a.equals("g_config.novice_cfg.novice_func_enabled_0")) {
            a();
        }
    }

    private boolean c() {
        return i.getInstance().c().isFromWifi() || b.getInstance().a(i.getInstance().c());
    }
}
