package antistatic.spinnerwheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import antistatic.spinnerwheel.g.a;
import dji.frame.widget.R;

public class WheelHorizontalView extends AbstractWheelView {
    private static int H = -1;
    protected int G;
    private final String I;
    private int J;

    public WheelHorizontalView(Context context) {
        this(context, null);
    }

    public WheelHorizontalView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.abstractWheelViewStyle);
    }

    public WheelHorizontalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        StringBuilder append = new StringBuilder().append(WheelVerticalView.class.getName()).append(" #");
        int i2 = H + 1;
        H = i2;
        this.I = append.append(i2).toString();
        this.J = 0;
    }

    protected void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.WheelHorizontalView, i, 0);
        this.G = obtainStyledAttributes.getDimensionPixelSize(R.styleable.WheelHorizontalView_selectionDividerWidth, 2);
        obtainStyledAttributes.recycle();
    }

    public void setSelectionDividerWidth(int i) {
        this.G = i;
    }

    public void setSelectorPaintCoeff(float f) {
        if (this.s < 100) {
            Shader linearGradient;
            int measuredWidth = getMeasuredWidth();
            int itemDimension = getItemDimension();
            float f2 = (1.0f - (((float) itemDimension) / ((float) measuredWidth))) / 2.0f;
            float f3 = (1.0f + (((float) itemDimension) / ((float) measuredWidth))) / 2.0f;
            float f4 = ((float) this.s) * (1.0f - f);
            float f5 = (255.0f * f) + f4;
            int round;
            if (this.b == 2) {
                itemDimension = Math.round(f5) << 24;
                round = Math.round(f4) << 24;
                linearGradient = new LinearGradient(0.0f, 0.0f, (float) measuredWidth, 0.0f, new int[]{round, itemDimension, -16777216, -16777216, itemDimension, round}, new float[]{0.0f, f2, f2, f3, f3, 1.0f}, TileMode.CLAMP);
            } else {
                float f6 = (1.0f - (((float) (itemDimension * 3)) / ((float) measuredWidth))) / 2.0f;
                float f7 = ((((float) (itemDimension * 3)) / ((float) measuredWidth)) + 1.0f) / 2.0f;
                float f8 = ((255.0f * f6) / f2) * f;
                int round2 = Math.round(f5) << 24;
                round = Math.round(f4 + f8) << 24;
                int round3 = Math.round(f8) << 24;
                linearGradient = new LinearGradient(0.0f, 0.0f, (float) measuredWidth, 0.0f, new int[]{117440512, round3, round, round2, -16777216, -16777216, round2, round, round3, 117440512}, new float[]{0.0f, f6, f6, f2, f2, f3, f3, f7, f7, 1.0f}, TileMode.CLAMP);
            }
            this.y.setShader(linearGradient);
            invalidate();
        }
    }

    protected g a(a aVar) {
        return new e(getContext(), aVar);
    }

    protected float a(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    protected int getBaseDimension() {
        return getWidth();
    }

    protected int getItemDimension() {
        if (this.J != 0) {
            return this.J;
        }
        if (this.h == null || this.h.getChildAt(0) == null) {
            return getBaseDimension() / this.b;
        }
        this.J = this.h.getChildAt(0).getMeasuredWidth();
        return this.J;
    }

    protected void c() {
        super.c();
        int childCount = this.h.getChildCount();
        Log.e(this.I, " ----- layout: " + this.h.getMeasuredWidth() + this.h.getMeasuredHeight());
        Log.e(this.I, " -------- dumping " + childCount + " items");
        for (int i = 0; i < childCount; i++) {
            View childAt = this.h.getChildAt(i);
            Log.e(this.I, " item #" + i + ": " + childAt.getWidth() + "x" + childAt.getHeight());
            childAt.forceLayout();
        }
        Log.e(this.I, " ---------- dumping finished ");
    }

    protected void e() {
        if (this.h == null) {
            this.h = new LinearLayout(getContext());
            this.h.setOrientation(0);
        }
    }

    protected void f() {
        this.h.layout(0, 0, getMeasuredWidth(), getMeasuredHeight() - (this.w * 2));
    }

    protected void j() {
        this.h.setLayoutParams(new LayoutParams(-2, -2));
        this.h.measure(MeasureSpec.makeMeasureSpec(getWidth() + getItemDimension(), 0), MeasureSpec.makeMeasureSpec(getHeight(), Integer.MIN_VALUE));
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        i();
        size2 = c(size2, mode2);
        if (mode != 1073741824) {
            mode2 = Math.max(getItemDimension() * (this.b - (this.v / 100)), getSuggestedMinimumWidth());
            size = mode == Integer.MIN_VALUE ? Math.min(mode2, size) : mode2;
        }
        setMeasuredDimension(size, size2);
    }

    private int c(int i, int i2) {
        this.h.setLayoutParams(new LayoutParams(-2, -2));
        this.h.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(i, 0));
        int measuredHeight = this.h.getMeasuredHeight();
        if (i2 != 1073741824) {
            measuredHeight = Math.max(measuredHeight + (this.w * 2), getSuggestedMinimumHeight());
            if (i2 != Integer.MIN_VALUE || i >= measuredHeight) {
                i = measuredHeight;
            }
        }
        this.h.measure(MeasureSpec.makeMeasureSpec(400, 1073741824), MeasureSpec.makeMeasureSpec(i - (this.w * 2), 1073741824));
        return i;
    }

    protected void a(Canvas canvas) {
        canvas.save();
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int itemDimension = getItemDimension();
        this.E.eraseColor(0);
        Canvas canvas2 = new Canvas(this.E);
        Canvas canvas3 = new Canvas(this.E);
        canvas2.translate((float) ((-(((this.a - this.i) * itemDimension) + ((itemDimension - getWidth()) / 2))) + this.g), (float) this.w);
        this.h.draw(canvas2);
        this.F.eraseColor(0);
        Canvas canvas4 = new Canvas(this.F);
        if (this.x != null) {
            int width = ((getWidth() - itemDimension) - this.G) / 2;
            int i = this.G + width;
            canvas4.save();
            canvas4.clipRect(width, 0, i, measuredHeight);
            this.x.setBounds(width, 0, i, measuredHeight);
            this.x.draw(canvas4);
            canvas4.restore();
            canvas4.save();
            width += itemDimension;
            itemDimension += i;
            canvas4.clipRect(width, 0, itemDimension, measuredHeight);
            this.x.setBounds(width, 0, itemDimension, measuredHeight);
            this.x.draw(canvas4);
            canvas4.restore();
        }
        canvas3.drawRect(0.0f, 0.0f, (float) measuredWidth, (float) measuredHeight, this.y);
        canvas4.drawRect(0.0f, 0.0f, (float) measuredWidth, (float) measuredHeight, this.z);
        canvas.drawBitmap(this.E, 0.0f, 0.0f, null);
        canvas.drawBitmap(this.F, 0.0f, 0.0f, null);
        canvas.restore();
    }
}
