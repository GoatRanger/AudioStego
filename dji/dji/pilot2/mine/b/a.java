package dji.pilot2.mine.b;

import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.pilot.usercenter.b.f;
import dji.pilot2.mine.e.d;
import dji.pilot2.nativeexplore.model.ExploreListModel;
import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.pilot2.utils.k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class a {
    public static final String a = "ArtworkController";
    public static final String b = "photos";
    public static final String c = "videos";
    public static final String d = "api/users/";
    public static final String e = "/home/?";
    public static final String f = "page=";
    public static final String g = "page_size=";
    private static volatile a o = null;
    private static a q = new a() {
        public void a() {
        }

        public void b() {
        }
    };
    public final List<dji.pilot2.mine.e.a> h;
    public final List<dji.pilot2.mine.e.a> i;
    public final List<dji.pilot2.mine.e.a> j;
    private int k;
    private int l;
    private String m;
    private boolean n;
    private List<a> p;

    public interface a {
        void a();

        void b();
    }

    private class b implements Runnable {
        a a;
        boolean b;
        final /* synthetic */ a c;

        public b(a aVar, a aVar2, boolean z) {
            this.c = aVar;
            this.a = aVar2;
            this.b = z;
        }

        public void run() {
            if (this.a != null) {
                if (this.b) {
                    this.a.a();
                } else {
                    this.a.b();
                }
            }
            for (a aVar : this.c.p) {
                if (this.b) {
                    aVar.a();
                } else {
                    aVar.b();
                }
            }
        }
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (o == null) {
                o = new a();
            }
            if (!f.getInstance().i().equals(o.m)) {
                o.m = f.getInstance().i();
            }
            aVar = o;
        }
        return aVar;
    }

    public a() {
        this.k = 1;
        this.n = false;
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.n = false;
        this.m = f.getInstance().i();
        this.p = new ArrayList();
    }

    public a(String str) {
        this();
        this.m = str;
    }

    public void a(a aVar) {
        if (!this.p.contains(aVar)) {
            this.p.add(aVar);
        }
    }

    public void b(a aVar) {
        this.p.remove(aVar);
    }

    public synchronized void c(a aVar) {
        if (this.m == null) {
            this.m = f.getInstance().i();
        }
        this.n = true;
        a(this.m, aVar);
    }

    public synchronized void d(a aVar) {
        if (this.m == null) {
            this.m = f.getInstance().i();
        }
        this.n = true;
        a(this.m, 1, 20, aVar);
    }

    public synchronized void a(String str) {
        this.n = true;
        a(str, this.k, 20, q);
    }

    public synchronized void a(String str, a aVar) {
        this.n = true;
        a(str, this.k, 20, aVar);
    }

    public synchronized void a(String str, final int i, final int i2, final a aVar) {
        this.n = true;
        c.b(dji.pilot2.b.a.a()).a(a(str, i, i2), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ a d;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(final String str) {
                new Thread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        ExploreListModel exploreListModel = (ExploreListModel) h.b(str, ExploreListModel.class);
                        if (exploreListModel == null || exploreListModel.items == null) {
                            dji.pilot2.b.a.b().post(new b(this.b.d, aVar, false));
                        } else {
                            this.b.d.l = exploreListModel.total_count;
                            List<ExploreItemModel> list = exploreListModel.items;
                            List arrayList = new ArrayList();
                            if (exploreListModel.items.size() >= i2 && i == this.b.d.k) {
                                this.b.d.k = this.b.d.k + 1;
                            }
                            for (ExploreItemModel exploreItemModel : list) {
                                if ("videos".equals(exploreItemModel.type)) {
                                    arrayList.add(new d(exploreItemModel));
                                } else if ("photos".equals(exploreItemModel.type)) {
                                    arrayList.add(new dji.pilot2.mine.e.c(exploreItemModel));
                                }
                            }
                            this.b.d.a(arrayList);
                            dji.pilot2.b.a.b().post(new b(this.b.d, aVar, true));
                        }
                        this.b.d.n = false;
                    }
                }).start();
            }

            public void a(Throwable th, int i, String str) {
                dji.pilot2.b.a.b().post(new b(this.d, aVar, false));
                this.d.n = false;
            }
        });
    }

    private boolean a(List<dji.pilot2.mine.e.a> list, dji.pilot2.mine.e.a aVar) {
        for (dji.pilot2.mine.e.a aVar2 : list) {
            if (aVar2.e() != null && aVar2.e().equals(aVar.e())) {
                if (!(aVar2.f() == null || aVar2.f().equals(aVar.f()))) {
                    aVar2.c(aVar.f());
                }
                if (!(aVar2.g() == null || aVar2.g().equals(aVar.g()))) {
                    aVar2.d(aVar.g());
                }
                if (!(aVar2.d() == null || aVar2.d().equals(aVar.d()))) {
                    aVar2.a(aVar.d());
                }
                if ((aVar2 instanceof d) && (aVar instanceof d)) {
                    ((d) aVar2).a(((d) aVar).i());
                    ((d) aVar2).r = ((d) aVar).r;
                }
                return true;
            }
        }
        return false;
    }

    private synchronized void a(List<dji.pilot2.mine.e.a> list) {
        if (list != null) {
            for (dji.pilot2.mine.e.a aVar : list) {
                if (aVar instanceof dji.pilot2.mine.e.c) {
                    if (!a(this.j, aVar)) {
                        this.j.add(aVar);
                    }
                } else if ((aVar instanceof d) && !a(this.i, aVar)) {
                    this.i.add(aVar);
                }
                if (!a(this.h, aVar)) {
                    this.h.add(aVar);
                }
            }
            Collections.sort(this.h);
            Collections.sort(this.i);
            Collections.sort(this.j);
        }
    }

    public int a() {
        return this.l;
    }

    public void a(int i) {
        this.l = i;
    }

    private String a(String str, int i, int i2) {
        if (f.getInstance().c()) {
            return k.h() + d + str + e + f + i + com.alipay.sdk.h.a.b + g + i2 + "&token=" + f.getInstance().n();
        }
        return k.h() + d + str + e + f + i + com.alipay.sdk.h.a.b + g + i2;
    }

    public boolean b() {
        return this.n;
    }

    public synchronized void c() {
        this.k = 1;
        this.h.clear();
        this.i.clear();
        this.j.clear();
        this.l = 0;
    }
}
