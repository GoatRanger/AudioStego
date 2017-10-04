package dji.pilot2.academy.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.c.d;
import dji.pilot.usercenter.f.c;
import dji.pilot.usercenter.protocol.e;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot.usercenter.protocol.e$b;
import dji.pilot2.academy.model.AcademyDocInfo;
import dji.pilot2.academy.model.AcademyDocInfo.DocInfo;
import dji.pilot2.utils.k;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class b implements e {
    public static final int a = 1000;
    public static final String b = "academy.db";
    private static final String c = b.class.getSimpleName();
    private static final int d = 1;
    private static final boolean e = true;
    private static final String f = "academy/";
    private static final String g = ".pdf";
    private static final String h = "en";
    private Map<String, AcademyDocInfo> i;
    private List<DocInfo> j;
    private d k;
    private e$a l;
    private a m;
    private e$a n;
    private boolean o;
    private dji.thirdparty.afinal.b.b p;
    private final Comparator<DocInfo> q;
    private dji.thirdparty.afinal.b r;
    private Context s;
    private Boolean t;

    private static final class a extends Handler {
        private final WeakReference<b> a;

        public a(b bVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(bVar);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            b bVar = (b) this.a.get();
            if (bVar != null) {
                switch (message.what) {
                    case 1000:
                        bVar.c(2, message.arg1, message.obj);
                        return;
                    case 65536:
                        DJILogHelper.getInstance().LOGI("bob", "CallBackHandler SUCCESS");
                        bVar.a(message.arg1, message.arg2, message.obj);
                        return;
                    case 65537:
                        DJILogHelper.getInstance().LOGI("bob", "CallBackHandler FAIL");
                        bVar.b(message.arg1, message.arg2, message.obj);
                        return;
                    case e.aC /*65538*/:
                        DJILogHelper.getInstance().LOGI("bob", "CallBackHandler START");
                        int i = message.arg1;
                        if (message.arg2 != 1) {
                            z = false;
                        }
                        bVar.a(i, z, message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static final class b {
        private static final b a = new b();

        private b() {
        }
    }

    public String a(String str) {
        return a(str, g);
    }

    private String a(String str, String str2) {
        String a = com.dji.frame.c.d.a(this.s, f);
        if (str.endsWith(str2)) {
            return a + str;
        }
        return a + str + str2;
    }

    private b() {
        this.k = null;
        this.m = new a(this);
        this.o = false;
        this.p = null;
        this.q = new Comparator<DocInfo>(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((DocInfo) obj, (DocInfo) obj2);
            }

            public int a(DocInfo docInfo, DocInfo docInfo2) {
                return (int) (dji.pilot2.academy.b.a.a(docInfo2.updated_at) - dji.pilot2.academy.b.a.a(docInfo.updated_at));
            }
        };
        this.t = Boolean.valueOf(false);
        c();
    }

    private void c() {
        this.k = new d(this) {
            final /* synthetic */ b a;

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
        this.p = new dji.thirdparty.afinal.b.b(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            }

            public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            }
        };
        this.l = new e$a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i, long j, long j2, int i2, Object obj) {
            }

            public void a(int i, int i2, int i3, Object obj, Object obj2) {
                this.a.m.obtainMessage(65536, i, 0, obj).sendToTarget();
            }

            public void a(int i, boolean z, int i2, Object obj) {
                this.a.m.obtainMessage(e.aC, i, z ? 1 : 0, obj).sendToTarget();
            }

            public void a(int i, int i2, int i3, Object obj) {
                this.a.m.obtainMessage(65537, i, i2, obj).sendToTarget();
            }
        };
    }

    public void a(Context context) {
        if (!this.t.booleanValue()) {
            this.s = context.getApplicationContext();
            dji.thirdparty.afinal.b.a aVar = new dji.thirdparty.afinal.b.a();
            aVar.a(this.s);
            aVar.a(b);
            aVar.a(1);
            aVar.a(this.p);
            this.r = dji.thirdparty.afinal.b.a(aVar);
            this.i = new HashMap();
            this.j = this.r.c(DocInfo.class);
            for (int i = 0; i < this.j.size(); i++) {
                DJILogHelper.getInstance().LOGI("bob", "" + ((DocInfo) this.j.get(i)).toString());
            }
            b(this.j);
        }
        this.t = Boolean.valueOf(true);
    }

    public void a(e$a dji_pilot_usercenter_protocol_e_a) {
        this.n = dji_pilot_usercenter_protocol_e_a;
    }

    public void a() {
        this.n = null;
    }

    public void a(DocInfo docInfo) {
        if (docInfo != null) {
            docInfo.mbNew = false;
            this.r.e(docInfo);
            int i = 0;
            while (i < this.j.size()) {
                DocInfo docInfo2 = (DocInfo) this.j.get(i);
                if (docInfo2 != null && docInfo2.getUrl().equals(docInfo.getUrl())) {
                    docInfo2.mDownloadState = 3;
                    break;
                }
                i++;
            }
            if (i >= this.j.size()) {
                this.j.add(docInfo);
            }
        }
    }

    public void a(DocInfo docInfo, boolean z) {
        if (docInfo != null) {
            if (z) {
                this.r.a(docInfo);
            } else {
                this.r.e(docInfo);
            }
            int i = 0;
            while (i < this.j.size()) {
                DocInfo docInfo2 = (DocInfo) this.j.get(i);
                if (docInfo2 != null && docInfo2.getUrl().equals(docInfo.getUrl())) {
                    docInfo2.mDownloadState = docInfo.mDownloadState;
                    docInfo2.mProgress = docInfo.mProgress;
                    break;
                }
                i++;
            }
            if (i >= this.j.size()) {
                this.j.add(docInfo);
            }
        }
    }

    public String a(Context context, String str) {
        return k.c(context, str);
    }

    public List<DocInfo> b(Context context, String str) {
        return ((AcademyDocInfo) this.i.get(a(context, str))).mDocInfos;
    }

    public void c(Context context, String str) {
        this.o = true;
        String a = a(context, str);
        DJILogHelper.getInstance().LOGI("bob", "requestAcademyDocInfos url = " + a);
        if (this.i.containsKey(a)) {
            AcademyDocInfo academyDocInfo = (AcademyDocInfo) this.i.get(a);
            long currentTimeMillis = System.currentTimeMillis() - academyDocInfo.mGetTime;
            DJILogHelper.getInstance().LOGI("bob", "requestAcademyDocInfos duration = " + currentTimeMillis);
            if (currentTimeMillis < 500) {
                this.m.obtainMessage(1000, 2, 0, academyDocInfo).sendToTarget();
                return;
            }
        }
        dji.pilot2.academy.b.a.a(context, a, null, this.l, 2);
    }

    public boolean b() {
        return this.o;
    }

    private void a(AcademyDocInfo academyDocInfo, List<DocInfo> list, String str) {
        if (list == null || academyDocInfo == null) {
            DJILogHelper.getInstance().LOGI("bob", "dealDBCache cacheInfos = null or netInfos=null" + list.toString() + "  " + academyDocInfo.toString());
            return;
        }
        List list2 = academyDocInfo.mDocInfos;
        if (list == null || list2 == null) {
            DJILogHelper.getInstance().LOGI("bob", "dealDBCache docs==null ");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            DocInfo docInfo = (DocInfo) list.get(i);
            if (docInfo != null && docInfo.listUrl.equals(str)) {
                int i2 = 0;
                while (i2 < list2.size()) {
                    DocInfo docInfo2 = (DocInfo) list2.get(i2);
                    if (docInfo2 == null || !docInfo2.url.equals(docInfo.url)) {
                        i2++;
                    } else if (dji.pilot2.academy.b.a.a(docInfo2.updated_at) > dji.pilot2.academy.b.a.a(docInfo.updated_at)) {
                        docInfo2.mbNew = false;
                        docInfo.mbNew = false;
                        docInfo2.mDownloadState = 0;
                        docInfo.mDownloadState = 0;
                        docInfo2.mProgress = 0;
                        docInfo.mProgress = 0;
                        String a = a(docInfo.name);
                        if (c.a(a)) {
                            new File(a).delete();
                        }
                        this.r.f(docInfo);
                    } else {
                        docInfo2.mbNew = docInfo.mbNew;
                        docInfo2.mDownloadState = docInfo.mDownloadState;
                        docInfo2.mProgress = docInfo.mProgress;
                    }
                }
            }
        }
    }

    private void a(List<DocInfo> list) {
        Collections.sort(list, this.q);
    }

    private void a(int i, int i2, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess cmdId " + i);
        if (obj instanceof e$b) {
            DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess arg instanceof ProtocolResult " + i);
            e$b dji_pilot_usercenter_protocol_e_b = (e$b) obj;
            if (dji_pilot_usercenter_protocol_e_b.d instanceof AcademyDocInfo) {
                String str = (String) dji_pilot_usercenter_protocol_e_b.c;
                Object obj2 = (AcademyDocInfo) dji_pilot_usercenter_protocol_e_b.d;
                obj2.mGetTime = System.currentTimeMillis();
                a((AcademyDocInfo) obj2, this.j, str);
                this.i.put(str, obj2);
                if (this.n != null) {
                    this.n.a(i, i2, 0, Integer.valueOf(0), obj2);
                }
            } else {
                DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess not AcademyDocInfo");
            }
        } else {
            DJILogHelper.getInstance().LOGI("bob", "handleResultSuccess not ProtocolResult");
        }
        this.o = false;
    }

    private void b(int i, int i2, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "handleResultFail ");
        if (obj instanceof e$b) {
            obj = (e$b) obj;
            if (this.n != null) {
                this.n.a(i, i2, 0, obj);
            }
        }
        this.o = false;
    }

    private void a(int i, boolean z, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "handleResultStart ");
        if (obj instanceof e$b) {
            e$b dji_pilot_usercenter_protocol_e_b = (e$b) obj;
            if (this.n != null) {
                this.n.a(i, z, 0, obj);
            }
        }
    }

    public void b(DocInfo docInfo) {
        if (docInfo != null) {
            docInfo.mDownloadState = 1;
            dji.pilot.usercenter.b.c.getInstance().a(docInfo.getUrl(), a(docInfo.name), true, true, c, this.k);
        }
    }

    public void c(DocInfo docInfo) {
        if (docInfo != null) {
            dji.pilot.usercenter.b.c.getInstance().a(docInfo.getUrl());
            docInfo.mDownloadState = 0;
            a(docInfo, false);
        }
    }

    private void b(List<DocInfo> list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                DocInfo docInfo = (DocInfo) it.next();
                DJILogHelper.getInstance().LOGI("bob", docInfo.toString());
                if (d(docInfo)) {
                    it.remove();
                }
            }
        }
    }

    private boolean d(DocInfo docInfo) {
        if (docInfo == null) {
            return false;
        }
        DJILogHelper.getInstance().LOGI("bob", "checkDownloadState " + docInfo.toString());
        String a = a(docInfo.name);
        if (c.a(a)) {
            File file = new File(a);
            int intValue;
            if (docInfo.size != null) {
                try {
                    intValue = Integer.valueOf(docInfo.size).intValue();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    intValue = 0;
                }
            } else {
                intValue = 0;
            }
            if (docInfo.mDownloadState == 3 || file.length() < ((long) r0)) {
                DJILogHelper.getInstance().LOGI("bob", "checkDownloadState FileUtil.exist true SUPPORT_RESUME ");
                docInfo.mProgress = Integer.valueOf(docInfo.size).intValue() != 0 ? (int) ((((float) file.length()) / ((float) Integer.valueOf(docInfo.size).intValue())) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) : 0;
                return false;
            }
            DJILogHelper.getInstance().LOGI("bob", "checkDownloadState FileUtil.exist true file.length() >= Integer.valueOf(docInfo.size)");
            docInfo.mDownloadState = 3;
            return false;
        }
        DJILogHelper.getInstance().LOGI("bob", "checkDownloadState FileUtil.exist false");
        docInfo.mProgress = 0;
        this.r.f(docInfo);
        return true;
    }

    private List<DocInfo> b(String str) {
        List<DocInfo> arrayList = new ArrayList();
        for (Entry value : this.i.entrySet()) {
            AcademyDocInfo academyDocInfo = (AcademyDocInfo) value.getValue();
            if (!(academyDocInfo == null || academyDocInfo.mDocInfos == null || academyDocInfo.mDocInfos.size() == 0)) {
                for (int i = 0; i < academyDocInfo.mDocInfos.size(); i++) {
                    DocInfo docInfo = (DocInfo) academyDocInfo.mDocInfos.get(i);
                    if (docInfo != null && docInfo.getUrl().equals(str)) {
                        arrayList.add(docInfo);
                    }
                }
            }
        }
        return arrayList;
    }

    private void a(String str, int i, int i2) {
        List<Object> b = b(str);
        if (!b.isEmpty()) {
            for (Object obj : b) {
                if (i >= obj.mProgress + 2 || i == i2) {
                    obj.mProgress = i;
                    obj.mDownloadState = 2;
                    a((DocInfo) obj, false);
                    if (this.n != null) {
                        this.n.a(3, (long) i, (long) i2, 0, obj);
                    }
                }
            }
        }
    }

    private void a(String str, boolean z) {
        DJILogHelper.getInstance().LOGI("bob", "handleDownloadStart ");
        List<Object> b = b(str);
        if (!b.isEmpty()) {
            for (Object obj : b) {
                obj.mDownloadState = 1;
                obj.mProgress = 0;
                a((DocInfo) obj, true);
                if (this.n != null) {
                    this.n.a(3, z, 0, obj);
                }
            }
        }
    }

    private void b(String str, String str2) {
        DJILogHelper.getInstance().LOGI("bob", "handleDownloadSuccess ");
        List<Object> b = b(str);
        if (!b.isEmpty()) {
            for (Object obj : b) {
                if (str2 != null) {
                    obj.mDownloadState = 3;
                    obj.size = "" + new File(str2).length();
                    a((DocInfo) obj, false);
                    int i = 0;
                    while (i < this.j.size()) {
                        DocInfo docInfo = (DocInfo) this.j.get(i);
                        if (docInfo != null && docInfo.getUrl().equals(obj.getUrl())) {
                            docInfo.mDownloadState = 3;
                            break;
                        }
                        i++;
                    }
                    if (i >= this.j.size()) {
                        this.j.add(obj);
                    }
                    if (this.n != null) {
                        this.n.a(3, 0, 0, null, obj);
                    }
                }
            }
        }
    }

    private void a(String str, int i) {
        DJILogHelper.getInstance().LOGI("bob", "handleDownloadFail errCode = " + i);
        List<Object> b = b(str);
        if (!b.isEmpty()) {
            for (Object obj : b) {
                if (i == 416) {
                    c.e(a(obj.name));
                    obj.mProgress = 0;
                }
                obj.mDownloadState = 0;
                a((DocInfo) obj, false);
                if (this.n != null) {
                    this.n.a(3, i, 0, obj);
                }
            }
        }
    }

    private void c(int i, int i2, Object obj) {
        if (this.n != null) {
            this.n.a(i, i2, 0, Integer.valueOf(0), obj);
        }
    }

    public static b getInstance() {
        return b.a;
    }
}
