package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsUnregisterReq extends g {
    static UnregInfo cache_unregInfo;
    public short deviceType = (short) 0;
    public UnregInfo unregInfo = null;

    public TpnsUnregisterReq(UnregInfo unregInfo, short s) {
        this.unregInfo = unregInfo;
        this.deviceType = s;
    }

    public void writeTo(f fVar) {
        fVar.a(this.unregInfo, 0);
        fVar.a(this.deviceType, 1);
    }

    public void readFrom(e eVar) {
        if (cache_unregInfo == null) {
            cache_unregInfo = new UnregInfo();
        }
        this.unregInfo = (UnregInfo) eVar.b(cache_unregInfo, 0, true);
        this.deviceType = eVar.a(this.deviceType, 1, false);
    }
}
