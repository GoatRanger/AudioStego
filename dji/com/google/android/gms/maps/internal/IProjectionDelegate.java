package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public interface IProjectionDelegate extends IInterface {

    public static abstract class zza extends Binder implements IProjectionDelegate {

        private static class zza implements IProjectionDelegate {
            private IBinder zznJ;

            zza(IBinder iBinder) {
                this.zznJ = iBinder;
            }

            public IBinder asBinder() {
                return this.zznJ;
            }

            public LatLng fromScreenLocation(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                LatLng latLng = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zznJ.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        latLng = LatLng.CREATOR.zzfl(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return latLng;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public VisibleRegion getVisibleRegion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
                    this.zznJ.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    VisibleRegion zzfw = obtain2.readInt() != 0 ? VisibleRegion.CREATOR.zzfw(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return zzfw;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd toScreenLocation(LatLng latLng) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IProjectionDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zznJ.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd zzbk = com.google.android.gms.dynamic.zzd.zza.zzbk(obtain2.readStrongBinder());
                    return zzbk;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IProjectionDelegate zzcJ(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IProjectionDelegate)) ? new zza(iBinder) : (IProjectionDelegate) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                    LatLng fromScreenLocation = fromScreenLocation(com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (fromScreenLocation != null) {
                        parcel2.writeInt(1);
                        fromScreenLocation.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                    zzd toScreenLocation = toScreenLocation(parcel.readInt() != 0 ? LatLng.CREATOR.zzfl(parcel) : null);
                    parcel2.writeNoException();
                    if (toScreenLocation != null) {
                        iBinder = toScreenLocation.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.maps.internal.IProjectionDelegate");
                    VisibleRegion visibleRegion = getVisibleRegion();
                    parcel2.writeNoException();
                    if (visibleRegion != null) {
                        parcel2.writeInt(1);
                        visibleRegion.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.maps.internal.IProjectionDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    LatLng fromScreenLocation(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    VisibleRegion getVisibleRegion() throws RemoteException;

    zzd toScreenLocation(LatLng latLng) throws RemoteException;
}
