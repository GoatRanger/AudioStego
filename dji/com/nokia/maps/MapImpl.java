package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import com.here.android.mpa.cluster.ClusterLayer;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.IconCategory;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.HistoricalTrafficRasterTileSource;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map$Scheme;
import com.here.android.mpa.mapping.Map.Animation;
import com.here.android.mpa.mapping.Map.InfoBubbleAdapter;
import com.here.android.mpa.mapping.Map.LayerCategory;
import com.here.android.mpa.mapping.Map.OnSchemeChangedListener;
import com.here.android.mpa.mapping.Map.OnTransformListener;
import com.here.android.mpa.mapping.Map.PixelResult;
import com.here.android.mpa.mapping.Map.Projection;
import com.here.android.mpa.mapping.MapBuildingLayer;
import com.here.android.mpa.mapping.MapContainer;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapObject.Type;
import com.here.android.mpa.mapping.MapOverlay;
import com.here.android.mpa.mapping.MapRasterTileSource;
import com.here.android.mpa.mapping.MapRoute$RenderType;
import com.here.android.mpa.mapping.MapState;
import com.here.android.mpa.mapping.MapTrafficLayer;
import com.here.android.mpa.mapping.MapTransitLayer;
import com.here.android.mpa.mapping.PositionIndicator;
import com.here.android.mpa.mapping.customization.CustomizableScheme;
import com.nokia.maps.ApplicationContext.c;
import com.nokia.maps.MapGestureHandler.MapUserInteractionListener;
import com.nokia.maps.MapsEngine.e;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import dji.midware.util.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import lecho.lib.hellocharts.model.h;

@Online
public final class MapImpl extends BaseNativeObject {
    private static Object al = new Object();
    private static final String e = MapImpl.class.getName();
    private static HashSet<String> s = null;
    private static k<Map, MapImpl> u = null;
    private static f v = f.d;
    private static String w = null;
    private static String x = null;
    private static int y = 0;
    private static int z = 0;
    private g A;
    private final p B = l.a();
    private final o C = new o();
    private final Set<ClusterLayer> D = new HashSet();
    private final Set<MapOverlay> E = new HashSet();
    private boolean F = false;
    private int[] G;
    private int[] J;
    private final Runnable K = new 1(this);
    private Runnable L = null;
    private final List<Pair<en, en>> M = new ArrayList();
    private Runnable N = null;
    private final AtomicBoolean O = new AtomicBoolean(false);
    private final Runnable P = new 23(this);
    private final Runnable Q = new b(this);
    private final c R = new 3(this);
    private Boolean S = null;
    private Boolean T = null;
    private final c U = new 4(this);
    private final c V = new 5(this);
    private String W = null;
    private final Runnable X = new 6(this);
    private boolean Y = false;
    private final CopyOnWriteArrayList<e> Z = new CopyOnWriteArrayList();
    final MapUserInteractionListener a = new 16(this);
    private final CopyOnWriteArrayList<a> aa = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnTransformListener> ab = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnSchemeChangedListener> ac = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<h> ad = new CopyOnWriteArrayList();
    private final Runnable ae = new 7(this);
    private CopyOnWriteArrayList<MapRouteImpl> af = null;
    private double ag = 0.0d;
    private boolean ah;
    private int ai;
    private int aj;
    private final c ak = new 13(this);
    private final List<MapRasterTileSource> am = new CopyOnWriteArrayList();
    private final Runnable an = new 14(this);
    private volatile MapTrafficLayer ao;
    private final c ap = new 15(this);
    private InfoBubbleAdapter aq;
    private final AtomicBoolean ar = new AtomicBoolean(true);
    private final CopyOnWriteArrayList<Runnable> as = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<Runnable> at = new CopyOnWriteArrayList();
    private fd au = null;
    private final MapEventDispatcher av;
    private final boolean aw = true;
    private int b = 0;
    private int c = 0;
    private PointF d;
    private final Context f;
    private final List<MapRasterTileSource> g;
    private PanoramaCoverageRasterTileSource h;
    private PositionIndicator i = null;
    private final Hashtable<Integer, MapObject> j = new Hashtable();
    private final List<MapContainer> k = new ArrayList();
    private boolean l = false;
    private boolean m = false;
    private int n = 51;
    private int o = 51;
    private AtomicInteger p = null;
    private boolean q;
    private final cq r = new cq(MapImpl.class.getName());
    private c t;

    private native synchronized boolean addMapObjectNative(MapObjectImpl mapObjectImpl);

    private native boolean addMapObjectsNative(Object[] objArr);

    private native synchronized boolean addRasterTileSourceNative(MapRasterTileSourceImpl mapRasterTileSourceImpl);

    private native synchronized boolean captureScreen(byte[] bArr);

    private native CustomizableSchemeImpl createCustomizableSchemeNative(String str, String str2);

    private native boolean createMapNative(int i, int i2, String str, String str2);

    private native void destroyMapNative();

    private native PixelResult geoToPixel(double d, double d2, double d3);

    private native PixelResult[] geoToPixelNative(GeoCoordinateImpl[] geoCoordinateImplArr);

    private native int getAltitudeConversionModeNative();

    private native synchronized byte[] getBitmapArrayStream(int i, int i2);

    private native synchronized GeoBoundingBoxImpl getBoundingBoxNative();

    private native CustomizableSchemeImpl getCustomizableSchemeNative(String str);

    private native synchronized int getDetailLevelNative();

    private native int getGlobePanModeNative();

    private native ViewObject[] getSelectedObjectsNative(float f, float f2);

    private native ViewObject[] getSelectedObjectsNative(int i, int i2, int i3, int i4);

    private native synchronized PointF getTransformCenterNative();

    private native synchronized int getViewType();

    private native synchronized boolean inChinaNative(GeoCoordinateImpl geoCoordinateImpl, double d);

    private native boolean isLandmarksVisibleNative();

    private native synchronized boolean isPoiCategoryVisibleNative(int i);

    private native boolean isUserSchemeNative(String str);

    private native void moveTo(float f, float f2, int i, double d, float f3, float f4);

    private native synchronized void moveToNative(int i, int i2, int i3, int i4, float f, float f2);

    private native synchronized void panNative(float f, float f2, float f3, float f4);

    private native GeoCoordinateImpl[] pixelToGeoNative(Object[] objArr);

    private native boolean removeCustomizableSchemeNative(String str);

    private native boolean removeMapObjectNative(MapObjectImpl mapObjectImpl);

    private native synchronized boolean removeMapObjectsNative(Object[] objArr);

    private native GeoCoordinateImpl screenToGeoCoordinates(float f, float f2);

    private native GeoCoordinateImpl screenToGeoCoordinates(float f, float f2, float f3);

    private native void setAltitudeConversionMode(int i);

    private native void setCenterNative(double d, double d2, int i, double d3, float f, float f2);

    private native void setClipRect(int i, int i2, int i3, int i4, float f, float f2);

    private native synchronized void setDetailLevel(int i);

    private native synchronized void setFleetFeaturesVisibleNative(int i);

    private native void setGlobePanModeNative(int i);

    private native synchronized boolean setLandmarksVisibleNative(boolean z);

    private native boolean setMapDisplayLanguageNative(String str);

    private native synchronized boolean setMapSchemeNative(String str);

    private native boolean setMapSecondaryDisplayLanguageNative(String str);

    private native void setMaximumTiltFunctionNative(fe feVar);

    private native void setOrientation(float f, int i);

    private native void setPausedNative();

    private native void setTilt(float f, int i);

    private native int setTrafficInfoVisibleNative(boolean z);

    private native void setTransformCenterNative(float f, float f2);

    private native void setViewRect(int i, int i2, int i3, int i4, float f, float f2);

    private native void setViewTypeNative(int i);

    private native void setVisuals(int i, int i2, float f, float f2);

    private native synchronized void setZoomLevel(double d, int i);

