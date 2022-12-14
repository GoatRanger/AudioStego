package com.tencent.android.tpush.b;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import com.d.d;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.tencent.android.tpush.XGBasicPushNotificationBuilder;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;
import com.tencent.android.tpush.XGPushNotificationBuilder;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.e;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static String a(int i) {
        return "TPUSH_NOTIF_BUILDID_" + String.valueOf(i);
    }

    public static synchronized XGPushNotificationBuilder a(Context context) {
        XGPushNotificationBuilder flags;
        synchronized (b.class) {
            flags = new XGBasicPushNotificationBuilder().setFlags(16);
        }
        return flags;
    }

    public static void a(Context context, int i, XGPushNotificationBuilder xGPushNotificationBuilder) {
        String a = a(i);
        JSONObject jSONObject = new JSONObject();
        xGPushNotificationBuilder.encode(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        e.a(jSONObject2, xGPushNotificationBuilder.getType(), jSONObject.toString());
        m.b(context, a, jSONObject2.toString());
    }

    public static XGPushNotificationBuilder a(Context context, int i) {
        XGPushNotificationBuilder xGBasicPushNotificationBuilder;
        Throwable th;
        Throwable th2;
        XGPushNotificationBuilder xGPushNotificationBuilder = null;
        if (context != null) {
            String a = m.a(context, a(i), null);
            if (a != null) {
                try {
                    JSONObject jSONObject = new JSONObject(a);
                    if (jSONObject.has(XGPushNotificationBuilder.BASIC_NOTIFICATION_BUILDER_TYPE)) {
                        xGBasicPushNotificationBuilder = new XGBasicPushNotificationBuilder();
                        try {
                            xGPushNotificationBuilder = xGBasicPushNotificationBuilder;
                            a = jSONObject.getString(XGPushNotificationBuilder.BASIC_NOTIFICATION_BUILDER_TYPE);
                        } catch (Throwable e) {
                            th = e;
                            xGPushNotificationBuilder = xGBasicPushNotificationBuilder;
                            th2 = th;
                            a.c(Constants.LogTag, "", th2);
                            return xGPushNotificationBuilder;
                        }
                    } else if (jSONObject.has(XGPushNotificationBuilder.CUSTOM_NOTIFICATION_BUILDER_TYPE)) {
                        xGBasicPushNotificationBuilder = new XGCustomPushNotificationBuilder();
                        try {
                            xGPushNotificationBuilder = xGBasicPushNotificationBuilder;
                            a = jSONObject.getString(XGPushNotificationBuilder.CUSTOM_NOTIFICATION_BUILDER_TYPE);
                        } catch (Throwable e2) {
                            th = e2;
                            xGPushNotificationBuilder = xGBasicPushNotificationBuilder;
                            th2 = th;
                            a.c(Constants.LogTag, "", th2);
                            return xGPushNotificationBuilder;
                        }
                    }
                    xGPushNotificationBuilder.decode(a);
                } catch (JSONException e3) {
                    th2 = e3;
                    a.c(Constants.LogTag, "", th2);
                    return xGPushNotificationBuilder;
                }
            }
        }
        return xGPushNotificationBuilder;
    }

    public static String b(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(context.getPackageName());
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
                if (resolveInfo.activityInfo != null) {
                    return resolveInfo.activityInfo.name;
                }
            }
        } catch (Throwable th) {
            a.c("MessageHelper", "get Activity error", th);
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.content.Intent a(android.content.Context r5, com.tencent.android.tpush.b.d r6, boolean r7) {
        /*
        r4 = 1;
        r0 = 0;
        r1 = r6.a;
        switch(r1) {
            case 1: goto L_0x002b;
            case 2: goto L_0x0078;
            case 3: goto L_0x009d;
            case 4: goto L_0x00c3;
            default: goto L_0x0007;
        };
    L_0x0007:
        r1 = "MessageHelper";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "unkown type";
        r2 = r2.append(r3);
        r3 = r6.a;
        r2 = r2.append(r3);
        r2 = r2.toString();
        com.tencent.android.tpush.a.a.h(r1, r2);
    L_0x0021:
        if (r0 == 0) goto L_0x002a;
    L_0x0023:
        r1 = "action_confirm";
        r2 = r6.g;
        r0.putExtra(r1, r2);
    L_0x002a:
        return r0;
    L_0x002b:
        r2 = new android.content.Intent;
        r0 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE";
        r2.<init>(r0);
        r0 = r6.b;
        r1 = com.tencent.android.tpush.common.p.a(r0);
        if (r1 == 0) goto L_0x003e;
    L_0x003a:
        r0 = b(r5);
    L_0x003e:
        r1 = 538968064; // 0x20200000 float:1.3552527E-19 double:2.662856046E-315;
        r3 = r6.c;
        if (r3 == 0) goto L_0x004a;
    L_0x0044:
        r3 = r6.c;
        r3 = r3.a;
        if (r3 > 0) goto L_0x0070;
    L_0x004a:
        if (r7 == 0) goto L_0x004e;
    L_0x004c:
        r1 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
    L_0x004e:
        r2.addFlags(r1);
        r1 = 67239936; // 0x4020000 float:1.5281427E-36 double:3.32209424E-316;
        r2.setFlags(r1);
    L_0x0056:
        r1 = "activity";
        r2.putExtra(r1, r0);
        r0 = "notificationActionType";
        r2.putExtra(r0, r4);
        r0 = "action_type";
        r2.putExtra(r0, r4);
        r0 = r5.getApplicationContext();
        r1 = com.tencent.android.tpush.XGPushActivity.class;
        r2.setClass(r0, r1);
        r0 = r2;
        goto L_0x0021;
    L_0x0070:
        r1 = r6.c;
        r1 = r1.a;
        r2.setFlags(r1);
        goto L_0x0056;
    L_0x0078:
        r0 = new android.content.Intent;
        r1 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE";
        r0.<init>(r1);
        r1 = "activity";
        r2 = r6.f;
        r0.putExtra(r1, r2);
        r1 = "action_type";
        r2 = r6.a;
        r0.putExtra(r1, r2);
        r1 = "notificationActionType";
        r2 = 2;
        r0.putExtra(r1, r2);
        r1 = r5.getApplicationContext();
        r2 = com.tencent.android.tpush.XGPushActivity.class;
        r0.setClass(r1, r2);
        goto L_0x0021;
    L_0x009d:
        r0 = new android.content.Intent;
        r1 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE";
        r0.<init>(r1);
        r1 = "activity";
        r2 = r6.d;
        r0.putExtra(r1, r2);
        r1 = "action_type";
        r2 = r6.a;
        r0.putExtra(r1, r2);
        r1 = "notificationActionType";
        r2 = 3;
        r0.putExtra(r1, r2);
        r1 = r5.getApplicationContext();
        r2 = com.tencent.android.tpush.XGPushActivity.class;
        r0.setClass(r1, r2);
        goto L_0x0021;
    L_0x00c3:
        r0 = new android.content.Intent;
        r1 = "com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE";
        r0.<init>(r1);
        r1 = r6.h;
        r2 = com.tencent.android.tpush.common.p.a(r1);
        if (r2 != 0) goto L_0x002a;
    L_0x00d2:
        r2 = "action_type";
        r3 = r6.a;
        r0.putExtra(r2, r3);
        r2 = "packageDownloadUrl";
        r3 = r6.j;
        r0.putExtra(r2, r3);
        r2 = "packageName";
        r0.putExtra(r2, r1);
        r2 = "activity";
        r0.putExtra(r2, r1);
        r1 = "notificationActionType";
        r2 = 4;
        r0.putExtra(r1, r2);
        r1 = r5.getApplicationContext();
        r2 = com.tencent.android.tpush.XGPushActivity.class;
        r0.setClass(r1, r2);
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.b.b.a(android.content.Context, com.tencent.android.tpush.b.d, boolean):android.content.Intent");
    }

    @d(a = 1, b = 3, c = "20150316", e = {com.d.a.RECEIVERCHECK, com.d.a.INTENTCHECK}, f = "???????????????????????????")
    public static void a(Context context, h hVar) {
        int identifier;
        boolean z;
        Integer num = null;
        c cVar = (c) hVar.g();
        d l = cVar.l();
        XGPushNotificationBuilder a = a(context, cVar.g());
        if (a == null || cVar.s() == 1) {
            if (a == null) {
                a = XGPushManager.getDefaultNotificationBuilder(context);
            }
            if (a == null) {
                a = a(context);
            }
            if (cVar.j() != 0) {
                a.setFlags(16);
            } else {
                a.setFlags(32);
            }
            if (cVar.h() == 0) {
                a.setSound(null);
            } else if (TextUtils.isEmpty(cVar.o())) {
                a.setDefaults(1);
            } else {
                identifier = context.getResources().getIdentifier(cVar.o(), "raw", context.getPackageName());
                if (identifier > 0) {
                    a.setSound(Uri.parse("android.resource://" + context.getPackageName() + dji.pilot.usercenter.protocol.d.t + identifier));
                } else {
                    a.setDefaults(1);
                }
            }
            if (cVar.i() != 0) {
                a.setDefaults(2);
            } else {
                a.setVibrate(null);
            }
            if (cVar.n() != 0) {
                a.setFlags(1);
            }
            Object q = cVar.q();
            if (q == null || TextUtils.isEmpty(q)) {
                a.setIcon(Integer.valueOf(context.getApplicationInfo().icon));
            } else {
                identifier = context.getResources().getIdentifier(q, "drawable", context.getPackageName());
                if (identifier > 0) {
                    a.setIcon(Integer.valueOf(identifier));
                } else {
                    a.setIcon(Integer.valueOf(context.getApplicationInfo().icon));
                }
            }
            identifier = cVar.r();
            String p = cVar.p();
            if (a instanceof XGCustomPushNotificationBuilder) {
                num = ((XGCustomPushNotificationBuilder) a).getLayoutIconId();
            }
            if (p == null || TextUtils.isEmpty(p)) {
                a.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
            } else if (identifier <= 0) {
                identifier = context.getResources().getIdentifier(p, "drawable", context.getPackageName());
                if (identifier > 0) {
                    a.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), identifier));
                    if (num != null) {
                        ((XGCustomPushNotificationBuilder) a).setLayoutIconDrawableId(identifier);
                    }
                } else {
                    a.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
                }
            } else {
                a(p, a, context, num);
            }
        }
        if (cVar.m() > 0) {
            a.setIcon(Integer.valueOf(cVar.m()));
        }
        if (a.getSmallIcon() == null && a.getLargeIcon() == null && a.getIcon() == null) {
            a.setIcon(Integer.valueOf(context.getApplicationInfo().icon));
            a.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
        }
        a.setTitle(cVar.d());
        a.setTickerText(cVar.e());
        String f = cVar.f();
        if (p.a(f) || "{}".equalsIgnoreCase(f)) {
            z = false;
        } else {
            z = true;
        }
        Intent a2 = a(context, l, z);
        if (a2 == null) {
            a.h("MessageHelper", "intent is null");
            return;
        }
        if (z) {
            a2.putExtra("custom_content", cVar.f());
        }
        a2.putExtra(Constants.TAG_TPUSH_MESSAGE, "true");
        a2.putExtra("title", Rijndael.encrypt(cVar.d()));
        a2.putExtra("content", Rijndael.encrypt(cVar.e()));
        if (cVar.f() != null) {
            a2.putExtra("custom_content", Rijndael.encrypt(cVar.f()));
        }
        a2.putExtra(MessageKey.MSG_ID, hVar.b());
        a2.putExtra("accId", hVar.c());
        a2.putExtra(MessageKey.MSG_BUSI_MSG_ID, hVar.d());
        a2.putExtra(MessageKey.MSG_CREATE_TIMESTAMPS, hVar.e());
        a2.putExtra(MessageKey.MSG_PORTECT_TAG, Rijndael.encrypt("" + (System.currentTimeMillis() - 1000)));
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int k = cVar.k();
        if (k <= 0) {
            identifier = b(context, cVar.g());
        } else {
            identifier = k;
        }
        if (identifier == -1) {
            notificationManager.cancelAll();
        }
        a2.putExtra(MessageKey.NOTIFACTION_ID, identifier);
        k = 134217728;
        if (l.c != null && l.c.b > 0) {
            k = l.c.b;
        }
        Intent intent = new Intent("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT");
        intent.putExtra(Constants.FLAG_PACK_NAME, context.getPackageName());
        intent.putExtra("action", 2);
        intent.putExtras(a2);
        a.setContentIntent(PendingIntent.getActivity(context.getApplicationContext(), identifier, a2, k));
        Notification buildNotification = a.buildNotification(context);
        buildNotification.deleteIntent = PendingIntent.getBroadcast(context.getApplicationContext(), identifier, intent, k);
        XGPushNotifactionCallback notifactionCallback = XGPushManager.getNotifactionCallback();
        if (notifactionCallback == null) {
            notificationManager.notify(identifier, buildNotification);
        } else {
            a.d("MessageHelper", "call notifactionCallback:" + buildNotification);
            notifactionCallback.handleNotify(new XGNotifaction(context, identifier, buildNotification, cVar));
        }
        Intent intent2 = new Intent(Constants.ACTION_FEEDBACK);
        intent2.putExtra(Constants.FEEDBACK_ERROR_CODE, 0);
        intent2.setPackage(context.getPackageName());
        intent2.putExtras(a2);
        intent2.putExtra(Constants.FEEDBACK_TAG, 5);
        intent2.putExtra(MessageKey.NOTIFACTION_ID, identifier);
        context.sendBroadcast(intent2);
    }

    private static void a(String str, XGPushNotificationBuilder xGPushNotificationBuilder, Context context, Integer num) {
        HttpGet httpGet;
        InputStream content;
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        InputStream inputStream;
        HttpGet httpGet2;
        Throwable th;
        HttpEntity httpEntity;
        HttpEntity httpEntity2 = null;
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setSoTimeout(basicHttpParams, CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, CommonStatusCodes.AUTH_API_INVALID_CREDENTIALS);
        HttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 4000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 4000);
        ConnManagerParams.setTimeout(basicHttpParams, 4000);
        InputStream inputStream2 = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        HttpEntity httpEntity3 = null;
        try {
            URL url = new URL(str);
            httpGet = new HttpGet(url.toURI());
            try {
                httpGet.addHeader("X-Online-Host", url.getHost());
                defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(20000));
                defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(20000));
                HttpResponse execute = defaultHttpClient.execute(httpGet);
                if (execute.getStatusLine().getStatusCode() != 200) {
                    xGPushNotificationBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), context.getApplicationInfo().icon));
                    if (null != null) {
                        try {
                            httpEntity3.consumeContent();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    if (null != null) {
                        inputStream2.close();
                    }
                    if (null != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (httpGet != null) {
                        httpGet.abort();
                    }
                    if (defaultHttpClient != null) {
                        defaultHttpClient.getConnectionManager().shutdown();
                        return;
                    }
                    return;
                }
                HttpEntity entity = execute.getEntity();
                if (entity != null) {
                    try {
                        content = entity.getContent();
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = content.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    byteArrayOutputStream.write(bArr, 0, read);
                                }
                                Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
                                xGPushNotificationBuilder.setLargeIcon(decodeByteArray);
                                if (num != null) {
                                    ((XGCustomPushNotificationBuilder) xGPushNotificationBuilder).setLayoutIconDrawableBmp(decodeByteArray);
                                }
                            } catch (Exception e3) {
                                e = e3;
                                httpEntity2 = entity;
                                byteArrayOutputStream2 = byteArrayOutputStream;
                                inputStream = content;
                                httpGet2 = httpGet;
                                try {
                                    e.printStackTrace();
                                    if (httpEntity2 != null) {
                                        try {
                                            httpEntity2.consumeContent();
                                        } catch (IOException e22) {
                                            e22.printStackTrace();
                                            return;
                                        }
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (byteArrayOutputStream2 != null) {
                                        byteArrayOutputStream2.close();
                                    }
                                    if (httpGet2 != null) {
                                        httpGet2.abort();
                                    }
                                    if (defaultHttpClient != null) {
                                        defaultHttpClient.getConnectionManager().shutdown();
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    httpGet = httpGet2;
                                    content = inputStream;
                                    byteArrayOutputStream = byteArrayOutputStream2;
                                    if (httpEntity2 != null) {
                                        try {
                                            httpEntity2.consumeContent();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (content != null) {
                                        content.close();
                                    }
                                    if (byteArrayOutputStream != null) {
                                        byteArrayOutputStream.close();
                                    }
                                    if (httpGet != null) {
                                        httpGet.abort();
                                    }
                                    if (defaultHttpClient != null) {
                                        defaultHttpClient.getConnectionManager().shutdown();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                httpEntity2 = entity;
                                if (httpEntity2 != null) {
                                    httpEntity2.consumeContent();
                                }
                                if (content != null) {
                                    content.close();
                                }
                                if (byteArrayOutputStream != null) {
                                    byteArrayOutputStream.close();
                                }
                                if (httpGet != null) {
                                    httpGet.abort();
                                }
                                if (defaultHttpClient != null) {
                                    defaultHttpClient.getConnectionManager().shutdown();
                                }
                                throw th;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            inputStream = content;
                            httpGet2 = httpGet;
                            httpEntity = entity;
                            byteArrayOutputStream2 = null;
                            httpEntity2 = httpEntity;
                            e.printStackTrace();
                            if (httpEntity2 != null) {
                                httpEntity2.consumeContent();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (byteArrayOutputStream2 != null) {
                                byteArrayOutputStream2.close();
                            }
                            if (httpGet2 != null) {
                                httpGet2.abort();
                            }
                            if (defaultHttpClient != null) {
                                defaultHttpClient.getConnectionManager().shutdown();
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            byteArrayOutputStream = null;
                            httpEntity2 = entity;
                            if (httpEntity2 != null) {
                                httpEntity2.consumeContent();
                            }
                            if (content != null) {
                                content.close();
                            }
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            if (httpGet != null) {
                                httpGet.abort();
                            }
                            if (defaultHttpClient != null) {
                                defaultHttpClient.getConnectionManager().shutdown();
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        inputStream = null;
                        httpGet2 = httpGet;
                        httpEntity = entity;
                        byteArrayOutputStream2 = null;
                        httpEntity2 = httpEntity;
                        e.printStackTrace();
                        if (httpEntity2 != null) {
                            httpEntity2.consumeContent();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        if (httpGet2 != null) {
                            httpGet2.abort();
                        }
                        if (defaultHttpClient != null) {
                            defaultHttpClient.getConnectionManager().shutdown();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        byteArrayOutputStream = null;
                        content = null;
                        httpEntity2 = entity;
                        if (httpEntity2 != null) {
                            httpEntity2.consumeContent();
                        }
                        if (content != null) {
                            content.close();
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        if (httpGet != null) {
                            httpGet.abort();
                        }
                        if (defaultHttpClient != null) {
                            defaultHttpClient.getConnectionManager().shutdown();
                        }
                        throw th;
                    }
                }
                byteArrayOutputStream = null;
                content = null;
                if (entity != null) {
                    try {
                        entity.consumeContent();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                        return;
                    }
                }
                if (content != null) {
                    content.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (httpGet != null) {
                    httpGet.abort();
                }
                if (defaultHttpClient != null) {
                    defaultHttpClient.getConnectionManager().shutdown();
                }
            } catch (Exception e7) {
                e = e7;
                byteArrayOutputStream2 = null;
                inputStream = null;
                httpGet2 = httpGet;
                e.printStackTrace();
                if (httpEntity2 != null) {
                    httpEntity2.consumeContent();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (httpGet2 != null) {
                    httpGet2.abort();
                }
                if (defaultHttpClient != null) {
                    defaultHttpClient.getConnectionManager().shutdown();
                }
            } catch (Throwable th6) {
                th = th6;
                byteArrayOutputStream = null;
                content = null;
                if (httpEntity2 != null) {
                    httpEntity2.consumeContent();
                }
                if (content != null) {
                    content.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (httpGet != null) {
                    httpGet.abort();
                }
                if (defaultHttpClient != null) {
                    defaultHttpClient.getConnectionManager().shutdown();
                }
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            byteArrayOutputStream2 = null;
            inputStream = null;
            httpGet2 = null;
            e.printStackTrace();
            if (httpEntity2 != null) {
                httpEntity2.consumeContent();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            if (httpGet2 != null) {
                httpGet2.abort();
            }
            if (defaultHttpClient != null) {
                defaultHttpClient.getConnectionManager().shutdown();
            }
        } catch (Throwable th7) {
            th = th7;
            byteArrayOutputStream = null;
            content = null;
            httpGet = null;
            if (httpEntity2 != null) {
                httpEntity2.consumeContent();
            }
            if (content != null) {
                content.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (httpGet != null) {
                httpGet.abort();
            }
            if (defaultHttpClient != null) {
                defaultHttpClient.getConnectionManager().shutdown();
            }
            throw th;
        }
    }

    public static void b(Context context, h hVar) {
        if (hVar.g() instanceof c) {
            if (XGPushConfig.enableDebug) {
                a.e("MessageHelper", "Action -> showNotification " + hVar.f());
            }
            c cVar = (c) hVar.g();
            if (cVar == null || cVar.l() == null) {
                a.h("MessageHelper", "showNotification holder == null || holder.getAction() == null");
            } else {
                a(context, hVar);
            }
        }
    }

    private static synchronized int b(Context context, int i) {
        int a;
        Throwable th;
        synchronized (b.class) {
            try {
                String str = "_XINGE_NOTIF_NUMBER_" + String.valueOf(i);
                a = m.a(context, str, 0);
                if (a >= 2147483646) {
                    a = 0;
                }
                try {
                    m.b(context, str, a + 1);
                } catch (Throwable th2) {
                    th = th2;
                    a.c("MessageHelper", "", th);
                    return a;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                a = 0;
                th = th4;
                a.c("MessageHelper", "", th);
                return a;
            }
        }
        return a;
    }
}
