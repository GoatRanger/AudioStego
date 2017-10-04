package com.nokia.maps.a;

import com.here.a.a.a.a.h;
import com.here.a.a.a.b;
import com.here.android.mpa.urbanmobility.CitySearchRequest;
import com.here.android.mpa.urbanmobility.CitySearchResult;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Locale;

public class l extends b<CitySearchResult, h, b> {
    private static am<CitySearchRequest, l> b;

    protected /* synthetic */ Object b(Object obj) {
        return a((h) obj);
    }

    public l(String str, String str2, String str3, String str4, RequestManager$ResponseListener<CitySearchResult> requestManager$ResponseListener) {
        super(48, new b(str, str2, str3, str4, MapsEngine.g().equals(Locale.CHINA.getISO3Country())), (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public void a(boolean z) {
        ((b) this.a).a(Boolean.valueOf(z));
    }

    protected com.here.a.a.a.l<h, b> b() {
        return com.here.a.a.a.l.f();
    }

    protected CitySearchResult a(h hVar) {
        com.nokia.maps.l.a().h(false);
        return m.a(new m(hVar));
    }

    protected void c() {
        com.nokia.maps.l.a().h(true);
    }

    public void a(int i) {
        ((b) this.a).a(Integer.valueOf(i));
    }

    public int a() {
        return ((b) this.a).c() != null ? ((b) this.a).c().intValue() : -1;
    }

    public static CitySearchRequest a(l lVar) {
        if (lVar != null) {
            return (CitySearchRequest) b.a(lVar);
        }
        return null;
    }

    public static void a(am<CitySearchRequest, l> amVar) {
        b = amVar;
    }

    static {
        ce.a(CitySearchRequest.class);
    }
}
