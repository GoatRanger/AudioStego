package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import com.here.odnp.config.OdnpConfigStatic;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

final class zzm extends zzl implements Callback {
    private final Handler mHandler;
    private final HashMap<zza, zzb> zzafY = new HashMap();
    private final com.google.android.gms.common.stats.zzb zzafZ;
    private final long zzaga;
    private final Context zzqZ;

    private static final class zza {
        private final String zzPp;
        private final ComponentName zzagb;

        public zza(ComponentName componentName) {
            this.zzPp = null;
            this.zzagb = (ComponentName) zzx.zzw(componentName);
        }

        public zza(String str) {
            this.zzPp = zzx.zzcr(str);
            this.zzagb = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_common_internal_zzm_zza = (zza) obj;
            return zzw.equal(this.zzPp, com_google_android_gms_common_internal_zzm_zza.zzPp) && zzw.equal(this.zzagb, com_google_android_gms_common_internal_zzm_zza.zzagb);
        }

        public int hashCode() {
            return zzw.hashCode(this.zzPp, this.zzagb);
        }

        public String toString() {
            return this.zzPp == null ? this.zzagb.flattenToString() : this.zzPp;
        }

        public Intent zzpm() {
            return this.zzPp != null ? new Intent(this.zzPp).setPackage("com.google.android.gms") : new Intent().setComponent(this.zzagb);
        }
    }

    private final class zzb {
        private int mState = 2;
        private IBinder zzaeJ;
        private ComponentName zzagb;
        private final zza zzagc = new zza(this);
        private final Set<ServiceConnection> zzagd = new HashSet();
        private boolean zzage;
        private final zza zzagf;
        final /* synthetic */ zzm zzagg;

        public class zza implements ServiceConnection {
            final /* synthetic */ zzb zzagh;

            public zza(zzb com_google_android_gms_common_internal_zzm_zzb) {
                this.zzagh = com_google_android_gms_common_internal_zzm_zzb;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                synchronized (this.zzagh.zzagg.zzafY) {
                    this.zzagh.zzaeJ = iBinder;
                    this.zzagh.zzagb = componentName;
                    for (ServiceConnection onServiceConnected : this.zzagh.zzagd) {
                        onServiceConnected.onServiceConnected(componentName, iBinder);
                    }
                    this.zzagh.mState = 1;
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                synchronized (this.zzagh.zzagg.zzafY) {
                    this.zzagh.zzaeJ = null;
                    this.zzagh.zzagb = componentName;
                    for (ServiceConnection onServiceDisconnected : this.zzagh.zzagd) {
                        onServiceDisconnected.onServiceDisconnected(componentName);
                    }
                    this.zzagh.mState = 2;
                }
            }
        }

        public zzb(zzm com_google_android_gms_common_internal_zzm, zza com_google_android_gms_common_internal_zzm_zza) {
            this.zzagg = com_google_android_gms_common_internal_zzm;
            this.zzagf = com_google_android_gms_common_internal_zzm_zza;
        }

        public IBinder getBinder() {
            return this.zzaeJ;
        }

        public ComponentName getComponentName() {
            return this.zzagb;
        }

        public int getState() {
            return this.mState;
        }

        public boolean isBound() {
            return this.zzage;
        }

        public void zza(ServiceConnection serviceConnection, String str) {
            this.zzagg.zzafZ.zza(this.zzagg.zzqZ, serviceConnection, str, this.zzagf.zzpm());
            this.zzagd.add(serviceConnection);
        }

        public boolean zza(ServiceConnection serviceConnection) {
            return this.zzagd.contains(serviceConnection);
        }

        public void zzb(ServiceConnection serviceConnection, String str) {
            this.zzagg.zzafZ.zzb(this.zzagg.zzqZ, serviceConnection);
            this.zzagd.remove(serviceConnection);
        }

