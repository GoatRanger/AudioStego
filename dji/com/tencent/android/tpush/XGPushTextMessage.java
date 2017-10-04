package com.tencent.android.tpush;

import dji.pilot.usercenter.protocol.d;

public class XGPushTextMessage {
    String a = "";
    String b = "";
    String c = "";

    public String getTitle() {
        return this.a;
    }

    public String getContent() {
        return this.b;
    }

    public String getCustomContent() {
        return this.c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("XGPushShowedResult [title=").append(this.a).append(", content=").append(this.b).append(", customContent=").append(this.c).append(d.H);
        return stringBuilder.toString();
    }
}
