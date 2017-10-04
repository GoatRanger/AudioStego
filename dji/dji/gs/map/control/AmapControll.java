package dji.gs.map.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMapScreenShotListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CameraPosition.Builder;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
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
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AmapControll implements OnMapClickListener, OnMarkerClickListener, e {
    public static LatLng i;
    private ArrayList<Marker> A = new ArrayList();
    private ArrayList<Marker> B = new ArrayList();
    private ArrayList<Marker> C = new ArrayList();
    private ArrayList<Marker> D = new ArrayList();
    private ArrayList<Marker> E = new ArrayList();
    private ArrayList<ArrayList<Circle>> F = new ArrayList();
    private int G;
    private c H;
    private ArrayList<Marker> I = new ArrayList();
    private AMap J;
    private Polyline K;
    private Polyline L;
    private Polyline M;
    private Polyline N;
    private Polyline O;
    private ArrayList<Polyline> P = new ArrayList();
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
    private boolean aA = true;
    private Circle aB = null;
    private boolean aC = false;
    private int aD;
    private final int aE;
    private final int aF;
    private final int aG = Color.HSVToColor(200, new float[]{0.0f, 1.0f, 1.0f});
    private final int aH = Color.HSVToColor(60, new float[]{0.0f, 1.0f, 1.0f});
    private final int aI = Color.HSVToColor(120, new float[]{0.0f, 1.0f, 1.0f});
    private final int aJ = Color.argb(90, 255, 255, 0);
    private final int aK = Color.argb(40, 255, 255, 0);
    private final int aL = Color.argb(90, 255, 255, 255);
    private final int aM = Color.argb(30, 255, 255, 255);
    private final int aN = Color.argb(0, 255, 255, 255);
    private final int aO = Color.argb(90, 0, 0, 255);
    private final int aP = Color.argb(0, 0, 0, 0);
    private final float aQ = 17.0f;
    private final float aR = b.a;
    private final float aS = 19.0f;
    private boolean aT = true;
    private boolean aU = false;
    private int aV = 0;
    private ArrayList<FlyForbidElement> aW = new ArrayList();
    private FlyForbidParam aX;
    private double aY = 1000000.0d;
    private boolean aZ = false;
    private BitmapDescriptor aa;
    private BitmapDescriptor ab;
    private View ac;
    private Bitmap ad;
    private SharedPreferences ae;
    private String af = "map_type";
    private int ag = 2;
    private BitmapDescriptor ah;
    private BitmapDescriptor ai;
    private BitmapDescriptor aj = null;
    private BitmapDescriptor ak;
    private Polyline al;
    private int am;
    private int an;
    private int ao;
    private BitmapDescriptor ap;
    private boolean aq = false;
    private ArrayList<b> ar = new ArrayList();
    private ArrayList<Circle> as = new ArrayList();
    private final int at = Color.argb(FTPCodes.DATA_CONNECTION_ALREADY_OPEN, 0, 255, 0);
    private final int au = Color.argb(70, 0, 255, 0);
    private float av = 0.0f;
    private boolean aw = true;
    private Circle ax;
    private LatLng ay;
    private Marker az;
    private boolean ba = false;
    private int bb = 16;
    private dji.gs.views.c bc;
    private d bd;
    private Handler be = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            Point point = (Point) message.obj;
            switch (message.what) {
                case 0:
                    if (AmapControll.this.bc == null) {
                        AmapControll.this.bc = new dji.gs.views.c(AmapControll.this.S, AmapControll.this, AmapControll.this.ac);
                    }
                    AmapControll.this.bc.a(point.x, point.y);
                    break;
                case 1:
                    if (AmapControll.this.bd == null) {
                        AmapControll.this.bd = new d(AmapControll.this.S, AmapControll.this, AmapControll.this.ac);
                    }
                    AmapControll.this.bd.a(point.x, point.y);
                    break;
                case 2:
                    AmapControll.this.k();
                    break;
            }
            return false;
        }
    });
    private dji.gs.d.e bf;
    private Marker bg;
    private boolean bh;
    private CameraPosition bi;
    private dji.gs.d.b bj;
    private dji.gs.d.c bk;
    private AMapLocationClient bl = null;
    private AMapLocationListener bm = null;
    private AMapLocation bn = null;
    private Marker bo = null;
    private BitmapDescriptor bp = null;
    private Circle bq = null;
    private boolean br = false;
    protected boolean j = true;
    AMapLocationClientOption k = new AMapLocationClientOption();
    private ArrayList<MarkerItem> l = new ArrayList();
    private ArrayList<Point> m = new ArrayList();
    private ArrayList<LatLng> n = new ArrayList();
    private ArrayList<b> o = new ArrayList();
    private ArrayList<Circle> p = new ArrayList();
    private ArrayList<b> q = new ArrayList();
    private ArrayList<Circle> r = new ArrayList();
    private ArrayList<b> s = new ArrayList();
    private ArrayList<Circle> t = new ArrayList();
    private ArrayList<b> u = new ArrayList();
    private ArrayList<Circle> v = new ArrayList();
    private ArrayList<b> w = new ArrayList();
    private ArrayList<Circle> x = new ArrayList();
    private ArrayList<Circle> y = new ArrayList();
    private ArrayList<Polygon> z = new ArrayList();

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

    public AmapControll(Context context, AMap aMap, View view) {
        int i = 0;
        this.J = aMap;
        this.S = context;
        this.ac = view;
        dji.thirdparty.a.c.a().e(a.AMap);
        this.ae = PreferenceManager.getDefaultSharedPreferences(context);
        if (this.ae != null) {
            this.ag = this.ae.getInt(this.af, dji.gs.e.a.b);
        }
        if (this.ag == dji.gs.e.a.b) {
            this.J.setMapType(1);
        } else if (this.ag == dji.gs.e.a.c) {
            this.J.setMapType(3);
        } else if (this.ag == dji.gs.e.a.e) {
            this.J.setMapType(2);
        } else {
            this.J.setMapType(1);
        }
        this.J.setOnMapClickListener(this);
        this.J.setOnMarkerClickListener(this);
        this.J.setOnCameraChangeListener(new OnCameraChangeListener() {
            public void onCameraChange(CameraPosition cameraPosition) {
            }

            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                Iterator it = AmapControll.this.C.iterator();
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
        this.ap = BitmapDescriptorFactory.fromResource(R.drawable.my_flight_photo);
        this.aE = this.S.getResources().getColor(R.color.gs_home_stroke);
        this.aF = this.S.getResources().getColor(R.color.gs_home_fill);
        this.ad = BitmapFactory.decodeResource(context.getResources(), R.drawable.gs_marker_normal);
        this.bp = BitmapDescriptorFactory.fromResource(R.drawable.gs_hot_point);
        this.am = this.S.getResources().getColor(R.color.gs_white);
        this.an = this.S.getResources().getColor(17170456);
        this.ao = this.S.getResources().getColor(R.color.gs_white_half);
        this.H = c.getInstance(context);
        this.G = this.H.b().length;
        while (i != this.G) {
            this.F.add(new ArrayList());
            i++;
        }
    }

    public void h(int i) {
        if (i == dji.gs.e.a.b) {
            this.J.setMapType(1);
        } else if (i == dji.gs.e.a.c) {
            this.J.setMapType(3);
        } else if (i == dji.gs.e.a.e) {
            this.J.setMapType(2);
        } else {
            this.J.setMapType(1);
        }
    }

    private void K() {
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            ((ArrayList) it.next()).clear();
        }
    }

    public void a() {
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
        this.y.clear();
        this.y = null;
        this.ar.clear();
        this.ar = null;
        this.as.clear();
        this.as = null;
        this.I.clear();
        this.I = null;
        this.P.clear();
        this.P = null;
        this.R.clear();
        this.R = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.Q = null;
        this.al = null;
        this.z.clear();
        this.z = null;
        this.A.clear();
        this.A = null;
        this.B.clear();
        this.B = null;
        this.C.clear();
        this.C = null;
        this.F.clear();
        this.F = null;
        this.D.clear();
        this.D = null;
        this.E.clear();
        this.E = null;
        this.bf = null;
        this.be.removeCallbacksAndMessages(null);
        this.be = null;
        if (this.bl != null) {
            this.bl.onDestroy();
        }
        this.bm = null;
        this.bl = null;
        this.k = null;
        this.S = null;
        this.ac = null;
        this.J.clear();
        this.J = null;
    }

    public int b() {
        return this.l == null ? -1 : this.l.size();
    }

    public ArrayList<MarkerItem> c() {
        return this.l;
    }

    public void a(int i, dji.gs.e.c cVar) {
        this.l.set(i, (MarkerItem) cVar);
    }

    public void a(int i) {
        MarkerItem i2 = i(i);
        a(i2);
        this.l.set(i, i2);
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
        return (MarkerItem) this.l.get(i);
    }

    private int a(Marker marker) {
        int b = b();
        for (int i = 0; i < b; i++) {
            if (((MarkerItem) this.l.get(i)).marker.equals(marker)) {
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
        if (k(bVar) && this.bh) {
            d(bVar, this.am);
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
            this.bg = this.J.addMarker(new MarkerOptions().draggable(false).position(l(a)).anchor(dji.pilot.visual.a.d.c, 1.0f).icon(this.ak));
            this.V = this.J.addMarker(new MarkerOptions().draggable(false).position(l(a)).anchor(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c).icon(this.Y));
        } else {
            this.V.setPosition(l(a));
            this.bg.setPosition(l(a));
        }
        return true;
    }

    public void c(b bVar) {
        a(bVar);
        d(bVar, this.an);
    }

    public void c(ArrayList<b> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList == null) {
            if (this.Q != null) {
                this.Q.remove();
            }
        } else if (arrayList.size() > 1) {
            arrayList2.clear();
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList2.add(l(dji.gs.utils.a.a((b) arrayList.get(i))));
            }
            a(arrayList2, this.ao);
        }
    }

    public void b(ArrayList<b> arrayList) {
        int i = 0;
        this.n.clear();
        if (arrayList == null) {
            while (i < this.P.size()) {
                ((Polyline) this.P.get(i)).remove();
                i++;
            }
            this.P.clear();
        } else if (arrayList.size() > 1) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.n.add(l(dji.gs.utils.a.a((b) arrayList.get(i2))));
            }
            Polyline b = b(this.n, this.an);
            a(b, this.an);
            while (i < this.P.size()) {
                ((Polyline) this.P.get(i)).remove();
                i++;
            }
            this.P.clear();
            this.P.add(b);
        }
    }

    private void a(Polyline polyline, int i) {
        if (i == this.ao || i == this.an) {
            polyline.setWidth(polyline.getWidth() * 2.0f);
        }
    }

    public void d(ArrayList<b> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                this.I.add(this.J.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a((b) arrayList.get(i)))).anchor(dji.pilot.visual.a.d.c, 1.0f).icon(this.ap)));
            }
        }
    }

    public void z() {
        for (int i = 0; i < this.I.size(); i++) {
            ((Marker) this.I.get(i)).remove();
        }
        this.I.clear();
    }

    private void d(b bVar, int i) {
        LatLng latLng = this.n.size() > 0 ? (LatLng) this.n.get(this.n.size() - 1) : null;
        b a = dji.gs.utils.a.a(bVar);
        if (latLng == null || ((double) dji.gs.utils.c.a(a(latLng), a)) >= 0.1d) {
            this.n.add(l(a));
            k(i);
        }
    }

    private void k(int i) {
        if (this.n.size() > 1) {
            Polyline b = b(this.n, i);
            a(b, i);
            int size = this.P.size() - 1;
            if (this.n.size() > e.g) {
                LatLng latLng = (LatLng) this.n.get(this.n.size() - 1);
                ((Polyline) this.P.get(size)).remove();
                this.P.set(size, b);
                this.n.clear();
                this.n.add(latLng);
            } else if (size < 0 || this.n.size() <= 2) {
                this.P.add(b);
            } else {
                ((Polyline) this.P.get(size)).remove();
                this.P.set(size, b);
            }
        }
    }

    private void a(ArrayList<LatLng> arrayList, int i) {
        if (arrayList.size() > 1) {
            Polyline b = b((ArrayList) arrayList, i);
            a(b, i);
            if (this.Q != null) {
                this.Q.remove();
            }
            this.Q = b;
        }
    }

    private Polyline b(ArrayList<LatLng> arrayList, int i) {
        PolylineOptions j = j(i);
        j.addAll(arrayList);
        return this.J.addPolyline(j);
    }

    public void v() {
        this.n.clear();
        for (int i = 0; i < this.P.size(); i++) {
            ((Polyline) this.P.get(i)).remove();
        }
        this.P.clear();
        if (this.al != null) {
            this.al.remove();
        }
    }

    public void a(float f, boolean z) {
        if (this.V != null) {
            if (z) {
                CameraPosition cameraPosition = this.J.getCameraPosition();
                this.J.moveCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(this.V.getPosition()).zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(f).build()));
                this.V.setRotateAngle(0.0f);
                return;
            }
            this.V.setRotateAngle((-f) + this.av);
        }
    }

    public void a(boolean z) {
        this.aw = z;
    }

    private Marker a(MarkerOptions markerOptions) {
        if (b() == 1) {
            this.j = false;
        }
        MarkerItem markerItem = new MarkerItem();
        Marker addMarker = this.J.addMarker(markerOptions);
        markerItem.marker = addMarker;
        markerItem.info = new dji.gs.e.e();
        markerItem.icon = new dji.gs.views.b(this.S);
        this.l.add(markerItem);
        this.m.add(this.J.getProjection().toScreenLocation(addMarker.getPosition()));
        k();
        if (b() <= 0 || !this.aw) {
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
        if (latLng != null && !this.aq && this.aA) {
            CameraPosition build;
            if (z) {
                build = new Builder().target(latLng).zoom(17.0f).build();
            } else {
                build = this.J.getCameraPosition();
                if (build != null) {
                    build = new Builder().zoom(build.zoom).tilt(build.tilt).bearing(build.bearing).target(latLng).build();
                } else {
                    build = new Builder().target(latLng).zoom(17.0f).build();
                }
            }
            this.J.moveCamera(CameraUpdateFactory.newCameraPosition(build));
        }
    }

    public void d(b bVar) {
        if (bVar != null) {
            this.aA = false;
            this.ay = l(bVar);
            i = l(dji.gs.utils.a.a(bVar));
            this.W = this.J.addMarker(new MarkerOptions().draggable(false).position(i).anchor(h[0], h[1]).icon(this.X));
            L();
            this.J.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(i).zoom(17.0f).build()), 500, new CancelableCallback() {
                public void onFinish() {
                    AmapControll.this.aA = true;
                }

                public void onCancel() {
                    AmapControll.this.aA = true;
                }
            });
        }
    }

    public void a(b bVar, LevelType levelType, AirmapMarkerType airmapMarkerType) {
        if (bVar != null) {
            if (levelType.equals(LevelType.CAN_NOT_UNLIMIT)) {
                if (!this.w.contains(bVar)) {
                    this.w.add(bVar);
                } else {
                    return;
                }
            } else if (levelType.equals(LevelType.WARNING)) {
                if (!this.s.contains(bVar)) {
                    this.s.add(bVar);
                } else {
                    return;
                }
            } else if (levelType.equals(LevelType.CAN_UNLIMIT)) {
                if (!this.q.contains(bVar)) {
                    this.q.add(bVar);
                } else {
                    return;
                }
            }
            Marker marker = null;
            if (airmapMarkerType.equals(AirmapMarkerType.AIRPORT)) {
                marker = this.J.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a(bVar))).anchor(h[0], h[1]).icon(this.aa));
            } else if (airmapMarkerType.equals(AirmapMarkerType.HELICOPTER)) {
                marker = this.J.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a(bVar))).anchor(h[0], h[1]).icon(this.ab));
            }
            if (levelType.equals(LevelType.CAN_NOT_UNLIMIT)) {
                this.A.add(marker);
            } else if (levelType.equals(LevelType.WARNING)) {
                this.C.add(marker);
                if (airmapMarkerType.equals(AirmapMarkerType.AIRPORT)) {
                    this.D.add(marker);
                    if (this.H.b(14) != c.a.WARN_SHOW.a()) {
                        marker.setVisible(false);
                        return;
                    }
                    return;
                }
                this.E.add(marker);
                if (this.H.b(19) != c.a.WARN_SHOW.a()) {
                    marker.setVisible(false);
                }
            } else if (levelType.equals(LevelType.CAN_UNLIMIT)) {
                this.B.add(marker);
            }
        }
    }

    public void a(b bVar, int i, int i2) {
        if (this.w.contains(bVar)) {
            Iterator it = this.y.iterator();
            while (it.hasNext()) {
                Circle circle = (Circle) it.next();
                if (circle.getCenter().equals(l(bVar))) {
                    circle.remove();
                }
            }
        } else {
            this.w.add(bVar);
        }
        this.y.add(this.J.addCircle(new CircleOptions().center(l(bVar)).radius((double) i2).strokeWidth(5.0f).strokeColor(this.aG).fillColor(this.aI)));
    }

    public void b(b bVar, int i) {
        if (!this.q.contains(bVar)) {
            this.q.add(bVar);
            this.r.add(this.J.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(10.0f).strokeColor(this.aJ).fillColor(this.aK)));
        }
    }

    public void c(b bVar, int i) {
        if (!this.q.contains(bVar)) {
            b(bVar, i);
            this.r.add(this.J.addCircle(new CircleOptions().center(l(bVar)).radius(8046.0d).strokeWidth(10.0f).strokeColor(this.aE).fillColor(this.aF)));
        }
    }

    public void b(b bVar, int i, int i2) {
        if (!this.s.contains(bVar)) {
            this.s.add(bVar);
            Circle addCircle = this.J.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(5.0f).strokeColor(this.aE).fillColor(this.aF));
            this.t.add(addCircle);
            ((ArrayList) this.F.get(this.H.a(i2))).add(addCircle);
            if (this.H.b(i2) != c.a.WARN_SHOW.a()) {
                addCircle.setVisible(false);
            }
        }
    }

    public void a(b bVar, int i, String str, int i2) {
        if (!this.s.contains(bVar)) {
            b(bVar, i, i2);
            a(str, this.aE);
        }
    }

    public void a(b bVar, int i, String str) {
        if (!this.q.contains(bVar)) {
            b(bVar, i);
            a(str, this.aJ);
        }
    }

    public void a(b bVar, int i) {
        if (!this.o.contains(bVar)) {
            this.o.add(bVar);
            this.p.add(this.J.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(10.0f).strokeColor(this.aG).fillColor(this.aI)));
        }
    }

    public void b(b bVar, int i, String str) {
        if (!this.o.contains(bVar)) {
            a(bVar, i);
            a(str, this.aH);
        }
    }

    public void c(b bVar, int i, String str) {
        if (!this.u.contains(bVar)) {
            this.u.add(bVar);
            this.v.add(this.J.addCircle(new CircleOptions().center(l(bVar)).radius((double) i).strokeWidth(10.0f).strokeColor(this.aE).fillColor(this.aF)));
            a(str, this.aE);
        }
    }

    private void a(String str, int i) {
        Iterable a = a(str);
        if (a != null && !a.isEmpty()) {
            this.z.add(this.J.addPolygon(new PolygonOptions().addAll(a).strokeColor(i).strokeWidth(DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity).fillColor(this.aN)));
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
            if (this.aC && this.aD == i) {
                return;
            }
        } else if (!this.aC) {
            return;
        }
        this.aC = z;
        if (this.ax != null) {
            this.ax.remove();
        }
        if (z) {
            this.aD = i;
            L();
        }
    }

    private void L() {
        if (this.aC && i != null) {
            if (this.ax != null) {
                this.ax.remove();
            }
            this.ax = this.J.addCircle(new CircleOptions().center(i).radius((double) this.aD).strokeWidth(5.0f).strokeColor(this.aE).fillColor(this.aF));
        }
    }

    public void e(b bVar) {
        if (this.W != null) {
            this.ay = l(bVar);
            i = l(dji.gs.utils.a.a(bVar));
            L();
            this.W.setPosition(i);
            a(this.W, false);
            return;
        }
        d(bVar);
    }

    public void a(int i, b bVar) {
        i(i).marker.setPosition(l(bVar));
        a(i, true, false);
        k();
    }

    public void a(b bVar, double d) {
        if (bVar == null) {
            return;
        }
        if (this.aB != null) {
            this.aB.remove();
            this.aB = this.J.addCircle(new CircleOptions().center(l(dji.gs.utils.a.a(bVar))).radius(d).strokeWidth(2.0f).strokeColor(this.aO).fillColor(this.aP));
            return;
        }
        this.aB = this.J.addCircle(new CircleOptions().center(l(dji.gs.utils.a.a(bVar))).radius(d).strokeWidth(2.0f).strokeColor(this.aO).fillColor(this.aP));
    }

    private LatLng l(b bVar) {
        return new LatLng(bVar.b, bVar.c);
    }

    private b a(LatLng latLng) {
        return new b(latLng.latitude, latLng.longitude);
    }

    public void a(float f, float f2, boolean z) {
        if (this.bg == null) {
            return;
        }
        if (z) {
            this.bg.setRotateAngle(-(((f - f2) - this.av) + 180.0f));
        } else {
            this.bg.setRotateAngle(-((f - this.av) + 180.0f));
        }
    }

    public boolean t() {
        return this.aU;
    }

    public boolean J() {
        return false;
    }

    public void g(b bVar) {
        if (J() && this.aU) {
            int b = b() - 1;
            this.l.remove(b);
            this.m.remove(b);
            this.aU = false;
        }
        a(new MarkerOptions().draggable(false).position(l(bVar)).icon(this.Z));
        a(b() - 1, true, false);
        if (J()) {
            this.aU = true;
            MarkerItem markerItem = (MarkerItem) this.l.get(0);
            markerItem.info.a(true);
            markerItem.info.a(dji.gs.utils.c.a(bVar, a(markerItem.marker.getPosition())));
            this.l.add(markerItem);
            this.m.add(this.J.getProjection().toScreenLocation(((MarkerItem) this.l.get(0)).marker.getPosition()));
            k();
        }
    }

    public void d(int i) {
        ((MarkerItem) this.l.get(i)).marker.remove();
        this.l.remove(i);
        this.aw = false;
        k();
        n(i);
        this.aw = true;
    }

    public void g() {
        if (b() == 1) {
            ((MarkerItem) this.l.remove(0)).marker.remove();
            return;
        }
        for (int b = b() - 1; b >= 0; b--) {
            if (this.aU) {
                this.aU = false;
            } else {
                ((MarkerItem) this.l.get(b)).marker.remove();
            }
            this.l.remove(b);
        }
        if (this.K != null) {
            this.K.remove();
        }
        if (this.M != null) {
            this.M.remove();
        }
        if (this.N != null) {
            this.N.remove();
        }
        this.U = 0;
    }

    public void h() {
        if (this.U >= 1) {
            int i = 0;
            while (i < this.U + 1 && i < b() - 1) {
                MarkerItem markerItem = (MarkerItem) this.l.get(i + 1);
                if (!markerItem.info.f()) {
                    markerItem.icon.b(false);
                    markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerItem.icon.d()));
                    this.l.set(i + 1, markerItem);
                }
                i++;
            }
            this.U = 0;
            k();
            if (this.M != null) {
                this.M.remove();
            }
        }
    }

    private void M() {
        dji.gs.utils.c.a(a(this.V.getPosition()), a(i));
    }

    private void N() {
        if (b() > 1) {
            a(1, true, false);
        }
    }

    private void l(int i) {
        if (i >= 1) {
            if (i == 0) {
                m(i);
                return;
            }
            MarkerItem markerItem = (MarkerItem) this.l.get(i);
            markerItem.info.a((float) Math.round(dji.gs.utils.c.a(a(markerItem.marker.getPosition()), a(((MarkerItem) this.l.get(i - 1)).marker.getPosition()))));
            this.l.set(i, markerItem);
        }
    }

    private void a(int i, boolean z, boolean z2) {
        if (z) {
            l(i);
        }
        if (!this.aU || i != b() - 1) {
            MarkerItem markerItem = (MarkerItem) this.l.get(i);
            a(markerItem.marker, markerItem.icon.b(), markerItem.icon.a(i, markerItem.info.a()));
            if (i != 1 && z2) {
                Q();
            }
        }
    }

    public void a(Object obj) {
        a(a((Marker) obj), true, true);
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
        MarkerItem markerItem = (MarkerItem) this.l.get(i);
        if (this.V != null && (this.U == 0 || i != 1)) {
            i2 = Math.round(dji.gs.utils.c.a(a(markerItem.marker.getPosition()), a(this.V.getPosition())));
        }
        if (i == 1) {
            markerItem.info.a((float) i2);
            this.l.set(i, markerItem);
        }
        return i2;
    }

    private long[] O() {
        long j = 0;
        int b = b();
        float a = dji.gs.utils.c.a();
        int i = 0;
        for (int i2 = (this.U + 1) + 1; i2 < b; i2++) {
            i += Math.round(((MarkerItem) this.l.get(i2)).info.a() * a);
            j += ((MarkerItem) this.l.get(i2)).info.b();
        }
        return new long[]{(long) (m(r8) + i), j};
    }

    private void n(int i) {
        Log.e("", "updateDistanceByDelIndex hasAddHomeToLast=" + this.aU);
        int b = b();
        int i2 = i;
        while (i2 < b) {
            if (i2 == i) {
                a(i2, true, false);
            }
            if (i2 > i) {
                if (this.aU && i2 == b - 1) {
                    a(i2, true, false);
                } else {
                    a(i2, false, false);
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
        this.aT = true;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((MarkerItem) it.next()).marker.setVisible(true);
        }
        if (this.K != null) {
            this.K.setVisible(true);
        }
        if (this.L != null) {
            this.L.setVisible(true);
        }
    }

    public void j() {
        this.aT = false;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((MarkerItem) it.next()).marker.setVisible(false);
        }
        if (this.K != null) {
            this.K.setVisible(false);
        }
        if (this.L != null) {
            this.L.setVisible(false);
        }
        if (this.N != null) {
            this.N.setVisible(false);
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
                j.add(((MarkerItem) this.l.get(i2)).marker.getPosition());
            }
            if (i > 2) {
                Polyline addPolyline = this.J.addPolyline(j);
                if (this.M != null) {
                    this.M.remove();
                }
                this.M = addPolyline;
            } else if (this.M != null) {
                this.M.remove();
            }
        }
        if (this.U < b()) {
            int i3;
            j = j(this.S.getResources().getColor(R.color.gs_line_normal));
            i = b();
            if (this.U > 0) {
                i3 = this.U;
            } else {
                i3 = 0;
            }
            int i4 = i3;
            i3 = 0;
            int i5 = i4;
            while (i5 < i) {
                i2 = i3 + 1;
                j.add(((MarkerItem) this.l.get(i5)).marker.getPosition());
                i5++;
                i3 = i2;
            }
            if (i3 > 1) {
                addPolyline = this.J.addPolyline(j);
                if (this.K != null) {
                    this.K.remove();
                }
                this.K = addPolyline;
            } else if (this.K != null) {
                this.K.remove();
            }
        }
    }

    public Point l() {
        return this.V == null ? null : this.J.getProjection().toScreenLocation(this.V.getPosition());
    }

    public void b(boolean z) {
        if (z || this.V == null || b() <= 1 || !this.aT || !this.j) {
            if (this.N != null) {
                this.N.remove();
                this.N = null;
            }
        } else if (this.U == 0) {
            PolylineOptions j = j(this.S.getResources().getColor(R.color.gs_line_flying));
            j.add(new LatLng[]{i(1).marker.getPosition(), this.V.getPosition()});
            try {
                Polyline addPolyline = this.J.addPolyline(j);
                if (this.N != null) {
                    this.N.remove();
                }
                this.N = addPolyline;
                if (!EventView.b) {
                    N();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.N != null) {
            this.N.remove();
            this.N = null;
        }
    }

    public void a(boolean z, boolean z2) {
        if (z2 || this.W == null) {
            if (this.O != null) {
                this.O.remove();
                this.O = null;
            }
        } else if (this.V != null && this.W != null) {
            PolylineOptions j;
            if (z) {
                j = j(this.S.getResources().getColor(R.color.gs_line_backhome));
            } else {
                j = j(this.S.getResources().getColor(R.color.gs_line_livehome));
            }
            j.add(new LatLng[]{this.W.getPosition(), this.V.getPosition()});
            try {
                Polyline addPolyline = this.J.addPolyline(j);
                if (this.O != null) {
                    this.O.remove();
                }
                this.O = addPolyline;
                M();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int a(Point point) {
        P();
        for (int i = 1; i < b() - 1; i++) {
            if (b(point, (Point) this.m.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private void P() {
        this.m.clear();
        Projection projection = this.J.getProjection();
        for (int i = 0; i < b(); i++) {
            this.m.add(projection.toScreenLocation(((MarkerItem) this.l.get(i)).marker.getPosition()));
        }
    }

    private void Q() {
        for (int i = 1; i < b(); i++) {
            MarkerItem i2 = i(i);
            if (!i2.info.f()) {
                i2.marker.setIcon(BitmapDescriptorFactory.fromBitmap(i2.icon.d()));
            }
        }
    }

    public ArrayList<Point> m() {
        return this.m;
    }

    public Point e(int i) {
        return this.J.getProjection().toScreenLocation(((MarkerItem) this.l.get(i)).marker.getPosition());
    }

    public void a(int i, dji.gs.views.b bVar) {
        MarkerItem markerItem = (MarkerItem) this.l.get(i);
        markerItem.icon = bVar;
        markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(bVar.d()));
        this.l.set(i, markerItem);
    }

    private Marker a(int i, boolean z) {
        MarkerItem markerItem = (MarkerItem) this.l.get(i);
        markerItem.icon.a(z);
        markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerItem.icon.d()));
        this.l.set(i, markerItem);
        return markerItem.marker;
    }

    public void f(int i) {
        MarkerItem markerItem = (MarkerItem) this.l.get(i);
        if (!markerItem.info.f() && !markerItem.icon.c()) {
            Log.d("", "目标航点 setDisableIcon " + i);
            if (i == 1) {
                N();
            }
            markerItem.icon.b(true);
            markerItem.marker.setIcon(BitmapDescriptorFactory.fromBitmap(markerItem.icon.d()));
            this.l.set(i, markerItem);
        }
    }

    private void o(int i) {
        if (this.T >= 0 && this.T < b() && !(this.aU && this.T == b() - 1)) {
            a(this.T, false);
        }
        a(i, true);
        this.T = i;
    }

    public void u() {
        a(this.T, false);
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
        MarkerItem markerItem = (MarkerItem) this.l.get(i);
        markerItem.marker.setPosition(this.J.getProjection().fromScreenLocation(point));
        this.l.set(i, markerItem);
        k();
    }

    public float a(Point point, Point point2) {
        Projection projection = this.J.getProjection();
        LatLng fromScreenLocation = projection.fromScreenLocation(point);
        LatLng fromScreenLocation2 = projection.fromScreenLocation(point2);
        float[] fArr = new float[1];
        Location.distanceBetween(fromScreenLocation.latitude, fromScreenLocation.longitude, fromScreenLocation2.latitude, fromScreenLocation2.longitude, fArr);
        return fArr[0];
    }

    public FlyForbidParam n() {
        return this.aX;
    }

    public void h(b bVar) {
        if (bVar == null) {
            Circle circle;
            Marker marker;
            this.s.clear();
            Iterator it = this.t.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.t.clear();
            this.u.clear();
            it = this.v.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.v.clear();
            this.o.clear();
            it = this.p.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.p.clear();
            this.q.clear();
            it = this.r.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            this.r.clear();
            it = this.z.iterator();
            while (it.hasNext()) {
                Polygon polygon = (Polygon) it.next();
                if (polygon != null) {
                    polygon.remove();
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
            it = this.C.iterator();
            while (it.hasNext()) {
                marker = (Marker) it.next();
                if (marker != null) {
                    marker.remove();
                }
            }
            this.C.clear();
            this.D.clear();
            this.E.clear();
            K();
        } else if (bVar.b != Map.MOVE_PRESERVE_ZOOM_LEVEL || bVar.c != Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            this.aX = new FlyForbidParam();
            List checkResult = DJIFlyForbidController.getInstance().getCheckResult();
            if (checkResult != null && !checkResult.isEmpty()) {
                int[] iArr = new int[0];
                String[] strArr = new String[0];
                if (checkResult != null) {
                    Log.e("", "getLimits 2, mCheckList.size() =" + checkResult.size());
                    this.aV = 0;
                    this.aW.clear();
                    for (int i = 0; i < checkResult.size(); i++) {
                        if (((FlyForbidElement) checkResult.get(i)).disable != 1) {
                            if (((FlyForbidElement) checkResult.get(i)).level == LevelType.WARNING.value()) {
                                this.aW.add(checkResult.get(i));
                            } else {
                                this.aV++;
                            }
                        }
                    }
                    this.aX.count = this.aV;
                    double[] dArr = new double[this.aX.count];
                    double[] dArr2 = new double[this.aX.count];
                    double[] dArr3 = new double[this.aX.count];
                    double[] dArr4 = new double[this.aX.count];
                    double[] dArr5 = new double[this.aX.count];
                    int[] iArr2 = new int[this.aX.count];
                    String[] strArr2 = new String[this.aX.count];
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
                    this.aX.SetForbidPoint(dArr, dArr2, dArr3, dArr4, dArr5, this.aX.count);
                    strArr = strArr2;
                    iArr = iArr2;
                } else {
                    Log.e("", "getLimits 2, mCheckList == null ");
                    FlyForbid.native_CheckNearForbidPoints(bVar.c, bVar.b, this.aX);
                }
                int i5 = this.aX.count;
                int i6 = 0;
                while (i6 < i5) {
                    if (this.aX.ForbidType[i6] == 0.0d || this.aX.ForbidType[i6] == 11.0d || this.aX.ForbidType[i6] == 13.0d || this.aX.ForbidType[i6] == 23.0d || this.aX.ForbidType[i6] == 24.0d || this.aX.ForbidType[i6] == 26.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            a(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6]);
                        } else if (this.aX.ForbidType[i6] == 13.0d) {
                            c(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6]);
                        } else {
                            b(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6]);
                        }
                    } else if (this.aX.ForbidType[i6] == 1.0d || this.aX.ForbidType[i6] == 2.0d || this.aX.ForbidType[i6] == 29.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            b(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6], strArr[i6]);
                        } else {
                            a(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6], strArr[i6]);
                        }
                    } else if (this.aX.ForbidType[i6] == 27.0d || this.aX.ForbidType[i6] == 28.0d) {
                        c(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6], strArr[i6]);
                    } else if (this.aX.ForbidType[i6] == 12.0d) {
                        a(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), LevelType.CAN_UNLIMIT, AirmapMarkerType.AIRPORT);
                    } else if (this.aX.ForbidType[i6] != 3.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            b(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6], strArr[i6]);
                        } else {
                            a(dji.gs.utils.a.a(new b(this.aX.ForbidLat[i6] / this.aY, this.aX.ForbidLon[i6] / this.aY)), (int) this.aX.ForbidRadius[i6], strArr[i6]);
                        }
                    }
                    i6++;
                }
                for (i6 = 0; i6 < this.aW.size(); i6++) {
                    FlyForbidElement flyForbidElement = (FlyForbidElement) this.aW.get(i6);
                    if (flyForbidElement.type == 14) {
                        a(dji.gs.utils.a.a(new b(flyForbidElement.lat, flyForbidElement.lng)), LevelType.WARNING, AirmapMarkerType.AIRPORT);
                    } else if (flyForbidElement.type == 19) {
                        a(dji.gs.utils.a.a(new b(flyForbidElement.lat, flyForbidElement.lng)), LevelType.WARNING, AirmapMarkerType.HELICOPTER);
                    } else {
                        b(dji.gs.utils.a.a(new b(((FlyForbidElement) this.aW.get(i6)).lat, ((FlyForbidElement) this.aW.get(i6)).lng)), ((FlyForbidElement) this.aW.get(i6)).radius, flyForbidElement.type);
                    }
                }
                if (this.W != null) {
                    this.W.remove();
                    this.W = this.J.addMarker(new MarkerOptions().draggable(false).position(i).anchor(h[0], h[1]).icon(this.X));
                }
                DJILogHelper.getInstance().LOGE("NFZ", "finished marker... ", false, true);
            }
        }
    }

    public boolean o() {
        int b = b() - 1;
        for (int i = 0; i < this.o.size(); i++) {
            int radius = (int) ((Circle) this.p.get(i)).getRadius();
            for (int i2 = 0; i2 < b; i2++) {
                b b2 = dji.gs.utils.a.b(a(((MarkerItem) this.l.get(i2)).marker.getPosition()));
                b b3 = dji.gs.utils.a.b(a(((MarkerItem) this.l.get(i2 + 1)).marker.getPosition()));
                b bVar = new b(((b) this.o.get(i)).b, ((b) this.o.get(i)).c);
                if (FlyForbid.native_intersectSegCircle((double) dji.gs.utils.c.a(b2, b3), (double) dji.gs.utils.c.a(bVar, b2), (double) dji.gs.utils.c.a(bVar, b3), radius)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void a(ArrayList<DJIFlightLimitAreaModel> arrayList) {
        int i = 0;
        Circle circle;
        if (arrayList == null) {
            this.w.clear();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            it = this.y.iterator();
            while (it.hasNext()) {
                circle = (Circle) it.next();
                if (circle != null) {
                    circle.remove();
                }
            }
            return;
        }
        int size = arrayList.size();
        if (this.x.size() == 0) {
            while (i < size) {
                if (p(((DJIFlightLimitAreaModel) arrayList.get(i)).type)) {
                    a(dji.gs.utils.a.a(new b((((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).latitude) * 1.0d) / 1000000.0d, (((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).longitude) * 1.0d) / 1000000.0d)), ((DJIFlightLimitAreaModel) arrayList.get(i)).innerRadius, ((DJIFlightLimitAreaModel) arrayList.get(i)).outerRadius);
                }
                i++;
            }
            return;
        }
        this.w.clear();
        Iterator it2 = this.x.iterator();
        while (it2.hasNext()) {
            circle = (Circle) it2.next();
            if (circle != null) {
                circle.remove();
            }
        }
        it2 = this.y.iterator();
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
        this.ba = z;
    }

    public boolean r() {
        return (this.ba || this.aZ) ? false : true;
    }

    public boolean j(b bVar) {
        return dji.gs.utils.c.a(bVar, a(i)) <= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition;
    }

    public void onMapClick(LatLng latLng) {
        if (this.bj != null) {
            this.bj.onClick();
        }
    }

    public boolean onMarkerClick(Marker marker) {
        if (this.bf != null && this.bf.a() == dji.gs.d.e.a.GOING) {
            g(a(marker));
        }
        return true;
    }

    public void g(int i) {
        if (i >= 1) {
            o(i);
            Point point = (Point) this.m.get(this.T);
            i(a(((MarkerItem) this.l.get(i)).marker.getPosition()));
            if (this.bf.a() == dji.gs.d.e.a.EDIT) {
                c(point);
            }
            if (this.bf.a() == dji.gs.d.e.a.GOING) {
                d(point);
            }
        }
    }

    private void a(Marker marker, boolean z) {
        this.be.removeMessages(2);
        final LatLng position = marker.getPosition();
        final long uptimeMillis = SystemClock.uptimeMillis();
        final Interpolator bounceInterpolator = new BounceInterpolator();
        final Point toScreenLocation = this.J.getProjection().toScreenLocation(position);
        final int i = toScreenLocation.y;
        final Marker marker2 = marker;
        this.be.post(new Runnable() {
            public void run() {
                float min = Math.min(bounceInterpolator.getInterpolation(((float) (SystemClock.uptimeMillis() - uptimeMillis)) / 200.0f), 1.0f);
                LatLng fromScreenLocation = AmapControll.this.J.getProjection().fromScreenLocation(new Point(toScreenLocation.x, Math.round(((float) i) * min)));
                if (min == 1.0f) {
                    fromScreenLocation = position;
                }
                marker2.setPosition(fromScreenLocation);
                if (min < 1.0f) {
                    AmapControll.this.be.postDelayed(this, 20);
                    return;
                }
                AmapControll.this.be.sendEmptyMessageDelayed(2, 200);
                AmapControll.this.aZ = false;
                AmapControll.this.j = true;
            }
        });
    }

    private void c(Point point) {
        this.be.sendMessageDelayed(this.be.obtainMessage(0, point), 100);
    }

    private void d(Point point) {
        this.be.sendMessageDelayed(this.be.obtainMessage(1, point), 200);
    }

    public void a(dji.gs.d.e eVar) {
        this.bf = eVar;
    }

    public void a(float f) {
        this.av = f;
    }

    public Point b(Point point) {
        Projection projection = this.J.getProjection();
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
            CameraPosition cameraPosition = this.J.getCameraPosition();
            Builder bearing = new Builder().zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(cameraPosition.bearing);
            if (i != null) {
                this.J.animateCamera(CameraUpdateFactory.newCameraPosition(bearing.target(i).build()), 100, null);
            }
        }
    }

    public void i(b bVar) {
        CameraPosition cameraPosition = this.J.getCameraPosition();
        Builder bearing = new Builder().zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(cameraPosition.bearing);
        Log.d("", "cameraToLoc " + bVar);
        if (bVar != null) {
            this.J.animateCamera(CameraUpdateFactory.newCameraPosition(bearing.target(l(dji.gs.utils.a.a(bVar))).build()), 100, null);
        }
    }

    public void b(float f) {
        if (this.aA) {
            this.J.moveCamera(CameraUpdateFactory.changeBearing(f));
        }
    }

    public b b(Object obj) {
        return a(((Marker) obj).getPosition());
    }

    public void a(int i, int i2) {
        this.J.setPointToCenter(i, i2);
    }

    public void f(b bVar) {
        if (bVar.b != 0.0d || bVar.c != 0.0d) {
            if (this.az == null) {
                this.az = this.J.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a(bVar))).anchor(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c).icon(this.ah));
                return;
            }
            if (this.aj != this.ah) {
                this.aj = this.ah;
                this.az.setIcon(this.ah);
            }
            this.az.setPosition(l(dji.gs.utils.a.a(bVar)));
        }
    }

    public void b(b bVar, boolean z) {
        if (bVar.b != 0.0d || bVar.c != 0.0d) {
            BitmapDescriptor bitmapDescriptor = z ? this.ah : this.ai;
            if (this.az == null) {
                this.az = this.J.addMarker(new MarkerOptions().draggable(false).position(l(dji.gs.utils.a.a(bVar))).anchor(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c).icon(bitmapDescriptor));
                return;
            }
            if (this.aj != bitmapDescriptor) {
                this.aj = bitmapDescriptor;
                this.az.setIcon(bitmapDescriptor);
            }
            this.az.setPosition(l(dji.gs.utils.a.c(bVar)));
        }
    }

    public void d(boolean z) {
        this.bh = z;
    }

    public void b(int i, int i2) {
        this.bi = this.J.getCameraPosition();
        if (this.V != null || i != null) {
            this.aq = true;
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            if (this.V != null) {
                builder.include(this.V.getPosition());
            }
            if (i != null) {
                builder.include(i);
            }
            if (this.V != null || i != null) {
                final LatLngBounds build = builder.build();
                this.J.animateCamera(CameraUpdateFactory.newLatLngBounds(build, i, i2, 10), new CancelableCallback() {
                    public void onFinish() {
                        AmapControll.this.aq = false;
                        AmapControll.this.a(AmapControll.this.a(build), false);
                    }

                    public void onCancel() {
                        AmapControll.this.aq = false;
                    }
                });
            }
        }
    }

    private LatLng a(LatLngBounds latLngBounds) {
        Point toScreenLocation = this.J.getProjection().toScreenLocation(latLngBounds.northeast);
        Point toScreenLocation2 = this.J.getProjection().toScreenLocation(latLngBounds.southwest);
        return this.J.getProjection().fromScreenLocation(new Point(toScreenLocation2.x + ((int) ((((float) (toScreenLocation.x - toScreenLocation2.x)) * 1.0f) / 2.0f)), ((int) ((((float) (toScreenLocation2.y - toScreenLocation.y)) * 1.0f) / 2.0f)) + toScreenLocation.y));
    }

    public void w() {
        this.J.animateCamera(CameraUpdateFactory.newCameraPosition(this.bi));
    }

    public void a(SparseArray<Point> sparseArray) {
        PolylineOptions d = d(this.S.getResources().getColor(R.color.gs_paint_blue), 20);
        int size = sparseArray.size();
        Projection projection = this.J.getProjection();
        for (int i = 0; i < size; i++) {
            d.add(projection.fromScreenLocation((Point) sparseArray.get(i)));
        }
        this.R.add(this.J.addPolyline(d));
    }

    public void x() {
        if (this.R != null) {
            for (int i = 0; i < this.R.size(); i++) {
                ((Polyline) this.R.get(i)).remove();
            }
        }
    }

    public void y() {
        this.J.clear();
    }

    public void a(dji.gs.d.b bVar) {
        this.bj = bVar;
    }

    public void a(dji.gs.d.c cVar) {
        this.bk = cVar;
    }

    public void A() {
        this.J.getMapScreenShot(new OnMapScreenShotListener() {
            public void onMapScreenShot(Bitmap bitmap) {
                AmapControll.this.bk.a(bitmap);
            }

            public void onMapScreenShot(Bitmap bitmap, int i) {
            }
        });
    }

    public boolean B() {
        return false;
    }

    public void a(Context context) {
        this.bl = new AMapLocationClient(context.getApplicationContext());
    }

    public void a(long j, float f, final dji.gs.d.a aVar) {
        if (this.bm == null) {
            if (this.bl.isStarted()) {
                this.bl.unRegisterLocationListener(this.bm);
                this.bl.stopLocation();
            }
            this.bm = new AMapLocationListener() {
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation != null) {
                        AMapLocation aMapLocation2 = (AMapLocation) dji.a.a.getInstance().a(AmapControll.this.bn, aMapLocation);
                        if (aMapLocation2 != null) {
                            if (aMapLocation2.getErrorCode() != 0) {
                                DJILogHelper.getInstance().LOGD("", aMapLocation2.getErrorInfo());
                            } else if (AmapControll.this.bn != aMapLocation2) {
                                AmapControll.this.bn = aMapLocation2;
                                double latitude = aMapLocation2.getLatitude();
                                double longitude = aMapLocation2.getLongitude();
                                float altitude = (float) aMapLocation2.getAltitude();
                                float accuracy = aMapLocation2.getAccuracy();
                                if (accuracy == 0.0f) {
                                    accuracy = AmapControll.this.R();
                                }
                                aVar.a(dji.gs.utils.a.d(new b(latitude, longitude, altitude, accuracy)));
                            }
                        }
                    }
                }
            };
        }
        this.bl.setLocationListener(this.bm);
        this.k.setLocationMode(AMapLocationMode.Hight_Accuracy);
        this.k.setInterval(j);
        this.k.setNeedAddress(false);
        this.bl.setLocationOption(this.k);
        this.bl.startLocation();
    }

    private float R() {
        b C = C();
        if (C != null) {
            return C.e;
        }
        return 10000.0f;
    }

    public b C() {
        AMapLocation lastKnownLocation = this.bl.getLastKnownLocation();
        if (lastKnownLocation == null) {
            return null;
        }
        return dji.gs.utils.a.b(new b(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), (float) lastKnownLocation.getAltitude(), lastKnownLocation.getAccuracy()));
    }

    public void D() {
        if (this.bl != null) {
            this.bl.unRegisterLocationListener(this.bm);
            this.bl.stopLocation();
        }
    }

    public void b(b bVar, double d) {
        if (this.bo != null) {
            this.bo.remove();
            this.bo = null;
        }
        if (this.bq != null) {
            this.bq.remove();
            this.bq = null;
        }
        if (bVar != null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.draggable(false).position(new LatLng(bVar.b, bVar.c)).anchor(h[0], h[1]).icon(this.bp);
            this.bo = this.J.addMarker(markerOptions);
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(new LatLng(bVar.b, bVar.c));
            circleOptions.radius(d);
            circleOptions.strokeWidth(dji.midware.util.a.b.c);
            circleOptions.strokeColor(Color.rgb(113, FTPCodes.ENTER_PASSIVE_MODE, 21));
            this.bq = this.J.addCircle(circleOptions);
        }
    }

    public b E() {
        if (this.bo == null) {
            return null;
        }
        LatLng position = this.bo.getPosition();
        return new b(position.latitude, position.longitude);
    }

    public float F() {
        if (this.bl != null) {
            Location lastKnownLocation = this.bl.getLastKnownLocation();
            if (lastKnownLocation != null) {
                return lastKnownLocation.getAccuracy();
            }
        }
        return 10000.0f;
    }

    public void c(b bVar, double d) {
        if (!this.ar.contains(bVar)) {
            this.ar.add(bVar);
            this.as.add(this.J.addCircle(new CircleOptions().center(l(bVar)).radius(d).strokeWidth(5.0f).strokeColor(this.at).fillColor(this.au)));
        }
    }

    public void G() {
        this.ar.clear();
        Iterator it = this.as.iterator();
        while (it.hasNext()) {
            ((Circle) it.next()).remove();
        }
        this.as.clear();
    }

    public void H() {
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            Circle circle = (Circle) it.next();
            if (circle != null && circle.isVisible()) {
                circle.setVisible(false);
            }
        }
        it = this.y.iterator();
        while (it.hasNext()) {
            circle = (Circle) it.next();
            if (circle != null && circle.isVisible()) {
                circle.setVisible(false);
            }
        }
        it = this.p.iterator();
        while (it.hasNext()) {
            circle = (Circle) it.next();
            if (circle != null && circle.isVisible()) {
                circle.setVisible(false);
            }
        }
    }

    public void I() {
        Iterator it = this.x.iterator();
        while (it.hasNext()) {
            Circle circle = (Circle) it.next();
            if (!(circle == null || circle.isVisible())) {
                circle.setVisible(true);
            }
        }
        it = this.y.iterator();
        while (it.hasNext()) {
            circle = (Circle) it.next();
            if (!(circle == null || circle.isVisible())) {
                circle.setVisible(true);
            }
        }
        it = this.p.iterator();
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
        int a = this.H.a(i);
        if ((i == 14 || i == 19) && this.br) {
            if (i != 14 || this.D.size() <= 0) {
                if (i == 19 && this.E.size() > 0) {
                    if (i2 == c.a.WARN_SHOW.a() && !((Marker) this.E.get(0)).isVisible()) {
                        a(this.E, true);
                    } else if (i2 != c.a.WARN_SHOW.a() && ((Marker) this.E.get(0)).isVisible()) {
                        a(this.E, false);
                    }
                }
            } else if (i2 == c.a.WARN_SHOW.a() && !((Marker) this.D.get(0)).isVisible()) {
                a(this.D, true);
            } else if (i2 != c.a.WARN_SHOW.a() && ((Marker) this.D.get(0)).isVisible()) {
                a(this.D, false);
            }
        }
        if (((ArrayList) this.F.get(a)).size() != 0) {
            Iterator it;
            if (i2 == c.a.WARN_SHOW.a()) {
                if (!((Circle) ((ArrayList) this.F.get(a)).get(0)).isVisible()) {
                    it = ((ArrayList) this.F.get(a)).iterator();
                    while (it.hasNext()) {
                        ((Circle) it.next()).setVisible(true);
                    }
                }
            } else if (((Circle) ((ArrayList) this.F.get(a)).get(0)).isVisible()) {
                it = ((ArrayList) this.F.get(a)).iterator();
                while (it.hasNext()) {
                    ((Circle) it.next()).setVisible(false);
                }
            }
        }
    }

    public void c(float f) {
        Iterator it;
        if (f < 10.0f && this.br) {
            this.br = false;
            it = this.C.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).setVisible(false);
            }
            it = this.B.iterator();
            while (it.hasNext()) {
                ((Marker) it.next()).setVisible(false);
            }
        }
        if (f > 10.0f && !this.br) {
            this.br = true;
            if (this.H.b(14) == c.a.WARN_SHOW.a()) {
                it = this.D.iterator();
                while (it.hasNext()) {
                    ((Marker) it.next()).setVisible(true);
                }
            }
            if (this.H.b(19) == c.a.WARN_SHOW.a()) {
                it = this.E.iterator();
                while (it.hasNext()) {
                    ((Marker) it.next()).setVisible(true);
                }
            }
            it = this.B.iterator();
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
            this.J.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10), null);
        }
    }
}
