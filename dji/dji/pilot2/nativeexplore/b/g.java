package dji.pilot2.nativeexplore.b;

import android.content.Context;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import com.google.api.client.util.Lists;
import dji.pilot2.nativeexplore.model.GuideListModel;
import dji.pilot2.nativeexplore.model.GuideListModel.GuideModel;
import dji.thirdparty.afinal.f.a;
import dji.thirdparty.afinal.f.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class g implements h {
    public List<GuideModel> a = new ArrayList();
    private Context b;
    private String[] c;
    private Map<String, String> d;
    private String e;
    private String f;
    private int[] g;
    private boolean[] h;
    private int i;
    private int j;
    private int k;
    private List<GuideModel> l = new ArrayList();
    private i m;

    public g(Context context, String[] strArr, Map<String, String> map, String str, String str2) {
        this.b = context;
        this.c = strArr;
        this.d = map;
        this.e = str;
        this.f = str2;
        this.k = 1;
        this.g = new int[strArr.length];
        this.h = new boolean[strArr.length];
        for (int i = 0; i < this.g.length; i++) {
            this.g[i] = this.k;
            this.h[i] = false;
        }
        this.i = 10;
    }

    public void a(i iVar) {
        this.m = iVar;
    }

    public void a(int i) {
        this.j = i;
    }

    public void b(int i) {
        this.i = i;
    }

    public void a(String str, String str2) {
        this.d.put(str, str2);
    }

    public void a() {
        this.a.clear();
        this.l.clear();
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
        String str = this.c[i];
        if (str != null && !str.equals("")) {
            b bVar = new b(this.d);
            bVar.a(this.e, Integer.toString(i2));
            bVar.a(this.f, Integer.toString(i3));
            a anonymousClass1 = new d.a(this) {
                final /* synthetic */ g b;

                public void a(String str) {
                    GuideListModel guideListModel = null;
                    if (str != null) {
                        guideListModel = (GuideListModel) h.b(str, GuideListModel.class);
                        if (guideListModel != null && guideListModel.status == 0) {
                            if (guideListModel.getItems() != null) {
                                if (guideListModel.getItems().size() < b() || guideListModel.getItems().size() > b()) {
                                    this.b.h[d()] = true;
                                }
                                for (GuideModel add : guideListModel.getItems()) {
                                    this.b.l.add(add);
                                }
                            }
                            if (a() == this.b.g[d()] && guideListModel.getItems().size() == this.b.i) {
                                int[] c = this.b.g;
                                int d = d();
                                c[d] = c[d] + 1;
                            }
                            if (c() && d() + 1 < this.b.c.length) {
                                this.b.a(d() + 1, this.b.g[d() + 1], b(), c());
                            } else if (d() + 1 < this.b.c.length) {
                                this.b.a(d() + 1, a(), b(), c());
                            }
                        }
                    }
                    if (guideListModel == null || guideListModel.status != 0) {
                        this.b.m.a(this.b.j);
                    } else if (d() + 1 == this.b.c.length) {
                        boolean z;
                        if (z) {
                            this.b.a(false);
                        } else {
                            this.b.a(true);
                        }
                        this.b.l.clear();
                        if (a() == this.b.k) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.b.m.a(this.b.j, z, this.b.b());
                    }
                }

                public void a(Throwable th, int i, String str) {
                    this.b.m.a(this.b.j);
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
        GuideModel guideModel;
        Object obj;
        if (z) {
            this.a.clear();
            for (size = this.l.size() - 1; size >= 0; size--) {
                guideModel = (GuideModel) this.l.get(size);
                for (GuideModel guideModel2 : this.a) {
                    if (guideModel2.id != null && guideModel2.id.equals(guideModel.id)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.a.add(0, guideModel);
                }
            }
        } else {
            for (size = 0; size < this.l.size(); size++) {
                guideModel = (GuideModel) this.l.get(size);
                for (GuideModel guideModel22 : this.a) {
                    if (guideModel22.id != null && guideModel22.id.equals(guideModel.id)) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    this.a.add(guideModel);
                }
            }
        }
        this.l.clear();
    }

    public synchronized List<GuideModel> e() {
        return Lists.newArrayList(this.a);
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
