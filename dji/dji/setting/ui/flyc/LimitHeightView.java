package dji.setting.ui.flyc;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.common.product.Model;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.b.h;
import dji.setting.a.b;
import dji.setting.ui.widget.ItemViewEditText;
import dji.thirdparty.a.c;
import java.util.Locale;

public class LimitHeightView extends ItemViewEditText {
    private static final String g = "g_config.flying_limit.max_height_0";
    OnEditorActionListener a = new OnEditorActionListener(this) {
        final /* synthetic */ LimitHeightView a;

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
    TextWatcher b = new TextWatcher(this) {
        final /* synthetic */ LimitHeightView a;

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

    public LimitHeightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.c.setOnEditorActionListener(this.a);
            this.c.addTextChangedListener(this.b);
            this.c.setInputType(2);
            this.c.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
            a.b().a(g);
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a() {
        int i;
        Model model = (Model) a.a(h.c);
        int i2 = d.read(dji.midware.data.params.P3.a.B).value.intValue() == 1 ? 1 : 0;
        if (model != null && model.equals(Model.MavicPro) && d()) {
            i = 1;
        } else {
            i = 0;
        }
        ParamInfo read = d.read(g);
        if (i2 != 0) {
            this.c.setVisibility(8);
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            this.f.setText("30m");
        } else if (i != 0) {
            this.c.setVisibility(8);
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            i = read.value.intValue();
            if (i > 50) {
                i = 50;
            }
            this.f.setText("" + i);
        } else {
            this.c.setVisibility(0);
            this.e.setVisibility(0);
            this.f.setVisibility(8);
            this.e.setText(String.format("(%d~%dM)", new Object[]{Integer.valueOf(read.range.a.intValue()), Integer.valueOf(read.range.b.intValue())}));
            this.c.setText("" + read.value.intValue());
        }
    }

    private void a(Editable editable) {
        String obj = this.c.getEditableText().toString();
        if (obj != null && !obj.isEmpty()) {
            int a = dji.setting.a.a.a(obj, Integer.MIN_VALUE);
            b.a(getContext(), this.c, d.read(g).isCorrect(Integer.valueOf(a)));
        }
    }

    private void b() {
        String obj = this.c.getEditableText().toString();
        if (obj == null || obj.isEmpty()) {
            a();
            return;
        }
        final int a = dji.setting.a.a.a(obj, Integer.MIN_VALUE);
        if (a == getFlycHeight()) {
            return;
        }
        if (!d.read(g).isCorrect(Integer.valueOf(a))) {
            a();
        } else if (a > 120) {
            AlertDialog a2 = dji.setting.ui.widget.a.a(getContext());
            a2.setTitle(R.string.setting_ui_dialog_title_statement);
            a2.setMessage(getResources().getString(R.string.setting_ui_flyc_limit_height_notice));
            a2.setButton(-1, getResources().getString(R.string.setting_ui_dialog_agree), new OnClickListener(this) {
                final /* synthetic */ LimitHeightView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.b.setLimitHeight(a);
                }
            });
            a2.setButton(-2, getResources().getString(R.string.setting_ui_dialog_refuse), new OnClickListener(this) {
                final /* synthetic */ LimitHeightView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.a.a();
                }
            });
            a2.show();
            DJILogHelper.getInstance().LOGD("", "result=" + a, false, true);
        } else {
            setLimitHeight(a);
        }
    }

    private void setLimitHeight(final int i) {
        final ParamInfo read = d.read(g);
        if (!read.isCorrect(Integer.valueOf(i))) {
            b(R.string.setting_ui_setting_fail_out_of_range);
            a();
        } else if (i < DataOsdGetPushHome.getInstance().getGoHomeHeight()) {
            Builder builder = new Builder(getContext());
            String format = String.format(Locale.getDefault(), "%dm", new Object[]{Integer.valueOf(i)});
            builder.setMessage(String.format(getContext().getString(R.string.setting_ui_flyc_error_go_home_height_updated), new Object[]{format}));
            builder.setPositiveButton(17039379, new OnClickListener(this) {
                final /* synthetic */ LimitHeightView c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                    dataFlycSetParams.a(new String[]{read.name, "g_config.go_home.fixed_go_home_altitude_0"});
                    dataFlycSetParams.a(new Number[]{Integer.valueOf(i), Integer.valueOf(i)});
                    dataFlycSetParams.start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            a.b().a(LimitHeightView.g, "g_config.go_home.fixed_go_home_altitude_0");
                            this.a.c.b(R.string.setting_ui_setting_success);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.c.c();
                            this.a.c.b(R.string.setting_ui_setting_fail_disconnect);
                        }
                    });
                }
            });
            builder.setNegativeButton(17039369, new OnClickListener(this) {
                final /* synthetic */ LimitHeightView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a();
                }
            });
            builder.create().show();
        } else {
            new DataFlycSetParams().a(read.name, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
                final /* synthetic */ LimitHeightView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.b().a(LimitHeightView.g);
                    this.a.b(R.string.setting_ui_setting_success);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.c();
                    this.a.b(R.string.setting_ui_setting_fail_disconnect);
                }
            });
        }
    }

    private void c() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ LimitHeightView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        });
    }

    private int getFlycHeight() {
        return d.read(g).value.intValue();
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals(g) || aVar.a.equals(dji.midware.data.params.P3.a.B)) {
            a();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    private boolean d() {
        return i.getInstance().c().isFromWifi() || dji.logic.c.b.getInstance().a(i.getInstance().c());
    }
}
