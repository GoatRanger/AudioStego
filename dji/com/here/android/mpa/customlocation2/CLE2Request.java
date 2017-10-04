package com.here.android.mpa.customlocation2;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.CLE2RequestImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public class CLE2Request {
    CLE2RequestImpl a;

    @HybridPlus
    public static class CLE2Error {
        public static final String BUSY = "Busy";
        public static final String INVALID_PARAMETER = "Invalid Parameter";
        public static final String NETWORK_COMMUNICATION = "Network Communication";
        public static final String NONE = "None";
        public static final String OPERATION_NOT_ALLOWED = "Operation not allowed";
    }

    @HybridPlus
    public interface CLE2ResultListener {
        void onCompleted(CLE2Result cLE2Result, String str);
    }

    @HybridPlusNative
    CLE2Request(CLE2RequestImpl cLE2RequestImpl) {
        this.a = cLE2RequestImpl;
    }

    CLE2Request(String str, GeoCoordinate geoCoordinate, int i) {
        this.a = new CLE2RequestImpl(str, geoCoordinate, i);
    }

    CLE2Request(List<String> list, GeoCoordinate geoCoordinate, int i) {
        this.a = new CLE2RequestImpl((List) list, geoCoordinate, i);
    }

    public String execute(CLE2ResultListener cLE2ResultListener) {
        return this.a.a(cLE2ResultListener);
    }

    public void cancel() {
        this.a.a();
    }

    public void setQuery(String str) {
        this.a.a(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (CLE2Request.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
    }

    static {
        CLE2RequestImpl.a(new k<CLE2Request, CLE2RequestImpl>() {
            public CLE2RequestImpl a(CLE2Request cLE2Request) {
                return cLE2Request.a;
            }
        }, new am<CLE2Request, CLE2RequestImpl>() {
            public CLE2Request a(CLE2RequestImpl cLE2RequestImpl) {
                if (cLE2RequestImpl != null) {
                    return new CLE2Request(cLE2RequestImpl);
                }
                return null;
            }
        });
    }
}
