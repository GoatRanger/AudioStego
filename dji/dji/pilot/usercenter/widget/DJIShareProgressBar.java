package dji.pilot.usercenter.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;

public class DJIShareProgressBar extends DJIStateImageView {
    private static Bitmap i = null;
    private static Bitmap j = null;
    private static Bitmap k = null;
    Paint a = null;
    int b = 0;
    private int c = 500;
    private int d = 281;
    private int e = 100;
    private int f = 40;
    private int g = 60;
    private int h = 90;
    private boolean l = false;
    private String m;
    private Context n;

    public DJIShareProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = context;
        a();
    }

    private void a() {
        this.a = new Paint();
        i = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
        j = BitmapFactory.decodeResource(getResources(), R.drawable.share_notify_close);
        k = BitmapFactory.decodeResource(getResources(), R.drawable.product_new);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.c = getMeasuredWidth();
        this.d = getMeasuredHeight();
        this.h = this.d / 3;
        this.e = this.d / 3;
        setMeasuredDimension(this.c, this.d);
    }

    public void setFilePath(String str) {
        this.m = str;
    }

    public void setNewFlag(boolean z) {
        this.l = z;
        invalidate();
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 100) {
            i = 100;
        }
        this.b = (i * 360) / 100;
        invalidate();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
