package com.nokia.maps;

import java.util.ArrayList;
import java.util.List;

class av {

    private static class a {
        public Double a;
        public Double b;
        public Double c;

        public a() {
            this.a = Double.valueOf(0.0d);
            this.b = Double.valueOf(0.0d);
            this.c = Double.valueOf(0.0d);
        }

        public a(double d, double d2, double d3) {
            this.a = Double.valueOf(d);
            this.b = Double.valueOf(d2);
            this.c = Double.valueOf(d3);
        }
    }

    av() {
    }

    public List<GeoCoordinateImpl> a(GeoCoordinateImpl geoCoordinateImpl, GeoCoordinateImpl geoCoordinateImpl2) {
        if (!geoCoordinateImpl.d()) {
            throw new IllegalArgumentException("Start coordinate is invalid");
        } else if (geoCoordinateImpl2.d()) {
            List<GeoCoordinateImpl> arrayList = new ArrayList();
            int intValue = Double.valueOf(geoCoordinateImpl.a(geoCoordinateImpl2)).intValue() / 100000;
            a aVar = new a((geoCoordinateImpl.b() * 3.141592653589793d) / 180.0d, (geoCoordinateImpl.a() * 3.141592653589793d) / 180.0d, 0.0d);
            a aVar2 = new a((geoCoordinateImpl2.b() * 3.141592653589793d) / 180.0d, (geoCoordinateImpl2.a() * 3.141592653589793d) / 180.0d, 0.0d);
            a aVar3 = new a();
            a aVar4 = new a();
            aVar3.a = Double.valueOf((6.378E7d * Math.cos(aVar.a.doubleValue())) * Math.cos(aVar.b.doubleValue()));
            aVar3.b = Double.valueOf((6.378E7d * Math.sin(aVar.a.doubleValue())) * Math.cos(aVar.b.doubleValue()));
            aVar3.c = Double.valueOf(Math.sin(aVar.b.doubleValue()) * 6.378E7d);
            aVar4.a = Double.valueOf((6.378E7d * Math.cos(aVar2.a.doubleValue())) * Math.cos(aVar2.b.doubleValue()));
            aVar4.b = Double.valueOf((6.378E7d * Math.sin(aVar2.a.doubleValue())) * Math.cos(aVar2.b.doubleValue()));
            aVar4.c = Double.valueOf(6.378E7d * Math.sin(aVar2.b.doubleValue()));
            if (intValue > 0) {
                for (int i = 0; i < intValue; i++) {
                    aVar = new a(aVar3.a.doubleValue() + ((((double) i) * (aVar4.a.doubleValue() - aVar3.a.doubleValue())) / ((double) intValue)), aVar3.b.doubleValue() + ((((double) i) * (aVar4.b.doubleValue() - aVar3.b.doubleValue())) / ((double) intValue)), aVar3.c.doubleValue() + ((((double) i) * (aVar4.c.doubleValue() - aVar3.c.doubleValue())) / ((double) intValue)));
                    Double valueOf = Double.valueOf(6.378E7d / Math.sqrt((Math.pow(aVar.a.doubleValue(), 2.0d) + Math.pow(aVar.b.doubleValue(), 2.0d)) + Math.pow(aVar.c.doubleValue(), 2.0d)));
                    aVar.a = Double.valueOf(aVar.a.doubleValue() * valueOf.doubleValue());
                    aVar.b = Double.valueOf(aVar.b.doubleValue() * valueOf.doubleValue());
                    aVar.c = Double.valueOf(aVar.c.doubleValue() * valueOf.doubleValue());
                    a aVar5 = new a();
                    aVar5.a = Double.valueOf((Math.atan(aVar.b.doubleValue() / aVar.a.doubleValue()) * 180.0d) / 3.141592653589793d);
                    if (aVar.a.doubleValue() < 0.0d && aVar.b.doubleValue() < 0.0d) {
                        aVar5.a = Double.valueOf(aVar5.a.doubleValue() - 180.0d);
                    }
                    if (aVar.a.doubleValue() < 0.0d && aVar.b.doubleValue() > 0.0d) {
                        aVar5.a = Double.valueOf(aVar5.a.doubleValue() + 180.0d);
                    }
                    aVar5.b = Double.valueOf((Math.asin(aVar.c.doubleValue() / 6.378E7d) * 180.0d) / 3.141592653589793d);
                    arrayList.add(new GeoCoordinateImpl(aVar5.b.doubleValue(), aVar5.a.doubleValue()));
                    bj.e("MapPolyline", "%d intcoord = %s", new Object[]{Integer.valueOf(i), ((GeoCoordinateImpl) arrayList.get(i)).toString()});
                }
            } else {
                arrayList.add(geoCoordinateImpl);
            }
            arrayList.add(geoCoordinateImpl2);
            return arrayList;
        } else {
            throw new IllegalArgumentException("End coordinate is invalid");
        }
    }
}
