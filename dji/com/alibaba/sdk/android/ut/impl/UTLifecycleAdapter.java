package com.alibaba.sdk.android.ut.impl;

import com.alibaba.sdk.android.app.AppContext;
import com.alibaba.sdk.android.plugin.PluginContext;
import com.alibaba.sdk.android.plugin.PluginLifecycleAdapter;

public class UTLifecycleAdapter implements PluginLifecycleAdapter {
    public void start(AppContext appContext, PluginContext pluginContext) {
        b.a = appContext;
    }

    public void stop(AppContext appContext, PluginContext pluginContext) {
    }
}
