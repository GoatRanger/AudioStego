package com.google.a;

import com.google.a.a.c;
import com.google.a.c.b;
import com.google.a.g.d;
import com.google.a.g.f;
import com.google.a.g.i;
import com.google.a.g.k;
import com.google.a.g.n;
import com.google.a.g.t;
import java.util.Map;

public final class l implements v {
    public b a(String str, a aVar, int i, int i2) throws w {
        return a(str, aVar, i, i2, null);
    }

    public b a(String str, a aVar, int i, int i2, Map<g, ?> map) throws w {
        v kVar;
        switch (aVar) {
            case EAN_8:
                kVar = new k();
                break;
            case EAN_13:
                kVar = new i();
                break;
            case UPC_A:
                kVar = new t();
                break;
            case QR_CODE:
                kVar = new com.google.a.i.b();
                break;
            case CODE_39:
                kVar = new f();
                break;
            case CODE_128:
                kVar = new d();
                break;
            case ITF:
                kVar = new n();
                break;
            case PDF_417:
                kVar = new com.google.a.h.d();
                break;
            case CODABAR:
                kVar = new com.google.a.g.b();
                break;
            case DATA_MATRIX:
                kVar = new com.google.a.d.b();
                break;
            case AZTEC:
                kVar = new c();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + aVar);
        }
        return kVar.a(str, aVar, i, i2, map);
    }
}