        public void zzcm(String str) {
            this.mState = 3;
            this.zzage = this.zzagg.zzafZ.zza(this.zzagg.zzqZ, str, this.zzagf.zzpm(), this.zzagc, 129);
            if (!this.zzage) {
                this.mState = 2;
                try {
                    this.zzagg.zzafZ.zza(this.zzagg.zzqZ, this.zzagc);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        public void zzcn(String str) {
            this.zzagg.zzafZ.zza(this.zzagg.zzqZ, this.zzagc);
            this.zzage = false;
            this.mState = 2;
        }

        public boolean zzpn() {
            return this.zzagd.isEmpty();
        }
    }

    zzm(Context context) {
        this.zzqZ = context.getApplicationContext();
        this.mHandler = new Handler(context.getMainLooper(), this);
        this.zzafZ = com.google.android.gms.common.stats.zzb.zzqh();
        this.zzaga = OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL;
    }

    private boolean zza(zza com_google_android_gms_common_internal_zzm_zza, ServiceConnection serviceConnection, String str) {
        boolean isBound;
        zzx.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzafY) {
            zzb com_google_android_gms_common_internal_zzm_zzb = (zzb) this.zzafY.get(com_google_android_gms_common_internal_zzm_zza);
            if (com_google_android_gms_common_internal_zzm_zzb != null) {
                this.mHandler.removeMessages(0, com_google_android_gms_common_internal_zzm_zzb);
                if (!com_google_android_gms_common_internal_zzm_zzb.zza(serviceConnection)) {
                    com_google_android_gms_common_internal_zzm_zzb.zza(serviceConnection, str);
                    switch (com_google_android_gms_common_internal_zzm_zzb.getState()) {
                        case 1:
                            serviceConnection.onServiceConnected(com_google_android_gms_common_internal_zzm_zzb.getComponentName(), com_google_android_gms_common_internal_zzm_zzb.getBinder());
                            break;
                        case 2:
                            com_google_android_gms_common_internal_zzm_zzb.zzcm(str);
                            break;
                        default:
                            break;
                    }
                }
                throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + com_google_android_gms_common_internal_zzm_zza);
            }
            com_google_android_gms_common_internal_zzm_zzb = new zzb(this, com_google_android_gms_common_internal_zzm_zza);
            com_google_android_gms_common_internal_zzm_zzb.zza(serviceConnection, str);
            com_google_android_gms_common_internal_zzm_zzb.zzcm(str);
            this.zzafY.put(com_google_android_gms_common_internal_zzm_zza, com_google_android_gms_common_internal_zzm_zzb);
            isBound = com_google_android_gms_common_internal_zzm_zzb.isBound();
        }
        return isBound;
    }

    private void zzb(zza com_google_android_gms_common_internal_zzm_zza, ServiceConnection serviceConnection, String str) {
        zzx.zzb((Object) serviceConnection, (Object) "ServiceConnection must not be null");
        synchronized (this.zzafY) {
            zzb com_google_android_gms_common_internal_zzm_zzb = (zzb) this.zzafY.get(com_google_android_gms_common_internal_zzm_zza);
            if (com_google_android_gms_common_internal_zzm_zzb == null) {
                throw new IllegalStateException("Nonexistent connection status for service config: " + com_google_android_gms_common_internal_zzm_zza);
            } else if (com_google_android_gms_common_internal_zzm_zzb.zza(serviceConnection)) {
                com_google_android_gms_common_internal_zzm_zzb.zzb(serviceConnection, str);
                if (com_google_android_gms_common_internal_zzm_zzb.zzpn()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0, com_google_android_gms_common_internal_zzm_zzb), this.zzaga);
                }
            } else {
                throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + com_google_android_gms_common_internal_zzm_zza);
            }
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                zzb com_google_android_gms_common_internal_zzm_zzb = (zzb) message.obj;
                synchronized (this.zzafY) {
                    if (com_google_android_gms_common_internal_zzm_zzb.zzpn()) {
                        if (com_google_android_gms_common_internal_zzm_zzb.isBound()) {
                            com_google_android_gms_common_internal_zzm_zzb.zzcn("GmsClientSupervisor");
                        }
                        this.zzafY.remove(com_google_android_gms_common_internal_zzm_zzb.zzagf);
                    }
                }
                return true;
            default:
                return false;
        }
    }

    public boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        return zza(new zza(componentName), serviceConnection, str);
    }

    public boolean zza(String str, ServiceConnection serviceConnection, String str2) {
        return zza(new zza(str), serviceConnection, str2);
    }

    public void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str) {
        zzb(new zza(componentName), serviceConnection, str);
    }

    public void zzb(String str, ServiceConnection serviceConnection, String str2) {
        zzb(new zza(str), serviceConnection, str2);
    }
}
