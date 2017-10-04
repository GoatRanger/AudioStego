package com.alibaba.sdk.android.repository.a;

import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.plugin.PluginInfo;
import com.alibaba.sdk.android.repository.PluginRepository;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.util.SortUtils;
import com.alibaba.sdk.android.util.SortUtils.SortInfo;
import com.taobao.tae.sdk.log.SdkCoreLog;
import java.util.LinkedHashMap;
import java.util.Map;

public class c implements PluginRepository {
    private b a = new b(a.a.getAssets());
    private a b = new a();
    private Map<String, PluginInfo> c = new LinkedHashMap();

    public c() {
        a();
    }

    public PluginInfo[] getPluginInfos() {
        return (PluginInfo[]) this.c.values().toArray(new PluginInfo[this.c.size()]);
    }

    public PluginInfo getPluginInfo(String str) {
        return (PluginInfo) this.c.get(str);
    }

    public String[] getPluginNames() {
        return (String[]) this.c.keySet().toArray(new String[this.c.size()]);
    }

    private void a() {
        PluginInfo[] a;
        Throwable e;
        if (ConfigManager.DEBUG) {
            SdkCoreLog.startTimeRecord("binaryFinder");
        }
        SortInfo[] sortInfoArr = null;
        try {
            sortInfoArr = this.b.a();
        } catch (Throwable e2) {
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, "Fail to read the plugin info from binary finder", e2);
        }
        if (ConfigManager.DEBUG) {
            SdkCoreLog.d("findPlugins", SdkCoreLog.content("binaryFinder", SdkCoreLog.getTimeUsed("binaryFinder"), new String[]{"success"}));
        }
        if (sortInfoArr == null) {
            try {
                if (ConfigManager.DEBUG) {
                    SdkCoreLog.startTimeRecord("propertiesFinder");
                }
                a = this.a.a();
                try {
                    if (ConfigManager.DEBUG) {
                        SdkCoreLog.d("findPlugins", SdkCoreLog.content("propertiesFinder", SdkCoreLog.getTimeUsed("propertiesFinder"), new String[]{"success"}));
                    }
                    if (ConfigManager.DEBUG) {
                        SdkCoreLog.startTimeRecord("sortPlugin");
                    }
                    if (a.length > 2) {
                        SortUtils.sorts(a);
                    }
                    if (ConfigManager.DEBUG) {
                        SdkCoreLog.d("sortPlugin", SdkCoreLog.content("PluginInfoSorter", SdkCoreLog.getTimeUsed("sortPlugin"), new String[]{"success"}));
                    }
                } catch (Exception e3) {
                    e = e3;
                    AliSDKLogger.e(SdkConstants.KERNEL_NAME, "Fail to read the plugin info from property file finder", e);
                    if (a == null) {
                        for (PluginInfo pluginInfo : a) {
                            this.c.put(pluginInfo.name, pluginInfo);
                        }
                        a.f.postTask(new d(this, a));
                    }
                }
            } catch (Throwable e22) {
                Throwable th = e22;
                a = sortInfoArr;
                e = th;
                AliSDKLogger.e(SdkConstants.KERNEL_NAME, "Fail to read the plugin info from property file finder", e);
                if (a == null) {
                    for (PluginInfo pluginInfo2 : a) {
                        this.c.put(pluginInfo2.name, pluginInfo2);
                    }
                    a.f.postTask(new d(this, a));
                }
            }
        }
        SortInfo[] sortInfoArr2 = sortInfoArr;
        if (a == null) {
            for (PluginInfo pluginInfo22 : a) {
                this.c.put(pluginInfo22.name, pluginInfo22);
            }
            a.f.postTask(new d(this, a));
        }
    }
}
