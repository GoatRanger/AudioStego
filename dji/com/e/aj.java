package com.e;

import com.amap.api.maps.AMapException;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;

public class aj {
    private static aj a;

    public static aj a() {
        if (a == null) {
            a = new aj();
        }
        return a;
    }

    public HttpURLConnection a(ao aoVar, boolean z) throws ct {
        ct e;
        try {
            c(aoVar);
            Proxy proxy = aoVar.e == null ? null : aoVar.e;
            HttpURLConnection a = (z ? new am(aoVar.c, aoVar.d, proxy, true) : new am(aoVar.c, aoVar.d, proxy, false)).a(aoVar.i(), aoVar.b(), true);
            byte[] j = aoVar.j();
            if (j != null && j.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(j);
                dataOutputStream.close();
            }
            a.connect();
            return a;
        } catch (ct e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new ct(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] a(ao aoVar) throws ct {
        ct e;
        try {
            ap b = b(aoVar, true);
            return b != null ? b.a : null;
        } catch (ct e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new ct(AMapException.ERROR_UNKNOWN);
        }
    }

    protected ap b(ao aoVar, boolean z) throws ct {
        ct e;
        try {
            c(aoVar);
            return new am(aoVar.c, aoVar.d, aoVar.e == null ? null : aoVar.e, z).a(aoVar.i(), aoVar.b(), aoVar.j());
        } catch (ct e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new ct(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] b(ao aoVar) throws ct {
        ct e;
        try {
            ap b = b(aoVar, false);
            return b != null ? b.a : null;
        } catch (ct e2) {
            throw e2;
        } catch (Throwable th) {
            dg.a(th, "BaseNetManager", "makeSyncPostRequest");
            e2 = new ct(AMapException.ERROR_UNKNOWN);
        }
    }

    protected void c(ao aoVar) throws ct {
        if (aoVar == null) {
            throw new ct("requeust is null");
        } else if (aoVar.c() == null || "".equals(aoVar.c())) {
            throw new ct("request url is empty");
        }
    }
}
