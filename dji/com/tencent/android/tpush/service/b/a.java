package com.tencent.android.tpush.service.b;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.d.c;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.android.tpush.common.g;
import com.tencent.android.tpush.common.k;
import com.tencent.android.tpush.data.CachedMessageIntent;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.data.PushClickEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.b;
import com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.service.d.e;
import com.tencent.android.tpush.service.l;
import com.tencent.android.tpush.service.o;
import com.tencent.android.tpush.service.t;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@c(a = 1, b = 3, c = "20150316", e = {com.d.a.INTENTSCHEMECHECK, com.d.a.INTENTCHECK, com.d.a.RECEIVERCHECK}, f = "确认已进行安全校验")
public class a {
    public static long a = 306000;
    private static a b = new a();
    private static final byte[] c = new byte[0];
    private static long d = 0;
    private static volatile boolean e = false;
    private static volatile boolean f = false;
    private static volatile boolean g = false;
    private PendingIntent h = null;

    private a() {
    }

    public static a a() {
        return b;
    }

    private void a(Context context, Long l) {
        long currentTimeMillis = System.currentTimeMillis();
        List b = b(context);
        if (e) {
            com.tencent.android.tpush.a.a.c("MessageManager", ">> msg ack is uploading , this time will give up! MessageId = " + l);
            return;
        }
        ArrayList b2 = b(context, b);
        if (b2 == null || b2.size() <= 0) {
            com.tencent.android.tpush.a.a.c("MessageManager", "Null report list with msgId " + l);
        } else {
            e = true;
            d = currentTimeMillis;
        }
        com.tencent.android.tpush.a.a.a(5, b2);
        o.a().a(b2, new b(this, b2, context));
    }

    public synchronized void a(Context context, Intent intent) {
        if (!(context == null || intent == null)) {
            long longExtra = intent.getLongExtra(MessageKey.MSG_ID, -1);
            String stringExtra = intent.getStringExtra(Constants.FLAG_PACK_NAME);
            com.tencent.android.tpush.a.a.a(4, longExtra);
            b(context, stringExtra, longExtra);
            a(context, stringExtra, longExtra, (short) 1);
            a(context, Long.valueOf(longExtra));
        }
    }

    public void b(Context context, Intent intent) {
        if (context != null && intent != null) {
            String stringExtra = intent.getStringExtra(Constants.FLAG_PACK_NAME);
            Context context2 = context;
            a(context2, stringExtra, new PushClickEntity(intent.getLongExtra(MessageKey.MSG_ID, -1), intent.getLongExtra("accId", -1), intent.getLongExtra(MessageKey.MSG_BUSI_MSG_ID, -1), intent.getLongExtra(MessageKey.MSG_CREATE_TIMESTAMPS, -1), stringExtra, 1, intent.getLongExtra(Constants.FLAG_CLICK_TIME, System.currentTimeMillis() / 1000), intent.getIntExtra("action", 0)));
            c(context, intent);
        }
    }

    public void a(Context context, TpnsPushMsg tpnsPushMsg, long j, com.tencent.android.tpush.service.channel.a aVar) {
        long currentTimeMillis = System.currentTimeMillis();
        MessageId messageId = new MessageId();
        messageId.id = tpnsPushMsg.msgId;
        messageId.accessId = tpnsPushMsg.accessId;
        messageId.host = e.b(aVar.d());
        messageId.port = aVar.e();
        messageId.pact = o.a(aVar.b());
        messageId.apn = e.e(l.e());
        messageId.isp = e.f(l.e());
        messageId.pushTime = j;
        messageId.serviceHost = l.e().getPackageName();
        messageId.receivedTime = currentTimeMillis;
        messageId.pkgName = tpnsPushMsg.appPkgName;
        messageId.busiMsgId = tpnsPushMsg.busiMsgId;
        messageId.timestamp = tpnsPushMsg.timestamp;
        messageId.msgType = tpnsPushMsg.type;
        messageId.multiPkg = tpnsPushMsg.multiPkg;
        messageId.date = tpnsPushMsg.date;
        a(context, tpnsPushMsg.appPkgName, messageId);
        b(context, messageId);
    }

    private synchronized void b(Context context, MessageId messageId) {
        List a = a(context, messageId);
        if (f) {
            Object obj;
            String str = "MessageManager";
            StringBuilder append = new StringBuilder().append("requestServiceAck ack is uploading , this time will give up!  msgId =  ");
            if (messageId == null) {
                obj = null;
            } else {
                obj = Long.valueOf(messageId.id);
            }
            com.tencent.android.tpush.a.a.c(str, append.append(obj).toString());
        } else {
            ArrayList a2 = a(context, a);
            if (a2 == null || a2.size() == 0) {
                com.tencent.android.tpush.a.a.c("MessageManager", "requestServiceAck with null list , give up this time");
            } else {
                f = true;
                o.a().a(a2, new c(this, messageId, context));
            }
        }
    }

