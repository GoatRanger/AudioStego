package com.here.android.mpa.customlocation;

import com.here.android.mpa.customlocation.Request.Error;
import com.here.android.mpa.search.ErrorCode;
import com.nokia.maps.ak;
import com.nokia.maps.bj;
import com.nokia.maps.cn;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;

abstract class a<T> extends cn<Void, T> {
    private static final String c = a.class.getSimpleName();
    protected final g a;
    protected WeakReference<f> b;

    protected abstract T a(String str) throws ak;

    protected abstract void a(T t, Error error);

    a(g gVar, WeakReference<f> weakReference) {
        super(true);
        this.a = gVar;
        this.b = weakReference;
    }

    protected void a(T t) {
        a(t, Error.NONE);
    }

    protected void a(ErrorCode errorCode) {
        bj.c(c, "error=%s", new Object[]{errorCode.toString()});
        Error error = Error.NONE;
        if (this.b != null) {
            switch (errorCode) {
                case NONE:
                case CREATED:
                case ACCEPTED:
                case HTTP:
                    break;
                case NO_CONTENT:
                    error = Error.NO_CONTENT;
                    break;
                case BAD_REQUEST:
                    error = Error.BAD_REQUEST;
                    break;
                case UNAUTHORIZED:
                    error = Error.UNAUTHORIZED;
                    break;
                case FORBIDDEN:
                    error = Error.FORBIDDEN;
                    break;
                case NOT_FOUND:
                    error = Error.NOT_FOUND;
                    break;
                case NETWORK_COMMUNICATION:
                    error = Error.NETWORK_COMMUNICATION;
                    break;
                case SERVER_INTERNAL:
                    error = Error.SERVER_INTERNAL;
                    break;
                case SERVICE_UNAVAILABLE:
                    error = Error.SERVICE_UNAVAILABLE;
                    break;
                default:
                    error = Error.UNKNOWN;
                    break;
            }
            if (error != Error.NONE) {
                a(null, error);
            }
        }
    }

    protected T a(byte[] bArr) throws ak {
        try {
            return a(new String(bArr, Charset.forName("UTF-8")));
        } catch (Exception e) {
            bj.c(c, "Exception thrown: %s", new Object[]{e.getLocalizedMessage()});
            return null;
        }
    }
}
