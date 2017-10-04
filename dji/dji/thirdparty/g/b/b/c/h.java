package dji.thirdparty.g.b.b.c;

import com.alipay.sdk.j.i;
import dji.thirdparty.g.b.b.a.e;
import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.c.c;
import java.util.ArrayList;
import java.util.List;

public final class h implements f {
    private static final String l = System.getProperty("line.separator");
    public final int j;
    private final ArrayList k;

    public h() {
        this(73);
    }

    public h(int i) {
        this.k = new ArrayList();
        this.j = i;
    }

    protected List a(i iVar) throws dji.thirdparty.g.f {
        List arrayList = new ArrayList();
        for (int i = 0; i < this.k.size(); i++) {
            arrayList.addAll(((e) this.k.get(i)).a(iVar));
        }
        return arrayList;
    }

    public void a(e eVar) throws dji.thirdparty.g.f {
        if (a(eVar.j) != null) {
            throw new dji.thirdparty.g.f("Output set already contains a directory of that type.");
        }
        this.k.add(eVar);
    }

    public List a() {
        return new ArrayList(this.k);
    }

    public e b() {
        return a(0);
    }

    public e c() {
        return a(-2);
    }

    public e d() throws dji.thirdparty.g.f {
        e a = a(0);
        return a != null ? a : i();
    }

    public e e() throws dji.thirdparty.g.f {
        d();
        e a = a(-2);
        return a != null ? a : j();
    }

    public e f() throws dji.thirdparty.g.f {
        e();
        e a = a(-3);
        return a != null ? a : k();
    }

    public e g() {
        return a(-3);
    }

    public e h() {
        return a(-4);
    }

    public e a(int i) {
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            e eVar = (e) this.k.get(i2);
            if (eVar.j == i) {
                return eVar;
            }
        }
        return null;
    }

    public void a(double d, double d2) throws dji.thirdparty.g.f {
        e f = f();
        String str = d < 0.0d ? "W" : "E";
        double abs = Math.abs(d);
        String str2 = d2 < 0.0d ? "S" : "N";
        double abs2 = Math.abs(d2);
        f a = f.a(f.z, this.j, str);
        f.a(f.z);
        f.a(a);
        a = f.a(f.ie_, this.j, str2);
        f.a(f.ie_);
        f.a(a);
        abs = (abs % 1.0d) * 60.0d;
        double d3 = (double) ((long) abs);
        abs = (abs % 1.0d) * 60.0d;
        a = f.a(f.C, this.j, new Double[]{new Double((double) ((long) abs)), new Double(d3), new Double(abs)});
        f.a(f.C);
        f.a(a);
        abs = (abs2 % 1.0d) * 60.0d;
        abs2 = (double) ((long) abs);
        abs = (abs % 1.0d) * 60.0d;
        a = f.a(f.y, this.j, new Double[]{new Double((double) ((long) abs2)), new Double(abs2), new Double(abs)});
        f.a(f.y);
        f.a(a);
    }

    public void a(e eVar) {
        b(eVar.k);
    }

    public void b(int i) {
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            ((e) this.k.get(i2)).a(i);
        }
    }

    public f b(e eVar) {
        return c(eVar.k);
    }

    public f c(int i) {
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            f b = ((e) this.k.get(i2)).b(i);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    public e i() throws dji.thirdparty.g.f {
        e eVar = new e(0);
        a(eVar);
        return eVar;
    }

    public e j() throws dji.thirdparty.g.f {
        e eVar = new e(-2);
        a(eVar);
        return eVar;
    }

    public e k() throws dji.thirdparty.g.f {
        e eVar = new e(-3);
        a(eVar);
        return eVar;
    }

    public e l() throws dji.thirdparty.g.f {
        e();
        e eVar = new e(-4);
        a(eVar);
        return eVar;
    }

    public String toString() {
        return a(null);
    }

    public String a(String str) {
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("TiffOutputSet {");
        stringBuffer.append(l);
        stringBuffer.append(str);
        stringBuffer.append("byteOrder: " + this.j);
        stringBuffer.append(l);
        for (int i = 0; i < this.k.size(); i++) {
            e eVar = (e) this.k.get(i);
            stringBuffer.append(str);
            stringBuffer.append("\tdirectory " + i + ": " + eVar.c() + " (" + eVar.j + ")");
            stringBuffer.append(l);
            ArrayList a = eVar.a();
            for (int i2 = 0; i2 < a.size(); i2++) {
                f fVar = (f) a.get(i2);
                stringBuffer.append(str);
                stringBuffer.append("\t\tfield " + i + ": " + fVar.k);
                stringBuffer.append(l);
            }
        }
        stringBuffer.append(str);
        stringBuffer.append(i.d);
        stringBuffer.append(l);
        return stringBuffer.toString();
    }

    public void m() {
        c.a(toString());
    }
}
