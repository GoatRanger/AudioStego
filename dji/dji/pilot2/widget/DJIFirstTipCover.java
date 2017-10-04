package dji.pilot2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.R;
import dji.pilot.R$styleable;
import dji.pilot.visual.a.d;

public class DJIFirstTipCover extends View {
    private final Paint a;
    private final Paint b;
    private final Context c;
    private int d;
    private int e;
    private int f;
    private int g;

    public DJIFirstTipCover(Context context) {
        this(context, null);
    }

    public DJIFirstTipCover(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FirstTipCover);
        this.d = dip2px(context, obtainStyledAttributes.getDimension(1, 0.0f));
        this.e = dip2px(context, obtainStyledAttributes.getDimension(2, 0.0f));
        this.f = dip2px(context, obtainStyledAttributes.getDimension(0, 30.0f));
        this.g = dip2px(context, context.getResources().getDimension(R.dimen.fz));
        this.a = new Paint();
        this.a.setAntiAlias(true);
        this.a.setStyle(Style.FILL);
        this.b = new Paint();
        this.b.setAntiAlias(true);
        this.b.setStyle(Style.STROKE);
        obtainStyledAttributes.recycle();
    }

    protected void onDraw(Canvas canvas) {
        int i = this.d + this.f;
        int i2 = this.e + this.f;
        canvas.drawColor(this.c.getResources().getColor(R.color.ai));
        this.a.setARGB(255, 0, 0, 0);
        this.a.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        canvas.drawCircle((float) i, (float) i2, (float) this.f, this.a);
        this.b.setARGB(255, 246, 229, 24);
        this.b.setStrokeWidth((float) this.g);
        canvas.drawCircle((float) i, (float) i2, (float) (this.f - (this.g / 2)), this.b);
        super.onDraw(canvas);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + d.c);
    }

    public void locationRing(int i, int i2, int i3) {
        this.d = i;
        this.e = i2;
        this.f = i3;
        invalidate();
    }
}
