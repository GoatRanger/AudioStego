package com.alibaba.sdk.android.security;

public interface AccessController {
    boolean checkPluginLoadPermission(String str);

    boolean checkWebPageAccessPermission(String str);

    String getConfigProperty(String str);
}
