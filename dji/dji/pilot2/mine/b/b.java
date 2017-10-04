package dji.pilot2.mine.b;

import dji.pilot2.mine.e.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class b implements Comparable<b> {
    private long a = 0;
    private final List<a> b = new ArrayList();

    public /* synthetic */ int compareTo(Object obj) {
        return b((b) obj);
    }

    public long a() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public List<a> b() {
        return this.b;
    }

    public void c() {
        Collections.sort(this.b);
    }

    public void a(a aVar) {
        Object obj;
        for (a e : this.b) {
            if (e.e().equals(aVar.e())) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj == null) {
            this.b.add(aVar);
        }
    }

    public void a(b bVar) {
        for (int i = 0; i < bVar.d(); i++) {
            a(bVar.a(i));
        }
    }

    public void b(a aVar) {
        a aVar2 = null;
        for (a aVar3 : this.b) {
            a aVar32;
            if (!aVar32.e().equals(aVar.e())) {
                aVar32 = aVar2;
            }
            aVar2 = aVar32;
        }
        if (aVar2 != null) {
            this.b.remove(aVar2);
        }
    }

    public int d() {
        return this.b.size();
    }

    public a a(int i) {
        if (i < 0 || i >= d()) {
            return null;
        }
        return (a) this.b.get(i);
    }

    public int b(b bVar) {
        long j = this.a - bVar.a;
        if (j == 0) {
            return 0;
        }
        return j < 0 ? 1 : -1;
    }
}
