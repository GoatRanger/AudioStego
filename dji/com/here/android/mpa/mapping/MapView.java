package com.here.android.mpa.mapping;

import android.annotation.SuppressLint;
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
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.here.android.mpa.common.CopyrightLogoPosition;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map.PixelResult.Error;
import com.nokia.maps.ResourceManager;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.bj;
import com.nokia.maps.bl;
import com.nokia.maps.bs;
import com.nokia.maps.bw;
import com.nokia.maps.bz;
import com.nokia.maps.cd;
import com.nokia.maps.ce;
import com.nokia.maps.cg;
import com.nokia.maps.fb;
import java.util.Hashtable;

@Online
public class MapView extends ViewGroup {
    private static final String a = MapView.class.getSimpleName();
    private ImageView b;
    private ImageView c;
    private cg d;
    private MapMarker$OnDragListener e;
    private boolean f;
    private boolean g;
    private Handler h;
    private MapMarker i;
    private GeoCoordinate j;
    private boolean k;
    private boolean l;
    private String m;
    protected AttributeSet m_attrs;
    protected MapScreenMarker m_copyrightLogoMarker;
    protected boolean m_copyrightLogoVisible;
    protected Context m_ctx;
    protected View m_viewImpl;
    protected bw m_viewProxy;
    private Hashtable<String, Image> n;
    private CopyrightLogoPosition o;
    private Rect p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private TextView u;
    private bs v;
    private Runnable w;
    private final OnMapRenderListener x;
    private final Map$OnSchemeChangedListener y;

    class a implements Runnable {
        final /* synthetic */ MapView a;

        a(MapView mapView) {
            this.a = mapView;
        }

        public void run() {
            this.a.s = true;
            this.a.requestLayout();
        }
    }

