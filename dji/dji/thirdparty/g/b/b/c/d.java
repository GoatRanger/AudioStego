package dji.thirdparty.g.b.b.c;

import dji.thirdparty.g.a.e;
import dji.thirdparty.g.f;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class d extends b {
    public d(int i) {
        super(i);
    }

    public void a(OutputStream outputStream, h hVar) throws IOException, f {
        i a = a(hVar);
        List a2 = hVar.a(a);
        a(a2);
        a.a(this.j);
        a(new e(outputStream, this.j), a2);
    }

    private void a(List list) throws IOException, f {
        int i = 8;
        for (int i2 = 0; i2 < list.size(); i2++) {
            g gVar = (g) list.get(i2);
            gVar.c(i);
            int e = gVar.e();
            i = (i + e) + b.a(e);
        }
    }

    private void a(e eVar, List list) throws IOException, f {
        a(eVar);
        for (int i = 0; i < list.size(); i++) {
            g gVar = (g) list.get(i);
            gVar.a(eVar);
            int a = b.a(gVar.e());
            for (int i2 = 0; i2 < a; i2++) {
                eVar.write(0);
            }
        }
    }
}