    private native synchronized boolean showPoiCategoryNative(int i, boolean z);

    private native synchronized void viewGeometryChangedNative(int i, int i2);

    private native void zoomToNative(GeoBoundingBoxImpl geoBoundingBoxImpl, int i, float f);

    private native void zoomToNative(GeoBoundingBoxImpl geoBoundingBoxImpl, int i, float f, float f2);

    private native void zoomToNative(GeoBoundingBoxImpl geoBoundingBoxImpl, int i, int i2, int i3, float f);

    private native void zoomToNative(GeoBoundingBoxImpl geoBoundingBoxImpl, int i, int i2, int i3, int i4, int i5, float f);

    native void destroyViewObjects();

    protected native synchronized boolean draw(boolean z, boolean z2);

    public native synchronized void freeGfxResources();

    native synchronized GeoCoordinateImpl getCenterNative();

    public native synchronized ViewRect getClipRect();

    public final native String getCopyright();

    public native synchronized MapBuildingLayerImpl getExtrudedBuildingsLayer();

    public native synchronized boolean getFadingAnimations();

    public native synchronized int getFleetFeaturesVisible();

    public native synchronized int[] getLayerCategory();

    public native String getMapDisplayLanguage();

    public native synchronized String getMapScheme();

    public native synchronized String[] getMapSchemesNative();

    public native String getMapSecondaryDisplayLanguage();

    public native synchronized MapState getMapState();

    public native MapTrafficLayerImpl getMapTrafficLayerNative();

    public native MapTransitLayerImpl getMapTransitLayerNative();

    public native synchronized float getMaxTilt();

    public native synchronized double getMaxZoomLevel();

    public native synchronized float getMinTilt();

    public native synchronized double getMinZoomLevel();

    public native synchronized float getOrientation();

    public native synchronized boolean getPanoramaCoverageEnabled();

    public native int getPedestrianFeaturesVisible();

    public native synchronized String[] getPoiCategories();

    public native boolean getSafetySpotsVisible();

    public native String[] getSupportedMapDisplayLanguagesNative();

    public native synchronized float getTilt();

    @Deprecated
    public native synchronized ViewRect getViewRect();

    public native synchronized double getZoomLevel();

    public native double getZoomLevelToZoomScale(float f);

    public native float getZoomScaleToZoomLevel(double d);

    public native synchronized void invalidate();

    public native synchronized boolean isExtrudedBuildingsVisible();

    public native boolean isRetainedLabelsEnabled();

    public native boolean isSubPixelLabelPositioningEnabled();

    public native synchronized boolean need_mapData();

    public native boolean need_redraw();

    public native synchronized boolean removeRasterTileSourceNative(MapRasterTileSourceImpl mapRasterTileSourceImpl);

    native void setAAEnabled(boolean z);

    public native void setDistanceToPoleTuning(double d);

    public native boolean setExtrudedBuildingsVisibleNative(boolean z);

    public native synchronized void setFadingAnimations(boolean z);

    public native synchronized void setLayerCategory(int[] iArr, boolean z);

    public native synchronized void setPanoramaCoverageEnabledNative(boolean z);

    public native void setPedestrianFeaturesVisibleNative(int i);

    public native void setRenderingStatisticsVisible(boolean z);

    public native void setRetainedLabelsEnabled(boolean z);

    public native void setSafetySpotsVisible(boolean z);

    public native void setSubPixelLabelPositioningEnabled(boolean z);

    public native synchronized void setZoomLevel(double d, int i, int i2, int i3);

    @OnlineNative
    static MapImpl get(Map map) {
        if (u != null) {
            return (MapImpl) u.a(map);
        }
        return null;
    }

    public static void a(k<Map, MapImpl> kVar) {
        u = kVar;
    }

    o a() {
        return this.C;
    }

    public static void a(String str, String str2) throws FileNotFoundException {
        File file = new File(str);
        if (str == null || !file.exists()) {
            throw new FileNotFoundException();
        }
        file = new File(str2);
        if (str2 == null || file.exists()) {
            w = str;
            x = str2;
            return;
        }
        throw new FileNotFoundException();
    }

    private void a(Context context) {
        this.A = new g();
        switch (17.a[v.ordinal()]) {
            case 1:
                this.A.c = 250;
                this.A.d = 250;
                return;
            case 2:
                this.A.c = 320;
                this.A.d = 400;
                return;
            case 3:
                this.A.c = 440;
                this.A.d = 600;
                return;
            case 4:
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                if (displayMetrics.densityDpi < 160) {
                    this.A.c = displayMetrics.densityDpi;
                    this.A.d = 250;
                    return;
                } else if (displayMetrics.densityDpi < 330) {
                    this.A.c = displayMetrics.densityDpi;
                    this.A.d = 400;
                    return;
                } else if (displayMetrics.densityDpi < 440) {
                    this.A.c = displayMetrics.densityDpi;
                    this.A.d = 600;
                    return;
                } else if (displayMetrics.densityDpi < 600) {
                    this.A.c = displayMetrics.densityDpi;
                    this.A.d = 600;
                    return;
                } else {
                    this.A.c = 600;
                    this.A.d = 600;
                    return;
                }
            case 5:
                this.A.c = y;
                this.A.d = z;
                return;
            default:
                return;
        }
    }

