package com.tencent.android.tpush;

import android.app.Notification;
import android.content.Context;
import org.json.JSONObject;

public class XGBasicPushNotificationBuilder extends XGPushNotificationBuilder {
    public Notification buildNotification(Context context) {
        return a(context);
    }

    protected void a(JSONObject jSONObject) {
    }

    protected void b(JSONObject jSONObject) {
    }

    public String getType() {
        return XGPushNotificationBuilder.BASIC_NOTIFICATION_BUILDER_TYPE;
    }
}
