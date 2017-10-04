package dji.setting.ui.wifi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import dji.pilot.fpv.control.v;
import dji.setting.ui.widget.ItemViewEditText;

public class JoystickMaxValView extends ItemViewEditText {
    private static final int b = 10;
    private static final int g = 330;
    OnEditorActionListener a = new OnEditorActionListener(this) {
        final /* synthetic */ JoystickMaxValView a;

        {
            this.a = r1;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (6 == i) {
                this.a.a();
            }
            return false;
        }
    };
    private int h = 0;

    public JoystickMaxValView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.h = v.getInstance().b();
            this.f.setVisibility(0);
            this.c.setOnEditorActionListener(this.a);
        }
    }

    private void a() {
        float floatValue = Float.valueOf(this.c.getText().toString()).floatValue();
        if (floatValue < 10.0f || floatValue > 330.0f) {
            this.c.setText("" + this.h);
            return;
        }
        this.h = (int) floatValue;
        this.c.setText("" + this.h);
        v.getInstance().b(this.h);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            b();
        }
    }

    private void b() {
        if (a.a()) {
            setVisibility(8);
        } else {
            setVisibility(8);
        }
        this.f.setText("(10~330)");
        this.c.setText("" + this.h);
    }
}
