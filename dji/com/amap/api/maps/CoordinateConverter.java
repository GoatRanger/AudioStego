package com.amap.api.maps;

import android.content.Context;
import com.amap.api.mapcore.util.bb;
import com.amap.api.mapcore.util.ee;
import com.amap.api.maps.model.LatLng;

public class CoordinateConverter {
    private Context a;
    private CoordType b = null;
    private LatLng c = null;

    public enum CoordType {
        BAIDU,
        MAPBAR,
        GPS,
        MAPABC,
        SOSOMAP,
        ALIYUN,
        GOOGLE
    }

    public CoordinateConverter(Context context) {
        this.a = context;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.b = coordType;
        return this;
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.c = latLng;
        return this;
    }

    public LatLng convert() {
        if (this.b == null || this.c == null) {
            return null;
        }
        try {
            switch (a.a[this.b.ordinal()]) {
                case 1:
                    return bb.a(this.c);
                case 2:
                    return bb.b(this.a, this.c);
                case 3:
                case 4:
                case 5:
                case 6:
                    return this.c;
                case 7:
                    return bb.a(this.a, this.c);
                default:
                    return null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            ee.a(th, "CoordinateConverter", "convert");
            return this.c;
        }
    }
}
