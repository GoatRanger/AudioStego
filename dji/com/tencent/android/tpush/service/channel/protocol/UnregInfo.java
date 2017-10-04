package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class UnregInfo extends g {
    static AppInfo cache_appInfo;
    public AppInfo appInfo = null;
    public byte isUninstall = (byte) 0;
    public long timestamp = 0;

    public UnregInfo(AppInfo appInfo, byte b, long j) {
        this.appInfo = appInfo;
        this.isUninstall = b;
        this.timestamp = j;
    }

    public void writeTo(f fVar) {
        fVar.a(this.appInfo, 0);
        fVar.b(this.isUninstall, 1);
        fVar.a(this.timestamp, 2);
    }

    public void readFrom(e eVar) {
        if (cache_appInfo == null) {
            cache_appInfo = new AppInfo();
        }
        this.appInfo = (AppInfo) eVar.b(cache_appInfo, 0, true);
        this.isUninstall = eVar.a(this.isUninstall, 1, true);
        this.timestamp = eVar.a(this.timestamp, 2, false);
    }
}
