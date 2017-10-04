package dji.thirdparty.f.g;

import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.f.c;
import dji.thirdparty.f.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class f<T> implements e<T> {
    private static e<Object> e = new e<Object>() {
        public void o_() {
        }

        public void a(Throwable th) {
        }

        public void a_(Object obj) {
        }
    };
    private final e<T> a;
    private final ArrayList<T> b;
    private final ArrayList<Throwable> c;
    private final ArrayList<c<T>> d;

    public f(e<T> eVar) {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.a = eVar;
    }

    public f() {
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.a = e;
    }

    public void o_() {
        this.d.add(c.a());
        this.a.o_();
    }

    public List<c<T>> b() {
        return Collections.unmodifiableList(this.d);
    }

    public void a(Throwable th) {
        this.c.add(th);
        this.a.a(th);
    }

    public List<Throwable> c() {
        return Collections.unmodifiableList(this.c);
    }

    public void a_(T t) {
        this.b.add(t);
        this.a.a_(t);
    }

    public List<T> d() {
        return Collections.unmodifiableList(this.b);
    }

    public List<Object> e() {
        List arrayList = new ArrayList();
        arrayList.add(this.b);
        arrayList.add(this.c);
        arrayList.add(this.d);
        return Collections.unmodifiableList(arrayList);
    }

    public void a(List<T> list) {
        if (this.b.size() != list.size()) {
            throw new AssertionError("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.b.size());
        }
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Object obj2 = this.b.get(i);
            if (obj == null) {
                if (obj2 != null) {
                    throw new AssertionError("Value at index: " + i + " expected to be [null] but was: [" + obj2 + d.H);
                }
            } else if (!obj.equals(obj2)) {
                throw new AssertionError("Value at index: " + i + " expected to be [" + obj + "] (" + obj.getClass().getSimpleName() + ") but was: [" + obj2 + "] (" + (obj2 != null ? obj2.getClass().getSimpleName() : "null") + ")");
            }
        }
    }

    public void f() {
        if (this.c.size() > 1) {
            throw new AssertionError("Too many onError events: " + this.c.size());
        } else if (this.d.size() > 1) {
            throw new AssertionError("Too many onCompleted events: " + this.d.size());
        } else if (this.d.size() == 1 && this.c.size() == 1) {
            throw new AssertionError("Received both an onError and onCompleted. Should be one or the other.");
        } else if (this.d.size() == 0 && this.c.size() == 0) {
            throw new AssertionError("No terminal events received.");
        }
    }
}
