package dji.midware.data.a.b;

import dji.midware.data.b.a.a;

public class c extends a {
    public void a() {
        this.b = this.i == null ? 10 : this.i.length + 10;
        Object obj = new byte[this.b];
        obj[0] = (byte) ((this.a << 6) | 10);
        obj[1] = (byte) ((this.c << 5) | this.d);
        Object b = dji.midware.util.c.b((short) this.b);
        System.arraycopy(b, 0, obj, 2, b.length);
        int length = b.length + 2;
        b = dji.midware.util.c.b(a.getSessionId());
        System.arraycopy(b, 0, obj, length, b.length);
        length += b.length;
        b = dji.midware.util.c.a(0);
        System.arraycopy(b, 0, obj, length, b.length);
        length += b.length;
        if (this.i != null) {
            System.arraycopy(this.i, 0, obj, length, this.i.length);
            length += this.i.length;
        }
        this.j = obj;
    }

    public void b() {
        this.b = this.i == null ? 10 : this.i.length + 10;
        Object obj = new byte[this.b];
        obj[0] = (byte) ((this.a << 6) | 10);
        obj[1] = (byte) ((this.c << 5) | this.d);
        Object b = dji.midware.util.c.b((short) this.b);
        System.arraycopy(b, 0, obj, 2, b.length);
        int length = b.length + 2;
        b = dji.midware.util.c.b(a.sessionId());
        System.arraycopy(b, 0, obj, length, b.length);
        length += b.length;
        b = dji.midware.util.c.a(0);
        System.arraycopy(b, 0, obj, length, b.length);
        length += b.length;
        if (this.i != null) {
            System.arraycopy(this.i, 0, obj, length, this.i.length);
            length += this.i.length;
        }
        this.j = obj;
    }

    public String toString() {
        return dji.midware.util.c.i(this.j);
    }
}
