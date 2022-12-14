package com.tencent.bugly.crashreport.crash.anr;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.bugly.proguard.z;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class c {

    public interface b {
        boolean a(long j);

        boolean a(long j, long j2, String str);

        boolean a(String str, int i, String str2, String str3, boolean z);

        boolean a(String str, long j, long j2);
    }

    public static class a {
        public long a;
        public String b;
        public long c;
        public Map<String, String[]> d;
    }

    public static a a(String str, String str2, final boolean z) {
        if (str == null || str2 == null) {
            return null;
        }
        final a aVar = new a();
        a(str2, new b() {
            public boolean a(String str, long j, long j2) {
                return true;
            }

            public boolean a(String str, int i, String str2, String str3, boolean z) {
                z.c("new thread %s", str);
                if (aVar.a > 0 && aVar.c > 0 && aVar.b != null) {
                    if (aVar.d == null) {
                        aVar.d = new HashMap();
                    }
                    aVar.d.put(str, new String[]{str2, str3, "" + i});
                }
                return true;
            }

            public boolean a(long j, long j2, String str) {
                z.c("new process %s", str);
                if (!str.equals(str)) {
                    return true;
                }
                aVar.a = j;
                aVar.b = str;
                aVar.c = j2;
                if (z) {
                    return true;
                }
                return false;
            }

            public boolean a(long j) {
                z.c("process end %d", Long.valueOf(j));
                if (aVar.a <= 0 || aVar.c <= 0 || aVar.b == null) {
                    return true;
                }
                return false;
            }
        });
        if (aVar.a <= 0 || aVar.c <= 0 || aVar.b == null) {
            return null;
        }
        return aVar;
    }

    public static a a(String str, final boolean z) {
        if (str == null) {
            z.e("path:%s", str);
            return null;
        }
        final a aVar = new a();
        a(str, new b() {
            public boolean a(String str, long j, long j2) {
                return true;
            }

            public boolean a(String str, int i, String str2, String str3, boolean z) {
                z.c("new thread %s", str);
                if (aVar.d == null) {
                    aVar.d = new HashMap();
                }
                aVar.d.put(str, new String[]{str2, str3, "" + i});
                return true;
            }

            public boolean a(long j, long j2, String str) {
                z.c("new process %s", str);
                aVar.a = j;
                aVar.b = str;
                aVar.c = j2;
                if (z) {
                    return true;
                }
                return false;
            }

            public boolean a(long j) {
                z.c("process end %d", Long.valueOf(j));
                return false;
            }
        });
        if (aVar.a > 0 && aVar.c > 0 && aVar.b != null) {
            return aVar;
        }
        z.e("first dump error %s", aVar.a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar.c + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar.b);
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r13, com.tencent.bugly.crashreport.crash.anr.c.b r14) {
        /*
        if (r13 == 0) goto L_0x0004;
    L_0x0002:
        if (r14 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r6 = new java.io.File;
        r6.<init>(r13);
        r0 = r6.exists();
        if (r0 == 0) goto L_0x0004;
    L_0x0010:
        r2 = r6.lastModified();
        r4 = r6.length();
        r0 = r14;
        r1 = r13;
        r0 = r0.a(r1, r2, r4);
        if (r0 == 0) goto L_0x0004;
    L_0x0020:
        r1 = 0;
        r7 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x01a1, all -> 0x01e2 }
        r0 = new java.io.FileReader;	 Catch:{ Exception -> 0x01a1, all -> 0x01e2 }
        r0.<init>(r6);	 Catch:{ Exception -> 0x01a1, all -> 0x01e2 }
        r7.<init>(r0);	 Catch:{ Exception -> 0x01a1, all -> 0x01e2 }
        r0 = "-{5}\\spid\\s\\d+\\sat\\s\\d+-\\d+-\\d+\\s\\d{2}:\\d{2}:\\d{2}\\s-{5}";
        r8 = java.util.regex.Pattern.compile(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = "-{5}\\send\\s\\d+\\s-{5}";
        r9 = java.util.regex.Pattern.compile(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = "Cmd\\sline:\\s(\\S+)";
        r10 = java.util.regex.Pattern.compile(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = "\".+\"\\s(daemon\\s){0,1}prio=\\d+\\stid=\\d+\\s.*";
        r11 = java.util.regex.Pattern.compile(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r12 = new java.text.SimpleDateFormat;	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = "yyyy-MM-dd HH:mm:ss";
        r1 = java.util.Locale.US;	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r12.<init>(r0, r1);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
    L_0x004c:
        r0 = 1;
        r0 = new java.util.regex.Pattern[r0];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 0;
        r0[r1] = r8;	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = a(r7, r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        if (r0 == 0) goto L_0x018e;
    L_0x0058:
        r1 = 1;
        r0 = r0[r1];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = "\\s";
        r0 = r0.split(r1);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 2;
        r1 = r0[r1];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r2 = java.lang.Long.parseLong(r1);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1.<init>();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r4 = 4;
        r4 = r0[r4];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r4 = " ";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r4 = 5;
        r0 = r0[r4];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r1.append(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r12.parse(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r4 = r0.getTime();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = 1;
        r0 = new java.util.regex.Pattern[r0];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 0;
        r0[r1] = r10;	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = a(r7, r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        if (r0 != 0) goto L_0x00b0;
    L_0x009d:
        if (r7 == 0) goto L_0x0004;
    L_0x009f:
        r7.close();	 Catch:{ IOException -> 0x00a4 }
        goto L_0x0004;
    L_0x00a4:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.z.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x00ab:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x00b0:
        r1 = 1;
        r0 = r0[r1];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r10.matcher(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0.find();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 1;
        r0.group(r1);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 1;
        r6 = r0.group(r1);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = r14;
        r0 = r1.a(r2, r4, r6);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        if (r0 != 0) goto L_0x00e1;
    L_0x00ce:
        if (r7 == 0) goto L_0x0004;
    L_0x00d0:
        r7.close();	 Catch:{ IOException -> 0x00d5 }
        goto L_0x0004;
    L_0x00d5:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.z.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x00dc:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x00e1:
        r0 = 2;
        r0 = new java.util.regex.Pattern[r0];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 0;
        r0[r1] = r11;	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 1;
        r0[r1] = r9;	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = a(r7, r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        if (r0 == 0) goto L_0x004c;
    L_0x00f0:
        r1 = 0;
        r1 = r0[r1];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        if (r1 != r11) goto L_0x0161;
    L_0x00f5:
        r1 = 1;
        r0 = r0[r1];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = "\".+\"";
        r1 = java.util.regex.Pattern.compile(r1);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = r1.matcher(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1.find();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = r1.group();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r2 = 1;
        r3 = r1.length();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r3 = r3 + -1;
        r1 = r1.substring(r2, r3);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r2 = "NATIVE";
        r5 = r0.contains(r2);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r2 = "tid=\\d+";
        r2 = java.util.regex.Pattern.compile(r2);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r2.matcher(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0.find();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r0.group();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r2 = "=";
        r2 = r0.indexOf(r2);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r2 = r2 + 1;
        r0 = r0.substring(r2);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r2 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r3 = a(r7);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r4 = b(r7);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r14;
        r0 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        if (r0 != 0) goto L_0x00e1;
    L_0x014e:
        if (r7 == 0) goto L_0x0004;
    L_0x0150:
        r7.close();	 Catch:{ IOException -> 0x0155 }
        goto L_0x0004;
    L_0x0155:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.z.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x015c:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x0161:
        r1 = 1;
        r0 = r0[r1];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = "\\s";
        r0 = r0.split(r1);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r1 = 2;
        r0 = r0[r1];	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        r0 = r14.a(r0);	 Catch:{ Exception -> 0x01fa, all -> 0x01f5 }
        if (r0 != 0) goto L_0x004c;
    L_0x017b:
        if (r7 == 0) goto L_0x0004;
    L_0x017d:
        r7.close();	 Catch:{ IOException -> 0x0182 }
        goto L_0x0004;
    L_0x0182:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.z.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x0189:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x018e:
        if (r7 == 0) goto L_0x0004;
    L_0x0190:
        r7.close();	 Catch:{ IOException -> 0x0195 }
        goto L_0x0004;
    L_0x0195:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.z.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x019c:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x01a1:
        r0 = move-exception;
    L_0x01a2:
        r2 = "trace open fail:%s : %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x01f7 }
        r4 = 0;
        r5 = r0.getClass();	 Catch:{ all -> 0x01f7 }
        r5 = r5.getName();	 Catch:{ all -> 0x01f7 }
        r3[r4] = r5;	 Catch:{ all -> 0x01f7 }
        r4 = 1;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x01f7 }
        r5.<init>();	 Catch:{ all -> 0x01f7 }
        r6 = "";
        r5 = r5.append(r6);	 Catch:{ all -> 0x01f7 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x01f7 }
        r0 = r5.append(r0);	 Catch:{ all -> 0x01f7 }
        r0 = r0.toString();	 Catch:{ all -> 0x01f7 }
        r3[r4] = r0;	 Catch:{ all -> 0x01f7 }
        com.tencent.bugly.proguard.z.d(r2, r3);	 Catch:{ all -> 0x01f7 }
        if (r1 == 0) goto L_0x0004;
    L_0x01d1:
        r1.close();	 Catch:{ IOException -> 0x01d6 }
        goto L_0x0004;
    L_0x01d6:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.z.a(r0);
        if (r1 != 0) goto L_0x0004;
    L_0x01dd:
        r0.printStackTrace();
        goto L_0x0004;
    L_0x01e2:
        r0 = move-exception;
        r7 = r1;
    L_0x01e4:
        if (r7 == 0) goto L_0x01e9;
    L_0x01e6:
        r7.close();	 Catch:{ IOException -> 0x01ea }
    L_0x01e9:
        throw r0;
    L_0x01ea:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.z.a(r1);
        if (r2 != 0) goto L_0x01e9;
    L_0x01f1:
        r1.printStackTrace();
        goto L_0x01e9;
    L_0x01f5:
        r0 = move-exception;
        goto L_0x01e4;
    L_0x01f7:
        r0 = move-exception;
        r7 = r1;
        goto L_0x01e4;
    L_0x01fa:
        r0 = move-exception;
        r1 = r7;
        goto L_0x01a2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.c.a(java.lang.String, com.tencent.bugly.crashreport.crash.anr.c$b):void");
    }

    protected static Object[] a(BufferedReader bufferedReader, Pattern... patternArr) throws IOException {
        if (bufferedReader == null || patternArr == null) {
            return null;
        }
        while (true) {
            CharSequence readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            for (Pattern matcher : patternArr) {
                if (matcher.matcher(readLine).matches()) {
                    return new Object[]{patternArr[r1], readLine};
                }
            }
        }
    }

    protected static String a(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return null;
            }
            stringBuffer.append(readLine + "\n");
        }
        return stringBuffer.toString();
    }

    protected static String b(BufferedReader bufferedReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.trim().length() > 0) {
                stringBuffer.append(readLine + "\n");
            }
        }
        return stringBuffer.toString();
    }
}
