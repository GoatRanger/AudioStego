package dji.thirdparty.c;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;

public class a extends x {
    private static final int a = 65536;
    private static a c;
    private boolean d;
    private a e;
    private long f;

    private static final class a extends Thread {
        public a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        public void run() {
            while (true) {
                try {
                    a e = a.e();
                    if (e != null) {
                        e.a();
                    }
                } catch (InterruptedException e2) {
                }
            }
        }
    }

    public final void c() {
        if (this.d) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long k_ = k_();
        boolean l_ = l_();
        if (k_ != 0 || l_) {
            this.d = true;
            a(this, k_, l_);
        }
    }

    private static synchronized void a(a aVar, long j, boolean z) {
        synchronized (a.class) {
            if (c == null) {
                c = new a();
                new a().start();
            }
            long nanoTime = System.nanoTime();
            if (j != 0 && z) {
                aVar.f = Math.min(j, aVar.d() - nanoTime) + nanoTime;
            } else if (j != 0) {
                aVar.f = nanoTime + j;
            } else if (z) {
                aVar.f = aVar.d();
            } else {
                throw new AssertionError();
            }
            long b = aVar.b(nanoTime);
            a aVar2 = c;
            while (aVar2.e != null && b >= aVar2.e.b(nanoTime)) {
                aVar2 = aVar2.e;
            }
            aVar.e = aVar2.e;
            aVar2.e = aVar;
            if (aVar2 == c) {
                a.class.notify();
            }
        }
    }

    public final boolean j_() {
        if (!this.d) {
            return false;
        }
        this.d = false;
        return a(this);
    }

    private static synchronized boolean a(a aVar) {
        boolean z;
        synchronized (a.class) {
            for (a aVar2 = c; aVar2 != null; aVar2 = aVar2.e) {
                if (aVar2.e == aVar) {
                    aVar2.e = aVar.e;
                    aVar.e = null;
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    private long b(long j) {
        return this.f - j;
    }

    protected void a() {
    }

    public final v a(final v vVar) {
        return new v(this) {
            final /* synthetic */ a b;

            public void a_(c cVar, long j) throws IOException {
                y.a(cVar.c, 0, j);
                long j2 = j;
                while (j2 > 0) {
                    s sVar = cVar.b;
                    long j3 = 0;
                    while (j3 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        long j4 = ((long) (cVar.b.e - cVar.b.d)) + j3;
                        if (j4 >= j2) {
                            j3 = j2;
                            break;
                        } else {
                            sVar = sVar.h;
                            j3 = j4;
                        }
                    }
                    this.b.c();
                    try {
                        vVar.a_(cVar, j3);
                        j2 -= j3;
                        this.b.a(true);
                    } catch (IOException e) {
                        throw this.b.b(e);
                    } catch (Throwable th) {
                        this.b.a(false);
                    }
                }
            }

            public void flush() throws IOException {
                this.b.c();
                try {
                    vVar.flush();
                    this.b.a(true);
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public void close() throws IOException {
                this.b.c();
                try {
                    vVar.close();
                    this.b.a(true);
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public x a() {
                return this.b;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + vVar + ")";
            }
        };
    }

    public final w a(final w wVar) {
        return new w(this) {
            final /* synthetic */ a b;

            public long a(c cVar, long j) throws IOException {
                this.b.c();
                try {
                    long a = wVar.a(cVar, j);
                    this.b.a(true);
                    return a;
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public void close() throws IOException {
                try {
                    wVar.close();
                    this.b.a(true);
                } catch (IOException e) {
                    throw this.b.b(e);
                } catch (Throwable th) {
                    this.b.a(false);
                }
            }

            public x a() {
                return this.b;
            }

            public String toString() {
                return "AsyncTimeout.source(" + wVar + ")";
            }
        };
    }

    final void a(boolean z) throws IOException {
        if (j_() && z) {
            throw a(null);
        }
    }

    final IOException b(IOException iOException) throws IOException {
        return !j_() ? iOException : a(iOException);
    }

    protected IOException a(IOException iOException) {
        IOException interruptedIOException = new InterruptedIOException(com.alipay.sdk.c.a.f);
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    static synchronized a e() throws InterruptedException {
        a aVar = null;
        synchronized (a.class) {
            a aVar2 = c.e;
            if (aVar2 == null) {
                a.class.wait();
            } else {
                long b = aVar2.b(System.nanoTime());
                if (b > 0) {
                    long j = b / 1000000;
                    a.class.wait(j, (int) (b - (1000000 * j)));
                } else {
                    c.e = aVar2.e;
                    aVar2.e = null;
                    aVar = aVar2;
                }
            }
        }
        return aVar;
    }
}
