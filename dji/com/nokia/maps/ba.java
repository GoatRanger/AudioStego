package com.nokia.maps;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ba extends IInterface {

    public static abstract class a extends Binder implements ba {
        public a() {
            attachInterface(this, "com.nokia.maps.IMapServiceLocker");
        }

        public static ba a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.nokia.maps.IMapServiceLocker");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ba)) {
                return new a(iBinder);
            }
            return (ba) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1598968902:
                    parcel2.writeString("com.nokia.maps.IMapServiceLocker");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }
}
