package com.tencent.android.tpush.service;

import com.f.a.a.g;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.service.b.j;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.p;
import com.tencent.android.tpush.service.channel.protocol.TpnsUnregisterReq;

class r implements p {
    final /* synthetic */ String a;
    final /* synthetic */ o b;

    r(o oVar, String str) {
        this.b = oVar;
        this.a = str;
    }

    public void a(g gVar, int i, g gVar2, a aVar) {
        if (XGPushConfig.enableDebug) {
            com.tencent.android.tpush.a.a.c("PushServiceNetworkHandler", "Report uninstall with pkgName = " + this.a + ", reponseCode = " + i);
        }
        if (i == 0) {
            CacheManager.UninstallInfoSuccessByPkgName(this.a);
            j.a().a(l.e(), this.a);
            com.tencent.android.tpush.service.b.a.a().c(l.e(), this.a);
            return;
        }
        com.tencent.android.tpush.a.a.h("PushServiceNetworkHandler", " uninstall report fail responseCode=" + i);
        this.b.a(i, "服务器处理失败，返回错误", this.a, (TpnsUnregisterReq) gVar, aVar);
    }

    public void a(g gVar, ChannelException channelException, a aVar) {
        this.b.a(channelException.errorCode, channelException.getMessage(), this.a, (TpnsUnregisterReq) gVar, aVar);
    }

    public void a(g gVar, a aVar) {
    }
}
