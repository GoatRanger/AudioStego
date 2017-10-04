package com.nokia.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.here.android.mpa.ar.ARController;
import com.here.android.mpa.common.CopyrightLogoPosition;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map$OnSchemeChangedListener;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapMarker$OnDragListener;
import com.here.android.mpa.mapping.MapScreenMarker;
import com.here.android.mpa.mapping.MapView;
import com.here.android.mpa.mapping.OnMapRenderListener;
import java.util.Hashtable;

@TargetApi(14)
public class aj extends ViewGroup {
    private static final String c = aj.class.getSimpleName();
    private static final String d = MapView.class.getSimpleName();
    private boolean A = false;
    private dx B = null;
    private TextView C = null;
    private OnEngineInitListener D = new OnEngineInitListener(this) {
        final /* synthetic */ aj a;

        {
            this.a = r1;
        }

        public void onEngineInitializationCompleted(Error error) {
            if (this.a.f != null) {
                this.a.f.a(error);
            }
        }
    };
    private Runnable E = new Runnable(this) {
        final /* synthetic */ aj a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.i != null) {
                this.a.i.setVisibility(8);
                this.a.removeView(this.a.i);
                this.a.i = null;
                if (!(this.a.s || this.a.j == null)) {
                    this.a.removeView(this.a.j);
                    this.a.j = null;
                }
                this.a.m = false;
            }
        }
    };
    private final OnMapRenderListener F = new OnMapRenderListener(this) {
        final /* synthetic */ aj a;

        {
            this.a = r1;
        }

        public void onPreDraw() {
        }

        public void onPostDraw(boolean z, long j) {
        }

        public void onSizeChanged(int i, int i2) {
            this.a.h();
        }

        public void onGraphicsDetached() {
        }

        public void onRenderBufferCreated() {
        }
    };
    private final Map$OnSchemeChangedListener G = new Map$OnSchemeChangedListener(this) {
        final /* synthetic */ aj a;

        {
            this.a = r1;
        }

        public void onMapSchemeChanged(String str) {
            this.a.g();
        }
    };
    protected MapScreenMarker a;
    protected boolean b = true;
    private bw e = null;
    private ai f = null;
    private Context g;
    private AttributeSet h = null;
    private ImageView i = null;
    private ImageView j = null;
    private cg k = null;
    private MapMarker$OnDragListener l = null;
    private boolean m = false;
    private boolean n = false;
    private Handler o = null;
    private MapMarker p = null;
    private GeoCoordinate q = null;
    private boolean r = false;
    private boolean s = false;
    private String t = null;
    private Hashtable<String, Image> u = null;
    private CopyrightLogoPosition v = CopyrightLogoPosition.BOTTOM_CENTER;
    private Rect w = null;
    private int x = -1;
    private int y = -1;
    private boolean z = false;

    class a implements Runnable {
        final /* synthetic */ aj a;

        a(aj ajVar) {
            this.a = ajVar;
        }

        public void run() {
            this.a.z = true;
            this.a.requestLayout();
        }
    }

    public aj(Context context) {
        super(context);
        bj.e(c, "CompositeView create", new Object[0]);
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        bj.e(h.a, "---------------> CREATE CompositeView", new Object[0]);
        this.h = attributeSet;
        this.g = context.getApplicationContext();
        MapEngine.getInstance().init(this.g, this.D);
        if (attributeSet == null) {
            this.f = new ai(context);
        } else {
            this.f = new ai(context, attributeSet);
        }
        addView(this.f, -1);
        this.e = this.f.getProxy();
        f();
        setSaveEnabled(true);
        this.B = new dx();
        this.f.a(this.B);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            this.w = null;
        }
        if (this.f != null) {
            this.f.layout(0, 0, i3 - i, i4 - i2);
        }
        if (this.C != null) {
            this.C.layout(0, 0, i3 - i, i4 - i2);
        }
        if (getMap() == null) {
            return;
        }
        if (!this.A) {
            b(getMap());
        } else if (this.z) {
            this.z = false;
            g();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        if (this.e != null) {
            bundle.putParcelable("m_textureView", this.e.f());
        }
        bundle.putInt("CopyrightLogoPosition", this.v.ordinal());
        bundle.putBoolean("CopyrightLogoVisibility", this.b);
        if (!(this.i == null || !this.n || this.p == null)) {
            this.n = false;
            if (this.e != null) {
                this.e.g();
            }
            if (this.q != null) {
                this.p.setCoordinate(this.q);
                this.q = null;
            }
            this.p.setVisible(true);
            if (this.r) {
                this.p.showInfoBubble();
                this.r = false;
            }
            this.p = null;
        }
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            if (!(this.e == null || bundle.getParcelable("m_textureView") == null)) {
                this.e.a(bundle.getParcelable("m_textureView"));
            }
            this.v = CopyrightLogoPosition.values()[bundle.getInt("CopyrightLogoPosition")];
            setCopyrightLogoVisibility(bundle.getBoolean("CopyrightLogoVisibility", true) ? 0 : 4);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    public void setMap(Map map) {
        e();
        if (this.e.b() != null) {
            this.e.b().removeSchemeChangedListener(this.G);
        }
        a(this.e.b());
        if (map == null) {
            b(this.F);
        } else {
            a(this.F);
        }
        try {
            bj.a(d, "Map::setMap attempt", new Object[0]);
            this.e.a(map);
            bj.a(d, "Map::setMap success", new Object[0]);
            if (this.e.b() != null) {
                this.e.b().addSchemeChangedListener(this.G);
            }
            f();
        } catch (Exception e) {
            bj.e(c, "FAILED: %s", new Object[]{e.getLocalizedMessage()});
        }
        d();
    }

    private void d() {
        this.C = new fb(this.g).a();
        if (this.C != null) {
            addView(this.C);
        }
    }

    private void e() {
        if (this.e != null && this.k != null) {
            this.e.b(this.k);
        }
    }

    private void f() {
        if (this.e != null && getMapGesture() != null) {
            if (this.k != null) {
                this.e.a(this.k);
                return;
            }
            this.k = new cg(this) {
                final /* synthetic */ aj a;
                private int b = 0;
                private int c = 0;
                private int d = 0;
                private int e = 0;
                private int f = 0;

                {
                    this.a = r2;
                }

                @SuppressLint({"NewApi"})
                public void a(MapMarker mapMarker) {
                    a();
                    if (this.a.i == null) {
                        this.a.i = new ImageView(this.a.g);
                        this.a.i.setId(this.a.i.hashCode());
                        if (!this.a.s && this.a.j == null) {
                            this.a.j = new ImageView(this.a.g);
                            this.a.j.setId(this.a.j.hashCode());
                        }
                    }
                    Bitmap a = this.a.e.a(mapMarker);
                    PointF result = this.a.getMap().projectToPixel(mapMarker.getCoordinate()).getResult();
                    if (!(a == null || this.a.i == null || result == null)) {
                        Bitmap createBitmap;
                        float transparency;
                        this.b = a.getWidth();
                        this.c = a.getHeight();
                        this.a.n = true;
                        this.a.q = new GeoCoordinate(mapMarker.getCoordinate());
                        if (mapMarker.isInfoBubbleVisible()) {
                            Bitmap h = this.a.e.h();
                            if (h != null) {
                                this.f = this.b;
                                this.d = h.getWidth();
                                this.e = h.getHeight();
                                this.b = Math.max(this.d, this.b);
                                this.c += this.e;
                                createBitmap = Bitmap.createBitmap(this.b, this.c, Config.ARGB_8888);
                                Canvas canvas = new Canvas(createBitmap);
                                Paint paint = new Paint();
                                canvas.drawBitmap(h, (((float) this.b) - ((float) this.d)) / 2.0f, 0.0f, paint);
                                canvas.drawBitmap(a, (((float) this.b) / 2.0f) - (((float) this.f) / 2.0f), (float) this.e, paint);
                                mapMarker.hideInfoBubble();
                                this.a.r = true;
                                this.a.i.setAdjustViewBounds(true);
                                this.a.i.setImageBitmap(createBitmap);
                                this.a.i.setMaxWidth(this.b);
                                this.a.i.setMaxHeight(this.c);
                                transparency = mapMarker.getTransparency();
                                if (transparency != 1.0f) {
                                    if (VERSION.SDK_INT < 16) {
                                        this.a.i.setImageAlpha((int) (transparency * 255.0f));
                                    } else {
                                        this.a.i.setAlpha((int) (transparency * 255.0f));
                                    }
                                }
                                if (!this.a.s) {
                                    this.a.j.setAdjustViewBounds(true);
                                    this.a.j.setMaxWidth(this.b + 200);
                                    this.a.j.setMaxHeight(this.c + 200);
                                }
                                a(result, this.b, this.c);
                                if (!this.a.m) {
                                    if (!this.a.s) {
                                        this.a.addView(this.a.j, -2);
                                    }
                                    this.a.addView(this.a.i, -2);
                                    this.a.m = true;
                                }
                                this.a.i.setVisibility(0);
                                if (!this.a.s) {
                                    this.a.j.setVisibility(0);
                                }
                                this.a.bringChildToFront(this.a.i);
                                mapMarker.setVisible(false);
                                c(mapMarker, result);
                                this.a.p = mapMarker;
                            }
                        }
                        createBitmap = a;
                        this.a.i.setAdjustViewBounds(true);
                        this.a.i.setImageBitmap(createBitmap);
                        this.a.i.setMaxWidth(this.b);
                        this.a.i.setMaxHeight(this.c);
                        transparency = mapMarker.getTransparency();
                        if (transparency != 1.0f) {
                            if (VERSION.SDK_INT < 16) {
                                this.a.i.setAlpha((int) (transparency * 255.0f));
                            } else {
                                this.a.i.setImageAlpha((int) (transparency * 255.0f));
                            }
                        }
                        if (this.a.s) {
                            this.a.j.setAdjustViewBounds(true);
                            this.a.j.setMaxWidth(this.b + 200);
                            this.a.j.setMaxHeight(this.c + 200);
                        }
                        a(result, this.b, this.c);
                        if (this.a.m) {
                            if (this.a.s) {
                                this.a.addView(this.a.j, -2);
                            }
                            this.a.addView(this.a.i, -2);
                            this.a.m = true;
                        }
                        this.a.i.setVisibility(0);
                        if (this.a.s) {
                            this.a.j.setVisibility(0);
                        }
                        this.a.bringChildToFront(this.a.i);
                        mapMarker.setVisible(false);
                        c(mapMarker, result);
                        this.a.p = mapMarker;
                    }
                    if (this.a.l != null) {
                        this.a.l.onMarkerDragStart(mapMarker);
                    }
                }

                public void a(MapMarker mapMarker, PointF pointF) {
                    if (this.a.n) {
                        this.a.n = false;
                        if (c(mapMarker, pointF)) {
                            a(pointF, this.b, this.c);
                        }
                        mapMarker.setVisible(true);
                        if (this.a.r) {
                            mapMarker.showInfoBubble();
                            this.a.r = false;
                        }
                        if (this.a.m) {
                            if (this.a.o == null) {
                                this.a.o = new Handler();
                            }
                            this.a.o.postDelayed(this.a.E, 150);
                        }
                        a();
                    }
                    if (this.a.l != null) {
                        this.a.l.onMarkerDragEnd(mapMarker);
                    }
                }

                public void b(MapMarker mapMarker, PointF pointF) {
                    if (this.a.n && c(mapMarker, pointF)) {
                        a(pointF, this.b, this.c);
                    }
                    if (this.a.l != null) {
                        this.a.l.onMarkerDrag(mapMarker);
                    }
                }

                private void a() {
                    this.b = 0;
                    this.c = 0;
                    this.d = 0;
                    this.e = 0;
                    this.a.p = null;
                    this.a.q = null;
                }

                private void a(PointF pointF, int i, int i2) {
                    int i3 = ((int) pointF.x) - (i / 2);
                    int i4 = (((int) pointF.y) - ((i2 / 2) * 2)) - 80;
                    if (this.a.i != null) {
                        this.a.i.layout(i3, i4, i3 + i, i4 + i2);
                        if (!this.a.s) {
                            this.a.j.layout(i3 - 100, i4 - 100, (i3 + i) + 100, (i4 + i2) + 100);
                            this.a.i.requestLayout();
                        }
                    }
                }

                private boolean c(MapMarker mapMarker, PointF pointF) {
                    int i;
                    int i2;
                    int i3 = this.f == 0 ? this.b / 2 : this.f / 2;
                    int i4 = (this.c - this.e) / 2;
                    PointF anchorPoint = mapMarker.getAnchorPoint();
                    if (anchorPoint != null) {
                        i = (int) anchorPoint.x;
                    } else {
                        i = i3;
                    }
                    if (anchorPoint != null) {
                        i2 = (int) anchorPoint.y;
                    } else {
                        i2 = i4;
                    }
                    Map b = this.a.e.b();
                    try {
                        GeoCoordinate pixelToGeo = b.pixelToGeo(new PointF((float) Math.min(b.getWidth(), Math.max(0, (i + ((int) pointF.x)) - i3)), (float) Math.min(b.getHeight(), Math.max(0, (i2 + ((((int) pointF.y) - i4) - 80)) - i4))));
                        if (pixelToGeo != null && pixelToGeo.isValid()) {
                            mapMarker.setCoordinate(pixelToGeo);
                            return true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            };
            this.e.a(this.k);
        }
    }

    public void a() {
        if (this.e != null) {
            this.e.b(this.k);
            if (this.e.b() != null) {
                this.e.b().removeSchemeChangedListener(this.G);
            }
            this.e.d();
        }
        if (this.f == null || this.f.getARRenderer() == null) {
            this.B.a.a(this, null);
            return;
        }
        synchronized (this.f.getARRenderer()) {
            this.B.a.a(this, null);
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.e();
            this.e.a(this.k);
            if (this.e.b() != null) {
                this.e.b().addSchemeChangedListener(this.G);
            }
        }
        if (this.f == null || this.f.getARRenderer() == null) {
            this.B.b.a(this, null);
            return;
        }
        synchronized (this.f.getARRenderer()) {
            this.B.b.a(this, null);
        }
    }

    public void c() {
        Log.d(h.a, "---------------> DESTROY CompositeView");
        setOnTouchListener(null);
        if (this.f == null || this.f.getARRenderer() == null) {
            this.B.c.a(this, null);
        } else {
            synchronized (this.f.getARRenderer()) {
                this.B.c.a(this, null);
            }
        }
        this.f.b();
        this.f = null;
        this.B = null;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        if (this.e != null && this.f != null) {
            this.f.setOnTouchListener(onTouchListener);
        }
    }

    private Image a(String str) {
        Image image;
        if (this.u != null) {
            image = (Image) this.u.get(str);
        } else {
            this.u = new Hashtable();
            image = null;
        }
        if (image != null) {
            return image;
        }
        byte[] a = ResourceManager.a(this.g, str);
        if (a == null || a.length <= 0) {
            return image;
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(a, 0, a.length);
        if (decodeByteArray == null) {
            return image;
        }
        image = new Image();
        if (!image.setBitmap(decodeByteArray)) {
            return null;
        }
        this.u.put(str, image);
        return image;
    }

    private PointF a(Map map, Image image) {
        int i;
        int i2;
        ViewRect viewRect = new ViewRect(0, 0, map.getWidth(), map.getHeight());
        if (getCopyrightMargin() < 0) {
            this.y = this.x;
        }
        Object obj = null;
        ViewRect b = b(map, image);
        if (b != null) {
            obj = 1;
        } else if (viewRect == null || !viewRect.isValid()) {
            b = new ViewRect(0, 0, map.getWidth(), map.getHeight());
            if (!b.isValid()) {
                return null;
            }
        } else {
            b = new ViewRect(viewRect.getX(), viewRect.getY(), viewRect.getWidth(), viewRect.getHeight());
            ViewRect a = this.e.a();
            if (!(a == null || !a.isValid() || a.equals(viewRect))) {
                if (a.getX() != viewRect.getX()) {
                    b.setX(Math.max(a.getX(), viewRect.getX()));
                    b.setWidth(Math.min(a.getX() + a.getWidth(), viewRect.getX() + viewRect.getWidth()) - b.getX());
                } else if (a.getWidth() < viewRect.getWidth()) {
                    b.setWidth(a.getWidth());
                }
                if (a.getY() != viewRect.getY()) {
                    b.setY(Math.max(a.getY(), viewRect.getY()));
                    b.setHeight(Math.min(a.getHeight() + a.getY(), viewRect.getY() + viewRect.getHeight()) - b.getY());
                } else if (a.getHeight() < viewRect.getHeight()) {
                    b.setHeight(a.getHeight());
                }
            }
        }
        int width = b.getWidth();
        int height = b.getHeight();
        int height2 = (int) image.getHeight();
        int width2 = (int) image.getWidth();
        if (obj == null) {
            double d = ((double) width2) / ((double) width);
            if (((double) height2) / ((double) height) > 0.4d || d > 0.4d) {
                return null;
            }
        }
        int a2 = a(width, height, width2, height2);
        int i3 = height - a2;
        int i4 = width - a2;
        switch (this.v) {
            case TOP_LEFT:
                i4 = a2 + width2;
                i3 = a2 + height2;
                i = a2;
                i2 = a2;
                break;
            case TOP_RIGHT:
                i = i4 - width2;
                i3 = a2 + height2;
                i2 = a2;
                break;
            case TOP_CENTER:
                i3 = a2 + height2;
                i = (width - width2) / 2;
                i4 = i + width2;
                i2 = a2;
                break;
            default:
                i = (width - width2) / 2;
                i4 = i + width2;
                i2 = i3 - height2;
                break;
        }
        if (i2 < a2) {
            i2 = a2 + height2;
            i3 = a2;
        } else {
            int i5 = i3;
            i3 = i2;
            i2 = i5;
        }
        if (i2 > height - a2) {
            i3 = (height - a2) - height2;
        }
        if (i < a2) {
            i = a2 + width2;
            i4 = a2;
        } else {
            i5 = i4;
            i4 = i;
            i = i5;
        }
        if (i > width - a2) {
            i4 = (width - a2) - width2;
        }
        PointF pointF = new PointF((float) i4, (float) i3);
        if (viewRect != null) {
            if (viewRect.getX() < b.getX()) {
                pointF.x += ((float) b.getX()) - ((float) viewRect.getX());
            } else {
                bj.b(d, "Map's visible area is not completely embedded within the view rect.", new Object[0]);
            }
            if (viewRect.getY() < b.getY()) {
                pointF.y = (((float) b.getY()) - ((float) viewRect.getY())) + pointF.y;
            } else {
                bj.b(d, "Map's visible area is not completely embedded within the view rect.", new Object[0]);
            }
        }
        return pointF;
    }

    private void a(Map map) {
        if (!(this.a == null || map == null)) {
            map.removeMapObject(this.a);
        }
        this.A = false;
    }

    private void b(Map map) {
        if (map != null && !this.A) {
            g();
            this.a.setZIndex(Integer.MAX_VALUE);
            MapImpl.get(map).a(this.a, false);
            this.A = true;
        }
    }

    private void g() {
        Map b = this.e.b();
        if (b != null) {
            String a = bl.a(b.getMapScheme(), this.g.getResources().getDisplayMetrics().densityDpi);
            PointF a2;
            if (this.a == null || this.t == null || this.t.compareTo(a) != 0) {
                Image a3 = a(a);
                if (a3 != null) {
                    this.x = (int) (a3.getWidth() >> 1);
                    a2 = a(b, a3);
                    if (a2 == null) {
                        a2 = new PointF((float) (a3.getWidth() * -1), (float) (a3.getHeight() * -1));
                    }
                    this.t = a;
                    if (this.a == null) {
                        this.a = new MapScreenMarker(a2, a3);
                    } else {
                        this.a.setIcon(a3);
                        this.a.setScreenCoordinate(a2);
                    }
                    this.a.setAnchorPoint(new PointF(0.0f, 0.0f));
                }
            } else {
                a2 = a(b, this.a.getIcon());
                PointF screenCoordinate = this.a.getScreenCoordinate();
                if (!(a2 == null || (a2.x == screenCoordinate.x && a2.y == screenCoordinate.y))) {
                    this.a.setScreenCoordinate(a2);
                }
            }
            if (this.a != null) {
                this.a.setVisible(this.b);
            }
        }
    }

    public final Map getMap() {
        return this.e.b();
    }

    public void a(ViewRect viewRect, PointF pointF) {
        if (!viewRect.isValid()) {
            throw new IllegalArgumentException("Input parameter rect is invalid");
        } else if (this.e != null && viewRect.isValid()) {
            this.e.a(viewRect, pointF);
            h();
        }
    }

    public void setClipRect(ViewRect viewRect) {
        if (viewRect.isValid()) {
            ViewRect clipRect = getClipRect();
            if (clipRect == null || !clipRect.isValid() || !clipRect.equals(viewRect)) {
                a(viewRect, null);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Input parameter rect is invalid");
    }

    public ViewRect getClipRect() {
        if (this.e != null) {
            return this.e.a();
        }
        return null;
    }

    public Rect getCopyrightBoundaryRect() {
        return this.w;
    }

    public void setCopyrightBoundaryRect(Rect rect) {
        Object obj = null;
        if (rect != null) {
            if (!(this.w != null && rect.left == this.w.left && rect.right == this.w.right && rect.top == this.w.top && rect.bottom == this.w.bottom)) {
                if (rect.isEmpty() || rect.right <= 0 || rect.bottom <= 0 || !new ViewRect(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top).isValid()) {
                    throw new IllegalArgumentException("Input parameter rect is invalid.");
                }
                this.w = new Rect(rect);
                obj = 1;
            }
        } else if (this.w != null) {
            this.w = null;
            int i = 1;
        }
        if (obj != null) {
            g();
        }
    }

    public void setCopyrightMargin(int i) {
        if (this.y != i) {
            int i2 = this.y;
            if (i >= this.x || this.x <= 0) {
                this.y = i;
            } else {
                this.y = this.x;
            }
            if (this.y != i2) {
                g();
            }
        }
    }

    public int getCopyrightMargin() {
        return this.y;
    }

    public int getCopyrightLogoWidth() {
        if (!(this.u == null || this.t == null)) {
            Image image = (Image) this.u.get(this.t);
            if (image != null) {
                return (int) image.getWidth();
            }
        }
        return -1;
    }

    public int getCopyrightLogoHeight() {
        if (!(this.u == null || this.t == null)) {
            Image image = (Image) this.u.get(this.t);
            if (image != null) {
                return (int) image.getHeight();
            }
        }
        return -1;
    }

    public void setCopyrightLogoPosition(CopyrightLogoPosition copyrightLogoPosition) {
        if (this.v != copyrightLogoPosition) {
            this.v = copyrightLogoPosition;
            g();
        }
    }

    public CopyrightLogoPosition getCopyrightLogoPosition() {
        return this.v;
    }

    public MapGesture getMapGesture() {
        if (this.e != null) {
            return this.e.c();
        }
        return null;
    }

    public void a(OnMapRenderListener onMapRenderListener) {
        if (this.e != null) {
            this.e.a(onMapRenderListener);
        }
    }

    public void b(OnMapRenderListener onMapRenderListener) {
        if (this.e != null) {
            this.e.b(onMapRenderListener);
        }
    }

    public void setMapMarkerDragListener(MapMarker$OnDragListener mapMarker$OnDragListener) {
        this.l = mapMarker$OnDragListener;
    }

    public int getCopyrightLogoVisibility() {
        return this.b ? 0 : 4;
    }

    private void h() {
        if (this.f != null) {
            post(new a(this));
        }
    }

    private ViewRect b(Map map, Image image) {
        if (this.w == null || image == null || image.getHeight() <= 0 || image.getWidth() <= 0) {
            return null;
        }
        Rect rect = new Rect(0, 0, map.getWidth(), map.getHeight());
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int a;
        if (rect.contains(this.w)) {
            a = a(this.w.width(), this.w.height(), width, height);
            if (this.w.width() < width + (a * 2) || this.w.height() < (a * 2) + height) {
                return null;
            }
            return new ViewRect(this.w.left, this.w.top, this.w.right - this.w.left, this.w.bottom - this.w.top);
        } else if (!rect.intersect(this.w)) {
            return null;
        } else {
            Rect rect2 = new Rect(Math.max(rect.left, this.w.left), Math.max(rect.top, this.w.top), Math.min(rect.right, this.w.right), Math.min(rect.bottom, this.w.bottom));
            a = a(rect2.width(), rect2.height(), width, height);
            if (rect2.width() < width + (a * 2) || rect2.height() < (a * 2) + height) {
                return null;
            }
            return new ViewRect(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
        }
    }

    private int a(int i, int i2, int i3, int i4) {
        int i5 = (i - i3 > i2 - i4 ? i2 - i4 : i - i3) >> 1;
        int i6 = this.y;
        if (i5 <= 0) {
            return i6;
        }
        if (this.y > i5) {
            return i5;
        }
        return this.y;
    }

    public void setCopyrightLogoVisibility(int i) {
        boolean z = i == 0;
        if (z != this.b) {
            this.b = z;
            if (this.a != null) {
                this.a.setVisible(z);
            }
        }
    }

    public ARController getARController() {
        return this.f != null ? this.f.getARController() : null;
    }
}
