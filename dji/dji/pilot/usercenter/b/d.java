package dji.pilot.usercenter.b;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.flightrecord.DJIRecordService;
import dji.pilot.fpv.control.s;
import dji.pilot.usercenter.mode.FlightOverviewInfo;
import dji.pilot.usercenter.mode.m;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot.usercenter.protocol.e$b;
import dji.thirdparty.gson.reflect.TypeToken;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.InflaterInputStream;

public class d implements dji.pilot.fpv.d.c.h, dji.pilot.usercenter.protocol.c {
    public static final int a = 1;
    private static final String aU = "key_flightrecord_cache";
    private static final String aV = "key_sync_only_wifi";
    private static final int aW = 2592000;
    private static final int aX = 15552000;
    private static final int aY = 30;
    private static final int aZ = 68;
    private static final int ba = 100;
    private static final int bb = 1;
    private static final boolean bc = true;
    private static boolean bd = false;
    private static final boolean be = true;
    private static final boolean bf = false;
    private static final int bg = 4096;
    private static final int bh = 4097;
    private static final int bi = 4098;
    private static final int bj = 8192;
    private static final long bk = 100;
    private static final long bl = 30;
    private static final String bm = d.class.getSimpleName();
    private static final String bn = (bm + "_OVDatas");
    private static final int bo = 425;
    private static final String bp = "_djipilot";
    private static final String bq = ".FlightDelete-";
    private static final String br = ".tmp";
    private static final String bs = ".Overview-";
    private static final String bt = ".tmp";
    private static final String bu = "yyyy-MM-dd_[HH-mm-ss]";
    private static final String bv = ".txt";
    private static final String bw = "info.txt";
    public static final int n = 2;
    public static final int o = 3;
    public static final String p = "FlightRecord/";
    public static final String q = "FlightRecord/Upload/";
    public static final String r = "FlightRecord/Download/";
    public static final String s = "FlightRecord/Info/";
    public static final String t = "userinfo.json";
    private e$a bA;
    private f bB;
    private final ArrayList<e$a> bC;
    private Context bD;
    private boolean bE;
    private final f bF;
    private final c bG;
    private final s bH;
    private c bI;
    private d bJ;
    private List<dji.pilot.fpv.model.f> bK;
    private volatile int bL;
    private FilenameFilter bM;
    private final List<a> bN;
    private boolean bO;
    private dji.pilot.usercenter.b.c.d bP;
    private String bQ;
    private String bR;
    private String bS;
    private String bT;
    private final ArrayList<m> bU;
    private final ArrayList<dji.pilot.fpv.model.f> bV;
    private final ArrayList<dji.pilot.fpv.model.f> bW;
    private final ArrayList<m> bX;
    private volatile boolean bY;
    private int bZ;
    private e$a bx;
    private e$a by;
    private e$a bz;
    private int ca;
    private int cb;
    private int cc;
    private int cd;
    private dji.pilot.fpv.model.f ce;
    private int cf;
    private boolean cg;
    private dji.pilot.fpv.model.f ch;
    private boolean ci;
    private byte[] cj;
    private byte[] ck;
    private LocationManager cl;
    private volatile boolean cm;
    private List<FlightOverviewInfo> cn;
    private String co;
    private final h cp;
    private final ArrayList<dji.pilot.fpv.model.f> cq;
    private int cr;
    private int cs;
    private int ct;
    private List<a> cu;
    private int cv;

    public static final class a implements Comparable {
        public FlightOverviewInfo a;
        public final ArrayList<dji.pilot.fpv.model.f> b;

        public a(boolean z) {
            this.a = null;
            this.b = new ArrayList();
            this.a = new FlightOverviewInfo();
        }

        public int compareTo(Object obj) {
            if (!(obj instanceof a)) {
                return 1;
            }
            a aVar = (a) obj;
            if (this.a == null || aVar.a == null) {
                return 0;
            }
            boolean a = d.a(this.a.mBoardNum, true);
            boolean a2 = d.a(aVar.a.mBoardNum, true);
            if (a) {
                return 1;
            }
            if (a2) {
                return -1;
            }
            if (d.a(this.a.mBoardNum)) {
                return -1;
            }
            if (d.a(aVar.a.mBoardNum)) {
                return 1;
            }
            return this.a.mTotalFlightTime - aVar.a.mTotalFlightTime;
        }
    }

    public static final class b {
        public boolean a = false;
    }

    private static final class c extends Handler {
        private final WeakReference<d> a;

        public c(Looper looper, d dVar) {
            super(looper);
            this.a = new WeakReference(dVar);
        }

