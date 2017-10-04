package com.alibaba.sdk.android.plugin.config.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.config.PropertyChangeListener;
import com.alibaba.sdk.android.plugin.PluginInfo;
import com.alibaba.sdk.android.plugin.config.PluginSystemConfigurations;
import com.alibaba.sdk.android.repository.PluginRepository;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.util.CommonUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.JSONObject;

public final class b implements PluginSystemConfigurations {
    private final Set<String> a = new LinkedHashSet();
    private ConcurrentMap<String, Map<String, String>> b = new ConcurrentHashMap();
    private Map<String, String> c = new ConcurrentHashMap();
    private SharedPreferences d;
    private Context e;

    private static class a {
        public String a;
        public String b;
        public String c;
        public String d;

        private a() {
        }
    }

    public b(Context context, PluginRepository pluginRepository) {
        this.e = context;
        a(pluginRepository);
    }

    private void a(PluginRepository pluginRepository) {
        PluginInfo[] pluginInfos = pluginRepository.getPluginInfos();
        if (pluginInfos.length != 0) {
            for (PluginInfo pluginInfo : pluginInfos) {
                this.a.add(pluginInfo.name);
                if (pluginInfo.properties.size() != 0) {
                    this.b.put(pluginInfo.name, new HashMap(pluginInfo.properties));
                }
            }
            a(a(), false);
            a(ConfigManager.userProperties, false);
            if (AliSDKLogger.isDebugEnabled()) {
                AliSDKLogger.d(SdkConstants.KERNEL_NAME, "Initialize plugin system persistent configurations successfully");
            }
        }
    }

    private void a(Map<String, String> map, boolean z) {
        List arrayList;
        if (z) {
            arrayList = new ArrayList();
        } else {
            arrayList = null;
        }
        for (Entry entry : map.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null)) {
                int indexOf = ((String) entry.getKey()).indexOf(46);
                String str;
                if (indexOf == -1) {
                    str = (String) this.c.put(((String) entry.getKey()).trim(), ((String) entry.getValue()).trim());
                    if (z && !CommonUtils.isEqual(str, entry.getValue())) {
                        a(arrayList, "$global$", (String) entry.getKey(), str, (String) entry.getValue());
                    }
                } else if (indexOf == -1 || indexOf == ((String) entry.getKey()).length() - 1) {
                    AliSDKLogger.w(SdkConstants.KERNEL_NAME, "Invalid property name " + ((String) entry.getKey()));
                } else {
                    String trim = ((String) entry.getKey()).substring(0, indexOf).trim();
                    String trim2 = ((String) entry.getKey()).substring(indexOf + 1).trim();
                    String trim3 = ((String) entry.getValue()).trim();
                    if ("$global$".equals(trim)) {
                        str = (String) this.c.put(trim2, trim3);
                        if (z && !CommonUtils.isEqual(str, entry.getValue())) {
                            a(arrayList, "$global$", trim2, str, trim3);
                        }
                    } else {
                        Map map2 = (Map) this.b.get(trim);
                        if (map2 == null) {
                            map2 = new HashMap();
                            this.b.put(trim, map2);
                        }
                        String str2 = (String) map2.put(trim2, trim3);
                        if (z && !CommonUtils.isEqual(str2, trim3)) {
                            a(arrayList, trim, trim2, str2, trim3);
                        }
                    }
                }
            }
        }
        if (z && arrayList.size() != 0) {
            com.alibaba.sdk.android.b.a.f.postTask(new c(this, arrayList));
        }
    }

    public final String getPluginProperty(String str, String str2) {
        Map map = (Map) this.b.get(str);
        return map == null ? null : (String) map.get(str2);
    }

    public final String[] getStartedPluginNames() {
        String[] strArr;
        synchronized (this.a) {
            strArr = (String[]) this.a.toArray(new String[this.a.size()]);
        }
        return strArr;
    }

    public final void addStartRequiredPlugin(String str) {
        synchronized (this.a) {
            this.a.add(str);
        }
    }

    public final void removeStartRequiredPlugin(String str) {
        synchronized (this.a) {
            this.a.remove(str);
        }
    }

    public final void setPluginProperty(String str, String str2, String str3) {
        Map map = (Map) this.b.get(str);
        if (map == null) {
            this.b.putIfAbsent(str, new ConcurrentHashMap());
            map = (Map) this.b.get(str);
        }
        String str4 = (String) map.put(str2, str3);
        if (CommonUtils.isEqual(str3, str4)) {
            a(str, str2, str4, str3);
        }
    }

    public final void unsetPluginProperty(String str, String str2) {
        Map map = (Map) this.b.get(str);
        if (map != null) {
            String str3 = (String) map.remove(str2);
            if (str3 != null) {
                a(null, str, str3, null);
            }
        }
    }

    public final void setGlobalProperty(String str, String str2) {
        if (str != null) {
            String str3 = (String) this.c.put(str, str2);
            if (CommonUtils.isEqual(str2, str3)) {
                a("$global$", str, str3, str2);
            }
        }
    }

    public final String getGlobalProperty(String str) {
        if (str == null) {
            return null;
        }
        return (String) this.c.get(str);
    }

    public final void unsetGlobalProperty(String str) {
        String str2 = (String) this.c.remove(str);
        if (str2 != null) {
            a(null, str, str2, null);
        }
    }

    private static void a(List<a> list, String str, String str2, String str3, String str4) {
        a aVar = new a();
        aVar.a = str;
        aVar.b = str2;
        aVar.c = str4;
        aVar.d = str3;
        list.add(aVar);
    }

    private void a(String str, String str2, String str3, String str4) {
        Map singletonMap;
        if ("$global$".equals(str)) {
            singletonMap = Collections.singletonMap(SdkConstants.PROPERTY_SCOPE, SdkConstants.PROPERTY_GLOBAL_SCOPE);
        } else {
            singletonMap = new HashMap();
            singletonMap.put(SdkConstants.PROPERTY_SCOPE, SdkConstants.PROPERTY_PLUGIN_SCOPE);
            singletonMap.put(SdkConstants.PLUGIN_VENDOR_KEY, str);
        }
        PropertyChangeListener[] propertyChangeListenerArr = (PropertyChangeListener[]) com.alibaba.sdk.android.b.a.e.b(PropertyChangeListener.class, singletonMap);
        if (propertyChangeListenerArr != null && propertyChangeListenerArr.length > 0) {
            com.alibaba.sdk.android.b.a.f.postTask(new d(this, propertyChangeListenerArr, str2, str3, str4));
        }
    }

    public final void setDynamicProperties(String str) {
        try {
            this.d.edit().putString(dji.pilot.college.b.b.g, str).commit();
        } catch (Throwable th) {
        }
        a(a(), true);
    }

    private Map<String, String> a() {
        Map<String, String> hashMap = new HashMap();
        try {
            this.d = this.e.getSharedPreferences(SdkConstants.DYNAMIC_CONFIG_SP, 0);
            String string = this.d.getString(dji.pilot.college.b.b.g, null);
            if (string != null) {
                JSONObject jSONObject = new JSONObject(string);
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    string = (String) keys.next();
                    hashMap.put(string, jSONObject.optString(string));
                }
            }
        } catch (Throwable th) {
            AliSDKLogger.e(SdkConstants.KERNEL_NAME, th);
        }
        return hashMap;
    }
}
