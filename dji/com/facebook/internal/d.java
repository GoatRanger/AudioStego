package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.k;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class d {
    private static final String a = d.class.getCanonicalName();
    private static final String b = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String c = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final String d = "aid";
    private static final String e = "androidid";
    private static final String f = "limit_tracking";
    private static final int g = 0;
    private static final long h = 3600000;
    private static d n;
    private String i;
    private String j;
    private String k;
    private boolean l;
    private long m;

    private static final class a implements IInterface {
        private static final int a = 1;
        private static final int b = 2;
        private IBinder c;

        a(IBinder iBinder) {
            this.c = iBinder;
        }

        public IBinder asBinder() {
            return this.c;
        }

        public String a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.c.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean b() throws RemoteException {
            boolean z = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(1);
                this.c.transact(2, obtain, obtain2, 0);
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
    }

    private static final class b implements ServiceConnection {
        private AtomicBoolean a;
        private final BlockingQueue<IBinder> b;

        private b() {
            this.a = new AtomicBoolean(false);
            this.b = new LinkedBlockingDeque();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder a() throws InterruptedException {
            if (!this.a.compareAndSet(true, true)) {
                return (IBinder) this.b.take();
            }
            throw new IllegalStateException("Binder already consumed");
        }
    }

    private static d b(Context context) {
        d c = c(context);
        if (c != null) {
            return c;
        }
        c = d(context);
        if (c == null) {
            return new d();
        }
        return c;
    }

    private static d c(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new k("getAndroidId cannot be called on the main thread.");
            }
            Method a = ah.a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
            if (a == null) {
                return null;
            }
            Object a2 = ah.a(null, a, context);
            if (!(a2 instanceof Integer) || ((Integer) a2).intValue() != 0) {
                return null;
            }
            a = ah.a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class);
            if (a == null) {
                return null;
            }
            Object a3 = ah.a(null, a, context);
            if (a3 == null) {
                return null;
            }
            a = ah.a(a3.getClass(), "getId", new Class[0]);
            Method a4 = ah.a(a3.getClass(), "isLimitAdTrackingEnabled", new Class[0]);
            if (a == null || a4 == null) {
                return null;
            }
            d dVar = new d();
            dVar.j = (String) ah.a(a3, a, new Object[0]);
            dVar.l = ((Boolean) ah.a(a3, a4, new Object[0])).booleanValue();
            return dVar;
        } catch (Exception e) {
            ah.a("android_id", e);
            return null;
        }
    }

    private static d d(Context context) {
        d dVar;
        ServiceConnection bVar = new b();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, bVar, 1)) {
            try {
                a aVar = new a(bVar.a());
                dVar = new d();
                dVar.j = aVar.a();
                dVar.l = aVar.b();
                return dVar;
            } catch (Exception e) {
                dVar = e;
                ah.a("android_id", (Exception) dVar);
            } finally {
                context.unbindService(bVar);
            }
        }
        return null;
    }

    public static d a(Context context) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        if (n != null && System.currentTimeMillis() - n.m < 3600000) {
            return n;
        }
        d b = b(context);
        Cursor query;
        try {
            Uri parse;
            String[] strArr = new String[]{"aid", e, f};
            if (context.getPackageManager().resolveContentProvider(b, 0) != null) {
                parse = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            } else if (context.getPackageManager().resolveContentProvider(c, 0) != null) {
                parse = Uri.parse("content://com.facebook.wakizashi.provider.AttributionIdProvider");
            } else {
                Object obj = cursor;
            }
            String e2 = e(context);
            if (e2 != null) {
                b.k = e2;
            }
            d a;
            if (parse == null) {
                a = a(b);
                if (cursor == null) {
                    return a;
                }
                cursor.close();
                return a;
            }
            query = context.getContentResolver().query(parse, strArr, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int columnIndex = query.getColumnIndex("aid");
                        int columnIndex2 = query.getColumnIndex(e);
                        int columnIndex3 = query.getColumnIndex(f);
                        b.i = query.getString(columnIndex);
                        if (columnIndex2 > 0 && columnIndex3 > 0 && b.b() == null) {
                            b.j = query.getString(columnIndex2);
                            b.l = Boolean.parseBoolean(query.getString(columnIndex3));
                        }
                        if (query != null) {
                            query.close();
                        }
                        return a(b);
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        Log.d(a, "Caught unexpected exception in getAttributionId(): " + e.toString());
                        if (query != null) {
                            query.close();
                        }
                        return cursor;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            a = a(b);
            if (query == null) {
                return a;
            }
            query.close();
            return a;
        } catch (Exception e4) {
            e = e4;
            query = cursor;
            Log.d(a, "Caught unexpected exception in getAttributionId(): " + e.toString());
            if (query != null) {
                query.close();
            }
            return cursor;
        } catch (Throwable th3) {
            th = th3;
            query = cursor;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    private static d a(d dVar) {
        dVar.m = System.currentTimeMillis();
        n = dVar;
        return dVar;
    }

    public String a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public String c() {
        return this.k;
    }

    public boolean d() {
        return this.l;
    }

    @Nullable
    private static String e(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }
}
