package com.alibaba.sdk.android.task;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.AlibabaSDKException;
import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.callback.InitResultCallback;
import com.alibaba.sdk.android.device.DeviceInfo;
import com.alibaba.sdk.android.executor.ExecutorService;
import com.alibaba.sdk.android.kernel.message.KernelMessageConstants;
import com.alibaba.sdk.android.message.Message;
import com.alibaba.sdk.android.plugin.b;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.trace.TraceLoggerManager;
import com.alibaba.sdk.android.trace.TraceLoggerService;
import com.alibaba.sdk.android.ut.UTConstants;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.alibaba.sdk.android.util.CommonUtils;
import com.alibaba.sdk.android.util.ReflectionUtils;
import com.alibaba.sdk.android.util.TraceHelper;
import com.taobao.tae.sdk.log.SdkCoreLog;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public final class a implements Runnable {
    private InitResultCallback a;
    private Integer b;
    private boolean c;
    private CountDownLatch d = new CountDownLatch(1);

    static /* synthetic */ void a(a aVar, boolean z, int i, String str) {
        InitResultCallback[] initResultCallbackArr = (InitResultCallback[]) com.alibaba.sdk.android.b.a.e.b(InitResultCallback.class, null);
        if (initResultCallbackArr != null) {
            com.alibaba.sdk.android.b.a.f.postTask(new c(aVar, initResultCallbackArr, z, i, str));
        }
    }

    static /* synthetic */ void a(boolean z, long j, String str) {
        String str2 = z ? UTConstants.TYPE_INIT_SUCCESS : UTConstants.TYPE_INIT_FAILED;
        Map hashMap = new HashMap();
        hashMap.put("type", str2);
        if (str != null) {
            hashMap.put("msg", str);
        }
        UserTrackerService userTrackerService = (UserTrackerService) com.alibaba.sdk.android.b.a.e.a(UserTrackerService.class, null);
        if (userTrackerService != null) {
            userTrackerService.sendCustomHit(UTConstants.E_SDK_INIT_RESULT, j, str2, hashMap);
        }
    }

    public a(InitResultCallback initResultCallback, Integer num) {
        boolean z;
        this.a = new b(this, TraceLoggerManager.INSTANCE.action(3, "init_sdk", "initTask").begin(), initResultCallback);
        this.b = num;
        if (com.alibaba.sdk.android.b.a.a != null) {
            String currentProcessName = CommonUtils.getCurrentProcessName();
            if (currentProcessName != null) {
                String packageName = com.alibaba.sdk.android.b.a.a.getPackageName();
                if (!currentProcessName.equals(packageName)) {
                    if ("true".equals(AlibabaSDK.getProperty(SdkConstants.KERNEL_NAME, "disableMultiProcessPluginSupport"))) {
                        z = true;
                    } else {
                        String property = AlibabaSDK.getProperty("hotpatch", "processName");
                        if (TextUtils.isEmpty(property)) {
                            property = "hotpatch";
                        }
                        if (currentProcessName.equals(packageName + ":" + property)) {
                            z = true;
                        }
                    }
                    this.c = z;
                }
            }
        }
        z = false;
        this.c = z;
    }

    public final boolean a() {
        DeviceInfo.init(com.alibaba.sdk.android.b.a.a);
        if (com.alibaba.sdk.android.b.a.c) {
            return true;
        }
        try {
            if (c()) {
                com.alibaba.sdk.android.b.a.c = true;
                return true;
            }
        } catch (Throwable th) {
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, "fail to sync start", th);
            a(th);
        }
        this.d.countDown();
        return false;
    }

    private boolean c() {
        try {
            Class.forName(AsyncTask.class.getName());
        } catch (Exception e) {
        }
        com.alibaba.sdk.android.b.a.b();
        ConfigManager.getInstance().init(this.b.intValue());
        TraceLoggerManager.INSTANCE.init(CommonUtils.isDebuggable(), ConfigManager.DEBUG);
        if (ConfigManager.DEBUG) {
            SdkCoreLog.startTimeRecord("securityGuardInit");
        }
        ResultCode a = com.alibaba.sdk.android.a.a.a.a("security", "com.alibaba.sdk.android.security.SecurityGuardService", "com.alibaba.sdk.android.security.impl.SecurityGuardWrapper", null);
        if (a.isSuccess() || a.code == 16) {
            String androidManifestMetadata;
            UserTrackerService userTrackerService;
            if (AlibabaSDK.getGlobalProperty(SdkConstants.APP_KEY) == null) {
                androidManifestMetadata = CommonUtils.getAndroidManifestMetadata(com.alibaba.sdk.android.b.a.a, SdkConstants.APP_KEY);
                if (androidManifestMetadata != null) {
                    AlibabaSDK.setGlobalProperty(SdkConstants.APP_KEY, androidManifestMetadata);
                }
            }
            if (AlibabaSDK.getGlobalProperty(SdkConstants.APP_SECRET) == null) {
                androidManifestMetadata = CommonUtils.getAndroidManifestMetadata(com.alibaba.sdk.android.b.a.a, SdkConstants.APP_SECRET);
                if (androidManifestMetadata != null) {
                    AlibabaSDK.setGlobalProperty(SdkConstants.APP_SECRET, androidManifestMetadata);
                }
            }
            if (com.alibaba.sdk.android.a.a.a.a("hotpatch", "com.alibaba.sdk.android.hotpatch.HotPatchManager", "com.alibaba.sdk.android.hotpatch.impl.HotPatchManagerImpl", null).isSuccess()) {
                a("1");
            }
            if (com.alibaba.sdk.android.a.a.a.a("ut", UserTrackerService.class.getName(), "com.alibaba.sdk.android.ut.impl.AlibabaUserTrackerService", Collections.singletonMap(SdkConstants.ISV_SCOPE_FLAG, "true")).isSuccess()) {
                userTrackerService = (UserTrackerService) com.alibaba.sdk.android.b.a.e.a(UserTrackerService.class, null);
            } else {
                AliSDKLogger.w(SdkConstants.KERNEL_NAME, "Fail to initialize the UT user tracker service, will fallback to the default one");
                userTrackerService = (UserTrackerService) UserTrackerService.class.cast(Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{UserTrackerService.class}, new d(this)));
                com.alibaba.sdk.android.b.a.e.a(new Class[]{UserTrackerService.class}, userTrackerService, Collections.singletonMap(SdkConstants.ISV_SCOPE_FLAG, "true"));
            }
            String globalProperty = AlibabaSDK.getGlobalProperty("appVersion");
            if (globalProperty != null) {
                userTrackerService.updateUserTrackerProperties(Collections.singletonMap(UTConstants.APP_VERSION, globalProperty));
            }
            if (ConfigManager.DEBUG) {
                SdkCoreLog.startTimeRecord("traceHelperInit");
            }
            TraceHelper.init(com.alibaba.sdk.android.b.a.a, AlibabaSDK.getGlobalProperty(SdkConstants.APP_KEY), null, AlibabaSDK.getVersion().toString());
            if (ConfigManager.DEBUG) {
                SdkCoreLog.d("traceHelperInit", SdkCoreLog.content(SdkConstants.KERNEL_NAME, SdkCoreLog.getTimeUsed("traceHelperInit"), new String[]{"success"}));
            }
            com.alibaba.sdk.android.registry.a aVar = com.alibaba.sdk.android.b.a.e;
            ResultCode a2 = b.a.a();
            if (a2.isSuccess()) {
                boolean z;
                Map singletonMap = Collections.singletonMap(SdkConstants.PLUGIN_VENDOR_KEY, SdkConstants.KERNEL_NAME);
                TraceLoggerManager.INSTANCE.setUserTrackerService(userTrackerService);
                TraceLoggerManager.INSTANCE.init(CommonUtils.isDebuggable(), ConfigManager.DEBUG);
                aVar.a(new Class[]{TraceLoggerService.class}, TraceLoggerManager.INSTANCE, singletonMap);
                aVar.a(new Class[]{ExecutorService.class, java.util.concurrent.ExecutorService.class}, com.alibaba.sdk.android.b.a.f, singletonMap);
                if (ConfigManager.DEBUG) {
                    SdkCoreLog.startTimeRecord("syncRun");
                }
                if (this.c) {
                    z = true;
                } else {
                    b bVar = b.a;
                    z = b.a(this.a);
                    if (!z) {
                        return false;
                    }
                }
                if (!ConfigManager.DEBUG) {
                    return z;
                }
                SdkCoreLog.d("syncRun", SdkCoreLog.content(SdkConstants.KERNEL_NAME, SdkCoreLog.getTimeUsed("syncRun"), new String[]{"success"}));
                return z;
            }
            CommonUtils.onFailure(this.a, a2.code, a2.message);
            return false;
        }
        CommonUtils.onFailure(this.a, a.code, a.message);
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r7 = this;
        r0 = 1;
        r1 = com.alibaba.sdk.android.b.a.d;	 Catch:{ Throwable -> 0x007a }
        r1.lock();	 Catch:{ Throwable -> 0x007a }
        r1 = com.alibaba.sdk.android.ConfigManager.DEBUG;	 Catch:{ Throwable -> 0x007a }
        if (r1 == 0) goto L_0x000f;
    L_0x000a:
        r1 = "asyncRun";
        com.taobao.tae.sdk.log.SdkCoreLog.startTimeRecord(r1);	 Catch:{ Throwable -> 0x007a }
    L_0x000f:
        r1 = "0";
        a(r1);	 Catch:{ Throwable -> 0x007a }
        r1 = r7.c;	 Catch:{ Throwable -> 0x007a }
        if (r1 == 0) goto L_0x0042;
    L_0x0018:
        if (r0 != 0) goto L_0x004b;
    L_0x001a:
        r0 = com.alibaba.sdk.android.ConfigManager.DEBUG;	 Catch:{ Throwable -> 0x007a }
        if (r0 == 0) goto L_0x0037;
    L_0x001e:
        r0 = "asyncRun";
        r1 = "kernel";
        r2 = "asyncRun";
        r2 = com.taobao.tae.sdk.log.SdkCoreLog.getTimeUsed(r2);	 Catch:{ Throwable -> 0x007a }
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x007a }
        r5 = 0;
        r6 = "failure";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x007a }
        r1 = com.taobao.tae.sdk.log.SdkCoreLog.content(r1, r2, r4);	 Catch:{ Throwable -> 0x007a }
        com.taobao.tae.sdk.log.SdkCoreLog.d(r0, r1);	 Catch:{ Throwable -> 0x007a }
    L_0x0037:
        r0 = r7.d;
        r0.countDown();
        r0 = com.alibaba.sdk.android.b.a.d;
        r0.unlock();
    L_0x0041:
        return;
    L_0x0042:
        r0 = com.alibaba.sdk.android.plugin.b.a;	 Catch:{ Throwable -> 0x007a }
        r0 = r7.a;	 Catch:{ Throwable -> 0x007a }
        r0 = com.alibaba.sdk.android.plugin.b.b(r0);	 Catch:{ Throwable -> 0x007a }
        goto L_0x0018;
    L_0x004b:
        r0 = com.alibaba.sdk.android.b.a.f;	 Catch:{ Throwable -> 0x007a }
        r1 = new com.alibaba.sdk.android.task.e;	 Catch:{ Throwable -> 0x007a }
        r1.<init>(r7);	 Catch:{ Throwable -> 0x007a }
        r0.postUITask(r1);	 Catch:{ Throwable -> 0x007a }
        r0 = 1;
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ Throwable -> 0x007a }
        com.alibaba.sdk.android.b.a.b = r0;	 Catch:{ Throwable -> 0x007a }
        r0 = com.alibaba.sdk.android.ConfigManager.DEBUG;	 Catch:{ Throwable -> 0x007a }
        if (r0 == 0) goto L_0x0037;
    L_0x0060:
        r0 = "asyncRun";
        r1 = "kernel";
        r2 = "asyncRun";
        r2 = com.taobao.tae.sdk.log.SdkCoreLog.getTimeUsed(r2);	 Catch:{ Throwable -> 0x007a }
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x007a }
        r5 = 0;
        r6 = "success";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x007a }
        r1 = com.taobao.tae.sdk.log.SdkCoreLog.content(r1, r2, r4);	 Catch:{ Throwable -> 0x007a }
        com.taobao.tae.sdk.log.SdkCoreLog.d(r0, r1);	 Catch:{ Throwable -> 0x007a }
        goto L_0x0037;
    L_0x007a:
        r0 = move-exception;
        r1 = "kernel";
        r2 = r0.getMessage();	 Catch:{ all -> 0x0092 }
        com.alibaba.sdk.android.trace.AliSDKLogger.e(r1, r2, r0);	 Catch:{ all -> 0x0092 }
        r7.a(r0);	 Catch:{ all -> 0x0092 }
        r0 = r7.d;
        r0.countDown();
        r0 = com.alibaba.sdk.android.b.a.d;
        r0.unlock();
        goto L_0x0041;
    L_0x0092:
        r0 = move-exception;
        r1 = r7.d;
        r1.countDown();
        r1 = com.alibaba.sdk.android.b.a.d;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.task.a.run():void");
    }

    public final void b() {
        try {
            this.d.await();
        } catch (Throwable e) {
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, e.getMessage(), e);
        }
    }

    private static void a(String str) {
        try {
            String[] strArr = new String[]{"java.lang.String"};
            ReflectionUtils.invoke("com.alibaba.sdk.android.hotpatch.HotPatchManager", "apply", strArr, com.alibaba.sdk.android.b.a.e.a(ReflectionUtils.loadClassQuietly("com.alibaba.sdk.android.hotpatch.HotPatchManager"), null), new Object[]{str});
        } catch (Exception e) {
        }
    }

    private void a(Throwable th) {
        int i;
        String commonUtils;
        com.alibaba.sdk.android.b.a.b = Boolean.valueOf(false);
        if (!(th instanceof AlibabaSDKException) || ((AlibabaSDKException) th).getSDKMessage() == null) {
            i = KernelMessageConstants.GENERIC_SYSTEM_ERROR;
            commonUtils = CommonUtils.toString(th);
        } else {
            Message sDKMessage = ((AlibabaSDKException) th).getSDKMessage();
            i = sDKMessage.code;
            commonUtils = sDKMessage.message;
        }
        CommonUtils.onFailure(this.a, i, commonUtils);
    }
}
