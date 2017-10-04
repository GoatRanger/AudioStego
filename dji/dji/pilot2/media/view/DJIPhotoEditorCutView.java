package dji.pilot2.media.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import dji.gs.e.b;
import dji.pilot.R;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class DJIPhotoEditorCutView extends View {
    private int a = 35;
    private int b = 500;
    private int c = 4;
    private boolean d = false;
    private int e = 0;
    private int f = 0;
    private String g;
    private Paint h = null;
    private float i = 30.0f;
    private float j = 30.0f;
    private Context k = null;

    public DJIPhotoEditorCutView(Context context) {
        super(context);
        this.k = context;
        a();
    }

    public DJIPhotoEditorCutView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = context;
        a();
    }

    private void a() {
        this.f = Color.rgb(255, 255, 255);
        this.e = Color.rgb(255, 255, 0);
        this.h = new Paint();
        this.h.setAntiAlias(true);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.a = 20;
        } else {
            this.a = this.k.getResources().getDimensionPixelSize(R.dimen.fj);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.b = getMeasuredWidth();
        this.c = getMeasuredHeight();
    }

    public void setText(String str) {
        this.g = str;
    }

    public void setChecked(boolean z) {
        this.d = z;
    }

    public boolean isChecked() {
        return this.d;
    }

    public void setRectSize(float f, float f2) {
        this.i = f;
        this.j = f2;
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.d) {
            this.h.setColor(this.e);
        } else {
            this.h.setColor(this.f);
        }
        this.h.setTextSize((float) this.a);
        Rect rect = new Rect();
        this.h.getTextBounds(this.g, 0, this.g.length(), rect);
        this.h.setStyle(Style.FILL);
        this.h.setStrokeWidth(1.0f);
        canvas.drawText(this.g, (float) ((this.b - rect.width()) / 2), (float) (this.c - 8), this.h);
        this.h.setStyle(Style.STROKE);
        this.h.setStrokeWidth(2.0f);
        canvas.drawRect(((((float) this.b) - this.i) + b.a) / 2.0f, (((float) ((this.c - rect.height()) + 16)) - this.j) / 2.0f, ((((float) this.b) + this.i) - b.a) / 2.0f, (((float) ((this.c - rect.height()) - 16)) + this.j) / 2.0f, this.h);
    }
}
