package com.nokia.maps.a;

import com.here.a.a.a.a.i;
import com.here.android.mpa.urbanmobility.CoverageType;

public class n {
    public static CoverageType a(i iVar) {
        switch (iVar) {
            case REAL_TIME:
                return CoverageType.REAL_TIME;
            case SIMPLE_ROUTING:
                return CoverageType.SIMPLE_ROUTING;
            case TIME_TABLE:
                return CoverageType.TIME_TABLE;
            default:
                return CoverageType.UNKNOWN;
        }
    }
}
