package dji.thirdparty.a.a;

import android.util.Log;
import dji.thirdparty.a.c;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class f {
    public final Map<Class<? extends Throwable>, Integer> a = new HashMap();

    public Integer a(Throwable th) {
        int i = 20;
        Throwable th2 = th;
        do {
            Integer b = b(th2);
            if (b != null) {
                return b;
            }
            th2 = th2.getCause();
            i--;
            if (i <= 0 || th2 == th) {
                Log.d(c.b, "No specific message ressource ID found for " + th);
            }
        } while (th2 != null);
        Log.d(c.b, "No specific message ressource ID found for " + th);
        return null;
    }

    protected Integer b(Throwable th) {
        Class cls = th.getClass();
        Integer num = (Integer) this.a.get(cls);
        if (num != null) {
            return num;
        }
        Class cls2 = null;
        Integer num2 = num;
        for (Entry entry : this.a.entrySet()) {
            Class cls3 = (Class) entry.getKey();
            if (cls3.isAssignableFrom(cls) && (r2 == null || r2.isAssignableFrom(cls3))) {
                cls2 = cls3;
                num2 = (Integer) entry.getValue();
            }
        }
        return num2;
    }

    public f a(Class<? extends Throwable> cls, int i) {
        this.a.put(cls, Integer.valueOf(i));
        return this;
    }
}
