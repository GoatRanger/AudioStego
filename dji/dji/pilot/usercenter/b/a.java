package dji.pilot.usercenter.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;
import com.dji.frame.c.d;
import com.google.android.gms.common.api.CommonStatusCodes;
import dji.log.DJILogHelper;
import dji.midware.media.e.e;
import dji.pilot.usercenter.f.c;
import dji.pilot.usercenter.mode.b;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"DefaultLocale"})
public class a {
    public static boolean a = false;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 16;
    private static final int h = 17;
    private static final long i = 50;
    private static final int j = 0;
    private static final int k = 1;
    private static final int l = 2;
    private static final String m = "DJI Album/";
    private static final String n = "DJI_RECORD";
    private static final String o = "VideoEditor/segmentLibrary";
    private static final String p = "RECORD_VOICE";
    private static final String q = "DJI Album";
    private static final String r = "DCIM";
    private ArrayList<g> A;
    private e B;
    private c C;
    private volatile int D;
    private Context E;
    private boolean F;
    private String G;
    private volatile boolean H;
    private g s;
    private final ArrayList<a> t;
    private String u;
    private final ArrayList<b> v;
    private final ArrayList<a> w;
    private final ArrayList<g> x;
    private final ArrayList<b> y;
    private final ArrayList<d> z;

    public static a getInstance() {
        return f.a;
    }

