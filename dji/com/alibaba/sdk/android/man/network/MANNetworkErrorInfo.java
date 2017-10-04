package com.alibaba.sdk.android.man.network;

import com.alibaba.sdk.android.man.util.ToolKit;
import java.util.HashMap;

public class MANNetworkErrorInfo {
    private final HashMap<String, String> properties;

    protected MANNetworkErrorInfo(HashMap<String, String> hashMap) {
        this.properties = hashMap;
    }

    public HashMap<String, String> getProperties() {
        return this.properties;
    }

    public MANNetworkErrorInfo withExtraInfo(String str, String str2) {
        if (!(ToolKit.isNullOrEmpty(str) || ToolKit.isNullOrEmpty(str2))) {
            this.properties.put(str, str2);
        }
        return this;
    }
}
