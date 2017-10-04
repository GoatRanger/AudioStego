package com.alibaba.sdk.android.plugin;

import com.alibaba.sdk.android.app.AppContext;

public interface PluginSyncLifecycleAdapter {
    void syncStart(AppContext appContext, PluginContext pluginContext) throws PluginLifecycleException;
}
