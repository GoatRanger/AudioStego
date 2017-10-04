package com.here.services.test.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.here.posclient.ClientConfiguration;

public interface ILocationTestClient extends IInterface {

    public static abstract class Stub extends Binder implements ILocationTestClient {
        private static final String DESCRIPTOR = "com.here.services.test.internal.ILocationTestClient";
        static final int TRANSACTION_availableFeatures = 7;
        static final int TRANSACTION_clearData = 4;
        static final int TRANSACTION_dumpCachedData = 2;
        static final int TRANSACTION_dumpData = 1;
        static final int TRANSACTION_dumpFingerprints = 9;
        static final int TRANSACTION_dumpHeapData = 3;
        static final int TRANSACTION_getActiveCollection = 11;
        static final int TRANSACTION_getAutoUpload = 13;
        static final int TRANSACTION_getClientConfiguration = 17;
        static final int TRANSACTION_getCollectionStatus = 8;
        static final int TRANSACTION_getGnssFingerprintCount = 15;
        static final int TRANSACTION_getNonGnssFingerprintCount = 16;
        static final int TRANSACTION_sendFingerprints = 10;
        static final int TRANSACTION_setActiveCollection = 12;
        static final int TRANSACTION_setAutoUpload = 14;
        static final int TRANSACTION_setUsername = 5;
        static final int TRANSACTION_toggleFeature = 6;
        static final int TRANSACTION_unBind = 18;

        private static class Proxy implements ILocationTestClient {
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

            public void dumpData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void dumpCachedData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void dumpHeapData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void clearData(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void setUsername(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void toggleFeature(String str, boolean z) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public int availableFeatures() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getCollectionStatus() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void dumpFingerprints() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void sendFingerprints() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public boolean getActiveCollection() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setActiveCollection(boolean z) throws RemoteException {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getAutoUpload() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setAutoUpload(boolean z) throws RemoteException {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getGnssFingerprintCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getNonGnssFingerprintCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    long readLong = obtain2.readLong();
                    return readLong;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ClientConfiguration getClientConfiguration() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    ClientConfiguration clientConfiguration;
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        clientConfiguration = (ClientConfiguration) ClientConfiguration.CREATOR.createFromParcel(obtain2);
                    } else {
                        clientConfiguration = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return clientConfiguration;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unBind() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationTestClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ILocationTestClient)) {
                return new Proxy(iBinder);
            }
            return (ILocationTestClient) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            boolean collectionStatus;
            long gnssFingerprintCount;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    dumpData();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    dumpCachedData();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    dumpHeapData();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearData(parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUsername(parcel.readString());
                    return true;
                case 6:
                    boolean z;
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    toggleFeature(readString, z);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    i3 = availableFeatures();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    collectionStatus = getCollectionStatus();
                    parcel2.writeNoException();
                    if (collectionStatus) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    dumpFingerprints();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendFingerprints();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    collectionStatus = getActiveCollection();
                    parcel2.writeNoException();
                    if (collectionStatus) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        collectionStatus = true;
                    } else {
                        collectionStatus = false;
                    }
                    collectionStatus = setActiveCollection(collectionStatus);
                    parcel2.writeNoException();
                    if (collectionStatus) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    collectionStatus = getAutoUpload();
                    parcel2.writeNoException();
                    if (collectionStatus) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        collectionStatus = true;
                    } else {
                        collectionStatus = false;
                    }
                    collectionStatus = setAutoUpload(collectionStatus);
                    parcel2.writeNoException();
                    if (collectionStatus) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    gnssFingerprintCount = getGnssFingerprintCount();
                    parcel2.writeNoException();
                    parcel2.writeLong(gnssFingerprintCount);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    gnssFingerprintCount = getNonGnssFingerprintCount();
                    parcel2.writeNoException();
                    parcel2.writeLong(gnssFingerprintCount);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    ClientConfiguration clientConfiguration = getClientConfiguration();
                    parcel2.writeNoException();
                    if (clientConfiguration != null) {
                        parcel2.writeInt(1);
                        clientConfiguration.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    unBind();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int availableFeatures() throws RemoteException;

    void clearData(int i) throws RemoteException;

    void dumpCachedData() throws RemoteException;

    void dumpData() throws RemoteException;

    void dumpFingerprints() throws RemoteException;

    void dumpHeapData() throws RemoteException;

    boolean getActiveCollection() throws RemoteException;

    boolean getAutoUpload() throws RemoteException;

    ClientConfiguration getClientConfiguration() throws RemoteException;

    boolean getCollectionStatus() throws RemoteException;

    long getGnssFingerprintCount() throws RemoteException;

    long getNonGnssFingerprintCount() throws RemoteException;

    void sendFingerprints() throws RemoteException;

    boolean setActiveCollection(boolean z) throws RemoteException;

    boolean setAutoUpload(boolean z) throws RemoteException;

    void setUsername(String str) throws RemoteException;

    void toggleFeature(String str, boolean z) throws RemoteException;

    void unBind() throws RemoteException;
}
