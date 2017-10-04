package com.facebook.internal;

import com.alipay.sdk.j.i;
import com.facebook.o;
import com.facebook.y;
import com.here.posclient.UpdateOptions;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class n {
    static final String a = n.class.getSimpleName();
    private static final String b = "key";
    private static final String c = "tag";
    private static final AtomicLong d = new AtomicLong();
    private final String e;
    private final d f;
    private final File g;
    private boolean h;
    private boolean i;
    private final Object j;
    private AtomicLong k = new AtomicLong(0);

    private interface f {
        void a();
    }

    private static class a {
        private static final String a = "buffer";
        private static final FilenameFilter b = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return !str.startsWith(a.a);
            }
        };
        private static final FilenameFilter c = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith(a.a);
            }
        };

        private a() {
        }

        static void a(File file) {
            File[] listFiles = file.listFiles(b());
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }

        static FilenameFilter a() {
            return b;
        }

        static FilenameFilter b() {
            return c;
        }

        static File b(File file) {
            return new File(file, a + Long.valueOf(n.d.incrementAndGet()).toString());
        }
    }

    private static class b extends OutputStream {
        final OutputStream a;
        final f b;

        b(OutputStream outputStream, f fVar) {
            this.a = outputStream;
            this.b = fVar;
        }

        public void close() throws IOException {
            try {
                this.a.close();
            } finally {
                this.b.a();
            }
        }

        public void flush() throws IOException {
            this.a.flush();
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.a.write(bArr, i, i2);
        }

        public void write(byte[] bArr) throws IOException {
            this.a.write(bArr);
        }

        public void write(int i) throws IOException {
            this.a.write(i);
        }
    }

    private static final class c extends InputStream {
        final InputStream a;
        final OutputStream b;

        c(InputStream inputStream, OutputStream outputStream) {
            this.a = inputStream;
            this.b = outputStream;
        }

        public int available() throws IOException {
            return this.a.available();
        }

        public void close() throws IOException {
            try {
                this.a.close();
            } finally {
                this.b.close();
            }
        }

        public void mark(int i) {
            throw new UnsupportedOperationException();
        }

        public boolean markSupported() {
            return false;
        }

        public int read(byte[] bArr) throws IOException {
            int read = this.a.read(bArr);
            if (read > 0) {
                this.b.write(bArr, 0, read);
            }
            return read;
        }

        public int read() throws IOException {
            int read = this.a.read();
            if (read >= 0) {
                this.b.write(read);
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.a.read(bArr, i, i2);
            if (read > 0) {
                this.b.write(bArr, i, read);
            }
            return read;
        }

        public synchronized void reset() {
            throw new UnsupportedOperationException();
        }

        public long skip(long j) throws IOException {
            byte[] bArr = new byte[1024];
            long j2 = 0;
            while (j2 < j) {
                int read = read(bArr, 0, (int) Math.min(j - j2, (long) bArr.length));
                if (read < 0) {
                    break;
                }
                j2 += (long) read;
            }
            return j2;
        }
    }

    public static final class d {
        private int a = 1048576;
        private int b = 1024;

        int a() {
            return this.a;
        }

        int b() {
            return this.b;
        }

        void a(int i) {
            if (i < 0) {
                throw new InvalidParameterException("Cache byte-count limit must be >= 0");
            }
            this.a = i;
        }

        void b(int i) {
            if (i < 0) {
                throw new InvalidParameterException("Cache file count limit must be >= 0");
            }
            this.b = i;
        }
    }

    private static final class e implements Comparable<e> {
        private static final int a = 29;
        private static final int b = 37;
        private final File c;
        private final long d;

        public /* synthetic */ int compareTo(Object obj) {
            return a((e) obj);
        }

        e(File file) {
            this.c = file;
            this.d = file.lastModified();
        }

        File a() {
            return this.c;
        }

        long b() {
            return this.d;
        }

        public int a(e eVar) {
            if (b() < eVar.b()) {
                return -1;
            }
            if (b() > eVar.b()) {
                return 1;
            }
            return a().compareTo(eVar.a());
        }

        public boolean equals(Object obj) {
            return (obj instanceof e) && a((e) obj) == 0;
        }

        public int hashCode() {
            return ((this.c.hashCode() + 1073) * 37) + ((int) (this.d % UpdateOptions.SOURCE_ANY));
        }
    }

    private static final class g {
        private static final int a = 0;

        private g() {
        }

        static void a(OutputStream outputStream, JSONObject jSONObject) throws IOException {
            byte[] bytes = jSONObject.toString().getBytes();
            outputStream.write(0);
            outputStream.write((bytes.length >> 16) & 255);
            outputStream.write((bytes.length >> 8) & 255);
            outputStream.write((bytes.length >> 0) & 255);
            outputStream.write(bytes);
        }

        static JSONObject a(InputStream inputStream) throws IOException {
            int i = 0;
            if (inputStream.read() != 0) {
                return null;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < 3; i3++) {
                int read = inputStream.read();
                if (read == -1) {
                    x.a(y.CACHE, n.a, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                i2 = (i2 << 8) + (read & 255);
            }
            byte[] bArr = new byte[i2];
            while (i < bArr.length) {
                i2 = inputStream.read(bArr, i, bArr.length - i);
                if (i2 < 1) {
                    x.a(y.CACHE, n.a, "readHeader: stream.read stopped at " + Integer.valueOf(i) + " when expected " + bArr.length);
                    return null;
                }
                i += i2;
            }
            try {
                Object nextValue = new JSONTokener(new String(bArr)).nextValue();
                if (nextValue instanceof JSONObject) {
                    return (JSONObject) nextValue;
                }
                x.a(y.CACHE, n.a, "readHeader: expected JSONObject, got " + nextValue.getClass().getCanonicalName());
                return null;
            } catch (JSONException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    public n(String str, d dVar) {
        this.e = str;
        this.f = dVar;
        this.g = new File(o.o(), str);
        this.j = new Object();
        if (this.g.mkdirs() || this.g.isDirectory()) {
            a.a(this.g);
        }
    }

    long a() {
        synchronized (this.j) {
            while (true) {
                if (!this.h && !this.i) {
                    break;
                }
                try {
                    this.j.wait();
                } catch (InterruptedException e) {
                }
            }
        }
        File[] listFiles = this.g.listFiles();
        long j = 0;
        if (listFiles != null) {
            int i = 0;
            while (i < listFiles.length) {
                long length = listFiles[i].length() + j;
                i++;
                j = length;
            }
        }
        return j;
    }

    public InputStream a(String str) throws IOException {
        return a(str, null);
    }

    public InputStream a(String str, String str2) throws IOException {
        File file = new File(this.g, ah.b(str));
        try {
            InputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
            try {
                JSONObject a = g.a(bufferedInputStream);
                if (a == null) {
                    return null;
                }
                String optString = a.optString("key");
                if (optString == null || !optString.equals(str)) {
                    bufferedInputStream.close();
                    return null;
                }
                String optString2 = a.optString(c, null);
                if ((str2 != null || optString2 == null) && (str2 == null || str2.equals(optString2))) {
                    long time = new Date().getTime();
                    x.a(y.CACHE, a, "Setting lastModified to " + Long.valueOf(time) + " for " + file.getName());
                    file.setLastModified(time);
                    return bufferedInputStream;
                }
                bufferedInputStream.close();
                return null;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException e) {
            return null;
        }
    }

    public OutputStream b(String str) throws IOException {
        return b(str, null);
    }

    public OutputStream b(String str, String str2) throws IOException {
        final File b = a.b(this.g);
        b.delete();
        if (b.createNewFile()) {
            try {
                OutputStream fileOutputStream = new FileOutputStream(b);
                final long currentTimeMillis = System.currentTimeMillis();
                final String str3 = str;
                OutputStream bufferedOutputStream = new BufferedOutputStream(new b(fileOutputStream, new f(this) {
                    final /* synthetic */ n d;

                    public void a() {
                        if (currentTimeMillis < this.d.k.get()) {
                            b.delete();
                        } else {
                            this.d.a(str3, b);
                        }
                    }
                }), 8192);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("key", str);
                    if (!ah.a(str2)) {
                        jSONObject.put(c, str2);
                    }
                    g.a(bufferedOutputStream, jSONObject);
                    return bufferedOutputStream;
                } catch (JSONException e) {
                    x.a(y.CACHE, 5, a, "Error creating JSON header for cache file: " + e);
                    throw new IOException(e.getMessage());
                } catch (Throwable th) {
                    bufferedOutputStream.close();
                }
            } catch (FileNotFoundException e2) {
                x.a(y.CACHE, 5, a, "Error creating buffer output stream: " + e2);
                throw new IOException(e2.getMessage());
            }
        }
        throw new IOException("Could not create file at " + b.getAbsolutePath());
    }

    public void b() {
        final File[] listFiles = this.g.listFiles(a.a());
        this.k.set(System.currentTimeMillis());
        if (listFiles != null) {
            o.f().execute(new Runnable(this) {
                final /* synthetic */ n b;

                public void run() {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            });
        }
    }

    public String c() {
        return this.g.getPath();
    }

    private void a(String str, File file) {
        if (!file.renameTo(new File(this.g, ah.b(str)))) {
            file.delete();
        }
        e();
    }

    public InputStream a(String str, InputStream inputStream) throws IOException {
        return new c(inputStream, b(str));
    }

    public String toString() {
        return "{FileLruCache: tag:" + this.e + " file:" + this.g.getName() + i.d;
    }

    private void e() {
        synchronized (this.j) {
            if (!this.h) {
                this.h = true;
                o.f().execute(new Runnable(this) {
                    final /* synthetic */ n a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.f();
                    }
                });
            }
        }
    }

    private void f() {
        synchronized (this.j) {
            this.h = false;
            this.i = true;
        }
        try {
            File file;
            x.a(y.CACHE, a, "trim started");
            PriorityQueue priorityQueue = new PriorityQueue();
            long j = 0;
            long j2 = 0;
            File[] listFiles = this.g.listFiles(a.a());
            if (listFiles != null) {
                int length = listFiles.length;
                int i = 0;
                while (i < length) {
                    file = listFiles[i];
                    e eVar = new e(file);
                    priorityQueue.add(eVar);
                    x.a(y.CACHE, a, "  trim considering time=" + Long.valueOf(eVar.b()) + " name=" + eVar.a().getName());
                    long length2 = file.length() + j;
                    i++;
                    j2 = 1 + j2;
                    j = length2;
                }
            }
            long j3 = j;
            j = j2;
            while (true) {
                if (j3 > ((long) this.f.a()) || j > ((long) this.f.b())) {
                    file = ((e) priorityQueue.remove()).a();
                    x.a(y.CACHE, a, "  trim removing " + file.getName());
                    j3 -= file.length();
                    j2 = j - 1;
                    file.delete();
                    j = j2;
                } else {
                    synchronized (this.j) {
                        this.i = false;
                        this.j.notifyAll();
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            synchronized (this.j) {
                this.i = false;
                this.j.notifyAll();
            }
        }
    }
}
