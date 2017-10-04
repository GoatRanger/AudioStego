package dji.pilot2.nativeexplore.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import dji.pilot.R$styleable;

public class RoundCImageView extends ImageView {
    private final Paint a;
    private Path b;
    private float c;
    private int d;

    public RoundCImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Paint();
        this.d = 5;
        this.c = (float) context.obtainStyledAttributes(attributeSet, R$styleable.RoundImageView).getDimensionPixelSize(0, (int) TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics()));
        a();
    }

    public RoundCImageView(Context context) {
        this(context, null);
    }

    public void setRectAdius(float f) {
        this.c = f;
        invalidate();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @TargetApi(11)
    private void a() {
        setLayerType(1, null);
        this.a.setAntiAlias(true);
        this.a.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
    }

    private void a(int i, int i2) {
        this.b = new Path();
        this.b.addRoundRect(new RectF(((float) this.d) + 0.0f, ((float) this.d) + 0.0f, (float) (i - this.d), (float) (i2 - this.d)), this.c, this.c, Direction.CW);
        this.b.setFillType(FillType.INVERSE_WINDING);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            a(i, i2);
        }
    }

    protected void onDraw(Canvas canvas) {
        int saveLayerAlpha = canvas.saveLayerAlpha(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), 255, 4);
        super.onDraw(canvas);
        if (this.b != null) {
            canvas.drawPath(this.b, this.a);
        }
        canvas.restoreToCount(saveLayerAlpha);
    }
}
