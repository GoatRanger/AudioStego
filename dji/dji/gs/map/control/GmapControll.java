package dji.gs.map.control;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.here.android.mpa.mapping.Map;
import com.here.odnp.config.OdnpConfigStatic;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.R;
import dji.gs.b.c;
import dji.gs.c.e;
import dji.gs.c.e.a;
import dji.gs.e.b;
import dji.gs.views.EventView;
import dji.gs.views.d;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.DJIFlightLimitAreaModel;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.FlyForbidElement;
import dji.midware.data.forbid.FlyForbidProtocol.LevelType;
import dji.midware.natives.FlyForbid;
import dji.midware.natives.FlyForbid.FlyForbidParam;
import dji.pilot.usercenter.mode.n;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GmapControll implements OnMapClickListener, OnMarkerClickListener, e {
    private static final int bk = 120000;
    public static LatLng i;
    private ArrayList<Marker> A = new ArrayList();
    private ArrayList<Marker> B = new ArrayList();
    private ArrayList<Marker> C = new ArrayList();
    private ArrayList<Marker> D = new ArrayList();
    private ArrayList<ArrayList<Circle>> E = new ArrayList();
    private int F;
    private c G;
    private ArrayList<Marker> H = new ArrayList();
    private GoogleMap I;
    private Polyline J;
    private Polyline K;
    private Polyline L;
    private Polyline M;
    private Polyline N;
    private ArrayList<Polyline> O = new ArrayList();
    private Polyline P;
    private Polyline Q;
    private ArrayList<Polyline> R = new ArrayList();
    private Context S;
    private int T = -1;
    private int U = 0;
    private Marker V;
    private Marker W;
    private BitmapDescriptor X;
    private BitmapDescriptor Y;
    private BitmapDescriptor Z;
    private Circle aA = null;
    private boolean aB = false;
    private int aC;
    private final int aD;
    private final int aE;
    private final int aF = Color.HSVToColor(200, new float[]{0.0f, 1.0f, 1.0f});
    private final int aG = Color.HSVToColor(60, new float[]{0.0f, 1.0f, 1.0f});
    private final int aH = Color.HSVToColor(120, new float[]{0.0f, 1.0f, 1.0f});
    private final int aI = Color.argb(90, 255, 255, 0);
    private final int aJ = Color.argb(40, 255, 255, 0);
    private final int aK = Color.argb(90, 255, 255, 255);
    private final int aL = Color.argb(30, 255, 255, 255);
    private final int aM = Color.argb(90, 0, 0, 255);
    private final int aN = Color.argb(0, 0, 0, 0);
    private final float aO = 15.5f;
    private boolean aP = true;
    private boolean aQ = false;
    private int aR = 0;
    private ArrayList<FlyForbidElement> aS = new ArrayList();
    private FlyForbidParam aT;
    private double aU = 1000000.0d;
    private boolean aV = false;
    private boolean aW = false;
    private int aX = 16;
    private dji.gs.views.c aY;
    private d aZ;
    private BitmapDescriptor aa;
    private BitmapDescriptor ab;
    private View ac;
    private Bitmap ad;
    private SharedPreferences ae;
    private String af = "map_type";
    private int ag = 4;
    private BitmapDescriptor ah;
    private BitmapDescriptor ai;
    private BitmapDescriptor aj = null;
    private BitmapDescriptor ak;
    private int al;
    private int am;
    private int an;
    private BitmapDescriptor ao;
    private boolean ap = false;
    private ArrayList<b> aq = new ArrayList();
    private ArrayList<Circle> ar = new ArrayList();
    private final int as = Color.argb(FTPCodes.DATA_CONNECTION_ALREADY_OPEN, 0, 255, 0);
    private final int at = Color.argb(70, 0, 255, 0);
    private float au = 0.0f;
    private boolean av = true;
    private Circle aw;
    private LatLng ax;
    private Marker ay;
    private boolean az = true;
    private Handler ba = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            Point point = (Point) message.obj;
            switch (message.what) {
                case 0:
                    if (GmapControll.this.aY == null) {
                        GmapControll.this.aY = new dji.gs.views.c(GmapControll.this.S, GmapControll.this, GmapControll.this.ac);
                    }
                    GmapControll.this.aY.a(point.x, point.y);
                    break;
                case 1:
                    if (GmapControll.this.aZ == null) {
                        GmapControll.this.aZ = new d(GmapControll.this.S, GmapControll.this, GmapControll.this.ac);
                    }
                    GmapControll.this.aZ.a(point.x, point.y);
                    break;
            }
            return false;
        }
    });
    private dji.gs.d.e bb;
    private Marker bc;
    private boolean bd;
    private CameraPosition be;
    private dji.gs.d.b bf;
    private dji.gs.d.c bg;
    private LocationManager bh = null;
    private LocationListener bi = null;
    private Location bj = null;
    private Marker bl = null;
    private BitmapDescriptor bm = null;
    private Circle bn = null;
    private boolean bo = false;
    protected boolean j = true;
    private ArrayList<MarkerItem> k = new ArrayList();
    private ArrayList<Point> l = new ArrayList();
    private ArrayList<LatLng> m = new ArrayList();
    private ArrayList<b> n = new ArrayList();
    private ArrayList<Circle> o = new ArrayList();
    private ArrayList<b> p = new ArrayList();
    private ArrayList<Circle> q = new ArrayList();
    private ArrayList<b> r = new ArrayList();
    private ArrayList<Circle> s = new ArrayList();
    private ArrayList<b> t = new ArrayList();
    private ArrayList<Circle> u = new ArrayList();
    private ArrayList<b> v = new ArrayList();
    private ArrayList<Circle> w = new ArrayList();
    private ArrayList<Circle> x = new ArrayList();
    private ArrayList<Polygon> y = new ArrayList();
    private ArrayList<Marker> z = new ArrayList();

    private enum AirmapMarkerType {
        AIRPORT,
        HELICOPTER
    }

    public class MarkerItem extends dji.gs.e.c {
        public dji.gs.views.b icon;
        public dji.gs.e.e info;
        public Marker marker;
        public Point point;

        public Object getMarker() {
            return this.marker;
        }

        public dji.gs.e.e getInfo() {
            return this.info;
        }

        public dji.gs.views.b getIcon() {
            return this.icon;
        }

        public Point getPoint() {
            return this.point;
        }
    }

    public /* synthetic */ dji.gs.e.c b(int i) {
        return i(i);
    }

    public GmapControll(Context context, GoogleMap googleMap, View view) {
        int i = 0;
        this.I = googleMap;
        this.S = context;
        this.ac = view;
        dji.thirdparty.a.c.a().e(a.GoogleMap);
        this.ae = PreferenceManager.getDefaultSharedPreferences(context);
        if (this.ae != null) {
            this.ag = this.ae.getInt(this.af, 4);
        }
        if (this.ag == 1) {
            this.I.setMapType(1);
        } else if (this.ag == 2) {
            this.I.setMapType(2);
        } else if (this.ag == 4) {
            this.I.setMapType(4);
        } else {
            this.I.setMapType(4);
        }
        this.I.setOnMapClickListener(this);
        this.I.setOnMarkerClickListener(this);
        this.I.setOnCameraChangeListener(new OnCameraChangeListener() {
            public void onCameraChange(CameraPosition cameraPosition) {
                Iterator it = GmapControll.this.B.iterator();
                while (it.hasNext()) {
                    ((Marker) it.next()).setVisible(false);
                }
                DJILogHelper.getInstance().LOGD("nfz", "" + cameraPosition.zoom, false, true);
            }
        });
        this.X = BitmapDescriptorFactory.fromResource(R.drawable.gs_home_annotation);
        this.Y = BitmapDescriptorFactory.fromResource(R.drawable.gs_attitude_aircraft);
        this.Z = BitmapDescriptorFactory.fromResource(R.drawable.gs_marker_normal);
        this.aa = BitmapDescriptorFactory.fromResource(R.drawable.gs_airport_icon);
        this.ab = BitmapDescriptorFactory.fromResource(R.drawable.gs_helicopter_icon);
        this.ah = BitmapDescriptorFactory.fromResource(R.drawable.gs_user_annotation_image);
        this.ai = BitmapDescriptorFactory.fromResource(R.drawable.gs_user_annotation_bad_image);
        this.ak = BitmapDescriptorFactory.fromResource(R.drawable.gs_fly_direction);
        this.ao = BitmapDescriptorFactory.fromResource(R.drawable.my_flight_photo);
        this.aD = this.S.getResources().getColor(R.color.gs_home_stroke);
        this.aE = this.S.getResources().getColor(R.color.gs_home_fill);
        this.ad = BitmapFactory.decodeResource(context.getResources(), R.drawable.gs_marker_normal);
        this.bm = BitmapDescriptorFactory.fromResource(R.drawable.gs_hot_point);
        this.al = this.S.getResources().getColor(R.color.gs_white);
        this.am = this.S.getResources().getColor(17170456);
        this.an = this.S.getResources().getColor(R.color.gs_white_half);
        this.G = c.getInstance(context);
        this.F = this.G.b().length;
        while (i != this.F) {
            this.E.add(new ArrayList());
            i++;
        }
    }

    public void h(int i) {
        this.I.setMapType(i);
    }

    private void K() {
        Iterator it = this.E.iterator();
        while (it.hasNext()) {
            ((ArrayList) it.next()).clear();
        }
    }

    public void a() {
        this.k.clear();
        this.k = null;
        this.l.clear();
        this.l = null;
        this.m.clear();
        this.m = null;
        this.n.clear();
        this.n = null;
        this.o.clear();
        this.o = null;
        this.p.clear();
        this.p = null;
        this.q.clear();
        this.q = null;
        this.r.clear();
        this.r = null;
        this.s.clear();
        this.s = null;
        this.t.clear();
        this.t = null;
        this.u.clear();
        this.u = null;
        this.v.clear();
        this.v = null;
        this.w.clear();
        this.w = null;
        this.x.clear();
        this.x = null;
        this.H.clear();
        this.H = null;
        this.O.clear();
        this.O = null;
        this.R.clear();
        this.R = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.P = null;
        this.Q = null;
        this.y.clear();
        this.y = null;
        this.z.clear();
        this.z = null;
        this.A.clear();
        this.A = null;
        this.B.clear();
        this.B = null;
        this.E.clear();
        this.E = null;
        this.C.clear();
        this.C = null;
        this.D.clear();
        this.D = null;
        this.bb = null;
        this.ba.removeCallbacksAndMessages(null);
        this.bh = null;
        this.bi = null;
        this.ba = null;
        this.S = null;
        this.ac = null;
        this.I.clear();
        this.I = null;
    }

    public int b() {
        return this.k == null ? -1 : this.k.size();
    }

    public ArrayList<MarkerItem> c() {
        return this.k;
    }

    public void a(int i, dji.gs.e.c cVar) {
        this.k.set(i, (MarkerItem) cVar);
    }

    public void a(int i) {
        MarkerItem i2 = i(i);
        a(i2);
        this.k.set(i, i2);
    }

    private void a(MarkerItem markerItem) {
        markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerItem.icon.d()));
    }

    public void a(int i, dji.gs.e.e eVar) {
        dji.gs.e.c i2 = i(i);
        i2.info = eVar;
        a(i, i2);
    }

    public MarkerItem i(int i) {
        if (i >= b()) {
            return null;
        }
        return (MarkerItem) this.k.get(i);
    }

    private int a(Marker marker) {
        int b = b();
        for (int i = 0; i < b; i++) {
            if (((MarkerItem) this.k.get(i)).marker.equals(marker)) {
                return i;
            }
        }
        return -1;
    }

    public int d() {
        return this.U;
    }

    public void c(int i) {
        this.U = i;
    }

    public void a(b bVar) {
        if (k(bVar) && this.bd) {
            d(bVar, this.al);
        }
    }

    public void b(b bVar) {
        k(bVar);
    }

    private boolean k(b bVar) {
        if (Math.abs(bVar.b) <= 2.0E-5d && Math.abs(bVar.c) <= 2.0E-5d) {
            return false;
        }
        b a = dji.gs.utils.a.a(bVar);
        if (this.V == null) {
            this.bc = this.I.addMarker(new MarkerOptions().draggable(false).position(l(a)).anchor(dji.pilot.visual.a.d.c, 1.0f).icon(this.ak));
            this.V = this.I.addMarker(new MarkerOptions().draggable(false).position(l(a)).anchor(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c).icon(this.Y));
        } else {
            this.V.setPosition(l(a));
            this.bc.setPosition(l(a));
        }
        return true;
    }

    public void c(b bVar) {
        a(bVar);
        d(bVar, this.am);
    }

    public void c(ArrayList<b> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList == null) {
            if (this.P != null) {
                this.P.remove();
            }
        } else if (arrayList.size() > 1) {
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList2.add(l(dji.gs.utils.a.a((b) arrayList.get(i))));
            }
            a(arrayList2, this.an);
        }
    }

    public void b(ArrayList<b> arrayList) {
        int i = 0;
        this.m.clear();
        if (arrayList == null) {
            while (i < this.O.size()) {
                ((Polyline) this.O.get(i)).remove();
                i++;
            }
            this.O.clear();
        } else if (arrayList.size() > 1) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.m.add(l(dji.gs.utils.a.a((b) arrayList.get(i2))));
            }
            Polyline b = b(this.m, this.am);
            a(b, this.am);
            while (i < this.O.size()) {
                ((Polyline) this.O.get(i)).remove();
                i++;
            }
            this.O.clear();
            this.O.add(b);
        }
    }

    private void a(Polyline polyline, int i) {
        if (i == this.am || i == this.an) {
            polyline.setWidth(polyline.getWidth() * 2.0f);
        }
    }

    public void d(ArrayList<b> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                this.H.add(this.I.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a((b) arrayList.get(i)))).anchor(dji.pilot.visual.a.d.c, 1.0f).icon(this.ao)));
            }
        }
    }

    public void z() {
        for (int i = 0; i < this.H.size(); i++) {
            ((Marker) this.H.get(i)).remove();
        }
        this.H.clear();
    }

    private void d(b bVar, int i) {
        LatLng latLng = this.m.size() > 0 ? (LatLng) this.m.get(this.m.size() - 1) : null;
        b a = dji.gs.utils.a.a(bVar);
        if (latLng == null || ((double) dji.gs.utils.c.a(a(latLng), a)) >= 0.1d) {
            this.m.add(l(a));
            k(i);
        }
    }

    private void k(int i) {
        if (this.m.size() > 1) {
            Polyline b = b(this.m, i);
            a(b, i);
            int size = this.O.size() - 1;
            if (this.m.size() > e.g) {
                LatLng latLng = (LatLng) this.m.get(this.m.size() - 1);
                ((Polyline) this.O.get(size)).remove();
                this.O.set(size, b);
                this.m.clear();
                this.m.add(latLng);
            } else if (size < 0 || this.m.size() <= 2) {
                this.O.add(b);
            } else {
                ((Polyline) this.O.get(size)).remove();
                this.O.set(size, b);
            }
        }
    }

    private void a(ArrayList<LatLng> arrayList, int i) {
        if (arrayList.size() > 1) {
            Polyline b = b((ArrayList) arrayList, i);
            a(b, i);
            if (this.P != null) {
                this.P.remove();
            }
            this.P = b;
        }
    }

    private Polyline b(ArrayList<LatLng> arrayList, int i) {
        PolylineOptions j = j(i);
        j.addAll(arrayList);
        return this.I.addPolyline(j);
    }

    public void v() {
        this.m.clear();
        for (int i = 0; i < this.O.size(); i++) {
            ((Polyline) this.O.get(i)).remove();
        }
        this.O.clear();
        if (this.Q != null) {
            this.Q.remove();
        }
    }

    public void a(float f, boolean z) {
        if (this.V != null) {
            if (z) {
                CameraPosition cameraPosition = this.I.getCameraPosition();
                this.I.moveCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(this.V.getPosition()).zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(-f).build()));
                this.V.setRotation(0.0f);
                return;
            }
            this.V.setRotation(f - this.au);
        }
    }

    public void a(boolean z) {
        this.av = z;
    }

    private Marker a(MarkerOptions markerOptions) {
        if (b() == 1) {
            this.j = false;
        }
        MarkerItem markerItem = new MarkerItem();
        Marker addMarker = this.I.addMarker(markerOptions);
        markerItem.marker = addMarker;
        markerItem.info = new dji.gs.e.e();
        markerItem.icon = new dji.gs.views.b(this.S);
        this.k.add(markerItem);
        this.l.add(this.I.getProjection().toScreenLocation(addMarker.getPosition()));
        k();
        if (b() <= 0 || !this.av) {
            this.j = true;
        } else {
            a(addMarker, false);
        }
        return addMarker;
    }

    public void a(b bVar, boolean z) {
        if (bVar != null) {
            a(l(dji.gs.utils.a.a(bVar)), z);
        }
    }

    private void a(LatLng latLng, boolean z) {
        if (latLng != null && !this.ap && this.az) {
            CameraPosition build;
            if (z) {
                build = new Builder().target(latLng).zoom(15.5f).build();
            } else {
                build = this.I.getCameraPosition();
                build = new Builder().zoom(build.zoom).tilt(build.tilt).bearing(build.bearing).target(latLng).build();
            }
            this.I.moveCamera(CameraUpdateFactory.newCameraPosition(build));
        }
    }

    public void d(b bVar) {
        if (bVar != null) {
            this.az = false;
            this.ax = l(bVar);
            i = l(dji.gs.utils.a.a(bVar));
            this.W = this.I.addMarker(new MarkerOptions().draggable(false).position(i).anchor(h[0], h[1]).icon(this.X));
            L();
            this.I.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(i).zoom(15.5f).build()), 500, new CancelableCallback() {
                public void onFinish() {
                    GmapControll.this.az = true;
                }

                public void onCancel() {
                    GmapControll.this.az = true;
                }
            });
        }
    }

    public void a(b bVar, LevelType levelType, AirmapMarkerType airmapMarkerType) {
        if (bVar != null) {
            if (levelType.equals(LevelType.CAN_NOT_UNLIMIT)) {
                if (!this.v.contains(bVar)) {
                    this.v.add(bVar);
                } else {
                    return;
                }
            } else if (levelType.equals(LevelType.WARNING)) {
                if (!this.r.contains(bVar)) {
                    this.r.add(bVar);
                } else {
                    return;
                }
            } else if (levelType.equals(LevelType.CAN_UNLIMIT)) {
                if (!this.p.contains(bVar)) {
                    this.p.add(bVar);
                } else {
                    return;
                }
            }
            Marker marker = null;
            if (airmapMarkerType.equals(AirmapMarkerType.AIRPORT)) {
                marker = this.I.addMarker(new MarkerOptions().draggable(false).position(l(bVar)).anchor(h[0], h[1]).icon(this.aa));
            } else if (airmapMarkerType.equals(AirmapMarkerType.HELICOPTER)) {
                marker = this.I.addMarker(new MarkerOptions().draggable(false).position(l(bVar)).anchor(h[0], h[1]).icon(this.ab));
            }
            if (levelType.equals(LevelType.CAN_NOT_UNLIMIT)) {
                this.z.add(marker);
            } else if (levelType.equals(LevelType.WARNING)) {
                this.B.add(marker);
                if (airmapMarkerType.equals(AirmapMarkerType.AIRPORT)) {
                    this.C.add(marker);
                    if (this.G.b(14) != c.a.WARN_SHOW.a()) {
                        marker.setVisible(false);
                        return;
                    }
                    return;
                }
                this.D.add(marker);
                if (this.G.b(19) != c.a.WARN_SHOW.a()) {
                    marker.setVisible(false);
                }
            } else if (levelType.equals(LevelType.CAN_UNLIMIT)) {
                this.A.add(marker);
            }
        }
    }

    public void a(b bVar, int i, int i2) {
        if (this.v.contains(bVar)) {
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                Circle circle = (Circle) it.next();
                if (circle.getCenter().equals(l(bVar))) {
                    circle.remove();
                }
            }
        } else {
            this.v.add(bVar);
        }
        this.x.add(this.I.addCircle(new CircleOptions().center(l(bVar)).radius((double) i2).strokeWidth(5.0f).strokeColor(this.aF).fillColor(this.aH)));
    }

    public void b(b bVar, int i, int i2) {
        if (!this.r.contains(bVar)) {
            this.r.add(bVar);
            Circle addCircle = this.I.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(5.0f).strokeColor(this.aD).fillColor(this.aE));
            this.s.add(addCircle);
            int a = this.G.a(i2);
            ((ArrayList) this.E.get(a)).add(addCircle);
            if (!((Integer) this.G.c().get(a)).equals(Integer.valueOf(c.a.WARN_SHOW.a()))) {
                addCircle.setVisible(false);
            }
        }
    }

    public void a(b bVar, int i, String str, int i2) {
        if (!this.r.contains(bVar)) {
            b(bVar, i, i2);
            a(str, this.aD);
        }
    }

    public void a(b bVar, int i) {
        if (!this.n.contains(bVar)) {
            this.n.add(bVar);
            this.o.add(this.I.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(10.0f).strokeColor(this.aF).fillColor(this.aH)));
        }
    }

    public void a(b bVar, int i, String str) {
        if (!this.n.contains(bVar)) {
            a(bVar, i);
            a(str, this.aG);
        }
    }

    public void b(b bVar, int i) {
        if (!this.p.contains(bVar)) {
            this.p.add(bVar);
            this.q.add(this.I.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(10.0f).strokeColor(this.aI).fillColor(this.aJ)));
        }
    }

    public void c(b bVar, int i) {
        if (!this.p.contains(bVar)) {
            b(bVar, i);
            this.q.add(this.I.addCircle(new CircleOptions().center(l(bVar)).radius(8046.0d).strokeWidth(10.0f).strokeColor(this.aD).fillColor(this.aE)));
        }
    }

    public void b(b bVar, int i, String str) {
        if (!this.p.contains(bVar)) {
            b(bVar, i);
            a(str, this.aI);
        }
    }

    public void c(b bVar, int i, String str) {
        if (!this.t.contains(bVar)) {
            this.t.add(bVar);
            this.u.add(this.I.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(10.0f).strokeColor(this.aD).fillColor(this.aE)));
            a(str, this.aD);
        }
    }

    private void a(String str, int i) {
        Iterable a = a(str);
        if (a != null && !a.isEmpty()) {
            this.y.add(this.I.addPolygon(new PolygonOptions().addAll(a).strokeColor(i).strokeWidth(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity)));
        }
    }

    private List<LatLng> a(String str) {
        if (str.equals("")) {
            return null;
        }
        String[] split = str.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]", "").split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        List<LatLng> arrayList = new ArrayList();
        int length = split.length;
        int i = 0;
        while (i < length) {
            if (!(split[i].equals("POLYGON") || split[i].equals("MULTIPOLYGON"))) {
                i++;
                arrayList.add(l(dji.gs.utils.a.a(new b(Double.valueOf(split[i + 1]).doubleValue(), Double.valueOf(split[i]).doubleValue()))));
            }
            i++;
        }
        return arrayList;
    }

    public void a(boolean z, int i) {
        if (z) {
            if (this.aB && this.aC == i) {
                return;
            }
        } else if (!this.aB) {
            return;
        }
        this.aB = z;
        if (this.aw != null) {
            this.aw.remove();
        }
        if (z) {
            this.aC = i;
            L();
        }
    }

    private void L() {
        if (this.aB && i != null) {
            if (this.aw != null) {
                this.aw.remove();
            }
            this.aw = this.I.addCircle(new CircleOptions().center(i).radius((double) this.aC).strokeWidth(5.0f).strokeColor(this.aD).fillColor(this.aE));
        }
    }

    public void e(b bVar) {
        if (this.W != null) {
            this.ax = l(bVar);
            i = l(dji.gs.utils.a.a(bVar));
            L();
            this.W.setPosition(i);
            a(this.W, false);
            a(this.W, false, new Runnable() {
                public void run() {
                    GmapControll.this.k();
                }
            });
            return;
        }
        d(bVar);
    }

    public void a(int i, b bVar) {
        i(i).marker.setPosition(l(bVar));
        a(i, true);
        k();
    }

    public void a(b bVar, double d) {
        if (bVar == null) {
            return;
        }
        if (this.aA != null) {
            this.aA.remove();
            this.aA = this.I.addCircle(new CircleOptions().center(l(dji.gs.utils.a.a(bVar))).radius(d).strokeWidth(2.0f).strokeColor(this.aM).fillColor(this.aN));
            return;
        }
        this.aA = this.I.addCircle(new CircleOptions().center(l(dji.gs.utils.a.a(bVar))).radius(d).strokeWidth(2.0f).strokeColor(this.aM).fillColor(this.aN));
    }

    private LatLng l(b bVar) {
        return new LatLng(bVar.b, bVar.c);
    }

    private b a(LatLng latLng) {
        return new b(latLng.latitude, latLng.longitude);
    }

    public void a(float f, float f2, boolean z) {
        if (this.bc == null) {
            return;
        }
        if (z) {
            this.bc.setRotation(((f - f2) - this.au) + 180.0f);
        } else {
            this.bc.setRotation((f - this.au) + 180.0f);
        }
    }

    public boolean t() {
        return this.aQ;
    }

    public boolean J() {
        return false;
    }

    public void g(b bVar) {
        if (J() && this.aQ) {
            int b = b() - 1;
            this.k.remove(b);
            this.l.remove(b);
            this.aQ = false;
        }
        a(new MarkerOptions().draggable(false).position(l(bVar)).icon(this.Z));
        a(b() - 1, true);
        if (J()) {
            this.aQ = true;
            MarkerItem markerItem = (MarkerItem) this.k.get(0);
            markerItem.info.a(true);
            markerItem.info.a(dji.gs.utils.c.a(bVar, a(markerItem.marker.getPosition())));
            this.k.add(markerItem);
            this.l.add(this.I.getProjection().toScreenLocation(((MarkerItem) this.k.get(0)).marker.getPosition()));
            k();
        }
    }

    public void d(int i) {
        ((MarkerItem) this.k.get(i)).marker.remove();
        this.k.remove(i);
        k();
        n(i);
    }

    public void g() {
        if (b() == 1) {
            ((MarkerItem) this.k.remove(0)).marker.remove();
            return;
        }
        for (int b = b() - 1; b >= 0; b--) {
            if (this.aQ) {
                this.aQ = false;
            } else {
                ((MarkerItem) this.k.get(b)).marker.remove();
            }
            this.k.remove(b);
        }
        if (this.J != null) {
            this.J.remove();
        }
        if (this.L != null) {
            this.L.remove();
        }
        if (this.M != null) {
            this.M.remove();
        }
        this.U = 0;
    }

    public void h() {
        if (this.U >= 1) {
            int i = 0;
            while (i < this.U + 1 && i < b() - 1) {
                MarkerItem markerItem = (MarkerItem) this.k.get(i + 1);
                if (!markerItem.info.f()) {
                    markerItem.icon.b(false);
                    markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerItem.icon.d()));
                    this.k.set(i + 1, markerItem);
                }
                i++;
            }
            this.U = 0;
            k();
            if (this.L != null) {
                this.L.remove();
            }
        }
    }

    private void M() {
        dji.gs.utils.c.a(a(this.V.getPosition()), a(i));
    }

    private void N() {
        if (b() > 1) {
            a(1, true);
        }
    }

    private void l(int i) {
        if (i >= 1) {
            if (i == 0) {
                m(i);
                return;
            }
            MarkerItem markerItem = (MarkerItem) this.k.get(i);
            markerItem.info.a((float) Math.round(dji.gs.utils.c.a(a(markerItem.marker.getPosition()), a(((MarkerItem) this.k.get(i - 1)).marker.getPosition()))));
            this.k.set(i, markerItem);
        }
    }

    private void a(int i, boolean z) {
        if (z) {
            l(i);
        }
        if (!this.aQ || i != b() - 1) {
            MarkerItem markerItem = (MarkerItem) this.k.get(i);
            a(markerItem.marker, markerItem.icon.b(), markerItem.icon.a(i, markerItem.info.a()));
        }
    }

    public void a(Object obj) {
        a(a((Marker) obj), true);
    }

    private void a(Marker marker, float[] fArr, Bitmap bitmap) {
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
        marker.setAnchor(fArr[0], fArr[1]);
    }

    private int m(int i) {
        int i2 = 0;
        if (i >= b()) {
            return 0;
        }
        MarkerItem markerItem = (MarkerItem) this.k.get(i);
        if (this.V != null && (this.U == 0 || i != 1)) {
            i2 = Math.round(dji.gs.utils.c.a(a(markerItem.marker.getPosition()), a(this.V.getPosition())));
        }
        if (i == 1) {
            markerItem.info.a((float) i2);
            this.k.set(i, markerItem);
        }
        return i2;
    }

    private long[] O() {
        long j = 0;
        int b = b();
        float a = dji.gs.utils.c.a();
        int i = 0;
        for (int i2 = (this.U + 1) + 1; i2 < b; i2++) {
            i += Math.round(((MarkerItem) this.k.get(i2)).info.a() * a);
            j += ((MarkerItem) this.k.get(i2)).info.b();
        }
        return new long[]{(long) (m(r8) + i), j};
    }

    private void n(int i) {
        int b = b();
        int i2 = i;
        while (i2 < b) {
            if (i2 == i) {
                a(i2, true);
            }
            if (i2 > i) {
                if (this.aQ && i2 == b - 1) {
                    a(i2, true);
                } else {
                    a(i2, false);
                }
            }
            i2++;
        }
    }

    public PolylineOptions j(int i) {
        return d(i, 10);
    }

    private PolylineOptions d(int i, int i2) {
        return new PolylineOptions().width((float) i2).color(i).geodesic(true);
    }

    public void i() {
        this.aP = true;
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            ((MarkerItem) it.next()).marker.setVisible(true);
        }
        if (this.J != null) {
            this.J.setVisible(true);
        }
        if (this.K != null) {
            this.K.setVisible(true);
        }
    }

    public void j() {
        this.aP = false;
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            ((MarkerItem) it.next()).marker.setVisible(false);
        }
        if (this.J != null) {
            this.J.setVisible(false);
        }
        if (this.K != null) {
            this.K.setVisible(false);
        }
        if (this.M != null) {
            this.M.setVisible(false);
        }
    }

    public void k() {
        PolylineOptions j;
        int i;
        int i2;
        Log.d("", "freshLines");
        if (this.U > 0 && this.U < b()) {
            j = j(this.S.getResources().getColor(R.color.gs_line_gray));
            i = this.U + 1;
            for (i2 = 1; i2 < i; i2++) {
                j.add(((MarkerItem) this.k.get(i2)).marker.getPosition());
            }
            if (i > 0) {
                Polyline addPolyline = this.I.addPolyline(j);
                if (this.L != null) {
                    this.L.remove();
                }
                this.L = addPolyline;
            }
        }
        if (this.U < b()) {
            j = j(this.S.getResources().getColor(R.color.gs_line_normal));
            i = b();
            int i3 = 0;
            int i4 = this.U > 0 ? this.U : 0;
            while (i4 < i) {
                i2 = i3 + 1;
                j.add(((MarkerItem) this.k.get(i4)).marker.getPosition());
                i4++;
                i3 = i2;
            }
            if (i3 > 0) {
                addPolyline = this.I.addPolyline(j);
                if (this.J != null) {
                    this.J.remove();
                }
                this.J = addPolyline;
            } else if (this.J != null) {
                this.J.remove();
            }
        }
    }

    public Point l() {
        return this.V == null ? null : this.I.getProjection().toScreenLocation(this.V.getPosition());
    }

    public void b(boolean z) {
        if (z || this.V == null || b() <= 1 || !this.aP || !this.j) {
            if (this.M != null) {
                this.M.remove();
                this.M = null;
            }
        } else if (this.U == 0) {
            PolylineOptions j = j(this.S.getResources().getColor(R.color.gs_line_flying));
            j.add(i(1).marker.getPosition(), this.V.getPosition());
            Polyline addPolyline = this.I.addPolyline(j);
            if (this.M != null) {
                this.M.remove();
            }
            this.M = addPolyline;
            if (!EventView.b) {
                N();
            }
        } else if (this.M != null) {
            this.M.remove();
            this.M = null;
        }
    }

    public void a(boolean z, boolean z2) {
        if (z2 || this.W == null) {
            if (this.N != null) {
                this.N.remove();
                this.N = null;
            }
        } else if (this.V != null && this.W != null) {
            PolylineOptions j;
            if (z) {
                j = j(this.S.getResources().getColor(R.color.gs_line_backhome));
            } else {
                j = j(this.S.getResources().getColor(R.color.gs_line_livehome));
            }
            j.add(this.W.getPosition(), this.V.getPosition());
            Polyline addPolyline = this.I.addPolyline(j);
            if (this.N != null) {
                this.N.remove();
            }
            this.N = addPolyline;
            M();
        }
    }

    public int a(Point point) {
        P();
        for (int i = 1; i < b() - 1; i++) {
            if (b(point, (Point) this.l.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private void P() {
        this.l.clear();
        Projection projection = this.I.getProjection();
        for (int i = 0; i < b(); i++) {
            this.l.add(projection.toScreenLocation(((MarkerItem) this.k.get(i)).marker.getPosition()));
        }
    }

    public ArrayList<Point> m() {
        P();
        return this.l;
    }

    public Point e(int i) {
        return this.I.getProjection().toScreenLocation(((MarkerItem) this.k.get(i)).marker.getPosition());
    }

    public void a(int i, dji.gs.views.b bVar) {
        MarkerItem markerItem = (MarkerItem) this.k.get(i);
        markerItem.icon = bVar;
        markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(bVar.d()));
        this.k.set(i, markerItem);
    }

    private Marker b(int i, boolean z) {
        MarkerItem markerItem = (MarkerItem) this.k.get(i);
        markerItem.icon.a(z);
        markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerItem.icon.d()));
        this.k.set(i, markerItem);
        return markerItem.marker;
    }

    public void f(int i) {
        MarkerItem markerItem = (MarkerItem) this.k.get(i);
        if (!markerItem.info.f() && !markerItem.icon.c()) {
            Log.d("", "目标航点 setDisableIcon " + i);
            if (i == 1) {
                N();
            }
            markerItem.icon.b(true);
            markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerItem.icon.d()));
            this.k.set(i, markerItem);
        }
    }

    private void o(int i) {
        if (this.T >= 0 && this.T < b() && !(this.aQ && this.T == b() - 1)) {
            b(this.T, false);
        }
        b(i, true);
        this.T = i;
    }

    public void u() {
        b(this.T, false);
    }

    public int e() {
        return this.T;
    }

    private boolean b(Point point, Point point2) {
        int width = this.ad.getWidth();
        int height = this.ad.getHeight();
        float[] b = i(1).icon.b();
        int[] iArr = new int[]{point2.x + ((int) (((float) width) * (1.0f - b[0]))), point2.x - ((int) (((float) width) * b[0])), point2.y, point2.y - height};
        if (point.x >= iArr[0] || point.x <= iArr[1] || point.y >= iArr[2] || point.y <= iArr[3]) {
            return false;
        }
        return true;
    }

    public void a(int i, Point point) {
        MarkerItem markerItem = (MarkerItem) this.k.get(i);
        markerItem.marker.setPosition(this.I.getProjection().fromScreenLocation(point));
        this.k.set(i, markerItem);
        k();
    }

    public float a(Point point, Point point2) {
        Projection projection = this.I.getProjection();
        LatLng fromScreenLocation = projection.fromScreenLocation(point);
        LatLng fromScreenLocation2 = projection.fromScreenLocation(point2);
        float[] fArr = new float[1];
        Location.distanceBetween(fromScreenLocation.latitude, fromScreenLocation.longitude, fromScreenLocation2.latitude, fromScreenLocation2.longitude, fArr);
        return fArr[0];
    }

    public FlyForbidParam n() {
        return this.aT;
    }

    public void h(b bVar) {
        if (bVar == null) {
            Circle circle;
            Marker marker;
            this.r.clear();
            Iterator it = this.s.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.s.clear();
            this.t.clear();
            it = this.u.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.u.clear();
            this.n.clear();
            it = this.o.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.o.clear();
            this.p.clear();
            it = this.q.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.q.clear();
            it = this.y.iterator();
            while (it.hasNext()) {
                Polygon polygon = (Polygon) it.next();
                if (polygon != null) {
                    polygon.remove();
                }
            }
            this.y.clear();
            it = this.z.iterator();
            while (it.hasNext()) {
                marker = (Marker) it.next();
                if (marker != null) {
                    marker.remove();
                }
            }
            this.z.clear();
            it = this.A.iterator();
            while (it.hasNext()) {
                marker = (Marker) it.next();
                if (marker != null) {
                    marker.remove();
                }
            }
            this.A.clear();
            it = this.B.iterator();
            while (it.hasNext()) {
                marker = (Marker) it.next();
                if (marker != null) {
                    marker.remove();
                }
            }
            this.B.clear();
            this.C.clear();
            this.D.clear();
            K();
        } else if (bVar.b != Map.MOVE_PRESERVE_ZOOM_LEVEL || bVar.c != Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            this.aT = new FlyForbidParam();
            List checkResult = DJIFlyForbidController.getInstance().getCheckResult();
            if (checkResult != null && !checkResult.isEmpty()) {
                int[] iArr = new int[0];
                String[] strArr = new String[0];
                if (checkResult != null) {
                    Log.e("", "getLimits 2, mCheckList.size() =" + checkResult.size());
                    this.aR = 0;
                    this.aS.clear();
                    for (int i = 0; i < checkResult.size(); i++) {
                        if (((FlyForbidElement) checkResult.get(i)).disable != 1) {
                            if (((FlyForbidElement) checkResult.get(i)).level == LevelType.WARNING.value()) {
                                this.aS.add(checkResult.get(i));
                            } else {
                                this.aR++;
                            }
                        }
                    }
                    this.aT.count = this.aR;
                    double[] dArr = new double[this.aT.count];
                    double[] dArr2 = new double[this.aT.count];
                    double[] dArr3 = new double[this.aT.count];
                    double[] dArr4 = new double[this.aT.count];
                    double[] dArr5 = new double[this.aT.count];
                    int[] iArr2 = new int[this.aT.count];
                    String[] strArr2 = new String[this.aT.count];
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < checkResult.size()) {
                        int i4;
                        Log.e("", "getLimits 2, i[" + i2 + dji.pilot.usercenter.protocol.d.H + ((FlyForbidElement) checkResult.get(i2)).lat + "," + ((FlyForbidElement) checkResult.get(i2)).lng + "," + ((FlyForbidElement) checkResult.get(i2)).area_id + "," + ((FlyForbidElement) checkResult.get(i2)).disable + "," + ((FlyForbidElement) checkResult.get(i2)).warning);
                        if (((FlyForbidElement) checkResult.get(i2)).disable != 0 || ((FlyForbidElement) checkResult.get(i2)).level == LevelType.WARNING.value()) {
                            i4 = i3;
                        } else {
                            dArr[i3] = ((FlyForbidElement) checkResult.get(i2)).lng * 1000000.0d;
                            dArr2[i3] = ((FlyForbidElement) checkResult.get(i2)).lat * 1000000.0d;
                            dArr3[i3] = (double) ((FlyForbidElement) checkResult.get(i2)).radius;
                            dArr4[i3] = (double) ((FlyForbidElement) checkResult.get(i2)).country;
                            dArr5[i3] = (double) ((FlyForbidElement) checkResult.get(i2)).type;
                            iArr2[i3] = ((FlyForbidElement) checkResult.get(i2)).level;
                            strArr2[i3] = ((FlyForbidElement) checkResult.get(i2)).points;
                            i4 = i3 + 1;
                        }
                        i2++;
                        i3 = i4;
                    }
                    this.aT.SetForbidPoint(dArr, dArr2, dArr3, dArr4, dArr5, this.aT.count);
                    strArr = strArr2;
                    iArr = iArr2;
                } else {
                    DJILogHelper.getInstance().LOGE("NFZ", "getLimits 2, mCheckList == null ", true, true);
                    FlyForbid.native_CheckNearForbidPoints(bVar.c, bVar.b, this.aT);
                }
                DJILogHelper.getInstance().LOGE("NFZ", "param.count: " + this.aT.count, true, true);
                int i5 = this.aT.count;
                int i6 = 0;
                while (i6 < i5) {
                    if (this.aT.ForbidType[i6] == 0.0d || this.aT.ForbidType[i6] == 11.0d || this.aT.ForbidType[i6] == 13.0d || this.aT.ForbidType[i6] == 23.0d || this.aT.ForbidType[i6] == 24.0d || this.aT.ForbidType[i6] == 26.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            a(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6]);
                        } else if (this.aT.ForbidType[i6] == 13.0d) {
                            c(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6]);
                        } else {
                            b(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6]);
                        }
                    } else if (this.aT.ForbidType[i6] == 1.0d || this.aT.ForbidType[i6] == 2.0d || this.aT.ForbidType[i6] == 29.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            a(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6], strArr[i6]);
                        } else {
                            b(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6], strArr[i6]);
                        }
                    } else if (this.aT.ForbidType[i6] == 27.0d || this.aT.ForbidType[i6] == 28.0d) {
                        c(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6], strArr[i6]);
                    } else if (this.aT.ForbidType[i6] == 12.0d) {
                        a(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), LevelType.CAN_UNLIMIT, AirmapMarkerType.AIRPORT);
                    } else if (this.aT.ForbidType[i6] != 3.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            a(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6], strArr[i6]);
                        } else {
                            b(dji.gs.utils.a.a(new b(this.aT.ForbidLat[i6] / this.aU, this.aT.ForbidLon[i6] / this.aU)), (int) this.aT.ForbidRadius[i6], strArr[i6]);
                        }
                    }
                    i6++;
                }
                for (i6 = 0; i6 < this.aS.size(); i6++) {
                    FlyForbidElement flyForbidElement = (FlyForbidElement) this.aS.get(i6);
                    if (flyForbidElement.type == 14) {
                        a(dji.gs.utils.a.a(new b(flyForbidElement.lat, flyForbidElement.lng)), LevelType.WARNING, AirmapMarkerType.AIRPORT);
                    } else if (flyForbidElement.type == 19) {
                        a(dji.gs.utils.a.a(new b(flyForbidElement.lat, flyForbidElement.lng)), LevelType.WARNING, AirmapMarkerType.HELICOPTER);
                    } else {
                        b(dji.gs.utils.a.a(new b(((FlyForbidElement) this.aS.get(i6)).lat, ((FlyForbidElement) this.aS.get(i6)).lng)), ((FlyForbidElement) this.aS.get(i6)).radius, flyForbidElement.type);
                    }
                }
                if (this.W != null) {
                    this.W.remove();
                    this.W = this.I.addMarker(new MarkerOptions().draggable(false).position(i).anchor(h[0], h[1]).icon(this.X));
                }
                DJILogHelper.getInstance().LOGE("NFZ", "finished marker... ", false, true);
            }
        }
    }

    public void a(ArrayList<DJIFlightLimitAreaModel> arrayList) {
        int i = 0;
        Circle circle;
        if (arrayList == null) {
            this.v.clear();
            Iterator it = this.w.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            it = this.x.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            return;
        }
        int size = arrayList.size();
        if (this.w.size() == 0) {
            while (i < size) {
                if (p(((DJIFlightLimitAreaModel) arrayList.get(i)).type)) {
                    a(dji.gs.utils.a.a(new b((((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).latitude) * 1.0d) / 1000000.0d, (((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).longitude) * 1.0d) / 1000000.0d)), ((DJIFlightLimitAreaModel) arrayList.get(i)).innerRadius, ((DJIFlightLimitAreaModel) arrayList.get(i)).outerRadius);
                }
                i++;
            }
            return;
        }
        this.v.clear();
        Iterator it2 = this.w.iterator();
        while (it2.hasNext()) {
            circle = (Circle) it2.next();
            if (circle != null) {
                circle.remove();
            }
        }
        it2 = this.x.iterator();
        while (it2.hasNext()) {
            circle = (Circle) it2.next();
            if (circle != null) {
                circle.remove();
            }
        }
        while (i < size) {
            if (p(((DJIFlightLimitAreaModel) arrayList.get(i)).type)) {
                a(dji.gs.utils.a.a(new b((((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).latitude) * 1.0d) / 1000000.0d, (((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).longitude) * 1.0d) / 1000000.0d)), ((DJIFlightLimitAreaModel) arrayList.get(i)).innerRadius, ((DJIFlightLimitAreaModel) arrayList.get(i)).outerRadius);
            }
            i++;
        }
    }

    private boolean p(int i) {
        return i == 0 || i == 9;
    }

    public boolean o() {
        int b = b() - 1;
        for (int i = 0; i < this.n.size(); i++) {
            int radius = (int) ((Circle) this.o.get(i)).getRadius();
            for (int i2 = 0; i2 < b; i2++) {
                b b2 = dji.gs.utils.a.b(a(((MarkerItem) this.k.get(i2)).marker.getPosition()));
                b b3 = dji.gs.utils.a.b(a(((MarkerItem) this.k.get(i2 + 1)).marker.getPosition()));
                b bVar = new b(((b) this.n.get(i)).b, ((b) this.n.get(i)).c);
                if (FlyForbid.native_intersectSegCircle((double) dji.gs.utils.c.a(b2, b3), (double) dji.gs.utils.c.a(bVar, b2), (double) dji.gs.utils.c.a(bVar, b3), radius)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean p() {
        if (O()[0] > OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) {
            return true;
        }
        return false;
    }

    public boolean q() {
        int b = b();
        for (int i = 1; i < b; i++) {
            if (dji.gs.utils.c.a(a(i), a(i(i).marker.getPosition())) > DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) {
                return false;
            }
        }
        return true;
    }

    public void c(boolean z) {
        this.aW = z;
    }

    public boolean r() {
        return (this.aW || this.aV) ? false : true;
    }

    public boolean j(b bVar) {
        return dji.gs.utils.c.a(bVar, a(i)) <= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition;
    }

    public void onMapClick(LatLng latLng) {
        if (this.bf != null) {
            this.bf.onClick();
        }
    }

    public boolean onMarkerClick(Marker marker) {
        if (this.bb != null && this.bb.a() == dji.gs.d.e.a.GOING) {
            g(a(marker));
        }
        return true;
    }

    public void g(int i) {
        if (i >= 1) {
            o(i);
            Point point = (Point) this.l.get(this.T);
            i(a(((MarkerItem) this.k.get(i)).marker.getPosition()));
            if (this.bb.a() == dji.gs.d.e.a.EDIT) {
                c(point);
            }
            if (this.bb.a() == dji.gs.d.e.a.GOING) {
                d(point);
            }
        }
    }

    private void a(Marker marker, boolean z) {
        a(marker, z, null);
    }

    private void a(Marker marker, boolean z, Runnable runnable) {
        final LatLng position = marker.getPosition();
        final long uptimeMillis = SystemClock.uptimeMillis();
        final TimeInterpolator bounceInterpolator = z ? new BounceInterpolator() : new AccelerateInterpolator(2.0f);
        final Point toScreenLocation = this.I.getProjection().toScreenLocation(position);
        final int i = toScreenLocation.y;
        final Marker marker2 = marker;
        final Runnable runnable2 = runnable;
        this.ba.post(new Runnable() {
            public void run() {
                float min = Math.min(bounceInterpolator.getInterpolation(((float) (SystemClock.uptimeMillis() - uptimeMillis)) / 300.0f), 1.0f);
                LatLng fromScreenLocation = GmapControll.this.I.getProjection().fromScreenLocation(new Point(toScreenLocation.x, Math.round(((float) i) * min)));
                if (min == 1.0f) {
                    fromScreenLocation = position;
                }
                marker2.setPosition(fromScreenLocation);
                if (min < 1.0f) {
                    GmapControll.this.ba.postDelayed(this, 20);
                    return;
                }
                GmapControll.this.aV = false;
                GmapControll.this.j = true;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    private void c(Point point) {
        this.ba.sendMessageDelayed(this.ba.obtainMessage(0, point), 100);
    }

    private void d(Point point) {
        this.ba.sendMessageDelayed(this.ba.obtainMessage(1, point), 200);
    }

    public void a(dji.gs.d.e eVar) {
        this.bb = eVar;
    }

    public void a(float f) {
        this.au = f;
    }

    public Point b(Point point) {
        Projection projection = this.I.getProjection();
        float a = dji.gs.utils.c.a(a(projection.fromScreenLocation(point)), a(i));
        if (a <= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) {
            return null;
        }
        Point toScreenLocation = projection.toScreenLocation(i);
        a = DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition / a;
        point.x = (int) (((float) toScreenLocation.x) - (((float) (toScreenLocation.x - point.x)) * a));
        point.y = (int) (((float) toScreenLocation.y) - (((float) (toScreenLocation.y - point.y)) * a));
        return point;
    }

    public b f() {
        if (i == null) {
            return null;
        }
        return a(i);
    }

    public void s() {
        if (i != null) {
            CameraPosition cameraPosition = this.I.getCameraPosition();
            Builder bearing = new Builder().zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(cameraPosition.bearing);
            if (i != null) {
                this.I.animateCamera(CameraUpdateFactory.newCameraPosition(bearing.target(i).build()), 100, null);
            }
        }
    }

    public void i(b bVar) {
        CameraPosition cameraPosition = this.I.getCameraPosition();
        Builder bearing = new Builder().zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(cameraPosition.bearing);
        if (bVar != null) {
            this.I.animateCamera(CameraUpdateFactory.newCameraPosition(bearing.target(l(dji.gs.utils.a.a(bVar))).build()), 100, null);
        }
    }

    public void b(float f) {
        if (this.az) {
            CameraPosition cameraPosition = this.I.getCameraPosition();
            this.I.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(cameraPosition.target).zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(f).build()), 100, null);
        }
    }

    public b b(Object obj) {
        return a(((Marker) obj).getPosition());
    }

    public void a(int i, int i2) {
    }

    public void f(b bVar) {
        if (bVar.b != 0.0d || bVar.c != 0.0d) {
            if (this.ay == null) {
                this.ay = this.I.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a(bVar))).anchor(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c).icon(this.ah));
                return;
            }
            if (this.aj != this.ah) {
                this.aj = this.ah;
                this.ay.setIcon(this.ah);
            }
            this.ay.setPosition(l(dji.gs.utils.a.a(bVar)));
        }
    }

    public void b(b bVar, boolean z) {
        if (bVar.b != 0.0d || bVar.c != 0.0d) {
            BitmapDescriptor bitmapDescriptor = z ? this.ah : this.ai;
            if (this.ay == null) {
                this.ay = this.I.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a(bVar))).anchor(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c).icon(bitmapDescriptor));
                return;
            }
            if (this.aj != bitmapDescriptor) {
                this.aj = bitmapDescriptor;
                this.ay.setIcon(bitmapDescriptor);
            }
            this.ay.setPosition(l(dji.gs.utils.a.a(bVar)));
        }
    }

    public void d(boolean z) {
        this.bd = z;
    }

    public void b(int i, int i2) {
        this.be = this.I.getCameraPosition();
        if (this.V != null || i != null) {
            this.ap = true;
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            if (this.V != null) {
                builder.include(this.V.getPosition());
            }
            if (i != null) {
                builder.include(i);
            }
            if (this.V != null || i != null) {
                final LatLngBounds build = builder.build();
                try {
                    this.I.animateCamera(CameraUpdateFactory.newLatLngBounds(build, i, i2, 50), new CancelableCallback() {
                        public void onFinish() {
                            GmapControll.this.ap = false;
                            GmapControll.this.a(build.getCenter(), false);
                        }

                        public void onCancel() {
                            GmapControll.this.ap = false;
                        }
                    });
                } catch (Exception e) {
                    this.ap = false;
                }
            }
        }
    }

    public void w() {
        this.I.animateCamera(CameraUpdateFactory.newCameraPosition(this.be));
    }

    public void a(SparseArray<Point> sparseArray) {
        PolylineOptions d = d(this.S.getResources().getColor(R.color.gs_paint_blue), 20);
        int size = sparseArray.size();
        Projection projection = this.I.getProjection();
        for (int i = 0; i < size; i++) {
            d.add(projection.fromScreenLocation((Point) sparseArray.get(i)));
        }
        this.R.add(this.I.addPolyline(d));
    }

    public void x() {
        if (this.R != null) {
            for (int i = 0; i < this.R.size(); i++) {
                ((Polyline) this.R.get(i)).remove();
            }
        }
    }

    public void y() {
        this.I.clear();
    }

    public void a(dji.gs.d.b bVar) {
        this.bf = bVar;
    }

    public void a(dji.gs.d.c cVar) {
        this.bg = cVar;
    }

    public void A() {
        this.I.snapshot(new SnapshotReadyCallback() {
            public void onSnapshotReady(Bitmap bitmap) {
                GmapControll.this.bg.a(bitmap);
            }
        });
    }

    public boolean B() {
        return true;
    }

    public void a(Context context) {
        this.bh = (LocationManager) context.getApplicationContext().getSystemService(n.C);
    }

    public void a(long j, float f, final dji.gs.d.a aVar) {
        if (this.bi == null) {
            this.bi = new LocationListener() {
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        Location a = dji.a.a.getInstance().a(GmapControll.this.bj, location, OdnpConfigStatic.OEM_MAX_HIGH_POWER_INTERVAL);
                        if (a != null && a != GmapControll.this.bj) {
                            GmapControll.this.bj = a;
                            double latitude = a.getLatitude();
                            double longitude = a.getLongitude();
                            float altitude = (float) a.getAltitude();
                            float accuracy = a.getAccuracy();
                            if (accuracy == 0.0f) {
                                accuracy = GmapControll.this.Q();
                            }
                            aVar.a(new b(latitude, longitude, altitude, accuracy));
                        }
                    }
                }

                public void onProviderDisabled(String str) {
                }

                public void onProviderEnabled(String str) {
                }

                public void onStatusChanged(String str, int i, Bundle bundle) {
                    switch (i) {
                    }
                }
            };
        }
        this.bh.removeUpdates(this.bi);
        if (this.bh.isProviderEnabled("gps")) {
            this.bh.requestLocationUpdates("gps", j, f, this.bi);
        }
        if (this.bh.isProviderEnabled("network")) {
            this.bh.requestLocationUpdates("network", j, f, this.bi);
        }
    }

    private float Q() {
        b C = C();
        if (C != null) {
            return C.e;
        }
        return 10000.0f;
    }

    public b C() {
        Location e = dji.a.a.getInstance().e();
        return new b(e.getLatitude(), e.getLongitude(), (float) e.getAltitude(), e.getAccuracy());
    }

    public void D() {
        if (this.bh != null && this.bi != null) {
            this.bh.removeUpdates(this.bi);
        }
    }

    public void b(b bVar, double d) {
        if (this.bl != null) {
            this.bl.remove();
            this.bl = null;
        }
        if (this.bn != null) {
            this.bn.remove();
            this.bn = null;
        }
        if (bVar != null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.draggable(false).position(new LatLng(bVar.b, bVar.c)).anchor(h[0], h[1]).icon(this.bm);
            this.bl = this.I.addMarker(markerOptions);
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(new LatLng(bVar.b, bVar.c));
            circleOptions.radius(d);
            circleOptions.strokeWidth(dji.midware.util.a.b.c);
            circleOptions.strokeColor(Color.rgb(113, FTPCodes.ENTER_PASSIVE_MODE, 21));
            this.bn = this.I.addCircle(circleOptions);
        }
    }

    public b E() {
        if (this.bl == null) {
            return null;
        }
        LatLng position = this.bl.getPosition();
        return new b(position.latitude, position.longitude);
    }

    public float F() {
        if (this.bh != null) {
            Location e = dji.a.a.getInstance().e();
            if (e != null) {
                return e.getAccuracy();
            }
        }
        return 10000.0f;
    }

    public void c(b bVar, double d) {
        if (!this.aq.contains(bVar)) {
            this.aq.add(bVar);
            this.ar.add(this.I.addCircle(new CircleOptions().center(l(bVar)).radius(d).strokeWidth(5.0f).strokeColor(this.as).fillColor(this.at)));
        }
    }

    public void G() {
        this.aq.clear();
        Iterator it = this.ar.iterator();
        while (it.hasNext()) {
            ((Circle) it.next()).remove();
        }
        this.ar.clear();
    }

    public void H() {
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            Circle circle = (Circle) it.next();
            if (circle != null && circle.isVisible()) {
                circle.setVisible(false);
            }
        }
        it = this.x.iterator();
        while (it.hasNext()) {
            circle = (Circle) it.next();
            if (circle != null && circle.isVisible()) {
                circle.setVisible(false);
            }
        }
        it = this.o.iterator();
        while (it.hasNext()) {
            circle = (Circle) it.next();
            if (circle != null && circle.isVisible()) {
                circle.setVisible(false);
            }
        }
    }

    public void I() {
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            Circle circle = (Circle) it.next();
            if (!(circle == null || circle.isVisible())) {
                circle.setVisible(true);
            }
        }
        it = this.x.iterator();
        while (it.hasNext()) {
            circle = (Circle) it.next();
            if (!(circle == null || circle.isVisible())) {
                circle.setVisible(true);
            }
        }
        it = this.o.iterator();
        while (it.hasNext()) {
            circle = (Circle) it.next();
            if (!(circle == null || circle.isVisible())) {
                circle.setVisible(true);
            }
        }
    }

    private void a(ArrayList<Marker> arrayList, boolean z) {
        if (arrayList != null && arrayList.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).setVisible(z);
            }
        }
    }

    public void c(int i, int i2) {
        int a = this.G.a(i);
        if ((i == 14 || i == 19) && this.bo) {
            if (i != 14 || this.C.size() <= 0) {
                if (i == 19 && this.D.size() > 0) {
                    if (i2 == c.a.WARN_SHOW.a() && !((Marker) this.D.get(0)).isVisible()) {
                        a(this.D, true);
                    } else if (i2 != c.a.WARN_SHOW.a() && ((Marker) this.D.get(0)).isVisible()) {
                        a(this.D, false);
                    }
                }
            } else if (i2 == c.a.WARN_SHOW.a() && !((Marker) this.C.get(0)).isVisible()) {
                a(this.C, true);
            } else if (i2 != c.a.WARN_SHOW.a() && ((Marker) this.C.get(0)).isVisible()) {
                a(this.C, false);
            }
        }
        if (((ArrayList) this.E.get(a)).size() != 0) {
            Iterator it;
            if (i2 == c.a.WARN_SHOW.a()) {
                if (!((Circle) ((ArrayList) this.E.get(a)).get(0)).isVisible()) {
                    it = ((ArrayList) this.E.get(a)).iterator();
                    while (it.hasNext()) {
                        ((Circle) it.next()).setVisible(true);
                    }
                }
            } else if (((Circle) ((ArrayList) this.E.get(a)).get(0)).isVisible()) {
                it = ((ArrayList) this.E.get(a)).iterator();
                while (it.hasNext()) {
                    ((Circle) it.next()).setVisible(false);
                }
            }
        }
    }

    public void c(float f) {
        Iterator it;
        if (f < 10.0f && this.bo) {
            this.bo = false;
            it = this.B.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).setVisible(false);
            }
            it = this.A.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).setVisible(false);
            }
        }
        if (f > 10.0f && !this.bo) {
            this.bo = true;
            if (this.G.b(14) == c.a.WARN_SHOW.a()) {
                it = this.C.iterator();
                while (it.hasNext()) {
                    ((Marker) it.next()).setVisible(true);
                }
            }
            if (this.G.b(19) == c.a.WARN_SHOW.a()) {
                it = this.D.iterator();
                while (it.hasNext()) {
                    ((Marker) it.next()).setVisible(true);
                }
            }
            it = this.A.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).setVisible(true);
            }
        }
    }

    public void a(int i, b... bVarArr) {
        if (bVarArr != null && bVarArr.length != 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (b l : bVarArr) {
                builder.include(l(l));
            }
            this.I.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10), null);
        }
    }
}
