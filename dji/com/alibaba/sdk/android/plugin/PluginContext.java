package com.alibaba.sdk.android.plugin;

import com.alibaba.sdk.android.plugin.config.PluginConfigurations;

public interface PluginContext {
    PluginConfigurations getPluginConfigurations();

    PluginInfo getPluginInfo();
}
