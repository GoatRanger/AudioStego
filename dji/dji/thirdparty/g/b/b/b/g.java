package dji.thirdparty.g.b.b.b;

import dji.thirdparty.g.a.m;
import dji.thirdparty.g.a.n;
import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.c.c;
import dji.thirdparty.g.f;

public class g extends a {
    public g(int i, String str) {
        super(i, 8, str);
    }

    public Object e(e eVar) {
        if (eVar.o == 1) {
            return a(this.m + " (" + eVar.j.j + ")", eVar.r, eVar.s);
        }
        return a(this.m + " (" + eVar.j.j + ")", d(eVar), 0, eVar.o, eVar.s);
    }

    public byte[] a(Object obj, int i) throws f {
        int i2 = 0;
        if (obj instanceof m) {
            return a((m) obj, i);
        }
        if (obj instanceof m[]) {
            return a((m[]) obj, i);
        }
        if (obj instanceof Number) {
            return a(n.a(((Number) obj).doubleValue()), i);
        }
        m[] mVarArr;
        if (obj instanceof Number[]) {
            Number[] numberArr = (Number[]) obj;
            mVarArr = new m[numberArr.length];
            while (i2 < numberArr.length) {
                mVarArr[i2] = n.a(numberArr[i2].doubleValue());
                i2++;
            }
            return a(mVarArr, i);
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            mVarArr = new m[dArr.length];
            while (i2 < dArr.length) {
                mVarArr[i2] = n.a(dArr[i2]);
                i2++;
            }
            return a(mVarArr, i);
        } else {
            throw new f("Invalid data: " + obj + " (" + c.b(obj) + ")");
        }
    }

    public byte[] a(int i, int i2, int i3) throws f {
        return b(new int[]{i}, new int[]{i2}, i3);
    }

    public byte[] b(int[] iArr, int[] iArr2, int i) throws f {
        return a(iArr, iArr2, i);
    }
}
