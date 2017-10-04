package android.databinding;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewGroup;
import com.android.databinding.library.R;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public abstract class ab extends a {
    static int a = VERSION.SDK_INT;
    public static final String b = "binding_";
    private static final int d = 1;
    private static final int e = 2;
    private static final int f = 3;
    private static final int g = b.length();
    private static final boolean h = (i.a >= 14);
    private static final boolean i;
    private static final a j = new a() {
        public f a(ab abVar, int i) {
            return new h(abVar, i).a();
        }
    };
    private static final a k = new a() {
        public f a(ab abVar, int i) {
            return new e(abVar, i).a();
        }
    };
    private static final a l = new a() {
        public f a(ab abVar, int i) {
            return new g(abVar, i).a();
        }
    };
    private static final android.databinding.h.a<y, ab, Void> m = new android.databinding.h.a<y, ab, Void>() {
        public void a(y yVar, ab abVar, int i, Void voidR) {
            switch (i) {
                case 1:
                    if (!yVar.a(abVar)) {
                        abVar.q = true;
                        return;
                    }
                    return;
                case 2:
                    yVar.b(abVar);
                    return;
                case 3:
                    yVar.c(abVar);
                    return;
                default:
                    return;
            }
        }
    };
    private static final OnAttachStateChangeListener n;
    protected final j c;
    private final Runnable o = new Runnable(this) {
        final /* synthetic */ ab a;

        {
            this.a = r1;
        }

        public void run() {
            synchronized (this) {
                this.a.p = false;
            }
            if (VERSION.SDK_INT < 19 || this.a.s.isAttachedToWindow()) {
                this.a.c();
                return;
            }
            this.a.s.removeOnAttachStateChangeListener(ab.n);
            this.a.s.addOnAttachStateChangeListener(ab.n);
        }
    };
    private boolean p = false;
    private boolean q = false;
    private f[] r;
    private final View s;
    private h<y, ab, Void> t;
    private boolean u;
    private Choreographer v;
    private final FrameCallback w;
    private Handler x;

    private interface a {
        f a(ab abVar, int i);
    }

    protected static class b {
        public final String[][] a;
        public final int[][] b;
        public final int[][] c;

        public b(int i) {
            this.a = new String[i][];
            this.b = new int[i][];
            this.c = new int[i][];
        }

        public void a(int i, String[] strArr, int[] iArr, int[] iArr2) {
            this.a[i] = strArr;
            this.b[i] = iArr;
            this.c[i] = iArr2;
        }
    }

    private interface c<T> {
        f<T> a();

        void a(T t);

        void b(T t);
    }

    protected static abstract class d extends android.databinding.s.a implements n {
        final int a;

        public d(int i) {
            this.a = i;
        }

        public void a(s sVar, int i) {
            if (i == this.a || i == 0) {
                a();
            }
        }
    }

    private static class e extends android.databinding.w.a implements c<w> {
        final f<w> a;

        public /* synthetic */ void a(Object obj) {
            b((w) obj);
        }

        public /* synthetic */ void b(Object obj) {
            c((w) obj);
        }

        public e(ab abVar, int i) {
            this.a = new f(abVar, i, this);
        }

        public f<w> a() {
            return this.a;
        }

        public void b(w wVar) {
            wVar.a(this);
        }

        public void c(w wVar) {
            wVar.b(this);
        }

        public void a(w wVar) {
            ab c = this.a.c();
            if (c != null) {
                Object obj = (w) this.a.b();
                if (obj == wVar) {
                    c.b(this.a.a, obj, 0);
                }
            }
        }

        public void a(w wVar, int i, int i2) {
            a(wVar);
        }

        public void b(w wVar, int i, int i2) {
            a(wVar);
        }

        public void a(w wVar, int i, int i2, int i3) {
            a(wVar);
        }

        public void c(w wVar, int i, int i2) {
            a(wVar);
        }
    }

    private static class f<T> extends WeakReference<ab> {
        protected final int a;
        private final c<T> b;
        private T c;

        public f(ab abVar, int i, c<T> cVar) {
            super(abVar);
            this.a = i;
            this.b = cVar;
        }

        public void a(T t) {
            a();
            this.c = t;
            if (this.c != null) {
                this.b.a(this.c);
            }
        }

        public boolean a() {
            boolean z = false;
            if (this.c != null) {
                this.b.b(this.c);
                z = true;
            }
            this.c = null;
            return z;
        }

        public T b() {
            return this.c;
        }

        protected ab c() {
            ab abVar = (ab) get();
            if (abVar == null) {
                a();
            }
            return abVar;
        }
    }

    private static class g extends android.databinding.x.a implements c<x> {
        final f<x> a;

        public g(ab abVar, int i) {
            this.a = new f(abVar, i, this);
        }

        public f<x> a() {
            return this.a;
        }

        public void a(x xVar) {
            xVar.a(this);
        }

        public void b(x xVar) {
            xVar.b(this);
        }

        public void a(x xVar, Object obj) {
            ab c = this.a.c();
            if (c != null && xVar == this.a.b()) {
                c.b(this.a.a, (Object) xVar, 0);
            }
        }
    }

    private static class h extends android.databinding.s.a implements c<s> {
        final f<s> a;

        public h(ab abVar, int i) {
            this.a = new f(abVar, i, this);
        }

        public f<s> a() {
            return this.a;
        }

        public void a(s sVar) {
            sVar.a(this);
        }

        public void b(s sVar) {
            sVar.b(this);
        }

        public void a(s sVar, int i) {
            ab c = this.a.c();
            if (c != null && ((s) this.a.b()) == sVar) {
                c.b(this.a.a, (Object) sVar, i);
            }
        }
    }

    public abstract boolean a(int i, Object obj);

    protected abstract boolean a(int i, Object obj, int i2);

    protected abstract void e();

    public abstract void f();

    public abstract boolean g();

    static {
        boolean z = true;
        if (a < 16) {
            z = false;
        }
        i = z;
        if (VERSION.SDK_INT < 19) {
            n = null;
        } else {
            n = new OnAttachStateChangeListener() {
                @TargetApi(19)
                public void onViewAttachedToWindow(View view) {
                    ab.b(view).o.run();
                    view.removeOnAttachStateChangeListener(this);
                }

                public void onViewDetachedFromWindow(View view) {
                }
            };
        }
    }

    protected ab(j jVar, View view, int i) {
        this.c = jVar;
        this.r = new f[i];
        this.s = view;
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("DataBinding must be created in view's UI Thread");
        } else if (i) {
            this.v = Choreographer.getInstance();
            this.w = new FrameCallback(this) {
                final /* synthetic */ ab a;

                {
                    this.a = r1;
                }

                public void doFrame(long j) {
                    this.a.o.run();
                }
            };
        } else {
            this.w = null;
            this.x = new Handler(Looper.myLooper());
        }
    }

    protected void a(View view) {
        if (h) {
            view.setTag(R.id.dataBinding, this);
        } else {
            view.setTag(this);
        }
    }

    protected void a(View[] viewArr) {
        int i = 0;
        int length;
        if (h) {
            length = viewArr.length;
            while (i < length) {
                viewArr[i].setTag(R.id.dataBinding, this);
                i++;
            }
            return;
        }
        length = viewArr.length;
        while (i < length) {
            viewArr[i].setTag(this);
            i++;
        }
    }

    public static int b() {
        return a;
    }

    public void a(y yVar) {
        if (this.t == null) {
            this.t = new h(m);
        }
        this.t.a((Object) yVar);
    }

    public void b(y yVar) {
        if (this.t != null) {
            this.t.b((Object) yVar);
        }
    }

    public void c() {
        if (this.u) {
            j();
        } else if (g()) {
            this.u = true;
            this.q = false;
            if (this.t != null) {
                this.t.a(this, 1, null);
                if (this.q) {
                    this.t.a(this, 2, null);
                }
            }
            if (!this.q) {
                e();
                if (this.t != null) {
                    this.t.a(this, 3, null);
                }
            }
            this.u = false;
        }
    }

    void d() {
        e();
    }

    public void h() {
        for (f fVar : this.r) {
            if (fVar != null) {
                fVar.a();
            }
        }
    }

    protected void finalize() throws Throwable {
        h();
    }

    static ab b(View view) {
        if (view != null) {
            if (h) {
                return (ab) view.getTag(R.id.dataBinding);
            }
            Object tag = view.getTag();
            if (tag instanceof ab) {
                return (ab) tag;
            }
        }
        return null;
    }

    public View i() {
        return this.s;
    }

    private void b(int i, Object obj, int i2) {
        if (a(i, obj, i2)) {
            j();
        }
    }

    protected boolean b(int i) {
        f fVar = this.r[i];
        if (fVar != null) {
            return fVar.a();
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void j() {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.p;	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x0007;
    L_0x0005:
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
    L_0x0006:
        return;
    L_0x0007:
        r0 = 1;
        r2.p = r0;	 Catch:{ all -> 0x0017 }
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
        r0 = i;
        if (r0 == 0) goto L_0x001a;
    L_0x000f:
        r0 = r2.v;
        r1 = r2.w;
        r0.postFrameCallback(r1);
        goto L_0x0006;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
        throw r0;
    L_0x001a:
        r0 = r2.x;
        r1 = r2.o;
        r0.post(r1);
        goto L_0x0006;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.databinding.ab.j():void");
    }

    protected Object c(int i) {
        f fVar = this.r[i];
        if (fVar == null) {
            return null;
        }
        return fVar.b();
    }

    private boolean b(int i, Object obj, a aVar) {
        if (obj == null) {
            return b(i);
        }
        f fVar = this.r[i];
        if (fVar == null) {
            a(i, obj, aVar);
            return true;
        } else if (fVar.b() == obj) {
            return false;
        } else {
            b(i);
            a(i, obj, aVar);
            return true;
        }
    }

    protected boolean a(int i, s sVar) {
        return b(i, (Object) sVar, j);
    }

    protected boolean a(int i, w wVar) {
        return b(i, (Object) wVar, k);
    }

    protected boolean a(int i, x xVar) {
        return b(i, (Object) xVar, l);
    }

    protected void a(Class<?> cls) {
        if (this.c == null) {
            throw new IllegalStateException("Required DataBindingComponent is null in class " + getClass().getSimpleName() + ". A BindingAdapter in " + cls.getCanonicalName() + " is not static and requires an object to use, retrieved from the " + "DataBindingComponent. If you don't use an inflation method taking a " + "DataBindingComponent, use DataBindingUtil.setDefaultComponent or " + "make all BindingAdapter methods static.");
        }
    }

    protected void a(int i, Object obj, a aVar) {
        if (obj != null) {
            f fVar = this.r[i];
            if (fVar == null) {
                fVar = aVar.a(this, i);
                this.r[i] = fVar;
            }
            fVar.a(obj);
        }
    }

    protected static ab a(j jVar, View view, int i) {
        return k.a(jVar, view, i);
    }

    protected static Object[] a(j jVar, View view, int i, b bVar, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        a(jVar, view, objArr, bVar, sparseIntArray, true);
        return objArr;
    }

    protected static boolean a(String str, boolean z) {
        return str == null ? z : Boolean.parseBoolean(str);
    }

    protected static byte a(String str, byte b) {
        try {
            b = Byte.parseByte(str);
        } catch (NumberFormatException e) {
        }
        return b;
    }

    protected static short a(String str, short s) {
        try {
            s = Short.parseShort(str);
        } catch (NumberFormatException e) {
        }
        return s;
    }

    protected static int a(String str, int i) {
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return i;
    }

    protected static long a(String str, long j) {
        try {
            j = Long.parseLong(str);
        } catch (NumberFormatException e) {
        }
        return j;
    }

    protected static float a(String str, float f) {
        try {
            f = Float.parseFloat(str);
        } catch (NumberFormatException e) {
        }
        return f;
    }

    protected static double a(String str, double d) {
        try {
            d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
        }
        return d;
    }

    protected static char a(String str, char c) {
        return (str == null || str.isEmpty()) ? c : str.charAt(0);
    }

    protected static int a(View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            return view.getContext().getColor(i);
        }
        return view.getResources().getColor(i);
    }

    protected static ColorStateList b(View view, int i) {
        if (VERSION.SDK_INT >= 23) {
            return view.getContext().getColorStateList(i);
        }
        return view.getResources().getColorStateList(i);
    }

    protected static Drawable c(View view, int i) {
        if (VERSION.SDK_INT >= 21) {
            return view.getContext().getDrawable(i);
        }
        return view.getResources().getDrawable(i);
    }

    protected static <T> T a(T[] tArr, int i) {
        if (tArr == null || i < 0 || i >= tArr.length) {
            return null;
        }
        return tArr[i];
    }

    protected static <T> void a(T[] tArr, int i, T t) {
        if (tArr != null && i >= 0 && i < tArr.length) {
            tArr[i] = t;
        }
    }

    protected static boolean a(boolean[] zArr, int i) {
        if (zArr == null || i < 0 || i >= zArr.length) {
            return false;
        }
        return zArr[i];
    }

    protected static void a(boolean[] zArr, int i, boolean z) {
        if (zArr != null && i >= 0 && i < zArr.length) {
            zArr[i] = z;
        }
    }

    protected static byte a(byte[] bArr, int i) {
        if (bArr == null || i < 0 || i >= bArr.length) {
            return (byte) 0;
        }
        return bArr[i];
    }

    protected static void a(byte[] bArr, int i, byte b) {
        if (bArr != null && i >= 0 && i < bArr.length) {
            bArr[i] = b;
        }
    }

    protected static short a(short[] sArr, int i) {
        if (sArr == null || i < 0 || i >= sArr.length) {
            return (short) 0;
        }
        return sArr[i];
    }

    protected static void a(short[] sArr, int i, short s) {
        if (sArr != null && i >= 0 && i < sArr.length) {
            sArr[i] = s;
        }
    }

    protected static char a(char[] cArr, int i) {
        if (cArr == null || i < 0 || i >= cArr.length) {
            return '\u0000';
        }
        return cArr[i];
    }

    protected static void a(char[] cArr, int i, char c) {
        if (cArr != null && i >= 0 && i < cArr.length) {
            cArr[i] = c;
        }
    }

    protected static int a(int[] iArr, int i) {
        if (iArr == null || i < 0 || i >= iArr.length) {
            return 0;
        }
        return iArr[i];
    }

    protected static void a(int[] iArr, int i, int i2) {
        if (iArr != null && i >= 0 && i < iArr.length) {
            iArr[i] = i2;
        }
    }

    protected static long a(long[] jArr, int i) {
        if (jArr == null || i < 0 || i >= jArr.length) {
            return 0;
        }
        return jArr[i];
    }

    protected static void a(long[] jArr, int i, long j) {
        if (jArr != null && i >= 0 && i < jArr.length) {
            jArr[i] = j;
        }
    }

    protected static float a(float[] fArr, int i) {
        if (fArr == null || i < 0 || i >= fArr.length) {
            return 0.0f;
        }
        return fArr[i];
    }

    protected static void a(float[] fArr, int i, float f) {
        if (fArr != null && i >= 0 && i < fArr.length) {
            fArr[i] = f;
        }
    }

    protected static double a(double[] dArr, int i) {
        if (dArr == null || i < 0 || i >= dArr.length) {
            return 0.0d;
        }
        return dArr[i];
    }

    protected static void a(double[] dArr, int i, double d) {
        if (dArr != null && i >= 0 && i < dArr.length) {
            dArr[i] = d;
        }
    }

    protected static <T> T a(List<T> list, int i) {
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    protected static <T> void a(List<T> list, int i, T t) {
        if (list != null && i >= 0 && i < list.size()) {
            list.set(i, t);
        }
    }

    protected static <T> T a(SparseArray<T> sparseArray, int i) {
        if (sparseArray == null || i < 0) {
            return null;
        }
        return sparseArray.get(i);
    }

    protected static <T> void a(SparseArray<T> sparseArray, int i, T t) {
        if (sparseArray != null && i >= 0 && i < sparseArray.size()) {
            sparseArray.put(i, t);
        }
    }

    @TargetApi(16)
    protected static <T> T a(LongSparseArray<T> longSparseArray, int i) {
        if (longSparseArray == null || i < 0) {
            return null;
        }
        return longSparseArray.get((long) i);
    }

    @TargetApi(16)
    protected static <T> void a(LongSparseArray<T> longSparseArray, int i, T t) {
        if (longSparseArray != null && i >= 0 && i < longSparseArray.size()) {
            longSparseArray.put((long) i, t);
        }
    }

    protected static <T> T a(android.support.v4.util.LongSparseArray<T> longSparseArray, int i) {
        if (longSparseArray == null || i < 0) {
            return null;
        }
        return longSparseArray.get((long) i);
    }

    protected static <T> void a(android.support.v4.util.LongSparseArray<T> longSparseArray, int i, T t) {
        if (longSparseArray != null && i >= 0 && i < longSparseArray.size()) {
            longSparseArray.put((long) i, t);
        }
    }

    protected static boolean a(SparseBooleanArray sparseBooleanArray, int i) {
        if (sparseBooleanArray == null || i < 0) {
            return false;
        }
        return sparseBooleanArray.get(i);
    }

    protected static void a(SparseBooleanArray sparseBooleanArray, int i, boolean z) {
        if (sparseBooleanArray != null && i >= 0 && i < sparseBooleanArray.size()) {
            sparseBooleanArray.put(i, z);
        }
    }

    protected static int a(SparseIntArray sparseIntArray, int i) {
        if (sparseIntArray == null || i < 0) {
            return 0;
        }
        return sparseIntArray.get(i);
    }

    protected static void a(SparseIntArray sparseIntArray, int i, int i2) {
        if (sparseIntArray != null && i >= 0 && i < sparseIntArray.size()) {
            sparseIntArray.put(i, i2);
        }
    }

    @TargetApi(18)
    protected static long a(SparseLongArray sparseLongArray, int i) {
        if (sparseLongArray == null || i < 0) {
            return 0;
        }
        return sparseLongArray.get(i);
    }

    @TargetApi(18)
    protected static void a(SparseLongArray sparseLongArray, int i, long j) {
        if (sparseLongArray != null && i >= 0 && i < sparseLongArray.size()) {
            sparseLongArray.put(i, j);
        }
    }

    protected static <K, T> T a(Map<K, T> map, K k) {
        if (map == null) {
            return null;
        }
        return map.get(k);
    }

    protected static <K, T> void a(Map<K, T> map, K k, T t) {
        if (map != null) {
            map.put(k, t);
        }
    }

    protected static void a(ab abVar, n nVar, d dVar) {
        if (nVar != dVar) {
            if (nVar != null) {
                abVar.b((d) nVar);
            }
            if (dVar != null) {
                abVar.a((android.databinding.s.a) dVar);
            }
        }
    }

    protected static Object[] a(j jVar, View[] viewArr, int i, b bVar, SparseIntArray sparseIntArray) {
        Object[] objArr = new Object[i];
        for (View a : viewArr) {
            a(jVar, a, objArr, bVar, sparseIntArray, true);
        }
        return objArr;
    }

    private static void a(j jVar, View view, Object[] objArr, b bVar, SparseIntArray sparseIntArray, boolean z) {
        if (b(view) == null) {
            String str;
            int lastIndexOf;
            int c;
            int i;
            Object tag = view.getTag();
            if (tag instanceof String) {
                str = (String) tag;
            } else {
                str = null;
            }
            tag = null;
            if (z && str != null && str.startsWith("layout")) {
                int i2;
                lastIndexOf = str.lastIndexOf(95);
                if (lastIndexOf <= 0 || !b(str, lastIndexOf + 1)) {
                    i2 = -1;
                } else {
                    c = c(str, lastIndexOf + 1);
                    if (objArr[c] == null) {
                        objArr[c] = view;
                    }
                    if (bVar == null) {
                        c = -1;
                    }
                    i2 = c;
                    tag = 1;
                }
                i = i2;
            } else if (str == null || !str.startsWith(b)) {
                i = -1;
            } else {
                c = c(str, g);
                if (objArr[c] == null) {
                    objArr[c] = view;
                }
                if (bVar == null) {
                    c = -1;
                }
                i = c;
                c = 1;
            }
            if (tag == null) {
                c = view.getId();
                if (c > 0 && sparseIntArray != null) {
                    c = sparseIntArray.get(c, -1);
                    if (c >= 0 && objArr[c] == null) {
                        objArr[c] = view;
                    }
                }
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                int i3 = 0;
                int i4 = 0;
                while (i4 < childCount) {
                    int i5;
                    int i6;
                    View childAt = viewGroup.getChildAt(i4);
                    if (i >= 0 && (childAt.getTag() instanceof String)) {
                        String str2 = (String) childAt.getTag();
                        if (str2.endsWith("_0") && str2.startsWith("layout") && str2.indexOf(47) > 0) {
                            int a = a(str2, i3, bVar, i);
                            if (a >= 0) {
                                tag = 1;
                                i3 = a + 1;
                                i5 = bVar.b[i][a];
                                a = bVar.c[i][a];
                                lastIndexOf = a(viewGroup, i4);
                                if (lastIndexOf == i4) {
                                    objArr[i5] = k.a(jVar, childAt, a);
                                    i5 = i4;
                                    i6 = i3;
                                } else {
                                    i6 = (lastIndexOf - i4) + 1;
                                    View[] viewArr = new View[i6];
                                    for (lastIndexOf = 0; lastIndexOf < i6; lastIndexOf++) {
                                        viewArr[lastIndexOf] = viewGroup.getChildAt(i4 + lastIndexOf);
                                    }
                                    objArr[i5] = k.a(jVar, viewArr, a);
                                    i5 = (i6 - 1) + i4;
                                    i6 = i3;
                                }
                                if (tag == null) {
                                    a(jVar, childAt, objArr, bVar, sparseIntArray, false);
                                }
                                i4 = i5 + 1;
                                i3 = i6;
                            }
                        }
                    }
                    tag = null;
                    i5 = i4;
                    i6 = i3;
                    if (tag == null) {
                        a(jVar, childAt, objArr, bVar, sparseIntArray, false);
                    }
                    i4 = i5 + 1;
                    i3 = i6;
                }
            }
        }
    }

    private static int a(String str, int i, b bVar, int i2) {
        CharSequence subSequence = str.subSequence(str.indexOf(47) + 1, str.length() - 2);
        String[] strArr = bVar.a[i2];
        int length = strArr.length;
        for (int i3 = i; i3 < length; i3++) {
            if (TextUtils.equals(subSequence, strArr[i3])) {
                return i3;
            }
        }
        return -1;
    }

    private static int a(ViewGroup viewGroup, int i) {
        String str = (String) viewGroup.getChildAt(i).getTag();
        String substring = str.substring(0, str.length() - 1);
        int length = substring.length();
        int childCount = viewGroup.getChildCount();
        int i2 = i + 1;
        int i3 = i;
        while (i2 < childCount) {
            int i4;
            View childAt = viewGroup.getChildAt(i2);
            String str2 = childAt.getTag() instanceof String ? (String) childAt.getTag() : null;
            if (str2 != null && str2.startsWith(substring)) {
                if (str2.length() == str.length() && str2.charAt(str2.length() - 1) == '0') {
                    break;
                } else if (b(str2, length)) {
                    i4 = i2;
                    i2++;
                    i3 = i4;
                }
            }
            i4 = i3;
            i2++;
            i3 = i4;
        }
        return i3;
    }

    private static boolean b(String str, int i) {
        int length = str.length();
        if (length == i) {
            return false;
        }
        while (i < length) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static int c(String str, int i) {
        int i2 = 0;
        while (i < str.length()) {
            i2 = (i2 * 10) + (str.charAt(i) - 48);
            i++;
        }
        return i2;
    }
}
