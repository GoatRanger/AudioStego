package dji.thirdparty.g.b.b;

import dji.thirdparty.g.a.a.a;
import dji.thirdparty.g.a.i;
import dji.thirdparty.g.b;
import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.c;
import dji.thirdparty.g.d;
import dji.thirdparty.g.e;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class h extends d implements f {
    private static final String k = ".tif";
    private static final String[] l = new String[]{k, ".tiff"};

    public String b() {
        return "Tiff-Custom";
    }

    public String c() {
        return k;
    }

    protected String[] d() {
        return l;
    }

    protected b[] e() {
        return new b[]{b.h};
    }

    public byte[] d(a aVar, Map map) throws e, IOException {
        e a = ((c) new i(d.a(map)).a(aVar, map, false, dji.thirdparty.g.a.a()).b.get(0)).a(ei);
        if (a == null) {
            return null;
        }
        return a.r;
    }

    public int[] e(a aVar, Map map) throws e, IOException {
        c cVar = (c) new i(d.a(map)).a(aVar, map, false, dji.thirdparty.g.a.a()).b.get(0);
        int n = cVar.a(fA_).n();
        int n2 = cVar.a(fB_).n();
        return new int[]{n, n2};
    }

    public byte[] e(byte[] bArr, byte[] bArr2) {
        return null;
    }

    public boolean a(File file, File file2, byte[] bArr) {
        return false;
    }

    public i a(a aVar, Map map) throws e, IOException {
        b a = new i(d.a(map)).a(aVar, map, dji.thirdparty.g.a.a());
        ArrayList arrayList = a.b;
        i gVar = new g(a);
        for (int i = 0; i < arrayList.size(); i++) {
            c cVar = (c) arrayList.get(i);
            i.a aVar2 = new g.a(cVar);
            ArrayList b = cVar.b();
            for (int i2 = 0; i2 < b.size(); i2++) {
                aVar2.a((e) b.get(i2));
            }
            gVar.a(aVar2);
        }
        return gVar;
    }

    public c b(a aVar, Map map) throws e, IOException {
        b a = new i(d.a(map)).a(aVar, false, dji.thirdparty.g.a.a());
        c cVar = (c) a.b.get(0);
        e a2 = cVar.a(fA_, true);
        e a3 = cVar.a(fB_, true);
        if (a2 == null || a3 == null) {
            throw new e("TIFF image missing size info.");
        }
        String str;
        int n = a3.n();
        int n2 = a2.n();
        a3 = cVar.a(gR_);
        int i = 2;
        if (!(a3 == null || a3.i() == null)) {
            i = a3.n();
        }
        double d = com.here.android.mpa.mapping.Map.MOVE_PRESERVE_ZOOM_LEVEL;
        switch (i) {
            case 2:
                d = 1.0d;
                break;
            case 3:
                d = 0.0254d;
                break;
        }
        a2 = cVar.a(gF_);
        e a4 = cVar.a(gG_);
        int i2 = -1;
        float f = -1.0f;
        int i3 = -1;
        float f2 = -1.0f;
        if (d > 0.0d) {
            if (!(a2 == null || a2.i() == null)) {
                double o = a2.o();
                i2 = (int) (o / d);
                f = (float) (((double) n2) / (o * d));
            }
            if (!(a4 == null || a4.i() == null)) {
                double o2 = a4.o();
                i3 = (int) (o2 / d);
                f2 = (float) (((double) n) / (d * o2));
            }
        }
        a2 = cVar.a(fC_);
        int i4 = -1;
        if (!(a2 == null || a2.i() == null)) {
            i4 = a2.m();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = cVar.k;
        for (int i5 = 0; i5 < arrayList2.size(); i5++) {
            arrayList.add(((e) arrayList2.get(i5)).toString());
        }
        b bVar = b.h;
        String str2 = "TIFF Tag-based Image File Format";
        String str3 = "image/tiff";
        int size = a.b.size();
        String str4 = "Tiff v." + a.a.e;
        boolean z = false;
        if (cVar.a(hb_) != null) {
            z = true;
        }
        switch (cVar.a(fD_).n()) {
            case 1:
                str = "None";
                break;
            case 2:
                str = c.q;
                break;
            case 3:
                str = c.o;
                break;
            case 4:
                str = c.p;
                break;
            case 5:
                str = c.i;
                break;
            case 6:
                str = c.k;
                break;
            case 32771:
                str = "None";
                break;
            case 32773:
                str = c.j;
                break;
            default:
                str = "Unknown";
                break;
        }
        return new c(str4, i4, arrayList, bVar, str2, n, str3, size, i3, f2, i2, f, n2, false, false, z, 2, str);
    }

    public String c(a aVar, Map map) throws e, IOException {
        e a = ((c) new i(d.a(map)).a(aVar, false, dji.thirdparty.g.a.a()).b.get(0)).a(hF_, false);
        if (a == null) {
            return null;
        }
        try {
            return new String(a.p(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new e("Invalid JPEG XMP Segment.");
        }
    }

    public boolean a(PrintWriter printWriter, a aVar) throws e, IOException {
        String str = null;
        printWriter.println("tiff.dumpImageFile");
        c b = b(aVar);
        if (b == null) {
            return str;
        }
        b.a(printWriter, "");
        printWriter.println("");
        ArrayList arrayList = new i(true).a(aVar, null, dji.thirdparty.g.a.a()).b;
        if (arrayList == null) {
            printWriter.println("");
            return false;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList arrayList2 = ((c) arrayList.get(i)).k;
            if (arrayList2 == null) {
                printWriter.println("");
                return false;
            }
            int i2 = 0;
            while (i2 < arrayList2.size()) {
                try {
                    ((e) arrayList2.get(i2)).a(printWriter, i + "");
                    i2++;
                } finally {
                    str = "";
                    printWriter.println(str);
                }
            }
        }
        printWriter.println("");
        printWriter.println("");
        return true;
    }

    public dji.thirdparty.g.a c(a aVar) throws e, IOException {
        dji.thirdparty.g.a a = dji.thirdparty.g.a.a();
        new i(d.a(null)).a(aVar, null, a);
        return a;
    }

    public List f(a aVar, Map map) throws e, IOException {
        b a = new i(d.a(map)).a(aVar, true, dji.thirdparty.g.a.a());
        List arrayList = new ArrayList();
        for (int i = 0; i < a.b.size(); i++) {
            List f = ((c) a.b.get(i)).f();
            for (int i2 = 0; i2 < f.size(); i2++) {
                c.a aVar2 = (c.a) f.get(i2);
                arrayList.add(aVar.c(aVar2.gv_, aVar2.gw_));
            }
        }
        return arrayList;
    }
}
