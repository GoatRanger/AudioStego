package com.tencent.android.tpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import com.d.a;
import com.d.c;
import dji.pilot.usercenter.protocol.d;

@c(a = 1, b = 3, c = "20150316", e = {a.RECEIVERCHECK, a.INTENTCHECK}, f = "确认已进行安全校验")
public class XGNotifaction {
    private int a = 0;
    private Notification b = null;
    private String c = null;
    private String d = null;
    private String e = null;
    private Context f = null;

    public XGNotifaction(Context context, int i, Notification notification, com.tencent.android.tpush.b.c cVar) {
        this.f = context.getApplicationContext();
        this.a = i;
        this.b = notification;
        this.c = cVar.d();
        this.d = cVar.e();
        this.e = cVar.f();
    }

    public void setNotifyId(int i) {
        this.a = i;
    }

    public String toString() {
        return "XGNotifaction [notifyId=" + this.a + ", title=" + this.c + ", content=" + this.d + ", customContent=" + this.e + d.H;
    }

    public boolean doNotify() {
        if (!(this.b == null || this.f == null)) {
            NotificationManager notificationManager = (NotificationManager) this.f.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.notify(this.a, this.b);
                return true;
            }
        }
        return false;
    }

    public int getNotifyId() {
        return this.a;
    }

    public Notification getNotifaction() {
        return this.b;
    }

    public String getTitle() {
        return this.c;
    }

    public String getContent() {
        return this.d;
    }

    public String getCustomContent() {
        return this.e;
    }
}
