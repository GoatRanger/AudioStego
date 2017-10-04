package dji.thirdparty.b;

import com.here.odnp.posclient.pos.IPositioningSession;
import com.sina.weibo.sdk.register.mobile.MobileRegisterActivity;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.b.a.b.f;
import dji.thirdparty.b.a.j;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class m {
    private static final Pattern a = Pattern.compile("(\\d{2,4})[^\\d]*");
    private static final Pattern b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");
    private static final Pattern c = Pattern.compile("(\\d{1,2})[^\\d]*");
    private static final Pattern d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");
    private final String e;
    private final String f;
    private final long g;
    private final String h;
    private final String i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    public static final class a {
        String a;
        String b;
        long c = f.a;
        String d;
        String e = d.t;
        boolean f;
        boolean g;
        boolean h;
        boolean i;

        public a a(String str) {
            if (str == null) {
                throw new NullPointerException("name == null");
            } else if (str.trim().equals(str)) {
                this.a = str;
                return this;
            } else {
                throw new IllegalArgumentException("name is not trimmed");
            }
        }

        public a b(String str) {
            if (str == null) {
                throw new NullPointerException("value == null");
            } else if (str.trim().equals(str)) {
                this.b = str;
                return this;
            } else {
                throw new IllegalArgumentException("value is not trimmed");
            }
        }

        public a a(long j) {
            long j2;
            long j3 = f.a;
            if (j <= 0) {
                j2 = Long.MIN_VALUE;
            } else {
                j2 = j;
            }
            if (j2 <= f.a) {
                j3 = j2;
            }
            this.c = j3;
            this.h = true;
            return this;
        }

        public a c(String str) {
            return a(str, false);
        }

        public a d(String str) {
            return a(str, true);
        }

        private a a(String str, boolean z) {
            if (str == null) {
                throw new IllegalArgumentException("domain == null");
            }
            String d = j.d(str);
            if (d == null) {
                throw new IllegalArgumentException("unexpected domain: " + str);
            }
            this.d = d;
            this.i = z;
            return this;
        }

        public a e(String str) {
            if (str.startsWith(d.t)) {
                this.e = str;
                return this;
            }
            throw new IllegalArgumentException("path must start with '/'");
        }

        public a a() {
            this.f = true;
            return this;
        }

        public a b() {
            this.g = true;
            return this;
        }

        public m c() {
            return new m();
        }
    }

    private m(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.e = str;
        this.f = str2;
        this.g = j;
        this.h = str3;
        this.i = str4;
        this.j = z;
        this.k = z2;
        this.m = z3;
        this.l = z4;
    }

    private m(a aVar) {
        if (aVar.a == null) {
            throw new IllegalArgumentException("builder.name == null");
        } else if (aVar.b == null) {
            throw new IllegalArgumentException("builder.value == null");
        } else if (aVar.d == null) {
            throw new IllegalArgumentException("builder.domain == null");
        } else {
            this.e = aVar.a;
            this.f = aVar.b;
            this.g = aVar.c;
            this.h = aVar.d;
            this.i = aVar.e;
            this.j = aVar.f;
            this.k = aVar.g;
            this.l = aVar.h;
            this.m = aVar.i;
        }
    }

    public String a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    public boolean c() {
        return this.l;
    }

    public long d() {
        return this.g;
    }

    public boolean e() {
        return this.m;
    }

    public String f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public boolean h() {
        return this.k;
    }

    public boolean i() {
        return this.j;
    }

    public boolean a(u uVar) {
        boolean equals;
        if (this.m) {
            equals = uVar.i().equals(this.h);
        } else {
            equals = b(uVar, this.h);
        }
        if (!equals || !c(uVar, this.i)) {
            return false;
        }
        if (!this.j || uVar.d()) {
            return true;
        }
        return false;
    }

    private static boolean b(u uVar, String str) {
        String i = uVar.i();
        if (i.equals(str)) {
            return true;
        }
        if (i.endsWith(str) && i.charAt((i.length() - str.length()) - 1) == '.' && !j.e(i)) {
            return true;
        }
        return false;
    }

    private static boolean c(u uVar, String str) {
        String l = uVar.l();
        if (l.equals(str)) {
            return true;
        }
        if (l.startsWith(str) && (str.endsWith(d.t) || l.charAt(str.length()) == '/')) {
            return true;
        }
        return false;
    }

    public static m a(u uVar, String str) {
        return a(System.currentTimeMillis(), uVar, str);
    }

    static m a(long j, u uVar, String str) {
        int length = str.length();
        int a = j.a(str, 0, length, ';');
        int a2 = j.a(str, 0, a, '=');
        if (a2 == a) {
            return null;
        }
        String c = j.c(str, 0, a2);
        if (c.isEmpty()) {
            return null;
        }
        String substring;
        String c2 = j.c(str, a2 + 1, a);
        long j2 = f.a;
        long j3 = -1;
        String str2 = null;
        String str3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = true;
        boolean z4 = false;
        a++;
        while (a < length) {
            long j4;
            int a3 = j.a(str, a, length, ';');
            int a4 = j.a(str, a, a3, '=');
            String c3 = j.c(str, a, a4);
            String c4 = a4 < a3 ? j.c(str, a4 + 1, a3) : "";
            if (c3.equalsIgnoreCase(MobileRegisterActivity.RESPONSE_EXPIRES)) {
                try {
                    j2 = a(c4, 0, c4.length());
                    z4 = true;
                    c4 = str2;
                    j4 = j2;
                } catch (IllegalArgumentException e) {
                    c4 = str2;
                    j4 = j2;
                }
            } else {
                if (c3.equalsIgnoreCase("max-age")) {
                    try {
                        j3 = a(c4);
                        z4 = true;
                        c4 = str2;
                        j4 = j2;
                    } catch (NumberFormatException e2) {
                        c4 = str2;
                        j4 = j2;
                    }
                } else {
                    if (c3.equalsIgnoreCase("domain")) {
                        try {
                            c4 = b(c4);
                            z3 = false;
                            j4 = j2;
                        } catch (IllegalArgumentException e3) {
                            c4 = str2;
                            j4 = j2;
                        }
                    } else {
                        if (c3.equalsIgnoreCase("path")) {
                            str3 = c4;
                            c4 = str2;
                            j4 = j2;
                        } else {
                            if (c3.equalsIgnoreCase("secure")) {
                                z = true;
                                c4 = str2;
                                j4 = j2;
                            } else {
                                if (c3.equalsIgnoreCase("httponly")) {
                                    z2 = true;
                                    c4 = str2;
                                    j4 = j2;
                                } else {
                                    c4 = str2;
                                    j4 = j2;
                                }
                            }
                        }
                    }
                }
            }
            String str4 = c4;
            a = a3 + 1;
            j2 = j4;
            str2 = str4;
        }
        if (j3 == Long.MIN_VALUE) {
            j3 = Long.MIN_VALUE;
        } else if (j3 != -1) {
            j3 = (j3 <= 9223372036854775L ? j3 * 1000 : IPositioningSession.NotSet) + j;
            if (j3 < j || j3 > f.a) {
                j3 = f.a;
            }
        } else {
            j3 = j2;
        }
        if (str2 == null) {
            str2 = uVar.i();
        } else if (!b(uVar, str2)) {
            return null;
        }
        if (str3 == null || !str3.startsWith(d.t)) {
            str3 = uVar.l();
            a = str3.lastIndexOf(47);
            substring = a != 0 ? str3.substring(0, a) : d.t;
        } else {
            substring = str3;
        }
        return new m(c, c2, j3, str2, substring, z, z2, z3, z4);
    }

    private static long a(String str, int i, int i2) {
        int a = a(str, i, i2, false);
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        Matcher matcher = d.matcher(str);
        while (a < i2) {
            int a2 = a(str, a + 1, i2, true);
            matcher.region(a, a2);
            if (i3 == -1 && matcher.usePattern(d).matches()) {
                i3 = Integer.parseInt(matcher.group(1));
                i4 = Integer.parseInt(matcher.group(2));
                i5 = Integer.parseInt(matcher.group(3));
            } else if (i6 == -1 && matcher.usePattern(c).matches()) {
                i6 = Integer.parseInt(matcher.group(1));
            } else if (i7 == -1 && matcher.usePattern(b).matches()) {
                i7 = b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
            } else if (i8 == -1 && matcher.usePattern(a).matches()) {
                i8 = Integer.parseInt(matcher.group(1));
            }
            a = a(str, a2 + 1, i2, false);
        }
        if (i8 >= 70 && i8 <= 99) {
            i8 += 1900;
        }
        if (i8 >= 0 && i8 <= 69) {
            i8 += 2000;
        }
        if (i8 < 1601) {
            throw new IllegalArgumentException();
        } else if (i7 == -1) {
            throw new IllegalArgumentException();
        } else if (i6 < 1 || i6 > 31) {
            throw new IllegalArgumentException();
        } else if (i3 < 0 || i3 > 23) {
            throw new IllegalArgumentException();
        } else if (i4 < 0 || i4 > 59) {
            throw new IllegalArgumentException();
        } else if (i5 < 0 || i5 > 59) {
            throw new IllegalArgumentException();
        } else {
            Calendar gregorianCalendar = new GregorianCalendar(j.d);
            gregorianCalendar.setLenient(false);
            gregorianCalendar.set(1, i8);
            gregorianCalendar.set(2, i7 - 1);
            gregorianCalendar.set(5, i6);
            gregorianCalendar.set(11, i3);
            gregorianCalendar.set(12, i4);
            gregorianCalendar.set(13, i5);
            gregorianCalendar.set(14, 0);
            return gregorianCalendar.getTimeInMillis();
        }
    }

    private static int a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            Object obj;
            char charAt = str.charAt(i3);
            Object obj2 = ((charAt >= ' ' || charAt == '\t') && charAt < '' && ((charAt < '0' || charAt > '9') && ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && charAt != ':')))) ? null : 1;
            if (z) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj2 == obj) {
                return i3;
            }
        }
        return i2;
    }

    private static long a(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong <= 0) {
                return Long.MIN_VALUE;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            if (!str.matches("-?\\d+")) {
                throw e;
            } else if (str.startsWith("-")) {
                return Long.MIN_VALUE;
            } else {
                return IPositioningSession.NotSet;
            }
        }
    }

    private static String b(String str) {
        if (str.endsWith(".")) {
            throw new IllegalArgumentException();
        }
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        String d = j.d(str);
        if (d != null) {
            return d;
        }
        throw new IllegalArgumentException();
    }

    public static List<m> a(u uVar, t tVar) {
        List c = tVar.c("Set-Cookie");
        List list = null;
        int size = c.size();
        for (int i = 0; i < size; i++) {
            m a = a(uVar, (String) c.get(i));
            if (a != null) {
                List arrayList;
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    arrayList = list;
                }
                arrayList.add(a);
                list = arrayList;
            }
        }
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.e);
        stringBuilder.append('=');
        stringBuilder.append(this.f);
        if (this.l) {
            if (this.g == Long.MIN_VALUE) {
                stringBuilder.append("; max-age=0");
            } else {
                stringBuilder.append("; expires=").append(f.a(new Date(this.g)));
            }
        }
        if (!this.m) {
            stringBuilder.append("; domain=").append(this.h);
        }
        stringBuilder.append("; path=").append(this.i);
        if (this.j) {
            stringBuilder.append("; secure");
        }
        if (this.k) {
            stringBuilder.append("; httponly");
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        if (mVar.e.equals(this.e) && mVar.f.equals(this.f) && mVar.h.equals(this.h) && mVar.i.equals(this.i) && mVar.g == this.g && mVar.j == this.j && mVar.k == this.k && mVar.l == this.l && mVar.m == this.m) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = (((((((((this.e.hashCode() + 527) * 31) + this.f.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31;
        if (this.j) {
            i = 0;
        } else {
            i = 1;
        }
        hashCode = (i + hashCode) * 31;
        if (this.k) {
            i = 0;
        } else {
            i = 1;
        }
        hashCode = (i + hashCode) * 31;
        if (this.l) {
            i = 0;
        } else {
            i = 1;
        }
        i = (i + hashCode) * 31;
        if (!this.m) {
            i2 = 1;
        }
        return i + i2;
    }
}
