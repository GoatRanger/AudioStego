package dji.thirdparty.e;

import dji.thirdparty.b.ab;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

final class g extends dji.thirdparty.e.c.a {
    final Executor a;

    static final class a<T> implements b<T> {
        final Executor a;
        final b<T> b;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return e();
        }

        a(Executor executor, b<T> bVar) {
            this.a = executor;
            this.b = bVar;
        }

        public void a(final d<T> dVar) {
            if (dVar == null) {
                throw new NullPointerException("callback == null");
            }
            this.b.a(new d<T>(this) {
                final /* synthetic */ a b;

                public void a(final b<T> bVar, final l<T> lVar) {
                    this.b.a.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 c;

                        public void run() {
                            if (this.c.b.b.d()) {
                                dVar.a(bVar, new IOException("Canceled"));
                            } else {
                                dVar.a(bVar, lVar);
                            }
                        }
                    });
                }

                public void a(final b<T> bVar, final Throwable th) {
                    this.b.a.execute(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 c;

                        public void run() {
                            dVar.a(bVar, th);
                        }
                    });
                }
            });
        }

        public boolean b() {
            return this.b.b();
        }

        public l<T> a() throws IOException {
            return this.b.a();
        }

        public void c() {
            this.b.c();
        }

        public boolean d() {
            return this.b.d();
        }

        public b<T> e() {
            return new a(this.a, this.b.e());
        }

        public ab f() {
            return this.b.f();
        }
    }

    g(Executor executor) {
        this.a = executor;
    }

    public c<b<?>> a(Type type, Annotation[] annotationArr, m mVar) {
        if (dji.thirdparty.e.c.a.a(type) != b.class) {
            return null;
        }
        final Type e = o.e(type);
        return new c<b<?>>(this) {
            final /* synthetic */ g b;

            public /* synthetic */ Object a(b bVar) {
                return b(bVar);
            }

            public Type a() {
                return e;
            }

            public <R> b<R> b(b<R> bVar) {
                return new a(this.b.a, bVar);
            }
        };
    }
}
