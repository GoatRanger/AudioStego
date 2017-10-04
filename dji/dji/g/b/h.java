package dji.g.b;

import android.view.Surface;
import dji.g.a.a.a;
import dji.g.c.a.e;

public class h {
    public static final String a = "SaveParam";
    public final String b;
    public i c;
    public e[] d;
    public boolean e;
    public String f;
    public a g;
    public Surface h;
    public e i;
    public boolean j;
    public long k;
    public int l;
    public int m;
    public long n = 0;

    public h(e[] eVarArr, boolean z, String str, a aVar, String str2, boolean z2, int i, int i2, Surface surface, i iVar, e eVar, long j) {
        this.d = eVarArr;
        this.e = z;
        this.f = str;
        this.g = aVar;
        this.b = str2;
        this.h = surface;
        this.c = iVar;
        this.i = eVar;
        this.j = z2;
        if (z2) {
            this.l = i;
            this.m = i2;
        } else {
            this.m = 0;
            this.l = 0;
        }
        this.k = j;
        dji.midware.media.e.d(a, String.format("Output Resolution=[width=%d height=%d]", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
    }

    public void a(long j) {
        this.n = j;
    }

    public h(String str, i iVar) {
        this.b = str;
        this.c = iVar;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.d == null) {
            stringBuilder.append("inputFiles==null");
        } else {
            for (e eVar : this.d) {
                stringBuilder.append(eVar.toString() + ", ");
            }
        }
        stringBuilder.append(" output_file=" + this.b);
        stringBuilder.append(" specifyOutputResolution=" + this.j);
        stringBuilder.append(" out_width=" + this.l);
        stringBuilder.append(" out_height=" + this.m);
        return stringBuilder.toString();
    }
}
