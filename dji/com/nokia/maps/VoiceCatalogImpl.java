package com.nokia.maps;

import com.here.android.mpa.guidance.VoiceCatalog;
import com.here.android.mpa.guidance.VoiceCatalog.Error;
import com.here.android.mpa.guidance.VoiceCatalog.OnDownloadDoneListener;
import com.here.android.mpa.guidance.VoiceCatalog.OnProgressListener;
import com.here.android.mpa.guidance.VoicePackage;
import com.here.android.mpa.guidance.VoiceSkin;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.io.File;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@HybridPlus
public class VoiceCatalogImpl extends BaseNativeObject {
    private static k<VoiceCatalog, VoiceCatalogImpl> a = null;
    private static am<VoiceCatalog, VoiceCatalogImpl> b = null;
    private static volatile VoiceCatalogImpl c = null;
    private static final Object d = new Object();
    private a e;
    private OnProgressListener f = null;
    private OnDownloadDoneListener g = null;
    private OnDownloadDoneListener h = null;

    private class a extends Thread {
        final /* synthetic */ VoiceCatalogImpl a;
        private long b = 50;
        private boolean c = false;
        private final Semaphore d = new Semaphore(0, true);

        public a(VoiceCatalogImpl voiceCatalogImpl) {
            this.a = voiceCatalogImpl;
            setName("VoiceCatalog");
            setPriority(1);
            start();
        }

        public void a() {
            this.d.release();
        }

        public void run() {
            while (true) {
                try {
                    if (this.d.tryAcquire(1, TimeUnit.MILLISECONDS)) {
                        this.d.drainPermits();
                        if (!this.c) {
                            if (!this.a.pollDownloader()) {
                                synchronized (this.a) {
                                    if (this.a.e == this) {
                                        this.a.e = null;
                                    }
                                }
                                this.d.drainPermits();
                                break;
                            }
                            sleep(this.b);
                            this.d.release();
                        } else {
                            return;
                        }
                    }
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private native void createVoiceCatalogNative();

    private native void destroyVoiceCatalogNative();

    private native boolean downloadCatalogNative();

    private native boolean downloadVoiceNative(long j);

    private native List<VoicePackageImpl> getCatalogListNative();

    private native VoiceSkinImpl getLocalVoiceSkinNative(long j);

    private native List<VoiceSkinImpl> getLocalVoiceSkinsNative();

    private native boolean isLocalVoiceSkinNative(long j);

    private native synchronized boolean pollDownloader();

    private native void refreshNative();

    private native boolean setUseStagingServerNative(boolean z);

    public native synchronized void cancel();

    public native boolean isLocalCatalogAvailable();

    static {
        ce.a(VoiceCatalog.class);
    }

    public static void a(k<VoiceCatalog, VoiceCatalogImpl> kVar, am<VoiceCatalog, VoiceCatalogImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static VoiceCatalogImpl a(VoiceCatalog voiceCatalog) {
        return (VoiceCatalogImpl) a.a(voiceCatalog);
    }

    public static VoiceCatalogImpl a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new VoiceCatalogImpl();
                }
            }
        }
        return c;
    }

    public VoiceCatalogImpl() {
        createVoiceCatalogNative();
    }

    public synchronized boolean a(long j) {
        boolean z;
        if (j < 0) {
            z = false;
        } else {
            z = isLocalVoiceSkinNative(j);
        }
        return z;
    }

    public synchronized VoiceSkin b(long j) {
        VoiceSkin a;
        if (isLocalVoiceSkinNative(j)) {
            a = VoiceSkinImpl.a(getLocalVoiceSkinNative(j));
        } else {
            a = null;
        }
        return a;
    }

    public synchronized List<VoiceSkin> b() {
        return VoiceSkinImpl.a(getLocalVoiceSkinsNative());
    }

    public synchronized List<VoicePackage> c() {
        return VoicePackageImpl.a(getCatalogListNative());
    }

