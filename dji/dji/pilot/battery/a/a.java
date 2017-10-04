package dji.pilot.battery.a;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;
import dji.midware.data.model.P3.DataCenterGetSelfDischarge;
import dji.midware.data.model.P3.DataCenterSelfDischarge;
import dji.midware.data.model.P3.DataCenterSetBatteryCommon;
import dji.midware.data.model.P3.DataCenterSetSelfDischarge;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetVoltageWarnning.WarnningLevel;
import dji.midware.data.model.P3.DataFlycSetLVoltageWarnning;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.BatteryType;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataSmartBatteryGetSetSelfDischargeDays;
import dji.midware.data.model.P3.DataSmartBatteryGetStaticData;
import dji.midware.e.d;
import dji.pilot.publics.objects.g;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Iterator;

public class a {
    private static final int[] A = new int[]{1, 10};
    private static final String B = "key_show_voltage";
    private static final String C = "key_show_voltage_inspire1_first_connect";
    public static final int a = 200;
    public static final int b = -1;
    public static final int c = 0;
    public static final int d = 1;
    private static final float e = 273.15f;
    private static final int f = 4096;
    private static final int g = 4097;
    private static final int h = 4098;
    private static final int i = 4099;
    private static final int j = 4100;
    private static final int k = 4101;
    private static final int l = 4102;
    private static final int m = 4103;
    private static final int n = 4104;
    private static final int o = 4105;
    private static final int p = 4106;
    private static final int q = 4107;
    private static final int r = 4108;
    private static final int s = 4109;
    private static final long t = 9500;
    private static final int u = 0;
    private static final int[] v = new int[]{15, 50};
    private static final int w = 10;
    private static final float[] x = new float[]{3.0f, 4.35f};
    private static final float y = 3.62f;
    private static final float z = 3.5f;
    private final ArrayList<c> D;
    private final a E;
    private final DataCenterGetPushBatteryCommon F;
    private final DataCenterSetBatteryCommon G;
    private final DataFlycSetLVoltageWarnning H;
    private final DataFlycSetLVoltageWarnning I;
    private final d J;
    private final d K;
    private final d L;
    private int M;
    private final DataCenterSelfDischarge N;
    private final DataCenterSelfDischarge O;
    private final d P;
    private final d Q;
    private DataCenterGetSelfDischarge R;
    private DataCenterSetSelfDischarge S;
    private final b T;
    private ConnStatus U;
    private int V;
    private boolean W;
    private int X;
    private boolean Y;
    private int Z;
    private int aA;
    private int aB;
    private float aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private int ag;
    private int ah;
    private float ai;
    private String aj;
    private String ak;
    private final d[] al;
    private int am;
    private float an;
    private int ao;
    private boolean ap;
    private boolean aq;
    private Context ar;
    private int as;
    private boolean at;
    private int au;
    private boolean av;
    private int aw;
    private final c ax;
    private boolean ay;
    private boolean az;

    public static a getInstance() {
        return f.a;
    }

    public void a(Context context) {
        if (this.ar == null) {
            this.ar = context;
            this.ap = g.b(context, dji.pilot.publics.e.a.a(B), this.ap);
            this.aq = g.b(this.ar, dji.pilot.publics.e.a.a(C), false);
        }
    }

    public boolean a() {
        if (i.getInstance().c() == ProductType.Orange && !this.aq) {
            this.aq = true;
            g.a(this.ar, dji.pilot.publics.e.a.a(C), this.aq);
            a(true);
        }
        return this.ap;
    }

    public void a(boolean z) {
        if (this.ap != z) {
            this.ap = z;
            g.a(this.ar, dji.pilot.publics.e.a.a(B), z);
            if (z) {
                c.a().e(e.a);
            } else {
                c.a().e(e.b);
            }
        }
    }

