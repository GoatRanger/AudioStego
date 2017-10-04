package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import dji.midware.data.config.P3.ProductType;
import dji.midware.util.i;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class FpvLongPressSelectView extends ItemViewRadio implements OnClickListener {
    public static final String a = "key_fpv_long_press_ctrl";
    public static final int b = 0;
    public static final int c = 1;

    public FpvLongPressSelectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g.setText(R.string.setting_ui_general_longpress_gimbal_ctrl);
        this.h.setText(R.string.setting_ui_general_longpress_focus);
        this.h.setChecked(true);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
    }

    public void onClick(View view) {
        if (view.getId() == this.g.getId()) {
            i.a(getContext(), a, 0);
        } else {
            i.a(getContext(), a, 1);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
        if (a.c(c) || c == ProductType.Pomato) {
            setVisibility(0);
            if (i.b(getContext(), a, 1) == 0) {
                this.g.setChecked(true);
                return;
            } else {
                this.h.setChecked(true);
                return;
            }
        }
        setVisibility(8);
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}
