package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public abstract class zzlc<R extends Result> extends PendingResult<R> {
    private boolean zzL;
    private volatile R zzaaX;
    private final Object zzabh = new Object();
    protected final zza<R> zzabi;
    private final ArrayList<com.google.android.gms.common.api.PendingResult.zza> zzabj = new ArrayList();
    private ResultCallback<? super R> zzabk;
    private volatile boolean zzabl;
    private boolean zzabm;
    private zzq zzabn;
    private Integer zzabo;
    private volatile zzlq<R> zzabp;
    private final CountDownLatch zzoS = new CountDownLatch(1);

    public static class zza<R extends Result> extends Handler {
        public zza() {
            this(Looper.getMainLooper());
        }

        public zza(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    Pair pair = (Pair) message.obj;
                    zzb((ResultCallback) pair.first, (Result) pair.second);
                    return;
                case 2:
                    ((zzlc) message.obj).zzw(Status.zzabe);
                    return;
                default:
                    Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                    return;
            }
        }

        public void zza(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        public void zza(zzlc<R> com_google_android_gms_internal_zzlc_R, long j) {
            sendMessageDelayed(obtainMessage(2, com_google_android_gms_internal_zzlc_R), j);
        }

        protected void zzb(ResultCallback<? super R> resultCallback, R r) {
            try {
                resultCallback.onResult(r);
            } catch (RuntimeException e) {
                zzlc.zzd(r);
                throw e;
            }
        }

        public void zznM() {
            removeMessages(2);
        }
    }

    @Deprecated
    protected zzlc(Looper looper) {
        this.zzabi = new zza(looper);
    }

    protected zzlc(GoogleApiClient googleApiClient) {
        this.zzabi = new zza(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    private R get() {
        R r;
        boolean z = true;
        synchronized (this.zzabh) {
            if (this.zzabl) {
                z = false;
            }
            zzx.zza(z, (Object) "Result has already been consumed.");
            zzx.zza(isReady(), (Object) "Result is not ready.");
            r = this.zzaaX;
            this.zzaaX = null;
            this.zzabk = null;
            this.zzabl = true;
        }
        zznL();
        return r;
    }

    private void zzc(R r) {
        this.zzaaX = r;
        this.zzabn = null;
        this.zzoS.countDown();
        Status status = this.zzaaX.getStatus();
        if (this.zzabk != null) {
            this.zzabi.zznM();
            if (!this.zzL) {
                this.zzabi.zza(this.zzabk, get());
            }
        }
        Iterator it = this.zzabj.iterator();
        while (it.hasNext()) {
            ((com.google.android.gms.common.api.PendingResult.zza) it.next()).zzt(status);
        }
        this.zzabj.clear();
    }

    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                Log.w("BasePendingResult", "Unable to release " + result, e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzx.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "await must not be called on the UI thread");
        zzx.zza(!this.zzabl, (Object) "Result has already been consumed");
        if (this.zzabp != null) {
            z = false;
        }
        zzx.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            this.zzoS.await();
        } catch (InterruptedException e) {
            zzw(Status.zzabc);
        }
        zzx.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        boolean z2 = j <= 0 || Looper.myLooper() != Looper.getMainLooper();
        zzx.zza(z2, (Object) "await must not be called on the UI thread when time is greater than zero.");
        zzx.zza(!this.zzabl, (Object) "Result has already been consumed.");
        if (this.zzabp != null) {
            z = false;
        }
        zzx.zza(z, (Object) "Cannot await if then() has been called.");
        try {
            if (!this.zzoS.await(j, timeUnit)) {
                zzw(Status.zzabe);
            }
        } catch (InterruptedException e) {
            zzw(Status.zzabc);
        }
        zzx.zza(isReady(), (Object) "Result is not ready.");
        return get();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
        r2 = this;
        r1 = r2.zzabh;
        monitor-enter(r1);
        r0 = r2.zzL;	 Catch:{ all -> 0x002c }
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.zzabl;	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.zzabn;	 Catch:{ all -> 0x002c }
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r2.zzabn;	 Catch:{ RemoteException -> 0x002f }
        r0.cancel();	 Catch:{ RemoteException -> 0x002f }
    L_0x0016:
        r0 = r2.zzaaX;	 Catch:{ all -> 0x002c }
        zzd(r0);	 Catch:{ all -> 0x002c }
        r0 = 0;
        r2.zzabk = r0;	 Catch:{ all -> 0x002c }
        r0 = 1;
        r2.zzL = r0;	 Catch:{ all -> 0x002c }
        r0 = com.google.android.gms.common.api.Status.zzabf;	 Catch:{ all -> 0x002c }
        r0 = r2.zzb(r0);	 Catch:{ all -> 0x002c }
        r2.zzc(r0);	 Catch:{ all -> 0x002c }
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
        goto L_0x000c;
    L_0x002c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x002c }
        throw r0;
    L_0x002f:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlc.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzabh) {
            z = this.zzL;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzoS.getCount() == 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r5) {
        /*
        r4 = this;
        r1 = 1;
        r2 = 0;
        r0 = r4.zzabl;
        if (r0 != 0) goto L_0x0020;
    L_0x0006:
        r0 = r1;
    L_0x0007:
        r3 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzx.zza(r0, r3);
        r3 = r4.zzabh;
        monitor-enter(r3);
        r0 = r4.zzabp;	 Catch:{ all -> 0x0035 }
        if (r0 != 0) goto L_0x0022;
    L_0x0013:
        r0 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzx.zza(r1, r0);	 Catch:{ all -> 0x0035 }
        r0 = r4.isCanceled();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0024;
    L_0x001e:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
    L_0x001f:
        return;
    L_0x0020:
        r0 = r2;
        goto L_0x0007;
    L_0x0022:
        r1 = r2;
        goto L_0x0013;
    L_0x0024:
        r0 = r4.isReady();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0038;
    L_0x002a:
        r0 = r4.zzabi;	 Catch:{ all -> 0x0035 }
        r1 = r4.get();	 Catch:{ all -> 0x0035 }
        r0.zza(r5, r1);	 Catch:{ all -> 0x0035 }
    L_0x0033:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        goto L_0x001f;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        throw r0;
    L_0x0038:
        r4.zzabk = r5;	 Catch:{ all -> 0x0035 }
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlc.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r1 = 1;
        r2 = 0;
        r0 = r6.zzabl;
        if (r0 != 0) goto L_0x0020;
    L_0x0006:
        r0 = r1;
    L_0x0007:
        r3 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzx.zza(r0, r3);
        r3 = r6.zzabh;
        monitor-enter(r3);
        r0 = r6.zzabp;	 Catch:{ all -> 0x0035 }
        if (r0 != 0) goto L_0x0022;
    L_0x0013:
        r0 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzx.zza(r1, r0);	 Catch:{ all -> 0x0035 }
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0024;
    L_0x001e:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
    L_0x001f:
        return;
    L_0x0020:
        r0 = r2;
        goto L_0x0007;
    L_0x0022:
        r1 = r2;
        goto L_0x0013;
    L_0x0024:
        r0 = r6.isReady();	 Catch:{ all -> 0x0035 }
        if (r0 == 0) goto L_0x0038;
    L_0x002a:
        r0 = r6.zzabi;	 Catch:{ all -> 0x0035 }
        r1 = r6.get();	 Catch:{ all -> 0x0035 }
        r0.zza(r7, r1);	 Catch:{ all -> 0x0035 }
    L_0x0033:
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        goto L_0x001f;
    L_0x0035:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0035 }
        throw r0;
    L_0x0038:
        r6.zzabk = r7;	 Catch:{ all -> 0x0035 }
        r0 = r6.zzabi;	 Catch:{ all -> 0x0035 }
        r4 = r10.toMillis(r8);	 Catch:{ all -> 0x0035 }
        r0.zza(r6, r4);	 Catch:{ all -> 0x0035 }
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlc.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public final void zza(com.google.android.gms.common.api.PendingResult.zza com_google_android_gms_common_api_PendingResult_zza) {
        boolean z = true;
        zzx.zza(!this.zzabl, (Object) "Result has already been consumed.");
        if (com_google_android_gms_common_api_PendingResult_zza == null) {
            z = false;
        }
        zzx.zzb(z, (Object) "Callback cannot be null.");
        synchronized (this.zzabh) {
            if (isReady()) {
                com_google_android_gms_common_api_PendingResult_zza.zzt(this.zzaaX.getStatus());
            } else {
                this.zzabj.add(com_google_android_gms_common_api_PendingResult_zza);
            }
        }
    }

    protected final void zza(zzq com_google_android_gms_common_internal_zzq) {
        synchronized (this.zzabh) {
            this.zzabn = com_google_android_gms_common_internal_zzq;
        }
    }

    protected abstract R zzb(Status status);

    public final void zzb(R r) {
        boolean z = true;
        synchronized (this.zzabh) {
            if (this.zzabm || this.zzL) {
                zzd(r);
                return;
            }
            zzx.zza(!isReady(), (Object) "Results have already been set");
            if (this.zzabl) {
                z = false;
            }
            zzx.zza(z, (Object) "Result has already been consumed");
            zzc(r);
        }
    }

    public Integer zznF() {
        return this.zzabo;
    }

    protected void zznL() {
    }

    public final void zzw(Status status) {
        synchronized (this.zzabh) {
            if (!isReady()) {
                zzb(zzb(status));
                this.zzabm = true;
            }
        }
    }
}
