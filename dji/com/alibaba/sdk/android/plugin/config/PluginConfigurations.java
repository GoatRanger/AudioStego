package com.alibaba.sdk.android.plugin.config;

import com.alibaba.sdk.android.config.PropertyChangeListener;
import com.alibaba.sdk.android.registry.ServiceRegistration;

public interface PluginConfigurations {
    boolean getBooleanValue(String str, boolean z);

    double getDoubleValue(String str, double d);

    float getFloatValue(String str, float f);

    int getIntValue(String str, int i);

    long getLongValue(String str, long j);

    String getStringValue(String str);

    String getStringValue(String str, String str2);

    ServiceRegistration registerGlobalPropertyChangeListener(PropertyChangeListener propertyChangeListener);

    ServiceRegistration registerPropertyChangeListener(PropertyChangeListener propertyChangeListener);

    void unregisterPropertyChangeListener(ServiceRegistration serviceRegistration);
}
