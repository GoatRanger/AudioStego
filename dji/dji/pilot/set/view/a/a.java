package dji.pilot.set.view.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import java.lang.ref.WeakReference;

public class a {
    public static final String a = ".";
    private static final int ae = 0;
    private static final int af = 1;
    private static final int ag = 2;
    public static final String b = "unknown";
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    public static final int i = 6;
    public static final int j = 7;
    public static final int k = 8;
    public static final int l = 9;
    public static final int m = 10;
    public static final int n = 0;
    public static final int o = 1;
    private static final int p = 3;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private DataCommonGetVersion K;
    private DataCommonGetVersion L;
    private DataCommonGetVersion M;
    private DataCommonGetVersion N;
    private DataCommonGetVersion O;
    private DataCommonGetVersion P;
    private DataCommonGetVersion Q;
    private DataCommonGetVersion R;
    private DataCommonGetVersion S;
    private DataCommonGetVersion T;
    private d U;
    private d V;
    private d W;
    private d X;
    private d Y;
    private d Z;
    private d aa;
    private d ab;
    private d ac;
    private d ad;
    private int ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private final a as;
    private b at;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    private static final class a extends Handler {
        private final WeakReference<a> a;

        public a(a aVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null) {
                aVar.a(message.arg1, message.arg2, message.obj);
            }
        }
    }

    public interface b {
        void a(int i);

        void a(int i, int i2, dji.midware.data.config.P3.a aVar);
    }

    private static final class c {
        public static final a a = new a();

        private c() {
        }
    }

    public static a getInstance() {
        return c.a;
    }

    public void a(b bVar) {
        this.at = bVar;
    }

    public void a() {
        this.at = null;
    }

    public void b() {
        if (this.ah == 2 && !this.K.isGetted()) {
            this.ah = 0;
            this.q = "unknown";
            this.z = "unknown";
        }
        if (this.ai == 2 && !this.L.isGetted()) {
            this.ai = 0;
            this.r = "unknown";
            this.A = "unknown";
        }
        if (this.aj == 2 && !this.M.isGetted()) {
            this.aj = 0;
            this.s = "unknown";
            this.B = "unknown";
        }
        if (this.ak == 2 && !this.N.isGetted()) {
            this.ak = 0;
            this.t = "unknown";
            this.C = "unknown";
        }
        if (this.al == 2 && !this.O.isGetted()) {
            this.al = 0;
            this.u = "unknown";
            this.D = "unknown";
        }
        if (this.am == 2 && !this.P.isGetted()) {
            this.am = 0;
            this.v = "unknown";
            this.E = "unknown";
        }
        if (this.an == 2 && !this.Q.isGetted()) {
            this.an = 0;
            this.w = "unknown";
            this.F = "unknown";
        }
        if (this.ao == 2 && !this.R.isGetted()) {
            this.ao = 0;
            this.x = "unknown";
            this.G = "unknown";
        }
        if (this.ap == 2 && !this.S.isGetted()) {
            this.ap = 0;
            this.y = "unknown";
            this.H = "unknown";
        }
        if (this.aq == 2 && !this.T.isGetted()) {
            this.aq = 0;
            this.I = "unknown";
            this.J = "unknown";
        }
        y();
    }

    public void c() {
        this.as.removeMessages(0);
        this.ah = 0;
        this.ai = 0;
        this.aj = 0;
        this.ak = 0;
        this.al = 0;
        this.am = 0;
        this.an = 0;
        this.ao = 0;
        this.ap = 0;
        this.aq = 0;
        this.q = "unknown";
        this.r = "unknown";
        this.s = "unknown";
        this.t = "unknown";
        this.u = "unknown";
        this.v = "unknown";
        this.w = "unknown";
        this.x = "unknown";
        this.y = "unknown";
        this.I = "unknown";
        this.z = "unknown";
        this.A = "unknown";
        this.B = "unknown";
        this.C = "unknown";
        this.D = "unknown";
        this.E = "unknown";
        this.F = "unknown";
        this.G = "unknown";
        this.H = "unknown";
        this.J = "unknown";
        a(0);
    }

    public void d() {
        if (this.ah == 1) {
            this.ah = 0;
        }
        if (this.ai == 1) {
            this.ai = 0;
        }
        if (this.aj == 1) {
            this.aj = 0;
        }
        if (this.ak == 1) {
            this.ak = 0;
        }
        if (this.al == 1) {
            this.al = 0;
        }
        if (this.am == 1) {
            this.am = 0;
        }
        if (this.an == 1) {
            this.an = 0;
        }
        if (this.ao == 1) {
            this.ao = 0;
        }
        if (this.ap == 1) {
            this.ap = 0;
        }
        if (this.aq == 1) {
            this.aq = 0;
        }
    }

    public String e() {
        return this.q;
    }

    public String f() {
        return this.z;
    }

    public String g() {
        return this.r;
    }

    public String h() {
        return this.A;
    }

    public String i() {
        return this.s;
    }

    public String j() {
        return this.B;
    }

    public String k() {
        return this.t;
    }

    public String l() {
        return this.C;
    }

    public String m() {
        return this.u;
    }

    public String n() {
        return this.D;
    }

    public String o() {
        return this.v;
    }

    public String p() {
        return this.E;
    }

    public String q() {
        return this.w;
    }

    public String r() {
        return this.F;
    }

    public String s() {
        return this.x;
    }

    public String t() {
        return this.G;
    }

    public String u() {
        return this.y;
    }

    public String v() {
        return this.I;
    }

    public String w() {
        return this.H;
    }

    public String x() {
        return this.J;
    }

    private void y() {
        if (this.ah == 0) {
            this.ah = 1;
            this.K.start(this.U);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.ai == 0) {
            this.ai = 1;
            this.L.start(this.V);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.aj == 0) {
            this.aj = 1;
            this.M.start(this.W);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.am == 0) {
            this.am = 1;
            this.P.start(this.Z);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.al == 0) {
            this.al = 1;
            this.O.start(this.Y);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.ao == 0) {
            this.ao = 1;
            this.R.start(this.ab);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.an == 0) {
            this.an = 1;
            this.Q.start(this.aa);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.ak == 0) {
            this.ak = 1;
            this.N.start(this.X);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.ap == 0) {
            this.ap = 1;
            this.S.start(this.ac);
            this.ar++;
            if (this.ar >= 3) {
                return;
            }
        }
        if (this.aq == 0) {
            this.aq = 1;
            this.T.start(this.ad);
            this.ar++;
            if (this.ar < 3) {
            }
        }
    }

    private a() {
        this.q = "unknown";
        this.r = "unknown";
        this.s = "unknown";
        this.t = "unknown";
        this.u = "unknown";
        this.v = "unknown";
        this.w = "unknown";
        this.x = "unknown";
        this.y = "unknown";
        this.z = "unknown";
        this.A = "unknown";
        this.B = "unknown";
        this.C = "unknown";
        this.D = "unknown";
        this.E = "unknown";
        this.F = "unknown";
        this.G = "unknown";
        this.H = "unknown";
        this.I = "unknown";
        this.J = "unknown";
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ah = 0;
        this.ai = 0;
        this.aj = 0;
        this.ak = 0;
        this.al = 0;
        this.am = 0;
        this.an = 0;
        this.ao = 0;
        this.ap = 0;
        this.aq = 0;
        this.ar = 0;
        this.at = null;
        this.as = new a(this);
        this.K = new DataCommonGetVersion().setDeviceType(DeviceType.FLYC);
        this.L = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA);
        this.M = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        this.P = new DataCommonGetVersion().setDeviceType(DeviceType.CENTER);
        this.O = new DataCommonGetVersion().setDeviceType(DeviceType.OSD);
        this.R = new DataCommonGetVersion().setDeviceType(DeviceType.BATTERY);
        this.N = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM);
        this.Q = new DataCommonGetVersion().setDeviceType(DeviceType.WIFI);
        this.S = new DataCommonGetVersion().setDeviceType(DeviceType.DM368);
        this.T = new DataCommonGetVersion().setDeviceType(DeviceType.DM368_G);
        z();
    }

    private void z() {
        this.U = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 1, 1, aVar).sendToTarget();
            }
        };
        this.V = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 2, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 2, 1, aVar).sendToTarget();
            }
        };
        this.W = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 3, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 3, 1, aVar).sendToTarget();
            }
        };
        this.X = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 4, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 4, 1, aVar).sendToTarget();
            }
        };
        this.Y = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 5, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 5, 1, aVar).sendToTarget();
            }
        };
        this.Z = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 6, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 6, 1, aVar).sendToTarget();
            }
        };
        this.aa = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 7, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 7, 1, aVar).sendToTarget();
            }
        };
        this.ab = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 8, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 8, 1, aVar).sendToTarget();
            }
        };
        this.ac = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 9, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 9, 1, aVar).sendToTarget();
            }
        };
        this.ad = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.as.obtainMessage(0, 10, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.as.obtainMessage(0, 10, 1, aVar).sendToTarget();
            }
        };
    }

    private String a(DataCommonGetVersion dataCommonGetVersion) {
        return dataCommonGetVersion.getFirmVerSimple(".");
    }

    private String b(DataCommonGetVersion dataCommonGetVersion) {
        return dataCommonGetVersion.getLoaderSimple(".");
    }

    private void a(int i, int i2, Object obj) {
        dji.midware.data.config.P3.a aVar;
        if (i2 == 0) {
            if (1 == i) {
                this.q = a(this.K);
                this.z = b(this.K);
                this.ah = 2;
            } else if (2 == i) {
                this.r = a(this.L);
                this.A = b(this.L);
                this.ai = 2;
            } else if (3 == i) {
                this.s = a(this.M);
                this.B = b(this.M);
                this.aj = 2;
            } else if (4 == i) {
                this.t = a(this.N);
                this.C = b(this.N);
                this.ak = 2;
            } else if (5 == i) {
                this.u = a(this.O);
                this.D = b(this.O);
                this.al = 2;
            } else if (6 == i) {
                this.v = a(this.P);
                this.E = b(this.P);
                this.am = 2;
            } else if (7 == i) {
                this.w = a(this.Q);
                this.F = b(this.Q);
                this.an = 2;
            } else if (8 == i) {
                this.x = a(this.R);
                this.G = b(this.R);
                this.ao = 2;
            } else if (9 == i) {
                this.y = a(this.S);
                this.H = b(this.S);
                this.ap = 2;
            } else if (10 == i) {
                this.I = a(this.T);
                this.J = b(this.T);
                this.aq = 2;
            }
        }
        this.ar--;
        if (this.ar == 0) {
            y();
        }
        DJILogHelper.getInstance().LOGD("", "type:" + i + ";result[" + i2 + ";code[" + (obj instanceof dji.midware.data.config.P3.a ? ((dji.midware.data.config.P3.a) obj).toString() : null) + i.b, true, true);
        if (obj instanceof dji.midware.data.config.P3.a) {
            aVar = (dji.midware.data.config.P3.a) obj;
        } else {
            aVar = null;
        }
        a(i, i2, aVar);
    }

    private void a(int i, int i2, dji.midware.data.config.P3.a aVar) {
        if (this.at != null) {
            this.at.a(i, i2, aVar);
        }
    }

    private void a(int i) {
        if (this.at != null) {
            this.at.a(i);
        }
    }
}
