package dji.sdksharedlib.hardware;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.common.error.DJISDKCacheError;
import dji.log.DJILog;
import dji.midware.c.a.d;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.g;
import dji.sdksharedlib.c.h;
import dji.sdksharedlib.d.a$a;
import dji.sdksharedlib.d.a$b;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b$f;
import dji.sdksharedlib.hardware.abstractions.b$g;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.b.i;
import dji.sdksharedlib.hardware.abstractions.b.l;
import dji.sdksharedlib.hardware.abstractions.c.d.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class a {
    public static final int a = 100;
    public static final int b = 500;
    protected static final String c = "Product";
    protected static final String d = "Battery";
    protected static final String e = "Gimbal";
    protected static final String f = "FlightController";
    protected static final String g = "RemoteController";
    protected static final String h = "HandheldController";
    protected static final String i = "Camera";
    protected static final String j = "AirLink";
    private static final String o = "DJISDKCacheHWAbstractionLayer";
    b$f k = new b$f(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(Object obj, c cVar, b$g dji_sdksharedlib_hardware_abstractions_b_g) {
            this.a.b(obj, cVar, a$b.Valid, a$a.Get, this.a.d(cVar) == DJISDKCacheUpdateType.DYNAMIC ? 100 : -1, dji_sdksharedlib_hardware_abstractions_b_g);
        }

        public void a(Object obj, c cVar) {
            DJILog.d("WudiRC", "set on value from setter", true, true);
            this.a.b(obj, cVar, a$b.Valid, a$a.Set, this.a.d(cVar) == DJISDKCacheUpdateType.DYNAMIC ? 100 : -1, null);
        }

        public void b(Object obj, c cVar) {
            this.a.b(obj, cVar, a$b.Valid, a$a.Push, this.a.d(cVar) == DJISDKCacheUpdateType.DYNAMIC ? 100 : -1, null);
        }
    };
    public Map<String, List<b>> l = null;
    TimerTask m = new TimerTask(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a("MockAbstraction");
        }
    };
    Timer n = new Timer();
    private DJISDKCacheError p = null;
    private dji.sdksharedlib.d.c q = null;
    private dji.sdksharedlib.hardware.a.a r = null;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[dji.midware.c.a.c.values().length];
        static final /* synthetic */ int[] b = new int[dji.midware.c.a.b.values().length];
        static final /* synthetic */ int[] c = new int[dji.midware.c.a.a.values().length];
        static final /* synthetic */ int[] d = new int[d.values().length];

        static {
            try {
                d[d.e.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                d[d.f.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                d[d.b.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                d[d.d.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                d[d.c.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                d[d.h.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                c[dji.midware.c.a.a.g.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                c[dji.midware.c.a.a.h.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                c[dji.midware.c.a.a.i.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                c[dji.midware.c.a.a.n.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                c[dji.midware.c.a.a.j.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
            try {
                c[dji.midware.c.a.a.k.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
            try {
                c[dji.midware.c.a.a.p.ordinal()] = 7;
            } catch (NoSuchFieldError e13) {
            }
            try {
                c[dji.midware.c.a.a.l.ordinal()] = 8;
            } catch (NoSuchFieldError e14) {
            }
            try {
                c[dji.midware.c.a.a.m.ordinal()] = 9;
            } catch (NoSuchFieldError e15) {
            }
            try {
                b[dji.midware.c.a.b.b.ordinal()] = 1;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[dji.midware.c.a.c.d.ordinal()] = 1;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[dji.midware.c.a.c.b.ordinal()] = 2;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[dji.midware.c.a.c.c.ordinal()] = 3;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[dji.midware.c.a.c.e.ordinal()] = 4;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[dji.midware.c.a.c.j.ordinal()] = 5;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[dji.midware.c.a.c.n.ordinal()] = 6;
            } catch (NoSuchFieldError e22) {
            }
            try {
                a[dji.midware.c.a.c.g.ordinal()] = 7;
            } catch (NoSuchFieldError e23) {
            }
            try {
                a[dji.midware.c.a.c.f.ordinal()] = 8;
            } catch (NoSuchFieldError e24) {
            }
            try {
                a[dji.midware.c.a.c.k.ordinal()] = 9;
            } catch (NoSuchFieldError e25) {
            }
            try {
                a[dji.midware.c.a.c.l.ordinal()] = 10;
            } catch (NoSuchFieldError e26) {
            }
            try {
                a[dji.midware.c.a.c.h.ordinal()] = 11;
            } catch (NoSuchFieldError e27) {
            }
            try {
                a[dji.midware.c.a.c.i.ordinal()] = 12;
            } catch (NoSuchFieldError e28) {
            }
            try {
                a[dji.midware.c.a.c.m.ordinal()] = 13;
            } catch (NoSuchFieldError e29) {
            }
            try {
                a[dji.midware.c.a.c.o.ordinal()] = 14;
            } catch (NoSuchFieldError e30) {
            }
        }
    }

    public static class a {
    }

    public void a(dji.sdksharedlib.d.c cVar, g gVar) {
        DJILog.i(o, "init");
        this.q = cVar;
        this.l = new HashMap();
        dji.thirdparty.a.c.a().a(this);
        a();
        y();
        h();
        s();
        n();
        v();
        q();
        this.r = new dji.sdksharedlib.hardware.a.a();
        this.r.a(this, gVar);
    }

    public void i() {
        DJILog.i(o, "destroy");
        dji.thirdparty.a.c.a().d(this);
        this.r.a();
    }

    public void a(c cVar, Object obj, h hVar) {
        b b = b(cVar);
        if (b != null) {
            b.b(cVar, obj, hVar);
        } else if (hVar != null) {
            hVar.a(DJISDKCacheError.INVALID_KEY_FOR_COMPONENT);
        }
    }

    public void a(c cVar, dji.sdksharedlib.c.c cVar2) {
        b b = b(cVar);
        if (b != null) {
            if (!b.a(cVar) || this.q.a(cVar) == null) {
                if (b.c(cVar)) {
                    dji.sdksharedlib.d.a a = this.q.a(cVar);
                    if (a != null) {
                        if (cVar2 != null) {
                            cVar2.a(a);
                            return;
                        }
                        return;
                    }
                }
                b.b(cVar, cVar2);
                return;
            }
            dji.sdksharedlib.d.a a2 = this.q.a(cVar);
            if (cVar2 != null) {
                cVar2.a(a2);
            }
        } else if (cVar2 != null) {
            cVar2.a(DJISDKCacheError.INVALID_KEY_FOR_COMPONENT);
        }
    }

    public void a(c cVar, dji.sdksharedlib.c.b bVar, Object... objArr) {
        b b = b(cVar);
        if (b != null) {
            b.b(cVar, bVar, objArr);
        } else {
            bVar.a(DJISDKCacheError.INVALID_KEY_FOR_COMPONENT);
        }
    }

    private b b(c cVar) {
        if (cVar.b() == null || !this.l.containsKey(cVar.b())) {
            return null;
        }
        List list = (List) this.l.get(cVar.b());
        int intValue = cVar.c().intValue();
        if (cVar.c().intValue() == Integer.MAX_VALUE || list.size() <= intValue) {
            return (b) list.get(list.size() - 1);
        }
        return (b) list.get(intValue);
    }

    private b c(c cVar) {
        return b(cVar);
    }

    protected void a() {
        a("Product");
        a(1, "Product", dji.sdksharedlib.hardware.abstractions.g.a.class);
    }

    private String a(b bVar, String str) {
        return bVar.toString() + "." + str;
    }

    protected void a(String str) {
        Set<String> keySet = this.l.keySet();
        List<String> arrayList = new ArrayList();
        for (String str2 : keySet) {
            if (str2.contains(str)) {
                arrayList.add(str2);
            }
        }
        for (String str22 : arrayList) {
            int i = 0;
            for (b e : (List) this.l.get(str22)) {
                e.e();
                this.q.a(str, i);
                i++;
            }
            this.l.remove(str22);
        }
    }

    protected void a(int i, String str, Class<? extends b> cls) {
        if (i <= 0 || cls == null || str == null) {
            DJILog.i(o, "addAbstraction: invalid input parameters");
        } else if (!this.l.containsKey(str) || i == Integer.MAX_VALUE) {
            Object arrayList = new ArrayList();
            b bVar;
            if (i == Integer.MAX_VALUE) {
                try {
                    bVar = (b) cls.newInstance();
                    bVar.a(str, Integer.MAX_VALUE, this.q, this.k);
                    arrayList.add(bVar);
                } catch (Exception e) {
                    DJILog.d(o, "DJISDKCacheHWAbstractionLayer addAbstraction * Exception  : " + str + DJILog.exceptionToString(e));
                    return;
                }
            }
            int i2 = 0;
            while (i2 < i) {
                try {
                    bVar = (b) cls.newInstance();
                    bVar.a(str, i2, this.q, this.k);
                    arrayList.add(bVar);
                    i2++;
                } catch (Exception e2) {
                    DJILog.e(o, "DJISDKCacheHWAbstractionLayer addAbstraction Exception  : " + str + DJILog.exceptionToString(e2));
                    return;
                }
            }
            if (this.l.containsKey(str)) {
                List list = (List) this.l.get(str);
                list.addAll(arrayList);
                this.l.put(str, list);
                return;
            }
            this.l.put(str, arrayList);
        }
    }

    private DJISDKCacheUpdateType d(c cVar) {
        b b = b(cVar);
        if (b != null) {
            return b.b(cVar);
        }
        return null;
    }

    private boolean b(Object obj, c cVar, a$b dji_sdksharedlib_d_a_b, a$a dji_sdksharedlib_d_a_a, long j, b$g dji_sdksharedlib_hardware_abstractions_b_g) {
        dji.sdksharedlib.d.a a = this.q.a(cVar);
        if (a == null && obj == null) {
            a(Boolean.valueOf(false), obj, dji_sdksharedlib_hardware_abstractions_b_g);
            return false;
        } else if (a == null || obj == null) {
            if (a != null) {
                this.q.b(cVar);
                a(Boolean.valueOf(true), obj, dji_sdksharedlib_hardware_abstractions_b_g);
                return true;
            }
            if (j > 0) {
                a = new dji.sdksharedlib.d.a(obj, dji_sdksharedlib_d_a_b, dji_sdksharedlib_d_a_a, j);
            } else {
                a = new dji.sdksharedlib.d.a(obj, dji_sdksharedlib_d_a_b, dji_sdksharedlib_d_a_a);
            }
            this.q.a(a, cVar);
            a(Boolean.valueOf(true), obj, dji_sdksharedlib_hardware_abstractions_b_g);
            return true;
        } else if (a.a(obj)) {
            a.c();
            if (dji_sdksharedlib_hardware_abstractions_b_g != null) {
                dji_sdksharedlib_hardware_abstractions_b_g.a(a);
            }
            return true;
        } else if (a.a() == a$a.Set && dji_sdksharedlib_d_a_a == a$a.Push && System.currentTimeMillis() - a.b() < 500) {
            return false;
        } else {
            if (j > 0) {
                a = new dji.sdksharedlib.d.a(obj, dji_sdksharedlib_d_a_b, dji_sdksharedlib_d_a_a, j);
            } else {
                a = new dji.sdksharedlib.d.a(obj, dji_sdksharedlib_d_a_b, dji_sdksharedlib_d_a_a);
            }
            this.q.a(a, cVar);
            a(Boolean.valueOf(true), obj, dji_sdksharedlib_hardware_abstractions_b_g);
            return true;
        }
    }

    private void a(Boolean bool, Object obj, b$g dji_sdksharedlib_hardware_abstractions_b_g) {
        if (dji_sdksharedlib_hardware_abstractions_b_g == null) {
            return;
        }
        if (bool.booleanValue()) {
            dji_sdksharedlib_hardware_abstractions_b_g.a(obj);
        } else {
            dji_sdksharedlib_hardware_abstractions_b_g.a(this.p);
        }
    }

    public void b(int i, String str, Class<? extends b> cls) {
        a(i, str, (Class) cls);
    }

    private void h() {
        c();
    }

    private void m() {
        a("Battery");
        c();
    }

    protected void c() {
        dji.midware.c.a.c a = dji.midware.c.a.getInstance().a();
        DJILog.i(o, "battery add abstration. platformType = " + a);
        switch (AnonymousClass3.a[a.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                a(1, "Battery", i.class);
                return;
            case 7:
            case 8:
                a(1, "Battery", f.class);
                return;
            case 9:
                a(6, "Battery", dji.sdksharedlib.hardware.abstractions.b.h.class);
                a(Integer.MAX_VALUE, "Battery", dji.sdksharedlib.hardware.abstractions.b.b.class);
                return;
            case 10:
                a(2, "Battery", dji.sdksharedlib.hardware.abstractions.b.g.class);
                a(Integer.MAX_VALUE, "Battery", dji.sdksharedlib.hardware.abstractions.b.b.class);
                return;
            case 11:
                a(1, "Battery", dji.sdksharedlib.hardware.abstractions.b.d.class);
                return;
            case 12:
                a(1, "Battery", e.class);
                return;
            case 13:
                a(1, "Battery", dji.sdksharedlib.hardware.abstractions.b.c.class);
                return;
            case 14:
                if (DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.A3) {
                    a(1, "Battery", l.class);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void n() {
        p();
    }

    private void o() {
        a("HandheldController");
        p();
    }

    private void p() {
        switch (AnonymousClass3.a[dji.midware.c.a.getInstance().a().ordinal()]) {
            case 11:
                DJILog.i("OSMO", "before handheld battery: " + this.l.size());
                a(1, "HandheldController", dji.sdksharedlib.hardware.abstractions.f.a.class);
                return;
            case 12:
                a(1, "HandheldController", dji.sdksharedlib.hardware.abstractions.f.c.class);
                return;
            default:
                return;
        }
    }

    private void q() {
        f();
    }

    private void r() {
        a("AirLink");
        f();
    }

    protected void f() {
        dji.sdksharedlib.hardware.abstractions.a.a aVar = null;
        try {
            switch (AnonymousClass3.a[dji.midware.c.a.getInstance().a().ordinal()]) {
                case 1:
                    aVar = new dji.sdksharedlib.hardware.abstractions.a.a(false, true, true, new dji.sdksharedlib.hardware.abstractions.a.b.d(), null, new dji.sdksharedlib.hardware.abstractions.a.b());
                    break;
                case 2:
                case 3:
                case 5:
                case 7:
                case 8:
                    aVar = new dji.sdksharedlib.hardware.abstractions.a.a(true, false, false, null, new dji.sdksharedlib.hardware.abstractions.a.a.a(), null);
                    break;
                case 4:
                    aVar = new dji.sdksharedlib.hardware.abstractions.a.a(false, true, true, new dji.sdksharedlib.hardware.abstractions.a.b.d(), null, new dji.sdksharedlib.hardware.abstractions.a.b());
                    break;
                case 6:
                case 9:
                case 14:
                    aVar = new dji.sdksharedlib.hardware.abstractions.a.a(true, false, false, null, new dji.sdksharedlib.hardware.abstractions.a.a.b(), null);
                    break;
                case 11:
                    aVar = new dji.sdksharedlib.hardware.abstractions.a.a(false, true, false, new dji.sdksharedlib.hardware.abstractions.a.b.c(), null, null);
                    break;
                case 13:
                    aVar = new dji.sdksharedlib.hardware.abstractions.a.a(true, true, false, new dji.sdksharedlib.hardware.abstractions.a.b.b(), new dji.sdksharedlib.hardware.abstractions.a.a.d(), null);
                    break;
            }
            if (aVar != null) {
                a(aVar);
            }
        } catch (Exception e) {
            DJILog.e(o, "DJISDKCacheHWAbstractionLayer addAbstraction Exception  : AirLink" + DJILog.exceptionToString(e));
        }
    }

    protected void a(dji.sdksharedlib.hardware.abstractions.a.a aVar) {
        aVar.a("AirLink", 0, this.q, this.k);
        ArrayList arrayList = new ArrayList();
        arrayList.add(aVar);
        this.l.put("AirLink", arrayList);
    }

    private void s() {
        e();
    }

    private void t() {
        a("Gimbal");
        e();
    }

    protected void e() {
        dji.midware.c.a.b g = dji.midware.c.a.getInstance().g();
        dji.midware.c.a.c a = dji.midware.c.a.getInstance().a();
        Boolean valueOf = Boolean.valueOf(false);
        switch (AnonymousClass3.b[g.ordinal()]) {
            case 1:
                valueOf = Boolean.valueOf(true);
                a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.h.class);
                break;
        }
        if (!valueOf.booleanValue()) {
            switch (AnonymousClass3.a[a.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                    a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.f.class);
                    return;
                case 5:
                case 6:
                    a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.g.class);
                    return;
                case 7:
                case 8:
                    a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.i.class);
                    return;
                case 11:
                    a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.d.class);
                    return;
                case 12:
                    a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.e.class);
                    return;
                case 13:
                    a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.c.class);
                    return;
                case 14:
                    switch (AnonymousClass3.c[dji.midware.c.a.getInstance().e().ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            a(1, "Gimbal", dji.sdksharedlib.hardware.abstractions.e.i.class);
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    private void u() {
        a("Battery");
        a(1, "Battery", i.class);
        DJILog.i("HWAbstractionLayer", "after update fake battery, map size: " + this.l.size());
    }

    private void v() {
        d();
    }

    private void w() {
        a("FlightController");
        d();
    }

    protected void d() {
        switch (AnonymousClass3.a[dji.midware.c.a.getInstance().a().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.d.class);
                return;
            case 5:
            case 6:
                a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.h.class);
                return;
            case 7:
                a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.g.class);
                return;
            case 8:
                a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.f.class);
                return;
            case 9:
                a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.b.class);
                return;
            case 10:
                a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.b.class);
                return;
            case 13:
                a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.e.class);
                return;
            case 14:
                if (DataOsdGetPushCommon.getInstance().getDroneType() != DroneType.A3) {
                    a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.a.class);
                    return;
                } else if (dji.midware.c.a.getInstance().c() == d.f) {
                    a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.c.class);
                    return;
                } else {
                    a(1, "FlightController", dji.sdksharedlib.hardware.abstractions.d.b.class);
                    return;
                }
            default:
                return;
        }
    }

    private void x() {
        a("RemoteController");
        g();
    }

    private void y() {
        b();
    }

    private void z() {
        a("Camera");
        b();
    }

    protected void g() {
        switch (AnonymousClass3.d[dji.midware.c.a.getInstance().c().ordinal()]) {
            case 1:
            case 2:
                a(1, "RemoteController", dji.sdksharedlib.hardware.abstractions.h.c.class);
                return;
            case 3:
            case 4:
                a(1, "RemoteController", dji.sdksharedlib.hardware.abstractions.h.d.class);
                return;
            case 5:
                a(1, "RemoteController", dji.sdksharedlib.hardware.abstractions.h.e.class);
                return;
            case 6:
                a(1, "RemoteController", dji.sdksharedlib.hardware.abstractions.h.b.class);
                return;
            default:
                return;
        }
    }

    protected void b() {
        dji.midware.c.a.c a = dji.midware.c.a.getInstance().a();
        dji.midware.c.a.a e = dji.midware.c.a.getInstance().e();
        DJILog.d(o, "addCameraAbstraction " + a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + e);
        switch (AnonymousClass3.c[e.ordinal()]) {
            case 1:
                if (a == dji.midware.c.a.c.h) {
                    a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.d.class);
                    return;
                } else {
                    a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.c.class);
                    return;
                }
            case 2:
                if (a == dji.midware.c.a.c.h) {
                    a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.g.class);
                    return;
                } else {
                    a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.e.class);
                    return;
                }
            case 3:
                if (a == dji.midware.c.a.c.h) {
                    a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.i.class);
                    return;
                } else {
                    a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.h.class);
                    return;
                }
            case 4:
                if (a == dji.midware.c.a.c.h) {
                    a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.l.class);
                    return;
                } else {
                    a(1, "Camera", k.class);
                    return;
                }
            case 5:
                a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.a.class);
                return;
            case 6:
                a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.d.b.class);
                return;
            case 7:
                a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.b.a.class);
                return;
            case 8:
                a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.a.b.class);
                return;
            case 9:
                a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.a.a.class);
                return;
            default:
                switch (AnonymousClass3.a[a.ordinal()]) {
                    case 1:
                        a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.c.c.class);
                        return;
                    case 2:
                        a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.c.b.class);
                        return;
                    case 3:
                        a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.c.a.class);
                        return;
                    case 4:
                        a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.c.d.class);
                        return;
                    case 5:
                        a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.c.e.class);
                        return;
                    case 6:
                        a(1, "Camera", dji.sdksharedlib.hardware.abstractions.c.c.f.class);
                        return;
                    default:
                        return;
                }
        }
    }

    public void onEventBackgroundThread(dji.midware.c.a.c cVar) {
        DJILog.i(o, "onEventBackgroundThread PlatformType " + cVar);
        w();
        m();
        o();
        r();
        j();
    }

    public void onEventBackgroundThread(d dVar) {
        DJILog.i(o, "onEventBackgroundThread RcComponentType " + dVar);
        x();
        j();
    }

    public void onEventBackgroundThread(dji.midware.c.a.a aVar) {
        DJILog.i(o, "onEventBackgroundThread CameraComponentType " + aVar);
        z();
        t();
        j();
    }

    public int a(c cVar) {
        b b = b(cVar);
        if (b != null) {
            return b.d(cVar);
        }
        return 0;
    }

    protected void j() {
        dji.thirdparty.a.c.a().e(new a());
    }

    public void k() {
        this.n.schedule(this.m, 0);
    }

    public void a(Object obj, c cVar, a$b dji_sdksharedlib_d_a_b, a$a dji_sdksharedlib_d_a_a, long j, b$g dji_sdksharedlib_hardware_abstractions_b_g) {
        b(obj, cVar, dji_sdksharedlib_d_a_b, dji_sdksharedlib_d_a_a, j, dji_sdksharedlib_hardware_abstractions_b_g);
    }

    public dji.sdksharedlib.hardware.a.a l() {
        return this.r;
    }
}
