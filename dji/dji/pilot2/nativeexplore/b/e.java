package dji.pilot2.nativeexplore.b;

import android.content.Context;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.pilot2.nativeexplore.model.ExploreLikeListModel;
import dji.pilot2.nativeexplore.model.FollowListModel.AccountModel;
import dji.thirdparty.afinal.f.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class e implements h {
    private static final int b = 20;
    public List<AccountModel> a;
    private Context c;
    private String[] d;
    private Map<String, String> e;
    private String f;
    private String g;
    private int[] h;
    private boolean[] i;
    private int j;
    private int k;
    private List<AccountModel> l;
    private i m;

    private abstract class a extends dji.thirdparty.afinal.f.a<String> {
        private int a;
        final /* synthetic */ e b;
        private int c;
        private int d;
        private boolean e;

        private a(e eVar) {
            this.b = eVar;
        }

        public int a() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }

        public int b() {
            return this.d;
        }

        public void b(int i) {
            this.d = i;
        }

        public boolean c() {
            return this.e;
        }

        public void b(boolean z) {
            this.e = z;
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

    public e(Context context, String[] strArr, Map<String, String> map, String str, String str2) {
        this.c = context;
        this.d = strArr;
        this.e = map;
        this.f = str;
        this.g = str2;
        this.h = new int[strArr.length];
        this.i = new boolean[strArr.length];
        for (int i = 0; i < this.h.length; i++) {
            this.h[i] = 1;
        }
        this.j = 20;
        this.a = new ArrayList();
        this.l = new ArrayList();
        if (map == null) {
            this.e = new HashMap();
        }
    }

    public void a(i iVar) {
        this.m = iVar;
    }

    public void a(int i) {
        this.k = i;
    }

    public void b(int i) {
        this.j = i;
    }

    public void a(String str, String str2) {
        this.e.put(str, str2);
    }

    public void a() {
        this.a.clear();
        this.l.clear();
        for (int i = 0; i < this.h.length; i++) {
            this.h[i] = 0;
            this.i[i] = false;
        }
    }

    public boolean b() {
        for (boolean z : this.i) {
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public void c() {
        a(0, 1, false);
    }

    public void d() {
        a(0, this.h[0], true);
    }

    private void a(int i, int i2, boolean z) {
        a(i, i2, this.j, z);
    }

    private void a(int i, int i2, int i3, boolean z) {
        String str = this.d[i];
        if (str != null && !str.equals("")) {
            b bVar = new b(this.e);
            bVar.a(this.f, Integer.toString(i2));
            bVar.a(this.g, Integer.toString(i3));
            dji.thirdparty.afinal.f.a anonymousClass1 = new a(this) {
                final /* synthetic */ e a;

                {
                    this.a = r2;
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    ExploreLikeListModel exploreLikeListModel = null;
                    if (str != null) {
                        exploreLikeListModel = (ExploreLikeListModel) h.b(str, ExploreLikeListModel.class);
                        if (exploreLikeListModel != null && exploreLikeListModel.status == 0) {
                            if (exploreLikeListModel.accounts != null) {
                                if (((double) exploreLikeListModel.page) >= Math.ceil((((double) exploreLikeListModel.total_count) * 1.0d) / ((double) exploreLikeListModel.page_size))) {
                                    this.a.i[d()] = true;
                                }
                                for (AccountModel add : exploreLikeListModel.accounts) {
                                    this.a.l.add(add);
                                }
                                if (((double) exploreLikeListModel.page) <= Math.ceil((((double) exploreLikeListModel.total_count) * 1.0d) / ((double) exploreLikeListModel.page_size))) {
                                    int[] c = this.a.h;
                                    int d = d();
                                    c[d] = c[d] + 1;
                                }
                            }
                            if (c() && d() + 1 < this.a.d.length) {
                                this.a.a(d() + 1, this.a.h[d() + 1], b(), c());
                            } else if (d() + 1 < this.a.d.length) {
                                this.a.a(d() + 1, a(), b(), c());
                            }
                        }
                    }
                    if (exploreLikeListModel == null || exploreLikeListModel.status != 0) {
                        this.a.m.a(this.a.k);
                    } else if (d() + 1 == this.a.d.length) {
                        this.a.a(this.a.l);
                        this.a.l.clear();
                        boolean z = false;
                        if (a() == 1) {
                            z = true;
                        }
                        this.a.m.a(this.a.k, z, this.a.b());
                    }
                }

                public void a(Throwable th, int i, String str) {
                    this.a.m.a(this.a.k);
                }
            };
            anonymousClass1.c(i);
            anonymousClass1.a(i2);
            anonymousClass1.b(i3);
            anonymousClass1.b(z);
            c.b(this.c).a(str, bVar, anonymousClass1);
        }
    }

    private synchronized void a(List<AccountModel> list) {
        for (int i = 0; i < list.size(); i++) {
            Object obj;
            AccountModel accountModel = (AccountModel) list.get(i);
            int i2 = 0;
            while (i2 < this.a.size()) {
                if (this.a.get(i2) != null && ((AccountModel) this.a.get(i2)).id != null && accountModel != null && ((AccountModel) this.a.get(i2)).id.equals(accountModel.id)) {
                    obj = 1;
                    break;
                }
                i2++;
            }
            obj = null;
            if (obj == null) {
                this.a.add(accountModel);
            }
        }
    }
}
