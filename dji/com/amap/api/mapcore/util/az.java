package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.view.View;
import com.amap.api.mapcore.util.r.a;
import java.io.InputStream;

class az extends View {
    int a = 10;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Paint f = new Paint();
    private boolean g = false;
    private int h = 0;
    private c i;
    private int j = 0;

    public void a() {
        try {
            if (this.b != null) {
                this.b.recycle();
            }
            if (this.c != null) {
                this.c.recycle();
            }
            this.b = null;
            this.c = null;
            if (this.d != null) {
                this.d.recycle();
                this.d = null;
            }
            if (this.e != null) {
                this.e.recycle();
                this.e = null;
            }
            this.f = null;
        } catch (Throwable th) {
            ee.a(th, "WaterMarkerView", "destory");
            th.printStackTrace();
        }
    }

    public az(Context context) {
        super(context);
    }

    public az(Context context, c cVar) {
        super(context);
        this.i = cVar;
        try {
            InputStream open;
            if (r.g == a.ALIBABA) {
                open = dh.a(context).open("apl.data");
            } else {
                open = dh.a(context).open("ap.data");
            }
            this.d = BitmapFactory.decodeStream(open);
            this.b = dj.a(this.d, r.a);
            open.close();
            if (r.g == a.ALIBABA) {
                open = dh.a(context).open("apl1.data");
            } else {
                open = dh.a(context).open("ap1.data");
            }
            this.e = BitmapFactory.decodeStream(open);
            this.c = dj.a(this.e, r.a);
            open.close();
            this.h = this.c.getHeight();
            this.f.setAntiAlias(true);
            this.f.setColor(-16777216);
            this.f.setStyle(Style.STROKE);
        } catch (Throwable th) {
            ee.a(th, "WaterMarkerView", "create");
            th.printStackTrace();
        }
    }

    public Bitmap b() {
        if (this.g) {
            return this.c;
        }
        return this.b;
    }

    public void a(boolean z) {
        try {
            this.g = z;
            if (z) {
                this.f.setColor(-1);
            } else {
                this.f.setColor(-16777216);
            }
            invalidate();
        } catch (Throwable th) {
            ee.a(th, "WaterMarkerView", "changeBitmap");
            th.printStackTrace();
        }
    }

    public Point c() {
        return new Point(this.a, (getHeight() - this.h) - 10);
    }

    public void a(int i) {
        this.j = i;
    }

    public void onDraw(Canvas canvas) {
        try {
            if (this.c != null) {
                int width = this.c.getWidth();
                if (this.j == 1) {
                    this.a = (this.i.c() - width) / 2;
                } else if (this.j == 2) {
                    this.a = (this.i.c() - width) - 10;
                } else {
                    this.a = 10;
                }
                if (r.g == a.ALIBABA) {
                    canvas.drawBitmap(b(), (float) (this.a + 15), (float) ((getHeight() - this.h) - 8), this.f);
                } else {
                    canvas.drawBitmap(b(), (float) this.a, (float) ((getHeight() - this.h) - 8), this.f);
                }
            }
        } catch (Throwable th) {
            ee.a(th, "WaterMarkerView", "onDraw");
            th.printStackTrace();
        }
    }
}
