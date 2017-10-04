package dji.thirdparty.g.b.a;

import dji.thirdparty.g.a.i;
import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.b.b.g;
import dji.thirdparty.g.b.b.g.c;
import java.io.IOException;
import java.util.ArrayList;

public class b implements i {
    private static final String b = System.getProperty("line.separator");
    private final g a;

    public b(Object obj, g gVar) {
        this.a = gVar;
    }

    public g b() {
        return this.a;
    }

    public e a(dji.thirdparty.g.b.b.a.e eVar) {
        ArrayList a = a();
        for (int i = 0; i < a.size(); i++) {
            Object obj = a.get(i);
            if (obj instanceof c) {
                e c = ((c) obj).c();
                if (c.l == eVar.k) {
                    return c;
                }
            }
        }
        return null;
    }

    public Object c() throws dji.thirdparty.g.e, IOException {
        return null;
    }

    public ArrayList a() {
        ArrayList arrayList = new ArrayList();
        if (this.a != null) {
            arrayList.addAll(this.a.a());
        }
        return arrayList;
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
        if (this.a == null) {
            stringBuffer.append("No Exif metadata.");
        } else {
            stringBuffer.append("Exif metadata:");
            stringBuffer.append(b);
            stringBuffer.append(this.a.a("\t"));
        }
        stringBuffer.append(b);
        stringBuffer.append(str);
        stringBuffer.append("No Photoshop (IPTC) metadata.");
        return stringBuffer.toString();
    }

    public void d() {
        dji.thirdparty.g.c.c.a(toString());
    }
}
