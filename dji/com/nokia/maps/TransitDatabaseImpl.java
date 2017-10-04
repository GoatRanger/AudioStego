package com.nokia.maps;

import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.mapping.TransitDatabase;
import com.here.android.mpa.mapping.TransitDatabase.Error;
import com.here.android.mpa.mapping.TransitDatabase.OnGetTransitInfoListener;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

@Online
public class TransitDatabaseImpl extends BaseNativeObject {
    private static final String a = TransitDatabaseImpl.class.getSimpleName();
    private static am<TransitDatabase, TransitDatabaseImpl> i = null;
    private static k<TransitDatabase, TransitDatabaseImpl> l = null;
    private long b = 30;
    private boolean c = false;
    private boolean d = false;
    private ArrayList<TransitStopInfoImpl> e;
    private boolean f = false;
    private boolean g;
    private cq h = new cq(TransitDatabaseImpl.class.getName());
    private a j = null;
    private OnGetTransitInfoListener k = null;

    private static class a extends Thread {
        private static final String a = TransitDatabaseImpl.a;
        private WeakReference<TransitDatabaseImpl> b = null;

        public a(TransitDatabaseImpl transitDatabaseImpl) {
            this.b = new WeakReference(transitDatabaseImpl);
            setName(a);
            setPriority(1);
            start();
        }

