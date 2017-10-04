package dji.pilot2.multimoment.template;

import android.content.Context;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.pilot2.template.DealedFilterConf;
import dji.pilot2.utils.n;
import dji.pilot2.videolib.VideoLibWrapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class c {
    private static c f = new c();
    private int a;
    private int b;
    private List<a> c;
    private List<dji.pilot2.template.c> d = null;
    private List<dji.pilot2.template.c> e = null;

    public static class a {
        List<Integer> a;
        List<Integer> b;
    }

    private c() {
    }

    public static c getInstance() {
        return f;
    }

    public int a() {
        return this.d.size();
    }

    public int a(int i, int i2, int i3, Context context, List<Integer> list, List<Integer> list2) {
        if (list == null || list2 == null) {
            return 1;
        }
        int i4;
        if (!(this.c != null && this.a == i2 && this.b == i3)) {
            this.c = new ArrayList();
            List a = a(context);
            for (i4 = 0; i4 < a.size(); i4++) {
                dji.pilot2.template.c cVar = (dji.pilot2.template.c) a.get(i4);
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                cVar.a(i2, i3, arrayList, arrayList2);
                a aVar = new a();
                aVar.a = arrayList;
                aVar.b = arrayList2;
                this.c.add(aVar);
            }
            this.a = i2;
            this.b = i3;
        }
        if (this.c.size() < i) {
            DJILogHelper.getInstance().LOGI("bob", "err getSingleTemplate templatenum = " + i + "mSingleTemplatesInfo.size() =  " + this.c.size());
            return 1;
        }
        aVar = (a) this.c.get(i);
        for (i4 = 0; i4 < aVar.a.size(); i4++) {
            list.add(aVar.a.get(i4));
            list2.add(aVar.b.get(i4));
        }
        return 0;
    }

    public synchronized dji.pilot2.template.c a(int i, Context context) {
        dji.pilot2.template.c cVar;
        List a = a(context);
        for (int i2 = 0; i2 < a.size(); i2++) {
            cVar = (dji.pilot2.template.c) a.get(i2);
            if (i == cVar.d()) {
                break;
            }
        }
        cVar = null;
        return cVar;
    }

    public synchronized List<dji.pilot2.template.c> a(Context context) {
        if (this.d == null) {
            String str;
            this.d = new ArrayList();
            File file = new File(n.t(context));
            String language = Locale.getDefault().getLanguage();
            if ("zh".equals(language)) {
                str = "cn";
            } else {
                str = "en";
            }
            File[] listFiles = file.listFiles(new FilenameFilter(this) {
                final /* synthetic */ c b;

                public boolean accept(File file, String str) {
                    return str.endsWith(str + ".cfg");
                }
            });
            Log.i("zhangchen", "file size:" + listFiles.length);
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                try {
                    dji.pilot2.template.c cVar = new dji.pilot2.template.c(context, new FileInputStream(file2));
                    if ("zh".equals(language) || cVar.d() > 1000) {
                        this.d.add(cVar);
                        Log.i("zhangchen", "add:" + cVar.d());
                        i++;
                    } else {
                        i++;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    if (file2.exists()) {
                        file2.delete();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    if (file2.exists()) {
                        file2.delete();
                    }
                }
            }
            Collections.sort(this.d, new Comparator<dji.pilot2.template.c>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((dji.pilot2.template.c) obj, (dji.pilot2.template.c) obj2);
                }

                public int a(dji.pilot2.template.c cVar, dji.pilot2.template.c cVar2) {
                    if (cVar.d() < cVar2.d()) {
                        return -1;
                    }
                    return 1;
                }
            });
            for (int i2 = 0; i2 < this.d.size(); i2++) {
                dji.pilot2.template.c cVar2 = (dji.pilot2.template.c) this.d.get(i2);
                List b = cVar2.b();
                for (int i3 = 0; i3 < b.size(); i3++) {
                    VideoLibWrapper.nativeSetSingleTemplateConf(cVar2.d(), (DealedFilterConf) b.get(i3));
                }
            }
        }
        return this.d;
    }

    public void b() {
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); i++) {
                this.d.add(this.e.get(i));
            }
            this.e = null;
            Collections.sort(this.d, new Comparator<dji.pilot2.template.c>(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((dji.pilot2.template.c) obj, (dji.pilot2.template.c) obj2);
                }

                public int a(dji.pilot2.template.c cVar, dji.pilot2.template.c cVar2) {
                    if (cVar.d() < cVar2.d()) {
                        return -1;
                    }
                    return 1;
                }
            });
        }
    }

    public synchronized void a(Context context, String str) {
        if (str.endsWith(".cfg")) {
            if (this.e == null) {
                this.e = new ArrayList();
            }
            File file = new File(str);
            try {
                dji.pilot2.template.c cVar = new dji.pilot2.template.c(context, new FileInputStream(file));
                this.e.add(cVar);
                List b = cVar.b();
                for (int i = 0; i < b.size(); i++) {
                    VideoLibWrapper.nativeSetSingleTemplateConf(cVar.d(), (DealedFilterConf) b.get(i));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                if (file.exists()) {
                    file.delete();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }
}
