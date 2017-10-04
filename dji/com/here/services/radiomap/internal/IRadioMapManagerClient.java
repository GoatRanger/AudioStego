package com.here.services.radiomap.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.here.services.radiomap.common.GeoArea;

public interface IRadioMapManagerClient extends IInterface {

    public static abstract class Stub extends Binder implements IRadioMapManagerClient {
        private static final String DESCRIPTOR = "com.here.services.radiomap.internal.IRadioMapManagerClient";
        static final int TRANSACTION_close = 4;
        static final int TRANSACTION_startRadioMapQuery = 2;
        static final int TRANSACTION_startRadioMapUpdate = 1;
        static final int TRANSACTION_stopRadioMapAction = 3;

        private static class Proxy implements IRadioMapManagerClient {
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

            public boolean startRadioMapUpdate(GeoArea[] geoAreaArr, int i, String str, RadioMapActionListener radioMapActionListener) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(geoAreaArr, 0);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(radioMapActionListener != null ? radioMapActionListener.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean startRadioMapQuery(GeoArea[] geoAreaArr, int i, String str, RadioMapActionListener radioMapActionListener) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(geoAreaArr, 0);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(radioMapActionListener != null ? radioMapActionListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
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

            public void stopRadioMapAction(RadioMapActionListener radioMapActionListener) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (radioMapActionListener != null) {
                        iBinder = radioMapActionListener.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void close() throws RemoteException {
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

        public static IRadioMapManagerClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IRadioMapManagerClient)) {
                return new Proxy(iBinder);
            }
            return (IRadioMapManagerClient) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            boolean startRadioMapUpdate;
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    startRadioMapUpdate = startRadioMapUpdate((GeoArea[]) parcel.createTypedArray(GeoArea.CREATOR), parcel.readInt(), parcel.readString(), com.here.services.radiomap.internal.RadioMapActionListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(startRadioMapUpdate ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    startRadioMapUpdate = startRadioMapQuery((GeoArea[]) parcel.createTypedArray(GeoArea.CREATOR), parcel.readInt(), parcel.readString(), com.here.services.radiomap.internal.RadioMapActionListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (startRadioMapUpdate) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopRadioMapAction(com.here.services.radiomap.internal.RadioMapActionListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    close();
                    return true;
                case 1598968902:
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void close() throws RemoteException;

    boolean startRadioMapQuery(GeoArea[] geoAreaArr, int i, String str, RadioMapActionListener radioMapActionListener) throws RemoteException;

    boolean startRadioMapUpdate(GeoArea[] geoAreaArr, int i, String str, RadioMapActionListener radioMapActionListener) throws RemoteException;

    void stopRadioMapAction(RadioMapActionListener radioMapActionListener) throws RemoteException;
}