        public void a() {
            TransitDatabaseImpl transitDatabaseImpl = (TransitDatabaseImpl) this.b.get();
            if (transitDatabaseImpl != null) {
                transitDatabaseImpl.c = true;
                try {
                    join(transitDatabaseImpl.b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            TransitDatabaseImpl transitDatabaseImpl = (TransitDatabaseImpl) this.b.get();
            if (transitDatabaseImpl != null) {
                transitDatabaseImpl.d = false;
                transitDatabaseImpl.c();
            }
        }
    }

    private native void createTransitDatabaseNative();

    private native void destroyTransitDatabaseNative();

    private native synchronized int getAccessInfo(IdentifierImpl identifierImpl);

    private native synchronized int getLineInfo(IdentifierImpl identifierImpl);

    private native synchronized int getStopInfo(IdentifierImpl identifierImpl);

    private native synchronized int getSystemInfo(IdentifierImpl identifierImpl);

    private native synchronized boolean pollTransitDatabase();

    public native synchronized void cancel();

    static {
        ce.a(TransitDatabase.class);
    }

    public TransitDatabaseImpl() {
        createTransitDatabaseNative();
        this.e = new ArrayList();
    }

    private synchronized void b() {
        this.j = new a(this);
    }

    private void c() {
        while (pollTransitDatabase() && !this.c) {
            if (this.d) {
                this.d = false;
                return;
            }
            try {
                Thread.sleep(this.b);
            } catch (InterruptedException e) {
            }
        }
    }

    protected void finalize() {
        if (!(this.j == null || this.j.b == null)) {
            this.j.a();
        }
        if (this.nativeptr != 0) {
            destroyTransitDatabaseNative();
        }
    }

    private synchronized void a(final Object obj) {
        if (this.k == null) {
            bj.c(a, "notifyDatabaseListener - m_transitDatabaseListener should not be NULL", new Object[0]);
        } else if (!MapSettings.l() || this.g) {
            try {
                b(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ez.a(new Runnable(this) {
                final /* synthetic */ TransitDatabaseImpl b;

                public void run() {
                    this.b.b(obj);
                }
            });
        }
    }

    private synchronized void b(Object obj) {
        if (this.k != null) {
            if (obj.getClass() == TransitSystemInfoImpl.class) {
                this.k.onTransitSystemInfo(TransitSystemInfoImpl.a((TransitSystemInfoImpl) obj));
            } else if (obj.getClass() == TransitLineInfoImpl.class) {
                this.k.onTransitLineInfo(TransitLineInfoImpl.a((TransitLineInfoImpl) obj));
            } else if (obj.getClass() == TransitAccessInfoImpl.class) {
                this.k.onTransitAccessInfo(TransitAccessInfoImpl.a((TransitAccessInfoImpl) obj));
            } else if (obj.getClass() == TransitStopInfoImpl.class) {
                this.k.onTransitStopInfo(TransitStopInfoImpl.a((TransitStopInfoImpl) obj));
            }
        }
    }

    @OnlineNative
    private synchronized void onTransitSystemInfo(TransitSystemInfoImpl transitSystemInfoImpl) {
        a((Object) transitSystemInfoImpl);
    }

    @OnlineNative
    private synchronized void onTransitLineInfo(TransitLineInfoImpl transitLineInfoImpl) {
        a((Object) transitLineInfoImpl);
    }

    @OnlineNative
    private synchronized void onTransitAccessInfo(TransitAccessInfoImpl transitAccessInfoImpl) {
        a((Object) transitAccessInfoImpl);
    }

    @OnlineNative
    private synchronized void onTransitStopInfo(TransitStopInfoImpl transitStopInfoImpl) {
        if (this.f) {
            this.e.add(transitStopInfoImpl);
        }
        a((Object) transitStopInfoImpl);
    }

    @OnlineNative
    private synchronized void onEnd(final int i) {
        if (this.f) {
            this.f = false;
        }
        if (this.k == null) {
            bj.c(a, "No listeners registered.", new Object[0]);
        } else {
            if (MapSettings.l()) {
                ez.a(new Runnable(this) {
                    final /* synthetic */ TransitDatabaseImpl b;

                    public void run() {
                        this.b.a(TransitDatabaseImpl.b(i));
                    }
                });
            } else {
                a(b(i));
            }
            this.d = true;
        }
    }

    private synchronized void a(Error error) {
        if (this.k != null) {
            OnGetTransitInfoListener onGetTransitInfoListener = this.k;
            this.k = null;
            onGetTransitInfoListener.onEnd(error);
        }
    }

    private synchronized Error a(Object obj, OnGetTransitInfoListener onGetTransitInfoListener) {
        Error error;
        if (this.k != null) {
            bj.c(a, "setupAsycRequest - last request has not finished reporting to caller.", new Object[0]);
            error = Error.INVALID_OPERATION;
        } else if (obj == null || onGetTransitInfoListener == null) {
            error = Error.INVALID_PARAMETERS;
        } else {
            this.k = onGetTransitInfoListener;
            error = Error.NONE;
        }
        return error;
    }

    public synchronized Error a(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        Error a;
        a = a((Object) identifier, onGetTransitInfoListener);
        if (a == Error.NONE) {
            IdentifierImpl a2 = IdentifierImpl.a(identifier);
            if (a2 == null) {
                this.k = null;
                a = Error.INVALID_PARAMETERS;
            } else {
                a = b(getSystemInfo(a2));
                if (a != Error.NONE) {
                    this.k = null;
                    bj.c(a, "ERROR: getSystemInfo() returns %s", new Object[]{a.toString()});
                } else {
                    b();
                }
            }
        }
        return a;
    }

    public synchronized Error b(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        Error error;
        if (identifier == null) {
            error = Error.INVALID_PARAMETERS;
        } else {
            Object a = IdentifierImpl.a(identifier);
            error = a(a, onGetTransitInfoListener);
            if (error == Error.NONE) {
                error = b(getLineInfo(a));
                if (error != Error.NONE) {
                    this.k = null;
                    bj.c(a, "ERROR: getLineInfo() returns %s", new Object[]{error.toString()});
                } else {
                    b();
                }
                bj.a(a, "<< getLineInfoAsync - returns %s", new Object[]{error.toString()});
            }
        }
        return error;
    }

    public synchronized Error c(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        Error a;
        a = a((Object) identifier, onGetTransitInfoListener);
        if (a == Error.NONE) {
            IdentifierImpl a2 = IdentifierImpl.a(identifier);
            if (a2 == null) {
                a = Error.INVALID_PARAMETERS;
            } else {
                a = b(getStopInfo(a2));
                if (a != Error.NONE) {
                    this.k = null;
                    bj.c(a, "ERROR: getStopInfo() returns %s", new Object[]{a.toString()});
                } else {
                    b();
                }
            }
        }
        return a;
    }

    public synchronized Error d(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        Error a;
        a = a((Object) identifier, onGetTransitInfoListener);
        if (a == Error.NONE) {
            IdentifierImpl a2 = IdentifierImpl.a(identifier);
            if (a2 == null) {
                this.k = null;
                a = Error.INVALID_PARAMETERS;
            } else {
                a = b(getAccessInfo(a2));
                if (a != Error.NONE) {
                    this.k = null;
                    bj.c(a, "ERROR: getAccessInfo returns %d", new Object[]{a.toString()});
                } else {
                    b();
                }
            }
        }
        return a;
    }

    public static void a(k<TransitDatabase, TransitDatabaseImpl> kVar, am<TransitDatabase, TransitDatabaseImpl> amVar) {
        l = kVar;
        i = amVar;
    }

    private static Error b(int i) {
        switch (i) {
            case 0:
                return Error.NONE;
            case 1:
                return Error.NOT_FOUND;
            case 2:
                return Error.ABORTED;
            case 3:
                return Error.INVALID_PARAMETERS;
            case 4:
                return Error.INVALID_OPERATION;
            default:
                return Error.UNKNOWN;
        }
    }
}
