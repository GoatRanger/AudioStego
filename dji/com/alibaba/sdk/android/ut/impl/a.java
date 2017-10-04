package com.alibaba.sdk.android.ut.impl;

import android.content.Context;
import android.util.Log;
import com.alibaba.sdk.android.ConfigManager;
import com.sina.weibo.sdk.constant.WBConstants;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public final class a extends UTSecuritySDKRequestAuthentication {
    private String a = null;
    private Object b = null;
    private Object c = null;
    private Class<?> d = null;
    private Field e = null;
    private Field f = null;
    private Field g = null;
    private Method h = null;
    private Context i;
    private boolean j;

    public final String getAppkey() {
        return this.a;
    }

    public a(String str, Context context) {
        super(str);
        this.a = str;
        this.i = context;
        try {
            Class cls = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            this.b = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{this.i});
            this.c = cls.getMethod("getSecureSignatureComp", new Class[0]).invoke(this.b, new Object[0]);
            try {
                this.d = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                this.e = this.d.getDeclaredField(WBConstants.SSO_APP_KEY);
                this.f = this.d.getDeclaredField("paramMap");
                this.g = this.d.getDeclaredField("requestType");
                this.h = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{this.d, String.class});
                this.j = true;
            } catch (Throwable th) {
                Log.e("ut", "Fail to init UT, the error message is " + th.getMessage());
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            Log.e("ut", "Fail to load security signature component", th2);
        }
    }

    public final String getSign(String str) {
        if (this.a == null) {
            Log.e("ut", "UTSecuritySDKRequestAuthentication:getSign, There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            if (!this.j) {
                return null;
            }
            try {
                Object newInstance = this.d.newInstance();
                this.e.set(newInstance, this.a);
                ((Map) this.f.get(newInstance)).put("INPUT", str);
                this.g.set(newInstance, Integer.valueOf(1));
                return (String) this.h.invoke(this.c, new Object[]{newInstance, ConfigManager.POSTFIX_OF_SECURITY_JPG});
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
