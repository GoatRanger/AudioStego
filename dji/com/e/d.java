package com.e;

import android.content.Context;
import android.os.Looper;
import com.e.af.b;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import org.json.JSONObject;

public abstract class d {
    private dc a;
    private int b;
    private ag c;
    private af d;

    class a implements ag {
        final /* synthetic */ d a;
        private n b;

        a(d dVar, n nVar) {
            this.a = dVar;
            this.b = nVar;
        }

        public void a(String str) {
            try {
                this.b.b(str, dh.a(this.a.b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    protected d(int i) {
        this.b = i;
    }

    private String a(Context context, String str) {
        String str2 = null;
        try {
            str2 = cw.e(context, dd.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return str2;
    }

    private String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2).append(",").append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    private String a(List<? extends o> list, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(f(context)).append("\",\"els\":[");
        Object obj = 1;
        for (o oVar : list) {
            Object obj2;
            String d = d(oVar.b());
            if (d != null) {
                if ("".equals(d)) {
                    obj2 = obj;
                    obj = obj2;
                } else {
                    String str = d + "||" + oVar.c();
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("{\"log\":\"").append(str).append("\"}");
                }
            }
            obj2 = obj;
            obj = obj2;
        }
        if (obj != null) {
            return null;
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    private void a(n nVar, int i) {
        try {
            a(nVar.a(2, dh.a(i)), nVar, i);
        } catch (Throwable th) {
            dg.a(th, "LogProcessor", "processDeleteFail");
        }
    }

    private void a(n nVar, String str, String str2, int i, boolean z) {
        o b = dh.b(i);
        b.a(0);
        b.b(str);
        b.a(str2);
        nVar.a(b);
    }

    private void a(List<? extends o> list, n nVar, int i) {
        if (list != null && list.size() > 0) {
            for (o oVar : list) {
                if (b(oVar.b())) {
                    nVar.a(oVar.b(), oVar.getClass());
                } else {
                    oVar.a(2);
                    nVar.b(oVar);
                }
            }
        }
    }

    private boolean a(Context context, String str, String str2, String str3, n nVar) {
        boolean z;
        Throwable th;
        Throwable th2;
        OutputStream outputStream = null;
        af afVar = null;
        b bVar = null;
        try {
            File file = new File(dh.a(context, str2));
            if (file.exists() || file.mkdirs()) {
                afVar = af.a(file, 1, 1, 20480);
                afVar.a(a(nVar));
                bVar = afVar.a(str);
                if (bVar != null) {
                    z = false;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th3) {
                            th3.printStackTrace();
                        }
                    }
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Throwable th4) {
                            th4.printStackTrace();
                        }
                    }
                    if (afVar == null || afVar.a()) {
                        return false;
                    }
                    try {
                        afVar.close();
                        return false;
                    } catch (Throwable th5) {
                        th4 = th5;
                        th4.printStackTrace();
                        return z;
                    }
                }
                byte[] a = dd.a(str3);
                com.e.af.a b = afVar.b(str);
                outputStream = b.a(0);
                outputStream.write(a);
                b.a();
                afVar.b();
                z = true;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th32) {
                        th32.printStackTrace();
                    }
                }
                if (bVar != null) {
                    try {
                        bVar.close();
                    } catch (Throwable th42) {
                        th42.printStackTrace();
                    }
                }
                if (afVar == null || afVar.a()) {
                    return true;
                }
                try {
                    afVar.close();
                    return true;
                } catch (Throwable th6) {
                    th42 = th6;
                    th42.printStackTrace();
                    return z;
                }
            }
            z = false;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable th322) {
                    th322.printStackTrace();
                }
            }
            if (bVar != null) {
                try {
                    bVar.close();
                } catch (Throwable th422) {
                    th422.printStackTrace();
                }
            }
            if (afVar == null || afVar.a()) {
                return false;
            }
            try {
                afVar.close();
                return false;
            } catch (Throwable th7) {
                th422 = th7;
            }
            return false;
            if (bVar != null) {
                bVar.close();
            }
            if (!(afVar == null || afVar.a())) {
                afVar.close();
            }
            return false;
            if (bVar != null) {
                bVar.close();
            }
            if (!(afVar == null || afVar.a())) {
                afVar.close();
            }
            return false;
            afVar.close();
            return false;
            afVar.close();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Throwable th8) {
            th2 = th8;
            th2.printStackTrace();
        }
    }

