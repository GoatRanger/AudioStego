package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.g.e;
import dji.thirdparty.f.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class df<T, K, V> implements d$g<Map<K, Collection<V>>, T> {
    final o<? super T, ? extends K> a;
    final o<? super T, ? extends V> b;
    final o<? super K, ? extends Collection<V>> c;
    private final n<? extends Map<K, Collection<V>>> d;

    public static final class a<K, V> implements o<K, Collection<V>> {
        public /* synthetic */ Object a(Object obj) {
            return b(obj);
        }

        public Collection<V> b(K k) {
            return new ArrayList();
        }
    }

    public static final class b<K, V> implements n<Map<K, Collection<V>>> {
        public /* synthetic */ Object call() {
            return a();
        }

        public Map<K, Collection<V>> a() {
            return new HashMap();
        }
    }

    public df(o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2) {
        this(oVar, oVar2, new b(), new a());
    }

    public df(o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2, n<? extends Map<K, Collection<V>>> nVar) {
        this(oVar, oVar2, nVar, new a());
    }

    public df(o<? super T, ? extends K> oVar, o<? super T, ? extends V> oVar2, n<? extends Map<K, Collection<V>>> nVar, o<? super K, ? extends Collection<V>> oVar3) {
        this.a = oVar;
        this.b = oVar2;
        this.d = nVar;
        this.c = oVar3;
    }

    public k<? super T> a(final k<? super Map<K, Collection<V>>> kVar) {
        try {
            final Map map = (Map) this.d.call();
            return new k<T>(this, kVar) {
                final /* synthetic */ df c;
                private Map<K, Collection<V>> d = map;

                public void c() {
                    a((long) IPositioningSession.NotSet);
                }

                public void a_(T t) {
                    try {
                        Object a = this.c.a.a(t);
                        Object a2 = this.c.b.a(t);
                        Collection collection = (Collection) this.d.get(a);
                        if (collection == null) {
                            try {
                                collection = (Collection) this.c.c.a(a);
                                this.d.put(a, collection);
                            } catch (Throwable th) {
                                dji.thirdparty.f.c.b.a(th, kVar);
                                return;
                            }
                        }
                        collection.add(a2);
                    } catch (Throwable th2) {
                        dji.thirdparty.f.c.b.a(th2, kVar);
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
            dji.thirdparty.f.c.b.b(th);
            kVar.a(th);
            k<? super T> a = e.a();
            a.n_();
            return a;
        }
    }
}
