package dji.thirdparty.g;

import dji.thirdparty.g.a.a.a;
import dji.thirdparty.g.a.a.b;
import dji.thirdparty.g.a.c;
import dji.thirdparty.g.a.i;
import dji.thirdparty.g.b.b.h;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public abstract class d extends c implements h {
    public abstract i a(a aVar, Map map) throws e, IOException;

    public abstract boolean a(File file, File file2, byte[] bArr);

    public abstract c b(a aVar, Map map) throws e, IOException;

    public abstract String b();

    public abstract String c();

    public abstract String c(a aVar, Map map) throws e, IOException;

    public abstract byte[] d(a aVar, Map map) throws e, IOException;

    protected abstract String[] d();

    protected abstract b[] e();

    public static final d[] a() {
        return new d[]{new dji.thirdparty.g.b.a.c(), new h()};
    }

    public final i a(a aVar) throws e, IOException {
        return a(aVar, null);
    }

    public final i a(byte[] bArr) throws e, IOException {
        return a(bArr);
    }

    public final i a(byte[] bArr, Map map) throws e, IOException {
        return a(new b(bArr), map);
    }

    public final i a(File file) throws e, IOException {
        return a(file, null);
    }

    public final i a(File file, Map map) throws e, IOException {
        if (this.fn_) {
            System.out.println(b() + ".getMetadata: " + file.getName());
        }
        if (e(file)) {
            return a(new dji.thirdparty.g.a.a.c(file), map);
        }
        return null;
    }

    public final c b(a aVar) throws e, IOException {
        return b(aVar, null);
    }

    public final c b(byte[] bArr, Map map) throws e, IOException {
        return b(new b(bArr), map);
    }

    public final c b(File file, Map map) throws e, IOException {
        if (e(file)) {
            return b(new dji.thirdparty.g.a.a.c(file), map);
        }
        return null;
    }

    public a c(a aVar) throws e, IOException {
        return null;
    }

    public final a b(byte[] bArr) throws e, IOException {
        return c(new b(bArr));
    }

    public final a b(File file) throws e, IOException {
        if (e(file)) {
            return c(new dji.thirdparty.g.a.a.c(file));
        }
        return null;
    }

    public final byte[] c(byte[] bArr) throws e, IOException {
        return c(bArr, null);
    }

    public final byte[] c(byte[] bArr, Map map) throws e, IOException {
        return d(new b(bArr), map);
    }

    public final byte[] c(File file) throws e, IOException {
        return c(file, null);
    }

    public final byte[] c(File file, Map map) throws e, IOException {
        if (!e(file)) {
            return null;
        }
        if (this.fn_) {
            System.out.println(b() + ": " + file.getName());
        }
        return d(new dji.thirdparty.g.a.a.c(file), map);
    }

    public final String d(byte[] bArr) throws e, IOException {
        return d(new b(bArr));
    }

    public final String d(File file) throws e, IOException {
        if (!e(file)) {
            return null;
        }
        if (this.fn_) {
            System.out.println(b() + ": " + file.getName());
        }
        return d(new dji.thirdparty.g.a.a.c(file));
    }

    public final String d(a aVar) throws e, IOException {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        a(printWriter, aVar);
        printWriter.flush();
        return stringWriter.toString();
    }

    public boolean a(PrintWriter printWriter, a aVar) throws e, IOException {
        return false;
    }

    public boolean a(b bVar) {
        b[] e = e();
        for (b equals : e) {
            if (equals.equals(bVar)) {
                return true;
            }
        }
        return false;
    }

    protected final boolean e(File file) {
        return a(file.getName());
    }

    protected final boolean a(String str) {
        String[] d = d();
        if (d == null) {
            return true;
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return false;
        }
        String toLowerCase = str.substring(lastIndexOf).toLowerCase();
        for (String toLowerCase2 : d) {
            if (toLowerCase2.toLowerCase().equals(toLowerCase)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean a(Map map) {
        if (map == null || !map.containsKey(h.fl_)) {
            return false;
        }
        return ((Boolean) map.get(h.fl_)).booleanValue();
    }
}
