package dji.midware.c.a;

import dji.midware.c.a.d;
import dji.midware.data.config.P3.ProductType;

public enum c {
    IN1_GL658A("GL658A", ProductType.Orange, d.Inspire),
    IN1_GL690A("GL690A", ProductType.Orange, d.Inspire),
    IN1_GL658B("GL658B", ProductType.Orange, d.Inspire),
    P3_GL300A("GL300A", ProductType.litchiX, d.P3P4),
    P3_GL300B("GL300B", ProductType.litchiX, d.P3P4),
    P4_GL300C("GL300C", ProductType.litchiX, d.P3P4),
    P3C_GL358wA("GL358wA", ProductType.litchiC, d.P3c),
    P3C_GL390wA("GL390wA", ProductType.litchiC, d.P3c),
    P3C_GL358wB("GL358wB", ProductType.P34K, d.P3w),
    LB2_GL858A("GL858A", ProductType.Grape2, d.LB2),
    LB2_GL890A("GL890A", ProductType.Grape2, d.LB2),
    P4_PV1("P4_PV1", ProductType.Pomato, d.P4),
    P4_PV2("P4_PV2", ProductType.Pomato, d.P4);
    
    protected ProductType n;
    protected d o;
    protected String p;

    private c(String str, ProductType productType, d dVar) {
        this.p = str;
        this.n = productType;
        this.o = dVar;
    }

    public static c getByOsdData(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        String c = dji.midware.util.c.c(bArr, 1, 16);
        if (c == null || c.isEmpty()) {
            return null;
        }
        c[] values = values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            c cVar = values[i];
            if (!c.contains(cVar.p)) {
                i++;
            } else if (cVar == P4_PV1 || cVar == P4_PV2) {
                return cVar;
            } else {
                return null;
            }
        }
        return null;
    }
}