    public void a(Context context, ArrayList arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                if (arrayList.size() > 0) {
                    try {
                        ArrayList d = d(context, "all");
                        if (d != null && d.size() > 0) {
                            HashMap hashMap = new HashMap();
                            Iterator it = d.iterator();
                            while (it.hasNext()) {
                                ArrayList arrayList2;
                                Object obj;
                                MessageId messageId = (MessageId) it.next();
                                ArrayList arrayList3 = (ArrayList) hashMap.get(messageId.pkgName);
                                if (arrayList3 == null) {
                                    arrayList3 = new ArrayList();
                                    hashMap.put(messageId.pkgName, arrayList3);
                                    arrayList2 = arrayList3;
                                } else {
                                    arrayList2 = arrayList3;
                                }
                                for (int i = 0; i < arrayList.size(); i++) {
                                    if (messageId.id == ((TpnsPushClientReport) arrayList.get(i)).msgId) {
                                        arrayList.remove(i);
                                        obj = null;
                                        break;
                                    }
                                }
                                int i2 = 1;
                                if (obj != null) {
                                    arrayList2.add(messageId);
                                    hashMap.put(messageId.pkgName, arrayList2);
                                }
                            }
                            for (String str : hashMap.keySet()) {
                                a(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Throwable e) {
                        com.tencent.android.tpush.a.a.c("MessageManager", "+++ clear msg id exception", e);
                    }
                }
            }
            com.tencent.android.tpush.a.a.h("MessageManager", "deleteServiceMsgIdBatch with null context or null list");
        }
    }

    public ArrayList a(Context context, List list) {
        ArrayList arrayList = null;
        if (list != null && list.size() > 0) {
            ArrayList arrayList2 = new ArrayList();
            for (MessageId messageId : list) {
                TpnsPushClientReport tpnsPushClientReport = new TpnsPushClientReport();
                tpnsPushClientReport.accessId = messageId.accessId;
                tpnsPushClientReport.msgId = messageId.id;
                tpnsPushClientReport.apn = messageId.apn;
                tpnsPushClientReport.isp = messageId.isp;
                tpnsPushClientReport.locip = messageId.host;
                tpnsPushClientReport.locport = messageId.port;
                tpnsPushClientReport.pack = messageId.pact;
                tpnsPushClientReport.timeUs = messageId.pushTime;
                tpnsPushClientReport.qua = CacheManager.getQua(context, tpnsPushClientReport.accessId);
                tpnsPushClientReport.serviceHost = messageId.serviceHost;
                tpnsPushClientReport.confirmMs = System.currentTimeMillis() - messageId.receivedTime;
                tpnsPushClientReport.broadcastId = messageId.busiMsgId;
                tpnsPushClientReport.timestamp = messageId.timestamp;
                tpnsPushClientReport.type = messageId.msgType;
                tpnsPushClientReport.ackType = (byte) 1;
                tpnsPushClientReport.receiveTime = messageId.receivedTime / 1000;
                if (XGPushConfig.enableDebug) {
                    com.tencent.android.tpush.a.a.c("MessageManager", "Ack to server : @msgId=" + tpnsPushClientReport.msgId + " @accId=" + tpnsPushClientReport.accessId + " @timeUs=" + tpnsPushClientReport.timeUs + " @confirmMs=" + tpnsPushClientReport.confirmMs + " @recTime=" + messageId.receivedTime + " @msgType=" + messageId.msgType + " @broadcastId=" + tpnsPushClientReport.broadcastId);
                }
                arrayList2.add(tpnsPushClientReport);
                if (arrayList2.size() > 30) {
                    return arrayList2;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public ArrayList a(Context context, MessageId messageId) {
        ArrayList arrayList;
        synchronized (c) {
            arrayList = null;
            if (context != null) {
                Object obj = null;
                List<String> registerInfos = CacheManager.getRegisterInfos(context);
                if (registerInfos != null && registerInfos.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (String str : registerInfos) {
                        Collection d = d(context, str);
                        if (messageId == null || str.equals(messageId.pkgName)) {
                            obj = 1;
                        }
                        if (d != null && d.size() > 0) {
                            arrayList2.addAll(d);
                        }
                    }
                    arrayList = arrayList2;
                }
                if (obj == null) {
                    try {
                        Collection d2 = d(context, messageId.pkgName);
                        if (d2 != null && d2.size() > 0) {
                            arrayList.retainAll(d2);
                            if (arrayList.size() > 0) {
                                arrayList.removeAll(arrayList);
                                arrayList.addAll(d2);
                            } else {
                                arrayList.addAll(d2);
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                a(context, "all", arrayList);
            }
        }
        return arrayList;
    }

    public void a(Context context, String str, MessageId messageId) {
        synchronized (c) {
            if (context != null) {
                if (!(e.a(str) || messageId == null)) {
                    ArrayList d = d(context, str);
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < d.size(); i++) {
                        MessageId messageId2 = (MessageId) d.get(i);
                        if (messageId2.id == messageId.id) {
                            arrayList.add(messageId2);
                        }
                    }
                    d.removeAll(arrayList);
                    d.add(messageId);
                    a(context, str, d);
                }
            }
        }
    }

    public void a(Context context, String str, ArrayList arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id.service", arrayList);
            }
        }
    }

    private ArrayList d(Context context, String str) {
        ArrayList arrayList;
        if (!(context == null || e.a(str))) {
            Object b = b(context, str, ".tpns.msg.id.service");
            if (b != null) {
                arrayList = (ArrayList) b;
                if (arrayList != null) {
                    return new ArrayList();
                }
                return arrayList;
            }
        }
        arrayList = null;
        if (arrayList != null) {
            return arrayList;
        }
        return new ArrayList();
    }

    public void b(Context context, ArrayList arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                if (arrayList.size() > 0) {
                    try {
                        ArrayList c = c(context);
                        if (c != null && c.size() > 0) {
                            HashMap hashMap = new HashMap();
                            Iterator it = c.iterator();
                            while (it.hasNext()) {
                                ArrayList arrayList2;
                                Object obj;
                                PushClickEntity pushClickEntity = (PushClickEntity) it.next();
                                ArrayList arrayList3 = (ArrayList) hashMap.get(pushClickEntity.pkgName);
                                if (arrayList3 == null) {
                                    arrayList3 = new ArrayList();
                                    hashMap.put(pushClickEntity.pkgName, arrayList3);
                                    arrayList2 = arrayList3;
                                } else {
                                    arrayList2 = arrayList3;
                                }
                                for (int i = 0; i < arrayList.size(); i++) {
                                    if (pushClickEntity.msgId == ((TpnsClickClientReport) arrayList.get(i)).msgId) {
                                        arrayList.remove(i);
                                        obj = null;
                                        break;
                                    }
                                }
                                int i2 = 1;
                                if (obj != null) {
                                    arrayList2.add(pushClickEntity);
                                    hashMap.put(pushClickEntity.pkgName, arrayList2);
                                }
                            }
                            for (String str : hashMap.keySet()) {
                                b(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Throwable e) {
                        com.tencent.android.tpush.a.a.c("MessageManager", "+++ clear msg id exception", e);
                    }
                }
            }
        }
    }

    public ArrayList a(Context context) {
        ArrayList arrayList = null;
        List<PushClickEntity> c = c(context);
        if (c != null && c.size() > 0) {
            ArrayList arrayList2 = new ArrayList();
            for (PushClickEntity pushClickEntity : c) {
                TpnsClickClientReport tpnsClickClientReport = new TpnsClickClientReport();
                tpnsClickClientReport.accessId = pushClickEntity.accessId;
                tpnsClickClientReport.msgId = pushClickEntity.msgId;
                tpnsClickClientReport.broadcastId = pushClickEntity.broadcastId;
                tpnsClickClientReport.timestamp = pushClickEntity.timestamp;
                tpnsClickClientReport.type = pushClickEntity.type;
                tpnsClickClientReport.clickTime = pushClickEntity.clickTime;
                tpnsClickClientReport.action = (long) pushClickEntity.action;
                arrayList2.add(tpnsClickClientReport);
                if (arrayList2.size() > 30) {
                    return arrayList2;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public void c(Context context, Intent intent) {
        if (!g) {
            ArrayList a = a(context);
            if (a == null || a.size() <= 0) {
                g = false;
                return;
            }
            g = true;
            o.a().b(a, new d(this, a, context, intent));
        }
    }

    public ArrayList b(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String f : registerInfos) {
            Collection f2 = f(context, f);
            if (f2 != null && f2.size() > 0) {
                arrayList.addAll(f2);
            }
        }
        return arrayList;
    }

    public ArrayList c(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String e : registerInfos) {
            Collection e2 = e(context, e);
            if (e2 != null && e2.size() > 0) {
                arrayList.addAll(e2);
            }
        }
        return arrayList;
    }

    private ArrayList e(Context context, String str) {
        ArrayList arrayList;
        if (!(context == null || e.a(str))) {
            Object b = b(context, str, ".tpns.msg.id.clicked");
            if (b != null) {
                arrayList = (ArrayList) b;
                if (arrayList != null) {
                    return new ArrayList();
                }
                return arrayList;
            }
        }
        arrayList = null;
        if (arrayList != null) {
            return arrayList;
        }
        return new ArrayList();
    }

    public void a(Context context, String str, PushClickEntity pushClickEntity) {
        synchronized (c) {
            if (context != null) {
                if (!(e.a(str) || pushClickEntity == null)) {
                    ArrayList e = e(context, str);
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < e.size(); i++) {
                        PushClickEntity pushClickEntity2 = (PushClickEntity) e.get(i);
                        if (pushClickEntity2.msgId == pushClickEntity.msgId) {
                            arrayList.add(pushClickEntity2);
                        }
                    }
                    e.removeAll(arrayList);
                    e.add(pushClickEntity);
                    b(context, str, e);
                }
            }
        }
    }

    public void b(Context context, String str, ArrayList arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id.clicked", arrayList);
            }
        }
    }

    public ArrayList b(Context context, List list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            ArrayList arrayList2 = new ArrayList();
            for (MessageId messageId : list) {
                TpnsPushClientReport tpnsPushClientReport = new TpnsPushClientReport();
                tpnsPushClientReport.accessId = messageId.accessId;
                tpnsPushClientReport.msgId = messageId.id;
                tpnsPushClientReport.apn = messageId.apn;
                tpnsPushClientReport.isp = messageId.isp;
                tpnsPushClientReport.locip = messageId.host;
                tpnsPushClientReport.locport = messageId.port;
                tpnsPushClientReport.pack = messageId.pact;
                tpnsPushClientReport.timeUs = messageId.pushTime;
                tpnsPushClientReport.qua = CacheManager.getQua(context, tpnsPushClientReport.accessId);
                tpnsPushClientReport.serviceHost = messageId.serviceHost;
                tpnsPushClientReport.confirmMs = System.currentTimeMillis() - messageId.receivedTime;
                tpnsPushClientReport.broadcastId = messageId.busiMsgId;
                tpnsPushClientReport.timestamp = messageId.timestamp;
                tpnsPushClientReport.type = messageId.msgType;
                tpnsPushClientReport.receiveTime = messageId.receivedTime / 1000;
                arrayList2.add(tpnsPushClientReport);
                if (arrayList2.size() > 30) {
                    return arrayList2;
                }
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    private ArrayList f(Context context, String str) {
        ArrayList arrayList;
        synchronized (c) {
            arrayList = null;
            if (context != null) {
                if (!e.a(str)) {
                    ArrayList a = a(context, str);
                    if (a != null && a.size() > 0) {
                        ArrayList arrayList2 = new ArrayList();
                        Iterator it = a.iterator();
                        while (it.hasNext()) {
                            MessageId messageId = (MessageId) it.next();
                            if (messageId.a()) {
                                arrayList2.add(messageId);
                            } else if (!d(context, str, messageId.id)) {
                                arrayList2.add(messageId);
                            }
                        }
                        arrayList = arrayList2;
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList a(Context context, String str) {
        ArrayList arrayList;
        if (!(context == null || e.a(str))) {
            Object b = b(context, str, ".tpns.msg.id");
            if (b != null) {
                arrayList = (ArrayList) b;
                if (arrayList != null) {
                    return new ArrayList();
                }
                return arrayList;
            }
        }
        arrayList = null;
        if (arrayList != null) {
            return arrayList;
        }
        return new ArrayList();
    }

    public void c(Context context, String str, ArrayList arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id", arrayList);
            }
        }
    }

    public void a(Context context, String str, long j, short s) {
        synchronized (c) {
            Object obj = null;
            if (context != null && j > 0) {
                ArrayList a = a(context, str);
                if (a == null || a.size() <= 0) {
                    com.tencent.android.tpush.a.a.a(12, j);
                } else {
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        Object obj2;
                        MessageId messageId = (MessageId) it.next();
                        if (messageId.id == j) {
                            messageId.isAck = s;
                            obj2 = 1;
                        } else {
                            obj2 = obj;
                        }
                        obj = obj2;
                    }
                    if (obj != null) {
                        c(context, str, a);
                    } else {
                        com.tencent.android.tpush.a.a.h("MessageManager", "updateMsgIdFlag Failed with no equal MessageId = " + j + " pkgName = " + str);
                        com.tencent.android.tpush.a.a.a(11, j);
                    }
                }
            }
        }
    }

    public void d(Context context) {
        if (context != null) {
            List<String> registerInfos = CacheManager.getRegisterInfos(context);
            if (registerInfos != null && registerInfos.size() > 0) {
                Collection arrayList = new ArrayList();
                for (String str : registerInfos) {
                    arrayList.clear();
                    ArrayList b = b(context, str);
                    if (b != null && b.size() > 0) {
                        for (int i = 0; i < b.size(); i++) {
                            CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) b.get(i);
                            try {
                                String decrypt = Rijndael.decrypt(cachedMessageIntent.intent);
                                if (!(e.a(decrypt) || Intent.parseUri(decrypt, 1).getLongExtra(MessageKey.MSG_ID, 0) == 0)) {
                                    arrayList.add(cachedMessageIntent);
                                }
                            } catch (Throwable e) {
                                com.tencent.android.tpush.a.a.c("MessageManager", "clearLocalCachedMsgIntentList", e);
                            }
                        }
                        b.removeAll(arrayList);
                        d(context, str, b);
                    }
                }
            }
        }
    }

    public ArrayList e(Context context) {
        if (context == null) {
            return null;
        }
        List<String> registerInfos = CacheManager.getRegisterInfos(context);
        if (registerInfos == null || registerInfos.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String b : registerInfos) {
            Collection b2 = b(context, b);
            if (b2 != null && b2.size() > 0) {
                arrayList.addAll(b2);
            }
        }
        return arrayList;
    }

    public ArrayList b(Context context, String str) {
        ArrayList arrayList;
        if (context != null) {
            try {
                if (!e.a(str)) {
                    Object b = b(context, str, ".tpns.msg.id.cached");
                    if (b != null) {
                        arrayList = (ArrayList) b;
                        if (arrayList != null) {
                            return new ArrayList();
                        }
                        return arrayList;
                    }
                }
            } catch (Throwable th) {
                return new ArrayList();
            }
        }
        arrayList = null;
        if (arrayList != null) {
            return arrayList;
        }
        return new ArrayList();
    }

    public void a(Context context, String str, Intent intent) {
        synchronized (c) {
            if (context != null) {
                if (!(e.a(str) || intent == null)) {
                    int i;
                    CachedMessageIntent cachedMessageIntent = new CachedMessageIntent();
                    cachedMessageIntent.pkgName = str;
                    cachedMessageIntent.msgId = intent.getLongExtra(MessageKey.MSG_ID, -1);
                    cachedMessageIntent.intent = Rijndael.encrypt(intent.toUri(1));
                    ArrayList b = b(context, str);
                    if (b == null) {
                        b = new ArrayList();
                    } else {
                        Collection arrayList = new ArrayList();
                        for (i = 0; i < b.size(); i++) {
                            CachedMessageIntent cachedMessageIntent2 = (CachedMessageIntent) b.get(i);
                            if (cachedMessageIntent2.equals(cachedMessageIntent)) {
                                arrayList.add(cachedMessageIntent2);
                            }
                        }
                        b.removeAll(arrayList);
                    }
                    i = b.size() / 2;
                    if (i > 1000) {
                        com.tencent.android.tpush.a.a.f("MessageManager", "too much cache msg, try to cut " + i);
                        for (int i2 = 0; i2 < i; i2++) {
                            com.tencent.android.tpush.a.a.f("MessageManager", "too much cache msg, delete " + b.remove(i2));
                        }
                    }
                    b.add(cachedMessageIntent);
                    d(context, str, b);
                }
            }
        }
    }

    public void d(Context context, String str, ArrayList arrayList) {
        synchronized (c) {
            if (!(context == null || arrayList == null)) {
                a(context, str, ".tpns.msg.id.cached", arrayList);
            }
        }
    }

    public void c(Context context, String str) {
        synchronized (c) {
            if (context != null) {
                d(context, str, new ArrayList());
            }
        }
    }

    public void a(Context context, String str, long j) {
        synchronized (c) {
            if (context != null) {
                ArrayList b = b(context, str);
                if (b != null && b.size() > 0) {
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < b.size(); i++) {
                        CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) b.get(i);
                        if (j == Intent.parseUri(Rijndael.decrypt(cachedMessageIntent.intent), 1).getLongExtra(MessageKey.MSG_BUSI_MSG_ID, -1)) {
                            arrayList.add(cachedMessageIntent);
                        }
                    }
                    b.removeAll(arrayList);
                }
                d(context, str, b);
            }
        }
    }

    public void b(Context context, String str, long j) {
        synchronized (c) {
            if (context != null) {
                ArrayList b = b(context, str);
                if (b != null && b.size() > 0) {
                    Collection arrayList = new ArrayList();
                    for (int i = 0; i < b.size(); i++) {
                        CachedMessageIntent cachedMessageIntent = (CachedMessageIntent) b.get(i);
                        if (cachedMessageIntent.msgId == j) {
                            arrayList.add(cachedMessageIntent);
                        }
                    }
                    if (arrayList != null && arrayList.size() == 0) {
                        com.tencent.android.tpush.a.a.h("MessageManager", "deleteCachedMsgIntentByPkgName do not have MessageId = " + j);
                    }
                    b.removeAll(arrayList);
                }
                d(context, str, b);
            }
        }
    }

    public void a(Context context, List list, ArrayList arrayList) {
        synchronized (c) {
            if (!(context == null || list == null)) {
                if (list.size() > 0) {
                    try {
                        Collection arrayList2 = new ArrayList();
                        if (arrayList != null && arrayList.size() > 0) {
                            CachedMessageIntent cachedMessageIntent;
                            HashMap hashMap = new HashMap();
                            for (int i = 0; i < arrayList.size(); i++) {
                                cachedMessageIntent = (CachedMessageIntent) arrayList.get(i);
                                for (CachedMessageIntent cachedMessageIntent2 : list) {
                                    if (cachedMessageIntent.equals(cachedMessageIntent2)) {
                                        arrayList2.add(cachedMessageIntent);
                                        Object obj = (ArrayList) hashMap.get(cachedMessageIntent2.pkgName);
                                        if (obj == null) {
                                            obj = new ArrayList();
                                        }
                                        hashMap.put(cachedMessageIntent2.pkgName, obj);
                                    }
                                }
                            }
                            arrayList.removeAll(arrayList2);
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                cachedMessageIntent = (CachedMessageIntent) it.next();
                                ArrayList arrayList3 = (ArrayList) hashMap.get(cachedMessageIntent.pkgName);
                                if (arrayList3 == null) {
                                    arrayList3 = new ArrayList();
                                }
                                arrayList3.add(cachedMessageIntent);
                                hashMap.put(cachedMessageIntent.pkgName, arrayList3);
                            }
                            for (String str : hashMap.keySet()) {
                                d(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Throwable e) {
                        com.tencent.android.tpush.a.a.c("MessageManager", "deleteCachedMsgIntent", e);
                    }
                }
            }
        }
    }

    public void c(Context context, List list) {
        synchronized (c) {
            if (!(context == null || list == null)) {
                if (list.size() > 0) {
                    try {
                        ArrayList b = b(context);
                        if (b != null && b.size() > 0) {
                            HashMap hashMap = new HashMap();
                            Iterator it = b.iterator();
                            while (it.hasNext()) {
                                ArrayList arrayList;
                                Object obj;
                                MessageId messageId = (MessageId) it.next();
                                ArrayList arrayList2 = (ArrayList) hashMap.get(messageId.pkgName);
                                if (arrayList2 == null) {
                                    arrayList2 = new ArrayList();
                                    hashMap.put(messageId.pkgName, arrayList2);
                                    arrayList = arrayList2;
                                } else {
                                    arrayList = arrayList2;
                                }
                                for (int i = 0; i < list.size(); i++) {
                                    if (messageId.id == ((TpnsPushClientReport) list.get(i)).msgId) {
                                        obj = null;
                                        break;
                                    }
                                }
                                int i2 = 1;
                                if (obj != null) {
                                    arrayList.add(messageId);
                                    hashMap.put(messageId.pkgName, arrayList);
                                }
                            }
                            for (String str : hashMap.keySet()) {
                                c(context, str, (ArrayList) hashMap.get(str));
                            }
                        }
                    } catch (Throwable e) {
                        com.tencent.android.tpush.a.a.c("MessageManager", "deleteMsgIdBatch", e);
                    }
                }
            }
        }
    }

    public void b(Context context, String str, MessageId messageId) {
        synchronized (c) {
            if (context != null) {
                if (!(e.a(str) || messageId == null)) {
                    ArrayList arrayList;
                    ArrayList a = a(context, str);
                    if (a == null) {
                        arrayList = new ArrayList();
                    } else {
                        for (int i = 0; i < a.size(); i++) {
                            if (((MessageId) a.get(i)).id == messageId.id) {
                                a.remove(i);
                                arrayList = a;
                                break;
                            }
                        }
                        arrayList = a;
                    }
                    arrayList.add(messageId);
                    c(context, str, arrayList);
                }
            }
        }
    }

    public boolean c(Context context, String str, long j) {
        if (!(context == null || e.a(str) || j <= 0)) {
            List<MessageId> a = a(context, str);
            if (a != null && a.size() > 0) {
                for (MessageId messageId : a) {
                    if (messageId.id == j) {
                        return messageId.a();
                    }
                }
            }
        }
        return false;
    }

    public boolean d(Context context, String str, long j) {
        if (!(context == null || e.a(str))) {
            List<CachedMessageIntent> b = b(context, str);
            if (b != null && b.size() > 0) {
                for (CachedMessageIntent cachedMessageIntent : b) {
                    if (cachedMessageIntent.msgId == j && str.equals(cachedMessageIntent.pkgName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void a(Intent intent, String str, MessageId messageId) {
        List list;
        long j = intent.getExtras().getLong(MessageKey.MSG_CREATE_MULTIPKG);
        long j2 = intent.getExtras().getLong("accId");
        List arrayList = new ArrayList();
        if (j == 0) {
            arrayList.add(intent.getPackage());
            list = arrayList;
        } else {
            list = j.a().a(l.e(), j2);
        }
        for (int i = 0; i < list.size(); i++) {
            try {
                String str2 = (String) list.get(i);
                if (e.a(str2)) {
                    com.tencent.android.tpush.a.a.c("MessageManager", ">> msg.appPkgName is null!");
                } else if (e.a(l.e(), str2, j2)) {
                    com.tencent.android.tpush.data.a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str2);
                    if (registerInfoByPkgName != null) {
                        if (registerInfoByPkgName.e > 0) {
                            d(l.e(), str2, new ArrayList());
                        } else if (!(d(l.e(), str2, messageId.id) || e.a(l.e(), registerInfoByPkgName.a).contains("@" + messageId.id + str2 + "@"))) {
                            if (c(l.e(), str2, messageId.id)) {
                                com.tencent.android.tpush.a.a.h(Constants.ServiceLogTag, ">> msgId:" + messageId.id + " has been acked.");
                            } else {
                                intent.setPackage(str2);
                                messageId.pkgName = str2;
                                if (messageId.id > 0) {
                                    b(l.e(), str2, messageId);
                                }
                                a(l.e(), str, intent);
                                c();
                                a(messageId.date, intent, str2);
                            }
                        }
                    }
                } else {
                    o.a().a(str2);
                    j.a().a(l.e(), str2);
                    d(l.e(), str2, new ArrayList());
                }
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c("MessageManager", "dispatchMessageOnTime", e);
            }
        }
    }

    public void a(String str, Intent intent, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        List a;
        if (e.a(str) || (!e.a(str) && simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(simpleDateFormat.format(new Date()))) == 0)) {
            if (e.a(intent)) {
                a = e.a(l.e(), str2 + Constants.RPC_SUFFIX);
                if (a == null || a.size() < 1) {
                    if (XGPushConfig.enableDebug) {
                        com.tencent.android.tpush.a.a.d(Constants.ServiceLogTag, ">> send message intent:" + intent);
                    }
                    l.e().sendBroadcast(intent);
                    return;
                }
                com.tencent.android.tpush.a.a.d(Constants.ServiceLogTag, ">> send rpc message intent:" + intent);
                a(intent);
            }
        } else if (!e.a(str) && simpleDateFormat.parse(str).compareTo(simpleDateFormat.parse(simpleDateFormat.format(new Date()))) < 0) {
            a = e.a(l.e(), str2 + Constants.RPC_SUFFIX);
            if (a == null || a.size() < 1) {
                l.e().sendBroadcast(intent);
            } else {
                a(intent);
            }
        }
    }

    private void a(TpnsPushMsg tpnsPushMsg, long j, com.tencent.android.tpush.service.channel.a aVar) {
        long currentTimeMillis = System.currentTimeMillis();
        MessageId messageId = new MessageId();
        messageId.id = tpnsPushMsg.msgId;
        messageId.isAck = (short) 0;
        messageId.accessId = tpnsPushMsg.accessId;
        messageId.host = e.b(aVar.d());
        messageId.port = aVar.e();
        messageId.pact = o.a(aVar.b());
        messageId.apn = e.e(l.e());
        messageId.isp = e.f(l.e());
        messageId.pushTime = j;
        messageId.serviceHost = l.e().getPackageName();
        messageId.receivedTime = currentTimeMillis;
        messageId.pkgName = tpnsPushMsg.appPkgName;
        messageId.busiMsgId = tpnsPushMsg.busiMsgId;
        messageId.timestamp = tpnsPushMsg.timestamp;
        messageId.msgType = tpnsPushMsg.type;
        messageId.multiPkg = tpnsPushMsg.multiPkg;
        messageId.date = tpnsPushMsg.date;
        long j2 = 259200000;
        if (tpnsPushMsg.ttl > 0) {
            j2 = (long) (tpnsPushMsg.ttl * 1000);
        } else if (tpnsPushMsg.msgId > 0 && tpnsPushMsg.ttl == 0) {
            j2 = 30000;
        }
        if (tpnsPushMsg.serverTime <= 0 || tpnsPushMsg.timestamp <= 0) {
            j2 += currentTimeMillis;
        } else {
            j2 += ((tpnsPushMsg.serverTime - tpnsPushMsg.timestamp) * 1000) + currentTimeMillis;
        }
        Intent intent = new Intent(Constants.ACTION_INTERNAL_PUSH_MESSAGE);
        intent.setPackage(tpnsPushMsg.appPkgName);
        intent.putExtra(MessageKey.MSG_ID, tpnsPushMsg.msgId);
        intent.putExtra("title", Rijndael.encrypt(tpnsPushMsg.title));
        intent.putExtra("content", Rijndael.encrypt(tpnsPushMsg.content));
        intent.putExtra("date", tpnsPushMsg.date);
        intent.putExtra("type", tpnsPushMsg.type);
        intent.putExtra("accId", tpnsPushMsg.accessId);
        intent.putExtra(MessageKey.MSG_BUSI_MSG_ID, tpnsPushMsg.busiMsgId);
        intent.putExtra(MessageKey.MSG_CREATE_TIMESTAMPS, tpnsPushMsg.timestamp);
        intent.putExtra(MessageKey.MSG_CREATE_MULTIPKG, tpnsPushMsg.multiPkg);
        intent.putExtra(MessageKey.MSG_SERVER_TIME, tpnsPushMsg.serverTime * 1000);
        intent.putExtra(MessageKey.MSG_TIME_GAP, currentTimeMillis - (tpnsPushMsg.serverTime * 1000));
        intent.putExtra(MessageKey.MSG_TTL, tpnsPushMsg.ttl * 1000);
        intent.putExtra(MessageKey.MSG_EXPIRE_TIME, j2);
        intent.putExtra(MessageKey.MSG_SERVICE_ACK, true);
        intent.putExtra(MessageKey.MSG_SERVICE_PACKAGE_NAME, l.f());
        try {
            intent.putExtra("enKeySet", k.a(new String[]{"title", "content"}));
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("MessageManager", "distribute2SDK", e);
        }
        a(intent, tpnsPushMsg.appPkgName, messageId);
    }

    public void a(ArrayList arrayList, long j, com.tencent.android.tpush.service.channel.a aVar) {
        b(arrayList, j, aVar);
    }

    public void a(Context context, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            int optInt;
            switch (jSONObject.optInt("action", 2)) {
                case 1:
                    for (String valueOf : jSONObject.optString("pushIdList", "").split(",")) {
                        a(context, str, Long.valueOf(valueOf).longValue());
                    }
                    return;
                case 2:
                    c(context, str);
                    return;
                case 3:
                    optInt = jSONObject.optInt("enabled", -1);
                    com.tencent.android.tpush.a.a.e("MessageManager", "setLogToFile with cmd = " + optInt);
                    com.tencent.android.tpush.a.a.a(optInt);
                    return;
                default:
                    return;
            }
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("MessageManager", "onCrtlMsgHandle", e);
        }
        com.tencent.android.tpush.a.a.c("MessageManager", "onCrtlMsgHandle", e);
    }

    public void a(TpnsPushMsg tpnsPushMsg) {
        String str = tpnsPushMsg.appPkgName;
        if (tpnsPushMsg.multiPkg == 1) {
            com.tencent.android.tpush.data.a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
            if (!(registerInfoByPkgName == null || e.a(registerInfoByPkgName.d))) {
                str = registerInfoByPkgName.d;
            }
        }
        a(l.e(), str, tpnsPushMsg.content);
    }

    public void b(ArrayList arrayList, long j, com.tencent.android.tpush.service.channel.a aVar) {
        if (!(l.e() == null || arrayList == null || arrayList.size() <= 0)) {
            com.tencent.android.tpush.a.a.b(0, arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                TpnsPushMsg tpnsPushMsg = (TpnsPushMsg) it.next();
                com.tencent.android.tpush.a.a.c("MessageManager", "distributeFromServer : accid=" + tpnsPushMsg.accessId + ",busiId=" + tpnsPushMsg.busiMsgId + ",pkg=" + tpnsPushMsg.appPkgName + ",msgId=" + tpnsPushMsg.msgId + ",type=" + tpnsPushMsg.type + ",ts=" + tpnsPushMsg.timestamp + ",multi=" + tpnsPushMsg.multiPkg + ",date=" + tpnsPushMsg.date + ",serverTime=" + tpnsPushMsg.serverTime + ",ttl=" + tpnsPushMsg.ttl);
                a(l.e(), tpnsPushMsg, j, aVar);
                if (e.a(tpnsPushMsg.appPkgName)) {
                    com.tencent.android.tpush.a.a.c("MessageManager", ">> messageDistribute, msg.appPkgName is null!");
                } else {
                    if (tpnsPushMsg.type == 3) {
                        a(tpnsPushMsg);
                    }
                    if (tpnsPushMsg.timestamp > 0) {
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        currentTimeMillis = (currentTimeMillis - (currentTimeMillis - tpnsPushMsg.serverTime)) - tpnsPushMsg.timestamp;
                        if (tpnsPushMsg.msgId >= 0 && tpnsPushMsg.ttl > 0 && ((long) tpnsPushMsg.ttl) < currentTimeMillis) {
                            com.tencent.android.tpush.a.a.h("MessageManager", "messageDistribute check server time failed, msg discarded cause msg is timeout, msg.ttl:" + tpnsPushMsg.ttl + "<reviseMaxTimeoutSec:" + currentTimeMillis);
                        }
                    }
                    a(tpnsPushMsg, j, aVar);
                }
            }
        }
        com.tencent.android.tpush.service.c.a.a(arrayList);
    }

    private synchronized void b() {
        if (b.a().b(true) > 0) {
            c();
        }
    }

    private void c() {
        if (this.h == null) {
            l.e().registerReceiver(new f(this), new IntentFilter("com.tencent.android.tpush.service.channel.cacheMsgBeatIntent"));
            this.h = PendingIntent.getBroadcast(l.e(), 0, new Intent("com.tencent.android.tpush.service.channel.cacheMsgBeatIntent"), 134217728);
        }
        t.a().a(0, System.currentTimeMillis() + a, this.h);
    }

    private void a(Context context, String str, String str2, ArrayList arrayList) {
        try {
            e.a(context, str + str2, Rijndael.encrypt(k.a((Serializable) arrayList)), true);
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("MessageManager", "putSettings", e);
        }
    }

    private Object b(Context context, String str, String str2) {
        try {
            return k.a(Rijndael.decrypt(e.a(context, str + str2, true)));
        } catch (Throwable e) {
            com.tencent.android.tpush.a.a.c("MessageManager", "getSettings", e);
            return null;
        }
    }

    public void a(Intent intent) {
        g.a().a(new g(this, intent));
    }
}
