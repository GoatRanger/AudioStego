package dji.sdksharedlib.hardware.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class b {
    private static final int a = 100;
    private static final int b = 10000;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final int i = 4;
    private int c = 0;
    private a d;
    private Map<Integer, List<Runnable>> e;

    private class a extends Handler {
        final /* synthetic */ b a;

        public a(b bVar, Looper looper) {
            this.a = bVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.d();
                    this.a.d.sendEmptyMessageDelayed(1, 100);
                    return;
                case 2:
                    this.a.d((List) message.obj, message.arg1);
                    return;
                case 3:
                    this.a.c((List) message.obj, message.arg1);
                    return;
                case 4:
                    this.a.e();
                    return;
                default:
                    return;
            }
        }
    }

    public void a(List<Runnable> list, int i) {
        if (list != null) {
            Message obtainMessage = this.d.obtainMessage(2);
            obtainMessage.arg1 = i;
            obtainMessage.obj = list;
            this.d.sendMessage(obtainMessage);
        }
    }

    public void a(Runnable runnable, int i) {
        if (runnable != null) {
            List arrayList = new ArrayList();
            arrayList.add(runnable);
            a(arrayList, i);
        }
    }

    public void b(Runnable runnable, int i) {
        if (runnable != null) {
            List arrayList = new ArrayList();
            arrayList.add(runnable);
            b(arrayList, i);
        }
    }

    public void b(List<Runnable> list, int i) {
        if (list != null) {
            Message obtainMessage = this.d.obtainMessage(3);
            obtainMessage.arg1 = i;
            obtainMessage.obj = list;
            this.d.sendMessage(obtainMessage);
        }
    }

    public void a() {
        this.d.sendMessage(this.d.obtainMessage(4));
    }

    public void a(Looper looper) {
        this.e = new ConcurrentHashMap();
        this.d = new a(this, looper);
        this.d.sendMessage(this.d.obtainMessage(1));
    }

    public void b() {
        this.e = null;
        this.d = null;
    }

    private void d() {
        for (Integer num : this.e.keySet()) {
            List list = (List) this.e.get(num);
            int intValue = num.intValue() / 100;
            int i = this.c % intValue;
            int size = (list.size() / intValue) + 1;
            for (int i2 = 0; i2 < size; i2++) {
                int i3 = (i2 * intValue) + i;
                if (list.size() > i3) {
                    ((Runnable) list.get(i3)).run();
                }
            }
        }
        this.c++;
        if (this.c > 1000000) {
            this.c = 0;
        }
    }

    private void c(List<Runnable> list, int i) {
        if (list != null && this.e.containsKey(Integer.valueOf(i))) {
            ((List) this.e.get(Integer.valueOf(i))).removeAll(list);
            if (((List) this.e.get(Integer.valueOf(i))).size() == 0) {
                this.e.remove(Integer.valueOf(i));
            }
        }
    }

    private void d(List<Runnable> list, int i) {
        if (list != null) {
            if (i <= 100 || i >= 10000 || i % 100 != 0) {
                throw new RuntimeException("interval is range is 100~10000, need divisible by 100");
            }
            if (!this.e.containsKey(Integer.valueOf(i))) {
                this.e.put(Integer.valueOf(i), new ArrayList());
            }
            ((List) this.e.get(Integer.valueOf(i))).addAll(list);
        }
    }

    private void e() {
        this.e.clear();
    }

    public int a(int i) {
        if (this.e != null) {
            return ((List) this.e.get(Integer.valueOf(i))).size();
        }
        return 0;
    }

    public int c() {
        if (this.e != null) {
            return this.e.size();
        }
        return 0;
    }
}
