package com.here.android.mpa.mapping;

import com.nokia.maps.ApplicationContext;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Locale;

@HybridPlus
public final class HistoricalTrafficRasterTileSource extends UrlMapRasterTileSourceBase {
    private int a;
    private int b;
    private int c;

    public HistoricalTrafficRasterTileSource(int i, int i2) {
        this(i, i2, 0);
    }

    public HistoricalTrafficRasterTileSource(int i, int i2, int i3) {
        super(MapRasterTileSource$a.HISTORICAL_TRAFFIC);
        if (i < 1 || i > 7) {
            throw new IllegalArgumentException("Invalid dayOfWeek specified.  Valid range is >= Calender.SUNDAY && <= Calendar.SATURDAY");
        } else if (i2 > 23 || i2 < 0) {
            throw new IllegalArgumentException("Invalid hourOfDay specified.  Valid range is >= 0 && <= 23");
        } else if (i3 < 0 || i3 >= 60) {
            throw new IllegalArgumentException("Invalid minOfHour specified.  Valid range is >= 0 && < 60");
        } else {
            this.a = i;
            this.b = i2;
            this.c = i3;
            setOverlayType(MapOverlayType.ROAD_OVERLAY);
            setCachePrefix(getClass().getCanonicalName() + "-" + this.a + "." + this.b + ":" + this.c);
            setCacheExpiration(86400);
            setCachingEnabled(true);
        }
    }

    private int a() {
        return ((this.c * 60) + (this.b * 3600)) + (86400 * (this.a - 1));
    }

    public String getUrl(int i, int i2, int i3) {
        return String.format(Locale.US, MapsEngine.h() + "%d/%d/%d/256/png8%s&pattern_time=%d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2), ApplicationContext.b().e(), Integer.valueOf(a())});
    }
}
