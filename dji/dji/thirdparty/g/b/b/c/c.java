package dji.thirdparty.g.b.b.c;

import dji.thirdparty.g.a.a.b;
import dji.thirdparty.g.b.b.d;
import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.b.b.i;
import dji.thirdparty.g.f;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class c extends b {
    private static final Comparator l = new Comparator() {
        public int compare(Object obj, Object obj2) {
            return ((d) obj).gw_ - ((d) obj2).gw_;
        }
    };
    private static final Comparator m = new Comparator() {
        public int compare(Object obj, Object obj2) {
            return ((g) obj).e() - ((g) obj2).e();
        }
    };
    private final byte[] k;

    private static class a extends OutputStream {
        private final byte[] a;
        private int b;

        public a(byte[] bArr, int i) {
            this.a = bArr;
            this.b = i;
        }

        public void write(int i) throws IOException {
            if (this.b >= this.a.length) {
                throw new IOException("Buffer overflow.");
            }
            byte[] bArr = this.a;
            int i2 = this.b;
            this.b = i2 + 1;
            bArr[i2] = (byte) i;
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (this.b + i2 > this.a.length) {
                throw new IOException("Buffer overflow.");
            }
            System.arraycopy(bArr, i, this.a, this.b, i2);
            this.b += i2;
        }
    }

    public c(byte[] bArr) {
        this.k = bArr;
    }

    public c(int i, byte[] bArr) {
        super(i);
        this.k = bArr;
    }

    private void a(List list) throws IOException {
        a(new b(this.k), list);
    }

    private void a(dji.thirdparty.g.a.a.a aVar, List list) throws IOException {
        int i = 8;
        for (int i2 = 0; i2 < list.size(); i2++) {
            d dVar = (d) list.get(i2);
            if (dVar.gv_ > i) {
                int i3 = dVar.gv_ - i;
                dji.thirdparty.g.c.c.a("gap of " + i3 + " bytes.");
                byte[] c = aVar.c(i, i3);
                if (c.length > 64) {
                    dji.thirdparty.g.c.c.b("\thead", dji.thirdparty.g.a.b.b(c, 32));
                    dji.thirdparty.g.c.c.b("\ttail", dji.thirdparty.g.a.b.a(c, 32));
                } else {
                    dji.thirdparty.g.c.c.b("\tbytes", c);
                }
            }
            dji.thirdparty.g.c.c.a("element[" + i2 + "]:" + dVar.j() + " (" + dVar.gv_ + " + " + dVar.gw_ + " = " + (dVar.gv_ + dVar.gw_) + ")");
            if (dVar instanceof dji.thirdparty.g.b.b.c) {
                dji.thirdparty.g.c.c.a("\tnext Directory Offset: " + ((dji.thirdparty.g.b.b.c) dVar).l);
            }
            i = dVar.gv_ + dVar.gw_;
        }
        dji.thirdparty.g.c.c.a();
    }

    private List a() throws f, IOException {
        try {
            int i;
            d c;
            dji.thirdparty.g.b.b.b a = new i(false).a(new b(this.k), null, dji.thirdparty.g.a.a());
            ArrayList arrayList = new ArrayList();
            List list = a.b;
            for (int i2 = 0; i2 < list.size(); i2++) {
                dji.thirdparty.g.b.b.c cVar = (dji.thirdparty.g.b.b.c) list.get(i2);
                arrayList.add(cVar);
                List b = cVar.b();
                for (i = 0; i < b.size(); i++) {
                    c = ((e) b.get(i)).c();
                    if (c != null) {
                        arrayList.add(c);
                    }
                }
                dji.thirdparty.g.b.b.a i3 = cVar.i();
                if (i3 != null) {
                    arrayList.add(i3);
                }
            }
            Collections.sort(arrayList, d.gx_);
            List arrayList2 = new ArrayList();
            i = -1;
            d dVar = null;
            int i4 = 0;
            while (i4 < arrayList.size()) {
                int i5;
                d dVar2 = (d) arrayList.get(i4);
                int i6 = dVar2.gv_ + dVar2.gw_;
                int i7;
                if (dVar == null) {
                    i7 = i6;
                    c = dVar2;
                    i5 = i7;
                } else if (dVar2.gv_ - i > 3) {
                    arrayList2.add(new d.b(dVar.gv_, i - dVar.gv_));
                    i7 = i6;
                    c = dVar2;
                    i5 = i7;
                } else {
                    i5 = i6;
                    c = dVar;
                }
                i4++;
                i = i5;
                dVar = c;
            }
            if (dVar != null) {
                arrayList2.add(new d.b(dVar.gv_, i - dVar.gv_));
            }
            return arrayList2;
        } catch (Exception e) {
            throw new f(e.getMessage(), e);
        }
    }

    public void a(OutputStream outputStream, h hVar) throws IOException, f {
        List a = a();
        int length = this.k.length;
        if (a.size() < 1) {
            throw new f("Couldn't analyze old tiff data.");
        }
        if (a.size() == 1) {
            d dVar = (d) a.get(0);
            if (dVar.gv_ == 8) {
                if ((dVar.gw_ + dVar.gv_) + 8 == length) {
                    new d(this.j).a(outputStream, hVar);
                    return;
                }
            }
        }
        i a2 = a(hVar);
        List a3 = hVar.a(a2);
        int a4 = a(a, a3);
        a2.a(this.j);
        a(outputStream, hVar, a, a3, a4);
    }

    private int a(List list, List list2) throws IOException, f {
        int length = this.k.length;
        List arrayList = new ArrayList(list);
        Collections.sort(arrayList, d.gx_);
        Collections.reverse(arrayList);
        int i = length;
        while (arrayList.size() > 0) {
            d dVar = (d) arrayList.get(0);
            if (dVar.gv_ + dVar.gw_ != i) {
                break;
            }
            length = i - dVar.gw_;
            arrayList.remove(0);
            i = length;
        }
        Collections.sort(arrayList, l);
        Collections.reverse(arrayList);
        List arrayList2 = new ArrayList(list2);
        Collections.sort(arrayList2, m);
        Collections.reverse(arrayList2);
        int i2 = i;
        while (arrayList2.size() > 0) {
            g gVar = (g) arrayList2.remove(0);
            int e = gVar.e();
            int i3 = 0;
            d dVar2 = null;
            while (i3 < arrayList.size()) {
                d dVar3 = (d) arrayList.get(i3);
                if (dVar3.gw_ < e) {
                    break;
                }
                i3++;
                dVar2 = dVar3;
            }
            if (dVar2 == null) {
                gVar.c(i2);
                i = i2 + e;
            } else {
                gVar.c(dVar2.gv_);
                arrayList.remove(dVar2);
                if (dVar2.gw_ > e) {
                    arrayList.add(new d.b(dVar2.gv_ + e, dVar2.gw_ - e));
                    Collections.sort(arrayList, l);
                    Collections.reverse(arrayList);
                }
                i = i2;
            }
            i2 = i;
        }
        return i2;
    }

    private void a(OutputStream outputStream, h hVar, List list, List list2, int i) throws IOException, f {
        int i2 = 0;
        e b = hVar.b();
        Object obj = new byte[i];
        System.arraycopy(this.k, 0, obj, 0, Math.min(this.k.length, obj.length));
        a(new dji.thirdparty.g.a.e(new a(obj, 0), this.j), b.g());
        for (int i3 = 0; i3 < list.size(); i3++) {
            d dVar = (d) list.get(i3);
            for (int i4 = 0; i4 < dVar.gw_; i4++) {
                int i5 = dVar.gv_ + i4;
                if (i5 < obj.length) {
                    obj[i5] = null;
                }
            }
        }
        while (i2 < list2.size()) {
            g gVar = (g) list2.get(i2);
            gVar.a(new dji.thirdparty.g.a.e(new a(obj, gVar.g()), this.j));
            i2++;
        }
        outputStream.write(obj);
    }
}
