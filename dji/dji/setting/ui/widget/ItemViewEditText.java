package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public abstract class ItemViewEditText extends DividerLinearLayout {
    protected EditText c;
    protected TextView d;
    protected TextView e;
    protected TextView f;

    public ItemViewEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_widget_item_edittext);
        if (!isInEditMode()) {
            this.c = (EditText) findViewById(R.id.setting_ui_item_edittext);
            this.d = (TextView) findViewById(R.id.setting_ui_item_title);
            this.e = (TextView) findViewById(R.id.setting_ui_item_desc);
            this.f = (TextView) findViewById(R.id.setting_ui_item_value);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_descText, 0);
            obtainStyledAttributes.recycle();
            if (resourceId != 0) {
                this.d.setText(resourceId);
            }
            if (resourceId2 != 0) {
                this.e.setVisibility(0);
                this.e.setText(resourceId2);
            }
        }
    }
}