    public void b() {
        int lowWarning;
        int seriousLowWarning;
        if (DataFlycGetPushSmartBattery.getInstance().isGetted()) {
            lowWarning = DataFlycGetPushSmartBattery.getInstance().getLowWarning();
            seriousLowWarning = DataFlycGetPushSmartBattery.getInstance().getSeriousLowWarning();
        } else {
            lowWarning = 35;
            seriousLowWarning = 20;
        }
        if (lowWarning != this.V) {
            boolean z;
            this.V = lowWarning;
            if (this.as == lowWarning) {
                this.as = 0;
                z = false;
            } else {
                z = true;
            }
            a(0, this.V, z);
        }
        if (seriousLowWarning != this.X) {
            boolean z2;
            this.X = seriousLowWarning;
            if (this.au == seriousLowWarning) {
                this.au = 0;
                z2 = false;
            } else {
                z2 = true;
            }
            a(1, this.X, z2);
        }
        if (DataOsdGetPushCommon.getInstance().getBatteryType() == BatteryType.NonSmart) {
            this.an = (((float) DataFlycGetPushSmartBattery.getInstance().getVoltage()) * 1.0f) / 1000.0f;
            this.ao = DataFlycGetPushSmartBattery.getInstance().getVoltagePercent();
            i(0);
        }
    }

    public void c() {
        ProductType c = i.getInstance().c();
        if (dji.pilot.publics.e.a.b()) {
            DataSmartBatteryGetSetSelfDischargeDays.getInstance().setType(false).setIndex(0).start(this.P);
        } else if (dji.pilot.publics.e.a.b(c)) {
            this.R = new DataCenterGetSelfDischarge();
            this.R.setEncrypt(0);
            this.R.start(new 1(this));
        } else {
            this.N.start(this.P);
        }
    }

    public void d() {
        this.V = 35;
        this.X = 20;
        this.am = 7;
    }

    public synchronized void b(boolean z) {
        this.ay = z;
        if (!z) {
            b();
            c();
        }
        if (!dji.pilot.publics.e.a.b()) {
            if (this.F.isGetted()) {
                G();
            }
        }
        if (!this.az) {
            this.az = true;
            this.G.a(1).start(this.J);
            c.a().a((Object) this);
        }
    }

    public synchronized void e() {
        this.az = false;
        this.E.removeMessages(4096);
        this.E.removeMessages(4097);
        c.a().d((Object) this);
    }

    public void f() {
        this.U = ConnStatus.EXCEPTION;
        this.ab = 0;
        this.aa = 0.0f;
        this.ac = 0;
        this.ad = 100;
        this.ae = 0;
        this.af = 0;
        this.Z = 0;
        this.ag = 0;
        this.ah = 0;
        this.ai = 0.0f;
        this.aj = "N/A";
        this.ak = "N/A";
        a(this.al);
        this.M = 0;
        i(this.ab);
        this.ax.b(0);
        F();
    }

    private void F() {
        this.an = 0.0f;
        this.ao = 0;
    }

