package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;
import java.util.ArrayList;

public final class TpnsClientReportReq extends g {
    static ArrayList cache_reportMsgs;
    public ArrayList reportMsgs = null;

    public TpnsClientReportReq(ArrayList arrayList) {
        this.reportMsgs = arrayList;
    }

    public void writeTo(f fVar) {
        if (this.reportMsgs != null) {
            fVar.a(this.reportMsgs, 1);
        }
    }

    public void readFrom(e eVar) {
        if (cache_reportMsgs == null) {
            cache_reportMsgs = new ArrayList();
            cache_reportMsgs.add(new TpnsClientReport());
        }
        this.reportMsgs = (ArrayList) eVar.a(cache_reportMsgs, 1, false);
    }
}
