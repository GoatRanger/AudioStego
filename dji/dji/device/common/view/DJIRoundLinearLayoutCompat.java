package dji.device.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIRoundLinearLayoutCompat extends DJILinearLayout {
    private Paint a = null;
    private Path b = null;
    private int c = 0;
    private boolean d = false;

    public DJIRoundLinearLayoutCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public void setHasFrame(boolean z) {
        this.d = z;
    }

    private void a() {
        if (!isInEditMode()) {
            this.a = new Paint();
            this.a.setAntiAlias(true);
            this.a.setFilterBitmap(true);
            this.a.setColor(-16777217);
            setLayerType(1, this.a);
            setWillNotDraw(false);
            this.c = getResources().getDimensionPixelSize(R.dimen.longna_gen_corner_radius);
        }
    }

    private void b() {
        this.b = new Path();
    }

    public void setRadius(int i) {
        this.c = i;
    }

    public void draw(Canvas canvas) {
        if (this.b == null) {
            b();
        }
        this.b.reset();
        this.b.addRoundRect(new RectF(0.0f, 0.0f, (float) getWidth(), (float) getHeight()), (float) this.c, (float) this.c, Direction.CW);
        canvas.clipPath(this.b);
        if (this.d && getResources().getConfiguration().orientation == 1) {
            this.a.setColor(getResources().getColor(R.color.white));
            this.a.setStyle(Style.STROKE);
            canvas.drawRoundRect(new RectF(1.0f, 1.0f, (float) (getWidth() - 1), (float) (getHeight() - 1)), (float) this.c, (float) this.c, this.a);
            this.a.setColor(-16777217);
            this.a.setStyle(Style.FILL);
        }
        super.draw(canvas);
    }
}
