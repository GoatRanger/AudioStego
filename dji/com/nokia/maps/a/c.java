package com.nokia.maps.a;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.here.a.a.a.a.ag;
import com.here.a.a.a.a.o;
import com.here.a.a.a.a.x;
import com.here.a.a.a.i;
import com.here.a.a.a.i.d;
import com.here.a.a.a.l;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.urbanmobility.AbstractRequest;
import com.here.android.mpa.urbanmobility.ErrorCode;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.here.android.mpa.urbanmobility.TransportType;
import com.nokia.maps.MapSettings;
import com.nokia.maps.ak;
import com.nokia.maps.am;
import com.nokia.maps.bj;
import com.nokia.maps.ce;
import com.nokia.maps.cn;
import com.nokia.maps.ez;
import com.nokia.maps.k;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class c<SDKType, UMType, UMReqType extends i> extends cn<Void, ag<UMType>> {
    private static final String b = c.class.getSimpleName();
    private static k<AbstractRequest, c> g;
    private static am<AbstractRequest, c> h;
    protected UMReqType a;
    private final RequestManager$ResponseListener<SDKType> c;
    private AtomicBoolean d;
    private List<TransportType> e;
    private final int[] f;

    protected abstract l<UMType, UMReqType> b();

    protected abstract SDKType b(UMType uMType);

    protected abstract void c();

    protected /* synthetic */ Object a(byte[] bArr) throws ak {
        return b(bArr);
    }

    static {
        o.a(com.here.a.a.a.c.class);
        ce.a(AbstractRequest.class);
    }

    public c(int i, UMReqType uMReqType, RequestManager$ResponseListener<SDKType> requestManager$ResponseListener) {
        this(new int[]{i}, (i) uMReqType, (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public c(int[] iArr, UMReqType uMReqType, RequestManager$ResponseListener<SDKType> requestManager$ResponseListener) {
        if (uMReqType == null) {
            throw new IllegalArgumentException("Request implementation can't be null.");
        }
        this.f = iArr;
        this.a = uMReqType;
        this.c = requestManager$ResponseListener;
        this.d = new AtomicBoolean(false);
        this.e = new ArrayList(TransportType.values().length);
        Collections.addAll(this.e, TransportType.values());
        this.a.b(Locale.getDefault().getLanguage());
    }

    public void d() {
        new t(this, this.f) {
            final /* synthetic */ c a;

            protected void c() {
                if (this.a.a.e()) {
                    this.a.a();
                    return;
                }
                String d = this.a.a.d();
                if (VERSION.SDK_INT >= 11) {
                    this.a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{d});
                    return;
                }
                this.a.execute(new String[]{d});
            }

            protected void d() {
                this.a.a(ErrorCode.OPERATION_NOT_ALLOWED, null);
            }
        }.e();
    }

    private void a() {
        try {
            a(b(this.a.f().getBytes(Charset.forName("UTF-8"))));
        } catch (ak e) {
            a(com.here.android.mpa.search.ErrorCode.NO_CONTENT);
        } catch (Throwable e2) {
            bj.c(b, bj.a(e2), new Object[0]);
            a(com.here.android.mpa.search.ErrorCode.UNKNOWN);
        }
    }

    protected ag<UMType> b(byte[] bArr) throws ak {
        try {
            return b().a(new String(bArr, Charset.forName("UTF-8")), this.a);
        } catch (Throwable e) {
            bj.c(b, bj.a(e), new Object[0]);
            throw new ak(e.getMessage());
        } catch (Throwable e2) {
            bj.c(b, bj.a(e2), new Object[0]);
            throw new ak(e2.getMessage());
        }
    }

    protected void a(final ag<UMType> agVar) {
        a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                if (agVar.a()) {
                    this.b.a(this.b.a((x) agVar.a.b()), (String) ((x) agVar.a.b()).d.c(""));
                } else if (agVar.b.c()) {
                    this.b.c.onSuccess(this.b.b(agVar.b.b()));
                } else {
                    this.b.a(ErrorCode.INVALID_RESPONSE, null);
                }
            }
        });
        this.d.set(true);
    }

    protected void a(final com.here.android.mpa.search.ErrorCode errorCode) {
        a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                this.b.a(this.b.b(errorCode), null);
            }
        });
        this.d.set(true);
    }

    public void a(String str) {
        this.a.a(str);
    }

    public void b(String str) {
        this.a.b(str);
    }

    public void a(List<TransitType> list) {
        d[] dVarArr;
        this.e.clear();
        if (list == null || list.isEmpty()) {
            dVarArr = new d[0];
        } else {
            d[] dVarArr2 = new d[list.size()];
            for (int i = 0; i < list.size(); i++) {
                TransportType a = ba.a((TransitType) list.get(i));
                dVarArr2[i] = ba.a(a);
                this.e.add(a);
            }
            dVarArr = dVarArr2;
        }
        this.a.a(dVarArr);
    }

    public void b(List<TransportType> list) {
        d[] dVarArr;
        this.e.clear();
        if (list == null || list.isEmpty()) {
            dVarArr = new d[0];
        } else {
            d[] dVarArr2 = new d[list.size()];
            for (int i = 0; i < list.size(); i++) {
                dVarArr2[i] = ba.a((TransportType) list.get(i));
                this.e.add(list.get(i));
            }
            dVarArr = dVarArr2;
        }
        this.a.a(dVarArr);
    }

    public String e() {
        return this.a.g();
    }

    public String f() {
        return this.a.h();
    }

    public String g() {
        return this.a.i();
    }

    public List<TransitType> h() {
        List<TransitType> arrayList = new ArrayList(this.e.size());
        for (TransportType b : this.e) {
            arrayList.add(ba.b(b));
        }
        return arrayList;
    }

    private void a(ErrorCode errorCode, String str) {
        c();
        this.c.onError(errorCode, str);
    }

    private static void a(Runnable runnable) {
        if (MapSettings.l()) {
            ez.a(runnable);
        } else {
            runnable.run();
        }
    }

    private ErrorCode a(x xVar) {
        switch (xVar.a) {
            case UNAUTHORIZED:
                return ErrorCode.INVALID_CREDENTIALS;
            case INVALID_PARAMETERS:
                return ErrorCode.INVALID_PARAMETERS;
            case NOT_FOUND:
                return ErrorCode.NOT_FOUND;
            case ROUTING_NOT_POSSIBLE:
                if (xVar.c.c()) {
                    switch ((com.here.a.a.a.a.x.c) xVar.c.b()) {
                        case DEP_ARR_TOO_CLOSE:
                            return ErrorCode.START_DESTINATION_TOO_CLOSE;
                        case NO_COVERAGE:
                            return ErrorCode.NO_COVERAGE;
                        case NO_STATION_NEARBY:
                            return ErrorCode.NO_STATION_NEARBY;
                    }
                }
                return ErrorCode.ROUTING_NOT_POSSIBLE;
            case UNAVAILABLE_API:
                return ErrorCode.UNAVAILABLE_API;
            case INVALID_PERIOD:
                return ErrorCode.INVALID_PERIOD;
            default:
                return ErrorCode.UNKNOWN;
        }
    }

    private ErrorCode b(com.here.android.mpa.search.ErrorCode errorCode) {
        switch (errorCode) {
            case NOT_FOUND:
                return ErrorCode.NOT_FOUND;
            case NETWORK_REQUIRED:
            case SERVER_CONNECTION:
            case NETWORK_COMMUNICATION:
            case NETWORK_SERVER:
            case HTTP:
            case NETWORK_UNKNOWN:
                return ErrorCode.NETWORK_COMMUNICATION;
            case OUT_OF_MEMORY:
                return ErrorCode.OUT_OF_MEMORY;
            case CANCEL:
            case CANCELLED:
                return ErrorCode.CANCELLED;
            case NO_CONTENT:
                return ErrorCode.INVALID_RESPONSE;
            case SERVICE_UNAVAILABLE:
                return ErrorCode.SERVICE_UNAVAILABLE;
            case UNAUTHORIZED:
            case INVALID_CREDENTIALS:
                return ErrorCode.INVALID_CREDENTIALS;
            case FORBIDDEN:
            case OPERATION_NOT_ALLOWED:
                return ErrorCode.OPERATION_NOT_ALLOWED;
            case NOT_ACCEPTABLE:
                return ErrorCode.INVALID_RESPONSE;
            case INVALID_PARAMETER:
                return ErrorCode.INVALID_PARAMETERS;
            default:
                return ErrorCode.UNKNOWN;
        }
    }

    public static void a(k<AbstractRequest, c> kVar, am<AbstractRequest, c> amVar) {
        g = kVar;
        h = amVar;
    }
}
