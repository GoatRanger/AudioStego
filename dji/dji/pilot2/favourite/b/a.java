package dji.pilot2.favourite.b;

import android.content.Context;
import com.dji.frame.c.c;
import com.google.api.client.util.Lists;
import dji.pilot2.nativeexplore.b.h;
import dji.pilot2.nativeexplore.b.i;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.ExploreListModel;
import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.thirdparty.afinal.f.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class a implements h {
    private final int a = 21;
    private Context b;
    private List<ExploreItem> c;
    private List<ExploreItem> d;
    private i e;
    private String[] f;
    private int[] g;
    private boolean[] h;
    private int i;
    private int j;
    private int k;
    private String l;
    private String m;
    private Map<String, String> n;

    public a(Context context, String[] strArr, Map<String, String> map, String str, String str2) {
        this.b = context;
        this.i = 21;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.j = 0;
        this.f = strArr;
        this.l = str;
        this.m = str2;
        this.n = map;
        this.k = 1;
        this.h = new boolean[strArr.length];
        this.g = new int[strArr.length];
        for (int i = 0; i < this.g.length; i++) {
            this.g[i] = this.k;
            this.h[i] = false;
        }
    }

    public void a(i iVar) {
        this.e = iVar;
    }

    public void a(int i) {
        this.j = i;
    }

    public void b(int i) {
        this.i = i;
    }

    public void a(String str, String str2) {
        this.n.put(str, str2);
    }

    public void a() {
        this.c.clear();
        this.d.clear();
    }

    public void c() {
        a(0, this.k, false);
    }

    public void d() {
        a(0, this.g[0], true);
    }

    private void a(int i, int i2, boolean z) {
        a(i, i2, this.i, z);
    }

    public void a(int i, int i2, int i3, final boolean z) {
        String str = this.f[i];
        if (str != null && !str.equals("")) {
            b bVar = new b(this.n);
            bVar.a(this.l, Integer.toString(i2));
            bVar.a(this.m, Integer.toString(i3));
            dji.thirdparty.afinal.f.a anonymousClass1 = new dji.pilot2.nativeexplore.b.d.a(this) {
                final /* synthetic */ a b;

                public void a(String str) {
                    ExploreListModel exploreListModel = null;
                    if (str != null) {
                        exploreListModel = (ExploreListModel) com.dji.frame.c.h.b(str, ExploreListModel.class);
                        if (exploreListModel != null && exploreListModel.status == 0) {
                            if (exploreListModel.items != null) {
                                if (exploreListModel.items.size() < b()) {
                                    this.b.h[d()] = true;
                                }
                                for (ExploreItemModel exploreItem : exploreListModel.items) {
                                    this.b.d.add(new ExploreItem(exploreItem));
                                }
                            }
                            if (a() == this.b.g[d()] && exploreListModel.items.size() == this.b.i) {
                                int[] c = this.b.g;
                                int d = d();
                                c[d] = c[d] + 1;
                            }
                            if (c() && d() + 1 < this.b.f.length) {
                                this.b.a(d() + 1, this.b.g[d() + 1], b(), c());
                            } else if (d() + 1 < this.b.f.length) {
                                this.b.a(d() + 1, a(), b(), c());
                            }
                        }
                    }
                    if (exploreListModel == null || exploreListModel.status != 0) {
                        this.b.e.a(this.b.j);
                    } else if (d() + 1 == this.b.f.length) {
                        boolean z;
                        if (z) {
                            this.b.a(false);
                        } else {
                            this.b.a(true);
                        }
                        this.b.d.clear();
                        if (a() == this.b.k) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.b.e.a(this.b.j, z, this.b.b());
                    }
                }

                public void a(Throwable th, int i, String str) {
                    this.b.e.a(this.b.j);
                }
            };
            anonymousClass1.c(i);
            anonymousClass1.a(i2);
            anonymousClass1.b(i3);
            anonymousClass1.b(z);
            c.b(this.b).a(str, bVar, anonymousClass1);
        }
    }

    private synchronized void a(boolean z) {
        int size;
        ExploreItem exploreItem;
        Object obj;
        if (z) {
            this.c.clear();
            for (size = this.d.size() - 1; size >= 0; size--) {
                exploreItem = (ExploreItem) this.d.get(size);
                for (ExploreItem exploreItem2 : this.c) {
                    if (exploreItem2.id != null && exploreItem2.id.equals(exploreItem.id)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.c.add(0, exploreItem);
                }
            }
        } else {
            for (size = 0; size < this.d.size(); size++) {
                exploreItem = (ExploreItem) this.d.get(size);
                for (ExploreItem exploreItem22 : this.c) {
                    if (exploreItem22.id != null && exploreItem22.id.equals(exploreItem.id)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.c.add(exploreItem);
                }
            }
        }
        this.d.clear();
    }

    public synchronized List<ExploreItem> e() {
        return Lists.newArrayList(this.c);
    }

    public boolean b() {
        for (boolean z : this.h) {
            if (!z) {
                return false;
            }
        }
        return true;
    }
}
