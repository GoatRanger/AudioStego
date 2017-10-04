package com.alibaba.sdk.android.plugin.config.a;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.config.PropertyChangeListener;
import com.alibaba.sdk.android.plugin.PluginInfo;
import com.alibaba.sdk.android.plugin.config.PluginConfigurations;
import com.alibaba.sdk.android.plugin.config.PluginSystemConfigurations;
import com.alibaba.sdk.android.registry.ServiceRegistration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class a implements PluginConfigurations {
    private String a;
    private PluginSystemConfigurations b;
    private List<ServiceRegistration> c = Collections.synchronizedList(new ArrayList());

    public a(PluginInfo pluginInfo, PluginSystemConfigurations pluginSystemConfigurations) {
        this.a = pluginInfo.name;
        this.b = pluginSystemConfigurations;
    }

    public final String getStringValue(String str) {
        return a(this.a, str);
    }

    public final String getStringValue(String str, String str2) {
        String a = a(this.a, str);
        return a == null ? str2 : a;
    }

    public final int getIntValue(String str, int i) {
        String a = a(this.a, str);
        return a == null ? i : Integer.parseInt(a);
    }

    public final double getDoubleValue(String str, double d) {
        String a = a(this.a, str);
        return a == null ? d : Double.parseDouble(a);
    }

    public final float getFloatValue(String str, float f) {
        String a = a(this.a, str);
        return a == null ? f : Float.parseFloat(a);
    }

    public final long getLongValue(String str, long j) {
        String a = a(this.a, str);
        return a == null ? j : Long.parseLong(a);
    }

    public final boolean getBooleanValue(String str, boolean z) {
        String a = a(this.a, str);
        return a == null ? z : Boolean.parseBoolean(a);
    }

    public final ServiceRegistration registerPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        Map hashMap = new HashMap();
        hashMap.put(SdkConstants.PLUGIN_VENDOR_KEY, this.a);
        hashMap.put(SdkConstants.PROPERTY_SCOPE, SdkConstants.PROPERTY_PLUGIN_SCOPE);
        ServiceRegistration a = com.alibaba.sdk.android.b.a.e.a(new Class[]{PropertyChangeListener.class}, propertyChangeListener, hashMap);
        this.c.add(a);
        return a;
    }

    public final ServiceRegistration registerGlobalPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        Map hashMap = new HashMap();
        hashMap.put(SdkConstants.PLUGIN_VENDOR_KEY, this.a);
        hashMap.put(SdkConstants.PROPERTY_SCOPE, SdkConstants.PROPERTY_GLOBAL_SCOPE);
        ServiceRegistration a = com.alibaba.sdk.android.b.a.e.a(new Class[]{PropertyChangeListener.class}, propertyChangeListener, hashMap);
        this.c.add(a);
        return a;
    }

    public final void unregisterPropertyChangeListener(ServiceRegistration serviceRegistration) {
        serviceRegistration.unregister();
    }

    public final void a() {
        for (ServiceRegistration unregister : this.c) {
            unregister.unregister();
        }
        this.c.clear();
    }

    private String a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        String pluginProperty = this.b.getPluginProperty(str, str2);
        if (pluginProperty == null) {
            return this.b.getGlobalProperty(str2);
        }
        return pluginProperty;
    }
}
