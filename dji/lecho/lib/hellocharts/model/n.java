package lecho.lib.hellocharts.model;

import dji.pilot.usercenter.protocol.d;

public class n {
    private int a;
    private int b;
    private a c = a.NONE;

    public enum a {
        NONE,
        LINE,
        COLUMN
    }

    public n() {
        a();
    }

    public n(int i, int i2, a aVar) {
        a(i, i2, aVar);
    }

    public void a(int i, int i2, a aVar) {
        this.a = i;
        this.b = i2;
        if (aVar != null) {
            this.c = aVar;
        } else {
            this.c = a.NONE;
        }
    }

    public void a(n nVar) {
        this.a = nVar.a;
        this.b = nVar.b;
        this.c = nVar.c;
    }

    public void a() {
        a(Integer.MIN_VALUE, Integer.MIN_VALUE, a.NONE);
    }

    public boolean b() {
        if (this.a < 0 || this.b < 0) {
            return false;
        }
        return true;
    }

    public int c() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public int d() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public a e() {
        return this.c;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public int hashCode() {
        return (this.c == null ? 0 : this.c.hashCode()) + ((((this.a + 31) * 31) + this.b) * 31);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        n nVar = (n) obj;
        if (this.a != nVar.a) {
            return false;
        }
        if (this.b != nVar.b) {
            return false;
        }
        if (this.c != nVar.c) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "SelectedValue [firstIndex=" + this.a + ", secondIndex=" + this.b + ", type=" + this.c + d.H;
    }
}
