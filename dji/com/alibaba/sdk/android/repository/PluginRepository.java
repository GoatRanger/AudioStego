package com.alibaba.sdk.android.repository;

import com.alibaba.sdk.android.plugin.PluginInfo;

public interface PluginRepository {
    PluginInfo getPluginInfo(String str);

    PluginInfo[] getPluginInfos();

    String[] getPluginNames();
}
