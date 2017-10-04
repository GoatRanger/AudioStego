package dji.thirdparty.g.b.b;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.thirdparty.g.b.b.a.f;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class e implements f {
    private static final Map mE = a(aB, false, "GPS");
    private static final Map mF = a(aU, false, "TIFF");
    private static final Map mG = a(lM, true, "EXIF");
    private static final Map mH = a(i, true, "All");
    public static final String t = "Tag";
    public final dji.thirdparty.g.b.b.a.e j;
    public final dji.thirdparty.g.b.b.b.a k;
    public final int l;
    public final int m;
    private int mD = -1;
    public final int n;
    public final int o;
    public final int p;
    public final byte[] q;
    public byte[] r = null;
    public final int s;

    public final class a extends d {
        final /* synthetic */ e a;

        public a(e eVar, int i, int i2) {
            this.a = eVar;
            super(i, i2);
        }

        public String a(boolean z) {
            if (z) {
                return null;
            }
            return "OversizeValueElement, tag: " + this.a.j.j + ", fieldType: " + this.a.k.m;
        }
    }

    public e(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6) {
        this.l = i;
        this.m = i2;
        this.n = i3;
        this.o = i4;
        this.p = i5;
        this.q = bArr;
        this.s = i6;
        this.k = b(i3);
        this.j = a(i2, i);
    }

    public boolean a() {
        return this.k.a(this);
    }

    public int b() throws dji.thirdparty.g.e {
        return this.k.b(this);
    }

    public d c() {
        if (this.k.a(this)) {
            return null;
        }
        return new a(this, this.p, this.r.length);
    }

    public void a(byte[] bArr) {
        this.r = bArr;
    }

    private static dji.thirdparty.g.b.b.b.a b(int i) {
        for (dji.thirdparty.g.b.b.b.a aVar : gj_) {
            if (aVar.k == i) {
                return aVar;
            }
        }
        return mo;
    }

    private static dji.thirdparty.g.b.b.a.e a(int i, int i2, List list) {
        int i3 = 0;
        if (list.size() < 1) {
            return null;
        }
        int i4;
        for (i4 = 0; i4 < list.size(); i4++) {
            dji.thirdparty.g.b.b.a.e eVar = (dji.thirdparty.g.b.b.a.e) list.get(i4);
            if (eVar.n != mB) {
                if (i == -2 && eVar.n == mz) {
                    return eVar;
                }
                if (i == -4 && eVar.n == mx) {
                    return eVar;
                }
                if (i == -3 && eVar.n == mA) {
                    return eVar;
                }
                if (i == -5 && eVar.n == my) {
                    return eVar;
                }
                if (i == 0 && eVar.n == fU_) {
                    return eVar;
                }
                if (i == 1 && eVar.n == fW_) {
                    return eVar;
                }
                if (i == 2 && eVar.n == fX_) {
                    return eVar;
                }
                if (i == 3 && eVar.n == fY_) {
                    return eVar;
                }
            }
        }
        for (i4 = 0; i4 < list.size(); i4++) {
            eVar = (dji.thirdparty.g.b.b.a.e) list.get(i4);
            if (eVar.n != mB) {
                if (i >= 0 && eVar.n.a()) {
                    return eVar;
                }
                if (i < 0 && !eVar.n.a()) {
                    return eVar;
                }
            }
        }
        while (i3 < list.size()) {
            eVar = (dji.thirdparty.g.b.b.a.e) list.get(i3);
            if (eVar.n == mB) {
                return eVar;
            }
            i3++;
        }
        return hG_;
    }

    private static dji.thirdparty.g.b.b.a.e a(int i, int i2) {
        List list = (List) mG.get(new Integer(i2));
        if (list == null) {
            return hG_;
        }
        return a(i, i2, list);
    }

    private int r() {
        return this.k.l * this.o;
    }

    public void a(dji.thirdparty.g.a.a.a aVar) throws dji.thirdparty.g.e, IOException {
        if (!this.k.a(this)) {
            a(aVar.c(this.p, r()));
        }
    }

    public String d() {
        try {
            return a(i());
        } catch (dji.thirdparty.g.e e) {
            return "Invalid value: " + e.getMessage();
        }
    }

    private String a(Object obj) {
        int i = 0;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return obj.toString();
        }
        if (obj instanceof String) {
            return "'" + obj.toString().trim() + "'";
        }
        if (obj instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format((Date) obj);
        }
        StringBuffer stringBuffer;
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            stringBuffer = new StringBuffer();
            while (i < objArr.length) {
                Object obj2 = objArr[i];
                if (i > 50) {
                    stringBuffer.append("... (" + objArr.length + ")");
                    break;
                }
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("" + obj2);
                i++;
            }
            return stringBuffer.toString();
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            stringBuffer = new StringBuffer();
            while (i < iArr.length) {
                int i2 = iArr[i];
                if (i > 50) {
                    stringBuffer.append("... (" + iArr.length + ")");
                    break;
                }
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("" + i2);
                i++;
            }
            return stringBuffer.toString();
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            stringBuffer = new StringBuffer();
            while (i < jArr.length) {
                long j = jArr[i];
                if (i > 50) {
                    stringBuffer.append("... (" + jArr.length + ")");
                    break;
                }
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("" + j);
                i++;
            }
            return stringBuffer.toString();
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            stringBuffer = new StringBuffer();
            while (i < dArr.length) {
                double d = dArr[i];
                if (i > 50) {
                    stringBuffer.append("... (" + dArr.length + ")");
                    break;
                }
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("" + d);
                i++;
            }
            return stringBuffer.toString();
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            stringBuffer = new StringBuffer();
            while (i < bArr.length) {
                byte b = bArr[i];
                if (i > 50) {
                    stringBuffer.append("... (" + bArr.length + ")");
                    break;
                }
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("" + b);
                i++;
            }
            return stringBuffer.toString();
        } else if (obj instanceof char[]) {
            char[] cArr = (char[]) obj;
            stringBuffer = new StringBuffer();
            while (i < cArr.length) {
                char c = cArr[i];
                if (i > 50) {
                    stringBuffer.append("... (" + cArr.length + ")");
                    break;
                }
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("" + c);
                i++;
            }
            return stringBuffer.toString();
        } else if (!(obj instanceof float[])) {
            return "Unknown: " + obj.getClass().getName();
        } else {
            float[] fArr = (float[]) obj;
            stringBuffer = new StringBuffer();
            while (i < fArr.length) {
                float f = fArr[i];
                if (i > 50) {
                    stringBuffer.append("... (" + fArr.length + ")");
                    break;
                }
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("" + f);
                i++;
            }
            return stringBuffer.toString();
        }
    }

    public void e() {
        PrintWriter printWriter = new PrintWriter(System.out);
        a(printWriter);
        printWriter.flush();
    }

    public void a(PrintWriter printWriter) {
        a(printWriter, null);
    }

    public void a(PrintWriter printWriter, String str) {
        if (str != null) {
            printWriter.print(str + ": ");
        }
        printWriter.println(toString());
        printWriter.flush();
    }

    public String f() {
        return this.l + " (0x" + Integer.toHexString(this.l) + ": " + this.j.j + "): ";
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.l + " (0x" + Integer.toHexString(this.l) + ": " + this.j.j + "): ");
        stringBuffer.append(d() + " (" + this.o + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.k.m + ")");
        return stringBuffer.toString();
    }

    public String g() {
        if (this.j == hG_) {
            return this.j.j + " (0x" + Integer.toHexString(this.l) + ")";
        }
        return this.j.j;
    }

    public String h() {
        return this.k.m;
    }

    public Object i() throws dji.thirdparty.g.e {
        return this.j.a(this);
    }

    public String j() throws dji.thirdparty.g.e {
        Object i = i();
        if (i == null) {
            return null;
        }
        if (i instanceof String) {
            return (String) i;
        }
        throw new dji.thirdparty.g.e("Expected String value(" + this.j.a() + "): " + i);
    }

    private static final Map a(dji.thirdparty.g.b.b.a.e[] eVarArr, boolean z, String str) {
        Map hashtable = new Hashtable();
        for (dji.thirdparty.g.b.b.a.e eVar : eVarArr) {
            Integer num = new Integer(eVar.k);
            List list = (List) hashtable.get(num);
            if (list == null) {
                list = new ArrayList();
                hashtable.put(num, list);
            }
            list.add(eVar);
        }
        return hashtable;
    }

    public int[] k() throws dji.thirdparty.g.e {
        int i = 0;
        Object i2 = i();
        if (i2 instanceof Number) {
            return new int[]{((Number) i2).intValue()};
        } else if (i2 instanceof Number[]) {
            Number[] numberArr = (Number[]) i2;
            r2 = new int[numberArr.length];
            while (i < numberArr.length) {
                r2[i] = numberArr[i].intValue();
                i++;
            }
            return r2;
        } else if (i2 instanceof int[]) {
            int[] iArr = (int[]) i2;
            r2 = new int[iArr.length];
            while (i < iArr.length) {
                r2[i] = iArr[i];
                i++;
            }
            return r2;
        } else {
            throw new dji.thirdparty.g.e("Unknown value: " + i2 + " for: " + this.j.a());
        }
    }

    public double[] l() throws dji.thirdparty.g.e {
        int i = 0;
        Object i2 = i();
        if (i2 instanceof Number) {
            return new double[]{((Number) i2).doubleValue()};
        } else if (i2 instanceof Number[]) {
            Number[] numberArr = (Number[]) i2;
            r2 = new double[numberArr.length];
            while (i < numberArr.length) {
                r2[i] = numberArr[i].doubleValue();
                i++;
            }
            return r2;
        } else if (i2 instanceof int[]) {
            int[] iArr = (int[]) i2;
            r2 = new double[iArr.length];
            while (i < iArr.length) {
                r2[i] = (double) iArr[i];
                i++;
            }
            return r2;
        } else if (i2 instanceof float[]) {
            float[] fArr = (float[]) i2;
            r2 = new double[fArr.length];
            while (i < fArr.length) {
                r2[i] = (double) fArr[i];
                i++;
            }
            return r2;
        } else if (i2 instanceof double[]) {
            double[] dArr = (double[]) i2;
            r2 = new double[dArr.length];
            while (i < dArr.length) {
                r2[i] = dArr[i];
                i++;
            }
            return r2;
        } else {
            throw new dji.thirdparty.g.e("Unknown value: " + i2 + " for: " + this.j.a());
        }
    }

    public int m() throws dji.thirdparty.g.e {
        int i = 0;
        Object i2 = i();
        if (i2 instanceof Number) {
            return ((Number) i2).intValue();
        }
        int i3;
        if (i2 instanceof Number[]) {
            Number[] numberArr = (Number[]) i2;
            i3 = 0;
            while (i < numberArr.length) {
                i3 += numberArr[i].intValue();
                i++;
            }
            return i3;
        } else if (i2 instanceof int[]) {
            int[] iArr = (int[]) i2;
            i3 = 0;
            while (i < iArr.length) {
                i3 += iArr[i];
                i++;
            }
            return i3;
        } else {
            throw new dji.thirdparty.g.e("Unknown value: " + i2 + " for: " + this.j.a());
        }
    }

    public int n() throws dji.thirdparty.g.e {
        Object i = i();
        if (i != null) {
            return ((Number) i).intValue();
        }
        throw new dji.thirdparty.g.e("Missing value: " + this.j.a());
    }

    public double o() throws dji.thirdparty.g.e {
        Object i = i();
        if (i != null) {
            return ((Number) i).doubleValue();
        }
        throw new dji.thirdparty.g.e("Missing value: " + this.j.a());
    }

    public byte[] p() throws dji.thirdparty.g.e {
        return this.k.d(this);
    }

    public int q() {
        return this.mD;
    }

    public void a(int i) {
        this.mD = i;
    }
}
