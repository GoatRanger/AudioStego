package android.databinding;

import java.util.ArrayList;
import java.util.List;

public class h<C, T, A> implements Cloneable {
    private static final String a = "CallbackRegistry";
    private List<C> b = new ArrayList();
    private long c = 0;
    private long[] d;
    private int e;
    private final a<C, T, A> f;

    public static abstract class a<C, T, A> {
        public abstract void a(C c, T t, int i, A a);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return d();
    }

    public h(a<C, T, A> aVar) {
        this.f = aVar;
    }

    public synchronized void a(T t, int i, A a) {
        this.e++;
        c(t, i, a);
        this.e--;
        if (this.e == 0) {
            if (this.d != null) {
                for (int length = this.d.length - 1; length >= 0; length--) {
                    long j = this.d[length];
                    if (j != 0) {
                        a((length + 1) * 64, j);
                        this.d[length] = 0;
                    }
                }
            }
            if (this.c != 0) {
                a(0, this.c);
                this.c = 0;
            }
        }
    }

    private void b(T t, int i, A a) {
        a(t, i, a, 0, Math.min(64, this.b.size()), this.c);
    }

    private void c(T t, int i, A a) {
        int size = this.b.size();
        int length = this.d == null ? -1 : this.d.length - 1;
        a(t, i, a, length);
        a(t, i, a, (length + 2) * 64, size, 0);
    }

    private void a(T t, int i, A a, int i2) {
        if (i2 < 0) {
            b(t, i, a);
            return;
        }
        long j = this.d[i2];
        int i3 = (i2 + 1) * 64;
        int min = Math.min(this.b.size(), i3 + 64);
        a(t, i, a, i2 - 1);
        a(t, i, a, i3, min, j);
    }

    private void a(T t, int i, A a, int i2, int i3, long j) {
        long j2 = 1;
        while (i2 < i3) {
            if ((j & j2) == 0) {
                this.f.a(this.b.get(i2), t, i, a);
            }
            j2 <<= 1;
            i2++;
        }
    }

    public synchronized void a(C c) {
        if (c == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        int lastIndexOf = this.b.lastIndexOf(c);
        if (lastIndexOf < 0 || a(lastIndexOf)) {
            this.b.add(c);
        }
    }

    private boolean a(int i) {
        if (i < 64) {
            if (((1 << i) & this.c) != 0) {
                return true;
            }
            return false;
        } else if (this.d == null) {
            return false;
        } else {
            int i2 = (i / 64) - 1;
            if (i2 >= this.d.length) {
                return false;
            }
            if ((this.d[i2] & (1 << (i % 64))) == 0) {
                return false;
            }
            return true;
        }
    }

    private void a(int i, long j) {
        long j2 = Long.MIN_VALUE;
        for (int i2 = (i + 64) - 1; i2 >= i; i2--) {
            if ((j & j2) != 0) {
                this.b.remove(i2);
            }
            j2 >>>= 1;
        }
    }

    public synchronized void b(C c) {
        if (this.e == 0) {
            this.b.remove(c);
        } else {
            int lastIndexOf = this.b.lastIndexOf(c);
            if (lastIndexOf >= 0) {
                b(lastIndexOf);
            }
        }
    }

    private void b(int i) {
        if (i < 64) {
            this.c = (1 << i) | this.c;
            return;
        }
        int i2 = (i / 64) - 1;
        if (this.d == null) {
            this.d = new long[(this.b.size() / 64)];
        } else if (this.d.length < i2) {
            Object obj = new long[(this.b.size() / 64)];
            System.arraycopy(this.d, 0, obj, 0, this.d.length);
            this.d = obj;
        }
        long j = 1 << (i % 64);
        long[] jArr = this.d;
        jArr[i2] = j | jArr[i2];
    }

    public synchronized ArrayList<C> a() {
        ArrayList<C> arrayList;
        arrayList = new ArrayList(this.b.size());
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (!a(i)) {
                arrayList.add(this.b.get(i));
            }
        }
        return arrayList;
    }

    public synchronized void a(List<C> list) {
        list.clear();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (!a(i)) {
                list.add(this.b.get(i));
            }
        }
    }

    public synchronized boolean b() {
        boolean z = true;
        synchronized (this) {
            if (!this.b.isEmpty()) {
                if (this.e == 0) {
                    z = false;
                } else {
                    int size = this.b.size();
                    for (int i = 0; i < size; i++) {
                        if (!a(i)) {
                            z = false;
                            break;
                        }
                    }
                }
            }
        }
        return z;
    }

    public synchronized void c() {
        if (this.e == 0) {
            this.b.clear();
        } else if (!this.b.isEmpty()) {
            for (int size = this.b.size() - 1; size >= 0; size--) {
                b(size);
            }
        }
    }

    public synchronized h<C, T, A> d() {
        h<C, T, A> hVar;
        CloneNotSupportedException e;
        try {
            hVar = (h) super.clone();
            try {
                hVar.c = 0;
                hVar.d = null;
                hVar.e = 0;
                hVar.b = new ArrayList();
                int size = this.b.size();
                for (int i = 0; i < size; i++) {
                    if (!a(i)) {
                        hVar.b.add(this.b.get(i));
                    }
                }
            } catch (CloneNotSupportedException e2) {
                e = e2;
                e.printStackTrace();
                return hVar;
            }
        } catch (CloneNotSupportedException e3) {
            CloneNotSupportedException cloneNotSupportedException = e3;
            hVar = null;
            e = cloneNotSupportedException;
            e.printStackTrace();
            return hVar;
        }
        return hVar;
    }
}
