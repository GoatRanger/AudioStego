package com.alibaba.sdk.android.plugin.config.a;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.config.PropertyChangeListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class c implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ b b;

    c(b bVar, List list) {
        this.b = bVar;
        this.a = list;
    }

    public final void run() {
        for (a aVar : this.a) {
            Map singletonMap;
            if ("$global$".equals(aVar.a)) {
                singletonMap = Collections.singletonMap(SdkConstants.PROPERTY_SCOPE, SdkConstants.PROPERTY_GLOBAL_SCOPE);
            } else {
                singletonMap = new HashMap();
                singletonMap.put(SdkConstants.PROPERTY_SCOPE, SdkConstants.PROPERTY_PLUGIN_SCOPE);
                singletonMap.put(SdkConstants.PLUGIN_VENDOR_KEY, aVar.a);
            }
            PropertyChangeListener[] propertyChangeListenerArr = (PropertyChangeListener[]) a.e.b(PropertyChangeListener.class, singletonMap);
            if (propertyChangeListenerArr != null && propertyChangeListenerArr.length > 0) {
                for (PropertyChangeListener propertyChanged : propertyChangeListenerArr) {
                    try {
                        propertyChanged.propertyChanged(aVar.b, aVar.d, aVar.c);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
