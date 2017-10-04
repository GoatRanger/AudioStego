package com.amap.api.mapcore.util;

import com.amap.api.maps.AMapException;
import java.net.Proxy;

public class fq {
    private static fq a;

    public static fq a() {
        if (a == null) {
            a = new fq();
        }
        return a;
    }

    public byte[] a(fw fwVar) throws dk {
        dk e;
        try {
            fy a = a(fwVar, true);
            if (a != null) {
                return a.a;
            }
            return null;
        } catch (dk e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new dk(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] b(fw fwVar) throws dk {
        dk e;
        try {
            fy a = a(fwVar, false);
            if (a != null) {
                return a.a;
            }
            return null;
        } catch (dk e2) {
            throw e2;
        } catch (Throwable th) {
            eb.a(th, "BaseNetManager", "makeSyncPostRequest");
            e2 = new dk(AMapException.ERROR_UNKNOWN);
        }
    }

    protected void c(fw fwVar) throws dk {
        if (fwVar == null) {
            throw new dk("requeust is null");
        } else if (fwVar.a() == null || "".equals(fwVar.a())) {
            throw new dk("request url is empty");
        }
    }

    protected fy a(fw fwVar, boolean z) throws dk {
        dk e;
        try {
            Proxy proxy;
            c(fwVar);
            if (fwVar.i == null) {
                proxy = null;
            } else {
                proxy = fwVar.i;
            }
            return new fs(fwVar.g, fwVar.h, proxy, z).a(fwVar.f(), fwVar.c(), fwVar.g());
        } catch (dk e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new dk(AMapException.ERROR_UNKNOWN);
        }
    }
}
