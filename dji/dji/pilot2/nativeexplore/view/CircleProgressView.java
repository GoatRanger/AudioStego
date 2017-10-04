package dji.pilot2.nativeexplore.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R$styleable;
import dji.publics.e.a;

public class CircleProgressView extends View {
    private Context a;
    private double b;
    private double c;
    private Paint d;
    private Paint e;
    private Paint f;
    private float g;
    private int h;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, 0);
        this.c = 100.0d;
        this.a = context;
        a(attributeSet);
        a();
    }

    protected void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(attributeSet, R$styleable.postRoundProgressBar);
        this.c = (double) obtainStyledAttributes.getInteger(0, 100);
        this.b = (double) obtainStyledAttributes.getInteger(1, 0);
        this.g = obtainStyledAttributes.getDimension(3, DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity);
        this.h = obtainStyledAttributes.getInteger(2, 0);
    }

    @TargetApi(21)
    public CircleProgressView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.c = 100.0d;
        this.a = context;
        a(attributeSet);
        a();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth);
    }

    public void setmMaxProgress(int i) {
        this.c = (double) i;
    }

    public void setmCurProgress(int i) {
        this.b = (double) i;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        getHeight();
        int i = width / 2;
        width = (int) (((float) i) - (this.g / 2.0f));
        canvas.drawCircle((float) i, (float) i, (float) width, this.d);
        canvas.drawArc(new RectF((float) (i - width), (float) (i - width), (float) (i + width), (float) (width + i)), (float) this.h, (float) ((360.0d * this.b) / this.c), false, this.e);
        width = (int) ((this.b / ((double) ((float) this.c))) * 100.0d);
        canvas.drawText(width + "%", ((float) i) - (this.f.measureText(width + "%") / 2.0f), (float) (i - (a.d(this.a, 12.0f) / 2)), this.f);
    }

    protected void a() {
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.d.setColor(Color.parseColor("#EEEEEE"));
        this.d.setStrokeWidth(this.g);
        this.d.setStyle(Style.STROKE);
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setColor(Color.parseColor("#1FA3F6"));
        this.e.setStrokeWidth(this.g);
        this.e.setStyle(Style.STROKE);
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.f.setColor(Color.parseColor("#9B9B9B"));
        this.f.setTextSize((float) a.d(this.a, 12.0f));
    }
}
