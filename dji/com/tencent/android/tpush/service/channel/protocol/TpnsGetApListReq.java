package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsGetApListReq extends g {
    static NetworkInfo cache_netInfo;
    public NetworkInfo netInfo = null;

    public TpnsGetApListReq(NetworkInfo networkInfo) {
        this.netInfo = networkInfo;
    }

    public void writeTo(f fVar) {
        fVar.a(this.netInfo, 0);
    }

    public void readFrom(e eVar) {
        if (cache_netInfo == null) {
            cache_netInfo = new NetworkInfo();
        }
        this.netInfo = (NetworkInfo) eVar.b(cache_netInfo, 0, true);
    }
}
