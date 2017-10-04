package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class StatusView extends DividerLinearLayout {
    public TextView a;
    public TextView b;
    public ProgressBar c;

    public StatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_status_item);
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.setting_ui_flyc_value);
            this.b = (TextView) findViewById(R.id.setting_ui_flyc_desc);
            this.c = (ProgressBar) findViewById(R.id.setting_ui_flyc_pgb);
        }
    }

    protected void a(int i) {
        if (i == 7) {
            this.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_green));
        } else if (i == 8) {
            this.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_yellow));
        } else if (i == 9) {
            this.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_red));
        }
    }
}
