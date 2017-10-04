package dji.pilot.publics.widget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import dji.pilot.R;

public class DJIRoundFrameLayout extends FrameLayout {
    private Paint a;
    private Path b;
    private int c;
    private boolean d;

    public DJIRoundFrameLayout(Context context) {
        this(context, null);
    }

    public DJIRoundFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIRoundFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = false;
        if (!isInEditMode()) {
            a();
        }
    }

    public void setRound(int i) {
        this.c = i;
    }

    public void setClip(boolean z) {
        this.d = z;
    }

    private void a() {
        if (this.a == null) {
            this.c = getResources().getDimensionPixelSize(R.dimen.nu);
            this.a = new Paint();
            this.a.setAntiAlias(true);
            this.a.setFilterBitmap(true);
            this.a.setColor(-16777217);
            setWillNotDraw(false);
        }
    }

    private void b() {
        if (this.b == null) {
            this.b = new Path();
            this.b.addRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), (float) this.c, (float) this.c, Direction.CW);
        }
    }
}
