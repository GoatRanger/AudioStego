package dji.thirdparty.g.b.b.b;

import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.c.c;

public class f extends a {
    public f(int i, String str) {
        super(i, 4, str);
    }

    public Object e(e eVar) {
        if (eVar.o == 1) {
            return new Integer(b(this.m + " (" + eVar.j.j + ")", eVar.q, eVar.s));
        }
        return b(this.m + " (" + eVar.j.j + ")", d(eVar), 0, eVar.o, eVar.s);
    }

    public byte[] a(Object obj, int i) throws dji.thirdparty.g.f {
        int i2 = 0;
        if (obj instanceof Integer) {
            return a(new int[]{((Integer) obj).intValue()}, i);
        } else if (obj instanceof int[]) {
            return a((int[]) obj, i);
        } else {
            if (obj instanceof Integer[]) {
                Integer[] numArr = (Integer[]) obj;
                int[] iArr = new int[numArr.length];
                while (i2 < iArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
                return a(iArr, i);
            }
            throw new dji.thirdparty.g.f("Invalid data: " + obj + " (" + c.b(obj) + ")");
        }
    }
}
