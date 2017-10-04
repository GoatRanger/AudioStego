package com.tencent.android.tpush.b;

import com.tencent.android.tpush.common.MessageKey;

public class c extends a {
    private int c = 0;
    private int d = 1;
    private int e = 1;
    private int f = 1;
    private int g = 0;
    private int h = 0;
    private String i = "";
    private int j = 1;
    private String k = "";
    private String l = "";
    private int m = 0;
    private int n = 0;
    private d o = new d();

    public c(String str) {
        super(str);
    }

    public int b() {
        return 1;
    }

    public int g() {
        return this.c;
    }

    public int h() {
        return this.d;
    }

    public int i() {
        return this.e;
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.g;
    }

    public d l() {
        return this.o;
    }

    public int m() {
        return this.h;
    }

    public int n() {
        return this.j;
    }

    public String o() {
        return this.k;
    }

    public String p() {
        return this.i;
    }

    public String q() {
        return this.l;
    }

    public int r() {
        return this.m;
    }

    public int s() {
        return this.n;
    }

    protected void c() {
        this.c = this.a.optInt(MessageKey.MSG_BUILDER_ID);
        this.d = this.a.optInt(MessageKey.MSG_RING, 1);
        this.k = this.a.optString(MessageKey.MSG_RING_RAW);
        this.i = this.a.optString(MessageKey.MSG_ICON_RES);
        this.l = this.a.optString(MessageKey.MSG_SMALL_ICON);
        this.j = this.a.optInt(MessageKey.MSG_LIGHTS, 1);
        this.e = this.a.optInt(MessageKey.MSG_VIBRATE, 1);
        this.h = this.a.optInt(MessageKey.MSG_ICON);
        this.m = this.a.optInt(MessageKey.MSG_ICON_TYPE, 0);
        this.g = this.a.optInt(MessageKey.MSG_NOTIFY_ID);
        this.n = this.a.optInt(MessageKey.MSG_STYLE_ID, 0);
        if (this.a.isNull(MessageKey.MSG_CLEARABLE)) {
            this.f = 1;
        } else {
            this.f = this.a.optInt(MessageKey.MSG_CLEARABLE);
        }
        if (!this.a.isNull("action")) {
            this.o.a(this.a.getString("action"));
        }
    }
}
