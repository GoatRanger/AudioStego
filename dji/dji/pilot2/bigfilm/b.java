package dji.pilot2.bigfilm;

import java.util.ArrayList;
import java.util.List;

public class b {
    protected float a;
    protected float b;
    protected b c;
    protected String d;
    protected String e;
    protected List<a> f;

    public static class a {
        public String a;
        public String b;
        public String c;
    }

    public static class b {
        public c a;
        public float b;
    }

    public int a(String str, String str2, String str3) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        a aVar = new a();
        aVar.b = str2;
        aVar.a = str;
        aVar.c = str3;
        this.f.add(aVar);
        return 0;
    }

    public List<a> a() {
        return this.f;
    }

    public int b() {
        return (int) (this.a * 1000.0f);
    }

    public int c() {
        return (int) ((this.a * 1000.0f) / this.b);
    }

    public float d() {
        return this.b;
    }

    public void a(float f) {
        if (f <= 0.0f) {
            f = 1.0f;
        }
        this.b = f;
    }

    public c e() {
        return this.c.a;
    }

    public void a(c cVar) {
        this.c.a = cVar;
    }

    public String f() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String g() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public float h() {
        return this.c.b;
    }

    public void b(float f) {
        this.c.b = f;
    }

    public void c(float f) {
        this.a = f;
    }

    public b i() {
        return this.c;
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
