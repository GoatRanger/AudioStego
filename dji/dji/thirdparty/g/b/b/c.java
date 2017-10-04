package dji.thirdparty.g.b.b;

import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.g.b.b.a.f;
import dji.thirdparty.g.e;
import java.io.IOException;
import java.util.ArrayList;

public class c extends d implements f {
    public final int j;
    public final ArrayList k;
    public final int l;
    private a m = null;

    public final class a extends d {
        final /* synthetic */ c a;

        public a(c cVar, int i, int i2) {
            this.a = cVar;
            super(i, i2);
        }

        public String a(boolean z) {
            if (z) {
                return null;
            }
            return "ImageDataElement";
        }
    }

    public String a() {
        return a(this.j);
    }

    public String a(boolean z) {
        if (!z) {
            return "TIFF Directory (" + a() + ")";
        }
        int i = this.gv_ + 2;
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = i;
        for (i = 0; i < this.k.size(); i++) {
            e eVar = (e) this.k.get(i);
            stringBuffer.append("\t");
            stringBuffer.append(d.G + i2 + "]: ");
            stringBuffer.append(eVar.j.j);
            stringBuffer.append(" (" + eVar.l + ", 0x" + Integer.toHexString(eVar.l) + ")");
            stringBuffer.append(", " + eVar.k.m);
            stringBuffer.append(", " + eVar.k.d(eVar).length);
            stringBuffer.append(": " + eVar.d());
            stringBuffer.append("\n");
            i2 += 12;
        }
        return stringBuffer.toString();
    }

    public static final String a(int i) {
        switch (i) {
            case -4:
                return "Interoperability";
            case -3:
                return "Gps";
            case -2:
                return "Exif";
            case -1:
                return "Unknown";
            case 0:
                return "Root";
            case 1:
                return "Sub";
            case 2:
                return "Thumbnail";
            default:
                return "Bad Type";
        }
    }

    public c(int i, ArrayList arrayList, int i2, int i3) {
        super(i2, ((arrayList.size() * 12) + 2) + 4);
        this.j = i;
        this.k = arrayList;
        this.l = i3;
    }

    public ArrayList b() {
        return new ArrayList(this.k);
    }

    protected void a(dji.thirdparty.g.a.a.a aVar) throws e, IOException {
        for (int i = 0; i < this.k.size(); i++) {
            ((e) this.k.get(i)).a(aVar);
        }
    }

    public void c() {
        for (int i = 0; i < this.k.size(); i++) {
            ((e) this.k.get(i)).e();
        }
    }

    public boolean d() throws e {
        if (a(hs_) != null) {
            return true;
        }
        return false;
    }

    public boolean e() throws e {
        if (a(hf_) == null && a(fM_) == null) {
            return false;
        }
        return true;
    }

    public e a(dji.thirdparty.g.b.b.a.e eVar) throws e {
        return a(eVar, false);
    }

    public e a(dji.thirdparty.g.b.b.a.e eVar, boolean z) throws e {
        if (this.k == null) {
            return null;
        }
        for (int i = 0; i < this.k.size(); i++) {
            e eVar2 = (e) this.k.get(i);
            if (eVar2.l == eVar.k) {
                return eVar2;
            }
        }
        if (!z) {
            return null;
        }
        throw new e("Missing expected field: " + eVar.a());
    }

    private ArrayList a(e eVar, e eVar2) throws e {
        int[] k = eVar.k();
        int[] k2 = eVar2.k();
        if (k.length != k2.length) {
            throw new e("offsets.length(" + k.length + ") != byteCounts.length(" + k2.length + ")");
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < k.length; i++) {
            arrayList.add(new a(this, k[i], k2[i]));
        }
        return arrayList;
    }

    public ArrayList f() throws e {
        e a = a(hf_);
        e a2 = a(hg_);
        e a3 = a(fM_);
        e a4 = a(gC_);
        if (a != null && a2 != null) {
            return a(a, a2);
        }
        if (a3 != null && a4 != null) {
            return a(a3, a4);
        }
        throw new e("Couldn't find image data.");
    }

    public boolean g() throws e {
        e a = a(hf_);
        e a2 = a(hg_);
        e a3 = a(fM_);
        e a4 = a(gC_);
        if (a != null && a2 != null) {
            return false;
        }
        if (a3 != null && a4 != null) {
            return true;
        }
        if (a3 != null && a4 != null) {
            return true;
        }
        throw new e("Couldn't find image data.");
    }

    public a h() throws e {
        e a = a(hs_);
        e a2 = a(ht_);
        if (a != null && a2 != null) {
            return new a(this, a.k()[0], a2.k()[0]);
        }
        throw new e("Couldn't find image data.");
    }

    public void a(a aVar) {
        this.m = aVar;
    }

    public a i() {
        return this.m;
    }
}
