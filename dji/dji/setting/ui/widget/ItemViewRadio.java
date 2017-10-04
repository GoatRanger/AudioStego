package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public abstract class ItemViewRadio extends DividerLinearLayout implements OnCheckedChangeListener {
    protected int d;
    protected TextView e;
    protected RadioGroup f;
    protected RadioButton g;
    protected RadioButton h;

    public ItemViewRadio(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_widget_item_radio);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        this.d = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        obtainStyledAttributes.recycle();
        this.e = (TextView) findViewById(R.id.setting_ui_item_title);
        this.e.setText(this.d);
        this.f = (RadioGroup) findViewById(R.id.setting_ui_item_radiogroup);
        this.g = (RadioButton) findViewById(R.id.setting_ui_group_unit_imperial);
        this.h = (RadioButton) findViewById(R.id.setting_ui_group_unit_metric);
        if (!isInEditMode()) {
            this.f.setOnCheckedChangeListener(this);
        }
    }
}
