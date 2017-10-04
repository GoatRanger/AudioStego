package dji.sdksharedlib.hardware.abstractions;

import android.text.TextUtils;
import dji.common.error.DJIError;
import dji.common.error.DJISDKCacheError;
import dji.log.DJILog;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.c.a;
import dji.sdksharedlib.b.d;
import dji.sdksharedlib.c.h;
import dji.sdksharedlib.hardware.a.i;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class b {
    protected static i B = new i();
    private static final String a = "DJISDKCacheHWAbstraction";
    protected static final int v = 0;
    protected static final int w = 1;
    protected static final int x = 2;
    protected static final int y = 3;
    protected f A;
    protected Map<String, c> C;
    protected Map<String, c> D;
    protected Map<String, Map<Integer, b>> E;
    private Map<String, Method> b;
    private Map<String, Method> c;
    private Map<String, Method> d;
    protected c z;

    public interface e {
        void a(DJIError dJIError);

        void a(Object obj);
    }

    protected abstract void a();

    public void a(String str, int i, dji.sdksharedlib.d.c cVar, f fVar) {
        DJILog.i(a, String.format("init, abstraction : %s, component : %s, index : %d", new Object[]{getClass().getSimpleName(), str, Integer.valueOf(i)}));
        this.A = fVar;
        DJILog.i("DJISDKMergeHandler", "init");
        this.z = new a().b(str).a(Integer.valueOf(i)).a();
        this.D = new HashMap();
        j();
        this.E = new HashMap();
        a(cVar);
        f();
        a(Boolean.valueOf(true), c(d.ck));
    }

    public void e() {
        if (!(this.E == null || this.E.entrySet().isEmpty())) {
            for (Entry value : this.E.entrySet()) {
                for (Entry value2 : ((Map) value.getValue()).entrySet()) {
                    b bVar = (b) value2.getValue();
                    if (bVar != null) {
                        bVar.e();
                    }
                }
            }
        }
        this.A = null;
        DJILog.i(a, String.format("destroy, abstraction : %s, defaultKeyPath : %s", new Object[]{getClass().getSimpleName(), this.z.toString()}));
    }

    protected void f() {
        this.b = new HashMap();
        this.c = new HashMap();
        this.d = new HashMap();
        DJILog.d(a, "class : " + getClass().toString());
        List<Method> arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(getClass().getMethods()));
        arrayList.addAll(a(getClass()));
        DJILog.d(a, "methods size : " + arrayList.size());
        for (Method method : arrayList) {
            e eVar = (e) method.getAnnotation(e.class);
            if (eVar != null) {
                this.b.put(eVar.a(), method);
            } else {
                f fVar = (f) method.getAnnotation(f.class);
                if (fVar != null) {
                    this.c.put(fVar.a(), method);
                } else {
                    a aVar = (a) method.getAnnotation(a.class);
                    if (aVar != null) {
                        this.d.put(aVar.a(), method);
                    }
                }
            }
        }
    }

    public boolean a(c cVar) {
        String f = cVar.f();
        if (f != null && this.D.containsKey(f)) {
            return ((c) this.D.get(f)).c();
        }
        return false;
    }

    public DJISDKCacheUpdateType b(c cVar) {
        if (cVar == null) {
            return null;
        }
        String f = cVar.f();
        if (f == null || !this.D.containsKey(f)) {
            return null;
        }
        return ((c) this.D.get(f)).b;
    }

    protected String b(String str) {
        if (str != null && this.D.containsKey(str)) {
            return ((c) this.D.get(str)).a;
        }
        return null;
    }

    protected c c(String str) {
        return this.z.c(str);
    }

    public ArrayList<String> g() {
        ArrayList<String> arrayList = new ArrayList();
        for (c cVar : this.C.values()) {
            if (cVar.a()) {
                arrayList.add(cVar.a);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public ArrayList<String> h() {
        ArrayList<String> arrayList = new ArrayList();
        for (c cVar : this.C.values()) {
            if (cVar.b()) {
                arrayList.add(cVar.a);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    public ArrayList<String> i() {
        ArrayList<String> arrayList = new ArrayList();
        for (c cVar : this.C.values()) {
            if (cVar.c()) {
                arrayList.add(cVar.a);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return arrayList;
    }

    protected void a(Object obj, String str) {
        if (this.A != null) {
            this.A.a(obj, c(str));
        }
    }

    protected void a(Object obj, String str, g gVar) {
        if (this.A != null) {
            this.A.a(obj, c(str), gVar);
        }
    }

    protected void b(Object obj, String str) {
        if (this.z != null) {
            a(obj, c(str));
        }
    }

    protected void a(Object obj, c cVar) {
        if (this.A != null) {
            this.A.b(obj, cVar);
        }
    }

    private <T> T[] a(T[] tArr, T t) {
        int length;
        if (tArr != null) {
            length = tArr.length;
            Object copyOf = Arrays.copyOf(tArr, length + 1);
            System.arraycopy(copyOf, 0, copyOf, 1, length);
            copyOf[0] = t;
            return copyOf;
        } else if (tArr == null) {
            return (Object[]) Array.newInstance(t.getClass().getComponentType(), new int[0]);
        } else {
            length = tArr.length;
            T[] copyOf2 = Arrays.copyOf(tArr, length + 1);
            System.arraycopy(copyOf2, 0, copyOf2, 1, length);
            copyOf2[0] = t;
            return copyOf2;
        }
    }

    protected boolean a(c cVar, dji.sdksharedlib.c.c cVar2) {
        return false;
    }

    protected boolean a(c cVar, Object obj, h hVar) {
        return false;
    }

    protected boolean a(c cVar, dji.sdksharedlib.c.b bVar, Object... objArr) {
        return false;
    }

    public void b(c cVar, dji.sdksharedlib.c.c cVar2) {
        CharSequence d = cVar.d();
        if (TextUtils.isEmpty(d)) {
            d = cVar.f();
            if (!TextUtils.isEmpty(d) && this.C.containsKey(d) && this.b.containsKey(d)) {
                try {
                    ((Method) this.b.get(d)).invoke(this, new Object[]{new b(this, cVar, cVar2)});
                } catch (Exception e) {
                    e.printStackTrace();
                    if (cVar2 != null) {
                        cVar2.a(DJISDKCacheError.INVALID_KEY_FOR_COMPONENT);
                    }
                }
            } else if (!a(cVar, cVar2) && cVar2 != null) {
                cVar2.a(DJISDKCacheError.KEY_UNSUPPORTED);
            }
        } else if (this.E.containsKey(d)) {
            c(cVar, cVar2);
        } else if (cVar2 != null) {
            cVar2.a(DJISDKCacheError.KEY_UNSUPPORTED);
        }
    }

    public void b(c cVar, Object obj, h hVar) {
        CharSequence d = cVar.d();
        if (TextUtils.isEmpty(d)) {
            Object f = cVar.f();
            if (!TextUtils.isEmpty(f) && this.C.containsKey(f) && this.c.containsKey(f)) {
                if (((c) this.C.get(cVar.f())).a(new Object[]{obj})) {
                    try {
                        ((Method) this.c.get(f)).invoke(this, new Object[]{obj, new c(this, f, obj, hVar)});
                    } catch (Exception e) {
                        if (hVar != null) {
                            hVar.a(DJISDKCacheError.INVALID_KEY_FORMAT);
                        }
                    }
                } else if (hVar != null) {
                    hVar.a(DJISDKCacheError.INVALID_VALUE_TYPE);
                }
            } else if (!a(cVar, obj, hVar) && hVar != null) {
                hVar.a(DJISDKCacheError.KEY_UNSUPPORTED);
            }
        } else if (this.E.containsKey(d)) {
            c(cVar, obj, hVar);
        } else if (hVar != null) {
            hVar.a(DJISDKCacheError.KEY_UNSUPPORTED);
        }
    }

    public void b(c cVar, dji.sdksharedlib.c.b bVar, Object... objArr) {
        String d = cVar.d();
        if (TextUtils.isEmpty(cVar.d())) {
            Object f = cVar.f();
            if (!TextUtils.isEmpty(f) && this.C.containsKey(f) && this.d.containsKey(f)) {
                if (((c) this.C.get(cVar.f())).a(objArr)) {
                    try {
                        ((Method) this.d.get(f)).invoke(this, a(objArr, new a(this, f, bVar, objArr)));
                    } catch (Exception e) {
                        DJILog.e(a, "invoke action method failed: " + e.getMessage());
                        if (bVar != null) {
                            bVar.a(DJISDKCacheError.INVALID_KEY_FOR_COMPONENT);
                        }
                    }
                } else if (bVar != null) {
                    bVar.a(DJISDKCacheError.INVALID_VALUE_TYPE);
                }
            } else if (!a(cVar, bVar, objArr) && bVar != null) {
                bVar.a(DJISDKCacheError.KEY_UNSUPPORTED);
            }
        } else if (this.E.containsKey(d)) {
            c(cVar, bVar, objArr);
        } else if (bVar != null) {
            bVar.a(DJISDKCacheError.KEY_UNSUPPORTED);
        }
    }

    protected String a(String str, int i) {
        if (i == 1) {
            return a(str);
        }
        if (i != 0) {
            return h(str);
        }
        return str;
    }

    private String a(String str) {
        return com.dji.frame.c.a.b(str).substring(0, 8);
    }

    private String g(String str) {
        return com.dji.frame.c.a.a(str).substring(0, 8);
    }

    private String h(String str) {
        return com.dji.frame.c.a.a(str);
    }

    protected String d(String str) {
        return com.dji.frame.c.a.b(str).substring(0, 8);
    }

    protected void a(String str, int i, DJISDKCacheUpdateType dJISDKCacheUpdateType, Class... clsArr) {
        if (!this.C.containsKey(str)) {
            c cVar = new c(str, i, dJISDKCacheUpdateType, clsArr);
            this.C.put(str, cVar);
            this.D.put(str, cVar);
        }
    }

    protected void a(Class<? extends d> cls, Class cls2) {
        Map a = d.a((Class) cls);
        if (a != null) {
            for (String str : a.keySet()) {
                d.a aVar = (d.a) a.get(str);
                if (aVar.a(cls2)) {
                    dji.sdksharedlib.b.b.d b = aVar.b(cls2);
                    if (b != null) {
                        if (b.b() == null || b.b().length <= 0) {
                            a(str, b.c(), b.d(), b.a());
                        } else {
                            a(str, b.c(), b.d(), b.b());
                        }
                    }
                }
            }
        }
    }

    protected void a(String str, int i, DJISDKCacheUpdateType dJISDKCacheUpdateType, Class cls) {
        if (this.C.containsKey(str)) {
            throw new RuntimeException("keyMap had contains this key, please check you logic.");
        }
        dji.sdksharedlib.hardware.a.d dVar = new dji.sdksharedlib.hardware.a.d(str, i, dJISDKCacheUpdateType, cls);
        this.C.put(str, dVar);
        this.D.put(str, dVar);
    }

    protected void a(String str, int i, DJISDKCacheUpdateType dJISDKCacheUpdateType) {
        a(str, i, dJISDKCacheUpdateType, null);
    }

    protected void e(String str) {
        this.C.remove(str);
        this.D.remove(str);
    }

    protected void f(String str) {
        this.C.remove(str);
        this.D.remove(str);
    }

    protected void a(String str, Method method) {
        this.c.put(str, method);
    }

    protected void b(String str, Method method) {
        this.b.put(str, method);
    }

    protected void c(String str, Method method) {
        this.d.put(str, method);
    }

    public dji.sdksharedlib.hardware.a.d a(c cVar) {
        return (dji.sdksharedlib.hardware.a.d) cVar;
    }

    protected void j() {
        k();
        a();
    }

    protected void k() {
        this.C = new HashMap();
    }

    private void c(c cVar, dji.sdksharedlib.c.c cVar2) {
        String d = cVar.d();
        ((b) ((Map) this.E.get(d)).get(cVar.e())).b(cVar.i(), cVar2);
    }

    private void c(c cVar, Object obj, h hVar) {
        String d = cVar.d();
        ((b) ((Map) this.E.get(d)).get(cVar.e())).b(cVar.i(), obj, hVar);
    }

    private void c(c cVar, dji.sdksharedlib.c.b bVar, Object... objArr) {
        String d = cVar.d();
        ((b) ((Map) this.E.get(d)).get(cVar.e())).b(cVar.i(), bVar, objArr);
    }

    protected void a(dji.sdksharedlib.d.c cVar) {
    }

    protected void a(b bVar, String str, int i, dji.sdksharedlib.d.c cVar) {
        if (bVar != null) {
            Map map;
            if (bVar instanceof d) {
                ((d) bVar).a(this.z.b(), this.z.c().intValue(), str, i, cVar, this.A);
            } else {
                bVar.a(str, i, cVar, this.A);
            }
            if (this.E.containsKey(str)) {
                map = (Map) this.E.get(str);
            } else {
                map = new HashMap();
            }
            map.put(Integer.valueOf(i), bVar);
            this.E.put(str, map);
        }
    }

    public void a(c cVar, DJISDKCacheError dJISDKCacheError) {
    }

    public void a(String str, c cVar, dji.sdksharedlib.c.c cVar2, int i) {
    }

    public c l() {
        return null;
    }

    public ArrayList<String> m() {
        return null;
    }

    private List<Method> a(Class<?> cls) {
        List<Method> arrayList = new ArrayList();
        Class superclass = cls.getSuperclass();
        while (superclass != null && superclass != b.class) {
            arrayList.addAll(Arrays.asList(superclass.getMethods()));
            superclass = superclass.getSuperclass();
        }
        return arrayList;
    }

    public Map<String, Method> n() {
        return this.b;
    }

    public Map<String, Method> o() {
        return this.c;
    }

    public boolean c(c cVar) {
        if (this.C.containsKey(cVar.f())) {
            return ((c) this.C.get(cVar.f())).d();
        }
        return false;
    }

    public int d(c cVar) {
        CharSequence d = cVar.d();
        if (!TextUtils.isEmpty(d)) {
            this.E.containsKey(d);
            return e(cVar);
        } else if (this.C.containsKey(cVar.f())) {
            return ((c) this.C.get(cVar.f())).e();
        } else {
            return 0;
        }
    }

    private int e(c cVar) {
        String d = cVar.d();
        c i = cVar.i();
        if (this.E.containsKey(d)) {
            Map map = (Map) this.E.get(d);
            if (map.containsKey(cVar.e())) {
                return ((b) map.get(cVar.e())).d(i);
            }
        }
        return 0;
    }

    protected void a(String str, String str2, e eVar, int i) {
        if (str == null || str2 == null) {
            throw new RuntimeException("Logic error");
        }
        ((dji.sdksharedlib.hardware.a.d) this.C.get(str)).a((c) this.C.get(str2), eVar);
        B.a(new i.a(this, (dji.sdksharedlib.hardware.a.d) this.C.get(str)), i);
    }
}
