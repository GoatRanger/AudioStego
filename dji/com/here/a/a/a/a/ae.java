package com.here.a.a.a.a;

public class ae {
    public final String a;

    public ae(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Name can't be null.");
        }
        this.a = str;
    }

    public static ae fromJSON(o oVar) {
        return new ae(oVar.i("@name"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ae) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
