package com.alibaba.sdk.android.plugin;

public class PluginNotFoundException extends Exception {
    private static final long serialVersionUID = -4638859050373683050L;
    private String pluginName;

    public PluginNotFoundException(String str, String str2, Throwable th) {
        super(str2, th);
        this.pluginName = str;
    }

    public PluginNotFoundException(String str, String str2) {
        super(str2);
        this.pluginName = str;
    }

    public String getPluginName() {
        return this.pluginName;
    }
}
