package com.alibaba.sdk.android.plugin;

import com.alibaba.sdk.android.util.SortUtils.SortInfo;
import java.io.Serializable;
import java.util.Map;

public class PluginInfo extends SortInfo implements Serializable {
    private static final long serialVersionUID = 9167423926625476763L;
    public String lifecycleAdapterClassName;
    public Map<String, String> properties;
    public String version;

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        return (this.name == null ? 0 : this.name.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PluginInfo pluginInfo = (PluginInfo) obj;
        if (this.name == null) {
            if (pluginInfo.name != null) {
                return false;
            }
            return true;
        } else if (this.name.equals(pluginInfo.name)) {
            return true;
        } else {
            return false;
        }
    }
}
