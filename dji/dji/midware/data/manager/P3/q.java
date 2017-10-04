package dji.midware.data.manager.P3;

import android.support.v4.media.TransportMediator;
import dji.midware.data.a.a.a;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class q {
    public static final Set<Integer> a = new HashSet(Arrays.asList(f));
    private static q b;
    private static final Integer[] f = new Integer[]{Integer.valueOf(TransportMediator.KEYCODE_MEDIA_RECORD), Integer.valueOf(36), Integer.valueOf(39)};
    private List<a> c = new LinkedList();
    private BlockingQueue<Runnable> d = new LinkedBlockingQueue();
    private ThreadPoolExecutor e;

    public static synchronized q getInstance() {
        q qVar;
        synchronized (q.class) {
            if (b == null) {
                b = new q();
            }
            qVar = b;
        }
        return qVar;
    }

    public boolean a(a aVar) {
        return this.c.add(aVar);
    }

    private boolean b(a aVar) {
        return this.c.add(aVar);
    }

    private void a() {
        this.c.clear();
    }

    private void b(a aVar, String str) {
        if (this.c != null && !this.c.isEmpty()) {
            this.e.execute(new 1(this, aVar, str));
        }
    }

    private boolean a(a aVar) {
        return (aVar == null || a.contains(Integer.valueOf(aVar.n))) ? false : true;
    }

    public void a(a aVar, Class cls) {
        if (a(aVar)) {
            b(aVar, cls.getSimpleName());
        }
    }

    public void a(a aVar, String str) {
        if (!a(aVar)) {
            return;
        }
        if (str == null) {
            b(aVar, "N/A");
            return;
        }
        String[] split = str.split(".");
        if (split.length > 0) {
            b(aVar, split[split.length - 1]);
        } else {
            b(aVar, str);
        }
    }

    public void b(a aVar, Class cls) {
        if (a(aVar) && this.c != null && !this.c.isEmpty()) {
            this.e.execute(new 2(this, aVar, cls));
        }
    }

    private q() {
        int availableProcessors = Runtime.getRuntime().availableProcessors() * 2;
        this.e = new ThreadPoolExecutor(availableProcessors, availableProcessors, 1, TimeUnit.SECONDS, this.d);
    }
}
