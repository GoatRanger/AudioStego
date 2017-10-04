package dji.pilot.dji_groundstation.controller;

import android.content.Context;
import android.net.Uri;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.g;

public class e {
    public static final String A = "waypoint";
    public static final String B = "waypoint/fromenter";
    public static final String C = "waypoint/collection/delete";
    public static final String D = "waypoint/collection";
    public static final String E = "waypoint/status";
    public static final String F = "waypoint/setting";
    public static final String G = "waypoint/setreturnhomeheight";
    public static final String H = "waypoint/uploadmission";
    public static final String I = "waypoint/canceluploadmission";
    public static final String J = "waypoint/exit";
    public static final String K = "waypoint/hide";
    public static final String L = "waypoint/pageaddnew";
    public static final String M = "waypoint/addnewpoint";
    public static final String N = "waypoint/addnewpointfinish";
    public static final String O = "waypoint/downloadMisson";
    public static final String P = "waypoint/favorite";
    public static final String Q = "followme/fromenter";
    public static final String R = "followme";
    public static final String S = "followme/status";
    public static final String T = "courselock";
    public static final String U = "courselock/fromenter";
    public static final String V = "courselock/status";
    public static final String W = "homelock";
    public static final String X = "homelock/fromenter";
    public static final String Y = "homelock/status";
    public static final String Z = "terraintracking";
    public static final String a = "flightmode";
    public static final String aa = "terraintracking/fromenter";
    public static final String ab = "terraintracking/status";
    public static final String ac = "terraintracking/help";
    public static final String ad = "pano";
    public static final String ae = "tripod/fromenter";
    public static final String af = "tripod";
    private static final String ag = "GSProtocal";
    private static final String ah = "gs://";
    private static String ai = "";
    private static String aj = "";
    public static final String b = "none";
    public static final String c = "point";
    public static final String d = "track";
    public static final String e = "gesture";
    public static final String f = "gesture/info";
    public static final String g = "normal";
    public static final String h = "smart";
    public static final String i = "joystick";
    public static final String j = "main";
    public static final String k = "main/openmultimode";
    public static final String l = "dismiss";
    public static final String m = "smartmode";
    public static final String n = "dismiss";
    public static final String o = "none";
    public static final String p = "exit";
    public static final String q = "hide";
    public static final String r = "back";
    public static final String s = "poi";
    public static final String t = "poi/fromenter";
    public static final String u = "poi/start";
    public static final String v = "poi/status";
    public static final String w = "poi/exit";
    public static final String x = "poi/hide";
    public static final String y = "poi/controlnotice/false";
    public static final String z = "poi/controlnotice/true";

    public static String a(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        if (str.toLowerCase().startsWith(ah)) {
            return Uri.parse(str).getAuthority();
        }
        return "";
    }

    public static String b(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        if (!str.toLowerCase().startsWith(ah)) {
            return "";
        }
        String path = Uri.parse(str).getPath();
        return path.substring(1, path.length());
    }

    public static boolean a(Context context) {
        return a(ai, context);
    }

    public static void c(String str) {
        ai = str;
    }

