package com.e;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.fpv.control.f;
import dji.pilot.usercenter.protocol.d;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import lecho.lib.hellocharts.model.h;

public final class af implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final OutputStream q = new OutputStream() {
        public void write(int i) throws IOException {
        }
    };
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private final int i;
    private long j = 0;
    private Writer k;
    private final LinkedHashMap<String, c> l = new LinkedHashMap(0, h.l, true);
    private int m;
    private ag n;
    private long o = 0;
    private final Callable<Void> p = new Callable<Void>(this) {
        final /* synthetic */ af a;

        {
            this.a = r1;
        }

        public Void a() throws Exception {
            synchronized (this.a) {
                if (this.a.k == null) {
                } else {
                    this.a.j();
                    if (this.a.h()) {
                        this.a.g();
                        this.a.m = 0;
                    }
                }
            }
            return null;
        }

        public /* synthetic */ Object call() throws Exception {
            return a();
        }
    };

    public final class a {
        final /* synthetic */ af a;
        private final c b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        private class a extends FilterOutputStream {
            final /* synthetic */ a a;

            private a(a aVar, OutputStream outputStream) {
                this.a = aVar;
                super(outputStream);
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    this.a.d = true;
                }
            }
        }

        private a(af afVar, c cVar) {
            this.a = afVar;
            this.b = cVar;
            this.c = cVar.d ? null : new boolean[afVar.i];
        }

        public OutputStream a(int i) throws IOException {
            if (i < 0 || i >= this.a.i) {
                throw new IllegalArgumentException("Expected index " + i + " to " + "be greater than 0 and less than the maximum value count " + "of " + this.a.i);
            }
            OutputStream d;
            synchronized (this.a) {
                OutputStream fileOutputStream;
                if (this.b.e != this) {
                    throw new IllegalStateException();
                }
                if (!this.b.d) {
                    this.c[i] = true;
                }
                r1 = this.b.b(i);
                try {
                    fileOutputStream = new FileOutputStream(r1);
                } catch (FileNotFoundException e) {
                    this.a.c.mkdirs();
                    try {
                        File b;
                        fileOutputStream = new FileOutputStream(b);
                    } catch (FileNotFoundException e2) {
                        d = af.q;
                    }
                }
                d = new a(fileOutputStream);
            }
            return d;
        }

        public void a() throws IOException {
            if (this.d) {
                this.a.a(this, false);
                this.a.c(this.b.b);
            } else {
                this.a.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            this.a.a(this, false);
        }
    }

    public final class b implements Closeable {
        final /* synthetic */ af a;
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(af afVar, String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.a = afVar;
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        public void close() {
            for (Closeable a : this.d) {
                ai.a(a);
            }
        }
    }

    private final class c {
        final /* synthetic */ af a;
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        private c(af afVar, String str) {
            this.a = afVar;
            this.b = str;
            this.c = new long[afVar.i];
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length != this.a.i) {
                throw b(strArr);
            }
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.c[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw b(strArr);
                }
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(this.a.c, this.b + "." + i);
        }

        public String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long append : this.c) {
                stringBuilder.append(' ').append(append);
            }
            return stringBuilder.toString();
        }

        public File b(int i) {
            return new File(this.a.c, this.b + "." + i + f.b);
        }
    }

    private af(File file, int i, int i2, long j) {
        this.c = file;
        this.g = i;
        this.d = new File(file, "journal");
        this.e = new File(file, "journal.tmp");
        this.f = new File(file, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    private synchronized a a(String str, long j) throws IOException {
        a aVar;
        i();
        e(str);
        c cVar = (c) this.l.get(str);
        if (j == -1 || (cVar != null && cVar.f == j)) {
            c cVar2;
            if (cVar == null) {
                cVar = new c(str);
                this.l.put(str, cVar);
                cVar2 = cVar;
            } else if (cVar.e != null) {
                aVar = null;
            } else {
                cVar2 = cVar;
            }
            aVar = new a(cVar2);
            cVar2.e = aVar;
            this.k.write("DIRTY " + str + '\n');
            this.k.flush();
        } else {
            aVar = null;
        }
        return aVar;
    }

    public static af a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            af afVar = new af(file, i, i2, j);
            if (afVar.d.exists()) {
                try {
                    afVar.e();
                    afVar.f();
                    afVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afVar.d, true), ai.a));
                    return afVar;
                } catch (IOException e) {
                    afVar.c();
                }
            }
            file.mkdirs();
            afVar = new af(file, i, i2, j);
            afVar.g();
            return afVar;
        }
    }

    private synchronized void a(a aVar, boolean z) throws IOException {
        int i = 0;
        synchronized (this) {
            c a = aVar.b;
            if (a.e != aVar) {
                throw new IllegalStateException();
            }
            if (z) {
                if (!a.d) {
                    int i2 = 0;
                    while (i2 < this.i) {
                        if (!aVar.c[i2]) {
                            aVar.b();
                            throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                        } else if (!a.b(i2).exists()) {
                            aVar.b();
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
            }
            while (i < this.i) {
                File b = a.b(i);
                if (!z) {
                    a(b);
                } else if (b.exists()) {
                    File a2 = a.a(i);
                    b.renameTo(a2);
                    long j = a.c[i];
                    long length = a2.length();
                    a.c[i] = length;
                    this.j = (this.j - j) + length;
                }
                i++;
            }
            this.m++;
            a.e = null;
            if ((a.d | z) != 0) {
                a.d = true;
                this.k.write("CLEAN " + a.b + a.a() + '\n');
                if (z) {
                    long j2 = this.o;
                    this.o = 1 + j2;
                    a.f = j2;
                }
            } else {
                this.l.remove(a.b);
                this.k.write("REMOVE " + a.b + '\n');
            }
            this.k.flush();
            if (this.j > this.h || h()) {
                this.b.submit(this.p);
            }
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    private void d(String str) throws IOException {
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        String str2;
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            String substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.l.remove(substring);
                return;
            }
            str2 = substring;
        } else {
            str2 = str.substring(i, indexOf2);
        }
        c cVar = (c) this.l.get(str2);
        if (cVar == null) {
            cVar = new c(str2);
            this.l.put(str2, cVar);
        }
        if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
            String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            cVar.d = true;
            cVar.e = null;
            cVar.a(split);
        } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
            cVar.e = new a(cVar);
        } else if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void e() throws IOException {
        int i;
        Closeable ahVar = new ah(new FileInputStream(this.d), ai.a);
        try {
            String a = ahVar.a();
            String a2 = ahVar.a();
            String a3 = ahVar.a();
            String a4 = ahVar.a();
            String a5 = ahVar.a();
            if ("libcore.io.DiskLruCache".equals(a) && "1".equals(a2) && Integer.toString(this.g).equals(a3) && Integer.toString(this.i).equals(a4) && "".equals(a5)) {
                i = 0;
                while (true) {
                    d(ahVar.a());
                    i++;
                }
            } else {
                throw new IOException("unexpected journal header: [" + a + ", " + a2 + ", " + a4 + ", " + a5 + d.H);
            }
        } catch (EOFException e) {
            this.m = i - this.l.size();
            ai.a(ahVar);
        } catch (Throwable th) {
            ai.a(ahVar);
        }
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    private void f() throws IOException {
        a(this.e);
        Iterator it = this.l.values().iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            int i;
            if (cVar.e == null) {
                for (i = 0; i < this.i; i++) {
                    this.j += cVar.c[i];
                }
            } else {
                cVar.e = null;
                for (i = 0; i < this.i; i++) {
                    a(cVar.a(i));
                    a(cVar.b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void g() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), ai.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (c cVar : this.l.values()) {
                if (cVar.e != null) {
                    bufferedWriter.write("DIRTY " + cVar.b + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + cVar.b + cVar.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), ai.a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private boolean h() {
        return this.m >= 2000 && this.m >= this.l.size();
    }

    private void i() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void j() throws IOException {
        while (this.j > this.h) {
            String str = (String) ((Entry) this.l.entrySet().iterator().next()).getKey();
            c(str);
            if (this.n != null) {
                this.n.a(str);
            }
        }
    }

    public synchronized b a(String str) throws IOException {
        int i;
        b bVar = null;
        synchronized (this) {
            i();
            e(str);
            c cVar = (c) this.l.get(str);
            if (cVar != null) {
                if (cVar.d) {
                    r6 = new InputStream[this.i];
                    int i2 = 0;
                    while (i2 < this.i) {
                        try {
                            r6[i2] = new FileInputStream(cVar.a(i2));
                            i2++;
                        } catch (FileNotFoundException e) {
                            i = 0;
                            while (i < this.i && r6[i] != null) {
                                InputStream[] inputStreamArr;
                                ai.a(inputStreamArr[i]);
                                i++;
                            }
                        }
                    }
                    this.m++;
                    this.k.append("READ " + str + '\n');
                    if (h()) {
                        this.b.submit(this.p);
                    }
                    bVar = new b(str, cVar.f, inputStreamArr, cVar.c);
                }
            }
        }
        return bVar;
    }

    public void a(ag agVar) {
        this.n = agVar;
    }

    public synchronized boolean a() {
        return this.k == null;
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    public synchronized void b() throws IOException {
        i();
        j();
        this.k.flush();
    }

    public void c() throws IOException {
        close();
        ai.a(this.c);
    }

    public synchronized boolean c(String str) throws IOException {
        boolean z;
        int i = 0;
        synchronized (this) {
            i();
            e(str);
            c cVar = (c) this.l.get(str);
            if (cVar == null || cVar.e != null) {
                z = false;
            } else {
                while (i < this.i) {
                    File a = cVar.a(i);
                    if (!a.exists() || a.delete()) {
                        this.j -= cVar.c[i];
                        cVar.c[i] = 0;
                        i++;
                    } else {
                        throw new IOException("failed to delete " + a);
                    }
                }
                this.m++;
                this.k.append("REMOVE " + str + '\n');
                this.l.remove(str);
                if (h()) {
                    this.b.submit(this.p);
                }
                z = true;
            }
        }
        return z;
    }

    public synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.l.values()).iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                if (cVar.e != null) {
                    cVar.e.b();
                }
            }
            j();
            this.k.close();
            this.k = null;
        }
    }
}
