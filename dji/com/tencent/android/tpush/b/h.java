package com.tencent.android.tpush.b;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.b.a;
import dji.pilot.usercenter.protocol.d;

public class h {
    private long a = -1;
    private long b = -1;
    private long c = -1;
    private String d = "";
    private long e = -1;
    private long f = -1;
    private Context g = null;
    private Intent h = null;
    private a i = null;

    private h(Context context, Intent intent) {
        this.g = context;
        this.h = intent;
    }

    public static h a(Context context, Intent intent) {
        h hVar = new h(context, intent);
        String decrypt = Rijndael.decrypt(intent.getStringExtra("content"));
        hVar.d = decrypt;
        hVar.a = intent.getLongExtra(MessageKey.MSG_ID, -1);
        hVar.b = intent.getLongExtra("accId", -1);
        hVar.c = intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, -1);
        hVar.e = intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, -1);
        hVar.f = intent.getLongExtra("type", -1);
        a aVar = null;
        switch ((int) hVar.f) {
            case 1:
                aVar = new c(decrypt);
                break;
            case 2:
                aVar = new i(decrypt);
                break;
            case 3:
                a.a().a(context, context.getPackageName(), decrypt);
                XGPushManager.msgAck(context, hVar);
                break;
            default:
                com.tencent.android.tpush.a.a.h(Constants.LogTag, "error type for message, drop it, type:" + hVar.f + ",intent:" + intent);
                XGPushManager.msgAck(context, hVar);
                break;
        }
        if (aVar != null) {
            hVar.i = aVar;
            hVar.i.a();
        }
        return hVar;
    }

    public void a() {
        if (this.i.b() == 1) {
            b.b(this.g, this);
        }
    }

    public long b() {
        return this.a;
    }

    public long c() {
        return this.b;
    }

    public long d() {
        return this.c;
    }

    public long e() {
        return this.e;
    }

    public String f() {
        return this.d;
    }

    public a g() {
        return this.i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PushMessageManager [msgId=").append(this.a).append(", accessId=").append(this.b).append(", busiMsgId=").append(this.c).append(", content=").append(this.d).append(", timestamps=").append(this.e).append(", type=").append(this.f).append(", intent=").append(this.h).append(", messageHolder=").append(this.i).append(d.H);
        return stringBuilder.toString();
    }
}
