package dji.thirdparty.f.e.a;

import dji.thirdparty.f.k;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public final class a {
    static final long a = Long.MIN_VALUE;
    static final long b = Long.MAX_VALUE;

    private a() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> long a(AtomicLongFieldUpdater<T> atomicLongFieldUpdater, T t, long j) {
        long j2;
        do {
            j2 = atomicLongFieldUpdater.get(t);
        } while (!atomicLongFieldUpdater.compareAndSet(t, j2, b(j2, j)));
        return j2;
    }

    public static long a(AtomicLong atomicLong, long j) {
        long j2;
        do {
            j2 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j2, b(j2, j)));
        return j2;
    }

    public static long a(long j, long j2) {
        long j3 = j * j2;
        if (((j | j2) >>> 31) == 0 || j2 == 0 || j3 / j2 == j) {
            return j3;
        }
        return Long.MAX_VALUE;
    }

    public static long b(long j, long j2) {
        long j3 = j + j2;
        if (j3 < 0) {
            return Long.MAX_VALUE;
        }
        return j3;
    }

    public static <T> void a(AtomicLong atomicLong, Queue<T> queue, k<? super T> kVar) {
        long j;
        do {
            j = atomicLong.get();
            if ((j & Long.MIN_VALUE) != 0) {
                return;
            }
        } while (!atomicLong.compareAndSet(j, j | Long.MIN_VALUE));
        if (j != 0) {
            b(atomicLong, queue, kVar);
        }
    }

    public static <T> boolean a(AtomicLong atomicLong, long j, Queue<T> queue, k<? super T> kVar) {
        if (j < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        } else if (j != 0) {
            long j2;
            long j3;
            do {
                j2 = atomicLong.get();
                j3 = Long.MIN_VALUE & j2;
            } while (!atomicLong.compareAndSet(j2, b(Long.MAX_VALUE & j2, j) | j3));
            if (j2 != Long.MIN_VALUE) {
                return j3 == 0;
            } else {
                b(atomicLong, queue, kVar);
                return false;
            }
        } else if ((atomicLong.get() & Long.MIN_VALUE) == 0) {
            return true;
        } else {
            return false;
        }
    }

    static <T> void b(AtomicLong atomicLong, Queue<T> queue, k<? super T> kVar) {
        long j = atomicLong.get();
        long j2 = Long.MIN_VALUE;
        while (true) {
            if (j2 == j) {
                if (j2 == j) {
                    if (!kVar.b()) {
                        if (queue.isEmpty()) {
                            kVar.o_();
                            return;
                        }
                    }
                    return;
                }
                j = atomicLong.get();
                if (j == j2) {
                    j2 = atomicLong.addAndGet(-(j2 & Long.MAX_VALUE));
                    if (j2 != Long.MIN_VALUE) {
                        j = j2;
                        j2 = Long.MIN_VALUE;
                    } else {
                        return;
                    }
                }
                continue;
            } else if (!kVar.b()) {
                Object poll = queue.poll();
                if (poll == null) {
                    kVar.o_();
                    return;
                } else {
                    kVar.a_(poll);
                    j2++;
                }
            } else {
                return;
            }
        }
    }

    public static long b(AtomicLong atomicLong, long j) {
        long j2;
        long j3;
        do {
            j3 = atomicLong.get();
            if (j3 == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            j2 = j3 - j;
            if (j2 < 0) {
                throw new IllegalStateException("More produced than requested: " + j2);
            }
        } while (!atomicLong.compareAndSet(j3, j2));
        return j2;
    }
}
