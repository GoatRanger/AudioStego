package com.flurry.sdk;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class iz {
    protected final String a;
    Set<String> b = new HashSet();
    jb c;
    protected String d = "defaultDataKey_";
    private ii<hs> e = new ii<hs>(this) {
        final /* synthetic */ iz a;

        {
            this.a = r1;
        }

        public void a(hs hsVar) {
            in.a(4, this.a.a, "onNetworkStateChanged : isNetworkEnable = " + hsVar.a);
            if (hsVar.a) {
                this.a.e();
            }
        }
    };

    public interface a {
        void a();
    }

    protected abstract void a(byte[] bArr, String str, String str2);

    public iz(String str, String str2) {
        this.a = str2;
        ij.a().a("com.flurry.android.sdk.NetworkStateEvent", this.e);
        a(str);
    }

    protected void a(kb kbVar) {
        hz.a().b(kbVar);
    }

    protected void a(final String str) {
        a(new kb(this) {
            final /* synthetic */ iz b;

            public void a() {
                this.b.c = new jb(str);
            }
        });
    }

    public void b(byte[] bArr, String str, String str2) {
        a(bArr, str, str2, null);
    }

    public void a(byte[] bArr, String str, String str2, a aVar) {
        if (bArr == null || bArr.length == 0) {
            in.a(6, this.a, "Report that has to be sent is EMPTY or NULL");
            return;
        }
        c(bArr, str, str2);
        a(aVar);
    }

    public int d() {
        return this.b.size();
    }

    protected void c(final byte[] bArr, final String str, final String str2) {
        a(new kb(this) {
            final /* synthetic */ iz d;

            public void a() {
                this.d.d(bArr, str, str2);
            }
        });
    }

    protected void e() {
        a(null);
    }

    protected void a(final a aVar) {
        a(new kb(this) {
            final /* synthetic */ iz b;

            public void a() {
                this.b.g();
                if (aVar != null) {
                    aVar.a();
                }
            }
        });
    }

    protected boolean f() {
        return d() <= 5;
    }

    public String a(String str, String str2) {
        return this.d + str + "_" + str2;
    }

    protected void d(byte[] bArr, String str, String str2) {
        String a = a(str, str2);
        ja jaVar = new ja(bArr);
        String a2 = jaVar.a();
        new ig(hz.a().c().getFileStreamPath(ja.a(a2)), ".yflurrydatasenderblock.", 1, new jk<ja>(this) {
            final /* synthetic */ iz a;

            {
                this.a = r1;
            }

            public jh<ja> a(int i) {
                return new com.flurry.sdk.ja.a();
            }
        }).a(jaVar);
        in.a(5, this.a, "Saving Block File " + a2 + " at " + hz.a().c().getFileStreamPath(ja.a(a2)));
        this.c.a(jaVar, a);
    }

    protected void g() {
        if (ht.a().c()) {
            List<String> a = this.c.a();
            if (a == null || a.isEmpty()) {
                in.a(4, this.a, "No more reports to send.");
                return;
            }
            for (String str : a) {
                if (f()) {
                    List c = this.c.c(str);
                    in.a(4, this.a, "Number of not sent blocks = " + c.size());
                    for (int i = 0; i < c.size(); i++) {
                        String str2 = (String) c.get(i);
                        if (!this.b.contains(str2)) {
                            if (!f()) {
                                break;
                            }
                            ja jaVar = (ja) new ig(hz.a().c().getFileStreamPath(ja.a(str2)), ".yflurrydatasenderblock.", 1, new jk<ja>(this) {
                                final /* synthetic */ iz a;

                                {
                                    this.a = r1;
                                }

                                public jh<ja> a(int i) {
                                    return new com.flurry.sdk.ja.a();
                                }
                            }).a();
                            if (jaVar == null) {
                                in.a(6, this.a, "Internal ERROR! Cannot read!");
                                this.c.a(str2, str);
                            } else {
                                byte[] b = jaVar.b();
                                if (b == null || b.length == 0) {
                                    in.a(6, this.a, "Internal ERROR! Report is empty!");
                                    this.c.a(str2, str);
                                } else {
                                    in.a(5, this.a, "Reading block info " + str2);
                                    this.b.add(str2);
                                    a(b, str2, str);
                                }
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            return;
        }
        in.a(5, this.a, "Reports were not sent! No Internet connection!");
    }

    protected void a(final String str, final String str2, int i) {
        a(new kb(this) {
            final /* synthetic */ iz c;

            public void a() {
                if (!this.c.c.a(str, str2)) {
                    in.a(6, this.c.a, "Internal error. Block wasn't deleted with id = " + str);
                }
                if (!this.c.b.remove(str)) {
                    in.a(6, this.c.a, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    protected void b(final String str, String str2) {
        a(new kb(this) {
            final /* synthetic */ iz b;

            public void a() {
                if (!this.b.b.remove(str)) {
                    in.a(6, this.b.a, "Internal error. Block with id = " + str + " was not in progress state");
                }
            }
        });
    }

    protected void c(String str, String str2) {
        if (!this.c.a(str, str2)) {
            in.a(6, this.a, "Internal error. Block wasn't deleted with id = " + str);
        }
        if (!this.b.remove(str)) {
            in.a(6, this.a, "Internal error. Block with id = " + str + " was not in progress state");
        }
    }
}
