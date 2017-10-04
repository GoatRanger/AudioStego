package dji.thirdparty.f.j;

public final class i<T> {
    private final long a;
    private final T b;

    public i(long j, T t) {
        this.b = t;
        this.a = j;
    }

    public long a() {
        return this.a;
    }

    public T b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        if (this.a != iVar.a) {
            return false;
        }
        if (this.b == null) {
            if (iVar.b != null) {
                return false;
            }
            return true;
        } else if (this.b.equals(iVar.b)) {
            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (this.b == null ? 0 : this.b.hashCode()) + ((((int) (this.a ^ (this.a >>> 32))) + 31) * 31);
    }

    public String toString() {
        return String.format("Timestamped(timestampMillis = %d, value = %s)", new Object[]{Long.valueOf(this.a), this.b.toString()});
    }
}
