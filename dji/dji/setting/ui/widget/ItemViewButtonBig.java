package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public abstract class ItemViewButtonBig extends DividerLinearLayout implements OnClickListener {
    protected Button c;

    public ItemViewButtonBig(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        obtainStyledAttributes.recycle();
        a.a((View) this, R.layout.setting_ui_widget_item_btn_big);
        this.c = (Button) findViewById(R.id.setting_ui_item_btn);
        if (resourceId != 0) {
            this.c.setText(resourceId);
        }
        if (!isInEditMode()) {
            this.c.setOnClickListener(this);
        }
    }
}
