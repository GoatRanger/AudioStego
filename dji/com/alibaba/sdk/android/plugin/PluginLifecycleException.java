package com.alibaba.sdk.android.plugin;

import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.message.Message;
import dji.pilot.usercenter.protocol.d;

public class PluginLifecycleException extends Exception {
    private static final long serialVersionUID = -1346625393468196870L;
    private String pluginName;
    private ResultCode resultCode;

    public PluginLifecycleException(Message message) {
        super(message.message);
        this.resultCode = ResultCode.create(message);
    }

    public PluginLifecycleException(Message message, Throwable th) {
        super(message.message, th);
        this.resultCode = ResultCode.create(message);
    }

    public PluginLifecycleException(String str, Throwable th) {
        super(str, th);
    }

    public PluginLifecycleException(String str) {
        super(str);
    }

    public PluginLifecycleException(String str, String str2, Throwable th) {
        super(str2, th);
        this.pluginName = str;
    }

    public PluginLifecycleException(String str, String str2) {
        super(str2);
        this.pluginName = str;
    }

    public String getPluginName() {
        return this.pluginName;
    }

    public void setPluginName(String str) {
        this.pluginName = str;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(d.G).append(this.pluginName).append(d.H);
        if (this.resultCode != null) {
            stringBuilder.append(this.resultCode);
        }
        stringBuilder.append(", the android stack error message is ").append(super.getMessage());
        if (getCause() != null) {
            stringBuilder.append(", which is caused by ").append(getCause().getMessage());
        }
        return stringBuilder.toString();
    }
}
