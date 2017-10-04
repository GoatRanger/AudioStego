package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public class ItemViewGroup extends DividerLinearLayout implements OnClickListener {
    protected int a;
    protected int b;

    public ItemViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        this.a = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_titleText, 0);
        this.b = obtainStyledAttributes.getResourceId(R.styleable.setting_ui_subLayout, 0);
        obtainStyledAttributes.recycle();
        a.a((View) this, R.layout.setting_ui_widget_item_group);
        ((TextView) findViewById(R.id.setting_ui_item_title)).setText(this.a);
        if (!isInEditMode()) {
            setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        if (!a.a()) {
            c.a().e(new dji.setting.ui.c(this.b, this.a, (View) this));
        }
    }
}
