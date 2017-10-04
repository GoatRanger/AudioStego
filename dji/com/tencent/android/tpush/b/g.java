package com.tencent.android.tpush.b;

import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.service.d.e;

class g implements Runnable {
    final /* synthetic */ f a;
    private final String b = g.class.getSimpleName();
    private Context c;
    private Intent d;
    private XGIOperateCallback e;

    public g(f fVar, Context context, Intent intent, XGIOperateCallback xGIOperateCallback) {
        this.a = fVar;
        this.c = context;
        this.d = intent;
        this.e = xGIOperateCallback;
    }

    private void a() {
        Intent intent = new Intent(Constants.ACTION_PUSH_MESSAGE);
        intent.setPackage(this.c.getPackageName());
        intent.putExtras(this.d);
        this.c.sendBroadcast(intent);
        String stringExtra = this.d.getStringExtra(MessageKey.MSG_SERVICE_PACKAGE_NAME);
        if (!e.a(stringExtra)) {
            Intent intent2 = new Intent("com.tencent.android.tpush.action.ack.sdk2srv");
            intent2.setPackage(stringExtra);
            intent2.putExtras(this.d);
            this.c.sendBroadcast(intent2);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12 = this;
        r1 = r12.a;
        monitor-enter(r1);
        r0 = com.tencent.android.tpush.XGPushConfig.enableDebug;	 Catch:{ all -> 0x0085 }
        if (r0 == 0) goto L_0x000e;
    L_0x0007:
        r0 = r12.b;	 Catch:{ all -> 0x0085 }
        r2 = "Action -> handlerPushMessage";
        com.tencent.android.tpush.a.a.c(r0, r2);	 Catch:{ all -> 0x0085 }
    L_0x000e:
        r0 = 0;
        r2 = r12.d;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = "expire_time";
        r4 = 0;
        r2 = r2.getLongExtra(r3, r4);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r12.d;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r4.getPackage();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r5 = r12.d;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = "msgId";
        r10 = -1;
        r8 = r5.getLongExtra(r8, r10);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r5 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r10 = r12.d;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r5 = com.tencent.android.tpush.b.h.a(r5, r10);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r10 = 0;
        r10 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
        if (r10 <= 0) goto L_0x0074;
    L_0x003b:
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0074;
    L_0x003f:
        r0 = "PushMessageHandler";
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = "msg is expired, currentTimeMillis=";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.append(r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = "current=";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.append(r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = ".";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r12.d;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.a.a.h(r0, r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r0 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.XGPushManager.msgAck(r0, r5);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        monitor-exit(r1);	 Catch:{ all -> 0x0085 }
    L_0x0073:
        return;
    L_0x0074:
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = com.tencent.android.tpush.b.f.a(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r2 != 0) goto L_0x0088;
    L_0x007e:
        r0 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.XGPushManager.msgAck(r0, r5);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        monitor-exit(r1);	 Catch:{ all -> 0x0085 }
        goto L_0x0073;
    L_0x0085:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0085 }
        throw r0;
    L_0x0088:
        r2 = 2;
        com.tencent.android.tpush.a.a.a(r2, r8);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = "@";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.append(r8);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = "@";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r12.d;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = "accId";
        r6 = -1;
        r6 = r3.getLongExtra(r4, r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = com.tencent.android.tpush.XGPushConfig.getAccessidList(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r3 == 0) goto L_0x00ff;
    L_0x00bb:
        r4 = r3.size();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r4 <= 0) goto L_0x00ff;
    L_0x00c1:
        r4 = java.lang.Long.valueOf(r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r3.contains(r4);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r4 != 0) goto L_0x00ff;
    L_0x00cb:
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r0.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = "PushMessageRunnable match accessId failed, message droped cause accessId:";
        r0 = r0.append(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r0 = r0.append(r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = " not in ";
        r0 = r0.append(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = " msgId = ";
        r0 = r0.append(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r0 = r0.append(r8);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r12.b;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.a.a.i(r2, r0);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r0 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.XGPushManager.msgAck(r0, r5);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        monitor-exit(r1);	 Catch:{ all -> 0x0085 }
        goto L_0x0073;
    L_0x00ff:
        r3 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = com.tencent.android.tpush.service.d.e.a(r3, r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r3.contains(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r4 != 0) goto L_0x0207;
    L_0x010b:
        r4 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = "tpush_msgId_";
        r8 = r8.append(r9);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = r8.append(r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = r8.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = r9.append(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = r9.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = r9.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.common.m.b(r4, r8, r9);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = "tpush_msgId_";
        r8 = r8.append(r9);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = r8.append(r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = r8.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = r9.append(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r9.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r9 = 1;
        com.tencent.android.tpush.service.d.e.a(r4, r8, r3, r9);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r8 = "tpush_msgId_";
        r4 = r4.append(r8);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r4.append(r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r6 = "";
        r3 = com.tencent.android.tpush.common.m.a(r3, r4, r6);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r3.contains(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r3 != 0) goto L_0x019a;
    L_0x017f:
        r0 = r12.b;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r3.append(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = " flag write failed";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.a.a.h(r0, r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        monitor-exit(r1);	 Catch:{ all -> 0x0085 }
        goto L_0x0073;
    L_0x019a:
        r2 = com.tencent.android.tpush.XGPushConfig.enableDebug;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r2 == 0) goto L_0x01ba;
    L_0x019e:
        r2 = r12.b;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3.<init>();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = "Receiver msg from server :";
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r4 = r5.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.a.a.e(r2, r3);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
    L_0x01ba:
        r12.a();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r5.g();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r2 == 0) goto L_0x01e0;
    L_0x01c3:
        r2 = r5.g();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = r2.b();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = 1;
        if (r2 != r3) goto L_0x01e0;
    L_0x01ce:
        r2 = r5.f();	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r2 = com.tencent.android.tpush.service.d.e.a(r2);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        if (r2 != 0) goto L_0x01e0;
    L_0x01d8:
        r5.a();	 Catch:{ Throwable -> 0x01f5, JSONException -> 0x01fe, IllegalArgumentException -> 0x020b }
    L_0x01db:
        r2 = r12.c;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        com.tencent.android.tpush.XGPushManager.msgAck(r2, r5);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
    L_0x01e0:
        r2 = r12.e;	 Catch:{ all -> 0x0085 }
        if (r2 == 0) goto L_0x01f2;
    L_0x01e4:
        if (r0 == 0) goto L_0x021d;
    L_0x01e6:
        r2 = r12.e;	 Catch:{ all -> 0x0085 }
        r3 = "";
        r4 = -1;
        r0 = r0.toString();	 Catch:{ all -> 0x0085 }
        r2.onFail(r3, r4, r0);	 Catch:{ all -> 0x0085 }
    L_0x01f2:
        monitor-exit(r1);	 Catch:{ all -> 0x0085 }
        goto L_0x0073;
    L_0x01f5:
        r0 = move-exception;
        r2 = r12.b;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        r3 = "unknown error";
        com.tencent.android.tpush.a.a.c(r2, r3, r0);	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        goto L_0x01db;
    L_0x01fe:
        r0 = move-exception;
        r2 = r12.b;	 Catch:{ all -> 0x0085 }
        r3 = "push parse error";
        com.tencent.android.tpush.a.a.c(r2, r3, r0);	 Catch:{ all -> 0x0085 }
        goto L_0x01e0;
    L_0x0207:
        r2 = 0;
        r12.e = r2;	 Catch:{ JSONException -> 0x01fe, IllegalArgumentException -> 0x020b, Throwable -> 0x0214 }
        goto L_0x01e0;
    L_0x020b:
        r0 = move-exception;
        r2 = "XGService";
        r3 = "push msg type error";
        com.tencent.android.tpush.a.a.c(r2, r3, r0);	 Catch:{ all -> 0x0085 }
        goto L_0x01e0;
    L_0x0214:
        r0 = move-exception;
        r2 = r12.b;	 Catch:{ all -> 0x0085 }
        r3 = "unknown error";
        com.tencent.android.tpush.a.a.c(r2, r3, r0);	 Catch:{ all -> 0x0085 }
        goto L_0x01e0;
    L_0x021d:
        r0 = r12.e;	 Catch:{ all -> 0x0085 }
        r2 = "";
        r3 = 0;
        r0.onSuccess(r2, r3);	 Catch:{ all -> 0x0085 }
        goto L_0x01f2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.android.tpush.b.g.run():void");
    }
}
