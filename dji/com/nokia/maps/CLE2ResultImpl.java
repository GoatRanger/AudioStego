package com.nokia.maps;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.customlocation2.CLE2Geometry;
import com.here.android.mpa.customlocation2.CLE2MultiPointGeometry;
import com.here.android.mpa.customlocation2.CLE2PointGeometry;
import com.here.android.mpa.customlocation2.CLE2Result;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@HybridPlus
public final class CLE2ResultImpl extends BaseNativeObject {
    private static k<CLE2Result, CLE2ResultImpl> a;
    private static am<CLE2Result, CLE2ResultImpl> b;

    private native void destroyNative();

    private native CLE2GeometryImpl[] getGeometriesNative();

    public static void a(k<CLE2Result, CLE2ResultImpl> kVar, am<CLE2Result, CLE2ResultImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    @OnlineNative
    static CLE2ResultImpl get(CLE2Result cLE2Result) {
        if (a != null) {
            return (CLE2ResultImpl) a.a(cLE2Result);
        }
        return null;
    }

    static CLE2Result a(CLE2ResultImpl cLE2ResultImpl) {
        if (cLE2ResultImpl != null) {
            return (CLE2Result) b.a(cLE2ResultImpl);
        }
        return null;
    }

    static {
        ce.a(CLE2Result.class);
    }

    @HybridPlusNative
    private CLE2ResultImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }

    public List<CLE2Geometry> a() {
        CLE2GeometryImpl[] geometriesNative = getGeometriesNative();
        List<CLE2Geometry> arrayList = new ArrayList();
        Pattern compile = Pattern.compile("\\(*\\((.*?)\\)");
        for (CLE2GeometryImpl cLE2GeometryImpl : geometriesNative) {
            String geometryNative = cLE2GeometryImpl.getGeometryNative();
            Matcher matcher = compile.matcher(geometryNative);
            List arrayList2 = new ArrayList();
            while (matcher.find()) {
                String[] split = matcher.group(1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                arrayList2.add(new GeoCoordinate(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
            }
            if (geometryNative.contains("MULTIPOINT")) {
                CLE2MultiPointGeometry cLE2MultiPointGeometry = new CLE2MultiPointGeometry(cLE2GeometryImpl);
                cLE2MultiPointGeometry.setMultiPoint(arrayList2);
                arrayList.add(cLE2MultiPointGeometry);
            } else if (geometryNative.contains("POINT")) {
                CLE2PointGeometry cLE2PointGeometry = new CLE2PointGeometry(cLE2GeometryImpl);
                cLE2PointGeometry.setPoint((GeoCoordinate) arrayList2.get(0));
                arrayList.add(cLE2PointGeometry);
            }
        }
        return arrayList;
    }
}