    public boolean a(c cVar) {
        boolean z = false;
        if (cVar != null) {
            synchronized (this.D) {
                if (!this.D.contains(cVar)) {
                    this.D.add(cVar);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean b(c cVar) {
        boolean z = false;
        if (cVar != null) {
            synchronized (this.D) {
                z = this.D.remove(cVar);
            }
        }
        return z;
    }

    public int g() {
        return this.V;
    }

    public boolean h() {
        return this.W;
    }

    public int i() {
        return this.X;
    }

    public boolean j() {
        return this.Y;
    }

    public void a(int i, boolean z) {
        if (this.W != z || this.V != i) {
            this.as = i;
            this.at = z;
            this.H.a(WarnningLevel.First);
            this.H.a(i);
            this.H.a(z);
            this.H.start(this.K);
        }
    }

    public void b(int i, boolean z) {
        if (this.Y != z || this.X != i) {
            this.au = i;
            this.av = z;
            this.I.a(WarnningLevel.Second);
            this.I.a(i);
            this.I.b(z);
            this.I.start(this.L);
        }
    }

    public int k() {
        return this.am - A[0];
    }

    public final String[] a(Context context, int i) {
        String[] strArr = new String[A[1]];
        for (int i2 = A[0]; i2 <= A[1]; i2++) {
            strArr[i2 - A[0]] = context.getString(i, new Object[]{Integer.valueOf(i2)});
        }
        return strArr;
    }

    public void a(int i) {
        DJILogHelper.getInstance().LOGD("", "setself day[" + i + dji.pilot.usercenter.protocol.d.H, false, true);
        int i2 = A[0] + i;
        if (this.am != i2) {
            this.aw = i2;
            ProductType c = i.getInstance().c();
            if (dji.pilot.publics.e.a.b()) {
                DataSmartBatteryGetSetSelfDischargeDays.getInstance().setDays(i2).setType(true).setIndex(0).start(this.Q);
            } else if (dji.pilot.publics.e.a.b(c)) {
                this.S = new DataCenterSetSelfDischarge();
                this.S.a(0);
                this.S.b(i2).start(new 2(this));
            } else {
                this.O.setDays(i2).start(this.Q);
            }
        }
    }

    public ConnStatus l() {
        return this.U;
    }

    public int m() {
        return this.Z;
    }

    public float n() {
        return this.aa;
    }

    public int o() {
        return this.ab;
    }

    public int p() {
        return this.ac;
    }

    public int q() {
        return this.ad;
    }

    public int r() {
        return this.ae;
    }

    public int s() {
        return this.af;
    }

    public c t() {
        return this.ax;
    }

    public int u() {
        return this.ag;
    }

    public int v() {
        return this.ah;
    }

    public float w() {
        return this.ai;
    }

    public String x() {
        return this.aj;
    }

    public String y() {
        return this.ak;
    }

    public d[] z() {
        return this.al;
    }

    public float A() {
        return this.an;
    }

    public int B() {
        return this.ao;
    }

    private void f(int i) {
        this.ab = i;
    }

    private void d(float f) {
        this.aa = f;
    }

    private void g(int i) {
        this.ag = i;
    }

    private void h(int i) {
        if (this.af != i) {
            this.T.a(i);
        }
        this.af = i;
    }

    private int a(int i, int i2, int i3, int i4) {
        if (i4 == 0) {
            return 0;
        }
        if (i3 > i4) {
            i3 = i4;
        }
        if (i > i2) {
            i = i2;
        }
        return (((i2 - i) * i3) / i4) + i;
    }

    private int b(int i, int i2, int i3, int i4) {
        if (i >= i2) {
            return 0;
        }
        if (i3 > i2) {
            i3 = i2;
        }
        return ((i3 - i) * i4) / (i2 - i);
    }

    public int a(int i, int i2) {
        return a(v[0], v[1], i, i2);
    }

    public int b(int i, int i2) {
        return a(10, this.V, i, i2);
    }

    public int b(int i) {
        return b(v[0], v[1], this.V, i);
    }

    public int c(int i) {
        return b(10, this.V, this.X, i);
    }

    private int a(float f, int i) {
        if (f >= x[1]) {
            return 100;
        }
        if (f > x[0]) {
            return (int) (((f - x[0]) * ((float) i)) / (x[1] - x[0]));
        }
        return 0;
    }

    private a() {
        this.M = 0;
        this.T = b.getInstance();
        this.U = ConnStatus.EXCEPTION;
        this.V = 35;
        this.W = true;
        this.X = 20;
        this.Y = true;
        this.Z = 0;
        this.aa = 0.0f;
        this.ab = 0;
        this.ac = 0;
        this.ad = 100;
        this.ae = 0;
        this.af = 0;
        this.ag = 0;
        this.ah = 0;
        this.ai = 0.0f;
        this.aj = "N/A";
        this.ak = "N/A";
        this.al = new d[6];
        this.am = 7;
        this.an = 0.0f;
        this.ao = 0;
        this.ap = false;
        this.aq = false;
        this.ar = null;
        this.as = 0;
        this.at = false;
        this.au = 0;
        this.av = false;
        this.aw = 0;
        this.ax = new c();
        this.ay = false;
        this.az = false;
        this.aA = 0;
        this.aB = 3;
        this.D = new ArrayList(4);
        this.E = new a(this);
        this.F = DataCenterGetPushBatteryCommon.getInstance();
        this.G = DataCenterSetBatteryCommon.getInstance();
        this.H = new DataFlycSetLVoltageWarnning();
        this.I = new DataFlycSetLVoltageWarnning();
        this.N = new DataCenterSelfDischarge();
        this.N.setFlag(true);
        this.O = new DataCenterSelfDischarge();
        this.O.setFlag(false);
        this.P = new 3(this);
        this.Q = new 4(this);
        this.J = new 5(this);
        this.K = new 6(this);
        this.L = new 7(this);
        for (int i = 0; i < this.al.length; i++) {
            this.al[i] = new d();
            this.al[i].c = 0;
            this.al[i].d = false;
            this.al[i].b = 50;
            this.al[i].a = z;
        }
    }

    private void G() {
        this.U = this.F.isGetted() ? this.F.getConnStatus() : ConnStatus.EXCEPTION;
        this.ai = (((float) this.F.getTemperature()) / 10.0f) - 273.15f;
        int currentCapacity = this.F.getCurrentCapacity();
        if (this.ay) {
            this.ax.b(this.F.getErrorType());
        } else {
            int errorType = this.F.getErrorType();
            f(currentCapacity);
            d(((float) this.F.getCurrentPV()) / 1000.0f);
            h(errorType);
            g(Math.abs(this.F.getCurrent()));
            this.T.a(this.U);
            this.ac = this.F.getFullCapacity();
            this.ad = this.F.getLife();
            this.ae = this.F.getLoopNum();
            this.Z = this.F.getRelativeCapacity();
            this.aj = String.format("%06d", new Object[]{Integer.valueOf(this.F.getSerialNo())});
            this.ax.a(errorType);
            int[] partVoltages = this.F.getPartVoltages();
            for (errorType = 0; errorType < this.al.length; errorType++) {
                this.al[errorType].a = ((float) partVoltages[errorType]) / 1000.0f;
                this.al[errorType].b = a(this.al[errorType].a, 100);
                this.al[errorType].d = this.ax.l() == errorType + 1;
                if (this.ax.k() == errorType + 1) {
                    this.al[errorType].c = 2;
                } else {
                    this.al[errorType].c = c(this.al[errorType].a);
                }
            }
            this.ah = (int) (((float) DataOsdGetPushCommon.getInstance().getFlyTime()) * 0.1f);
            int[] productDate = this.F.getProductDate();
            this.ak = String.format("%1$d-%2$02d-%3$02d", new Object[]{Integer.valueOf(productDate[0]), Integer.valueOf(productDate[1]), Integer.valueOf(productDate[2])});
        }
        i(currentCapacity);
    }

    private void H() {
        DataSmartBatteryGetPushDynamicData instance = DataSmartBatteryGetPushDynamicData.getInstance();
        this.U = instance.isGetted() ? ConnStatus.ofData((int) instance.getStatus()) : ConnStatus.EXCEPTION;
        if (instance.getIndex() == 0) {
            this.ai = ((float) instance.getTemperature()) / 10.0f;
            this.ah = (int) (((float) DataOsdGetPushCommon.getInstance().getFlyTime()) * 0.1f);
            this.ac = instance.getFullCapacity();
            this.Z = instance.getRelativeCapacityPercentage();
            int remainCapacity = instance.getRemainCapacity();
            f(remainCapacity);
            d(((float) instance.getVoltage()) / 1000.0f);
            g(Math.abs(instance.getCurrent()));
            this.T.a(this.U);
            DataSmartBatteryGetStaticData dataSmartBatteryGetStaticData = new DataSmartBatteryGetStaticData();
            dataSmartBatteryGetStaticData.setIndex(0).start(new 8(this, dataSmartBatteryGetStaticData));
            i(remainCapacity);
        }
    }

    public static int C() {
        ProductType c = i.getInstance().c();
        if (c == ProductType.KumquatX) {
            return 3;
        }
        if (dji.pilot.publics.e.a.b(c)) {
            return 4;
        }
        return 6;
    }

    public static int a(float f) {
        int C = C();
        float f2 = ((float) C) * y;
        if (f < z * ((float) C)) {
            return 2;
        }
        if (f < f2) {
            return 1;
        }
        return 0;
    }

    public static int b(float f) {
        if (f < d.getInstance().d()) {
            return 2;
        }
        if (f < d.getInstance().c()) {
            return 1;
        }
        return 0;
    }

    public static int c(float f) {
        if (f < z) {
            return 2;
        }
        if (f < y) {
            return 1;
        }
        return 0;
    }

    private void a(d[] dVarArr) {
        if (dVarArr != null && dVarArr.length > 0) {
            for (d dVar : dVarArr) {
                dVar.a = z;
                dVar.d = false;
                dVar.c = 0;
                dVar.b = 50;
            }
        }
    }

    private void I() {
        this.G.start(this.J);
        this.E.sendEmptyMessageDelayed(4097, t);
    }

    private void c(int i, int i2) {
    }

    private void d(int i, int i2) {
        d(i, true);
    }

    private void e(int i, int i2) {
        if (i == 0 || 1 != i) {
        }
    }

    private void f(int i, int i2) {
        d(i, false);
    }

    private void c(boolean z) {
        if (z) {
            if (dji.pilot.publics.e.a.b()) {
                this.am = DataSmartBatteryGetSetSelfDischargeDays.getInstance().getDays();
                if (this.am < A[0] || this.am > A[1]) {
                    this.am = 7;
                }
            } else {
                this.am = this.N.getDay();
                if (this.am < A[0] || this.am > A[1]) {
                    this.am = 7;
                }
            }
            c(this.am, true);
            return;
        }
        g(true);
    }

    private void d(boolean z) {
        DJILogHelper.getInstance().LOGD("", "getself[" + z + "]day[" + this.R.getDay() + dji.pilot.usercenter.protocol.d.H, false, true);
        if (z) {
            this.am = this.R.getDay();
            if (this.am < A[0] || this.am > A[1]) {
                this.am = 7;
            }
            c(this.am, true);
            return;
        }
        g(true);
    }

    private void e(boolean z) {
        if (z) {
            this.am = this.aw;
            c(this.am, false);
            return;
        }
        g(false);
    }

    private void f(boolean z) {
        DJILogHelper.getInstance().LOGD("", "setself[" + z + "]day[" + this.aw + dji.pilot.usercenter.protocol.d.H, false, true);
        if (z) {
            this.am = this.aw;
            c(this.am, false);
            return;
        }
        g(false);
    }

    private void c(int i, boolean z) {
        synchronized (this.D) {
            Iterator it = this.D.iterator();
            while (it.hasNext()) {
                ((c) it.next()).b(i - A[0], z);
            }
        }
    }

    private void g(boolean z) {
        synchronized (this.D) {
            Iterator it = this.D.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(z);
            }
        }
    }

    private void i(int i) {
        synchronized (this.D) {
            Iterator it = this.D.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(i);
            }
        }
    }

    private void a(int i, int i2, boolean z) {
        synchronized (this.D) {
            Iterator it = this.D.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(i, i2, z);
            }
        }
    }

    private void d(int i, boolean z) {
        synchronized (this.D) {
            Iterator it = this.D.iterator();
            while (it.hasNext()) {
                ((c) it.next()).a(i, z);
            }
        }
    }

    public void onEventBackgroundThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        if (!dji.pilot.publics.e.a.b()) {
            this.E.sendEmptyMessage(4096);
        }
    }

