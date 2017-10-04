package dji.pilot.dji_groundstation.controller.DataMgr;

import android.content.Context;
import android.os.Handler;
import com.dji.frame.c.c;
import com.here.android.mpa.mapping.Map;
import dji.gs.e.b;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.FINISH_ACTION;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.YAW_MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class e extends a {
    public static final int b = 257;
    public static final int c = 258;
    public static final int d = 2000;
    private static final String e = "WayPointDataMgr";
    private static final String f = "SELECT * FROM dji_pilot_groundStation_db_DJIWPCollectionItem";
    private static e o = null;
    private Handler g = null;
    private Context h = null;
    private List<DJIWPCollectionItem> i = new ArrayList();
    private List<DJIWPCollectionItem> j = new ArrayList();
    private ArrayList<DJIWPCollectionItem> k = new ArrayList();
    private DJIWPCollectionItem l = null;
    private YAW_MODE m = YAW_MODE.PATH_COURSE;
    private FINISH_ACTION n = FINISH_ACTION.NO_LIMIT;
    private float p = 3.5f;
    private boolean q = false;
    private DJIWPCollectionItem r = null;

    private static final class a implements Comparator<DJIWPCollectionItem> {
        private a() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((DJIWPCollectionItem) obj, (DJIWPCollectionItem) obj2);
        }

        public int a(DJIWPCollectionItem dJIWPCollectionItem, DJIWPCollectionItem dJIWPCollectionItem2) {
            if (dJIWPCollectionItem.getAutoAddFlag() == dJIWPCollectionItem2.getAutoAddFlag()) {
                if (dJIWPCollectionItem.getCreatedDate() > dJIWPCollectionItem2.getCreatedDate()) {
                    return -1;
                }
                if (dJIWPCollectionItem.getCreatedDate() < dJIWPCollectionItem2.getCreatedDate()) {
                    return 1;
                }
                return 0;
            } else if (dJIWPCollectionItem.getAutoAddFlag() > dJIWPCollectionItem2.getAutoAddFlag()) {
                if (dJIWPCollectionItem2.getAutoAddFlag() != 0) {
                    return 1;
                }
                return -1;
            } else if (dJIWPCollectionItem.getAutoAddFlag() == 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (o == null) {
                o = new e();
            }
            eVar = o;
        }
        return eVar;
    }

    private e() {
    }

    public DJIWPCollectionItem d(int i) {
        if (i >= this.i.size()) {
            return null;
        }
        return (DJIWPCollectionItem) this.i.get(i);
    }

    public boolean i() {
        return this.q;
    }

    public void a(boolean z) {
        this.q = z;
    }

    private boolean h(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem == null) {
            return false;
        }
        if (this.i == null || this.i.isEmpty()) {
            return false;
        }
        if (this.i.contains(dJIWPCollectionItem) && dJIWPCollectionItem.getAutoAddFlag() <= 0) {
            return true;
        }
        for (DJIWPCollectionItem dJIWPCollectionItem2 : this.i) {
            if (dJIWPCollectionItem2.getCreatedDate() == dJIWPCollectionItem.getCreatedDate() && dJIWPCollectionItem.getAutoAddFlag() == dJIWPCollectionItem2.getAutoAddFlag()) {
                if (dJIWPCollectionItem.getAutoAddFlag() > 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public void a(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null && this.h != null && dJIWPCollectionItem.getPoints().size() > 1) {
            Object obj;
            int i;
            if (this.i.isEmpty()) {
                i = 1;
            } else {
                for (DJIWPCollectionItem dJIWPCollectionItem2 : this.i) {
                    if (dJIWPCollectionItem2.getCreatedDate() == dJIWPCollectionItem.getCreatedDate()) {
                        obj = dJIWPCollectionItem2.getAutoAddFlag() > 0 ? 1 : null;
                    }
                }
                i = 1;
            }
            if (obj != null) {
                if (dJIWPCollectionItem.getCreatedDate() == 0) {
                    dJIWPCollectionItem.setCreatedDate(System.currentTimeMillis());
                }
                dJIWPCollectionItem.setDistance(f.a(dJIWPCollectionItem.getPoints()));
                c.c(this.h).a(dJIWPCollectionItem);
                this.i.add(dJIWPCollectionItem);
            }
        }
    }

    public void b(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null && this.h != null) {
            if (dJIWPCollectionItem.getCreatedDate() == 0) {
                dJIWPCollectionItem.setCreatedDate(System.currentTimeMillis());
            }
            dJIWPCollectionItem.setDistance(f.a(dJIWPCollectionItem.getPoints()));
            c.c(this.h).e(dJIWPCollectionItem);
        }
    }

    public void c(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null && this.h != null) {
            for (DJIWPCollectionItem dJIWPCollectionItem2 : this.i) {
                if (dJIWPCollectionItem2.getCreatedDate() == dJIWPCollectionItem.getCreatedDate() && dJIWPCollectionItem2.getAutoAddFlag() <= 0) {
                    this.i.remove(dJIWPCollectionItem2);
                }
            }
            c.c(this.h).a(DJIWPCollectionItem.class);
            for (DJIWPCollectionItem dJIWPCollectionItem22 : this.i) {
                if (dJIWPCollectionItem != null && dJIWPCollectionItem.getPoints().size() > 1) {
                    c.c(this.h).a(dJIWPCollectionItem22);
                }
            }
        }
    }

    public boolean d(DJIWPCollectionItem dJIWPCollectionItem) {
        boolean z;
        if (!(dJIWPCollectionItem == null || dJIWPCollectionItem.getPoints() == null)) {
            for (DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint : dJIWPCollectionItem.getPoints()) {
                b b = dji.gs.utils.a.b(this.a);
                if (dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint.lat, dJIWPCollectionItem$WayPoint.lng, b.b, b.c) > FlyForbidProtocol.UNLOCK_RADIUS) {
                    d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_add_point_radius_limit));
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z) {
            this.l = new DJIWPCollectionItem(dJIWPCollectionItem);
            g(this.l);
        } else {
            this.l = null;
        }
        return z;
    }

    public DJIWPCollectionItem j() {
        return this.l;
    }

    public void a(Context context) {
        if (context != null) {
            int i;
            this.h = context.getApplicationContext();
            if (this.i == null) {
                this.i = new ArrayList();
            }
            if (this.i.isEmpty()) {
                List<DJIWPCollectionItem> c = c.c(this.h).c(DJIWPCollectionItem.class);
                B();
                for (DJIWPCollectionItem dJIWPCollectionItem : c) {
                    if (this.i.isEmpty()) {
                        this.i.add(dJIWPCollectionItem);
                    } else {
                        Object obj;
                        for (DJIWPCollectionItem dJIWPCollectionItem2 : this.i) {
                            if (dJIWPCollectionItem2.getCreatedDate() == dJIWPCollectionItem.getCreatedDate()) {
                                obj = dJIWPCollectionItem2.getAutoAddFlag() > 0 ? 1 : null;
                                if (obj != null) {
                                    this.i.add(dJIWPCollectionItem);
                                }
                            }
                        }
                        i = 1;
                        if (obj != null) {
                            this.i.add(dJIWPCollectionItem);
                        }
                    }
                }
            }
            Collections.sort(this.i, new a());
            ArrayList arrayList = new ArrayList();
            i = 0;
            for (DJIWPCollectionItem dJIWPCollectionItem3 : this.i) {
                if (dJIWPCollectionItem3.getAutoAddFlag() > 0) {
                    i++;
                    dJIWPCollectionItem3.setAutoAddFlag(i);
                    if (i > 3) {
                        arrayList.add(dJIWPCollectionItem3);
                    }
                }
                i = i;
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.i.remove((DJIWPCollectionItem) it.next());
            }
        }
    }

    public int k() {
        return this.i.size();
    }

    public void e(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null) {
            this.k.add(dJIWPCollectionItem);
        }
    }

    public void l() {
        Iterator it = this.k.iterator();
        while (it.hasNext()) {
            c((DJIWPCollectionItem) it.next());
        }
        this.k.clear();
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 23;
        eVar.t = null;
        dji.thirdparty.a.c.a().e(eVar);
    }

    private void B() {
        List<dji.thirdparty.afinal.d.a.b> b = c.c(this.h).b(f);
        this.j.clear();
        for (dji.thirdparty.afinal.d.a.b a : b) {
            DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) dji.thirdparty.afinal.d.a.a.a(a, DJIWPCollectionItem.class);
            dJIWPCollectionItem.setAutoAddFlag(-1);
            if (dJIWPCollectionItem != null) {
                this.i.add(dJIWPCollectionItem);
                this.j.add(dJIWPCollectionItem);
            } else {
                return;
            }
        }
    }

    public YAW_MODE m() {
        return this.m;
    }

    public void a(YAW_MODE yaw_mode) {
        this.m = yaw_mode;
    }

    public FINISH_ACTION n() {
        return this.n;
    }

    public void a(FINISH_ACTION finish_action) {
        this.n = finish_action;
    }

    public float o() {
        return this.p;
    }

    public void a(float f) {
        this.p = f;
    }

    public Handler p() {
        return this.g;
    }

    public void a(Handler handler) {
        this.g = handler;
    }

    public double e(int i) {
        if (this.l == null) {
            return 0.0d;
        }
        if (i >= this.l.getPoints().size()) {
            return 0.0d;
        }
        if (i <= 0 || i >= this.l.getPoints().size() - 1) {
            return 2.0d;
        }
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) this.l.getPoints().get(i - 1);
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint2 = (DJIWPCollectionItem$WayPoint) this.l.getPoints().get(i);
        DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint3 = (DJIWPCollectionItem$WayPoint) this.l.getPoints().get(i + 1);
        double a = dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng(), dJIWPCollectionItem$WayPoint2.getLat(), dJIWPCollectionItem$WayPoint2.getLng());
        double height = dJIWPCollectionItem$WayPoint.getHeight() - dJIWPCollectionItem$WayPoint2.getHeight();
        double sqrt = Math.sqrt((a * a) + (height * height));
        a = dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint2.getLat(), dJIWPCollectionItem$WayPoint2.getLng(), dJIWPCollectionItem$WayPoint3.getLat(), dJIWPCollectionItem$WayPoint3.getLng());
        height = dJIWPCollectionItem$WayPoint2.getHeight() - dJIWPCollectionItem$WayPoint3.getHeight();
        double sqrt2 = Math.sqrt((a * a) + (height * height));
        a = dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng(), dJIWPCollectionItem$WayPoint3.getLat(), dJIWPCollectionItem$WayPoint3.getLng());
        height = dJIWPCollectionItem$WayPoint.getHeight() - dJIWPCollectionItem$WayPoint3.getHeight();
        double sqrt3 = Math.sqrt((a * a) + (height * height));
        return a(2.0d, 40.0d, a(2.0d, 40.0d, (Math.min(sqrt, sqrt2) - 1.0d) / 2.0d) * a(0.2d, 1.0d, Math.sqrt(Math.acos(a(Map.MOVE_PRESERVE_ZOOM_LEVEL, 1.0d, (((sqrt * sqrt) + (sqrt2 * sqrt2)) - (sqrt3 * sqrt3)) / ((2.0d * sqrt) * sqrt2))) / 3.141592653589793d)));
    }

    public boolean f(DJIWPCollectionItem dJIWPCollectionItem) {
        boolean z;
        if (!(dJIWPCollectionItem == null || dJIWPCollectionItem.getPoints() == null)) {
            for (DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint : dJIWPCollectionItem.getPoints()) {
                b b = dji.gs.utils.a.b(this.a);
                if (dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint.lat, dJIWPCollectionItem$WayPoint.lng, b.b, b.c) > FlyForbidProtocol.UNLOCK_RADIUS) {
                    d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_add_point_radius_limit));
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z) {
            this.r = dJIWPCollectionItem;
        } else {
            this.r = null;
        }
        return z;
    }

    public void q() {
        if (this.r == null) {
            this.r = new DJIWPCollectionItem();
        } else {
            this.r.getPoints().clear();
        }
    }

    public void a(DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint) {
        if (dJIWPCollectionItem$WayPoint != null) {
            if (this.r == null) {
                this.r = new DJIWPCollectionItem();
            }
            if (this.r.getPoints().isEmpty()) {
                this.r.getPoints().add(dJIWPCollectionItem$WayPoint);
                return;
            }
            DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint2 = (DJIWPCollectionItem$WayPoint) this.r.getPoints().get(this.r.getPoints().size() - 1);
            if (dJIWPCollectionItem$WayPoint2.getLat() != dJIWPCollectionItem$WayPoint.getLat() || dJIWPCollectionItem$WayPoint2.getLng() != dJIWPCollectionItem$WayPoint.getLng()) {
                b b = dji.gs.utils.a.b(this.a);
                if (dji.gs.utils.a.a(dJIWPCollectionItem$WayPoint.lat, dJIWPCollectionItem$WayPoint.lng, b.b, b.c) > FlyForbidProtocol.UNLOCK_RADIUS) {
                    d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_add_point_radius_limit));
                } else {
                    this.r.getPoints().add(dJIWPCollectionItem$WayPoint);
                }
            }
        }
    }

    public void r() {
        if (this.r == null) {
            this.r = new DJIWPCollectionItem();
        }
        if (this.r.getPoints().size() >= 99) {
            d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_add_point_size_limit));
        } else if (DataOsdGetPushCommon.getInstance().isSwaveWork() && DataOsdGetPushCommon.getInstance().getSwaveHeight() <= 30) {
            d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_has_ultrasonic_data));
        } else if (f.a(this.r.getPoints()) > FlyForbidProtocol.STRONG_WARNING_CHECK_RADIUS) {
            d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_distance_too_long));
        } else if (DataOsdGetPushCommon.getInstance().getGpsLevel() < 3 || this.a == null || this.a.b == 0.0d || this.a.c == 0.0d) {
            d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_add_point_gps_weak));
        } else {
            double b = f.b();
            double c = f.c();
            double a = f.a();
            int yaw = DataOsdGetPushCommon.getInstance().getYaw() / 10;
            int yawAngle = DataGimbalGetPushParams.getInstance().getYawAngle() / 10;
            b b2 = dji.gs.utils.a.b(this.a);
            b b3 = dji.gs.utils.a.b(new b(b, c));
            if (dji.gs.utils.a.a(b3.b, b3.c, b2.b, b2.c) > FlyForbidProtocol.UNLOCK_RADIUS) {
                d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_add_point_radius_limit));
            } else if (a < 3.0d) {
                d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_has_ultrasonic_data));
            } else {
                if (a < 10.0d) {
                    d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_attitude_too_low_warning));
                }
                if (this.r.getPoints().size() > 0) {
                    DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) this.r.getPoints().get(this.r.getPoints().size() - 1);
                    double a2 = dji.gs.utils.a.a(b, c, dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng());
                    if (Math.sqrt((a2 * a2) + ((a - dJIWPCollectionItem$WayPoint.getHeight()) * (a - dJIWPCollectionItem$WayPoint.getHeight()))) < 5.0d) {
                        d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_add_point_distance_limit));
                        return;
                    }
                }
                DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint2 = new DJIWPCollectionItem$WayPoint(b, c, a);
                dJIWPCollectionItem$WayPoint2.setCraftYaw(yaw);
                dJIWPCollectionItem$WayPoint2.setGimbalYaw(yawAngle);
                dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
                eVar.s = 5;
                eVar.t = dJIWPCollectionItem$WayPoint2;
                dji.thirdparty.a.c.a().e(eVar);
                this.r.getPoints().add(dJIWPCollectionItem$WayPoint2);
            }
        }
    }

    public void s() {
        if (this.r != null && this.r.getPoints().size() > 0) {
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 22;
            eVar.t = Integer.valueOf(this.r.getPoints().size() - 1);
            dji.thirdparty.a.c.a().e(eVar);
            this.r.getPoints().remove(this.r.getPoints().size() - 1);
        }
    }

    public void t() {
        if (this.r != null) {
            this.r.getPoints().clear();
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            eVar.t = null;
            dji.thirdparty.a.c.a().e(eVar);
        }
    }

    public void u() {
        this.l = new DJIWPCollectionItem(this.r);
        g(this.l);
    }

    public void g(DJIWPCollectionItem dJIWPCollectionItem) {
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 1;
        dji.thirdparty.a.c.a().e(eVar);
        if (dJIWPCollectionItem != null) {
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 2;
            eVar.t = dJIWPCollectionItem;
            dji.thirdparty.a.c.a().e(eVar);
        }
    }

    public int v() {
        if (this.r == null) {
            return 0;
        }
        return this.r.getPoints().size();
    }

    public double w() {
        if (this.r == null) {
            return 0.0d;
        }
        return f.a(this.r.getPoints());
    }

    public boolean x() {
        if (this.j != null && h(this.l)) {
            return true;
        }
        return false;
    }

    public void y() {
        if (this.l != null) {
            this.l.setAutoAddFlag(1);
            a(this.l);
        }
    }

    public void z() {
        if (this.l != null && !h(this.l)) {
            this.l.setAutoAddFlag(-1);
            a(this.l);
        }
    }

    public void A() {
        if (this.l != null) {
            c(this.l);
        }
    }

    public void e() {
        super.e();
        o = null;
        this.l = null;
        this.r = null;
    }

    private double a(double d, double d2, double d3) {
        if (d3 > d2) {
            return d2;
        }
        if (d3 < d) {
            return d;
        }
        return d3;
    }
}
