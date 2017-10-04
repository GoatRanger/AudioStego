package dji.thirdparty.f.e.d;

import dji.thirdparty.f.c.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public enum c {
    ;
    
    private static final Throwable a = null;

    static {
        a = new Throwable("Terminated");
    }

    public static boolean addThrowable(AtomicReference<Throwable> atomicReference, Throwable th) {
        Throwable th2;
        Object obj;
        do {
            th2 = (Throwable) atomicReference.get();
            if (th2 == a) {
                return false;
            }
            if (th2 == null) {
                obj = th;
            } else if (th2 instanceof a) {
                Collection arrayList = new ArrayList(((a) th2).a());
                arrayList.add(th);
                obj = new a(arrayList);
            } else {
                obj = new a(th2, th);
            }
        } while (!atomicReference.compareAndSet(th2, obj));
        return true;
    }

    public static Throwable terminate(AtomicReference<Throwable> atomicReference) {
        Throwable th = (Throwable) atomicReference.get();
        if (th != a) {
            return (Throwable) atomicReference.getAndSet(a);
        }
        return th;
    }

    public static boolean isTerminated(AtomicReference<Throwable> atomicReference) {
        return isTerminated((Throwable) atomicReference.get());
    }

    public static boolean isTerminated(Throwable th) {
        return th == a;
    }
}
