package com.facebook.share.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.R;
import dji.common.flightcontroller.DJIFlightControllerDataType;

public class LikeBoxCountView extends FrameLayout {
    private TextView a;
    private a b = a.LEFT;
    private float c;
    private float d;
    private float e;
    private Paint f;
    private int g;
    private int h;

    public enum a {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM
    }

    public LikeBoxCountView(Context context) {
        super(context);
        a(context);
    }

    public void setText(String str) {
        this.a.setText(str);
    }

    public void setCaretPosition(a aVar) {
        this.b = aVar;
        switch (aVar) {
            case LEFT:
                a(this.h, 0, 0, 0);
                return;
            case TOP:
                a(0, this.h, 0, 0);
                return;
            case RIGHT:
                a(0, 0, this.h, 0);
                return;
            case BOTTOM:
                a(0, 0, 0, this.h);
                return;
            default:
                return;
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        switch (this.b) {
            case LEFT:
                paddingLeft = (int) (((float) paddingLeft) + this.c);
                break;
            case TOP:
                paddingTop = (int) (((float) paddingTop) + this.c);
                break;
            case RIGHT:
                width = (int) (((float) width) - this.c);
                break;
            case BOTTOM:
                height = (int) (((float) height) - this.c);
                break;
        }
        Canvas canvas2 = canvas;
        a(canvas2, (float) paddingLeft, (float) paddingTop, (float) width, (float) height);
    }

    private void a(Context context) {
        setWillNotDraw(false);
        this.c = getResources().getDimension(R.dimen.com_facebook_likeboxcountview_caret_height);
        this.d = getResources().getDimension(R.dimen.com_facebook_likeboxcountview_caret_width);
        this.e = getResources().getDimension(R.dimen.com_facebook_likeboxcountview_border_radius);
        this.f = new Paint();
        this.f.setColor(getResources().getColor(R.color.com_facebook_likeboxcountview_border_color));
        this.f.setStrokeWidth(getResources().getDimension(R.dimen.com_facebook_likeboxcountview_border_width));
        this.f.setStyle(Style.STROKE);
        b(context);
        addView(this.a);
        setCaretPosition(this.b);
    }

    private void b(Context context) {
        this.a = new TextView(context);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        this.a.setGravity(17);
        this.a.setTextSize(0, getResources().getDimension(R.dimen.com_facebook_likeboxcountview_text_size));
        this.a.setTextColor(getResources().getColor(R.color.com_facebook_likeboxcountview_text_color));
        this.g = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeboxcountview_text_padding);
        this.h = getResources().getDimensionPixelSize(R.dimen.com_facebook_likeboxcountview_caret_height);
    }

    private void a(int i, int i2, int i3, int i4) {
        this.a.setPadding(this.g + i, this.g + i2, this.g + i3, this.g + i4);
    }

    private void a(Canvas canvas, float f, float f2, float f3, float f4) {
        Path path = new Path();
        float f5 = this.e * 2.0f;
        path.addArc(new RectF(f, f2, f + f5, f2 + f5), DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle, 90.0f);
        if (this.b == a.TOP) {
            path.lineTo((((f3 - f) - this.d) / 2.0f) + f, f2);
            path.lineTo(((f3 - f) / 2.0f) + f, f2 - this.c);
            path.lineTo((((f3 - f) + this.d) / 2.0f) + f, f2);
        }
        path.lineTo(f3 - this.e, f2);
        path.addArc(new RectF(f3 - f5, f2, f3, f2 + f5), -90.0f, 90.0f);
        if (this.b == a.RIGHT) {
            path.lineTo(f3, (((f4 - f2) - this.d) / 2.0f) + f2);
            path.lineTo(this.c + f3, ((f4 - f2) / 2.0f) + f2);
            path.lineTo(f3, (((f4 - f2) + this.d) / 2.0f) + f2);
        }
        path.lineTo(f3, f4 - this.e);
        path.addArc(new RectF(f3 - f5, f4 - f5, f3, f4), 0.0f, 90.0f);
        if (this.b == a.BOTTOM) {
            path.lineTo((((f3 - f) + this.d) / 2.0f) + f, f4);
            path.lineTo(((f3 - f) / 2.0f) + f, this.c + f4);
            path.lineTo((((f3 - f) - this.d) / 2.0f) + f, f4);
        }
        path.lineTo(this.e + f, f4);
        path.addArc(new RectF(f, f4 - f5, f5 + f, f4), 90.0f, 90.0f);
        if (this.b == a.LEFT) {
            path.lineTo(f, (((f4 - f2) + this.d) / 2.0f) + f2);
            path.lineTo(f - this.c, ((f4 - f2) / 2.0f) + f2);
            path.lineTo(f, (((f4 - f2) - this.d) / 2.0f) + f2);
        }
        path.lineTo(f, this.e + f2);
        canvas.drawPath(path, this.f);
    }
}
