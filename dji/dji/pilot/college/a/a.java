package dji.pilot.college.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.college.model.CollegeInfo;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.c;
import dji.pilot.usercenter.b.c.d;
import dji.pilot.usercenter.protocol.e;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot.usercenter.protocol.e$b;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class a implements dji.pilot.college.b.b {
    private static final String A = "en";
    private static final String B = "key_college_gettime";
    private static final String a = a.class.getSimpleName();
    private static final long b = 0;
    private static final boolean v = true;
    private static final String w = "College/";
    private static final String x = "college.db";
    private static final int y = 1;
    private static final String z = ".pdf";
    private volatile boolean C;
    private Context D;
    private final dji.pilot.college.model.a E;
    private final e$a F;
    private final b G;
    private final ArrayList<e$a> H;
    private d I;
    private long J;
    private String K;
    private dji.thirdparty.afinal.b L;
    private dji.thirdparty.afinal.b.b M;
    private final dji.pilot.college.model.a N;
    private ProductType O;
    private final Comparator<CollegeInfo> P;
    private final List<CollegeInfo> Q;

    private static final class a {
        private static final a a = new a();

        private a() {
        }
    }

    private static final class b extends Handler {
        private final WeakReference<a> a;

        public b(a aVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            a aVar = (a) this.a.get();
            if (aVar != null && aVar.d()) {
                switch (message.what) {
                    case 65536:
                        aVar.a(message.arg1, message.arg2, message.obj);
                        return;
                    case 65537:
                        aVar.b(message.arg1, message.arg2, message.obj);
                        return;
                    case e.aC /*65538*/:
                        int i = message.arg1;
                        if (message.arg2 != 1) {
                            z = false;
                        }
                        aVar.a(i, z, message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static a getInstance() {
        return a.a;
    }

    public synchronized void a(Context context) {
        int i = 0;
        synchronized (this) {
            if (!this.C) {
                CollegeInfo collegeInfo;
                this.D = context.getApplicationContext();
                this.J = g.b(this.D, B, 0);
                dji.thirdparty.afinal.b.a aVar = new dji.thirdparty.afinal.b.a();
                aVar.a(this.D);
                aVar.a(x);
                aVar.a(1);
                aVar.a(this.M);
                this.L = dji.thirdparty.afinal.b.a(aVar);
                List c = this.L.c(CollegeInfo.class, "mDataType=" + String.valueOf(0));
                for (int i2 = 0; i2 < c.size(); i2++) {
                    collegeInfo = (CollegeInfo) c.get(i2);
                    a(collegeInfo, true);
                    this.E.a.add(collegeInfo);
                }
                List c2 = this.L.c(CollegeInfo.class, "mDataType=" + String.valueOf(1));
                while (i < c2.size()) {
                    collegeInfo = (CollegeInfo) c2.get(i);
                    collegeInfo.mDownloadState = 0;
                    this.E.b.add(collegeInfo);
                    i++;
                }
                if (this.E.a.isEmpty() && this.E.b.isEmpty()) {
                    this.J = 0;
                }
                this.C = true;
            }
        }
    }

    public synchronized void a() {
        if (this.C) {
            this.C = false;
        }
    }

    public boolean a(e$a dji_pilot_usercenter_protocol_e_a) {
        boolean z = false;
        if (dji_pilot_usercenter_protocol_e_a != null) {
            synchronized (this.H) {
                if (!this.H.contains(dji_pilot_usercenter_protocol_e_a)) {
                    this.H.add(0, dji_pilot_usercenter_protocol_e_a);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean b(e$a dji_pilot_usercenter_protocol_e_a) {
        boolean z = false;
        if (dji_pilot_usercenter_protocol_e_a != null) {
            synchronized (this.H) {
                z = this.H.remove(dji_pilot_usercenter_protocol_e_a);
            }
        }
        return z;
    }

    private void a(List<CollegeInfo> list, List<CollegeInfo> list2, String str) {
        int i;
        int i2 = 0;
        list.clear();
        int size = list2.size();
        for (i = 0; i < size; i++) {
            CollegeInfo collegeInfo = (CollegeInfo) list2.get(i);
            if (str.equalsIgnoreCase(collegeInfo.mAppId) && ("".equals(collegeInfo.mLanguage) || this.K.equals(collegeInfo.mLanguage))) {
                list.add(collegeInfo);
            }
        }
        if (list.isEmpty()) {
            i = list2.size();
            while (i2 < i) {
                collegeInfo = (CollegeInfo) list2.get(i2);
                if (str.equalsIgnoreCase(collegeInfo.mAppId) && "en".equals(collegeInfo.mLanguage)) {
                    list.add(collegeInfo);
                }
                i2++;
            }
        }
    }

    private void a(List<CollegeInfo> list) {
        Collections.sort(list, this.P);
    }

    private void b() {
        this.K = this.D.getResources().getConfiguration().locale.getLanguage();
        String c = c();
        a(this.E.a);
        a(this.E.b);
        a(this.N.a, this.E.a, c);
        a(this.N.b, this.E.b, c);
    }

    private String c() {
        return dji.pilot.publics.c.d.getInstance().k(this.O);
    }

    public void a(ProductType productType) {
        if (this.O != productType) {
            this.O = productType;
        }
    }

    public dji.pilot.college.model.a a(boolean z) {
        b();
        if (z && System.currentTimeMillis() - this.J >= 0) {
            dji.pilot.college.b.a.a(this.D, c(), a, this.F);
        }
        return this.N;
    }

    public void a(CollegeInfo collegeInfo) {
        if (collegeInfo != null && collegeInfo.mDataType == 0) {
            collegeInfo.mDownloadState = 1;
            c.getInstance().a(collegeInfo.getUrl(), a(collegeInfo.mName), true, true, a, this.I);
        }
    }

    public void b(CollegeInfo collegeInfo) {
        if (collegeInfo != null && collegeInfo.mDataType == 0) {
            c.getInstance().a(collegeInfo.getUrl());
            collegeInfo.mDownloadState = 0;
        }
    }

    public void c(CollegeInfo collegeInfo) {
        if (collegeInfo != null) {
            collegeInfo.mbNew = false;
            this.L.e(collegeInfo);
        }
    }

    public String a(String str) {
        return a(str, z);
    }

    private String a(String str, String str2) {
        String a = com.dji.frame.c.d.a(this.D, w);
        if (str.endsWith(str2)) {
            return a + str;
        }
        return a + str + str2;
    }

    private boolean d() {
        return this.C;
    }

    private e$a e() {
        e$a dji_pilot_usercenter_protocol_e_a = null;
        synchronized (this.H) {
            if (!this.H.isEmpty()) {
                dji_pilot_usercenter_protocol_e_a = (e$a) this.H.get(0);
            }
        }
        return dji_pilot_usercenter_protocol_e_a;
    }

    private void a(CollegeInfo collegeInfo, boolean z) {
        String a = a(collegeInfo.mName);
        if (dji.pilot.usercenter.f.c.a(a)) {
            File file = new File(a);
            if (file.length() >= collegeInfo.mSize) {
                collegeInfo.mDownloadState = 3;
                return;
            }
            int length;
            if (collegeInfo.mSize != 0) {
                length = (int) ((((float) file.length()) / ((float) collegeInfo.mSize)) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            } else {
                length = 0;
            }
            collegeInfo.mProgress = length;
            if (z) {
                collegeInfo.mDownloadState = 0;
                return;
            }
            return;
        }
        collegeInfo.mProgress = 0;
        if (z) {
            collegeInfo.mDownloadState = 0;
        }
    }

    private void a(List<CollegeInfo> list, List<CollegeInfo> list2, List<CollegeInfo> list3, int i, String str) {
        int i2 = 0;
        CollegeInfo collegeInfo;
        if (list.isEmpty()) {
            if (!list3.isEmpty()) {
                while (i2 < list3.size()) {
                    collegeInfo = (CollegeInfo) list3.get(i2);
                    a(collegeInfo, true);
                    try {
                        this.L.a(collegeInfo);
                        list.add(collegeInfo);
                    } catch (Exception e) {
                    }
                    i2++;
                }
            }
        } else if (list3.isEmpty()) {
            this.L.a(CollegeInfo.class, "mDataType=" + String.valueOf(i) + " and mAppId=" + str);
            int size = list.size();
            r2 = 0;
            while (r2 < size) {
                int i3;
                if (str.equals(((CollegeInfo) list.get(r2)).mAppId)) {
                    list.remove(r2);
                    i3 = size - 1;
                    size = r2 - 1;
                } else {
                    i3 = size;
                    size = r2;
                }
                r2 = size + 1;
                size = i3;
            }
        } else {
            for (r2 = 0; r2 < list.size(); r2++) {
                boolean z;
                collegeInfo = (CollegeInfo) list.get(r2);
                int size2 = list3.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    CollegeInfo collegeInfo2 = (CollegeInfo) list3.get(i4);
                    if (collegeInfo2.equals(collegeInfo)) {
                        collegeInfo.copyFromServer(collegeInfo2);
                        list3.remove(i4);
                        z = true;
                        break;
                    }
                }
                z = false;
                if (z) {
                    a(collegeInfo, false);
                    this.L.e(collegeInfo);
                } else if (str.equals(collegeInfo.mAppId)) {
                    list.remove(r2);
                    this.L.f(collegeInfo);
                }
            }
            if (!list3.isEmpty()) {
                while (i2 < list3.size()) {
                    collegeInfo = (CollegeInfo) list3.get(i2);
                    if (!list.contains(collegeInfo)) {
                        a(collegeInfo, true);
                        try {
                            this.L.a(collegeInfo);
                            list.add(collegeInfo);
                        } catch (Exception e2) {
                        }
                    }
                    i2++;
                }
            }
        }
    }

    private void a(dji.pilot.college.model.a aVar, String str) {
        a(this.E.a, this.N.a, aVar.a, 0, str);
        a(this.E.b, this.N.b, aVar.b, 1, str);
        this.J = System.currentTimeMillis();
        g.a(this.D, B, this.J);
    }

    private void a(int i, int i2, Object obj) {
        if (obj instanceof e$b) {
            e$b dji_pilot_usercenter_protocol_e_b = (e$b) obj;
            if (i == 65536 && (dji_pilot_usercenter_protocol_e_b.d instanceof dji.pilot.college.model.a)) {
                a((dji.pilot.college.model.a) dji_pilot_usercenter_protocol_e_b.d, (String) dji_pilot_usercenter_protocol_e_b.c);
            }
            e$a e = e();
            if (e != null) {
                e.a(i, dji_pilot_usercenter_protocol_e_b.a, 0, dji_pilot_usercenter_protocol_e_b.c, obj);
            }
        }
    }

    private void b(int i, int i2, Object obj) {
        if (obj instanceof e$b) {
            obj = (e$b) obj;
            e$a e = e();
            if (e != null) {
                e.a(i, i2, obj.a, obj);
            }
        }
    }

    private void a(int i, boolean z, Object obj) {
        if (obj instanceof e$b) {
            obj = (e$b) obj;
            e$a e = e();
            if (e != null) {
                e.a(i, z, obj.a, obj);
            }
        }
    }

    private CollegeInfo b(String str) {
        if (str != null) {
            int size = this.E.a.size();
            for (int i = 0; i < size; i++) {
                CollegeInfo collegeInfo = (CollegeInfo) this.E.a.get(i);
                if (str.equals(collegeInfo.getUrl())) {
                    return collegeInfo;
                }
            }
        }
        return null;
    }

    private List<CollegeInfo> c(String str) {
        this.Q.clear();
        if (str != null) {
            int size = this.E.a.size();
            for (int i = 0; i < size; i++) {
                CollegeInfo collegeInfo = (CollegeInfo) this.E.a.get(i);
                if (collegeInfo.beDownload() && str.trim().equals(collegeInfo.getUrl())) {
                    this.Q.add(collegeInfo);
                }
            }
        }
        return this.Q;
    }

    private void a(String str, int i, int i2) {
        List<Object> c = c(str);
        if (!c.isEmpty()) {
            for (Object obj : c) {
                if (!(obj.mSize == ((long) i2) || i2 == 0)) {
                    obj.mSize = (long) i2;
                    this.L.e(obj);
                }
                if (i >= obj.mProgress + 2 || i == i2) {
                    obj.mProgress = i;
                    e$a e = e();
                    if (e != null) {
                        e.a(65537, (long) i, (long) i2, 0, obj);
                    }
                }
            }
        }
    }

    private void a(String str, boolean z) {
        List<Object> c = c(str);
        if (!c.isEmpty()) {
            for (Object obj : c) {
                obj.mDownloadState = 2;
                e$a e = e();
                if (e != null) {
                    e.a(65537, z, 0, obj);
                }
            }
        }
    }

    private void b(String str, String str2) {
        List<Object> c = c(str);
        if (!c.isEmpty()) {
            for (Object obj : c) {
                if (str2 != null) {
                    obj.mSize = new File(str2).length();
                    this.L.e(obj);
                }
                obj.mDownloadState = 3;
                e$a e = e();
                if (e != null) {
                    e.a(65537, 0, 0, null, obj);
                }
            }
        }
    }

    private void a(String str, int i) {
        List<Object> c = c(str);
        if (!c.isEmpty()) {
            for (Object obj : c) {
                if (i == 416) {
                    dji.pilot.usercenter.f.c.e(a(obj.mName));
                    obj.mProgress = 0;
                }
                obj.mDownloadState = 0;
                e$a e = e();
                if (e != null) {
                    e.a(65537, i, 0, obj);
                }
            }
        }
    }

    private a() {
        this.C = false;
        this.D = null;
        this.E = new dji.pilot.college.model.a();
        this.H = new ArrayList(4);
        this.I = null;
        this.J = 0;
        this.K = "";
        this.L = null;
        this.M = null;
        this.N = new dji.pilot.college.model.a();
        this.O = null;
        this.P = new Comparator<CollegeInfo>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((CollegeInfo) obj, (CollegeInfo) obj2);
            }

            public int a(CollegeInfo collegeInfo, CollegeInfo collegeInfo2) {
                return (int) (collegeInfo2.mCreateTime - collegeInfo.mCreateTime);
            }
        };
        this.Q = new ArrayList(2);
        this.G = new b(this);
        this.F = new e$a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.G.obtainMessage(65536, i, 0, obj).sendToTarget();
            }

            public void a(int i, boolean z, int i2, Object obj) {
                this.a.G.obtainMessage(e.aC, i, z ? 1 : 0, obj).sendToTarget();
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.G.obtainMessage(65537, i, i2, obj).sendToTarget();
            }
        };
        this.I = new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(String str, Object obj, int i, int i2) {
                this.a.a(str, i, i2);
            }

            public void a(String str, String str2, Object obj) {
                this.a.b(str, str2);
            }

            public void a(String str, Object obj, boolean z) {
                this.a.a(str, z);
            }

            public void a(String str, Object obj, int i) {
                this.a.a(str, i);
            }
        };
        this.M = new dji.thirdparty.afinal.b.b(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            }

            public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            }
        };
    }
}
