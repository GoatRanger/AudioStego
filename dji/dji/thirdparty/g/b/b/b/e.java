package dji.thirdparty.g.b.b.b;

import dji.thirdparty.g.c.c;
import dji.thirdparty.g.f;

public class e extends a {
    public e() {
        super(11, 4, "Float");
    }

    public Object e(dji.thirdparty.g.b.b.e eVar) {
        if (eVar.o == 1) {
            return new Float(f(this.m + " (" + eVar.j.j + ")", eVar.q, eVar.s));
        }
        return d(this.m + " (" + eVar.j.j + ")", d(eVar), 0, eVar.o, eVar.s);
    }

    public byte[] a(Object obj, int i) throws f {
        if (obj instanceof Float) {
            return a(((Float) obj).floatValue(), i);
        }
        if (obj instanceof float[]) {
            return a((float[]) obj, i);
        }
        if (obj instanceof Float[]) {
            Float[] fArr = (Float[]) obj;
            float[] fArr2 = new float[fArr.length];
            for (int i2 = 0; i2 < fArr2.length; i2++) {
                fArr2[i2] = fArr[i2].floatValue();
            }
            return a(fArr2, i);
        }
        throw new f("Invalid data: " + obj + " (" + c.b(obj) + ")");
    }
}
