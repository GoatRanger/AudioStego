package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import java.util.HashMap;
import java.util.Map;

public final class de<T, K, V> implements d$g<Map<K, V>, T> {
    final o<? super T, ? extends K> a;
    final o<? super T, ? extends V> b;
    private final n<? extends Map<K, V>> c;

    public static final class a<K, V> implements n<Map<K, V>> {
        public /* synthetic */ Object call() {
            return a();
        }

        public Map<K, V> a() {
            return new HashMap();
        }
    }

    public de(o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2) {
        this(oVar, oVar2, new a());
    }

    public de(o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2, n<? extends Map<K, V>> nVar) {
        this.a = oVar;
        this.b = oVar2;
        this.c = nVar;
    }

    public k<? super T> a(final k<? super Map<K, V>> kVar) {
        try {
            final Map map = (Map) this.c.call();
            return new k<T>(this, kVar) {
                final /* synthetic */ de c;
                private Map<K, V> d = map;

                public void c() {
                    a((long) IPositioningSession.NotSet);
                }

                public void a_(T t) {
                    try {
                        this.d.put(this.c.a.a(t), this.c.b.a(t));
                    } catch (Throwable th) {
                        b.a(th, kVar);
                    }
                }

                public void a(Throwable th) {
                    this.d = null;
                    kVar.a(th);
                }

                public void o_() {
                    Map map = this.d;
                    this.d = null;
                    kVar.a_(map);
                    kVar.o_();
                }
            };
        } catch (Throwable th) {
            b.a(th, (e) kVar);
            k<? super T> a = dji.thirdparty.f.g.e.a();
            a.n_();
            return a;
        }
    }
}
