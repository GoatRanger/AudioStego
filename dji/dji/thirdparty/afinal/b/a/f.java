package dji.thirdparty.afinal.b.a;

import java.util.ArrayList;

public class f {
    private final int a;
    private final int b;
    private final ArrayList<a> c;

    public static class a {
        public byte[] a;
        public int b;
        public int c;

        private a(int i) {
            this.a = new byte[i];
        }
    }

    public f(int i, int i2) {
        this.c = new ArrayList(i);
        this.a = i;
        this.b = i2;
    }

    public synchronized a a() {
        int size;
        size = this.c.size();
        return size > 0 ? (a) this.c.remove(size - 1) : new a(this.b);
    }

    public synchronized void a(a aVar) {
        if (aVar.a.length == this.b) {
            if (this.c.size() < this.a) {
                aVar.b = 0;
                aVar.c = 0;
                this.c.add(aVar);
            }
        }
    }

    public synchronized void b() {
        this.c.clear();
    }
}