        public void handleMessage(Message message) {
            d dVar = (d) this.a.get();
            if (dVar != null) {
                switch (message.what) {
                    case 4096:
                        dVar.bL = dji.pilot.usercenter.f.c.a(dVar.bQ, dVar.bM);
                        dVar.bB.obtainMessage(4096, message.arg1, 0, dji.pilot.fpv.model.i.b(dVar.bD)).sendToTarget();
                        return;
                    case 4097:
                        if (message.obj instanceof dji.pilot.fpv.model.f) {
                            dVar.i((dji.pilot.fpv.model.f) message.obj);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class d extends HandlerThread {
        public d(String str) {
            super(str);
        }
    }

    public static final class e {
        public dji.pilot.fpv.model.f a = null;
        public float b = 0.0f;
    }

    private static final class f extends Handler {
        private final WeakReference<d> a;

        public f(d dVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dVar);
        }

        public void handleMessage(Message message) {
            d dVar = (d) this.a.get();
            if (dVar != null) {
                switch (message.what) {
                    case 4096:
                        dVar.a(message.obj, message.arg1);
                        dVar.E();
                        return;
                    case 4098:
                        dVar.a((dji.pilot.fpv.model.f) message.obj, true);
                        return;
                    case 8192:
                        dVar.bZ = dVar.bZ + 1;
                        if (message.arg1 == 0) {
                            if (dVar.bZ > 30) {
                                dVar.bZ = 30;
                            } else {
                                sendMessageDelayed(obtainMessage(8192, message.arg1, 0), d.bk);
                            }
                        } else if (message.arg1 == 1) {
                            if (dVar.bZ > 100) {
                                dVar.bZ = 100;
                            } else {
                                sendMessageDelayed(obtainMessage(8192, 1, 0), d.bl);
                            }
                        }
                        dVar.a(dVar.bZ, 100);
                        return;
                    case 65536:
                        dVar.a(message.arg1, message.arg2, message.obj);
                        return;
                    case 65537:
                        dVar.b(message.arg1, message.arg2, message.obj);
                        return;
                    case dji.pilot.usercenter.protocol.e.aC /*65538*/:
                        dVar.d(message.arg1, message.arg2, message.obj);
                        return;
                    case dji.pilot.usercenter.protocol.e.aD /*65539*/:
                        dVar.c(message.arg1, message.arg2, message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class g {
        public static d a = new d();

        private g() {
        }
    }

    public static final class h {
        public int a = 0;
        public int b = 0;
        public int c = 0;
        public int d = 0;
        public int e = 0;
        public int f = 0;
        public int g = 0;
    }

    public enum i {
        Start,
        Stop
    }

    public static d getInstance() {
        return g.a;
    }

    public synchronized boolean a(Context context) {
        if (!this.bE) {
            this.bD = context.getApplicationContext();
            this.bQ = com.dji.frame.c.d.a(this.bD, p);
            this.cd = dji.pilot.publics.objects.g.b(this.bD, aU, 3);
            this.ci = dji.pilot.publics.objects.g.b(this.bD, aV, false);
            this.bM = new FilenameFilter(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public boolean accept(File file, String str) {
                    if (str.charAt(0) == '.') {
                        return false;
                    }
                    return true;
                }
            };
            this.bJ = new d(bm);
            this.bJ.start();
            this.bI = new c(this.bJ.getLooper(), this);
            this.bE = true;
        }
        return this.bE;
    }

    public synchronized boolean a() {
        if (this.bE) {
        }
        return !this.bE;
    }

    public boolean a(e$a dji_pilot_usercenter_protocol_e_a) {
        boolean z = false;
        if (dji_pilot_usercenter_protocol_e_a != null) {
            synchronized (this.bC) {
                if (!this.bC.contains(dji_pilot_usercenter_protocol_e_a)) {
                    this.bC.add(0, dji_pilot_usercenter_protocol_e_a);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean b(e$a dji_pilot_usercenter_protocol_e_a) {
        boolean z = false;
        if (dji_pilot_usercenter_protocol_e_a != null) {
            synchronized (this.bC) {
                z = this.bC.remove(dji_pilot_usercenter_protocol_e_a);
            }
        }
        return z;
    }

    public boolean c(e$a dji_pilot_usercenter_protocol_e_a) {
        this.bA = dji_pilot_usercenter_protocol_e_a;
        return true;
    }

    public boolean b() {
        this.bA = null;
        return true;
    }

    public int c() {
        return this.cd;
    }

    public void a(int i) {
        if (this.cd != i) {
            this.cd = i;
            dji.pilot.publics.objects.g.a(this.bD, aU, i);
        }
    }

    public boolean d() {
        return this.ci;
    }

    public void a(boolean z) {
        if (this.ci != z) {
            this.ci = z;
            dji.pilot.publics.objects.g.a(this.bD, aV, z);
        }
    }

    public void e() {
        if (!this.bN.isEmpty()) {
            a aVar = (a) this.bN.get(0);
            if (dji.pilot.usercenter.protocol.c.T.equals(aVar.a.mBoardNum)) {
                aVar.a.mAircraftName = this.bF.m();
            }
        }
    }

    public void f() {
        int size = this.bK.size();
        int i = 0;
        while (i < size) {
            dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) this.bK.get(i);
            if (fVar.x != (byte) 1) {
                this.bK.remove(i);
                size--;
                i--;
                dji.pilot.fpv.model.i.a(this.bD, fVar);
            }
            i++;
            size = size;
        }
        int size2 = this.bN.size();
        int i2 = 0;
        while (i2 < size2) {
            int i3;
            a aVar = (a) this.bN.get(i2);
            i = aVar.b.size();
            int i4 = 0;
            while (i4 < i) {
                dji.pilot.fpv.model.f fVar2 = (dji.pilot.fpv.model.f) aVar.b.get(i4);
                if (fVar2.x != (byte) 1) {
                    aVar.b.remove(i4);
                    int i5 = i - 1;
                    i4--;
                    if (fVar2.a() == 1) {
                        FlightOverviewInfo flightOverviewInfo = aVar.a;
                        flightOverviewInfo.mTotalDistance -= (double) fVar2.F;
                        FlightOverviewInfo flightOverviewInfo2 = aVar.a;
                        if (aVar.a.mTotalFlightTime > 0) {
                            i = aVar.a.mTotalFlightTime - 1;
                        } else {
                            i = 0;
                        }
                        flightOverviewInfo2.mTotalFlightTime = i;
                        flightOverviewInfo = aVar.a;
                        flightOverviewInfo.mTotalTime -= (long) fVar2.G;
                    }
                    size = i5;
                    i = i4;
                } else {
                    size = i;
                    i = i4;
                }
                i4 = i + 1;
                i = size;
            }
            if (aVar.a.mTotalFlightTime <= 0) {
                this.bN.remove(i2);
                i3 = size2 - 1;
                size = i2 - 1;
            } else {
                i3 = size2;
                size = i2;
            }
            i2 = size + 1;
            size2 = i3;
        }
        e$a I = I();
        if (I != null) {
            I.a(1048584, 0, 0, null, null);
        }
    }

    public void g() {
        int size = this.bK.size();
        while (true) {
            int i = size - 1;
            if (size <= 0) {
                break;
            }
            dji.pilot.fpv.model.i.a(this.bD, (dji.pilot.fpv.model.f) this.bK.remove(0));
            size = i;
        }
        int size2 = this.bN.size();
        int i2 = 0;
        while (i2 < size2) {
            a aVar = (a) this.bN.get(i2);
            i = aVar.b.size();
            while (true) {
                int i3 = i - 1;
                if (i <= 0) {
                    break;
                }
                dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) aVar.b.remove(0);
                if (fVar.a() == 1) {
                    int i4;
                    FlightOverviewInfo flightOverviewInfo = aVar.a;
                    flightOverviewInfo.mTotalDistance -= (double) fVar.F;
                    FlightOverviewInfo flightOverviewInfo2 = aVar.a;
                    if (aVar.a.mTotalFlightTime > 0) {
                        i4 = aVar.a.mTotalFlightTime - 1;
                    } else {
                        i4 = 0;
                    }
                    flightOverviewInfo2.mTotalFlightTime = i4;
                    flightOverviewInfo = aVar.a;
                    flightOverviewInfo.mTotalTime -= (long) fVar.G;
                }
                i = i3;
            }
            if (aVar.a.mTotalFlightTime <= 0) {
                this.bN.remove(i2);
                size = size2 - 1;
                i = i2 - 1;
            } else {
                size = size2;
                i = i2;
            }
            i2 = i + 1;
            size2 = size;
        }
        e$a I = I();
        if (I != null) {
            I.a(1048584, 0, 0, null, null);
        }
    }

    private void u() {
        String v = v();
        this.cn.clear();
        v = dji.pilot.usercenter.f.c.g(v);
        if (v != null && v.trim().length() > 0) {
            Collection a = com.dji.frame.c.h.a(dji.pilot.usercenter.f.e.b(v), new TypeToken<List<FlightOverviewInfo>>(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }
            });
            if (a != null && !a.isEmpty()) {
                this.cn.addAll(a);
            }
        }
    }

    private String v() {
        String e = e(bs + this.bF.l() + dji.pilot.fpv.control.f.b);
        if (dji.pilot.usercenter.f.c.b(e)) {
            return e;
        }
        return e(bs + this.bF.j() + dji.pilot.fpv.control.f.b);
    }

    private void w() {
        if (this.cn == null || this.cn.isEmpty()) {
            dji.pilot.usercenter.f.c.e(v());
            return;
        }
        dji.pilot.usercenter.f.c.a(v(), dji.pilot.usercenter.f.e.a(com.dji.frame.c.h.a(this.cn)), false);
    }

    public boolean h() {
        return this.bY;
    }

    public int i() {
        return this.bZ;
    }

    public boolean j() {
        int a = dji.pilot.usercenter.f.c.a(this.bQ, this.bM);
        if (dji.pilot.publics.objects.g.b(this.bD, "fly_recordnum", 0) == a) {
            return false;
        }
        dji.pilot.publics.objects.g.a(this.bD, "fly_recordnum", a);
        return true;
    }

    public List<a> a(boolean z, b bVar) {
        String str = this.co;
        this.co = this.bF.j();
        if (!this.bY && this.bE) {
            if (this.bK == null) {
                u();
                this.bK = new ArrayList();
                bVar.a = true;
                this.bI.sendEmptyMessage(4096);
            } else if (z && (dji.pilot.usercenter.f.c.a(this.bQ, this.bM) != this.bL || !this.co.equals(str) || this.bH.c() || DJIRecordService.b)) {
                DJILogHelper.getInstance().LOGD("", "飞行记录已刷新", false, true);
                this.bH.b();
                DJIRecordService.b = false;
                bVar.a = true;
                u();
                this.bI.sendEmptyMessage(4096);
            }
        }
        return this.bN;
    }

    public void k() {
        if (this.bK != null) {
            this.bK.clear();
            this.bK = null;
        }
        e(false);
        this.bL = 0;
        this.cn.clear();
        c(true);
    }

    public void a(dji.pilot.fpv.model.f fVar, float f) {
        if (fVar != null) {
            if (fVar.a() == 1) {
                String str = a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
                int size = this.bN.size();
                for (int i = 0; i < size; i++) {
                    a aVar = (a) this.bN.get(i);
                    if (str.equals(aVar.a.mBoardNum) || dji.pilot.usercenter.protocol.c.T.equals(aVar.a.mBoardNum)) {
                        FlightOverviewInfo flightOverviewInfo = aVar.a;
                        flightOverviewInfo.mTotalDistance += (double) (fVar.F - f);
                    }
                }
            }
            e$a I = I();
            if (I != null) {
                Object eVar = new e();
                eVar.b = f;
                eVar.a = fVar;
                I.a((int) dji.pilot.usercenter.protocol.c.m, 0, 0, 0, eVar);
            }
        }
    }

    public void a(dji.pilot.fpv.model.f fVar, boolean z) {
        if (fVar != null) {
            if (z) {
                String str = a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
                int size = this.bN.size();
                for (int i = 0; i < size; i++) {
                    a aVar = (a) this.bN.get(i);
                    if ((str.equals(aVar.a.mBoardNum) || dji.pilot.usercenter.protocol.c.T.equals(aVar.a.mBoardNum)) && aVar != null && aVar.a.mTimeStamp == fVar.C) {
                        aVar.a.mArea = fVar.w;
                        aVar.a.mCity = fVar.v;
                        aVar.a.mStreet = fVar.u;
                        aVar.a.mSubStreet = fVar.t;
                        aVar.a.mLongitude = fVar.D;
                        aVar.a.mLatitude = fVar.E;
                    }
                }
                e$a I = I();
                if (I != null) {
                    I.a((int) dji.pilot.usercenter.protocol.c.l, 0, 0, 0, (Object) fVar);
                    return;
                }
                return;
            }
            this.bB.obtainMessage(4098, fVar).sendToTarget();
        }
    }

    private FlightOverviewInfo d(dji.pilot.fpv.model.f fVar) {
        String str = a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
        int size = this.cn.size();
        for (int i = 0; i < size; i++) {
            FlightOverviewInfo flightOverviewInfo = (FlightOverviewInfo) this.cn.get(i);
            if (str.equals(flightOverviewInfo.mBoardNum)) {
                return flightOverviewInfo;
            }
        }
        return null;
    }

    public void a(dji.pilot.fpv.model.f fVar) {
        int i = 0;
        int size = this.bK.size();
        for (int i2 = 0; i2 < size; i2++) {
            dji.pilot.fpv.model.f fVar2 = (dji.pilot.fpv.model.f) this.bK.get(i2);
            if (fVar2 == fVar || (fVar2.r != null && fVar2.r.equals(fVar.r))) {
                this.bK.remove(i2);
                break;
            }
        }
        if (fVar.a() != 1) {
            FlightOverviewInfo d = d(fVar);
            if (d != null) {
                d.mTotalDistance -= (double) fVar.F;
                d.mTotalTime -= (long) fVar.G;
                d.mTotalFlightTime = d.mTotalFlightTime > 0 ? d.mTotalFlightTime - 1 : 0;
                w();
            }
        }
        String str = a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
        size = this.bN.size();
        while (i < size) {
            a aVar = (a) this.bN.get(i);
            if (str.equals(aVar.a.mBoardNum) || dji.pilot.usercenter.protocol.c.T.equals(aVar.a.mBoardNum)) {
                a(aVar, fVar, true);
                if (i != 0) {
                    break;
                }
            }
            i++;
        }
        dji.pilot.fpv.model.i.a(this.bD, fVar);
        if (fVar.a() != 1) {
            d(fVar.r);
        }
    }

    public void a(a aVar, dji.pilot.fpv.model.f fVar, boolean z) {
        long j = 0;
        dji.pilot.fpv.model.f fVar2 = null;
        int size = aVar.b.size();
        int i = 0;
        int i2 = -1;
        while (i < size) {
            int i3;
            dji.pilot.fpv.model.f fVar3 = (dji.pilot.fpv.model.f) aVar.b.get(i);
            if (fVar3 == fVar || (fVar3.r != null && fVar3.r.equals(fVar.r))) {
                fVar3 = fVar2;
                i3 = i;
            } else if (j < fVar3.C) {
                j = fVar3.C;
                i3 = i2;
            } else {
                fVar3 = fVar2;
                i3 = i2;
            }
            i++;
            i2 = i3;
            fVar2 = fVar3;
        }
        if (-1 != i2) {
            if (z) {
                FlightOverviewInfo flightOverviewInfo = aVar.a;
                flightOverviewInfo.mTotalTime -= (long) fVar.G;
                flightOverviewInfo = aVar.a;
                flightOverviewInfo.mTotalDistance -= (double) fVar.F;
                aVar.a.mTotalFlightTime = aVar.a.mTotalFlightTime > 0 ? aVar.a.mTotalFlightTime - 1 : 0;
            }
            aVar.b.remove(i2);
            if (fVar.C >= aVar.a.mTimeStamp && fVar2 != null) {
                aVar.a.mLongitude = fVar2.D;
                aVar.a.mLatitude = fVar2.E;
                aVar.a.mSubStreet = fVar2.t;
                aVar.a.mStreet = fVar2.u;
                aVar.a.mCity = fVar2.v;
                aVar.a.mArea = fVar2.w;
                aVar.a.mTimeStamp = fVar2.C;
            }
        }
    }

    public void b(dji.pilot.fpv.model.f fVar) {
        int i = 1;
        if (fVar.a() != 1) {
            i = 2;
        }
        fVar.a(i);
        dji.pilot.fpv.model.i.b(this.bD, fVar);
    }

    public void c(dji.pilot.fpv.model.f fVar) {
        dji.pilot.fpv.model.i.b(this.bD, fVar);
    }

    public void l() {
        e(true);
        this.bZ = 0;
        this.cm = false;
        x();
        dji.pilot.usercenter.f.c.f(this.bQ);
        List B = B();
        if (B == null || B.isEmpty()) {
            this.bB.sendMessageDelayed(this.bB.obtainMessage(8192, 0, 0), bk);
            a(this.bZ, 100);
            this.bU.clear();
            c(1);
            return;
        }
        a(this.bZ, 100);
        this.bB.sendMessageDelayed(this.bB.obtainMessage(8192, 0, 0), bk);
        a(B);
    }

    private void x() {
        this.cp.a = 0;
        this.cp.b = 0;
        this.cp.c = 0;
        this.cp.d = 0;
        this.cp.e = 0;
        this.cp.f = 0;
        this.cp.g = 0;
    }

    public boolean m() {
        return this.cp.a == -1 || this.cp.b == -1 || this.cp.d > 0 || this.cp.f > 0;
    }

    public String n() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.cp.a == -1) {
            stringBuilder.append(this.bD.getString(R.string.flight_record_sync_del_result, new Object[]{Integer.valueOf(1)}));
        }
        if (this.cp.d > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(';');
            }
            stringBuilder.append(this.bD.getString(R.string.flight_record_sync_upload_result, new Object[]{Integer.valueOf(this.cp.d)}));
        }
        if (this.cp.f > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(';');
            }
            stringBuilder.append(this.bD.getString(R.string.flight_record_sync_download_result, new Object[]{Integer.valueOf(this.cp.f)}));
        }
        return stringBuilder.toString();
    }

    private void a(List<String> list) {
        dji.pilot.usercenter.protocol.b.a(this.bD, this.bF.n(), list, bm, this.bx);
    }

    private void c(int i) {
        if (!r()) {
            dji.pilot.usercenter.protocol.b.a(this.bD, this.bF.n(), i, 48, bm, this.bx);
        }
    }

    private void e(dji.pilot.fpv.model.f fVar) {
        if (!r()) {
            String f = f(fVar);
            if (f != "") {
                DJILogHelper.getInstance().LOGD(bm, "上传压缩后的飞行记录", true, true);
                dji.pilot.usercenter.protocol.b.b(this.bD, this.bF.n(), f, fVar.r, bm, this.bx);
                return;
            }
            DJILogHelper.getInstance().LOGD(bm, "上传未压缩飞行记录", true, true);
            dji.pilot.usercenter.protocol.b.a(this.bD, this.bF.n(), fVar.q, fVar.r, bm, this.bx);
        }
    }

    private void a(byte[] bArr) {
        DJILogHelper.getInstance().LOGD("SYC", "压缩info信息", false, true);
        dji.pilot.usercenter.protocol.b.c(this.bD, this.bF.n(), b(bArr), bw, bm, this.by);
    }

    private String f(dji.pilot.fpv.model.f fVar) {
        this.bS = com.dji.frame.c.d.a(this.bD, q);
        File file = new File(this.bS);
        if (!file.exists()) {
            file.mkdir();
        }
        this.cj = null;
        this.ck = null;
        String str = "";
        try {
            this.cj = com.dji.frame.c.f.c(fVar.q);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.cj != null) {
            this.ck = com.dji.frame.c.m.a(this.cj);
            if (this.ck != null) {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(this.bS + fVar.r + ".zip"));
                    dataOutputStream.write(this.ck);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    str = this.bS + fVar.r + ".zip";
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return str;
    }

    private String b(byte[] bArr) {
        this.bT = com.dji.frame.c.d.a(this.bD, s);
        File file = new File(this.bT);
        if (!file.exists()) {
            file.mkdir();
        }
        String str = "";
        this.ck = null;
        if (bArr != null) {
            this.ck = com.dji.frame.c.m.a(bArr);
            if (this.ck != null) {
                try {
                    DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(this.bT + bw));
                    dataOutputStream.write(this.ck);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    str = this.bT + bw;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return str;
    }

    private String b(String str) {
        return String.format(dji.pilot.usercenter.protocol.c.I, new Object[]{this.bF.n(), str});
    }

    private void a(m mVar) {
        if (!r()) {
            this.bR = com.dji.frame.c.d.a(this.bD, r);
            File file = new File(this.bR);
            if (!file.exists()) {
                file.mkdir();
            }
            this.bG.a(b(mVar.a), this.bR + mVar.a, false, false, bm, this.bP);
        }
    }

    public static boolean a(InputStream inputStream, OutputStream outputStream) {
        boolean z = false;
        InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inflaterInputStream.read(bArr, 0, 1024);
                if (read <= 0) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            z = true;
            if (inflaterInputStream != null) {
                try {
                    inflaterInputStream.close();
                } catch (IOException e) {
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e2) {
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            if (inflaterInputStream != null) {
                try {
                    inflaterInputStream.close();
                } catch (IOException e4) {
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e5) {
                }
            }
        } catch (Throwable th) {
            if (inflaterInputStream != null) {
                try {
                    inflaterInputStream.close();
                } catch (IOException e6) {
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e7) {
                }
            }
        }
        return z;
    }

    private void c(String str) {
        if (new File(str).exists()) {
            DJILogHelper.getInstance().LOGD(bm, "解压缩下载的记录" + str, true, true);
            this.cj = null;
            this.ck = null;
            try {
                InputStream fileInputStream = new FileInputStream(str);
                DJILogHelper.getInstance().LOGD(bm, "movePath" + str.replace("Download/", ""), false, true);
                a(fileInputStream, new FileOutputStream(str.replace("Download/", "")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void y() {
        int i = 0;
        this.bR = com.dji.frame.c.d.a(this.bD, r);
        File file = new File(this.bR);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File delete : listFiles) {
                delete.delete();
            }
        }
        this.bS = com.dji.frame.c.d.a(this.bD, q);
        file = new File(this.bS);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles2 = file.listFiles();
            while (i < listFiles2.length) {
                listFiles2[i].delete();
                i++;
            }
        }
        DJILogHelper.getInstance().LOGD(bm, "同步完成，清空缓存文件夹", true, true);
        HashMap hashMap = new HashMap();
        hashMap.put(dji.pilot.fpv.d.c.h.bf_, "" + this.cp.c);
        hashMap.put(dji.pilot.fpv.d.c.h.bg_, "" + this.cp.d);
        hashMap.put(dji.pilot.fpv.d.c.h.cs_, "" + this.cp.e);
        hashMap.put(dji.pilot.fpv.d.c.h.ct_, "" + this.cp.f);
        dji.pilot.fpv.d.e.a(dji.pilot.fpv.d.c.h.be_, hashMap);
    }

    private void d(String str) {
        String A = A();
        dji.pilot.usercenter.f.c.d(A);
        dji.pilot.usercenter.f.c.a(A, str + "\n", true);
    }

    private void z() {
        dji.pilot.usercenter.f.c.e(A());
    }

    private String A() {
        String str = this.bQ + bq + this.bF.l() + dji.pilot.fpv.control.f.b;
        if (dji.pilot.usercenter.f.c.b(str)) {
            return str;
        }
        return this.bQ + bq + this.bF.j() + dji.pilot.fpv.control.f.b;
    }

    private List<String> B() {
        Throwable th;
        BufferedReader bufferedReader = null;
        File file = new File(A());
        if (!dji.pilot.usercenter.f.c.a(file)) {
            return null;
        }
        List<String> arrayList = new ArrayList();
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new FileReader(file));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    arrayList.add(readLine.replace('\n', ' ').trim());
                } catch (Exception e) {
                    bufferedReader = bufferedReader2;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (bufferedReader2 == null) {
                return arrayList;
            }
            try {
                bufferedReader2.close();
                return arrayList;
            } catch (Exception e2) {
                return arrayList;
            }
        } catch (Exception e3) {
            if (bufferedReader == null) {
                return arrayList;
            }
            try {
                bufferedReader.close();
                return arrayList;
            } catch (Exception e4) {
                return arrayList;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = null;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (Exception e5) {
                }
            }
            throw th;
        }
    }

    private boolean a(Object obj) {
        boolean z = false;
        if (obj != null) {
            try {
                List list = (List) obj;
                if (list.size() >= 48) {
                    z = true;
                }
                if (!(list == null || list.isEmpty())) {
                    this.bU.addAll(list);
                }
            } catch (Exception e) {
            }
        }
        return z;
    }

    private void c(boolean z) {
        int size;
        int i;
        if (!this.bN.isEmpty()) {
            size = this.bN.size();
            for (i = 0; i < size; i++) {
                ((a) this.bN.get(i)).b.clear();
            }
            this.bN.clear();
        }
        if (!z) {
            size = this.cn.size();
            for (i = 0; i < size; i++) {
                FlightOverviewInfo flightOverviewInfo = (FlightOverviewInfo) this.cn.get(i);
                a aVar = new a(true);
                aVar.a.copyOf(flightOverviewInfo);
                if (dji.pilot.usercenter.protocol.c.T.equals(dji.pilot.publics.e.d.a(flightOverviewInfo.mBoardNum) ? dji.pilot.usercenter.protocol.c.T : flightOverviewInfo.mBoardNum)) {
                    this.bN.add(0, aVar);
                } else {
                    this.bN.add(aVar);
                }
            }
        }
    }

    private a g(dji.pilot.fpv.model.f fVar) {
        String str = a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
        boolean a = a(str);
        int size = this.bN.size();
        for (int i = 0; i < size; i++) {
            a aVar = (a) this.bN.get(i);
            if ((a && a(aVar.a.mBoardNum)) || str.equals(aVar.a.mBoardNum)) {
                return aVar;
            }
        }
        return null;
    }

    public static boolean a(String str, boolean z) {
        boolean z2;
        if (dji.pilot.publics.e.d.a(str) || dji.pilot.usercenter.protocol.c.T.equals(str)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!(z2 || z)) {
            int length = str.length();
            z2 = true;
            for (int i = 0; i < length; i++) {
                if ('0' != str.charAt(i)) {
                    z2 = false;
                }
            }
        }
        return z2;
    }

    public static boolean a(String str) {
        if (dji.pilot.publics.e.d.a(str)) {
            return false;
        }
        boolean z = true;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if ('0' != str.charAt(i)) {
                z = false;
            }
        }
        return z;
    }

    private void h(dji.pilot.fpv.model.f fVar) {
        a g = g(fVar);
        if (g == null) {
            String str = a(fVar.P, true) ? dji.pilot.usercenter.protocol.c.T : fVar.P;
            a aVar = new a(true);
            aVar.a.mBoardNum = str;
            aVar.a.mActiveTime = fVar.S;
            aVar.a.mAircraftName = fVar.R;
            aVar.a.mDroneType = fVar.Q;
            aVar.a.mVersion = dji.pilot.usercenter.protocol.c.T.equals(str) ? 2 : fVar.m;
            aVar.a.mLongitude = fVar.D;
            aVar.a.mLatitude = fVar.E;
            aVar.a.mSubStreet = fVar.t;
            aVar.a.mStreet = fVar.u;
            aVar.a.mCity = fVar.v;
            aVar.a.mArea = fVar.w;
            aVar.a.mTimeStamp = fVar.C;
            aVar.a.mTotalDistance = (double) fVar.F;
            aVar.a.mTotalFlightTime = 1;
            aVar.a.mTotalTime = (long) fVar.G;
            aVar.b.add(fVar);
            if (dji.pilot.usercenter.protocol.c.T.equals(str)) {
                this.bN.add(0, aVar);
                return;
            } else {
                this.bN.add(aVar);
                return;
            }
        }
        if (dji.pilot.publics.e.d.a(g.a.mAircraftName)) {
            g.a.mAircraftName = fVar.R;
        }
        if (g.a.mTimeStamp < fVar.C) {
            g.a.mLongitude = fVar.D;
            g.a.mLatitude = fVar.E;
            g.a.mSubStreet = fVar.t;
            g.a.mStreet = fVar.u;
            g.a.mCity = fVar.v;
            g.a.mArea = fVar.w;
            g.a.mTimeStamp = fVar.C;
        }
        g.a.mDroneType = fVar.Q;
        if (fVar.a() == 1 || d(fVar) == null) {
            FlightOverviewInfo flightOverviewInfo = g.a;
            flightOverviewInfo.mTotalDistance += (double) fVar.F;
            flightOverviewInfo = g.a;
            flightOverviewInfo.mTotalFlightTime++;
            flightOverviewInfo = g.a;
            flightOverviewInfo.mTotalTime += (long) fVar.G;
        }
        g.b.add(fVar);
    }

    private int C() {
        if (this.cd == 1) {
            return aW;
        }
        if (this.cd == 2) {
            return aX;
        }
        return 0;
    }

    private void a(Object obj, int i) {
        if (this.bK == null) {
            this.bB.removeMessages(8192);
            e(false);
            return;
        }
        Object obj2;
        int i2;
        c(false);
        if (this.bK != null) {
            this.bK.clear();
        }
        if (obj != null) {
            try {
                List list = (List) obj;
                if (list == null || list.isEmpty()) {
                    obj2 = 1;
                } else {
                    int C = C();
                    long currentTimeMillis = System.currentTimeMillis();
                    int size = list.size();
                    for (i2 = 0; i2 < size; i2++) {
                        dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) list.get(i2);
                        if (!(fVar == null || fVar.C == 0)) {
                            if (i == 0 || fVar.a() != 0 || C == 0 || (currentTimeMillis - fVar.C) / 1000 <= ((long) C)) {
                                this.bK.add(fVar);
                                h(fVar);
                            } else {
                                dji.pilot.fpv.model.i.a(this.bD, fVar);
                            }
                        }
                    }
                    obj2 = null;
                }
            } catch (Exception e) {
                obj2 = null;
            }
        } else {
            obj2 = 1;
        }
        if (obj2 == null) {
            a aVar;
            i2 = this.bN.size();
            for (int i3 = 0; i3 < i2; i3++) {
                aVar = (a) this.bN.get(i3);
                if (dji.pilot.publics.e.d.a(aVar.a.mAircraftName) && !dji.pilot.usercenter.protocol.c.T.equals(aVar.a.mBoardNum)) {
                    aVar.a.mAircraftName = dji.pilot.publics.c.d.getInstance().a(ProductType.find(aVar.a.mDroneType)).activePlaneName;
                }
            }
            if (!this.bN.isEmpty()) {
                a aVar2;
                aVar = (a) this.bN.get(0);
                if (dji.pilot.usercenter.protocol.c.T.equals(aVar.a.mBoardNum)) {
                    aVar.a.mAircraftName = this.bF.m();
                    aVar2 = aVar;
                } else {
                    aVar = new a(true);
                    aVar.a.mVersion = 2;
                    aVar.a.mBoardNum = dji.pilot.usercenter.protocol.c.T;
                    aVar.a.mActiveTime = 0;
                    aVar.a.mAircraftName = this.bF.m();
                    aVar.a.mDroneType = 1;
                    aVar.a.mTimeStamp = 0;
                    this.bN.add(0, aVar);
                    aVar2 = aVar;
                }
                int size2 = this.bN.size();
                for (int i4 = 1; i4 < size2; i4++) {
                    FlightOverviewInfo flightOverviewInfo;
                    aVar = (a) this.bN.get(i4);
                    if (aVar2.a.mTimeStamp < aVar.a.mTimeStamp) {
                        aVar2.a.mLongitude = aVar.a.mLongitude;
                        aVar2.a.mLatitude = aVar.a.mLatitude;
                        aVar2.a.mSubStreet = aVar.a.mSubStreet;
                        aVar2.a.mStreet = aVar.a.mStreet;
                        aVar2.a.mCity = aVar.a.mCity;
                        aVar2.a.mArea = aVar.a.mArea;
                        aVar2.a.mTimeStamp = aVar.a.mTimeStamp;
                    }
                    if (aVar.a.mTotalFlightTime <= 0 || aVar.a.mTotalFlightTime < aVar.b.size()) {
                        aVar.a.mTotalFlightTime = aVar.b.size();
                    }
                    if (aVar.a.mTotalDistance <= 0.0d) {
                        for (C = 0; C < aVar.b.size(); C++) {
                            flightOverviewInfo = aVar.a;
                            flightOverviewInfo.mTotalDistance += (double) ((dji.pilot.fpv.model.f) aVar.b.get(C)).F;
                        }
                    }
                    if (aVar.a.mTotalTime <= 0) {
                        for (C = 0; C < aVar.b.size(); C++) {
                            flightOverviewInfo = aVar.a;
                            flightOverviewInfo.mTotalTime += (long) ((dji.pilot.fpv.model.f) aVar.b.get(C)).G;
                        }
                    }
                    if (!a(aVar.a.mBoardNum, true)) {
                        FlightOverviewInfo flightOverviewInfo2 = aVar2.a;
                        flightOverviewInfo2.mTotalDistance += aVar.a.mTotalDistance;
                        flightOverviewInfo2 = aVar2.a;
                        flightOverviewInfo2.mTotalFlightTime += aVar.a.mTotalFlightTime;
                        flightOverviewInfo2 = aVar2.a;
                        flightOverviewInfo2.mTotalTime += aVar.a.mTotalTime;
                    }
                    aVar2.b.addAll(aVar.b);
                }
            }
        } else {
            this.bN.clear();
        }
        if (i == 0) {
            f(0);
        } else {
            e(0);
        }
    }

    private void D() {
        double latitude;
        double longitude;
        if (ServiceManager.getInstance().isRemoteOK()) {
            latitude = DataOsdGetPushCommon.getInstance().getLatitude();
            longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        } else {
            Location e = dji.a.a.getInstance().e();
            if (e != null) {
                latitude = e.getLatitude();
                longitude = e.getLongitude();
            } else {
                latitude = ((dji.pilot.fpv.model.f) this.cq.get(0)).E;
                longitude = ((dji.pilot.fpv.model.f) this.cq.get(0)).D;
            }
        }
        if (latitude != 0.0d && longitude != 0.0d) {
            DJILogHelper.getInstance().LOGD("", "检查上传位置：latitude为" + latitude + "longitude为" + longitude, false, true);
            dji.pilot.usercenter.protocol.b.a(this.bD, latitude, longitude, this.bz);
        }
    }

    private void E() {
        DJILogHelper.getInstance().LOGD("SYC", "自动上传info", false, true);
        this.cq.clear();
        if (!(this.bK == null || this.bK.isEmpty())) {
            int size = this.bK.size();
            for (int i = 0; i < size; i++) {
                dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) this.bK.get(i);
                if (!(fVar.a() == 0 || fVar.b())) {
                    this.cq.add(fVar);
                }
            }
        }
        DJILogHelper.getInstance().LOGI("bob", "mUploadInfoNeedCheckArea = " + this.bO + "mUploadInfo.size = " + this.cq.size());
        if (this.cq.size() > 0) {
            if (this.bO) {
                D();
                return;
            }
            DJILogHelper.getInstance().LOGI("bob", "mUploadInfoNeedCheckArea = " + this.bO);
            G();
        } else if (this.bA != null) {
            this.bA.a(0, 0, 0, Integer.valueOf(0), null);
        }
    }

    public void b(boolean z) {
        this.bO = z;
    }

    private void F() {
        if (!r()) {
            int i;
            int i2;
            Object obj;
            this.bB.removeMessages(8192);
            this.bZ = 30;
            a(this.bZ, 100);
            this.bV.clear();
            this.bX.clear();
            int C = C();
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(bu, Locale.US);
            List B = B();
            DJILogHelper.getInstance().LOGD("SYC", "SYC----- mRecordList Size:" + this.bU.size(), false, true);
            int size = this.bU.size();
            for (i = 0; i < size && !r(); i++) {
                String str;
                m mVar = (m) this.bU.get(i);
                if (B != null) {
                    int size2 = B.size();
                    for (i2 = 0; i2 < size2; i2++) {
                        str = (String) B.get(i2);
                        if (str != null && str.equals(mVar.a)) {
                            obj = null;
                            break;
                        }
                    }
                }
                int i3 = 1;
                if (obj != null) {
                    if (C == 0) {
                        this.bX.add(mVar);
                    } else {
                        long time;
                        try {
                            str = mVar.a.substring(dji.pilot.fpv.model.i.a.length());
                            time = simpleDateFormat.parse(str.substring(0, str.indexOf(bv))).getTime();
                        } catch (Exception e) {
                            time = currentTimeMillis;
                        }
                        if ((currentTimeMillis - time) / 1000 <= ((long) C)) {
                            this.bX.add(mVar);
                        }
                    }
                }
            }
            if (!(this.bK == null || this.bK.isEmpty())) {
                int size3 = this.bK.size();
                for (int i4 = 0; i4 < size3 && !r(); i4++) {
                    m mVar2;
                    dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) this.bK.get(i4);
                    if (fVar.a() != 0) {
                        this.bV.add(fVar);
                    } else if (bd) {
                        if (this.bU.isEmpty()) {
                            this.bV.add(fVar);
                        } else {
                            i = this.bU.size();
                            for (i2 = 0; i2 < i; i2++) {
                                mVar2 = (m) this.bU.get(i2);
                                if (mVar2.a != null && mVar2.a.equals(fVar.r)) {
                                    obj = 1;
                                    break;
                                }
                            }
                            obj = null;
                            if (obj == null) {
                                this.bV.add(fVar);
                            }
                        }
                    }
                    i2 = this.bX.size();
                    int i5 = 0;
                    while (i5 < i2 && !r()) {
                        mVar2 = (m) this.bX.get(i5);
                        if (mVar2.a == null || !mVar2.a.equals(fVar.r)) {
                            i5++;
                        } else if (dji.pilot.publics.e.d.a(mVar2.b) || mVar2.b.equals(fVar.s)) {
                            this.bX.remove(i5);
                        }
                    }
                }
            }
            this.ca = this.bV.size();
            this.cb = this.bX.size();
            this.cc = 0;
            d(false);
        }
    }

    private void G() {
        int i;
        int i2;
        DJILogHelper.getInstance().LOGD("SYC", "拼接info信息", false, true);
        byte[] bArr = new byte[425];
        int size = this.cq.size();
        if (size > 100) {
            i = 42500;
        } else {
            i = this.cq.size() * 425;
        }
        byte[] bArr2 = new byte[i];
        this.cr = 0;
        while (size - 100 > 0) {
            int i3 = 0;
            for (i2 = 0; i2 < 100; i2++) {
                dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) this.cq.get((this.cr * 100) + i2);
                fVar.a(true);
                dji.midware.util.c.a(dji.midware.util.c.a(fVar.r.replace("DJIFlightRecord_", "").replace(bv, "")), bArr, 0);
                dji.midware.util.c.a(dji.midware.util.c.b(fVar.m), bArr, 21);
                dji.midware.util.c.a(dji.midware.util.c.b(fVar.l), bArr, 23);
                dji.midware.util.c.a(fVar.i(), bArr, 25);
                dji.midware.util.c.a(bArr, bArr2, i3 * 425);
                i3++;
                fVar.a(false);
            }
            i = size - 100;
            this.cr++;
            a(bArr2);
            size = i;
        }
        byte[] bArr3 = new byte[((this.cq.size() - (this.cr * 100)) * 425)];
        i2 = 0;
        for (size = 0; size < this.cq.size() - (this.cr * 100); size++) {
            fVar = (dji.pilot.fpv.model.f) this.cq.get((this.cr * 100) + size);
            fVar.a(true);
            dji.midware.util.c.a(dji.midware.util.c.a(fVar.r.replace("DJIFlightRecord_", "").replace(bv, "")), bArr, 0);
            dji.midware.util.c.a(dji.midware.util.c.b(fVar.m), bArr, 21);
            dji.midware.util.c.a(dji.midware.util.c.b(fVar.l), bArr, 23);
            dji.midware.util.c.a(fVar.i(), bArr, 25);
            dji.midware.util.c.a(bArr, bArr3, i2 * 425);
            i2++;
            fVar.a(false);
        }
        a(bArr3);
    }

    public void o() {
        dji.pilot.usercenter.protocol.b.a(this.bD, this.bF.n(), bn, this.bx);
    }

    public void p() {
        dji.pilot.usercenter.protocol.b.a(this.bD, bm, this.bx);
    }

    private void d(boolean z) {
        if (!r()) {
            if (this.cg && !this.bV.isEmpty()) {
                this.cc = this.ca + this.cb;
                this.bW.addAll(this.bV);
                this.bV.clear();
            }
            int size = this.bW.size() + (this.bV.size() + this.bX.size());
            if (!this.bV.isEmpty()) {
                this.ce = (dji.pilot.fpv.model.f) this.bV.remove(0);
                this.cf = this.ce.a();
                DJILogHelper.getInstance().LOGD("SYC", "mCurUploadRecordNeedUpload: upload--" + this.cf, false, true);
                if (this.cf == 0) {
                    this.cf = 2;
                }
                this.ce.a(0);
                dji.pilot.fpv.model.i.b(this.bD, this.ce);
                e(this.ce);
                DJILogHelper.getInstance().LOGD("SYC", "SYC-----Uploading: " + size + dji.pilot.usercenter.protocol.d.t + this.ca, false, true);
            } else if (!this.bX.isEmpty()) {
                a((m) this.bX.remove(0));
                DJILogHelper.getInstance().LOGD("SYC", "SYC-----Downloading: " + size + dji.pilot.usercenter.protocol.d.t + this.cb, false, true);
            } else if (this.cg) {
                H();
            } else if (this.bW.isEmpty()) {
                p();
                dji.pilot.usercenter.protocol.b.a(this.bD, this.bF.n(), bm, this.bx);
                y();
            } else {
                DJILogHelper.getInstance().LOGD("SYC", "SYC-----start update", false, true);
                this.ce = (dji.pilot.fpv.model.f) this.bW.remove(0);
                this.cf = this.ce.a();
                DJILogHelper.getInstance().LOGD("SYC", "mCurUploadRecordNeedUpload: update--" + this.cf, false, true);
                if (this.cf == 0) {
                    this.cf = 2;
                }
                this.ce.a(0);
                dji.pilot.fpv.model.i.b(this.bD, this.ce);
                e(this.ce);
            }
            if (this.bY) {
                if ((this.ca + this.cb) + this.cc > 0) {
                    this.bZ = (((((this.ca + this.cb) + this.cc) - size) * 68) / ((this.ca + this.cb) + this.cc)) + 30;
                } else {
                    this.bZ = 98;
                }
                a(this.bZ, 100);
            }
        }
    }

    private void H() {
        String trim = f.getInstance().l().trim();
        DJILogHelper.getInstance().LOGD("SYC", "userEmailInfo" + trim, false, true);
        Object b = dji.midware.util.c.b(com.dji.frame.c.a.b(trim.toLowerCase(Locale.US) + bp));
        Object b2 = dji.midware.util.c.b(com.dji.frame.c.a.b(f.getInstance().o() + bp));
        DJILogHelper.getInstance().LOGD("SYC", "mRecordList SIze: " + this.bU.size(), false, true);
        List a = dji.pilot.fpv.model.i.a(this.bD, this.bU);
        if (a == null) {
            DJILogHelper.getInstance().LOGD("SYC", "read info fail", false, true);
            return;
        }
        DJILogHelper.getInstance().LOGD("SYC", "mCheckList SIze: " + a.size(), false, true);
        for (int i = 0; i < a.size(); i++) {
            this.ch = (dji.pilot.fpv.model.f) a.get(i);
            if (this.ch.m <= (short) 5) {
                DJILogHelper.getInstance().LOGD("SYC", "index:" + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + "name: " + this.ch.r + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + dji.midware.util.c.i(this.ch.ag), false, true);
                if (!Arrays.equals(b, this.ch.ag)) {
                    DJILogHelper.getInstance().LOGD("SYC", "email MD5不匹配，进行修复", false, true);
                    System.arraycopy(b, 0, this.ch.ag, 0, this.ch.ag.length);
                    this.bW.add(this.ch);
                }
            } else if (!Arrays.equals(b2, this.ch.ah)) {
                DJILogHelper.getInstance().LOGD("SYC", "uuid MD5不匹配，进行修复", false, true);
                System.arraycopy(b2, 0, this.ch.ah, 0, this.ch.ah.length);
                this.bW.add(this.ch);
            }
        }
        DJILogHelper.getInstance().LOGD("SYC", "updateCount:" + this.bW.size(), false, true);
        this.cc = this.bW.size();
        this.cg = false;
        d(true);
    }

    private e$a I() {
        e$a dji_pilot_usercenter_protocol_e_a = null;
        synchronized (this.bC) {
            if (!this.bC.isEmpty()) {
                dji_pilot_usercenter_protocol_e_a = (e$a) this.bC.get(0);
            }
        }
        return dji_pilot_usercenter_protocol_e_a;
    }

    private String e(String str) {
        return this.bQ + str;
    }

    private void a(Object obj, boolean z) {
        if (obj != null && z) {
            try {
                List list = (List) obj;
                this.cn.clear();
                if (!(list == null || list.isEmpty())) {
                    this.cn.addAll(list);
                }
                w();
            } catch (Exception e) {
            }
        }
    }

    private void a(int i, int i2, Object obj) {
        if (obj instanceof e$b) {
            dji.pilot.usercenter.mode.i iVar;
            boolean z;
            Object obj2 = (e$b) obj;
            if (obj2.d instanceof dji.pilot.usercenter.mode.i) {
                iVar = (dji.pilot.usercenter.mode.i) obj2.d;
            } else {
                iVar = null;
            }
            boolean z2 = iVar != null && iVar.bo == 0;
            if (iVar == null || iVar.bo != 400) {
                z = false;
            } else {
                z = true;
            }
            if (1048576 == i) {
                if (z2 || !bd) {
                    if (z2) {
                        this.cp.b = this.cp.b == 0 ? 1 : this.cp.b;
                    } else if (this.cp.b != -1) {
                        this.cp.b = -1;
                    }
                    if (!z) {
                        if (z2 && a(iVar.bq)) {
                            c(obj2.a + 1);
                        } else {
                            F();
                        }
                    }
                } else {
                    d(i);
                }
            } else if (dji.pilot.usercenter.protocol.c.f == i) {
                if (z2 || !bd) {
                    if (z2) {
                        z();
                        this.cp.a = 1;
                    } else {
                        this.cp.a = -1;
                    }
                    if (!z) {
                        this.bU.clear();
                        c(1);
                    }
                } else {
                    d(i);
                }
            } else if (dji.pilot.usercenter.protocol.c.d == i) {
                if (z2 || !bd) {
                    if (this.ce != null) {
                        h hVar;
                        if (z2) {
                            hVar = this.cp;
                            hVar.c++;
                        } else {
                            this.ce.a(this.cf);
                            dji.pilot.fpv.model.i.b(this.bD, this.ce);
                            hVar = this.cp;
                            hVar.d++;
                        }
                        this.ce = null;
                    }
                    if (!z) {
                        d(false);
                    }
                } else {
                    if (this.ce != null) {
                        this.ce.a(this.cf);
                        dji.pilot.fpv.model.i.b(this.bD, this.ce);
                    }
                    d(i);
                }
            } else if (dji.pilot.usercenter.protocol.c.j == i) {
                if (!z2 && bd) {
                    d(i);
                } else if (!z) {
                    a(iVar.bq, z2);
                    dji.pilot.usercenter.protocol.b.b(this.bD, this.bF.n(), obj2.c, this.bx);
                }
            } else if (dji.pilot.usercenter.protocol.c.v == i) {
                if (!z2 && bd) {
                    d(i);
                } else if (!z) {
                    try {
                        File file = new File(this.bQ + t);
                        if (file.exists()) {
                            file.delete();
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(this.bQ + t);
                        fileOutputStream.write(((String) obj2.d).getBytes("UTF-8"));
                        fileOutputStream.close();
                    } catch (Exception e) {
                    }
                    if (bn.equalsIgnoreCase((String) obj2.c)) {
                        this.bI.sendMessage(this.bI.obtainMessage(4096, 0, 0));
                    } else {
                        this.bB.sendMessage(this.bB.obtainMessage(8192, 1, 0));
                        this.bI.sendMessage(this.bI.obtainMessage(4096, 1, 0));
                    }
                }
            }
            e$a I = I();
            if (I != null) {
                if (z2) {
                    if (!(i == 1048576 || i == dji.pilot.usercenter.protocol.c.f || i == dji.pilot.usercenter.protocol.c.d || i == dji.pilot.usercenter.protocol.c.j || dji.pilot.usercenter.protocol.c.v == i)) {
                        I.a(i, obj2.a, 0, null, obj);
                    }
                } else if (!(i == 1048576 || i == dji.pilot.usercenter.protocol.c.f || i == dji.pilot.usercenter.protocol.c.d || i == dji.pilot.usercenter.protocol.c.j || dji.pilot.usercenter.protocol.c.v == i)) {
                    I.a(i, iVar == null ? 1 : iVar.bo, obj2.a, obj2);
                }
            }
            if (iVar != null && iVar.bo == 400) {
                d(i);
                f.getInstance().a(true);
            }
        }
    }

    private void a(int i, int i2) {
        e$a I = I();
        if (I != null) {
            I.a((int) dji.pilot.usercenter.protocol.c.i, (long) i, (long) i2, 0, null);
        }
    }

    private void d(int i) {
        this.ce = null;
        this.bB.removeMessages(8192);
        e(false);
        e$a I = I();
        if (I != null) {
            I.a((int) dji.pilot.usercenter.protocol.c.g, i, 0, null);
        }
    }

    private void e(int i) {
        this.bB.removeMessages(8192);
        e(false);
        e$a I = I();
        if (I != null) {
            I.a((int) dji.pilot.usercenter.protocol.c.h, i, 0, null, null);
        } else if (m()) {
            Toast.makeText(this.bD, R.string.flight_record_sync_fail, 1).show();
        }
    }

    private void f(int i) {
        e$a I = I();
        if (I != null) {
            I.a((int) dji.pilot.usercenter.protocol.c.x, i, 0, null, this.bK);
        }
    }

    private void b(int i, int i2, Object obj) {
        if (obj instanceof e$b) {
            obj = (e$b) obj;
            e$a I = I();
            if (I == null) {
                return;
            }
            if (i != 1048576 && i != dji.pilot.usercenter.protocol.c.f && i != dji.pilot.usercenter.protocol.c.d && i != dji.pilot.usercenter.protocol.c.j && dji.pilot.usercenter.protocol.c.v != i) {
                I.a(i, i2, obj.a, obj);
            } else if (1048576 == i) {
                if (bd) {
                    d(i);
                    return;
                }
                this.cp.b = -1;
                F();
            } else if (dji.pilot.usercenter.protocol.c.f == i) {
                if (bd) {
                    d(i);
                    return;
                }
                this.cp.a = -1;
                this.bU.clear();
                c(1);
            } else if (dji.pilot.usercenter.protocol.c.d == i) {
                this.ce.a(this.cf);
                dji.pilot.fpv.model.i.b(this.bD, this.ce);
                if (bd) {
                    d(i);
                    return;
                }
                h hVar = this.cp;
                hVar.d++;
                d(false);
            } else if (dji.pilot.usercenter.protocol.c.j == i) {
                if (bd) {
                    d(i);
                    return;
                }
                a(null, false);
                dji.pilot.usercenter.protocol.b.b(this.bD, this.bF.n(), obj.c, this.bx);
            } else if (dji.pilot.usercenter.protocol.c.v != i) {
            } else {
                if (bd) {
                    d(i);
                } else if (!bn.equalsIgnoreCase((String) obj.c)) {
                    this.bB.sendMessage(this.bB.obtainMessage(8192, 1, 0));
                    this.bI.sendMessage(this.bI.obtainMessage(4096, 1, 0));
                }
            }
        }
    }

    private void c(int i, int i2, Object obj) {
        if (obj instanceof e$b) {
            Object obj2 = (e$b) obj;
            e$a I = I();
            if (I != null) {
                I.a(i, (long) i2, (long) bk, obj2.a, obj2);
            }
        }
    }

    private void d(int i, int i2, Object obj) {
        boolean z = true;
        if (obj instanceof e$b) {
            obj = (e$b) obj;
            e$a I = I();
            if (I != null) {
                if (i2 != 1) {
                    z = false;
                }
                I.a(i, z, obj.a, obj);
            }
        }
    }

    private d() {
        this.bx = null;
        this.by = null;
        this.bz = null;
        this.bA = null;
        this.bB = null;
        this.bC = new ArrayList(4);
        this.bD = null;
        this.bE = false;
        this.bF = f.getInstance();
        this.bG = c.getInstance();
        this.bH = s.getInstance();
        this.bI = null;
        this.bJ = null;
        this.bK = null;
        this.bL = 0;
        this.bM = null;
        this.bN = new ArrayList();
        this.bO = true;
        this.bP = null;
        this.bQ = null;
        this.bR = null;
        this.bS = null;
        this.bT = null;
        this.bU = new ArrayList();
        this.bV = new ArrayList();
        this.bW = new ArrayList();
        this.bX = new ArrayList();
        this.bY = false;
        this.bZ = 0;
        this.ca = 0;
        this.cb = 0;
        this.cc = 0;
        this.cd = 3;
        this.ce = null;
        this.cf = 0;
        this.cg = true;
        this.ch = null;
        this.ci = false;
        this.cj = null;
        this.ck = null;
        this.cl = null;
        this.cm = false;
        this.cn = new ArrayList();
        this.co = null;
        this.cp = new h();
        this.cq = new ArrayList();
        this.cr = 0;
        this.cs = 0;
        this.ct = 0;
        this.cu = null;
        this.cv = 0;
        this.bB = new f(this);
        this.by = new e$a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
                DJILogHelper.getInstance().LOGD("SYC", "info upload onUpdate", false, true);
                if (this.a.bA != null) {
                    this.a.bA.a(i, j, j2, i2, obj);
                }
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                DJILogHelper.getInstance().LOGD("SYC", "info upload onSuccess", false, true);
                this.a.cs = this.a.cs + 1;
                if (this.a.cr + 1 == this.a.cs) {
                    Iterator it = this.a.cq.iterator();
                    while (it.hasNext()) {
                        dji.pilot.fpv.model.f fVar = (dji.pilot.fpv.model.f) it.next();
                        DJILogHelper.getInstance().LOGD("", "重写infoExist", false, true);
                        fVar.a(true);
                        dji.pilot.fpv.model.i.b(this.a.bD, fVar);
                    }
                    this.a.cs = 0;
                    this.a.ct = 0;
                    if (this.a.bA != null) {
                        this.a.bA.a(i, i2, i3, obj, obj2);
                        new HashMap().put("success", "true");
                        dji.pilot.fpv.d.e.b(dji.pilot.fpv.d.c.h.bc_);
                    }
                }
            }

            public void a(int i, boolean z, int i2, Object obj) {
                DJILogHelper.getInstance().LOGD("SYC", "开始自动上传info", false, true);
            }

            public void a(int i, int i2, int i3, Object obj) {
                DJILogHelper.getInstance().LOGD("SYC", "自动上传info失败，重传" + i2, false, true);
                if (this.a.ct < 3) {
                    this.a.E();
                    this.a.ct = this.a.ct + 1;
                } else if (this.a.bA != null) {
                    new HashMap().put("success", dji.pilot.phonecamera.h.e);
                    dji.pilot.fpv.d.e.b(dji.pilot.fpv.d.c.h.bc_);
                    this.a.bA.a(i, i2, i3, obj);
                }
            }
        };
        this.bz = new e$a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
                DJILogHelper.getInstance().LOGD("SYC", "area onUpdate", false, true);
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                DJILogHelper.getInstance().LOGD("SYC", "area onSuccess", false, true);
                dji.pilot.usercenter.mode.i iVar = (dji.pilot.usercenter.mode.i) obj2;
                DJILogHelper.getInstance().LOGD("", "check area success: in_china" + iVar.bo, false, true);
                if (iVar.bo == 1) {
                    this.a.G();
                }
            }

            public void a(int i, boolean z, int i2, Object obj) {
                DJILogHelper.getInstance().LOGD("SYC", "area start", false, true);
            }

            public void a(int i, int i2, int i3, Object obj) {
                DJILogHelper.getInstance().LOGD("SYC", "area fail" + i2, false, true);
            }
        };
        this.bx = new e$a(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.bB.obtainMessage(65536, i, 0, obj).sendToTarget();
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.bB.obtainMessage(65537, i, i2, obj).sendToTarget();
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
                if (j >= j2) {
                    j = j2;
                }
                this.a.bB.obtainMessage(dji.pilot.usercenter.protocol.e.aD, i, j2 == 0 ? 0 : (int) ((d.bk * j) / j2), obj).sendToTarget();
            }

            public void a(int i, boolean z, int i2, Object obj) {
                this.a.bB.obtainMessage(dji.pilot.usercenter.protocol.e.aC, i, z ? 1 : 0, obj).sendToTarget();
            }
        };
        this.bP = new dji.pilot.usercenter.b.c.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(String str, Object obj, int i, int i2) {
            }

            public void a(String str, String str2, Object obj) {
                h l = this.a.cp;
                l.e++;
                this.a.c(str2);
                this.a.d(true);
            }

            public void a(String str, Object obj, boolean z) {
            }

            public void a(String str, Object obj, int i) {
                h l = this.a.cp;
                l.f++;
                if (d.bd) {
                    this.a.d((int) dji.pilot.usercenter.protocol.c.e);
                } else {
                    this.a.d(true);
                }
            }
        };
    }

    private void i(dji.pilot.fpv.model.f fVar) {
    }

    public void a(List<a> list, int i) {
        this.cu = list;
        this.cv = i;
    }

    public dji.pilot.fpv.model.f b(int i) {
        if (((a) this.cu.get(this.cv)).b.size() <= i) {
            return null;
        }
        return (dji.pilot.fpv.model.f) ((a) this.cu.get(this.cv)).b.get(i);
    }

    public int q() {
        return ((a) this.cu.get(this.cv)).b.size();
    }

    public boolean r() {
        return this.cm;
    }

    private void e(boolean z) {
        this.bY = z;
        dji.thirdparty.a.c.a().e(z ? i.Start : i.Stop);
    }

    public void s() {
        e(false);
        this.cm = true;
        this.bZ = 0;
        x();
    }
}
