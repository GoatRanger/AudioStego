package com.here.services.location.internal;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.here.posclient.UpdateOptions;

public interface PositionListener extends IInterface {

    public static abstract class Stub extends Binder implements PositionListener {
        private static final String DESCRIPTOR = "com.here.services.location.internal.PositionListener";
        static final int TRANSACTION_onOptionsChanged = 2;
        static final int TRANSACTION_onPositionUpdate = 1;

        private static class Proxy implements PositionListener {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void onPositionUpdate(Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (updateOptions != null) {
                        obtain.writeInt(1);
                        updateOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (updateOptions2 != null) {
                        obtain.writeInt(1);
                        updateOptions2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static PositionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof PositionListener)) {
                return new Proxy(iBinder);
            }
            return (PositionListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    Location location;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        location = (Location) Location.CREATOR.createFromParcel(parcel);
                    } else {
                        location = null;
                    }
                    onPositionUpdate(location);
                    return true;
                case 2:
                    UpdateOptions updateOptions;
                    UpdateOptions updateOptions2;
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        updateOptions = (UpdateOptions) UpdateOptions.CREATOR.createFromParcel(parcel);
                    } else {
                        updateOptions = null;
                    }
                    if (parcel.readInt() != 0) {
                        updateOptions2 = (UpdateOptions) UpdateOptions.CREATOR.createFromParcel(parcel);
                    } else {
                        updateOptions2 = null;
                    }
                    onOptionsChanged(updateOptions, updateOptions2);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2) throws RemoteException;

    void onPositionUpdate(Location location) throws RemoteException;
}
