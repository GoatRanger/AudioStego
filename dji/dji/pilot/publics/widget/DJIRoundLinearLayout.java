package dji.pilot.publics.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import dji.pilot.R;
import dji.pilot.R$styleable;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIRoundLinearLayout extends DJILinearLayout {
    private Paint a = null;
    private Path b = null;
    private int c = 0;

    public DJIRoundLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RoundView);
        this.c = obtainStyledAttributes.getDimensionPixelSize(0, this.c) - 2;
        obtainStyledAttributes.recycle();
    }

    protected void setRadius(int i) {
        this.c = i;
    }

    private void a() {
        if (!isInEditMode()) {
            this.a = new Paint();
            this.a.setAntiAlias(true);
            this.a.setFilterBitmap(true);
            this.a.setColor(-16777217);
            setLayerType(1, this.a);
            setWillNotDraw(false);
            this.c = getResources().getDimensionPixelSize(R.dimen.nu);
        }
    }

    public void draw(Canvas canvas) {
        if (this.b == null) {
            this.b = new Path();
        }
        this.b.reset();
        this.b.addRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), (float) this.c, (float) this.c, Direction.CW);
        canvas.clipPath(this.b);
        super.draw(canvas);
    }
}
