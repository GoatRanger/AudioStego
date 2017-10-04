package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zzadn = new String[]{"data"};
    private final Creator<T> zzado;

    public zzd(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.zzado = creator;
    }

    public /* synthetic */ Object get(int i) {
        return zzbs(i);
    }

    public T zzbs(int i) {
        byte[] zzg = this.zzabq.zzg("data", i, this.zzabq.zzbt(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) this.zzado.createFromParcel(obtain);
        obtain.recycle();
        return safeParcelable;
    }
}
