package dji.thirdparty.g.b.b.b;

import dji.thirdparty.g.b.b.e;
import dji.thirdparty.g.f;

public class b extends a {
    public b(int i, String str) {
        super(i, 1, str);
    }

    public Object e(e eVar) {
        byte[] d = d(eVar);
        if (d.length > 0) {
            return new String(d, 0, d.length - 1);
        }
        return "";
    }

    public byte[] a(Object obj, int i) throws f {
        if (obj instanceof byte[]) {
            obj = (byte[]) obj;
        } else if (obj instanceof String) {
            obj = ((String) obj).getBytes();
        } else {
            throw new f("Unknown data type: " + obj);
        }
        Object obj2 = new byte[(obj.length + 1)];
        System.arraycopy(obj, 0, obj2, 0, obj.length);
        return obj2;
    }
}
