package com.e;

import android.content.Context;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class be {
    private static be a = null;
    private Hashtable<String, ArrayList<a>> b = new Hashtable();
    private long c = 0;
    private boolean d = false;

    public class a {
        final /* synthetic */ be a;
        private AmapLoc b = null;
        private String c = null;

        protected a(be beVar) {
            this.a = beVar;
        }

        public AmapLoc a() {
            return this.b;
        }

        public void a(AmapLoc amapLoc) {
            this.b = amapLoc;
        }

        public void a(String str) {
            if (TextUtils.isEmpty(str)) {
                this.c = null;
            } else {
                this.c = str.replace("##", "#");
            }
        }

        public String b() {
            return this.c;
        }
    }

    private be() {
    }

    private synchronized a a(StringBuilder stringBuilder, String str) {
        a aVar;
        if (this.b.isEmpty() || TextUtils.isEmpty(stringBuilder)) {
            aVar = null;
        } else if (this.b.containsKey(str)) {
            Hashtable hashtable = new Hashtable();
            Hashtable hashtable2 = new Hashtable();
            Hashtable hashtable3 = new Hashtable();
            ArrayList arrayList = (ArrayList) this.b.get(str);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                a aVar2 = (a) arrayList.get(size);
                if (!TextUtils.isEmpty(aVar2.b())) {
                    Object obj;
                    String str2;
                    if (b(aVar2.b(), stringBuilder)) {
                        if (a(aVar2.b(), stringBuilder)) {
                            aVar = aVar2;
                            break;
                        }
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    a(aVar2.b(), hashtable);
                    a(stringBuilder.toString(), hashtable2);
                    hashtable3.clear();
                    for (String str22 : hashtable.keySet()) {
                        hashtable3.put(str22, "");
                    }
                    for (String str222 : hashtable2.keySet()) {
                        hashtable3.put(str222, "");
                    }
                    Set keySet = hashtable3.keySet();
                    double[] dArr = new double[keySet.size()];
                    double[] dArr2 = new double[keySet.size()];
                    Iterator it = keySet.iterator();
                    int i = 0;
                    while (it != null && it.hasNext()) {
                        str222 = (String) it.next();
                        dArr[i] = hashtable.containsKey(str222) ? 1.0d : 0.0d;
                        dArr2[i] = hashtable2.containsKey(str222) ? 1.0d : 0.0d;
                        i++;
                    }
                    keySet.clear();
                    double[] a = a(dArr, dArr2);
                    if (a[0] < 0.800000011920929d) {
                        if (a[1] < 0.618d) {
                            if (obj != null && a[0] >= 0.618d) {
                                aVar = aVar2;
                                break;
                            }
                        }
                        aVar = aVar2;
                        break;
                    }
                    aVar = aVar2;
                    break;
                }
            }
            aVar = null;
            hashtable.clear();
            hashtable2.clear();
            hashtable3.clear();
        } else {
            aVar = null;
        }
        return aVar;
    }

    public static synchronized be a() {
        be beVar;
        synchronized (be.class) {
            if (a == null) {
                a = new be();
            }
            beVar = a;
        }
        return beVar;
    }

    private void a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            hashtable.clear();
            for (Object obj : str.split("#")) {
                if (!(TextUtils.isEmpty(obj) || obj.contains("|"))) {
                    hashtable.put(obj, "");
                }
            }
        }
    }

    private double[] a(double[] dArr, double[] dArr2) {
        int i;
        double[] dArr3 = new double[3];
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        int i3 = 0;
        for (i = 0; i < dArr.length; i++) {
            d2 += dArr[i] * dArr[i];
            d3 += dArr2[i] * dArr2[i];
            d += dArr[i] * dArr2[i];
            if (dArr2[i] == 1.0d) {
                i2++;
                if (dArr[i] == 1.0d) {
                    i3++;
                }
            }
        }
        dArr3[0] = d / (Math.sqrt(d3) * Math.sqrt(d2));
        dArr3[1] = (1.0d * ((double) i3)) / ((double) i2);
        dArr3[2] = (double) i3;
        for (i = 0; i < dArr3.length - 1; i++) {
            if (dArr3[i] > 1.0d) {
                dArr3[i] = 1.0d;
            }
        }
        return dArr3;
    }

    private boolean b(String str, StringBuilder stringBuilder) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(stringBuilder)) {
            return false;
        }
        if (!str.contains(",access") || stringBuilder.indexOf(",access") == -1) {
            return false;
        }
        String[] split = str.split(",access");
        Object substring = split[0].contains("#") ? split[0].substring(split[0].lastIndexOf("#") + 1) : split[0];
        return TextUtils.isEmpty(substring) ? false : stringBuilder.toString().contains(substring + ",access");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.autonavi.aps.amapapi.model.AmapLoc a(java.lang.String r9, java.lang.StringBuilder r10, boolean r11) {
        /*
        r8 = this;
        r1 = 0;
        monitor-enter(r8);
        r0 = "gps";
        r0 = r9.contains(r0);	 Catch:{ all -> 0x00b1 }
        if (r0 != 0) goto L_0x0012;
    L_0x000a:
        if (r11 == 0) goto L_0x0012;
    L_0x000c:
        r0 = com.e.bo.p();	 Catch:{ all -> 0x00b1 }
        if (r0 != 0) goto L_0x0015;
    L_0x0012:
        r0 = r1;
    L_0x0013:
        monitor-exit(r8);
        return r0;
    L_0x0015:
        r0 = r8.b();	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x0020;
    L_0x001b:
        r8.c();	 Catch:{ all -> 0x00b1 }
        r0 = r1;
        goto L_0x0013;
    L_0x0020:
        r0 = r8.b;	 Catch:{ all -> 0x00b1 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x002a;
    L_0x0028:
        r0 = r1;
        goto L_0x0013;
    L_0x002a:
        r0 = "found#⊗";
        r0 = "cgiwifi";
        r0 = r9.contains(r0);	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x007c;
    L_0x0034:
        r0 = r8.a(r10, r9);	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x003c;
    L_0x003a:
        r2 = "found#cgiwifi";
    L_0x003c:
        if (r0 == 0) goto L_0x007a;
    L_0x003e:
        r2 = r0.a();	 Catch:{ all -> 0x00b1 }
        r2 = com.e.br.a(r2);	 Catch:{ all -> 0x00b1 }
        if (r2 == 0) goto L_0x007a;
    L_0x0048:
        r0 = r0.a();	 Catch:{ all -> 0x00b1 }
        r2 = com.e.bo.q();	 Catch:{ all -> 0x00b1 }
        r4 = "mem";
        r0.setType(r4);	 Catch:{ all -> 0x00b1 }
        r4 = com.e.bc.f;	 Catch:{ all -> 0x00b1 }
        r4 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x00b1 }
        if (r4 == 0) goto L_0x0067;
    L_0x005d:
        r4 = r0.getCoord();	 Catch:{ all -> 0x00b1 }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ all -> 0x00b1 }
        com.e.bc.f = r4;	 Catch:{ all -> 0x00b1 }
    L_0x0067:
        r4 = 0;
        r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r4 == 0) goto L_0x007a;
    L_0x006d:
        r4 = com.e.br.a();	 Catch:{ all -> 0x00b1 }
        r6 = r0.getTime();	 Catch:{ all -> 0x00b1 }
        r4 = r4 - r6;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x0013;
    L_0x007a:
        r0 = r1;
        goto L_0x0013;
    L_0x007c:
        r0 = "wifi";
        r0 = r9.contains(r0);	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x008d;
    L_0x0084:
        r0 = r8.a(r10, r9);	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x003c;
    L_0x008a:
        r2 = "found#wifi";
        goto L_0x003c;
    L_0x008d:
        r0 = "cgi";
        r0 = r9.contains(r0);	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x00b6;
    L_0x0095:
        r0 = r8.b;	 Catch:{ all -> 0x00b1 }
        r0 = r0.containsKey(r9);	 Catch:{ all -> 0x00b1 }
        if (r0 == 0) goto L_0x00b4;
    L_0x009d:
        r0 = r8.b;	 Catch:{ all -> 0x00b1 }
        r0 = r0.get(r9);	 Catch:{ all -> 0x00b1 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x00b1 }
        r2 = 0;
        r0 = r0.get(r2);	 Catch:{ all -> 0x00b1 }
        r0 = (com.e.be.a) r0;	 Catch:{ all -> 0x00b1 }
    L_0x00ac:
        if (r0 == 0) goto L_0x003c;
    L_0x00ae:
        r2 = "found#cgi";
        goto L_0x003c;
    L_0x00b1:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x00b4:
        r0 = r1;
        goto L_0x00ac;
    L_0x00b6:
        r0 = r1;
        goto L_0x003c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.be.a(java.lang.String, java.lang.StringBuilder, boolean):com.autonavi.aps.amapapi.model.AmapLoc");
    }

    public void a(Context context) {
        if (!this.d) {
            br.b();
            try {
                bf.a().a(context);
            } catch (Throwable th) {
                bc.a(th, "Cache", "loadDB");
            }
            this.d = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(java.lang.String r7, java.lang.StringBuilder r8, com.autonavi.aps.amapapi.model.AmapLoc r9, android.content.Context r10, boolean r11) {
        /*
        r6 = this;
        r0 = 0;
        monitor-enter(r6);
        r1 = r6.a(r7, r9);	 Catch:{ all -> 0x010d }
        if (r1 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r6);
        return;
    L_0x000a:
        r1 = r9.getType();	 Catch:{ all -> 0x010d }
        r2 = "mem";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x010d }
        if (r1 != 0) goto L_0x0008;
    L_0x0016:
        r1 = r9.getType();	 Catch:{ all -> 0x010d }
        r2 = "file";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x010d }
        if (r1 != 0) goto L_0x0008;
    L_0x0022:
        r1 = r9.getRetype();	 Catch:{ all -> 0x010d }
        r2 = "-3";
        r1 = r1.equals(r2);	 Catch:{ all -> 0x010d }
        if (r1 != 0) goto L_0x0008;
    L_0x002e:
        r1 = r6.b();	 Catch:{ all -> 0x010d }
        if (r1 == 0) goto L_0x0037;
    L_0x0034:
        r6.c();	 Catch:{ all -> 0x010d }
    L_0x0037:
        r1 = r9.getExtra();	 Catch:{ all -> 0x010d }
        r2 = "offpct";
        r2 = com.e.br.a(r1, r2);	 Catch:{ all -> 0x010d }
        if (r2 == 0) goto L_0x004b;
    L_0x0043:
        r2 = "offpct";
        r1.remove(r2);	 Catch:{ all -> 0x010d }
        r9.setExtra(r1);	 Catch:{ all -> 0x010d }
    L_0x004b:
        r1 = "wifi";
        r1 = r7.contains(r1);	 Catch:{ all -> 0x010d }
        if (r1 == 0) goto L_0x011c;
    L_0x0053:
        r1 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x010d }
        if (r1 != 0) goto L_0x0008;
    L_0x0059:
        r1 = r9.getAccuracy();	 Catch:{ all -> 0x010d }
        r2 = 1133903872; // 0x43960000 float:300.0 double:5.60222949E-315;
        r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r1 < 0) goto L_0x0110;
    L_0x0063:
        r1 = r8.toString();	 Catch:{ all -> 0x010d }
        r2 = "#";
        r2 = r1.split(r2);	 Catch:{ all -> 0x010d }
        r3 = r2.length;	 Catch:{ all -> 0x010d }
        r1 = r0;
    L_0x006f:
        if (r1 >= r3) goto L_0x0080;
    L_0x0071:
        r4 = r2[r1];	 Catch:{ all -> 0x010d }
        r5 = ",";
        r4 = r4.contains(r5);	 Catch:{ all -> 0x010d }
        if (r4 == 0) goto L_0x007d;
    L_0x007b:
        r0 = r0 + 1;
    L_0x007d:
        r1 = r1 + 1;
        goto L_0x006f;
    L_0x0080:
        r1 = 8;
        if (r0 >= r1) goto L_0x0008;
    L_0x0084:
        r0 = "cgiwifi";
        r0 = r7.contains(r0);	 Catch:{ all -> 0x010d }
        if (r0 == 0) goto L_0x00b3;
    L_0x008c:
        r0 = r9.getMcell();	 Catch:{ all -> 0x010d }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x010d }
        if (r0 != 0) goto L_0x00b3;
    L_0x0096:
        r0 = "cgiwifi";
        r1 = "cgi";
        r1 = r7.replace(r0, r1);	 Catch:{ all -> 0x010d }
        r3 = r9.getMcellLoc();	 Catch:{ all -> 0x010d }
        r0 = com.e.br.a(r3);	 Catch:{ all -> 0x010d }
        if (r0 == 0) goto L_0x00b3;
    L_0x00a8:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010d }
        r2.<init>();	 Catch:{ all -> 0x010d }
        r5 = 1;
        r0 = r6;
        r4 = r10;
        r0.a(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x010d }
    L_0x00b3:
        r0 = 1;
        r0 = r6.a(r7, r8, r0);	 Catch:{ all -> 0x010d }
        r1 = com.e.br.a(r0);	 Catch:{ all -> 0x010d }
        if (r1 == 0) goto L_0x00cd;
    L_0x00be:
        r0 = r0.toStr();	 Catch:{ all -> 0x010d }
        r1 = 3;
        r1 = r9.toStr(r1);	 Catch:{ all -> 0x010d }
        r0 = r0.equals(r1);	 Catch:{ all -> 0x010d }
        if (r0 != 0) goto L_0x0008;
    L_0x00cd:
        r0 = com.e.br.b();	 Catch:{ all -> 0x010d }
        r6.c = r0;	 Catch:{ all -> 0x010d }
        r1 = new com.e.be$a;	 Catch:{ all -> 0x010d }
        r1.<init>(r6);	 Catch:{ all -> 0x010d }
        r1.a(r9);	 Catch:{ all -> 0x010d }
        r0 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x010d }
        if (r0 == 0) goto L_0x013b;
    L_0x00e1:
        r0 = 0;
    L_0x00e2:
        r1.a(r0);	 Catch:{ all -> 0x010d }
        r0 = r6.b;	 Catch:{ all -> 0x010d }
        r0 = r0.containsKey(r7);	 Catch:{ all -> 0x010d }
        if (r0 == 0) goto L_0x0140;
    L_0x00ed:
        r0 = r6.b;	 Catch:{ all -> 0x010d }
        r0 = r0.get(r7);	 Catch:{ all -> 0x010d }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x010d }
        r0.add(r1);	 Catch:{ all -> 0x010d }
    L_0x00f8:
        if (r11 == 0) goto L_0x0008;
    L_0x00fa:
        r0 = com.e.bf.a();	 Catch:{ Throwable -> 0x0103 }
        r0.a(r7, r9, r8, r10);	 Catch:{ Throwable -> 0x0103 }
        goto L_0x0008;
    L_0x0103:
        r0 = move-exception;
        r1 = "Cache";
        r2 = "add";
        com.e.bc.a(r0, r1, r2);	 Catch:{ all -> 0x010d }
        goto L_0x0008;
    L_0x010d:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x0110:
        r0 = r9.getAccuracy();	 Catch:{ all -> 0x010d }
        r1 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 > 0) goto L_0x0084;
    L_0x011a:
        goto L_0x0008;
    L_0x011c:
        r0 = "cgi";
        r0 = r7.contains(r0);	 Catch:{ all -> 0x010d }
        if (r0 == 0) goto L_0x00b3;
    L_0x0124:
        r0 = ",";
        r0 = r8.indexOf(r0);	 Catch:{ all -> 0x010d }
        r1 = -1;
        if (r0 != r1) goto L_0x0008;
    L_0x012d:
        r0 = r9.getRetype();	 Catch:{ all -> 0x010d }
        r1 = "4";
        r0 = r0.equals(r1);	 Catch:{ all -> 0x010d }
        if (r0 == 0) goto L_0x00b3;
    L_0x0139:
        goto L_0x0008;
    L_0x013b:
        r0 = r8.toString();	 Catch:{ all -> 0x010d }
        goto L_0x00e2;
    L_0x0140:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x010d }
        r0.<init>();	 Catch:{ all -> 0x010d }
        r0.add(r1);	 Catch:{ all -> 0x010d }
        r1 = r6.b;	 Catch:{ all -> 0x010d }
        r1.put(r7, r0);	 Catch:{ all -> 0x010d }
        goto L_0x00f8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.be.a(java.lang.String, java.lang.StringBuilder, com.autonavi.aps.amapapi.model.AmapLoc, android.content.Context, boolean):void");
    }

    public boolean a(String str, AmapLoc amapLoc) {
        return !TextUtils.isEmpty(str) && br.a(amapLoc) && !str.startsWith("#") && str.contains("network");
    }

    public boolean a(String str, StringBuilder stringBuilder) {
        String[] split = str.split("#");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < split.length) {
            if (split[i].contains(",nb") || split[i].contains(",access")) {
                arrayList.add(split[i]);
            }
            i++;
        }
        String[] split2 = stringBuilder.toString().split("#");
        i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < split2.length) {
            if (split2[i].contains(",nb") || split2[i].contains(",access")) {
                i2++;
                if (arrayList.contains(split2[i])) {
                    i3++;
                }
            }
            i++;
        }
        return ((double) (i3 * 2)) >= ((double) (arrayList.size() + i2)) * 0.618d;
    }

    public boolean b() {
        return this.c == 0 ? false : this.b.size() > 360 ? true : br.b() - this.c > 36000000;
    }

    public void c() {
        this.c = 0;
        if (!this.b.isEmpty()) {
            this.b.clear();
        }
        this.d = false;
    }
}