    public boolean a(b bVar) {
        boolean z = false;
        if (bVar != null) {
            synchronized (this.v) {
                if (!this.v.contains(bVar)) {
                    this.v.add(bVar);
                    if (this.D == 0) {
                        this.s.sendMessageDelayed(this.s.obtainMessage(2, 2, 0), 200);
                    }
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean b(b bVar) {
        boolean z = false;
        if (bVar != null) {
            synchronized (this.v) {
                z = this.v.remove(bVar);
            }
        }
        return z;
    }

    private boolean f(String str) {
        if (str != null && str.length() > 0) {
            int size = this.z.size();
            for (int i = 0; i < size; i++) {
                if (((d) this.z.get(i)).a.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void g(String str) {
        if (str != null && str.length() > 0) {
            int size = this.z.size();
            for (int i = 0; i < size; i++) {
                if (((d) this.z.get(i)).a.equals(str)) {
                    this.z.remove(i);
                    return;
                }
            }
        }
    }

    private String d() {
        return d.a(this.E, "DJI Album/");
    }

    private String e() {
        return d.a(this.E, n);
    }

    private String f() {
        return d.a(this.E, o);
    }

    private String g() {
        return d.a(this.E, p);
    }

    private String h() {
        return d.a(this.E, q);
    }

    public void a(Context context) {
        if (!this.F) {
            this.E = context.getApplicationContext();
            this.F = true;
            if (this.G == null) {
                m();
            }
            String e = e();
            File file = new File(e);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (!f(e)) {
                this.z.add(new d(e, d.b));
                this.D++;
            }
            e = d();
            file = new File(e);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (!f(e)) {
                this.z.add(new d(e, d.b));
                this.D++;
            }
            for (String e2 : n()) {
                if (!f(e2 + dji.pilot.usercenter.f.b.f)) {
                    this.z.add(new d(e2 + dji.pilot.usercenter.f.b.f, e2));
                    this.D++;
                }
            }
            if (this.B == null) {
                this.B = new e("album_scan");
                this.B.start();
                this.C = new c(this, this.B.getLooper());
            }
            Iterator it = this.z.iterator();
            while (it.hasNext()) {
                this.C.obtainMessage(16, (d) it.next()).sendToTarget();
            }
        }
    }

    public void b(Context context) {
        if (!this.F) {
            this.E = context.getApplicationContext();
            this.F = true;
            if (this.G == null) {
                m();
            }
            String e = e();
            File file = new File(e);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (!f(e)) {
                this.z.add(new d(e, d.b));
                this.D++;
            }
            e = g();
            if (!f(e)) {
                this.z.add(new d(e, d.b));
                this.D++;
            }
            e = h();
            if (!f(e)) {
                this.z.add(new d(e, d.b));
                this.D++;
            }
            e = f();
            if (!f(e)) {
                this.z.add(new d(e, d.b));
                this.D++;
            }
            for (String e2 : n()) {
                if (!f(e2 + dji.pilot.usercenter.f.b.f)) {
                    this.z.add(new d(e2 + dji.pilot.usercenter.f.b.f, e2));
                    this.D++;
                }
            }
            if (this.B == null) {
                this.B = new e("album_scan");
                this.B.start();
                this.C = new c(this, this.B.getLooper());
            }
            Iterator it = this.z.iterator();
            while (it.hasNext()) {
                this.C.obtainMessage(16, (d) it.next()).sendToTarget();
            }
        }
    }

    public void c(Context context) {
        if (!this.F) {
            this.E = context.getApplicationContext();
            this.F = true;
            if (this.G == null) {
                m();
            }
            String c = dji.pilot2.library.b.c(context);
            File file = new File(c);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (!f(c)) {
                this.z.add(new d(c, d.b));
                this.D++;
            }
            for (String str : dji.pilot.storage.a.e(context)) {
                if (!TextUtils.isEmpty(str)) {
                    File file2 = new File(str);
                    if (file2.exists() && file2.isDirectory() && !f(str)) {
                        this.z.add(new d(str, d.b));
                        this.D++;
                    }
                }
            }
            c = dji.pilot2.library.b.b(context);
            file = new File(c);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (!f(c)) {
                this.z.add(new d(c, d.b));
                this.D++;
            }
            c = dji.pilot2.library.b.e(context);
            file = new File(c);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (!f(c)) {
                this.z.add(new d(c, d.b));
                this.D++;
            }
            c = dji.pilot2.library.b.d(context);
            file = new File(c);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (!f(c)) {
                this.z.add(new d(c, d.b));
                this.D++;
            }
            c = dji.pilot2.library.b.a(context);
            file = new File(c);
            if (!(file.exists() || file.isDirectory())) {
                file.mkdirs();
            }
            if (file.exists() && !f(c)) {
                this.z.add(new d(c, d.b));
                this.D++;
            }
            for (String c2 : n()) {
                if (!f(c2 + dji.pilot.usercenter.f.b.f)) {
                    this.z.add(new d(c2 + dji.pilot.usercenter.f.b.f, c2));
                    this.D++;
                }
            }
            if (this.B == null) {
                this.B = new e("album_scan");
                this.B.start();
                this.C = new c(this, this.B.getLooper());
            }
            Iterator it = this.z.iterator();
            while (it.hasNext()) {
                this.C.obtainMessage(16, (d) it.next()).sendToTarget();
            }
        }
    }

    public void d(Context context) {
        if (this.B != null) {
            this.B = null;
            this.C.removeMessages(16);
            this.C = null;
        }
        if (this.s != null) {
            this.s.removeMessages(0);
            this.s.removeMessages(1);
            this.s.removeMessages(2);
            this.C = null;
        }
        this.z.clear();
        this.t.clear();
        this.F = false;
    }

    public static void a(File file) {
        f.a.F = true;
        f.a.b(file);
    }

    public void b(File file) {
        Log.d(DJIPhotoPreveiwActivity.N, "a");
        if (this.F && c.a(file)) {
            Log.d(DJIPhotoPreveiwActivity.N, dji.pilot.usercenter.protocol.d.A);
            synchronized (this.t) {
                String d = d();
                Iterator it = this.t.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (d.equals(aVar.a)) {
                        g a;
                        Object obj;
                        Log.d(DJIPhotoPreveiwActivity.N, "downPath.equals(entity.mScanPath)");
                        List<g> list = aVar.d;
                        for (g a2 : list) {
                            if (file.getAbsolutePath().equals(a2.e)) {
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            a2 = g.a(file, false);
                            if (a2 != null) {
                                a((List) list, a2);
                                Log.d(DJIPhotoPreveiwActivity.N, "add list");
                            }
                        }
                    }
                }
            }
        }
    }

    public void c(File file) {
        if (this.F && c.a(file)) {
            synchronized (this.t) {
                String e = e();
                Iterator it = this.t.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (e.equals(aVar.a)) {
                        g b;
                        Object obj;
                        List<g> list = aVar.d;
                        for (g b2 : list) {
                            if (file.getAbsolutePath().equals(b2.e)) {
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            b2 = g.b(file, false);
                            if (b2 != null) {
                                a((List) list, b2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(File file, boolean z) {
        if (this.F && c.a(file)) {
            synchronized (this.t) {
                String f = f();
                Iterator it = this.t.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (f.equals(aVar.a)) {
                        g b;
                        Object obj;
                        List<g> list = aVar.d;
                        for (g b2 : list) {
                            if (file.getAbsolutePath().equals(b2.e)) {
                                obj = 1;
                                break;
                            }
                        }
                        obj = null;
                        if (obj == null) {
                            b2 = g.b(file, false);
                            if (z) {
                                b2.z = true;
                            }
                            if (b2 != null) {
                                a((List) list, b2);
                            }
                        }
                    }
                }
            }
        }
    }

    public void d(File file) {
        if (this.F && c.a(file)) {
            synchronized (this.t) {
                String e = e();
                Iterator it = this.t.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (e.equals(aVar.a)) {
                        List<g> list = aVar.d;
                        for (g gVar : list) {
                            if (file.getAbsolutePath().equals(gVar.e)) {
                                list.remove(gVar);
                                DJILogHelper.getInstance().LOGD("O", "remove", false, true);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(String str) {
        if (str != null && this.F) {
            synchronized (this.t) {
                String e = e();
                Iterator it = this.t.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (e.equals(aVar.a)) {
                        List<g> list = aVar.d;
                        for (g gVar : list) {
                            if (str.equals(gVar.e)) {
                                list.remove(gVar);
                                DJILogHelper.getInstance().LOGD("O", "remove", false, true);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void b(String str) {
        if (str != null && this.F) {
            synchronized (this.t) {
                String f = f();
                Iterator it = this.t.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (f.equals(aVar.a)) {
                        List<g> list = aVar.d;
                        for (g gVar : list) {
                            if (str.equals(gVar.e)) {
                                list.remove(gVar);
                                File file = new File(str);
                                if (file.exists()) {
                                    if (file.delete()) {
                                        DJILogHelper.getInstance().LOGD("O", "removeMomentFileSuccess", false, true);
                                    } else {
                                        DJILogHelper.getInstance().LOGD("O", "removeMomentFileFail", false, true);
                                    }
                                }
                                e.e(str);
                            }
                        }
                        continue;
                    }
                }
            }
        }
    }

    public void c(String str) {
        if (str != null && this.F) {
            synchronized (this.t) {
                String f = f();
                Iterator it = this.t.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (f.equals(aVar.a)) {
                        for (g gVar : aVar.d) {
                            if (str.equals(gVar.e)) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void d(String str) {
        if (this.G == null) {
            m();
        }
        if (!str.equals(this.G)) {
            if (str.charAt(str.length() - 1) == File.separatorChar) {
                str = str.substring(0, str.length() - 1);
            }
            String str2 = str + dji.pilot.usercenter.f.b.f;
            if (c.c(str2) && !f(str2)) {
                d dVar = new d(str2, str);
                this.z.add(dVar);
                this.D++;
                if (this.F) {
                    if (this.B == null) {
                        this.B = new e("album_scan");
                        this.B.start();
                        this.C = new c(this, this.B.getLooper());
                    }
                    this.C.obtainMessage(16, dVar).sendToTarget();
                }
            }
        }
    }

    public void e(String str) {
        if (this.G == null) {
            m();
        }
        if (!str.equals(this.G)) {
            if (str.charAt(str.length() - 1) == File.separatorChar) {
                str = str.substring(0, str.length() - 1);
            }
            if (f(str + dji.pilot.usercenter.f.b.f)) {
                g(str + dji.pilot.usercenter.f.b.f);
                this.D = this.D == 0 ? 0 : this.D - 1;
                b(str, 0);
            }
        }
    }

    public List<a> a() {
        j();
        synchronized (this.t) {
            Iterator it = this.t.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (!aVar.d.isEmpty()) {
                    a aVar2 = new a();
                    aVar2.b = aVar.b;
                    aVar2.d.addAll(aVar.d);
                    this.w.add(aVar2);
                }
            }
        }
        return this.w;
    }

    public List<a> b() {
        j();
        synchronized (this.t) {
            Iterator it = this.t.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                a aVar2 = new a();
                aVar2.b = aVar.b;
                aVar2.d.addAll(aVar.d);
                this.w.add(aVar2);
            }
        }
        return this.w;
    }

    public void a(List<g> list) {
        this.A = (ArrayList) list;
    }

    public List<g> c() {
        return this.A;
    }

    public void b(List<b> list) {
        this.y.clear();
        if (!(list == null || list.isEmpty())) {
            this.y.addAll(list);
        }
        i();
        b(this.u, 2);
    }

    public void c(List<g> list) {
        this.C.obtainMessage(17, list).sendToTarget();
    }

    private void i() {
        if (!this.y.isEmpty() && !this.x.isEmpty()) {
            int size = this.y.size();
            int size2 = this.x.size();
            for (int i = 0; i < size2; i++) {
                int i2;
                g gVar = (g) this.x.get(i);
                if (gVar.f != null) {
                    for (int i3 = 0; i3 < size; i3++) {
                        b bVar = (b) this.y.get(i3);
                        if (bVar.f == gVar.i && gVar.f.equals(bVar.c)) {
                            gVar.l = 2;
                            i2 = 1;
                            break;
                        }
                    }
                }
                i2 = 0;
                if (i2 == 0) {
                    gVar.l = 0;
                }
            }
        }
    }

    private void j() {
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            aVar.b = null;
            aVar.d.clear();
        }
        this.w.clear();
    }

    private void a(g gVar, String str) {
        if (gVar != null && f(str)) {
            synchronized (this.t) {
                a aVar = new a();
                aVar.b = gVar;
                aVar.a = str;
                this.t.add(aVar);
            }
        }
    }

    private void a(List<g> list, g gVar) {
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size && ((g) list.get(i)).h >= gVar.h) {
                i++;
            }
            list.add(i, gVar);
        }
    }

    private void b(g gVar, String str) {
        if (gVar != null && f(str)) {
            synchronized (this.t) {
                int size = this.t.size();
                if (size == 0) {
                    return;
                }
                a(((a) this.t.get(size - 1)).d, gVar);
            }
        }
    }

    private void h(String str) {
        if (str != null) {
            synchronized (this.t) {
                int size = this.t.size();
                int i = 0;
                while (i < size) {
                    int i2;
                    if (str.equals(((a) this.t.get(i)).a)) {
                        this.t.remove(i);
                        i2 = size - 1;
                    } else {
                        i2 = size;
                    }
                    i++;
                    size = i2;
                }
            }
        }
    }

    private b k() {
        b bVar = null;
        synchronized (this.v) {
            if (!this.v.isEmpty()) {
                bVar = (b) this.v.get(0);
            }
        }
        return bVar;
    }

    private void i(String str) {
        b k = k();
        if (k != null) {
            k.c(str, 0, null);
        }
    }

    private void a(String str, int i) {
        b k = k();
        if (k != null) {
            k.a(str, i, null);
        }
    }

    private void l() {
        b k = k();
        if (k != null) {
            k.a(0);
        }
    }

    private void b(String str, int i) {
        if (!(str == null || f(str))) {
            h(str + dji.pilot.usercenter.f.b.f);
        }
        if (this.D == 0) {
            if (i != 2) {
                this.x.clear();
                synchronized (this.t) {
                    Iterator it = this.t.iterator();
                    while (it.hasNext()) {
                        a aVar = (a) it.next();
                        if (!aVar.d.isEmpty()) {
                            this.x.addAll(aVar.d);
                        }
                    }
                }
                i();
            }
            b k = k();
            if (k != null) {
                k.b(str, 0, null);
            }
        }
    }

    private a() {
        this.s = null;
        this.t = new ArrayList();
        this.u = null;
        this.v = new ArrayList(2);
        this.w = new ArrayList();
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.z = new ArrayList(3);
        this.A = new ArrayList();
        this.B = null;
        this.C = null;
        this.D = 0;
        this.E = null;
        this.F = false;
        this.G = null;
        this.H = false;
        this.s = new g(this);
    }

    private void m() {
        if ("mounted".equals(Environment.getExternalStorageState())) {
            this.G = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }

    private List<String> n() {
        if (this.G == null) {
            m();
        }
        StorageManager storageManager = (StorageManager) this.E.getSystemService("storage");
        Class cls = storageManager.getClass();
        List<String> arrayList = new ArrayList();
        try {
            Method declaredMethod = cls.getDeclaredMethod("getVolumePaths", new Class[0]);
            declaredMethod.setAccessible(true);
            String[] strArr = (String[]) declaredMethod.invoke(storageManager, new Object[0]);
            if (strArr != null && strArr.length > 0) {
                for (String str : strArr) {
                    String str2;
                    if (!str2.equals(this.G)) {
                        if (str2.charAt(str2.length() - 1) == File.separatorChar) {
                            str2 = str2.substring(0, str2.length() - 1);
                        }
                        if (c.c(str2 + dji.pilot.usercenter.f.b.f)) {
                            arrayList.add(str2);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return arrayList;
    }

    private void a(String str, String str2) {
        File file = new File(str);
        if (file != null && file.isDirectory()) {
            if (!(str2.equals(g()) || str2.equals(h()) || str2.equals(dji.pilot2.library.b.a(this.E)) || str2.equals(dji.pilot2.library.b.h(this.E)) || str2.equals(dji.pilot.storage.a.e(this.E)[0]) || str2.equals(dji.pilot.storage.a.e(this.E)[1]))) {
                if (str2.equals(e())) {
                    a(g.b(file, false), str2);
                } else {
                    a(g.a(file, false), str2);
                }
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                int length = listFiles.length;
                int i = 0;
                for (int i2 = 0; i2 < length && f(str2) && this.D > 0; i2++) {
                    File file2 = listFiles[i2];
                    if (!(file2.getName().startsWith(".") || file2.getName().startsWith(".."))) {
                        if (file2.isFile() && file2.length() != 0) {
                            if (str2.equals(e()) || str2.equals(g()) || str2.equals(h())) {
                                if (dji.pilot2.library.b.a(file2.getName())) {
                                    g b = g.b(file2, false);
                                    if (b.n >= 10) {
                                        if (b.v < CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS && b.u < 2000 && b.v > 0 && b.u > 0) {
                                            Log.i("ui", "add path:" + b.e);
                                            b(b, str2);
                                            i++;
                                        }
                                    }
                                }
                            }
                            if (dji.pilot2.library.b.d(file2.getName()) || dji.pilot2.library.b.c(file2.getName())) {
                                g a = g.a(file2, false);
                                if (str2.equals(dji.pilot2.library.b.a(this.E))) {
                                    a.z = true;
                                }
                                if (a.e != null) {
                                    try {
                                        dji.pilot2.media.a aVar = new dji.pilot2.media.a();
                                        aVar.a(a.e);
                                        if (aVar.a() > 0 && aVar.d()) {
                                            b(a, str2);
                                            i++;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (i % 5 == 0 && !this.s.hasMessages(1)) {
                                    this.s.sendMessageDelayed(this.s.obtainMessage(1, 0, 0, str2), 50);
                                }
                            }
                        } else if (file2.isDirectory()) {
                            a(file2.getAbsolutePath(), str2);
                            this.s.obtainMessage(1, 0, 0, str2).sendToTarget();
                        }
                    }
                }
            }
        }
    }

    private void a(d dVar) {
        Log.i("origin", "doScanPath:" + dVar.a);
        this.s.obtainMessage(0, 0, 0, dVar.a).sendToTarget();
        a(dVar.a, dVar.a);
        this.s.removeMessages(1);
        this.D = this.D == 0 ? 0 : this.D - 1;
        this.s.obtainMessage(2, 1, 0, dVar.a).sendToTarget();
    }

    private void a(g gVar) {
        int lastIndexOf = gVar.e.lastIndexOf(".");
        if (-1 != lastIndexOf) {
            c.e(gVar.e.substring(0, lastIndexOf) + ".h264");
            c.e(gVar.e.substring(0, lastIndexOf) + ".info");
        }
    }

    private void a(Object obj) {
        try {
            List list = (List) obj;
            synchronized (this.t) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    g gVar = (g) list.get(i);
                    int size2 = this.t.size();
                    int i2 = 0;
                    while (i2 < size2) {
                        a aVar = (a) this.t.get(i2);
                        if (gVar.e.contains(aVar.a)) {
                            aVar.d.remove(gVar);
                            c.e(gVar.e);
                            c.e(gVar.e + ".info");
                            if (gVar.o) {
                                a(gVar);
                            }
                        } else {
                            i2++;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        this.s.sendEmptyMessageDelayed(4, 200);
    }
}
