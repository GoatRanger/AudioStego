package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;
import java.util.ArrayList;

public final class TpnsPushVerifyReq extends g {
    static ArrayList cache_msgReportList;
    public ArrayList msgReportList = null;

    public TpnsPushVerifyReq(ArrayList arrayList) {
        this.msgReportList = arrayList;
    }

    public void writeTo(f fVar) {
        fVar.a(this.msgReportList, 1);
    }

    public void readFrom(e eVar) {
        if (cache_msgReportList == null) {
            cache_msgReportList = new ArrayList();
            cache_msgReportList.add(new TpnsPushClientReport());
        }
        this.msgReportList = (ArrayList) eVar.a(cache_msgReportList, 1, true);
    }
}
