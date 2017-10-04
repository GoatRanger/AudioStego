package com.ut.mini.core.c;

import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.core.b.b;
import com.ut.mini.d.n;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class a implements b {
    private b a = null;
    private List<String> b = new LinkedList();
    private Object c = new Object();

    public static class a {
        private List<a> a = new LinkedList();
        private List<String> b = new LinkedList();
        private List<String> c = new LinkedList();
        private List<String> d = new LinkedList();
        private boolean e = false;

        public static class a {
            private String a;
            private String b;
            private List<String> c;

            public void a(String str) {
                this.a = str;
            }

            public String a() {
                return this.b;
            }

            public void b(String str) {
                this.b = str;
            }

            public List<String> b() {
                return this.c;
            }

            public void a(List<String> list) {
                this.c = list;
            }
        }

        public void a() {
            this.e = true;
        }

        public boolean b() {
            return this.e;
        }

        public int c() {
            return this.a.size();
        }

        public void a(String str) {
            if (str != null) {
                this.d.add(str);
            }
        }

        public List<String> d() {
            return this.d;
        }

        public void a(a aVar) {
            if (aVar != null) {
                this.a.add(aVar);
            }
        }

        public List<a> e() {
            return this.a;
        }

        public void b(String str) {
            if (str != null) {
                this.b.add(str);
            }
        }

        public void c(String str) {
            if (str != null) {
                this.c.add(str);
            }
        }

        public List<String> f() {
            return this.b;
        }

        public List<String> g() {
            return this.c;
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    private String a(Map<String, String> map) {
        String str;
        Map hashMap = new HashMap();
        hashMap.putAll(map);
        if (hashMap.containsKey(UTLogFieldsScheme.EVENTID.toString())) {
            str = (String) hashMap.get(UTLogFieldsScheme.EVENTID.toString());
        } else {
            str = null;
        }
        if (str == null || !str.equals("19999")) {
            return null;
        }
        if (hashMap.containsKey(UTLogFieldsScheme.ARG1.toString())) {
            str = (String) hashMap.get(UTLogFieldsScheme.ARG1.toString());
        } else {
            str = null;
        }
        if (n.a(str)) {
            return null;
        }
        hashMap.put(UTLogFieldsScheme.EVENTID.toString(), str);
        str = com.ut.mini.core.d.b.assemble(hashMap);
        if (n.a(str)) {
            return null;
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ut.mini.core.c.a.a a(int r16, boolean r17, boolean r18, java.util.List<java.lang.String> r19) {
        /*
        r15 = this;
        r1 = r15.a;
        r7 = r1.a();
        if (r7 == 0) goto L_0x017b;
    L_0x0008:
        r1 = r7.size();
        if (r1 <= 0) goto L_0x017b;
    L_0x000e:
        r4 = 0;
        r3 = 0;
        r5 = new com.ut.mini.core.c.a$a;
        r5.<init>();
        r1 = r7.size();
        r1 = new java.lang.String[r1];
        r2 = r7.keySet();
        r2.toArray(r1);
        r2 = com.ut.mini.d.f.a();
        r0 = r18;
        r8 = r2.a(r1, r0);
        r9 = r8.length;
        r1 = 0;
        r6 = r1;
    L_0x002f:
        if (r6 >= r9) goto L_0x0037;
    L_0x0031:
        r10 = r8[r6];
        r0 = r16;
        if (r4 < r0) goto L_0x0090;
    L_0x0037:
        r2 = r15.c;
        monitor-enter(r2);
        r1 = r5.g();	 Catch:{ all -> 0x0178 }
        if (r1 == 0) goto L_0x008d;
    L_0x0040:
        r1 = r15.b;	 Catch:{ all -> 0x0178 }
        r1 = r1.size();	 Catch:{ all -> 0x0178 }
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        if (r1 <= r3) goto L_0x005d;
    L_0x004a:
        r1 = com.ut.mini.b.a.a();	 Catch:{ all -> 0x0178 }
        if (r1 == 0) goto L_0x0058;
    L_0x0050:
        r1 = 2;
        r3 = "delay log";
        r4 = "clear[size overflow:10000]";
        com.ut.mini.b.a.b(r1, r3, r4);	 Catch:{ all -> 0x0178 }
    L_0x0058:
        r1 = r15.b;	 Catch:{ all -> 0x0178 }
        r1.clear();	 Catch:{ all -> 0x0178 }
    L_0x005d:
        r1 = r15.b;	 Catch:{ all -> 0x0178 }
        r3 = r5.g();	 Catch:{ all -> 0x0178 }
        r1.addAll(r3);	 Catch:{ all -> 0x0178 }
        r1 = com.ut.mini.b.a.a();	 Catch:{ all -> 0x0178 }
        if (r1 == 0) goto L_0x008d;
    L_0x006c:
        r1 = 2;
        r3 = "delay log";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0178 }
        r4.<init>();	 Catch:{ all -> 0x0178 }
        r6 = "add:";
        r4 = r4.append(r6);	 Catch:{ all -> 0x0178 }
        r6 = r5.g();	 Catch:{ all -> 0x0178 }
        r6 = r6.toString();	 Catch:{ all -> 0x0178 }
        r4 = r4.append(r6);	 Catch:{ all -> 0x0178 }
        r4 = r4.toString();	 Catch:{ all -> 0x0178 }
        com.ut.mini.b.a.b(r1, r3, r4);	 Catch:{ all -> 0x0178 }
    L_0x008d:
        monitor-exit(r2);	 Catch:{ all -> 0x0178 }
        r1 = r5;
    L_0x008f:
        return r1;
    L_0x0090:
        r2 = r15.c;
        monitor-enter(r2);
        r1 = r15.b;	 Catch:{ all -> 0x00f3 }
        r1 = r1.contains(r10);	 Catch:{ all -> 0x00f3 }
        if (r1 == 0) goto L_0x00a4;
    L_0x009b:
        monitor-exit(r2);	 Catch:{ all -> 0x00f3 }
        r1 = r3;
        r2 = r4;
    L_0x009e:
        r3 = r6 + 1;
        r6 = r3;
        r4 = r2;
        r3 = r1;
        goto L_0x002f;
    L_0x00a4:
        monitor-exit(r2);	 Catch:{ all -> 0x00f3 }
        r1 = com.ut.mini.d.n.a(r10);
        if (r1 != 0) goto L_0x0180;
    L_0x00ab:
        r1 = r7.get(r10);
        r1 = com.ut.mini.d.n.a(r1);
        r2 = com.ut.mini.d.n.a(r1);
        if (r2 != 0) goto L_0x0180;
    L_0x00b9:
        r2 = 0;
        r11 = "UTF-8";
        r1 = r1.getBytes(r11);	 Catch:{ Exception -> 0x00f6 }
        r11 = 2;
        r1 = com.ut.mini.d.c.a(r1, r11);	 Catch:{ Exception -> 0x00f6 }
        if (r1 == 0) goto L_0x0184;
    L_0x00c7:
        r11 = com.ut.mini.base.a.b();	 Catch:{ Exception -> 0x00f6 }
        r11 = com.ut.mini.a.a.a(r1, r11);	 Catch:{ Exception -> 0x00f6 }
        r1 = new java.lang.String;	 Catch:{ Exception -> 0x00f6 }
        r1.<init>(r11);	 Catch:{ Exception -> 0x00f6 }
    L_0x00d4:
        r2 = r1;
    L_0x00d5:
        if (r2 == 0) goto L_0x0180;
    L_0x00d7:
        r11 = com.ut.mini.core.d.b.disassemble(r2);
        if (r11 == 0) goto L_0x0180;
    L_0x00dd:
        r1 = com.ut.mini.core.b.a.a();
        r12 = r1.a(r11);
        r1 = "drop";
        r1 = r12.contains(r1);
        if (r1 == 0) goto L_0x00fb;
    L_0x00ed:
        r5.b(r10);
        r1 = r3;
        r2 = r4;
        goto L_0x009e;
    L_0x00f3:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00f3 }
        throw r1;
    L_0x00f6:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00d5;
    L_0x00fb:
        r1 = "delay";
        r1 = r12.contains(r1);
        if (r1 == 0) goto L_0x0109;
    L_0x0103:
        r5.c(r10);
        r1 = r3;
        r2 = r4;
        goto L_0x009e;
    L_0x0109:
        if (r19 == 0) goto L_0x0127;
    L_0x010b:
        r1 = r19.size();
        if (r1 <= 0) goto L_0x0127;
    L_0x0111:
        r1 = com.ut.mini.base.UTLogFieldsScheme.EVENTID;
        r1 = r1.toString();
        r1 = r11.get(r1);
        r0 = r19;
        r1 = r0.contains(r1);
        if (r1 != 0) goto L_0x0127;
    L_0x0123:
        r1 = r3;
        r2 = r4;
        goto L_0x009e;
    L_0x0127:
        if (r17 == 0) goto L_0x017e;
    L_0x0129:
        r1 = r15.a(r11);
        if (r1 == 0) goto L_0x017e;
    L_0x012f:
        r2 = r1.length();
        r2 = r2 + r3;
        r13 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
        if (r2 <= r13) goto L_0x0143;
    L_0x0139:
        r1 = 2;
        r2 = "getCacheLog";
        r3 = "The size will exceed.";
        com.ut.mini.b.a.b(r1, r2, r3);
        goto L_0x0037;
    L_0x0143:
        r2 = r1.length();
        r2 = r2 + r3;
        r5.a(r10);
        r3 = "2001";
        r13 = com.ut.mini.base.UTLogFieldsScheme.EVENTID;
        r13 = r13.toString();
        r11 = r11.get(r13);
        r3 = r3.equals(r11);
        if (r3 == 0) goto L_0x0160;
    L_0x015d:
        r5.a();
    L_0x0160:
        r3 = new com.ut.mini.core.c.a$a$a;
        r3.<init>();
        r3.a(r12);
        r3.a(r10);
        r3.b(r1);
        r5.a(r3);
        r1 = r4 + 1;
        r14 = r2;
        r2 = r1;
        r1 = r14;
        goto L_0x009e;
    L_0x0178:
        r1 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0178 }
        throw r1;
    L_0x017b:
        r1 = 0;
        goto L_0x008f;
    L_0x017e:
        r1 = r2;
        goto L_0x012f;
    L_0x0180:
        r1 = r3;
        r2 = r4;
        goto L_0x009e;
    L_0x0184:
        r1 = r2;
        goto L_0x00d4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.core.c.a.a(int, boolean, boolean, java.util.List):com.ut.mini.core.c.a$a");
    }

    public void a() {
        synchronized (this.c) {
            this.b.clear();
            if (com.ut.mini.b.a.a()) {
                com.ut.mini.b.a.b(2, "delay log", "clear[EventStreamGroupStrategyArrived]");
            }
        }
    }
}
