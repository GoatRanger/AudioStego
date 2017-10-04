package dji.thirdparty.g.b.b.b;

import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.c.c;
import dji.thirdparty.g.f;

public class h extends a {
    public h(int i, String str) {
        super(i, 2, str);
    }

    public Object e(e eVar) throws dji.thirdparty.g.e {
        if (eVar.o == 1) {
            return new Integer(c(this.m + " (" + eVar.j.j + ")", eVar.q, eVar.s));
        }
        return c(this.m + " (" + eVar.j.j + ")", d(eVar), 0, eVar.o, eVar.s);
    }

    public byte[] a(Object obj, int i) throws f {
        int i2 = 0;
        if (obj instanceof Integer) {
            return b(new int[]{((Integer) obj).intValue()}, i);
        } else if (obj instanceof int[]) {
            return b((int[]) obj, i);
        } else {
            if (obj instanceof Integer[]) {
                Integer[] numArr = (Integer[]) obj;
                int[] iArr = new int[numArr.length];
                while (i2 < iArr.length) {
                    iArr[i2] = numArr[i2].intValue();
                    i2++;
                }
                return b(iArr, i);
            }
            throw new f("Invalid data: " + obj + " (" + c.b(obj) + ")");
        }
    }
}