    public static boolean a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            String[] split = str.split("<br/>");
            for (CharSequence charSequence : strArr) {
                for (String str2 : split) {
                    if (str2.contains("at") && str2.contains(charSequence)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private af b(Context context, String str) {
        af afVar = null;
        try {
            File file = new File(dh.a(context, str));
            if (file.exists() || file.mkdirs()) {
                afVar = af.a(file, 1, 1, 20480);
            }
        } catch (Throwable e) {
            dg.a(e, "LogProcessor", "initDiskLru");
        } catch (Throwable e2) {
            dg.a(e2, "LogProcessor", "initDiskLru");
        }
        return afVar;
    }

    public static String b(Throwable th) {
        String a = dd.a(th);
        return a != null ? a.replaceAll("\n", "<br/>") : null;
    }

    private boolean b(String str) {
        boolean z = false;
        if (this.d != null) {
            try {
                z = this.d.c(str);
            } catch (Throwable th) {
                dg.a(th, "LogUpdateProcessor", "deleteLogData");
            }
        }
        return z;
    }

    private int c(String str) {
        int i = 0;
        try {
            byte[] b = aj.a().b(new di(dd.c(dd.a(str))));
            if (b == null) {
                return 0;
            }
            try {
                JSONObject jSONObject = new JSONObject(dd.a(b));
                String str2 = "code";
                return jSONObject.has(str2) ? jSONObject.getInt(str2) : 0;
            } catch (Throwable e) {
                dg.a(e, "LogProcessor", "processUpdate");
                return 1;
            }
        } catch (Throwable e2) {
            if (e2.b() != 27) {
                i = 1;
            }
            dg.a(e2, "LogProcessor", "processUpdate");
            return i;
        }
    }

    private String c(Throwable th) {
        return th.toString();
    }

    private String d() {
        return dd.a(new Date().getTime());
    }

    private String d(String str) {
        Throwable e;
        String str2;
        String str3;
        InputStream a;
        Throwable th;
        Object obj;
        String str4 = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            if (this.d == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e2) {
                        dg.a(e2, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e = e3;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        dg.a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            b a2 = this.d.a(str);
            if (a2 == null) {
                if (str4 != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable e22) {
                        dg.a(e22, "LogProcessor", "readLog1");
                    }
                }
                if (str4 != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e = e4;
                        str2 = "LogProcessor";
                        str3 = "readLog2";
                        dg.a(e, str2, str3);
                        return str4;
                    }
                }
                return str4;
            }
            a = a2.a(0);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = a.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    str4 = dd.a(byteArrayOutputStream.toByteArray());
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable e5) {
                            dg.a(e5, "LogProcessor", "readLog1");
                        }
                    }
                    if (a != null) {
                        try {
                            a.close();
                        } catch (IOException e6) {
                            e5 = e6;
                            str2 = "LogProcessor";
                            str3 = "readLog2";
                            dg.a(e5, str2, str3);
                            return str4;
                        }
                    }
                } catch (Throwable th2) {
                    e5 = th2;
                    try {
                        dg.a(e5, "LogProcessor", "readLog");
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e52) {
                                dg.a(e52, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (IOException e7) {
                                e52 = e7;
                                str2 = "LogProcessor";
                                str3 = "readLog2";
                                dg.a(e52, str2, str3);
                                return str4;
                            }
                        }
                        return str4;
                    } catch (Throwable th3) {
                        th = th3;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable e522) {
                                dg.a(e522, "LogProcessor", "readLog1");
                            }
                        }
                        if (a != null) {
                            try {
                                a.close();
                            } catch (Throwable e5222) {
                                dg.a(e5222, "LogProcessor", "readLog2");
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable e52222) {
                obj = str4;
                th = e52222;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (a != null) {
                    a.close();
                }
                throw th;
            }
            return str4;
        } catch (Throwable e522222) {
            byteArrayOutputStream = str4;
            a = str4;
            th = e522222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    private void d(Context context) {
        try {
            this.d = b(context, a());
        } catch (Throwable th) {
            dg.a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    private List<dc> e(Context context) {
        List<dc> a;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<dc> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    a = new p(context, false).a();
                    try {
                    } catch (Throwable th22) {
                        th = th22;
                        list = a;
                        th3 = th;
                        try {
                            throw th3;
                        } catch (Throwable th32) {
                            th = th32;
                            a = list;
                            th22 = th;
                        }
                    }
                } catch (Throwable th4) {
                    th32 = th4;
                    throw th32;
                }
            }
        } catch (Throwable th322) {
            th = th322;
            a = null;
            th22 = th;
            th22.printStackTrace();
            return a;
        }
    }

    private String f(Context context) {
        String str = null;
        try {
            String b = cw.b(context);
            if (!"".equals(b)) {
                str = cw.b(context, dd.a(b));
            }
        } catch (Throwable th) {
            dg.a(th, "LogProcessor", "getPublicInfo");
        }
        return str;
    }

    protected ag a(n nVar) {
        try {
            if (this.c == null) {
                this.c = new a(this, nVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.c;
    }

    protected String a() {
        return dh.c(this.b);
    }

    protected String a(String str) {
        return cz.c(str);
    }

    protected String a(Throwable th) {
        String str = null;
        try {
            str = b(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        return str;
    }

    protected abstract String a(List<dc> list);

    void a(Context context, Throwable th, String str, String str2) {
        List<dc> e = e(context);
        if (e != null && e.size() != 0) {
            String a = a(th);
            if (a != null && !"".equals(a)) {
                for (dc dcVar : e) {
                    if (a(dcVar.e(), a)) {
                        a(dcVar, context, th, a, str, str2);
                        return;
                    }
                }
                if (a.contains("com.amap.api.col")) {
                    try {
                        a(new com.e.dc.a("collection", "1.0", "AMap_collection_1.0").a(new String[]{"com.amap.api.collection"}).a(), context, th, a, str, str2);
                    } catch (ct e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    protected void a(dc dcVar) {
        this.a = dcVar;
    }

    void a(dc dcVar, Context context, Throwable th, String str, String str2, String str3) {
        a(dcVar);
        String d = d();
        String a = cw.a(context, dcVar);
        String a2 = cu.a(context);
        String c = c(th);
        if (c != null && !"".equals(c)) {
            int b = b();
            StringBuilder stringBuilder = new StringBuilder();
            if (str2 != null) {
                stringBuilder.append("class:").append(str2);
            }
            if (str3 != null) {
                stringBuilder.append(" method:").append(str3).append("$").append("<br/>");
            }
            stringBuilder.append(str);
            String a3 = a(str);
            String a4 = a(a2, a, d, b, c, stringBuilder.toString());
            if (a4 != null && !"".equals(a4)) {
                String a5 = a(context, a4);
                String a6 = a();
                synchronized (Looper.getMainLooper()) {
                    n nVar = new n(context);
                    a(nVar, dcVar.a(), a3, b, a(context, a3, a6, a5, nVar));
                }
            }
        }
    }

    protected abstract boolean a(Context context);

    protected int b() {
        return this.b;
    }

    void b(Context context) {
        List e = e(context);
        if (e != null && e.size() != 0) {
            String a = a(e);
            if (a != null && !"".equals(a)) {
                String d = d();
                String a2 = cw.a(context, this.a);
                int b = b();
                String a3 = a(cu.a(context), a2, d, b, "ANR", a);
                if (a3 != null && !"".equals(a3)) {
                    String a4 = a(a);
                    String a5 = a(context, a3);
                    String a6 = a();
                    synchronized (Looper.getMainLooper()) {
                        n nVar = new n(context);
                        a(nVar, this.a.a(), a4, b, a(context, a4, a6, a5, nVar));
                    }
                }
            }
        }
    }

    void c() {
        try {
            if (this.d != null && !this.d.a()) {
                this.d.close();
            }
        } catch (Throwable e) {
            dg.a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable e2) {
            dg.a(e2, "LogProcessor", "closeDiskLru");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void c(android.content.Context r6) {
        /*
        r5 = this;
        r5.d(r6);	 Catch:{ Throwable -> 0x0035 }
        r0 = r5.a(r6);	 Catch:{ Throwable -> 0x0035 }
        if (r0 != 0) goto L_0x000a;
    L_0x0009:
        return;
    L_0x000a:
        r1 = android.os.Looper.getMainLooper();	 Catch:{ Throwable -> 0x0035 }
        monitor-enter(r1);	 Catch:{ Throwable -> 0x0035 }
        r0 = new com.e.n;	 Catch:{ all -> 0x0032 }
        r0.<init>(r6);	 Catch:{ all -> 0x0032 }
        r2 = r5.b();	 Catch:{ all -> 0x0032 }
        r5.a(r0, r2);	 Catch:{ all -> 0x0032 }
        r2 = 0;
        r3 = r5.b();	 Catch:{ all -> 0x0032 }
        r3 = com.e.dh.a(r3);	 Catch:{ all -> 0x0032 }
        r2 = r0.a(r2, r3);	 Catch:{ all -> 0x0032 }
        if (r2 == 0) goto L_0x0030;
    L_0x002a:
        r3 = r2.size();	 Catch:{ all -> 0x0032 }
        if (r3 != 0) goto L_0x003e;
    L_0x0030:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        throw r0;	 Catch:{ Throwable -> 0x0035 }
    L_0x0035:
        r0 = move-exception;
        r1 = "LogProcessor";
        r2 = "processUpdateLog";
        com.e.dg.a(r0, r1, r2);
        goto L_0x0009;
    L_0x003e:
        r3 = r5.a(r2, r6);	 Catch:{ all -> 0x0032 }
        if (r3 != 0) goto L_0x0046;
    L_0x0044:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
    L_0x0046:
        r3 = r5.c(r3);	 Catch:{ all -> 0x0032 }
        r4 = 1;
        if (r3 != r4) goto L_0x0054;
    L_0x004d:
        r3 = r5.b();	 Catch:{ all -> 0x0032 }
        r5.a(r2, r0, r3);	 Catch:{ all -> 0x0032 }
    L_0x0054:
        monitor-exit(r1);	 Catch:{ all -> 0x0032 }
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.d.c(android.content.Context):void");
    }
}
