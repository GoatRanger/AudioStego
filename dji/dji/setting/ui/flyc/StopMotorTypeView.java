package dji.setting.ui.flyc;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.DividerLinearLayout;

public class StopMotorTypeView extends DividerLinearLayout implements d, b {
    protected int a;
    protected TextView b;
    protected TextView c;
    protected DJISpinnerButton d;
    protected ImageView e;
    a[] f;
    ProductType g;
    dji.midware.e.d h = new dji.midware.e.d(this) {
        final /* synthetic */ StopMotorTypeView a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.b("set param success");
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
            this.a.b("set param fail code=" + aVar.name());
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
    };
    private final String i = dji.midware.data.params.P3.a.A;
    private final String l = e.cm;
    private String[] m;

    class a {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        final /* synthetic */ StopMotorTypeView g;

        public a(StopMotorTypeView stopMotorTypeView, int i, int i2, int i3, int i4, int i5, int i6) {
            this.g = stopMotorTypeView;
            this.b = i2;
            this.a = i;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }
    }

    public StopMotorTypeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            dji.setting.a.a.a((View) this, R.layout.setting_ui_widget_stop_motor_view);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
            this.a = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
            obtainStyledAttributes.recycle();
            this.b = (TextView) findViewById(R.id.setting_ui_stop_motor_title);
            this.b.setText(this.a);
            this.d = (DJISpinnerButton) findViewById(R.id.setting_ui_stop_motor_spinner_btn);
            this.c = (TextView) findViewById(R.id.setting_ui_stop_motor_desc_text);
            this.e = (ImageView) findViewById(R.id.setting_ui_stop_motor_desc_img);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.sdksharedlib.a.a.g(this, new String[]{e.cm});
            dji.sdksharedlib.a.a.a(this, h.c);
            a();
            b();
            c();
        }
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        ProductType c = i.getInstance().c();
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.cm));
        a[] aVarArr;
        if ((c == ProductType.KumquatS || c == ProductType.KumquatX) && !dji.logic.c.b.getInstance().a(null)) {
            if (a >= 16 && a < 21) {
                aVarArr = new a[2];
                aVarArr[0] = new a(this, 0, 1, R.string.setting_ui_flyc_stop_motor_type_kumquat_1, R.drawable.setting_ui_stop_motor_kumquat, R.string.setting_ui_flyc_stop_motor_kumquat_desc_1, R.string.setting_ui_flyc_stop_motor_kumquat_alert_1);
                aVarArr[1] = new a(this, 1, 2, R.string.setting_ui_flyc_stop_motor_type_kumquat_2, R.drawable.setting_ui_stop_motor_kumquat, R.string.setting_ui_flyc_stop_motor_kumquat_desc_2, R.string.setting_ui_flyc_stop_motor_kumquat_alert_2);
                this.f = aVarArr;
            } else if (a >= 21) {
                a[] aVarArr2 = new a[2];
                aVarArr2[0] = new a(this, 0, 2, R.string.setting_ui_flyc_stop_motor_type_kumquat_1, R.drawable.setting_ui_stop_motor_kumquat, R.string.setting_ui_flyc_stop_motor_kumquat_desc_1, R.string.setting_ui_flyc_stop_motor_kumquat_alert_1);
                aVarArr2[1] = new a(this, 1, 6, R.string.setting_ui_flyc_stop_motor_type_kumquat_2, R.drawable.setting_ui_stop_motor_kumquat, R.string.setting_ui_flyc_stop_motor_kumquat_desc_2, R.string.setting_ui_flyc_stop_motor_kumquat_alert_2);
                this.f = aVarArr2;
            }
        } else if ((c == ProductType.Pomato || c == ProductType.Orange2) && a >= 21) {
            aVarArr = new a[2];
            aVarArr[0] = new a(this, 0, 1, R.string.setting_ui_flyc_stop_motor_type_1, R.drawable.setting_ui_stop_motor_pomato, R.string.setting_ui_flyc_stop_motor_desc_1, R.string.setting_ui_flyc_stop_motor_alert_1);
            aVarArr[1] = new a(this, 1, 0, R.string.setting_ui_flyc_stop_motor_type_2, -1, -1, R.string.setting_ui_flyc_stop_motor_alert_2);
            this.f = aVarArr;
        } else {
            this.f = null;
        }
        if (this.f != null) {
            this.m = new String[this.f.length];
            for (int i = 0; i < this.f.length; i++) {
                this.m[i] = getContext().getResources().getString(this.f[i].c);
            }
            this.d.setData(this.m, 0, (b) this);
        }
    }

    private void b() {
        new DataFlycGetParams().setInfos(new String[]{dji.midware.data.params.P3.a.A}).start(this.h);
    }

    private void c() {
        if (this.f != null) {
            setVisibility(0);
            int intValue = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.A).value.intValue();
            for (a aVar : this.f) {
                if (aVar.b == intValue) {
                    if (this.d.getCurIndex() != aVar.a) {
                        this.d.setIndex(aVar.a);
                    }
                    if (aVar.d > 0) {
                        this.e.setVisibility(0);
                        this.c.setVisibility(0);
                        this.e.setImageResource(aVar.d);
                        this.c.setText(aVar.e);
                        return;
                    }
                    this.e.setVisibility(8);
                    this.c.setVisibility(8);
                    return;
                }
            }
            return;
        }
        setVisibility(8);
    }

    public void onItemClick(int i) {
        if (i < this.f.length) {
            dji.setting.ui.widget.a.a(getContext(), this.f[i].f);
            new DataFlycSetParams().a(dji.midware.data.params.P3.a.A, Integer.valueOf(this.f[i].b)).start(this.h);
        }
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        a();
        c();
    }

    private void b(String str) {
        DJILogHelper.getInstance().LOGE("MOTOR_STOP_TYPE", str);
    }
}
