package dji.thirdparty.a;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class c {
    static ExecutorService a = Executors.newCachedThreadPool();
    public static String b = "Event";
    private static volatile c c;
    private static final Map<Class<?>, List<Class<?>>> d = new HashMap();
    private static /* synthetic */ int[] p;
    private final Map<Class<?>, CopyOnWriteArrayList<l>> e = new HashMap();
    private final Map<Object, List<Class<?>>> f = new HashMap();
    private final Map<Class<?>, Object> g = new ConcurrentHashMap();
    private final ThreadLocal<b> h = new ThreadLocal<b>(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected b a() {
            return new b();
        }
    };
    private String i = "onEvent";
    private final e j = new e(this, Looper.getMainLooper(), 10);
    private final b k = new b(this);
    private final a l = new a(this);
    private final k m = new k();
    private boolean n;
    private boolean o = true;

    interface a {
        void a(List<i> list);
    }

    static final class b {
        List<Object> a = new ArrayList();
        boolean b;
        boolean c;
        l d;
        Object e;
        boolean f;

        b() {
        }
    }

    static /* synthetic */ int[] e() {
        int[] iArr = p;
        if (iArr == null) {
            iArr = new int[m.values().length];
            try {
                iArr[m.Async.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[m.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[m.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[m.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            p = iArr;
        }
        return iArr;
    }

    public static c a() {
        if (c == null) {
            synchronized (c.class) {
                if (c == null) {
                    c = new c();
                }
            }
        }
        return c;
    }

    public static void b() {
        k.a();
        d.clear();
    }

    public static void a(Class<?> cls) {
        k.a(cls);
    }

    public static void c() {
        k.b();
    }

    public void a(boolean z) {
        if (this.n) {
            throw new d("This method must be called before any registration");
        }
        this.o = z;
    }

    public void a(Object obj) {
        a(obj, this.i, false, 0);
    }

    public void a(Object obj, int i) {
        a(obj, this.i, false, i);
    }

    @Deprecated
    public void a(Object obj, String str) {
        a(obj, str, false, 0);
    }

    public void b(Object obj) {
        a(obj, this.i, true, 0);
    }

    public void b(Object obj, int i) {
        a(obj, this.i, true, i);
    }

    @Deprecated
    public void b(Object obj, String str) {
        a(obj, str, true, 0);
    }

    private synchronized void a(Object obj, String str, boolean z, int i) {
        for (j a : this.m.a(obj.getClass(), str)) {
            a(obj, a, z, i);
        }
    }

    @Deprecated
    public void a(Object obj, Class<?> cls, Class<?>... clsArr) {
        a(obj, this.i, false, cls, clsArr);
    }

    @Deprecated
    public void a(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        a(obj, str, false, cls, clsArr);
    }

    @Deprecated
    public void b(Object obj, Class<?> cls, Class<?>... clsArr) {
        a(obj, this.i, true, cls, clsArr);
    }

    @Deprecated
    public void b(Object obj, String str, Class<?> cls, Class<?>... clsArr) {
        a(obj, str, true, cls, clsArr);
    }

    private synchronized void a(Object obj, String str, boolean z, Class<?> cls, Class<?>... clsArr) {
        for (j jVar : this.m.a(obj.getClass(), str)) {
            if (cls == jVar.c) {
                a(obj, jVar, z, 0);
            } else if (clsArr != null) {
                for (Class<?> cls2 : clsArr) {
                    if (cls2 == jVar.c) {
                        a(obj, jVar, z, 0);
                        break;
                    }
                }
            } else {
                continue;
            }
        }
    }

    private void a(Object obj, j jVar, boolean z, int i) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        this.n = true;
        Class cls = jVar.c;
        CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) this.e.get(cls);
        l lVar = new l(obj, jVar, i);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.e.put(cls, copyOnWriteArrayList2);
            copyOnWriteArrayList = copyOnWriteArrayList2;
        } else {
            Iterator it = copyOnWriteArrayList2.iterator();
            while (it.hasNext()) {
                if (((l) it.next()).equals(lVar)) {
                    throw new d("Subscriber " + obj.getClass() + " already registered to event " + cls);
                }
            }
            copyOnWriteArrayList = copyOnWriteArrayList2;
        }
        int size = copyOnWriteArrayList.size();
        int i2 = 0;
        while (i2 <= size) {
            if (i2 == size || lVar.c > ((l) copyOnWriteArrayList.get(i2)).c) {
                copyOnWriteArrayList.add(i2, lVar);
                break;
            }
            i2++;
        }
        List list = (List) this.f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f.put(obj, list);
        }
        list.add(cls);
        if (z) {
            Object obj2;
            synchronized (this.g) {
                obj2 = this.g.get(cls);
            }
            if (obj2 != null) {
                boolean z2;
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                a(lVar, obj2, z2);
            }
        }
    }

    public synchronized boolean c(Object obj) {
        return this.f.containsKey(obj);
    }

    @Deprecated
    public synchronized void a(Object obj, Class<?>... clsArr) {
        if (clsArr.length == 0) {
            throw new IllegalArgumentException("Provide at least one event class");
        }
        List list = (List) this.f.get(obj);
        if (list != null) {
            for (Class cls : clsArr) {
                a(obj, cls);
                list.remove(cls);
            }
            if (list.isEmpty()) {
                this.f.remove(obj);
            }
        } else {
            Log.w(b, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    private void a(Object obj, Class<?> cls) {
        List list = (List) this.e.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2;
                l lVar = (l) list.get(i);
                if (lVar.a == obj) {
                    lVar.d = false;
                    list.remove(i);
                    i2 = i - 1;
                    i = size - 1;
                } else {
                    i2 = i;
                    i = size;
                }
                size = i;
                i = i2 + 1;
            }
        }
    }

    public synchronized void d(Object obj) {
        List<Class> list = (List) this.f.get(obj);
        if (list != null) {
            for (Class a : list) {
                a(obj, a);
            }
            this.f.remove(obj);
        } else {
            Log.w(b, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void e(Object obj) {
        b bVar = (b) this.h.get();
        List list = bVar.a;
        list.add(obj);
        if (!bVar.b) {
            boolean z;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            } else {
                z = false;
            }
            bVar.c = z;
            bVar.b = true;
            if (bVar.f) {
                throw new d("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    a(list.remove(0), bVar);
                } catch (Throwable th) {
                    bVar.b = false;
                    bVar.c = false;
                }
            }
            bVar.b = false;
            bVar.c = false;
        }
    }

    public void f(Object obj) {
        b bVar = (b) this.h.get();
        if (!bVar.b) {
            throw new d("This method may only be called from inside event handling methods on the posting thread");
        } else if (obj == null) {
            throw new d("Event may not be null");
        } else if (bVar.e != obj) {
            throw new d("Only the currently handled event may be aborted");
        } else if (bVar.d.b.b != m.PostThread) {
            throw new d(" event handlers may only abort the incoming event");
        } else {
            bVar.f = true;
        }
    }

    public void g(Object obj) {
        synchronized (this.g) {
            this.g.put(obj.getClass(), obj);
        }
        e(obj);
    }

    public <T> T b(Class<T> cls) {
        T cast;
        synchronized (this.g) {
            cast = cls.cast(this.g.get(cls));
        }
        return cast;
    }

    public <T> T c(Class<T> cls) {
        T cast;
        synchronized (this.g) {
            cast = cls.cast(this.g.remove(cls));
        }
        return cast;
    }

    public boolean h(Object obj) {
        synchronized (this.g) {
            Class cls = obj.getClass();
            if (obj.equals(this.g.get(cls))) {
                this.g.remove(cls);
                return true;
            }
            return false;
        }
    }

    public void d() {
        synchronized (this.g) {
            this.g.clear();
        }
    }

    private void a(Object obj, b bVar) throws Error {
        Class cls = obj.getClass();
        List d = d(cls);
        int size = d.size();
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            Class cls2 = (Class) d.get(i);
            synchronized (this) {
                CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.e.get(cls2);
            }
            if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
                z2 = z;
            } else {
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    Object obj2;
                    l lVar = (l) it.next();
                    bVar.e = obj;
                    bVar.d = lVar;
                    try {
                        a(lVar, obj, bVar.c);
                        obj2 = bVar.f;
                        continue;
                    } finally {
                        bVar.e = null;
                        bVar.d = null;
                        bVar.f = false;
                    }
                    if (obj2 != null) {
                        break;
                    }
                }
                z2 = true;
            }
            i++;
            z = z2;
        }
        if (!z && cls != f.class && cls != i.class) {
            e(new f(this, obj));
        }
    }

    private void a(l lVar, Object obj, boolean z) {
        switch (e()[lVar.b.b.ordinal()]) {
            case 1:
                a(lVar, obj);
                return;
            case 2:
                if (z) {
                    a(lVar, obj);
                    return;
                } else {
                    this.j.a(lVar, obj);
                    return;
                }
            case 3:
                if (z) {
                    this.k.a(lVar, obj);
                    return;
                } else {
                    a(lVar, obj);
                    return;
                }
            case 4:
                this.l.a(lVar, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + lVar.b.b);
        }
    }

    private List<Class<?>> d(Class<?> cls) {
        List<Class<?>> list;
        synchronized (d) {
            list = (List) d.get(cls);
            if (list == null) {
                List arrayList = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    arrayList.add(cls2);
                    a(arrayList, cls2.getInterfaces());
                }
                d.put(cls, arrayList);
            }
        }
        return list;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    void a(g gVar) {
        Object obj = gVar.a;
        l lVar = gVar.b;
        g.a(gVar);
        if (lVar.d) {
            a(lVar, obj);
        }
    }

    void a(l lVar, Object obj) throws Error {
        Throwable cause;
        try {
            lVar.b.a.invoke(lVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            cause = e.getCause();
            if (obj instanceof i) {
                Log.e(b, "SubscriberExceptionEvent subscriber " + lVar.a.getClass() + " threw an exception", cause);
                i iVar = (i) obj;
                Log.e(b, "Initial event " + iVar.c + " caused exception in " + iVar.d, iVar.b);
                return;
            }
            if (this.o) {
                Log.e(b, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + lVar.a.getClass(), cause);
            }
            e(new i(this, cause, obj, lVar.a));
        } catch (Throwable cause2) {
            throw new IllegalStateException("Unexpected exception", cause2);
        }
    }
}
