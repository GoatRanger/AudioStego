package com.alibaba.sdk.android.repository.a;

import com.alibaba.sdk.android.plugin.PluginInfo;

final class d implements Runnable {
    final /* synthetic */ PluginInfo[] a;
    final /* synthetic */ c b;

    d(c cVar, PluginInfo[] pluginInfoArr) {
        this.b = cVar;
        this.a = pluginInfoArr;
    }

    public final void run() {
        this.b.b.a(this.a);
    }
}
