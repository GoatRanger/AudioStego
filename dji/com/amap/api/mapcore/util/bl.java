package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class bl extends Thread {
    private Context a;
    private bx b;

    public bl(Context context) {
        this.a = context;
        this.b = bx.a(context);
    }

    public void run() {
        a();
    }

    private void a() {
        ArrayList a;
        Object obj;
        ArrayList arrayList = new ArrayList();
        ArrayList a2 = this.b.a();
        if (a2.size() < 1) {
            a = a(this.a);
            obj = 1;
        } else {
            a = a2;
            obj = null;
        }
        Iterator it = a.iterator();
        while (it.hasNext()) {
            bs bsVar = (bs) it.next();
            if (!(bsVar == null || bsVar.e() == null || bsVar.g().length() < 1)) {
                if (Thread.interrupted()) {
                    break;
                }
                if (obj != null) {
                    arrayList.add(bsVar);
                }
                if ((bsVar.l == 4 || bsVar.l == 7) && !a(bsVar.g())) {
                    bsVar.b();
                    try {
                        cf.a(bsVar.g(), this.a);
                    } catch (Exception e) {
                    }
                    arrayList.add(bsVar);
                }
            }
        }
        bi a3 = bi.a(this.a);
        if (a3 != null) {
            a3.a(arrayList);
        }
    }

    private ArrayList<bs> a(Context context) {
        ArrayList<bs> arrayList = new ArrayList();
        File file = new File(dj.b(context));
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.getName().endsWith(".zip.tmp.dt")) {
                        bs a = a(file2);
                        if (!(a == null || a.e() == null)) {
                            arrayList.add(a);
                            this.b.a(a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private bs a(File file) {
        String a = dj.a(file);
        bs bsVar = new bs();
        bsVar.b(a);
        return bsVar;
    }

    private boolean a(String str) {
        List<String> a = this.b.a(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dj.a(this.a));
        stringBuilder.append("vmap/");
        int length = stringBuilder.length();
        for (String replace : a) {
            stringBuilder.replace(length, stringBuilder.length(), replace);
            if (!new File(stringBuilder.toString()).exists()) {
                return false;
            }
        }
        return true;
    }

    public void destroy() {
        this.a = null;
        this.b = null;
    }
}
