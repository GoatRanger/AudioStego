package com.facebook.internal;

import com.facebook.o;
import java.util.concurrent.Executor;

public class ak {
    public static final int a = 8;
    static final /* synthetic */ boolean b = (!ak.class.desiredAssertionStatus());
    private final Object c;
    private b d;
    private final int e;
    private final Executor f;
    private b g;
    private int h;

    public interface a {
        boolean a();

        boolean b();

        void c();
    }

    private class b implements a {
        static final /* synthetic */ boolean a = (!ak.class.desiredAssertionStatus());
        final /* synthetic */ ak b;
        private final Runnable c;
        private b d;
        private b e;
        private boolean f;

        b(ak akVar, Runnable runnable) {
            this.b = akVar;
            this.c = runnable;
        }

        public boolean a() {
            synchronized (this.b.c) {
                if (b()) {
                    return false;
                }
                this.b.d = a(this.b.d);
                return true;
            }
        }

        public void c() {
            synchronized (this.b.c) {
                if (!b()) {
                    this.b.d = a(this.b.d);
                    this.b.d = a(this.b.d, true);
                }
            }
        }

        public boolean b() {
            return this.f;
        }

        Runnable d() {
            return this.c;
        }

        b e() {
            return this.d;
        }

        void a(boolean z) {
            this.f = z;
        }

        b a(b bVar, boolean z) {
            if (!a && this.d != null) {
                throw new AssertionError();
            } else if (a || this.e == null) {
                b bVar2;
                if (bVar == null) {
                    this.e = this;
                    this.d = this;
                    bVar2 = this;
                } else {
                    this.d = bVar;
                    this.e = bVar.e;
                    bVar2 = this.d;
                    this.e.d = this;
                    bVar2.e = this;
                    bVar2 = bVar;
                }
                if (z) {
                    return this;
                }
                return bVar2;
            } else {
                throw new AssertionError();
            }
        }

        b a(b bVar) {
            if (!a && this.d == null) {
                throw new AssertionError();
            } else if (a || this.e != null) {
                if (bVar == this) {
                    if (this.d == this) {
                        bVar = null;
                    } else {
                        bVar = this.d;
                    }
                }
                this.d.e = this.e;
                this.e.d = this.d;
                this.e = null;
                this.d = null;
                return bVar;
            } else {
                throw new AssertionError();
            }
        }

        void b(boolean z) {
            if (!a && this.e.d != this) {
                throw new AssertionError();
            } else if (!a && this.d.e != this) {
                throw new AssertionError();
            } else if (!a && b() != z) {
                throw new AssertionError();
            }
        }
    }

    public ak() {
        this(8);
    }

    public ak(int i) {
        this(i, o.f());
    }

    public ak(int i, Executor executor) {
        this.c = new Object();
        this.g = null;
        this.h = 0;
        this.e = i;
        this.f = executor;
    }

    public a a(Runnable runnable) {
        return a(runnable, true);
    }

    public a a(Runnable runnable, boolean z) {
        a bVar = new b(this, runnable);
        synchronized (this.c) {
            this.d = bVar.a(this.d, z);
        }
        b();
        return bVar;
    }

    public void a() {
        synchronized (this.c) {
            int i = 0;
            if (this.g != null) {
                b bVar = this.g;
                do {
                    bVar.b(true);
                    i++;
                    bVar = bVar.e();
                } while (bVar != this.g);
            }
            if (b || this.h == r0) {
            } else {
                throw new AssertionError();
            }
        }
    }

    private void b() {
        a(null);
    }

    private void a(b bVar) {
        b bVar2 = null;
        synchronized (this.c) {
            if (bVar != null) {
                this.g = bVar.a(this.g);
                this.h--;
            }
            if (this.h < this.e) {
                bVar2 = this.d;
                if (bVar2 != null) {
                    this.d = bVar2.a(this.d);
                    this.g = bVar2.a(this.g, false);
                    this.h++;
                    bVar2.a(true);
                }
            }
        }
        if (bVar2 != null) {
            b(bVar2);
        }
    }

    private void b(final b bVar) {
        this.f.execute(new Runnable(this) {
            final /* synthetic */ ak b;

            public void run() {
                try {
                    bVar.d().run();
                } finally {
                    this.b.a(bVar);
                }
            }
        });
    }
}
