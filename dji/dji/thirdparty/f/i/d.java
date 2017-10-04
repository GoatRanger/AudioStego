package dji.thirdparty.f.i;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public class d {
    static final a a = new a() {
    };
    private static final d b = new d();
    private final AtomicReference<a> c = new AtomicReference();
    private final AtomicReference<b> d = new AtomicReference();
    private final AtomicReference<f> e = new AtomicReference();
    private final AtomicReference<e> f = new AtomicReference();

    public static d getInstance() {
        return b;
    }

    d() {
    }

    void a() {
        b.c.set(null);
        b.d.set(null);
        b.e.set(null);
        b.f.set(null);
    }

    public a b() {
        if (this.c.get() == null) {
            Object a = a(a.class, System.getProperties());
            if (a == null) {
                this.c.compareAndSet(null, a);
            } else {
                this.c.compareAndSet(null, (a) a);
            }
        }
        return (a) this.c.get();
    }

    public void a(a aVar) {
        if (!this.c.compareAndSet(null, aVar)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.c.get());
        }
    }

    public b c() {
        if (this.d.get() == null) {
            Object a = a(b.class, System.getProperties());
            if (a == null) {
                this.d.compareAndSet(null, c.getInstance());
            } else {
                this.d.compareAndSet(null, (b) a);
            }
        }
        return (b) this.d.get();
    }

    public void a(b bVar) {
        if (!this.d.compareAndSet(null, bVar)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.d.get());
        }
    }

    public f d() {
        if (this.e.get() == null) {
            Object a = a(f.class, System.getProperties());
            if (a == null) {
                this.e.compareAndSet(null, g.getInstance());
            } else {
                this.e.compareAndSet(null, (f) a);
            }
        }
        return (f) this.e.get();
    }

    public void a(f fVar) {
        if (!this.e.compareAndSet(null, fVar)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.e.get());
        }
    }

    static Object a(Class<?> cls, Properties properties) {
        String simpleName = cls.getSimpleName();
        String str = "rxjava.plugin.";
        String property = properties.getProperty("rxjava.plugin." + simpleName + ".implementation");
        if (property == null) {
            str = ".class";
            str = ".impl";
            for (Entry entry : properties.entrySet()) {
                String obj = entry.getKey().toString();
                if (obj.startsWith("rxjava.plugin.") && obj.endsWith(".class") && simpleName.equals(entry.getValue().toString())) {
                    property = "rxjava.plugin." + obj.substring(0, obj.length() - ".class".length()).substring("rxjava.plugin.".length()) + ".impl";
                    str = properties.getProperty(property);
                    if (str == null) {
                        throw new RuntimeException("Implementing class declaration for " + simpleName + " missing: " + property);
                    }
                    property = str;
                }
            }
        }
        if (property == null) {
            return null;
        }
        try {
            return Class.forName(property).asSubclass(cls).newInstance();
        } catch (ClassCastException e) {
            throw new RuntimeException(simpleName + " implementation is not an instance of " + simpleName + ": " + property);
        } catch (Throwable e2) {
            throw new RuntimeException(simpleName + " implementation class not found: " + property, e2);
        } catch (Throwable e22) {
            throw new RuntimeException(simpleName + " implementation not able to be instantiated: " + property, e22);
        } catch (Throwable e222) {
            throw new RuntimeException(simpleName + " implementation not able to be accessed: " + property, e222);
        }
    }

    public e e() {
        if (this.f.get() == null) {
            Object a = a(e.class, System.getProperties());
            if (a == null) {
                this.f.compareAndSet(null, e.d());
            } else {
                this.f.compareAndSet(null, (e) a);
            }
        }
        return (e) this.f.get();
    }

    public void a(e eVar) {
        if (!this.f.compareAndSet(null, eVar)) {
            throw new IllegalStateException("Another strategy was already registered: " + this.f.get());
        }
    }
}
