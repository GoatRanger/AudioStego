package com.alibaba.sdk.android.plugin;

import com.alibaba.sdk.android.app.AppContext;

public interface PluginLifecycleAdapter {
    void start(AppContext appContext, PluginContext pluginContext) throws PluginLifecycleException;

    void stop(AppContext appContext, PluginContext pluginContext) throws PluginLifecycleException;
}
