package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzx;
import com.here.odnp.config.OdnpConfigStatic;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lecho.lib.hellocharts.model.h;

public final class zzli extends GoogleApiClient {
    private final Context mContext;
    private final int zzaaM;
    private final Looper zzaaO;
    private final GoogleApiAvailability zzaaP;
    final com.google.android.gms.common.api.Api.zza<? extends zzqw, zzqx> zzaaQ;
    final com.google.android.gms.common.internal.zzf zzabI;
    final Map<Api<?>, Integer> zzabJ;
    private final Condition zzabY;
    final zzk zzabZ;
    private final Lock zzabt = new ReentrantLock();
    final Queue<zzf<?>> zzaca = new LinkedList();
    private volatile boolean zzacb;
    private long zzacc = OdnpConfigStatic.OEM_MAX_HIGH_POWER_INTERVAL;
    private long zzacd = OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL;
    private final zza zzace;
    zzd zzacf;
    final Map<com.google.android.gms.common.api.Api.zzc<?>, com.google.android.gms.common.api.Api.zzb> zzacg = new HashMap();
    final Map<com.google.android.gms.common.api.Api.zzc<?>, ConnectionResult> zzach = new HashMap();
    Set<Scope> zzaci = new HashSet();
    private volatile zzlj zzacj;
    private ConnectionResult zzack = null;
    private final Set<zzlm<?>> zzacl = Collections.newSetFromMap(new WeakHashMap());
    final Set<zzf<?>> zzacm = Collections.newSetFromMap(new ConcurrentHashMap(16, h.l, 2));
    private com.google.android.gms.common.api.zza zzacn;
    private final zze zzaco = new zze(this) {
        final /* synthetic */ zzli zzacr;

        {
            this.zzacr = r1;
        }

        public void zzc(zzf<?> com_google_android_gms_internal_zzli_zzf_) {
            this.zzacr.zzacm.remove(com_google_android_gms_internal_zzli_zzf_);
            if (com_google_android_gms_internal_zzli_zzf_.zznF() != null && this.zzacr.zzacn != null) {
                this.zzacr.zzacn.remove(com_google_android_gms_internal_zzli_zzf_.zznF().intValue());
            }
        }
    };
    private final ConnectionCallbacks zzacp = new ConnectionCallbacks(this) {
        final /* synthetic */ zzli zzacr;

        {
            this.zzacr = r1;
        }

        public void onConnected(Bundle bundle) {
            this.zzacr.zzabt.lock();
            try {
                this.zzacr.zzacj.onConnected(bundle);
            } finally {
                this.zzacr.zzabt.unlock();
            }
        }

        public void onConnectionSuspended(int i) {
            this.zzacr.zzabt.lock();
            try {
                this.zzacr.zzacj.onConnectionSuspended(i);
            } finally {
                this.zzacr.zzabt.unlock();
            }
        }
    };
    private final com.google.android.gms.common.internal.zzk.zza zzacq = new com.google.android.gms.common.internal.zzk.zza(this) {
        final /* synthetic */ zzli zzacr;

        {
            this.zzacr = r1;
        }

        public boolean isConnected() {
            return this.zzacr.isConnected();
        }

        public Bundle zzmS() {
            return null;
        }
    };

    interface zzf<A extends com.google.android.gms.common.api.Api.zzb> {
        void cancel();

        boolean isReady();

        void zza(zze com_google_android_gms_internal_zzli_zze);

        void zzb(A a) throws DeadObjectException;

        Integer zznF();

        void zznJ();

        int zznK();

        com.google.android.gms.common.api.Api.zzc<A> zznx();

        void zzv(Status status);

        void zzw(Status status);
    }

    static abstract class zzb {
        private final zzlj zzacy;

        protected zzb(zzlj com_google_android_gms_internal_zzlj) {
            this.zzacy = com_google_android_gms_internal_zzlj;
        }

        public final void zzg(zzli com_google_android_gms_internal_zzli) {
            com_google_android_gms_internal_zzli.zzabt.lock();
            try {
                if (com_google_android_gms_internal_zzli.zzacj == this.zzacy) {
                    zznO();
                    com_google_android_gms_internal_zzli.zzabt.unlock();
                }
            } finally {
                com_google_android_gms_internal_zzli.zzabt.unlock();
            }
        }

