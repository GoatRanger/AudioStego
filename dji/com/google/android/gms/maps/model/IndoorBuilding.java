package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zze.zza;
import java.util.ArrayList;
import java.util.List;

public final class IndoorBuilding {
    private final zzd zzaKd;

    public IndoorBuilding(zzd com_google_android_gms_maps_model_internal_zzd) {
        this.zzaKd = (zzd) zzx.zzw(com_google_android_gms_maps_model_internal_zzd);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IndoorBuilding)) {
            return false;
        }
        try {
            return this.zzaKd.zzb(((IndoorBuilding) obj).zzaKd);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getActiveLevelIndex() {
        try {
            return this.zzaKd.getActiveLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getDefaultLevelIndex() {
        try {
            return this.zzaKd.getActiveLevelIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public List<IndoorLevel> getLevels() {
        try {
            List<IBinder> levels = this.zzaKd.getLevels();
            List<IndoorLevel> arrayList = new ArrayList(levels.size());
            for (IBinder zzcT : levels) {
                arrayList.add(new IndoorLevel(zza.zzcT(zzcT)));
            }
            return arrayList;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        try {
            return this.zzaKd.hashCodeRemote();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isUnderground() {
        try {
            return this.zzaKd.isUnderground();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
