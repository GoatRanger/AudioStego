package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.Intent;
import com.d.a;
import com.d.c;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.service.d.e;
import java.util.ArrayList;

@c(a = 1, b = 3, c = "20150316", e = {a.INTENTCHECK}, f = "确认已进行安全校验")
class j implements Runnable {
    final /* synthetic */ a a;
    private Context b = null;
    private Intent c = null;

    public j(a aVar, Context context, Intent intent) {
        this.a = aVar;
        this.b = context;
        this.c = intent;
    }

    public void run() {
        try {
            String action = this.c.getAction();
            if (action != null) {
                if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
                    this.a.a(this.b, this.c);
                    XGWatchdog.getInstance(this.b).sendAllLocalXGAppList();
                } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                    this.a.b(this.b, this.c);
                    XGWatchdog.getInstance(this.b).sendAllLocalXGAppList();
                } else if ("com.tencent.android.tpush.action.REGISTER".equals(action)) {
                    this.a.c(this.b, this.c);
                } else if ("com.tencent.android.tpush.action.UNREGISTER".equals(action)) {
                    this.a.e(this.b, this.c);
                } else if ("com.tencent.android.tpush.action.ENABLE_DEBUG".equals(action)) {
                    this.a.f(this.b, this.c);
                } else if ("com.tencent.android.tpush.action.MSG_ACK".equals(action)) {
                    com.tencent.android.tpush.service.b.a.a().a(this.b, this.c);
                } else if ("com.tencent.android.tpush.action.TAG".equals(action)) {
                    this.a.d(this.b, this.c);
                } else if ("com.tencent.android.tpush.action.PUSH_CLICK.RESULT".equals(action)) {
                    com.tencent.android.tpush.service.b.a.a().b(this.b, this.c);
                } else if ("com.tencent.android.tpush.action.PUSH_CANCELLED.RESULT".equals(action)) {
                    com.tencent.android.tpush.service.b.a.a().b(this.b, this.c);
                    action = this.c.getStringExtra(Constants.FLAG_PACK_NAME);
                    if (!e.a(action)) {
                        Intent intent = new Intent(Constants.ACTION_FEEDBACK);
                        intent.setPackage(action);
                        intent.putExtras(this.c);
                        intent.putExtra(Constants.FEEDBACK_TAG, 4);
                        this.b.sendBroadcast(intent);
                    }
                } else if ("com.tencent.android.tpush.action.CUSTOM_NOTIFICATION".equals(action)) {
                    TpnsPushMsg tpnsPushMsg = new TpnsPushMsg();
                    tpnsPushMsg.msgId = this.c.getLongExtra(MessageKey.MSG_ID, -System.currentTimeMillis());
                    tpnsPushMsg.accessId = this.c.getLongExtra("accessId", -1);
                    tpnsPushMsg.appPkgName = this.c.getStringExtra("appPkgName");
                    tpnsPushMsg.busiMsgId = 0;
                    tpnsPushMsg.timestamp = System.currentTimeMillis();
                    tpnsPushMsg.type = (long) this.c.getIntExtra("type", 1);
                    tpnsPushMsg.multiPkg = 0;
                    tpnsPushMsg.date = this.c.getStringExtra("date");
                    tpnsPushMsg.content = "{\"title\":\"" + this.c.getStringExtra("title") + "\",\"content\":\"" + this.c.getStringExtra("content") + "\",\"builder_id\":" + this.c.getLongExtra("builderId", 0) + ",\"custom_content\":" + this.c.getStringExtra("custom_content") + ",\"ring\":" + this.c.getIntExtra(MessageKey.MSG_RING, 1) + ",\"vibrate\":" + this.c.getIntExtra(MessageKey.MSG_VIBRATE, 1) + ",\"lights\":" + this.c.getIntExtra(MessageKey.MSG_LIGHTS, 1) + ",\"ring_raw\":\"" + this.c.getStringExtra(MessageKey.MSG_RING_RAW) + "\",\"icon_type\":" + this.c.getIntExtra(MessageKey.MSG_ICON_TYPE, 0) + ",\"icon_res\":\"" + this.c.getStringExtra(MessageKey.MSG_ICON_RES) + "\",\"style_id\":" + this.c.getIntExtra(MessageKey.MSG_STYLE_ID, 1) + ",\"small_icon\":\"" + this.c.getStringExtra(MessageKey.MSG_SMALL_ICON) + "\",\"clearable\":1,\"accept_time\":[{\"start\":{\"hour\":\"" + this.c.getStringExtra(MessageKey.MSG_ACCEPT_TIME_HOUR) + "\",\"min\":\"" + this.c.getStringExtra(MessageKey.MSG_ACCEPT_TIME_MIN) + "\"},\"end\":{\"hour\":\"23\",\"min\":\"59\"}}],\"action\":{\"action_type\":" + this.c.getIntExtra("action_type", 1) + ",\"activity\":\"" + this.c.getStringExtra(Constants.FLAG_ACTIVITY_NAME) + "\",\"browser\":{\"url\":\"" + this.c.getStringExtra("url") + "\"},\"intent\":\"" + this.c.getStringExtra("intent") + "\",\"package_name\":{\"packageDownloadUrl\":\"" + this.c.getStringExtra(Constants.FLAG_PACKAGE_DOWNLOAD_URL) + "\",\"packageName\":\"" + this.c.getStringExtra(Constants.FLAG_PACKAGE_NAME) + "\"}}}";
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(tpnsPushMsg);
                    com.tencent.android.tpush.service.b.a.a().b(arrayList, this.c.getLongExtra("timeUs", System.currentTimeMillis()), new com.tencent.android.tpush.service.channel.a(Integer.valueOf(0), "127.0.0.1"));
                } else if ("com.tencent.android.tpush.action.ack.sdk2srv".equals(action)) {
                    com.tencent.android.tpush.service.c.a.a(this.c);
                }
            }
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c(a.a, a.a + " run error.", th);
        }
    }
}
