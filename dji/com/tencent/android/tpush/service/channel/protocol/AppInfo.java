package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class AppInfo extends g {
    public long accessId = 0;
    public String accessKey = "";
    public String appCert = "";
    public byte keyEncrypted = (byte) 0;

    public AppInfo(long j, String str, String str2, byte b) {
        this.accessId = j;
        this.accessKey = str;
        this.appCert = str2;
        this.keyEncrypted = b;
    }

    public void writeTo(f fVar) {
        fVar.a(this.accessId, 0);
        fVar.c(this.accessKey, 1);
        fVar.c(this.appCert, 2);
        fVar.b(this.keyEncrypted, 3);
    }

    public void readFrom(e eVar) {
        this.accessId = eVar.a(this.accessId, 0, true);
        this.accessKey = eVar.a(1, true);
        this.appCert = eVar.a(2, true);
        this.keyEncrypted = eVar.a(this.keyEncrypted, 3, false);
    }
}
