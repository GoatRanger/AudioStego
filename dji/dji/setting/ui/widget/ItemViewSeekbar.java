package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public abstract class ItemViewSeekbar extends DividerLinearLayout implements OnSeekBarChangeListener {
    protected int b;
    protected TextView c;
    protected DJINumberProgress d;

    public ItemViewSeekbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        this.b = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        obtainStyledAttributes.recycle();
        a.a((View) this, R.layout.setting_ui_widget_item_seekbar);
        this.c = (TextView) findViewById(R.id.setting_ui_item_title);
        if (this.b > 0) {
            this.c.setText(this.b);
        }
        this.d = (DJINumberProgress) findViewById(R.id.setting_ui_item_seekbar);
    }
}
