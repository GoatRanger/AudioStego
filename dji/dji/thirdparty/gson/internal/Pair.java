package dji.thirdparty.gson.internal;

public final class Pair<FIRST, SECOND> {
    public final FIRST first;
    public final SECOND second;

    public Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.first != null ? this.first.hashCode() : 0) * 17;
        if (this.second != null) {
            i = this.second.hashCode();
        }
        return hashCode + (i * 17);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (equal(this.first, pair.first) && equal(this.second, pair.second)) {
            return true;
        }
        return false;
    }

    private static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public String toString() {
        return String.format("{%s,%s}", new Object[]{this.first, this.second});
    }
}
