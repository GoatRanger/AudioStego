package com.alibaba.sdk.android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.alibaba.sdk.android.AlibabaSDK;
import com.alibaba.sdk.android.SdkConstants;
import com.alibaba.sdk.android.trace.AliSDKLogger;
import com.sina.weibo.sdk.statistic.LogBuilder;

public final class TraceHelper {
    private static final String a = TraceHelper.class.getSimpleName();
    public static String channel = "0";
    public static String clientTTID;
    public static int ttidVersion = 2;
    public static String webTTID;

    static {
        String str = "2014@taobao_h5_3.0.0";
        clientTTID = str;
        webTTID = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void init(android.content.Context r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
        r2 = 1;
        r1 = 0;
        r4 = com.alibaba.sdk.android.util.TraceHelper.class;
        monitor-enter(r4);
        if (r9 == 0) goto L_0x000d;
    L_0x0007:
        r0 = r9.length();	 Catch:{ RuntimeException -> 0x0076 }
        if (r0 != 0) goto L_0x00ac;
    L_0x000d:
        r0 = a(r7);	 Catch:{ RuntimeException -> 0x0076 }
    L_0x0011:
        if (r0 == 0) goto L_0x0019;
    L_0x0013:
        r3 = r0.length();	 Catch:{ RuntimeException -> 0x0076 }
        if (r3 != 0) goto L_0x003d;
    L_0x0019:
        if (r1 != 0) goto L_0x0034;
    L_0x001b:
        r1 = 7;
        r2 = "kernel";
        r3 = "initChannel";
        r5 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0076 }
        r6 = "Channel chars must in [0-9][a-z][A-Z], now : ";
        r5.<init>(r6);	 Catch:{ RuntimeException -> 0x0076 }
        r0 = r5.append(r0);	 Catch:{ RuntimeException -> 0x0076 }
        r0 = r0.toString();	 Catch:{ RuntimeException -> 0x0076 }
        com.alibaba.sdk.android.trace.AliSDKLogger.e(r1, r2, r3, r0);	 Catch:{ RuntimeException -> 0x0076 }
        r0 = "0";
    L_0x0034:
        channel = r0;	 Catch:{ RuntimeException -> 0x0076 }
        r0 = ttidVersion;	 Catch:{ RuntimeException -> 0x0076 }
        switch(r0) {
            case 1: goto L_0x0065;
            case 2: goto L_0x0093;
            default: goto L_0x003b;
        };
    L_0x003b:
        monitor-exit(r4);
        return;
    L_0x003d:
        r3 = r1;
    L_0x003e:
        r5 = r0.length();	 Catch:{ RuntimeException -> 0x0076 }
        if (r3 >= r5) goto L_0x0063;
    L_0x0044:
        r5 = r0.charAt(r3);	 Catch:{ RuntimeException -> 0x0076 }
        r6 = 48;
        if (r5 < r6) goto L_0x0050;
    L_0x004c:
        r6 = 57;
        if (r5 <= r6) goto L_0x0060;
    L_0x0050:
        r6 = 97;
        if (r5 < r6) goto L_0x0058;
    L_0x0054:
        r6 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        if (r5 <= r6) goto L_0x0060;
    L_0x0058:
        r6 = 65;
        if (r5 < r6) goto L_0x0019;
    L_0x005c:
        r6 = 90;
        if (r5 > r6) goto L_0x0019;
    L_0x0060:
        r3 = r3 + 1;
        goto L_0x003e;
    L_0x0063:
        r1 = r2;
        goto L_0x0019;
    L_0x0065:
        r0 = "2014@taobao_h5_3.0.0$%s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ RuntimeException -> 0x0076 }
        r2 = 0;
        r1[r2] = r8;	 Catch:{ RuntimeException -> 0x0076 }
        r0 = java.lang.String.format(r0, r1);	 Catch:{ RuntimeException -> 0x0076 }
        clientTTID = r0;	 Catch:{ RuntimeException -> 0x0076 }
        webTTID = r0;	 Catch:{ RuntimeException -> 0x0076 }
        goto L_0x003b;
    L_0x0076:
        r0 = move-exception;
        r1 = a;	 Catch:{ all -> 0x0090 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0090 }
        r3 = "init trace info error: ";
        r2.<init>(r3);	 Catch:{ all -> 0x0090 }
        r3 = r0.getMessage();	 Catch:{ all -> 0x0090 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0090 }
        r2 = r2.toString();	 Catch:{ all -> 0x0090 }
        com.alibaba.sdk.android.trace.AliSDKLogger.e(r1, r2, r0);	 Catch:{ all -> 0x0090 }
        goto L_0x003b;
    L_0x0090:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0093:
        r0 = "2014_%s_%s@baichuan_android_%s";
        r1 = 3;
        r1 = new java.lang.Object[r1];	 Catch:{ RuntimeException -> 0x0076 }
        r2 = 0;
        r3 = channel;	 Catch:{ RuntimeException -> 0x0076 }
        r1[r2] = r3;	 Catch:{ RuntimeException -> 0x0076 }
        r2 = 1;
        r1[r2] = r8;	 Catch:{ RuntimeException -> 0x0076 }
        r2 = 2;
        r1[r2] = r10;	 Catch:{ RuntimeException -> 0x0076 }
        r0 = java.lang.String.format(r0, r1);	 Catch:{ RuntimeException -> 0x0076 }
        clientTTID = r0;	 Catch:{ RuntimeException -> 0x0076 }
        webTTID = r0;	 Catch:{ RuntimeException -> 0x0076 }
        goto L_0x003b;
    L_0x00ac:
        r0 = r9;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.util.TraceHelper.init(android.content.Context, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private static String a(Context context) {
        try {
            String property = AlibabaSDK.getProperty(SdkConstants.KERNEL_NAME, LogBuilder.KEY_CHANNEL);
            if (property == null || property.length() <= 0) {
                property = a(context, SdkConstants.CHANNEL_META_CONFIG_KEY_ALISDK);
                if (property != null && property.length() > 0) {
                    return "0" + property;
                }
                property = a(context, SdkConstants.CHANNEL_META_CONFIG_KEY_UMENG);
                if (property != null && property.length() > 0) {
                    return "u" + property;
                }
                return "0";
            }
            String property2 = AlibabaSDK.getProperty(SdkConstants.KERNEL_NAME, "channelType");
            if (property2 == null || property2.length() <= 0) {
                return property;
            }
            if (property2.equals("umeng")) {
                return "u" + property;
            }
            if (property2.equals("baidu")) {
                return "b" + property;
            }
            return "0" + property;
        } catch (Throwable e) {
            AliSDKLogger.e(a, "getChannel error: " + e.getMessage(), e);
        }
    }

    private static String a(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 16512).applicationInfo;
            if (applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get(str);
                if (obj != null) {
                    return obj.toString();
                }
            }
        } catch (NameNotFoundException e) {
            AliSDKLogger.d("Meta config not found: " + str);
        }
        return "";
    }
}
