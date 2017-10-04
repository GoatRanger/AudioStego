package com.nokia.maps.a;

import com.here.a.a.a.a;
import com.here.a.a.a.a.g;
import com.here.a.a.a.a.m;
import com.here.a.a.a.l;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.CityCoverageRequest;
import com.here.android.mpa.urbanmobility.CityCoverageRequest.UpdateType;
import com.here.android.mpa.urbanmobility.CityCoverageResult;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Date;
import java.util.Locale;

public class i extends b<CityCoverageResult, g, a> {
    private static am<CityCoverageRequest, i> b;

    protected /* synthetic */ Object b(Object obj) {
        return a((g) obj);
    }

    public i(String str, String str2, String str3, RequestManager$ResponseListener<CityCoverageResult> requestManager$ResponseListener) {
        super(48, new a(str, str2, str3, MapsEngine.g().equals(Locale.CHINA.getISO3Country())), (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public void a(boolean z) {
        ((a) this.a).a(Boolean.valueOf(z));
    }

    public void b(int i) {
        ((a) this.a).a(Integer.valueOf(i));
    }

    public void c(int i) {
        ((a) this.a).b(Integer.valueOf(i));
    }

    public void a(GeoCoordinate geoCoordinate) {
        ((a) this.a).a(geoCoordinate != null ? new m(geoCoordinate.getLatitude(), geoCoordinate.getLongitude()) : null);
    }

    public void a(Date date) {
        ((a) this.a).a(date);
    }

    public void a(UpdateType updateType) {
        a.a aVar;
        if (updateType != null) {
            switch (updateType) {
                case NEW:
                    aVar = a.a.NEW;
                    break;
                case UPDATED:
                    aVar = a.a.UPDATED;
                    break;
                default:
                    aVar = a.a.ALL;
                    break;
            }
        }
        aVar = null;
        ((a) this.a).a(aVar);
    }

    protected l<g, a> b() {
        return l.g();
    }

    protected CityCoverageResult a(g gVar) {
        com.nokia.maps.l.a().g(false);
        return j.a(new j(gVar));
    }

    protected void c() {
        com.nokia.maps.l.a().g(true);
    }

    public void a(int i) {
        ((a) this.a).c(Integer.valueOf(i));
    }

    public int a() {
        return ((a) this.a).c() != null ? ((a) this.a).c().intValue() : -1;
    }

    public static CityCoverageRequest a(i iVar) {
        if (iVar != null) {
            return (CityCoverageRequest) b.a(iVar);
        }
        return null;
    }

    public static void a(am<CityCoverageRequest, i> amVar) {
        b = amVar;
    }

    static {
        ce.a(CityCoverageRequest.class);
    }
}
