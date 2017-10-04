package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public abstract class ItemViewSwitchBottomDesc extends DividerLinearLayout implements OnCheckedChangeListener {
    protected TextView a;
    protected Switch b;
    protected TextView c;

    public ItemViewSwitchBottomDesc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_widget_item_switch_bottom_desc);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_descText, 0);
        obtainStyledAttributes.recycle();
        this.a = (TextView) findViewById(R.id.setting_ui_item_title);
        this.b = (Switch) findViewById(R.id.setting_ui_item_switch);
        this.c = (TextView) findViewById(R.id.setting_ui_item_desc);
        if (resourceId != 0) {
            this.a.setText(resourceId);
        }
        if (resourceId2 != 0) {
            this.c.setVisibility(0);
            this.c.setText(resourceId2);
        }
        if (!isInEditMode()) {
            this.b.setOnCheckedChangeListener(this);
        }
    }
}
