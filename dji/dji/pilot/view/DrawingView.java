package dji.pilot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class DrawingView extends View {
    private boolean a = false;
    private Rect b;
    private Paint c = new Paint();

    public DrawingView(Context context) {
        super(context);
        this.c.setColor(-287844393);
        this.c.setStyle(Style.STROKE);
        this.c.setStrokeWidth(2.0f);
        this.a = false;
    }

    public void setHaveTouch(boolean z, Rect rect) {
        this.a = z;
        this.b = rect;
    }

    public void onDraw(Canvas canvas) {
        if (this.a) {
            Log.d("DrawingView", "onDraw");
            canvas.drawRect((float) this.b.left, (float) this.b.top, (float) this.b.right, (float) this.b.bottom, this.c);
        }
    }
}
