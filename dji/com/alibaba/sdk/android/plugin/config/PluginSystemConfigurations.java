package com.alibaba.sdk.android.plugin.config;

public interface PluginSystemConfigurations {
    void addStartRequiredPlugin(String str);

    String getGlobalProperty(String str);

    String getPluginProperty(String str, String str2);

    String[] getStartedPluginNames();

    void removeStartRequiredPlugin(String str);

    void setDynamicProperties(String str);

    void setGlobalProperty(String str, String str2);

    void setPluginProperty(String str, String str2, String str3);

    void unsetGlobalProperty(String str);

    void unsetPluginProperty(String str, String str2);
}
