package dji.thirdparty.b;

import android.support.v4.media.TransportMediator;
import com.alipay.sdk.b.b;
import com.google.android.gms.location.places.Place;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.b.a.j;
import dji.thirdparty.c.c;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class u {
    static final String a = " \"':;<=>@[]^`{}|/\\?#";
    static final String b = " \"':;<=>@[]^`{}|/\\?#";
    static final String c = " \"<>^`{}|/\\?#";
    static final String d = "[]";
    static final String e = " \"'<>#";
    static final String f = " \"'<>#&=";
    static final String g = "\\^`{|}";
    static final String h = " \"':;<=>@[]^`{}|/\\?#&!$(),~";
    static final String i = "";
    static final String j = " \"#<>\\^`{|}";
    private static final char[] k = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final int p;
    private final List<String> q;
    private final List<String> r;
    private final String s;
    private final String t;

    public static final class a {
        String a;
        String b = "";
        String c = "";
        String d;
        int e = -1;
        final List<String> f = new ArrayList();
        List<String> g;
        String h;

        enum a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public a() {
            this.f.add("");
        }

        public a a(String str) {
            if (str == null) {
                throw new IllegalArgumentException("scheme == null");
            }
            if (str.equalsIgnoreCase("http")) {
                this.a = "http";
            } else if (str.equalsIgnoreCase(b.a)) {
                this.a = b.a;
            } else {
                throw new IllegalArgumentException("unexpected scheme: " + str);
            }
            return this;
        }

        public a b(String str) {
            if (str == null) {
                throw new IllegalArgumentException("username == null");
            }
            this.b = u.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a c(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedUsername == null");
            }
            this.b = u.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public a d(String str) {
            if (str == null) {
                throw new IllegalArgumentException("password == null");
            }
            this.c = u.a(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a e(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPassword == null");
            }
            this.c = u.a(str, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
            return this;
        }

        public a f(String str) {
            if (str == null) {
                throw new IllegalArgumentException("host == null");
            }
            String e = e(str, 0, str.length());
            if (e == null) {
                throw new IllegalArgumentException("unexpected host: " + str);
            }
            this.d = e;
            return this;
        }

        public a a(int i) {
            if (i <= 0 || i > 65535) {
                throw new IllegalArgumentException("unexpected port: " + i);
            }
            this.e = i;
            return this;
        }

        int a() {
            return this.e != -1 ? this.e : u.a(this.a);
        }

        public a g(String str) {
            if (str == null) {
                throw new IllegalArgumentException("pathSegment == null");
            }
            a(str, 0, str.length(), false, false);
            return this;
        }

        public a h(String str) {
            if (str != null) {
                return a(str, false);
            }
            throw new IllegalArgumentException("pathSegments == null");
        }

        public a i(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPathSegment == null");
            }
            a(str, 0, str.length(), false, true);
            return this;
        }

        public a j(String str) {
            if (str != null) {
                return a(str, true);
            }
            throw new IllegalArgumentException("encodedPathSegments == null");
        }

        private a a(String str, boolean z) {
            int i = 0;
            do {
                boolean z2;
                int a = j.a(str, i, str.length(), "/\\");
                if (a < str.length()) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                a(str, i, a, z2, z);
                i = a + 1;
            } while (i <= str.length());
            return this;
        }

        public a a(int i, String str) {
            if (str == null) {
                throw new IllegalArgumentException("pathSegment == null");
            }
            String a = u.a(str, 0, str.length(), u.c, false, false, false, true);
            if (s(a) || t(a)) {
                throw new IllegalArgumentException("unexpected path segment: " + str);
            }
            this.f.set(i, a);
            return this;
        }

        public a b(int i, String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPathSegment == null");
            }
            String a = u.a(str, 0, str.length(), u.c, true, false, false, true);
            this.f.set(i, a);
            if (!s(a) && !t(a)) {
                return this;
            }
            throw new IllegalArgumentException("unexpected path segment: " + str);
        }

        public a b(int i) {
            this.f.remove(i);
            if (this.f.isEmpty()) {
                this.f.add("");
            }
            return this;
        }

        public a k(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedPath == null");
            } else if (str.startsWith(d.t)) {
                a(str, 0, str.length());
                return this;
            } else {
                throw new IllegalArgumentException("unexpected encodedPath: " + str);
            }
        }

        public a l(String str) {
            List b;
            if (str != null) {
                b = u.b(u.a(str, u.e, false, false, true, true));
            } else {
                b = null;
            }
            this.g = b;
            return this;
        }

        public a m(String str) {
            List b;
            if (str != null) {
                b = u.b(u.a(str, u.e, true, false, true, true));
            } else {
                b = null;
            }
            this.g = b;
            return this;
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            Object a;
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(u.a(str, u.f, false, false, true, true));
            List list = this.g;
            if (str2 != null) {
                a = u.a(str2, u.f, false, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        public a b(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("encodedName == null");
            }
            Object a;
            if (this.g == null) {
                this.g = new ArrayList();
            }
            this.g.add(u.a(str, u.f, true, false, true, true));
            List list = this.g;
            if (str2 != null) {
                a = u.a(str2, u.f, true, false, true, true);
            } else {
                a = null;
            }
            list.add(a);
            return this;
        }

        public a c(String str, String str2) {
            n(str);
            a(str, str2);
            return this;
        }

        public a d(String str, String str2) {
            o(str);
            b(str, str2);
            return this;
        }

        public a n(String str) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            }
            if (this.g != null) {
                r(u.a(str, u.f, false, false, true, true));
            }
            return this;
        }

        public a o(String str) {
            if (str == null) {
                throw new IllegalArgumentException("encodedName == null");
            }
            if (this.g != null) {
                r(u.a(str, u.f, true, false, true, true));
            }
            return this;
        }

        private void r(String str) {
            for (int size = this.g.size() - 2; size >= 0; size -= 2) {
                if (str.equals(this.g.get(size))) {
                    this.g.remove(size + 1);
                    this.g.remove(size);
                    if (this.g.isEmpty()) {
                        this.g = null;
                        return;
                    }
                }
            }
        }

        public a p(String str) {
            String a;
            if (str != null) {
                a = u.a(str, "", false, false, false, false);
            } else {
                a = null;
            }
            this.h = a;
            return this;
        }

        public a q(String str) {
            String a;
            if (str != null) {
                a = u.a(str, "", true, false, false, false);
            } else {
                a = null;
            }
            this.h = a;
            return this;
        }

        a b() {
            int size = this.f.size();
            for (int i = 0; i < size; i++) {
                this.f.set(i, u.a((String) this.f.get(i), u.d, true, true, false, true));
            }
            if (this.g != null) {
                int size2 = this.g.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    String str = (String) this.g.get(i2);
                    if (str != null) {
                        this.g.set(i2, u.a(str, u.g, true, true, true, true));
                    }
                }
            }
            if (this.h != null) {
                this.h = u.a(this.h, u.j, true, true, false, false);
            }
            return this;
        }

        public u c() {
            if (this.a == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.d != null) {
                return new u();
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append("://");
            if (!(this.b.isEmpty() && this.c.isEmpty())) {
                stringBuilder.append(this.b);
                if (!this.c.isEmpty()) {
                    stringBuilder.append(':');
                    stringBuilder.append(this.c);
                }
                stringBuilder.append('@');
            }
            if (this.d.indexOf(58) != -1) {
                stringBuilder.append('[');
                stringBuilder.append(this.d);
                stringBuilder.append(']');
            } else {
                stringBuilder.append(this.d);
            }
            int a = a();
            if (a != u.a(this.a)) {
                stringBuilder.append(':');
                stringBuilder.append(a);
            }
            u.a(stringBuilder, this.f);
            if (this.g != null) {
                stringBuilder.append('?');
                u.b(stringBuilder, this.g);
            }
            if (this.h != null) {
                stringBuilder.append('#');
                stringBuilder.append(this.h);
            }
            return stringBuilder.toString();
        }

        a a(u uVar, String str) {
            int d;
            int a = j.a(str, 0, str.length());
            int b = j.b(str, a, str.length());
            if (b(str, a, b) != -1) {
                if (str.regionMatches(true, a, "https:", 0, 6)) {
                    this.a = b.a;
                    a += "https:".length();
                } else {
                    if (!str.regionMatches(true, a, "http:", 0, 5)) {
                        return a.UNSUPPORTED_SCHEME;
                    }
                    this.a = "http";
                    a += "http:".length();
                }
            } else if (uVar == null) {
                return a.MISSING_SCHEME;
            } else {
                this.a = uVar.l;
            }
            int c = c(str, a, b);
            if (c >= 2 || uVar == null || !uVar.l.equals(this.a)) {
                Object obj = null;
                Object obj2 = null;
                int i = a + c;
                while (true) {
                    Object obj3;
                    Object obj4;
                    int a2 = j.a(str, i, b, "@/\\?#");
                    switch (a2 != b ? str.charAt(a2) : '￿') {
                        case '￿':
                        case '#':
                        case '/':
                        case '?':
                        case Place.TYPE_TRAIN_STATION /*92*/:
                            d = d(str, i, a2);
                            if (d + 1 < a2) {
                                this.d = e(str, i, d);
                                this.e = g(str, d + 1, a2);
                                if (this.e == -1) {
                                    return a.INVALID_PORT;
                                }
                            }
                            this.d = e(str, i, d);
                            this.e = u.a(this.a);
                            if (this.d != null) {
                                a = a2;
                                break;
                            }
                            return a.INVALID_HOST;
                        case '@':
                            if (obj == null) {
                                a = j.a(str, i, a2, ':');
                                String a3 = u.a(str, i, a, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                if (obj2 != null) {
                                    a3 = this.b + "%40" + a3;
                                }
                                this.b = a3;
                                if (a != a2) {
                                    obj = 1;
                                    this.c = u.a(str, a + 1, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                }
                                obj2 = 1;
                            } else {
                                this.c += "%40" + u.a(str, i, a2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            }
                            a = a2 + 1;
                            obj3 = obj;
                            obj4 = obj2;
                            continue;
                        default:
                            obj3 = obj;
                            a = i;
                            obj4 = obj2;
                            continue;
                    }
                    obj = obj3;
                    obj2 = obj4;
                    i = a;
                }
            } else {
                this.b = uVar.e();
                this.c = uVar.g();
                this.d = uVar.o;
                this.e = uVar.p;
                this.f.clear();
                this.f.addAll(uVar.m());
                if (a == b || str.charAt(a) == '#') {
                    m(uVar.o());
                }
            }
            d = j.a(str, a, b, "?#");
            a(str, a, d);
            if (d >= b || str.charAt(d) != '?') {
                a = d;
            } else {
                a = j.a(str, d, b, '#');
                this.g = u.b(u.a(str, d + 1, a, u.e, true, false, true, true));
            }
            if (a < b && str.charAt(a) == '#') {
                this.h = u.a(str, a + 1, b, "", true, false, false, false);
            }
            return a.SUCCESS;
        }

        private void a(String str, int i, int i2) {
            if (i != i2) {
                char charAt = str.charAt(i);
                if (charAt == '/' || charAt == '\\') {
                    this.f.clear();
                    this.f.add("");
                    i++;
                } else {
                    this.f.set(this.f.size() - 1, "");
                }
                int i3 = i;
                while (i3 < i2) {
                    int a = j.a(str, i3, i2, "/\\");
                    boolean z = a < i2;
                    a(str, i3, a, z, true);
                    if (z) {
                        a++;
                    }
                    i3 = a;
                }
            }
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String a = u.a(str, i, i2, u.c, z2, false, false, true);
            if (!s(a)) {
                if (t(a)) {
                    d();
                    return;
                }
                if (((String) this.f.get(this.f.size() - 1)).isEmpty()) {
                    this.f.set(this.f.size() - 1, a);
                } else {
                    this.f.add(a);
                }
                if (z) {
                    this.f.add("");
                }
            }
        }

        private boolean s(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean t(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private void d() {
            if (!((String) this.f.remove(this.f.size() - 1)).isEmpty() || this.f.isEmpty()) {
                this.f.add("");
            } else {
                this.f.set(this.f.size() - 1, "");
            }
        }

        private static int b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char charAt = str.charAt(i);
            if ((charAt < 'a' || charAt > 'z') && (charAt < 'A' || charAt > 'Z')) {
                return -1;
            }
            int i3 = i + 1;
            while (i3 < i2) {
                char charAt2 = str.charAt(i3);
                if ((charAt2 >= 'a' && charAt2 <= 'z') || ((charAt2 >= 'A' && charAt2 <= 'Z') || ((charAt2 >= '0' && charAt2 <= '9') || charAt2 == '+' || charAt2 == '-' || charAt2 == '.'))) {
                    i3++;
                } else if (charAt2 == ':') {
                    return i3;
                } else {
                    return -1;
                }
            }
            return -1;
        }

        private static int c(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int d(java.lang.String r3, int r4, int r5) {
            /*
            r0 = r4;
        L_0x0001:
            if (r0 >= r5) goto L_0x001a;
        L_0x0003:
            r1 = r3.charAt(r0);
            switch(r1) {
                case 58: goto L_0x001b;
                case 91: goto L_0x000d;
                default: goto L_0x000a;
            };
        L_0x000a:
            r0 = r0 + 1;
            goto L_0x0001;
        L_0x000d:
            r0 = r0 + 1;
            if (r0 >= r5) goto L_0x000a;
        L_0x0011:
            r1 = r3.charAt(r0);
            r2 = 93;
            if (r1 != r2) goto L_0x000d;
        L_0x0019:
            goto L_0x000a;
        L_0x001a:
            r0 = r5;
        L_0x001b:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.b.u.a.d(java.lang.String, int, int):int");
        }

        private static String e(String str, int i, int i2) {
            String a = u.a(str, i, i2, false);
            if (!a.contains(":")) {
                return j.d(a);
            }
            InetAddress f;
            if (a.startsWith(d.G) && a.endsWith(d.H)) {
                f = f(a, 1, a.length() - 1);
            } else {
                f = f(a, 0, a.length());
            }
            if (f == null) {
                return null;
            }
            byte[] address = f.getAddress();
            if (address.length == 16) {
                return a(address);
            }
            throw new AssertionError();
        }

        private static InetAddress f(String str, int i, int i2) {
            byte[] bArr = new byte[16];
            int i3 = i;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            while (i3 < i2) {
                if (i6 == bArr.length) {
                    return null;
                }
                int a;
                if (i3 + 2 > i2 || !str.regionMatches(i3, "::", 0, 2)) {
                    if (i6 != 0) {
                        if (str.regionMatches(i3, ":", 0, 1)) {
                            i3++;
                        } else if (!str.regionMatches(i3, ".", 0, 1)) {
                            return null;
                        } else {
                            if (!a(str, i4, i2, bArr, i6 - 2)) {
                                return null;
                            }
                            i6 += 2;
                        }
                    }
                } else if (i5 != -1) {
                    return null;
                } else {
                    i3 += 2;
                    i5 = i6 + 2;
                    if (i3 == i2) {
                        i6 = i5;
                        break;
                    }
                    i6 = i5;
                }
                i4 = 0;
                int i7 = i3;
                while (i7 < i2) {
                    a = u.a(str.charAt(i7));
                    if (a == -1) {
                        break;
                    }
                    i4 = (i4 << 4) + a;
                    i7++;
                }
                a = i7 - i3;
                if (a == 0 || a > 4) {
                    return null;
                }
                a = i6 + 1;
                bArr[i6] = (byte) ((i4 >>> 8) & 255);
                i6 = a + 1;
                bArr[a] = (byte) (i4 & 255);
                i4 = i3;
                i3 = i7;
            }
            if (i6 != bArr.length) {
                if (i5 == -1) {
                    return null;
                }
                System.arraycopy(bArr, i5, bArr, bArr.length - (i6 - i5), i6 - i5);
                Arrays.fill(bArr, i5, (bArr.length - i6) + i5, (byte) 0);
            }
            try {
                return InetAddress.getByAddress(bArr);
            } catch (UnknownHostException e) {
                throw new AssertionError();
            }
        }

        private static boolean a(String str, int i, int i2, byte[] bArr, int i3) {
            int i4 = i;
            int i5 = i3;
            while (i4 < i2) {
                if (i5 == bArr.length) {
                    return false;
                }
                if (i5 != i3) {
                    if (str.charAt(i4) != '.') {
                        return false;
                    }
                    i4++;
                }
                int i6 = 0;
                int i7 = i4;
                while (i7 < i2) {
                    char charAt = str.charAt(i7);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    } else if (i6 == 0 && i4 != i7) {
                        return false;
                    } else {
                        i6 = ((i6 * 10) + charAt) - 48;
                        if (i6 > 255) {
                            return false;
                        }
                        i7++;
                    }
                }
                if (i7 - i4 == 0) {
                    return false;
                }
                i4 = i5 + 1;
                bArr[i5] = (byte) i6;
                i5 = i4;
                i4 = i7;
            }
            if (i5 != i3 + 4) {
                return false;
            }
            return true;
        }

        private static String a(byte[] bArr) {
            int i = 0;
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            while (i4 < bArr.length) {
                int i5 = i4;
                while (i5 < 16 && bArr[i5] == (byte) 0 && bArr[i5 + 1] == (byte) 0) {
                    i5 += 2;
                }
                int i6 = i5 - i4;
                if (i6 > i2) {
                    i2 = i6;
                    i3 = i4;
                }
                i4 = i5 + 2;
            }
            c cVar = new c();
            while (i < bArr.length) {
                if (i == i3) {
                    cVar.b(58);
                    i += i2;
                    if (i == 16) {
                        cVar.b(58);
                    }
                } else {
                    if (i > 0) {
                        cVar.b(58);
                    }
                    cVar.l((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)));
                    i += 2;
                }
            }
            return cVar.t();
        }

        private static int g(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(u.a(str, i, i2, "", false, false, false, true));
                return (parseInt <= 0 || parseInt > 65535) ? -1 : parseInt;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    private u(a aVar) {
        String str = null;
        this.l = aVar.a;
        this.m = a(aVar.b, false);
        this.n = a(aVar.c, false);
        this.o = aVar.d;
        this.p = aVar.a();
        this.q = a(aVar.f, false);
        this.r = aVar.g != null ? a(aVar.g, true) : null;
        if (aVar.h != null) {
            str = a(aVar.h, false);
        }
        this.s = str;
        this.t = aVar.toString();
    }

    public URL a() {
        try {
            return new URL(this.t);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public URI b() {
        String aVar = u().b().toString();
        try {
            return new URI(aVar);
        } catch (Throwable e) {
            try {
                return URI.create(aVar.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception e2) {
                throw new RuntimeException(e);
            }
        }
    }

    public String c() {
        return this.l;
    }

    public boolean d() {
        return this.l.equals(b.a);
    }

    public String e() {
        if (this.m.isEmpty()) {
            return "";
        }
        int length = this.l.length() + 3;
        return this.t.substring(length, j.a(this.t, length, this.t.length(), ":@"));
    }

    public String f() {
        return this.m;
    }

    public String g() {
        if (this.n.isEmpty()) {
            return "";
        }
        return this.t.substring(this.t.indexOf(58, this.l.length() + 3) + 1, this.t.indexOf(64));
    }

    public String h() {
        return this.n;
    }

    public String i() {
        return this.o;
    }

    public int j() {
        return this.p;
    }

    public static int a(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals(b.a)) {
            return 443;
        }
        return -1;
    }

    public int k() {
        return this.q.size();
    }

    public String l() {
        int indexOf = this.t.indexOf(47, this.l.length() + 3);
        return this.t.substring(indexOf, j.a(this.t, indexOf, this.t.length(), "?#"));
    }

    static void a(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append('/');
            stringBuilder.append((String) list.get(i));
        }
    }

    public List<String> m() {
        int indexOf = this.t.indexOf(47, this.l.length() + 3);
        int a = j.a(this.t, indexOf, this.t.length(), "?#");
        List<String> arrayList = new ArrayList();
        while (indexOf < a) {
            int i = indexOf + 1;
            indexOf = j.a(this.t, i, a, '/');
            arrayList.add(this.t.substring(i, indexOf));
        }
        return arrayList;
    }

    public List<String> n() {
        return this.q;
    }

    public String o() {
        if (this.r == null) {
            return null;
        }
        int indexOf = this.t.indexOf(63) + 1;
        return this.t.substring(indexOf, j.a(this.t, indexOf + 1, this.t.length(), '#'));
    }

    static void b(StringBuilder stringBuilder, List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i += 2) {
            String str = (String) list.get(i);
            String str2 = (String) list.get(i + 1);
            if (i > 0) {
                stringBuilder.append('&');
            }
            stringBuilder.append(str);
            if (str2 != null) {
                stringBuilder.append('=');
                stringBuilder.append(str2);
            }
        }
    }

    static List<String> b(String str) {
        List<String> arrayList = new ArrayList();
        int i = 0;
        while (i <= str.length()) {
            int indexOf = str.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i, indexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i, indexOf2));
                arrayList.add(str.substring(indexOf2 + 1, indexOf));
            }
            i = indexOf + 1;
        }
        return arrayList;
    }

    public String p() {
        if (this.r == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, this.r);
        return stringBuilder.toString();
    }

    public int q() {
        return this.r != null ? this.r.size() / 2 : 0;
    }

    public String c(String str) {
        if (this.r == null) {
            return null;
        }
        int size = this.r.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.r.get(i))) {
                return (String) this.r.get(i + 1);
            }
        }
        return null;
    }

    public Set<String> r() {
        if (this.r == null) {
            return Collections.emptySet();
        }
        Set linkedHashSet = new LinkedHashSet();
        int size = this.r.size();
        for (int i = 0; i < size; i += 2) {
            linkedHashSet.add(this.r.get(i));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public List<String> d(String str) {
        if (this.r == null) {
            return Collections.emptyList();
        }
        List arrayList = new ArrayList();
        int size = this.r.size();
        for (int i = 0; i < size; i += 2) {
            if (str.equals(this.r.get(i))) {
                arrayList.add(this.r.get(i + 1));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String a(int i) {
        return (String) this.r.get(i * 2);
    }

    public String b(int i) {
        return (String) this.r.get((i * 2) + 1);
    }

    public String s() {
        if (this.s == null) {
            return null;
        }
        return this.t.substring(this.t.indexOf(35) + 1);
    }

    public String t() {
        return this.s;
    }

    public u e(String str) {
        a f = f(str);
        return f != null ? f.c() : null;
    }

    public a u() {
        a aVar = new a();
        aVar.a = this.l;
        aVar.b = e();
        aVar.c = g();
        aVar.d = this.o;
        aVar.e = this.p != a(this.l) ? this.p : -1;
        aVar.f.clear();
        aVar.f.addAll(m());
        aVar.m(o());
        aVar.h = s();
        return aVar;
    }

    public a f(String str) {
        a aVar = new a();
        return aVar.a(this, str) == a.SUCCESS ? aVar : null;
    }

    public static u g(String str) {
        a aVar = new a();
        if (aVar.a(null, str) == a.SUCCESS) {
            return aVar.c();
        }
        return null;
    }

    public static u a(URL url) {
        return g(url.toString());
    }

    static u h(String str) throws MalformedURLException, UnknownHostException {
        a aVar = new a();
        a a = aVar.a(null, str);
        switch (a) {
            case SUCCESS:
                return aVar.c();
            case INVALID_HOST:
                throw new UnknownHostException("Invalid host: " + str);
            default:
                throw new MalformedURLException("Invalid URL: " + a + " for " + str);
        }
    }

    public static u a(URI uri) {
        return g(uri.toString());
    }

    public boolean equals(Object obj) {
        return (obj instanceof u) && ((u) obj).t.equals(this.t);
    }

    public int hashCode() {
        return this.t.hashCode();
    }

    public String toString() {
        return this.t;
    }

    static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    private List<String> a(List<String> list, boolean z) {
        List arrayList = new ArrayList(list.size());
        for (String str : list) {
            arrayList.add(str != null ? a(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static String a(String str, int i, int i2, boolean z) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '%' || (charAt == '+' && z)) {
                c cVar = new c();
                cVar.a(str, i, i3);
                a(cVar, str, i3, i2, z);
                return cVar.t();
            }
        }
        return str.substring(i, i2);
    }

    static void a(c cVar, String str, int i, int i2, boolean z) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt != 37 || i3 + 2 >= i2) {
                if (codePointAt == 43 && z) {
                    cVar.b(32);
                }
                cVar.a(codePointAt);
            } else {
                int a = a(str.charAt(i3 + 1));
                int a2 = a(str.charAt(i3 + 2));
                if (!(a == -1 || a2 == -1)) {
                    cVar.b((a << 4) + a2);
                    i3 += 2;
                }
                cVar.a(codePointAt);
            }
            i3 += Character.charCount(codePointAt);
        }
    }

    static boolean a(String str, int i, int i2) {
        return i + 2 < i2 && str.charAt(i) == '%' && a(str.charAt(i + 1)) != -1 && a(str.charAt(i + 2)) != -1;
    }

    static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 65) + 10;
    }

    static String a(String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int i3 = i;
        while (i3 < i2) {
            int codePointAt = str.codePointAt(i3);
            if (codePointAt < 32 || codePointAt == TransportMediator.KEYCODE_MEDIA_PAUSE || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || ((codePointAt == 37 && (!z || (z2 && !a(str, i3, i2)))) || (codePointAt == 43 && z3)))) {
                c cVar = new c();
                cVar.a(str, i, i3);
                a(cVar, str, i3, i2, str2, z, z2, z3, z4);
                return cVar.t();
            }
            i3 += Character.charCount(codePointAt);
        }
        return str.substring(i, i2);
    }

    static void a(c cVar, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        c cVar2 = null;
        while (i < i2) {
            int codePointAt = str.codePointAt(i);
            if (!(z && (codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13))) {
                if (codePointAt == 43 && z3) {
                    cVar.a(z ? "+" : "%2B");
                } else if (codePointAt < 32 || codePointAt == TransportMediator.KEYCODE_MEDIA_PAUSE || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !a(str, i, i2)))))) {
                    if (cVar2 == null) {
                        cVar2 = new c();
                    }
                    cVar2.a(codePointAt);
                    while (!cVar2.g()) {
                        int j = cVar2.j() & 255;
                        cVar.b(37);
                        cVar.b(k[(j >> 4) & 15]);
                        cVar.b(k[j & 15]);
                    }
                } else {
                    cVar.a(codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4);
    }
}
