package com.alibaba.sdk.android.a.a;

import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.app.AppContext;
import com.alibaba.sdk.android.kernel.message.KernelMessageConstants;
import com.alibaba.sdk.android.message.Message;
import com.alibaba.sdk.android.message.MessageUtils;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.taobao.tae.sdk.log.SdkCoreLog;
import dji.pilot.usercenter.protocol.d;
import java.lang.reflect.Method;
import java.util.Map;

public final class a {
    public static ResultCode a(String str, String str2, String str3, Map<String, String> map) {
        Object obj;
        if (ConfigManager.DEBUG) {
            SdkCoreLog.startTimeRecord(str2);
        }
        if (str2 != null) {
            try {
                Class cls = Class.forName(str2);
                if (com.alibaba.sdk.android.b.a.e.a(cls, null) != null) {
                    return ResultCode.SUCCESS;
                }
                Class cls2 = cls;
            } catch (ClassNotFoundException e) {
                AliSDKLogger.i("BeanLoader", str2 + d.t + str3 + " is not available, the error message is " + e.getMessage());
                return ResultCode.create(16, e.getMessage());
            } catch (Throwable e2) {
                Throwable th = e2;
                Object[] objArr = new Object[1];
                objArr[0] = th.getTargetException() != null ? th.getTargetException().getMessage() : th.getMessage();
                Message createMessage = MessageUtils.createMessage(KernelMessageConstants.GENERIC_SYSTEM_ERROR, objArr);
                AliSDKLogger.log("BeanLoader", createMessage, th);
                if (ConfigManager.DEBUG) {
                    SdkCoreLog.d(str2, SdkCoreLog.content("BeanLoader", SdkCoreLog.getTimeUsed(str2), new String[]{SdkCoreLog.FAILURE}));
                }
                return ResultCode.create(createMessage.code, createMessage.message);
            } catch (Throwable e22) {
                Message createMessage2 = MessageUtils.createMessage(KernelMessageConstants.GENERIC_SYSTEM_ERROR, e22.getMessage());
                AliSDKLogger.log("BeanLoader", createMessage2, e22);
                if (ConfigManager.DEBUG) {
                    SdkCoreLog.d(str2, SdkCoreLog.content("BeanLoader", SdkCoreLog.getTimeUsed(str2), new String[]{SdkCoreLog.FAILURE}));
                }
                return ResultCode.create(createMessage2.code, createMessage2.message);
            }
        }
        cls2 = null;
        Class cls3 = Class.forName(str3);
        try {
            obj = cls3.getField("INSTANCE").get(null);
        } catch (NoSuchFieldException e3) {
            obj = cls3.getConstructor(new Class[0]).newInstance(new Object[0]);
        }
        AppContext b = com.alibaba.sdk.android.app.a.a.b(str);
        try {
            ResultCode resultCode;
            Method method = cls3.getMethod("init", new Class[]{AppContext.class});
            Object invoke = method.invoke(obj, new Object[]{b});
            if (method.getReturnType() == Void.TYPE) {
                resultCode = ResultCode.SUCCESS;
            } else {
                resultCode = (ResultCode) invoke;
            }
            if (!resultCode.isSuccess()) {
                return resultCode;
            }
        } catch (NoSuchMethodException e4) {
        }
        if (cls2 != null) {
            b.registerService(new Class[]{cls2}, obj, map);
        }
        if (ConfigManager.DEBUG) {
            SdkCoreLog.d(str2, SdkCoreLog.content("BeanLoader", SdkCoreLog.getTimeUsed(str2), new String[]{"success"}));
        }
        return ResultCode.SUCCESS;
    }
}
