package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.e.b.e;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class dh<T> implements d$g<List<T>, T> {
    private static Comparator c = new a();
    final Comparator<? super T> a;
    final int b;

    private static class a implements Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    }

    public dh(int i) {
        this.a = c;
        this.b = i;
    }

    public dh(final p<? super T, ? super T, Integer> pVar, int i) {
        this.b = i;
        this.a = new Comparator<T>(this) {
            final /* synthetic */ dh b;

            public int compare(T t, T t2) {
                return ((Integer) pVar.b(t, t2)).intValue();
            }
        };
    }

    public k<? super T> a(final k<? super List<T>> kVar) {
        final f eVar = new e(kVar);
        l anonymousClass2 = new k<T>(this) {
            List<T> a = new ArrayList(this.e.b);
            boolean b;
            final /* synthetic */ dh e;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void o_() {
                if (!this.b) {
                    this.b = true;
                    Object obj = this.a;
                    this.a = null;
                    try {
                        Collections.sort(obj, this.e.a);
                        eVar.a(obj);
                    } catch (Throwable th) {
                        b.a(th, (dji.thirdparty.f.e) this);
                    }
                }
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                if (!this.b) {
                    this.a.add(t);
                }
            }
        };
        kVar.a(anonymousClass2);
        kVar.a(eVar);
        return anonymousClass2;
    }
}
