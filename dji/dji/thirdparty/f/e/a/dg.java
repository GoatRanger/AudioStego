package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.e.b.e;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class dg<T> implements d$g<List<T>, T> {

    private static final class a {
        static final dg<Object> a = new dg();

        private a() {
        }
    }

    public static <T> dg<T> a() {
        return a.a;
    }

    dg() {
    }

    public k<? super T> a(final k<? super List<T>> kVar) {
        final f eVar = new e(kVar);
        l anonymousClass1 = new k<T>(this) {
            boolean a = false;
            List<T> b = new LinkedList();
            final /* synthetic */ dg e;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void o_() {
                if (!this.a) {
                    this.a = true;
                    try {
                        Object arrayList = new ArrayList(this.b);
                        this.b = null;
                        eVar.a(arrayList);
                    } catch (Throwable th) {
                        b.a(th, (dji.thirdparty.f.e) this);
                    }
                }
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                if (!this.a) {
                    this.b.add(t);
                }
            }
        };
        kVar.a(anonymousClass1);
        kVar.a(eVar);
        return anonymousClass1;
    }
}
