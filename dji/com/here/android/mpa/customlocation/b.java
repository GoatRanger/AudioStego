package com.here.android.mpa.customlocation;

import android.accounts.NetworkErrorException;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.MapsEngine.e;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import javax.security.cert.CertificateException;

final class b {
    private static final String a = b.class.getSimpleName();
    private a<? extends CLEResponse> b;

    private static final class a {
        static String a(g gVar, int i, GeoBoundingBox geoBoundingBox, int i2, String str, String str2) throws CertificateException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a(gVar, i));
            stringBuilder.append(a(geoBoundingBox));
            stringBuilder.append(a(i2));
            stringBuilder.append(b(str));
            stringBuilder.append(a(str2));
            return stringBuilder.toString();
        }

        static String a(g gVar, int i, GeoCoordinate geoCoordinate, int i2, String str, String str2) throws CertificateException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a(gVar, i));
            stringBuilder.append(a(geoCoordinate));
            stringBuilder.append(a(i2));
            stringBuilder.append(b(str));
            stringBuilder.append(a(str2));
            return stringBuilder.toString();
        }

        static String a(g gVar, int i, int i2, int i3, String str, String str2) throws CertificateException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a(gVar, i));
            stringBuilder.append(b(i2));
            stringBuilder.append(a(i3));
            stringBuilder.append(b(str));
            stringBuilder.append(a(str2));
            return stringBuilder.toString();
        }

        static String a(g gVar, int i, int i2, String str, String str2) throws CertificateException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a(gVar, i));
            stringBuilder.append(a(i2));
            stringBuilder.append(b(str));
            stringBuilder.append(a(str2));
            return stringBuilder.toString();
        }

        static String a(g gVar, int i, GeoCoordinate geoCoordinate, int i2, int i3, String str, String str2) throws CertificateException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a(gVar, i));
            stringBuilder.append(a(geoCoordinate));
            stringBuilder.append(String.format(Locale.US, "&radius=%d", new Object[]{Integer.valueOf(i2)}));
            stringBuilder.append(a(i3));
            stringBuilder.append(b(str));
            stringBuilder.append(a(str2));
            return stringBuilder.toString();
        }

        static String a(g gVar, int i, List<GeoCoordinate> list, int i2, int i3, String str, String str2) throws CertificateException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.setLength(0);
            stringBuilder.append(a(gVar, i));
            stringBuilder.append(a((List) list));
            stringBuilder.append(String.format(Locale.US, "&radius=%d", new Object[]{Integer.valueOf(i2)}));
            stringBuilder.append(a(i3));
            stringBuilder.append(b(str));
            stringBuilder.append(a(str2));
            return stringBuilder.toString();
        }

        private static String a(int i) {
            String str = "";
            if (i <= 0) {
                return str;
            }
            return String.format(Locale.US, "&limit=%d", new Object[]{Integer.valueOf(i)});
        }

        private static String a(String str) {
            String str2 = "";
            if (str == null || str.isEmpty()) {
                return str2;
            }
            return String.format("&customAttributeQuery=%s", new Object[]{str});
        }

        private static String a(g gVar, int i) throws CertificateException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(gVar.b());
            stringBuilder.append(a());
            stringBuilder.append(String.format(Locale.US, "&layerId=%d", new Object[]{Integer.valueOf(i)}));
            return stringBuilder.toString();
        }

        private static String a() throws CertificateException {
            ApplicationContext b = ApplicationContext.b();
            if (b != null) {
                return b.e().replace("?", "");
            }
            throw new CertificateException("Missing App ID & token for HTTP request");
        }

        private static String b(String str) {
            String str2 = "";
            if (str == null || str.isEmpty()) {
                return str2;
            }
            return String.format("&query=%s", new Object[]{str});
        }

        private static String c(String str) {
            try {
                str = URLEncoder.encode(str, Charset.defaultCharset().displayName());
            } catch (Exception e) {
            }
            return str;
        }

        private static String a(GeoCoordinate geoCoordinate) {
            StringBuilder stringBuilder = new StringBuilder();
            if (geoCoordinate != null) {
                stringBuilder.append("&coord=");
                stringBuilder.append(c(String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(geoCoordinate.getLatitude()), Double.valueOf(geoCoordinate.getLongitude())})));
            }
            return stringBuilder.toString();
        }

        private static String b(int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("&geometryId=");
            stringBuilder.append(i);
            return stringBuilder.toString();
        }

        private static String a(GeoBoundingBox geoBoundingBox) {
            StringBuilder stringBuilder = new StringBuilder();
            if (geoBoundingBox != null) {
                stringBuilder.append("&bbox=");
                stringBuilder.append(c(String.format(Locale.US, "%f,%f;%f,%f", new Object[]{Double.valueOf(geoBoundingBox.getTopLeft().getLatitude()), Double.valueOf(geoBoundingBox.getTopLeft().getLongitude()), Double.valueOf(geoBoundingBox.getBottomRight().getLatitude()), Double.valueOf(geoBoundingBox.getBottomRight().getLongitude())})));
            }
            return stringBuilder.toString();
        }

        private static String a(List<GeoCoordinate> list) {
            StringBuilder stringBuilder = new StringBuilder();
            if (!(list == null || list.isEmpty())) {
                stringBuilder.append("&route=");
                int i = 0;
                String str = "";
                while (i < list.size()) {
                    GeoCoordinate geoCoordinate = (GeoCoordinate) list.get(i);
                    String concat = str.concat(String.format(Locale.US, "%f,%f", new Object[]{Double.valueOf(geoCoordinate.getLatitude()), Double.valueOf(geoCoordinate.getLongitude())}));
                    if (i + 1 < list.size()) {
                        concat = concat.concat(",");
                    }
                    i++;
                    str = concat;
                }
                stringBuilder.append(c(str));
            }
            return stringBuilder.toString();
        }
    }

    b() {
        if (MapsEngine.b() != e.EInitalized) {
            throw new RuntimeException("Cannot created HERE SDK objects before MapEngine is initialized.  See MapEngine.init()");
        }
    }

    void a(int i, GeoCoordinate geoCoordinate, int i2, int i3, String str, String str2, f fVar) throws CertificateException {
        String a = a.a(g.PROXIMITY, i, geoCoordinate, i2, i3, str, str2);
        this.b = new e(g.PROXIMITY, new WeakReference(fVar));
        this.b.execute(new String[]{a});
    }

    void a(int i, List<GeoCoordinate> list, int i2, int i3, String str, String str2, f fVar) throws CertificateException, NetworkErrorException, IllegalArgumentException {
        if (i >= 1 || i2 >= 0) {
            String a = a.a(g.CORRIDOR, i, (List) list, i2, i3, str, str2);
            this.b = new e(g.CORRIDOR, new WeakReference(fVar));
            this.b.execute(new String[]{a});
            return;
        }
        throw new IllegalArgumentException("layerId should be > 0 & radius should be >= 0");
    }

    void a(int i, int i2, String str, String str2, f fVar) throws CertificateException, IllegalArgumentException {
        if (i < 1) {
            throw new IllegalArgumentException("layerId should be > 0");
        }
        String a = a.a(g.CUSTOM_ATTRIBUTE, i, i2, str, str2);
        this.b = new e(g.CUSTOM_ATTRIBUTE, new WeakReference(fVar));
        this.b.execute(new String[]{a});
    }

    void a(int i, GeoBoundingBox geoBoundingBox, int i2, String str, String str2, f fVar) throws NetworkErrorException, CertificateException, IllegalArgumentException {
        if (i < 1) {
            throw new IllegalArgumentException("layerId should be > 0");
        }
        String a = a.a(g.BOUNDING_BOX, i, geoBoundingBox, i2, str, str2);
        this.b = new e(g.BOUNDING_BOX, new WeakReference(fVar));
        this.b.execute(new String[]{a});
    }

    void b(int i, GeoBoundingBox geoBoundingBox, int i2, String str, String str2, f fVar) throws NetworkErrorException, CertificateException, IllegalArgumentException {
        if (i < 1) {
            throw new IllegalArgumentException("layerId should be > 0");
        }
        String a = a.a(g.GEOMETRY_BOUNDING_BOX, i, geoBoundingBox, i2, str, str2);
        this.b = new c(g.GEOMETRY_BOUNDING_BOX, new WeakReference(fVar));
        this.b.execute(new String[]{a});
    }

    void a(int i, GeoCoordinate geoCoordinate, int i2, String str, String str2, f fVar) throws NetworkErrorException, CertificateException, IllegalArgumentException {
        if (i < 1) {
            throw new IllegalArgumentException("layerId should be > 0");
        }
        String a = a.a(g.GEOMETRY_POINT, i, geoCoordinate, i2, str, str2);
        this.b = new c(g.GEOMETRY_POINT, new WeakReference(fVar));
        this.b.execute(new String[]{a});
    }

    void a(int i, int i2, int i3, String str, String str2, f fVar) throws NetworkErrorException, CertificateException, IllegalArgumentException {
        if (i < 1) {
            throw new IllegalArgumentException("layerId should be > 0");
        }
        String a = a.a(g.GEOMETRY_ID, i, i2, i3, str, str2);
        this.b = new c(g.GEOMETRY_ID, new WeakReference(fVar));
        this.b.execute(new String[]{a});
    }

    void a() {
        if (this.b != null) {
            this.b.cancel(true);
        }
    }
}
