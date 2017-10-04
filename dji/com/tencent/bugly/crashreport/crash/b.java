package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.CrashHandleCallback;
import com.tencent.bugly.crashreport.common.strategy.c;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class b {
    protected final Context a;
    protected final w b;
    protected final q c;
    protected final c d;

    public b(Context context, w wVar, q qVar, c cVar, CrashHandleCallback crashHandleCallback) {
        this.a = context;
        this.b = wVar;
        this.c = qVar;
        this.d = cVar;
    }

    protected List<a> a(List<a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long b = ag.b();
        List<a> arrayList = new ArrayList();
        for (a aVar : list) {
            if (aVar.d && aVar.b < b - 86400000) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    protected CrashDetailBean a(List<a> list, CrashDetailBean crashDetailBean) {
        if (list == null || list.size() == 0) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2;
        CrashDetailBean crashDetailBean3 = null;
        List arrayList = new ArrayList(10);
        for (a aVar : list) {
            if (aVar.e) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0) {
            List b = this.c.b(arrayList);
            if (b != null && b.size() > 0) {
                Collections.sort(b);
                int i = 0;
                while (i < b.size()) {
                    crashDetailBean2 = (CrashDetailBean) b.get(i);
                    if (i != 0) {
                        if (crashDetailBean2.s == null) {
                            crashDetailBean2 = crashDetailBean3;
                        } else {
                            String[] split = crashDetailBean2.s.split("\n");
                            if (split == null) {
                                crashDetailBean2 = crashDetailBean3;
                            } else {
                                for (String str : split) {
                                    if (!crashDetailBean3.s.contains("" + str)) {
                                        crashDetailBean3.t++;
                                        crashDetailBean3.s += str + "\n";
                                    }
                                }
                                crashDetailBean2 = crashDetailBean3;
                            }
                        }
                    }
                    i++;
                    crashDetailBean3 = crashDetailBean2;
                }
                crashDetailBean2 = crashDetailBean3;
                if (crashDetailBean2 != null) {
                    crashDetailBean.j = true;
                    crashDetailBean.t = 0;
                    crashDetailBean.s = "";
                    crashDetailBean3 = crashDetailBean;
                } else {
                    crashDetailBean3 = crashDetailBean2;
                }
                for (a aVar2 : list) {
                    if (!(aVar2.e || aVar2.d || crashDetailBean3.s.contains("" + aVar2.b))) {
                        crashDetailBean3.t++;
                        crashDetailBean3.s += aVar2.b + "\n";
                    }
                }
                if (crashDetailBean3.r == crashDetailBean.r && !crashDetailBean3.s.contains("" + crashDetailBean.r)) {
                    crashDetailBean3.t++;
                    crashDetailBean3.s += crashDetailBean.r + "\n";
                    return crashDetailBean3;
                }
            }
        }
        crashDetailBean2 = null;
        if (crashDetailBean2 != null) {
            crashDetailBean3 = crashDetailBean2;
        } else {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean3 = crashDetailBean;
        }
        for (a aVar22 : list) {
            crashDetailBean3.t++;
            crashDetailBean3.s += aVar22.b + "\n";
        }
        return crashDetailBean3.r == crashDetailBean.r ? crashDetailBean3 : crashDetailBean3;
    }

    public boolean a(CrashDetailBean crashDetailBean) {
        this.d.a(crashDetailBean);
        z.b("[crash] a crash occur, handling...", new Object[0]);
        List<a> c = this.c.c();
        List list = null;
        if (c != null && c.size() > 0) {
            List arrayList = new ArrayList(10);
            List arrayList2 = new ArrayList(10);
            arrayList.addAll(a((List) c));
            c.removeAll(arrayList);
            if (!CrashReport.isDebug) {
                int i = 0;
                for (a aVar : c) {
                    if (crashDetailBean.u.equals(aVar.c)) {
                        if (aVar.e) {
                            i = true;
                        }
                        arrayList2.add(aVar);
                    }
                    i = i;
                }
                if (i != 0 || arrayList2.size() + 1 >= 5) {
                    z.a("same crash occur too much do merged!", new Object[0]);
                    CrashDetailBean a = a(arrayList2, crashDetailBean);
                    a.a = -1;
                    this.c.b(a);
                    arrayList.addAll(arrayList2);
                    this.c.c(arrayList);
                    z.b("[crash] save crash success. this device crash many times, won't upload crashes immediately", new Object[0]);
                    return true;
                }
            }
            list = arrayList;
        }
        this.c.b(crashDetailBean);
        this.c.c(list);
        z.b("[crash] save crash success", new Object[0]);
        return false;
    }

    public void a(CrashDetailBean crashDetailBean, long j) {
        z.a("try to upload right now", new Object[0]);
        List arrayList = new ArrayList();
        arrayList.add(crashDetailBean);
        this.b.a(arrayList, this.d, j);
    }

    public void a(CrashDetailBean crashDetailBean, CrashHandleCallback crashHandleCallback) {
        if (crashDetailBean == null || crashHandleCallback == null) {
            z.d("handle user callback args are null! %s %s", "" + crashDetailBean, "" + crashHandleCallback);
            return;
        }
        try {
            int i;
            z.a("start notify crashHandleCallback!", new Object[0]);
            int i2 = crashDetailBean.b == 0 ? 0 : 1;
            switch (crashDetailBean.b) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 2;
                    break;
                case 2:
                    i = 1;
                    break;
                case 3:
                    i = 4;
                    break;
                case 4:
                    i = 3;
                    break;
                case 5:
                    i = 5;
                    break;
                case 6:
                    i = 6;
                    break;
                default:
                    i = i2;
                    break;
            }
            Map onCrashHandleStart = crashHandleCallback.onCrashHandleStart(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
            if (onCrashHandleStart != null && onCrashHandleStart.size() > 0) {
                crashDetailBean.O = new LinkedHashMap(onCrashHandleStart.size());
                for (Entry entry : onCrashHandleStart.entrySet()) {
                    if (!ag.b((String) entry.getKey())) {
                        String str;
                        String str2 = (String) entry.getKey();
                        if (str2.length() > 100) {
                            str2 = str2.substring(0, 100);
                            z.d("setted key length is over limit %d substring to %s", Integer.valueOf(100), str2);
                        }
                        String str3 = str2;
                        str2 = "";
                        if (ag.b((String) entry.getValue()) || ((String) entry.getValue()).length() <= 30000) {
                            str = "" + ((String) entry.getValue());
                        } else {
                            str = ((String) entry.getValue()).substring(((String) entry.getValue()).length() - 30000);
                            z.d("setted %s value length is over limit %d substring", str3, Integer.valueOf(30000));
                        }
                        crashDetailBean.O.put(str3, str);
                        z.a("add setted key %s value size:%d", str3, Integer.valueOf(str.length()));
                    }
                }
            }
            z.a("start notify crashHandleCallback2GetBytes!", new Object[0]);
            crashDetailBean.T = crashHandleCallback.onCrashHandleStart2GetExtraDatas(i, crashDetailBean.n, crashDetailBean.o, crashDetailBean.q);
            if (crashDetailBean.T != null) {
                if (crashDetailBean.T.length > 30000) {
                    z.d("extra bytes size %d is over limit %d will drop over part", Integer.valueOf(crashDetailBean.T.length), Integer.valueOf(30000));
                    byte[] bArr = new byte[30000];
                    for (int i3 = 0; i3 < 30000; i3++) {
                        bArr[i3] = crashDetailBean.T[i3];
                    }
                }
                z.a("add extra bytes %d ", Integer.valueOf(crashDetailBean.T.length));
            }
        } catch (Throwable th) {
            z.d("crash handle callback somthing wrong! %s", th.getClass().getName());
            if (!z.a(th)) {
                th.printStackTrace();
            }
        }
    }
}
