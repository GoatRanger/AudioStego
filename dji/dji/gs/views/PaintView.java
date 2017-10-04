package dji.gs.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.dji.frame.c.g;
import dji.gs.R;
import dji.gs.c.e;
import dji.gs.views.EventView.b;
import dji.gs.views.EventView.c;
import java.util.ArrayList;

public class PaintView extends ImageView {
    protected ArrayList<Point> a;
    private e b;
    private Paint c;
    private Paint d;
    private int e;
    private Bitmap f;
    private Paint g;
    private c h;
    private EventView i;
    private int j;
    private int k = 0;
    private SparseArray<Point> l = new SparseArray();

    public PaintView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setLayerType(1, null);
        if (!isInEditMode()) {
            this.c = new Paint(257);
            this.c.setColor(context.getResources().getColor(R.color.gs_line_flying));
            this.c.setAntiAlias(true);
            this.c.setStyle(Style.STROKE);
            this.c.setStrokeWidth(10.0f);
            this.d = new Paint();
            this.d.setColor(context.getResources().getColor(R.color.gs_line_normal));
            this.d.setAntiAlias(true);
            this.d.setStyle(Style.STROKE);
            this.d.setStrokeWidth(10.0f);
            this.g = new Paint();
            this.g.setColor(context.getResources().getColor(R.color.gs_paint_blue));
            this.g.setAntiAlias(true);
            this.g.setStyle(Style.STROKE);
            this.g.setStrokeWidth(20.0f);
            this.f = g.a(context, R.drawable.gs_homepoint);
        }
    }

    public PaintView(Context context, e eVar) {
        super(context);
        bringToFront();
        setLayoutParams(new LayoutParams(-1, -1));
        this.b = eVar;
        setWillNotDraw(false);
        setLayerType(1, null);
        if (!isInEditMode()) {
            this.c = new Paint(257);
            this.c.setColor(context.getResources().getColor(R.color.gs_line_flying));
            this.c.setAntiAlias(true);
            this.c.setStyle(Style.STROKE);
            this.c.setStrokeWidth(10.0f);
            this.d = new Paint();
            this.d.setColor(context.getResources().getColor(R.color.gs_line_normal));
            this.d.setAntiAlias(true);
            this.d.setStyle(Style.STROKE);
            this.d.setStrokeWidth(10.0f);
            this.g = new Paint();
            this.g.setColor(context.getResources().getColor(R.color.gs_paint_blue));
            this.g.setAntiAlias(true);
            this.g.setStyle(Style.STROKE);
            this.g.setStrokeWidth(20.0f);
            this.f = g.a(context, R.drawable.gs_homepoint);
            dji.thirdparty.a.c.a().a(this);
        }
    }

    public void onEventMainThread(EventView eventView) {
        this.i = eventView;
        this.h = eventView.getMode();
        if (this.h == c.PAINTING) {
            if (!eventView.isPaintUp()) {
                Point point = (Point) this.l.get(this.k - 1);
                Point point2 = eventView.getPoint();
                if (!point2.equals(point)) {
                    this.l.append(this.k, point2);
                    this.k++;
                }
            }
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.h == c.PAINTING) {
            a(canvas);
        } else if (this.h != c.NORMAL) {
            b(canvas);
        }
    }

    private void a(Canvas canvas) {
        if (this.i.getMode() == c.MULTIPLE) {
            this.l.clear();
            this.k = 0;
        } else if (this.i.isPaintUp()) {
            if (this.k > 1) {
                this.b.a(this.l);
            }
            this.l.clear();
            this.k = 0;
        } else {
            if (this.k > 1) {
                Path path = new Path();
                for (int i = 0; i < this.k; i++) {
                    Point point = (Point) this.l.get(i);
                    if (i == 0) {
                        path.moveTo((float) point.x, (float) point.y);
                    } else {
                        path.lineTo((float) point.x, (float) point.y);
                    }
                }
                canvas.drawPath(path, this.g);
            }
            if (this.k > 0) {
                dji.thirdparty.a.c.a().e(b.HAS);
            }
        }
    }

    public void init(int i) {
        this.e = i;
        this.a = this.b.m();
        getOffset();
        postInvalidate();
    }

    public void update(int i, int i2) {
        Point point = new Point(i, i2 - this.j);
        Point b = this.b.b(point);
        if (b != null) {
            point = b;
        }
        this.a.set(this.e, point);
        if (this.e == 1) {
            dji.gs.e.c b2 = this.b.b(this.e);
            Point l = this.b.l();
            if (l != null) {
                float a = this.b.a(point, l);
                b2.getInfo().a(a);
                b2.getIcon().b(this.e, a);
                this.b.a(this.e, b2);
            }
        }
        if (this.e > 1) {
            dji.gs.e.c b3 = this.b.b(this.e);
            float a2 = this.b.a(point, (Point) this.a.get(this.e - 1));
            b3.getInfo().a(a2);
            b3.getIcon().b(this.e, a2);
            this.b.a(this.e, b3);
        }
        int i3 = this.e + 1;
        if (i3 < this.b.b()) {
            dji.gs.e.c b4 = this.b.b(i3);
            a2 = this.b.a(point, (Point) this.a.get(i3));
            b4.getInfo().a(a2);
            b4.getIcon().a(i3, a2);
            this.b.a(i3, b4);
        }
        postInvalidate();
    }

    private void b(Canvas canvas) {
        if (this.a != null) {
            Point point;
            int b = this.b.b();
            Path path = new Path();
            for (int i = 1; i < b; i++) {
                point = (Point) this.a.get(i);
                if (i == 1) {
                    path.moveTo((float) point.x, (float) point.y);
                } else {
                    path.lineTo((float) point.x, (float) point.y);
                }
            }
            canvas.drawPath(path, this.d);
            if (b > 1) {
                point = (Point) this.a.get(1);
                Point l = this.b.l();
                if (l != null) {
                    canvas.drawLine(((float) point.x) * 1.0f, ((float) point.y) * 1.0f, ((float) l.x) * 1.0f, ((float) l.y) * 1.0f, this.c);
                }
            }
            int i2 = 0;
            while (i2 < b) {
                if (!this.b.t() || i2 != b - 1) {
                    float width;
                    float height;
                    Bitmap bitmap;
                    dji.gs.e.c b2 = this.b.b(i2);
                    point = (Point) this.a.get(i2);
                    float[] b3 = b2.getIcon().b();
                    Bitmap e;
                    Bitmap bitmap2;
                    if (i2 == this.e) {
                        e = b2.getIcon().e();
                        width = ((float) point.x) - (b3[0] * ((float) e.getWidth()));
                        bitmap2 = e;
                        height = (float) ((point.y - e.getHeight()) + this.j);
                        bitmap = bitmap2;
                    } else if (i2 == 0) {
                        e = this.f;
                        width = ((float) point.x) - (((float) e.getWidth()) * e.h[0]);
                        bitmap2 = e;
                        height = ((float) point.y) - (((float) e.getHeight()) * e.h[1]);
                        bitmap = bitmap2;
                    } else {
                        e = b2.getIcon().d();
                        width = ((float) point.x) - (((float) e.getWidth()) * b3[0]);
                        bitmap2 = e;
                        height = ((float) point.y) - (b3[1] * ((float) e.getHeight()));
                        bitmap = bitmap2;
                    }
                    canvas.drawBitmap(bitmap, width, height, null);
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    private void getOffset() {
        dji.gs.e.c b = this.b.b(this.e);
        b.getIcon().e(true);
        float height = (float) b.getIcon().e().getHeight();
        this.j = Math.round((1.0f - b.getIcon().b()[1]) * height);
        this.b.a(this.e, b);
    }

    public void onDestroy() {
        dji.thirdparty.a.c.a().d(this);
        this.b = null;
    }
}
