package com.tencent.android.tpush.service.channel.protocol;

import com.f.a.a.e;
import com.f.a.a.f;
import com.f.a.a.g;

public final class TpnsRegisterReq extends g {
    static DeviceInfo cache_deviceInfo;
    static MutableInfo cache_mutableInfo;
    public long accessId = 0;
    public String accessKey = "";
    public String account = "";
    public String appCert = "";
    public String appVersion = "";
    public String deviceId = "";
    public DeviceInfo deviceInfo = null;
    public short deviceType = (short) 0;
    public byte keyEncrypted = (byte) 0;
    public MutableInfo mutableInfo = null;
    public String reserved = "";
    public String ticket = "";
    public short ticketType = (short) 0;
    public String token = "";
    public short updateAutoTag = (short) 0;
    public short version = (short) 0;

    public TpnsRegisterReq(long j, String str, String str2, String str3, String str4, String str5, short s, short s2, DeviceInfo deviceInfo, String str6, short s3, byte b, MutableInfo mutableInfo, short s4, String str7, String str8) {
        this.accessId = j;
        this.accessKey = str;
        this.deviceId = str2;
        this.appCert = str3;
        this.account = str4;
        this.ticket = str5;
        this.ticketType = s;
        this.deviceType = s2;
        this.deviceInfo = deviceInfo;
        this.token = str6;
        this.version = s3;
        this.keyEncrypted = b;
        this.mutableInfo = mutableInfo;
        this.updateAutoTag = s4;
        this.appVersion = str7;
        this.reserved = str8;
    }

    public void writeTo(f fVar) {
        fVar.a(this.accessId, 0);
        fVar.c(this.accessKey, 1);
        fVar.c(this.deviceId, 2);
        fVar.c(this.appCert, 3);
        if (this.account != null) {
            fVar.c(this.account, 4);
        }
        if (this.ticket != null) {
            fVar.c(this.ticket, 5);
        }
        fVar.a(this.ticketType, 6);
        fVar.a(this.deviceType, 7);
        if (this.deviceInfo != null) {
            fVar.a(this.deviceInfo, 8);
        }
        if (this.token != null) {
            fVar.c(this.token, 9);
        }
        fVar.a(this.version, 10);
        fVar.b(this.keyEncrypted, 11);
        if (this.mutableInfo != null) {
            fVar.a(this.mutableInfo, 12);
        }
        fVar.a(this.updateAutoTag, 13);
        if (this.appVersion != null) {
            fVar.c(this.appVersion, 14);
        }
        if (this.reserved != null) {
            fVar.c(this.reserved, 15);
        }
    }

    public void readFrom(e eVar) {
        this.accessId = eVar.a(this.accessId, 0, true);
        this.accessKey = eVar.a(1, true);
        this.deviceId = eVar.a(2, true);
        this.appCert = eVar.a(3, true);
        this.account = eVar.a(4, false);
        this.ticket = eVar.a(5, false);
        this.ticketType = eVar.a(this.ticketType, 6, false);
        this.deviceType = eVar.a(this.deviceType, 7, false);
        if (cache_deviceInfo == null) {
            cache_deviceInfo = new DeviceInfo();
        }
        this.deviceInfo = (DeviceInfo) eVar.b(cache_deviceInfo, 8, false);
        this.token = eVar.a(9, false);
        this.version = eVar.a(this.version, 10, false);
        this.keyEncrypted = eVar.a(this.keyEncrypted, 11, false);
        if (cache_mutableInfo == null) {
            cache_mutableInfo = new MutableInfo();
        }
        this.mutableInfo = (MutableInfo) eVar.b(cache_mutableInfo, 12, false);
        this.updateAutoTag = eVar.a(this.updateAutoTag, 13, false);
        this.appVersion = eVar.a(14, false);
        this.reserved = eVar.a(15, false);
    }
}
