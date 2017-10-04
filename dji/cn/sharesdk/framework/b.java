package cn.sharesdk.framework;

import cn.sharesdk.framework.authorize.AuthorizeHelper;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.c;
import cn.sharesdk.framework.authorize.d;
import cn.sharesdk.framework.authorize.e;

public abstract class b implements AuthorizeHelper {
    protected Platform a;
    private AuthorizeListener b;
    private SSOListener c;

    public b(Platform platform) {
        this.a = platform;
    }

    public Platform getPlatform() {
        return this.a;
    }

    public d getSSOProcessor(c cVar) {
        return null;
    }

    public int c() {
        return this.a.getPlatformId();
    }

    protected void b(AuthorizeListener authorizeListener) {
        this.b = authorizeListener;
        e eVar = new e();
        eVar.a(this.b);
        eVar.a(this);
    }

    public AuthorizeListener getAuthorizeListener() {
        return this.b;
    }

    protected void a(SSOListener sSOListener) {
        this.c = sSOListener;
        c cVar = new c();
        cVar.a(sSOListener);
        cVar.a(this);
    }

    public SSOListener getSSOListener() {
        return this.c;
    }
}
