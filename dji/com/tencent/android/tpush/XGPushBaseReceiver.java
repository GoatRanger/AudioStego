package com.tencent.android.tpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.b.h;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.d.e;
import java.util.List;

public abstract class XGPushBaseReceiver extends BroadcastReceiver {
    public static final int SUCCESS = 0;

    public abstract void onDeleteTagResult(Context context, int i, String str);

    public abstract void onNotifactionClickedResult(Context context, XGPushClickedResult xGPushClickedResult);

    public abstract void onNotifactionShowedResult(Context context, XGPushShowedResult xGPushShowedResult);

    public abstract void onRegisterResult(Context context, int i, XGPushRegisterResult xGPushRegisterResult);

    public abstract void onSetTagResult(Context context, int i, String str);

    public abstract void onTextMessage(Context context, XGPushTextMessage xGPushTextMessage);

    public abstract void onUnregisterResult(Context context, int i);

    public final void onReceive(Context context, Intent intent) {
        if (context != null && intent != null && p.a(context) <= 0) {
            String action = intent.getAction();
            if (Constants.ACTION_PUSH_MESSAGE.equals(action)) {
                a(context, intent);
            } else if (Constants.ACTION_FEEDBACK.equals(action)) {
                b(context, intent);
            } else {
                a.h(Constants.PushMessageLogTag, "未知的action:" + action);
            }
        }
    }

    private void a(Context context, Intent intent) {
        try {
            h a = h.a(context, intent);
            if (a != null && a.g() != null && a.g().b() == 2) {
                XGPushManager.msgAck(context, a);
                XGPushTextMessage xGPushTextMessage = new XGPushTextMessage();
                xGPushTextMessage.a = a.g().d();
                xGPushTextMessage.b = a.g().e();
                xGPushTextMessage.c = a.g().f();
                onTextMessage(context, xGPushTextMessage);
            }
        } catch (Throwable e) {
            a.c(Constants.PushMessageLogTag, "解包失败", e);
        } catch (Throwable e2) {
            a.c(Constants.PushMessageLogTag, "参数不对", e2);
        }
    }

    private void b(Context context, Intent intent) {
        int intExtra = intent.getIntExtra(Constants.FEEDBACK_TAG, -1);
        int intExtra2 = intent.getIntExtra(Constants.FEEDBACK_ERROR_CODE, -1);
        switch (intExtra) {
            case 1:
                XGPushRegisterResult xGPushRegisterResult = new XGPushRegisterResult();
                xGPushRegisterResult.parseIntent(intent);
                onRegisterResult(context, intExtra2, xGPushRegisterResult);
                return;
            case 2:
                onUnregisterResult(context, intExtra2);
                return;
            case 3:
                String decrypt = Rijndael.decrypt(intent.getStringExtra(Constants.FLAG_TAG_NAME));
                if (!e.a(decrypt)) {
                    int intExtra3 = intent.getIntExtra(Constants.FLAG_TAG_TYPE, -1);
                    if (intExtra3 == 1) {
                        onSetTagResult(context, intExtra2, decrypt);
                        return;
                    } else if (intExtra3 == 2) {
                        onDeleteTagResult(context, intExtra2, decrypt);
                        return;
                    } else {
                        a.h(Constants.PushMessageLogTag, "错误的标签处理类型：" + intExtra3 + " ,标签名：" + decrypt);
                        return;
                    }
                }
                return;
            case 4:
                intent.getIntExtra("action", 2);
                long longExtra = intent.getLongExtra("accId", 0);
                List accessidList = XGPushConfig.getAccessidList(context);
                if (accessidList != null && accessidList.size() > 0 && accessidList.contains(Long.valueOf(longExtra))) {
                    XGPushClickedResult xGPushClickedResult = new XGPushClickedResult();
                    xGPushClickedResult.parseIntent(intent);
                    onNotifactionClickedResult(context, xGPushClickedResult);
                    return;
                }
                return;
            case 5:
                XGPushShowedResult xGPushShowedResult = new XGPushShowedResult();
                xGPushShowedResult.parseIntent(intent);
                onNotifactionShowedResult(context, xGPushShowedResult);
                return;
            default:
                a.h(Constants.PushMessageLogTag, "未知的feedbackType:" + intExtra);
                return;
        }
    }
}
