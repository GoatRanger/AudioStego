package dji.gs.map.control;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.location.Location;
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
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map$Animation;
import com.here.android.mpa.mapping.Map$OnTransformListener;
import com.here.android.mpa.mapping.Map$Scheme;
import com.here.android.mpa.mapping.MapCircle;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapPolygon;
import com.here.android.mpa.mapping.MapPolyline;
import com.here.android.mpa.mapping.MapState;
import com.here.android.mpa.mapping.MapView;
import com.here.odnp.config.OdnpConfigStatic;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.R;
import dji.gs.c.e;
import dji.gs.views.EventView;
import dji.gs.views.d;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.DJIFlightLimitAreaModel;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.FlyForbidElement;
import dji.midware.data.forbid.FlyForbidProtocol.LevelType;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.natives.FlyForbid;
import dji.midware.natives.FlyForbid.FlyForbidParam;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a implements OnGestureListener, e {
    private static final int br = 120000;
    public static GeoCoordinate l;
    private ArrayList<dji.gs.e.b> A = new ArrayList();
    private ArrayList<MapCircle> B = new ArrayList();
    private ArrayList<MapCircle> C = new ArrayList();
    private ArrayList<MapPolygon> D = new ArrayList();
    private ArrayList<MapMarker> E = new ArrayList();
    private ArrayList<MapMarker> F = new ArrayList();
    private ArrayList<MapMarker> G = new ArrayList();
    private ArrayList<MapMarker> H = new ArrayList();
    private ArrayList<MapMarker> I = new ArrayList();
    private ArrayList<ArrayList<MapCircle>> J = new ArrayList();
    private int K;
    private dji.gs.b.c L;
    private ArrayList<MapMarker> M = new ArrayList();
    private Map N;
    private MapPolyline O;
    private MapPolyline P;
    private MapPolyline Q;
    private MapPolyline R;
    private MapPolyline S;
    private ArrayList<MapPolyline> T = new ArrayList();
    private MapPolyline U;
    private MapPolyline V;
    private ArrayList<MapPolyline> W = new ArrayList();
    private Context X;
    private int Y = -1;
    private int Z = 0;
    private boolean aA = true;
    private MapCircle aB;
    private GeoCoordinate aC;
    private MapMarker aD;
    private boolean aE = true;
    private MapCircle aF = null;
    private int aG = Color.HSVToColor(0, new float[]{0.0f, 1.0f, 1.0f});
    private boolean aH = false;
    private int aI;
    private final int aJ;
    private final int aK;
    private final int aL = Color.HSVToColor(200, new float[]{0.0f, 1.0f, 1.0f});
    private final int aM = Color.HSVToColor(60, new float[]{0.0f, 1.0f, 1.0f});
    private final int aN = Color.HSVToColor(120, new float[]{0.0f, 1.0f, 1.0f});
    private final int aO = Color.argb(90, 255, 255, 0);
    private final int aP = Color.argb(40, 255, 255, 0);
    private final int aQ = Color.argb(90, 255, 255, 255);
    private final int aR = Color.argb(30, 255, 255, 255);
    private final int aS = Color.argb(90, 0, 0, 255);
    private final int aT = Color.argb(0, 0, 0, 0);
    private final float aU = 18.0f;
    private boolean aV = true;
    private boolean aW = false;
    private boolean aX = false;
    private int aY = 0;
    private ArrayList<FlyForbidElement> aZ = new ArrayList();
    private MapMarker aa;
    private MapMarker ab;
    private Image ac;
    private Image ad;
    private Image ae;
    private Image af;
    private Image ag;
    private View ah;
    private Bitmap ai;
    private SharedPreferences aj;
    private String ak = "map_type";
    private int al = b.NORMAL.e;
    private Image am;
    private Image an;
    private Image ao = null;
    private Image ap;
    private int aq;
    private int ar;
    private int as;
    private Image at;
    private boolean au = false;
    private ArrayList<dji.gs.e.b> av = new ArrayList();
    private ArrayList<MapCircle> aw = new ArrayList();
    private final int ax = Color.argb(FTPCodes.DATA_CONNECTION_ALREADY_OPEN, 0, 255, 0);
    private final int ay = Color.argb(70, 0, 255, 0);
    private float az = 0.0f;
    private FlyForbidParam ba;
    private double bb = 1000000.0d;
    private boolean bc = false;
    private boolean bd = false;
    private int be = 16;
    private dji.gs.views.c bf;
    private d bg;
    private Handler bh = new Handler(new Callback(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            Point point = (Point) message.obj;
            switch (message.what) {
                case 0:
                    if (this.a.bf == null) {
                        this.a.bf = new dji.gs.views.c(this.a.X, this.a, this.a.ah);
                    }
                    this.a.bf.a(point.x, point.y);
                    break;
                case 1:
                    if (this.a.bg == null) {
                        this.a.bg = new d(this.a.X, this.a, this.a.ah);
                    }
                    this.a.bg.a(point.x, point.y);
                    break;
            }
            return false;
        }
    });
    private dji.gs.d.e bi;
    private MapMarker bj;
    private boolean bk;
    private MapState bl;
    private dji.gs.d.b bm;
    private dji.gs.d.c bn;
    private AMapLocationClient bo = null;
    private AMapLocationListener bp = null;
    private AMapLocation bq = null;
    private MapMarker bs = null;
    private Image bt = null;
    private MapCircle bu = null;
    private boolean bv = false;
    PointF i = new PointF(dji.pilot.visual.a.d.c, 1.0f);
    PointF j = new PointF(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c);
    Matrix k = new Matrix();
    protected boolean m = true;
    AMapLocationClientOption n = new AMapLocationClientOption();
    private final MapView o;
    private ArrayList<c> p = new ArrayList();
    private ArrayList<Point> q = new ArrayList();
    private ArrayList<GeoCoordinate> r = new ArrayList();
    private ArrayList<dji.gs.e.b> s = new ArrayList();
    private ArrayList<MapCircle> t = new ArrayList();
    private ArrayList<dji.gs.e.b> u = new ArrayList();
    private ArrayList<MapCircle> v = new ArrayList();
    private ArrayList<dji.gs.e.b> w = new ArrayList();
    private ArrayList<MapCircle> x = new ArrayList();
    private ArrayList<dji.gs.e.b> y = new ArrayList();
    private ArrayList<MapCircle> z = new ArrayList();

    private enum a {
        AIRPORT,
        HELICOPTER
    }

    private enum b {
        NORMAL(Map$Scheme.NORMAL_DAY, dji.gs.e.a.b),
        SATELLITE(Map$Scheme.SATELLITE_DAY, dji.gs.e.a.c),
        HYBRID(Map$Scheme.HYBRID_DAY, dji.gs.e.a.e);
        
        private String d;
        private int e;

        private b(String str, int i) {
            this.d = str;
            this.e = i;
        }

        public static b find(int i) {
            for (int i2 = 0; i2 < values().length; i2++) {
                if (i == values()[i2].e) {
                    return values()[i2];
                }
            }
            return NORMAL;
        }
    }

    public class c extends dji.gs.e.c {
        public MapMarker a;
        public dji.gs.e.e b;
        public dji.gs.views.b c;
        public Point d;
        final /* synthetic */ a e;

        public c(a aVar) {
            this.e = aVar;
        }

        public Object getMarker() {
            return this.a;
        }

        public dji.gs.e.e getInfo() {
            return this.b;
        }

        public dji.gs.views.b getIcon() {
            return this.c;
        }

        public Point getPoint() {
            return this.d;
        }
    }

    public /* synthetic */ dji.gs.e.c b(int i) {
        return i(i);
    }

    public void onPanStart() {
    }

    public void onPanEnd() {
    }

    public void onMultiFingerManipulationStart() {
    }

    public void onMultiFingerManipulationEnd() {
    }

    public boolean onMapObjectsSelected(List<ViewObject> list) {
        if (this.bi != null && this.bi.a() == dji.gs.d.e.a.GOING) {
            for (ViewObject viewObject : list) {
                if (viewObject instanceof MapMarker) {
                    g(a((MapMarker) viewObject));
                    break;
                }
            }
        }
        return true;
    }

    public boolean onTapEvent(PointF pointF) {
        if (this.bm != null) {
            this.bm.onClick();
        }
        return false;
    }

    public boolean onDoubleTapEvent(PointF pointF) {
        return false;
    }

    public void onPinchLocked() {
    }

    public boolean onPinchZoomEvent(float f, PointF pointF) {
        return false;
    }

    public void onRotateLocked() {
    }

    public boolean onRotateEvent(float f) {
        return false;
    }

    public boolean onTiltEvent(float f) {
        return false;
    }

    public boolean onLongPressEvent(PointF pointF) {
        return false;
    }

    public void onLongPressRelease() {
    }

    public boolean onTwoFingerTapEvent(PointF pointF) {
        return false;
    }

    public a(Context context, MapView mapView, View view) {
        int i = 0;
        this.o = mapView;
        this.X = context;
        this.ah = view;
        dji.thirdparty.a.c.a().e(dji.gs.c.e.a.HereMap);
        this.aj = PreferenceManager.getDefaultSharedPreferences(context);
        this.o.setMap(new Map());
        this.N = this.o.getMap();
        if (this.aj != null) {
            this.al = this.aj.getInt(this.ak, b.NORMAL.e);
        }
        K();
        this.aJ = this.X.getResources().getColor(R.color.gs_home_stroke);
        this.aK = this.X.getResources().getColor(R.color.gs_home_fill);
        this.ai = BitmapFactory.decodeResource(context.getResources(), R.drawable.gs_marker_normal);
        this.aq = this.X.getResources().getColor(R.color.gs_white);
        this.ar = this.X.getResources().getColor(17170456);
        this.as = this.X.getResources().getColor(R.color.gs_white_half);
        this.L = dji.gs.b.c.getInstance(context);
        this.K = this.L.b().length;
        while (i != this.K) {
            this.J.add(new ArrayList());
            i++;
        }
    }

    private void K() {
        if (this.al == b.NORMAL.e) {
            this.N.setMapScheme(b.NORMAL.d);
        } else if (this.al == b.SATELLITE.e) {
            this.N.setMapScheme(b.SATELLITE.d);
        } else if (this.al == b.HYBRID.e) {
            this.N.setMapScheme(b.HYBRID.d);
        } else {
            this.N.setMapScheme(b.NORMAL.d);
        }
        this.o.getMapGesture().addOnGestureListener(this);
        this.N.addTransformListener(new Map$OnTransformListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onMapTransformStart() {
            }

            public void onMapTransformEnd(MapState mapState) {
                DJILogHelper.getInstance().LOGD("nfz", "" + mapState.getZoomLevel(), false, true);
                if (this.a.G != null && this.a.G.size() != 0) {
                    Iterator it = this.a.G.iterator();
                    while (it.hasNext()) {
                        ((MapMarker) it.next()).setVisible(false);
                    }
                }
            }
        });
        this.ac = new Image();
        this.ad = new Image();
        this.ae = new Image();
        this.af = new Image();
        this.ag = new Image();
        this.am = new Image();
        this.an = new Image();
        this.ap = new Image();
        this.at = new Image();
        this.bt = new Image();
        this.ac.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_home_annotation));
        this.ad.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_attitude_aircraft));
        this.ae.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_marker_normal));
        this.af.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_airport_icon));
        this.ag.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_helicopter_icon));
        this.am.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_user_annotation_image));
        this.an.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_user_annotation_bad_image));
        this.ap.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_fly_direction2));
        this.at.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.my_flight_photo));
        this.bt.setBitmap(BitmapFactory.decodeResource(this.X.getResources(), R.drawable.gs_hot_point));
    }

    public void h(int i) {
        this.N.setMapScheme(b.find(i).d);
    }

    private void L() {
        Iterator it = this.J.iterator();
        while (it.hasNext()) {
            ((ArrayList) it.next()).clear();
        }
    }

    public void a() {
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
        this.z.clear();
        this.z = null;
        this.A.clear();
        this.A = null;
        this.B.clear();
        this.B = null;
        this.C.clear();
        this.C = null;
        this.M.clear();
        this.M = null;
        this.T.clear();
        this.T = null;
        this.W.clear();
        this.W = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.U = null;
        this.V = null;
        this.D.clear();
        this.D = null;
        this.E.clear();
        this.E = null;
        this.F.clear();
        this.F = null;
        this.G.clear();
        this.G = null;
        this.J.clear();
        this.J = null;
        this.H.clear();
        this.H = null;
        this.I.clear();
        this.I = null;
        this.bi = null;
        this.bh.removeCallbacksAndMessages(null);
        this.bo = null;
        this.bp = null;
        this.bh = null;
        this.X = null;
        this.ah = null;
        this.N = null;
    }

    public int b() {
        return this.p == null ? -1 : this.p.size();
    }

    public ArrayList<c> c() {
        return this.p;
    }

    public void a(int i, dji.gs.e.c cVar) {
        this.p.set(i, (c) cVar);
    }

    public void a(int i) {
        c i2 = i(i);
        a(i2);
        this.p.set(i, i2);
    }

    private void a(c cVar) {
        Image image = new Image();
        image.setBitmap(cVar.c.d());
        cVar.a.setIcon(image);
    }

    public void a(int i, dji.gs.e.e eVar) {
        dji.gs.e.c i2 = i(i);
        i2.b = eVar;
        a(i, i2);
    }

    public c i(int i) {
        if (i >= b()) {
            return null;
        }
        return (c) this.p.get(i);
    }

    private int a(MapMarker mapMarker) {
        int b = b();
        for (int i = 0; i < b; i++) {
            if (((c) this.p.get(i)).a.equals(mapMarker)) {
                return i;
            }
        }
        return -1;
    }

    public int d() {
        return this.Z;
    }

    public void c(int i) {
        this.Z = i;
    }

    public void a(dji.gs.e.b bVar) {
        if (this.N != null && k(bVar) && this.bk) {
            d(bVar, this.aq);
        }
    }

    public void b(dji.gs.e.b bVar) {
        k(bVar);
    }

    private boolean k(dji.gs.e.b bVar) {
        if (Math.abs(bVar.b) <= 2.0E-5d && Math.abs(bVar.c) <= 2.0E-5d) {
            return false;
        }
        dji.gs.e.b a = dji.gs.utils.a.a(bVar);
        if (this.aa == null) {
            Map map = this.N;
            MapObject icon = new MapMarker().setDraggable(false).setCoordinate(l(a)).setIcon(this.ap);
            this.bj = icon;
            map.addMapObject(icon);
            map = this.N;
            MapObject icon2 = new MapMarker().setDraggable(false).setCoordinate(l(a)).setIcon(this.ad);
            this.aa = icon2;
            map.addMapObject(icon2);
            this.aa.setZIndex(65536);
        } else {
            this.aa.setCoordinate(l(a));
            this.bj.setCoordinate(l(a));
        }
        return true;
    }

    public void c(dji.gs.e.b bVar) {
        a(bVar);
        d(bVar, this.ar);
    }

    public void c(ArrayList<dji.gs.e.b> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList == null) {
            if (this.U != null) {
                this.N.removeMapObject(this.U);
            }
        } else if (arrayList.size() > 1) {
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList2.add(l(dji.gs.utils.a.a((dji.gs.e.b) arrayList.get(i))));
            }
            a(arrayList2, this.as);
        }
    }

    public void b(ArrayList<dji.gs.e.b> arrayList) {
        int i = 0;
        this.r.clear();
        if (arrayList == null) {
            while (i < this.T.size()) {
                this.N.removeMapObject((MapObject) this.T.get(i));
                i++;
            }
            this.T.clear();
        } else if (arrayList.size() > 1) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                this.r.add(l(dji.gs.utils.a.a((dji.gs.e.b) arrayList.get(i2))));
            }
            MapPolyline b = b(this.r, this.ar);
            while (i < this.T.size()) {
                this.N.removeMapObject((MapObject) this.T.get(i));
                i++;
            }
            this.T.clear();
            this.T.add(b);
        }
    }

    public void d(ArrayList<dji.gs.e.b> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                Map map = this.N;
                MapObject icon = new MapMarker().setDraggable(false).setCoordinate(l(dji.gs.utils.a.a((dji.gs.e.b) arrayList.get(i)))).setAnchorPoint(a(this.at, this.i)).setIcon(this.at);
                map.addMapObject(icon);
                this.M.add(icon);
            }
        }
    }

    public void z() {
        for (int i = 0; i < this.M.size(); i++) {
            this.N.removeMapObject((MapObject) this.M.get(i));
        }
        this.M.clear();
    }

    private void d(dji.gs.e.b bVar, int i) {
        GeoCoordinate geoCoordinate = this.r.size() > 0 ? (GeoCoordinate) this.r.get(this.r.size() - 1) : null;
        dji.gs.e.b a = dji.gs.utils.a.a(bVar);
        if (geoCoordinate == null || ((double) dji.gs.utils.c.a(a(geoCoordinate), a)) >= 0.1d) {
            this.r.add(l(a));
            k(i);
        }
    }

    private void k(int i) {
        if (this.r.size() > 1) {
            MapPolyline b = b(this.r, i);
            int size = this.T.size() - 1;
            if (this.r.size() > e.g) {
                GeoCoordinate geoCoordinate = (GeoCoordinate) this.r.get(this.r.size() - 1);
                this.N.removeMapObject((MapObject) this.T.get(size));
                this.T.set(size, b);
                this.r.clear();
                this.r.add(geoCoordinate);
            } else if (size < 0 || this.r.size() <= 2) {
                this.T.add(b);
            } else {
                this.N.removeMapObject((MapObject) this.T.get(size));
                this.T.set(size, b);
            }
        }
    }

    private void a(ArrayList<GeoCoordinate> arrayList, int i) {
        if (arrayList.size() > 1) {
            MapPolyline b = b((ArrayList) arrayList, i);
            if (this.U != null) {
                this.N.removeMapObject(this.U);
            }
            this.U = b;
        }
    }

    private MapPolyline b(ArrayList<GeoCoordinate> arrayList, int i) {
        MapObject mapPolyline = new MapPolyline(new GeoPolyline((List) arrayList));
        mapPolyline.setLineColor(i);
        mapPolyline.setLineWidth(8);
        this.N.addMapObject(mapPolyline);
        return mapPolyline;
    }

    public void v() {
        this.r.clear();
        for (int i = 0; i < this.T.size(); i++) {
            this.N.removeMapObject((MapObject) this.T.get(i));
        }
        this.T.clear();
        if (this.V != null) {
            this.N.removeMapObject(this.V);
        }
    }

    public void a(float f, boolean z) {
        if (this.aa != null) {
            if (z) {
                this.N.setCenter(this.aa.getCoordinate(), Map$Animation.BOW);
                a(this.aa, R.drawable.gs_attitude_aircraft, 0.0f);
                return;
            }
            a(this.aa, R.drawable.gs_attitude_aircraft, f - this.az);
        }
    }

    private Bitmap a(int i, float f) {
        this.k.reset();
        this.k.postRotate(f);
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        Bitmap decodeResource = BitmapFactory.decodeResource(this.X.getResources(), i, options);
        return Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), this.k, true);
    }

    private MapMarker a(MapMarker mapMarker, int i, float f) {
        Image icon = mapMarker.getIcon();
        if (icon.getBitmap() != null) {
            icon.setBitmap(a(i, f));
            mapMarker.setIcon(icon);
        }
        return mapMarker;
    }

    public void a(boolean z) {
        this.aA = z;
    }

    private MapMarker b(MapMarker mapMarker) {
        if (b() == 1) {
            this.m = false;
        }
        c cVar = new c(this);
        mapMarker.setZIndex(65525);
        this.N.addMapObject(mapMarker);
        cVar.a = mapMarker;
        cVar.b = new dji.gs.e.e();
        cVar.c = new dji.gs.views.b(this.X);
        this.p.add(cVar);
        this.q.add(a(this.N.projectToPixel(mapMarker.getCoordinate()).getResult()));
        k();
        if (b() <= 0 || !this.aA) {
            this.m = true;
        } else {
            a(mapMarker, false);
        }
        return mapMarker;
    }

    public void a(dji.gs.e.b bVar, boolean z) {
        if (this.N != null && bVar != null) {
            a(l(dji.gs.utils.a.a(bVar)), z);
        }
    }

    private void a(GeoCoordinate geoCoordinate, boolean z) {
        if (geoCoordinate != null && !this.au && this.aE) {
            if (z) {
                this.N.setCenter(geoCoordinate, Map$Animation.NONE);
                this.N.setZoomLevel(18.0d);
                return;
            }
            this.N.setCenter(geoCoordinate, Map$Animation.NONE);
        }
    }

    public void d(dji.gs.e.b bVar) {
        if (bVar != null && this.N != null) {
            this.aE = false;
            this.aC = l(bVar);
            l = l(dji.gs.utils.a.a(bVar));
            this.ab = new MapMarker().setDraggable(false).setCoordinate(l).setAnchorPoint(a(this.ac, this.i)).setIcon(this.ac);
            this.ab.setZIndex(65530);
            this.N.addMapObject(this.ab);
            M();
            this.N.setCenter(l, Map$Animation.BOW, 18.0d, -1.0f, -1.0f);
            this.N.setZoomLevel(18.0d);
            this.aE = true;
        }
    }

    private PointF a(Image image, PointF pointF) {
        Bitmap bitmap = image.getBitmap();
        bitmap.getWidth();
        return new PointF(((float) bitmap.getWidth()) * pointF.x, ((float) bitmap.getHeight()) * pointF.y);
    }

    public void a(dji.gs.e.b bVar, LevelType levelType, a aVar) {
        if (bVar != null) {
            if (levelType.equals(LevelType.CAN_NOT_UNLIMIT)) {
                if (!this.A.contains(bVar)) {
                    this.A.add(bVar);
                } else {
                    return;
                }
            } else if (levelType.equals(LevelType.WARNING)) {
                if (!this.w.contains(bVar)) {
                    this.w.add(bVar);
                } else {
                    return;
                }
            } else if (levelType.equals(LevelType.CAN_UNLIMIT)) {
                if (!this.u.contains(bVar)) {
                    this.u.add(bVar);
                } else {
                    return;
                }
            }
            MapMarker mapMarker = null;
            Map map;
            if (aVar.equals(a.AIRPORT)) {
                map = this.N;
                mapMarker = new MapMarker().setDraggable(false).setCoordinate(l(bVar)).setAnchorPoint(a(this.af, this.i)).setIcon(this.af);
                map.addMapObject(mapMarker);
            } else if (aVar.equals(a.HELICOPTER)) {
                map = this.N;
                mapMarker = new MapMarker().setDraggable(false).setCoordinate(l(bVar)).setAnchorPoint(a(this.ag, this.i)).setIcon(this.ag);
                map.addMapObject(mapMarker);
            }
            if (levelType.equals(LevelType.CAN_NOT_UNLIMIT)) {
                this.E.add(mapMarker);
            } else if (levelType.equals(LevelType.WARNING)) {
                this.G.add(mapMarker);
                if (aVar.equals(a.AIRPORT)) {
                    this.H.add(mapMarker);
                    if (this.L.b(14) != dji.gs.b.c.a.WARN_SHOW.a()) {
                        mapMarker.setVisible(false);
                        return;
                    }
                    return;
                }
                this.I.add(mapMarker);
                if (this.L.b(19) != dji.gs.b.c.a.WARN_SHOW.a()) {
                    mapMarker.setVisible(false);
                }
            } else if (levelType.equals(LevelType.CAN_UNLIMIT)) {
                this.F.add(mapMarker);
            }
        }
    }

    public void a(dji.gs.e.b bVar, int i, int i2) {
        if (this.A.contains(bVar)) {
            Iterator it = this.C.iterator();
            while (it.hasNext()) {
                MapCircle mapCircle = (MapCircle) it.next();
                if (mapCircle.getCenter().equals(l(bVar))) {
                    this.N.removeMapObject(mapCircle);
                }
            }
        } else {
            this.A.add(bVar);
        }
        if (i2 > 0) {
            Map map = this.N;
            MapObject fillColor = new MapCircle().setCenter(l(bVar)).setRadius((double) i2).setLineWidth(5).setLineColor(this.aL).setFillColor(this.aN);
            map.addMapObject(fillColor);
            this.C.add(fillColor);
        }
    }

    public void b(dji.gs.e.b bVar, int i, int i2) {
        if (!this.w.contains(bVar)) {
            this.w.add(bVar);
            if (i > 0) {
                Map map = this.N;
                MapObject fillColor = new MapCircle().setCenter(l(bVar)).setRadius((double) i).setLineWidth(5).setLineColor(this.aJ).setFillColor(this.aK);
                map.addMapObject(fillColor);
                this.x.add(fillColor);
                ((ArrayList) this.J.get(this.L.a(i2))).add(fillColor);
                if (this.L.b(i2) != dji.gs.b.c.a.WARN_SHOW.a()) {
                    fillColor.setVisible(false);
                }
            }
        }
    }

    public void a(dji.gs.e.b bVar, int i, String str, int i2) {
        if (!this.w.contains(bVar)) {
            b(bVar, i, i2);
            a(str, this.aJ);
        }
    }

    public void a(dji.gs.e.b bVar, int i) {
        if (!this.s.contains(bVar)) {
            this.s.add(bVar);
            if (i > 0) {
                Map map = this.N;
                MapObject fillColor = new MapCircle().setCenter(l(bVar)).setRadius((double) i).setLineWidth(10).setLineColor(this.aL).setFillColor(this.aN);
                map.addMapObject(fillColor);
                this.t.add(fillColor);
            }
        }
    }

    public void a(dji.gs.e.b bVar, int i, String str) {
        if (!this.s.contains(bVar)) {
            a(bVar, i);
            a(str, this.aM);
        }
    }

    public void b(dji.gs.e.b bVar, int i) {
        if (!this.u.contains(bVar)) {
            this.u.add(bVar);
            if (i > 0) {
                Map map = this.N;
                MapObject fillColor = new MapCircle().setCenter(l(bVar)).setRadius((double) i).setLineWidth(10).setLineColor(this.aO).setFillColor(this.aP);
                map.addMapObject(fillColor);
                this.v.add(fillColor);
            }
        }
    }

    public void c(dji.gs.e.b bVar, int i) {
        if (!this.u.contains(bVar)) {
            b(bVar, i);
            if (i > 0) {
                Map map = this.N;
                MapObject fillColor = new MapCircle().setCenter(l(bVar)).setRadius(8046.0d).setLineWidth(10).setLineColor(this.aJ).setFillColor(this.aK);
                map.addMapObject(fillColor);
                this.v.add(fillColor);
            }
        }
    }

    public void b(dji.gs.e.b bVar, int i, String str) {
        if (!this.u.contains(bVar)) {
            b(bVar, i);
            a(str, this.aO);
        }
    }

    public void c(dji.gs.e.b bVar, int i, String str) {
        if (!this.y.contains(bVar) && i > 0) {
            this.y.add(bVar);
            Map map = this.N;
            MapObject fillColor = new MapCircle().setCenter(l(bVar)).setRadius((double) i).setLineWidth(10).setLineColor(this.aJ).setFillColor(this.aK);
            map.addMapObject(fillColor);
            this.z.add(fillColor);
            a(str, this.aJ);
        }
    }

    private void a(String str, int i) {
        List a = a(str);
        if (a != null && !a.isEmpty()) {
            Map map = this.N;
            MapObject fillColor = new MapPolygon(new GeoPolygon(a)).setLineColor(i).setLineWidth(4).setFillColor(this.aG);
            map.addMapObject(fillColor);
            this.D.add(fillColor);
        }
    }

    private List<GeoCoordinate> a(String str) {
        if (str.equals("")) {
            return null;
        }
        String[] split = str.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]", "").split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        List<GeoCoordinate> arrayList = new ArrayList();
        int length = split.length;
        int i = 0;
        while (i < length) {
            if (!(split[i].equals("POLYGON") || split[i].equals("MULTIPOLYGON"))) {
                i++;
                arrayList.add(l(dji.gs.utils.a.a(new dji.gs.e.b(Double.valueOf(split[i + 1]).doubleValue(), Double.valueOf(split[i]).doubleValue()))));
            }
            i++;
        }
        return arrayList;
    }

    public void a(boolean z, int i) {
        if (z) {
            if (this.aH && this.aI == i) {
                return;
            }
        } else if (!this.aH) {
            return;
        }
        this.aH = z;
        if (this.aB != null) {
            this.N.removeMapObject(this.aB);
        }
        if (z) {
            this.aI = i;
            M();
        }
    }

    private void M() {
        if (this.aH && l != null && this.N != null) {
            if (this.aB == null) {
                this.aB = new MapCircle();
                this.N.addMapObject(this.aB);
            }
            this.aB.setCenter(l).setRadius((double) this.aI).setLineWidth(5).setLineColor(this.aJ).setFillColor(this.aK);
        }
    }

    public void e(dji.gs.e.b bVar) {
        if (this.N != null) {
            if (this.ab != null) {
                this.aC = l(bVar);
                l = l(dji.gs.utils.a.a(bVar));
                M();
                this.ab.setCoordinate(l);
                a(this.ab, false);
                a(this.ab, false, new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.k();
                    }
                });
                return;
            }
            d(bVar);
        }
    }

    public void a(int i, dji.gs.e.b bVar) {
        i(i).a.setCoordinate(l(bVar));
        a(i, true);
        k();
    }

    public void a(dji.gs.e.b bVar, double d) {
        if (bVar != null && d > 0.0d) {
            if (this.aF == null) {
                this.aF = new MapCircle().setLineColor(this.aS).setFillColor(this.aT);
                this.N.addMapObject(this.aF);
            }
            this.aF.setCenter(l(dji.gs.utils.a.a(bVar))).setRadius(d).setLineWidth(2);
        }
    }

    private GeoCoordinate l(dji.gs.e.b bVar) {
        return new GeoCoordinate(bVar.b, bVar.c);
    }

    private dji.gs.e.b a(GeoCoordinate geoCoordinate) {
        return new dji.gs.e.b(geoCoordinate.getLatitude(), geoCoordinate.getLongitude());
    }

    public void a(float f, float f2, boolean z) {
        if (this.N != null && this.bj != null) {
            if (z) {
                a(this.bj, R.drawable.gs_fly_direction2, ((f - f2) - this.az) + 180.0f);
            } else {
                a(this.bj, R.drawable.gs_fly_direction2, (f - this.az) + 180.0f);
            }
        }
    }

    public boolean t() {
        return this.aW;
    }

    public boolean J() {
        return false;
    }

    public void g(dji.gs.e.b bVar) {
        if (J() && this.aW) {
            int b = b() - 1;
            this.p.remove(b);
            this.q.remove(b);
            this.aW = false;
        }
        b(new MapMarker().setDraggable(false).setCoordinate(l(bVar)).setIcon(this.ae));
        a(b() - 1, true);
        if (J()) {
            this.aW = true;
            c cVar = (c) this.p.get(0);
            cVar.b.a(true);
            cVar.b.a(dji.gs.utils.c.a(bVar, a(cVar.a.getCoordinate())));
            this.p.add(cVar);
            this.q.add(a(this.N.projectToPixel(((c) this.p.get(0)).a.getCoordinate()).getResult()));
            k();
        }
    }

    private GeoCoordinate c(Point point) {
        return this.N.pixelToGeo(new PointF((float) point.x, (float) point.y));
    }

    private Point a(PointF pointF) {
        return new Point((int) pointF.x, (int) pointF.y);
    }

    private Point c(MapMarker mapMarker) {
        return a(this.N.projectToPixel(mapMarker.getCoordinate()).getResult());
    }

    private Point b(GeoCoordinate geoCoordinate) {
        return a(this.N.projectToPixel(geoCoordinate).getResult());
    }

    public void d(int i) {
        this.N.removeMapObject(((c) this.p.get(i)).a);
        this.p.remove(i);
        k();
        n(i);
    }

    public void g() {
        if (b() == 1) {
            this.N.removeMapObject(((c) this.p.remove(0)).a);
            return;
        }
        for (int b = b() - 1; b >= 0; b--) {
            if (this.aW) {
                this.aW = false;
            } else {
                this.N.removeMapObject(((c) this.p.get(b)).a);
            }
            this.p.remove(b);
        }
        if (this.O != null) {
            this.N.removeMapObject(this.O);
        }
        if (this.Q != null) {
            this.N.removeMapObject(this.Q);
        }
        if (this.R != null) {
            this.N.removeMapObject(this.R);
        }
        this.Z = 0;
    }

    public void h() {
        if (this.Z >= 1) {
            int i = 0;
            while (i < this.Z + 1 && i < b() - 1) {
                c cVar = (c) this.p.get(i + 1);
                if (!cVar.b.f()) {
                    cVar.c.b(false);
                    cVar.a.setIcon(a(cVar.c.d()));
                    this.p.set(i + 1, cVar);
                }
                i++;
            }
            this.Z = 0;
            k();
            if (this.Q != null) {
                this.N.removeMapObject(this.Q);
            }
        }
    }

    private Image a(Bitmap bitmap) {
        Image image = new Image();
        image.setBitmap(bitmap);
        return image;
    }

    private void N() {
    }

    private void O() {
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
            c cVar = (c) this.p.get(i);
            cVar.b.a((float) Math.round(dji.gs.utils.c.a(a(cVar.a.getCoordinate()), a(((c) this.p.get(i - 1)).a.getCoordinate()))));
            this.p.set(i, cVar);
        }
    }

    private void a(int i, boolean z) {
        if (z) {
            l(i);
        }
        if (!this.aW || i != b() - 1) {
            c cVar = (c) this.p.get(i);
            a(cVar.a, cVar.c.b(), cVar.c.a(i, cVar.b.a()));
        }
    }

    public void a(Object obj) {
        a(a((MapMarker) obj), true);
    }

    private void a(MapMarker mapMarker, float[] fArr, Bitmap bitmap) {
        mapMarker.setIcon(a(bitmap));
        mapMarker.setAnchorPoint(a(mapMarker.getIcon(), new PointF(fArr[0], fArr[1])));
    }

    private int m(int i) {
        int i2 = 0;
        if (i >= b()) {
            return 0;
        }
        c cVar = (c) this.p.get(i);
        if (this.aa != null && (this.Z == 0 || i != 1)) {
            i2 = Math.round(dji.gs.utils.c.a(a(cVar.a.getCoordinate()), a(this.aa.getCoordinate())));
        }
        if (i == 1) {
            cVar.b.a((float) i2);
            this.p.set(i, cVar);
        }
        return i2;
    }

    private long[] P() {
        long j = 0;
        int b = b();
        float a = dji.gs.utils.c.a();
        int i = 0;
        for (int i2 = (this.Z + 1) + 1; i2 < b; i2++) {
            i += Math.round(((c) this.p.get(i2)).b.a() * a);
            j += ((c) this.p.get(i2)).b.b();
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
                if (this.aW && i2 == b - 1) {
                    a(i2, true);
                } else {
                    a(i2, false);
                }
            }
            i2++;
        }
    }

    public MapPolyline j(int i) {
        return new MapPolyline().setLineColor(i).setLineWidth(8);
    }

    private MapPolyline d(int i, int i2) {
        return new MapPolyline().setLineColor(i).setLineWidth(i2).setGeodesicEnabled(true);
    }

    public void i() {
        this.aV = true;
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a.setVisible(true);
        }
        if (this.O != null) {
            this.O.setVisible(true);
        }
        if (this.P != null) {
            this.P.setVisible(true);
        }
    }

    public void j() {
        this.aV = false;
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a.setVisible(false);
        }
        if (this.O != null) {
            this.O.setVisible(false);
        }
        if (this.P != null) {
            this.P.setVisible(false);
        }
        if (this.R != null) {
            this.R.setVisible(false);
        }
    }

    public void k() {
        MapObject j;
        GeoPolyline geoPolyline;
        int i;
        int i2;
        int i3 = 0;
        Log.d("", "freshLines");
        if (this.Z > 0 && this.Z < b()) {
            j = j(this.X.getResources().getColor(R.color.gs_line_gray));
            geoPolyline = new GeoPolyline();
            i = this.Z + 1;
            for (i2 = 1; i2 < i; i2++) {
                geoPolyline.add(((c) this.p.get(i2)).a.getCoordinate());
            }
            if (i > 2) {
                j.setGeoPolyline(geoPolyline);
                j.setZIndex(65510);
                this.N.addMapObject(j);
                if (this.Q != null) {
                    this.N.removeMapObject(this.Q);
                }
                this.Q = j;
            }
        }
        if (this.Z < b()) {
            j = j(this.X.getResources().getColor(R.color.gs_line_normal));
            geoPolyline = new GeoPolyline();
            i = b();
            i2 = this.Z > 0 ? this.Z : 0;
            while (i2 < i) {
                int i4;
                GeoCoordinate coordinate = ((c) this.p.get(i2)).a.getCoordinate();
                if (coordinate != null) {
                    i4 = i3 + 1;
                    geoPolyline.add(coordinate);
                } else {
                    i4 = i3;
                }
                i2++;
                i3 = i4;
            }
            if (i3 > 1) {
                j.setGeoPolyline(geoPolyline);
                j.setZIndex(65520);
                this.N.addMapObject(j);
                if (this.O != null) {
                    this.N.removeMapObject(this.O);
                }
                this.O = j;
            } else if (this.O != null) {
                this.N.removeMapObject(this.O);
            }
        }
    }

    public Point l() {
        return this.aa == null ? null : c(this.aa);
    }

    public void b(boolean z) {
        if (z || this.aa == null || b() <= 1 || !this.aV || !this.m) {
            if (this.R != null) {
                this.N.removeMapObject(this.R);
                this.R = null;
            }
        } else if (this.Z == 0) {
            MapObject j = j(this.X.getResources().getColor(R.color.gs_line_flying));
            GeoPolyline geoPolyline = new GeoPolyline();
            geoPolyline.add(i(1).a.getCoordinate());
            geoPolyline.add(this.aa.getCoordinate());
            j.setGeoPolyline(geoPolyline);
            this.N.addMapObject(j);
            if (this.R != null) {
                this.N.removeMapObject(this.R);
            }
            this.R = j;
            if (!EventView.b) {
                O();
            }
        } else if (this.R != null) {
            this.N.removeMapObject(this.R);
            this.R = null;
        }
    }

    public void a(boolean z, boolean z2) {
        if (this.N != null) {
            if (z2 || this.ab == null) {
                if (this.S != null) {
                    this.N.removeMapObject(this.S);
                    this.S = null;
                }
            } else if (this.aa != null && this.ab != null) {
                MapPolyline j;
                if (this.S == null || this.aX != z) {
                    this.aX = z;
                    if (z) {
                        j = j(this.X.getResources().getColor(R.color.gs_line_backhome));
                    } else {
                        j = j(this.X.getResources().getColor(R.color.gs_line_livehome));
                    }
                    j.setZIndex(65521);
                    this.N.addMapObject(j);
                    if (this.S != null) {
                        this.N.removeMapObject(this.S);
                    }
                    this.S = j;
                } else {
                    j = this.S;
                }
                GeoPolyline geoPolyline = new GeoPolyline();
                geoPolyline.add(this.ab.getCoordinate());
                geoPolyline.add(this.aa.getCoordinate());
                j.setGeoPolyline(geoPolyline);
                N();
            }
        }
    }

    public int a(Point point) {
        Q();
        for (int i = 1; i < b() - 1; i++) {
            if (b(point, (Point) this.q.get(i))) {
                return i;
            }
        }
        return -1;
    }

    private void Q() {
        this.q.clear();
        for (int i = 0; i < b(); i++) {
            this.q.add(c(((c) this.p.get(i)).a));
        }
    }

    public ArrayList<Point> m() {
        Q();
        return this.q;
    }

    public Point e(int i) {
        return c(((c) this.p.get(i)).a);
    }

    public void a(int i, dji.gs.views.b bVar) {
        c cVar = (c) this.p.get(i);
        cVar.c = bVar;
        Image image = new Image();
        image.setBitmap(bVar.d());
        cVar.a.setIcon(image);
        this.p.set(i, cVar);
    }

    private MapMarker b(int i, boolean z) {
        c cVar = (c) this.p.get(i);
        cVar.c.a(z);
        Image image = new Image();
        image.setBitmap(cVar.c.d());
        cVar.a.setIcon(image);
        this.p.set(i, cVar);
        return cVar.a;
    }

    public void f(int i) {
        c cVar = (c) this.p.get(i);
        if (!cVar.b.f() && !cVar.c.c()) {
            Log.d("", "目标航点 setDisableIcon " + i);
            if (i == 1) {
                O();
            }
            cVar.c.b(true);
            Image image = new Image();
            image.setBitmap(cVar.c.d());
            cVar.a.setIcon(image);
            this.p.set(i, cVar);
        }
    }

    private void o(int i) {
        if (this.Y >= 0 && this.Y < b() && !(this.aW && this.Y == b() - 1)) {
            b(this.Y, false);
        }
        b(i, true);
        this.Y = i;
    }

    public void u() {
        b(this.Y, false);
    }

    public int e() {
        return this.Y;
    }

    private boolean b(Point point, Point point2) {
        int width = this.ai.getWidth();
        int height = this.ai.getHeight();
        float[] b = i(1).c.b();
        int[] iArr = new int[]{point2.x + ((int) (((float) width) * (1.0f - b[0]))), point2.x - ((int) (((float) width) * b[0])), point2.y, point2.y - height};
        if (point.x >= iArr[0] || point.x <= iArr[1] || point.y >= iArr[2] || point.y <= iArr[3]) {
            return false;
        }
        return true;
    }

    public void a(int i, Point point) {
        c cVar = (c) this.p.get(i);
        cVar.a.setCoordinate(c(point));
        this.p.set(i, cVar);
        k();
    }

    public float a(Point point, Point point2) {
        GeoCoordinate c = c(point);
        GeoCoordinate c2 = c(point2);
        float[] fArr = new float[1];
        Location.distanceBetween(c.getLatitude(), c.getLongitude(), c2.getLatitude(), c2.getLongitude(), fArr);
        return fArr[0];
    }

    public FlyForbidParam n() {
        return this.ba;
    }

    public void h(dji.gs.e.b bVar) {
        if (bVar == null) {
            MapCircle mapCircle;
            MapMarker mapMarker;
            this.w.clear();
            Iterator it = this.x.iterator();
            while (it.hasNext()) {
                mapCircle = (MapCircle) it.next();
                if (mapCircle != null) {
                    this.N.removeMapObject(mapCircle);
                }
            }
            this.x.clear();
            this.y.clear();
            it = this.z.iterator();
            while (it.hasNext()) {
                mapCircle = (MapCircle) it.next();
                if (mapCircle != null) {
                    this.N.removeMapObject(mapCircle);
                }
            }
            this.z.clear();
            this.s.clear();
            it = this.t.iterator();
            while (it.hasNext()) {
                mapCircle = (MapCircle) it.next();
                if (mapCircle != null) {
                    this.N.removeMapObject(mapCircle);
                }
            }
            this.t.clear();
            this.u.clear();
            it = this.v.iterator();
            while (it.hasNext()) {
                mapCircle = (MapCircle) it.next();
                if (mapCircle != null) {
                    this.N.removeMapObject(mapCircle);
                }
            }
            this.v.clear();
            it = this.D.iterator();
            while (it.hasNext()) {
                MapPolygon mapPolygon = (MapPolygon) it.next();
                if (mapPolygon != null) {
                    this.N.removeMapObject(mapPolygon);
                }
            }
            this.D.clear();
            it = this.E.iterator();
            while (it.hasNext()) {
                mapMarker = (MapMarker) it.next();
                if (mapMarker != null) {
                    this.N.removeMapObject(mapMarker);
                }
            }
            this.E.clear();
            it = this.F.iterator();
            while (it.hasNext()) {
                mapMarker = (MapMarker) it.next();
                if (mapMarker != null) {
                    this.N.removeMapObject(mapMarker);
                }
            }
            this.F.clear();
            it = this.G.iterator();
            while (it.hasNext()) {
                mapMarker = (MapMarker) it.next();
                if (mapMarker != null) {
                    this.N.removeMapObject(mapMarker);
                }
            }
            this.G.clear();
            this.H.clear();
            this.I.clear();
            L();
        } else if (bVar.b != Map.MOVE_PRESERVE_ZOOM_LEVEL || bVar.c != Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            this.ba = new FlyForbidParam();
            List checkResult = DJIFlyForbidController.getInstance().getCheckResult();
            if (checkResult != null && !checkResult.isEmpty()) {
                int[] iArr = new int[0];
                String[] strArr = new String[0];
                if (checkResult != null) {
                    Log.e("", "getLimits 2, mCheckList.size() =" + checkResult.size());
                    this.aY = 0;
                    this.aZ.clear();
                    for (int i = 0; i < checkResult.size(); i++) {
                        if (((FlyForbidElement) checkResult.get(i)).disable != 1) {
                            if (((FlyForbidElement) checkResult.get(i)).level == LevelType.WARNING.value()) {
                                this.aZ.add(checkResult.get(i));
                            } else {
                                this.aY++;
                            }
                        }
                    }
                    this.ba.count = this.aY;
                    double[] dArr = new double[this.ba.count];
                    double[] dArr2 = new double[this.ba.count];
                    double[] dArr3 = new double[this.ba.count];
                    double[] dArr4 = new double[this.ba.count];
                    double[] dArr5 = new double[this.ba.count];
                    int[] iArr2 = new int[this.ba.count];
                    String[] strArr2 = new String[this.ba.count];
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
                    this.ba.SetForbidPoint(dArr, dArr2, dArr3, dArr4, dArr5, this.ba.count);
                    strArr = strArr2;
                    iArr = iArr2;
                } else {
                    DJILogHelper.getInstance().LOGE("NFZ", "getLimits 2, mCheckList == null ", true, true);
                    FlyForbid.native_CheckNearForbidPoints(bVar.c, bVar.b, this.ba);
                }
                NFZLogUtil.savedLOGD("HMap start rendering ignored warning area, size: " + this.ba.count);
                int i5 = this.ba.count;
                int i6 = 0;
                while (i6 < i5) {
                    if (this.ba.ForbidType[i6] == 0.0d || this.ba.ForbidType[i6] == 11.0d || this.ba.ForbidType[i6] == 13.0d || this.ba.ForbidType[i6] == 23.0d || this.ba.ForbidType[i6] == 24.0d || this.ba.ForbidType[i6] == 26.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            a(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6]);
                        } else if (this.ba.ForbidType[i6] == 13.0d) {
                            c(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6]);
                        } else {
                            b(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6]);
                        }
                    } else if (this.ba.ForbidType[i6] == 1.0d || this.ba.ForbidType[i6] == 2.0d || this.ba.ForbidType[i6] == 29.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            a(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6], strArr[i6]);
                        } else {
                            b(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6], strArr[i6]);
                        }
                    } else if (this.ba.ForbidType[i6] == 27.0d || this.ba.ForbidType[i6] == 28.0d) {
                        c(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6], strArr[i6]);
                    } else if (this.ba.ForbidType[i6] == 12.0d) {
                        a(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), LevelType.CAN_UNLIMIT, a.AIRPORT);
                    } else if (this.ba.ForbidType[i6] != 3.0d) {
                        if (iArr[i6] == LevelType.CAN_NOT_UNLIMIT.value()) {
                            a(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6], strArr[i6]);
                        } else {
                            b(dji.gs.utils.a.a(new dji.gs.e.b(this.ba.ForbidLat[i6] / this.bb, this.ba.ForbidLon[i6] / this.bb)), (int) this.ba.ForbidRadius[i6], strArr[i6]);
                        }
                    }
                    i6++;
                }
                for (i6 = 0; i6 < this.aZ.size(); i6++) {
                    FlyForbidElement flyForbidElement = (FlyForbidElement) this.aZ.get(i6);
                    if (flyForbidElement.type == 14) {
                        a(dji.gs.utils.a.a(new dji.gs.e.b(flyForbidElement.lat, flyForbidElement.lng)), LevelType.WARNING, a.AIRPORT);
                    } else if (flyForbidElement.type == 19) {
                        a(dji.gs.utils.a.a(new dji.gs.e.b(flyForbidElement.lat, flyForbidElement.lng)), LevelType.WARNING, a.HELICOPTER);
                    } else {
                        b(dji.gs.utils.a.a(new dji.gs.e.b(((FlyForbidElement) this.aZ.get(i6)).lat, ((FlyForbidElement) this.aZ.get(i6)).lng)), ((FlyForbidElement) this.aZ.get(i6)).radius, flyForbidElement.type);
                    }
                }
                if (this.ab != null) {
                    this.N.removeMapObject(this.ab);
                    this.ab = new MapMarker().setDraggable(false).setCoordinate(l).setAnchorPoint(a(this.ac, new PointF(h[0], h[1]))).setIcon(this.ac);
                    this.N.addMapObject(this.ab);
                }
                NFZLogUtil.savedLOGD("HMap finished rendering!");
            }
        }
    }

    public void a(ArrayList<DJIFlightLimitAreaModel> arrayList) {
        int i = 0;
        MapCircle mapCircle;
        if (arrayList == null) {
            this.A.clear();
            Iterator it = this.B.iterator();
            while (it.hasNext()) {
                mapCircle = (MapCircle) it.next();
                if (mapCircle != null) {
                    this.N.removeMapObject(mapCircle);
                }
            }
            it = this.C.iterator();
            while (it.hasNext()) {
                mapCircle = (MapCircle) it.next();
                if (mapCircle != null) {
                    this.N.removeMapObject(mapCircle);
                }
            }
            return;
        }
        int size = arrayList.size();
        if (this.B.size() == 0) {
            while (i < size) {
                if (p(((DJIFlightLimitAreaModel) arrayList.get(i)).type)) {
                    a(dji.gs.utils.a.a(new dji.gs.e.b((((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).latitude) * 1.0d) / 1000000.0d, (((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).longitude) * 1.0d) / 1000000.0d)), ((DJIFlightLimitAreaModel) arrayList.get(i)).innerRadius, ((DJIFlightLimitAreaModel) arrayList.get(i)).outerRadius);
                }
                i++;
            }
            return;
        }
        this.A.clear();
        Iterator it2 = this.B.iterator();
        while (it2.hasNext()) {
            mapCircle = (MapCircle) it2.next();
            if (mapCircle != null) {
                this.N.removeMapObject(mapCircle);
            }
        }
        it2 = this.C.iterator();
        while (it2.hasNext()) {
            mapCircle = (MapCircle) it2.next();
            if (mapCircle != null) {
                this.N.removeMapObject(mapCircle);
            }
        }
        while (i < size) {
            if (p(((DJIFlightLimitAreaModel) arrayList.get(i)).type)) {
                a(dji.gs.utils.a.a(new dji.gs.e.b((((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).latitude) * 1.0d) / 1000000.0d, (((double) ((DJIFlightLimitAreaModel) arrayList.get(i)).longitude) * 1.0d) / 1000000.0d)), ((DJIFlightLimitAreaModel) arrayList.get(i)).innerRadius, ((DJIFlightLimitAreaModel) arrayList.get(i)).outerRadius);
            }
            i++;
        }
    }

    private boolean p(int i) {
        return i == 0 || i == 9;
    }

    public boolean o() {
        int b = b() - 1;
        for (int i = 0; i < this.s.size(); i++) {
            int radius = (int) ((MapCircle) this.t.get(i)).getRadius();
            for (int i2 = 0; i2 < b; i2++) {
                dji.gs.e.b b2 = dji.gs.utils.a.b(a(((c) this.p.get(i2)).a.getCoordinate()));
                dji.gs.e.b b3 = dji.gs.utils.a.b(a(((c) this.p.get(i2 + 1)).a.getCoordinate()));
                dji.gs.e.b bVar = new dji.gs.e.b(((dji.gs.e.b) this.s.get(i)).b, ((dji.gs.e.b) this.s.get(i)).c);
                if (FlyForbid.native_intersectSegCircle((double) dji.gs.utils.c.a(b2, b3), (double) dji.gs.utils.c.a(bVar, b2), (double) dji.gs.utils.c.a(bVar, b3), radius)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean p() {
        if (P()[0] > OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) {
            return true;
        }
        return false;
    }

    public boolean q() {
        int b = b();
        for (int i = 1; i < b; i++) {
            if (dji.gs.utils.c.a(a(l), a(i(i).a.getCoordinate())) > DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) {
                return false;
            }
        }
        return true;
    }

    public void c(boolean z) {
        this.bd = z;
    }

    public boolean r() {
        return (this.bd || this.bc) ? false : true;
    }

    public boolean j(dji.gs.e.b bVar) {
        return dji.gs.utils.c.a(bVar, a(l)) <= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition;
    }

    public void g(int i) {
        if (i >= 1) {
            o(i);
            Point point = (Point) this.q.get(this.Y);
            i(a(((c) this.p.get(i)).a.getCoordinate()));
            if (this.bi.a() == dji.gs.d.e.a.EDIT) {
                d(point);
            }
            if (this.bi.a() == dji.gs.d.e.a.GOING) {
                e(point);
            }
        }
    }

    private void a(MapMarker mapMarker, boolean z) {
        a(mapMarker, z, null);
    }

    private void a(MapMarker mapMarker, boolean z, Runnable runnable) {
        final GeoCoordinate coordinate = mapMarker.getCoordinate();
        final long uptimeMillis = SystemClock.uptimeMillis();
        final TimeInterpolator bounceInterpolator = z ? new BounceInterpolator() : new AccelerateInterpolator(2.0f);
        final Point c = c(mapMarker);
        final int i = c.y;
        final MapMarker mapMarker2 = mapMarker;
        final Runnable runnable2 = runnable;
        this.bh.post(new Runnable(this) {
            final /* synthetic */ a h;

            public void run() {
                float min = Math.min(bounceInterpolator.getInterpolation(((float) (SystemClock.uptimeMillis() - uptimeMillis)) / 300.0f), 1.0f);
                GeoCoordinate a = this.h.c(new Point(c.x, Math.round(((float) i) * min)));
                if (a != null) {
                    if (min == 1.0f) {
                        a = coordinate;
                    }
                    mapMarker2.setCoordinate(a);
                    if (min < 1.0f) {
                        this.h.bh.postDelayed(this, 20);
                        return;
                    }
                    this.h.bc = false;
                    this.h.m = true;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }
        });
    }

    private void d(Point point) {
        this.bh.sendMessageDelayed(this.bh.obtainMessage(0, point), 100);
    }

    private void e(Point point) {
        this.bh.sendMessageDelayed(this.bh.obtainMessage(1, point), 200);
    }

    public void a(dji.gs.d.e eVar) {
        this.bi = eVar;
    }

    public void a(float f) {
        this.az = f;
    }

    public Point b(Point point) {
        float a = dji.gs.utils.c.a(a(c(point)), a(l));
        if (a <= DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition) {
            return null;
        }
        Point b = b(l);
        a = DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition / a;
        point.x = (int) (((float) b.x) - (((float) (b.x - point.x)) * a));
        point.y = (int) (((float) b.y) - (a * ((float) (b.y - point.y))));
        return point;
    }

    public dji.gs.e.b f() {
        if (l == null) {
            return null;
        }
        return a(l);
    }

    public void s() {
        if (l != null && l != null) {
            this.N.setCenter(l, Map$Animation.BOW);
        }
    }

    public void i(dji.gs.e.b bVar) {
        if (bVar != null && this.N != null) {
            this.N.setCenter(l(dji.gs.utils.a.a(bVar)), Map$Animation.BOW);
        }
    }

    public void b(float f) {
        if (this.aE) {
            this.N.setOrientation(f, Map$Animation.LINEAR);
        }
    }

    public dji.gs.e.b b(Object obj) {
        return a(((MapMarker) obj).getCoordinate());
    }

    public void a(int i, int i2) {
    }

    public void f(dji.gs.e.b bVar) {
        if ((bVar.b != 0.0d || bVar.c != 0.0d) && this.N != null) {
            if (this.aD == null) {
                Map map = this.N;
                MapObject icon = new MapMarker().setDraggable(false).setCoordinate(l(dji.gs.utils.a.a(bVar))).setAnchorPoint(a(this.am, new PointF(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c))).setIcon(this.am);
                this.aD = icon;
                map.addMapObject(icon);
                return;
            }
            if (this.ao != this.am) {
                this.ao = this.am;
                this.aD.setIcon(this.am);
            }
            this.aD.setCoordinate(l(dji.gs.utils.a.a(bVar)));
        }
    }

    public void b(dji.gs.e.b bVar, boolean z) {
        if ((bVar.b != 0.0d || bVar.c != 0.0d) && this.N != null) {
            Image image = z ? this.am : this.an;
            if (this.aD == null) {
                Map map = this.N;
                MapObject icon = new MapMarker().setDraggable(false).setCoordinate(l(dji.gs.utils.a.a(bVar))).setAnchorPoint(a(image, new PointF(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c))).setIcon(image);
                this.aD = icon;
                map.addMapObject(icon);
                return;
            }
            if (this.ao != image) {
                this.ao = image;
                this.aD.setIcon(image);
            }
            this.aD.setCoordinate(l(dji.gs.utils.a.a(bVar)));
        }
    }

    public void d(boolean z) {
        this.bk = z;
    }

    public void b(int i, int i2) {
        this.bl = this.N.getMapState();
        if (this.aa != null || l != null) {
            this.au = true;
            if (this.aa != null || l != null) {
                GeoBoundingBox geoBoundingBox;
                if (this.aa == null || l == null) {
                    geoBoundingBox = new GeoBoundingBox(this.aa == null ? l : this.aa.getCoordinate(), 50.0f, 50.0f);
                } else {
                    geoBoundingBox = new GeoBoundingBox(this.aa.getCoordinate(), 50.0f, 50.0f).merge(new GeoBoundingBox(l, 50.0f, 50.0f));
                }
                try {
                    this.N.zoomTo(geoBoundingBox, i, i2, Map$Animation.BOW, -1.0f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.au = false;
            }
        }
    }

    public void w() {
        this.N.setCenter(this.bl.getCenter(), Map$Animation.BOW, this.bl.getZoomLevel(), this.bl.getOrientation(), this.bl.getTilt());
    }

    public void a(SparseArray<Point> sparseArray) {
        MapObject d = d(this.X.getResources().getColor(R.color.gs_paint_blue), 20);
        GeoPolyline geoPolyline = new GeoPolyline();
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            geoPolyline.add(c((Point) sparseArray.get(i)));
        }
        d.setGeoPolyline(geoPolyline);
        this.N.addMapObject(d);
        this.W.add(d);
    }

    public void x() {
        if (this.W != null) {
            for (int i = 0; i < this.W.size(); i++) {
                this.N.removeMapObject((MapObject) this.W.get(i));
            }
        }
    }

    public void y() {
        try {
            throw new Throwable("not support clear method in here map sdk");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(dji.gs.d.b bVar) {
        this.bm = bVar;
    }

    public void a(dji.gs.d.c cVar) {
        this.bn = cVar;
    }

    public void A() {
        this.o.getScreenCapture(new OnScreenCaptureListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onScreenCaptured(Bitmap bitmap) {
                this.a.bn.a(bitmap);
            }
        });
    }

    public boolean B() {
        return true;
    }

    public void a(Context context) {
        this.bo = new AMapLocationClient(context.getApplicationContext());
    }

    public void a(long j, float f, final dji.gs.d.a aVar) {
        if (this.bp == null) {
            if (this.bo.isStarted()) {
                this.bo.unRegisterLocationListener(this.bp);
                this.bo.stopLocation();
            }
            this.bp = new AMapLocationListener(this) {
                final /* synthetic */ a b;

                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation != null) {
                        AMapLocation aMapLocation2 = (AMapLocation) dji.a.a.getInstance().a(this.b.bq, aMapLocation);
                        if (aMapLocation2 != null) {
                            if (aMapLocation2.getErrorCode() != 0) {
                                DJILogHelper.getInstance().LOGD("", aMapLocation2.getErrorInfo());
                            } else if (this.b.bq != aMapLocation2) {
                                this.b.bq = aMapLocation2;
                                double latitude = aMapLocation2.getLatitude();
                                double longitude = aMapLocation2.getLongitude();
                                float altitude = (float) aMapLocation2.getAltitude();
                                float accuracy = aMapLocation2.getAccuracy();
                                if (accuracy == 0.0f) {
                                    accuracy = this.b.R();
                                }
                                aVar.a(dji.gs.utils.a.d(new dji.gs.e.b(latitude, longitude, altitude, accuracy)));
                            }
                        }
                    }
                }
            };
        }
        this.bo.setLocationListener(this.bp);
        this.n.setLocationMode(AMapLocationMode.Hight_Accuracy);
        this.n.setInterval(j);
        this.n.setNeedAddress(false);
        this.bo.setLocationOption(this.n);
        this.bo.startLocation();
    }

    private float R() {
        dji.gs.e.b C = C();
        if (C != null) {
            return C.e;
        }
        return 10000.0f;
    }

    public dji.gs.e.b C() {
        Location e = dji.a.a.getInstance().e();
        if (e == null) {
            return null;
        }
        return new dji.gs.e.b(e.getLatitude(), e.getLongitude(), (float) e.getAltitude(), e.getAccuracy());
    }

    public void D() {
        if (this.bo != null) {
            this.bo.unRegisterLocationListener(this.bp);
            this.bo.stopLocation();
        }
    }

    public void b(dji.gs.e.b bVar, double d) {
        if (this.bs == null) {
            this.bs = new MapMarker();
            this.N.addMapObject(this.bs);
        } else if (bVar == null) {
            this.N.removeMapObject(this.bs);
            this.bs = null;
        }
        if (this.bu == null) {
            this.bu = new MapCircle();
            this.N.addMapObject(this.bu);
        } else if (bVar == null) {
            this.N.removeMapObject(this.bu);
            this.bu = null;
        }
        if (bVar != null) {
            this.bs.setDraggable(false).setCoordinate(new GeoCoordinate(bVar.b, bVar.c)).setAnchorPoint(a(this.bt, new PointF(h[0], h[1]))).setIcon(this.bt).setZIndex(65537);
            if (d > 0.0d) {
                this.bu.setCenter(new GeoCoordinate(bVar.b, bVar.c)).setRadius(d).setLineWidth(4).setLineColor(Color.rgb(113, FTPCodes.ENTER_PASSIVE_MODE, 21));
            }
        }
    }

    public dji.gs.e.b E() {
        if (this.bs == null) {
            return null;
        }
        GeoCoordinate coordinate = this.bs.getCoordinate();
        return new dji.gs.e.b(coordinate.getLatitude(), coordinate.getLongitude());
    }

    public float F() {
        if (this.bo != null) {
            Location lastKnownLocation = this.bo.getLastKnownLocation();
            if (lastKnownLocation != null) {
                return lastKnownLocation.getAccuracy();
            }
        }
        return 10000.0f;
    }

    public void c(dji.gs.e.b bVar, double d) {
        if (!this.av.contains(bVar)) {
            this.av.add(bVar);
            if (d > 0.0d) {
                Map map = this.N;
                MapObject fillColor = new MapCircle().setCenter(l(bVar)).setRadius(d).setLineWidth(5).setLineColor(this.ax).setFillColor(this.ay);
                map.addMapObject(fillColor);
                this.aw.add(fillColor);
            }
        }
    }

    public void G() {
        this.av.clear();
        Iterator it = this.aw.iterator();
        while (it.hasNext()) {
            this.N.removeMapObject((MapCircle) it.next());
        }
        this.aw.clear();
    }

    public void H() {
        a(this.B, false);
        a(this.C, false);
        a(this.t, false);
        a(this.v, false);
        a(this.x, false);
    }

    private void a(List<MapCircle> list, boolean z) {
        for (MapCircle mapCircle : list) {
            if (!(mapCircle == null || mapCircle.isVisible() == z)) {
                mapCircle.setVisible(z);
            }
        }
    }

    public void I() {
        a(this.B, true);
        a(this.C, true);
        a(this.t, true);
        a(this.v, true);
        a(this.x, true);
    }

    private void a(ArrayList<MapMarker> arrayList, boolean z) {
        if (arrayList != null && arrayList.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((MapMarker) it.next()).setVisible(z);
            }
        }
    }

    public void c(int i, int i2) {
        int a = this.L.a(i);
        if ((i == 14 || i == 19) && this.bv) {
            if (i != 14 || this.H.size() <= 0) {
                if (i == 19 && this.I.size() > 0) {
                    if (i2 == dji.gs.b.c.a.WARN_SHOW.a() && !((MapMarker) this.I.get(0)).isVisible()) {
                        a(this.I, true);
                    } else if (i2 != dji.gs.b.c.a.WARN_SHOW.a() && ((MapMarker) this.I.get(0)).isVisible()) {
                        a(this.I, false);
                    }
                }
            } else if (i2 == dji.gs.b.c.a.WARN_SHOW.a() && !((MapMarker) this.H.get(0)).isVisible()) {
                a(this.H, true);
            } else if (i2 != dji.gs.b.c.a.WARN_SHOW.a() && ((MapMarker) this.H.get(0)).isVisible()) {
                a(this.H, false);
            }
        }
        if (((ArrayList) this.J.get(a)).size() != 0) {
            Iterator it;
            if (i2 == dji.gs.b.c.a.WARN_SHOW.a()) {
                if (!((MapCircle) ((ArrayList) this.J.get(a)).get(0)).isVisible()) {
                    it = ((ArrayList) this.J.get(a)).iterator();
                    while (it.hasNext()) {
                        ((MapCircle) it.next()).setVisible(true);
                    }
                }
            } else if (((MapCircle) ((ArrayList) this.J.get(a)).get(0)).isVisible()) {
                it = ((ArrayList) this.J.get(a)).iterator();
                while (it.hasNext()) {
                    ((MapCircle) it.next()).setVisible(false);
                }
            }
        }
    }

    public void c(float f) {
        Iterator it;
        if (f < 10.0f && this.bv) {
            this.bv = false;
            it = this.G.iterator();
            while (it.hasNext()) {
                ((MapMarker) it.next()).setVisible(false);
            }
            it = this.F.iterator();
            while (it.hasNext()) {
                ((MapMarker) it.next()).setVisible(false);
            }
        }
        if (f > 10.0f && !this.bv) {
            this.bv = true;
            if (this.L.b(14) == dji.gs.b.c.a.WARN_SHOW.a()) {
                it = this.H.iterator();
                while (it.hasNext()) {
                    ((MapMarker) it.next()).setVisible(true);
                }
            }
            if (this.L.b(19) == dji.gs.b.c.a.WARN_SHOW.a()) {
                it = this.I.iterator();
                while (it.hasNext()) {
                    ((MapMarker) it.next()).setVisible(true);
                }
            }
            it = this.F.iterator();
            while (it.hasNext()) {
                ((MapMarker) it.next()).setVisible(true);
            }
        }
    }

    public void a(int i, dji.gs.e.b... bVarArr) {
        int i2 = 1;
        if (bVarArr != null && bVarArr.length != 0) {
            GeoBoundingBox geoBoundingBox = new GeoBoundingBox(l(bVarArr[0]), 50.0f, 50.0f);
            if (bVarArr.length > 1) {
                while (i2 < bVarArr.length) {
                    geoBoundingBox = geoBoundingBox.merge(new GeoBoundingBox(l(bVarArr[i2]), 50.0f, 50.0f));
                    i2++;
                }
            }
            try {
                this.N.zoomTo(geoBoundingBox, Map$Animation.BOW, -1.0f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
