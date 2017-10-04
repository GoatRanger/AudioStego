package dji.setting.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;
import dji.pilot.setting.ui.R;
import dji.publics.a;

public class DividerLinearLayout extends LinearLayout {
    protected static Paint eV_ = new Paint();
    protected boolean eU_ = true;

    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ DividerLinearLayout b;

        AnonymousClass1(DividerLinearLayout dividerLinearLayout, String str) {
            this.b = dividerLinearLayout;
            this.a = str;
        }

        public void run() {
            Toast.makeText(this.b.getContext(), this.a, 0).show();
        }
    }

    static {
        eV_.setColor(Color.parseColor("#77979797"));
    }

    public DividerLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.setting_ui);
        this.eU_ = obtainStyledAttributes.getBoolean(R.styleable.setting_ui_showDivider, true);
        obtainStyledAttributes.recycle();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.eU_) {
            canvas.drawRect(0.0f, (float) (getHeight() - 1), (float) getWidth(), (float) getHeight(), eV_);
        }
    }

    public void runOnUiThread(Runnable runnable) {
        if (runnable != null) {
            a.a(runnable);
        }
    }

    protected void a(String str) {
    }

    protected void b(final int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ DividerLinearLayout b;

            public void run() {
                Toast.makeText(this.b.getContext(), this.b.getContext().getString(i), 0).show();
            }
        });
    }
}
