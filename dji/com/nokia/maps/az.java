package com.nokia.maps;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface az extends IInterface {

    public static abstract class a extends Binder implements az {
        public a() {
            attachInterface(this, "com.nokia.maps.IMapServiceClient");
        }

        public static az a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.nokia.maps.IMapServiceClient");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof az)) {
                return new a(iBinder);
            }
            return (az) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.nokia.maps.IMapServiceClient");
                    String a = a();
                    parcel2.writeNoException();
                    parcel2.writeString(a);
                    return true;
                case 2:
                    parcel.enforceInterface("com.nokia.maps.IMapServiceClient");
                    b();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.nokia.maps.IMapServiceClient");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String a() throws RemoteException;

    void b() throws RemoteException;
}
