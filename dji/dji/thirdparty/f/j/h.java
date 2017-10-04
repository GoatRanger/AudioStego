package dji.thirdparty.f.j;

import dji.pilot.usercenter.protocol.d;

public class h<T> {
    private final long a;
    private final T b;

    public h(long j, T t) {
        this.b = t;
        this.a = j;
    }

    public long a() {
        return this.a;
    }

    public T b() {
        return this.b;
    }

    public int hashCode() {
        return (this.b == null ? 0 : this.b.hashCode()) + ((((int) (this.a ^ (this.a >>> 32))) + 31) * 31);
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
        h hVar = (h) obj;
        if (this.a != hVar.a) {
            return false;
        }
        if (this.b == null) {
            if (hVar.b != null) {
                return false;
            }
            return true;
        } else if (this.b.equals(hVar.b)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "TimeInterval [intervalInMilliseconds=" + this.a + ", value=" + this.b + d.H;
    }
}
