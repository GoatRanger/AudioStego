package com.flurry.sdk;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class im<T extends kc> {
    private static final String a = im.class.getSimpleName();
    private final ie<Object, T> b = new ie();
    private final HashMap<T, Object> c = new HashMap();
    private final HashMap<T, Future<?>> d = new HashMap();
    private final ThreadPoolExecutor e;

    public im(String str, int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        this.e = new ThreadPoolExecutor(this, i, i2, j, timeUnit, blockingQueue) {
            final /* synthetic */ im a;

            protected void beforeExecute(Thread thread, Runnable runnable) {
                super.beforeExecute(thread, runnable);
                final kc a = this.a.a(runnable);
                if (a != null) {
                    new kb(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void a() {
                            a.k();
                        }
                    }.run();
                }
            }

            protected void afterExecute(Runnable runnable, Throwable th) {
                super.afterExecute(runnable, th);
                final kc a = this.a.a(runnable);
                if (a != null) {
                    synchronized (this.a.d) {
                        this.a.d.remove(a);
                    }
                    this.a.b(a);
                    new kb(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void a() {
                            a.l();
                        }
                    }.run();
                }
            }

            protected <V> RunnableFuture<V> newTaskFor(Callable<V> callable) {
                throw new UnsupportedOperationException("Callable not supported");
            }

            protected <V> RunnableFuture<V> newTaskFor(Runnable runnable, V v) {
                RunnableFuture ilVar = new il(runnable, v);
                synchronized (this.a.d) {
                    this.a.d.put((kc) runnable, ilVar);
                }
                return ilVar;
            }
        };
        this.e.setRejectedExecutionHandler(new DiscardPolicy(this) {
            final /* synthetic */ im a;

            {
                this.a = r1;
            }

            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                super.rejectedExecution(runnable, threadPoolExecutor);
                final kc a = this.a.a(runnable);
                if (a != null) {
                    synchronized (this.a.d) {
                        this.a.d.remove(a);
                    }
                    this.a.b(a);
                    new kb(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void a() {
                            a.m();
                        }
                    }.run();
                }
            }
        });
        this.e.setThreadFactory(new js(str, 1));
    }

    public synchronized void a(Object obj, T t) {
        if (!(obj == null || t == null)) {
            b(obj, t);
            this.e.submit(t);
        }
    }

    public synchronized void a(Object obj) {
        if (obj != null) {
            Collection<kc> hashSet = new HashSet();
            hashSet.addAll(this.b.a(obj));
            for (kc a : hashSet) {
                a(a);
            }
        }
    }

    public synchronized void a(final T t) {
        if (t != null) {
            Future future;
            synchronized (this.d) {
                future = (Future) this.d.remove(t);
            }
            b((kc) t);
            if (future != null) {
                future.cancel(true);
            }
            new kb(this) {
                final /* synthetic */ im b;

                public void a() {
                    t.i();
                }
            }.run();
        }
    }

    public synchronized void c() {
        Set<Object> hashSet = new HashSet();
        hashSet.addAll(this.b.c());
        for (Object a : hashSet) {
            a(a);
        }
    }

    public synchronized long b(Object obj) {
        long j;
        if (obj == null) {
            j = 0;
        } else {
            j = (long) this.b.a(obj).size();
        }
        return j;
    }

    private synchronized void b(Object obj, T t) {
        this.b.a(obj, (Object) t);
        this.c.put(t, obj);
    }

    private synchronized void b(T t) {
        c(this.c.get(t), t);
    }

    private synchronized void c(Object obj, T t) {
        this.b.b(obj, t);
        this.c.remove(t);
    }

    private T a(Runnable runnable) {
        if (runnable instanceof il) {
            return (kc) ((il) runnable).a();
        }
        if (runnable instanceof kc) {
            return (kc) runnable;
        }
        in.a(6, a, "Unknown runnable class: " + runnable.getClass().getName());
        return null;
    }
}
