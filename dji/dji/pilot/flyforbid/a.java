package dji.pilot.flyforbid;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.android.mpa.mapping.Map;
import com.here.odnp.config.OdnpConfigStatic;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.forbid.DJIFlightLimitAreaModel;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.DJISetFlyForbidAreaModel;
import dji.midware.data.forbid.FlyForbidElement;
import dji.midware.data.forbid.FlyForbidElementAirMap;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.midware.data.forbid.FlyForbidProtocol.DJIWarningAreaState;
import dji.midware.data.forbid.FlyForbidProtocol.LevelType;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataFlycGetPushForbidStatus;
import dji.midware.data.model.P3.DataFlycGetPushForbidStatus.DJIFlightLimitAreaState;
import dji.midware.data.model.P3.DataFlycGetPushRequestLimitUpdate;
import dji.midware.data.model.P3.DataFlycSetFlyForbidAreaData;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.R;
import dji.pilot.flyunlimit.a.b;
import dji.pilot.flyunlimit.a.d;
import dji.pilot.flyunlimit.a.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.f;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class a {
    public static final String a = "key_support_airmap_country";
    public static final String b = "sp_key_airmap_api_key";
    public static final String d = "key_flyforbid_type_13_dlg_show_time";
    private static a k = null;
    private static final String o = "key_last_flyforbid_flyc_sn";
    private ArrayList<DJIFlightLimitAreaModel> A = new ArrayList();
    private ArrayList<DJIFlightLimitAreaModel> B = new ArrayList();
    private DJIWarningAreaState C = DJIWarningAreaState.None;
    private DJIWarningAreaState D = DJIWarningAreaState.None;
    private String E = "";
    private int F = -1;
    private int G = 14;
    private double H = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private double I = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private int J = -1;
    private int K = -1;
    private b L;
    private d M;
    private e N;
    private int O = 0;
    private double P = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private double Q = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private double R = 0.0d;
    private double S = 0.0d;
    private boolean T = true;
    private int U = 0;
    private final int V = 1;
    private final int W = 2;
    private final int X = 3;
    private ArrayList<DJISetFlyForbidAreaModel> Y = new ArrayList();
    private ArrayList<DJISetFlyForbidAreaModel> Z = new ArrayList();
    private boolean aa = false;
    private boolean ab = false;
    private final int ac = 5;
    private Handler ad = new Handler(new 6(this));
    private int ae = 0;
    private MotorStartFailedCause af = MotorStartFailedCause.OTHER;
    private boolean ag = false;
    private int ah = 0;
    dji.thirdparty.afinal.b c;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private Context j = null;
    private String l = "unknown";
    private String m = DJIFlyForbidController.AIRMAP_DATA_SOURCE;
    private String n = "";
    private long p = 0;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private double u = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private double v = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    private Thread w;
    private Runnable x = new 3(this);
    private int y = 0;
    private int z = 0;

    static /* synthetic */ int l(a aVar) {
        int i = aVar.ae + 1;
        aVar.ae = i;
        return i;
    }

    public void a(boolean z) {
        this.e = z;
        if (z) {
            this.f = false;
            c.a().e(b.a);
        }
    }

    public void b(boolean z) {
        this.f = z;
        if (z) {
            this.e = false;
            c.a().e(b.b);
        }
    }

    public void c(boolean z) {
        this.g = z;
        if (z) {
            this.h = false;
            c.a().e(b.d);
        }
    }

    public void d(boolean z) {
        this.h = z;
        if (z) {
            this.g = false;
            c.a().e(b.c);
        }
    }

    public void e(boolean z) {
        this.i = z;
        if (z) {
            c.a().e(b.e);
        }
    }

    private a(Context context) {
        this.j = context.getApplicationContext();
        if (!c.a().c((Object) this)) {
            c.a().a((Object) this);
        }
        this.c = DJIFlyForbidController.getInstance(context).getDb();
    }

    public static a getInstance(Context context) {
        synchronized (a.class) {
            if (k == null) {
                k = new a(context);
            }
        }
        return k;
    }

    public void a(double d, double d2) {
        if (!this.r) {
            this.r = true;
            String str = "";
            if (f.getInstance().c()) {
                str = f.getInstance().j();
            }
            String str2 = dji.pilot.flyunlimit.b.b;
            String string = this.j.getString(R.string.versionname);
            long currentTimeMillis = System.currentTimeMillis();
            NFZLogUtil.LOGD("requestNfzParams : " + str + d + d2 + str2 + "android" + string + currentTimeMillis);
            String c = dji.pilot.a.a.c(str + d + d2 + str2 + "android" + string + currentTimeMillis, dji.pilot.flyunlimit.b.a);
            str = String.format("%saccount=%s&lat=%s&lng=%s&country=%s&os=%s&appVersion=%s&timestamp=%s&signature=%s", new Object[]{b.b(), str, Double.valueOf(d), Double.valueOf(d2), str2, "android", string, Long.valueOf(currentTimeMillis), c});
            dji.thirdparty.afinal.c b = com.dji.frame.c.c.b(this.j);
            String replace = str.replace(' ', '+');
            NFZLogUtil.LOGD("requestNfzParams tarUrl: " + replace);
            b.a(replace, new 1(this, d, d2));
        }
    }

    private void a(double d, double d2, boolean z) {
        if (!this.t && g.b(this.j, FlyforbidUpdateService.c, "").equals(this.j.getString(R.string.versionname))) {
            this.t = true;
            long b = g.b(this.j, FlyforbidUpdateService.a, 0);
            if (this.m == DJIFlyForbidController.AIRMAP_DATA_SOURCE) {
                b = g.b(this.j, FlyforbidUpdateService.b, 0);
            }
            String string = this.j.getString(R.string.versionname);
            String format = String.format(Locale.getDefault(), "%d", new Object[]{Long.valueOf(System.currentTimeMillis())});
            String c = dji.pilot.a.a.c("" + d + d2 + this.l + "android" + string + b + format + this.m, dji.pilot.flyunlimit.b.a);
            String str = "";
            if (z) {
                str = b.l();
            } else {
                str = b.c();
            }
            String replace = String.format(Locale.getDefault(), "%s%s&version=1.3&lat=%s&lng=%s&country=%s&os=%s&appVersion=%s&timestamp=%s&signature=%s&datasource=%s", new Object[]{str, Long.valueOf(b), Double.valueOf(d), Double.valueOf(d2), this.l, r3, string, format, c, this.m}).replace(' ', '+');
            NFZLogUtil.LOGD("*****downloadDataFromServer start: " + replace);
            dji.thirdparty.afinal.c cVar = new dji.thirdparty.afinal.c();
            if (!z) {
                cVar.a("X-API-Key", dji.pilot.flyunlimit.b.e());
            }
            cVar.a(replace, new 2(this, z, d, d2));
        }
    }

    private long a(FlyForbidElementAirMap flyForbidElementAirMap) {
        if (this.c == null || flyForbidElementAirMap == null) {
            return 0;
        }
        if (flyForbidElementAirMap.city == null) {
            flyForbidElementAirMap.city = "";
        }
        if (flyForbidElementAirMap.name == null) {
            flyForbidElementAirMap.name = "";
        }
        if (flyForbidElementAirMap.points == null) {
            flyForbidElementAirMap.points = "";
        }
        if (flyForbidElementAirMap.type == 29 && flyForbidElementAirMap.begin_at < dji.pilot.flyunlimit.c.a.a() && flyForbidElementAirMap.end_at > dji.pilot.flyunlimit.c.a.a()) {
            NFZLogUtil.LOGD("**into type 29 changed");
            flyForbidElementAirMap.begin_at = dji.pilot.flyunlimit.c.a.a();
            flyForbidElementAirMap.end_at = dji.pilot.flyunlimit.c.a.a() + 10800;
        }
        this.c.b((Object) flyForbidElementAirMap, "" + flyForbidElementAirMap.area_id);
        return flyForbidElementAirMap.updated_at;
    }

    private void a(FlyForbidElement flyForbidElement) {
        if (this.c != null && flyForbidElement != null) {
            if (flyForbidElement.city == null) {
                flyForbidElement.city = "";
            }
            if (flyForbidElement.name == null) {
                flyForbidElement.name = "";
            }
            if (flyForbidElement.points == null) {
                flyForbidElement.points = "";
            }
            this.c.b((Object) flyForbidElement, "" + flyForbidElement.area_id);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon.groundOrSky() == 1 && this.aa) {
            c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
            this.aa = false;
        }
        this.R = dataOsdGetPushCommon.getLatitude();
        this.S = dataOsdGetPushCommon.getLongitude();
        a(dataOsdGetPushCommon.getMotorFailedCause(), dataOsdGetPushCommon.isMotorUp());
        if (dji.pilot.fpv.d.b.a(this.R) && dji.pilot.fpv.d.b.b(this.S)) {
            if (!(DJIFlyForbidController.getInstance().getIsCheckingData() || "".equals(this.n))) {
                if (!(this.w == null || this.w.isAlive())) {
                    try {
                        this.w.join();
                        this.w = null;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (this.w == null) {
                    this.w = new Thread(this.x);
                    this.w.start();
                }
            }
            c(this.R, this.S);
        }
    }

    private void b(double d, double d2) {
        Object obj;
        if (this.P == Map.MOVE_PRESERVE_ZOOM_LEVEL && this.Q == Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            NFZLogUtil.LOGD("***in mAircraftLatLast -1");
            obj = 1;
        } else {
            float[] fArr = new float[1];
            Location.distanceBetween(this.P, this.Q, d, d2, fArr);
            if (((double) fArr[0]) >= 10000.0d) {
                NFZLogUtil.LOGD("***in distanceBetween big");
                obj = 1;
            } else {
                obj = null;
            }
        }
        if (obj != null && !DJIFlyForbidController.getInstance().getIsCheckingData()) {
            DJIFlyForbidController.getInstance().setIsCheckingData(true);
            NFZLogUtil.LOGD("FlyforbidEventManager DataOsdGetPushCommon in");
            NFZLogUtil.savedLOG("nfz log 1 n c f a");
            DJIFlyForbidController.getInstance().refreshUnlockList(f.getInstance().o(), this.n);
            List checkNearFlyForbidArea = DJIFlyForbidController.getInstance(this.j).checkNearFlyForbidArea(d, d2);
            if (checkNearFlyForbidArea != null) {
                NFZLogUtil.savedLOG("nfz log 5 f c f a" + checkNearFlyForbidArea.size());
            } else {
                NFZLogUtil.savedLOG("nfz log 5 f c f a null");
            }
            DJIFlyForbidController.getInstance().setIsCheckingData(false);
            if (checkNearFlyForbidArea != null) {
                this.P = d;
                this.Q = d2;
                c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
                this.H = Map.MOVE_PRESERVE_ZOOM_LEVEL;
                this.I = Map.MOVE_PRESERVE_ZOOM_LEVEL;
            }
            NFZLogUtil.LOGD("FlyforbidEventManager DataOsdGetPushCommon finish check data");
        }
    }

    private void c(double d, double d2) {
        if (DJINetWorkReceiver.a(this.j)) {
            if (this.u == Map.MOVE_PRESERVE_ZOOM_LEVEL && this.v == Map.MOVE_PRESERVE_ZOOM_LEVEL) {
                this.s = false;
                this.q = false;
            } else {
                float[] fArr = new float[1];
                Location.distanceBetween(this.u, this.v, d, d2, fArr);
                if (((double) fArr[0]) >= FlyForbidProtocol.SEARCH_RADIUS) {
                    this.s = false;
                    this.q = false;
                }
            }
            if (!this.s || !this.q) {
                if (!(this.q || this.r)) {
                    a(d, d2);
                }
                if (!this.s && !this.t && this.q) {
                    a(d, d2, false);
                }
            }
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushForbidStatus dataFlycGetPushForbidStatus) {
        if (dji.pilot.c.d.b.equals(MODE.Master) && dataFlycGetPushForbidStatus != null) {
            this.y = dataFlycGetPushForbidStatus.getFlightLimitAreaState().value();
            this.z = dataFlycGetPushForbidStatus.getDJIFlightLimitActionEvent().value();
            h();
            this.B = dataFlycGetPushForbidStatus.getFlightLimitAreaModels();
            g();
            j();
            k();
        }
    }

    private void g() {
        boolean z;
        boolean z2 = false;
        int size = this.B.size();
        if (size <= 0) {
            if (size == 0 && this.A.size() != 0) {
                this.A.clear();
                this.B.clear();
                z = false;
                z2 = true;
            }
            z = false;
        } else if (this.A.size() != size) {
            this.A.clear();
            this.A.addAll(this.B);
            z = true;
        } else {
            int i = 0;
            while (i < size) {
                if (((DJIFlightLimitAreaModel) this.A.get(i)).latitude != ((DJIFlightLimitAreaModel) this.B.get(i)).latitude || ((DJIFlightLimitAreaModel) this.A.get(i)).longitude != ((DJIFlightLimitAreaModel) this.B.get(i)).longitude || ((DJIFlightLimitAreaModel) this.A.get(i)).innerRadius != ((DJIFlightLimitAreaModel) this.B.get(i)).innerRadius || ((DJIFlightLimitAreaModel) this.A.get(i)).outerRadius != ((DJIFlightLimitAreaModel) this.B.get(i)).outerRadius) {
                    this.A.clear();
                    this.A.addAll(this.B);
                    z = true;
                    break;
                }
                i++;
            }
            z = false;
        }
        if (z) {
            b(true);
        }
        if (z2) {
            a(true);
        }
    }

    private void h() {
        if (this.z == DJIFlightLimitAreaState.None.value() || this.z == DJIFlightLimitAreaState.InnerUnLimit.value()) {
            double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
            double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
            if (dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude)) {
                int i;
                if (this.H == Map.MOVE_PRESERVE_ZOOM_LEVEL && this.I == Map.MOVE_PRESERVE_ZOOM_LEVEL) {
                    i = 1;
                } else {
                    float[] fArr = new float[1];
                    Location.distanceBetween(this.H, this.I, latitude, longitude, fArr);
                    if (fArr[0] >= 50.0f) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                }
                if (i != 0) {
                    this.H = latitude;
                    this.I = longitude;
                    if (DJIFlyForbidController.getInstance().isInStrongWarningArea(latitude, longitude) && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                        c.a().e(b.f);
                        return;
                    }
                    DJIWarningAreaState handleWarningArea = DJIFlyForbidController.getInstance().handleWarningArea(latitude, longitude);
                    FlyForbidElement curWarningArea = DJIFlyForbidController.getInstance().getCurWarningArea();
                    NFZLogUtil.LOGD("DataFlycGetPushForbidStatus warning state: " + handleWarningArea);
                    if (curWarningArea == null) {
                        return;
                    }
                    if (handleWarningArea != this.C || this.F != curWarningArea.area_id) {
                        this.F = curWarningArea.area_id;
                        this.D = this.C;
                        this.C = handleWarningArea;
                        if (!this.C.equals(DJIWarningAreaState.None) && !this.D.equals(DJIWarningAreaState.InnerLimit)) {
                            int i2;
                            if (curWarningArea != null) {
                                NFZLogUtil.LOGD("DataFlycGetPushForbidStatus warningEle id: " + curWarningArea.area_id);
                            }
                            String str = "";
                            if (this.C.equals(DJIWarningAreaState.InnerLimit)) {
                                str = String.format(this.j.getResources().getString(R.string.flight_forbid_warn_alert_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(this.j, curWarningArea.type)});
                            } else {
                                str = String.format(this.j.getResources().getString(R.string.flight_forbid_warn_edge_alert_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(this.j, curWarningArea.type)});
                            }
                            this.E = str;
                            this.G = curWarningArea.type;
                            if (dji.gs.b.c.getInstance(this.j).b(this.G) != dji.gs.b.c.a.c.a()) {
                                i2 = 1;
                            } else {
                                i2 = 0;
                            }
                            if (i2 != 0) {
                                a(str);
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<DJIFlightLimitAreaModel> a() {
        return this.B;
    }

    public void a(Context context) {
        if ((this.M == null || !this.M.isShowing()) && DJIFlyForbidController.getInstance() != null) {
            double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
            double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
            if (dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude)) {
                FlyForbidElement findAreaByCoordinate = DJIFlyForbidController.getInstance().findAreaByCoordinate(latitude, longitude, this.J);
                if (findAreaByCoordinate != null) {
                    NFZLogUtil.LOGD("showFlyForbidTip type: " + findAreaByCoordinate.type + " id: " + findAreaByCoordinate.area_id + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + findAreaByCoordinate.lat + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + findAreaByCoordinate.lng);
                    if (findAreaByCoordinate != null && findAreaByCoordinate.level == LevelType.CAN_UNLIMIT.value() && !i.getInstance().c().equals(ProductType.litchiC) && !i.getInstance().c().equals(ProductType.P34K) && dji.pilot.c.d.b.equals(MODE.Master)) {
                        a(context, findAreaByCoordinate.type, this.J, findAreaByCoordinate.name);
                        return;
                    } else if (findAreaByCoordinate != null) {
                        a(context, findAreaByCoordinate.type, findAreaByCoordinate.level, this.J, findAreaByCoordinate.name);
                        return;
                    } else {
                        return;
                    }
                }
                NFZLogUtil.LOGD("showFlyForbidTip findedElement: null return.");
                a(this.j.getString(R.string.takeoff_failed_tips_in_fly_limit_zone));
                return;
            }
            NFZLogUtil.LOGD("showFlyForbidTip check coordinate error return");
        }
    }

    private void a(Context context, int i, int i2, int i3, String str) {
        if (context != null) {
            if (this.L == null) {
                this.L = new b(context);
            }
            if (!this.L.isShowing()) {
                if (this.M == null || !this.M.isShowing()) {
                    if (this.N != null) {
                        this.N.dismiss();
                    }
                    this.L.a(i, i2, i3, str);
                    this.L.show();
                }
            }
        }
    }

    public void a(Context context, int i, int i2, String str) {
        a(this.j.getString(R.string.takeoff_failed_tips_in_can_unlock_limit_zone));
        if (context != null) {
            if (this.M == null) {
                this.M = new d(context);
            }
            if (i == -1 || i2 == -1) {
                this.M.a(String.format(this.j.getString(R.string.flight_forbid_takeoff_txt), new Object[]{"" + this.ah}) + i());
            } else {
                this.M.a(a(i, i2, str));
            }
            if (this.L == null || !this.L.isShowing()) {
                this.M.dismiss();
                if (this.N != null) {
                    this.N.dismiss();
                }
                this.M.show();
            }
        }
    }

    private String i() {
        String str = "";
        for (FlyForbidElement flyForbidElement : DJIFlyForbidController.getInstance().getCurUnlockableAreasAround()) {
            if (!str.equals("")) {
                str = str + ", ";
            }
            str = str + flyForbidElement.name;
        }
        return this.j.getString(R.string.nfz_detected_zone_title) + str;
    }

    private String a(int i, int i2, String str) {
        String str2 = "";
        if (i2 == DJIFlightLimitAreaState.NearLimit.value() || i2 == DJIFlightLimitAreaState.InHalfLimit.value()) {
            return String.format(this.j.getString(R.string.flight_forbid_edge_alert_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(this.j, i)}) + this.j.getString(R.string.nfz_detected_zone_title) + str;
        }
        return String.format(this.j.getString(R.string.flight_forbid_alert_txt), new Object[]{dji.pilot.flyunlimit.c.a.a(this.j, i)}) + this.j.getString(R.string.nfz_detected_zone_title) + str;
    }

    public void b(Context context) {
        if (context != null) {
            if (this.N == null) {
                this.N = new e(context);
            }
            if (this.M != null && this.M.isShowing()) {
                return;
            }
            if ((this.L == null || !this.L.isShowing()) && !this.N.isShowing()) {
                this.N.show();
            }
        }
    }

    public void b() {
        if (this.M != null && this.M.isShowing()) {
            this.M.dismiss();
        }
        if (this.L != null && this.L.isShowing()) {
            this.L.dismiss();
        }
    }

    private void j() {
        this.O++;
        if (this.O == 8) {
            this.O = 0;
            NFZLogUtil.LOGD("checkFlyForbidState LimitAreaState: " + this.y);
        }
        if (this.J != this.y) {
            this.J = this.y;
            NFZLogUtil.LOGD("in checkFlyForbidState: " + this.y);
            switch (this.J) {
                case 1:
                case 2:
                case 3:
                case 4:
                    if (DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                        c(true);
                        return;
                    }
                    return;
                case 5:
                    d(true);
                    return;
                default:
                    return;
            }
        }
    }

    private boolean a(int i) {
        if (this.J == 0 && i != 0) {
            return true;
        }
        if ((this.J == 1 || this.J == 2 || this.J == 3 || this.J == 4) && i == 0) {
            return true;
        }
        return false;
    }

    private void k() {
        if (this.O == 0) {
            NFZLogUtil.LOGD("checkFlyForbidState LimitActionState: " + this.z);
        }
        int i = this.z;
        switch (i) {
            case 2:
                if (this.K != 2) {
                    DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                    bVar.b = R.string.limit_tips6;
                    c.a().e(bVar);
                    c.a().e(b.d);
                    this.K = i;
                    return;
                }
                return;
            default:
                this.K = i;
                return;
        }
    }

    private void l() {
        this.P = Map.MOVE_PRESERVE_ZOOM_LEVEL;
        this.Q = Map.MOVE_PRESERVE_ZOOM_LEVEL;
        synchronized (this.Y) {
            this.Y.clear();
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushRequestLimitUpdate dataFlycGetPushRequestLimitUpdate) {
        if (dji.pilot.c.d.b.equals(MODE.Master)) {
            NFZLogUtil.LOGD("DataFlycGetPushRequestLimitUpdate in");
            if (ServiceManager.getInstance().isRemoteOK() && !"".equals(this.n) && this.T) {
                NFZLogUtil.LOGD("onEventBackgroundThread DataFlycGetPushRequestLimitUpdate In 0");
                double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
                double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
                if (!dji.pilot.fpv.d.b.a(latitude) || !dji.pilot.fpv.d.b.b(longitude)) {
                    NFZLogUtil.LOGD("onEventBackgroundThread DataFlycGetPushRequestLimitUpdate In 1");
                } else if (!DJIFlyForbidController.getInstance().getIsCheckingData()) {
                    new Thread(new 4(this, latitude, longitude)).start();
                }
            }
        }
    }

    public dji.gs.e.b c() {
        return new dji.gs.e.b(this.R, this.S);
    }

    private void a(String str) {
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        bVar.a = DJIErrorPopView.d.WARNING;
        bVar.c = str;
        c.a().e(bVar);
    }

    private void b(int i) {
        if (!this.ab) {
            this.ab = true;
            synchronized (this.Y) {
                int size = this.Y.size();
                if (this.Z == null) {
                    this.Z = new ArrayList();
                }
                this.Z.clear();
                if (((double) i) >= Math.ceil(((double) size) / 5.0d)) {
                    this.Z = null;
                } else {
                    if (((double) i) < Math.ceil(((double) size) / 5.0d)) {
                        if ((i + 1) * 5 < size) {
                            size = (i + 1) * 5;
                        }
                        for (int i2 = i * 5; i2 < size; i2++) {
                            this.Z.add(this.Y.get(i2));
                        }
                    }
                }
            }
            DataFlycSetFlyForbidAreaData.getInstance().a(this.Z).a(i).start(new 5(this, i));
        }
    }

    public String d() {
        if ("".equals(DJIGlobalService.f) && ServiceManager.getInstance().isRemoteOK()) {
            this.ae = 0;
            m();
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
            }
        }
        return DJIGlobalService.f;
    }

    private void m() {
        DataFlycActiveStatus dataFlycActiveStatus = new DataFlycActiveStatus();
        dataFlycActiveStatus.start(new 7(this, dataFlycActiveStatus));
    }

    private void a(MotorStartFailedCause motorStartFailedCause, boolean z) {
        boolean z2 = true;
        if (this.af != motorStartFailedCause) {
            this.af = motorStartFailedCause;
            if (motorStartFailedCause == MotorStartFailedCause.FlyForbiddenError) {
                if (dji.pilot.fpv.d.b.a(this.R) && dji.pilot.fpv.d.b.b(this.S)) {
                    int checkAreaNumAround = DJIFlyForbidController.getInstance().checkAreaNumAround(this.R, this.S);
                    this.ah = checkAreaNumAround;
                    if (checkAreaNumAround > 0) {
                        c.a().e(b.g);
                        return;
                    }
                }
                c(true);
                return;
            }
        }
        if (this.ag != z) {
            this.ag = z;
            if (this.ag) {
                boolean z3;
                if (dji.pilot.fpv.d.b.a(this.R) && dji.pilot.fpv.d.b.b(this.S) && DJIFlyForbidController.getInstance().isInStrongWarningArea(this.R, this.S)) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    c.a().e(b.f);
                    return;
                }
                if (dji.gs.b.c.getInstance(this.j).b(this.G) == dji.gs.b.c.a.c.a()) {
                    z2 = false;
                }
                if (this.ag && !this.C.equals(DJIWarningAreaState.None) && !"".equals(this.E) && r0) {
                    a(this.E);
                }
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar.equals(o.ConnectLose)) {
            c.a().e(b.c);
            this.ad.sendEmptyMessageDelayed(3, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
        } else if (oVar.equals(o.ConnectOK)) {
            this.ad.removeMessages(3);
        }
    }

    public void e() {
        this.P = Map.MOVE_PRESERVE_ZOOM_LEVEL;
        this.Q = Map.MOVE_PRESERVE_ZOOM_LEVEL;
    }

    public void c(Context context) {
        c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
        new Handler().postDelayed(new 8(this), 1000);
    }

    public void f() {
        this.M = null;
        this.L = null;
    }

    public void onEventBackgroundThread(a aVar) {
        if (dji.pilot.fpv.d.b.a(aVar.a().b) && dji.pilot.fpv.d.b.b(aVar.a().c)) {
            DJIFlyForbidController.getInstance().checkNearFlyForbidArea(aVar.a().b, aVar.a().c);
            this.R = aVar.a().b;
            this.S = aVar.a().c;
            c.a().e(b.e);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar.equals(p.ConnectLose)) {
            l();
            this.n = "";
        }
    }

    public void onEventBackgroundThread(c cVar) {
        if (cVar != c.a) {
            this.n = g.b(this.j, o, "def");
            e();
            NFZLogUtil.LOGD("**into FlycSnGetted FAIL sn: " + this.n);
            c.a().e(DataOsdGetPushCommon.getInstance());
        } else if (!this.n.equals(DJIGlobalService.f)) {
            this.n = DJIGlobalService.f;
            g.a(this.j, o, this.n);
            e();
            NFZLogUtil.LOGD("**into FlycSnGetted SUCCESS sn: " + this.n);
            c.a().e(DataOsdGetPushCommon.getInstance());
        }
    }
}
