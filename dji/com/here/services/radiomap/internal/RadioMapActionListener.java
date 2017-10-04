package com.here.services.radiomap.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface RadioMapActionListener extends IInterface {

    public static abstract class Stub extends Binder implements RadioMapActionListener {
        private static final String DESCRIPTOR = "com.here.services.radiomap.internal.RadioMapActionListener";
        static final int TRANSACTION_onRadioMapActionProgress = 1;
        static final int TRANSACTION_onRadioMapQueryActionComplete = 2;
        static final int TRANSACTION_onRadioMapStorageActionComplete = 3;
        static final int TRANSACTION_onSessionClosed = 4;

        private static class Proxy implements RadioMapActionListener {
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

            public void onRadioMapActionProgress(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onRadioMapQueryActionComplete(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onRadioMapStorageActionComplete(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onSessionClosed() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static RadioMapActionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof RadioMapActionListener)) {
                return new Proxy(iBinder);
            }
            return (RadioMapActionListener) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRadioMapActionProgress(parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRadioMapQueryActionComplete(parcel.readInt(), parcel.readLong());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onRadioMapStorageActionComplete(parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSessionClosed();
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void onRadioMapActionProgress(int i) throws RemoteException;

    void onRadioMapQueryActionComplete(int i, long j) throws RemoteException;

    void onRadioMapStorageActionComplete(int i) throws RemoteException;

    void onSessionClosed() throws RemoteException;
}
