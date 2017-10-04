package dji.pilot.newfpv;

import dji.pilot.newfpv.view.b.a;

public class d {
    private static final String f = "-";
    public final h a;
    public final a b;
    public final Object c;
    public final Object d;
    public Object e = null;

    public d(h hVar, a aVar, Object obj, Object obj2) {
        this.a = hVar;
        this.b = aVar;
        this.c = obj;
        this.d = obj2;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof d)) {
            return equals;
        }
        return this.a == ((d) obj).a;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(70);
        stringBuilder.append("id").append(f).append(String.valueOf(this.b));
        stringBuilder.append(f).append("cls").append(f).append(this.a == null ? "null" : this.a.getClass().getSimpleName());
        stringBuilder.append(f).append("showEvent").append(f).append(this.c);
        stringBuilder.append(f).append("hideEvent").append(f).append(this.d);
        return stringBuilder.toString();
    }
}
