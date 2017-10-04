package dji.pilot2.academy.a;

import android.content.Context;
import android.util.Log;
import com.dji.frame.c.c;
import dji.pilot2.academy.model.FrequentlyQuestionModel;
import dji.pilot2.academy.model.FrequentlyQuestionModel.Data2;
import dji.pilot2.academy.model.FrequentlyQuestionModel.Faq;
import dji.pilot2.nativeexplore.b.h;
import dji.pilot2.nativeexplore.b.i;
import dji.thirdparty.afinal.f.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a implements h {
    private static final int b = 20;
    public List<Faq> a;
    private Context c;
    private String[] d;
    private Map<String, String> e;
    private String f;
    private String g;
    private int[] h;
    private boolean[] i;
    private int j;
    private int k;
    private List<Faq> l;
    private i m;

    private abstract class a extends dji.thirdparty.afinal.f.a<String> {
        private int a;
        final /* synthetic */ a b;
        private int c;
        private int d;
        private boolean e;

        private a(a aVar) {
            this.b = aVar;
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

    public a(Context context, String[] strArr, Map<String, String> map, String str, String str2) {
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
                final /* synthetic */ a a;

                {
                    this.a = r2;
                }

                public void a(boolean z) {
                    Log.i("rxq", "frequently q onStart: ");
                }

                public void a(long j, long j2) {
                    Log.i("rxq", "frequently q onLoading");
                }

                public void a(String str) {
                    a.a(str);
                    FrequentlyQuestionModel frequentlyQuestionModel = null;
                    if (str != null) {
                        frequentlyQuestionModel = (FrequentlyQuestionModel) com.dji.frame.c.h.b(str, FrequentlyQuestionModel.class);
                        if (frequentlyQuestionModel != null && frequentlyQuestionModel.result == 0) {
                            for (Data2 data2 : frequentlyQuestionModel.data.data) {
                                for (Faq faq : data2.faq) {
                                    Log.i("rxq", "frequently q suc faq" + faq.title);
                                    this.a.l.add(faq);
                                }
                            }
                        }
                    }
                    if (frequentlyQuestionModel == null || frequentlyQuestionModel.result != 0) {
                        this.a.m.a(this.a.k);
                        return;
                    }
                    this.a.a(this.a.l);
                    this.a.l.clear();
                    this.a.m.a(this.a.k, true, this.a.b());
                }

                public void a(Throwable th, int i, String str) {
                    Log.i("rxq", "frequently q onFailure");
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

    public static void a(String str) {
        String trim = str.trim();
        int i = 0;
        while (i < trim.length()) {
            String substring;
            if (trim.length() <= i + 4000) {
                substring = trim.substring(i);
            } else {
                substring = trim.substring(i, i + 4000);
            }
            i += 4000;
            Log.i("rxq", substring.trim());
        }
    }

    private synchronized void a(List<Faq> list) {
        for (int i = 0; i < list.size(); i++) {
            Object obj;
            Faq faq = (Faq) list.get(i);
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                if (((Faq) this.a.get(i2)).title.equals(faq.title)) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj == null) {
                this.a.add(faq);
            }
        }
    }
}
