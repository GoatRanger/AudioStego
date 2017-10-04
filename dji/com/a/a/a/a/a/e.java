package com.a.a.a.a.a;

import com.a.a.a.b;
import com.sina.weibo.sdk.component.WidgetRequestParam;

public class e extends b {
    private String a;

    public String a() {
        return c(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY);
    }

    public String d() {
        return c("name");
    }

    public String e() {
        if (b.a(this.a)) {
            this.a = b.a(d()) ? a() : d();
        }
        return this.a;
    }

    public String toString() {
        return "ScreenPayload{\"" + e() + '}';
    }
}
