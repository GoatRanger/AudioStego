package dji.setting.ui.flyc;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.logic.c.b;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class GohomeNewView extends DividerLinearLayout implements TextWatcher, OnFocusChangeListener, OnEditorActionListener {
    private static final String a = "g_config.go_home.fixed_go_home_altitude_0";
    private static final String b = "g_config.flying_limit.max_height_0";
    private EditText c;
    private TextView d;

    public GohomeNewView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_gohome);
        if (!isInEditMode()) {
            this.c = (EditText) findViewById(R.id.setting_ui_item_edittext);
            this.d = (TextView) findViewById(R.id.setting_ui_item_desc);
            setListener(this.c);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            b();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals(a)) {
            c();
        }
    }

    private void b() {
        c();
        DataFlycGetParams.getInstance().setInfos(new String[]{a, b}).start(new d(this) {
            final /* synthetic */ GohomeNewView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.c();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void c() {
        this.c.setText("" + dji.midware.data.manager.P3.d.read(a).value.intValue());
        this.d.setText(String.format("(%d~%dM)", new Object[]{r0.range.a, r0.range.b}));
        if (b.getInstance().a(null)) {
            this.d.setText(String.format("(%d~%dM)", new Object[]{Integer.valueOf(20), Integer.valueOf(50)}));
        }
    }

    private ParamInfo getGoHomeHeight() {
        return dji.midware.data.manager.P3.d.read(a);
    }

    private void setListener(EditText editText) {
        editText.addTextChangedListener(this);
        editText.setOnEditorActionListener(this);
        editText.setOnFocusChangeListener(this);
    }

    private void d() {
        new DataFlycGetParams().setInfos(new String[]{a}).start(new d(this) {
            final /* synthetic */ GohomeNewView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.c();
                    }
                });
            }
        });
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        e.a("FPV_MCSettings_AdvancedSettings_AdvancedFailsafeSettings_TextField_ReturnToHomeAltitude");
        int a = a.a(textView.getText().toString(), 0);
        if (getGoHomeHeight().isCorrect(Integer.valueOf(a))) {
            int intValue = dji.midware.data.manager.P3.d.read(b).value.intValue();
            if (intValue == -1) {
                d();
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_error_can_not_get_limit_height);
            } else if (a > intValue) {
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_error_go_home_height_heigher_than_limit_height);
                textView.setText("" + dji.midware.data.manager.P3.d.read(a).value.intValue());
            } else {
                new DataFlycSetParams().a(a, Integer.valueOf(a)).start(new d(this) {
                    final /* synthetic */ GohomeNewView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.b(R.string.setting_ui_setting_success);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.c();
                        this.a.b(R.string.setting_ui_setting_fail_disconnect);
                    }
                });
            }
        } else {
            c();
            b(R.string.setting_ui_setting_fail_out_of_range);
        }
        return false;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        TextView textView = this.c;
        if (!obj.equals("") && !obj.equals(null)) {
            dji.setting.a.b.a(getContext(), textView, getGoHomeHeight().isCorrect(Integer.valueOf(a.a(obj, Integer.MIN_VALUE))));
        }
    }

    public void onFocusChange(View view, boolean z) {
    }
}
