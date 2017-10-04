package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import dji.pilot.fpv.control.v;
import dji.pilot.fpv.control.v$b;
import dji.pilot.fpv.control.v$c;
import dji.pilot.publics.objects.g;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class RcStickView extends DividerLinearLayout implements b {
    protected DJISpinnerButton a;
    protected ImageView b;
    private int[] c = new int[]{R.string.setting_ui_rc_master_japan, R.string.setting_ui_rc_master_usa, R.string.setting_ui_rc_master_china};
    private int[] d = new int[]{R.drawable.setting_ui_rc_japan, R.drawable.setting_ui_rc_usa, R.drawable.setting_ui_rc_china};
    private v$b[] e = new v$b[]{v$b.JapaneseMode, v$b.AmericanMode, v$b.ChineseMode};

    public RcStickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_rc_stick);
        if (!isInEditMode()) {
            this.a = (DJISpinnerButton) findViewById(R.id.setting_ui_item_spinner_btn);
            this.b = (ImageView) findViewById(R.id.setting_ui_item_stick_img);
            this.a.setData(this.c, 0, (b) this);
            this.b.setImageResource(this.d[0]);
        }
    }

    public void onItemClick(int i) {
        this.b.setImageResource(this.d[i]);
        g.a(getContext(), v.a, this.e[i].ordinal());
        c.a().e(v$c.TRUE);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    private void a() {
        int b = g.b(getContext(), v.a, v$b.AmericanMode.ordinal());
        for (int i = 0; i != this.e.length; i++) {
            if (this.e[i].ordinal() == b) {
                this.a.setIndex(i);
            }
        }
    }
}
