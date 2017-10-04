package dji.thirdparty.f.c;

import dji.thirdparty.f.e;
import dji.thirdparty.f.i;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class b {
    private static final int a = 25;

    private b() {
        throw new IllegalStateException("No instances!");
    }

    public static RuntimeException a(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new RuntimeException(th);
        }
    }

    public static void b(Throwable th) {
        if (th instanceof f) {
            throw ((f) th);
        } else if (th instanceof e) {
            throw ((e) th);
        } else if (th instanceof StackOverflowError) {
            throw ((StackOverflowError) th);
        } else if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        } else if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        } else if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }

    public static void a(Throwable th, Throwable th2) {
        Set hashSet = new HashSet();
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i < 25) {
                th = th.getCause();
                if (!hashSet.contains(th.getCause())) {
                    hashSet.add(th.getCause());
                    i = i2;
                }
            } else {
                return;
            }
        }
        try {
            th.initCause(th2);
        } catch (Throwable th3) {
        }
    }

    public static Throwable c(Throwable th) {
        int i = 0;
        while (th.getCause() != null) {
            int i2 = i + 1;
            if (i >= 25) {
                return new RuntimeException("Stack too deep to get final cause");
            }
            th = th.getCause();
            i = i2;
        }
        return th;
    }

    public static void a(List<? extends Throwable> list) {
        if (list != null && !list.isEmpty()) {
            if (list.size() == 1) {
                Throwable th = (Throwable) list.get(0);
                if (th instanceof RuntimeException) {
                    throw ((RuntimeException) th);
                } else if (th instanceof Error) {
                    throw ((Error) th);
                } else {
                    throw new RuntimeException(th);
                }
            }
            throw new a("Multiple exceptions", list);
        }
    }

    @dji.thirdparty.f.b.b
    public static void a(Throwable th, e<?> eVar, Object obj) {
        b(th);
        eVar.a(g.a(th, obj));
    }

    @dji.thirdparty.f.b.b
    public static void a(Throwable th, e<?> eVar) {
        b(th);
        eVar.a(th);
    }

    @dji.thirdparty.f.b.b
    public static void a(Throwable th, i<?> iVar) {
        b(th);
        iVar.a(th);
    }
}
