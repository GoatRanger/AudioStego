package com.alibaba.sdk.android.plugin;

public interface PluginManager {
    String[] getPluginNames();

    PluginState getPluginState(String str) throws PluginNotFoundException;

    void startPlugin(String str) throws PluginLifecycleException, PluginNotFoundException;

    void stopPlugin(String str) throws PluginLifecycleException, PluginNotFoundException;

    void syncStartPlugin(String str) throws PluginLifecycleException, PluginNotFoundException;
}
