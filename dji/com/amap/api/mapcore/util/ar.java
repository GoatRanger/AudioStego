package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

class ar extends View {
    private String a = "";
    private int b = 0;
    private IAMapDelegate c;
    private Paint d;
    private Paint e;
    private Rect f;
    private final int[] g = new int[]{10000000, 5000000, 2000000, 1000000, 500000, 200000, 100000, 50000, 30000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 5};

    public void a() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.a = null;
    }

    public ar(Context context) {
        super(context);
    }

    public ar(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.c = iAMapDelegate;
        this.d = new Paint();
        this.f = new Rect();
        this.d.setAntiAlias(true);
        this.d.setColor(-16777216);
        this.d.setStrokeWidth(2.0f * r.a);
        this.d.setStyle(Style.STROKE);
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setColor(-16777216);
        this.e.setTextSize(20.0f * r.a);
    }

    protected void onDraw(Canvas canvas) {
        if (this.a != null && !this.a.equals("") && this.b != 0) {
            Point waterMarkerPositon = this.c.getWaterMarkerPositon();
            if (waterMarkerPositon != null) {
                this.e.getTextBounds(this.a, 0, this.a.length(), this.f);
                int i = waterMarkerPositon.x;
                int height = (waterMarkerPositon.y - this.f.height()) + 5;
                canvas.drawText(this.a, (float) i, (float) height, this.e);
                int height2 = height + (this.f.height() - 5);
                canvas.drawLine((float) i, (float) (height2 - 2), (float) i, (float) (height2 + 2), this.d);
                canvas.drawLine((float) i, (float) height2, (float) (this.b + i), (float) height2, this.d);
                canvas.drawLine((float) (this.b + i), (float) (height2 - 2), (float) (this.b + i), (float) (height2 + 2), this.d);
            }
        }
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            b();
            return;
        }
        a("");
        a(0);
        setVisibility(8);
    }

    void b() {
        if (this.c != null) {
            try {
                CameraPosition cameraPosition = this.c.getCameraPosition();
                if (cameraPosition != null) {
                    LatLng latLng = cameraPosition.target;
                    float zoomLevel = this.c.getZoomLevel();
                    double cos = (double) ((float) ((((Math.cos((latLng.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (256.0d * Math.pow(2.0d, (double) zoomLevel))));
                    int mapZoomScale = (int) (((double) this.g[(int) zoomLevel]) / (((double) this.c.getMapZoomScale()) * cos));
                    String b = dj.b(this.g[(int) zoomLevel]);
                    a(mapZoomScale);
                    a(b);
                    invalidate();
                }
            } catch (Throwable th) {
                ee.a(th, "AMapDelegateImpGLSurfaceView", "changeScaleState");
                th.printStackTrace();
            }
        }
    }
}
