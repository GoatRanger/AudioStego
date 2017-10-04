package dji.pilot2.multimoment.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import dji.pilot.R;

public class ViewTransitions extends ViewGroup {
    private int[] a = new int[]{0, 0};
    private int b;
    private int c;
    private LayoutInflater d;
    private LinearLayout e;
    private int f = 20;
    private int g = 0;
    private int h = 0;
    private int i;

    public ViewTransitions(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    public ViewTransitions(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public ViewTransitions(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ViewTransitions(Context context) {
        super(context);
        a(context);
    }

    public void setLocation(int[] iArr) {
        this.a = iArr;
        invalidate();
        requestLayout();
    }

    public void go() {
        setVisibility(8);
    }

    public void show() {
        setVisibility(0);
    }

    private void a(Context context) {
        Log.i("zhang", "Myview");
        this.b = context.getResources().getDimensionPixelSize(R.dimen.a8m);
        this.c = context.getResources().getDimensionPixelSize(R.dimen.a8k);
        this.i = context.getResources().getDimensionPixelOffset(R.dimen.a8l);
        this.d = LayoutInflater.from(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.b = displayMetrics.widthPixels;
        this.e = new LinearLayout(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo((float) (this.a[0] + 12), (float) (this.a[1] + 5));
        path.lineTo((float) ((this.a[0] + this.f) + 12), (float) ((this.a[1] + this.f) + 5));
        path.lineTo((float) ((this.a[0] - this.f) + 12), (float) ((this.a[1] + this.f) + 5));
        path.close();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(3687499);
        paint.setStyle(Style.STROKE);
        canvas.drawPath(path, paint);
        a();
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(3687499);
        paint.setStyle(Style.FILL);
        canvas.drawRect(new RectF((float) this.g, (float) (this.a[1] + this.f), (float) this.h, (float) ((this.a[1] + this.f) + this.c)), paint);
    }

    private void a() {
        if (this.a[0] <= 50) {
            this.g = 0;
            this.h = this.b - 100;
        } else if (this.a[0] < this.b - 50) {
            this.g = 50;
            this.h = this.b - 50;
        } else {
            this.g = (this.a[0] + 50) - this.b;
            this.h = this.a[0];
        }
    }

    public void addChildView(View view) {
        view.setLayoutParams(new LayoutParams(100, 100));
        ((LinearLayout) getChildAt(0)).addView(view);
        requestLayout();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Log.i("zhang", "child:" + getChildCount());
        int childCount = getChildCount();
        int i5 = (this.b - 100) / (childCount + 1);
        a();
        for (int i6 = 0; i6 < childCount; i6++) {
            getChildAt(i6).layout(this.g + ((i6 + 1) * i5), ((this.a[1] + this.f) + (this.c / 2)) - this.i, (this.g + ((i6 + 1) * i5)) + this.i, ((this.a[1] + this.f) + (this.c / 2)) + this.i);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                float rawX = motionEvent.getRawX();
                float rawY = motionEvent.getRawY();
                if (rawX < ((float) this.g) || rawX > ((float) this.h) || rawY < ((float) this.a[1]) || rawY > ((float) (this.a[1] + this.c))) {
                    go();
                    break;
                }
        }
        return super.onTouchEvent(motionEvent);
    }
}
