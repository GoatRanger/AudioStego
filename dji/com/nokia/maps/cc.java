package com.nokia.maps;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class cc extends Thread {
    private static final String a = cc.class.getName();
    private static volatile long b = 100;
    private static final Semaphore c = new Semaphore(0, true);
    private static int e = 0;
    private final int d = 3;
    private MapsEngine f;
    private boolean g = true;
    private List<a> h = new CopyOnWriteArrayList();

    public interface a {
        void a();

        void b();
    }

    public cc(MapsEngine mapsEngine) {
        this.f = mapsEngine;
        setName("MapsDataDownload");
        setPriority(3);
        c.release(e);
        bj.a(a, "Constructor %d", new Object[]{Integer.valueOf(e)});
    }

    static void a() {
        synchronized (cc.class) {
            if (e > 0) {
                e--;
            }
            bj.e("MapsDataDownload", "pausePolling - s_pollCounter=%d", new Object[]{Integer.valueOf(e)});
        }
    }

    static void b() {
        synchronized (cc.class) {
            e++;
            bj.e("MapsDataDownload", "resumePolling - s_pollCounter=%d", new Object[]{Integer.valueOf(e)});
        }
        b = 100;
        c.release();
    }

    public void run() {
        while (true) {
            try {
                c.acquire();
                c.drainPermits();
                sleep(b);
                synchronized (cc.class) {
                    if (e == 0) {
                        bj.e(a, "Halt polling ...", new Object[0]);
                    } else {
                        Object obj = !this.f.pollMapData() ? 1 : null;
                        String str = a;
                        String str2 = "Maps Data Thread tick - m_mapEngine.pollMapData()=%s";
                        Object[] objArr = new Object[1];
                        objArr[0] = obj != null ? "downloading" : "idle";
                        bj.a(str, str2, objArr);
                        if (obj != null) {
                            bj.a(a, "<--Poll false-->", new Object[0]);
                            c();
                            this.g = true;
                            b = 50;
                        } else {
                            bj.a(a, "<--Poll true--> anything? %b", new Object[]{Boolean.valueOf(this.g)});
                            if (this.g) {
                                this.g = false;
                                b = 100;
                                d();
                            } else {
                                b = 1000;
                            }
                        }
                        c.release();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void c() {
        ez.a(new Runnable(this) {
            final /* synthetic */ cc a;

            {
                this.a = r1;
            }

            public void run() {
                for (a aVar : this.a.h) {
                    if (aVar != null) {
                        aVar.a();
                    }
                }
            }
        });
    }

    private void d() {
        ez.a(new Runnable(this) {
            final /* synthetic */ cc a;

            {
                this.a = r1;
            }

            public void run() {
                for (a aVar : this.a.h) {
                    if (aVar != null) {
                        aVar.b();
                    }
                }
            }
        });
    }

    public void a(a aVar) {
        if (!this.h.contains(aVar)) {
            this.h.add(aVar);
        }
    }

    public void b(a aVar) {
        this.h.remove(aVar);
    }
}
