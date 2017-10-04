package dji.setting.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.setting.ui.R;

public class DJICalProgressBar extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Paint g;
    private int h;
    private int i;
    private Paint j;

    public DJICalProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 2;
        this.b = 2;
        this.c = 12;
        this.d = 50;
        this.e = Color.parseColor("#727272");
        this.f = Color.parseColor("#00d8ff");
        this.h = 20;
        this.i = 70;
        this.d = (int) getResources().getDimension(R.dimen.dp_10);
        this.c = (int) getResources().getDimension(R.dimen.setting_ui_txt_small);
        this.a = (int) getResources().getDimension(R.dimen.dp_2);
        this.b = (int) getResources().getDimension(R.dimen.dp_2);
        this.g = new Paint();
        this.g.setStyle(Style.FILL);
        this.g.setAntiAlias(true);
        this.g.setColor(this.f);
        this.g.setStrokeWidth(0.0f);
        this.j = new Paint(1);
        this.j.setStrokeWidth(0.0f);
        this.j.setTextSize((float) this.c);
        this.j.setColor(-1);
        this.j.setTextAlign(Align.CENTER);
    }

    public void setValue(int i, int i2) {
        this.h = i;
        this.i = i2;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        this.g.setColor(this.e);
        canvas.drawRect(new Rect(0, (height - this.a) / 2, width, ((height - this.a) / 2) + this.a), this.g);
        this.g.setColor(this.f);
        canvas.drawRect(new Rect(((width / 2) * (100 - this.h)) / 100, (height - this.a) / 2, width / 2, ((height - this.a) / 2) + this.a), this.g);
        canvas.drawRect(new Rect(width / 2, (height - this.a) / 2, (width / 2) + (((width / 2) * this.i) / 100), ((height - this.a) / 2) + this.a), this.g);
        this.g.setColor(-1);
        canvas.drawRect(new Rect((width - this.b) / 2, (height - this.a) / 2, ((width - this.b) / 2) + this.b, ((height - this.a) / 2) + this.a), this.g);
    }
}
