package com.alibaba.sdk.android.repository.a;

import android.content.res.AssetManager;
import android.text.TextUtils;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.plugin.PluginInfo;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public final class b {
    private static final String[] a = new String[0];
    private AssetManager b;

    public b(AssetManager assetManager) {
        this.b = assetManager;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.alibaba.sdk.android.plugin.PluginInfo[] a() throws java.io.IOException {
        /*
        r10 = this;
        r0 = 0;
        r3 = new java.util.ArrayList;
        r3.<init>();
        r1 = r10.b;
        r2 = "tae_sdk_plugins";
        r4 = r1.list(r2);
        if (r4 == 0) goto L_0x0013;
    L_0x0010:
        r1 = r4.length;
        if (r1 != 0) goto L_0x0016;
    L_0x0013:
        r0 = new com.alibaba.sdk.android.plugin.PluginInfo[r0];
    L_0x0015:
        return r0;
    L_0x0016:
        r5 = r4.length;
        r2 = r0;
    L_0x0018:
        if (r2 >= r5) goto L_0x006e;
    L_0x001a:
        r6 = r4[r2];
        r1 = 0;
        r0 = r10.b;	 Catch:{ Exception -> 0x0050 }
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0050 }
        r8 = "tae_sdk_plugins";
        r7.<init>(r8);	 Catch:{ Exception -> 0x0050 }
        r8 = java.io.File.separator;	 Catch:{ Exception -> 0x0050 }
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0050 }
        r7 = r7.append(r6);	 Catch:{ Exception -> 0x0050 }
        r7 = r7.toString();	 Catch:{ Exception -> 0x0050 }
        r1 = r0.open(r7);	 Catch:{ Exception -> 0x0050 }
        r0 = new java.util.Properties;	 Catch:{ Exception -> 0x0050 }
        r0.<init>();	 Catch:{ Exception -> 0x0050 }
        r0.load(r1);	 Catch:{ Exception -> 0x0050 }
        r0 = a(r6, r0);	 Catch:{ Exception -> 0x0050 }
        if (r0 == 0) goto L_0x0049;
    L_0x0046:
        r3.add(r0);	 Catch:{ Exception -> 0x0050 }
    L_0x0049:
        com.alibaba.sdk.android.util.IOUtils.closeQuietly(r1);
    L_0x004c:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0018;
    L_0x0050:
        r0 = move-exception;
        r7 = "kernel";
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0069 }
        r9 = "Fail to load plugin configuration properties ";
        r8.<init>(r9);	 Catch:{ all -> 0x0069 }
        r6 = r8.append(r6);	 Catch:{ all -> 0x0069 }
        r6 = r6.toString();	 Catch:{ all -> 0x0069 }
        com.alibaba.sdk.android.trace.AliSDKLogger.e(r7, r6, r0);	 Catch:{ all -> 0x0069 }
        com.alibaba.sdk.android.util.IOUtils.closeQuietly(r1);
        goto L_0x004c;
    L_0x0069:
        r0 = move-exception;
        com.alibaba.sdk.android.util.IOUtils.closeQuietly(r1);
        throw r0;
    L_0x006e:
        r0 = r3.size();
        r0 = new com.alibaba.sdk.android.plugin.PluginInfo[r0];
        r0 = r3.toArray(r0);
        r0 = (com.alibaba.sdk.android.plugin.PluginInfo[]) r0;
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.repository.a.b.a():com.alibaba.sdk.android.plugin.PluginInfo[]");
    }

    private static PluginInfo a(String str, Properties properties) {
        String property = properties.getProperty("plugin.name");
        if (property == null) {
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, "Unable to find expected plugin property plugin.name in the file " + str);
            return null;
        }
        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.name = property;
        String str2 = property + ".properties.";
        Map hashMap = new HashMap();
        for (Entry entry : properties.entrySet()) {
            String str3 = (String) entry.getKey();
            if (str3.startsWith(str2)) {
                if (str3.length() == str2.length()) {
                    AliSDKLogger.e(SdkConstants.KERNEL_NAME, "Invalid property configuration " + str3 + " in the file " + str);
                } else {
                    hashMap.put(str3.substring(str2.length()), (String) entry.getValue());
                }
            }
        }
        pluginInfo.properties = Collections.unmodifiableMap(hashMap);
        String property2 = properties.getProperty(property + ".lifecycleAdapter.class");
        if (property2 == null && AliSDKLogger.isDebugEnabled()) {
            AliSDKLogger.i(SdkConstants.KERNEL_NAME, "No plugin lifecycle adapter is defined for the plugin " + property + " in the file " + str);
        }
        pluginInfo.lifecycleAdapterClassName = property2;
        property2 = properties.getProperty(property + ".version");
        if (TextUtils.isEmpty(property2)) {
            property2 = "1";
        }
        pluginInfo.version = property2;
        Object property3 = properties.getProperty(property + ".before");
        if (TextUtils.isEmpty(property3)) {
            pluginInfo.before = a;
        } else {
            pluginInfo.before = property3.split("[,]");
        }
        property3 = properties.getProperty(property + ".after");
        if (TextUtils.isEmpty(property3)) {
            pluginInfo.after = a;
        } else {
            pluginInfo.after = property3.split("[,]");
        }
        pluginInfo.beforeAll = Boolean.valueOf(properties.getProperty(property + ".beforeAll"));
        pluginInfo.afterAll = Boolean.valueOf(properties.getProperty(property + ".afterAll"));
        if (!pluginInfo.beforeAll.booleanValue() || !pluginInfo.afterAll.booleanValue()) {
            return pluginInfo;
        }
        throw new RuntimeException("The plugin " + property + " config the beforeAll and afterAll at the same time");
    }
}
