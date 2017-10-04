package com.flurry.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class iy<ReportInfo extends ix> {
    private static final String a = iy.class.getSimpleName();
    private final ig<List<ReportInfo>> b;
    private final List<ReportInfo> c = new ArrayList();
    private boolean d;
    private int e;
    private long f;
    private final Runnable g = new kb(this) {
        final /* synthetic */ iy a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.b();
        }
    };
    private final ii<hs> h = new ii<hs>(this) {
        final /* synthetic */ iy a;

        {
            this.a = r1;
        }

        public void a(hs hsVar) {
            if (hsVar.a) {
                this.a.b();
            }
        }
    };

    protected abstract ig<List<ReportInfo>> a();

    protected abstract void a(ReportInfo reportInfo);

    public iy() {
        ij.a().a("com.flurry.android.sdk.NetworkStateEvent", this.h);
        this.b = a();
        this.f = 10000;
        this.e = -1;
        hz.a().b(new kb(this) {
            final /* synthetic */ iy a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.a(this.a.c);
                this.a.b();
            }
        });
    }

    public void c() {
        hz.a().c(this.g);
        i();
    }

    public void d() {
        this.d = true;
    }

    public void e() {
        this.d = false;
        hz.a().b(new kb(this) {
            final /* synthetic */ iy a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.b();
            }
        });
    }

    public synchronized void b(ReportInfo reportInfo) {
        if (reportInfo != null) {
            this.c.add(reportInfo);
            hz.a().b(new kb(this) {
                final /* synthetic */ iy a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.b();
                }
            });
        }
    }

    protected synchronized void c(ReportInfo reportInfo) {
        reportInfo.a(true);
        hz.a().b(new kb(this) {
            final /* synthetic */ iy a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.f();
            }
        });
    }

    protected synchronized void d(ReportInfo reportInfo) {
        reportInfo.k();
        hz.a().b(new kb(this) {
            final /* synthetic */ iy a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.f();
            }
        });
    }

    private synchronized void b() {
        if (!this.d) {
            if (this.e >= 0) {
                in.a(3, a, "Transmit is in progress");
            } else {
                h();
                if (this.c.isEmpty()) {
                    this.f = 10000;
                    this.e = -1;
                } else {
                    this.e = 0;
                    hz.a().b(new kb(this) {
                        final /* synthetic */ iy a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.f();
                        }
                    });
                }
            }
        }
    }

    private synchronized void f() {
        ix ixVar;
        jz.b();
        if (ht.a().c()) {
            while (this.e < this.c.size()) {
                List list = this.c;
                int i = this.e;
                this.e = i + 1;
                ixVar = (ix) list.get(i);
                if (!ixVar.g()) {
                    break;
                }
            }
            ixVar = null;
        } else {
            in.a(3, a, "Network is not available, aborting transmission");
            ixVar = null;
        }
        if (ixVar == null) {
            g();
        } else {
            a(ixVar);
        }
    }

    private synchronized void g() {
        h();
        b(this.c);
        if (this.d) {
            in.a(3, a, "Reporter paused");
            this.f = 10000;
        } else if (this.c.isEmpty()) {
            in.a(3, a, "All reports sent successfully");
            this.f = 10000;
        } else {
            this.f <<= 1;
            in.a(3, a, "One or more reports failed to send, backing off: " + this.f + "ms");
            hz.a().b(this.g, this.f);
        }
        this.e = -1;
    }

    private synchronized void a(List<ReportInfo> list) {
        jz.b();
        List list2 = (List) this.b.a();
        if (list2 != null) {
            list.addAll(list2);
        }
    }

    private synchronized void b(List<ReportInfo> list) {
        jz.b();
        this.b.a(new ArrayList(list));
    }

    private synchronized void h() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            ix ixVar = (ix) it.next();
            if (ixVar.g()) {
                in.a(3, a, "Expired: Url transmitted - " + ixVar.i() + " Attempts: " + ixVar.h());
                it.remove();
            } else if (ixVar.h() > ixVar.d()) {
                in.a(3, a, "Expired: Exceeded max no of attempts - " + ixVar.i() + " Attempts: " + ixVar.h());
                it.remove();
            } else if (System.currentTimeMillis() > ixVar.f() && ixVar.h() > 0) {
                in.a(3, a, "Expired: Time expired - " + ixVar.i() + " Attempts: " + ixVar.h());
                it.remove();
            }
        }
    }

    private void i() {
        ij.a().b("com.flurry.android.sdk.NetworkStateEvent", this.h);
    }
}
