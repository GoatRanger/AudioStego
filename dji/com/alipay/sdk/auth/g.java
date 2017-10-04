package com.alipay.sdk.auth;

import android.webkit.SslErrorHandler;
import com.alipay.sdk.k.d;

final class g implements Runnable {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ b b;

    g(b bVar, SslErrorHandler sslErrorHandler) {
        this.b = bVar;
        this.a = sslErrorHandler;
    }

    public final void run() {
        d.a(this.b.a, "安全警告", "由于您的设备缺少根证书，将无法校验该访问站点的安全性，可能存在风险，请选择是否继续？", "继续", new h(this), "退出", new i(this));
    }
}
