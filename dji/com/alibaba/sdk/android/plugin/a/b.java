package com.alibaba.sdk.android.plugin.a;

import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.app.AppContext;
import com.alibaba.sdk.android.message.Message;
import com.alibaba.sdk.android.plugin.PluginContext;
import com.alibaba.sdk.android.plugin.PluginInfo;
import com.alibaba.sdk.android.plugin.PluginLifecycleAdapter;
import com.alibaba.sdk.android.plugin.PluginLifecycleException;
import com.alibaba.sdk.android.plugin.PluginManager;
import com.alibaba.sdk.android.plugin.PluginNotFoundException;
import com.alibaba.sdk.android.plugin.PluginState;
import com.alibaba.sdk.android.plugin.PluginSyncLifecycleAdapter;
import com.alibaba.sdk.android.repository.PluginRepository;
import com.alibaba.sdk.android.repository.a.c;
import com.alibaba.sdk.android.security.AccessController;
import com.alibaba.sdk.android.trace.ActionTraceLogger;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.alibaba.sdk.android.trace.TraceLoggerManager;
import com.alibaba.sdk.android.ut.UTConstants;
import com.alibaba.sdk.android.ut.UserTrackerService;
import com.taobao.tae.sdk.log.SdkCoreLog;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class b implements PluginManager {
    private ClassLoader a = c.class.getClassLoader();
    private Map<String, a> b = new ConcurrentHashMap();
    private AccessController c;

    static class a {
        public volatile PluginState a;
        public Object b;
        public PluginInfo c;

        a() {
        }
    }

    public b(PluginRepository pluginRepository) {
        for (PluginInfo pluginInfo : pluginRepository.getPluginInfos()) {
            a aVar = new a();
            aVar.c = pluginInfo;
            aVar.a = PluginState.LOADED;
            this.b.put(pluginInfo.name, aVar);
        }
    }

    public final void syncStartPlugin(String str) throws PluginLifecycleException, PluginNotFoundException {
        PluginLifecycleException pluginLifecycleException;
        if (ConfigManager.DEBUG) {
            SdkCoreLog.startTimeRecord(str);
        }
        ActionTraceLogger action = TraceLoggerManager.INSTANCE.action(str + ".pluginStart");
        a aVar = (a) this.b.get(str);
        if (aVar == null) {
            action.failed("e", "plugin not found");
            throw new PluginNotFoundException(str, "The plugin " + str + " was not found in the current repository");
        } else if (aVar.a == PluginState.LOADED || aVar.a == PluginState.SYNC_START_FAILED) {
            String[] strArr = aVar.c.after;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str2 = strArr[i];
                PluginState pluginState = getPluginState(str2);
                if (pluginState == PluginState.SYNC_STARTED || pluginState == PluginState.UNDEFINED) {
                    i++;
                } else {
                    action.failed("e", "dependent plugin " + aVar.a.name() + " not started");
                    throw new PluginLifecycleException(str, "The dependent plugin " + str2 + " is not started");
                }
            }
            action.info("version", aVar.c.version);
            AliSDKLogger.i(SdkConstants.KERNEL_NAME, "Sync starting the plugin " + str + " version " + aVar.c.version);
            long currentTimeMillis = System.currentTimeMillis();
            aVar.a = PluginState.SYNC_STARTING;
            AppContext b = com.alibaba.sdk.android.app.a.a.b(str);
            PluginContext a = com.alibaba.sdk.android.plugin.a.a.a(aVar.c);
            String str3 = aVar.c.lifecycleAdapterClassName;
            if (str3 == null) {
                aVar.a = PluginState.SYNC_STARTED;
                action.success();
                if (ConfigManager.DEBUG) {
                    SdkCoreLog.d(str, SdkCoreLog.content(SdkConstants.KERNEL_NAME, SdkCoreLog.getTimeUsed(str), new String[]{"success"}));
                    return;
                }
                return;
            }
            try {
                Class loadClass = this.a.loadClass(str3);
                Object newInstance = loadClass.newInstance();
                aVar.b = newInstance;
                if (newInstance instanceof PluginSyncLifecycleAdapter) {
                    ((PluginSyncLifecycleAdapter) newInstance).syncStart(b, a);
                }
                aVar.a = PluginState.SYNC_STARTED;
                a(true, str, System.currentTimeMillis() - currentTimeMillis);
                action.success("lifecycleAdapter", loadClass);
                if (ConfigManager.DEBUG) {
                    SdkCoreLog.d(str, SdkCoreLog.content(SdkConstants.KERNEL_NAME, SdkCoreLog.getTimeUsed(str), new String[]{"success"}));
                }
                if (aVar.a == PluginState.SYNC_START_FAILED) {
                    aVar.b = null;
                    com.alibaba.sdk.android.plugin.a.a.a(str);
                    com.alibaba.sdk.android.app.a.a.c(str);
                    a(false, str, System.currentTimeMillis() - currentTimeMillis);
                }
            } catch (Throwable e) {
                throw new PluginLifecycleException(Message.create(16, e.getMessage()), e);
            } catch (Throwable e2) {
                pluginLifecycleException = new PluginLifecycleException(str, e2.getMessage(), e2);
                if (e2.getSDKMessage() != null) {
                    pluginLifecycleException.setResultCode(ResultCode.create(e2.getSDKMessage()));
                }
                throw pluginLifecycleException;
            } catch (PluginLifecycleException e3) {
                e3.setPluginName(str);
                aVar.a = PluginState.SYNC_START_FAILED;
                action.failed("e", e3);
                throw e3;
            } catch (Throwable th) {
                if (aVar.a == PluginState.SYNC_START_FAILED) {
                    aVar.b = null;
                    com.alibaba.sdk.android.plugin.a.a.a(str);
                    com.alibaba.sdk.android.app.a.a.c(str);
                    a(false, str, System.currentTimeMillis() - currentTimeMillis);
                }
            }
        } else {
            action.failed("e", "plugin state in " + aVar.a.name());
            if (AliSDKLogger.isDebugEnabled()) {
                AliSDKLogger.d(SdkConstants.KERNEL_NAME, "Plugin " + str + " is in the " + aVar.a.name() + ", startPlugin exits");
            }
        }
    }

    public final synchronized void startPlugin(String str) throws PluginLifecycleException, PluginNotFoundException {
        PluginLifecycleException pluginLifecycleException;
        AliSDKLogger.d(SdkConstants.KERNEL_NAME, "start plugin " + str);
        if (ConfigManager.DEBUG) {
            SdkCoreLog.startTimeRecord(str);
        }
        ActionTraceLogger action = TraceLoggerManager.INSTANCE.action(str + ".pluginStart");
        a aVar = (a) this.b.get(str);
        if (aVar == null) {
            action.failed("e", "plugin not found");
            throw new PluginNotFoundException(str, "The plugin " + str + " was not found in the current repository");
        }
        if (this.c == null) {
            this.c = (AccessController) com.alibaba.sdk.android.b.a.e.a(AccessController.class, Collections.singletonMap(SdkConstants.PLUGIN_VENDOR_KEY, "security"));
        }
        if (this.c != null && !this.c.checkPluginLoadPermission(str)) {
            action.failed("e", "plugin " + str + "not allowed to start");
        } else if (aVar.a == PluginState.SYNC_STARTED || aVar.a == PluginState.ASYNC_START_FAILED) {
            String[] strArr = aVar.c.after;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str2 = strArr[i];
                PluginState pluginState = getPluginState(str2);
                if (pluginState == PluginState.ASYNC_STARTED || pluginState == PluginState.UNDEFINED) {
                    i++;
                } else {
                    action.failed("e", "dependent plugin " + aVar.a.name() + " not started");
                    throw new PluginLifecycleException(str, "The dependent plugin " + str2 + " is not started");
                }
            }
            action.info("version", aVar.c.version);
            AliSDKLogger.i(SdkConstants.KERNEL_NAME, "Async starting the plugin " + str + " version " + aVar.c.version);
            long currentTimeMillis = System.currentTimeMillis();
            aVar.a = PluginState.ASYNC_STARTING;
            AppContext b = com.alibaba.sdk.android.app.a.a.b(str);
            PluginContext a = com.alibaba.sdk.android.plugin.a.a.a(aVar.c);
            if (aVar.c.lifecycleAdapterClassName == null) {
                aVar.a = PluginState.ASYNC_STARTED;
                action.success();
                if (ConfigManager.DEBUG) {
                    SdkCoreLog.d(str, SdkCoreLog.content(SdkConstants.KERNEL_NAME, SdkCoreLog.getTimeUsed(str), new String[]{"success"}));
                }
            } else {
                try {
                    if (aVar.b instanceof PluginLifecycleAdapter) {
                        ((PluginLifecycleAdapter) aVar.b).start(b, a);
                    }
                    aVar.a = PluginState.ASYNC_STARTED;
                    a(true, str, System.currentTimeMillis() - currentTimeMillis);
                    action.success("lifecycleAdapter", aVar.b);
                    if (ConfigManager.DEBUG) {
                        SdkCoreLog.d(str, SdkCoreLog.content(SdkConstants.KERNEL_NAME, SdkCoreLog.getTimeUsed(str), new String[]{"success"}));
                    }
                    if (aVar.a == PluginState.ASYNC_START_FAILED) {
                        aVar.b = null;
                        com.alibaba.sdk.android.plugin.a.a.a(str);
                        com.alibaba.sdk.android.app.a.a.c(str);
                        a(false, str, System.currentTimeMillis() - currentTimeMillis);
                    }
                } catch (Throwable e) {
                    pluginLifecycleException = new PluginLifecycleException(str, e.getMessage(), e);
                    if (e.getSDKMessage() != null) {
                        pluginLifecycleException.setResultCode(ResultCode.create(e.getSDKMessage()));
                    }
                    throw pluginLifecycleException;
                } catch (PluginLifecycleException e2) {
                    e2.setPluginName(str);
                    aVar.a = PluginState.ASYNC_START_FAILED;
                    action.failed("e", e2);
                    throw e2;
                } catch (Throwable th) {
                    if (aVar.a == PluginState.ASYNC_START_FAILED) {
                        aVar.b = null;
                        com.alibaba.sdk.android.plugin.a.a.a(str);
                        com.alibaba.sdk.android.app.a.a.c(str);
                        a(false, str, System.currentTimeMillis() - currentTimeMillis);
                    }
                }
            }
        } else {
            action.failed("e", "plugin state in " + aVar.a.name());
            if (AliSDKLogger.isDebugEnabled()) {
                AliSDKLogger.d(SdkConstants.KERNEL_NAME, "Plugin " + str + " is in the " + aVar.a.name() + ", startPlugin exits");
            }
        }
    }

    public final synchronized void stopPlugin(String str) throws PluginLifecycleException, PluginNotFoundException {
        PluginLifecycleException pluginLifecycleException;
        ActionTraceLogger action = TraceLoggerManager.INSTANCE.action(str + ".pluginStop");
        a aVar = (a) this.b.get(str);
        if (aVar == null) {
            action.failed("e", "plugin not found");
            throw new PluginNotFoundException(str, "The plugin " + str + " was not found");
        } else if (aVar.a == PluginState.SYNC_STARTED || aVar.a == PluginState.ASYNC_STARTED) {
            action.info("version", aVar.c.version);
            AliSDKLogger.i(SdkConstants.KERNEL_NAME, "Stopping the plugin " + str + " version " + aVar.c.version);
            aVar.a = PluginState.ASYNC_STOPPING;
            try {
                if (aVar.b instanceof PluginLifecycleAdapter) {
                    ((PluginLifecycleAdapter) aVar.b).stop(com.alibaba.sdk.android.app.a.a.a(str), com.alibaba.sdk.android.plugin.a.a.b(str));
                }
                aVar.b = null;
                aVar.a = PluginState.ASYNC_STOPPED;
                action.success();
                com.alibaba.sdk.android.app.a.a.c(str);
                com.alibaba.sdk.android.plugin.a.a.a(str);
            } catch (Throwable e) {
                pluginLifecycleException = new PluginLifecycleException(str, e.getMessage(), e);
                if (e.getSDKMessage() != null) {
                    pluginLifecycleException.setResultCode(ResultCode.create(e.getSDKMessage()));
                }
                throw pluginLifecycleException;
            } catch (PluginLifecycleException pluginLifecycleException2) {
                pluginLifecycleException2.setPluginName(str);
                aVar.a = PluginState.ASYNC_STOP_FAILED;
                action.failed("e", pluginLifecycleException2);
                throw pluginLifecycleException2;
            } catch (Throwable th) {
                com.alibaba.sdk.android.app.a.a.c(str);
                com.alibaba.sdk.android.plugin.a.a.a(str);
            }
        } else {
            if (AliSDKLogger.isDebugEnabled()) {
                AliSDKLogger.d(SdkConstants.KERNEL_NAME, "The plugin " + str + " is in the " + aVar.a.name() + " state, stopPlugin exits");
            }
            action.failed("e", "plugin state in " + aVar.a.name());
        }
    }

    public final PluginState getPluginState(String str) {
        a aVar = (a) this.b.get(str);
        return aVar != null ? aVar.a : PluginState.UNDEFINED;
    }

    public final String[] getPluginNames() {
        return (String[]) this.b.keySet().toArray(new String[this.b.size()]);
    }

    private static void a(boolean z, String str, long j) {
        String str2 = z ? UTConstants.E_SDK_PLUGIN_START_SUCCESS : UTConstants.E_SDK_PLUGIN_START_FAILURE;
        UserTrackerService userTrackerService = (UserTrackerService) com.alibaba.sdk.android.b.a.e.a(UserTrackerService.class, null);
        if (userTrackerService != null) {
            userTrackerService.sendCustomHit(str2, j, str, null);
        }
    }
}
