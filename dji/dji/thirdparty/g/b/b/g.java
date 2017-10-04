package dji.thirdparty.g.b.b;

import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.a.j;
import dji.thirdparty.g.a.m;
import dji.thirdparty.g.b.b.a.e;
import dji.thirdparty.g.b.b.c.h;
import dji.thirdparty.g.f;
import java.util.ArrayList;
import java.util.List;

public class g extends j implements dji.thirdparty.g.b.b.a.g {
    public final b b;

    public static class a extends j implements dji.thirdparty.g.a.i.a {
        public final int b;
        private final c c;

        public a(c cVar) {
            this.b = cVar.j;
            this.c = cVar;
        }

        public void a(e eVar) {
            a(new c(eVar));
        }

        public e a(e eVar) throws dji.thirdparty.g.e {
            return this.c.a(eVar);
        }

        public List b() throws dji.thirdparty.g.e {
            return this.c.b();
        }

        public a c() {
            return this.c.i();
        }

        public String a(String str) {
            return (str != null ? str : "") + this.c.a() + ": " + (c() != null ? " (jpegImageData)" : "") + "\n" + super.a(str) + "\n";
        }

        public dji.thirdparty.g.b.b.c.e a(int i) throws f {
            try {
                dji.thirdparty.g.b.b.c.e eVar = new dji.thirdparty.g.b.b.c.e(this.b);
                ArrayList a = a();
                for (int i2 = 0; i2 < a.size(); i2++) {
                    e c = ((c) a.get(i2)).c();
                    if (eVar.b(c.l) == null && !(c.j instanceof dji.thirdparty.g.b.b.a.e.b)) {
                        e eVar2 = c.j;
                        dji.thirdparty.g.b.b.b.a aVar = c.k;
                        dji.thirdparty.g.b.b.c.f fVar = new dji.thirdparty.g.b.b.c.f(c.l, eVar2, aVar, c.o, eVar2.a(aVar, c.i(), i));
                        fVar.a(c.q());
                        eVar.a(fVar);
                    }
                }
                eVar.a(c());
                return eVar;
            } catch (Exception e) {
                throw new f(e.getMessage(), e);
            }
        }
    }

    public static class b {
        public final String a;
        public final String b;
        public final m c;
        public final m d;
        public final m e;
        public final m f;
        public final m g;
        public final m h;

        public b(String str, String str2, m mVar, m mVar2, m mVar3, m mVar4, m mVar5, m mVar6) {
            this.a = str;
            this.b = str2;
            this.c = mVar;
            this.d = mVar2;
            this.e = mVar3;
            this.f = mVar4;
            this.g = mVar5;
            this.h = mVar6;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[GPS. ");
            stringBuffer.append("Latitude: " + this.c.c() + " degrees, " + this.d.c() + " minutes, " + this.e.c() + " seconds " + this.a);
            stringBuffer.append(", Longitude: " + this.f.c() + " degrees, " + this.g.c() + " minutes, " + this.h.c() + " seconds " + this.b);
            stringBuffer.append(d.H);
            return stringBuffer.toString();
        }

        public double a() throws dji.thirdparty.g.e {
            double doubleValue = (this.f.doubleValue() + (this.g.doubleValue() / 60.0d)) + (this.h.doubleValue() / 3600.0d);
            if (this.b.trim().equalsIgnoreCase("e")) {
                return doubleValue;
            }
            if (this.b.trim().equalsIgnoreCase("w")) {
                return -doubleValue;
            }
            throw new dji.thirdparty.g.e("Unknown longitude ref: \"" + this.b + "\"");
        }

        public double b() throws dji.thirdparty.g.e {
            double doubleValue = (this.c.doubleValue() + (this.d.doubleValue() / 60.0d)) + (this.e.doubleValue() / 3600.0d);
            if (this.a.trim().equalsIgnoreCase("n")) {
                return doubleValue;
            }
            if (this.a.trim().equalsIgnoreCase("s")) {
                return -doubleValue;
            }
            throw new dji.thirdparty.g.e("Unknown latitude ref: \"" + this.a + "\"");
        }
    }

    public static class c extends dji.thirdparty.g.a.j.a {
        private final e a;

        public c(e eVar) {
            super(eVar.g(), eVar.d());
            this.a = eVar;
        }

        public e c() {
            return this.a;
        }
    }

    public g(b bVar) {
        this.b = bVar;
    }

    public ArrayList b() {
        return super.a();
    }

    public ArrayList a() {
        ArrayList arrayList = new ArrayList();
        ArrayList a = super.a();
        for (int i = 0; i < a.size(); i++) {
            arrayList.addAll(((a) a.get(i)).a());
        }
        return arrayList;
    }

    public h c() throws f {
        int i = this.b.a.a;
        h hVar = new h(i);
        ArrayList b = b();
        for (int i2 = 0; i2 < b.size(); i2++) {
            a aVar = (a) b.get(i2);
            if (hVar.a(aVar.b) == null) {
                hVar.a(aVar.a(i));
            }
        }
        return hVar;
    }

    public e a(e eVar) throws dji.thirdparty.g.e {
        ArrayList b = b();
        for (int i = 0; i < b.size(); i++) {
            e a = ((a) b.get(i)).a(eVar);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    public c a(int i) {
        ArrayList b = b();
        for (int i2 = 0; i2 < b.size(); i2++) {
            a aVar = (a) b.get(i2);
            if (aVar.b == i) {
                return aVar.c;
            }
        }
        return null;
    }

    public List d() throws dji.thirdparty.g.e {
        List arrayList = new ArrayList();
        ArrayList b = b();
        for (int i = 0; i < b.size(); i++) {
            arrayList.addAll(((a) b.get(i)).b());
        }
        return arrayList;
    }

    public b e() throws dji.thirdparty.g.e {
        c a = a(-3);
        if (a == null) {
            return null;
        }
        e a2 = a.a(dji.thirdparty.g.b.b.a.f.ie_);
        e a3 = a.a(dji.thirdparty.g.b.b.a.f.y);
        e a4 = a.a(dji.thirdparty.g.b.b.a.f.z);
        e a5 = a.a(dji.thirdparty.g.b.b.a.f.C);
        if (a2 == null || a3 == null || a4 == null || a5 == null) {
            return null;
        }
        String j = a2.j();
        m[] mVarArr = (m[]) a3.i();
        String j2 = a4.j();
        m[] mVarArr2 = (m[]) a5.i();
        if (mVarArr.length == 3 && mVarArr2.length == 3) {
            return new b(j, j2, mVarArr[0], mVarArr[1], mVarArr[2], mVarArr2[0], mVarArr2[1], mVarArr2[2]);
        }
        throw new dji.thirdparty.g.e("Expected three values for latitude and longitude.");
    }
}
