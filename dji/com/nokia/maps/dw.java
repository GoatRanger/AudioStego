package com.nokia.maps;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import com.facebook.internal.af;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.here.android.mpa.common.PositioningManager.LocationStatus;
import com.here.android.mpa.common.PositioningManager.OnPositionChangedListener;
import com.here.android.mpa.guidance.NavigationManager;
import com.here.android.mpa.guidance.NavigationManager$NavigationState;
import com.here.android.mpa.guidance.NavigationManager$RoadView$Listener;
import com.here.android.mpa.mapping.Map$OnTransformListener;
import com.here.android.mpa.mapping.MapCircle;
import com.here.android.mpa.mapping.MapContainer;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapState;
import com.here.android.mpa.mapping.OnMapRenderListener;
import com.here.android.mpa.mapping.PositionIndicator;
import dji.pilot2.multimoment.videolib.c;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class dw implements OnPositionChangedListener, NavigationManager$RoadView$Listener, Map$OnTransformListener, OnMapRenderListener {
    private static am<PositionIndicator, dw> a;
    private static k<PositionIndicator, dw> b;
    private static String c = "default";
    private static String d = af.o;
    private static final String e = dw.class.getSimpleName();
    private cq f = new cq(dw.class.getName());
    private MapImpl g;
    private PositioningManagerImpl h;
    private MapMarker i;
    private MapCircle j;
    private MapContainer k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private double q = 1.073741824E9d;
    private boolean r = false;
    private HashMap<String, a> s = new HashMap();
    private a t;
    private boolean u = false;
    private boolean v = false;
    private GeoCoordinate w = null;
    private PointF x = null;

    public static class a {
        public MapMarker a;
        public MapMarker b;
        public MapMarker c;
        public MapMarker d;

        public a(MapMarker mapMarker) {
            mapMarker.setVisible(false);
            this.a = mapMarker;
            this.b = mapMarker;
            this.c = mapMarker;
            this.d = mapMarker;
        }
    }

    public static void a(k<PositionIndicator, dw> kVar, am<PositionIndicator, dw> amVar) {
        a = amVar;
        b = kVar;
    }

    static PositionIndicator a(dw dwVar) {
        if (dwVar != null) {
            return (PositionIndicator) a.a(dwVar);
        }
        return null;
    }

    static dw a(PositionIndicator positionIndicator) {
        if (b != null) {
            return (dw) b.a(positionIndicator);
        }
        return null;
    }

    static {
        ce.a(PositionIndicator.class);
    }

    public dw(Context context, MapImpl mapImpl) {
        this.g = mapImpl;
        this.k = new MapContainer();
        byte[] a = ResourceManager.a(context, "./res/images/tracker_dot_20px.png");
        if (a.length > 0) {
            Image image = new Image();
            if (BitmapFactory.decodeByteArray(a, 0, a.length) != null) {
                image.setBitmap(BitmapFactory.decodeByteArray(a, 0, a.length));
                MapMarker mapMarker = new MapMarker();
                mapMarker.setIcon(image);
                a(c, new a(mapMarker));
            }
        }
        this.j = new MapCircle();
        this.j.setFillColor(Color.argb(76, 61, 137, 12));
        this.j.setLineWidth(0);
        this.k.addMapObject(this.j);
        this.g.a(this.k, false);
        this.k.setZIndex(MapObjectImpl.b - 1);
        this.l = false;
        this.m = false;
        this.n = false;
        this.v = this.g.getTilt() > 0.0f;
        b(c);
        this.k.setVisible(false);
        this.g.a(this);
        try {
            this.h = PositioningManagerImpl.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.p = true;
    }

    private void f() {
        if (this.h != null) {
            this.h.a(new WeakReference(this));
        }
        if (this.p) {
            NavigationManager instance = NavigationManager.getInstance();
            if (instance != null) {
                instance.getRoadView().addListener(new WeakReference(this));
            }
        }
    }

    private void g() {
        if (this.h != null) {
            this.h.a((OnPositionChangedListener) this);
        }
        if (this.p) {
            NavigationManager instance = NavigationManager.getInstance();
            if (instance != null) {
                instance.getRoadView().removeListener(this);
            }
        }
    }

    public void a(String str, a aVar) {
        if (!this.s.containsKey(str)) {
            aVar.a.setVisible(false);
            aVar.b.setVisible(false);
            aVar.d.setVisible(false);
            aVar.d.setVisible(false);
            this.k.addMapObject(aVar.a);
            this.k.addMapObject(aVar.b);
            this.k.addMapObject(aVar.c);
            this.k.addMapObject(aVar.d);
            this.s.put(str, aVar);
        }
    }

    public void a(String str) {
        a aVar = (a) this.s.get(str);
        if (aVar != null) {
            if (this.t.equals(aVar)) {
                b(c);
            }
            this.k.removeMapObject(aVar.a);
            this.k.removeMapObject(aVar.b);
            this.k.removeMapObject(aVar.c);
            this.k.removeMapObject(aVar.d);
            this.s.remove(str);
        }
    }

    public void b(String str) {
        a aVar = (a) this.s.get(str);
        if (aVar != null) {
            this.t = aVar;
            a(h());
        }
    }

    private MapMarker h() {
        if (this.v) {
            if (this.u) {
                return this.t.c;
            }
            return this.t.d;
        } else if (this.u) {
            return this.t.a;
        } else {
            return this.t.b;
        }
    }

    private void a(MapMarker mapMarker) {
        if (mapMarker != this.i) {
            GeoCoordinate coordinate;
            if (this.i != null) {
                this.i.setVisible(false);
                coordinate = this.i.getCoordinate();
            } else {
                coordinate = new GeoCoordinate(0.0d, 0.0d);
            }
            mapMarker.setCoordinate(coordinate);
            mapMarker.setVisible(true);
            this.i = mapMarker;
            this.g.redraw();
        }
    }

    public void a(Image image) {
        if (image.isValid()) {
            a(d);
            MapMarker mapMarker = new MapMarker();
            mapMarker.setIcon(image);
            a(d, new a(mapMarker));
            b(d);
            return;
        }
        throw new IllegalArgumentException("Marker is invalid.");
    }

    public Image a() {
        return ((a) this.s.get(d)).a.getIcon();
    }

    public void a(boolean z) {
        this.l = z;
    }

    public void b(boolean z) {
        this.m = z;
        boolean z2 = this.o;
        if (this.m) {
            f();
            onPositionUpdated(this.h.h(), this.h.e(), z2);
            return;
        }
        g();
        this.k.setVisible(false);
        this.g.redraw();
    }

    public boolean b() {
        return this.m;
    }

    public void c(boolean z) {
        this.n = z;
        boolean z2 = this.o;
        if (this.n) {
            onPositionUpdated(this.h.h(), this.h.e(), z2);
            return;
        }
        this.j.setVisible(false);
        this.g.redraw();
    }

    public boolean c() {
        return this.n;
    }

    public int d() {
        return this.k.getZIndex();
    }

    public void a(int i) {
        this.k.setZIndex(i);
    }

    public void d(boolean z) {
        this.p = z;
    }

    private boolean i() {
        if (!this.p) {
            return false;
        }
        NavigationManager instance = NavigationManager.getInstance();
        if (instance == null || instance.getRunningState() != NavigationManager$NavigationState.RUNNING) {
            return false;
        }
        return true;
    }

    public boolean e() {
        if (!this.p) {
            return false;
        }
        NavigationManager instance = NavigationManager.getInstance();
        if (instance == null || !instance.getRoadView().a()) {
            return false;
        }
        return true;
    }

    private GeoCoordinate a(GeoCoordinate geoCoordinate) {
        if (this.g.getMapScheme().compareTo("3d.hybrid.day") != 0) {
            geoCoordinate.setAltitude(1.073741824E9d);
        }
        return geoCoordinate;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPositionUpdated(com.here.android.mpa.common.PositioningManager.LocationMethod r13, com.here.android.mpa.common.GeoPosition r14, boolean r15) {
        /*
        r12 = this;
        r4 = 4742290407621132288; // 0x41d0000000000000 float:0.0 double:1.073741824E9;
        r0 = 1;
        r1 = 0;
        r2 = r14.isValid();
        if (r2 == 0) goto L_0x0052;
    L_0x000a:
        r2 = e;
        r3 = "IN - position=(%.5f, %5f).isValid=%B method=%s m_visible=%B mapMatched=%B";
        r6 = 6;
        r6 = new java.lang.Object[r6];
        r7 = r14.getCoordinate();
        r8 = r7.getLatitude();
        r7 = java.lang.Double.valueOf(r8);
        r6[r1] = r7;
        r7 = r14.getCoordinate();
        r8 = r7.getLongitude();
        r7 = java.lang.Double.valueOf(r8);
        r6[r0] = r7;
        r7 = 2;
        r8 = r14.isValid();
        r8 = java.lang.Boolean.valueOf(r8);
        r6[r7] = r8;
        r7 = 3;
        r8 = r13.toString();
        r6[r7] = r8;
        r7 = 4;
        r8 = r12.m;
        r8 = java.lang.Boolean.valueOf(r8);
        r6[r7] = r8;
        r7 = 5;
        r8 = java.lang.Boolean.valueOf(r15);
        r6[r7] = r8;
        com.nokia.maps.bj.a(r2, r3, r6);
    L_0x0052:
        r12.o = r15;
        r2 = r14.isValid();
        if (r2 == 0) goto L_0x0132;
    L_0x005a:
        r2 = r12.m;
        if (r2 == 0) goto L_0x0132;
    L_0x005e:
        r7 = r12.g;
        monitor-enter(r7);
        r2 = r14.getCoordinate();	 Catch:{ all -> 0x012f }
        r8 = r12.a(r2);	 Catch:{ all -> 0x012f }
        r2 = r12.k;	 Catch:{ all -> 0x012f }
        r2 = r2.isVisible();	 Catch:{ all -> 0x012f }
        if (r2 != 0) goto L_0x0149;
    L_0x0071:
        r2 = r12.k;	 Catch:{ all -> 0x012f }
        r3 = 1;
        r2.setVisible(r3);	 Catch:{ all -> 0x012f }
        r6 = r0;
    L_0x0078:
        r2 = r12.j;	 Catch:{ all -> 0x012f }
        r9 = r2.isVisible();	 Catch:{ all -> 0x012f }
        r2 = r12.n;	 Catch:{ all -> 0x012f }
        if (r2 == 0) goto L_0x00dc;
    L_0x0082:
        r2 = r14.getLatitudeAccuracy();	 Catch:{ all -> 0x012f }
        r2 = (double) r2;	 Catch:{ all -> 0x012f }
        r10 = r14.getLongitudeAccuracy();	 Catch:{ all -> 0x012f }
        r10 = (double) r10;	 Catch:{ all -> 0x012f }
        r2 = r12.a(r2, r10);	 Catch:{ all -> 0x012f }
        r10 = r12.j;	 Catch:{ all -> 0x012f }
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 == 0) goto L_0x00da;
    L_0x0096:
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x00da;
    L_0x009c:
        r4 = r0;
    L_0x009d:
        r10.setVisible(r4);	 Catch:{ all -> 0x012f }
    L_0x00a0:
        r4 = r12.j;	 Catch:{ all -> 0x012f }
        r4 = r4.isVisible();	 Catch:{ all -> 0x012f }
        if (r4 == 0) goto L_0x00b8;
    L_0x00a8:
        r4 = r12.e();	 Catch:{ all -> 0x012f }
        if (r4 != 0) goto L_0x00b8;
    L_0x00ae:
        r4 = r12.j;	 Catch:{ all -> 0x012f }
        r4.setCenter(r8);	 Catch:{ all -> 0x012f }
        r4 = r12.j;	 Catch:{ all -> 0x012f }
        r4.setRadius(r2);	 Catch:{ all -> 0x012f }
    L_0x00b8:
        r2 = r12.j;	 Catch:{ all -> 0x012f }
        r2 = r2.isVisible();	 Catch:{ all -> 0x012f }
        if (r9 == r2) goto L_0x00e4;
    L_0x00c0:
        r0 = r0 | r6;
        r2 = r12.w;	 Catch:{ all -> 0x012f }
        if (r2 == 0) goto L_0x00e6;
    L_0x00c5:
        r2 = r12.w;	 Catch:{ all -> 0x012f }
        r2 = r8.distanceTo(r2);	 Catch:{ all -> 0x012f }
        r4 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x00e6;
    L_0x00d1:
        if (r0 == 0) goto L_0x00d8;
    L_0x00d3:
        r0 = r12.g;	 Catch:{ all -> 0x012f }
        r0.redraw();	 Catch:{ all -> 0x012f }
    L_0x00d8:
        monitor-exit(r7);	 Catch:{ all -> 0x012f }
    L_0x00d9:
        return;
    L_0x00da:
        r4 = r1;
        goto L_0x009d;
    L_0x00dc:
        r2 = r12.j;	 Catch:{ all -> 0x012f }
        r3 = 0;
        r2.setVisible(r3);	 Catch:{ all -> 0x012f }
        r2 = r4;
        goto L_0x00a0;
    L_0x00e4:
        r0 = r1;
        goto L_0x00c0;
    L_0x00e6:
        r0 = r12.e();	 Catch:{ all -> 0x012f }
        if (r0 != 0) goto L_0x00f3;
    L_0x00ec:
        r12.w = r8;	 Catch:{ all -> 0x012f }
        r0 = r12.i;	 Catch:{ all -> 0x012f }
        r0.setCoordinate(r8);	 Catch:{ all -> 0x012f }
    L_0x00f3:
        r0 = r12.h();	 Catch:{ all -> 0x012f }
        r12.a(r0);	 Catch:{ all -> 0x012f }
        r0 = r12.l;	 Catch:{ all -> 0x012f }
        if (r0 == 0) goto L_0x0124;
    L_0x00fe:
        r0 = r12.i();	 Catch:{ all -> 0x012f }
        if (r0 != 0) goto L_0x0124;
    L_0x0104:
        r0 = e;	 Catch:{ all -> 0x012f }
        r2 = "Tracking is ON - setting map center to (%s)...";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x012f }
        r4 = 0;
        r5 = r14.getCoordinate();	 Catch:{ all -> 0x012f }
        r5 = r5.toString();	 Catch:{ all -> 0x012f }
        r3[r4] = r5;	 Catch:{ all -> 0x012f }
        com.nokia.maps.bj.a(r0, r2, r3);	 Catch:{ all -> 0x012f }
        r0 = r12.g;	 Catch:{ all -> 0x012f }
        r2 = r14.getCoordinate();	 Catch:{ all -> 0x012f }
        r3 = com.here.android.mpa.mapping.Map$Animation.LINEAR;	 Catch:{ all -> 0x012f }
        r0.a(r2, r3);	 Catch:{ all -> 0x012f }
    L_0x0124:
        monitor-exit(r7);	 Catch:{ all -> 0x012f }
    L_0x0125:
        r0 = e;
        r2 = "OUT";
        r1 = new java.lang.Object[r1];
        com.nokia.maps.bj.a(r0, r2, r1);
        goto L_0x00d9;
    L_0x012f:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x012f }
        throw r0;
    L_0x0132:
        r0 = e;
        r2 = "Setting m_mapLayer to invisible ...";
        r3 = new java.lang.Object[r1];
        com.nokia.maps.bj.e(r0, r2, r3);
        r0 = r12.k;
        r0 = r0.isVisible();
        if (r0 == 0) goto L_0x0125;
    L_0x0143:
        r0 = r12.k;
        r0.setVisible(r1);
        goto L_0x0125;
    L_0x0149:
        r6 = r1;
        goto L_0x0078;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.dw.onPositionUpdated(com.here.android.mpa.common.PositioningManager$LocationMethod, com.here.android.mpa.common.GeoPosition, boolean):void");
    }

    public void onPositionFixChanged(LocationMethod locationMethod, LocationStatus locationStatus) {
        bj.a(e, "IN - method=%s status=%s", new Object[]{locationMethod.toString(), locationStatus.toString()});
        if (locationMethod == LocationMethod.GPS) {
            this.u = locationStatus == LocationStatus.AVAILABLE;
            bj.e(e, "Setting m_hasGps=%B", new Object[]{Boolean.valueOf(this.u)});
        }
        a(h());
        bj.a(e, "OUT - method=%s status=%s", new Object[]{locationMethod.toString(), locationStatus.toString()});
    }

    private double a(double d, double d2) {
        double min = Math.min(d, d2);
        if (min < 1.073741824E9d && min > c.c * this.q) {
            if (this.r) {
                this.r = false;
            } else {
                this.r = true;
                min = this.q;
            }
        }
        this.q = min;
        return min;
    }

    public void onMapTransformStart() {
    }

    public void onMapTransformEnd(MapState mapState) {
        a(mapState.getTilt());
    }

    private void a(float f) {
        this.v = f > 0.0f;
        a(h());
    }

    public void onPositionChanged(GeoCoordinate geoCoordinate) {
        if (e() && geoCoordinate.isValid()) {
            this.w = a(geoCoordinate);
            this.x = this.g.a(this.w).getResult();
            this.i.setCoordinate(this.w);
            this.j.setCenter(this.w);
        }
    }

    public void onPreDraw() {
        if (e() && this.x != null && !this.g.r() && !this.g.a(this.i.getCoordinate()).getResult().equals(this.x.x, this.x.y)) {
            GeoCoordinate b = this.g.b(this.x);
            if (this.i.getCoordinate().distanceTo(b) < this.g.b(this.g.getZoomLevel()) / 400.0d) {
                this.i.setCoordinate(b);
                this.j.setCenter(b);
            }
        }
    }

    public void onPostDraw(boolean z, long j) {
    }

    public void onSizeChanged(int i, int i2) {
    }

    public void onGraphicsDetached() {
    }

    public void onRenderBufferCreated() {
    }
}
