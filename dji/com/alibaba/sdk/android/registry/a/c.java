package com.alibaba.sdk.android.registry.a;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.message.Message;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

final class c implements InvocationHandler {
    final /* synthetic */ Class a;
    final /* synthetic */ Map b;
    final /* synthetic */ b c;

    c(b bVar, Class cls, Map map) {
        this.c = bVar;
        this.a = cls;
        this.b = map;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Object a = this.c.a.a(this.a, this.b);
        if (a != null) {
            return method.invoke(a, objArr);
        }
        Object[] objArr2 = new Object[2];
        objArr2[0] = this.a.getName();
        objArr2[1] = this.b != null ? this.b.toString() : "";
        AliSDKLogger.log(SdkConstants.KERNEL_NAME, Message.create(17, objArr2));
        return null;
    }
}
