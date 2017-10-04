package com.alibaba.sdk.android.registry.a;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.registry.ServiceRegistration;
import com.alibaba.sdk.android.registry.a;
import java.lang.reflect.Proxy;
import java.util.Map;

public final class b implements a {
    private a a;

    public b(a aVar) {
        this.a = aVar;
    }

    public final ServiceRegistration a(Class<?>[] clsArr, Object obj, Map<String, String> map) {
        return this.a.a(clsArr, obj, map);
    }

    public final <T> T a(Class<T> cls, Map<String, String> map) {
        T a = this.a.a(cls, map);
        if (a == null && map != null) {
            if ("true".equals((String) map.get(SdkConstants.ISV_SCOPE_FLAG))) {
                return cls.cast(Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{cls}, new c(this, cls, map)));
            }
        }
        return a;
    }

    public final <T> T[] b(Class<T> cls, Map<String, String> map) {
        return this.a.b(cls, map);
    }

    public final Object a(ServiceRegistration serviceRegistration) {
        return this.a.a(serviceRegistration);
    }
}
