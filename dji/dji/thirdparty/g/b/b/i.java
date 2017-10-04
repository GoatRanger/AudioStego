package dji.thirdparty.g.b.b;

import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.e;
import dji.thirdparty.g.h;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class i extends dji.thirdparty.g.a.c implements f {
    private final boolean k;

    public interface d {
        boolean a();

        boolean a(c cVar);

        boolean a(e eVar);

        boolean a(f fVar);

        boolean b();
    }

    private static class a implements d {
        private f a;
        private ArrayList b;
        private ArrayList c;
        private final boolean d;

        public a() {
            this(null);
        }

        public a(Map map) {
            this.a = null;
            this.b = new ArrayList();
            this.c = new ArrayList();
            boolean z = true;
            if (map != null && map.containsKey(h.fk_)) {
                z = Boolean.TRUE.equals(map.get(h.fk_));
            }
            this.d = z;
        }

        public boolean a(f fVar) {
            this.a = fVar;
            return true;
        }

        public boolean a(c cVar) {
            this.b.add(cVar);
            return true;
        }

        public boolean a(e eVar) {
            this.c.add(eVar);
            return true;
        }

        public boolean a() {
            return this.d;
        }

        public boolean b() {
            return true;
        }

        public b c() {
            return new b(this.a, this.b);
        }
    }

    private static class b extends a {
        private final boolean a;

        public b(boolean z) {
            this.a = z;
        }

        public boolean a(c cVar) {
            super.a(cVar);
            return false;
        }

        public boolean a() {
            return this.a;
        }
    }

    private static class c extends a {
        private final boolean a;

        public c(boolean z) {
            this.a = z;
        }

        public boolean a(c cVar) {
            super.a(cVar);
            return false;
        }

        public boolean a() {
            return this.a;
        }
    }

    public i(boolean z) {
        this.k = z;
    }

    private f a(dji.thirdparty.g.a.a.a aVar, dji.thirdparty.g.a aVar2) throws e, IOException {
        InputStream inputStream = null;
        try {
            inputStream = aVar.a();
            f a = a(inputStream, aVar2);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e) {
                    dji.thirdparty.g.c.c.a(e);
                }
            }
            return a;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e2) {
                    dji.thirdparty.g.c.c.a(e2);
                }
            }
        }
    }

    private f a(InputStream inputStream, dji.thirdparty.g.a aVar) throws e, IOException {
        byte a = a("BYTE_ORDER_1", inputStream, "Not a Valid TIFF File");
        c((int) a, (int) a("BYTE_ORDER_2", inputStream, "Not a Valid TIFF File"));
        int d = d("tiffVersion", inputStream, "Not a Valid TIFF File");
        if (d != 42) {
            throw new e("Unknown Tiff Version: " + d);
        }
        int b = b("offsetToFirstIFD", inputStream, "Not a Valid TIFF File");
        a(inputStream, b - 8, "Not a Valid TIFF File: couldn't find IFDs");
        if (this.fn_) {
            System.out.println("");
        }
        return new f(a, d, b);
    }

    private void a(dji.thirdparty.g.a.a.a aVar, dji.thirdparty.g.a aVar2, d dVar) throws e, IOException {
        f a = a(aVar, aVar2);
        if (dVar.a(a)) {
            a(aVar, a.f, 0, aVar2, dVar, new ArrayList());
        }
    }

    private boolean a(dji.thirdparty.g.a.a.a aVar, int i, int i2, dji.thirdparty.g.a aVar2, d dVar, List list) throws e, IOException {
        return a(aVar, i, i2, aVar2, dVar, false, list);
    }

    private boolean a(dji.thirdparty.g.a.a.a aVar, int i, int i2, dji.thirdparty.g.a aVar2, d dVar, boolean z, List list) throws e, IOException {
        Throwable th;
        Integer num = new Integer(i);
        if (list.contains(num)) {
            return false;
        }
        list.add(num);
        InputStream inputStream = null;
        try {
            InputStream a = aVar.a();
            if (i > 0) {
                a.skip((long) i);
            }
            ArrayList arrayList = new ArrayList();
            if (((long) i) < aVar.c()) {
                try {
                    int d;
                    int d2 = d("DirectoryEntryCount", a, "Not a Valid TIFF File");
                    for (int i3 = 0; i3 < d2; i3++) {
                        int d3 = d(e.t, a, "Not a Valid TIFF File");
                        d = d("Type", a, "Not a Valid TIFF File");
                        int b = b("Length", a, "Not a Valid TIFF File");
                        byte[] a2 = a("ValueOffset", 4, a, "Not a Valid TIFF File");
                        int b2 = b("ValueOffset", a2);
                        if (d3 != 0) {
                            e eVar = new e(d3, i2, d, b, b2, a2, g());
                            eVar.a(i3);
                            eVar.a(aVar);
                            arrayList.add(eVar);
                            if (!dVar.a(eVar)) {
                                if (a == null) {
                                    return true;
                                }
                                try {
                                    a.close();
                                    return true;
                                } catch (Throwable e) {
                                    dji.thirdparty.g.c.c.a(e);
                                    return true;
                                }
                            }
                        }
                    }
                    c cVar = new c(i2, arrayList, i, b("nextDirectoryOffset", a, "Not a Valid TIFF File"));
                    if (dVar.a() && cVar.d()) {
                        cVar.a(a(aVar, cVar));
                    }
                    if (dVar.a(cVar)) {
                        if (dVar.b()) {
                            Collection arrayList2 = new ArrayList();
                            for (d2 = 0; d2 < arrayList.size(); d2++) {
                                e eVar2 = (e) arrayList.get(d2);
                                if (eVar2.l == f.eh.k || eVar2.l == f.ew.k || eVar2.l == f.gL.k) {
                                    int intValue = ((Number) eVar2.i()).intValue();
                                    if (eVar2.l == f.eh.k) {
                                        d = -2;
                                    } else if (eVar2.l == f.ew.k) {
                                        d = -3;
                                    } else if (eVar2.l == f.gL.k) {
                                        d = -4;
                                    } else {
                                        throw new e("Unknown subdirectory type.");
                                    }
                                    if (!a(aVar, intValue, d, aVar2, dVar, true, list)) {
                                        arrayList2.add(eVar2);
                                    }
                                }
                            }
                            arrayList.removeAll(arrayList2);
                        }
                        if (!z && cVar.l > 0) {
                            a(aVar, cVar.l, i2 + 1, aVar2, dVar, list);
                        }
                        if (a == null) {
                            return true;
                        }
                        try {
                            a.close();
                            return true;
                        } catch (Throwable e2) {
                            dji.thirdparty.g.c.c.a(e2);
                            return true;
                        }
                    } else if (a == null) {
                        return true;
                    } else {
                        try {
                            a.close();
                            return true;
                        } catch (Throwable e22) {
                            dji.thirdparty.g.c.c.a(e22);
                            return true;
                        }
                    }
                } catch (IOException e3) {
                    if (this.k) {
                        throw e3;
                    } else if (a == null) {
                        return true;
                    } else {
                        try {
                            a.close();
                            return true;
                        } catch (Throwable e222) {
                            dji.thirdparty.g.c.c.a(e222);
                            return true;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = a;
                }
            } else if (a == null) {
                return true;
            } else {
                try {
                    a.close();
                    return true;
                } catch (Throwable e2222) {
                    dji.thirdparty.g.c.c.a(e2222);
                    return true;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e22222) {
                    dji.thirdparty.g.c.c.a(e22222);
                }
            }
            throw th;
        }
    }

    public b a(dji.thirdparty.g.a.a.a aVar, Map map, boolean z, dji.thirdparty.g.a aVar2) throws e, IOException {
        d cVar = new c(z);
        a(aVar, map, aVar2, cVar);
        b c = cVar.c();
        if (c.b.size() >= 1) {
            return c;
        }
        throw new e("Image did not contain any directories.");
    }

    public b a(dji.thirdparty.g.a.a.a aVar, boolean z, dji.thirdparty.g.a aVar2) throws e, IOException {
        d cVar = new c(z);
        a(aVar, aVar2, cVar);
        b c = cVar.c();
        if (c.b.size() >= 1) {
            return c;
        }
        throw new e("Image did not contain any directories.");
    }

    public b a(dji.thirdparty.g.a.a.a aVar, Map map, dji.thirdparty.g.a aVar2) throws e, IOException {
        d aVar3 = new a(map);
        a(aVar, map, aVar2, aVar3);
        return aVar3.c();
    }

    public void a(dji.thirdparty.g.a.a.a aVar, Map map, dji.thirdparty.g.a aVar2, d dVar) throws e, IOException {
        a(aVar, aVar2, dVar);
    }

    private a a(dji.thirdparty.g.a.a.a aVar, c cVar) throws e, IOException {
        dji.thirdparty.g.b.b.c.a h = cVar.h();
        int i = h.gv_;
        int i2 = h.gw_;
        if (((long) (i + i2)) == aVar.c() + 1) {
            i2--;
        }
        return new a(i, i2, aVar.c(i, i2));
    }
}
