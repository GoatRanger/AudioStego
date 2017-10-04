package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DJISpinnerButton.b;

public abstract class ItemViewSpinner extends DividerLinearLayout implements b {
    protected int d;
    protected TextView e;
    protected DJISpinnerButton f;

    public ItemViewSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_widget_item_spinner);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        this.d = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        obtainStyledAttributes.recycle();
        this.e = (TextView) findViewById(R.id.setting_ui_item_title);
        this.e.setText(this.d);
        this.f = (DJISpinnerButton) findViewById(R.id.setting_ui_item_spinner_btn);
        if (!isInEditMode()) {
        }
    }
}
