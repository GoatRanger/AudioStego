package dji.thirdparty.c;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

public class x {
    public static final x b = new x() {
        public x a(long j, TimeUnit timeUnit) {
            return this;
        }

        public x a(long j) {
            return this;
        }

        public void g() throws IOException {
        }
    };
    private boolean a;
    private long c;
    private long d;

    public x a(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("timeout < 0: " + j);
        } else if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        } else {
            this.d = timeUnit.toNanos(j);
            return this;
        }
    }

    public long k_() {
        return this.d;
    }

    public boolean l_() {
        return this.a;
    }

    public long d() {
        if (this.a) {
            return this.c;
        }
        throw new IllegalStateException("No deadline");
    }

    public x a(long j) {
        this.a = true;
        this.c = j;
        return this;
    }

    public final x b(long j, TimeUnit timeUnit) {
        if (j <= 0) {
            throw new IllegalArgumentException("duration <= 0: " + j);
        } else if (timeUnit != null) {
            return a(System.nanoTime() + timeUnit.toNanos(j));
        } else {
            throw new IllegalArgumentException("unit == null");
        }
    }

    public x m_() {
        this.d = 0;
        return this;
    }

    public x f() {
        this.a = false;
        return this;
    }

    public void g() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        } else if (this.a && this.c - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }
}
