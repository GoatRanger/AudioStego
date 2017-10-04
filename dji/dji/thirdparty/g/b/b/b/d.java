package dji.thirdparty.g.b.b.b;

import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.c.c;
import dji.thirdparty.g.f;

public class d extends a {
    public d() {
        super(12, 8, "Double");
    }

    public Object e(e eVar) {
        return "?";
    }

    public byte[] a(Object obj, int i) throws f {
        if (obj instanceof Double) {
            return a(((Double) obj).doubleValue(), i);
        }
        if (obj instanceof double[]) {
            return a((double[]) obj, i);
        }
        if (obj instanceof Double[]) {
            Double[] dArr = (Double[]) obj;
            double[] dArr2 = new double[dArr.length];
            for (int i2 = 0; i2 < dArr2.length; i2++) {
                dArr2[i2] = dArr[i2].doubleValue();
            }
            return a(dArr2, i);
        }
        throw new f("Invalid data: " + obj + " (" + c.b(obj) + ")");
    }
}
