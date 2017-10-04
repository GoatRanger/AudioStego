package com.nokia.maps;

import android.content.Context;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RouteOptions.Type;
import com.here.posclient.analytics.PositioningCounters;
import com.here.posclient.analytics.RadiomapCounters;
import com.nokia.maps.RouteManagerImpl.a;
import com.nokia.maps.dd.c;

public interface p {
    void a();

    void a(int i);

    void a(int i, int i2, int i3);

    void a(long j);

    void a(Context context, boolean z);

    void a(TransportMode transportMode, Type type, boolean z, int i);

    void a(TransportMode transportMode, RouteImpl routeImpl, a aVar);

    void a(TransportMode transportMode, boolean z);

    void a(TransportMode transportMode, boolean z, long j, boolean z2, boolean z3, boolean z4);

    void a(PositioningCounters positioningCounters);

    void a(RadiomapCounters radiomapCounters);

    void a(c cVar, boolean z, boolean z2);

    void a(a aVar, boolean z, boolean z2);

    void a(String str);

    void a(String str, String str2);

    void a(boolean z);

    void a(boolean z, int i, int i2);

    void a(boolean z, String str);

    void a(boolean z, boolean z2);

    void a(boolean z, boolean z2, int i, boolean z3);

    void b();

    void b(TransportMode transportMode, boolean z);

    void b(String str);

    void b(boolean z);

    void b(boolean z, int i, int i2);

    void b(boolean z, boolean z2);

    void c();

    void c(TransportMode transportMode, boolean z);

    void c(String str);

    void c(boolean z);

    void c(boolean z, boolean z2);

    void d();

    void d(TransportMode transportMode, boolean z);

    void d(String str);

    void d(boolean z);

    void e();

    void e(TransportMode transportMode, boolean z);

    void e(String str);

    void e(boolean z);

    void f();

    void f(TransportMode transportMode, boolean z);

    void f(String str);

    void f(boolean z);

    void g();

    void g(String str);

    void g(boolean z);

    void h();

    void h(boolean z);

    void i();

    void i(boolean z);

    void j();

    void j(boolean z);

    void k(boolean z);

    void l(boolean z);
}
