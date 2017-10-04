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

public abstract class ItemViewSwitch extends DividerLinearLayout implements OnCheckedChangeListener {
    protected TextView eR_;
    protected Switch eS_;
    protected TextView eT_;

    public ItemViewSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_widget_item_switch);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_descText, 0);
        obtainStyledAttributes.recycle();
        this.eR_ = (TextView) findViewById(R.id.setting_ui_item_title);
        this.eS_ = (Switch) findViewById(R.id.setting_ui_item_switch);
        this.eT_ = (TextView) findViewById(R.id.setting_ui_item_desc);
        if (resourceId != 0) {
            this.eR_.setText(resourceId);
        }
        if (resourceId2 != 0) {
            this.eT_.setVisibility(0);
            this.eT_.setText(resourceId2);
        }
        if (!isInEditMode()) {
            this.eS_.setOnCheckedChangeListener(this);
        }
    }
}
