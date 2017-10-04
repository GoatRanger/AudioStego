package com.facebook.internal;

import com.facebook.k;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class g {

    public interface b {
        void a(k kVar);
    }

    public interface d extends b {
        void a();
    }

    public interface c extends b {
        void a(Object obj);
    }

    public interface a<T> {
        Object a(T t);

        Iterator<T> a();

        void a(T t, Object obj, b bVar);
    }

    public interface e {
        void a(Object obj, c cVar);
    }

    public static <T> void a(final a<T> aVar, e eVar, final d dVar) {
        final z zVar = new z(Boolean.valueOf(false));
        final z zVar2 = new z(Integer.valueOf(1));
        final d anonymousClass1 = new d() {
            public void a() {
                if (!((Boolean) zVar.a).booleanValue()) {
                    z zVar = zVar2;
                    Integer valueOf = Integer.valueOf(((Integer) zVar2.a).intValue() - 1);
                    zVar.a = valueOf;
                    if (valueOf.intValue() == 0) {
                        dVar.a();
                    }
                }
            }

            public void a(k kVar) {
                if (!((Boolean) zVar.a).booleanValue()) {
                    zVar.a = Boolean.valueOf(true);
                    dVar.a(kVar);
                }
            }
        };
        Iterator a = aVar.a();
        List linkedList = new LinkedList();
        while (a.hasNext()) {
            linkedList.add(a.next());
        }
        for (final Object next : linkedList) {
            Object a2 = aVar.a(next);
            c anonymousClass2 = new c() {
                public void a(Object obj) {
                    aVar.a(next, obj, anonymousClass1);
                    anonymousClass1.a();
                }

                public void a(k kVar) {
                    anonymousClass1.a(kVar);
                }
            };
            Integer num = (Integer) zVar2.a;
            zVar2.a = Integer.valueOf(((Integer) zVar2.a).intValue() + 1);
            eVar.a(a2, anonymousClass2);
        }
        anonymousClass1.a();
    }

    private g() {
    }
}
