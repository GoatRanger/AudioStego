package dji.pilot2.nativeexplore.b;

import android.content.Context;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.objects.g;
import dji.pilot2.nativeexplore.model.ExploreItem;
import dji.pilot2.nativeexplore.model.ExploreListModel;
import dji.pilot2.nativeexplore.model.ExploreListModel.ExploreItemModel;
import dji.thirdparty.afinal.f.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class d implements h {
    public static final int a = 2;
    private static final int c = 12;
    private static final int d = 50;
    private static final String e = "controller_cache_first_page";
    private static final String f = "controller_cache_first_page_content_";
    public List<ExploreItem> b;
    private Context g;
    private String[] h;
    private Map<String, String> i;
    private String j;
    private String k;
    private int[] l;
    private boolean[] m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private boolean r = true;
    private List<ExploreItem> s;
    private i t;

    public static abstract class a extends dji.thirdparty.afinal.f.a<String> {
        private int a;
        private int b;
        private int c;
        private boolean d;

        public int a() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public int b() {
            return this.c;
        }

        public void b(int i) {
            this.c = i;
        }

        public boolean c() {
            return this.d;
        }

        public void b(boolean z) {
            this.d = z;
        }

        public void a(boolean z) {
        }

        public void a(long j, long j2) {
        }

        public int d() {
            return this.a;
        }

        public void c(int i) {
            this.a = i;
        }
    }

    public d(Context context, String[] strArr, Map<String, String> map, String str, String str2) {
        this.g = context;
        this.h = strArr;
        this.i = map;
        this.j = str;
        this.k = str2;
        this.l = new int[strArr.length];
        this.m = new boolean[strArr.length];
        this.p = 1;
        for (int i = 0; i < this.l.length; i++) {
            this.l[i] = this.p;
            this.m[i] = false;
        }
        this.n = 12;
        this.b = new ArrayList();
        this.s = new ArrayList();
    }

    public void a(i iVar) {
        this.t = iVar;
    }

    public void a(int i) {
        this.o = i;
    }

    public void a(boolean z) {
        this.q = z;
        this.p = 1;
        if (this.q) {
            this.p = new Random(System.currentTimeMillis()).nextInt(51);
        }
        for (int i = 0; i < this.l.length; i++) {
            this.l[i] = this.p;
        }
    }

    public boolean b() {
        for (boolean z : this.m) {
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public void b(int i) {
        this.n = i;
    }

    public void a(String str, String str2) {
        this.i.put(str, str2);
    }

    public void a() {
        this.b.clear();
        this.s.clear();
        if (this.q) {
            a(true);
        }
        for (int i = 0; i < this.l.length; i++) {
            this.l[i] = this.p;
            this.m[i] = false;
        }
    }

    public boolean a(String str) {
        int i = 0;
        while (i < this.b.size()) {
            if (this.b.get(i) == null || !((ExploreItem) this.b.get(i)).id.equals(str)) {
                i++;
            } else {
                this.b.remove(i);
                return true;
            }
        }
        return false;
    }

    public void c() {
        if (this.r) {
            this.r = false;
            if (!this.q || DJINetWorkReceiver.b(this.g)) {
                if (this.q) {
                    a(true);
                }
                a(0, this.p, false);
                return;
            }
            String[] strArr = new String[this.h.length];
            this.p = g.b(this.g, e, 1);
            for (int i = 0; i < this.h.length; i++) {
                strArr[i] = g.b(this.g, f + i, "");
                ExploreListModel exploreListModel = (ExploreListModel) h.b(strArr[i], ExploreListModel.class);
                if (!(exploreListModel == null || exploreListModel.status != 0 || exploreListModel.items == null)) {
                    for (ExploreItemModel exploreItemModel : exploreListModel.items) {
                        if (this.o == 2) {
                            exploreItemModel.is_follow = true;
                        }
                        this.s.add(new ExploreItem(exploreItemModel));
                    }
                }
            }
            a(this.s, false);
            this.t.a(this.o, true, b());
            return;
        }
        if (this.q) {
            a(true);
        }
        a(0, this.p, false);
    }

    public void d() {
        a(0, this.l[0], true);
    }

    private void a(int i, int i2, boolean z) {
        a(i, i2, this.n, z);
    }

    private void a(int i, int i2, int i3, final boolean z) {
        String str = this.h[i];
        if (str != null && !str.equals("")) {
            b bVar = new b(this.i);
            bVar.a(this.j, Integer.toString(i2));
            bVar.a(this.k, Integer.toString(i3));
            dji.thirdparty.afinal.f.a anonymousClass1 = new a(this) {
                final /* synthetic */ d b;

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    ExploreListModel exploreListModel = null;
                    if (str != null) {
                        exploreListModel = (ExploreListModel) h.b(str, ExploreListModel.class);
                        if (exploreListModel != null && exploreListModel.status == 0) {
                            if (exploreListModel.items != null) {
                                if (exploreListModel.items.size() < b()) {
                                    this.b.m[d()] = true;
                                }
                                for (ExploreItemModel exploreItemModel : exploreListModel.items) {
                                    if (this.b.o == 2) {
                                        exploreItemModel.is_follow = true;
                                    }
                                    this.b.s.add(new ExploreItem(exploreItemModel));
                                }
                            }
                            if (exploreListModel.page == this.b.l[d()] && exploreListModel.items.size() == this.b.n) {
                                int[] d = this.b.l;
                                int d2 = d();
                                d[d2] = d[d2] + 1;
                            }
                            if (!c() && this.b.q) {
                                g.a(this.b.g, d.e, this.b.p);
                                g.a(this.b.g, d.f + d(), str);
                            }
                            if (c() && d() + 1 < this.b.h.length) {
                                this.b.a(d() + 1, this.b.l[d() + 1], b(), c());
                            } else if (d() + 1 < this.b.h.length) {
                                this.b.a(d() + 1, a(), b(), c());
                            }
                        }
                    }
                    if (exploreListModel == null || exploreListModel.status != 0) {
                        this.b.t.a(this.b.o);
                    } else if (d() + 1 == this.b.h.length) {
                        boolean z;
                        if (z) {
                            this.b.a(this.b.s, false);
                        } else {
                            this.b.a(this.b.s, true);
                        }
                        this.b.s.clear();
                        if (a() == this.b.p) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.b.t.a(this.b.o, z, this.b.b());
                    }
                }

                public void a(Throwable th, int i, String str) {
                    this.b.t.a(this.b.o);
                }
            };
            anonymousClass1.c(i);
            anonymousClass1.a(i2);
            anonymousClass1.b(i3);
            anonymousClass1.b(z);
            c.b(this.g).a(str, bVar, anonymousClass1);
        }
    }

    private synchronized void a(List<ExploreItem> list, boolean z) {
        if (z) {
            this.b.clear();
        }
        int size;
        ExploreItem exploreItem;
        int i;
        Object obj;
        if (z) {
            for (size = list.size() - 1; size >= 0; size--) {
                exploreItem = (ExploreItem) list.get(size);
                i = 0;
                while (i < this.b.size()) {
                    if (this.b.get(i) != null && ((ExploreItem) this.b.get(i)).id != null && exploreItem != null && ((ExploreItem) this.b.get(i)).id.equals(exploreItem.id)) {
                        obj = 1;
                        break;
                    }
                    i++;
                }
                obj = null;
                if (obj == null) {
                    if (z) {
                        this.b.add(0, exploreItem);
                    } else {
                        this.b.add(exploreItem);
                    }
                }
            }
        } else {
            for (size = 0; size < list.size(); size++) {
                exploreItem = (ExploreItem) list.get(size);
                i = 0;
                while (i < this.b.size()) {
                    if (this.b.get(i) != null && ((ExploreItem) this.b.get(i)).id != null && exploreItem != null && ((ExploreItem) this.b.get(i)).id.equals(exploreItem.id)) {
                        obj = 1;
                        break;
                    }
                    i++;
                }
                obj = null;
                if (obj == null) {
                    if (z) {
                        this.b.add(0, exploreItem);
                    } else {
                        this.b.add(exploreItem);
                    }
                }
            }
        }
    }
}
