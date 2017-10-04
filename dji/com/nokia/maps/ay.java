package com.nokia.maps;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

public interface ay extends IInterface {

    public static abstract class a extends Binder implements ay {

        private static class a implements ay {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public ParcelFileDescriptor a(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    ParcelFileDescriptor parcelFileDescriptor;
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeInt(i);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
                    } else {
                        parcelFileDescriptor = null;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return parcelFileDescriptor;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(long j, ba baVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeLong(j);
                    obtain.writeStrongBinder(baVar != null ? baVar.asBinder() : null);
                    this.a.transact(2, obtain, obtain2, 0);
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

            public boolean a(long j) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeLong(j);
                    this.a.transact(3, obtain, obtain2, 0);
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

            public byte[] b(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeInt(i);
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] b(long j, ba baVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeLong(j);
                    obtain.writeStrongBinder(baVar != null ? baVar.asBinder() : null);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(long j, byte[] bArr) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeLong(j);
                    obtain.writeByteArray(bArr);
                    this.a.transact(6, obtain, obtain2, 0);
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

            public List<String> a(ba baVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeStrongBinder(baVar != null ? baVar.asBinder() : null);
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    List<String> createStringArrayList = obtain2.createStringArrayList();
                    return createStringArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean b() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    this.a.transact(9, obtain, obtain2, 0);
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

            public void a(az azVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeStrongBinder(azVar != null ? azVar.asBinder() : null);
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(az azVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeStrongBinder(azVar != null ? azVar.asBinder() : null);
                    this.a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    this.a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean d() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    this.a.transact(13, obtain, obtain2, 0);
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

            public boolean a(boolean z) throws RemoteException {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.nokia.maps.IMapService");
                    obtain.writeInt(z ? 1 : 0);
                    this.a.transact(14, obtain, obtain2, 0);
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
        }

        public a() {
            attachInterface(this, "com.nokia.maps.IMapService");
        }

        public static ay a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.nokia.maps.IMapService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ay)) {
                return new a(iBinder);
            }
            return (ay) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int i3 = 0;
            boolean a;
            byte[] b;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    ParcelFileDescriptor a2 = a(parcel.readInt());
                    parcel2.writeNoException();
                    if (a2 != null) {
                        parcel2.writeInt(1);
                        a2.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case 2:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    a = a(parcel.readLong(), com.nokia.maps.ba.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 3:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    a = a(parcel.readLong());
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 4:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    b = b(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(b);
                    return true;
                case 5:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    b = b(parcel.readLong(), com.nokia.maps.ba.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeByteArray(b);
                    return true;
                case 6:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    a = a(parcel.readLong(), parcel.createByteArray());
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 7:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    List a3 = a(com.nokia.maps.ba.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStringList(a3);
                    return true;
                case 8:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    a();
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    a = b();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 10:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    a(com.nokia.maps.az.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    b(com.nokia.maps.az.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    i3 = c();
                    parcel2.writeNoException();
                    parcel2.writeInt(i3);
                    return true;
                case 13:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    a = d();
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 14:
                    parcel.enforceInterface("com.nokia.maps.IMapService");
                    if (parcel.readInt() != 0) {
                        a = true;
                    } else {
                        a = false;
                    }
                    a = a(a);
                    parcel2.writeNoException();
                    if (a) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.nokia.maps.IMapService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    ParcelFileDescriptor a(int i) throws RemoteException;

    List<String> a(ba baVar) throws RemoteException;

    void a() throws RemoteException;

    void a(az azVar) throws RemoteException;

    boolean a(long j) throws RemoteException;

    boolean a(long j, ba baVar) throws RemoteException;

    boolean a(long j, byte[] bArr) throws RemoteException;

    boolean a(boolean z) throws RemoteException;

    void b(az azVar) throws RemoteException;

    boolean b() throws RemoteException;

    byte[] b(int i) throws RemoteException;

    byte[] b(long j, ba baVar) throws RemoteException;

    int c() throws RemoteException;

    boolean d() throws RemoteException;
}
