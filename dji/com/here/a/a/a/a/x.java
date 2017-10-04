package com.here.a.a.a.a;

public class x {
    public final a a;
    public final b b;
    public final ad<c> c;
    public final ad<String> d;

    public enum a {
        UNAUTHORIZED,
        INVALID_PARAMETERS,
        NOT_FOUND,
        ROUTING_NOT_POSSIBLE,
        NO_RESPONSE,
        UNEXPECTED,
        UNAVAILABLE_API,
        INVALID_PERIOD,
        UNKNOWN;

        public static a[] a() {
            return (a[]) j.clone();
        }

        public static a a(String str) {
            if ("I4".equalsIgnoreCase(str)) {
                return UNAUTHORIZED;
            }
            if ("GW100".equalsIgnoreCase(str)) {
                return INVALID_PARAMETERS;
            }
            if ("SS0007".equalsIgnoreCase(str)) {
                return NOT_FOUND;
            }
            if ("GW0001".equalsIgnoreCase(str)) {
                return ROUTING_NOT_POSSIBLE;
            }
            if ("GW0002".equalsIgnoreCase(str)) {
                return NO_RESPONSE;
            }
            if ("GW0006".equalsIgnoreCase(str)) {
                return UNEXPECTED;
            }
            if ("GW0007".equalsIgnoreCase(str)) {
                return UNAVAILABLE_API;
            }
            if ("K9360".equalsIgnoreCase(str)) {
                return INVALID_PERIOD;
            }
            return UNKNOWN;
        }
    }

    public enum b {
        MESSAGE,
        WARNING,
        ERROR,
        FATAL;

        public boolean a(b bVar) {
            return ordinal() > bVar.ordinal();
        }

        public static b a(String str) {
            if ("F".equalsIgnoreCase(str)) {
                return FATAL;
            }
            if ("E".equalsIgnoreCase(str)) {
                return ERROR;
            }
            if ("W".equalsIgnoreCase(str)) {
                return WARNING;
            }
            return MESSAGE;
        }
    }

    public enum c {
        NO_COVERAGE,
        NO_STATION_NEARBY,
        DEP_ARR_TOO_CLOSE;

        public static c[] a() {
            return (c[]) d.clone();
        }

        public static c a(String str) {
            if ("NO_COV".equalsIgnoreCase(str)) {
                return NO_COVERAGE;
            }
            if ("NO_STN_NEARBY".equalsIgnoreCase(str)) {
                return NO_STATION_NEARBY;
            }
            if ("DEP_ARR_TOO_CLOSE".equalsIgnoreCase(str)) {
                return DEP_ARR_TOO_CLOSE;
            }
            return null;
        }
    }

    public x(a aVar, b bVar, c cVar, String str) {
        if (aVar == null || bVar == null) {
            throw new NullPointerException("Both Message code and severity should be not null.");
        }
        this.a = aVar;
        this.b = bVar;
        this.c = ad.b(cVar);
        this.d = ad.b(str);
    }

    public static x fromJSON(o oVar) {
        return new x(a.a(oVar.i("@code")), b.a(oVar.i("@level")), oVar.b("@subcode") ? null : c.a(oVar.i("@subcode")), oVar.a("$", null));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        x xVar = (x) obj;
        if (this.a.equals(xVar.a) && this.b == xVar.b && this.c.equals(xVar.c) && this.d.equals(xVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
    }
}
