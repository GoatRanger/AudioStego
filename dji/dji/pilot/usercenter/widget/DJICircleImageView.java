package dji.pilot.usercenter.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.util.AttributeSet;
import dji.pilot.publics.widget.DJIStateImageView;

public class DJICircleImageView extends DJIStateImageView {
    private Paint a = null;
    private Path b = null;

    public DJICircleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setFilterBitmap(true);
        this.a.setColor(-16777217);
        setLayerType(1, this.a);
    }

    private void b() {
        if (this.b == null) {
            this.b = new Path();
            int width = getWidth() / 2;
            this.b.addCircle((float) width, (float) width, (float) width, Direction.CW);
        }
    }

    protected void onDraw(Canvas canvas) {
        b();
        canvas.clipPath(this.b);
        super.onDraw(canvas);
    }
}
