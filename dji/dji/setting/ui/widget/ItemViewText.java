package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public abstract class ItemViewText extends DividerLinearLayout {
    protected TextView c;
    protected TextView d;

    public ItemViewText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_widget_item_text);
        this.c = (TextView) findViewById(R.id.setting_ui_item_title);
        this.d = (TextView) findViewById(R.id.setting_ui_item_text);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        obtainStyledAttributes.getResourceId(R.styleable.setting_ui_descText, 0);
        obtainStyledAttributes.recycle();
        if (resourceId != 0) {
            this.c.setText(resourceId);
        }
        if (!isInEditMode()) {
        }
    }
}
