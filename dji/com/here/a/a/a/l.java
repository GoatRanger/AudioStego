package com.here.a.a.a;

import com.here.a.a.a.a.aa;
import com.here.a.a.a.a.ab;
import com.here.a.a.a.a.ad;
import com.here.a.a.a.a.ag;
import com.here.a.a.a.a.ai;
import com.here.a.a.a.a.al;
import com.here.a.a.a.a.g;
import com.here.a.a.a.a.h;
import com.here.a.a.a.a.o;
import com.here.a.a.a.a.x;
import com.here.a.a.a.a.z;

public abstract class l<T, V extends i> {
    protected abstract T a(o oVar, V v);

    public static <T extends j> l<ai, T> a() {
        return new l<ai, T>() {
            protected ai a(o oVar, j jVar) {
                return ai.fromJSON(oVar);
            }
        };
    }

    public static l<ab, q> b() {
        return new l<ab, q>() {
            protected ab a(o oVar, q qVar) {
                return ab.a(oVar, qVar);
            }
        };
    }

    public static <V extends i> l<al, V> c() {
        return new l<al, V>() {
            protected /* synthetic */ Object a(o oVar, i iVar) {
                return b(oVar, iVar);
            }

            protected al b(o oVar, V v) {
                return al.a(oVar, v);
            }
        };
    }

    public static l<z, g> d() {
        return new l<z, g>() {
            protected z a(o oVar, g gVar) {
                return z.a(oVar, gVar);
            }
        };
    }

    public static l<aa, h> e() {
        return new l<aa, h>() {
            protected aa a(o oVar, h hVar) {
                return aa.fromJSON(oVar);
            }
        };
    }

    public static <V extends i> l<h, V> f() {
        return new l<h, V>() {
            protected /* synthetic */ Object a(o oVar, i iVar) {
                return b(oVar, iVar);
            }

            protected h b(o oVar, V v) {
                return h.b(oVar, v);
            }
        };
    }

    public static <V extends i> l<g, V> g() {
        return new l<g, V>() {
            protected /* synthetic */ Object a(o oVar, i iVar) {
                return b(oVar, iVar);
            }

            protected g b(o oVar, V v) {
                return g.a(oVar, v);
            }
        };
    }

    public ag<T> a(String str, V v) {
        o a = o.a(str);
        if (!a.b("Res")) {
            ag<T> agVar = new ag();
            a = a.c("Res");
            if (!a.b("Message")) {
                agVar.a = ad.b(x.fromJSON(a.c("Message")));
            }
            if (!agVar.a()) {
                agVar.b = ad.b(a(a, (i) v));
            }
            return agVar;
        } else if (a.b("error")) {
            throw new k("Unknown response format: " + a);
        } else {
            String i = a.i("error");
            String a2 = a.a(com.facebook.internal.ab.am, "none");
            throw new k(String.format("%s: %s", new Object[]{i, a2}));
        }
    }
}
