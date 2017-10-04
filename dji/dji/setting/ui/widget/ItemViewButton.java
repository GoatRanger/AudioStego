package dji.setting.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;

public class ItemViewButton extends DividerLinearLayout implements OnClickListener {
    protected Button a;

    public ItemViewButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_widget_item_btn);
        this.a = (Button) findViewById(R.id.setting_ui_item_btn);
        this.a.setOnClickListener(this);
    }

    public void onClick(View view) {
    }
}