        protected abstract void zznO();
    }

    interface zze {
        void zzc(zzf<?> com_google_android_gms_internal_zzli_zzf_);
    }

    final class zza extends Handler {
        final /* synthetic */ zzli zzacr;

        zza(zzli com_google_android_gms_internal_zzli, Looper looper) {
            this.zzacr = com_google_android_gms_internal_zzli;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.zzacr.zzod();
                    return;
                case 2:
                    this.zzacr.resume();
                    return;
                case 3:
                    ((zzb) message.obj).zzg(this.zzacr);
                    return;
                case 4:
                    throw ((RuntimeException) message.obj);
                default:
                    Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
                    return;
            }
        }
    }

    private static class zzc implements DeathRecipient, zze {
        private final WeakReference<com.google.android.gms.common.api.zza> zzacA;
        private final WeakReference<IBinder> zzacB;
        private final WeakReference<zzf<?>> zzacz;

        private zzc(zzf com_google_android_gms_internal_zzli_zzf, com.google.android.gms.common.api.zza com_google_android_gms_common_api_zza, IBinder iBinder) {
            this.zzacA = new WeakReference(com_google_android_gms_common_api_zza);
            this.zzacz = new WeakReference(com_google_android_gms_internal_zzli_zzf);
            this.zzacB = new WeakReference(iBinder);
        }

        private void zzoh() {
            zzf com_google_android_gms_internal_zzli_zzf = (zzf) this.zzacz.get();
            com.google.android.gms.common.api.zza com_google_android_gms_common_api_zza = (com.google.android.gms.common.api.zza) this.zzacA.get();
            if (!(com_google_android_gms_common_api_zza == null || com_google_android_gms_internal_zzli_zzf == null)) {
                com_google_android_gms_common_api_zza.remove(com_google_android_gms_internal_zzli_zzf.zznF().intValue());
            }
            IBinder iBinder = (IBinder) this.zzacB.get();
            if (this.zzacB != null) {
                iBinder.unlinkToDeath(this, 0);
            }
        }

        public void binderDied() {
            zzoh();
        }

        public void zzc(zzf<?> com_google_android_gms_internal_zzli_zzf_) {
            zzoh();
        }
    }

    static class zzd extends zzll {
        private WeakReference<zzli> zzacC;

        zzd(zzli com_google_android_gms_internal_zzli) {
            this.zzacC = new WeakReference(com_google_android_gms_internal_zzli);
        }

        public void zzoi() {
            zzli com_google_android_gms_internal_zzli = (zzli) this.zzacC.get();
            if (com_google_android_gms_internal_zzli != null) {
                com_google_android_gms_internal_zzli.resume();
            }
        }
    }

    public zzli(Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, GoogleApiAvailability googleApiAvailability, com.google.android.gms.common.api.Api.zza<? extends zzqw, zzqx> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzqw__com_google_android_gms_internal_zzqx, Map<Api<?>, ApiOptions> map, ArrayList<ConnectionCallbacks> arrayList, ArrayList<OnConnectionFailedListener> arrayList2, int i) {
        this.mContext = context;
        this.zzabZ = new zzk(looper, this.zzacq);
        this.zzaaO = looper;
        this.zzace = new zza(this, looper);
        this.zzaaP = googleApiAvailability;
        this.zzaaM = i;
        this.zzabJ = new HashMap();
        this.zzabY = this.zzabt.newCondition();
        this.zzacj = new zzlh(this);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.zzabZ.registerConnectionCallbacks((ConnectionCallbacks) it.next());
        }
        it = arrayList2.iterator();
        while (it.hasNext()) {
            this.zzabZ.registerConnectionFailedListener((OnConnectionFailedListener) it.next());
        }
        Map zzoM = com_google_android_gms_common_internal_zzf.zzoM();
        for (Api api : map.keySet()) {
            int i2;
            Object obj = map.get(api);
            if (zzoM.get(api) != null) {
                i2 = ((com.google.android.gms.common.internal.zzf.zza) zzoM.get(api)).zzafk ? 1 : 2;
            } else {
                i2 = 0;
            }
            this.zzabJ.put(api, Integer.valueOf(i2));
            this.zzacg.put(api.zznx(), api.zzny() ? zza(api.zznw(), obj, context, looper, com_google_android_gms_common_internal_zzf, this.zzacp, zza(api, i2)) : zza(api.zznv(), obj, context, looper, com_google_android_gms_common_internal_zzf, this.zzacp, zza(api, i2)));
        }
        this.zzabI = com_google_android_gms_common_internal_zzf;
        this.zzaaQ = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzqw__com_google_android_gms_internal_zzqx;
    }

    private void resume() {
        this.zzabt.lock();
        try {
            if (zzoc()) {
                connect();
            }
            this.zzabt.unlock();
        } catch (Throwable th) {
            this.zzabt.unlock();
        }
    }

    private static <C extends com.google.android.gms.common.api.Api.zzb, O> C zza(com.google.android.gms.common.api.Api.zza<C, O> com_google_android_gms_common_api_Api_zza_C__O, Object obj, Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return com_google_android_gms_common_api_Api_zza_C__O.zza(context, looper, com_google_android_gms_common_internal_zzf, obj, connectionCallbacks, onConnectionFailedListener);
    }

    private OnConnectionFailedListener zza(final Api<?> api, final int i) {
        return new OnConnectionFailedListener(this) {
            final /* synthetic */ zzli zzacr;

            public void onConnectionFailed(ConnectionResult connectionResult) {
                this.zzacr.zzabt.lock();
                try {
                    this.zzacr.zzacj.zza(connectionResult, api, i);
                } finally {
                    this.zzacr.zzabt.unlock();
                }
            }
        };
    }

    private static <C extends com.google.android.gms.common.api.Api.zzd, O> zzac zza(com.google.android.gms.common.api.Api.zze<C, O> com_google_android_gms_common_api_Api_zze_C__O, Object obj, Context context, Looper looper, com.google.android.gms.common.internal.zzf com_google_android_gms_common_internal_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zzac(context, looper, com_google_android_gms_common_api_Api_zze_C__O.zznA(), connectionCallbacks, onConnectionFailedListener, com_google_android_gms_common_internal_zzf, com_google_android_gms_common_api_Api_zze_C__O.zzn(obj));
    }

    private void zza(final GoogleApiClient googleApiClient, final zzlo com_google_android_gms_internal_zzlo, final boolean z) {
        zzlx.zzagw.zzb(googleApiClient).setResultCallback(new ResultCallback<Status>(this) {
            final /* synthetic */ zzli zzacr;

            public /* synthetic */ void onResult(Result result) {
                zzo((Status) result);
            }

            public void zzo(Status status) {
                if (status.isSuccess() && this.zzacr.isConnected()) {
                    this.zzacr.reconnect();
                }
                com_google_android_gms_internal_zzlo.zzb((Result) status);
                if (z) {
                    googleApiClient.disconnect();
                }
            }
        });
    }

    private static void zza(zzf<?> com_google_android_gms_internal_zzli_zzf_, com.google.android.gms.common.api.zza com_google_android_gms_common_api_zza, IBinder iBinder) {
        if (com_google_android_gms_internal_zzli_zzf_.isReady()) {
            com_google_android_gms_internal_zzli_zzf_.zza(new zzc(com_google_android_gms_internal_zzli_zzf_, com_google_android_gms_common_api_zza, iBinder));
        } else if (iBinder == null || !iBinder.isBinderAlive()) {
            com_google_android_gms_internal_zzli_zzf_.zza(null);
            com_google_android_gms_internal_zzli_zzf_.cancel();
            com_google_android_gms_common_api_zza.remove(com_google_android_gms_internal_zzli_zzf_.zznF().intValue());
        } else {
            Object com_google_android_gms_internal_zzli_zzc = new zzc(com_google_android_gms_internal_zzli_zzf_, com_google_android_gms_common_api_zza, iBinder);
            com_google_android_gms_internal_zzli_zzf_.zza(com_google_android_gms_internal_zzli_zzc);
            try {
                iBinder.linkToDeath(com_google_android_gms_internal_zzli_zzc, 0);
            } catch (RemoteException e) {
                com_google_android_gms_internal_zzli_zzf_.cancel();
                com_google_android_gms_common_api_zza.remove(com_google_android_gms_internal_zzli_zzf_.zznF().intValue());
            }
        }
    }

    private void zzod() {
        this.zzabt.lock();
        try {
            if (zzof()) {
                connect();
            }
            this.zzabt.unlock();
        } catch (Throwable th) {
            this.zzabt.unlock();
        }
    }

    public ConnectionResult blockingConnect() {
        ConnectionResult connectionResult;
        zzx.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.zzabt.lock();
        try {
            connect();
            while (isConnecting()) {
                this.zzabY.await();
            }
            if (isConnected()) {
                connectionResult = ConnectionResult.zzZY;
            } else if (this.zzack != null) {
                connectionResult = this.zzack;
                this.zzabt.unlock();
            } else {
                connectionResult = new ConnectionResult(13, null);
                this.zzabt.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            connectionResult = new ConnectionResult(15, null);
        } finally {
            this.zzabt.unlock();
        }
        return connectionResult;
    }

    public ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        ConnectionResult connectionResult;
        zzx.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        zzx.zzb((Object) timeUnit, (Object) "TimeUnit must not be null");
        this.zzabt.lock();
        try {
            connect();
            long toNanos = timeUnit.toNanos(j);
            while (isConnecting()) {
                toNanos = this.zzabY.awaitNanos(toNanos);
                if (toNanos <= 0) {
                    connectionResult = new ConnectionResult(14, null);
                    break;
                }
            }
            if (isConnected()) {
                connectionResult = ConnectionResult.zzZY;
                this.zzabt.unlock();
            } else if (this.zzack != null) {
                connectionResult = this.zzack;
                this.zzabt.unlock();
            } else {
                connectionResult = new ConnectionResult(13, null);
                this.zzabt.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            connectionResult = new ConnectionResult(15, null);
        } finally {
            this.zzabt.unlock();
        }
        return connectionResult;
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzx.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        final zzlo com_google_android_gms_internal_zzlo = new zzlo((GoogleApiClient) this);
        if (this.zzacg.containsKey(zzlx.zzRk)) {
            zza((GoogleApiClient) this, com_google_android_gms_internal_zzlo, false);
        } else {
            final AtomicReference atomicReference = new AtomicReference();
            ConnectionCallbacks anonymousClass5 = new ConnectionCallbacks(this) {
                final /* synthetic */ zzli zzacr;

                public void onConnected(Bundle bundle) {
                    this.zzacr.zza((GoogleApiClient) atomicReference.get(), com_google_android_gms_internal_zzlo, true);
                }

                public void onConnectionSuspended(int i) {
                }
            };
            GoogleApiClient build = new Builder(this.mContext).addApi(zzlx.API).addConnectionCallbacks(anonymousClass5).addOnConnectionFailedListener(new OnConnectionFailedListener(this) {
                final /* synthetic */ zzli zzacr;

                public void onConnectionFailed(ConnectionResult connectionResult) {
                    com_google_android_gms_internal_zzlo.zzb(new Status(8));
                }
            }).setHandler(this.zzace).build();
            atomicReference.set(build);
            build.connect();
        }
        return com_google_android_gms_internal_zzlo;
    }

    public void connect() {
        this.zzabt.lock();
        try {
            this.zzacj.connect();
        } finally {
            this.zzabt.unlock();
        }
    }

    public void disconnect() {
        this.zzabt.lock();
        try {
            zzof();
            this.zzacj.disconnect();
        } finally {
            this.zzabt.unlock();
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mState=").append(this.zzacj.getName());
        printWriter.append(" mResuming=").print(this.zzacb);
        printWriter.append(" mWorkQueue.size()=").print(this.zzaca.size());
        printWriter.append(" mUnconsumedRunners.size()=").println(this.zzacm.size());
        String str2 = str + "  ";
        for (Api api : this.zzabJ.keySet()) {
            printWriter.append(str).append(api.getName()).println(":");
            ((com.google.android.gms.common.api.Api.zzb) this.zzacg.get(api.zznx())).dump(str2, fileDescriptor, printWriter, strArr);
        }
    }

    public ConnectionResult getConnectionResult(Api<?> api) {
        com.google.android.gms.common.api.Api.zzc zznx = api.zznx();
        this.zzabt.lock();
        try {
            if (!isConnected() && !zzoc()) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.zzacg.containsKey(zznx)) {
                ConnectionResult connectionResult;
                if (((com.google.android.gms.common.api.Api.zzb) this.zzacg.get(zznx)).isConnected()) {
                    connectionResult = ConnectionResult.zzZY;
                } else if (this.zzach.containsKey(zznx)) {
                    connectionResult = (ConnectionResult) this.zzach.get(zznx);
                    this.zzabt.unlock();
                } else {
                    Log.i("GoogleApiClientImpl", zzog());
                    Log.wtf("GoogleApiClientImpl", api.getName() + " requested in getConnectionResult" + " is not connected but is not present in the failed connections map", new Exception());
                    connectionResult = new ConnectionResult(8, null);
                    this.zzabt.unlock();
                }
                return connectionResult;
            } else {
                this.zzabt.unlock();
                throw new IllegalArgumentException(api.getName() + " was never registered with GoogleApiClient");
            }
        } finally {
            this.zzabt.unlock();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public Looper getLooper() {
        return this.zzaaO;
    }

    public int getSessionId() {
        return System.identityHashCode(this);
    }

    public boolean hasConnectedApi(Api<?> api) {
        com.google.android.gms.common.api.Api.zzb com_google_android_gms_common_api_Api_zzb = (com.google.android.gms.common.api.Api.zzb) this.zzacg.get(api.zznx());
        return com_google_android_gms_common_api_Api_zzb != null && com_google_android_gms_common_api_Api_zzb.isConnected();
    }

    public boolean isConnected() {
        return this.zzacj instanceof zzlf;
    }

    public boolean isConnecting() {
        return this.zzacj instanceof zzlg;
    }

    public boolean isConnectionCallbacksRegistered(ConnectionCallbacks connectionCallbacks) {
        return this.zzabZ.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener onConnectionFailedListener) {
        return this.zzabZ.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public void reconnect() {
        disconnect();
        connect();
    }

    public void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.zzabZ.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzabZ.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public void stopAutoManage(final FragmentActivity fragmentActivity) {
        if (this.zzaaM >= 0) {
            zzlp zza = zzlp.zza(fragmentActivity);
            if (zza == null) {
                new Handler(this.mContext.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ zzli zzacr;

                    public void run() {
                        if (!fragmentActivity.isFinishing() && !fragmentActivity.getSupportFragmentManager().isDestroyed()) {
                            zzlp.zzb(fragmentActivity).zzbp(this.zzacr.zzaaM);
                        }
                    }
                });
                return;
            } else {
                zza.zzbp(this.zzaaM);
                return;
            }
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    public void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks) {
        this.zzabZ.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzabZ.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    public <C extends com.google.android.gms.common.api.Api.zzb> C zza(com.google.android.gms.common.api.Api.zzc<C> com_google_android_gms_common_api_Api_zzc_C) {
        Object obj = (com.google.android.gms.common.api.Api.zzb) this.zzacg.get(com_google_android_gms_common_api_Api_zzc_C);
        zzx.zzb(obj, (Object) "Appropriate Api was not requested.");
        return obj;
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, R extends Result, T extends com.google.android.gms.internal.zzlb.zza<R, A>> T zza(T t) {
        zzx.zzb(t.zznx() != null, (Object) "This task can not be enqueued (it's probably a Batch or malformed)");
        zzx.zzb(this.zzacg.containsKey(t.zznx()), (Object) "GoogleApiClient is not configured to use the API required for this call.");
        this.zzabt.lock();
        try {
            T zza = this.zzacj.zza(t);
            return zza;
        } finally {
            this.zzabt.unlock();
        }
    }

    void zza(zzb com_google_android_gms_internal_zzli_zzb) {
        this.zzace.sendMessage(this.zzace.obtainMessage(3, com_google_android_gms_internal_zzli_zzb));
    }

    void zza(RuntimeException runtimeException) {
        this.zzace.sendMessage(this.zzace.obtainMessage(4, runtimeException));
    }

    public boolean zza(Api<?> api) {
        return this.zzacg.containsKey(api.zznx());
    }

    public <A extends com.google.android.gms.common.api.Api.zzb, T extends com.google.android.gms.internal.zzlb.zza<? extends Result, A>> T zzb(T t) {
        zzx.zzb(t.zznx() != null, (Object) "This task can not be executed (it's probably a Batch or malformed)");
        this.zzabt.lock();
        try {
            if (zzoc()) {
                this.zzaca.add(t);
                while (!this.zzaca.isEmpty()) {
                    zzf com_google_android_gms_internal_zzli_zzf = (zzf) this.zzaca.remove();
                    zzb(com_google_android_gms_internal_zzli_zzf);
                    com_google_android_gms_internal_zzli_zzf.zzv(Status.zzabd);
                }
            } else {
                t = this.zzacj.zzb(t);
                this.zzabt.unlock();
            }
            return t;
        } finally {
            this.zzabt.unlock();
        }
    }

    <A extends com.google.android.gms.common.api.Api.zzb> void zzb(zzf<A> com_google_android_gms_internal_zzli_zzf_A) {
        this.zzacm.add(com_google_android_gms_internal_zzli_zzf_A);
        com_google_android_gms_internal_zzli_zzf_A.zza(this.zzaco);
    }

    void zzg(ConnectionResult connectionResult) {
        this.zzabt.lock();
        try {
            this.zzack = connectionResult;
            this.zzacj = new zzlh(this);
            this.zzacj.begin();
            this.zzabY.signalAll();
        } finally {
            this.zzabt.unlock();
        }
    }

    void zznY() {
        for (zzf com_google_android_gms_internal_zzli_zzf : this.zzacm) {
            com_google_android_gms_internal_zzli_zzf.zza(null);
            if (com_google_android_gms_internal_zzli_zzf.zznF() == null) {
                com_google_android_gms_internal_zzli_zzf.cancel();
            } else {
                com_google_android_gms_internal_zzli_zzf.zznJ();
                zza(com_google_android_gms_internal_zzli_zzf, this.zzacn, zza(com_google_android_gms_internal_zzli_zzf.zznx()).zznz());
            }
        }
        this.zzacm.clear();
        for (zzlm clear : this.zzacl) {
            clear.clear();
        }
        this.zzacl.clear();
    }

    void zznZ() {
        for (com.google.android.gms.common.api.Api.zzb disconnect : this.zzacg.values()) {
            disconnect.disconnect();
        }
    }

    public <L> zzlm<L> zzo(L l) {
        zzx.zzb((Object) l, (Object) "Listener must not be null");
        this.zzabt.lock();
        try {
            zzlm<L> com_google_android_gms_internal_zzlm = new zzlm(this.zzaaO, l);
            this.zzacl.add(com_google_android_gms_internal_zzlm);
            return com_google_android_gms_internal_zzlm;
        } finally {
            this.zzabt.unlock();
        }
    }

    void zzoa() {
        this.zzabt.lock();
        try {
            this.zzacj = new zzlg(this, this.zzabI, this.zzabJ, this.zzaaP, this.zzaaQ, this.zzabt, this.mContext);
            this.zzacj.begin();
            this.zzabY.signalAll();
        } finally {
            this.zzabt.unlock();
        }
    }

    void zzob() {
        this.zzabt.lock();
        try {
            zzof();
            this.zzacj = new zzlf(this);
            this.zzacj.begin();
            this.zzabY.signalAll();
        } finally {
            this.zzabt.unlock();
        }
    }

    boolean zzoc() {
        return this.zzacb;
    }

    void zzoe() {
        if (!zzoc()) {
            this.zzacb = true;
            if (this.zzacf == null) {
                this.zzacf = (zzd) zzll.zza(this.mContext.getApplicationContext(), new zzd(this), this.zzaaP);
            }
            this.zzace.sendMessageDelayed(this.zzace.obtainMessage(1), this.zzacc);
            this.zzace.sendMessageDelayed(this.zzace.obtainMessage(2), this.zzacd);
        }
    }

    boolean zzof() {
        if (!zzoc()) {
            return false;
        }
        this.zzacb = false;
        this.zzace.removeMessages(2);
        this.zzace.removeMessages(1);
        if (this.zzacf != null) {
            this.zzacf.unregister();
            this.zzacf = null;
        }
        return true;
    }

    String zzog() {
        Writer stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
}
