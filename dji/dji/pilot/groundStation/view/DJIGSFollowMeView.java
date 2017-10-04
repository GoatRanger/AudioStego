package dji.pilot.groundStation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.R;
import it.sauronsoftware.ftp4j.FTPCodes;

public class DJIGSFollowMeView extends View {
    private static int d = 20;
    private Bitmap a = null;
    private Bitmap b = null;
    private Bitmap c = null;
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = 0.0f;

    public DJIGSFollowMeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d = (int) getResources().getDimension(R.dimen.g1);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a = BitmapFactory.decodeResource(getResources(), R.drawable.gs_follow_me_image);
        this.b = BitmapFactory.decodeResource(getResources(), R.drawable.gs_follow_me_aircraft);
        this.c = BitmapFactory.decodeResource(getResources(), R.drawable.gs_follow_me_triangle);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!(this.a == null || this.a.isRecycled())) {
            this.a.recycle();
            this.a = null;
        }
        if (!(this.b == null || this.b.isRecycled())) {
            this.b.recycle();
            this.b = null;
        }
        if (this.c != null && !this.c.isRecycled()) {
            this.c.recycle();
            this.c = null;
        }
    }

    public void updateRotate(float f, float f2, float f3) {
        this.e = f;
        this.f = f2;
        this.g = f3;
        postInvalidate();
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.a.getWidth(), this.a.getHeight() + (d * 2));
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(this.a, 0.0f, (float) d, null);
        float width = (float) ((this.a.getWidth() * 171) / FTPCodes.CONNECTION_CLOSED);
        float sin = (float) (((double) width) * Math.sin((((double) this.e) * 3.141592653589793d) / 180.0d));
        width = (float) (((double) width) * Math.cos((((double) this.e) * 3.141592653589793d) / 180.0d));
        Matrix matrix = new Matrix();
        matrix.postRotate(this.g, (float) (this.c.getWidth() / 2), (float) this.c.getHeight());
        matrix.postTranslate(((float) ((this.a.getWidth() / 2) - (this.c.getWidth() / 2))) + sin, ((float) (((this.a.getHeight() / 2) + d) - this.c.getHeight())) + width);
        canvas.drawBitmap(this.c, matrix, null);
        matrix = new Matrix();
        matrix.postRotate(this.f, (float) (this.b.getWidth() / 2), (float) (this.b.getHeight() / 2));
        matrix.postTranslate(sin + ((float) ((this.a.getWidth() / 2) - (this.b.getWidth() / 2))), width + ((float) (((this.a.getHeight() / 2) + d) - (this.b.getHeight() / 2))));
        canvas.drawBitmap(this.b, matrix, null);
    }
}
