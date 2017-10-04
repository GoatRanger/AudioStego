package dji.pilot.usercenter.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;

public class DJIRoundImageView extends DJIStateImageView {
    private Paint a = null;
    private Path b = null;
    private int c = 0;
    private boolean d = false;

    public DJIRoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    public void setBeCircle(boolean z) {
        this.d = z;
    }

    public void setRound(int i) {
        this.c = i;
    }

    private void a() {
        this.c = getResources().getDimensionPixelSize(R.dimen.nu);
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setFilterBitmap(true);
        this.a.setColor(-16777217);
        setLayerType(1, this.a);
    }

    private void b() {
        if (this.b == null) {
            this.b = new Path();
            int width = getWidth();
            int height = getHeight();
            if (this.d) {
                this.c = width / 2;
            }
            this.b.addRoundRect(new RectF(0.0f, 0.0f, (float) width, (float) height), (float) this.c, (float) this.c, Direction.CW);
        }
    }

    public void draw(Canvas canvas) {
        b();
        canvas.clipPath(this.b);
        super.draw(canvas);
    }
}