    public static boolean a(String str, final Context context) {
        if (!(str == null || str.trim().isEmpty() || !str.toLowerCase().startsWith(ah))) {
            Uri parse = Uri.parse(str);
            String authority = parse.getAuthority();
            String path = parse.getPath();
            path = path.substring(1, path.length());
            if (authority.equals(a)) {
                if (path.equals("none")) {
                    d.getInstance().c(d$a.None);
                } else if (path.equals(i)) {
                    d.getInstance().c(d$a.Joystick);
                } else if (path.equals("normal")) {
                    d.getInstance().c(d$a.Normal);
                } else if (path.equals(c)) {
                    d.getInstance().c(d$a.Point);
                } else if (path.equals(h)) {
                    d.getInstance().c(d$a.Smart);
                } else if (path.equals(d)) {
                    d.getInstance().c(d$a.Track);
                } else if (path.equals(e)) {
                    d.getInstance().c(d$a.Gesture);
                } else if (path.equals("dismiss")) {
                    f.getInstance(context).e();
                } else if (path.equals(j)) {
                    f.getInstance(context).k();
                    f.getInstance(context).b(false);
                } else if (path.equals(k)) {
                    new DataFlycSetParams().a("g_config.control.multi_control_mode_enable_0", Integer.valueOf(1)).start(new d() {
                        public void onSuccess(Object obj) {
                            if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                                f.getInstance(context).k();
                                f.getInstance(context).b(false);
                            }
                        }

                        public void onFailure(a aVar) {
                        }
                    });
                }
            } else if (authority.equals(m) && !b(path, context)) {
                if (path.equals(p)) {
                    f.getInstance(context).f();
                } else if (path.equals(q)) {
                    f.getInstance(context).b(true);
                } else if (path.equals(r)) {
                    if (d.getInstance().b().a() == c.k.a()) {
                        dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().t();
                    }
                    d.getInstance().d();
                    d.getInstance().b(d.getInstance().b());
                } else if (path.equals("dismiss") || path.equals(p)) {
                    f.getInstance(context).b(false);
                } else if (path.equals(T)) {
                    if (d.getInstance().b() == c.p) {
                        d.getInstance().b(c.p);
                    } else {
                        d.getInstance().b(c.o);
                    }
                } else if (path.equals(V)) {
                    if (i.getInstance().c().value() == ProductType.A2.value()) {
                        d.getInstance().a(d$a.Smart);
                    }
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_COURSELOCK_STATUS);
                } else if (path.equals(R)) {
                    if (d.getInstance().b() == c.n) {
                        d.getInstance().b(c.n);
                    } else {
                        d.getInstance().b(c.m);
                    }
                } else if (path.equals(S)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_FOLLOWME_STATUS);
                } else if (path.equals(W)) {
                    if (d.getInstance().b() == c.r) {
                        d.getInstance().b(c.r);
                    } else {
                        d.getInstance().b(c.q);
                    }
                } else if (path.equals(Y)) {
                    if (i.getInstance().c().value() == ProductType.A2.value()) {
                        d.getInstance().a(d$a.Smart);
                    }
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_HOMELOCK_STATUS);
                } else if (path.equals("none")) {
                    d.getInstance().b(c.a);
                } else if (path.equals("pano")) {
                    d.getInstance().b(c.s);
                } else if (path.equals(s)) {
                    if (d.getInstance().b() == c.d) {
                        d.getInstance().b(c.d);
                    } else {
                        d.getInstance().b(c.b);
                    }
                } else if (path.equals(u)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_POI_START);
                } else if (path.equals(v)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_POI_STATUS);
                } else if (path.equals(w)) {
                    f.getInstance(context).f();
                } else if (path.equals(x)) {
                    f.getInstance(context).b(true);
                } else if (path.equals(z)) {
                    if (!dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().c()) {
                        d.getInstance().a(4, null);
                    } else if (dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().n()) {
                        f.getInstance(context).a(true, null);
                    } else {
                        d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_point_of_insterest_radius_limits));
                    }
                } else if (path.equals(y)) {
                    f.getInstance(context).a(false, null);
                } else if (path.equals(A)) {
                    d.getInstance().b(c.e);
                } else if (path.equals(O)) {
                    d.getInstance().b(c.l);
                } else if (path.equals(C)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_WAYPOINT_DELETE);
                } else if (path.equals(D)) {
                    r0 = new dji.pilot.dji_groundstation.a.e();
                    r0.s = 1;
                    dji.thirdparty.a.c.a().e(r0);
                    d.getInstance().b(c.f);
                } else if (path.equals(E)) {
                    if (dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().c()) {
                        d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_WAYPOINT_STATUS);
                    }
                } else if (path.equals(F)) {
                    dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().u();
                    if (d.getInstance().b().a() != c.f.a()) {
                        d.getInstance().b(c.h);
                    } else if (dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j() == null || dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j().getPoints().size() <= 1) {
                        d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_collection_item_too_less_point));
                    } else {
                        d.getInstance().b(c.h);
                    }
                } else if (path.equals(G)) {
                    if (d.getInstance().b().a() == c.k.a() || d.getInstance().b().a() == c.f.a() || d.getInstance().b().a() == c.h.a()) {
                        dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().u();
                        if (dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j() == null || dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j().getPoints().size() <= 1) {
                            d.getInstance().a(8, Integer.valueOf(R.string.gsnew_gs_way_point_collection_item_too_less_point));
                        } else {
                            d.getInstance().b(c.i);
                        }
                    } else {
                        d.getInstance().b(c.i);
                    }
                } else if (path.equals(H)) {
                    if (dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().c()) {
                        d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_WAYPOINT_UPLOADMISSION);
                    } else {
                        d.getInstance().a(4, null);
                    }
                } else if (path.equals(I)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_WAYPOINT_CANCEL_UPLOADMISSION);
                    d.getInstance().d();
                    d.getInstance().b(d.getInstance().b());
                } else if (path.equals(J)) {
                    f.getInstance(context).f();
                } else if (path.equals(K)) {
                    f.getInstance(context).b(true);
                } else if (path.equals(L)) {
                    r0 = new dji.pilot.dji_groundstation.a.e();
                    r0.s = 1;
                    dji.thirdparty.a.c.a().e(r0);
                    dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().f(null);
                    dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().d(null);
                    dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().q();
                    d.getInstance().b(c.k);
                } else if (path.equals(M)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_WAYPOINT_ADD_NEW_POINT);
                } else if (path.equals(N)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWTICH_WAYPOINT_ADDPOINT_FINISH);
                } else if (path.equals(P)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_WAYPOINT_FAVORITE);
                } else if (path.equals(Z)) {
                    f.getInstance(context).a(true);
                } else if (path.equals(ab)) {
                    d.getInstance().a(g.EVENT_SMARTMODE_SWITCH_TERRAINTRACKING_STATUS);
                } else if (path.equals(ac)) {
                    f.getInstance(context).a(false);
                } else if (path.equals(af)) {
                    f.getInstance(context).b();
                }
            }
            aj = str;
        }
        return false;
    }

    private static boolean b(String str, Context context) {
        c b = d.getInstance().b();
        dji.pilot.dji_groundstation.a.e eVar;
        if (str.equals(U)) {
            if (b.a(c.o)) {
                d.getInstance().b(b);
                return true;
            }
            a.getInstance(context).a(-1);
            d.getInstance().b(c.o);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        } else if (str.equals(X)) {
            if (b.a(c.q)) {
                d.getInstance().b(b);
                return true;
            }
            a.getInstance(context).a(-1);
            d.getInstance().b(c.q);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        } else if (str.equals(Q)) {
            dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().a(context);
            if (b.a(c.m)) {
                d.getInstance().b(b);
                return true;
            }
            d.getInstance().b(c.m);
            dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().a(context);
            a.getInstance(context).a(-1);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        } else if (str.equals(t)) {
            if (b.a(c.b)) {
                d.getInstance().b(b);
                return true;
            }
            d.getInstance().b(c.b);
            a.getInstance(context).a(-1);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        } else if (str.equals(B)) {
            if (b.a(c.e)) {
                d.getInstance().b(b);
                return true;
            }
            d.getInstance().b(c.e);
            a.getInstance(context).a(-1);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        } else if (str.equals(aa)) {
            if (b.a(c.t)) {
                d.getInstance().b(b);
                return true;
            }
            f.getInstance(context).a(true);
            a.getInstance(context).a(-1);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        } else if (!str.equals(ae)) {
            return false;
        } else {
            if (b.a(c.v)) {
                d.getInstance().b(b);
                return true;
            }
            f.getInstance(context).b();
            a.getInstance(context).a(-1);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return true;
        }
    }
}
