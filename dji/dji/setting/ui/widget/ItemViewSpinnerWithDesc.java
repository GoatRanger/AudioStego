package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DJISpinnerButton.b;

public abstract class ItemViewSpinnerWithDesc extends DividerLinearLayout implements b {
    protected int b;
    protected TextView c;
    protected TextView d;
    protected DJISpinnerButton e;

    public ItemViewSpinnerWithDesc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_widget_item_spinner_with_desc);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        this.b = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        obtainStyledAttributes.recycle();
        this.c = (TextView) findViewById(R.id.setting_ui_item_title);
        this.c.setText(this.b);
        this.d = (TextView) findViewById(R.id.setting_ui_item_desc);
        this.e = (DJISpinnerButton) findViewById(R.id.setting_ui_item_spinner_btn);
        if (!isInEditMode()) {
        }
    }
}
