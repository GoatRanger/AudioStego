package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public abstract class ItemViewSeekbarRightValue extends DividerLinearLayout implements OnSeekBarChangeListener {
    protected int a;
    protected TextView b;
    protected DJISeekbarRightValue c;

    public ItemViewSeekbarRightValue(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        this.a = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        obtainStyledAttributes.recycle();
        a.a((View) this, R.layout.setting_ui_widget_item_seekbar_right_value);
        this.b = (TextView) findViewById(R.id.setting_ui_item_title);
        if (this.a > 0) {
            this.b.setText(this.a);
        }
        this.c = (DJISeekbarRightValue) findViewById(R.id.setting_ui_item_seekbar);
    }
}