    public synchronized boolean a(OnDownloadDoneListener onDownloadDoneListener) {
        boolean z = false;
        synchronized (this) {
            this.g = onDownloadDoneListener;
            String e = MapSettings.e();
            if (e != null) {
                File file = new File(e);
                if (file.exists() || file.mkdirs()) {
                    z = downloadCatalogNative();
                    f();
                }
            }
        }
        return z;
    }

    public synchronized boolean a(long j, OnDownloadDoneListener onDownloadDoneListener) {
        boolean z;
        String e = MapSettings.e();
        if (e == null) {
            z = false;
        } else {
            this.h = onDownloadDoneListener;
            for (VoicePackage voicePackage : c()) {
                if (voicePackage.getId() == j) {
                    if (!a((long) (voicePackage.getContentSize() * 1048576.0f), new File(e))) {
                        this.h.onDownloadDone(Error.NOT_ENOUGH_DISK_SPACE);
                        z = false;
                    }
                    z = downloadVoiceNative(j);
                    f();
                }
            }
            z = downloadVoiceNative(j);
            f();
        }
        return z;
    }

    private static boolean a(long j, File file) {
        try {
            if (file.getUsableSpace() - j > 268435456) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean c(long j) {
        boolean z;
        z = false;
        VoiceSkin b = b(j);
        if (b != null) {
            z = a(b);
        }
        return z;
    }

    public synchronized boolean a(VoiceSkin voiceSkin) {
        boolean delete;
        long id = voiceSkin.getId();
        delete = voiceSkin.delete();
        d();
        bj.e(VoiceCatalogImpl.class.getSimpleName(), "skinId=%d returns %B", new Object[]{Long.valueOf(id), Boolean.valueOf(delete)});
        return delete;
    }

    public synchronized void d() {
        refreshNative();
    }

    public void a(OnProgressListener onProgressListener) {
        this.f = onProgressListener;
    }

    @HybridPlusNative
    private void progress(final int i) {
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            if (this.f != null) {
                this.f.onProgress(i);
            }
        } else if (this.f != null) {
            ez.a(new Runnable(this) {
                final /* synthetic */ VoiceCatalogImpl b;

                public void run() {
                    this.b.f.onProgress(i);
                }
            });
        }
    }

    @HybridPlusNative
    private void catalogDownloadDone(final int i) {
        for (VoicePackage isTts : c()) {
            if (isTts.isTts()) {
                NavigationManagerImpl.a().c().a();
                break;
            }
        }
        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
            if (this.g != null) {
                d();
                this.g.onDownloadDone(c(i));
            }
        } else if (this.g != null) {
            ez.a(new Runnable(this) {
                final /* synthetic */ VoiceCatalogImpl b;

                public void run() {
                    this.b.d();
                    this.b.g.onDownloadDone(VoiceCatalogImpl.c(i));
                }
            });
        }
        synchronized (this) {
            this.e = null;
        }
    }

    @HybridPlusNative
    private void packageDownloadDone(final int i) {
        if (e()) {
            if (MapSettings.k() == MapSettings$b.EWorkerThread) {
                if (this.h != null) {
                    b(i);
                }
            } else if (this.h != null) {
                ez.a(new Runnable(this) {
                    final /* synthetic */ VoiceCatalogImpl b;

                    public void run() {
                        this.b.b(i);
                    }
                });
            }
            synchronized (this) {
                this.e = null;
            }
            return;
        }
        bj.b(VoiceCatalogImpl.class.getSimpleName(), "Receives callback from JNI layer packageDownloadDone(%d) even when there's no outstanding downloads.", new Object[]{Integer.valueOf(i)});
    }

    private void b(int i) {
        d();
        this.h.onDownloadDone(c(i));
    }

    public synchronized boolean e() {
        return this.e != null;
    }

    protected void finalize() {
        destroyVoiceCatalogNative();
    }

    private synchronized void f() {
        if (this.e == null) {
            this.e = new a(this);
        }
        this.e.a();
    }

    private static Error c(int i) {
        switch (i) {
            case 0:
                return Error.NONE;
            default:
                return Error.UNKNOWN;
        }
    }
}
