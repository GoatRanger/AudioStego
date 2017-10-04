package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsGetApListRsp extends g {
    static ApList cache_apList;
    public ApList apList = null;

    public TpnsGetApListRsp(ApList apList) {
        this.apList = apList;
    }

    public void writeTo(f fVar) {
        fVar.a(this.apList, 0);
    }

    public void readFrom(e eVar) {
        if (cache_apList == null) {
            cache_apList = new ApList();
        }
        this.apList = (ApList) eVar.b(cache_apList, 0, true);
    }
}
