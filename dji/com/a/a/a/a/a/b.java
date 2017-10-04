package com.a.a.a.a.a;

import com.a.a.i;
import com.a.a.p;
import com.sina.weibo.sdk.statistic.LogBuilder;
import dji.pilot.usercenter.protocol.c;
import java.util.Date;
import java.util.UUID;

public abstract class b extends p {

    public enum a {
        browser,
        mobile,
        server
    }

    public enum b {
        alias,
        group,
        identify,
        screen,
        e;

        public static b[] a() {
            return (b[]) f.clone();
        }
    }

    public /* synthetic */ p b(String str, Object obj) {
        return a(str, obj);
    }

    public b(b bVar, com.a.a.b bVar2, i iVar) {
        com.a.a.b a = bVar2.a();
        c("messageId", UUID.randomUUID().toString());
        c("type", bVar);
        c(LogBuilder.KEY_CHANNEL, a.mobile);
        c("context", a);
        c("anonymousId", a.b().d());
        CharSequence c = a.b().c();
        if (!com.a.a.a.b.a(c)) {
            c("userId", c);
        }
        c(c.ad, com.a.a.a.b.a(new Date()));
        c("integrations", iVar.a());
    }

    public b b() {
        return (b) a(b.class, "type");
    }

    public String c() {
        return c("userId");
    }

    public b a(String str, Object obj) {
        super.b(str, obj);
        return this;
    }
}
