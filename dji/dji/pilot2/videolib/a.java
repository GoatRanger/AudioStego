package dji.pilot2.videolib;

import dji.g.c.a.e;
import dji.pilot2.bigfilm.RenderDatas;

public class a implements e {
    protected VideoLibWrapper a = new VideoLibWrapper();
    protected a b;

    public interface a {
        int a();

        int a(int i, int i2);

        RenderDatas a(int[] iArr, int[] iArr2, int[] iArr3, long j, long j2, boolean z);
    }

    public void a() {
        if (this.a != null) {
            this.a._nativeInit();
        }
    }

    public void b() {
        if (this.a != null) {
            this.a._nativeUnInit();
        }
    }

    public void a(double[] dArr) {
        if (this.a != null) {
            this.a.nativeSetBright(dArr);
        }
    }

    public void b(double[] dArr) {
        if (this.a != null) {
            this.a.nativeSetSaturation(dArr);
        }
    }

    public void c(double[] dArr) {
        if (this.a != null) {
            this.a.nativeSetContrast(dArr);
        }
    }

    public void a(int[] iArr, double[] dArr) {
        if (this.a != null) {
            this.a.nativeSetFilters(iArr, dArr);
        }
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.nativeSetWaterFresh(i);
        }
    }

    public void b(int i) {
        if (this.a != null) {
            this.a.nativeSetSelectSingleTemplateID(i);
        }
    }

    public void c(int i) {
        if (this.a != null) {
            this.a.setMultiVideoFilterNum(i);
        }
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public int a(int i, int i2) {
        if (this.a == null) {
            return 0;
        }
        if (this.b != null) {
            this.b.a(i, i2);
        }
        return this.a.nativeFilterSetOutputSize(i, i2);
    }

    public int a(int[] iArr, int[] iArr2, int[] iArr3, long j, long j2, boolean z) {
        if (this.a == null) {
            return 0;
        }
        RenderDatas renderDatas = null;
        if (this.b != null) {
            renderDatas = this.b.a(iArr, iArr2, iArr3, j, j2, z);
        }
        return this.a.nativeFilterApply(iArr, iArr2, j, j2, z, renderDatas);
    }

    public int a(int[] iArr, int[] iArr2, long j, long j2, boolean z) {
        return 0;
    }

    public int c() {
        if (this.a != null) {
            return this.a.nativeFilterDestroy();
        }
        return 0;
    }
}
