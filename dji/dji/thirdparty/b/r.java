package dji.thirdparty.b;

import com.google.api.client.http.UrlEncodedParser;
import dji.thirdparty.b.a.j;
import dji.thirdparty.c.c;
import dji.thirdparty.c.d;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class r extends ac {
    private static final w a = w.a(UrlEncodedParser.CONTENT_TYPE);
    private final List<String> b;
    private final List<String> c;

    public static final class a {
        private final List<String> a = new ArrayList();
        private final List<String> b = new ArrayList();

        public a a(String str, String str2) {
            this.a.add(u.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            this.b.add(u.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true));
            return this;
        }

        public a b(String str, String str2) {
            this.a.add(u.a(str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            this.b.add(u.a(str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true));
            return this;
        }

        public r a() {
            return new r(this.a, this.b);
        }
    }

    private r(List<String> list, List<String> list2) {
        this.b = j.a((List) list);
        this.c = j.a((List) list2);
    }

    public int a() {
        return this.b.size();
    }

    public String a(int i) {
        return (String) this.b.get(i);
    }

    public String b(int i) {
        return u.a(a(i), true);
    }

    public String c(int i) {
        return (String) this.c.get(i);
    }

    public String d(int i) {
        return u.a(c(i), true);
    }

    public w b() {
        return a;
    }

    public long c() {
        return a(null, true);
    }

    public void a(d dVar) throws IOException {
        a(dVar, false);
    }

    private long a(d dVar, boolean z) {
        c cVar;
        long j = 0;
        if (z) {
            cVar = new c();
        } else {
            cVar = dVar.c();
        }
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                cVar.b(38);
            }
            cVar.a((String) this.b.get(i));
            cVar.b(61);
            cVar.a((String) this.c.get(i));
        }
        if (z) {
            j = cVar.b();
            cVar.y();
        }
        return j;
    }
}
