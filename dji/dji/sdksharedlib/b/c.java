package dji.sdksharedlib.b;

import dji.pilot.usercenter.protocol.d;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class c {
    private static final int a = b.Default.c;
    private static final String b = null;
    private static Map<String, c> j = new ConcurrentHashMap();
    private final String c;
    private final String d;
    private final Integer e;
    private final String f;
    private final Integer g;
    private final String h;
    private final String i;
    private boolean k;

    public static final class a {
        private String a;
        private String b;
        private String c = c.b;
        private Integer d = Integer.valueOf(c.a);
        private String e = c.b;
        private Integer f = Integer.valueOf(c.a);

        public a a(String str) {
            if (c.b(str)) {
                this.c = str;
                return this;
            }
            throw new RuntimeException("Invalid path");
        }

        public a b(String str) {
            this.a = str;
            return this;
        }

        public a a(Integer num) {
            this.d = num;
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }

        public a b(Integer num) {
            this.f = num;
            return this;
        }

        public a d(String str) {
            this.b = str;
            return this;
        }

        public c a() {
            String b;
            if (this.a != null || this.c == null) {
                b = c.c(this.a, this.d, this.e, this.f, this.b);
            } else {
                b = this.c;
            }
            c d = c.d(b);
            if (d != null) {
                return d;
            }
            d = new c();
            c.b(b, d);
            return d;
        }
    }

    public enum b {
        Default(0),
        All(Integer.MAX_VALUE);
        
        private int c;

        private b(int i) {
            this.c = i;
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return j();
    }

    private c(a aVar) {
        if (b(aVar.c)) {
            this.d = e(aVar.c);
            this.e = f(aVar.c);
            this.f = g(aVar.c);
            this.g = h(aVar.c);
            this.h = i(aVar.c);
            this.c = c(this.d, this.e, this.f, this.g, this.h);
            this.i = c(this.d, this.e, this.f, this.g, "");
        } else {
            this.d = aVar.a;
            this.e = aVar.d;
            this.f = aVar.e;
            this.g = aVar.f;
            this.h = aVar.b;
            this.c = c(this.d, this.e, this.f, this.g, this.h);
            this.i = c(this.d, this.e, this.f, this.g, "");
        }
        boolean z = this.c != null && b(this.c);
        this.k = z;
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public Integer c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public Integer e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }

    public boolean g() {
        return this.c == null || this.c.isEmpty();
    }

    public boolean h() {
        return this.k;
    }

    static c a(String str) {
        return null;
    }

    public static boolean b(String str) {
        if (str == null || str.length() == 0 || !str.matches("^\\w+[/](\\d+|\\*)[/]\\w+([/](\\d+)[/]\\w+)?$")) {
            return false;
        }
        return true;
    }

    private static String e(String str) {
        String[] j = j(str);
        if (j.length > 0) {
            return j[0];
        }
        return null;
    }

    private static Integer f(String str) {
        String[] j = j(str);
        if (j.length > 1 && j[1].matches("\\d+")) {
            return Integer.valueOf(Integer.parseInt(j[1]));
        }
        if (j[1].equals("*")) {
            return Integer.valueOf(Integer.MAX_VALUE);
        }
        return Integer.valueOf(0);
    }

    private static String g(String str) {
        String[] j = j(str);
        if (j.length > 3) {
            return j[2];
        }
        return null;
    }

    private static Integer h(String str) {
        String[] j = j(str);
        if (j.length <= 3 || !j[3].matches("\\d+")) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(Integer.parseInt(j[3]));
    }

    private static String i(String str) {
        String[] j = j(str);
        if (j.length > 1) {
            return j[j.length - 1];
        }
        return null;
    }

    private static String a(Integer num) {
        if (num.intValue() == Integer.MAX_VALUE) {
            return "*";
        }
        return num.toString();
    }

    private static String b(Integer num) {
        return a(num);
    }

    private static String c(String str, Integer num, String str2, Integer num2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        if (num.intValue() == 0 && str2 == null) {
            stringBuilder.append(str).append("/0/").append(str3);
            return stringBuilder.toString();
        } else if (str2 == null) {
            stringBuilder.append(str).append(d.t).append(a(num)).append(d.t).append(str3);
            return stringBuilder.toString();
        } else if (num2.intValue() == 0 && str2 != null) {
            stringBuilder.append(str).append(d.t).append(a(num)).append(d.t).append(str2).append("/0/").append(str3);
            return stringBuilder.toString();
        } else if (num2.intValue() == 0 || str2 == null) {
            return null;
        } else {
            stringBuilder.append(str).append(d.t).append(a(num)).append(d.t).append(str2).append(d.t).append(b(num2)).append(d.t).append(str3);
            return stringBuilder.toString();
        }
    }

    public static String a(String str, Integer num, String str2) {
        return a(str, num, null, Integer.valueOf(0), str2);
    }

    public static String a(String str, Integer num, String str2, Integer num2, String str3) {
        return c(str, num, str2, num2, str3);
    }

    public c c(String str) {
        if (str == null) {
            return null;
        }
        c d = d(this.i + str);
        if (d == null) {
            return new a().b(this.d).a(this.e).c(this.f).b(this.g).d(str).a();
        }
        return d;
    }

    public c i() {
        return new a().b(this.f).a(this.g).d(this.h).a();
    }

    private static String[] j(String str) {
        return str.split(d.t);
    }

    public c j() {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof c) {
            return this.c.equals(((c) obj).c);
        }
        return super.equals(obj);
    }

    public int hashCode() {
        if (this.c != null) {
            return this.c.hashCode();
        }
        return 0;
    }

    public String toString() {
        if (this.c == null) {
            return "";
        }
        return this.c;
    }

    public static c d(String str) {
        if (str == null || !j.containsKey(str)) {
            return null;
        }
        return (c) j.get(str);
    }

    private static void b(String str, c cVar) {
        if (str != null && cVar != null && !j.containsKey(str)) {
            j.put(str, cVar);
        }
    }
}
