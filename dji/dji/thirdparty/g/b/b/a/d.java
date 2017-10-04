package dji.thirdparty.g.b.b.a;

import dji.thirdparty.g.b.b.a.g.a;

public class d implements g {
    public static e[] a(e[][] eVarArr) {
        int i;
        int i2 = 0;
        for (e[] length : eVarArr) {
            i2 += length.length;
        }
        Object obj = new e[i2];
        i2 = 0;
        for (i = 0; i < eVarArr.length; i++) {
            System.arraycopy(eVarArr[i], 0, obj, i2, eVarArr[i].length);
            i2 += eVarArr[i].length;
        }
        return obj;
    }

    public static a a(int i) {
        for (int i2 = 0; i2 < mC.length; i2++) {
            if (mC[i2].a == i) {
                return mC[i2];
            }
        }
        return mB;
    }
}
