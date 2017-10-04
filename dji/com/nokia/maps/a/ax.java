package com.nokia.maps.a;

import com.here.a.a.a.j.a;
import com.here.a.a.a.r;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.UMRouter.SubsequentRouteType;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;

public class ax extends ao<r> {
    public ax(String str, String str2, String str3, RoutePlan routePlan, h hVar, SubsequentRouteType subsequentRouteType, int i, RequestManager$ResponseListener<h> requestManager$ResponseListener) {
        super(new r(str, str2, str3, hVar.e(), hVar.f()), routePlan, requestManager$ResponseListener);
        ((r) this.a).a(subsequentRouteType == SubsequentRouteType.EARLIER ? a.BACKWARD : a.FORWARD);
        ((r) this.a).e(Integer.valueOf(i));
    }

    public void a(int i) {
        ((r) this.a).e(Integer.valueOf(i));
    }

    public int a() {
        return ((r) this.a).s() != null ? ((r) this.a).s().intValue() : -1;
    }
}
