package com.e;

import android.content.Context;
import android.text.TextUtils;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class bh {
    private static bh a = null;
    private Hashtable<String, JSONObject> b = new Hashtable();
    private boolean c = false;

    private bh() {
    }

    public static synchronized bh a() {
        bh bhVar;
        synchronized (bh.class) {
            if (a == null) {
                a = new bh();
            }
            bhVar = a;
        }
        return bhVar;
    }

    private void d() {
        if (!this.b.isEmpty()) {
            this.b.clear();
        }
    }

    public void a(Context context) {
        if (at.a && !this.c) {
            br.b();
            try {
                bf.a().b(context);
            } catch (Throwable th) {
                bc.a(th, "HeatMap", "loadDB");
            }
            this.c = true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r10, java.lang.String r11, com.autonavi.aps.amapapi.model.AmapLoc r12) {
        /*
        r9 = this;
        monitor-enter(r9);
        r3 = 0;
        r0 = com.e.br.a(r12);	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x000e;
    L_0x0008:
        if (r10 == 0) goto L_0x000e;
    L_0x000a:
        r0 = com.e.at.a;	 Catch:{ all -> 0x0071 }
        if (r0 != 0) goto L_0x0010;
    L_0x000e:
        monitor-exit(r9);
        return;
    L_0x0010:
        r0 = r9.b;	 Catch:{ all -> 0x0071 }
        r0 = r0.size();	 Catch:{ all -> 0x0071 }
        r1 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r0 <= r1) goto L_0x002e;
    L_0x001a:
        r0 = r12.getLat();	 Catch:{ all -> 0x0071 }
        r2 = r12.getLon();	 Catch:{ all -> 0x0071 }
        r3 = com.e.aw.a(r0, r2);	 Catch:{ all -> 0x0071 }
        r0 = r9.b;	 Catch:{ all -> 0x0071 }
        r0 = r0.containsKey(r3);	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x000e;
    L_0x002e:
        if (r3 != 0) goto L_0x003c;
    L_0x0030:
        r0 = r12.getLat();	 Catch:{ all -> 0x0071 }
        r2 = r12.getLon();	 Catch:{ all -> 0x0071 }
        r3 = com.e.aw.a(r0, r2);	 Catch:{ all -> 0x0071 }
    L_0x003c:
        r0 = new org.json.JSONObject;	 Catch:{ all -> 0x0071 }
        r0.<init>();	 Catch:{ all -> 0x0071 }
        r1 = "key";
        r0.put(r1, r11);	 Catch:{ Throwable -> 0x0068 }
        r1 = "lat";
        r4 = r12.getLat();	 Catch:{ Throwable -> 0x0068 }
        r0.put(r1, r4);	 Catch:{ Throwable -> 0x0068 }
        r1 = "lon";
        r4 = r12.getLon();	 Catch:{ Throwable -> 0x0068 }
        r0.put(r1, r4);	 Catch:{ Throwable -> 0x0068 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0068 }
        r5 = 1;
        r6 = com.e.br.a();	 Catch:{ Throwable -> 0x0068 }
        r8 = 1;
        r1 = r9;
        r2 = r10;
        r1.a(r2, r3, r4, r5, r6, r8);	 Catch:{ Throwable -> 0x0068 }
        goto L_0x000e;
    L_0x0068:
        r0 = move-exception;
        r1 = "HeatMap";
        r2 = "update";
        com.e.bc.a(r0, r1, r2);	 Catch:{ all -> 0x0071 }
        goto L_0x000e;
    L_0x0071:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.bh.a(android.content.Context, java.lang.String, com.autonavi.aps.amapapi.model.AmapLoc):void");
    }

    public synchronized void a(Context context, String str, String str2, int i, long j, boolean z) {
        if (context != null) {
            if (!TextUtils.isEmpty(str)) {
                if (at.a) {
                    JSONObject jSONObject = (JSONObject) this.b.get(str);
                    JSONObject jSONObject2 = jSONObject == null ? new JSONObject() : jSONObject;
                    try {
                        jSONObject2.put("x", str2);
                        jSONObject2.put(n.ax, j);
                        if (this.b.containsKey(str)) {
                            jSONObject2.put("num", jSONObject2.getInt("num") + i);
                        } else {
                            jSONObject2.put("num", i);
                        }
                    } catch (Throwable th) {
                        bc.a(th, "HeatMap", "update1");
                    }
                    this.b.put(str, jSONObject2);
                    if (i >= 120 && !bc.r) {
                        bc.r = true;
                        bq.a(context, "pref", "ded", true);
                    }
                    if (z) {
                        try {
                            bf.a().a(context, str, str2, j);
                        } catch (Throwable th2) {
                            bc.a(th2, "HeatMap", "update");
                        }
                    }
                }
            }
        }
    }

    public synchronized ArrayList<bg> b() {
        ArrayList<bg> arrayList;
        List arrayList2 = new ArrayList();
        if (this.b.isEmpty()) {
            arrayList = arrayList2;
        } else {
            Hashtable hashtable = this.b;
            ArrayList arrayList3 = new ArrayList(hashtable.keySet());
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                try {
                    JSONObject jSONObject = (JSONObject) hashtable.get(str);
                    int i = jSONObject.getInt("num");
                    String string = jSONObject.getString("x");
                    long j = jSONObject.getLong(n.ax);
                    if (i >= 120) {
                        arrayList2.add(new bg(str, j, i, string));
                    }
                } catch (Throwable th) {
                    bc.a(th, "HeatMap", "hot");
                }
            }
            Collections.sort(arrayList2, new Comparator<bg>(this) {
                final /* synthetic */ bh a;

                {
                    this.a = r1;
                }

                public int a(bg bgVar, bg bgVar2) {
                    return bgVar2.b() - bgVar.b();
                }

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return a((bg) obj, (bg) obj2);
                }
            });
            arrayList3.clear();
            List list = arrayList2;
        }
        return arrayList;
    }

    public void c() {
        a().d();
        this.c = false;
    }
}
