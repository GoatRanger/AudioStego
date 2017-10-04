package com.here.services.positioning.analytics.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUsageTrackingClient extends IInterface {

    public static abstract class Stub extends Binder implements IUsageTrackingClient {
        private static final String DESCRIPTOR = "com.here.services.positioning.analytics.internal.IUsageTrackingClient";
        static final int TRANSACTION_getSupportedTrackers = 1;
        static final int TRANSACTION_subscribe = 2;
        static final int TRANSACTION_unsubscribe = 3;

        private static class Proxy implements IUsageTrackingClient {
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

            public Bundle getSupportedTrackers() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    Bundle bundle;
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                    } else {
                        bundle = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int subscribe(UsageTrackingListener usageTrackingListener, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(usageTrackingListener != null ? usageTrackingListener.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int unsubscribe() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUsageTrackingClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUsageTrackingClient)) {
                return new Proxy(iBinder);
            }
            return (IUsageTrackingClient) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle supportedTrackers;
            int subscribe;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    supportedTrackers = getSupportedTrackers();
                    parcel2.writeNoException();
                    if (supportedTrackers != null) {
                        parcel2.writeInt(1);
                        supportedTrackers.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    UsageTrackingListener asInterface = com.here.services.positioning.analytics.internal.UsageTrackingListener.Stub.asInterface(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        supportedTrackers = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        supportedTrackers = null;
                    }
                    subscribe = subscribe(asInterface, supportedTrackers);
                    parcel2.writeNoException();
                    parcel2.writeInt(subscribe);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    subscribe = unsubscribe();
                    parcel2.writeNoException();
                    parcel2.writeInt(subscribe);
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Bundle getSupportedTrackers() throws RemoteException;

    int subscribe(UsageTrackingListener usageTrackingListener, Bundle bundle) throws RemoteException;

    int unsubscribe() throws RemoteException;
}
