package com.alipay.sdk.auth;

final class f implements Runnable {
    final /* synthetic */ AuthActivity a;

    f(AuthActivity authActivity) {
        this.a = authActivity;
    }

    public final void run() {
        AuthActivity.g(this.a);
    }
}
