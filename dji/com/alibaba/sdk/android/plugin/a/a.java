package com.alibaba.sdk.android.plugin.a;

import com.alibaba.sdk.android.plugin.PluginContext;
import com.alibaba.sdk.android.plugin.PluginInfo;
import com.alibaba.sdk.android.plugin.config.PluginConfigurations;
import com.alibaba.sdk.android.plugin.config.PluginSystemConfigurations;

public final class a implements PluginContext {
    private PluginInfo a;
    private com.alibaba.sdk.android.plugin.config.a.a b;

    public a(PluginInfo pluginInfo) {
        this.a = pluginInfo;
        this.b = new com.alibaba.sdk.android.plugin.config.a.a(pluginInfo, (PluginSystemConfigurations) com.alibaba.sdk.android.b.a.e.a(PluginSystemConfigurations.class, null));
    }

    public final PluginConfigurations getPluginConfigurations() {
        return this.b;
    }

    public final PluginInfo getPluginInfo() {
        return this.a;
    }

    public final void a() {
        this.b.a();
    }
}
