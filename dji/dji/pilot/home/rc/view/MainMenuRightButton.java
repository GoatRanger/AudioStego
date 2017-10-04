package dji.pilot.home.rc.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import dji.pilot.R;
import dji.pilot.R$styleable;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.setting.a.a;

public class MainMenuRightButton extends LinearLayout implements OnTouchListener {
    private Drawable a;
    private String b;
    private DJIImageView c;
    private DJITextView d;
    private ScaleAnimation e;
    private ScaleAnimation f;

    public MainMenuRightButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        a();
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, (int) R.layout.rc_main_right_menu_btn);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MainMenuRightButton);
            this.a = obtainStyledAttributes.getDrawable(0);
            this.b = obtainStyledAttributes.getString(1);
            this.c = (DJIImageView) findViewById(R.id.bk8);
            this.d = (DJITextView) findViewById(R.id.bk9);
            this.c.setImageDrawable(this.a);
            this.d.setText(this.b);
            setOnTouchListener(this);
        }
    }

    private void a() {
        this.e = new ScaleAnimation(1.0f, 1.09f, 1.0f, 1.08f, 1, d.c, 1, d.c);
        this.e.setDuration(150);
        this.e.setFillAfter(true);
        this.f = new ScaleAnimation(1.09f, 1.0f, 1.08f, 1.0f, 1, d.c, 1, d.c);
        this.f.setDuration(150);
        this.f.setFillAfter(true);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            startAnimation(this.f);
        }
        if (motionEvent.getAction() == 0) {
            startAnimation(this.e);
        }
        return false;
    }
}
