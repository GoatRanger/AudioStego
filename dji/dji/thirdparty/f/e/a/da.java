package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.g.a;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;

public final class da<T> extends db<T> {

    class AnonymousClass1 implements a<T> {
        final /* synthetic */ long a;
        final /* synthetic */ TimeUnit b;

        AnonymousClass1(long j, TimeUnit timeUnit) {
            this.a = j;
            this.b = timeUnit;
        }

        public l a(final c<T> cVar, final Long l, a aVar) {
            return aVar.a(new b(this) {
                final /* synthetic */ AnonymousClass1 c;

                public void a() {
                    cVar.b(l.longValue());
                }
            }, this.a, this.b);
        }
    }

    class AnonymousClass2 implements b<T> {
        final /* synthetic */ long a;
        final /* synthetic */ TimeUnit b;

        AnonymousClass2(long j, TimeUnit timeUnit) {
            this.a = j;
            this.b = timeUnit;
        }

        public l a(final c<T> cVar, final Long l, T t, a aVar) {
            return aVar.a(new b(this) {
                final /* synthetic */ AnonymousClass2 c;

                public void a() {
                    cVar.b(l.longValue());
                }
            }, this.a, this.b);
        }
    }

    public /* bridge */ /* synthetic */ k a(k kVar) {
        return super.a(kVar);
    }

    public da(long j, TimeUnit timeUnit, d<? extends T> dVar, g gVar) {
        super(new AnonymousClass1(j, timeUnit), new AnonymousClass2(j, timeUnit), dVar, gVar);
    }
}
