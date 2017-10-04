package com.tencent.android.tpush.b;

import com.tencent.android.tpush.common.MessageKey;
import dji.pilot.usercenter.protocol.d;
import org.json.JSONObject;

public abstract class a {
    protected JSONObject a = null;
    protected String b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private String f = null;

    public abstract int b();

    protected abstract void c();

    protected a(String str) {
        this.b = str;
    }

    public void a() {
        this.a = new JSONObject(this.b);
        if (!this.a.isNull("title")) {
            this.c = this.a.getString("title");
        }
        if (!this.a.isNull("content")) {
            this.d = this.a.getString("content");
        }
        if (!this.a.isNull("custom_content")) {
            String string = this.a.getString("custom_content");
            if (!(string == null || string.trim().equals("{}"))) {
                this.e = string;
            }
        }
        if (!this.a.isNull(MessageKey.MSG_ACCEPT_TIME)) {
            this.f = this.a.getString(MessageKey.MSG_ACCEPT_TIME);
        }
        c();
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BaseMessageHolder [msgJson=").append(this.a).append(", msgJsonStr=").append(this.b).append(", title=").append(this.c).append(", content=").append(this.d).append(", customContent=").append(this.e).append(", acceptTime=").append(this.f).append(d.H);
        return stringBuilder.toString();
    }
}
