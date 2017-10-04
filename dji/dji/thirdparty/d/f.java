package dji.thirdparty.d;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.fpv.d.c$i;
import dji.pilot.usercenter.protocol.d;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class f implements g {
    private static final String a = "PRETTYLOGGER";
    private static final int b = 3;
    private static final int c = 6;
    private static final int d = 7;
    private static final int e = 4;
    private static final int f = 2;
    private static final int g = 5;
    private static final int h = 4000;
    private static final int i = 2;
    private static final int j = 3;
    private static final char k = '╔';
    private static final char l = '╚';
    private static final char m = '╟';
    private static final char n = '║';
    private static final String o = "════════════════════════════════════════════";
    private static final String p = "────────────────────────────────────────────";
    private static final String q = "╔════════════════════════════════════════════════════════════════════════════════════════";
    private static final String r = "╚════════════════════════════════════════════════════════════════════════════════════════";
    private static final String s = "╟────────────────────────────────────────────────────────────────────────────────────────";
    private String t;
    private final ThreadLocal<String> u = new ThreadLocal();
    private final ThreadLocal<Integer> v = new ThreadLocal();
    private final h w = new h();

    public f() {
        a(a);
    }

    public h a(String str) {
        if (str == null) {
            throw new NullPointerException("tag may not be null");
        } else if (str.trim().length() == 0) {
            throw new IllegalStateException("tag may not be empty");
        } else {
            this.t = str;
            return this.w;
        }
    }

    public h a() {
        return this.w;
    }

    public g a(String str, int i) {
        if (str != null) {
            this.u.set(str);
        }
        this.v.set(Integer.valueOf(i));
        return this;
    }

    public void a(String str, Object... objArr) {
        a(3, null, str, objArr);
    }

    public void a(Object obj) {
        String deepToString;
        if (obj.getClass().isArray()) {
            deepToString = Arrays.deepToString((Object[]) obj);
        } else {
            deepToString = obj.toString();
        }
        a(3, null, deepToString, new Object[0]);
    }

    public void b(String str, Object... objArr) {
        a(null, str, objArr);
    }

    public void a(Throwable th, String str, Object... objArr) {
        a(6, th, str, objArr);
    }

    public void c(String str, Object... objArr) {
        a(5, null, str, objArr);
    }

    public void d(String str, Object... objArr) {
        a(4, null, str, objArr);
    }

    public void e(String str, Object... objArr) {
        a(2, null, str, objArr);
    }

    public void f(String str, Object... objArr) {
        a(7, null, str, objArr);
    }

    public void b(String str) {
        if (b.a((CharSequence) str)) {
            a((Object) "Empty/Null json content");
            return;
        }
        try {
            String trim = str.trim();
            if (trim.startsWith("{")) {
                a(new JSONObject(trim).toString(2));
            } else if (trim.startsWith(d.G)) {
                a(new JSONArray(trim).toString(2));
            } else {
                b("Invalid Json", new Object[0]);
            }
        } catch (JSONException e) {
            b("Invalid Json", new Object[0]);
        }
    }

    public void c(String str) {
        if (b.a((CharSequence) str)) {
            a((Object) "Empty/Null xml content");
            return;
        }
        try {
            Source streamSource = new StreamSource(new StringReader(str));
            Object streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", c$i.b);
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            a(streamResult.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            b("Invalid xml", new Object[0]);
        }
    }

    public synchronized void a(int i, String str, String str2, Throwable th) {
        if (this.w.d() != d.NONE) {
            String str3;
            if (th == null || str2 == null) {
                str3 = str2;
            } else {
                str3 = str2 + " : " + b.a(th);
            }
            if (th != null && r0 == null) {
                str3 = b.a(th);
            }
            if (str3 == null) {
                str3 = "No message/exception is set";
            }
            int d = d();
            if (b.a((CharSequence) str3)) {
                str3 = "Empty/NULL log message";
            }
            a(i, str);
            a(i, str, d);
            byte[] bytes = str3.getBytes();
            int length = bytes.length;
            if (length <= h) {
                if (d > 0) {
                    c(i, str);
                }
                a(i, str, str3);
                b(i, str);
            } else {
                if (d > 0) {
                    c(i, str);
                }
                for (int i2 = 0; i2 < length; i2 += h) {
                    a(i, str, new String(bytes, i2, Math.min(length - i2, h)));
                }
                b(i, str);
            }
        }
    }

    public void b() {
        this.w.g();
    }

    private synchronized void a(int i, Throwable th, String str, Object... objArr) {
        if (this.w.d() != d.NONE) {
            a(i, c(), g(str, objArr), th);
        }
    }

    private void a(int i, String str) {
        b(i, str, q);
    }

    private void a(int i, String str, int i2) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (this.w.c()) {
            b(i, str, "║ Thread: " + Thread.currentThread().getName());
            c(i, str);
        }
        String str2 = "";
        int a = a(stackTrace) + this.w.e();
        if (i2 + a > stackTrace.length) {
            i2 = (stackTrace.length - a) - 1;
        }
        while (i2 > 0) {
            int i3 = i2 + a;
            if (i3 < stackTrace.length) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("║ ").append(str2).append(d(stackTrace[i3].getClassName())).append(".").append(stackTrace[i3].getMethodName()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(" (").append(stackTrace[i3].getFileName()).append(":").append(stackTrace[i3].getLineNumber()).append(")");
                str2 = str2 + "   ";
                b(i, str, stringBuilder.toString());
            }
            i2--;
        }
    }

    private void b(int i, String str) {
        b(i, str, r);
    }

    private void c(int i, String str) {
        b(i, str, s);
    }

    private void a(int i, String str, String str2) {
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            b(i, str, "║ " + str3);
        }
    }

    private void b(int i, String str, String str2) {
        String e = e(str);
        switch (i) {
            case 2:
                this.w.f().e(e, str2);
                return;
            case 4:
                this.w.f().d(e, str2);
                return;
            case 5:
                this.w.f().c(e, str2);
                return;
            case 6:
                this.w.f().b(e, str2);
                return;
            case 7:
                this.w.f().f(e, str2);
                return;
            default:
                this.w.f().a(e, str2);
                return;
        }
    }

    private String d(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    private String e(String str) {
        if (b.a((CharSequence) str) || b.a(this.t, str)) {
            return this.t;
        }
        return this.t + "-" + str;
    }

    private String c() {
        String str = (String) this.u.get();
        if (str == null) {
            return this.t;
        }
        this.u.remove();
        return str;
    }

    private String g(String str, Object... objArr) {
        return (objArr == null || objArr.length == 0) ? str : String.format(str, objArr);
    }

    private int d() {
        int intValue;
        Integer num = (Integer) this.v.get();
        int b = this.w.b();
        if (num != null) {
            this.v.remove();
            intValue = num.intValue();
        } else {
            intValue = b;
        }
        if (intValue >= 0) {
            return intValue;
        }
        throw new IllegalStateException("methodCount cannot be negative");
    }

    private int a(StackTraceElement[] stackTraceElementArr) {
        for (int i = 3; i < stackTraceElementArr.length; i++) {
            String className = stackTraceElementArr[i].getClassName();
            if (!className.equals(f.class.getName()) && !className.equals(e.class.getName())) {
                return i - 1;
            }
        }
        return -1;
    }
}
