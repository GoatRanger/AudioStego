package com.alibaba.sdk.android.repository.a;

import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.plugin.PluginInfo;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.util.PersistentUtils.ObjectStore;
import java.io.IOException;

public final class a {
    private ObjectStore<PluginInfo[]> a = new ObjectStore("alisdk_plugin_list", ".pluginlist");

    public final PluginInfo[] a() throws IOException {
        try {
            return (PluginInfo[]) this.a.readItem();
        } catch (Throwable e) {
            e.printStackTrace();
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, "Fail to read the plugin list from pluginlist file", e);
            return null;
        }
    }

    public final void a(PluginInfo[] pluginInfoArr) {
        try {
            this.a.writeItem(pluginInfoArr);
        } catch (Throwable e) {
            e.printStackTrace();
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, "fail to write the plugin info", e);
        }
    }
}
