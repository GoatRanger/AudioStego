package dji.midware.data.model.d;

import dji.midware.data.a.b.c;
import dji.midware.data.config.a.a.b;

public class g extends a {
    private static g a = null;
    private dji.midware.data.config.a.a.a b;
    private a c;

    public enum a {
        Error(0),
        Force(1),
        SizeErr(2),
        ReadFail(3),
        Seek(4),
        UNDEFINED(100);
        
        private int g;

        private a(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }

        public boolean a(int i) {
            return this.g == i;
        }

        public static a find(int i) {
            a aVar = UNDEFINED;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aVar;
        }
    }

    public static synchronized g getInstance() {
        g gVar;
        synchronized (g.class) {
            if (a == null) {
                a = new g();
            }
            gVar = a;
        }
        return gVar;
    }

    public g a(dji.midware.data.config.a.a.a aVar) {
        this.b = aVar;
        return this;
    }

    public g a(a aVar) {
        this.c = aVar;
        return this;
    }

    protected void doPack() {
        c cVar = new c();
        cVar.c = this.b.a();
        cVar.d = b.ABORT.a();
        if (this.c != null) {
            cVar.i = new byte[4];
            System.arraycopy(dji.midware.util.c.a(this.c.a()), 0, cVar.i, 0, 4);
            this.c = a.Force;
        }
        cVar.b();
        this._sendData = cVar.j;
    }
}
