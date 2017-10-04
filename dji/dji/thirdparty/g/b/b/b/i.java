package dji.thirdparty.g.b.b.b;

import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.c.c;
import dji.thirdparty.g.f;

public class i extends a {
    public i() {
        super(-1, 1, "Unknown");
    }

    public Object e(e eVar) {
        if (eVar.o == 1) {
            return new Byte(eVar.q[0]);
        }
        return d(eVar);
    }

    public byte[] a(Object obj, int i) throws f {
        if (obj instanceof Byte) {
            return new byte[]{((Byte) obj).byteValue()};
        } else if (obj instanceof byte[]) {
            return (byte[]) obj;
        } else {
            throw new f("Invalid data: " + obj + " (" + c.b(obj) + ")");
        }
    }
}
