package dji.pilot.dji_groundstation.controller;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import dji.common.flightcontroller.DJIFlightControllerFlightMode;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.d$b;
import dji.pilot.dji_groundstation.a.j;
import dji.pilot.dji_groundstation.a.k;
import dji.pilot.dji_groundstation.ui.a.g;
import dji.pilot.dji_groundstation.ui.a.h;
import dji.pilot.dji_groundstation.ui.a.i;
import dji.sdksharedlib.b.e;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    private static final String b = "GSViewController";
    private static final String c = "show_terrain_tracking_info";
    private static f o = null;
    private static final String q = "terrain_follow_hint_shown";
    private static final String r = "tripod_hint_shown";
    public Runnable a = new Runnable(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.g != null && this.a.e(this.a.i)) {
                Iterator it = this.a.g.iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    if (bVar != null) {
                        bVar.a(dji.pilot.dji_groundstation.a.b.EVENT_SMARTDIALOG_DATA_FINISH, this.a.f);
                    }
                }
            }
        }
    };
    private Context d = null;
    private a e = new a(this);
    private d f = new d(this);
    private ArrayList<b> g = new ArrayList();
    private String h = null;
    private String i = null;
    private dji.pilot.dji_groundstation.ui.a.d j = null;
    private i k = null;
    private dji.pilot.dji_groundstation.ui.a.f l = null;
    private g m = null;
    private Runnable n = new Runnable(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.g != null && this.a.d(this.a.h)) {
                Iterator it = this.a.g.iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    if (bVar != null) {
                        bVar.a(dji.pilot.dji_groundstation.a.b.EVENT_ENTERDIALOG_DATA_FINISH, this.a.e);
                    }
                }
            }
        }
    };
    private boolean p = false;

    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] a = new int[dji.pilot.dji_groundstation.a.d.c.values().length];

        static {
            b = new int[d$a.values().length];
            try {
                b[d$a.Point.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[d$a.Track.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[d$a.Normal.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[d$a.Trackselfie.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[dji.pilot.dji_groundstation.a.d.c.d.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[dji.pilot.dji_groundstation.a.d.c.g.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[dji.pilot.dji_groundstation.a.d.c.n.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[dji.pilot.dji_groundstation.a.d.c.p.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[dji.pilot.dji_groundstation.a.d.c.r.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[dji.pilot.dji_groundstation.a.d.c.u.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
        }
    }

    public class a {
        public String a = "";
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public int e = 0;
        public int f = 0;
        public double g = 0.0d;
        public double h = 0.0d;
        public int i = 17;
        public int j = 0;
        public String k = "";
        public boolean l = false;
        public ArrayList<b> m = new ArrayList();
        final /* synthetic */ f n;

        public a(f fVar) {
            this.n = fVar;
        }

        public void a(a aVar) {
            if (aVar != null) {
                this.a = aVar.a;
                this.d = aVar.d;
                this.b = aVar.b;
                this.c = aVar.c;
                this.e = aVar.e;
                this.f = aVar.f;
                this.g = aVar.g;
                this.h = aVar.h;
                this.i = aVar.i;
                this.k = aVar.k;
                this.l = aVar.l;
                for (int i = 0; i < aVar.m.size(); i++) {
                    if (i >= this.m.size()) {
                        this.m.add(aVar.m.get(i));
                    } else {
                        ((b) this.m.get(i)).a((b) aVar.m.get(i));
                    }
                }
            }
        }
    }

    public class b {
        public int a = -1;
        public int b = -1;
        public boolean c = false;
        public String d = "";
        final /* synthetic */ f e;

        public b(f fVar) {
            this.e = fVar;
        }

        public void a(b bVar) {
            if (bVar != null) {
                this.a = bVar.a;
                this.b = bVar.b;
                this.c = bVar.c;
                this.d = bVar.d;
            }
        }
    }

    public class c {
        public int a = 0;
        public String b = "";
        public String c = "";
        public String d = "";
        final /* synthetic */ f e;

        public c(f fVar) {
            this.e = fVar;
        }
    }

    public class d {
        public String a = "";
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public int e = 0;
        public String f = "#ffffff";
        public String g = "";
        public int h = 0;
        public int i = 0;
        public String j = "#ffffff";
        public String k = "";
        public String l = "";
        public int m = 0;
        public int n = 0;
        public double o = 0.0d;
        public double p = 0.0d;
        public int q = 17;
        public boolean r = false;
        public ArrayList<c> s = new ArrayList();
        final /* synthetic */ f t;

        public d(f fVar) {
            this.t = fVar;
        }
    }

    public static synchronized f getInstance(Context context) {
        f fVar;
        synchronized (f.class) {
            if (o == null) {
                o = new f(context);
            }
            fVar = o;
        }
        return fVar;
    }

    public boolean a() {
        return this.p;
    }

    private void m() {
        this.p = true;
        a(this.j);
        d.getInstance().a(this.j);
        a(this.k);
        d.getInstance().a(this.k);
        a(this.l);
        d.getInstance().a(this.l);
    }

    public void a(String str) {
        if (str != null && !str.trim().isEmpty()) {
            Builder builder = new Builder(this.d);
            builder.setMessage(str);
            builder.setPositiveButton(R.string.gsnew_btn_dlg_yes, null);
            builder.create().show();
        }
    }

    public void a(int i) {
        Builder builder = new Builder(this.d);
        builder.setMessage(i);
        builder.setPositiveButton(R.string.gsnew_btn_dlg_yes, null);
        builder.create().show();
    }

    public void a(boolean z, dji.pilot.dji_groundstation.ui.a.b.a aVar) {
        dji.pilot.dji_groundstation.ui.a.b bVar = new dji.pilot.dji_groundstation.ui.a.b(this.d, z);
        if (z) {
            bVar.a(aVar);
            bVar.a();
        } else {
            bVar.show();
        }
        b(false);
    }

    public void a(boolean z) {
        e();
        if (!z) {
            n();
        } else if (dji.midware.util.i.b(this.d, q, true)) {
            d.getInstance().b(dji.pilot.dji_groundstation.a.d.c.t);
        } else {
            n();
        }
    }

    private void n() {
        final h hVar = new h(this.d);
        hVar.c(R.drawable.gs_terrain_tracking_help_image);
        hVar.a(R.string.gsnew_gs_terrain_tracking_help_title);
        hVar.b(R.string.gsnew_gs_terrain_tracking_help_desc);
        hVar.a(new OnClickListener(this) {
            final /* synthetic */ f b;

            public void onClick(DialogInterface dialogInterface, int i) {
                DJIFlightControllerFlightMode dJIFlightControllerFlightMode = (DJIFlightControllerFlightMode) dji.sdksharedlib.a.a.e(e.bk);
                Object obj = (dJIFlightControllerFlightMode == null || !dJIFlightControllerFlightMode.equals(DJIFlightControllerFlightMode.TerrainTracking)) ? null : 1;
                dji.midware.util.i.a(this.b.d, f.q, hVar.a());
                if (obj != null) {
                    d.getInstance().b(dji.pilot.dji_groundstation.a.d.c.u);
                } else {
                    d.getInstance().b(dji.pilot.dji_groundstation.a.d.c.t);
                }
                hVar.dismiss();
            }
        }).show();
    }

    public void b() {
        e();
        if (dji.midware.util.i.b(this.d, r, true)) {
            final h hVar = new h(this.d);
            hVar.a(R.string.gsnew_gs_tripod_title).c(R.drawable.gs_tripod_help_image).b(R.string.gsnew_gs_tripod_stage_desc).a(true).a(new OnClickListener(this) {
                final /* synthetic */ f b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    dji.midware.util.i.a(this.b.d, f.r, hVar.a());
                    d.getInstance().a(d$a.Smart);
                    d.getInstance().a(dji.pilot.dji_groundstation.a.g.EVENT_SMARTMODE_SWITCH_TRIPOD);
                    hVar.dismiss();
                }
            }).show();
            return;
        }
        d.getInstance().a(d$a.Smart);
        d.getInstance().a(dji.pilot.dji_groundstation.a.g.EVENT_SMARTMODE_SWITCH_TRIPOD);
    }

    public void a(Context context) {
        if (context != null && (context instanceof Activity)) {
            if (this.j == null) {
                this.j = new dji.pilot.dji_groundstation.ui.a.d(context);
            }
            if (!this.j.isShowing()) {
                m();
                o();
                getInstance(context).d();
                this.j.show();
            }
        }
    }

    public boolean c() {
        if (this.j == null) {
            return false;
        }
        return this.j.isShowing();
    }

    private void o() {
        new Thread(new Runnable(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void run() {
                dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().p();
            }
        }).run();
    }

    public void b(Context context) {
        if (context != null && (context instanceof Activity)) {
            if (this.k == null) {
                this.k = new i(context);
            }
            getInstance(this.d).a(d.getInstance().b());
        }
    }

    public void d() {
        if (this.k != null) {
            this.k.dismiss();
        }
        this.h = i.getInstance(this.d).c();
        b(this.h);
    }

    public void e() {
        a(dji.pilot.dji_groundstation.a.b.EVENT_ENTERDIALOG_DISMISS, null);
    }

    public void b(boolean z) {
        if (z) {
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 8;
            eVar.t = null;
            switch (AnonymousClass7.a[d.getInstance().b().ordinal()]) {
                case 1:
                    eVar.t = new Integer(R.drawable.mini_point_of_interest);
                    break;
                case 2:
                    eVar.t = new Integer(R.drawable.mini_waypoint);
                    break;
                case 3:
                    eVar.t = new Integer(R.drawable.mini_followme);
                    break;
                case 4:
                    eVar.t = new Integer(R.drawable.mini_course_lock);
                    break;
                case 5:
                    eVar.t = new Integer(R.drawable.mini_home_lock);
                    break;
                case 6:
                    eVar.t = new Integer(R.drawable.mini_terrain_tracking);
                    break;
            }
            dji.thirdparty.a.c.a().e(eVar);
        }
        a(dji.pilot.dji_groundstation.a.b.EVENT_SMARTDIALOG_DISMISS, null);
    }

    public void f() {
        new dji.pilot.dji_groundstation.ui.a.a(this.d).show();
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 9;
        eVar.t = null;
        dji.thirdparty.a.c.a().e(eVar);
    }

    public void a(dji.pilot.dji_groundstation.a.b bVar, Object obj) {
        if (this.g != null) {
            synchronized (this.g) {
                Iterator it = this.g.iterator();
                while (it.hasNext()) {
                    b bVar2 = (b) it.next();
                    if (bVar2 != null) {
                        bVar2.a(bVar, obj);
                    }
                }
            }
        }
    }

    public a g() {
        return new a(this);
    }

    private void b(String str) {
        this.h = str;
        new Thread(this.n).run();
    }

    private void c(String str) {
        this.i = str;
        new Thread(this.a).run();
    }

    public void h() {
        this.h = i.getInstance(this.d).d();
        b(this.h);
    }

    public void a(dji.pilot.dji_groundstation.a.d.c cVar) {
        if (cVar != null) {
            if (cVar == dji.pilot.dji_groundstation.a.d.c.e) {
                dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().a(this.d);
            }
            e();
            this.i = i.getInstance(this.d).a(cVar);
            if (this.i == null || this.i.isEmpty()) {
                a(this.d);
            } else {
                c(this.i);
            }
        }
    }

    public void a(d$b dji_pilot_dji_groundstation_a_d_b) {
        if (dji_pilot_dji_groundstation_a_d_b != null) {
            this.i = i.getInstance(this.d).a(dji_pilot_dji_groundstation_a_d_b);
            c(this.i);
        }
    }

    public void i() {
        if (this.j != null) {
            this.j.dismiss();
            this.j = null;
        }
        if (this.k != null) {
            this.k.dismiss();
            this.k = null;
        }
        if (this.l != null) {
            this.l.hide();
            this.l = null;
        }
        if (this.m != null) {
            this.m.dismiss();
            this.m = null;
        }
    }

    public void c(Context context) {
        this.d = context;
        if (this.d != null) {
            if (this.j == null) {
                this.j = new dji.pilot.dji_groundstation.ui.a.d(this.d);
            }
            a(this.j);
            d.getInstance().a(this.j);
            if (this.k == null) {
                this.k = new i(this.d);
            }
            a(this.k);
            d.getInstance().a(this.k);
            if (this.l == null) {
                this.l = new dji.pilot.dji_groundstation.ui.a.f(this.d);
            }
            a(this.l);
            d.getInstance().a(this.l);
            if (this.m == null) {
                this.m = new g(this.d);
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.p();
                    dji.pilot.dji_groundstation.a.e l = this.a.l();
                    if (l != null) {
                        dji.thirdparty.a.c.a().e(l);
                    }
                    if (d.getInstance().b().a() == dji.pilot.dji_groundstation.a.d.c.g.a()) {
                        dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().g(dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().j());
                    } else if (d.getInstance().b().a() == dji.pilot.dji_groundstation.a.d.c.d.a()) {
                        l = new dji.pilot.dji_groundstation.a.e();
                        l.s = 21;
                        l.t = dji.gs.utils.a.a(new dji.gs.e.b(dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().i(), dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().j()));
                        dji.thirdparty.a.c.a().e(l);
                    }
                }
            }, 1000);
        }
    }

    public void j() {
        if (this.d == null) {
            this.d = null;
        }
        i();
        if (this.g != null) {
            Iterator it = this.g.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
            }
            this.g.clear();
            this.g = null;
        }
    }

    private f(Context context) {
        this.d = context;
        if (this.j == null) {
            this.j = new dji.pilot.dji_groundstation.ui.a.d(context);
        }
        if (this.k == null) {
            this.k = new i(context);
        }
        if (this.l == null) {
            this.l = new dji.pilot.dji_groundstation.ui.a.f(context);
        }
        if (this.m == null) {
            this.m = new g(context);
        }
    }

    private synchronized boolean d(String str) {
        boolean z = false;
        synchronized (this) {
            if (!(this.e == null || str == null)) {
                if (!(str.trim().isEmpty() || this.e.m == null)) {
                    this.e.m.clear();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            this.e.a = k.getInstance().a(jSONObject);
                            this.e.c = k.getInstance().k(jSONObject);
                            this.e.d = k.getInstance().d(jSONObject);
                            this.e.h = k.getInstance().b(jSONObject, this.d);
                            this.e.g = k.getInstance().a(jSONObject, this.d);
                            this.e.e = k.getInstance().g(jSONObject);
                            this.e.f = k.getInstance().h(jSONObject);
                            this.e.i = k.getInstance().i(jSONObject);
                            this.e.l = k.getInstance().j(jSONObject);
                            this.e.k = k.getInstance().b(jSONObject);
                            this.e.j = k.getInstance().e(jSONObject, this.d);
                            JSONArray n = k.getInstance().n(jSONObject);
                            if (n != null) {
                                int i;
                                int flycVersion;
                                for (i = 0; i < n.length(); i++) {
                                    JSONObject jSONObject2 = n.getJSONObject(i);
                                    if (jSONObject2 != null) {
                                        int f = k.getInstance().f(jSONObject2);
                                        int e = k.getInstance().e(jSONObject2);
                                        flycVersion = DataOsdGetPushCommon.getInstance().isGetted() ? DataOsdGetPushCommon.getInstance().getFlycVersion() : -2;
                                        if (flycVersion >= e && flycVersion <= f) {
                                            b bVar = new b(this);
                                            bVar.a = k.getInstance().c(jSONObject2, this.d);
                                            bVar.b = k.getInstance().d(jSONObject2, this.d);
                                            bVar.c = k.getInstance().l(jSONObject2);
                                            bVar.d = k.getInstance().m(jSONObject2);
                                            this.e.m.add(bVar);
                                        }
                                    }
                                }
                                i = (this.e.d * this.e.c) - (n.length() % (this.e.d * this.e.c));
                                for (flycVersion = 0; flycVersion < i; flycVersion++) {
                                    b bVar2 = new b(this);
                                    bVar2.a = -1;
                                    bVar2.b = -1;
                                    bVar2.c = false;
                                    bVar2.d = "";
                                    this.e.m.add(bVar2);
                                }
                                this.e.b = this.e.m.size();
                                z = true;
                            }
                        }
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    private synchronized boolean e(String str) {
        boolean z = false;
        synchronized (this) {
            if (!(this.f == null || str == null)) {
                if (!(str.trim().isEmpty() || this.f.s == null)) {
                    this.f.s.clear();
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            this.f.a = j.getInstance().a(jSONObject);
                            this.f.b = j.getInstance().a(jSONObject, this.d);
                            this.f.c = j.getInstance().f(jSONObject, this.d);
                            this.f.d = j.getInstance().b(jSONObject, this.d);
                            this.f.g = j.getInstance().b(jSONObject);
                            this.f.f = j.getInstance().c(jSONObject);
                            this.f.e = j.getInstance().c(jSONObject, this.d);
                            this.f.h = j.getInstance().d(jSONObject, this.d);
                            this.f.k = j.getInstance().e(jSONObject);
                            this.f.i = j.getInstance().e(jSONObject, this.d);
                            this.f.j = j.getInstance().d(jSONObject);
                            this.f.l = j.getInstance().n(jSONObject);
                            this.f.o = j.getInstance().h(jSONObject, this.d);
                            this.f.p = j.getInstance().g(jSONObject, this.d);
                            this.f.q = j.getInstance().l(jSONObject);
                            this.f.r = j.getInstance().m(jSONObject);
                            this.f.m = j.getInstance().j(jSONObject);
                            this.f.n = j.getInstance().k(jSONObject);
                            JSONArray f = j.getInstance().f(jSONObject);
                            if (f != null && f.length() > 0) {
                                for (int i = 0; i < f.length(); i++) {
                                    JSONObject jSONObject2 = f.getJSONObject(i);
                                    if (jSONObject2 != null) {
                                        c cVar = new c(this);
                                        cVar.a = j.getInstance().f(jSONObject2, this.d);
                                        cVar.d = j.getInstance().i(jSONObject2);
                                        cVar.b = j.getInstance().g(jSONObject2);
                                        cVar.c = j.getInstance().h(jSONObject2);
                                        this.f.s.add(cVar);
                                    }
                                }
                            }
                            z = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    public void k() {
        if (this.m == null) {
            this.m = new g(this.d);
        }
        this.m.show();
    }

    public dji.pilot.dji_groundstation.a.e l() {
        if (d.getInstance().a().a() != d$a.Smart.a()) {
            return null;
        }
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 8;
        eVar.t = null;
        switch (AnonymousClass7.a[d.getInstance().b().ordinal()]) {
            case 1:
                eVar.t = new Integer(R.drawable.mini_point_of_interest);
                return eVar;
            case 2:
                eVar.t = new Integer(R.drawable.mini_waypoint);
                return eVar;
            case 3:
                eVar.t = new Integer(R.drawable.mini_followme);
                return eVar;
            case 4:
                eVar.t = new Integer(R.drawable.mini_course_lock);
                return eVar;
            case 5:
                eVar.t = new Integer(R.drawable.mini_home_lock);
                return eVar;
            case 6:
                eVar.t = new Integer(R.drawable.mini_terrain_tracking);
                return eVar;
            default:
                return eVar;
        }
    }

    private void p() {
        d$a a = d.getInstance().a();
        dji.pilot.dji_groundstation.a.d.c b = d.getInstance().b();
        if (a.a() != d$a.Smart.a()) {
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 16;
            switch (a) {
                case Point:
                    eVar.t = new Integer(R.drawable.mini_point);
                    break;
                case Track:
                    eVar.t = new Integer(R.drawable.mini_track);
                    break;
                case Normal:
                    eVar.t = new Integer(R.drawable.mini_normal);
                    break;
                case Trackselfie:
                    eVar.t = new Integer(R.drawable.mini_selfie);
                    break;
            }
            dji.thirdparty.a.c.a().e(eVar);
            return;
        }
        dji.pilot.dji_groundstation.a.e eVar2 = new dji.pilot.dji_groundstation.a.e();
        eVar2.s = 16;
        if (b.a(dji.pilot.dji_groundstation.a.d.c.o)) {
            eVar2.t = new Integer(R.drawable.mini_course_lock);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.q)) {
            eVar2.t = new Integer(R.drawable.mini_home_lock);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.m)) {
            eVar2.t = new Integer(R.drawable.mini_followme);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.b)) {
            eVar2.t = new Integer(R.drawable.mini_point_of_interest);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.e)) {
            eVar2.t = new Integer(R.drawable.mini_waypoint);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.t)) {
            eVar2.t = new Integer(R.drawable.mini_terrain_tracking);
        } else if (b.a(dji.pilot.dji_groundstation.a.d.c.v)) {
            eVar2.t = new Integer(R.drawable.mini_tripod);
        } else {
            eVar2.t = new Integer(R.drawable.mini_normal);
        }
        dji.thirdparty.a.c.a().e(eVar2);
    }

    public boolean a(b bVar) {
        if (bVar == null) {
            return false;
        }
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (this.g.contains(bVar)) {
            return false;
        }
        this.g.add(bVar);
        return true;
    }
}
