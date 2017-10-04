package dji.thirdparty.g.b.a;

import dji.thirdparty.g.a.a.a;
import dji.thirdparty.g.b;
import dji.thirdparty.g.b.a.b.h;
import dji.thirdparty.g.b.b.a.i;
import dji.thirdparty.g.b.b.g;
import dji.thirdparty.g.d;
import dji.thirdparty.g.e;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c extends d implements a, i {
    public static final String[] K = new String[]{".jpg", ".jpeg"};
    public static final boolean L = true;
    private static final String aV = ".jpg";

    public c() {
        a(77);
    }

    protected b[] e() {
        return new b[]{b.i};
    }

    public String b() {
        return "Jpeg-Custom";
    }

    public String c() {
        return ".jpg";
    }

    protected String[] d() {
        return K;
    }

    private boolean a(int i, int[] iArr) {
        if (iArr == null) {
            return true;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public ArrayList a(a aVar, final int[] iArr, final boolean z, boolean z2) throws e, IOException {
        final ArrayList arrayList = new ArrayList();
        new d().a(aVar, new d.a(this) {
            final /* synthetic */ c d;

            public boolean a() {
                return false;
            }

            public void a(int i, byte[] bArr, byte[] bArr2) {
            }

            public boolean a(int i, byte[] bArr, InputStream inputStream) {
                return false;
            }

            public boolean a(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws e, IOException {
                if (i == 65497) {
                    return false;
                }
                if (!this.d.a(i, iArr)) {
                    return true;
                }
                if (i != a.m) {
                    if (i == a.l) {
                        arrayList.add(new dji.thirdparty.g.b.a.b.b(i, bArr3));
                    } else if (i == 65504) {
                        arrayList.add(new dji.thirdparty.g.b.a.b.d(i, bArr3));
                    } else if (i >= a.q && i <= a.F) {
                        arrayList.add(new dji.thirdparty.g.b.a.b.e(i, bArr3));
                    } else if (i >= a.k && i <= a.o) {
                        arrayList.add(new h(i, bArr3));
                    }
                }
                if (z) {
                    return false;
                }
                return true;
            }
        });
        return arrayList;
    }

    private byte[] a(ArrayList arrayList) throws e, IOException {
        try {
            return a(arrayList, false);
        } catch (e e) {
            return a(arrayList, true);
        }
    }

    private byte[] a(ArrayList arrayList, boolean z) throws e, IOException {
        int i = 1;
        if (arrayList.size() < 1) {
            throw new e("No App2 Segments Found.");
        }
        int i2 = ((dji.thirdparty.g.b.a.b.b) arrayList.get(0)).m;
        if (arrayList.size() != i2) {
            throw new e("App2 Segments Missing.  Found: " + arrayList.size() + ", Expected: " + i2 + ".");
        }
        Collections.sort(arrayList);
        if (z) {
            i = 0;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < arrayList.size()) {
            dji.thirdparty.g.b.a.b.b bVar = (dji.thirdparty.g.b.a.b.b) arrayList.get(i3);
            if (i3 + i != bVar.l) {
                b(arrayList);
                throw new e("Incoherent App2 Segment Ordering.  i: " + i3 + ", segment[" + i3 + "].cur_marker: " + bVar.l + ".");
            } else if (i2 != bVar.m) {
                b(arrayList);
                throw new e("Inconsistent App2 Segment Count info.  markerCount: " + i2 + ", segment[" + i3 + "].num_markers: " + bVar.m + ".");
            } else {
                i4 += bVar.k.length;
                i3++;
            }
        }
        Object obj = new byte[i4];
        i3 = 0;
        for (i = 0; i < arrayList.size(); i++) {
            bVar = (dji.thirdparty.g.b.a.b.b) arrayList.get(i);
            System.arraycopy(bVar.k, 0, obj, i3, bVar.k.length);
            i3 += bVar.k.length;
        }
        return obj;
    }

    private void b(ArrayList arrayList) {
        dji.thirdparty.g.c.c.a();
        dji.thirdparty.g.c.c.b("dumpSegments", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            dji.thirdparty.g.b.a.b.b bVar = (dji.thirdparty.g.b.a.b.b) arrayList.get(i);
            dji.thirdparty.g.c.c.a(i + ": " + bVar.l + " / " + bVar.m);
        }
        dji.thirdparty.g.c.c.a();
    }

    public ArrayList a(a aVar, int[] iArr, boolean z) throws e, IOException {
        return a(aVar, iArr, z, false);
    }

    public byte[] d(a aVar, Map map) throws e, IOException {
        ArrayList arrayList;
        ArrayList a = a(aVar, new int[]{a.l}, false);
        if (a != null) {
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < a.size(); i++) {
                dji.thirdparty.g.b.a.b.b bVar = (dji.thirdparty.g.b.a.b.b) a.get(i);
                if (bVar.k != null) {
                    arrayList2.add(bVar);
                }
            }
            arrayList = arrayList2;
        } else {
            arrayList = a;
        }
        if (arrayList == null || arrayList.size() < 1) {
            return null;
        }
        byte[] a2 = a(arrayList);
        if (this.fn_) {
            System.out.println("bytes: " + (a2 == null ? null : "" + a2.length));
        }
        if (this.fn_) {
            System.out.println("");
        }
        return a2;
    }

    public dji.thirdparty.g.a.i a(a aVar, Map map) throws e, IOException {
        g e = e(aVar, map);
        if (e == null && null == null) {
            return null;
        }
        return new b(null, e);
    }

    public static boolean a(dji.thirdparty.g.b.a.b.c cVar) {
        return dji.thirdparty.g.a.c.d(cVar.n, fr_);
    }

    private ArrayList c(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            dji.thirdparty.g.b.a.b.c cVar = (dji.thirdparty.g.b.a.b.c) arrayList.get(i);
            if (a(cVar)) {
                arrayList2.add(cVar);
            }
        }
        return arrayList2;
    }

    private ArrayList a(ArrayList arrayList, List list) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            dji.thirdparty.g.b.a.b.g gVar = (dji.thirdparty.g.b.a.b.g) arrayList.get(i);
            if (list.contains(new Integer(gVar.gt_))) {
                arrayList2.add(gVar);
            }
        }
        return arrayList2;
    }

    public g e(a aVar, Map map) throws e, IOException {
        byte[] e = e(aVar);
        if (e == null) {
            return null;
        }
        if (map == null) {
            map = new HashMap();
        }
        if (!map.containsKey(dji.thirdparty.g.h.fk_)) {
            map.put(dji.thirdparty.g.h.fk_, Boolean.TRUE);
        }
        return (g) new dji.thirdparty.g.b.b.h().a(e, map);
    }

    public byte[] e(a aVar) throws e, IOException {
        ArrayList a = a(aVar, new int[]{a.k}, false);
        if (a == null || a.size() < 1) {
            return null;
        }
        a = c(a);
        if (this.fn_) {
            System.out.println("exif_segments.size: " + a.size());
        }
        if (a.size() < 1) {
            return null;
        }
        if (a.size() > 1) {
            throw new e("Sanselan currently can't parse EXIF metadata split across multiple APP1 segments.  Please send this image to the Sanselan project.");
        }
        return d("trimmed exif bytes", ((dji.thirdparty.g.b.a.b.c) a.get(0)).n, 6);
    }

    public boolean f(a aVar) throws e, IOException {
        final boolean[] zArr = new boolean[]{false};
        new d().a(aVar, new d.a(this) {
            final /* synthetic */ c b;

            public boolean a() {
                return false;
            }

            public void a(int i, byte[] bArr, byte[] bArr2) {
            }

            public boolean a(int i, byte[] bArr, InputStream inputStream) {
                return false;
            }

            public boolean a(int i, byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) throws e, IOException {
                if (i == 65497) {
                    return false;
                }
                if (i != a.k || !dji.thirdparty.g.a.c.d(bArr3, a.fr_)) {
                    return true;
                }
                zArr[0] = true;
                return false;
            }
        });
        return zArr[0];
    }

    public boolean g(a aVar) throws e, IOException {
        return new boolean[]{false}[0];
    }

    public boolean h(a aVar) throws e, IOException {
        return new boolean[]{false}[0];
    }

    public String c(a aVar, Map map) throws e, IOException {
        return null;
    }

    public Object f(a aVar, Map map) throws e, IOException {
        return null;
    }

    public int[] g(a aVar, Map map) throws e, IOException {
        ArrayList a = a(aVar, new int[]{a.q, a.r, a.s, a.t, a.v, a.w, a.x, a.z, a.A, a.B, a.D, a.E, a.F}, true);
        if (a == null || a.size() < 1) {
            throw new e("No JFIF Data Found.");
        } else if (a.size() > 1) {
            throw new e("Redundant JFIF Data Found.");
        } else {
            dji.thirdparty.g.b.a.b.e eVar = (dji.thirdparty.g.b.a.b.e) a.get(0);
            return new int[]{eVar.k, eVar.l};
        }
    }

    public byte[] e(byte[] bArr, byte[] bArr2) {
        return null;
    }

    public boolean a(File file, File file2, byte[] bArr) {
        return false;
    }

    public dji.thirdparty.g.c b(a aVar, Map map) throws e, IOException {
        ArrayList a = a(aVar, new int[]{a.q, a.r, a.s, a.t, a.v, a.w, a.x, a.z, a.A, a.B, a.D, a.E, a.F}, false);
        if (a == null) {
            throw new e("No SOFN Data Found.");
        }
        ArrayList a2 = a(aVar, new int[]{65504}, true);
        dji.thirdparty.g.b.a.b.e eVar = (dji.thirdparty.g.b.a.b.e) a.get(0);
        if (eVar == null) {
            throw new e("No SOFN Data Found.");
        }
        int i;
        String str;
        double d;
        double d2;
        int i2 = eVar.k;
        int i3 = eVar.l;
        dji.thirdparty.g.b.a.b.d dVar = null;
        if (a2 != null && a2.size() > 0) {
            dVar = (dji.thirdparty.g.b.a.b.d) a2.get(0);
        }
        double d3 = com.here.android.mpa.mapping.Map.MOVE_PRESERVE_ZOOM_LEVEL;
        double d4;
        if (dVar != null) {
            double d5 = (double) dVar.N;
            d4 = (double) dVar.O;
            i = dVar.M;
            str = "Jpeg/JFIF v." + dVar.K + "." + dVar.L;
            switch (i) {
                case 0:
                    d = com.here.android.mpa.mapping.Map.MOVE_PRESERVE_ZOOM_LEVEL;
                    break;
                case 1:
                    d = 1.0d;
                    break;
                case 2:
                    d = 2.54d;
                    break;
                default:
                    d = com.here.android.mpa.mapping.Map.MOVE_PRESERVE_ZOOM_LEVEL;
                    break;
            }
            d3 = d4;
            d2 = d5;
        } else {
            b bVar = (b) a(aVar, map);
            if (bVar != null) {
                dji.thirdparty.g.b.b.e a3 = bVar.a(gF_);
                if (a3 != null) {
                    d4 = ((Number) a3.i()).doubleValue();
                } else {
                    d4 = -1.0d;
                }
                dji.thirdparty.g.b.b.e a4 = bVar.a(gG_);
                d = a4 != null ? ((Number) a4.i()).doubleValue() : com.here.android.mpa.mapping.Map.MOVE_PRESERVE_ZOOM_LEVEL;
                dji.thirdparty.g.b.b.e a5 = bVar.a(gR_);
                if (a5 != null) {
                    switch (((Number) a5.i()).intValue()) {
                        case 2:
                            d3 = 1.0d;
                            break;
                        case 3:
                            d3 = 2.54d;
                            break;
                    }
                }
            }
            d4 = -1.0d;
            d = com.here.android.mpa.mapping.Map.MOVE_PRESERVE_ZOOM_LEVEL;
            str = "Jpeg/DCM";
            d2 = d4;
            double d6 = d;
            d = d3;
            d3 = d6;
        }
        int i4 = -1;
        float f = -1.0f;
        int i5 = -1;
        float f2 = -1.0f;
        if (d > 0.0d) {
            i5 = (int) Math.round(d2 / d);
            f2 = (float) (((double) i2) / (d2 * d));
            i4 = (int) Math.round(d3 * d);
            f = (float) (((double) i3) / (d * d3));
        }
        ArrayList arrayList = new ArrayList();
        int i6 = eVar.m;
        i = eVar.n * i6;
        b bVar2 = b.i;
        String str2 = "JPEG (Joint Photographic Experts Group) Format";
        String str3 = "image/jpeg";
        boolean z = eVar.gt_ == 65474;
        if (i6 == 1) {
            i6 = 0;
        } else if (i6 == 3) {
            i6 = 2;
        } else if (i6 == 4) {
            i6 = 3;
        } else {
            i6 = -2;
        }
        return new dji.thirdparty.g.c(str, i, arrayList, bVar2, str2, i3, str3, 1, i4, f, i5, f2, i2, z, false, false, i6, dji.thirdparty.g.c.k);
    }

    public boolean a(PrintWriter printWriter, a aVar) throws e, IOException {
        printWriter.println("tiff.dumpImageFile");
        dji.thirdparty.g.c b = b(aVar);
        if (b == null) {
            return false;
        }
        b.a(printWriter, "");
        printWriter.println("");
        ArrayList a = a(aVar, null, false);
        if (a == null) {
            throw new e("No Segments Found.");
        }
        for (int i = 0; i < a.size(); i++) {
            dji.thirdparty.g.b.a.b.g gVar = (dji.thirdparty.g.b.a.b.g) a.get(i);
            printWriter.println(i + ": marker: " + Integer.toHexString(gVar.gt_) + ", " + gVar.a() + " (length: " + NumberFormat.getIntegerInstance().format((long) gVar.gu_) + ")");
            gVar.a(printWriter);
        }
        printWriter.println("");
        return true;
    }
}
