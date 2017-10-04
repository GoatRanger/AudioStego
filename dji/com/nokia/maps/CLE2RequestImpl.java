package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.customlocation2.CLE2Request;
import com.here.android.mpa.customlocation2.CLE2Request.CLE2Error;
import com.here.android.mpa.customlocation2.CLE2Request.CLE2ResultListener;
import com.here.android.mpa.customlocation2.CLE2Result;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

@HybridPlus
public class CLE2RequestImpl extends BaseNativeObject {
    private static k<CLE2Request, CLE2RequestImpl> b;
    private static am<CLE2Request, CLE2RequestImpl> c;
    private CLE2ResultListener a;
    private ApplicationContext$c d = new ApplicationContext$c(this) {
        final /* synthetic */ CLE2RequestImpl a;

        {
            this.a = r1;
        }

        @HybridPlusNative
        public void a() {
            ez.a(new Runnable(this) {
                final /* synthetic */ AnonymousClass2 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.a.onCompleted(null, CLE2Error.OPERATION_NOT_ALLOWED);
                }
            });
        }

        @HybridPlusNative
        public void b() {
            this.a.executeNative(!MapServiceClient.b);
        }
    };

    private native void cancelNative();

    private native void createNative(int i, String str, GeoCoordinateImpl geoCoordinateImpl, int i2);

    private native void destroyCLERequestNative();

    private native void executeNative(boolean z);

    private native void setCustomAttributeQueryNative(String str);

    public static void a(k<CLE2Request, CLE2RequestImpl> kVar, am<CLE2Request, CLE2RequestImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static {
        ce.a(CLE2Request.class);
    }

    @HybridPlusNative
    public CLE2RequestImpl(int i) {
        this.nativeptr = i;
    }

    public CLE2RequestImpl(String str, GeoCoordinate geoCoordinate, int i) {
        a(str, geoCoordinate, i);
    }

    public CLE2RequestImpl(List<String> list, GeoCoordinate geoCoordinate, int i) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("layerIds is not valid");
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        for (String append : list) {
            stringBuilder.append(append);
            int i3 = i2 + 1;
            if (i2 != list.size()) {
                stringBuilder.append(",");
            }
            i2 = i3;
        }
        a(stringBuilder.toString(), geoCoordinate, i);
    }

    private void a(String str, GeoCoordinate geoCoordinate, int i) {
        if (!geoCoordinate.isValid()) {
            throw new IllegalArgumentException("Center is invalid");
        } else if (i <= 0) {
            throw new IllegalArgumentException("Radius should be > 0");
        } else if (str.trim().equals("")) {
            throw new IllegalArgumentException("Layer id is not valid");
        } else {
            createNative(1, str, GeoCoordinateImpl.get(geoCoordinate), i);
        }
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyCLERequestNative();
        }
    }

    public String a(CLE2ResultListener cLE2ResultListener) {
        if (cLE2ResultListener == null) {
            return CLE2Error.INVALID_PARAMETER;
        }
        if (this.a != null) {
            return CLE2Error.BUSY;
        }
        if (!MapEngine.isOnlineEnabled()) {
            return CLE2Error.NETWORK_COMMUNICATION;
        }
        this.a = cLE2ResultListener;
        b();
        return "None";
    }

    @HybridPlusNative
    private void onCLEResultNative(CLE2ResultImpl cLE2ResultImpl, String str) {
        CLE2Result cLE2Result = null;
        if (cLE2ResultImpl != null) {
            cLE2Result = CLE2ResultImpl.a(cLE2ResultImpl);
        }
        a(cLE2Result, str);
    }

    private void a(final CLE2Result cLE2Result, final String str) {
        if (this.a != null) {
            boolean equals = "None".equals(str);
            List geometries = cLE2Result == null ? null : cLE2Result.getGeometries();
            boolean z = (geometries == null || geometries.isEmpty()) ? false : true;
            l.a().a(equals, z);
            ez.a(new Runnable(this) {
                final /* synthetic */ CLE2RequestImpl c;

                public void run() {
                    this.c.a.onCompleted(cLE2Result, str);
                }
            });
        }
    }

    private void b() {
        ApplicationContext.b().check(11, this.d);
    }

    public void a() {
        cancelNative();
    }

    public void a(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Query specified is empty");
        }
        String encode;
        try {
            encode = URLEncoder.encode(str, Charset.defaultCharset().displayName());
        } catch (Exception e) {
            encode = str.toString();
        }
        setCustomAttributeQueryNative(encode);
    }
}