    public MapView(Context context) {
        this(context, null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m_viewProxy = null;
        this.m_viewImpl = null;
        this.m_attrs = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = false;
        this.l = false;
        this.m_copyrightLogoVisible = true;
        this.m = null;
        this.n = null;
        this.o = CopyrightLogoPosition.BOTTOM_CENTER;
        this.p = null;
        this.q = -1;
        this.r = -1;
        this.s = false;
        this.t = false;
        this.u = null;
        this.w = new Runnable(this) {
            final /* synthetic */ MapView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.b != null) {
                    this.a.b.setVisibility(8);
                    this.a.removeView(this.a.b);
                    this.a.b = null;
                    if (!(this.a.l || this.a.c == null)) {
                        this.a.removeView(this.a.c);
                        this.a.c = null;
                    }
                    this.a.f = false;
                }
            }
        };
        this.x = new OnMapRenderListener(this) {
            final /* synthetic */ MapView a;

            {
                this.a = r1;
            }

            public void onPreDraw() {
            }

            public void onPostDraw(boolean z, long j) {
            }

            public void onSizeChanged(int i, int i2) {
                this.a.e();
            }

            public void onGraphicsDetached() {
            }

            public void onRenderBufferCreated() {
            }
        };
        this.y = new Map$OnSchemeChangedListener(this) {
            final /* synthetic */ MapView a;

            {
                this.a = r1;
            }

            public void onMapSchemeChanged(String str) {
                this.a.d();
            }
        };
        this.m_attrs = attributeSet;
        this.m_ctx = context.getApplicationContext();
        a(this.m_ctx, this.m_attrs);
        this.v = new bs(this.m_ctx);
        this.v.setLayoutParams(new MarginLayoutParams(-1, -1));
        addView(this.v);
        setSaveEnabled(true);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        measureChildWithMargins(this.v, i, 0, i2, 0);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            this.p = null;
        }
        this.m_viewImpl.layout(0, 0, i3 - i, i4 - i2);
        if (this.u != null) {
            this.u.layout(0, 0, i3 - i, i4 - i2);
        }
        this.v.layout(i, i2, i3, i4);
        if (getMap() == null) {
            return;
        }
        if (!this.t) {
            b(getMap());
        } else if (this.s) {
            this.s = false;
            d();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        if (this.m_viewProxy != null) {
            bundle.putParcelable("m_viewImpl", this.m_viewProxy.f());
        }
        bundle.putInt("CopyrightLogoPosition", this.o.ordinal());
        if (!(this.b == null || !this.g || this.i == null)) {
            this.g = false;
            if (this.m_viewProxy != null) {
                this.m_viewProxy.g();
            }
            if (this.j != null) {
                this.i.setCoordinate(this.j);
                this.j = null;
            }
            this.i.setVisible(true);
            if (this.k) {
                this.i.showInfoBubble();
                this.k = false;
            }
            this.i = null;
        }
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            if (!(this.m_viewProxy == null || bundle.getParcelable("m_viewImpl") == null)) {
                this.m_viewProxy.a(bundle.getParcelable("m_viewImpl"));
            }
            this.o = CopyrightLogoPosition.values()[bundle.getInt("CopyrightLogoPosition")];
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

    private void a(Context context, AttributeSet attributeSet) {
        if (this.m_viewImpl == null) {
            String str = (String) getTag();
            if (str == null) {
                str = "";
            }
            this.l = true;
            if (VERSION.SDK_INT < 14) {
                this.l = false;
            } else if (str.compareTo("SurfaceView") == 0) {
                this.l = false;
            }
            View bzVar;
            if (this.l) {
                if (attributeSet == null) {
                    bzVar = new bz(context);
                } else {
                    bzVar = new bz(context, attributeSet);
                }
                this.m_viewProxy = bzVar.getProxy();
                this.m_viewImpl = bzVar;
            } else {
                if (attributeSet == null) {
                    bzVar = new cd(context);
                } else {
                    bzVar = new cd(context, attributeSet);
                }
                this.m_viewProxy = bzVar.getProxy();
                this.m_viewImpl = bzVar;
            }
            addView(this.m_viewImpl, -1);
        }
    }

    public void setMap(Map map) {
        b();
        if (this.m_viewProxy.b() != null) {
            this.m_viewProxy.b().removeSchemeChangedListener(this.y);
            this.v.a();
            removeOnMapRenderListener(this.v.getMapRenderListener());
        }
        a(this.m_viewProxy.b());
        if (map == null) {
            removeOnMapRenderListener(this.x);
        } else {
            addOnMapRenderListener(this.x);
        }
        try {
            bj.a(a, "Map::setMap attempt", new Object[0]);
            this.m_viewProxy.a(map);
            bj.a(a, "Map::setMap success", new Object[0]);
            if (this.m_viewProxy.b() != null) {
                this.m_viewProxy.b().addSchemeChangedListener(this.y);
                addOnMapRenderListener(this.v.getMapRenderListener());
                this.v.a(this.m_viewProxy.b());
            }
            c();
        } catch (Exception e) {
            bj.c(a, "FAILED: %s", new Object[]{e.getLocalizedMessage()});
        }
        a();
    }

    private void a() {
        this.u = new fb(this.m_ctx).a();
        if (this.u != null) {
            addView(this.u);
        }
    }

    private void b() {
        if (this.m_viewProxy != null && this.d != null) {
            this.m_viewProxy.b(this.d);
        }
    }

    @SuppressLint({"NewApi"})
    private void c() {
        if (this.m_viewProxy != null && getMapGesture() != null) {
            if (this.d == null) {
                this.d = new cg(this) {
                    final /* synthetic */ MapView a;
                    private int b = 0;
                    private int c = 0;
                    private int d = 0;
                    private int e = 0;
                    private int f = 0;

                    {
                        this.a = r2;
                    }

                    public void a(MapMarker mapMarker) {
                        a();
                        if (this.a.b == null) {
                            this.a.b = new ImageView(this.a.m_ctx);
                            this.a.b.setId(ce.a());
                            if (!this.a.l && this.a.c == null) {
                                this.a.c = new ImageView(this.a.m_ctx);
                                this.a.c.setId(ce.a());
                            }
                        }
                        Bitmap a = this.a.m_viewProxy.a(mapMarker);
                        Map$PixelResult projectToPixel = this.a.getMap().projectToPixel(mapMarker.getCoordinate());
                        if (!(projectToPixel.getError() != Error.NONE || a == null || this.a.b == null)) {
                            Bitmap createBitmap;
                            float transparency;
                            PointF result = projectToPixel.getResult();
                            this.b = a.getWidth();
                            this.c = a.getHeight();
                            this.a.g = true;
                            this.a.j = new GeoCoordinate(mapMarker.getCoordinate());
                            if (mapMarker.isInfoBubbleVisible()) {
                                Bitmap h = this.a.m_viewProxy.h();
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
                                    this.a.k = true;
                                    this.a.b.setAdjustViewBounds(true);
                                    this.a.b.setImageBitmap(createBitmap);
                                    this.a.b.setMaxWidth(this.b);
                                    this.a.b.setMaxHeight(this.c);
                                    transparency = mapMarker.getTransparency();
                                    if (transparency != 1.0f) {
                                        if (VERSION.SDK_INT < 16) {
                                            this.a.b.setImageAlpha((int) (transparency * 255.0f));
                                        } else {
                                            this.a.b.setAlpha((int) (transparency * 255.0f));
                                        }
                                    }
                                    if (!this.a.l) {
                                        this.a.c.setAdjustViewBounds(true);
                                        this.a.c.setMaxWidth(this.b + 200);
                                        this.a.c.setMaxHeight(this.c + 200);
                                    }
                                    a(result, this.b, this.c);
                                    if (!this.a.f) {
                                        if (!this.a.l) {
                                            this.a.addView(this.a.c, -2);
                                        }
                                        this.a.addView(this.a.b, -2);
                                        this.a.f = true;
                                    }
                                    this.a.b.setVisibility(0);
                                    if (!this.a.l) {
                                        this.a.c.setVisibility(0);
                                    }
                                    this.a.bringChildToFront(this.a.b);
                                    mapMarker.setVisible(false);
                                    c(mapMarker, result);
                                    this.a.i = mapMarker;
                                }
                            }
                            createBitmap = a;
                            this.a.b.setAdjustViewBounds(true);
                            this.a.b.setImageBitmap(createBitmap);
                            this.a.b.setMaxWidth(this.b);
                            this.a.b.setMaxHeight(this.c);
                            transparency = mapMarker.getTransparency();
                            if (transparency != 1.0f) {
                                if (VERSION.SDK_INT < 16) {
                                    this.a.b.setAlpha((int) (transparency * 255.0f));
                                } else {
                                    this.a.b.setImageAlpha((int) (transparency * 255.0f));
                                }
                            }
                            if (this.a.l) {
                                this.a.c.setAdjustViewBounds(true);
                                this.a.c.setMaxWidth(this.b + 200);
                                this.a.c.setMaxHeight(this.c + 200);
                            }
                            a(result, this.b, this.c);
                            if (this.a.f) {
                                if (this.a.l) {
                                    this.a.addView(this.a.c, -2);
                                }
                                this.a.addView(this.a.b, -2);
                                this.a.f = true;
                            }
                            this.a.b.setVisibility(0);
                            if (this.a.l) {
                                this.a.c.setVisibility(0);
                            }
                            this.a.bringChildToFront(this.a.b);
                            mapMarker.setVisible(false);
                            c(mapMarker, result);
                            this.a.i = mapMarker;
                        }
                        if (this.a.e != null) {
                            this.a.e.onMarkerDragStart(mapMarker);
                        }
                    }

                    public void a(MapMarker mapMarker, PointF pointF) {
                        if (this.a.g) {
                            this.a.g = false;
                            if (c(mapMarker, pointF)) {
                                a(pointF, this.b, this.c);
                            }
                            mapMarker.setVisible(true);
                            if (this.a.k) {
                                mapMarker.showInfoBubble();
                                this.a.k = false;
                            }
                            if (this.a.f) {
                                if (this.a.h == null) {
                                    this.a.h = new Handler();
                                }
                                this.a.h.postDelayed(this.a.w, 150);
                            }
                            a();
                        }
                        if (this.a.e != null) {
                            this.a.e.onMarkerDragEnd(mapMarker);
                        }
                    }

                    public void b(MapMarker mapMarker, PointF pointF) {
                        if (this.a.g && c(mapMarker, pointF)) {
                            a(pointF, this.b, this.c);
                        }
                        if (this.a.e != null) {
                            this.a.e.onMarkerDrag(mapMarker);
                        }
                    }

                    private void a() {
                        this.b = 0;
                        this.c = 0;
                        this.d = 0;
                        this.e = 0;
                        this.a.i = null;
                        this.a.j = null;
                    }

                    private void a(PointF pointF, int i, int i2) {
                        int i3 = ((int) pointF.x) - (i / 2);
                        int i4 = (((int) pointF.y) - ((i2 / 2) * 2)) - 80;
                        if (this.a.b != null) {
                            this.a.b.layout(i3, i4, i3 + i, i4 + i2);
                            if (!this.a.l) {
                                this.a.c.layout(i3 - 100, i4 - 100, (i3 + i) + 100, (i4 + i2) + 100);
                                this.a.b.requestLayout();
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
                        Map b = this.a.m_viewProxy.b();
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
            }
            this.m_viewProxy.a(this.d);
        }
    }

    public MapGesture getMapGesture() {
        if (this.m_viewProxy != null) {
            return this.m_viewProxy.c();
        }
        return null;
    }

    public void addOnMapRenderListener(OnMapRenderListener onMapRenderListener) {
        if (this.m_viewProxy != null) {
            this.m_viewProxy.a(onMapRenderListener);
        }
    }

    public void removeOnMapRenderListener(OnMapRenderListener onMapRenderListener) {
        if (this.m_viewProxy != null) {
            this.m_viewProxy.b(onMapRenderListener);
        }
    }

    public void onPause() {
        if (this.m_viewProxy != null) {
            this.m_viewProxy.b(this.d);
            if (this.m_viewProxy.b() != null) {
                this.m_viewProxy.b().removeSchemeChangedListener(this.y);
            }
            this.m_viewProxy.d();
        }
    }

    public void onResume() {
        if (this.m_viewProxy != null) {
            this.m_viewProxy.e();
            this.m_viewProxy.a(this.d);
            if (this.m_viewProxy.b() != null) {
                this.m_viewProxy.b().addSchemeChangedListener(this.y);
            }
        }
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        if (this.m_viewProxy != null) {
            this.m_viewImpl.setOnTouchListener(onTouchListener);
        }
    }

    public void setMapMarkerDragListener(MapMarker$OnDragListener mapMarker$OnDragListener) {
        this.e = mapMarker$OnDragListener;
    }

    private Image a(String str) {
        Image image;
        if (this.n != null) {
            image = (Image) this.n.get(str);
        } else {
            this.n = new Hashtable();
            image = null;
        }
        if (image != null) {
            return image;
        }
        byte[] a = ResourceManager.a(this.m_ctx, str);
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
        this.n.put(str, image);
        return image;
    }

    private PointF a(Map map, Image image) {
        int i;
        int i2;
        ViewRect viewRect = new ViewRect(0, 0, map.getWidth(), map.getHeight());
        if (getCopyrightMargin() < 0) {
            this.r = this.q;
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
            ViewRect a = this.m_viewProxy.a();
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
        switch (this.o) {
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
                bj.b(a, "Map's visible area is not completely embedded within the view rect.", new Object[0]);
            }
            if (viewRect.getY() < b.getY()) {
                pointF.y = (((float) b.getY()) - ((float) viewRect.getY())) + pointF.y;
            } else {
                bj.b(a, "Map's visible area is not completely embedded within the view rect.", new Object[0]);
            }
        }
        return pointF;
    }

    private void a(Map map) {
        if (!(this.m_copyrightLogoMarker == null || map == null)) {
            map.removeMapObject(this.m_copyrightLogoMarker);
        }
        this.t = false;
    }

    private void b(Map map) {
        if (map != null && !this.t) {
            d();
            this.m_copyrightLogoMarker.setZIndex(Integer.MAX_VALUE);
            map.a(this.m_copyrightLogoMarker, false);
            this.t = true;
        }
    }

    private void d() {
        Map b = this.m_viewProxy.b();
        if (b != null) {
            String a = bl.a(b.getMapScheme(), this.m_ctx.getResources().getDisplayMetrics().densityDpi);
            PointF a2;
            if (this.m_copyrightLogoMarker == null || this.m == null || this.m.compareTo(a) != 0) {
                Image a3 = a(a);
                if (a3 != null) {
                    this.q = (int) (a3.getWidth() >> 1);
                    a2 = a(b, a3);
                    if (a2 == null) {
                        a2 = new PointF((float) (a3.getWidth() * -1), (float) (a3.getHeight() * -1));
                    }
                    this.m = a;
                    if (this.m_copyrightLogoMarker == null) {
                        this.m_copyrightLogoMarker = new MapScreenMarker(a2, a3);
                    } else {
                        this.m_copyrightLogoMarker.setIcon(a3);
                        this.m_copyrightLogoMarker.setScreenCoordinate(a2);
                    }
                    this.m_copyrightLogoMarker.setAnchorPoint(new PointF(0.0f, 0.0f));
                }
            } else {
                a2 = a(b, this.m_copyrightLogoMarker.getIcon());
                PointF screenCoordinate = this.m_copyrightLogoMarker.getScreenCoordinate();
                if (!(a2 == null || (a2.x == screenCoordinate.x && a2.y == screenCoordinate.y))) {
                    this.m_copyrightLogoMarker.setScreenCoordinate(a2);
                }
            }
            if (this.m_copyrightLogoMarker != null) {
                this.m_copyrightLogoMarker.setVisible(this.m_copyrightLogoVisible);
            }
        }
    }

    public final Map getMap() {
        if (this.m_viewProxy != null) {
            return this.m_viewProxy.b();
        }
        return null;
    }

    public void setClipRect(ViewRect viewRect, PointF pointF) {
        if (!viewRect.isValid()) {
            throw new IllegalArgumentException("Input parameter rect is invalid");
        } else if (this.m_viewProxy != null && viewRect.isValid()) {
            this.m_viewProxy.a(viewRect, pointF);
            e();
        }
    }

    public void setClipRect(ViewRect viewRect) {
        if (viewRect.isValid()) {
            ViewRect clipRect = getClipRect();
            if (clipRect == null || !clipRect.isValid() || !clipRect.equals(viewRect)) {
                setClipRect(viewRect, null);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Input parameter rect is invalid");
    }

    public ViewRect getClipRect() {
        if (this.m_viewProxy != null) {
            return this.m_viewProxy.a();
        }
        return null;
    }

    public Rect getCopyrightBoundaryRect() {
        return this.p;
    }

    public void setCopyrightBoundaryRect(Rect rect) {
        Object obj = null;
        if (rect != null) {
            if (!(this.p != null && rect.left == this.p.left && rect.right == this.p.right && rect.top == this.p.top && rect.bottom == this.p.bottom)) {
                if (rect.isEmpty() || rect.right <= 0 || rect.bottom <= 0 || !new ViewRect(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top).isValid()) {
                    throw new IllegalArgumentException("Input parameter rect is invalid.");
                }
                this.p = new Rect(rect);
                obj = 1;
            }
        } else if (this.p != null) {
            this.p = null;
            int i = 1;
        }
        if (obj != null) {
            d();
        }
    }

    public void setCopyrightMargin(int i) {
        if (this.r != i) {
            int i2 = this.r;
            if (i >= this.q || this.q <= 0) {
                this.r = i;
            } else {
                this.r = this.q;
            }
            if (this.r != i2) {
                d();
            }
        }
    }

    public int getCopyrightMargin() {
        return this.r;
    }

    public int getCopyrightLogoWidth() {
        if (!(this.n == null || this.m == null)) {
            Image image = (Image) this.n.get(this.m);
            if (image != null) {
                return (int) image.getWidth();
            }
        }
        return -1;
    }

    public int getCopyrightLogoHeight() {
        if (!(this.n == null || this.m == null)) {
            Image image = (Image) this.n.get(this.m);
            if (image != null) {
                return (int) image.getHeight();
            }
        }
        return -1;
    }

    public void setCopyrightLogoPosition(CopyrightLogoPosition copyrightLogoPosition) {
        if (this.o != copyrightLogoPosition) {
            this.o = copyrightLogoPosition;
            d();
        }
    }

    public CopyrightLogoPosition getCopyrightLogoPosition() {
        return this.o;
    }

    public int getCopyrightLogoVisibility() {
        return this.m_copyrightLogoVisible ? 0 : 4;
    }

    private void e() {
        if (this.m_viewImpl != null) {
            post(new a(this));
        }
    }

    public void getScreenCapture(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.m_viewProxy != null) {
            this.m_viewProxy.a(onScreenCaptureListener);
        }
    }

    private ViewRect b(Map map, Image image) {
        if (this.p == null || image == null || image.getHeight() <= 0 || image.getWidth() <= 0) {
            return null;
        }
        Rect rect = new Rect(0, 0, map.getWidth(), map.getHeight());
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int a;
        if (rect.contains(this.p)) {
            a = a(this.p.width(), this.p.height(), width, height);
            if (this.p.width() < width + (a * 2) || this.p.height() < (a * 2) + height) {
                return null;
            }
            return new ViewRect(this.p.left, this.p.top, this.p.right - this.p.left, this.p.bottom - this.p.top);
        } else if (!rect.intersect(this.p)) {
            return null;
        } else {
            Rect rect2 = new Rect(Math.max(rect.left, this.p.left), Math.max(rect.top, this.p.top), Math.min(rect.right, this.p.right), Math.min(rect.bottom, this.p.bottom));
            a = a(rect2.width(), rect2.height(), width, height);
            if (rect2.width() < width + (a * 2) || rect2.height() < (a * 2) + height) {
                return null;
            }
            return new ViewRect(rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top);
        }
    }

    private int a(int i, int i2, int i3, int i4) {
        int i5 = (i - i3 > i2 - i4 ? i2 - i4 : i - i3) >> 1;
        int i6 = this.r;
        if (i5 <= 0) {
            return i6;
        }
        if (this.r > i5) {
            return i5;
        }
        return this.r;
    }
}