    public void onEventBackgroundThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        if (dji.pilot.publics.e.a.b()) {
            this.E.sendEmptyMessage(4096);
        }
    }

    public void onEventBackgroundThread(DataSmartBatteryGetPushCellVoltage dataSmartBatteryGetPushCellVoltage) {
        int[] voltages = dataSmartBatteryGetPushCellVoltage.getVoltages();
        int i = 0;
        while (i < voltages.length && i < this.al.length) {
            boolean z;
            this.al[i].a = ((float) voltages[i]) / 1000.0f;
            this.al[i].b = a(this.al[i].a, 100);
            d dVar = this.al[i];
            if (this.ax.l() == i + 1) {
                z = true;
            } else {
                z = false;
            }
            dVar.d = z;
            if (this.ax.k() == i + 1) {
                this.al[i].c = 2;
            } else {
                this.al[i].c = c(this.al[i].a);
            }
            i++;
        }
    }

    public void onEventMainThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        b();
    }

    public void onEventMainThread(ProductType productType) {
        if (productType == ProductType.Orange && !this.aq) {
            this.aq = true;
            g.a(this.ar, dji.pilot.publics.e.a.a(C), this.aq);
            a(true);
        }
    }

    public void onEventMainThread(p pVar) {
        if (p.ConnectOK == pVar) {
            this.G.a(1).start(this.J);
        } else if (p.ConnectLose == pVar) {
            f();
        }
    }

    public void onEventMainThread(o oVar) {
        if (o.ConnectOK == oVar) {
            this.G.a(1).start(this.J);
        } else if (o.ConnectLose == oVar) {
            f();
        }
    }

    public int D() {
        return this.aA;
    }

    public void d(int i) {
        this.aA = i;
    }

    public void e(int i) {
        if (this.aB != i) {
            this.aB = i;
            c.a().e(b.a);
        }
    }

    public int E() {
        return this.aB;
    }
}