    private void b(Context context) {
        float f = 1.0f;
        switch (17.a[v.ordinal()]) {
            case 1:
                f = b.c;
                break;
            case 2:
                f = h.l;
                break;
            case 3:
            case 4:
                f = ((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f;
                break;
        }
        this.n = (int) (((float) this.n) * f);
        if (this.n % 2 == 0) {
            this.n++;
        }
        this.o = (int) (f * ((float) this.o));
        if (this.o % 2 == 0) {
            this.o++;
        }
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public MapImpl(Context context) {
        super(true);
        MapsEngine.b(context);
        if (MapsEngine.b() != e.c) {
            throw new RuntimeException("MapsEngine is not ready. Map Download maybe happening.");
        }
        String str;
        String str2;
        this.f = context.getApplicationContext();
        a(context);
        b(context);
        if (w != null) {
            str = w;
            str2 = x != null ? x : null;
        } else {
            str2 = null;
            str = null;
        }
        if (createMapNative(this.A.c, this.A.d, str, str2)) {
            j();
            try {
                this.t = new c(this);
                MapsEngine.c().a(this.t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            a(aw.a);
            this.d = null;
            this.g = new CopyOnWriteArrayList();
            this.au = new fd();
            this.av = new MapEventDispatcher(this);
            this.av.start();
            this.p = new AtomicInteger(0);
            setRetainedLabelsEnabled(true);
            List arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(1));
            arrayList.add(Integer.valueOf(2));
            arrayList.add(Integer.valueOf(3));
            arrayList.add(Integer.valueOf(4));
            arrayList.add(Integer.valueOf(17));
            arrayList.add(Integer.valueOf(52));
            arrayList.add(Integer.valueOf(68));
            arrayList.add(Integer.valueOf(71));
            arrayList.add(Integer.valueOf(74));
            arrayList.add(Integer.valueOf(75));
            arrayList.add(Integer.valueOf(76));
            arrayList.add(Integer.valueOf(79));
            arrayList.add(Integer.valueOf(80));
            arrayList.add(Integer.valueOf(87));
            arrayList.add(Integer.valueOf(88));
            arrayList.add(Integer.valueOf(89));
            arrayList.add(Integer.valueOf(90));
            LayerCategory[] values = LayerCategory.values();
            this.J = new int[(values.length - arrayList.size())];
            int i = 0;
            for (LayerCategory a : values) {
                int a2 = Map.a(a);
                if (!arrayList.contains(Integer.valueOf(a2))) {
                    this.J[i] = a2;
                    i++;
                }
            }
            this.F = false;
            c(getZoomLevel());
            return;
        }
        throw new RuntimeException("Invalid configuration file. Check MWConfig!");
    }

    protected void finalize() {
        for (ClusterLayer a : this.D) {
            ab.a(a).b();
        }
        if (this.au != null) {
            this.au.a();
        }
        if (this.av != null) {
            this.av.a();
        }
        if (this.j != null) {
            this.j.clear();
        }
        if (this.k != null) {
            this.k.clear();
        }
        if (this.nativeptr != 0) {
            destroyMapNative();
        }
    }

    public void a(boolean z) {
        this.q = z;
        if (this.q) {
            setPausedNative();
            a(this.K);
        }
    }

    public synchronized void a(GeoCoordinateImpl geoCoordinateImpl, Animation animation, double d, float f, float f2) {
        String str = e;
        String str2 = "point(%f, %f)";
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(geoCoordinateImpl != null ? geoCoordinateImpl.a() : 1.073741824E9d);
        objArr[1] = Double.valueOf(geoCoordinateImpl != null ? geoCoordinateImpl.b() : 1.073741824E9d);
        bj.e(str, str2, objArr);
        if (geoCoordinateImpl != null && geoCoordinateImpl.d()) {
            if (animation != Animation.NONE) {
                this.at.add(new 12(this, geoCoordinateImpl, animation, d, f, f2));
            } else {
                I();
                G();
                setCenterNative(geoCoordinateImpl.a(), geoCoordinateImpl.b(), b(animation), d, f, f2);
                if (d == Map.MOVE_PRESERVE_ZOOM_LEVEL) {
                    d = getZoomLevel();
                }
                c(d);
            }
            redraw();
        }
    }

    public synchronized void a(GeoCoordinateImpl geoCoordinateImpl, Animation animation) {
        a(geoCoordinateImpl, animation, (double) Map.MOVE_PRESERVE_ZOOM_LEVEL, -1.0f, -1.0f);
    }

    public synchronized void a(PointF pointF) {
        if (pointF != null) {
            if (this.b == 0 && this.c == 0) {
                this.d = pointF;
            } else {
                this.d = null;
                setTransformCenterNative(pointF.x, pointF.y);
            }
        }
    }

    public synchronized PointF d() {
        return getTransformCenterNative();
    }

    public synchronized void a(GeoBoundingBoxImpl geoBoundingBoxImpl, Animation animation, float f) {
        if (geoBoundingBoxImpl != null) {
            if (!geoBoundingBoxImpl.e()) {
                this.at.add(new 18(this, geoBoundingBoxImpl, animation, f));
                redraw();
            }
        }
    }

    public synchronized void a(GeoBoundingBoxImpl geoBoundingBoxImpl, Animation animation, float f, float f2) {
        if (geoBoundingBoxImpl != null) {
            if (!geoBoundingBoxImpl.e()) {
                this.at.add(new 19(this, geoBoundingBoxImpl, animation, f, f2));
                redraw();
            }
        }
    }

    public synchronized void a(GeoBoundingBoxImpl geoBoundingBoxImpl, ViewRect viewRect, Animation animation, float f) {
        if (this.c == 0 || this.b == 0) {
            throw new RuntimeException("This API cannot be called until the map control has been given a size");
        } else if (geoBoundingBoxImpl != null) {
            if (!geoBoundingBoxImpl.e()) {
                this.at.add(new 20(this, geoBoundingBoxImpl, viewRect, animation, f));
                redraw();
            }
        }
    }

    public synchronized void a(GeoBoundingBoxImpl geoBoundingBoxImpl, int i, int i2, Animation animation, float f) {
        if (this.c == 0 || this.b == 0) {
            throw new RuntimeException("This API cannot be called until the map control has been given a size");
        } else if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both width and height must be greater than 0");
        } else if (!(geoBoundingBoxImpl == null || geoBoundingBoxImpl.e())) {
            this.at.add(new 21(this, geoBoundingBoxImpl, i, i2, animation, f));
            redraw();
        }
    }

    public void a(en enVar, en enVar2) {
        synchronized (this.M) {
            this.M.add(new Pair(enVar, enVar2));
        }
        redraw();
    }

    private void F() {
        synchronized (this.M) {
            if (!this.M.isEmpty()) {
                int size = this.M.size() - 1;
                a(((en) ((Pair) this.M.get(0)).first).x, ((en) ((Pair) this.M.get(0)).first).y, ((en) ((Pair) this.M.get(size)).second).x, ((en) ((Pair) this.M.get(size)).second).y);
                this.M.clear();
            }
        }
    }

    private synchronized void a(float f, float f2, float f3, float f4) {
        G();
        panNative(f, f2, f3, f4);
    }

    public synchronized void a(aw awVar) {
        setGlobePanModeNative(awVar.ordinal());
    }

    public void b(boolean z) {
        showPoiCategoryNative(bb.a(IconCategory.ALL), z);
    }

    public boolean e() {
        return a(IconCategory.ALL);
    }

    public boolean a(IconCategory iconCategory, boolean z) {
        return showPoiCategoryNative(bb.a(iconCategory), z);
    }

    public boolean a(IconCategory iconCategory) {
        return isPoiCategoryVisibleNative(bb.a(iconCategory));
    }

    public void a(int i) {
        int fleetFeaturesVisible = getFleetFeaturesVisible();
        setFleetFeaturesVisibleNative(i);
        int i2 = (fleetFeaturesVisible & i) ^ i;
        if ((i2 & 1) > 0) {
            this.B.d(getMapScheme());
        }
        if (((i2 & 4) > 0 || (i2 & 2) > 0) && (fleetFeaturesVisible & 6) == 0) {
            this.B.e(getMapScheme());
        }
        redraw();
    }

    public void b(int i) {
        setPedestrianFeaturesVisibleNative(i);
        redraw();
    }

    public void a(double d) {
        a(d, Animation.NONE);
    }

    public void a(double d, Animation animation) {
        if (d >= getMinZoomLevel() && d <= getMaxZoomLevel()) {
            I();
            G();
            setZoomLevel(d, b(animation));
            c(d);
        }
    }

    public void a(double d, PointF pointF, Animation animation) {
        if (this.c == 0 || this.b == 0) {
            throw new RuntimeException("This API cannot be called until the map control has been given a size");
        } else if (d >= getMinZoomLevel() && d <= getMaxZoomLevel()) {
            I();
            G();
            setZoomLevel(d, (int) pointF.x, (int) pointF.y, b(animation));
            c(d);
        }
    }

    public synchronized double b(double d) {
        return getZoomLevelToZoomScale((float) d);
    }

    public void a(float f, float f2, float f3, float f4, float f5, float f6) {
        this.at.remove(this.N);
        this.N = new 22(this, f, f2, f3, f4, f5, f6);
        this.at.add(this.N);
        redraw();
    }

    private synchronized void a(int i, int i2, int i3, int i4, float f, float f2) {
        G();
        moveToNative(i, i2, i3, i4, f, f2);
    }

    public synchronized void a(float f) {
        a(f, Animation.NONE);
    }

    public synchronized void a(float f, Animation animation) {
        I();
        G();
        setOrientation(f, b(animation));
    }

    protected synchronized void a(int i, int i2) {
        this.b = i;
        this.c = i2;
        viewGeometryChangedNative(i, i2);
        if (this.d != null) {
            setTransformCenterNative(this.d.x, this.d.y);
            this.d = null;
        }
    }

    public synchronized void b(float f) {
        b(f, Animation.NONE);
    }

    public synchronized void b(float f, Animation animation) {
        if (f >= getMinTilt() && f <= getMaxTilt()) {
            I();
            G();
            setTilt(f, b(animation));
        }
    }

    private void G() {
        if (this.O.compareAndSet(false, true)) {
            c(this.P);
            L();
            H();
        }
    }

    private void H() {
        if (this.O.get()) {
            c(this.Q);
        }
    }

    private boolean a(MapState mapState, MapState mapState2) {
        if (mapState == null || mapState2 == null) {
            return false;
        }
        if (!a((double) mapState.getOrientation(), (double) mapState2.getOrientation(), 1.0E-6d)) {
            return false;
        }
        if (a((double) mapState.getTilt(), (double) mapState2.getTilt(), 1.0E-6d) && a(mapState.getZoomLevel(), mapState2.getZoomLevel(), 1.0E-6d) && mapState.getCenter().equals(mapState2.getCenter())) {
            return true;
        }
        return false;
    }

    private boolean a(double d, double d2, double d3) {
        return Math.abs(d - d2) < d3;
    }

    private void I() {
        Iterator it = this.aa.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
    }

    public synchronized boolean f() {
        boolean booleanValue;
        if (this.S != null) {
            booleanValue = this.S.booleanValue();
        } else {
            booleanValue = isLandmarksVisibleNative();
        }
        return booleanValue;
    }

    public void c(boolean z) {
        a(z, false);
    }

    private void a(boolean z, boolean z2) {
        boolean z3;
        if (z) {
            ApplicationContext.b().check(18, this.R);
        }
        if (z && inChinaNative(getCenterNative(), getZoomLevel())) {
            z3 = false;
        } else {
            z3 = z;
        }
        synchronized (this) {
            this.S = z3 ? Boolean.TRUE : Boolean.FALSE;
            if (!z2) {
                this.T = z ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        this.as.add(new 2(this));
        redraw();
    }

    private synchronized void J() {
        if (this.S != null) {
            if (this.S.booleanValue() != isLandmarksVisibleNative()) {
                setLandmarksVisibleNative(this.S.booleanValue());
                if (this.S.booleanValue()) {
                    this.B.h();
                }
            }
            this.S = null;
        }
    }

    public void a(String str) {
        if (e(str)) {
            if (str.contains("satellite") || str.contains("hybrid")) {
                ApplicationContext.b().check(17, this.U);
            }
            if (str.contains("truck")) {
                ApplicationContext.b().check(15, this.V);
            }
            synchronized (this) {
                this.W = str;
            }
            if (this.at.addIfAbsent(this.X)) {
                redraw();
            }
        } else if (f(str)) {
            synchronized (this) {
                this.W = str;
            }
            if (this.at.addIfAbsent(this.X)) {
                redraw();
            }
        }
    }

    public void a(CustomizableScheme customizableScheme) {
        String name = customizableScheme.getName();
        if (f(name)) {
            synchronized (this) {
                this.W = name;
            }
            if (this.at.addIfAbsent(this.X)) {
                redraw();
            }
        }
    }

    private synchronized void K() {
        if (this.W != null) {
            String mapScheme = getMapScheme();
            if (!mapScheme.contentEquals(this.W)) {
                setMapSchemeNative(this.W);
                if (f(mapScheme) || f(this.W)) {
                    d("changescheme");
                } else {
                    c(mapScheme, this.W);
                }
            }
            this.W = null;
        }
    }

    private void d(String str) {
        this.B.b(str);
    }

    private void c(String str, String str2) {
        if (str2.contains("truck")) {
            if (!str.contains("truck")) {
                this.B.c(str2);
                if (str.compareTo(Map$Scheme.NORMAL_DAY) != 0 || str2.compareTo(Map$Scheme.TRUCK_DAY) != 0) {
                    if (str.compareTo(Map$Scheme.HYBRID_DAY) != 0 || str2.compareTo(Map$Scheme.TRUCK_HYBRID_DAY) != 0) {
                        if (str.compareTo(Map$Scheme.HYBRID_NIGHT) != 0 || str2.compareTo(Map$Scheme.TRUCK_HYBRID_NIGHT) != 0) {
                            if (str.compareTo(Map$Scheme.NORMAL_NIGHT) != 0 || str2.compareTo(Map$Scheme.TRUCK_NIGHT) != 0) {
                                if (str.compareTo(Map$Scheme.CARNAV_DAY) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_DAY) != 0) {
                                    if (str.compareTo(Map$Scheme.CARNAV_NIGHT) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_NIGHT) != 0) {
                                        if (str.compareTo(Map$Scheme.CARNAV_HYBRID_DAY) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_HYBRID_DAY) != 0) {
                                            if (str.compareTo(Map$Scheme.CARNAV_HYBRID_NIGHT) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_HYBRID_NIGHT) != 0) {
                                                this.B.a(str, str2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (str2.compareTo(str) != 0) {
                this.B.a(str, str2);
            }
        } else if (str.compareTo(Map$Scheme.TRUCK_DAY) != 0 || str2.compareTo(Map$Scheme.NORMAL_DAY) != 0) {
            if (str.compareTo(Map$Scheme.TRUCK_HYBRID_DAY) != 0 || str2.compareTo(Map$Scheme.HYBRID_DAY) != 0) {
                if (str.compareTo(Map$Scheme.TRUCK_HYBRID_NIGHT) != 0 || str2.compareTo(Map$Scheme.HYBRID_NIGHT) != 0) {
                    if (str.compareTo(Map$Scheme.TRUCK_NIGHT) != 0 || str2.compareTo(Map$Scheme.NORMAL_NIGHT) != 0) {
                        if (str.compareTo(Map$Scheme.CARNAV_DAY) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_DAY) != 0) {
                            if (str.compareTo(Map$Scheme.CARNAV_NIGHT) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_NIGHT) != 0) {
                                if (str.compareTo(Map$Scheme.CARNAV_HYBRID_DAY) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_HYBRID_DAY) != 0) {
                                    if (str.compareTo(Map$Scheme.CARNAV_HYBRID_NIGHT) != 0 || str2.compareTo(Map$Scheme.TRUCKNAV_HYBRID_NIGHT) != 0) {
                                        this.B.a(str, str2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private synchronized boolean e(String str) {
        if (s == null) {
            s = new HashSet();
            s.add(Map$Scheme.NORMAL_DAY);
            s.add(Map$Scheme.NORMAL_NIGHT);
            s.add(Map$Scheme.TERRAIN_DAY);
            s.add(Map$Scheme.SATELLITE_DAY);
            s.add(Map$Scheme.SATELLITE_NIGHT);
            s.add(Map$Scheme.HYBRID_DAY);
            s.add(Map$Scheme.HYBRID_NIGHT);
            s.add(Map$Scheme.PEDESTRIAN_DAY);
            s.add(Map$Scheme.PEDESTRIAN_NIGHT);
            s.add(Map$Scheme.NORMAL_DAY_TRANSIT);
            s.add(Map$Scheme.NORMAL_NIGHT_TRANSIT);
            s.add(Map$Scheme.HYBRID_DAY_TRANSIT);
            s.add(Map$Scheme.HYBRID_NIGHT_TRANSIT);
            s.add(Map$Scheme.PEDESTRIAN_DAY_HYBRID);
            s.add(Map$Scheme.PEDESTRIAN_NIGHT_HYBRID);
            s.add(Map$Scheme.NORMAL_DAY_GREY);
            s.add(Map$Scheme.NORMAL_NIGHT_GREY);
            s.add(Map$Scheme.HYBRID_GREY_DAY);
            s.add(Map$Scheme.HYBRID_GREY_NIGHT);
            s.add(Map$Scheme.REDUCED_DAY);
            s.add(Map$Scheme.REDUCED_NIGHT);
            s.add(Map$Scheme.HYBRID_REDUCED_DAY);
            s.add(Map$Scheme.HYBRID_REDUCED_NIGHT);
            s.add(Map$Scheme.MANEUVER_DAY);
            s.add(Map$Scheme.NORMAL_TRAFFIC_DAY);
            s.add(Map$Scheme.NORMAL_TRAFFIC_NIGHT);
            s.add(Map$Scheme.HYBRID_TRAFFIC_DAY);
            s.add(Map$Scheme.HYBRID_TRAFFIC_NIGHT);
            s.add(Map$Scheme.CARNAV_DAY);
            s.add(Map$Scheme.CARNAV_NIGHT);
            s.add(Map$Scheme.CARNAV_HYBRID_DAY);
            s.add(Map$Scheme.CARNAV_HYBRID_NIGHT);
            s.add(Map$Scheme.CARNAV_TRAFFIC_DAY);
            s.add(Map$Scheme.CARNAV_TRAFFIC_NIGHT);
            s.add(Map$Scheme.CARNAV_TRAFFIC_HYBRID_DAY);
            s.add(Map$Scheme.CARNAV_TRAFFIC_HYBRID_NIGHT);
            s.add(Map$Scheme.CARNAV_DAY_GREY);
            s.add(Map$Scheme.CARNAV_NIGHT_GREY);
            s.add(Map$Scheme.TRUCK_DAY);
            s.add(Map$Scheme.TRUCK_NIGHT);
            s.add(Map$Scheme.TRUCK_HYBRID_DAY);
            s.add(Map$Scheme.TRUCK_HYBRID_NIGHT);
            s.add(Map$Scheme.TRUCKNAV_DAY);
            s.add(Map$Scheme.TRUCKNAV_NIGHT);
            s.add(Map$Scheme.TRUCKNAV_HYBRID_DAY);
            s.add(Map$Scheme.TRUCKNAV_HYBRID_NIGHT);
        }
        return s.contains(str);
    }

    private boolean f(String str) {
        return isUserSchemeNative(str);
    }

    public synchronized List<String> g() {
        List arrayList;
        arrayList = new ArrayList();
        for (String str : getMapSchemesNative()) {
            if (e(str) || f(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public synchronized GeoCoordinate h() {
        return GeoCoordinateImpl.create(getCenterNative());
    }

    public synchronized GeoBoundingBox i() {
        return GeoBoundingBoxImpl.create(getBoundingBoxNative());
    }

    public boolean j() {
        String country = Locale.getDefault().getCountry();
        String language = Locale.getDefault().getLanguage();
        if (!country.isEmpty()) {
            language = language + "-" + country;
        } else if (language.indexOf("_") != -1) {
            String[] split = language.split("_");
            if (split != null && split.length == 2) {
                language = split[0] + "-" + split[1].toUpperCase(Locale.getDefault());
            }
        }
        if (!setMapDisplayLanguageNative(language)) {
            return false;
        }
        redraw();
        return true;
    }

    public boolean a(Locale locale) {
        String language;
        if (locale.getCountry().isEmpty()) {
            language = locale.getLanguage();
        } else {
            language = locale.getLanguage() + "-" + locale.getCountry();
        }
        if (!setMapDisplayLanguageNative(language)) {
            return false;
        }
        redraw();
        return true;
    }

    public synchronized List<String> k() {
        List<String> arrayList;
        arrayList = new ArrayList();
        for (Object add : getSupportedMapDisplayLanguagesNative()) {
            arrayList.add(add);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public boolean b(Locale locale) {
        if (locale == null) {
            return setMapSecondaryDisplayLanguageNative("");
        }
        String language;
        if (locale.getCountry().isEmpty()) {
            language = locale.getLanguage();
        } else {
            language = locale.getLanguage() + "-" + locale.getCountry();
        }
        if (!setMapSecondaryDisplayLanguageNative(language)) {
            return false;
        }
        redraw();
        return true;
    }

    public synchronized boolean d(boolean z) {
        boolean z2;
        z2 = false;
        if (setTrafficInfoVisibleNative(z) == 0) {
            z2 = true;
            this.Y = z;
            TrafficUpdaterImpl.a().a(z, this);
            if (z) {
                this.B.a(getMapScheme());
            }
        }
        return z2;
    }

    public synchronized boolean l() {
        return this.Y;
    }

    public synchronized GeoCoordinate b(PointF pointF) {
        return GeoCoordinateImpl.create(screenToGeoCoordinates(pointF.x, pointF.y));
    }

    public synchronized GeoCoordinate a(PointF pointF, float f) {
        return GeoCoordinateImpl.create(screenToGeoCoordinates(pointF.x, pointF.y, f));
    }

    public synchronized List<GeoCoordinate> a(List<PointF> list) {
        return new ArrayList(GeoCoordinateImpl.b(Arrays.asList(pixelToGeoNative(list.toArray()))));
    }

    public synchronized List<PixelResult> b(List<GeoCoordinate> list) {
        return new ArrayList(Arrays.asList(geoToPixelNative(GeoCoordinateImpl.a(list))));
    }

    public void a(e eVar) {
        if (eVar != null) {
            this.Z.addIfAbsent(eVar);
        }
    }

    public void b(e eVar) {
        if (eVar != null) {
            this.Z.remove(eVar);
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.aa.addIfAbsent(aVar);
        }
    }

    public void b(a aVar) {
        if (aVar != null) {
            this.aa.remove(aVar);
        }
    }

    public void a(OnTransformListener onTransformListener) {
        if (onTransformListener != null) {
            this.ab.addIfAbsent(onTransformListener);
        }
    }

    public void b(OnTransformListener onTransformListener) {
        if (onTransformListener != null) {
            this.ab.remove(onTransformListener);
        }
    }

    public void a(OnSchemeChangedListener onSchemeChangedListener) {
        if (onSchemeChangedListener != null) {
            this.ac.addIfAbsent(onSchemeChangedListener);
        }
    }

    public void b(OnSchemeChangedListener onSchemeChangedListener) {
        if (onSchemeChangedListener != null) {
            this.ac.remove(onSchemeChangedListener);
        }
    }

    public void a(h hVar) {
        if (hVar != null && this.ad.addIfAbsent(hVar)) {
            for (MapOverlay a : this.E) {
                hVar.a(a);
            }
        }
    }

    public void b(h hVar) {
        if (hVar != null && this.ad.remove(hVar)) {
            for (MapOverlay b : this.E) {
                hVar.b(b);
            }
        }
    }

    private void L() {
        setSubPixelLabelPositioningEnabled(true);
        if (MapSettings.k() == MapSettings.b.a) {
            c(this.ae);
        } else {
            ez.a(this.ae);
        }
    }

    private void a(MapState mapState) {
        setSubPixelLabelPositioningEnabled(false);
        Runnable 8 = new 8(this, mapState);
        if (MapSettings.k() == MapSettings.b.a) {
            c(8);
        } else {
            ez.a(8);
        }
    }

    private void g(String str) {
        Runnable 9 = new 9(this, str);
        if (MapSettings.k() == MapSettings.b.a) {
            c(9);
        } else {
            ez.a(9);
        }
    }

    private void a(MapOverlay mapOverlay, boolean z) {
        Runnable 10 = new 10(this, z, mapOverlay);
        if (MapSettings.k() == MapSettings.b.a) {
            c(10);
        } else {
            ez.a(10);
        }
    }

    private void M() {
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            try {
                ((e) it.next()).b();
            } catch (Exception e) {
            }
        }
    }

    @OnlineNative
    public void redraw() {
        if (!this.m) {
            N();
        }
    }

    public void e(boolean z) {
        if (z) {
            N();
        } else {
            redraw();
        }
    }

    public void f(boolean z) {
        bj.e(e, "Setting m_limitRedraw to: " + Boolean.toString(z), new Object[0]);
        this.m = z;
    }

    private void N() {
        if (this.l) {
            invalidate();
        }
        Iterator it = this.Z.iterator();
        while (it.hasNext()) {
            ((e) it.next()).a();
        }
    }

    public boolean m() {
        return this.i != null;
    }

    public PositionIndicator n() {
        if (this.i == null) {
            this.i = dw.a(new dw(this.f, this));
        }
        return this.i;
    }

    public synchronized void a(ClusterLayer clusterLayer) {
        if (clusterLayer == null) {
            throw new NullPointerException("layer cannot ne null");
        } else if (this.D.add(clusterLayer)) {
            ab.a(clusterLayer).a(this);
        }
    }

    public synchronized void b(ClusterLayer clusterLayer) {
        if (clusterLayer != null) {
            if (this.D.remove(clusterLayer)) {
                ab.a(clusterLayer).b();
            }
        }
    }

    public synchronized boolean a(MapOverlay mapOverlay) {
        boolean z = true;
        synchronized (this) {
            if (mapOverlay == null) {
                throw new NullPointerException("Null overlay not allowed.");
            }
            if (this.E.add(mapOverlay)) {
                a(mapOverlay, true);
            } else {
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean b(MapOverlay mapOverlay) {
        boolean z = false;
        synchronized (this) {
            if (mapOverlay == null) {
                throw new NullPointerException("Null overlay not allowed.");
            }
            if (this.E.remove(mapOverlay)) {
                a(mapOverlay, false);
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean a(MapObject mapObject) {
        boolean z;
        MapObjectImpl d = MapObjectImpl.d(mapObject);
        z = false;
        if (!this.j.containsKey(Integer.valueOf(d.hashCode()))) {
            z = a(d);
            if (z) {
                this.j.put(Integer.valueOf(d.hashCode()), mapObject);
                if (mapObject.getType() == Type.CONTAINER && !this.k.contains(mapObject)) {
                    this.k.add((MapContainer) mapObject);
                }
            }
        }
        return z;
    }

    public synchronized boolean a(MapObject mapObject, boolean z) {
        boolean a;
        if (z) {
            a = a(mapObject);
        } else {
            a = a(MapObjectImpl.d(mapObject));
        }
        return a;
    }

    private synchronized boolean a(MapObjectImpl mapObjectImpl) {
        boolean addMapObjectsNative;
        mapObjectImpl.a(this);
        if (mapObjectImpl instanceof ca) {
            MapRouteImpl mapRouteImpl = (ca) mapObjectImpl;
            addMapObjectsNative = addMapObjectsNative(mapRouteImpl.e());
            a(mapRouteImpl);
        } else {
            addMapObjectsNative = addMapObjectNative(mapObjectImpl);
        }
        return addMapObjectsNative;
    }

    public synchronized boolean c(List<MapObject> list) {
        boolean z = true;
        boolean z2 = false;
        synchronized (this) {
            if (!list.isEmpty()) {
                List copyOnWriteArrayList = new CopyOnWriteArrayList(list);
                int i = 0;
                boolean z3 = true;
                for (MapObject mapObject : list) {
                    int i2;
                    boolean z4;
                    if (mapObject != null) {
                        MapObjectImpl d = MapObjectImpl.d(mapObject);
                        if (this.j.containsKey(Integer.valueOf(d.hashCode()))) {
                            copyOnWriteArrayList.remove(mapObject);
                            i2 = i;
                            z4 = z3;
                        } else {
                            d.a(this);
                            this.j.put(Integer.valueOf(d.hashCode()), mapObject);
                            int i3 = i + 1;
                            if (mapObject.getType() == Type.CONTAINER && !this.k.contains(mapObject)) {
                                this.k.add((MapContainer) mapObject);
                            }
                            if (d instanceof ca) {
                                MapRouteImpl mapRouteImpl = (ca) d;
                                boolean addMapObjectsNative = addMapObjectsNative(mapRouteImpl.e());
                                a(mapRouteImpl);
                                copyOnWriteArrayList.remove(mapObject);
                                i2 = i3;
                                z4 = addMapObjectsNative;
                            } else {
                                i2 = i3;
                                z4 = z3;
                            }
                        }
                    } else {
                        i2 = i;
                        z4 = false;
                    }
                    z3 = z4;
                    i = i2;
                }
                if (z3 && copyOnWriteArrayList.size() > 0) {
                    z3 = addMapObjectsNative(MapObjectImpl.a(copyOnWriteArrayList));
                }
                if (z3 && list.size() == i) {
                    z2 = true;
                }
                z = z2;
            }
        }
        return z;
    }

    public synchronized boolean b(MapObject mapObject) {
        boolean z;
        MapObjectImpl d = MapObjectImpl.d(mapObject);
        z = false;
        if (d != null) {
            z = b(d);
        }
        if (z) {
            this.j.remove(Integer.valueOf(d.hashCode()));
            if (mapObject.getType() == Type.CONTAINER) {
                this.k.remove(mapObject);
            }
        }
        return z;
    }

    private synchronized boolean b(MapObjectImpl mapObjectImpl) {
        boolean removeMapObjectNative;
        mapObjectImpl.a(null);
        if (mapObjectImpl instanceof MapMarkerImpl) {
            MapMarkerImpl mapMarkerImpl = (MapMarkerImpl) mapObjectImpl;
            a(mapMarkerImpl);
            if (mapMarkerImpl.i()) {
                ((MapMarkerImpl) mapObjectImpl).h();
            }
            removeMapObjectNative = removeMapObjectNative(mapObjectImpl);
        } else if (mapObjectImpl instanceof MapContainerImpl) {
            ((MapContainerImpl) mapObjectImpl).c();
            removeMapObjectNative = removeMapObjectNative(mapObjectImpl);
        } else if (mapObjectImpl instanceof ca) {
            MapRouteImpl mapRouteImpl = (ca) mapObjectImpl;
            removeMapObjectNative = removeMapObjectsNative(mapRouteImpl.e());
            b(mapRouteImpl);
        } else {
            removeMapObjectNative = removeMapObjectNative(mapObjectImpl);
        }
        return removeMapObjectNative;
    }

    private void a(MapMarkerImpl mapMarkerImpl) {
        for (ClusterLayer a : this.D) {
            ab.a(a).c(MapMarkerImpl.a(mapMarkerImpl));
        }
    }

    public synchronized boolean d(List<MapObject> list) {
        boolean z;
        z = true;
        if (list != null) {
            if (!list.isEmpty()) {
                List copyOnWriteArrayList = new CopyOnWriteArrayList(list);
                boolean z2 = true;
                for (MapObject mapObject : list) {
                    if (mapObject != null) {
                        boolean z3;
                        MapObjectImpl d = MapObjectImpl.d(mapObject);
                        d.a(null);
                        if (d instanceof MapMarkerImpl) {
                            MapMarkerImpl mapMarkerImpl = (MapMarkerImpl) d;
                            a(mapMarkerImpl);
                            mapMarkerImpl.h();
                            z3 = z2;
                        } else if (d instanceof MapContainerImpl) {
                            ((MapContainerImpl) d).c();
                            z3 = z2;
                        } else if (d instanceof ca) {
                            MapRouteImpl mapRouteImpl = (ca) d;
                            z2 = removeMapObjectsNative(mapRouteImpl.e());
                            b(mapRouteImpl);
                            copyOnWriteArrayList.remove(mapObject);
                            z3 = z2;
                        } else {
                            z3 = z2;
                        }
                        this.j.remove(Integer.valueOf(d.hashCode()));
                        if (mapObject.getType() == Type.CONTAINER) {
                            this.k.remove(mapObject);
                        }
                        z = z3;
                    } else {
                        z = false;
                    }
                    z2 = z;
                }
                z = (!z2 || copyOnWriteArrayList.size() <= 0) ? z2 : removeMapObjectsNative(MapObjectImpl.a(copyOnWriteArrayList));
            }
        }
        return z;
    }

    private void a(MapRouteImpl mapRouteImpl) {
        if (mapRouteImpl instanceof ca) {
            if (this.af == null) {
                this.af = new CopyOnWriteArrayList();
            }
            this.af.addIfAbsent(mapRouteImpl);
        }
    }

    private void O() {
        if (this.af != null && !this.af.isEmpty()) {
            double zoomLevel = getZoomLevel();
            boolean panoramaCoverageEnabled = getPanoramaCoverageEnabled();
            MapRouteImpl mapRouteImpl;
            Iterator it;
            if (Math.abs(this.ag - zoomLevel) > 0.1d || this.ah != panoramaCoverageEnabled) {
                this.ag = zoomLevel;
                this.ah = panoramaCoverageEnabled;
                double b = b(zoomLevel);
                if (panoramaCoverageEnabled) {
                    this.ai = ee.a(zoomLevel, b, this.A.c, this.A.d);
                    this.aj = this.ai;
                    Iterator it2 = this.af.iterator();
                    while (it2.hasNext()) {
                        mapRouteImpl = (MapRouteImpl) it2.next();
                        if (mapRouteImpl instanceof ca) {
                            ((ca) mapRouteImpl).b(this.ai);
                        }
                    }
                    return;
                }
                int[] b2 = ee.b(zoomLevel, b, this.A.c, this.A.d);
                this.ai = b2[0];
                this.aj = b2[1];
                it = this.af.iterator();
                while (it.hasNext()) {
                    mapRouteImpl = (MapRouteImpl) it.next();
                    if (mapRouteImpl instanceof ca) {
                        ((ca) mapRouteImpl).b(mapRouteImpl.b() == MapRoute$RenderType.SECONDARY ? this.aj : this.ai);
                    }
                }
                return;
            }
            it = this.af.iterator();
            while (it.hasNext()) {
                mapRouteImpl = (MapRouteImpl) it.next();
                if ((mapRouteImpl instanceof ca) && ((ca) mapRouteImpl).f()) {
                    ((ca) mapRouteImpl).b(mapRouteImpl.b() == MapRoute$RenderType.SECONDARY ? this.aj : this.ai);
                }
            }
        }
    }

    private void b(MapRouteImpl mapRouteImpl) {
        if (this.af != null) {
            this.af.remove(mapRouteImpl);
            if (this.af.isEmpty()) {
                this.ag = 0.0d;
                this.ai = 0;
                this.aj = 0;
                this.af = null;
            }
        }
    }

    public synchronized List<ViewObject> c(PointF pointF) {
        List<ViewObject> a;
        a = a(e(pointF));
        ViewObjectImpl.a(a, d(pointF));
        return a;
    }

    public synchronized List<ViewObject> d(PointF pointF) {
        return a(getSelectedObjectsNative(pointF.x, pointF.y));
    }

    private Cluster a(ViewObject viewObject) {
        if (viewObject instanceof MapMarker) {
            for (ClusterLayer a : this.D) {
                for (Cluster cluster : ab.a(a).c()) {
                    if (cluster.representedBy(ViewObjectImpl.a(viewObject).hashCode())) {
                        return cluster;
                    }
                }
            }
        }
        return null;
    }

    public synchronized List<ViewObject> a(ViewRect viewRect) {
        return a(getSelectedObjectsNative(viewRect.getX(), viewRect.getY(), viewRect.getWidth(), viewRect.getHeight()));
    }

    private List<ViewObject> a(ViewObject[] viewObjectArr) {
        List arrayList = new ArrayList();
        for (ViewObject viewObject : viewObjectArr) {
            switch (17.b[viewObject.getBaseType().ordinal()]) {
                case 1:
                    a(arrayList, (MapObject) viewObject);
                    break;
                case 2:
                    arrayList.add(viewObject);
                    break;
                default:
                    break;
            }
        }
        return arrayList;
    }

    private void a(List<ViewObject> list, MapObject mapObject) {
        MapObjectImpl d = MapObjectImpl.d(mapObject);
        if (d != null) {
            Object obj = (MapObject) this.j.get(Integer.valueOf(d.hashCode()));
            if (obj == null) {
                for (MapContainer d2 : this.k) {
                    obj = ((MapContainerImpl) MapObjectImpl.d(d2)).c(mapObject);
                    if (obj != null) {
                        break;
                    }
                }
                Cluster a = a((ViewObject) mapObject);
                if (a != null) {
                    list.add(ae.a(new ae(a)));
                }
            }
            if (obj != null) {
                list.add(obj);
            }
        }
    }

    public synchronized void a(Projection projection) {
        boolean z = projection == Projection.GLOBE || projection == Projection.MERCATOR;
        dy.a(z, "Deprecated Projection mode.");
        setViewTypeNative(c(projection));
        b(projection);
    }

    public synchronized Projection o() {
        return c(getViewType());
    }

    public void g(boolean z) {
        h(z);
    }

    public boolean p() {
        return getPanoramaCoverageEnabled();
    }

    public synchronized void h(boolean z) {
        if (z) {
            if (this.h == null) {
                this.h = new PanoramaCoverageRasterTileSource();
            }
            b(c(getViewType()));
            a(this.h);
        } else if (this.h != null) {
            b(this.h);
        }
        setPanoramaCoverageEnabledNative(z);
    }

    private void b(Projection projection) {
        if (this.h != null) {
            if (projection == Projection.GLOBE) {
                this.h.b();
            } else {
                this.h.a();
            }
        }
    }

    public void i(boolean z) {
        this.at.add(new 11(this, z));
        redraw();
    }

    public synchronized boolean j(boolean z) {
        boolean z2;
        if (isExtrudedBuildingsVisible() == z) {
            z2 = false;
        } else {
            z2 = setExtrudedBuildingsVisibleNative(z);
            if (z && z2) {
                this.B.i();
            }
        }
        return z2;
    }

    public MapBuildingLayer q() {
        return MapBuildingLayerImpl.a(getExtrudedBuildingsLayer());
    }

    @OnlineNative
    private void onMapSchemeChanged(String str) {
        g(str);
    }

    @OnlineNative
    private void onRenderBufferCreated() {
        M();
    }

    @OnlineNative
    private void mapMoveBegin() {
        G();
    }

    @OnlineNative
    private void onTiltChangeStart() {
        G();
    }

    @OnlineNative
    private void onScaleChangeStart() {
        G();
    }

    @OnlineNative
    private void onOrientationChangeStart() {
        G();
    }

    public boolean r() {
        return this.p.get() > 0;
    }

    public void s() {
        this.p.set(0);
    }

    public void a(GeoCoordinate geoCoordinate, Animation animation, double d, float f, float f2) {
        if (geoCoordinate != null && geoCoordinate.isValid()) {
            a(GeoCoordinateImpl.get(geoCoordinate), animation, d, f, f2);
        }
    }

    public void a(GeoCoordinate geoCoordinate, Animation animation) {
        if (geoCoordinate != null && geoCoordinate.isValid()) {
            a(GeoCoordinateImpl.get(geoCoordinate), animation);
        }
    }

    public void a(GeoBoundingBox geoBoundingBox, Animation animation, float f) {
        a(GeoBoundingBoxImpl.get(geoBoundingBox), animation, f);
    }

    public void a(GeoBoundingBox geoBoundingBox, Animation animation, float f, float f2) {
        a(GeoBoundingBoxImpl.get(geoBoundingBox), animation, f, f2);
    }

    public void a(GeoBoundingBox geoBoundingBox, ViewRect viewRect, Animation animation, float f) {
        a(GeoBoundingBoxImpl.get(geoBoundingBox), viewRect, animation, f);
    }

    public void a(GeoBoundingBox geoBoundingBox, int i, int i2, Animation animation, float f) {
        a(GeoBoundingBoxImpl.get(geoBoundingBox), i, i2, animation, f);
    }

    public synchronized PixelResult a(GeoCoordinate geoCoordinate) {
        return a(GeoCoordinateImpl.get(geoCoordinate));
    }

    public synchronized PixelResult a(GeoCoordinateImpl geoCoordinateImpl) {
        if (geoCoordinateImpl.d()) {
        } else {
            throw new IllegalArgumentException("GeoCoordinate supplied is invalid.");
        }
        return geoToPixel(geoCoordinateImpl.a(), geoCoordinateImpl.b(), geoCoordinateImpl.c());
    }

    public synchronized void b(ViewRect viewRect) {
        setViewRect(viewRect.getX(), viewRect.getY(), viewRect.getWidth(), viewRect.getHeight(), d().x, d().y);
        redraw();
    }

    public ViewRect e(PointF pointF) {
        return new ViewRect(((int) pointF.x) - ((this.n - 1) >> 1), ((int) pointF.y) - ((this.o - 1) >> 1), this.n, this.o);
    }

    public synchronized void a(ViewRect viewRect, PointF pointF) {
        float f = -1.0f;
        synchronized (this) {
            float f2;
            if (pointF != null) {
                f2 = pointF.x;
                f = pointF.y;
            } else {
                f2 = -1.0f;
            }
            setClipRect(viewRect.getX(), viewRect.getY(), viewRect.getWidth(), viewRect.getHeight(), f2, f);
            redraw();
        }
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.b <= 0 || this.c <= 0) {
            throw new RuntimeException("Width and height must be > 0.");
        } else if (onScreenCaptureListener == null) {
            throw new IllegalArgumentException("OnScreenCaptureListener is null");
        } else {
            this.as.add(new d(this, onScreenCaptureListener));
            redraw();
        }
    }

    public synchronized boolean a(MapRasterTileSource mapRasterTileSource) {
        boolean z = false;
        synchronized (this) {
            try {
                this.am.remove(mapRasterTileSource);
                if (!this.g.contains(mapRasterTileSource)) {
                    MapRasterTileSourceImpl a = MapRasterTileSourceImpl.a(mapRasterTileSource);
                    z = addRasterTileSourceNative(a);
                    if (z) {
                        this.g.add(mapRasterTileSource);
                        redraw();
                        Object url = a.getUrl(0, 0, 1);
                        if (!(TextUtils.isEmpty(url) || url.contains("here.com"))) {
                            this.B.g(getMapScheme());
                        }
                        if (mapRasterTileSource.getClass() == HistoricalTrafficRasterTileSource.class) {
                            this.B.f(getMapScheme());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public boolean b(MapRasterTileSource mapRasterTileSource) {
        this.am.add(mapRasterTileSource);
        this.at.addIfAbsent(this.an);
        redraw();
        return true;
    }

    public synchronized void v() {
        for (MapRasterTileSource c : this.am) {
            c(c);
        }
        if (this.am.size() > 0) {
            redraw();
        }
        this.am.clear();
    }

    private boolean c(MapRasterTileSource mapRasterTileSource) {
        boolean removeRasterTileSourceNative = removeRasterTileSourceNative(MapRasterTileSourceImpl.a(mapRasterTileSource));
        if (removeRasterTileSourceNative) {
            this.g.remove(mapRasterTileSource);
        }
        return removeRasterTileSourceNative;
    }

    void w() {
        if (this.g != null && !this.ar.get()) {
            for (MapRasterTileSource a : this.g) {
                addRasterTileSourceNative(MapRasterTileSourceImpl.a(a));
            }
            redraw();
            this.ar.set(true);
        }
    }

    public MapTransitLayer x() {
        return MapTransitLayerImpl.a(getMapTransitLayerNative());
    }

    public MapTrafficLayer y() {
        if (this.ao == null) {
            synchronized (this) {
                if (this.ao == null) {
                    this.ao = MapTrafficLayerImpl.a(getMapTrafficLayerNative());
                }
            }
        }
        return this.ao;
    }

    public void a(int[] iArr, boolean z) {
        if (!this.F) {
            setLayerCategory(iArr, z);
            this.G = getLayerCategory();
            redraw();
        }
    }

    public CustomizableScheme b(String str, String str2) {
        dy.a(str, "newSchemeName cannot be null");
        dy.a(str2, "baseSchemeName cannot be null");
        if (!e(str2) && !f(str2)) {
            throw new InvalidParameterException("baseSchemeName is not valid");
        } else if (e(str) || f(str)) {
            throw new InvalidParameterException("newSchemeName already exist");
        } else {
            if (str2.contains("satellite") || str2.contains("hybrid")) {
                ApplicationContext.b().check(17, this.U);
            }
            if (str2.contains("truck")) {
                ApplicationContext.b().check(15, this.V);
            }
            ApplicationContext.b().check(43, this.ap);
            CustomizableScheme a = CustomizableSchemeImpl.a(createCustomizableSchemeNative(str, str2));
            if (a != null) {
                d("createscheme");
            }
            return a;
        }
    }

    public boolean b(String str) {
        dy.a(str, "schemeName cannot be null");
        if (e(str)) {
            throw new InvalidParameterException("Scheme is not removable");
        } else if (!f(str)) {
            throw new InvalidParameterException("Scheme is not valid");
        } else if (!str.equals(getMapScheme())) {
            return removeCustomizableSchemeNative(str);
        } else {
            throw new InvalidParameterException("Current Scheme can not be removed");
        }
    }

    public CustomizableScheme c(String str) {
        dy.a(str, "schemeName cannot be null");
        if (e(str)) {
            throw new InvalidParameterException("This scheme is not configurable");
        } else if (f(str)) {
            return CustomizableSchemeImpl.a(getCustomizableSchemeNative(str));
        } else {
            return null;
        }
    }

    private void c(double d) {
        boolean z = this.F;
        this.F = inChinaNative(getCenterNative(), d);
        if (!z && this.F) {
            this.G = getLayerCategory();
            setLayerCategory(this.J, false);
            if (this.T == null) {
                this.T = Boolean.valueOf(f());
            }
            a(false, true);
        } else if (z && !this.F) {
            setLayerCategory(this.G, true);
            a(this.T.booleanValue(), true);
        }
    }

    public void k(boolean z) {
        if (this.i != null) {
            dw.a(this.i).d(z);
        }
    }

    public void a(InfoBubbleAdapter infoBubbleAdapter) {
        this.aq = infoBubbleAdapter;
    }

    InfoBubbleAdapter z() {
        return this.aq;
    }

    void A() {
        if (this.g != null && this.ar.get()) {
            for (MapRasterTileSource a : this.g) {
                removeRasterTileSourceNative(MapRasterTileSourceImpl.a(a));
            }
            this.ar.set(false);
        }
    }

    void B() {
        F();
        O();
        synchronized (this.at) {
            Iterator it = this.at.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            this.at.clear();
        }
    }

    void C() {
        synchronized (this.as) {
            Iterator it = this.as.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            this.as.clear();
        }
    }

    public void a(Runnable runnable) {
        this.as.add(runnable);
    }

    public void b(Runnable runnable) {
        this.at.add(runnable);
    }

    public void D() {
        this.as.clear();
        this.at.clear();
    }

    public void c(Runnable runnable) {
        a(runnable, 0);
    }

    public void a(Runnable runnable, long j) {
        this.au.a(runnable, j);
    }

    public synchronized void a(PointF pointF, Animation animation, double d, float f, float f2) {
        if (f2 != -1.0f) {
            if (f2 > getMaxTilt() || f2 < getMinTilt()) {
                throw new IllegalArgumentException("New tilt value is out of range.");
            }
        }
        if (d != Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            if (d > getMaxZoomLevel() || d < getMinZoomLevel()) {
                throw new IllegalArgumentException("New zoom level value is out of range.");
            }
        }
        I();
        G();
        moveTo(pointF.x, pointF.y, b(animation), d, f, f2);
        if (d == Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            d = getZoomLevel();
        }
        c(d);
    }

    public void a(PointF pointF, PointF pointF2) {
        a(pointF.x, pointF.y, pointF2.x, pointF2.y);
    }

    private static final int b(Animation animation) {
        switch (17.c[animation.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            case 3:
                return 2;
            default:
                throw new IllegalArgumentException("Animation value not recognized.");
        }
    }

    private static final int c(Projection projection) {
        switch (17.d[projection.ordinal()]) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                throw new IllegalArgumentException("Projection value not recognized.");
        }
    }

    private static final Projection c(int i) {
        switch (i) {
            case 0:
                return Projection.MERCATOR;
            case 1:
                return Projection.GLOBE;
            default:
                throw new IllegalArgumentException("Projection value not recognized.");
        }
    }
}
