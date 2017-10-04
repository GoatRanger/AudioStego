package com.a.a;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.JsonWriter;
import com.a.a.a.a.a.d;
import com.a.a.a.a.a.e;
import com.a.a.a.a.a.f;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class m extends com.a.a.a.a {
    private static final Charset a = Charset.forName("UTF-8");
    private final Context b;
    private final l c;
    private final d d;
    private final int e;
    private final n f;
    private final Handler g;
    private final HandlerThread h;
    private final com.a.a.a.c i;
    private final Map<String, Boolean> j;
    private final c k;
    private final ExecutorService l;
    private final ScheduledExecutorService m;
    private final Object n = new Object();

    static class a implements Closeable {
        private final JsonWriter a;
        private final BufferedWriter b;
        private boolean c = false;

        a(OutputStream outputStream) {
            this.b = new BufferedWriter(new OutputStreamWriter(outputStream));
            this.a = new JsonWriter(this.b);
        }

        a a() throws IOException {
            this.a.beginObject();
            return this;
        }

        a a(Map<String, Boolean> map) throws IOException {
            if (!com.a.a.a.b.a((Map) map)) {
                this.a.name("integrations").beginObject();
                for (Entry entry : map.entrySet()) {
                    this.a.name((String) entry.getKey()).value(((Boolean) entry.getValue()).booleanValue());
                }
                this.a.endObject();
            }
            return this;
        }

        a b() throws IOException {
            this.a.name("batch").beginArray();
            this.c = false;
            return this;
        }

        a a(String str) throws IOException {
            if (this.c) {
                this.b.write(44);
            } else {
                this.c = true;
            }
            this.b.write(str);
            return this;
        }

        a c() throws IOException {
            if (this.c) {
                this.a.endArray();
                return this;
            }
            throw new IOException("At least one payload must be provided.");
        }

        a d() throws IOException {
            this.a.name("sentAt").value(com.a.a.a.b.a(new Date())).endObject();
            return this;
        }

        public void close() throws IOException {
            this.a.close();
        }
    }

    static class b implements c {
        final a a;
        int b;
        int c;

        b(a aVar) {
            this.a = aVar;
        }

        public boolean a(InputStream inputStream, int i) throws IOException {
            int i2 = this.b + i;
            if (i2 > 475000) {
                return false;
            }
            this.b = i2;
            byte[] bArr = new byte[i];
            inputStream.read(bArr, 0, i);
            this.a.a(new String(bArr, m.a));
            this.c++;
            return true;
        }
    }

    static class c extends Handler {
        private final m a;

        c(Looper looper, m mVar) {
            super(looper);
            this.a = mVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a((com.a.a.a.a.a.b) message.obj);
                    return;
                case 1:
                    this.a.d();
                    return;
                default:
                    throw new AssertionError("Unknown dispatcher message: " + message.what);
            }
        }
    }

    private static l a(File file, String str) throws IOException {
        com.a.a.a.b.a(file);
        File file2 = new File(file, str);
        try {
            return new l(file2);
        } catch (IOException e) {
            if (file2.delete()) {
                return new l(file2);
            }
            throw new IOException("Could not create queue file (" + str + ") in " + file + ".");
        }
    }

    static synchronized m a(Context context, d dVar, c cVar, ExecutorService executorService, n nVar, Map<String, Boolean> map, String str, long j, int i, com.a.a.a.c cVar2) {
        m mVar;
        synchronized (m.class) {
            try {
                mVar = new m(context, dVar, cVar, executorService, a(context.getDir("segment-disk-queue", 0), str), nVar, map, j, i, cVar2);
            } catch (Throwable e) {
                throw new IOError(e);
            }
        }
        return mVar;
    }

    m(Context context, d dVar, c cVar, ExecutorService executorService, l lVar, n nVar, Map<String, Boolean> map, long j, int i, com.a.a.a.c cVar2) {
        this.b = context;
        this.d = dVar;
        this.l = executorService;
        this.c = lVar;
        this.f = nVar;
        this.i = cVar2;
        this.j = map;
        this.k = cVar;
        this.e = i;
        this.m = Executors.newScheduledThreadPool(1, new com.a.a.a.b.c());
        this.h = new HandlerThread("SegmentAnalytics-SegmentDispatcher", 10);
        this.h.start();
        this.g = new c(this.h.getLooper(), this);
        this.m.scheduleAtFixedRate(new Runnable(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        }, lVar.b() >= i ? 0 : j, j, TimeUnit.MILLISECONDS);
    }

    public void a(a aVar, p pVar) throws IllegalStateException {
    }

    public String b() {
        return "Segment.io";
    }

    public void a(d dVar) {
        b((com.a.a.a.a.a.b) dVar);
    }

    public void a(com.a.a.a.a.a.c cVar) {
        b((com.a.a.a.a.a.b) cVar);
    }

    public void a(f fVar) {
        b((com.a.a.a.a.a.b) fVar);
    }

    public void a(com.a.a.a.a.a.a aVar) {
        b((com.a.a.a.a.a.b) aVar);
    }

    public void a(e eVar) {
        b((com.a.a.a.a.a.b) eVar);
    }

    private void b(com.a.a.a.a.a.b bVar) {
        this.g.sendMessage(this.g.obtainMessage(0, bVar));
    }

    void a(com.a.a.a.a.a.b bVar) {
        if (this.c.b() >= 1000) {
            synchronized (this.n) {
                if (this.c.b() >= 1000) {
                    if (this.i.a()) {
                        com.a.a.a.b.a("Queue is at max capacity (%s), removing oldest payload.", Integer.valueOf(this.c.b()));
                    }
                    try {
                        this.c.c();
                    } catch (Throwable e) {
                        throw new IOError(e);
                    }
                }
            }
        }
        try {
            CharSequence a = this.k.a((Map) bVar);
            if (com.a.a.a.b.a(a) || a.length() > 15000) {
                throw new IOException("Could not serialize payload " + bVar);
            }
            this.c.a(a.getBytes(a));
            if (this.i.a()) {
                com.a.a.a.b.a("Enqueued %s payload. Queue size is now : %s.", bVar, Integer.valueOf(this.c.b()));
            }
            if (this.c.b() >= this.e) {
                d();
            }
        } catch (Throwable e2) {
            if (this.i.a()) {
                com.a.a.a.b.a(e2, "Could not add payload %s to queue: %s.", bVar, this.c);
            }
        }
    }

    public void c() {
        this.g.sendMessage(this.g.obtainMessage(1));
    }

    void d() {
        if (f()) {
            this.l.submit(new Runnable(this) {
                final /* synthetic */ m a;

                {
                    this.a = r1;
                }

                public void run() {
                    synchronized (this.a.n) {
                        this.a.g();
                    }
                }
            });
        }
    }

    private boolean f() {
        return this.c.b() > 0 && com.a.a.a.b.b(this.b);
    }

    private void g() {
        int i;
        boolean a = this.d.a().a();
        if (this.i.a()) {
            com.a.a.a.b.a("isOkToSend status for flushing events is " + a, new Object[0]);
        }
        if (f() && a) {
            if (this.i.a()) {
                com.a.a.a.b.a("Uploading payloads in queue to Segment.", new Object[0]);
            }
            Closeable closeable = null;
            try {
                closeable = this.d.b();
                a b = new a(closeable.c).a().a(this.j).b();
                c bVar = new b(b);
                this.c.a(bVar);
                b.c().d().close();
                i = bVar.c;
                closeable.close();
            } catch (Throwable e) {
                if (this.i.a()) {
                    com.a.a.a.b.a(e, "Payloads were rejected by server. Marked for removal.", new Object[0]);
                }
            } catch (Throwable th) {
                com.a.a.a.b.a(closeable);
            }
            try {
                com.a.a.a.b.a(closeable);
                try {
                    this.c.a(i);
                    if (this.i.a()) {
                        com.a.a.a.b.a("Uploaded %s payloads. Queue size is now %s.", Integer.valueOf(i), Integer.valueOf(this.c.b()));
                    }
                    this.f.a(i);
                    if (this.c.b() > 0) {
                        g();
                    }
                } catch (Throwable e2) {
                    throw new IOError(new IOException("Unable to remove " + i + " payload(s) from queueFile: " + this.c, e2));
                } catch (Throwable e22) {
                    com.a.a.a.b.a(e22, "Unable to remove %s payload(s) from queueFile: %s", Integer.valueOf(i), this.c);
                    throw e22;
                }
            } catch (Throwable e222) {
                if (this.i.a()) {
                    com.a.a.a.b.a(e222, "Error while uploading payloads", new Object[0]);
                }
            }
        }
    }
}
