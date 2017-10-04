package dji.pilot.fpv.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.Locale;

public class DJIRedundancyNumView extends View {
    Paint a;
    private int b;
    private int c;
    private a d;

    public interface a {
        void a(View view, int i, boolean z);
    }

    public DJIRedundancyNumView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = 1;
        this.c = 0;
        this.d = null;
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setStrokeWidth(1.0f);
    }

    public void setOnValueChangedListener(a aVar) {
        this.d = aVar;
    }

    public void setVaule(int i) {
        if (i > 0 && i < 7) {
            this.b = i;
            postInvalidate();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 || action == 2 || action == 1) {
            int x = (int) motionEvent.getX();
            if (x != this.c) {
                this.c = x;
                x = getHeight();
                int width = (getWidth() - x) / 5;
                int i = x / 2;
                for (x = 0; x < 6; x++) {
                    if (this.c < ((x * width) + i) + (width / 2)) {
                        this.b = x + 1;
                        break;
                    }
                }
                if (!(this.d == null || action == 1)) {
                    this.d.a(this, this.b, false);
                }
            }
            if (action == 1) {
                invalidate();
                if (this.d != null) {
                    this.d.a(this, this.b, true);
                }
            } else {
                invalidate();
            }
        }
        return true;
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();
        this.a.setStyle(Style.STROKE);
        this.a.setColor(-1);
        canvas.drawArc(new RectF((float) (height / 2), (float) (height / 4), (float) height, (float) ((height * 3) / 4)), 90.0f, 180.0f, false, this.a);
        canvas.drawArc(new RectF((float) (width - height), (float) (height / 4), (float) (width - (height / 2)), (float) ((height * 3) / 4)), 270.0f, 180.0f, false, this.a);
        canvas.drawLine((float) ((height * 3) / 4), (float) (height / 4), (float) (width - ((height * 3) / 4)), (float) (height / 4), this.a);
        canvas.drawLine((float) ((height * 3) / 4), (float) ((height * 3) / 4), (float) (width - ((height * 3) / 4)), (float) ((height * 3) / 4), this.a);
        int i = (width - height) / 5;
        int i2 = (height / 2) + i;
        for (int i3 = 0; i3 < 4; i3++) {
            canvas.drawLine((float) ((i3 * i) + i2), (float) (height / 4), (float) ((i3 * i) + i2), (float) ((height * 3) / 4), this.a);
        }
        this.a.setStyle(Style.FILL);
        this.a.setColor(-1);
        canvas.drawCircle((float) ((height / 2) + ((this.b - 1) * i)), (float) (height / 2), (float) ((height / 2) - 1), this.a);
        this.a.setStyle(Style.STROKE);
        this.a.setColor(-16711936);
        canvas.drawCircle((float) ((height / 2) + ((this.b - 1) * i)), (float) (height / 2), (float) ((height / 2) - 1), this.a);
        Rect rect = new Rect();
        this.a.setColor(-16777216);
        String format = String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(this.b)});
        this.a.getTextBounds(format, 0, format.length(), rect);
        canvas.drawText(format, (float) (((i * (this.b - 1)) + (height / 2)) - (rect.width() / 2)), (float) ((rect.height() / 4) + (height / 2)), this.a);
        super.onDraw(canvas);
    }
}
