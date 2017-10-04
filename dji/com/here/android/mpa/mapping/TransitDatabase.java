package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Identifier;
import com.nokia.maps.TransitDatabaseImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public final class TransitDatabase {
    private TransitDatabaseImpl a;

    @Online
    public enum Error {
        NONE,
        NOT_FOUND,
        ABORTED,
        INVALID_PARAMETERS,
        INVALID_OPERATION,
        UNKNOWN
    }

    @Online
    public interface OnGetTransitInfoListener {
        void onEnd(Error error);

        void onTransitAccessInfo(TransitAccessInfo transitAccessInfo);

        void onTransitLineInfo(TransitLineInfo transitLineInfo);

        void onTransitStopInfo(TransitStopInfo transitStopInfo);

        void onTransitSystemInfo(TransitSystemInfo transitSystemInfo);
    }

    @Online
    public static abstract class OnGetTransitInfoListenerAdapter implements OnGetTransitInfoListener {
        public abstract void onEnd(Error error);

        public void onTransitLineInfo(TransitLineInfo transitLineInfo) {
        }

        public void onTransitStopInfo(TransitStopInfo transitStopInfo) {
        }

        public void onTransitSystemInfo(TransitSystemInfo transitSystemInfo) {
        }

        public void onTransitAccessInfo(TransitAccessInfo transitAccessInfo) {
        }
    }

    public TransitDatabase() {
        this.a = new TransitDatabaseImpl();
    }

    private TransitDatabase(TransitDatabaseImpl transitDatabaseImpl) {
        this.a = transitDatabaseImpl;
    }

    public Error getLineInfo(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        return this.a.b(identifier, onGetTransitInfoListener);
    }

    public Error getStopInfo(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        return this.a.c(identifier, onGetTransitInfoListener);
    }

    public Error getAccessInfo(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        return this.a.d(identifier, onGetTransitInfoListener);
    }

    public Error getSystemInfo(Identifier identifier, OnGetTransitInfoListener onGetTransitInfoListener) {
        return this.a.a(identifier, onGetTransitInfoListener);
    }

    public void cancel() {
        this.a.cancel();
    }

    static {
        TransitDatabaseImpl.a(new k<TransitDatabase, TransitDatabaseImpl>() {
            public TransitDatabaseImpl a(TransitDatabase transitDatabase) {
                return transitDatabase.a;
            }
        }, new am<TransitDatabase, TransitDatabaseImpl>() {
            public TransitDatabase a(TransitDatabaseImpl transitDatabaseImpl) {
                return new TransitDatabase(transitDatabaseImpl);
            }
        });
    }
}
