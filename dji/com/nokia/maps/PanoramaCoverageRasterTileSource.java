package com.nokia.maps;

import com.here.android.mpa.mapping.MapOverlayType;
import com.here.android.mpa.mapping.UrlMapRasterTileSourceBase;
import com.nokia.maps.MapRasterTileSourceImpl.a;
import com.nokia.maps.annotation.HybridPlusNative;

public class PanoramaCoverageRasterTileSource extends UrlMapRasterTileSourceBase {
    private static final String a = PanoramaCoverageRasterTileSource.class.getSimpleName();
    private cq b = new cq(PanoramaCoverageRasterTileSource.class.getName());

    public PanoramaCoverageRasterTileSource() {
        this.m_impl.a(a.PIXEL_FORMAT_DISTANCE_FIELD_24);
        setOverlayType(MapOverlayType.ROAD_OVERLAY);
        setCacheExpiration(7776000);
    }

    public void a() {
        hideAtZoomRange(15, 20);
        showAtZoomRange(0, 15);
    }

    public void b() {
        hideAtZoomRange(0, 4);
        hideAtZoomRange(15, 20);
        showAtZoomRange(4, 15);
    }

    @HybridPlusNative
    public String getUrl(int i, int i2, int i3) {
        String format;
        Exception e;
        try {
            format = String.format("%s/%02d/%03d/%03d/%02d/%02d/cov_z%d_c%d_r%d.png", new Object[]{MapsEngine.n(), Integer.valueOf(i3), Integer.valueOf((i / 1000) % 1000), Integer.valueOf((i2 / 1000) % 1000), Integer.valueOf((i / 10) % 100), Integer.valueOf((i2 / 10) % 100), Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2)});
            try {
                ApplicationContext b = ApplicationContext.b();
                if (b != null) {
                    String e2 = b.e();
                    if (e2 != null) {
                        format = format + e2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                bj.c(a, "%s", new Object[]{e.toString()});
                return format;
            }
        } catch (Exception e4) {
            Exception exception = e4;
            format = null;
            e = exception;
            bj.c(a, "%s", new Object[]{e.toString()});
            return format;
        }
        return format;
    }
}
