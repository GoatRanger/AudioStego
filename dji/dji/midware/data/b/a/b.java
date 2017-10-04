package dji.midware.data.b.a;

import android.util.SparseArray;
import dji.midware.data.b.a.c.a;
import java.util.ArrayList;

public class b extends c {
    private SparseArray<a> a;
    private ArrayList<Integer> b;
    private int c;

    public b() {
        this.a = null;
        this.b = null;
        this.c = 100;
        this.a = new SparseArray();
        this.b = new ArrayList();
    }

    public void a() {
        this.a = null;
        this.b = null;
    }

    public synchronized a a(dji.midware.data.a.a.a aVar) {
        a aVar2;
        aVar2 = new a(this);
        if (this.a.size() > this.c) {
            b();
        }
        int c = c(aVar);
        if (this.a.indexOfKey(c) >= 0) {
            this.a.put(c, aVar2);
            this.b.remove(Integer.valueOf(c));
            this.b.add(Integer.valueOf(c));
        } else {
            this.a.append(c, aVar2);
            this.b.add(Integer.valueOf(c));
        }
        return aVar2;
    }

    private void b() {
        if (this.b != null && this.a != null) {
            int i = 0;
            while (i < 40) {
                try {
                    this.a.remove(((Integer) this.b.get(0)).intValue());
                    this.b.remove(0);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.a.size();
        }
    }

    public synchronized void b(dji.midware.data.a.a.a aVar) {
        a aVar2 = (a) this.a.get(c(aVar));
        if (aVar2 != null) {
            aVar2.b = aVar;
            aVar2.a = true;
            try {
                synchronized (aVar2) {
                    aVar2.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int c(dji.midware.data.a.a.a aVar) {
        return (aVar.n << 16) | aVar.i;
    }
}
